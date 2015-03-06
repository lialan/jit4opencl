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
package org.unpython.compiler.frontend;

import org.unpython.compiler.types.PyDouble;
import org.unpython.compiler.types.PyFunType;
import org.unpython.compiler.types.PyInt;
import org.unpython.compiler.types.PyFloat;
import org.unpython.compiler.types.PyArrayType;
import org.unpython.compiler.types.PyType;
import org.unpython.compiler.*;
import java.util.Scanner;
import java.util.ArrayList;
import org.unpython.compiler.types.PyClassType;

public class AnnotationParser {
    

    private PyType parseTypeHelper(Scanner sc,PyModule mod) {
        sc.useDelimiter(" |\\[|\\]");
        //String next = sc.next();
        if (sc.hasNext("int")) {
            sc.next("int");
            return new PyInt();
        } else if (sc.hasNext("float")) {
            sc.next("float");
            return new PyFloat();
        } else if (sc.hasNext("double")) {
            sc.next("double");
            return new PyDouble();
        } else if (sc.hasNext("ndarray")) {
            sc.next("ndarray");
            //sc.next();
            PyType type = parseTypeHelper(sc,mod);
            int dims = sc.nextInt();
            //sc.next();
            return new PyArrayType(type, dims);

        }else{
            //check for class type
            int sym = mod.getSymbol(sc.next());
            return mod.table.getType(sym);
            
        }

    }

    public PyType parseFunType(PyTree decorator,PyModule mod) {
        //System.out.println(decorator.toStringTree());
        assert decorator.getType() == PythonParser.CALLFUNC : "decorator not proper";
        PyTree cfunc = decorator.getCallFuncExpr();
        assert cfunc.getType() == PythonParser.GETATTR : "2";
        assert cfunc.getAttrName().equals("type") : 3;
        PyTree module = cfunc.getAttrExpr();
        assert module.getType() == PythonParser.NAME : 4;
        assert module.getNameString().equals("unpython") : 5;
        PyTree types = decorator.getCallFuncArgList();
        ArrayList<PyType> params = new ArrayList<PyType>();
        PyType rtype = null;
        //System.out.println(types.toStringTree());
        for (int j = 0; j < types.getChildCount(); j++) {
            int pystr = types.getChild(j).getChild(0).getPyString();
            String temp = decorator.module.getString(pystr);
            temp = temp.substring(1,temp.length()-1);
            if(j<types.getChildCount()-1)params.add(parseTypeHelper(new Scanner(temp),mod));
            else rtype = parseTypeHelper(new Scanner(temp),mod);
        }
        PyType funtype = new PyFunType(params,rtype);
        return funtype;
    }
    
	public PyType parseConsType(PyTree decorator,PyModule mod) {
        //System.out.println(decorator.toStringTree());
        assert decorator.getType() == PythonParser.CALLFUNC : "decorator not proper";
        PyTree cfunc = decorator.getCallFuncExpr();
        assert cfunc.getType() == PythonParser.GETATTR : "2";
        assert cfunc.getAttrName().equals("type") : 3;
        PyTree module = cfunc.getAttrExpr();
        assert module.getType() == PythonParser.NAME : 4;
        assert module.getNameString().equals("unpython") : 5;
        PyTree types = decorator.getCallFuncArgList();
        ArrayList<PyType> params = new ArrayList<PyType>();
        PyType rtype = null;
        //System.out.println(types.toStringTree());
        for (int j = 0; j < types.getChildCount(); j++) {
            int pystr = types.getChild(j).getChild(0).getPyString();
            String temp = decorator.module.getString(pystr);
            temp = temp.substring(1,temp.length()-1);
            params.add(parseTypeHelper(new Scanner(temp),mod));
        }
        PyType funtype = new PyFunType(params,new PyInt());
        return funtype;
    }

    public void parseClassMembers(String annot,PyClassType klass,PyModule mod){
        annot = annot.substring(1,annot.length()-1);
        Scanner sc = new Scanner(annot);
        while(sc.hasNext()){
            String attr = sc.next();
            //System.out.println(attr);
            sc.next(":");
            klass.setAttr(attr,parseTypeHelper(sc,mod));
            if(sc.hasNext(",")) sc.next();
            else break;
        }
        
    }

    
}
