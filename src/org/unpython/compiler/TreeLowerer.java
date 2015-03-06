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
import org.unpython.compiler.frontend.PythonParser;

public class TreeLowerer {

    int temp;

    String createLabel() {
        String ans = "unpython_label" + temp;
        temp++;
        return ans;
    }

    String createVariable(PyTree root, PyType type) {
        String name = "unpython_extra" + temp;
        int sym = root.module.table.createEntry(type);
        root.map.put(name, sym);
        root.localVars.add(sym);
        temp++;
        return name;
    }

    /**
     * Creates an ASSNAME statement. But since no index is passed, its not attached into any block
     * @param name
     * @param value
     * @param root
     * @return
     */
    PyTree createAssName(String name, PyTree value, PyTree root) {
        PyTree tree = new PyTree(PythonParser.ASSIGN);
        tree.setText("Assign");
        PyTree alist = new PyTree(PythonParser.LIST);
        alist.setText("LIST");

        PyTree assname = new PyTree(PythonParser.ASSNAME);
        assname.pytype = root.module.table.getType(root.map.get(name));
        PyTree idexpr = new PyTree(PythonParser.ID);
        idexpr.setText(name);
        assname.setChild(0, idexpr);
        assname.setText("AssName");

        alist.setChild(0, assname);
        tree.setAssignList(alist);
        tree.setAssignExpr(value);
        return tree;
    }

    /*will create a name expression but without type*/
    PyTree createNameExpr(String name) {
        PyTree expr = new PyTree(PythonParser.NAME);
        PyTree nexpr = new PyTree(PythonParser.ID);
        nexpr.setText(name);
        expr.setChild(0, nexpr);
        expr.setText("Name");
        return expr;
    }

    PyTree createNameExpr(String name, PyTree root) {
        PyTree expr = createNameExpr(name);
        expr.pytype = root.module.table.getType(root.map.get(name));
        return expr;
    }

    PyTree createConvert(String name, PyTree root, PyType type) {
        PyTree tree = new PyTree(PythonParser.CONVERT);
        tree.setChild(0, createNameExpr(name, root));
        tree.pytype = type;
        return tree;
    }

