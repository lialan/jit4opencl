/** unPython : Python to C compiler
Copyright (C) 2008  Rahul Garg

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License version 3 as published by
the Free Software Foundation.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.

Rahul Garg grants some additional permissions as per GNU General Public License version 3 
section 7. These additional permissions can be found in a file exceptions.txt.
*/

package org.unpython.newscala

import scala.collection.mutable.ListBuffer
import org.unpython.compiler.types._
import org.unpython.compiler._
import org.unpython.compiler.frontend._
import freemarker.template._
import java.io._
import java.util.ArrayList
import java.util.HashMap
import java.util.HashSet;

class Codegen{
  val mtemplate = new CodeGenWrapper().getModuleTemplate();

  def getChildren(tree:PyTree):List[PyTree] = {
    val buf = new ListBuffer[PyTree];
    for(i <- 0 until tree.getChildCount){
      buf.append(tree.getChild(i));    
    }
    return buf.toList;
  }

  def mapType(pytype:PyType):String ={
    pytype match{
      case x:PyInt32 => "int";
      case x:PyInt64 => "long";
      case x:PyFloat32 => "float";
      case x:PyFloat64 => "double";
      case x:PyArrayType => "PyArrayObject *";
      case x:PyObjectType => "PyObject *";
      case x:PyNoneType => "PyObject *";
      case _ => null;
    }
  }

  def fromALtoList[A](al:java.util.ArrayList[A]):List[A] ={
    val listbuf = new ListBuffer[A];
    for(i <- 0 until al.size){
      listbuf.append(al.get(i));
    }
    return listbuf.toList;
  }

  def isPyObject(pytype:PyType):boolean = {
    return pytype.isInstanceOf[PyObjectType];
  }

  def formatString(pytype:PyType):String = {
    pytype match{
      case x:PyInt32 => "i"
      case x:PyInt64 => "i"
      case x:PyFloat32 => "f"
      case x:PyFloat64 => "d"
      case x:PyObjectType => "O"
    }
  }

  def funCall(name:String,args:List[String]):String = {
    args match{
      //TODO: check if this fold is quadratic-time??
      case head::tail => name + "(" + ((head /: tail)(_ + "," + _)) + ")" 
      case List() => name + "()"
    }
  }

  def genSubCode(expr:PyTree,root:PyTree):String = {
    //println("genSubCode "+expr.toStringTree);
    val aname = expr.getChild(0).getNameString;
    val subexpr = expr.getChild(1);
    if(PyUtils.isPrimitive(expr.pytype)){
      val code = new StringBuilder("(*(("+mapType(expr.pytype)+"*)("+aname+"->data");
      for(i <- 0 until subexpr.getChildCount) code.append(" + "+aname + "->strides[" + i + "]*(" + genExprCode(subexpr.getChild(i),root)+")");
      code.append(")))");
      return code.toString;
    }else{
      //this can use funCall instead. but right now this is fine
      val code = new StringBuilder("unpython_slice("+aname+subexpr.getChildCount);
      for(i <- 0 until subexpr.getChildCount){
        val sub = subexpr.getChild(i);
        if(sub.pytype.isInstanceOf[PyInt64]){
          code.append(",1"+genExprCode(sub,root));
        }else{
          code.append(",3,");
          code.append(genExprCode(sub.getChild(0),root)+",");
          code.append(genExprCode(sub.getChild(1),root)+",");
          code.append(genExprCode(sub.getChild(2),root));
        }
      }
      code.append(")");
      return code.toString;
    }
  }

