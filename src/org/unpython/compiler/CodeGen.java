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
/*This file is for historical reasons only and has been replaced with Scala*/
package org.unpython.compiler;

import org.unpython.compiler.types.*;
import freemarker.template.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.unpython.compiler.frontend.PythonParser;

public class CodeGen {

    Configuration cfg;
    Template mtemplate;
    Template ctemplate;

    public CodeGen() {
        cfg = new Configuration();
        cfg.setClassForTemplateLoading(CodeGen.class, "");
        cfg.setObjectWrapper(new DefaultObjectWrapper());
        try {
            mtemplate = cfg.getTemplate("module.ftl");
        //ctemplate = cfg.getTemplate("class.ftl");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
    }

    private String getModuleName(PyModule m) {
        String name = m.name;
        if (name.contains(File.separator)) {
            int index = name.lastIndexOf(File.separator);
            name = name.substring(index + 1);
        }
        return name;
    }

    String mapType(PyType type) {
        if (type == null) {
            throw new NullPointerException();
        }
        if (type instanceof PyInt) {
            return "int";
        }
        if (type instanceof PyFloat) {
            return "float";
        }
        if (type instanceof PyDouble) {
            return "double";
        }
        if (type instanceof PyArrayType) {
            return "PyArrayObject *";
        }
        return "PyObject *";
    }

    //TODO:fix this hack
    boolean isPyObject(PyType type) {
        String map = mapType(type);
        if (map.equals("PyObject *")) {
            return true;
        }
        return false;
    }

    String formatString(PyType type) {
        if (type instanceof PyInt) {
            return "i";
        }
        if (type instanceof PyFloat) {
            return "f";
        }
        if (type instanceof PyDouble) {
            return "d";
        }
        if (type instanceof PyObjectType) {
            return "O";
        }
        return null;
    }

    String genExprCode(PyTree expr, PyTree root) {
        //System.out.println(expr.toStringTree());
        switch (expr.getType()) {
            case (PythonParser.ADD): {
                PyTree lchild = expr.getLeftChild();
                PyTree rchild = expr.getRightChild();
                //System.out.println("add "+lchild.type+ " "+rchild.type);
                if (PyUtils.isPrimitive(lchild, root) && PyUtils.isPrimitive(rchild, root)) {
                    //we should generate simple code
                    return genExprCode(lchild, root) + "+" + genExprCode(rchild, root);
                } else if (PyUtils.isPrimitive(lchild.pytype)) {
                    return "PyNumber_Add(Py_BuildValue(" +
                            "\"" + formatString(lchild.pytype) + "\"," + genExprCode(lchild, root) + ")," +
                            genExprCode(rchild, root);
                } else if (PyUtils.isPrimitive(rchild.pytype)) {
                    return "PyNumber_Add(" + genExprCode(lchild, root) + "Py_BuildValue(\"" + formatString(rchild.pytype) + "\"," + genExprCode(rchild, root) + "))";
                } else {
                    return "PyNumber_Add(" + genExprCode(lchild, root) + "," + genExprCode(rchild, root) + ")";
                }
            }
            case (PythonParser.SUB): {
                PyTree lchild = expr.getLeftChild();
                PyTree rchild = expr.getRightChild();
                //System.out.println(expr.toStringTree());
                if (PyUtils.isPrimitive(lchild, root) && PyUtils.isPrimitive(rchild, root)) {
                    //we should generate simple code
                    return genExprCode(lchild, root) + "-" + genExprCode(rchild, root);
                } else if (PyUtils.isPrimitive(lchild.pytype)) {
                    return "PyNumber_Subtract(Py_BuildValue(" +
                            "\"" + formatString(lchild.pytype) + "\"," + genExprCode(lchild, root) + ")," +
                            genExprCode(rchild, root);
                } else if (PyUtils.isPrimitive(rchild.pytype)) {
                    return "PyNumber_Subtract(" + genExprCode(lchild, root) + "Py_BuildValue(\"" + formatString(rchild.pytype) + "\"," + genExprCode(rchild, root) + "))";
                } else {
                    return "PyNumber_Subtract(" + genExprCode(lchild, root) + "," + genExprCode(rchild, root) + ")";
                }
            }
            case (PythonParser.MUL): {
                PyTree lchild = expr.getLeftChild();
                PyTree rchild = expr.getRightChild();
                //System.out.println("add "+lchild.type+ " "+rchild.type);
                if (PyUtils.isPrimitive(lchild.pytype) && PyUtils.isPrimitive(rchild.pytype)) {
                    //we should generate simple code
                    return genExprCode(lchild, root) + "*" + genExprCode(rchild, root);
                } else if (PyUtils.isPrimitive(lchild.pytype)) {
                    return "PyNumber_Mul(Py_BuildValue(" +
                            "\"" + formatString(lchild.pytype) + "\"," + genExprCode(lchild, root) + ")," +
                            genExprCode(rchild, root);
                } else if (PyUtils.isPrimitive(rchild.pytype)) {
                    return "PyNumber_Mul(" + genExprCode(lchild, root) + "Py_BuildValue(\"" + formatString(rchild.pytype) + "\"," + genExprCode(rchild, root) + "))";
                } else {
                    return "PyNumber_Mul(" + genExprCode(lchild, root) + "," + genExprCode(rchild, root) + ")";
                }
            }
            case (PythonParser.DIV): {
                PyTree lchild = expr.getLeftChild();
                PyTree rchild = expr.getRightChild();
                //System.out.println("add "+lchild.type+ " "+rchild.type);
                if (PyUtils.isPrimitive(lchild.pytype) && PyUtils.isPrimitive(rchild.pytype)) {
                    //we should generate simple code
                    return genExprCode(lchild, root) + "/" + genExprCode(rchild, root);
                } else if (PyUtils.isPrimitive(lchild.pytype)) {
                    return "PyNumber_Div(Py_BuildValue(" +
                            "\"" + formatString(lchild.pytype) + "\"," + genExprCode(lchild, root) + ")," +
                            genExprCode(rchild, root);
                } else if (PyUtils.isPrimitive(rchild.pytype)) {
                    return "PyNumber_Div(" + genExprCode(lchild, root) + "Py_BuildValue(\"" + formatString(rchild.pytype) + "\"," + genExprCode(rchild, root) + "))";
                } else {
                    return "PyNumber_Div(" + genExprCode(lchild, root) + "," + genExprCode(rchild, root) + ")";
                }
            }
            case (PythonParser.NAME): {
                return expr.getNameString();
            }
            case (PythonParser.CONST): {
                PyTree tree = expr.getConstValue();
                if (tree.getType() == PythonParser.INT || tree.getType() == PythonParser.FLOAT) {
                    return tree.getText();
                }
                break;
            }
            case (PythonParser.CALLFUNC): {
                //System.out.println("calling function "+expr.toStringTree());
                PyTree func = expr.getCallFuncExpr();
                if (func.getType() == PythonParser.NAME) {
                    //System.out.println("calling function "+func.toStringTree());
                    StringBuilder code = new StringBuilder();
                    code.append(func.getNameString());
                    code.append("(");
                    PyTree arglist = expr.getCallFuncArgList();
                    for (int i = 0; i < arglist.getChildCount(); i++) {
                        code.append(genExprCode(arglist.getChild(i), root));
                        if (i < arglist.getChildCount() - 1) {
                            code.append(",");
                        }
                    }
                    code.append(")");
                    return code.toString();
                } else if (func.getType() == PythonParser.GETATTR) {
                }
                break;
            }
            case PythonParser.CALLMETH:{
                PyTree func = expr.getCallFuncExpr();
                StringBuilder code = new StringBuilder();
                code.append("PyObject_CallMethodObjArgs(");
                code.append(genExprCode(func.getGetattrExpr(),root)+",\""+func.getGetattrName()+"\"");
                
                PyTree arglist = expr.getCallFuncArgList();
                for(int i=0;i<arglist.getChildCount();i++){
                    code.append(","+genExprCode(arglist.getChild(i),root));
                    
                }
                code.append(",NULL)");
                return code.toString();
            }
            case PythonParser.SUBSCRIPTG: {
                return genSubCode(expr, root);

            }
            case PythonParser.DIM: {
                return genExprCode(expr.getChild(0), root) + "->dimensions[" + genExprCode(expr.getChild(1), root) + "]";
            }
            case PythonParser.COMPARE: {
                PyTree lchild = expr.getChild(0);
                PyTree rchild = expr.getChild(2);
                if (PyUtils.isPrimitive(lchild.pytype) && PyUtils.isPrimitive(rchild.pytype)) {
                    String op = "==";
                    switch (expr.getChild(1).getType()) {
                        case PythonParser.CEQ:
                            op = "==";
                            break;
                        case PythonParser.CGT:
                            op = ">";
                            break;
                        case PythonParser.CLT:
                            op = "<";
                            break;
                        case PythonParser.CGTE:
                            op = ">=";
                            break;
                        case PythonParser.CLTE:
                            op = "<=";
                            break;

                    }
                    return genExprCode(lchild, root) + op + genExprCode(rchild, root);
                }
                break;
            }
            case PythonParser.CONVERT: {
                if (expr.pytype instanceof PyObjectType) {
                    return "Py_BuildValue(\"" + formatString(expr.getChild(0).pytype) + "\"," + expr.getChild(0).getNameString() + ")";
                }else if(expr.pytype instanceof PyDouble){
                    return "PyFloat_AS_DOUBLE("+genExprCode(expr.getChild(0),root)+")";
                }else if(expr.pytype instanceof PyFloat){
                    return "(float)(PyFloat_AS_DOUBLE("+genExprCode(expr.getChild(0),root)+"))";
                }else if(expr.pytype instanceof PyInt){
                    return "(int)(PyInt_AS_LONG("+genExprCode(expr.getChild(0),root)+"))";
                }
                break;
            }
            case PythonParser.GETATTR:{
                return "PyObject_GetAttrString("+genExprCode(expr.getGetattrExpr(),root)+","
                       +"\""+expr.getGetattrName()+"\")"; 
                
            }


        }
        return "0";
    }

    String genSubCode(PyTree expr, PyTree root) {
        StringBuilder code = new StringBuilder();
        String aname = expr.getChild(0).getNameString();
        PyTree subexpr = expr.getChild(1);
        if (PyUtils.isPrimitive(expr.pytype)) {
            //generate code for normal array access

            code.append("*((" + mapType(expr.pytype) + " *)(" + aname + "->data+");
            //System.out.println(expr.toStringTree());

            //PySubscript subtype = (PySubscript)subexpr.type;
            for (int i = 0; i < subexpr.getChildCount(); i++) {
                if (i > 0) {
                    code.append(" + ");
                }
                code.append(aname + "->strides[" + i + "]*" + genExprCode(subexpr.getChild(i), root));
            }
            code.append("))");
            return code.toString();
        } else {
            code.append("unpython_slice(" + aname + ",");
            code.append(subexpr.getChildCount() + ",");
            for (int i = 0; i < subexpr.getChildCount(); i++) {
                PyTree sub = subexpr.getChild(i);
                if (sub.pytype instanceof PyInt) {
                    code.append(1 + ",");
                    code.append(genExprCode(sub, root));
                } else if (sub.getType() == PythonParser.SLICEOBJ) {
                    code.append(3 + ",");
                    code.append(genExprCode(sub.getChild(0), root) + ",");
                    code.append(genExprCode(sub.getChild(1), root) + ",");
                    code.append(genExprCode(sub.getChild(2), root));
                }
                if (i < subexpr.getChildCount() - 1) {
                    code.append(",");
                }
            }
            code.append(")");
            return code.toString();
        //throw new RuntimeException("Cannot handle it yet " + expr.toStringTree());
        }
    }

    String genStmtCode(PyTree stmt, PyTree fun) {
        //System.out.println("CodeGen.genStmtCode : " + stmt.toStringTree());
        StringBuilder code = new StringBuilder();
        switch (stmt.getType()) {
            //we dont have multiple assignments here
            //we have also taken care of set slice etc by now
            //we have also taken care of copy ops
            //so this has to be of the form : name = expression
            case PythonParser.ASSIGN: {
                PyTree expr = stmt.getAssignExpr();
                PyTree assign = stmt.getAssignList().getChild(0);
                switch (assign.getType()) {
                    case PythonParser.ASSNAME: {
                        String assname = stmt.getAssignList().getChild(0).getAssName();

                        code.append(assname + " = " + genExprCode(expr, fun) + ";\n");
                        if (expr.getType() == PythonParser.NAME && (expr.pytype instanceof PyObjectType)) {
                            code.append("Py_XINCREF(" + assname + ");\n");
                        }
                        break;
                    }
                    //we should not have any array assigns .. only assignment to scalars
                    case PythonParser.SUBSCRIPTS: {
                        code.append(genSubCode(assign, fun) + " = " + genExprCode(expr, fun) + ";\n");
                        break;
                    }
                    case PythonParser.ASSATTR: {
                        code.append("PyObject_SetAttrString(" + genExprCode(assign.getGetattrExpr(), fun) + ",");
                        code.append("\"" + assign.getGetattrName() + "\",");
                        code.append(genExprCode(expr, fun) + ");\n");
                        break;
                    }
                }
                break;
            }
            //we have taken care of any temporary variables etc by now
            //TODO: however we have to take care of decrefs here
            case PythonParser.RETURN: {
                PyTree expr = stmt.getReturnExpr();
                int rsym = -1;
                if (expr.getType() == PythonParser.NAME) {
                    rsym = fun.map.get(expr.getNameString());
                }
                ArrayList<String> params = fun.getFunParams();
                for (String name : fun.map.keySet()) {
                    int sym = fun.map.get(name);
                    PyType symtype = fun.module.table.getType(sym);
                    if (!params.contains(name) && rsym != sym && fun.localVars.contains(sym) && !PyUtils.isPrimitive(symtype)) {
                        code.append("Py_XDECREF(" + name + ");\n");
                    }
                }

                code.append("return " + genExprCode(expr, fun) + ";\n");
                break;
            }

            case PythonParser.CFOR: {
                String name = stmt.getForVar();
                code.append("for(" + name + " = ");
                PyTree range = stmt.getChild(1);
                //System.out.println("CodeGen:genStmtCode: " + range.toStringTree());
                if (range.getChild(0) == null) {
                    code.append("0;");
                } else {
                    code.append(genExprCode(range.getChild(0), fun) + ";");
                }
                code.append(name + "<" + genExprCode(range.getChild(1), fun) + ";");
                if (range.getChild(2) == null) {
                    code.append(name + "++");
                } else {
                    code.append(name + "+=" + genExprCode(range.getChild(2), fun));
                }
                code.append("){\n");
                for (PyTree fstmt : stmt.getForBody()) {
                    code.append(genStmtCode(fstmt, fun));
                }
                code.append("}\n");
                break;
            }
            case PythonParser.CIF: {
                code.append("if(");
                //System.out.println("CodeGen: If test : " + stmt.getChild(0).toStringTree());
                code.append(genExprCode(stmt.getChild(0), fun));
                code.append("){\n");
                for (PyTree child : stmt.getChild(1)) {
                    code.append(genStmtCode(child, fun));
                }
                code.append("goto " + stmt.getChild(2).getNameString() + ";\n");
                code.append("}\n");
                break;
            }
            case PythonParser.LABEL: {
                code.append(stmt.getChild(0).getText() + ":\n");
                break;
            }
            case PythonParser.STMT: {
                for (PyTree child : stmt) {
                    code.append(genStmtCode(child, fun));
                    break;
                }
            }
        }
        return code.toString();

    }

    String genFunBody(PyTree tree) {
        PyTree body = tree.getFunBody();
        TreeLowerer lower = new TreeLowerer();
        lower.lowerFun(tree, false);
        StringBuilder code = new StringBuilder("");
        ArrayList<String> params = tree.getFunParams();
        //generate local declarations 
        for (String name : tree.map.keySet()) {
            int var = tree.map.get(name);
            if (tree.localVars.contains(var) && !(params.contains(name))) {
                PyType type = tree.module.table.getType(var);
                code.append(mapType(tree.module.table.getType(var)) + " " + name);
                if (isPyObject(type)) {
                    code.append(" = NULL;\n");
                } else {
                    code.append(";\n");
                }

            }
        }

        for (PyTree stmt : body) {
            code.append(genStmtCode(stmt, tree));
        //System.out.println(stmt.toStringTree());
        }

        return code.toString();
    }

    String genWrapBody(PyTree tree, String cname) {
        StringBuilder code = new StringBuilder();
        ArrayList<String> params = tree.getFunParams();
        if (params.size() == 0 || (params.size() == 1 && tree.pytype instanceof PyMethod)) {
            //zero args
        } else {
            code.append("if(!PyArg_ParseTuple(args,\"");

            for (int i = 0; i < params.size(); i++) {
                if (i == 0 && (tree.pytype instanceof PyMethod)) {
                    continue;
                }
                String param = params.get(i);
                code.append(formatString(tree.module.table.getType(tree.map.get(param))));
            }


            code.append("\",");
            for (int i = 0; i < params.size(); i++) {
                if (i == 0 && (tree.pytype instanceof PyMethod)) {
                    continue;
                }
                String param = params.get(i);
                code.append("&" + param);
                if (i < params.size() - 1) {
                    code.append(",");
                }
            }
            code.append(")){\n return NULL;\n}\n");
        }
        code.append("unpython_returner = ");
        if (tree.pytype instanceof PyFunType) {
            code.append(tree.getFunName() + "(");
        } else if (tree.pytype instanceof PyMethod) {
            code.append(cname + "_" + tree.getFunName() + "(");
        }
        for (int i = 0; i < params.size(); i++) {
            String param = params.get(i);
            code.append(param);
            if (i < params.size() - 1) {
                code.append(",");
            }

        }
        code.append(");\n");
        PyFunType ftype;
        if (tree.pytype instanceof PyFunType) {
            ftype = (PyFunType) tree.pytype;
        } else {
            ftype = ((PyMethod) tree.pytype).getFun();
        }
        PyType rtype = ftype.getReturnType();
        if (!PyUtils.isPrimitive(rtype)) {
            code.append("return unpython_returner;\n");
        } else {
            code.append("return Py_BuildValue(\"" + formatString(rtype) + "\",unpython_returner);\n");
        }

        return code.toString();
    }

    Map getFunModel(PyTree tree, String cname) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("name", tree.getFunName());
        PyFunType ftype;
        if (tree.pytype instanceof PyFunType) {
            ftype = (PyFunType) tree.pytype;
        } else {
            ftype = ((PyMethod) tree.pytype).getFun();
        }
        map.put("rtype", mapType(ftype.getReturnType()));
        ArrayList<String> params = tree.getFunParams();

        ArrayList<HashMap<String, Object>> paramdata = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i <
                params.size(); i++) {
            HashMap<String, Object> temp = new HashMap<String, Object>();
            temp.put("name", params.get(i));
            temp.put("type", mapType(ftype.getParamType(i)));
            paramdata.add(temp);
        }

