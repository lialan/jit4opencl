/***
 *
 * WARNING : THIS FILE IS OLD. TO BE REMOVED 
 * SERVES NO PURPOSE CURRENTLY.
 * */


#include "tree.hpp"
#include <queue>
#include <climits>
using std::queue;

LoopInfo *constructLoopInfo(Tree *tree,LoopInfo *parent=NULL,int depth=0){
	static int base;
	if(parent==NULL) base = 0;

	LoopInfo *linfo = new LoopInfo;
	linfo->loopid = base;
	linfo->tree = tree;
	base++;
	linfo->parent = parent;
	linfo->depth = depth;

	if(tree->tag==CSEUM_PFOR) linfo->isParallel = true;
	else if(tree->tag==CSEUM_FOR) linfo->isParallel = false;
	
	Tree *assign = tree->getChild(0);
	linfo->indexid = assign->getChild(0)->idata;
	cout<<"index of loop "<<linfo->indexid<<endl;
	linfo->lower = assign->getChild(1)->idata;	
	cout<<"lower bound "<<linfo->lower<<endl;
	linfo->upper = tree->getChild(1)->getChild(1)->idata;
	cout<<"upper bound of loop "<<linfo->upper<<endl;

	Tree *body = tree->getChild(3);

	queue<Tree *> bfs;
	bfs.push(body);

	while(!bfs.empty()){
		Tree *child = bfs.front();
		bfs.pop();
		if(child->tag==CSEUM_FOR || child->tag==CSEUM_PFOR){
		   LoopInfo *cinfo = constructLoopInfo(child,linfo,depth+1);
		   linfo->children.push_back(cinfo);
		}
		else{
			for(int i=0;i<child->getChildCount();i++) bfs.push(child->getChild(i));
		}		
	}	
	
	return linfo;
}

struct ArefInfo{
	int *refs;
	bool isWrite;
	bool isRead;
	int pos;
	void * base;
	int group;
	int dims;
	Tree *t;
	int basetype;
	ArefInfo(int d):dims(d),base(NULL),refs(new int[d+1]),isWrite(false),pos(0),group(-1){}
};

void computeArefInfoWorker(LoopInfo *linfo,vector<ArefInfo *> &ainfo,map<int,int> &imap,map<int,ArrayInfo*> table){
	Tree *body = linfo->tree->getChild(3);
	int dims = linfo->nloops;
	queue<Tree *> bfs;
	imap[linfo->indexid] = linfo->loopid;
	bfs.push(body);
	while(!bfs.empty()){
		Tree *child = bfs.front();
		bfs.pop();
		if(child->tag==CSEUM_FOR || child->tag==CSEUM_PFOR){
		}else if(child->tag==CSEUM_AREAD || child->tag==CSEUM_AWRITE){
			ArefInfo *aref = new ArefInfo(dims);
			ainfo.push_back(aref);
			if(child->tag==CSEUM_AWRITE){
			   	aref->isWrite = true;
				aref->isRead = false;
			}else{
			   aref->isRead = true;
			   aref->isWrite = false;
			}
			aref->pos = child->pos;	
			aref->basetype = child->gtype.basetype;
			for(int i=0;i<(dims+1);i++) aref->refs[i] = 0;
			cout<<"Found aref for array "<<child->getChild(0)->idata<<endl;
			ArrayInfo *arr = table[child->getChild(0)->idata];
			if(arr==NULL) cout<<"could not find array "<<child->getChild(0)->idata<<endl;
			aref->base = arr->getBase();
			aref->t = child;

			Tree *subs = child->getChild(1);
			for(int i=0;i<subs->getChildCount();i++){
				Tree *subscript = subs->getChild(i);
				if(subscript->tag==CSEUM_ID){
					int loopid = imap[subscript->idata];
					cout<<"Aref subscript "<<i<<" "<<loopid<<endl;
					aref->refs[loopid] = arr->getStride(i);
				}
			}

		}else{
			for(int i=0;i<child->getChildCount();i++) bfs.push(child->getChild(i));
		}
	}
	for(int i=0;i<linfo->children.size();i++) computeArefInfoWorker(linfo->children[i],ainfo,imap,table);
	//TODO: should erase key to avoid duplicate index
}

