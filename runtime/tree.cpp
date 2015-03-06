 /* Copyright [2009] [Rahul Garg] 
  * Licensed under the Apache License, Version 2.0 (the "License"); 
  * you may not use this file except in compliance with the License. 
  * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 
  * Unless required by applicable law or agreed to in writing, software distributed under the License 
  * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
  * See the License for the specific language governing permissions and limitations under the License. */
#include "tree.hpp"
#include <iostream>
#include <cstring>
#include <map>
#include <sstream>
extern "C"{
#include "CseumLexer.h"
#include "CseumParser.h"
}
using namespace std;
Tree *Tree::copy(){
	Tree *t = new Tree(tag);
	t->edata = edata;
	t->idata = idata;
	t->fdata = fdata;
	t->ddata = ddata;
	t->gtype = gtype;
	for(int i=0;i<children.size();i++){
		t->addChild(children[i]->copy());	
	}
	return t;
}
ostream &operator<<(ostream &os,GPUType gtype){
	os<<"( ";
	switch(gtype.basetype){
		case CSEUM_DT_FLOAT:
			os<<"float";
			break;
		case CSEUM_DT_DOUBLE:
			os<<"double";
			break;
		case CSEUM_DT_INT:
			os<<"int";
			break;
		default:
			os<<gtype.basetype;
			break;
	}
	os<<" "<<gtype.components<<" "<<gtype.dims<<" )";
	return os;	
}

istream &operator>>(istream &is,GPUType &gtype){
	string s;
	is>>s;	//read lparen
	is>>s;
	if(s=="float"){
		gtype.basetype = CSEUM_DT_FLOAT;
	}else if(s=="double"){
		gtype.basetype = CSEUM_DT_DOUBLE;
	}else if(s=="int"){
		gtype.basetype = CSEUM_DT_INT;
	}else{
		gtype.basetype = atoi(s.c_str());
	}
	is>>gtype.components;
	is>>gtype.dims;
	is>>s;
	return is;

}
string tagToName(int tag){
	switch(tag){
		case CSEUM_LITERAL:
			return "literal";
		case CSEUM_LOOP:
			return "loop";
		case CSEUM_BREAK_LOGICALZ:
			return "break_logicalz";
		case CSEUM_VATID:
			return "vatid";
		case CSEUM_PROGRAM:
			return "program";
		case CSEUM_ASSIGN:
			return "=";
		case CSEUM_STMTLIST:
			return "stmtlist";
		case CSEUM_AREAD:
			return "aread";
		case CSEUM_UNPY_AREAD:
			return "unpy_aread";
		case CSEUM_AWRITE:
			return "awrite";
		case CSEUM_UNPY_AWRITE:
			return "unpy_awrite";
		case CSEUM_SUBSCRIPT:
			return "subscript";
		case CSEUM_SWIZ_READ:
			return "swizread";
		case CSEUM_SWIZ_WRITE:
			return "swizwrite";
		case CSEUM_ATYPE:
			return "atype";
		case CSEUM_CONST_INT:
			return "constint";
		case CSEUM_CONST_FLOAT:
			return "constfloat";
		case CSEUM_CONST_DOUBLE:
			return "constdouble";
		case CSEUM_WHILE:
			return "while";
		case CSEUM_IF:
			return "if";
		case CSEUM_FOR:
			return "for";
		case CSEUM_PFOR:
			return "pfor";
		case CSEUM_ELSE:
			return "else";
		case CSEUM_SWIZZLE:
			return "swizzle";
		case CSEUM_ID:
			return "id";
		case CSEUM_EQ:
			return "eq";
		case CSEUM_NEQ:
			return "neq";
		case CSEUM_LT:
			return "lt";
		case CSEUM_LTE:
			return "lte";
		case CSEUM_GT:
			return "gt";
		case CSEUM_GTE:
			return "gte";
		case CSEUM_PLUS:
			return "+";
		case CSEUM_MINUS:
			return "-";
		case CSEUM_MUL:
			return "*";
		case CSEUM_DIV:
			return "/";
		case CSEUM_MOD:
			return "%";
		case CSEUM_GLOB:
			return "g";
		case CSEUM_INRES:
			return "inres";
		case CSEUM_OUTRES:
			return "outres";
		case CSEUM_CAST:
			return "cast";
		default:
			return "unknown";
	}
}