    /*returns the index of the statement AFTER lowering the expression*/
    /*TODO : complete this for non-primitive expressions*/
    Tuple<Integer, PyTree> lowerExpr(PyTree expr, PyTree root, PyTree block, int index) {
        if (isPrimitiveExpression(expr)) {
            return new Tuple<Integer, PyTree>(index, expr);
        }
        int type = expr.getType();
        switch (type) {
            case (PythonParser.NAME): {
                return new Tuple<Integer, PyTree>(index, expr);
            }
            case PythonParser.ADD:
            case PythonParser.SUB:
            case PythonParser.MUL:
            case PythonParser.DIV: {
                PyTree rchild = expr.getRightChild();
                PyTree lchild = expr.getLeftChild();
                if (rchild.getType() == PythonParser.NAME && lchild.getType() == PythonParser.NAME) {
                    return new Tuple(index, expr);
                }
                Tuple<Integer, PyTree> temp1 = lowerExpr(lchild, root, block, index);
                Tuple<Integer, PyTree> temp2 = lowerExpr(rchild, root, block, temp1.first);
                expr.setChild(0, temp1.second);
                expr.setChild(1, temp2.second);
                return new Tuple(temp2.first, expr);
            }

            case PythonParser.CALLFUNC: {
                Tuple<Integer, PyTree> tup;

                PyTree fun = expr.getCallFuncExpr();
                boolean isMethod = false;
                if (fun.getType() == PythonParser.GETATTR) {
                    //TODO:fix imports
                    isMethod = true;
                    tup = lowerExpr(fun.getGetattrExpr(), root, block, index);
                    index = tup.first;
                    fun.setGetattrExpr(tup.second);
                    expr.setType(PythonParser.CALLMETH);
                }
                //we have to lower the args
                PyTree args = expr.getCallFuncArgList();
                for (int i = 0; i < args.getChildCount(); i++) {
                    tup = lowerExpr(args.getChild(i), root, block, index);
                    index = tup.first;
                    if(!isMethod)args.setChild(i, tup.second);
                    else {
                        PyTree converted = new PyTree(PythonParser.CONVERT);
                        converted.setChild(0,tup.second);
                        converted.setText("Convert");
                        converted.pytype = new PyObjectType();
                        args.setChild(i, converted);
                    }
                    
                }
                return new Tuple<Integer, PyTree>(index, expr);
            }
            case PythonParser.SUBSCRIPTG: {
                //currently the only thing that can have subscript is ndarray

                //lower the array expr .. it may or may not be a name
                PyTree arrayexpr = expr.getChild(0);
                Tuple<Integer, PyTree> temptup = lowerExpr(arrayexpr, root, block, index);
                expr.setChild(0, temptup.second);
                index = temptup.first;

                //we shud create a temporary variable to hold the value returned by subscript
                String name = createVariable(root, expr.pytype);

                //lower the subscript expression
                PyTree subexpr = expr.getChild(1);
                //System.out.println("TreeLowere.lowerExpr "+subexpr.toStringTree());
                for (int i = 0; i < subexpr.getChildCount(); i++) {
                    temptup = lowerExpr(subexpr.getChild(i), root, block, index);
                    index = temptup.first;
                    subexpr.setChild(i, temptup.second);
                    if (subexpr.getChild(i).getType() == PythonParser.SLICEOBJ) {
                        lowerSlice(subexpr.getChild(i), expr.getChild(0), i);
                    }
                }

                //we now have a lowered subscript expression 
                if (PyUtils.isPrimitive(expr.pytype)) {
                    //TODO:check for negative indices
                }
                PyTree assname = createAssName(name, expr, root);
                block.insertChild(index, assname);
                return new Tuple<Integer, PyTree>(index + 1, createNameExpr(name, root));
            }
            case PythonParser.SLICEOBJ: {
                return new Tuple<Integer, PyTree>(index, expr);

            }
            case PythonParser.GETATTR: {
                System.out.println("in getattr "+expr.toStringTree());
                Tuple<Integer, PyTree> tup = lowerExpr(expr.getGetattrExpr(), root, block, index);
                if (tup.second.getType() != PythonParser.NAME) {
                    String name = createVariable(root, tup.second.pytype);
                    PyTree assname = createAssName(name, tup.second, root);
                    block.insertChild(index, assname);
                    PyTree nameexpr = createNameExpr(name, root);
                    tup.second = nameexpr;
                    tup.first = tup.first + 1;
                }
                index = tup.first;
                expr.setGetattrExpr(tup.second);
                PyType etype = expr.pytype;
                expr.pytype = new PyObjectType();
                String name1 = createVariable(root, expr.pytype);
                String name2 = createVariable(root, etype);

                block.insertChild(index, createAssName(name1, expr, root));
                index++;

                block.insertChild(index, createAssName(name2, createConvert(name1, root, etype), root));
                index++;

                return new Tuple<Integer, PyTree>(index, createNameExpr(name2, root));
            }
            default: {
                return new Tuple<Integer, PyTree>(index, expr);
            }

        }
    }

    boolean isPrimitiveExpression(PyTree expr) {
        if (!PyUtils.isPrimitive(expr.pytype)) {
            return false;
        }
        switch (expr.getType()) {
            case (PythonParser.NAME): {
                //we already checked expression type.
                return true;
            }
            case (PythonParser.SUB):
            case (PythonParser.MUL):
            case (PythonParser.ADD): {
                PyTree lchild = expr.getLeftChild();
                PyTree rchild = expr.getRightChild();
                if (isPrimitiveExpression(lchild) && isPrimitiveExpression(rchild)) {
                    return true;
                }
                return false;
            }
            case PythonParser.SUBSCRIPTG:
                return false;
        }
        return false;
    }

    void lowerBlock(PyTree fun, PyTree block) {
        assert block.getType() == PythonParser.STMT : block.toStringTree();
        for (int i = 0; i < block.getChildCount();) {
            i = lowerStmt(block.getChild(i), block, fun, i);
        }
    }

    PyTree createLabelStmt(String label) {
        PyTree stmt = new PyTree(PythonParser.LABEL);
        PyTree nexpr = new PyTree(PythonParser.ID);
        nexpr.setText(label);
        stmt.setChild(0, nexpr);
        stmt.setText("Label");
        return stmt;
    }

    PyTree createDimExpr(PyTree base, PyTree dim) {
        assert dim.pytype instanceof PyInt : dim.toStringTree();
        assert base.pytype instanceof PyArrayType : base.toStringTree();
        PyTree expr = new PyTree(PythonParser.DIM);
        expr.setChild(0, base);
        expr.setChild(1, dim);
        expr.pytype = new PyInt();
        expr.setText("Dim");
        return expr;
    }

