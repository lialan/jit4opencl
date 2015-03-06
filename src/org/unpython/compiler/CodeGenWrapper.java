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
import freemarker.template.*;

public class CodeGenWrapper{
	private Configuration cfg;
	private Template mtemplate;

	public CodeGenWrapper(){
		cfg = new Configuration();
		cfg.setClassForTemplateLoading(CodeGenWrapper.class,"");
		cfg.setObjectWrapper(new DefaultObjectWrapper());
		try{
			mtemplate = cfg.getTemplate("module.ftl");
		}catch(Exception ex){
			ex.printStackTrace();
			System.exit(-1);
		}
	}

	public Template getModuleTemplate(){
		return mtemplate;
	}
}
