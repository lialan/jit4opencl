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
package org.unpython.compiler.types;

public abstract class PyType {
    public PyType ladd(PyType arg){
        return null;
    }
    public PyType radd(PyType arg){
        return null;
    }
    public PyType lsub(PyType arg){
        return null;
    }
    public PyType rsub(PyType arg){
        return null;
    }
    public PyType rmul(PyType arg){
        return null;
    }
    public PyType lmul(PyType arg){
        return null;
    }
    public PyType ldiv(PyType arg){
    	return null;
    }
    public PyType rdiv(PyType arg){
    	return null;
    }
    public boolean canAssign(PyType arg){
        if(this.equals(arg)) return true;
        return false;
    }
    
    public boolean canAssignSub(PyType subscript,PyType expr){
        return false;
    }
    
    public PyType get(PySubscript subscript){
        return null;
    }
    
    public boolean hasAttr(String name){
        return false;
    }
    public PyType getAttr(String name){
        return null;
    }

    public PyType compareLt(PyType r){ return null;}
    public PyType compareGt(PyType r){ return null;}
    public PyType compareLte(PyType r){ return null;}
    public PyType compareGte(PyType r){ return null;}
    public PyType compareEq(PyType r){return null;}
    public PyType compareNeq(PyType r){return null;}

}