ostream &writeTree(ostream &os,Tree &tree,int depth=0){
	for(int i=0;i<depth;i++) os<<" ";
	os<<"( ";
	os<<tagToName(tree.tag)<<" ";
	os<<tree.gtype<<" ";
	os<<tree.idata<<" ";
	os<<tree.fdata<<" ";
	os<<tree.ddata<<" ";
	os<<tree.getChildCount()<<" ";
	for(int i=0;i<tree.getChildCount();i++){
		os<<"\n";
		writeTree(os,*(tree.getChild(i)),depth+4);
		os<<" ";
	}
	os<<")";
	return os;

}
ostream &operator<<(ostream &os,Tree &tree){
	return writeTree(os,tree);
}
int nameToTag(string name){
	//cout<<"trying to convert "<<name<<" of size "<<name.length()<<endl;
	if(name=="program") return CSEUM_PROGRAM;
	else if(name=="stmtlist") return CSEUM_STMTLIST;
	else if(name=="=") return CSEUM_ASSIGN;
	else if(name=="aread") return CSEUM_AREAD;
	else if(name=="unpy_aread") return CSEUM_UNPY_AREAD;
	else if(name=="unpy_awrite") return CSEUM_UNPY_AWRITE;
	else if(name=="awrite") return CSEUM_AWRITE;
	else if(name=="subscript") return CSEUM_SUBSCRIPT;
	else if(name=="swizread") return CSEUM_SWIZ_READ;
	else if(name=="swizwrite") return CSEUM_SWIZ_WRITE;
	else if(name=="atype") return CSEUM_ATYPE;
	else if(name=="constint") return CSEUM_CONST_INT;
	else if(name=="constfloat") return CSEUM_CONST_FLOAT;
	else if(name=="constdouble") return CSEUM_CONST_DOUBLE;
	else if(name=="while") return CSEUM_WHILE;
	else if(name=="for") return CSEUM_FOR;
	else if(name=="pfor") return CSEUM_PFOR;
	else if(name=="if") return CSEUM_IF;
	else if(name=="else") return CSEUM_ELSE;
	else if(name=="swizzle") return CSEUM_SWIZZLE;
	else if(name=="id") return CSEUM_ID;
	else if(name=="eq") return CSEUM_EQ;
	else if(name=="neq") return CSEUM_NEQ;
	else if(name=="lt") return CSEUM_LT;
	else if(name=="lte") return CSEUM_LTE;
	else if(name=="gt") return CSEUM_GT;
	else if(name=="gte") return CSEUM_GTE;
	else if(name=="+") return CSEUM_PLUS;
	else if(name=="-") return CSEUM_MINUS;
	else if(name=="*") return CSEUM_MUL;
	else if(name=="%") return CSEUM_MOD;
	else if(name=="/") return CSEUM_DIV;
	else if(name=="cast") return CSEUM_CAST;
	return -1;
}
istream &operator>>(istream &is,Tree &tree){
	string s;
	is>>s;
	is>>s;
	tree.tag = nameToTag(s);
	is>>tree.gtype;
	is>>tree.idata;
	is>>tree.fdata;
	is>>tree.ddata;
	int count;
	is>>count;
	for(int i=0;i<count;i++){
		Tree *child = new Tree(CSEUM_ID);//random tag
		is>>(*child);
		tree.addChild(child);
	}
	is>>s;
	return is;
}
int cseumCharToOffset(char c){
	if(c=='x') return 0;
	else if(c=='y') return 1;
	else if(c=='z') return 2;
	else if(c=='w') return 3;
	return -1;
}

char cseumOffsetToChar(int o){
	if(o==0) return 'x';
	else if(o==1) return 'y';
	else if(o==2) return 'z';
	else if(o==3) return 'w';
	return '?';
}

int GPUType::typeSize(){
	if(dims!=0) return -1;
	switch(basetype){
		case CSEUM_DT_INT:
		case CSEUM_DT_FLOAT:
			return components;
		case CSEUM_DT_DOUBLE:
			return 2*components;

	}
	return -1;
}

bool operator==(GPUType g1,GPUType g2){
	if(g1.basetype==g2.basetype && g1.dims==g2.dims && g1.components==g2.components) return true;
	return false;
}
bool operator!=(GPUType g1,GPUType g2){
	return (g1==g2)==false;
}


