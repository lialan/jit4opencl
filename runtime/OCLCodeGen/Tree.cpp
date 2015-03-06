#include "tree.hpp"
#include <string>
#include <iostream>
#include <sstream>
#include <map>
#include <boost/timer.hpp>
using boost::timer;
using namespace std;
struct CFGData{
	BaseData data;
	vector<CFGData *> parents;
bool *livevars;
};
union LitData{
	int idata;
	float fdata;
	double ddata;
};


void printTree(Tree *tree,int offset=0,bool isBase=true){
	stringstream name;
	switch(tree->tag){
		case CSEUM_PROGRAM:
			name<<"program";
			break;
		case CSEUM_STMTLIST:
			for(int i=0;i<offset;i++) name<<' ';
			name<<"stmtlist\n";
			offset += 3;
			break;
		case CSEUM_ASSIGN:
			for(int i=0;i<offset;i++) name<<' ';
			name<<"=";
			break;
		case CSEUM_FOR:
			for(int i=0;i<offset;i++) name<<' ';
			name<<"for\n";
			offset += 3;
			break;
		case CSEUM_ID:
			name<<"id "<<tree->idata<<" "<<tree->gtype.basetype<<" "<<tree->gtype.components;
			break;
		case CSEUM_PLUS:
			name<<"+";
			break;
		case CSEUM_MUL:
			name<<"*";
			break;
		case CSEUM_DIV:
			name<<"/";
			break;
		case CSEUM_MOD:
			name<<"%";
			break;
		case CSEUM_MINUS:
			name<<"-";
			break;
		case CSEUM_AREAD:
			name<<"aread";
			break;
		case CSEUM_AWRITE:
			name<<"awrite";
			break;
		case CSEUM_GLOB:
			name<< "glob";
			break;
		case CSEUM_INRES:
			name<<"in"<<tree->idata;
			break;
		case CSEUM_OUTRES:
			name<<"o"<<tree->idata;
			break;
		case CSEUM_CONST_INT:
			name<<"const "<<tree->idata;
			break;
		case CSEUM_CONST_FLOAT:
			name<<"const "<<tree->fdata;
			break;
		case CSEUM_LT:
			name<<"<";
			break;
		case CSEUM_GT:
			name<<">";
			break;
		case CSEUM_GTE:
			name<<">=";
			break;
		case CSEUM_LTE:
			name<<"<=";
			break;
		case CSEUM_WHILE:
			name<<"while";
			break;
		case CSEUM_IF:
			name<<"if";
			break;
		case CSEUM_ELSE:
			name<<"else";
			break;
		case CSEUM_LITERAL:
			name<<"literal ";
			if(tree->gtype.basetype==CSEUM_DT_INT) name<<tree->idata;
			if(tree->gtype.basetype==CSEUM_DT_FLOAT) name<<tree->fdata;
			break;
		case CSEUM_STOP:
			name<<"stop";
			break;
		case CSEUM_LOOP:
			name<<"loop";
			break;
		case CSEUM_BREAK_LOGICALZ:
			name<<"break_logicalz";
			break;
		case CSEUM_BREAK_LOGICALNZ:
			name<<"break_logicalnz";
			break;
		case CSEUM_VATID:
			name<<"vatid";
			break;
		case CSEUM_VTID:
			name<<"vTid";
			break;
		case CSEUM_SWIZ_READ:
			name<<"swiz "<<tree->idata<<" ";
			break;
		case CSEUM_SWIZ_WRITE:
			name<<"swiz "<<tree->idata<<" ";
			break;
		case CSEUM_SWIZZLE:
			name<<"swizzle";
			break;
		case CSEUM_CAST:
			name<<"cast "<<tree->gtype.basetype;
			break;
		case CSEUM_LDS:
			name<<"lds";
			break;
		case CSEUM_UNPY_AREAD:
			name<<"unpy_aread";
			break;
		case CSEUM_UNPY_AWRITE:
			name<<"unpy_awrite";
			break;
		default:
			name<<"unknown "<<tree->tag;
			break;

	}	
	cout<<"("<<name.str();
	for(int i=0;i<tree->getChildCount();i++) printTree(tree->getChild(i),offset,false);
	cout<<")";
	if(tree->tag==CSEUM_ASSIGN || tree->tag==CSEUM_FOR ) cout<<'\n';
	if(isBase) cout<<endl;
}

