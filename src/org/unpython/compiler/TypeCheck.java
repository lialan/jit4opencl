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

import org.unpython.compiler.types.*;
import java.util.ArrayList;
import java.util.HashMap;
import org.unpython.compiler.frontend.PythonParser;

public class TypeCheck {

    boolean inMethod;
    String self;

    public TypeCheck() {
        inMethod = false;
        self = null;
    }
	
	void checkConstructor(PyTree tree){
			
	}

    void checkFunType(PyTree tree, boolean isMethod) {
        PyUtils.initNameSpace(tree);
        ArrayList<String> params = tree.getFunParams();
        inMethod = isMethod;

        if (tree.map == null) {
            tree.map = new HashMap<String, Integer>();
        }
        for (int i = 0; i < params.size(); i++) {
            PyFunType ftype;
            if(tree.pytype instanceof PyFunType){
                ftype = (PyFunType) tree.pytype;
            }else if(tree.pytype instanceof PyMethod){
                ftype = ((PyMethod)tree.pytype).getFun();
            }else{
                ftype = null;
            }
            String name = params.get(i);
            if (inMethod) {
                self = name;
            }
            int sym = tree.module.table.createEntry(ftype.getParamType(i));
            tree.map.put(name, sym);
            tree.localVars.add(sym);
        }
        PyTree body = tree.getFunBody();
        try {
            for (PyTree stmt : body) {
                if (!checkStmt(stmt, tree));
            }
        } catch (Exception ex) {
            System.out.println("In : " + tree.getFunName());
            ex.printStackTrace();
        }
    }

    PyType exprType(PyTree tree, PyTree root) {
        if (tree.pytype != null) {
            return tree.pytype;
        }
        switch (tree.getType()) {
            case (PythonParser.ADD): {
                PyType lside = exprType(tree.getLeftChild(), root);
                PyType rside = exprType(tree.getRightChild(), root);
                PyType temp = lside.ladd(rside);
                if (temp != null) {
                    tree.pytype = temp;
                    return temp;
                }
                temp = rside.radd(lside);
                if (temp != null) {
                    tree.pytype = temp;
                    return temp;
                }
                assert false : "Cannot add types " + lside.toString() + " " + rside.toString();
                break;
            }
            case (PythonParser.SUB): {
                PyType lside = exprType(tree.getLeftChild(), root);
                PyType rside = exprType(tree.getRightChild(), root);
                PyType temp = lside.lsub(rside);
                if (temp != null) {
                    tree.pytype = temp;
                    return temp;
                }
                temp = rside.rsub(lside);
                if (temp != null) {
                    tree.pytype = temp;
                    return temp;
                }
                assert false : "Cannot subtract types " + lside.toString() + " " + rside.toString();
                break;
            }
            case (PythonParser.MUL): {
                PyType lside = exprType(tree.getLeftChild(), root);
                PyType rside = exprType(tree.getRightChild(), root);
                PyType temp = lside.lmul(rside);
                if (temp != null) {
                    tree.pytype = temp;
                    return temp;
                }
                temp = rside.rmul(lside);
                if (temp != null) {
                    tree.pytype = temp;
                    return temp;
                }
                assert false : "Cannot multiply types " + lside.toString() + " " + rside.toString();
                break;
            }
            case (PythonParser.NAME): {
                int sym;
                String name = tree.getNameString();
                if (root.map.containsKey(name)) {
                    sym = root.map.get(name);
                } else {
                    sym = tree.module.getSymbol(name);
                    root.map.put(name, sym);
                    root.globalVars.add(sym);
                }
                tree.symindex = sym;
                tree.pytype = tree.module.table.getType(sym);
                return tree.pytype;
            }
            case (PythonParser.CALLFUNC): {
                PyType etype = exprType(tree.getCallFuncExpr(), root);
                if (etype instanceof PyFunType) {
                    PyFunType funtype = (PyFunType) etype;
                    PyTree args = tree.getCallFuncArgList();
                    for (int i = 0; i < args.getChildCount(); i++) {
                        PyType argtype = exprType(args.getChild(i), root);
                        PyType funargtype = funtype.getParamType(i);
                        if (!funargtype.canAssign(argtype)) {
                            throw new RuntimeException("Type of function call argument is wrong");
                        }
                    }
                    tree.pytype = funtype.getReturnType();
                    return tree.pytype;
                } else if (etype instanceof PyMethod) {
                    PyMethod mtype = (PyMethod) etype;
                    PyTree args = tree.getCallFuncArgList();
                    for (int i = 0; i < args.getChildCount(); i++) {
                        PyType argtype = exprType(args.getChild(i), root);
                        PyType methargtype = mtype.getParamType(i);
                        if (!methargtype.canAssign(argtype)) {
                            throw new RuntimeException("Type of method call argument is wrong");
                        }
                    }
                    tree.pytype = mtype.getFun().getReturnType();
                    return tree.pytype;
                }
                break;
            }

            case (PythonParser.CONST): {
                PyTree value = tree.getConstValue();
                switch (value.getType()) {
                    case (PythonParser.INT): {
                        tree.pytype = new PyInt();
                        value.pytype = tree.pytype;
                        return tree.pytype;
                    }
                    case (PythonParser.FLOAT): {
                        tree.pytype = new PyFloat();
                        value.pytype = tree.pytype;
                        return tree.pytype;
                    }
                    case (PythonParser.NONE): {
                        tree.pytype = new PyNoneType();
                        value.pytype = tree.pytype;
                        return tree.pytype;
                    }
                }
                break;
            }
            case (PythonParser.GETATTR): {
                PyTree expr = tree.getGetattrExpr();
                exprType(expr, root);
                //check for imports
                if (expr.getType() == PythonParser.NAME) {
                    if (expr.pytype instanceof PyModuleType) {
                        //TODO: fix imports
                    }
                }
                //not an import. must be an object
                tree.pytype = expr.pytype.getAttr(tree.getGetattrName());
                return tree.pytype;
            }
            case (PythonParser.SUBSCRIPTG): {
                PyTree subexpr = tree.getChild(0);
                PyTree subscript = tree.getChild(1);
                PyType exprtype = exprType(subexpr, root);
                PySubscript subtype = new PySubscript();
                for (PyTree temp : subscript) {
                    subtype.addChild(exprType(temp, root));
                }
                tree.pytype = exprtype.get(subtype);
                return tree.pytype;
            }
            case PythonParser.SLICEOBJ: {
                PySliceObj obj = new PySliceObj();
                int n = tree.getChildCount();
                if (n < 1 || n > 3) {
                    return null;
                }
                for (PyTree expr : tree) {
                    obj.addChild(exprType(expr, root));
                }
                tree.pytype = obj;
                return tree.pytype;
            }
            case PythonParser.COMPARE: {
                PyTree op = tree.getChild(1);
                PyType ltype = exprType(tree.getChild(0), root);
                PyType rtype = exprType(tree.getChild(2), root);
                switch (op.getType()) {
                    case PythonParser.CGT: {
                        tree.pytype = ltype.compareGt(rtype);
                        return tree.pytype;
                    }
                    case PythonParser.CLT: {
                        tree.pytype = ltype.compareLt(rtype);
                        return tree.pytype;
                    }
                    case PythonParser.CEQ: {
                        tree.pytype = ltype.compareEq(rtype);
                        return tree.pytype;
                    }
                    case PythonParser.CGTE: {
                        tree.pytype = ltype.compareGte(rtype);
                        return tree.pytype;
                    }
                    case PythonParser.CLTE: {
                        tree.pytype = ltype.compareLte(rtype);
                        return tree.pytype;
                    }
                }
            }
            default: {
                System.err.println("why am i here?");
            }

        }
        assert false : "dont know how to handle expressions of type " + tree.getText() + " " + tree.toStringTree();
        return null;
    }