        map.put("params", paramdata);
        map.put("body", genFunBody(tree));
        if (tree.pytype instanceof PyFunType) {
            map.put("wrapbody", genWrapBody(tree, null));
        } else if (tree.pytype instanceof PyMethod) {
            map.put("wrapbody", genWrapBody(tree, cname));
        }
        return map;
    }

    private String getCode(PyType type) {
        if (type instanceof PyInt) {
            return "T_INT";
        }
        if (type instanceof PyFloat) {
            return "T_FLOAT";
        }
        if (type instanceof PyDouble) {
            return "T_DOUBLE";
        }
        return "T_OBJECT_EX";
    }

    public Map getClassModel(PyTree tree) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("name", tree.getClassName());
        ArrayList<Object> methods = new ArrayList<Object>();
        for (PyTree method : tree.getMethods()) {
            Map methmodel = getFunModel(method, tree.getClassName());
            //System.out.println(methmodel);
            methods.add(methmodel);
        }
        map.put("methods", methods);
        PyClassType ctype = (PyClassType) tree.pytype;
        ArrayList<HashMap<String, Object>> members = new ArrayList<HashMap<String, Object>>();
        for (String member : ctype.getMembers()) {
            HashMap memmap = new HashMap();
            memmap.put("name", member);
            memmap.put("code", getCode(ctype.getAttr(member)));
            memmap.put("type", mapType(ctype.getAttr(member)));
            members.add(memmap);
        }
        map.put("members", members);
        return map;
    }

    public void dumpModuleCode(PyModule mod) {
        try {
            Map root = new HashMap<String, Object>();
            root.put("name", getModuleName(mod));
            ArrayList<Object> functions = new ArrayList<Object>();
            ArrayList<Object> classes = new ArrayList<Object>();
            for (PyTree child : mod.tree.getModuleBody()) {
                switch (child.getType()) {
                    case (PythonParser.FUNCTION): {
                        functions.add(getFunModel(child, null));
                        break;
                    }
                    case (PythonParser.CLASS): {
                        classes.add(getClassModel(child));
                        break;
                    }
                }
            }
            root.put("functions", functions);
            root.put("classes", classes);
            FileWriter stream = new FileWriter(new File(mod.name + ".c"));
            mtemplate.process(root, stream);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(-1);
        }
    }
}