int setNloops(LoopInfo *linfo,int nloops){
	linfo->nloops = nloops;
	for(int i=0;i<linfo->children.size();i++) setNloops(linfo->children[i],nloops);
}

void computeArefInfo(vector<ArefInfo*> &info,LoopInfo *linfo,map<int,ArrayInfo*> table){
	int nloops;
	LoopInfo *c = linfo;
	while(c->children.size()>0) c = c->children[c->children.size()-1];
	nloops = c->loopid+1;
	cout<<"Number of loops "<<nloops<<endl;
	setNloops(linfo,nloops);
	//maps loop counter to loopid
	map<int,int> imap;
	computeArefInfoWorker(linfo,info,imap,table);	

	cout<<"Computed aref table"<<endl;
	for(int i=0;i<info.size();i++){
		for(int j=0;j<nloops+1;j++){
			cout<<info[i]->refs[j]<<" ";	
		}
		cout<<endl;
	}


}

LoopInfo *getLoop(LoopInfo *linfo,int id){
	if(linfo->loopid==id) return linfo;
	for(int i=0;i<linfo->children.size();i++){
	   LoopInfo *temp =	getLoop(linfo->children[i],id);
	   if(temp!=NULL) return temp;
	}
	return NULL;
}
int createGroup(vector<ArefInfo *> &arefs,LoopInfo *linfo){
	int nloops = linfo->nloops;
	int n = arefs.size();
	int d = nloops;
	bool *mat = new bool[n*n];
	for(int i=0;i<n*n;i++) mat[i] = false;
	vector<unsigned int> start(n);
	vector<unsigned int> stop(n);
	for(int i=0;i<n;i++){
		start[i] = ((unsigned int)arefs[i]->base);
		start[i] = start[i] + arefs[i]->refs[d];
		stop[i] = start[i];
		for(int j=0;j<d;j++){
			LoopInfo *loop = getLoop(linfo,j);
			int stride = arefs[i]->refs[j];
			if(stride>0){
				stop[i] = stop[i] + stride*(loop->upper-1);
			}else{
				stop[i] = stop[i] + stride*loop->lower;
			}
		}
	}
	
	for(int i=0;i<n;i++){
		for(int j=i+1;j<n;j++){
			bool interf = false;
			if(start[i]>start[j] && start[i]<stop[j]) interf = true;
			if(start[j]>start[i] && start[j]<stop[i]) interf = true;
			if(interf){
				cout<<"interfering "<<i<<" "<<j<<endl;
				cout<<"starts "<<start[i]<<" "<<start[j]<<endl;
				cout<<"stop "<<stop[i]<<" "<<stop[j]<<endl;
				mat[i*n+j] = true;
				mat[j*n+i] = true;
			}

		}

	}
	int ngroups = 0;	
	for(int i=0;i<n;i++) arefs[i]->group = -1;
	while(true){
		queue<int> nodeq;
		for(int i=0;i<n;i++){
			if(arefs[i]->group==-1){
				nodeq.push(i);
				break;
			}
		}
		if(nodeq.empty()) break;
		cout<<"Found vertex without group "<<nodeq.front()<<endl;
		while(!nodeq.empty()){
			int vertex = nodeq.front();
		   	nodeq.pop();
			arefs[vertex]->group = ngroups;
			for(int i=0;i<n;i++){
				if(mat[vertex*n+i] && arefs[i]->group==-1){
				   	nodeq.push(i);
					cout<<"found interfering vertex "<<i<<endl;
				}
			}	
		}
		ngroups++;
	}
	delete[] mat;	
	return ngroups;
}

bool checkGroups(vector<ArefInfo *> &ainfo,LoopInfo *linfo){
	int n = ainfo.size();
	int d = linfo->nloops;
	for(int i=0;i<n;i++){
		int group = ainfo[i]->group;
		for(int j=i+1;j<n;j++){
		 	if(ainfo[j]->group==group){
				for(int k=0;k<d+1;k++){
					if(ainfo[i]->refs[k]!=ainfo[j]->refs[k]) return false;
				}	
			}
		}
	}
	return true;
}

