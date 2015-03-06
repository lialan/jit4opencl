package org.unpython.newscala
import org.unpython.compiler._
import org.unpython.compiler.types._
import org.unpython.compiler.frontend._
import scala.collection.jcl.HashSet;
import java.util.HashMap;
import java.util.ArrayList;
object GpuCodegen{

  def writtenVarsHelper(tree:PyTree,set:HashSet[Integer]):Unit = {
    tree.getType match{
      case PythonParser.CFOR => {
        set += (tree.getChild(0).symindex);
      }
      case PythonParser.ASSNAME => {
        set += (tree.symindex);
      }
      case _ => {}
    }
    for(i <- 0 until tree.getChildCount) writtenVarsHelper(tree.getChild(i),set);
  }

  def writtenVars(tree:PyTree,fun:PyTree):HashSet[Integer] = {
    val written = new HashSet[Integer];
    writtenVarsHelper(tree,written);
    return written;

  }

  def setDifference(s1:HashSet[Integer],s2:HashSet[Integer]):HashSet[Integer] = {
    val diff = s1.clone;
    for(next <- s2) diff.remove(next);
    return diff;
  }

  def setIntersection(s1:HashSet[Integer],s2:HashSet[Integer]):HashSet[Integer] = {
    var inters = new HashSet[Integer];
    for(next <- s1){
      if(s2.contains(next)) inters += (next);
    }
    return inters;
  }
  //TODO: check that subscripts are valid LMAD
  def buildTableHelper(tree:PyTree,invariants:HashSet[Integer],loopVars:HashSet[Integer],list:ArrayList[PyTree]):Unit = {
    if(tree.getType==PythonParser.SUBSCRIPTG || tree.getType==PythonParser.SUBSCRIPTS){
      list.add(tree);
      tree.annot = list.size()-1;
    }
    else{
        for(i <- 0 until tree.getChildCount) buildTableHelper(tree.getChild(i),invariants,loopVars,list);
    }
  }

  def buildLmadTable(stmt:PyTree,invariants:HashSet[Integer],loopVars:HashSet[Integer]):ArrayList[PyTree]={
    val list = new ArrayList[PyTree];
    buildTableHelper(stmt,invariants,loopVars,list);
    return list;
  }

  def getLoops(stmt:PyTree):Array[PyTree] = {
    def getLoopsHelper(tree:PyTree,set:HashSet[PyTree]):Unit = {
        if(tree.getType==PythonParser.CFOR) set += tree;
        for(i <- 0 until tree.getChildCount) getLoopsHelper(tree.getChild(i),set);
    }
    val set = new HashSet[PyTree];
    getLoopsHelper(stmt,set);
    return set.toArray;
  }

  def buildVarsMap(tree:PyTree,map:HashMap[Integer,Integer],invariant:HashSet[Integer]):Unit = {
    if(tree.getType==PythonParser.NAME || tree.getType==PythonParser.ASSNAME){
      if(!invariant.contains(tree.symindex))
        if(map.get(tree.symindex)==null) 
        map.put(tree.symindex,map.size());    
    }else{
      for(i <- 0 until tree.getChildCount) buildVarsMap(tree.getChild(i),map,invariant);
    }
  }
  def exprToString(tree:PyTree):String = {
    tree.getType match {
        case PythonParser.NAME => return tree.getNameString;
        case PythonParser.CONST => return tree.getChild(0).getText;
        case PythonParser.ADD =>{
            return exprToString(tree.getLeftChild)+"+"+exprToString(tree.getRightChild);
        }
        case PythonParser.SUB =>{
            return exprToString(tree.getRightChild)+"-"+exprToString(tree.getRightChild);
        }
        case PythonParser.MUL => {
            val buf = new StringBuffer;
            buf.append("("+exprToString(tree.getLeftChild)+")");
            buf.append("*");
            buf.append("("+exprToString(tree.getRightChild)+")");
            return buf.toString;
        }
    }
  }

