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

import org.unpython.compiler.types.PyInt;
import org.unpython.compiler.types.PyFloat;
import org.unpython.compiler.types.PyType;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import org.antlr.runtime.*;
import org.unpython.compiler.frontend.*;

/**
 *
 * @author rahul
 */
public class PyUtils {
    static HashMap<String,HashMap<String,PyType>> modules;
    static{
        modules = new HashMap<String,HashMap<String,PyType>>();
    }
    
    static String getPath(String module) {
        return module;
    }

    static ArrayList<String> getStringList(String modname) {
        ArrayList<String> list = null;
        String path = getPath(modname) + ".str";
        try {
            FileInputStream in = new FileInputStream(path);
            ANTLRInputStream astream = new ANTLRInputStream(in);
            PystringLexer lexer = new PystringLexer(astream);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            PystringParser parser = new PystringParser(tokens);
            list = parser.literals();


        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        return list;
    }

    static void initNameSpace(PyTree tree) {
        if (tree.map == null) {
            tree.map = new HashMap<String, Integer>();
        }
        if (tree.localVars == null) {
            tree.localVars = new HashSet<Integer>();
        }
        if (tree.globalVars == null) {
            tree.globalVars = new HashSet<Integer>();
        }
        if (tree.closedVars == null) {
            tree.closedVars = new HashSet<Integer>();
        }
    }

    public static boolean isPrimitive(PyTree expr, PyTree root) {
        TypeCheck tc = new TypeCheck();
        PyType type = tc.exprType(expr, root);
        return isPrimitive(type);
    }

    public static boolean isPrimitive(PyType type) {
        if (type instanceof PyInt || type instanceof PyFloat) {
            return true;
        }
        return false;
    }

    static boolean isPrimitive(String name, PyTree obj) {
        return isPrimitive(obj.module.table.getType(obj.map.get(name)));
    }
    
    public static boolean hasAttr(String modname,String attr){
        assert modules.containsKey(modname):"Module does not exist "+modname;
        return modules.get(modname).containsKey(attr);
    }
    
    public static PyType getAttr(String modname,String attr){
       return modules.get(modname).get(attr);
    }
    
    public static void registerModule(PyModule mod){
        HashMap<String,PyType> modmap = new HashMap<String,PyType>();
        for(String var:mod.tree.map.keySet()){
            modmap.put(var, mod.table.getType(mod.tree.map.get(var)));
        }
        modules.put(mod.name,modmap);
    }
}
