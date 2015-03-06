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

import java.util.ArrayList;
import java.util.HashMap;


public class PyClassType extends PyObjectType{
    private HashMap<String,PyType> attrmap;
    private ArrayList<PyClassType> supers;
    private String cname;
    
    public PyClassType(String cname,HashMap<String, PyType> map,ArrayList<PyClassType> supers){
        attrmap = map;
        this.cname = cname;
        this.supers = supers;
    }
    
    public PyClassType(String cname){
        attrmap = new HashMap<String,PyType>();
        supers = new ArrayList<PyClassType>();
        this.cname = cname;
    }
    
    @Override
    public boolean hasAttr(String name){
        boolean has = attrmap.containsKey(name);
		if(has) return true;
        if(!has){
            for(PyClassType sup:supers){
                if(sup.hasAttr(name)) return true;
            }
        }
		return false;
    }
    
    @Override
    public PyType getAttr(String name){
        if(attrmap.containsKey(name)) return attrmap.get(name);
        for(PyClassType sup:supers){
			System.out.println(sup.attrmap);
            if(sup.hasAttr(name)) return sup.getAttr(name);
        }
		System.out.println(supers);
        throw new RuntimeException("Class "+cname+" does not contain attr "+name);
    }
    
    public void setAttr(String name,PyType type){
        attrmap.put(name,type);
    }
    
    public ArrayList<String> getMembers(){
        ArrayList<String> members = new ArrayList<String>();
        for(String name:attrmap.keySet()){
            if(attrmap.get(name) instanceof PyMethod) continue;
            members.add(name);
        }
        return members;
    }

	public ArrayList<PyType> getSupers(){
		ArrayList<PyType> alist = new ArrayList<PyType>();
		for(PyType kls : supers) alist.add(kls);
		return alist;
	}

	public void addSuper(PyClassType sup){
		supers.add(sup);
	}

	@Override
	public String toString(){
		return "PyClassType "+cname;
	}

}
