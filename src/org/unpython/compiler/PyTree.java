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
package org.unpython.compiler;

import org.unpython.*;
import org.unpython.compiler.types.PyType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import org.antlr.runtime.tree.*;
import org.unpython.compiler.frontend.PythonParser;

class TreeIterator implements Iterator<PyTree>{
    private PyTree tree;
    private int index;
     
    public TreeIterator(PyTree tree){
        this.tree = tree;
        index = 0;
    }
    
    public boolean hasNext(){
        if(index<tree.getChildCount()){
            return true;
        }
        return false;
    }
    
    public PyTree next(){
        index++;
        return tree.getChild(index-1);
    }
    
    public void remove(){
        
    }
}

public class PyTree implements Iterable<PyTree>{
    public int symindex;
    public PyType pytype;
    public HashMap<String,Integer> map;
    public int annot;
    HashSet<Integer> closedVars;
    HashSet<Integer> globalVars;
    public HashSet<Integer> localVars;
    public PyModule module;
    private int itype;
    private String text;

    public void setText(String text) {
        this.text = text;
    }
    private ArrayList<PyTree> children;
    public PyTree(int itype){
        this.itype = itype;
        symindex = -1;
        annot = -1;
        children = new ArrayList<PyTree>();
    }
    
    public PyTree(Tree tree){
        itype = tree.getType();
        symindex = -1;
        annot = -1;
        text = tree.getText();
        children = new ArrayList<PyTree>();
        for(int i=0;i<tree.getChildCount();i++){
            children.add(new PyTree(tree.getChild(i)));
        }
    }
    
    public int getType(){
        return itype;
    }
    
    public void setType(int type){
        itype = type;
        if(type==PythonParser.COPY) text = "Copy";
    }
    
    public int getChildCount(){
        return children.size();
    }

    public String getText() {
        return text;
    }
    
    public Iterator<PyTree> iterator(){
        return new TreeIterator(this);
    }
    public PyTree getModuleBody(){
        assert getType()==PythonParser.MODULE;
        return getChild(1); 
    }
    
    public PyTree getAnnotBody(){
        assert getType()==PythonParser.ANNOT;
        return getChild(1);
    }
    
    public String toStringTree(){
        if(children.size()==0) return text+":"+pytype;
        StringBuilder ans = new StringBuilder("("+text+":"+pytype+" ");
        for(PyTree child:children){
            if(child==null)
                ans.append(" null ");
            else
                ans.append(child.toStringTree());
        }
        ans.append(")");
        return ans.toString();
    }
    
    public void setChild(int i,PyTree tree){
        if(i==children.size())
            children.add(tree);
        else 
            children.set(i, tree);
    }
    public int getAnnotation(){
        assert getType()==PythonParser.ANNOT;
        return Integer.parseInt(getChild(0).getChild(0).getText());
    }
    
    public PyTree getChild(int i){
        return children.get(i);
    }
    
    public String getFunName(){
    	assert getType()==PythonParser.FUNCTION;
        return getChild(1).getText();
    }
    
    public PyTree getFunDecorator(){
        return getChild(0).getChild(0);
    }
    public PyTree getRightChild(){
        return getChild(1);
    }
    public PyTree getLeftChild(){
        return getChild(0);
    }
    
    public String getNameString(){
        assert getType()==PythonParser.NAME;
        return getChild(0).getText();
    }
    
    public PyTree getAssignExpr(){
        assert getType()==PythonParser.ASSIGN;
        return getChild(1);
    }
    
    public void setAssignExpr(PyTree expr){
        assert getType()==PythonParser.ASSIGN;
        setChild(1,expr);
    }
     public PyTree getAssignList(){
        assert getType()==PythonParser.ASSIGN;
        return getChild(0);
    }
     
    public PyTree getCallFuncExpr(){
        assert getType()==PythonParser.CALLFUNC || getType()==PythonParser.CALLMETH;
    	return getChild(0);
    }
    
    public void setCallFuncExpr(PyTree tree){
        assert getType()==PythonParser.CALLFUNC;
        setChild(0,tree);
    }
    
    public PyTree getCallFuncArgList(){
        assert getType()==PythonParser.CALLFUNC || getType()==PythonParser.CALLMETH;
        return getChild(1);
    }
    
    public String getAssName(){
        assert getType()==PythonParser.ASSNAME;
        return getChild(0).getText();
        
    }
    
    public void setAssName(String s){
        assert getType()==PythonParser.ASSNAME;
        getChild(0).setText(s);
    }
    
    public PyTree getAssAttrExpr(){
        assert getType()==PythonParser.ASSATTR;
        return getChild(0);
    }
    
    public void setAssignList(PyTree tree){
        assert getType()==PythonParser.ASSIGN;
        setChild(0,tree);
    }
    