static bool isAtom(Tree *tree){
	int tag = tree->tag;
	switch(tag){
		case CSEUM_ID:
			return true;
		case CSEUM_OUTRES:
			return true;
		case CSEUM_LITERAL:
			return true;
		case CSEUM_SWIZ_READ:
			return true;
		default:
			return false;
	}
}


static bool isAtomicExpr(Tree *tree){
	int tag = tree->tag;
	switch(tag){
		case CSEUM_ID:
			return true;
		case CSEUM_INRES:
			return true;
		case CSEUM_OUTRES:
			return true;
		case CSEUM_GLOB:
			return true;
		default:
			return false;
	}
}

bool isSimpleAlu(Tree *tree){
	int tag=tree->tag;
	if(tag==CSEUM_ID || tag==CSEUM_VATID || tag==CSEUM_VTID) return true;
	if(tag==CSEUM_PLUS || tag==CSEUM_MINUS || tag==CSEUM_MUL || tag==CSEUM_DIV || tag==CSEUM_MOD){
		if(tree->getChild(0)->tag==CSEUM_ID && tree->getChild(1)->tag==CSEUM_ID) return true;
		return false;
	}
	if(tag==CSEUM_MAD){
		if(tree->getChild(0)->tag==CSEUM_ID && tree->getChild(1)->tag==CSEUM_ID || tree->getChild(2)->tag==CSEUM_ID) return true;
	}
	if(tag==CSEUM_AREAD && tree->getChild(0)->tag==CSEUM_GLOB && tree->getChild(1)->tag==CSEUM_ID) return true;
	return false;
}

bool isLdsWrite(Tree *tree){
	if(tree->tag==CSEUM_LDS) return true;
	for(int i=0;i<tree->getChildCount();i++){
		if(isLdsWrite(tree->getChild(i))) 
			return true;
	}
	return false;
}

bool isSimpleTarget(Tree *tree){
	if(tree->tag==CSEUM_OUTRES) return true;
	if(tree->tag==CSEUM_ID) return true;
	if(tree->tag==CSEUM_SWIZ_WRITE && tree->getChild(0)->tag==CSEUM_ID ) return true;
	if(tree->tag==CSEUM_AWRITE && (tree->getChild(0)->tag==CSEUM_GLOB || tree->getChild(0)->tag==CSEUM_LDS) && tree->getChild(1)->tag==CSEUM_ID) return true;
	return false;
}

/*By Xunhao.
	this method instead creates the compile tree for OPENCL.
*/

//TODO: check to see whether 
vector<Tree *> compileLltreeOCL(Tree *tree, BackendInfo *Info)[
	vector<Tree *> *vec = new vector<Tree *>;
	int tag = tree->tag;
	Tree *ntree = new Tree(tree->tag);
	ntree->idata = tree->idata;
	ntree->fdata = tree->fdata;
	ntree->ddata = tree->ddata;
	ntree->gtype = tree->gtype;
	if(tag==CSEUM_STMTLIST || tag==CSEUM_PROGRAM){
	
	}else if (tag==CSEUM_ID || tag==CSEUM_INRES || tag==CSEUM_OUTRES || tag==CSEUM_GLOB || tag==CSEUM_LITERAL || tag==CSEUM_VATID || tag==CSEUM_VTID){
	
	}else if (tag==CSEUM_ASSIGN){
	
	}else if (tag==CSEUM_SWIZ_READ){
	
	}else if (tag==CSEUM_FOR){
	
	}else if (tag==CSEUM_WHILE){
	
	}else if (tag==CSEUM_IF){
	
	}else if (tag==CSEUM_PLUS || tag==CSEUM_MINUS || tag==CSEUM_MUL || tag==CSEUM_MOD || tag==CSEUM_DIV || tag==CSEUM_LT || tag==CSEUM_GT || tag==CSEUM_LTE 
			|| tag==CSEUM_GTE || tag==CSEUM_NEQ || tag==CSEUM_EQ){
	
	}else if (tag==CSEUM_AREAD){
	
	}else if (tag==CSEUM_UNPY_AREAD){
	
	}else if (tag==CSEUM_AWRITE){
	
	}else if (tag==CSEUM_UNPY_AWRITE){
	
	}else if (tag==CSEUM_CAST){
	
	}else if (tag==CSEUM_SWIZ_WRITE){
	
	}else if (tag==CSEUM_MAD){
	
	}else if (tag==CSEUM_FENCE_LDS){
	
	}else if (tag==CSEUM_PFOR){
	
	}else{
		cout<<"Unknown tag name: "<<tag<<"\n";
	}
	
	return vec;
}
