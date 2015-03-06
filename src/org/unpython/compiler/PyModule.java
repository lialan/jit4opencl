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

import java.util.ArrayList;

import org.unpython.compiler.frontend.AnnotationParser;
import org.unpython.compiler.frontend.PythonParser;
import org.unpython.compiler.types.*;

public class PyModule {

    public PyTree tree;
    public PySymtable table;
    private ArrayList<String> strlist;
    public String name;

    public PyModule(String name, PyTree tree) {
        assert tree.getType() == PythonParser.MODULE;
        this.tree = tree;
        PyUtils.initNameSpace(this.tree);
        this.name = name;
        table = new PySymtable(this);
        strlist = PyUtils.getStringList(name);
        fixAnnot(tree);
        addModule(tree);
    }
    
    //small utility method to set module fields of all children recursively to this
    private void addModule(PyTree tree) {
        tree.module = this;
        for (PyTree child : tree) {
            addModule(child);
        }
    }

    private void fixAnnot(PyTree tree) {
        for (int i = 0; i < tree.getChildCount(); i++) {
            PyTree child = tree.getChild(i);
            if (child.getType() == PythonParser.ANNOT) {
                PyTree stmt = child.getAnnotBody();
                stmt.annot = child.getAnnotation();
                tree.setChild(i, stmt);
            }
        }
        for (PyTree child : tree) {
            fixAnnot(child);
        }
    }

    public String getString(int i) {
        return strlist.get(i);
    }

    private void parseClassType(PyTree tree,PyTree root) {
        String cname = tree.getClassName();
        PyClassType ctype = new PyClassType(cname);
        tree.pytype = ctype;
        int index = table.createEntry(tree.pytype);
        root.map.put(cname,index);
        PyUtils.initNameSpace(tree);
        //check super classes exist 
        PyTree supers = tree.getSuperClasses();
        for (PyTree sclass : supers) {
            String sname = sclass.getNameString();
			System.out.println("Found super class "+sname);
            if (!sname.equals("object")) {
                PyType stype = table.getType(root.map.get(sname));
                if (!(stype instanceof PyClassType)) {
                    throw new RuntimeException("Super class not found " + sname);
                }
                ctype.addSuper((PyClassType) stype);
            }
        }
       
	   	//delete __init__ from the list of methods

		for(int i=0;i<tree.getMethods().getChildCount();i++){
			PyTree method = tree.getMethods().getChild(i);
			if(method.getFunName().equals("__init__")){
			   	tree.getMethods().removeChild(i);
				i--;
			}
		}	
        //add all methods
        for (PyTree method : tree.getMethods()) {
            if(tree.map.containsKey(method.getFunName())){
                throw new RuntimeException("Class "+cname+" already contains method"+method.getFunName());
            }
            PyTree decorator = method.getFunDecorator();
            method.pytype = new PyMethod((PyFunType)new AnnotationParser().parseFunType(decorator,this));
            ctype.setAttr(method.getFunName(),method.pytype);     
            tree.map.put(method.getFunName(), tree.module.table.createEntry(method.pytype));
        }
		//add init
		PyTree init = tree.getClassInit();
		PyTree decorator = init.getFunDecorator();
		init.pytype = new PyMethod((PyFunType)new AnnotationParser().parseConsType(decorator,this));
        
        //add attributes 
        new AnnotationParser().parseClassMembers(getString(tree.annot), ctype, this);

    }

    public void parseTopLevelBindings() {
        PyTree body = tree.getModuleBody();
        for (int i = 0; i < body.getChildCount(); i++) {
            PyTree child = body.getChild(i);
            switch (child.getType()) {
                case PythonParser.FUNCTION: {
                    PyTree decorator = child.getFunDecorator();
                    child.pytype = new AnnotationParser().parseFunType(decorator,this);
                    int index = table.createEntry(child.pytype);
                    tree.map.put(child.getFunName(), index);
                    tree.localVars.add(index);
                    break;
                }
                case PythonParser.ASSIGN: {
                    PyTree list = child.getAssignList();
                    for (PyTree ass : list) {
                        if (!(ass.getType() == PythonParser.ASSNAME)) {
                            throw new RuntimeException("Can only have assignment to names at module level");
                        }
                        String assname = ass.getAssName();
                        TypeCheck tc = new TypeCheck();
                        PyType type = tc.exprType(child.getAssignExpr(), tree);
                        tree.map.put(assname, table.createEntry(type));

                    }
                    break;


                }
                case PythonParser.CLASS: {
                    parseClassType(child,tree);
                    break;

                }
                case PythonParser.IMPORT: {
                    String modname = child.getChild(0).getChild(0).getText();
                    System.out.println(modname);
                    if (modname.equals("numpy") || modname.equals("unpython")) {
                        break;
                    } else {
                        tree.map.put(modname, table.createEntry(new PyModuleType(modname)));
                    }
                }
                default: {
                    throw new RuntimeException("not supported yet " + child.getText());
                }
            }
        }
    }

    public int getSymbol(String name) {
        return tree.map.get(name);
    }

    public void checkType() {
        parseTopLevelBindings();
        TypeCheck tc = new TypeCheck();
        for (PyTree child : tree.getModuleBody()) {
            if (child.getType() == PythonParser.FUNCTION) {
                
                tc.checkFunType(child, false);
            }else if(child.getType()==PythonParser.CLASS){
                tc.checkClassType(child);
            }

        }
    }
}