int mapTag(pANTLR3_BASE_TREE tree){
	ANTLR3_UINT32 tag = tree->getType(tree);
	switch(tag){
		case CS_PROGRAM: 
			return CSEUM_PROGRAM;
		case CS_DECL:
			return CSEUM_DECL;
		case CS_ASSIGN:
			return CSEUM_ASSIGN;
		case CS_STMTLIST:
			return CSEUM_STMTLIST;
		case CS_AREAD:
			return CSEUM_AREAD;
		case CS_AWRITE:
			return CSEUM_AWRITE;
		case CS_SWIZ_READ:
			return CSEUM_SWIZ_READ;
		case CS_SWIZ_WRITE:
			return CSEUM_SWIZ_WRITE;
		case CS_ATYPE:
			return CSEUM_ATYPE;
		case CS_DT_FLOAT:
		case CS_DT_FLOAT2:
		case CS_DT_FLOAT4:
			return CSEUM_DT_FLOAT;
		case CS_DT_DOUBLE:
		case CS_DT_DOUBLE2:
			return CSEUM_DT_DOUBLE;
		case CS_DT_INT:
		case CS_DT_INT2:
		case CS_DT_INT4:
			return CSEUM_DT_INT;
		case CS_CONST_INT:
			return CSEUM_CONST_INT;
		case CS_CONST_FLOAT:
			return CSEUM_CONST_FLOAT;
		case CS_CONST_DOUBLE:
			return CSEUM_CONST_DOUBLE;
		case CS_WHILE:
			return CSEUM_WHILE;
		case CS_FOR:
			return CSEUM_FOR;
		case CS_SWIZZLE:
			return CSEUM_SWIZZLE;
		case CS_ID:
			return CSEUM_ID;
		case CS_INPUT_BUF:
			return CSEUM_INRES;
		case CS_OUTPUT_BUF:
			return CSEUM_OUTRES;
		case CS_GLOB:
			return CSEUM_GLOB;
		case CS_EQ:
			return CSEUM_EQ;
		case CS_NEQ:
			return CSEUM_NEQ;
		case CS_LT:
			return CSEUM_LT;
		case CS_GT:
			return CSEUM_GT;
		case CS_PLUS:
			return CSEUM_PLUS;
		case CS_MINUS:
			return CSEUM_MINUS;
		case CS_MUL:
			return CSEUM_MUL;
		case CS_DIV:
			return CSEUM_DIV;
		case CS_MOD:
			return CSEUM_MOD;
		case CS_IF:
			return CSEUM_IF;
		case CS_ELSE:
			return CSEUM_ELSE;
		case CS_VATID:
			return CSEUM_VATID;
		case CS_VTID:
			return CSEUM_VTID;
		case CS_CAST:
			return CSEUM_CAST;
		case CS_LDS:
			return CSEUM_LDS;
		case CS_FENCE_LDS:
			return CSEUM_FENCE_LDS;
		case CS_FENCE_MEM:
			return CSEUM_FENCE_MEM;
		case CS_FENCE_SR:
			return CSEUM_FENCE_SR;
		case CS_FENCE_THREADS:
			return CSEUM_FENCE_THREADS;
		default:
			cout<<"error in mapping tag "<<tag<<endl;
			return -1;
	}

	return CSEUM_PROGRAM;
}



struct SymTable{
	map<string,BaseData> table;
	SymTable *base;
	SymTable(SymTable *b=NULL):base(b){};
	bool contains(string text);
	bool containsLocal(string text);
	BaseData get(string text);
	void add(string text,BaseData val){ table[text] = val;}
};


bool SymTable::contains(string text){
	bool local = containsLocal(text);
	if(local==true) return true;
	else if(base==NULL) return false;
	else return base->contains(text);
}

BaseData SymTable::get(string text){
	map<string,BaseData>::iterator iter = table.find(text);
	if(iter!=table.end()) return iter->second;
	return base->get(text);
}

bool SymTable::containsLocal(string text){
	map<string,BaseData>::iterator iter = table.find(text);
	if(iter!=table.end()) return true;
	return false;
}

struct EBaseData{
	BaseData data;
	SymTable *syms;
	EBaseData():syms(NULL){}
	~EBaseData(){ if(syms!=NULL) delete syms;}
};