struct ArefGroup{
	int *refs;
	bool isWrite;
	bool isRead;
	void *base;
	int dims;
	int basetype;
	vector<int> offsets;
	vector<ArefInfo*> arefs;
	ArefGroup(int d):dims(d),base(NULL),refs(new int[d]),isWrite(false),isRead(false){}
};


void computeGroups(vector<ArefGroup *> &agroups,vector<ArefInfo *> &ainfo,LoopInfo *linfo){	
	//TODO:fix this hack
	for(int i=0;i<ainfo.size();i++){
		int d = ainfo[i]->dims;
		ArefGroup *g = new ArefGroup(d);
		for(int j=0;j<d;j++){
			g->refs[j] = ainfo[i]->refs[j];
		}
		g->offsets.push_back(0);
		g->base = ainfo[i]->base;
		g->isRead = ainfo[i]->isRead;
		g->isWrite = ainfo[i]->isWrite;
		g->arefs.push_back(ainfo[i]);
		g->basetype = ainfo[i]->basetype;
		agroups.push_back(g);
	}	
}

unsigned int getGroupSize(ArefGroup *group,int lower[],int upper[]){
	unsigned int size = 1;
	for(int i=0;i<group->dims;i++){
		if(group->refs[i]!=0){
			size *= (unsigned int)((upper[i]-lower[i]))*group->offsets.size();	
		}
	}
	GPUType gtype;
	gtype.basetype = group->basetype;
	gtype.components = 1;
	gtype.dims = 0;
	return (4*size*gtype.typeSize());
	
}

struct SchedPhase{
	int *lower;
	int *upper;
	int n;
	SchedPhase(int d):n(d){lower = new int[n]; upper = new int[n];} 
};

typedef vector<SchedPhase*> Schedule;

void deriveSchedule(LoopInfo *linfo,vector<ArefGroup*> &groups,Schedule &sched,int *error){
	int nloops = linfo->nloops;
	*error = 0;
	bool *isParallel = new bool[nloops];
	int *olower = new int[nloops];
	int *oupper = new int[nloops];
	for(int i=0;i<nloops;i++){
		LoopInfo *loop = getLoop(linfo,i);
		olower[i] = loop->lower;
		oupper[i] = loop->upper;
		isParallel[i] = loop->isParallel;
	}
	unsigned int size=0;
	for(int i=0;i<groups.size();i++){
		size += getGroupSize(groups[i],olower,oupper);
	}
	const unsigned int maxsize = 24*1024*1024;
	if(size<maxsize){
		cout<<"Total size "<<(size/(1024.0*1024.0))<<" MB"<<endl;
		cout<<"Fits on GPU!!"<<endl;
		cout<<nloops<<endl;
		SchedPhase *phase = new SchedPhase(nloops);
		for(int i=0;i<nloops;i++){
			phase->lower[i] = olower[i];
			phase->upper[i] = oupper[i];
		}
		sched.push_back(phase);
	}else{
		int divider = 2;
		int *nlower = new int[nloops];
		int *nupper = new int[nloops];
		bool dividerIsMax = false;
		while(true){
			for(int i=0;i<nloops;i++){
				if(isParallel[i]){
					nlower[i] = 0;
					nupper[i] = (oupper[i]-olower[i])/divider;
					if(nupper[i]==0) dividerIsMax = true;
				}else{
					nupper[i] = oupper[i];
					nlower[i] = olower[i];
				}
			}
			if(dividerIsMax) break;
			unsigned int size = 0;
			for(int i=0;i<groups.size();i++) size += getGroupSize(groups[i],nlower,nupper);
			if(size<maxsize) break;
			divider = divider*2;
		}
		if(dividerIsMax){
			cout<<"no feasible solution found"<<endl;
			*error = 1;
		}else{
			cout<<"Feasible divider "<<divider<<endl;
			vector<int> pindex;
			for(int i=0;i<nloops;i++) if(isParallel[i]) pindex.push_back(i);
			vector<int> blocks;
			vector<int> diff;
			int nblocks=1;
			for(int i=0;i<pindex.size();i++){
			   int idiv = divider;
			   int idiff = (oupper[pindex[i]] - olower[pindex[i]])/divider;
			   if(idiff%divider!=0) idiv++;
			   nblocks *= idiv;
			   blocks.push_back(idiv);
			   diff.push_back(idiff);
			}
			cout<<"Number of blocks required "<<nblocks<<endl;
			for(int i=0;i<nblocks;i++){
				SchedPhase *phase = new SchedPhase(nloops);
			   	for(int j=0;j<nloops;j++){
			   		phase->lower[j] = olower[j];
					phase->upper[j] = oupper[j];
			   }
			   sched.push_back(phase);
			}
			for(int i=0;i<nblocks;i++){
				SchedPhase *phase = sched[i];
				int id = i;
				int v = 1;
				for(int j=0;j<pindex.size();j++){
					int bid = id%blocks[j];
					int index = pindex[j];
					phase->lower[index] = olower[index] + diff[j]*bid;
					phase->upper[index] = phase->lower[index] + diff[j];
					if(phase->upper[index]>oupper[index]) phase->upper[index] = oupper[index];
					id = (id - bid)/blocks[j];		
				}
			}
		}
		delete[] nlower;
		delete[] nupper;
	}
	delete[] olower;
	delete[] oupper;
	delete[] isParallel;
}