    public PyTree getConstValue(){
        assert getType()==PythonParser.CONST;
        return getChild(0);
    }
    
    public PyTree getFunBody(){
        assert getType()==PythonParser.FUNCTION;
        return getChild(3);
    }
    
    public ArrayList<String> getFunParams(){
        assert getType()==PythonParser.FUNCTION;
        ArrayList<String> params = new ArrayList<String>();
        PyTree plist = getChild(2);
        //System.out.println(plist.toStringTree());
        for(int i=0;i<plist.getChildCount();i++){
            params.add(plist.getChild(i).getText());
        }
        return params;
    }
    
    public ArrayList<String> getGlobalNames(){
        assert getType()==PythonParser.GLOBAL;
        ArrayList<String> names = new ArrayList<String>();
        for(PyTree child:getChild(0)){
            names.add(child.getText());
        }
        return names;
    }
    
    public PyTree getReturnExpr(){
        assert getType()==PythonParser.RETURN;
        return getChild(0);
    }
    
    public void setReturnExpr(PyTree tree){
        assert getType()==PythonParser.RETURN;
        setChild(0,tree);
    }
    
    public PyTree getDiscardExpr(){
        assert getType()==PythonParser.DISCARD;
        return getChild(0);
    }
    public String getClassName(){
        return getChild(0).getText();
    }
    public PyTree getGetattrExpr(){
        assert getType()==PythonParser.GETATTR || getType()==PythonParser.ASSATTR: toStringTree();
        return getChild(0);
    }
    
    public void setGetattrExpr(PyTree expr){
        assert getType()==PythonParser.GETATTR || getType()==PythonParser.ASSATTR;
        setChild(0,expr);
    }
    public String getGetattrName(){
        assert getType()==PythonParser.GETATTR || getType()==PythonParser.ASSATTR;
        return getChild(1).getText();
    }
    
    public void insertChild(int i,PyTree tree){
        children.add(i,tree);
    }
    
    public void removeChild(int i){
        children.remove(i);
    }
    
    public String getForVar(){
        assert getType()==PythonParser.FOR || getType()==PythonParser.CFOR || getType()==PythonParser.PARFOR;
        return getChild(0).getAssName();
    }
    
    public void setForVar(String var){
         assert getType()==PythonParser.FOR || getType()==PythonParser.CFOR || getType()==PythonParser.PARFOR;
         getChild(0).setAssName(var);
    }
    
    public PyTree getForBody(){
        assert getType()==PythonParser.FOR || getType()==PythonParser.CFOR || getType()==PythonParser.PARFOR;
        return getChild(2);
    }
    
    public PyTree getForExpr(){
        assert getType()==PythonParser.FOR || getType()==PythonParser.PARFOR;
        return getChild(1);
    }
    
    public void setForBody(PyTree body){
        assert getType()==PythonParser.FOR || getType()==PythonParser.CFOR || getType()==PythonParser.PARFOR;
        setChild(2,body);
    }

   public PyTree getAttrExpr(){
       assert getType()==PythonParser.GETATTR;
       return getChild(0);
   }
   
   public String getAttrName(){
       assert getType()==PythonParser.GETATTR;
       return getChild(1).getText();
   }
   
   public int getPyString(){
       assert getType()==PythonParser.PYSTR;
       return Integer.parseInt(getChild(0).getText());
   }
   
   public PyTree getIfList(){
       assert getType()==PythonParser.IF;
       return getChild(0);
   }
   
   public PyTree getIfElse(){
       assert getType()==PythonParser.IF;
       return getChild(1);
   }
   
   public PyTree getSubBase(){
       assert getType()==PythonParser.SUBSCRIPTG || getType()==PythonParser.SUBSCRIPTS;
       return getChild(0);
   }
   
   public void setSubBase(PyTree base){
       assert getType()==PythonParser.SUBSCRIPTG || getType()==PythonParser.SUBSCRIPTS;
       setChild(0,base);
   }
   
   public PyTree getSubScript(){
        assert getType()==PythonParser.SUBSCRIPTG || getType()==PythonParser.SUBSCRIPTS;
        return getChild(1);
   
   }
   
   public void setSubScript(PyTree sub){
       assert getType()==PythonParser.SUBSCRIPTG || getType()==PythonParser.SUBSCRIPTS;
       setChild(1,sub);
   }
   
   public PyTree getSuperClasses(){
       assert getType()==PythonParser.CLASS;
       return getChild(1);
   }
   
   public PyTree getMethods(){
       assert getType()==PythonParser.CLASS;
       return getChild(2);
   }

   public PyTree getClassInit(){
	   assert getType()==PythonParser.CLASS;
	   return getChild(3);
   }
}
