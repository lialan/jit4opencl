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


public class PyFloat extends PyType {

    @Override
    public PyType ladd(PyType obj) {
        if ((obj instanceof PyInt) || (obj instanceof PyFloat)) {
            return new PyFloat();
        } else if (obj instanceof PyDouble) {
            return new PyDouble();
        }
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PyFloat) {
            return true;
        }
        return false;
    }

    @Override
    public PyType radd(PyType obj) {
        return ladd(obj);
    }

    @Override
    public PyType lsub(PyType obj){
        return ladd(obj);
    }
    
    @Override
    public String toString() {
        return "PyFloat";
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean canAssign(PyType t){
        if(t instanceof PyFloat) return true;
        if(t instanceof PyInt) return true;
        return false;
    }

    @Override
    public PyType compareLt(PyType t){
        if(t instanceof PyFloat) return new PyInt();
        if(t instanceof PyInt) return new PyInt();
        if(t instanceof PyDouble) return new PyInt();
        return null;
    }

    @Override 
    public PyType compareGt(PyType t){
        return compareLt(t);
    }

    @Override
    public PyType compareEq(PyType t){
        return compareLt(t);
    }

    @Override 
    public PyType compareGte(PyType t){
        return compareLt(t);
    }

    @Override 
    public PyType compareLte(PyType t){ return compareLt(t);}

    @Override 
    public PyType compareNeq(PyType t){ return compareLt(t);}

}