    PyTree createConstExpr(int i) {
        PyTree cons = new PyTree(PythonParser.CONST);
        PyTree val = new PyTree(PythonParser.INT);
        val.setText(Integer.toString(i));
        cons.setChild(0, val);
        cons.pytype = new PyInt();
        return cons;
    }

    PyTree createAddExpr(PyTree left, PyTree right, PyTree root) {
        PyTree add = new PyTree(PythonParser.ADD);
        add.setChild(0, left);
        add.setChild(1, right);
        new TypeCheck().exprType(add, root);
        add.setText("Add");
        return add;
    }

    PyTree createAssignExpr() {
        PyTree tree = new PyTree(PythonParser.ASSIGN);
        tree.setAssignList(new PyTree(PythonParser.LIST));
        tree.setAssignExpr(null);
        tree.setText("Assign");
        return tree;

    }

    PyTree createCfor(String var) {
        PyTree cfor = new PyTree(PythonParser.CFOR);
        PyTree assname = new PyTree(PythonParser.ASSNAME);
        assname.setChild(0, new PyTree(PythonParser.ID));
        assname.setAssName(var);
        cfor.setChild(0, assname);
        cfor.setChild(1, null);
        cfor.setChild(2, new PyTree(PythonParser.STMT));
        cfor.getChild(2).setText("Stmt");
        cfor.setText("Cfor");
        return cfor;
    }

    PyTree createSubscriptS(PyTree base, PyTree sub) {
        PyTree tree = new PyTree(PythonParser.SUBSCRIPTS);
        tree.setText("Subscript");
        tree.setChild(0, base);
        tree.setChild(1, sub);
        return tree;
    }

    void lowerSlice(PyTree slice, PyTree base, int dim) {
        //System.out.println("TreeLowerer:lowerSlice: " + slice.toStringTree());
        assert slice.getType() == PythonParser.SLICEOBJ : slice.toStringTree();
        PyTree start, stop, step;
        switch (slice.getChildCount()) {
            case 1: {
                stop = slice.getChild(0);
                start = createConstExpr(0);
                step = createConstExpr(1);
                break;
            }
            case 2: {
                start = slice.getChild(0);
                stop = slice.getChild(1);
                step = createConstExpr(1);
                break;
            }
            case 3: {
                start = slice.getChild(0);
                stop = slice.getChild(1);
                step = slice.getChild(2);
                break;
            }
            default: {
                throw new RuntimeException("wrong number of dimensions for slice " + slice.getChildCount());
            }
        }
        if (start.getType() == PythonParser.NONE) {
            start = createConstExpr(0);
        }
        if (stop.getType() == PythonParser.NONE) {
            stop = createDimExpr(base, createConstExpr(dim));
        }
        slice.setChild(0, start);
        slice.setChild(1, stop);
        slice.setChild(2, step);

    }