  def containsVar(tree:PyTree,lvar:Integer):Boolean ={
    tree.getType match{
        case PythonParser.NAME =>{
            if(tree.symindex==lvar) true;
            else false;
        }
        case PythonParser.ADD | PythonParser.SUB | PythonParser.MUL =>{
            if(containsVar(tree.getLeftChild,lvar) || containsVar(tree.getRightChild,lvar)) true;
            else false;
        }
        case _ => false;
    }
  }

  def simplifySubConst(sub:PyTree,loopVars:Array[Integer],buf:StringBuffer):Unit = {
    def isConstant(tree:PyTree):Boolean = {
        for(loopVar <- loopVars.elements) if(containsVar(tree,loopVar)) return false;
        return true;
    }
    sub.getType match{
        case PythonParser.NAME => {
            if(isConstant(sub)) buf.append(sub.getNameString);
            else buf.append("0");
        }
        case PythonParser.CONST => {
            buf.append(sub.getChild(0).getText);
        }
        case PythonParser.ADD =>{
            simplifySubConst(sub.getLeftChild,loopVars,buf);
            buf.append("+");
            simplifySubConst(sub.getRightChild,loopVars,buf);
        }
        case PythonParser.SUB =>{
            simplifySubConst(sub.getLeftChild,loopVars,buf);
            buf.append("-");
            simplifySubConst(sub.getRightChild,loopVars,buf);
        }
        case PythonParser.MUL =>{
            buf.append("(");
            simplifySubConst(sub.getLeftChild,loopVars,buf);
            buf.append(")*(");
            simplifySubConst(sub.getRightChild,loopVars,buf);
            buf.append(")");
        }
        case _ =>{
            buf.append("0"); 
        }
    }
  }
  def simplifySub(sub:PyTree,loopVar:Integer,loopVars:Array[Integer],buf:StringBuffer):Unit = {
    sub.getType match{
      case PythonParser.NAME => {
        if(sub.symindex==loopVar) buf.append("1");
        else buf.append("0");
      }
      case PythonParser.ADD => {
        val buf1 = new StringBuffer;
        val buf2 = new StringBuffer;
        simplifySub(sub.getLeftChild,loopVar,loopVars,buf1);
        simplifySub(sub.getRightChild,loopVar,loopVars,buf2);
        buf.append(buf1.toString);
        buf.append("+");
        buf.append(buf2.toString);
      }
      case PythonParser.SUB => {
        val buf1 = new StringBuffer;
        val buf2 = new StringBuffer;
        simplifySub(sub.getLeftChild,loopVar,loopVars,buf1);
        simplifySub(sub.getRightChild,loopVar,loopVars,buf2);
        buf.append(buf1.toString);
        buf.append("-");
        buf.append(buf2.toString);
      }
      case PythonParser.MUL => {
        if(containsVar(sub.getLeftChild,loopVar)){
            val buf1 = new StringBuffer;
            simplifySub(sub.getLeftChild,loopVar,loopVars,buf1);
            val coef = exprToString(sub.getRightChild);
            buf.append(coef+"*("+buf1.toString+")");
        }else if(containsVar(sub.getRightChild,loopVar)){
            val buf1 = new StringBuffer;
            simplifySub(sub.getRightChild,loopVar,loopVars,buf1);
            val coef = exprToString(sub.getLeftChild);
            buf.append(coef+"*("+buf1.toString+")");
        }else{
            buf.append("0");
        }

      }
      case _ => {
        buf.append("0");
      }
    }
      
  }
  def simplifyLmad(lmad:PyTree,loopVars:Array[Integer]):ArrayList[String] = {
    val nloops = loopVars.size;
    val repr = new ArrayList[String];
    val aname = lmad.getSubBase.getNameString;
    val subs = lmad.getSubScript;
    println(aname);
    for(i <- 0 until nloops){
        val loopVar = loopVars(i);
        val strbuffer = new StringBuffer;
        strbuffer.append("0");
        //this will actually be a subset of cases we can theoretically deal with
        //TODO: correct this implementation
        for(j <- 0 until subs.getChildCount){
            //Subscript must be of sum-of-products form.
            val sub = subs.getChild(j);
            strbuffer.append("+"+aname+"->strides["+j+"]*(");
            simplifySub(sub,loopVar,loopVars,strbuffer);
            strbuffer.append(")");
        }
        repr.add(strbuffer.toString);
    }
    //Now compute the constant term
    val strbuffer = new StringBuffer;
    strbuffer.append("(long int)("+aname+"->data)");
    for(j <- 0 until subs.getChildCount){
        val sub = subs.getChild(j);
        strbuffer.append("+"+aname+"->strides["+j+"]*(");
        simplifySubConst(sub,loopVars,strbuffer);
        strbuffer.append(")");
    }
    repr.add(strbuffer.toString);
    
    return repr;
  }

