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

import org.unpython.compiler._
import org.unpython.compiler.frontend._
import org.antlr.runtime._
import org.antlr.runtime.tree._
import java.io.FileInputStream

object Unpython extends Application{
    override def main(args:Array[String]){
        for(name <- args){
            val input = new ANTLRInputStream(new FileInputStream(name+".pyk"));
            val lexer = new PythonLexer(input);
            val tokens = new CommonTokenStream(lexer);
            val parser = new PythonParser(tokens);
            parser.setTreeAdaptor(new CommonTreeAdaptor());
            val result = parser.module();
            val t = new PyTree(result.getTree().asInstanceOf[CommonTree]);
            val module = new PyModule(name,t);
            module.checkType();
            println("type checked");
            PyUtils.registerModule(module);
            val codegen = new Codegen();
            println("generating code");
            codegen.dumpModuleCode(module);
            println("generated code");

        }
    }
}