  def genExprCode(expr:PyTree,root:PyTree):String = {
  //println(expr.toStringTree);
    expr.getType match{
      case PythonParser.ADD => {
        val lchild = expr.getLeftChild;
        val rchild = expr.getRightChild;
        if(PyUtils.isPrimitive(lchild,root) && PyUtils.isPrimitive(rchild,root)){
          return genExprCode(lchild,root)+"+"+genExprCode(rchild,root);
        }else if(PyUtils.isPrimitive(lchild,root)){
          return funCall("PyNumber_Add",
            List(funCall("Py_BuildValue",List(formatString(lchild.pytype),genExprCode(lchild,root))),genExprCode(rchild,root)));
        }else if(PyUtils.isPrimitive(lchild,root)){
          return funCall("PyNumber_Add",
            List(funCall("Py_BuildValue",List(formatString(rchild.pytype),genExprCode(rchild,root))),genExprCode(lchild,root)));
        }else{
          return funCall("PyNumber_Add",List(genExprCode(lchild,root),genExprCode(rchild,root)))
        }

      }
      case PythonParser.SUB => {
        val lchild = expr.getLeftChild;
        val rchild = expr.getRightChild;
        if(PyUtils.isPrimitive(lchild,root) && PyUtils.isPrimitive(rchild,root)){
          return genExprCode(lchild,root)+"-"+genExprCode(rchild,root);
        }else if(PyUtils.isPrimitive(lchild,root)){
          return funCall("PyNumber_Subtract",
            List(funCall("Py_BuildValue",List(formatString(lchild.pytype),genExprCode(lchild,root))),genExprCode(rchild,root)));
        }else if(PyUtils.isPrimitive(lchild,root)){
          return funCall("PyNumber_Subtract",
            List(funCall("Py_BuildValue",List(formatString(rchild.pytype),genExprCode(rchild,root))),genExprCode(lchild,root)));
        }else{
          return funCall("PyNumber_Subtract",List(genExprCode(lchild,root),genExprCode(rchild,root)))
        }

      }
   case PythonParser.MUL => {
        val lchild = expr.getLeftChild;
        val rchild = expr.getRightChild;
        if(PyUtils.isPrimitive(lchild,root) && PyUtils.isPrimitive(rchild,root)){
          return genExprCode(lchild,root)+"*"+genExprCode(rchild,root);
        }else if(PyUtils.isPrimitive(lchild,root)){
          return funCall("PyNumber_Multiply",
            List(funCall("Py_BuildValue",List(formatString(lchild.pytype),genExprCode(lchild,root))),genExprCode(rchild,root)));
        }else if(PyUtils.isPrimitive(lchild,root)){
          return funCall("PyNumber_Multipy",
            List(funCall("Py_BuildValue",List(formatString(rchild.pytype),genExprCode(rchild,root))),genExprCode(lchild,root)));
        }else{
          return funCall("PyNumber_Multiply",List(genExprCode(lchild,root),genExprCode(rchild,root)))
        }

      }
   case PythonParser.DIV => {
        val lchild = expr.getLeftChild;
        val rchild = expr.getRightChild;
        if(PyUtils.isPrimitive(lchild,root) && PyUtils.isPrimitive(rchild,root)){
          return genExprCode(lchild,root)+"/"+genExprCode(rchild,root);
        }else if(PyUtils.isPrimitive(lchild,root)){
          return funCall("PyNumber_Divide",
            List(funCall("Py_BuildValue",List(formatString(lchild.pytype),genExprCode(lchild,root))),genExprCode(rchild,root)));
        }else if(PyUtils.isPrimitive(lchild,root)){
          return funCall("PyNumber_Divide",
            List(funCall("Py_BuildValue",List(formatString(rchild.pytype),genExprCode(rchild,root))),genExprCode(lchild,root)));
        }else{
          return funCall("PyNumber_Divide",List(genExprCode(lchild,root),genExprCode(rchild,root)))
        }

      }


      case PythonParser.NAME => return expr.getNameString;
      case PythonParser.CONST => {
        val tree = expr.getConstValue;
        if(tree.getType()== PythonParser.INT || tree.getType()==PythonParser.FLOAT){
          return tree.getText();
        }
      }
      case PythonParser.CALLMATH => {
        val funname = expr.getText;
        return "("+funname+"("+genExprCode(expr.getChild(0),root)+"))";
      }
      case PythonParser.CALLFUNC => {
        val func = expr.getCallFuncExpr();
        if(func.getType()==PythonParser.NAME){
          val funname = func.getNameString;
          val arglist = expr.getCallFuncArgList;
          return funCall(funname,getChildren(arglist).map(x => genExprCode(x,root)));
        }
      }
      case PythonParser.CALLMETH => {
        val func = expr.getCallFuncExpr;
        val arglist = expr.getCallFuncArgList;
        return funCall("PyObject_CallMethodObjArgs",genExprCode(func.getGetattrExpr,root)::"\""::func.getGetattrName::"\""::
          getChildren(arglist).map(x => genExprCode(x,root)):::List("NULL"));
      }
      case PythonParser.SUBSCRIPTG => {
        return genSubCode(expr,root);
      }
      case PythonParser.DIM => {
        return genExprCode(expr.getChild(0),root) + "->dimensions[" + genExprCode(expr.getChild(1),root) + "]";
      }
      case PythonParser.COMPARE => {
        val lchild = expr.getChild(0);
        val rchild = expr.getChild(2);
        var op:String = "";
        expr.getChild(1).getType match {
          case PythonParser.CEQ => op = "==";
          case PythonParser.CLT => op = "<"
          case PythonParser.CGT => op = ">"
          case PythonParser.CGTE => op = ">="
          case PythonParser.CLTE => op = "<="
        }
        return genExprCode(lchild,root) + op + genExprCode(rchild,root);
      }
      case PythonParser.CONVERT =>{
        expr.pytype match {
          case x:PyObjectType => return funCall("Py_BuildValue",List("\""+formatString(expr.getChild(0).pytype)+"\"",expr.getChild(0).getNameString));
          case x:PyFloat32 => return funCall("PyFloat_AS_FLOAT",List(genExprCode(expr.getChild(0),root)));
          case x:PyFloat64 => return funCall("PyFloat_AS_DOUBLE",List(genExprCode(expr.getChild(0),root)));
          case x:PyInt64 => return funCall("PyInt_AS_LONG",List(genExprCode(expr.getChild(0),root)));
          case x:PyInt32 => return funCall("PyInt_AS_INT",List(genExprCode(expr.getChild(0),root)));
        }
      }
      case PythonParser.GETATTR => return funCall("PyObject_GetAttrString",List(genExprCode(expr.getGetattrExpr,root),"\""+expr.getGetattrName+"\""));
      case PythonParser.CASTNODE =>{
        expr.pytype match{
            case x:PyFloat32 => return "(float)"+genExprCode(expr.getChild(0),root);
            case x:PyFloat64 => return "(double)"+genExprCode(expr.getChild(0),root);
            case x:PyInt32 => return "(int)"+genExprCode(expr.getChild(0),root);
            case x:PyInt64 => return "(long)"+genExprCode(expr.getChild(0),root);
        }
      }
    }
  return null;
  }
  
