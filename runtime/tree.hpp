#ifndef _CSEUM_TREE_H
#define _CSEUM_TREE_H

#include <vector>
#include <cassert>
#include <string>
#include <iostream>
#include <map>
using namespace std;
enum {CSEUM_PROGRAM, 
	CSEUM_DECL,
	CSEUM_ASSIGN, 
	CSEUM_STMTLIST, 
	CSEUM_AREAD, 
	CSEUM_UNPY_AREAD,
	CSEUM_AWRITE, 
	CSEUM_UNPY_AWRITE,
	CSEUM_SUBSCRIPT,
	CSEUM_SWIZ_READ, 
	CSEUM_SWIZ_WRITE, 
	CSEUM_ATYPE,
	CSEUM_DT_FLOAT, 
	CSEUM_DT_DOUBLE, 
	CSEUM_DT_INT, 
	CSEUM_CONST_INT, 
	CSEUM_CONST_FLOAT, 
	CSEUM_CONST_DOUBLE, 
	CSEUM_WHILE, 
	CSEUM_FOR, 
	CSEUM_PFOR,
	CSEUM_IF, 
	CSEUM_ELSE, 
	CSEUM_SWIZZLE, 
	CSEUM_ID, 
	CSEUM_EQ, 
	CSEUM_NEQ, 
	CSEUM_LT, 
	CSEUM_LTE, 
	CSEUM_GT, 
	CSEUM_GTE, 
	CSEUM_PLUS, 
	CSEUM_MINUS, 
	CSEUM_MUL, 
	CSEUM_DIV, 
	CSEUM_MOD, 
	CSEUM_GLOB, 
	CSEUM_INRES, 
	CSEUM_OUTRES,
	CSEUM_LITERAL,
	CSEUM_LOOP, 
	CSEUM_BREAK_LOGICALNZ, 
	CSEUM_BREAK_LOGICALZ, 
	CSEUM_STOP, 
	CSEUM_VATID, 
	CSEUM_VTID, 
	CSEUM_CAST, 
	CSEUM_LDS,  
	CSEUM_FENCE_LDS, 
	CSEUM_FENCE_THREADS, 
	CSEUM_FENCE_SR, 
	CSEUM_FENCE_MEM, 
	CSEUM_MAD};

using std::vector;
using std::string;
struct GPUType{
	int basetype;
	int dims;	
	int components;
	int numComponents() const{return components;}
	int typeSize();
};

/*by Xunhao.
it defines the GPU type in the OpenCL context.
*/
struct OCLGPUType{
	int basetype;
	int dims;
	int components;
	int numComponents() const(return components;}
	//int typeSize();
	//TODO: add more

}

struct Tree{
	void *edata;
	long int *subs;
	int *loopvars;
	int ndims;
	int tag;
	vector<Tree *> children;
	GPUType gtype;
	int idata;
	float fdata;
	double ddata;
	int pos;
	Tree(int t):tag(t){}
	
	void addChild(Tree *child){
		children.push_back(child);
	}
	
	void insertChild(int pos,Tree *child){
		if(pos>=0 && pos<=getChildCount()) 
			children.insert(children.begin()+pos,child);
	}	

	void overwriteChild(int pos,Tree *child){
		delete children[pos];
		children[pos] = child;
	}	
	int getChildCount(){ 
		return children.size();
	}
	
	Tree *getChild(int i){
		return children[i];
	}

	~Tree(){
		for(int i=0;i<children.size();i++) delete children[i];
	}

	void* getDataPtr(){
		return edata;
	}
	Tree *copy();
};

struct BaseData{
	GPUType gtype;
	int idata;
	float fdata;
	double ddata;
};

ostream &operator<<(ostream &os,GPUType gtype);
istream &operator>>(istream &is,GPUType &gtype);
ostream &operator<<(ostream &os,Tree &tree);
istream &operator>>(istream &is,Tree &tree);
int cseumCharToOffset(char c);
char cseumOffsetToChar(int offset);
string compileTree(Tree*,bool isComputeShader,int numThreadGroup,int ldsSize,int loopvars[]);
string cseumCompile(string kernel,bool isComputeShader=false);
struct ArrayInfo{
	ArrayInfo(int d,int t): dims(d), strides(d,0), uppers(d,0), type(t), base(NULL){}
	int dims;
	vector<int> strides;
	vector<int> uppers;
	int type;
	void *base;
	int getStride(int i){ return strides[i];}
	int getUpper(int i){ return uppers[i];}
	void setStride(int i,int stride){strides[i] = stride;}
	void setUpper(int i,int upper){uppers[i] = upper;}
	void *getBase(){return base;}
	void setBase(void *b){ base = b;}
};

struct LoopInfo{
	Tree *tree;
	int loopid;
	int indexid;
	int depth;
	int lower;
	int upper;
	bool isParallel;
	LoopInfo *parent;
	vector<LoopInfo *> children;
	int nloops;
};

struct LoopStruct{
	Tree *tree;
	int loopid;
	struct LoopStruct *parent;
	bool isParallel;
	int unroll;
	int reginfo;
	vector<LoopStruct *> children;
};
#endif