static Tree* mapNode(pANTLR3_BASE_TREE tree){
	ANTLR3_UINT32 tag = tree->getType(tree);
	Tree *ptree;
	bool convertedToMad = false;
	
	if(tag==CS_PLUS){
		pANTLR3_BASE_TREE ltree = (pANTLR3_BASE_TREE)tree->getChild(tree,0);
		pANTLR3_BASE_TREE rtree = (pANTLR3_BASE_TREE)tree->getChild(tree,1);
		if(rtree->getType(rtree)==CS_MUL){
			cout<<"converting to mad"<<endl;
			ptree = new Tree(CSEUM_MAD);
			BaseData data = ((EBaseData *)tree->u)->data;
			ptree->idata = data.idata;
			ptree->fdata = data.fdata;
			ptree->ddata = data.ddata;
			ptree->gtype = data.gtype;
			ptree->addChild(mapNode((pANTLR3_BASE_TREE)rtree->getChild(rtree,0)));
			ptree->addChild(mapNode((pANTLR3_BASE_TREE)rtree->getChild(rtree,1)));
			ptree->addChild(mapNode(ltree));
			convertedToMad = true;
		}
	}
	if(!convertedToMad){
		ptree = new Tree(mapTag(tree));
		BaseData data = ((EBaseData *)tree->u)->data;
		ptree->idata = data.idata;
		ptree->fdata = data.fdata;
		ptree->ddata = data.ddata;
		ptree->gtype = data.gtype;
		for(ANTLR3_UINT32 i=0;i<tree->getChildCount(tree);i++){
			pANTLR3_BASE_TREE child = (pANTLR3_BASE_TREE)tree->getChild(tree,i);
			if(child->getType(child)==CS_DECL) continue;
			if(child->getType(child)==CS_SWIZZLE) continue;
			ptree->addChild(mapNode(child));	
		}
	}	
	return ptree;
}
class TypeCheck{
	int idnum;
	pANTLR3_BASE_TREE_ADAPTOR adaptor;
	bool isComputeShader;
	int ldsSize;
	int numThreadGroup;
	public:
	TypeCheck(pANTLR3_BASE_TREE_ADAPTOR ad,bool isCS):idnum(15),adaptor(ad),isComputeShader(isCS),ldsSize(4),numThreadGroup(64){}
	int checkProgram(pANTLR3_BASE_TREE tree,int &num_thread_group,int &lds_size);
	int checkStmtlist(pANTLR3_BASE_TREE tree,SymTable *base);
	int checkTree(pANTLR3_BASE_TREE tree,SymTable *base);
	int checkAssign(pANTLR3_BASE_TREE tree,SymTable *base);
	int checkFor(pANTLR3_BASE_TREE tree, SymTable *base);
	int checkWhile(pANTLR3_BASE_TREE tree, SymTable *base);
	int checkExpr(pANTLR3_BASE_TREE tree,SymTable *base);
	int checkSwizRead(pANTLR3_BASE_TREE tree, SymTable *base);
	int checkSwizWrite(pANTLR3_BASE_TREE tree,SymTable *base);
	void debug(string text){cout<<text<<endl;}
	int checkIf(pANTLR3_BASE_TREE tree,SymTable *base);
	bool binaryUpcast(pANTLR3_BASE_TREE tree,GPUType *result);
	GPUType getType(pANTLR3_BASE_TREE tree);	
};


//TODO
int TypeCheck::checkSwizRead(pANTLR3_BASE_TREE tree,SymTable *base){
	pANTLR3_BASE_TREE idtree,swiztree;
	idtree = (pANTLR3_BASE_TREE)tree->getChild(tree,0);
	swiztree = (pANTLR3_BASE_TREE)tree->getChild(tree,1);
	int res = checkTree(idtree,base);
	if(res==0) return 0;
	GPUType gtype = ((EBaseData *)idtree->u)->data.gtype;
	int idata = cseumCharToOffset((char)swiztree->getText(swiztree)->chars[0]);
	GPUType treetype;
	treetype.dims = 0;
	treetype.components = 1;
	treetype.basetype = gtype.basetype;
	EBaseData *treedata = ((EBaseData *)tree->u);
	treedata->data.gtype = treetype;
	treedata->data.idata = idata;
	return 1;
}

//TODO
int TypeCheck::checkSwizWrite(pANTLR3_BASE_TREE tree,SymTable *base){
	return checkSwizRead(tree,base);
}
//TODO: fix this
bool TypeCheck::binaryUpcast(pANTLR3_BASE_TREE tree, GPUType *result){
	pANTLR3_BASE_TREE ltree = (pANTLR3_BASE_TREE)tree->getChild(tree,0);
	pANTLR3_BASE_TREE rtree = (pANTLR3_BASE_TREE)tree->getChild(tree,1);
	GPUType ltype = ((EBaseData *)ltree->u)->data.gtype;
	GPUType rtype = ((EBaseData *)rtree->u)->data.gtype;
	
	if(ltype.dims!=0 || rtype.dims!=0) return false;
	if(ltype.numComponents() != rtype.numComponents()) return false;
	if(ltype!=rtype){
		int upcast = 0;
		int lbt = ltype.basetype;
		int rbt = rtype.basetype;
		if(lbt==CSEUM_DT_INT && rbt==CSEUM_DT_FLOAT) upcast = -1;
		if(lbt==CSEUM_DT_INT && rbt==CSEUM_DT_DOUBLE) upcast = -1;
		if(lbt==CSEUM_DT_FLOAT && rbt==CSEUM_DT_DOUBLE) upcast=-1;
		if(rbt==CSEUM_DT_INT && lbt==CSEUM_DT_FLOAT) upcast = 1;
		if(rbt==CSEUM_DT_INT && lbt==CSEUM_DT_DOUBLE) upcast = 1;
		if(rbt==CSEUM_DT_FLOAT && lbt==CSEUM_DT_DOUBLE) upcast = 1;
		if(upcast==0) return false;
		pANTLR3_BASE_TREE castnode = (pANTLR3_BASE_TREE)adaptor->createTypeText(adaptor,CS_CAST,(uint8_t *)"()");
		EBaseData *data = new EBaseData;
		castnode->u = (void *)data;
		if(upcast==-1){
			*result = rtype;
			castnode->addChild(castnode,ltree);
			data->data.gtype = rtype;
			tree->setChild(tree,0,(void *)castnode);
			return true;
		}else if(upcast==1){
			*result = ltype;
			castnode->addChild(castnode,rtree);
			data->data.gtype = ltype;
			tree->setChild(tree,1,(void *)castnode);	
			return true;
		}
		
	}else{
		*result = ltype;
		return true;
	}
	return false;

}
int TypeCheck::checkIf(pANTLR3_BASE_TREE tree,SymTable *base){
	int nchild = tree->getChildCount(tree);
	pANTLR3_BASE_TREE expr = (pANTLR3_BASE_TREE)tree->getChild(tree,0);
	pANTLR3_BASE_TREE stmt1 = (pANTLR3_BASE_TREE)tree->getChild(tree,1);

	//the test expression must have type int
	int res1 = checkTree(expr,base);
	if(res1==0) return 0;

	GPUType exprtype = ((EBaseData *)expr->u)->data.gtype;
	if(exprtype.basetype!=CSEUM_DT_INT || exprtype.dims!=0){
	   stringstream msg;
	   msg<<"expression does not have type int instead has type "<<exprtype.basetype<<" "<<exprtype.dims<<endl;
	   debug(msg.str());
	   return 0;
	}

	//check stmt
	if(checkTree(stmt1,base)==0) return 0;
	return 1;
}