    /**
     * @param assign : should NOT be the array assignment statement. should be the expression for LHS
     * @param expr : should be the lowered expression for RHS
     * @param block : block to which assign belongs 
     * @param fun : root/fun
     * @index : index of statement
     * @return : returns the value of index AFTER lowering the statement
     */
    int lowerSubAssign(PyTree assign, PyTree expr, PyTree block, PyTree fun, int index) {
        //the array on LHS .. might not be name 
        assert block.getChild(index).getAssignList().getChild(0) == assign;
        //System.out.println("TreeLowerer: " + assign.toStringTree());
        PyTree base = assign.getSubBase();
        PyTree subs = assign.getChild(1);

        for (int i = 0; i < subs.getChildCount(); i++) {
            Tuple<Integer, PyTree> temptup = lowerExpr(subs.getChild(i), fun, block, index);
            index = temptup.first;
            subs.setChild(i, temptup.second);
            if (subs.getChild(i).getType() == PythonParser.SLICEOBJ) {
                lowerSlice(subs.getChild(i), base, i);
            }
        }

        if (base.getType() != PythonParser.NAME) {
            Tuple<Integer, PyTree> t = lowerExpr(base, fun, block, index);
            base = t.second;
            assign.setSubBase(t.second);
            index = t.first;
        }
        //are we dealing with simple scalar assignment ?
        if (PyUtils.isPrimitive(assign.pytype)) {
            //do nothing
        } // array assignment
        else if (assign.pytype instanceof PyArrayType) {
            PyTree rhs;

            // replace rhs with a name
            if (expr.getType() != PythonParser.NAME) {
                String aname = createVariable(fun, expr.pytype);
                block.insertChild(index, createAssName(aname, expr, fun));
                index++;
                rhs = createNameExpr(aname, fun);
            } else {
                rhs = expr;
            }

            //create a nested-for loop
            PyArrayType atype = (PyArrayType) assign.pytype;
            int assdims = atype.getDims(); //dimension of subscript

            int arraydims = ((PyArrayType) base.pytype).getDims(); //dimension of base array on LHS

            PyTree[] nest = new PyTree[arraydims];
            String[] nestvars = new String[arraydims];
            for (int i = 0; i < arraydims; i++) {
                nestvars[i] = createVariable(fun, new PyInt());
                nest[i] = createCfor(nestvars[i]);
                nest[i].setForVar(nestvars[i]);
                PyTree range = new PyTree(PythonParser.SLICEOBJ);

                if (i < subs.getChildCount()) {
                    //we do have some slice in this dim. 
                    PyTree sub = subs.getChild(i);
                    if (sub.pytype instanceof PySliceObj) {
                        range.setChild(0, sub.getChild(0));
                        range.setChild(1, sub.getChild(1));
                        range.setChild(2, sub.getChild(2));
                    } else if (sub.pytype instanceof PyInt) {
                        range.setChild(0, sub);
                        range.setChild(1, createAddExpr(sub, createConstExpr(1), fun));
                        range.setChild(2, createConstExpr(1));
                    }

                } else {
                    //System.out.println("TreeLowerer:lowerSubs: " + subs.getChildCount() + " " + i);
                    range.setChild(0, createConstExpr(0));
                    range.setChild(1, createDimExpr(base, createConstExpr(i)));
                    range.setChild(2, createConstExpr(1));

                }
                nest[i].setChild(1, range);
            }
            for (int i = 0; i < arraydims - 1; i++) {
                nest[i].getForBody().setChild(0, nest[i + 1]);
            }


            PyTree stmt = createAssignExpr();
            PyTree newsubs = new PyTree(PythonParser.SUBSCRIPT);
            newsubs.setText("SUBSCRIPTS");
            for (int i = 0; i < arraydims; i++) {
                newsubs.setChild(i, createNameExpr(nestvars[i]));
            }
            PyTree newSubscriptS = createSubscriptS(base, newsubs);
            newSubscriptS.pytype = atype.getElementType();
            stmt.getAssignList().setChild(0, newSubscriptS);

            //is rhs a scalar or an array?
            //TODO:fix this
            if (rhs.pytype instanceof PyArrayType) {
                throw new RuntimeException("Assignment of array to array not implemented yet");
            } else {
                stmt.setAssignExpr(rhs);
            }

            PyTree body = new PyTree(PythonParser.STMT);
            body.setChild(0, stmt);
            nest[nest.length - 1].setForBody(body);
            block.setChild(index, nest[0]);
            index++;
        //System.out.println("TreeLower lowerSub: "+block.toStringTree());
        }
        return index;

    }

