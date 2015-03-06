/* Copyright [2009] [Rahul Garg] 
  * Licensed under the Apache License, Version 2.0 (the "License"); 
  * you may not use this file except in compliance with the License. 
  * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 
  * Unless required by applicable law or agreed to in writing, software distributed under the License 
  * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
  * See the License for the specific language governing permissions and limitations under the License. */
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
struct BackendInfo{
	map<int,int> renames;
	vector<GPUType> idtypes;
	GPUType intypes[8];
	GPUType outtypes[8];
	GPUType gtype;
	vector<LitData> literals;
	vector<int> lbasetypes;
	void computeInfo(Tree *tree,bool base=true);
	vector<int> regs;
	vector<int> offsets;
	vector<int> literal_regs;
	vector<int> literal_offsets;
	int maxreg;
	bool isComputeShader;
	int numThreadGroup;
	int ldsSize;
};


void BackendInfo::computeInfo(Tree *tree,bool base){
	int tag = tree->tag;
	if(base){
		for(int i=0;i<8;i++){
			intypes[i].basetype = -1;
			intypes[i].dims = -1;
			outtypes[i].basetype = -1;
			outtypes[i].dims = -1;
		}
	}
	if(tag==CSEUM_ID){
		int id = tree->idata;
		map<int,int>::iterator iter = renames.find(id);
		if(iter==renames.end()){
			int newid = idtypes.size();
			renames[id] = newid;
			tree->idata = newid;
			idtypes.push_back(tree->gtype);
		}else{
			tree->idata = (*iter).second;
		}
	}else if(tag==CSEUM_INRES){
		intypes[tree->idata] = tree->gtype;
	}else if(tag==CSEUM_OUTRES){
		outtypes[tree->idata] = tree->gtype;
	}else if(tag==CSEUM_GLOB){
		gtype = tree->gtype;
	}else if(tag==CSEUM_CONST_INT || tag==CSEUM_CONST_FLOAT || tag==CSEUM_CONST_DOUBLE){
		LitData literal;
		if(tag==CSEUM_CONST_INT) literal.idata = tree->idata;
		else if(tag==CSEUM_CONST_FLOAT) literal.fdata = tree->fdata;
		else if(tag==CSEUM_CONST_DOUBLE) literal.ddata = tree->ddata;
		literals.push_back(literal);
		lbasetypes.push_back(tree->gtype.basetype);
		tree->tag = CSEUM_LITERAL;
		tree->idata = literals.size()-1;
	}
	for(int i=0;i<tree->getChildCount();i++) 
		computeInfo(tree->getChild(i),false);
	if(base){
		literal_regs.resize(4096,0);
		literal_offsets.resize(4096,0);
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








//each thing compiles to a list of stuff
//for expressions, it returns a list of stuff if and only if the expression needs to be split
//into multiple pieces. if an expression is split into multiple pieces, then the last piece will
//simply be an ID of some temporary variable containig the value of the expression
//TODO: requires major refactoring. lots of code duplication
vector<Tree *> *compileLltree(Tree *tree,BackendInfo *info){
	vector<Tree *> *vec = new vector<Tree *>;
	int tag = tree->tag;
	Tree *ntree = new Tree(tree->tag);
	ntree->idata = tree->idata;
	ntree->fdata = tree->fdata;
	ntree->ddata = tree->ddata;
	ntree->gtype = tree->gtype;
	if(tag==CSEUM_STMTLIST || tag==CSEUM_PROGRAM){
		vec->push_back(ntree);
		for(int i=0;i<tree->getChildCount();i++){
			vector<Tree *> *childvec = compileLltree(tree->getChild(i),info);
			for(int j=0;j<childvec->size();j++){
				ntree->addChild(childvec->at(j));
			}
			delete childvec;
		}
		if(tag==CSEUM_PROGRAM){
			vec->push_back(new Tree(CSEUM_STOP));
		}
	}else if(tag==CSEUM_ID || tag==CSEUM_INRES || tag==CSEUM_OUTRES || tag==CSEUM_GLOB || tag==CSEUM_LITERAL || tag==CSEUM_VATID || tag==CSEUM_VTID ) {
		vec->push_back(ntree);
	}else if(tag==CSEUM_SWIZ_READ){
		for(int i=0;i<tree->getChildCount();i++){
			ntree->addChild(tree->getChild(i)->copy());
		}
		vec->push_back(ntree);
	}else if(tag==CSEUM_ASSIGN){
		Tree *exprtree = tree->getChild(1);
		Tree *targettree = tree->getChild(0);
		Tree *exprcfg,*targetcfg;
		if(isSimpleAlu(exprtree) && !(isLdsWrite(targettree))){
			exprcfg = exprtree->copy();
		}else{
			vector<Tree *> *rvec = compileLltree(exprtree,info);
			for(int i=0;i<rvec->size()-1;i++){
				vec->push_back(rvec->at(i));
			}
			exprcfg = rvec->at(rvec->size()-1);
			delete rvec;
		}
		if(isSimpleTarget(targettree)){
			targetcfg = targettree->copy();
		}else{
			vector<Tree *> *lvec = compileLltree(targettree,info);
			for(int i=0;i<lvec->size()-1;i++){
				vec->push_back(lvec->at(i));	
			}
			targetcfg = lvec->at(lvec->size()-1);
			delete lvec;
		}
	
		ntree->addChild(targetcfg);
		ntree->addChild(exprcfg);
		vec->push_back(ntree);

	}else if(tag==CSEUM_FOR){
		//convert into CSEUM_LOOP and CSEUM_BREAK_LOGICALZ
		vector<Tree *> *initvec,*testvec,*finalvec,*stmtvec;
		initvec = compileLltree(tree->getChild(0),info);
		testvec = compileLltree(tree->getChild(1),info);
		finalvec = compileLltree(tree->getChild(2),info);
		stmtvec = compileLltree(tree->getChild(3),info);
		for(int i=0;i<initvec->size();i++){
			vec->push_back(initvec->at(i));
		}
		vec->push_back(ntree);

		//ok now change the loop from CSEUM_FOR to CSEUM_LOOP
		ntree->tag = CSEUM_LOOP;
		//now in the body first put the test stuff
		for(int i=0;i<testvec->size()-1;i++){
			ntree->addChild(testvec->at(i));
		}
		Tree *testexpr = testvec->at(testvec->size()-1);

		//now the break stmt
		Tree *breakstmt = new Tree(CSEUM_BREAK_LOGICALZ);
		breakstmt->addChild(testexpr);
		ntree->addChild(breakstmt);
		//now add the body
		for(int i=0;i<stmtvec->size();i++){
			ntree->addChild(stmtvec->at(i));
		}

		//now add the finalization stuff
		//TODO: if the final thingy is not actually assignment but just an expression then u shud do something more
		for(int i=0;i<finalvec->size();i++){
			ntree->addChild(finalvec->at(i));
		}
		delete stmtvec;
		delete initvec;
		delete testvec;
		delete finalvec;
	}else if(tag==CSEUM_WHILE){
		//convert into CSEUM_LOOP and CSEUM_BREAK_LOGICALZ
		vector<Tree *> *testvec,*stmtvec;
		testvec = compileLltree(tree->getChild(0),info);
		stmtvec = compileLltree(tree->getChild(1),info);
		vec->push_back(ntree);

		//ok now change the loop from CSEUM_FOR to CSEUM_LOOP
		ntree->tag = CSEUM_LOOP;
		//now in the body first put the test stuff
		for(int i=0;i<testvec->size()-1;i++){
			ntree->addChild(testvec->at(i));
		}
		Tree *testexpr = testvec->at(testvec->size()-1);

		//now the break stmt
		Tree *breakstmt = new Tree(CSEUM_BREAK_LOGICALZ);
		breakstmt->addChild(testexpr);
		ntree->addChild(breakstmt);
		//now add the body
		for(int i=0;i<stmtvec->size();i++){
			ntree->addChild(stmtvec->at(i));
		}

		delete stmtvec;
		delete testvec;
	}else if(tag==CSEUM_IF){	
		//TODO: implement this thingy
		cout<<"transformations for 'if' not implemented in backend yet :("<<endl;
	}else if(tag==CSEUM_PLUS || tag==CSEUM_MINUS || tag==CSEUM_MUL || tag==CSEUM_MOD || tag==CSEUM_DIV || tag==CSEUM_LT || tag==CSEUM_GT || tag==CSEUM_LTE 
			|| tag==CSEUM_GTE || tag==CSEUM_NEQ || tag==CSEUM_EQ ){
		vector<Tree *> *lvec,*rvec;
		lvec = compileLltree(tree->getChild(0),info);
		rvec = compileLltree(tree->getChild(1),info);
		for(int i=0;i<lvec->size()-1;i++) vec->push_back(lvec->at(i));
		for(int i=0;i<rvec->size()-1;i++) vec->push_back(rvec->at(i));
		ntree->addChild(lvec->at(lvec->size()-1));
		ntree->addChild(rvec->at(rvec->size()-1));
		
		Tree *assign = new Tree(CSEUM_ASSIGN);
		assign->gtype = tree->gtype;
		assign->addChild(new Tree(CSEUM_ID));
		assign->addChild(ntree);
		vec->push_back(assign);
		info->idtypes.push_back(tree->gtype);
		int newid = info->idtypes.size()-1;
		assign->getChild(0)->idata = newid;
		assign->getChild(0)->gtype = tree->gtype;
		vec->push_back(assign->getChild(0)->copy());

		delete lvec;
		delete rvec;
	}else if(tag==CSEUM_AREAD){
		Tree *expr = tree->getChild(1);
		Tree *arr = tree->getChild(0);
		vector<Tree *> *exprvec = compileLltree(expr,info);
		for(int i=0;i<exprvec->size()-1;i++) vec->push_back(exprvec->at(i));
		ntree->addChild(arr->copy());
		ntree->addChild(*(exprvec->end()-1));

		//now we need a assign  temp = arr[expr], return temp;
		Tree *assign = new Tree(CSEUM_ASSIGN);
		assign->addChild(new Tree(CSEUM_ID));
		assign->addChild(ntree);
		vec->push_back(assign);
		info->idtypes.push_back(tree->gtype);
		int newid = info->idtypes.size()-1;
		assign->getChild(0)->idata = newid;
		assign->getChild(0)->gtype = tree->gtype;
		vec->push_back(assign->getChild(0)->copy());
		delete exprvec;
	}else if(tag==CSEUM_UNPY_AREAD){
		ntree->tag = CSEUM_AREAD;
		GPUType indextype;
		indextype.basetype = CSEUM_DT_FLOAT;
		indextype.components = 2;
		indextype.dims = 0;
		info->idtypes.push_back(indextype);
	  	int indexsym = info->idtypes.size()-1;
		tree->addChild(new Tree(CSEUM_ID));	
		tree->getChild(1)->idata = indexsym;
		tree->getChild(1)->gtype = indextype;

		GPUType t1;
		t1.basetype = CSEUM_DT_INT;
		t1.components = 1;
		t1.dims = 0;

		info->idtypes.push_back(t1);
		int lmadsym = info->idtypes.size()-1;
		Tree *lmadtree = new Tree(CSEUM_ASSIGN);
		lmadtree->gtype = t1;
		lmadtree->addChild(new Tree(CSEUM_ID));
		lmadtree->getChild(0)->idata = lmadsym;
		lmadtree->getChild(0)->gtype = t1;
		
		Tree *lmadexpr = new Tree(CSEUM_PLUS);
		lmadexpr->gtype = t1;
		Tree *current = lmadexpr;
		for(int i=0;i<tree->ndims;i++){
			Tree *multree = new Tree(CSEUM_MUL);
			multree->gtype = t1;
			Tree *consttree = new Tree(CSEUM_LITERAL);
			LitData ldata;
			ldata.idata = (int)(tree->subs[i]);
			info->literals.push_back(ldata);
			info->lbasetypes.push_back(CSEUM_DT_INT);
			consttree->gtype = t1;
			consttree->idata = info->literals.size()-1;
			Tree *lvartree = new Tree(CSEUM_ID);
			lvartree->gtype = t1;
			lvartree->idata = info->renames[tree->loopvars[i]];
			multree->addChild(consttree);
			multree->addChild(lvartree);
			current->addChild(multree);
			if(i<(tree->ndims-1)){
				current->addChild(new Tree(CSEUM_PLUS));
				current = current->getChild(1);
				current->gtype = t1;
			}
		}
		current->addChild(new Tree(CSEUM_LITERAL));
		current->getChild(1)->gtype = t1;
		LitData ldata;
		ldata.idata = tree->subs[tree->ndims];
		info->literals.push_back(ldata);
		info->lbasetypes.push_back(CSEUM_DT_INT);
		current->getChild(1)->idata = info->literals.size()-1;
		vector<Tree *> *lmadexprvec = compileLltree(lmadexpr,info);
		for(int i=0;i<lmadexprvec->size()-1;i++) vec->push_back(lmadexprvec->at(i));
		lmadtree->addChild(lmadexprvec->at(lmadexprvec->size()-1));
		
		vec->push_back(lmadtree);
		
		GPUType intindextype;
		intindextype.basetype = CSEUM_DT_INT;
		intindextype.components = 2;
		intindextype.dims = 0;
		info->idtypes.push_back(intindextype);
		int intindexsym = info->idtypes.size()-1;

			
		Tree *index1 = new Tree(CSEUM_ASSIGN);
		index1->gtype = t1;
		index1->addChild(new Tree(CSEUM_SWIZ_WRITE));
		index1->getChild(0)->gtype = t1;
		index1->getChild(0)->idata = 0;
		index1->getChild(0)->addChild(new Tree(CSEUM_ID));
		index1->getChild(0)->getChild(0)->idata = intindexsym;
		index1->getChild(0)->getChild(0)->gtype = intindextype;
		index1->addChild(new Tree(CSEUM_MOD));
		index1->getChild(1)->gtype = t1;
		index1->getChild(1)->addChild(new Tree(CSEUM_ID));
		index1->getChild(1)->getChild(0)->idata = lmadsym;
		index1->getChild(1)->getChild(0)->gtype = t1;
		index1->getChild(1)->addChild(new Tree(CSEUM_LITERAL));
		index1->getChild(1)->getChild(1)->gtype = t1;
		ldata.idata = 8192;
		info->literals.push_back(ldata);
		info->lbasetypes.push_back(CSEUM_DT_INT);
		index1->getChild(1)->getChild(1)->idata = info->literals.size()-1;
		vec->push_back(index1);	
		
		Tree *index2 = new Tree(CSEUM_ASSIGN);
		index2->gtype = t1;
		index2->addChild(new Tree(CSEUM_SWIZ_WRITE));
		index2->getChild(0)->gtype = t1;
		index2->getChild(0)->idata = 1;
		index2->getChild(0)->addChild(new Tree(CSEUM_ID));
		index2->getChild(0)->getChild(0)->idata = intindexsym;
		index2->getChild(0)->getChild(0)->gtype = intindextype;
		index2->addChild(new Tree(CSEUM_DIV));
		index2->getChild(1)->gtype = t1;
		index2->getChild(1)->addChild(new Tree(CSEUM_ID));
		index2->getChild(1)->getChild(0)->idata = lmadsym;
		index2->getChild(1)->getChild(0)->gtype = t1;
		index2->getChild(1)->addChild(new Tree(CSEUM_LITERAL));
		index2->getChild(1)->getChild(1)->gtype = t1;
		ldata.idata = 8192;
		info->literals.push_back(ldata);
		info->lbasetypes.push_back(CSEUM_DT_INT);
		index2->getChild(1)->getChild(1)->idata = info->literals.size()-1;
		vec->push_back(index2);	

		Tree *floatcast = new Tree(CSEUM_ASSIGN);
		floatcast->addChild(new Tree(CSEUM_ID));
		floatcast->getChild(0)->idata = indexsym;
		floatcast->getChild(0)->gtype = indextype;
		floatcast->addChild(new Tree(CSEUM_CAST));
		floatcast->getChild(1)->gtype = indextype;
		floatcast->getChild(1)->addChild(new Tree(CSEUM_ID));
		floatcast->getChild(1)->getChild(0)->idata = intindexsym;
		floatcast->getChild(1)->getChild(0)->gtype = intindextype;
		vec->push_back(floatcast);
	
		Tree *expr = tree->getChild(1);
		Tree *arr = tree->getChild(0);
		vector<Tree *> *exprvec = compileLltree(expr,info);
		for(int i=0;i<exprvec->size()-1;i++) vec->push_back(exprvec->at(i));
		ntree->addChild(arr->copy());
		ntree->addChild(*(exprvec->end()-1));

		//now we need a assign  temp = arr[expr], return temp;
		Tree *assign = new Tree(CSEUM_ASSIGN);
		assign->addChild(new Tree(CSEUM_ID));
		assign->addChild(ntree);
		vec->push_back(assign);
		info->idtypes.push_back(tree->gtype);
		int newid = info->idtypes.size()-1;
		assign->getChild(0)->idata = newid;
		assign->getChild(0)->gtype = tree->gtype;
		vec->push_back(assign->getChild(0)->copy());
		delete exprvec;
	}else if(tag==CSEUM_AWRITE){
		Tree *expr = tree->getChild(1);
		Tree *arr = tree->getChild(0);
		vector<Tree*> *exprvec = compileLltree(expr,info);
		for(int i=0;i<exprvec->size()-1;i++) vec->push_back(exprvec->at(i));
		ntree->addChild(arr->copy());
		ntree->addChild(*(exprvec->end()-1));
		vec->push_back(ntree);
		delete exprvec;
	}else if(tag==CSEUM_UNPY_AWRITE){
		ntree->tag = CSEUM_OUTRES;
		ntree->idata = tree->getChild(0)->idata;
		vec->push_back(ntree);
	}else if(tag==CSEUM_CAST){
		Tree *expr = tree->getChild(0);
		vector<Tree*> *exprvec = compileLltree(expr,info);
		for(int i=0;i<exprvec->size()-1;i++) vec->push_back(exprvec->at(i));
		//what you need is to do a cast of the last expression and then store in a temporary
		ntree->addChild(*(exprvec->end()-1));
		Tree *assign = new Tree(CSEUM_ASSIGN);
		assign->addChild(new Tree(CSEUM_ID));
		assign->addChild(ntree);
		vec->push_back(assign);
		info->idtypes.push_back(tree->gtype);
		int newid = info->idtypes.size()-1;
		assign->getChild(0)->idata = newid;
		assign->getChild(0)->gtype = tree->gtype;
		vec->push_back(assign->getChild(0)->copy());
		delete exprvec;
	}else if(tag==CSEUM_SWIZ_WRITE){
		Tree *target = tree->getChild(0);
		if(target->tag==CSEUM_ID){
			ntree->addChild(target->copy());
			vec->push_back(ntree);
		}else if(target->tag==CSEUM_AWRITE){
			Tree *expr = target->getChild(1);
			vector<Tree *> *exprvec = compileLltree(expr,info);
			for(int i=0;i<exprvec->size()-1;i++) vec->push_back(exprvec->at(i));
			Tree *ntarget = target->copy();
			ntarget->overwriteChild(1,*(exprvec->end()-1));
			ntree->addChild(ntarget);
			//printTree(ntree);
			vec->push_back(ntree);
			delete exprvec;
		}	
	}else if(tag==CSEUM_MAD){
		vector<Tree *> *lvec,*rvec1,*rvec2;
		lvec = compileLltree(tree->getChild(0),info);
		rvec1 = compileLltree(tree->getChild(1),info);
		rvec2 = compileLltree(tree->getChild(2),info);
		for(int i=0;i<lvec->size()-1;i++) vec->push_back(lvec->at(i));
		for(int i=0;i<rvec1->size()-1;i++) vec->push_back(rvec1->at(i));
		for(int i=0;i<rvec2->size()-1;i++) vec->push_back(rvec2->at(i));
		ntree->addChild(lvec->at(lvec->size()-1));
		ntree->addChild(rvec1->at(rvec1->size()-1));
		ntree->addChild(rvec2->at(rvec2->size()-1));
		
		Tree *assign = new Tree(CSEUM_ASSIGN);
		assign->addChild(new Tree(CSEUM_ID));
		assign->addChild(ntree);
		vec->push_back(assign);
		info->idtypes.push_back(tree->gtype);
		int newid = info->idtypes.size()-1;
		assign->getChild(0)->idata = newid;
		assign->getChild(0)->gtype = tree->gtype;
		vec->push_back(assign->getChild(0)->copy());

		delete lvec;
		delete rvec1;
		delete rvec2;
	
	}else if(tag==CSEUM_FENCE_LDS){
		vec->push_back(ntree);
	}else if(tag==CSEUM_PFOR){
		Tree *assign = new Tree(CSEUM_ASSIGN);
		Tree *rhs = new Tree(CSEUM_SWIZ_READ);
		if(tree->getChild(1)->tag==CSEUM_STMTLIST && tree->getChild(1)->getChild(0)->tag==CSEUM_PFOR) rhs->idata = 0;
		else if(tree->getChild(1)->tag==CSEUM_PFOR) rhs->idata = 0;
		else rhs->idata = 1;
		rhs->gtype.basetype = CSEUM_DT_INT;
		rhs->gtype.components = 1;
		rhs->gtype.dims = 0;
		rhs->addChild(new Tree(CSEUM_VATID));
		Tree *lhs = new Tree(CSEUM_ID);
		lhs->idata = tree->getChild(0)->idata;
		lhs->gtype.basetype = CSEUM_DT_INT;
		lhs->gtype.components = 1;
		lhs->gtype.dims = 0;
		assign->gtype = lhs->gtype;
		assign->addChild(lhs);
		assign->addChild(rhs);
		vec->push_back(assign);
		vector<Tree *> *stmtvec = compileLltree(tree->getChild(1),info);
		for(int i=0;i<stmtvec->size();i++) vec->push_back(stmtvec->at(i));
		delete stmtvec;
	}else{
		cout<<"Unknown tag "<<tag<<endl;
	}
	return vec;

}

bool allocateRegisters(Tree *tree,BackendInfo *info){
	//qdos register allocation hack
	int maxreg = info->maxreg;
	int size = 0;
	int curreg=2,curoffset=0;
	info->regs.resize(info->idtypes.size(),0);
	info->offsets.resize(info->idtypes.size(),0);
	for(int i=0;i<info->idtypes.size();i++){
		int s = info->idtypes[i].typeSize();
		if(s<=0 || s>4){
		   	cout<<"id "<<i<<" with unknown typesize "<<s<<endl;
			return false;
		}else{
			if((curoffset+s)<=4){
				info->regs[i] = curreg;
				info->offsets[i] = curoffset;
				curoffset += s;	
			}else{
				curreg++;
				if(curreg>=maxreg) return false;
				curoffset = s;
				info->regs[i] = curreg;
				info->offsets[i] = 0;
			}
	   		size +=s ;
		}	
	}
	return true;
}

static string compileSourceTree(Tree *tree,BackendInfo *info,int toffset){
	stringstream ss;
	if(toffset>4) toffset = 4;
	if(toffset<0) toffset = 0;
	int ts = tree->gtype.typeSize();
	if(ts>4 || ts<0){
		printTree(tree);
		return "weird typesize of ";
	}	
	int tag = tree->tag;
	if(tag==CSEUM_ID){
		int id = tree->idata;
		ss<<"r"<<info->regs[id]<<".";
		for(int i=0;i<toffset;i++)ss<<"0";
		for(int i=0;i<ts;i++){
			int offset = info->offsets[id] + i;
			ss<<cseumOffsetToChar(offset);
		}
	}else if(tag==CSEUM_LITERAL){
		int id = tree->idata;
		ss<<'l'<<info->literal_regs[id]<<'.';
		for(int i=0;i<toffset;i++) ss<<'0';
		if(tree->gtype.basetype!=CSEUM_DT_DOUBLE){
			ss<<cseumOffsetToChar(info->literal_offsets[id]);
		}else{
			ss<<cseumOffsetToChar(info->literal_offsets[id]);
			ss<<cseumOffsetToChar(info->literal_offsets[id]+1);
		
		}
	}else if(tag==CSEUM_SWIZ_READ){
		//TODO: fix swizzles of double
		Tree *expr = tree->getChild(0);
		if(expr->tag==CSEUM_ID){
			int id = expr->idata;
			ss<<'r'<<info->regs[id]<<'.';
			for(int i=0;i<toffset;i++) ss<<'0';
			int btype = expr->gtype.basetype;
			if(btype!=CSEUM_DT_DOUBLE){
				ss<<cseumOffsetToChar(tree->idata+info->offsets[id]);
			}else{
				int o1 = 2*tree->idata+info->offsets[id];
				int o2 = o1+1;
				ss<<cseumOffsetToChar(o1)<<cseumOffsetToChar(o2);
			}
		}else if(expr->tag==CSEUM_VATID){
			if(info->isComputeShader){
				ss<<"vaTid.";
				for(int i=0;i<toffset;i++) ss<<'0';
				ss<<'x';
			}else{
				ss<<"r1.";
				for(int i=0;i<toffset;i++) ss<<'0';
				ss<<cseumOffsetToChar(tree->idata);
			}
		}else if(expr->tag==CSEUM_VTID){
			ss<<"vTid.";
			for(int i=0;i<toffset;i++) ss<<'0';
			ss<<cseumOffsetToChar(tree->idata);
		}
	}
	return ss.str();
}

static string compileTargetTree(Tree *tree,BackendInfo *info,int *toffset){
	stringstream ss;
	int tag = tree->tag;
	if(tag==CSEUM_ID){
		int id = tree->idata;
		*toffset = info->offsets[id];
		ss<<"r"<<info->regs[id]<<".";
		int ts = tree->gtype.typeSize();
		if(ts<0 || ts>4){
			return string("weird typesize");
		}
		for(int i=0;i<ts;i++){
			int offset = info->offsets[id] + i;
			ss<<cseumOffsetToChar(offset);
		}
	}else if(tag==CSEUM_OUTRES){
		ss<<'o'<<tree->idata;
		*toffset = 0;
	}else if(tag==CSEUM_AWRITE){
		ss<<"g["<<compileSourceTree(tree->getChild(1),info,0)<<"]";
		*toffset = 0;
	}else if(tag==CSEUM_SWIZ_WRITE){
		//TODO: fix swizzles of double 
		Tree *expr = tree->getChild(0);
		if(expr->tag==CSEUM_ID){
			int id = expr->idata;
			ss<<'r'<<info->regs[id]<<'.';
			if(expr->gtype.basetype==CSEUM_DT_DOUBLE){
				int offset = 2*tree->idata + info->offsets[id];
				*toffset = offset;
				ss<<cseumOffsetToChar(offset);
				ss<<cseumOffsetToChar(offset+1);
			}else{
				int offset = tree->idata + info->offsets[id];
				*toffset = offset;
				ss<<cseumOffsetToChar(offset);
			}
		}else if(expr->tag==CSEUM_AWRITE){
			ss<<"g["<<compileSourceTree(expr->getChild(1),info,0)<<"].";
			*toffset = tree->idata;
			if(tree->getChild(0)->gtype.basetype=CSEUM_DT_DOUBLE){
				ss<<cseumOffsetToChar(2*tree->idata);
				ss<<cseumOffsetToChar(2*tree->idata+1);
			}else{
				ss<<cseumOffsetToChar(tree->idata);
			}
		}else if(expr->tag==CSEUM_OUTRES){
			ss<<'o'<<expr->idata<<'.';
			*toffset = tree->idata;
			if(expr->gtype.basetype==CSEUM_DT_DOUBLE){
			   ss<<cseumOffsetToChar(2*tree->idata);
			   ss<<cseumOffsetToChar(2*tree->idata+1);
			}else{ 
				ss<<cseumOffsetToChar(tree->idata);
			}
		}else{
			ss<<"weird target "<<expr->tag;
		}
	}else{
		ss<<"target is "<<tag;
	}
	return ss.str();
}
static string compileBinaryOp(Tree *tree,BackendInfo *info){
	stringstream ss;
	Tree *rhstree = tree->getChild(1);
	int toffset;
	ss<<compileTargetTree(tree->getChild(0),info,&toffset)<<", ";
	if(tree->getChild(0)->gtype.basetype==CSEUM_DT_DOUBLE){
	   toffset = 0;
	   //cout<<"toffset is 0"<<endl;
	}
	for(int i=0;i<rhstree->getChildCount()-1;i++){
		ss<<compileSourceTree(rhstree->getChild(i),info,toffset)<<", ";
	}
	ss<<compileSourceTree(rhstree->getChild(rhstree->getChildCount()-1),info,toffset);
	return ss.str();
}

static string compileAssignTree(Tree *tree,BackendInfo *info){
	stringstream ss;
	Tree *rhstree = tree->getChild(1);
	Tree *targettree = tree->getChild(0);
	if(isLdsWrite(targettree)){
		ss<<"lds_write_vec _lOffset(";
		ss<<targettree->idata;
		ss<<") mem, "<<compileSourceTree(rhstree,info,0)<<'\n';
		return ss.str();
	}
	int toffset;
	//TODO: all this should go in a hashtable not this stupid switch/case
	switch(rhstree->tag){
		case CSEUM_PLUS:{
			switch(rhstree->gtype.basetype){
				case CSEUM_DT_INT:
					ss<<"iadd ";
					break;
				case CSEUM_DT_FLOAT:
					ss<<"add ";
					break;
				case CSEUM_DT_DOUBLE:
					ss<<"dadd ";
					break;
			}	
			ss<<compileBinaryOp(tree,info);
			break;
		}
		case CSEUM_MINUS:{
			switch(rhstree->gtype.basetype){
				case CSEUM_DT_FLOAT:
					ss<<"sub ";
					break;
				case CSEUM_DT_DOUBLE:
					ss<<"dsub ";
					break;
			}	
			ss<<compileBinaryOp(tree,info);
			break;
		}
		case CSEUM_MUL:{
			switch(rhstree->gtype.basetype){
				case CSEUM_DT_INT:
					ss<<"imul ";
					break;
				case CSEUM_DT_FLOAT:
					ss<<"mul ";
					break;
				case CSEUM_DT_DOUBLE:
					ss<<"dmul ";
					break;	
			}
			ss<<compileBinaryOp(tree,info);
			break;
		}
		case CSEUM_MOD:{
			switch(rhstree->gtype.basetype){
				case CSEUM_DT_INT:
					ss<<"umod ";
					break;
				case CSEUM_DT_FLOAT:
					ss<<"mod ";
					break;
			}
			ss<<compileBinaryOp(tree,info);
			break;
		}
		case CSEUM_DIV:{
			switch(rhstree->gtype.basetype){
				case CSEUM_DT_INT:
					ss<<"udiv ";
					break;
				case CSEUM_DT_FLOAT:
					ss<<"div ";
					break;
			}
			ss<<compileBinaryOp(tree,info);
			break;
		}
		case CSEUM_VTID:
		case CSEUM_VATID:
		case CSEUM_LITERAL:
		case CSEUM_SWIZ_READ:
		case CSEUM_ID:{
			ss<<"mov "<<compileTargetTree(tree->getChild(0),info,&toffset)<<", ";
			ss<<compileSourceTree(rhstree,info,toffset);
			break;
		}
		case CSEUM_AREAD:{
			Tree *arr = rhstree->getChild(0);
			Tree *target = tree->getChild(0);
			if(arr->tag==CSEUM_GLOB) {
				ss<<"mov r0, g["<<compileSourceTree(rhstree->getChild(1),info,0)<<"]\n";
			}
			else if(arr->tag==CSEUM_INRES){
			   	ss<<"sample_resource("<<arr->idata<<")_sampler("<<arr->idata<<")";
				if(rhstree->getChildCount()==4){
					ss<<"_aoffimmi(";
					ss<<rhstree->getChild(2)->idata<<".0 ,";
					ss<<rhstree->getChild(3)->idata<<".0 ,";
					ss<<",0.0)";
				}
				ss<<" r0, "<<compileSourceTree(rhstree->getChild(1),info,0)<<"00\n";
			}else if(arr->tag==CSEUM_LDS){
				ss<<"lds_read_vec r0, "<<compileSourceTree(rhstree->getChild(1),info,0)<<'\n';
			}
			ss<<"mov "<<compileTargetTree(target,info,&toffset)<<", r0.";
			for(int i=0;i<toffset;i++) ss<<'0';
			int ts = target->gtype.typeSize();
			if(ts<0 || ts>4) return string("weird size");
			for(int i=0;i<ts;i++){
				ss<<cseumOffsetToChar(i);
			}
			break;
		}
		case CSEUM_LT:{
			switch(rhstree->getChild(0)->gtype.basetype){
				case CSEUM_DT_INT:
					ss<<"ilt ";
					break;
				case CSEUM_DT_FLOAT:
					ss<<"lt ";
					break;
				case CSEUM_DT_DOUBLE:
					ss<<"dlt ";
					break;
			}
			ss<<compileBinaryOp(tree,info);
			break;
		}	
		case CSEUM_MAD:{
			switch(rhstree->getChild(0)->gtype.basetype){
				case CSEUM_DT_INT:
					ss<<"imad ";
					break;
				case CSEUM_DT_FLOAT:
					ss<<"mad ";
					break;
				case CSEUM_DT_DOUBLE:
					ss<<"dmad ";
					break;
			}
			ss<<compileBinaryOp(tree,info);
			break;
		}			
			
		case CSEUM_CAST:{
			int fromtype = rhstree->getChild(0)->gtype.basetype;
			int totype = rhstree->gtype.basetype;
			if(fromtype==CSEUM_DT_INT && totype==CSEUM_DT_FLOAT){
				ss<<"itof ";
			}else if(fromtype==CSEUM_DT_FLOAT && totype==CSEUM_DT_INT){
				ss<<"ftoi ";
			}else if(fromtype==CSEUM_DT_FLOAT && totype==CSEUM_DT_DOUBLE){
				ss<<"f2d ";
			}
			ss<<compileTargetTree(tree->getChild(0),info,&toffset)<<", ";
			ss<<compileSourceTree(rhstree->getChild(0),info,toffset);
			break;
		}
		default:
			ss<<"assign:huh "<<rhstree->tag;
			break;
	}
	ss<<"\n";
	return ss.str();
}
static string compileProgramHeader(Tree *prgtree,BackendInfo *info){
	stringstream ss;
	if(info->isComputeShader){
		ss<<"il_cs_2_0\n";
		ss<<"dcl_num_thread_per_group "<<info->numThreadGroup<<"\n";
		ss<<"dcl_lds_size_per_thread "<<info->ldsSize<<"\n";
		ss<<"dcl_lds_sharing_mode _wavefrontRel\n";
	}else{
		ss<<"il_ps_2_0\n";
		ss<<"dcl_input_position_interp(linear_noperspective) vWinCoord0.xy__\n";
	}
	for(int i=0;i<8;i++){
		if(info->intypes[i].dims == -1) break;
		ss<<"dcl_resource_id("<<i<<")_type(2d,unnorm)_fmtx(float)_fmty(float)_fmtz(float)_fmtw(float)\n";
	}
	if(!(info->isComputeShader)){
		for(int i=0;i<8;i++){
			if(info->outtypes[i].dims == -1) break;
			ss<<"dcl_output_generic o"<<i<<'\n';	
		}
	}
	int offset = 0;
	int reg = 0;
	bool newliteral = true;
	for(int i=0;i<info->literals.size();i++){
		if(info->lbasetypes[i]==CSEUM_DT_INT){
			if(newliteral){
				ss<<"dcl_literal l"<<reg<<", ";
				newliteral = false;
			}
			ss<<info->literals[i].idata;
			info->literal_regs[i] = reg;
			info->literal_offsets[i] = offset;
			offset++;
			if(offset!=4){
				ss<<", ";
			}else{
				ss<<"\n";
				newliteral=true;
				offset=0;
				reg++;
			}
		}	
	}
	if(!newliteral){
		for(int i=offset;i<3;i++) ss<<"0,";
		ss<<"0\n";
		reg++;
		offset=0;
		newliteral = true;
	}
	for(int i=0;i<info->literals.size();i++){
		if(info->lbasetypes[i]==CSEUM_DT_FLOAT){
			if(newliteral){
				ss<<"dcl_literal l"<<reg<<", ";
				newliteral = false;
			}
			ss<<info->literals[i].fdata<<"f";
			info->literal_regs[i] = reg;
			info->literal_offsets[i] = offset;
			offset++;
			if(offset!=4){
				ss<<", ";
			}else{
				ss<<"\n";
				newliteral=true;
				offset=0;
				reg++;
			}
		}	
	}
	if(!newliteral){
		for(int i=offset;i<3;i++) ss<<"0.0f, ";
		ss<<"0.0f\n";
		reg++;
		offset=0;
		newliteral=true;
	}
	//TODO: double literals
	for(int i=0;i<info->literals.size();i++){
		if(info->lbasetypes[i]==CSEUM_DT_DOUBLE){
			if(newliteral){
				ss<<"dcl_literal l"<<reg<<", ";
				newliteral = false;
			}
			float *t = (float *)&(info->literals[i].ddata);
			float hi = t[0];
			float lo = t[1];
			ss<<hi<<", "<<lo;
			info->literal_regs[i] = reg;
			info->literal_offsets[i] = offset;
			offset+=2;
			if(offset!=4){
				ss<<", ";
			}else{
				ss<<"\n";
				newliteral=true;
				offset=0;
				reg++;
			}
		}	
	}
	if(!newliteral){
		for(int i=offset;i<3;i++) ss<<"0.0f, ";
		ss<<"0.0f\n";
		reg++;
		offset=0;
		newliteral=true;
	}

	if(!info->isComputeShader) 	ss<<"ftoi r1.xy, vWinCoord0.xy\n";

	return ss.str();
}

static string compileTreeHelper(Tree *tree,BackendInfo *info){
	stringstream ss;
	switch(tree->tag){
		case CSEUM_PROGRAM:
			ss<<compileProgramHeader(tree,info);
			ss<<compileTreeHelper(tree->getChild(0),info);
			ss<<"end\n";
			break;
		case CSEUM_STMTLIST:{
			for(int i=0;i<tree->getChildCount();i++){
				ss<<compileTreeHelper(tree->getChild(i),info);
			}
			break;
		}
		case CSEUM_ASSIGN:{
			return compileAssignTree(tree,info);
		}
		case CSEUM_LOOP:{
			ss<<"whileloop\n";
			for(int i=0;i<tree->getChildCount();i++) ss<<compileTreeHelper(tree->getChild(i),info);
			ss<<"endloop\n";
			break;
		}
		case CSEUM_BREAK_LOGICALZ:{
			ss<<"break_logicalz "<<compileSourceTree(tree->getChild(0),info,0)<<'\n';
			break;
		}
		case CSEUM_FENCE_LDS:{
			ss<<"fence_lds\n";
			break;
		}
		default:
			ss<<"huh\n";
			printTree(tree);
			break;
	}
   	return ss.str();	
}

string compileTree(Tree *tree,bool isComputeShader,int numThreadGroup,int ldsSize,int loopvars[]){
	//find all literals and rename all ids on the fly while forming symbol table
	timer timr;
	BackendInfo info;
	info.maxreg = 64;
	info.isComputeShader = isComputeShader;
	info.numThreadGroup = numThreadGroup;
	info.ldsSize = ldsSize;
	info.computeInfo(tree);
	//printTree(tree);
	//compile to lower level tree .. generating 3-address code type stuff
	vector<Tree *> *vec = compileLltree(tree,&info);
	//cout<<(*(vec->at(0)))<<endl;
	//do register allocation
	bool success = allocateRegisters(vec->at(0),&info);
	string str("error");
	if(!success) cout<<"Register allocation failed"<<endl;
	else str = compileTreeHelper(vec->at(0),&info);
	delete vec->at(0);
	delete vec;
	return str;
}