int TypeCheck::checkWhile(pANTLR3_BASE_TREE tree,SymTable *base){
	if(tree->getChildCount(tree)!=2) return 0;
	pANTLR3_BASE_TREE testtree,stmttree;
	testtree = (pANTLR3_BASE_TREE)tree->getChild(tree,0);
	stmttree = (pANTLR3_BASE_TREE)tree->getChild(tree,1);
	if(checkTree(testtree,base)==0 || checkTree(stmttree,base)==0) return 0;
	int testtype = ((EBaseData *)testtree->u)->data.gtype.basetype;
	if(testtype!=CSEUM_DT_INT) return 0;
	return 1;	
}
int TypeCheck::checkFor(pANTLR3_BASE_TREE tree,SymTable *base){
	//TODO: fix this function
	if(tree->getChildCount(tree)!=4){
		cout<<"Number of for children "<<tree->getChildCount(tree)<<endl;
	   	return 0;
	}
	pANTLR3_BASE_TREE inittree,testtree,finaltree,stmttree;
	inittree = (pANTLR3_BASE_TREE)tree->getChild(tree,0);
	testtree = (pANTLR3_BASE_TREE)tree->getChild(tree,1);
	finaltree = (pANTLR3_BASE_TREE)tree->getChild(tree,2);
	stmttree = (pANTLR3_BASE_TREE)tree->getChild(tree,3);
	int res=1;
	if(checkTree(inittree,base)==0){
		res = 0;
	}
	if(checkTree(testtree,base)==0){
	   	res= 0;
	}

	if(checkTree(finaltree,base)==0){
	   	res= 0;
	}
	if(checkTree(stmttree,base)==0){
	   	res= 0;
	}
	return res;	
}

//TODO: finish this, handle all cases
GPUType TypeCheck::getType(pANTLR3_BASE_TREE tree){
	GPUType gtype;
	int tag = tree->getType(tree);
	if(tag==CS_ATYPE){
		gtype = getType((pANTLR3_BASE_TREE)tree->getChild(tree,0));
		gtype.dims = 2;
		return gtype;
	}else{
		gtype.dims = 0;
		gtype.basetype = mapTag(tree);
		switch(tree->getType(tree)){
			case CS_DT_INT:
			case CS_DT_FLOAT:
			case CS_DT_DOUBLE:
				gtype.components = 1;
				break;
			case CS_DT_INT2:
			case CS_DT_FLOAT2:
			case CS_DT_DOUBLE2:
				gtype.components = 2;
				break;
			case CS_DT_INT4:
			case CS_DT_FLOAT4:
				gtype.components = 4;
				break;
		}
	}
	return gtype;
}

int TypeCheck::checkProgram(pANTLR3_BASE_TREE tree,int &num_thread_group,int &lds_size){
	int res = checkStmtlist((pANTLR3_BASE_TREE)tree->getChild(tree,0),NULL);
	num_thread_group = numThreadGroup;
	lds_size = ldsSize;
	return res;
}

