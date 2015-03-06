/*	By Xunhao
*/
#include <assert>
#include <string>
#include "OCLCodeGen.hpp"

/*	This will convert the 
*/
static int[] getIDExpression(ProgramInfo pinfo){

}

/*	This will return the mapped name of the shared memory.
*/
static string LMADSMMap(int number){
	stringstream s;
	s << "sm";
	s << number;
	return s.str();
}

/*	generate OpenCL kernel header.
	New kernel name "GPUKernel"  
*/
static void genHeader(stringstream &ss, ProgramInfo pinfo){
	ss<<"__kernel GPUKernel(";
	
	//now generate parameter lists
	
	//generate global array lists
	for (int i = 0; i < pinfo.nlmads; i ++){
		string s;
		if (pinfo.type == CSEUM_FLOAT)
			s = "__global float ";
		else if (pinfo.type == CSEUM_INT)
			s = "__global int ";
		else //pinfo.type == CSEUM_DOUBLE
			s = "__global double ";
		ss<<s;
		//rename the lmad to.. lmad_i
		ss <<"lmad"<<i<<", ";
	}
	
	//generate primitive lists	
	for (int i = 0; i < pinfo.nlmads; i++){
		if (pinfo.primtype[i] == CSEUM_FLOAT)
			ss<<"float ";
		else if (pinfo.primtype[i] == CSEUM_INT)
			ss<<"int ";
		else if (pinfo.primtype[i] == CSEUM_DOUBLE)
			ss<<"double ";
		ss << "prim" << i << ", ";
	}
	
	//generate shared memory
	//name would be sm0, sm1, sm2, etc.
	for (int i = 0; i < pinfo.nlmads; i++){
		ss<<"__local "<<LMADSMMap(i);
		//we don't need the last comma
		if (i != pinfo.nlmads - 1)
			ss<<",";
	}
	
	//now generate the header tail
	ss<<"){\n";
	ss<<"\n";
}

/*	generate code to retrieve thread/grid ID.*/
static void genGetID(stringstream &ss, ProgramInfo pinfo, Configuration conf){
	
	//retrieve how many dimensions
	int dimsize = conf.griddim;
	int dim[3];
	for (int i = 0; i < dimsize; i++){
		dim[i] = conf.gridsize;
	}
	
	//generate ID retrieval code:
	//local id
	int lid[3];
	ss<<"int lid["<<dimsize<<"];\n";
	for (int i = 0; i < dimsize; i++){
		ss<<"lid["<<i<<"] = get_local_id("<<i<<<<");\n";
	}
	//global id
	ss<<"int gid["<<dimsize<<"];\n";
	for (int i = 0; i < dimsize; i++){
		ss<<"gid["<<i<<"] = get_global_id("<<i<<");\n";
	}
	ss<<"\n";
}
/*	generate global to shared memory load operation. It requires RCSLMAD function*/
static void genMemLoad(stringstream &ss, ProgramInfo pinfo, GPUInfo gpuinfo){
	LMAD *lmads = pinfo.lmads;
	
	//use the algorithm in the doc to load memory.
	
	
	
	//don't forget to synchronize after transfer.
	ss<<"barrier(CLK_LOCAL_MEM_FENCE);\n";
}


/*	TAG helper.*/
static string TAGHelper(int tag){
	string s;
	switch (tag){
		case CSEUM_PLUS:
			s = "+";
			break;
		case CSEUM_MINUS:
			s = "-";
			break;
		case CSEUM_MUL;
			s = " * ";
			break;
		case CSEUM_DIV:
			s = "/";
			break;
		case CSEUM_LT:
			s = "<";
			break;
		case CSEUM_GT:
			s = ">";
			break;
		case CSEUM_LTE:
			s = ">=";
			break;
		case CSEUM_GTE:
			s = "<=";
			break;
		case CSEUM_EQ:
			s = "=";
			break;
		case CSEUM_NEQ:
			 s = "!=";
			 break;
	}
	return s;
}


/*	generate function body, which will call genUnrolledBody to generate unrolled code.*/
static void genBody(stringstream &ss, ProgramInfo pinfo, GPUInfo gpuinfo){
	Tree *tree = pinfo.tree;
	assert(tree->tag == CSEUM_PFOR);
	
	//bug?
	//this will verify the perfect nest loop.
	int level = pinfo.loopNestLevel - 1;
	while (level > 0){
		//for perfect loop nest.
		assert(tree->children.size() == 1);
		level--;
		tree = tree->getChild(0);
	}
	
	//now here we start to generate body:
	ss<<compileBody(ss, tree, pinfo);

}

static string compileBody(Tree *tree, ProgramInfo pinfo){

	string s;
	switch (tree->tag){
		//statement list
		case CSEUM_STMTLIST:

			for (int i = 0; i > tree->getChildCount(); i++){
				s += compileBody(tree->getChild(i), pinfo);
				s += ";\n";
			}
			break;
		
		//for loop:
		/*
			There is something we need to talk about for loop.
			the four children of a for loop is as follows:
			child0	init stmts
			child1	test stmts
			child2	final stmts
			child3	stmt body
		*/
		case CSEUM_FOR:
			s+="for (";
			Tree *init = tree->getChild(0);
			s+=compileBody(init);
			
			Tree *test = tree->getChild(1);
			s+=compileBody(test);
			
			Tree *final = tree->getChild(2);
			s+=compileBody(final);
			
			s+="){\n";
			
			assert(Tree->getChild(3)->tag == CSEUM_STMTLIST);
			Tree *stmtbody = tree->getChild(3);
			
			s+=compileBody(stmtbody);
		
			s+="}\n";
			
			break;
		case CSEUM_ASSIGN:
			Tree *rhstree = tree->getChild(1);
			Tree *targettree = tree->getChild(0);
			
			s += compileBody(targettree);
			s += " = "
			s += compileBody(rhstree);			
			break;
			
		//Binary operations
		case 
			CSEUM_PLUS:
			CSEUM_MINUS:
			CSEUM_MUL:
			CSEUM_MOD:
			CSEUM_DIV:
			CSEUM_LT:
			CSEUM_GT:
			CSEUM_LTE:
			CSEUM_GTE:
			CSEUM_EQ:
			CSEUM_NEQ:

			s = compileBody(tree.getChildren(0));
			s += TAGHelper(tree->tag);
			s += compileBody(tree.getChildren(1));			
			break;
			
		//Uniary operations:
		//TODO: no uniary operations????
		case :
			break;
			
		//TODO: primitives:
		case
			CSEUM_:
			
			
			break;
		
		//TODO: ID:
		case
			CSEUM_ID:
				
			break;
		
		//TODO: Array read/write
		/*	Here we need to map it onto 
			shared memory
		*/
		case
			CSEUM_AREAD:
			CSEUM_AWRITE:
				s += arrayMapper(tree);
			break;
	}
	
	return s;
}

/*	Maps AREAD, AWRITE address into shared memory address, also change the subscription.
*/
static string arrayMapper(Tree* tree){
	Tree *expr = tree->getChild(1);
	Tree *arr = tree->getChild(0);
	
	string s;
	
	
	//TODO: now what?
	return s;
}

/*	generate shared to global memory store operation.*/
static void genMemStore(stringstream &ss, ProgramInfo pinfo, GPUInfo gpuinfo){
	
}

/*	generate Kernel tail.*/
static void genTail(stringstream &ss, ProgramInfo pinfo){
	ss<<"}\n";
	//flush
	ss.close();
}