void execSchedule(LoopInfo *linfo,vector<ArefGroup*> &groups,Schedule &sched){
	for(int i=0;i<sched.size();i++){
		for(int j=0;j<linfo->nloops;j++){
			cout<<sched[i]->lower[j]<<" "<<sched[i]->upper[j]<<" | ";
		}
		cout<<endl;
	}
	
	for(int i=0;i<sched.size();i++){
		int textures[33];
		int components[33];
		int rotex = 0;
		int rbetex = 16;
		for(int j=0;j<33;j++) {
			textures[j] = -1;
			components[j] = 1;
		}
		SchedPhase *phase = sched[i];
		for(int j=0;j<groups.size();j++){
			ArefGroup *group = groups[j];
			int minindex = -1;
			int minval = INT_MAX;
			for(int k=0;k<linfo->nloops;k++){
				if(group->refs[k]>0 && group->refs[k]<minval){
					minval = group->refs[k];
					minindex = k;
				}
			}
			int miniters = (phase->upper[minindex] - phase->lower[minindex]);
			LoopInfo *minloop = getLoop(linfo,minindex);
			if(group->isRead && !group->isWrite && rotex<16){
				textures[rotex] = j;
			   	cout<<"Found read-only group. Placing in ro texture"<<endl;
				if(miniters%4==0 && minloop->children.size()==0){
				   cout<<"Converting to 4-wide"<<endl;
				   components[rotex] = 4;
				   minloop->tree->idata = 4;
				}
				rotex++;
			}
			if(group->isWrite && !group->isRead){
				bool isROP=true;
				for(int k=0;k<linfo->nloops;k++){
					LoopInfo *loop = getLoop(linfo,k);
					if(loop->isParallel && group->refs[k]==0){
						isROP = false;
						break;
					}
					if(!(loop->isParallel) && group->refs[k]!=0){
						isROP = false;
						break;
					}
				}
				if(isROP && rbetex<32){
					textures[rbetex] = j;
				   	cout<<"Found write-only group with proper indexing. Placing in render backend"<<endl;
					if(miniters%4==0){
					   	cout<<"Converting to 4-wide"<<endl;
				   		components[rbetex] = 4;
					}
					rbetex++;

				}		
				else {
					cout<<"Cannot place in RBE. Checking for global buffer conditions"<<endl;
				}
			}
		}
	}	
}
bool execOnGpu(Tree &tree,map<int,ArrayInfo*> table){
	LoopInfo *linfo = constructLoopInfo(&tree);
	vector<ArefInfo *> ainfo;
    computeArefInfo(ainfo,linfo,table);
	int ngroups = createGroup(ainfo,linfo);
	bool check = checkGroups(ainfo,linfo);
	if(!check) return false;
	vector<ArefGroup *> agroups;
	computeGroups(agroups,ainfo,linfo);
	cout<<"Number of LMAD groups "<<ngroups<<endl;
	Schedule sched;
	int error;
	deriveSchedule(linfo,agroups,sched,&error);
	if(error==1) return false;
	execSchedule(linfo,agroups,sched);
	delete linfo;
	return true;
}