int TypeCheck::checkStmtlist(pANTLR3_BASE_TREE tree,SymTable *const base){
	ANTLR3_UINT32 i;
	EBaseData *edata = (EBaseData *)tree->u;
	edata->syms = new SymTable(base);
	//process all declarations	
	for(i=0;i<tree->getChildCount(tree);i++){
		pANTLR3_BASE_TREE child = (pANTLR3_BASE_TREE)tree->getChild(tree,i);
		int childtag = child->getType(child);
		if(childtag!=CS_DECL) break;
		pANTLR3_BASE_TREE idtree = (pANTLR3_BASE_TREE)child->getChild(child,1);
		pANTLR3_BASE_TREE dttree = (pANTLR3_BASE_TREE)child->getChild(child,0);
		int idtag = idtree->getType(idtree);
		string idtext((char *)idtree->getText(idtree)->chars);
		if(idtag==CS_GLOB || idtag==CS_ID || idtag==CS_OUTPUT_BUF || idtag==CS_INPUT_BUF){
			GPUType gtype = getType(dttree);
			if(edata->syms->containsLocal(idtext)){
				debug("double declaration of "+idtext);
				return 0;
			}
			int res;
			BaseData childdata;
			childdata.gtype = gtype;
			//check if we are declaring g,i0,o0 etc in an inner block
			if(!(idtag==CS_ID)){
				if(base!=NULL){
					debug("cannot declare "+idtext+" in inner block");
					return 0;
				}
			}else{
				childdata.idata = idnum;
				idnum++;
			}
			//checked, now put string and data in symbol table	
			edata->syms->add(idtext,childdata);
		}else if(idtag==CS_LDS){
			BaseData childdata;
			childdata.gtype = getType((pANTLR3_BASE_TREE)dttree->getChild(dttree,0));
			pANTLR3_BASE_TREE ldsSizeTree = (pANTLR3_BASE_TREE)dttree->getChild(dttree,1);	
			ldsSize = atoi((char *)(ldsSizeTree->getText(ldsSizeTree)->chars));
			edata->syms->add(idtext,childdata);
		}else if(idtag==CS_NUM_THREADS){
			numThreadGroup = atoi((char *)(dttree->getText(dttree)->chars));	
		}
	}
	for(;i<tree->getChildCount(tree);i++){
		int res = checkTree((pANTLR3_BASE_TREE)tree->getChild(tree,i),edata->syms);
		if(res==0) return 0;
	}	
	return 1;
}


//TODO: fix this
static bool canAssign(GPUType target,GPUType expr){
	return true;
}