	void checkConstructorType(PyTree method,PyTree klass){
		
	}
    void checkClassType(PyTree klass) {
        for (PyTree method : klass.getMethods()) {
			String name = method.getFunName();
			if(!name.equals("__init__"))
            	checkFunType(method, true);
        }
		PyTree init = klass.getClassInit();
		System.out.println("Typecheck:checkClassType: "+init.toStringTree());
		checkFunType(init,true);
    }

	void changeToSerialLoop(PyTree loop){
		if(loop.getType()!=PythonParser.PARFOR) return;
		loop.setType(PythonParser.FOR);
	}

	//checks whether the expression can occur in parallel loop
	boolean checkParallelExpr(PyTree expr){
		if(expr==null) return true;
		int type = expr.getType();
		switch(expr.getType()){
			case PythonParser.CALLFUNC:
			case PythonParser.CALLMETH:
			case PythonParser.GETATTR:
			case PythonParser.ASSATTR:
			case PythonParser.RETURN:
				return false;
			case PythonParser.PARFOR:{
				changeToSerialLoop(expr);
				//Dont "break" here.
			}
			default:{
				boolean check = true;
				for(PyTree child:expr){
					check = check && checkParallelExpr(child);
				}
				return check;
			}
		}
	}

	//returns whether or not loop should be parallelized
	boolean checkParallelBody(PyTree body,PyTree root){
		boolean check = true;
		for(PyTree stmt:body){
			//inner loops cannot be parallel
			check = check && checkParallelExpr(stmt);
			
		}
		return check;
	}

