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


public class PyArrayType extends PyObjectType{
    private PyType type;
    private int dims;
    public PyArrayType(PyType type,int dims){
        this.type = type;
        this.dims = dims;
    }
    
    public int getDims(){ return dims;}
    
    @Override
    public PyType ladd(PyType arg){
    	if(arg.equals(type)){
            return new PyArrayType(type,dims);
    	}
        if(arg instanceof PyArrayType){
            PyArrayType parg = (PyArrayType)arg;
            PyType result = type.ladd(parg.type);
            if(result==null) return null;
            int newdims = dims;
            if(parg.dims>dims) newdims = parg.dims;
            if(dims==-1 || parg.dims==-1) newdims = -1;
            return new PyArrayType(result,newdims);
        }
        return null;
    }
    
    @Override
    public PyType get(PySubscript subscript){
        int idims = subscript.getDims();
        if(idims<dims) return new PyArrayType(type,dims-idims);
        if(idims>dims) return null;
        int nints = 0;
        for(int i=0;i<idims;i++){
            PyType child = subscript.getChild(i);
            if(child instanceof PyInt) nints++;
        }
        if(nints==dims) return type;
        return new PyArrayType(type,dims-nints);
    }
    
    @Override
    public boolean canAssignSub(PyType sub,PyType expr){
        if(type.canAssign(expr)) return true;
        if(expr instanceof PyArrayType){
            PyArrayType aexpr = (PyArrayType)expr;
            if(!type.canAssign(aexpr.type)) return false;
            if(aexpr.dims<dims) return true;
        }
        return false;
    }
    
    @Override
    public PyType radd(PyType arg){
    	if(arg.equals(type)){
            return new PyArrayType(type,dims);
    	}
        if(arg instanceof PyArrayType){
            PyArrayType parg = (PyArrayType)arg;
            PyType result = type.radd(parg.type);
            int newdims = dims;
            if(parg.dims>dims) newdims = parg.dims;
            return new PyArrayType(result,newdims);
        }
        return null;
    }
    
    @Override
    public PyType lmul(PyType arg){
    	if(arg.equals(type)){
            return new PyArrayType(type,dims);
    	}
        if(arg instanceof PyArrayType){
            PyArrayType parg = (PyArrayType)arg;
            PyType result = type.lmul(parg.type);
            int newdims = dims;
            if(parg.dims>dims) newdims = parg.dims;
            return new PyArrayType(result,newdims);
        }
        return null;
    }
     
    @Override
    public PyType rmul(PyType arg){
    	if(arg.equals(type)){
            return new PyArrayType(type,dims);
    	}
        if(arg instanceof PyArrayType){
            PyArrayType parg = (PyArrayType)arg;
            PyType result = type.rmul(parg.type);
            if(result==null) return null;
            int newdims = dims;
            if(parg.dims>dims) newdims = parg.dims;
            return new PyArrayType(result,newdims);
        }
        return null;
    }
      
      
    
    @Override
    public boolean equals(Object obj){
        if(!(obj instanceof PyArrayType)){
            return false;
        }
        PyArrayType p = (PyArrayType)obj;
        if(p.dims==dims && p.type.equals(type)){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + (this.type != null ? this.type.hashCode() : 0);
        hash = 83 * hash + this.dims;
        return hash;
    }
    
    @Override
    public String toString(){
        return "PyArrayType "+dims+" "+type;
    }
    
    public PyType getElementType(){
        return type;
    }

}