  def reverseLookup(map:HashMap[String,Integer],value:Integer):String = {
    val setiter = map.keySet.iterator;
    while(setiter.hasNext){
        val next = setiter.next;
        if(map.get(next)==value) return next;
    }
    return null;
  }

  def mapToNames(set:HashSet[Integer],fun:PyTree):HashSet[String] = {
    val setiter = set.iterator;
    val strings = new HashSet[String];
    while(setiter.hasNext){
        strings.add(reverseLookup(fun.map,setiter.next));
      }
    return strings;
      
  }
   def disableInnerParallel(stmt:PyTree):Unit = {
    for(i<- 0 to (stmt.getChildCount()-1)){
        val child = stmt.getChild(i);
        if(child.getType()==PythonParser.CFOR) child.annot = 0;
        disableInnerParallel(child);
    }
  }
  def genStmtCode(stmt:PyTree,fun:PyTree):String = {
  //println("got stmt in codegen "+stmt.toStringTree);
    stmt.getType match{
      //we dont have multiple assignments here
      //we have also taken care of set slice etc by now
      //we have also taken care of copy ops
      //so this has to be of the form : name = expression
      case PythonParser.ASSIGN => {
        val code = new StringBuilder;
        val expr = stmt.getAssignExpr;
        val assign = stmt.getAssignList.getChild(0);
        assign.getType match {
          case PythonParser.ASSNAME => {
            val assname = stmt.getAssignList.getChild(0).getAssName;
            code.append(assname+ "="+ genExprCode(expr,fun)+";\n");
            if(expr.getType==PythonParser.NAME && expr.pytype.isInstanceOf[PyObjectType]) code.append("Py_XINCREF("+assname+");\n");
          }
          case PythonParser.SUBSCRIPTS => {
            code.append(genSubCode(assign,fun) + "=" + genExprCode(expr,fun)+";\n");
          }
          case PythonParser.ASSATTR =>{
            return funCall("PyObject_SetAttrString",List(genExprCode(assign.getGetattrExpr,fun),"\""+assign.getGetattrName+"\"",genExprCode(expr,fun)))+";\n";
        }
      }
      return code.toString;

    }
    //we have taken care of any temporary variables etc by now
    //TODO : however we have to take care of decrefs here
    case PythonParser.RETURN => {
      val code = new StringBuilder;
      val expr = stmt.getReturnExpr;
      var rsym = -1;
      if(expr.getType == PythonParser.NAME) rsym = fun.map.get(expr.getNameString).intValue;
      val params = fun.getFunParams;
      val keyset = fun.map.keySet.toArray;
      for(name <- keyset){
        val sym = fun.map.get(name).intValue;
        val symtype = fun.module.table.getType(sym);
        if(!params.contains(name) && rsym !=sym && fun.localVars.contains(sym) && !PyUtils.isPrimitive(symtype)){
          code.append("Py_XDECREF("+name+");\n");
        }
      }
      code.append("return "+genExprCode(expr,fun)+";\n");
      return code.toString;   
    }
    case PythonParser.CFOR => {
      val code = new StringBuffer;
      val name = stmt.getForVar;
      if(stmt.annot==2){
        code.append(GpuCodegen.genGpuCode(stmt,fun));
        code.append("if(!unpython_gpu_success){\n");
        disableInnerParallel(stmt);
      }
      if(stmt.annot==1 || stmt.annot==2){
        code.append("#pragma omp parallel for private(");
        val privatevars = mapToNames(Liveness.liveInBody(stmt),fun);
        val varsiter = privatevars.iterator;
        while(varsiter.hasNext) {
          code.append(varsiter.next);
          if(varsiter.hasNext) code.append(",");
        }
        code.append(")\n");
      }
      code.append("for("+name+"=");
      val range = stmt.getChild(1);
      code.append(genExprCode(range.getChild(0),fun)+";");
      code.append(name+"<"+genExprCode(range.getChild(1),fun)+";");
      code.append(name+"+="+genExprCode(range.getChild(2),fun));
      code.append("){\n");
      for(fstmt <- getChildren(stmt.getForBody)) {
        //println("generating code for "+fstmt);
        code.append(genStmtCode(fstmt,fun));
      }
      code.append("}\n");
      if(stmt.annot==2) code.append("}\n");
      return code.toString;
    }
    case PythonParser.CIF => {
      val code = new StringBuffer;
      code.append("if(");
      code.append(genExprCode(stmt.getChild(0),fun));
      code.append("){\n");
      for(child <- getChildren(stmt.getChild(1))) code.append(genStmtCode(child,fun));
      code.append("goto "+stmt.getChild(2).getNameString+";\n");
      code.append("}\n");
      return code.toString;
    }
    case PythonParser.LABEL => {
      return stmt.getChild(0).getText + ";\n";
    }
    case PythonParser.STMT => {
      val code = new StringBuffer;
      getChildren(stmt).foreach(x => code.append(genStmtCode(x,fun)));
      return code.toString;

    }
  }
}

def genFunBody(tree:PyTree):String = {
  println("genFunBody");
  val body = tree.getFunBody;
  val lower = new TreeLowerer;
  lower.lowerFun(tree,false);
  Liveness.computeLiveVars(tree);
  println("lowered fun");
  val code = new StringBuilder;
  val params = tree.getFunParams;
  for(name <- tree.map.keySet.toArray){
    val variable = tree.map.get(name).intValue;
    if(tree.localVars.contains(variable) && !(params.contains(name))){
      val vartype = tree.module.table.getType(variable);
      code.append(mapType(vartype)+" "+name);
      if(isPyObject(vartype)) code.append(" = NULL;\n");
      else code.append(";\n");
    }
  }
  println("added params");
  for(stmt <- getChildren(body)) code.append(genStmtCode(stmt,tree));
  return code.toString;
}

def withSeparator(lst:List[String],separator:String):String = {
  lst match{
    case head::tail => (head /: tail)(_ + separator + _);
    case List() => ""; 
  }
}

//TODO : ugly ugly ugly. Fix this. This is like writing assembler
//TODO : I need to use templates here
def genWrapBody(tree:PyTree,cname:String):String = {
  val code = new StringBuilder;
  val funparams = fromALtoList(tree.getFunParams);
  val params = tree.pytype match{
    //get rid of self from method argument list
    case pytype:PyMethod => funparams.tail;
    case _ => funparams;
  };

  if(params.size>0){
    //we have more than 0 args and hence need to parse the arg tuple
    code.append("if(!PyArg_ParseTuple(args,\"");
    params.foreach(param => 
      code.append(formatString(tree.module.table.getType(tree.map.get(param).intValue)))
    );
  code.append("\"");
  params.foreach(param => code.append(",&"+param));
  code.append(")){\n return NULL;\n}\n");
}

code.append("unpython_returner = ");
val funname = tree.pytype match{
  case x:PyFunType => tree.getFunName;
  case x:PyMethod => cname+"_"+tree.getFunName;
}
code.append(funCall(funname,funparams)+";\n");
val ftype = tree.pytype match{
  case x:PyFunType => x
  case x:PyMethod => x.getFun();
}
val rtype = ftype.getReturnType;
if(!PyUtils.isPrimitive(rtype)) code.append("return unpython_returner;\n");
else code.append("return Py_BuildValue(\"" + formatString(rtype) + "\",unpython_returner);\n");
return code.toString;
}

def getFunModel(tree:PyTree,cname:String):java.util.Map[String,Object] = {
  val map = new HashMap[String,Object]();
  map.put("name",tree.getFunName);
  val ftype = tree.pytype match{
    case x:PyFunType => x;
    case x:PyMethod => x.getFun;
  }
  map.put("rtype",mapType(ftype.getReturnType));
  val params = tree.getFunParams;
  val paramdata = new ArrayList[HashMap[String,Object]]();
  for(i <- 0 until params.size){
    val temp = new HashMap[String,Object]();
    temp.put("name",params.get(i));
    temp.put("type",mapType(ftype.getParamType(i)));
    paramdata.add(temp);
  }
  map.put("params",paramdata);
  map.put("body",genFunBody(tree));
  tree.pytype match {
    case x:PyFunType => map.put("wrapbody",genWrapBody(tree,null));
    case x:PyMethod => map.put("wrapbody",genWrapBody(tree,cname));
  }
  return map;
}

def getCode(pytype:PyType):String = {
  pytype match{
    case x:PyInt32 => "T_INT"
    case x:PyInt64 => "T_INT"
    case x:PyFloat32 => "T_FLOAT"
    case x:PyFloat64 => "T_DOUBLE"
    case _ => "T_OBJECT_EX"
  }
}

def getClassModel(tree:PyTree):HashMap[String,Object] = {
  val map = new HashMap[String,Object]();
  map.put("name",tree.getClassName());
  val methods = new ArrayList[Object]();
  val superklass = tree.getSuperClasses.getChild(0).getNameString;
  val cname = tree.getClassName;
  for(method <- getChildren(tree.getMethods)){
    val methmodel = getFunModel(method,cname);
    methods.add(methmodel);
  }
  //println(cname + " "+tree.getClassInit.toStringTree);
  map.put("init",getFunModel(tree.getClassInit,cname));
  map.put("methods",methods);
  val ctype = tree.pytype.asInstanceOf[PyClassType];
  val members = new ArrayList[HashMap[String,Object]];
  for(i <- 0 until ctype.getMembers.size){
    val member = ctype.getMembers.get(i);
    val memmap = new HashMap[String,Object];
    memmap.put("name",member);
    memmap.put("code",getCode(ctype.getAttr(member)));
    memmap.put("type",mapType(ctype.getAttr(member)));
    members.add(memmap);
  }
  map.put("members",members);
  if(superklass.equals("object")) 
    map.put("superklass",null);
  else
    map.put("superklass",superklass);
  //println(superklass);
  return map;
}
    def getModuleName(mod:PyModule):String = {
        var name = mod.name;
        if(name.contains(File.separator)){
            val index = name.lastIndexOf(File.separator);
            name = name.substring(index+1);
        }
        return name;
    }

def dumpModuleCode(mod:PyModule):Unit = {
  try{
    val root = new HashMap[String,Object];
    root.put("name",getModuleName(mod));
    val functions = new ArrayList[Object];
    val classes = new ArrayList[Object];
    for(child <- getChildren(mod.tree.getModuleBody)){
      child.getType match {
        case PythonParser.FUNCTION => functions.add(getFunModel(child,null))
        case PythonParser.CLASS => classes.add(getClassModel(child));
        case _ => ;
      }
    }
    root.put("functions",functions);
    root.put("classes",classes);
    val stream = new FileWriter(new File(mod.name+".cpp"));
    mtemplate.process(root,stream);
  }catch{
    case ex:Exception => {
      ex.printStackTrace;
      System.exit(-1);
    }
  }
}
}