int TypeCheck::checkAssign(pANTLR3_BASE_TREE tree,SymTable *base){
	pANTLR3_BASE_TREE rhstree = (pANTLR3_BASE_TREE)(tree->getChild(tree,1));
	pANTLR3_BASE_TREE lhstree = (pANTLR3_BASE_TREE)(tree->getChild(tree,0));
	int res1 = checkTree(rhstree,base);
	int res2 = checkTree(lhstree,base);
	if(res1==0 || res2==0) return 0;
	GPUType rtype = ((EBaseData *)rhstree->u)->data.gtype;
	GPUType ltype = ((EBaseData *)lhstree->u)->data.gtype;
	if(rtype.numComponents()!=ltype.numComponents()) return 0;
	if(rtype.basetype!=ltype.basetype){
		if((ltype.basetype==CSEUM_DT_FLOAT && rtype.basetype==CSEUM_DT_INT)
				|| (ltype.basetype=CSEUM_DT_DOUBLE && rtype.basetype==CSEUM_DT_FLOAT)){
			pANTLR3_BASE_TREE castnode = (pANTLR3_BASE_TREE)adaptor->createTypeText(adaptor,CS_CAST,(uint8_t *)"()");
			EBaseData *data = new EBaseData;
			data->data.gtype = ltype;
			castnode->u = (void *)data;
			castnode->addChild(castnode,rhstree);
			tree->setChild(tree,1,castnode);
		}else{
			return 0;
		}	
	}
	((EBaseData *)tree->u)->data.gtype = ltype;
	return 1;
}
int TypeCheck::checkTree(pANTLR3_BASE_TREE tree,SymTable *base){
	int tag = tree->getType(tree);
	EBaseData *tdata = (EBaseData *)tree->u;
	string text((const char *)tree->getText(tree)->chars);
	int res = 0;
	switch(tag){
		case CS_SWIZ_READ:
			res = checkSwizRead(tree,base);
			break;
		case CS_SWIZ_WRITE:
			res = checkSwizWrite(tree,base);
			break;
		case CS_STMTLIST:{ 
			res =  checkStmtlist(tree,base);
			break;
						 }
		case CS_ASSIGN:{
			res = checkAssign(tree,base);
			break;
					   }
		case CS_ID:{
			if(!(base->contains(text))){
				res = 0;
				break;
			}
			BaseData data = base->get(text);
			tdata->data = data;
			res = 1;
			break;
		}
		case CS_CAST:{
			if(tree->getChildCount(tree)!=2) cout<<"something went horribly wrong"<<endl;
			//we should only reach here from a parse tree
			pANTLR3_BASE_TREE typetree = (pANTLR3_BASE_TREE)tree->getChild(tree,0);
			tdata->data.gtype.basetype = typetree->getType(typetree);
			tdata->data.gtype.dims = 0;
			tree->deleteChild(tree,0);
			res =checkTree((pANTLR3_BASE_TREE)tree->getChild(tree,0),base);
			break;
		}
		case CS_VTID:
		case CS_VATID:{
			if(isComputeShader){
				tdata->data.gtype.basetype = CSEUM_DT_INT;
				tdata->data.gtype.dims = 0;
				tdata->data.gtype.components = 1;
				res = 1;
			}else{
				tdata->data.gtype.basetype = CSEUM_DT_FLOAT;
				tdata->data.gtype.dims = 0;
				tdata->data.gtype.components = 2;
				res = 1;
			}
			break;
		}
		case CS_OUTPUT_BUF:
		case CS_INPUT_BUF:{
			BaseData data = base->get(text);
			tdata->data.gtype = data.gtype;
			tdata->data.idata = tree->getText(tree)->chars[1] - '0';
			res = 1;
			break;
		}
		case CS_FOR: 
			res = checkFor(tree,base);
			break;
		case CS_WHILE:
			res = checkWhile(tree,base);
			break;
		case CS_CONST_INT:{
			tdata->data.idata = atoi((const char *)tree->getText(tree)->chars);
			tdata->data.gtype.basetype = CSEUM_DT_INT;
			tdata->data.gtype.dims = 0;
			tdata->data.gtype.components = 1;
			return 1;
		}
		case CS_CONST_FLOAT:{
			tdata->data.fdata = atof((const char *)tree->getText(tree)->chars);
			tdata->data.gtype.basetype = CSEUM_DT_FLOAT;
			tdata->data.gtype.dims = 0;
			tdata->data.gtype.components = 1;
			return 1;
		}	
		case CS_CONST_DOUBLE:{
			tdata->data.ddata = strtod((const char *)tree->getText(tree)->chars,NULL);
			tdata->data.gtype.basetype = CSEUM_DT_DOUBLE;
			tdata->data.gtype.dims = 0;
			tdata->data.gtype.components = 1;
			return 1;
		}

		case CS_MOD:
		case CS_PLUS:
		case CS_MUL:
		case CS_MINUS:
		case CS_DIV:{
			pANTLR3_BASE_TREE rtree,ltree;
			rtree = (pANTLR3_BASE_TREE)tree->getChild(tree,1);
			ltree = (pANTLR3_BASE_TREE)tree->getChild(tree,0);
			int res1,res2;
			res1 = checkTree(ltree,base);
			res2 = checkTree(rtree,base);
			if(res1==0 or res2==0){
				res = 0;
				break;
			}
			GPUType ltype = ((EBaseData *)ltree->u)->data.gtype;
			GPUType rtype = ((EBaseData *)rtree->u)->data.gtype;
			bool success= binaryUpcast(tree,&tdata->data.gtype);	
			if(!success) res = 0;
			else res = 1;
			break;
		}
		//TODO: fix, do actual checks, add more relations operators like ne
		case CS_LT:
		case CS_GT:
		case CS_GTE:
		case CS_LTE:{
			pANTLR3_BASE_TREE rtree,ltree;
			rtree = (pANTLR3_BASE_TREE)tree->getChild(tree,1);
			ltree = (pANTLR3_BASE_TREE)tree->getChild(tree,0);
			int res1 = checkTree(ltree,base);
			int res2 = checkTree(rtree,base);
			tdata->data.gtype.basetype = CSEUM_DT_INT;
			tdata->data.gtype.dims = 0;
			tdata->data.gtype.components = 1;
			GPUType temp;
			bool success = binaryUpcast(tree,&temp);
			if(!success) cout<<"binary upcast did not succeed "<<tree->toStringTree(tree)->chars<<endl;
			if(!success) res = 0;
			else res = 1;
			break;
		}
		case CS_AWRITE:
		case CS_AREAD:{
			pANTLR3_BASE_TREE atree,exprtree;
			atree = (pANTLR3_BASE_TREE)tree->getChild(tree,0);
			exprtree= (pANTLR3_BASE_TREE)tree->getChild(tree,1);
			int res1 = checkTree(atree,base);
			int res2 = checkTree(exprtree,base);
			if(res1==0 || res2==0){
				res = 0;
				break;
			}
			GPUType exprtype = ((EBaseData *)exprtree->u)->data.gtype;
			if(atree->getType(atree)==CS_INPUT_BUF){
				if(exprtype.numComponents()!=2){
					res = 0;
					break;
				}
				if(!(exprtype.basetype==CSEUM_DT_INT || exprtype.basetype==CSEUM_DT_FLOAT)){
					res = 0;
					break;
				}
				if(exprtype.basetype==CSEUM_DT_INT){
					//TODO:need to insert cast here to float2
					pANTLR3_BASE_TREE castnode = (pANTLR3_BASE_TREE)adaptor->createTypeText(adaptor,CS_CAST,(uint8_t *)"()");
					EBaseData *data = new EBaseData;
					castnode->u = (void *)data;
					data->data.gtype.basetype = CSEUM_DT_FLOAT;
					data->data.gtype.components = 2;
					data->data.gtype.dims = 0;
					castnode->addChild(castnode,exprtree);
					tree->setChild(tree,1,(void *)castnode);
				}
				if(tree->getChildCount(tree)==4){
					pANTLR3_BASE_TREE c1 = (pANTLR3_BASE_TREE)tree->getChild(tree,2);
					pANTLR3_BASE_TREE c2 = (pANTLR3_BASE_TREE)tree->getChild(tree,3);
				}
			}else if(atree->getType(atree)==CS_GLOB){
				if(exprtype.numComponents()!=1 || exprtype.basetype!=CSEUM_DT_INT){
					res = 0;
					break;
				}
			}else if(atree->getType(atree)==CS_LDS){
				if(tag==CS_AREAD){
					if(exprtype.numComponents()!=2 || exprtype.basetype!=CSEUM_DT_INT){
						res = 0;
						break;
					}
				}
				if(tag==CSEUM_AWRITE){
					if(exprtype.numComponents()!=1 || exprtype.basetype!=CSEUM_DT_INT){
						res = 0;
						break;
					}
				}	
				res = 1;
			}
			tdata->data.gtype = ((EBaseData *)atree->u)->data.gtype;
			if(atree->getType(atree)==CS_LDS) cout<<"lds read/write "<<tdata->data.gtype.numComponents()<<" "<<tdata->data.gtype.basetype<<endl;
			tdata->data.gtype.dims = 0;
			res = 1;
			break;
		}
		case CS_IF:
			res = checkIf(tree,base);
			break;
		case CS_FENCE_MEM:
		case CS_FENCE_THREADS:
		case CS_FENCE_SR:
		case CS_FENCE_LDS:
			if(isComputeShader){
				res = 1;
				break;
			}else{
				res = 0;
				break;
			}
		case CS_LDS:
		case CS_GLOB:
			tdata->data.gtype = base->get(text).gtype;
			res = 1;
			break;
		default:
			cout<<tree->toStringTree(tree)->chars<<endl;	
			break;
	}
	if(res==0) cout<<"Type error at "<<tree->toStringTree(tree)->chars<<endl;
	return res;
}
static void allocBaseData(pANTLR3_BASE_TREE tree){
	tree->u = (void *)(new EBaseData);
	for(ANTLR3_UINT32 i=0;i<tree->getChildCount(tree);i++){
		allocBaseData((pANTLR3_BASE_TREE)tree->getChild(tree,i));
	}
}

