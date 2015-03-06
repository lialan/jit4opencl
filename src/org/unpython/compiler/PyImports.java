package org.unpython.compiler;

class PyImports{
	boolean numpy;
	boolean unpython;
	boolean shape;
	boolean zeros;
	boolean ones;
	boolean float32;
	boolean float64;
	boolean int32;
	boolean int64;
	boolean types;
	boolean gpu;
	void importNumpy(){numpy = true;}
	void importUnpython(){unpython = true;}
	
	void importFromNumpy(String name){
		if(name.equals("float32")) float32= true;
		else if(name.equals("float64")) float64=true;
		else if(name.equals("int32")) int32 = true;
		else if(name.equals("int64")) int64 = true;
		else if(name.equals("shape")) shape = true;
		else if(name.equals("zeros")) zeros = true;
	}

	void importFromUnpython(String name){
	}
}