  def checkLmadTableType(lmads:ArrayList[PyTree]):Boolean = {
    if(lmads.size==0) return true;
    val ptype = lmads.get(0);
    for(i <- 0 until lmads.size){
        if(!ptype.equals(lmads.get(i).pytype)) return false;
    }
    return true;
  }
  
  def gpuType(t:PyType):String = {
    t match{
        case x:PyInt32 => "( int 1 0 )";
        case x:PyFloat32 => "( float 1 0 )";
        case x:PyFloat64 => "( double 1 0 )";
    }
  }

  def gpuOperator(t:Int):String = {
    t match{
      case PythonParser.ADD => "+";
      case PythonParser.SUB => "-";
      case PythonParser.MUL => "*";
      case PythonParser.DIV => "/";
    }
  }
  def genGpuTreeHelper(stmt:PyTree,fun:PyTree,map:HashMap[Integer,Integer],invariant:HashSet[Integer],buf:StringBuffer):Unit = {
    buf.append("( ");
    stmt.getType match{
        case PythonParser.CFOR => {
            if(stmt.annot==2){
              buf.append("pfor ( float 0 0 ) 0 0 0 2 ( id ( int 1 0 ) "+map.get(fun.map.get(stmt.getForVar))+" 0 0 0 ) ");
            }
            else {
              buf.append("for ");
              val loopVar = map.get(fun.map.get(stmt.getForVar));
              buf.append("( float 0 0 ) 0 0 0 4 ");
              buf.append("( = ( int 1 0 ) 0 0 0 2 ( id ( int 1 0 ) " + loopVar + " 0 0 0 ) ( constint ( int 1 0 ) 0 0 0 0 ) ) ");
              buf.append("( lt ( int 1 0 ) 0 0 0 2 ( id ( int 1 0 ) " + loopVar + " 0 0 0 ) ( constint ( int 1 0 ) 0 0 0 0 ) ) ");
              buf.append("( = ( int 1 0 ) 0 0 0 2 ( id ( int 1 0 ) " + loopVar + " 0 0 0 ) ");
              buf.append("( + ( int 1 0 ) 0 0 0 2 ( id ( int 1 0 ) "+loopVar+" 0 0 0 ) ( constint ( int 1 0 ) 1 0 0 0 ) ) ) ");
            }
            genGpuTreeHelper(stmt.getChild(2),fun,map,invariant,buf);
        }
        case PythonParser.STMT => {
            buf.append(" stmtlist ( float 0 0 ) 0 0 0 "+stmt.getChildCount+" ");
            for(i<- 0 until stmt.getChildCount) {
              genGpuTreeHelper(stmt.getChild(i),fun,map,invariant,buf);
              buf.append(" ");
            }
        }
        case PythonParser.ASSIGN => {
            buf.append(" = ");
            buf.append(" "+gpuType(stmt.getChild(0).getChild(0).pytype)+" 0 0 0 2 ");
            genGpuTreeHelper(stmt.getChild(0).getChild(0),fun,map,invariant,buf);
            buf.append(" ");
            genGpuTreeHelper(stmt.getChild(1),fun,map,invariant,buf);
            buf.append(" ");
        }
        case PythonParser.SUBSCRIPTS => {
          buf.append(" unpy_awrite ");
          buf.append(" "+gpuType(stmt.pytype)+" "+stmt.annot+" 0 0 0");
        }
        case PythonParser.SUBSCRIPTG => {
          buf.append(" unpy_aread ");
          buf.append(" "+gpuType(stmt.pytype)+" "+stmt.annot+" 0 0 0");
        }
        case PythonParser.ASSNAME => {
            buf.append(" id "+gpuType(stmt.pytype)+" "+map.get(fun.map.get(stmt.getAssName()))+" 0 0 0");
        }
        case PythonParser.NAME => {
          if(invariant.contains(stmt.symindex)){
            stmt.pytype match{
              case x:PyInt32 => buf.append(" constint ( int 1 0 ) \"<<" + stmt.getNameString() + "<<\" 0 0 0 ");    
              case x:PyFloat32 => buf.append(" constfloat ( float 1 0 ) 0 \"<<" + stmt.getNameString() + "<<\" 0 0 ");    
              case x:PyFloat64 => buf.append(" constdouble ( double 1 0 ) 0 0 \"<<" + stmt.getNameString() + "<<\" 0 ");    
            }
          }else{
            buf.append(" id ");
            buf.append(" "+gpuType(stmt.pytype)+" "+map.get(stmt.symindex)+" 0 0 0");
          }
        }
        case PythonParser.ADD | PythonParser.SUB | PythonParser.MUL | PythonParser.DIV => {
           buf.append(" "+gpuOperator(stmt.getType)+" "+gpuType(stmt.pytype)+" 0 0 0 2 ");
           if(needToCast(stmt.pytype,stmt.getChild(0).pytype)){
             buf.append(" ( cast "+gpuType(stmt.pytype)+" 0 0 0 1 ");
             genGpuTreeHelper(stmt.getChild(0),fun,map,invariant,buf);
             buf.append(" ) ");
           }else{
             genGpuTreeHelper(stmt.getChild(0),fun,map,invariant,buf);
           }
           buf.append(" ");
           if(needToCast(stmt.pytype,stmt.getChild(1).pytype)){
             buf.append(" ( cast "+gpuType(stmt.pytype)+" 0 0 0 1 ");
             genGpuTreeHelper(stmt.getChild(1),fun,map,invariant,buf);
             buf.append(" ) ");
           }else{
             genGpuTreeHelper(stmt.getChild(1),fun,map,invariant,buf);
           }
           buf.append(" ");
        }
        case PythonParser.CASTNODE => {
            if(stmt.pytype.isInstanceOf[PyFloat32]) buf.append(" constfloat ( float 1 0 ) 0 "+stmt.getChild(0).getChild(0).getText()+ " 0 0 ");
            if(stmt.pytype.isInstanceOf[PyFloat64]) buf.append(" constdouble ( double 1 0 ) 0 0 "+stmt.getChild(0).getChild(0).getText()+ " 0 ");

        }
        case _ => {println("Could not understand " + stmt.toStringTree); buf.append(" constint ( int 0 0 ) 0 0 0 0 "); }
    }
    buf.append(" )");
  }
  def needToCast(tref:PyType,ttest:PyType):Boolean = {
    if(tref.equals(ttest)) return false;
    else return true;
  }
  def genGpuTree(stmt:PyTree,fun:PyTree,map:HashMap[Integer,Integer]):String = {
    val buf = new StringBuffer;
    buf.append("\"");
    val invariant = new HashSet[Integer](stmt.liveBefore);
    genGpuTreeHelper(stmt,fun,map,invariant,buf);
    buf.append("\"");
    return buf.toString;
  }
  def genGpuCode(stmt:PyTree,fun:PyTree):String = {
    val code = new StringBuffer;
    code.append("unpython_gpu_success=false;\n");
    val varsWritten = writtenVars(stmt,fun);
    val liveBefore = new HashSet(stmt.liveBefore);
    val invariant = setDifference(liveBefore,varsWritten);
    val globalsWritten = setIntersection(liveBefore,varsWritten);
    if(globalsWritten.size>0) return code.toString;
    val loops = getLoops(stmt);
    val loopVars = new HashSet[Integer];
    for(loop <-loops.elements) loopVars += loop.getChild(0).symindex;
    val lmads = buildLmadTable(stmt,invariant,loopVars);
    val varMap = new HashMap[Integer,Integer];
    val loopVarsArray = new Array[Integer](loops.size);
    for(i <- 0 until loops.size) loopVarsArray(i) = loops(i).getChild(0).symindex;
    buildVarsMap(stmt,varMap,new HashSet[Integer](stmt.liveBefore));
    println(varMap);
    code.append("if(unpyIsGpuEnabled()){\n");
    code.append("long int unpython_lmads["+lmads.size+"]["+(loopVars.size+1)+"];\n"); 
    code.append("int unpython_loopvars["+loopVars.size+"];\n");
    code.append("int unpython_pvars["+loopVars.size+"];\n");
    code.append("int unpython_regs["+loopVars.size+"];\n");
    code.append("bool unpython_writes["+lmads.size+"];\n");
    code.append("long int unpython_ubounds["+loopVars.size+"];\n");
    code.append("int unpython_domain[2];\n");
    code.append("unpython_domain[0] = "+varMap.get(fun.map.get(stmt.getForVar))+";\n");
    code.append("unpython_domain[1] = "+varMap.get(fun.map.get(stmt.getChild(2).getChild(0).getForVar))+";\n");
    for((value,i) <- loopVarsArray.elements.zipWithIndex){
      code.append("//"+loops(i).getForVar+"\n");
      code.append("unpython_loopvars["+i+"] = "+varMap.get(value)+";\n");
      code.append("unpython_ubounds["+i+"] = "+exprToString(loops(i).getChild(1).getChild(1))+";\n");
      if(loops(i).annot==2) code.append("unpython_pvars["+i+"]=1;\n");
      else code.append("unpython_pvars["+i+"]=0;\n");
      val pvars = Liveness.liveInBody(loops(i)).toArray;
      var regs = 0;
      for(pvar<-pvars.elements){
        if(PyUtils.isPrimitive(fun.module.table.getType(pvar.asInstanceOf[Int]))) regs+=1;
      }
      code.append("unpython_regs["+i+"]="+regs+";\n");

    }
    val ptype = lmads.get(0).pytype;
    for(i <- 0 until lmads.size) {
      val lmad = lmads.get(i);
      val strides = simplifyLmad(lmad,loopVarsArray);
      for(j <- 0 until strides.size){
        code.append("unpython_lmads["+i+"]["+j+"] = " + strides.get(j)+";\n");
      }
      code.append("unpython_writes["+i+"] = ");
      if(lmads.get(i).getType==PythonParser.SUBSCRIPTG) code.append("false;\n");
      else code.append("true;\n");

    }
    val ltype = ptype match{
      case x:PyFloat32 => "UNPY_FLOAT";
      case x:PyInt32 => "UNPY_INT";
      case x:PyFloat64 => "UNPY_DOUBLE";
    }
    code.append("std::stringstream unpython_tree;\n unpython_tree<<"+genGpuTree(stmt,fun,varMap)+";\n");
    code.append("string unpython_tree_str = unpython_tree.str();\n");
    code.append("unpython_gpu_success=execOnGpu((long *)unpython_lmads,unpython_writes,unpython_ubounds,unpython_tree_str,unpython_loopvars,unpython_pvars,unpython_domain,unpython_regs,"+lmads.size+","+loopVars.size+","+ltype+");\n");
    code.append("cout<<\"GPU success \"<<unpython_gpu_success<<endl;\n");
    code.append("}\n");
    return code.toString;
  }

}