    /*returns the index of the statement NEXT TO stmt after stmt has been lowered*/
    int lowerStmt(PyTree stmt, PyTree block, PyTree fun, int index) {
        //System.out.println(stmt.toStringTree());
        switch (stmt.getType()) {

            /**assignments are complicated.
             * we have to distinguish b/w various kinds of assignments because
             * of garbage collection details. if the RHS is just a name or an attribute,
             * then we may need to increase refcounts. in short, refcounts suck.
             */
            case (PythonParser.ASSIGN): {
                PyTree alist = stmt.getAssignList();
                PyTree expr = stmt.getAssignExpr();
                boolean isPrimitive = false;

                //first find out if we are dealing with multiple assignments
                //for now its best to restrict ourselves to single assignments.
                if (alist.getChildCount() > 1) {
                    throw new RuntimeException("Multiple Assignments not handled in " + stmt.toStringTree());
                }
                Tuple<Integer, PyTree> tup = lowerExpr(expr, fun, block, index);
                index = tup.first;
                stmt.setAssignExpr(tup.second);
                //ok now distinguish b/w various assignments 
                for (PyTree assign : alist) {
                    switch (assign.getType()) {
                        case PythonParser.ASSNAME: {
                            index++;
                            //nothing to do here for now
                            break;
                        }
                        case PythonParser.SUBSCRIPTS: {
							System.out.println("lowering assign "+assign.toStringTree());
                            index = lowerSubAssign(assign, expr, block, fun, index);
							index++;
                            break;
                        }
                        case PythonParser.ASSATTR: {
                            //set attribute
                            // (aaexpr).attr = value

                            //lower the object whose attribute we are setting
                            PyTree aaexpr = assign.getGetattrExpr();
                            tup = lowerExpr(aaexpr, fun, block, index);
                            index = tup.first;
                            assign.setGetattrExpr(tup.second);

                            PyTree value = stmt.getAssignExpr();
                            //the value we are setting must be a name .. otherwise change to name
                            if (value.getType() != PythonParser.NAME) {
                                String name = createVariable(fun, value.pytype);
                                PyTree assname = createAssName(name, value, fun);
                                block.insertChild(index, assname);
                                value = createNameExpr(name, fun);
                                index++;
                            }

                            //insert a convert if required
                            if (!(value.pytype instanceof PyObjectType)) {
                                String name = createVariable(fun, new PyObjectType());
                                PyTree assname = createAssName(name, createConvert(value.getNameString(), fun, new PyObjectType()), fun);
                                block.insertChild(index, assname);
                                value = createNameExpr(name, fun);
                                index++;
                            }
                            stmt.setAssignExpr(value);

                            //ok finished. routine index increment 
                            index++;
                            break;
                        }
                    }
                }
                break;
            }
			case PythonParser.PARFOR:
            case (PythonParser.FOR): {
                //since the only expression thats supported is for/range
                //we only need to check for expressions to be evaluated for range
                //TODO: check range expressions

                PyTree fbody = stmt.getForBody();
                PyTree args = stmt.getForExpr().getCallFuncArgList();
                lowerBlock(fun, fbody);
                if(stmt.getType()==PythonParser.PARFOR){
					stmt.annot = 1; //dirty dirty hack 
				}else{
					stmt.annot = 0; //dirty dirty hack;
				}
				stmt.setType(PythonParser.CFOR);
                PyTree range = new PyTree(PythonParser.SLICEOBJ);
                if (args.getChildCount() == 1) {
                    range.setChild(0, null);
                    range.setChild(1, args.getChild(0));
                    range.setChild(2, null);
                } else if (args.getChildCount() == 2) {
                    range.setChild(0, args.getChild(0));
                    range.setChild(1, args.getChild(1));
                    range.setChild(2, null);
                } else {
                    range.setChild(0, args.getChild(0));
                    range.setChild(1, args.getChild(1));
                    range.setChild(2, args.getChild(2));
                }
				
                stmt.setChild(1, range);
                index = index + 1;
                break;
            }
            case PythonParser.IF: {
                PyTree branches = stmt.getIfList();

                //heres the label : we will need this for goto
                String label = createLabel();
                //basically each branch shud have a test and a body
                for (PyTree branch : branches) {
                    PyTree cmp = branch.getChild(0);
                    PyTree body = branch.getChild(1);

                    //convert this to CIF : a test, a body and a label
                    PyTree cif = new PyTree(PythonParser.CIF);

                    //lower the test expression
                    Tuple<Integer, PyTree> lowcmp = lowerExpr(cmp, fun, block, index);
                    index = lowcmp.first;
                    cif.setChild(0, lowcmp.second);

                    //now lower the body
                    lowerBlock(fun, body);
                    cif.setChild(1, body);

                    //the third has to be label
                    cif.setChild(2, createNameExpr(label));
                    block.insertChild(index, cif);
                    index++;
                }

                //this shud be the else branch. there are no tests
                PyTree elbranch = stmt.getIfElse();
                if (elbranch.getType() == PythonParser.STMT) {
                    lowerBlock(fun, elbranch);
                    block.insertChild(index, elbranch);
                    index++;
                }

                //remove the original IF tree
                block.removeChild(index);

                //insert the label here
                block.insertChild(index, createLabelStmt(label));
                index = index + 1;
                break;

            }
            case PythonParser.RETURN:{
                Tuple<Integer,PyTree> tup = lowerExpr(stmt.getReturnExpr(),fun,block,index);
                stmt.setReturnExpr(tup.second);
                return tup.first+1;
            }
            default: {
                return index + 1;
            }
        }
        return index;

    }

    public void lowerFun(PyTree tree, boolean isMethod) {
        PyTree body = tree.getFunBody();
        for (int i = 0; i < body.getChildCount();) {
            PyTree stmt = body.getChild(i);
			System.out.println("lowering statement "+stmt.toStringTree());
            i = lowerStmt(stmt, body, tree, i);

        }
    }
}