static void freeBaseData(pANTLR3_BASE_TREE tree){
	delete (EBaseData *)tree->u;
	for(ANTLR3_UINT32 i=0;i<tree->getChildCount(tree);i++){
		freeBaseData((pANTLR3_BASE_TREE)tree->getChild(tree,i));
	}
}

/*
string cseumCompile(string str,bool isComputeShader){
	pANTLR3_INPUT_STREAM input = antlr3NewAsciiStringCopyStream((pANTLR3_UINT8)str.c_str(),str.size(),NULL);
	pCseumLexer lxr = CseumLexerNew(input);
	pANTLR3_COMMON_TOKEN_STREAM tstream = antlr3CommonTokenStreamSourceNew(ANTLR3_SIZE_HINT, TOKENSOURCE(lxr));
	pCseumParser psr = CseumParserNew(tstream);
	CseumParser_program_return preturn = psr->program(psr);
	pANTLR3_BASE_TREE tree = preturn.tree;
	cout<<tree->toStringTree(tree)->chars<<endl;
	allocBaseData(tree);
	TypeCheck tc(psr->adaptor,isComputeShader);
	int ldsSize,numThreadGroup;
	int res = tc.checkProgram(tree,numThreadGroup,ldsSize);
	Tree *ntree = mapNode(preturn.tree);
	freeBaseData(tree);
	string result = compileTree(ntree,isComputeShader,numThreadGroup,ldsSize);
	psr->free(psr); psr = NULL;
	tstream->free(tstream); tstream = NULL;
	lxr->free(lxr); lxr = NULL;
	input->free(input); input = NULL;
	return result;
}*/