    boolean checkStmt(PyTree stmt, PyTree root) {
        //System.out.println("checking "+stmt.toStringTree());
        switch (stmt.getType()) {
            case (PythonParser.ASSIGN): {
                PyType etype = exprType(stmt.getAssignExpr(), root);
                stmt.getAssignExpr().pytype = etype;
                for (PyTree assign : stmt.getAssignList()) {
                    switch (assign.getType()) {
                        case (PythonParser.ASSNAME): {
                            String aname = assign.getAssName();
                            //System.out.println("assigned "+aname);
                            if (root.map.containsKey(aname)) {
                                assert root.module.table.getType(root.map.get(aname)).canAssign(etype) :
                                        "Cannot assign " + stmt.getAssignExpr() + " to " + aname + " : Type mismatch";
                            } else {
                                int sym = root.module.table.createEntry(etype);
                                root.map.put(aname, sym);
                                root.localVars.add(sym);
                            }
                            assign.pytype = root.module.table.getType(root.map.get(aname));
                            break;
                        }
                        case (PythonParser.ASSATTR): {
                            PyTree aaexpr = assign.getGetattrExpr();
                            PyType aatype = exprType(aaexpr, root);
                            assert aatype.getAttr(assign.getGetattrName()).canAssign(etype) :
                                    "cannot assign" + stmt.getAssignExpr().toStringTree() + " to " + assign.toStringTree();
                            break;
                        }
                        case PythonParser.SUBSCRIPTS: {
                            PyTree obj = assign.getChild(0);
                            PyTree subtree = assign.getChild(1);
                            PyType otype = exprType(obj, root);
                            PySubscript subtype = new PySubscript();
                            for (PyTree child : subtree) {
                                subtype.addChild(exprType(child, root));
                            }
                            assert otype instanceof PyArrayType : "Assignment to subscript only supported on ndarray";
                            PyType result = otype.get(subtype);
                            assign.pytype = result;
                            assert otype.canAssignSub(subtype, etype) : "Cannot assign " + etype + " to " + result;
                            break;
                        }
                        default: {
                            throw new RuntimeException("not handled yet");
                        }
                    }
                }
                return false;
            }
            case (PythonParser.RETURN): {
                PyType etype = exprType(stmt.getReturnExpr(), root);
                PyFunType ftype;
                if(root.pytype instanceof PyMethod) ftype = ((PyMethod)root.pytype).getFun();
                else ftype = (PyFunType) root.pytype;
                return ftype.getReturnType().canAssign(etype);

            }
            case (PythonParser.DISCARD): {
                exprType(stmt.getDiscardExpr(), root);
                return false;
            }
            case (PythonParser.FOR): {
                String var = stmt.getForVar();
                if (root.map.containsKey(var)) {
                    PyType type = root.module.table.getType(root.map.get(var));
                    if (!(type instanceof PyInt)) {
                        throw new RuntimeException("type checking failed in" + stmt);
                    }
                } else {
                    int sym = root.module.table.createEntry(new PyInt());
                    root.map.put(var, sym);
                    root.localVars.add(sym);
                }
                PyTree fexpr = stmt.getForExpr().getCallFuncExpr();
                //System.out.println(fexpr.toStringTree());
				if(fexpr.getType()==PythonParser.NAME){
                	String fname = fexpr.getNameString();
                	assert fname.equals("range") || fname.equals("xrange"): "unrecognized for";
				}else if(fexpr.getType()==PythonParser.GETATTR){
					String attrname = fexpr.getGetattrName();
					PyTree attrexpr = fexpr.getGetattrExpr();
					assert attrname.equals("prange");
					assert attrexpr.getType()==PythonParser.NAME && attrexpr.getNameString().equals("unpython"):"unrecognized expression in for";
					stmt.setType(PythonParser.PARFOR);
				}
                PyTree args = stmt.getForExpr().getCallFuncArgList();
                int i = 0;
                for (PyTree arg : args) {
                    PyType etype = exprType(arg, root);
                    if (!(etype instanceof PyInt)) {
                        throw new RuntimeException("argument to range should be integers" + arg.toStringTree());
                    }
                    i++;
                }
                if (i < 1 || i > 3) {
                    throw new RuntimeException("range accepts 1 to 3 args");
                }
                PyTree fbody = stmt.getForBody();
                for (PyTree fstmt : fbody) {
                    checkStmt(fstmt, root);
                }
				if(stmt.getType()==PythonParser.PARFOR){
				   boolean check = checkParallelBody(stmt.getForBody(),root);
				   if(!check) changeToSerialLoop(stmt.getForBody());
				}
                return true;
            }
            case (PythonParser.IF): {
                PyTree branches = stmt.getIfList();
                for (PyTree branch : branches) {
                    PyType cmp = exprType(branch.getChild(0), root);
                    assert cmp != null : branch.getChild(0).toStringTree();
                    assert cmp instanceof PyInt : "If tests should yield integers : instead got " + cmp;
                    checkStmt(branch.getChild(1), root);

                }
                PyTree elbranch = stmt.getIfElse();
                return checkStmt(elbranch, root);

            }
            case PythonParser.STMT: {
                for (PyTree child : stmt) {
                    checkStmt(child, root);
                }
                break;
            }
            default: {
                //throw new RuntimeException("not handled yet");
            }
        }
        return false;
    }
}	
