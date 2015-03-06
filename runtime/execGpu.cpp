#include <iostream>
#include <vector>
#include <utility>
#include <algorithm>
#include <queue>
#include <string>
#include <sstream>
extern "C"{
#include <glpk.h>
#include <unpython.h>
#include <cal.h>
#include <calcl.h>
};
#include "jit4gpu.hpp"
#include "tree.hpp"
#define UDEBUG 0
using namespace std;

struct LmadGroup{
	vector<long> strides;
	vector<long> bases;
	vector<int> ids;
	bool isERL;
	vector<long> toffsets;
	long tbase;
	vector<long> residues;
	vector<long> gaddress;
	vector<int> sortorder;
	int rw;
	int resno;
	LmadGroup(int nlmads,int ndims):strides(nlmads*ndims),bases(nlmads),ids(nlmads),isERL(false),toffsets(ndims*nlmads),tbase(0),gaddress((ndims+1)*nlmads),sortorder(ndims),residues(nlmads){}
	int getNdims(){return strides.size()/bases.size();}
	int getNlmads(){return bases.size();}
	void setStride(int lmad,int dim,long value){strides[lmad*getNdims()+dim]=value;}
	int getStride(int lmad,int dim){return strides[lmad*getNdims()+dim];}
	void initWithBounds(long ubounds[]); //initialize parameters toffets and tbase using array access analysis
	bool canTile(){return isERL;}
	long computeSize(long ubounds[]); // compute the size given particular loop bounds
	int getQ(); //Q = the number of distinct LMADs 
	CALresource res;
};

int LmadGroup::getQ(){
	if(!isERL) return 0;
	const int nlmads = getNlmads();
	vector<long> temp(nlmads);
	for(int i=0;i<nlmads;i++) temp[i] = bases[i];
	sort(temp.begin(),temp.end());
	int q = nlmads;
	long current = temp[0];
	for(int i=1;i<nlmads;i++){
		if(temp[i]==current) q--;
		else current = temp[i];
	}
	return q;
}

bool isRcsLmad(long *lmad,long *ubounds,int ndims,bool weak=false){
	vector< pair<int,int> > sorted(ndims);
	for(int i=0;i<ndims;i++){
		sorted[i].first = lmad[i];
		sorted[i].second = i;
	}
	//cout<<"Checking RCSLMAD: ";
	//for(int i=0;i<ndims;i++) cout<<lmad[i]<<" "<<ubounds[i]<<endl;
	sort(sorted.rbegin(),sorted.rend());
	//for(int i=0;i<ndims;i++) cout<<sorted[i].first<<" "<<sorted[i].second<<endl;
	bool ans = true;
	for(int i=0;i<ndims-1;i++){
		int sum = 0;
		if(sorted[i].first==0) break;
		for(int j=i+1;j<ndims;j++){
			sum += (ubounds[sorted[j].second]-1)*sorted[j].first;	
		}
		if(!weak) sum += sorted[ndims-1].first-1;
		ans = ans && (sorted[i].first > sum);
	}
	return ans;
}

bool isOverlapping(long l1,long u1,long l2,long u2){
	if(l1>u2) return false;
	if(l2>u1) return false;
	return true;
}

int computeGroups(vector<bool> &overlaps,vector<int> &groups){
	int n = groups.size();
	int current = 0;
	
	//THE following must be wrong
	//but what does it mean?
	for(int i=0;i<n;i++){
		groups[i] = -1;
	}
	for(int i=0;i<n;i++){
		if(groups[i]!=-1) continue;
		queue<int> q;
		q.push(i);
		while(!q.empty()){
			int first = q.front();
			q.pop();
			if(groups[first]==-1){
				groups[first] = current;
				for(int j=0;j<n;j++){
					if(overlaps[first*n+j] && groups[j]==-1) q.push(j);
				}
			}
		}
		current++;
	}
	return current;
}

bool checkGroup(long* strides,long *ubounds,int ndims,int gsize){
	if(gsize==1) return isRcsLmad(strides,ubounds,ndims,true);
	for(int i=0;i<gsize;i++){
		if(!isRcsLmad(strides,ubounds,ndims,false)) return false;		
	}
	for(int i=1;i<gsize;i++){
		for(int j=0;j<ndims;j++){
			if(strides[i*ndims+j]!=strides[j]) return false;
		}
	}
	return true;
}

long LmadGroup::computeSize(long ubounds[]){
	if(isERL){
		const int q = getQ();
		long size = q;
		const int ndims = getNdims();
		const int nlmads = getNlmads();
		vector<long> tmax(ndims);
		for(int i=0;i<ndims;i++){
			if(strides[i]==0) continue;
			tmax[i] = 0;
			for(int j=0;j<nlmads;j++){
				if(tmax[i]>toffsets[j*ndims+i]) tmax[i] = toffsets[j*ndims+i];
			}
			size = size*(ubounds[i]+tmax[i]);
		}
		for(int i=0;i<nlmads;i++){
			long gbase=residues[i];
			for(int j=0;j<ndims;j++){
				int index = sortorder[j];
				if(getStride(i,index)==0){
					gaddress[i*(ndims+1)+index] = 0;
				}else{
					long stride = q;
					for(int k=j+1;k<ndims;k++){
						int temp = sortorder[k];
						if(getStride(i,temp)==0) break;
						stride = stride*(ubounds[temp]+tmax[temp]);
					}	
					gaddress[i*(ndims+1)+index] = stride;
					gbase += stride*toffsets[i*ndims+index];
				}
			}
			gaddress[i*(ndims+1)+ndims] = gbase;
			//for(int j=0;j<ndims+1;j++) cout<<gaddress[i*(ndims+1)+j]<<" ";
			//cout<<endl;
		}
		return size;
	}else{
		//TODO
		return 0;	
	}
}
void LmadGroup::initWithBounds(long ubounds[]){
	long *sarray = new long[strides.size()];
	for(int i=0;i<strides.size();i++) sarray[i] = strides[i];
	bool canBeErl = checkGroup(sarray,ubounds,getNdims(),getNlmads());
	delete[] sarray;
	const int ndims = getNdims();
	const int nlmads = getNlmads();
	vector< pair<int,int> > sindex(ndims);
	for(int i=0;i<ndims;i++){
		sindex[i].first = strides[i];
		sindex[i].second = i;	
	}
	sort(sindex.rbegin(),sindex.rend());
	for(int i=0;i<ndims;i++) sortorder[i] = sindex[i].second;
	if(canBeErl){
		long maxbase = bases[0];
		long minbase = bases[0];
		for(int i=0;i<nlmads;i++){
			if(bases[i]>maxbase) maxbase = bases[i];
			if(bases[i]<minbase) minbase = bases[i];
		}
		long minstride = 0;
		for(int i=0;i<ndims;i++) if(strides[sortorder[i]]!=0) minstride++;
		minstride--;
		if((maxbase-minbase)<strides[sortorder[minstride]]){
			for(int i=0;i<nlmads;i++){
				for(int j=0;j<ndims;j++){
					toffsets[i*ndims+j] = 0;
				}
			}
			tbase = minbase;
			for(int i=0;i<nlmads;i++) residues[i] = bases[i]-tbase;
		   	isERL=true;
		}
		//TODO: must do LP
		else{
		   isERL = false;
		}
	}else{
		isERL = false;
	}
}

bool scaleLmads(long *lmads,int nlmads,int ndims,int type){
	int scale = 1;
	if(type==UNPY_FLOAT || type==UNPY_INT) scale = 4;
	if(type==UNPY_DOUBLE) scale = 8;
	for(int i=0;i<nlmads;i++){
		for(int j=0;j<ndims+1;j++){
			if(!(lmads[i*(ndims+1)+j]%scale==0)){
				if(UDEBUG) cout<<"could not scale lmad "<<i<<endl;
			   	return false;
			}
			else lmads[i*(ndims+1)+j] = lmads[i*(ndims+1)+j]/scale;
		}
	}
	return true;

}

void formLmadGroups(vector<LmadGroup *>&lgroups,long *lmads,bool *writes,vector<int> &groups,int nlmads,int ndims,int ngroups){
	if(ngroups==nlmads){
		if(UDEBUG) cout<<"Groups are same as LMADs"<<endl;
		for(int i=0;i<nlmads;i++){
			LmadGroup *group = new LmadGroup(1,ndims);
			long *lmad = &lmads[i*(ndims+1)];
			for(int j=0;j<ndims;j++){
				group->setStride(0,j,lmad[j]);
			}
			group->ids[0] = i;
		    group->bases[0] = lmad[ndims];	
			group->rw = writes[i];
			lgroups.push_back(group);
		}
	}
	//TODO
	else if(ngroups<nlmads){
		if(UDEBUG) cout<<"Less groups than LMADs"<<endl;
		for(int i=0;i<ngroups;i++){
			int gsize = 0;
			for(int j=0;j<nlmads;j++) if(groups[j]==i) gsize++;
			LmadGroup *group = new LmadGroup(gsize,ndims);
			int index = 0;
			int gwrites = 0;
			int greads = 0;
			for(int j=0;j<nlmads;j++){
				if(groups[j]!=i) continue;
				if(writes[j]) gwrites++;
				if(!writes[j]) greads++;
				group->ids[index] = j;
				group->bases[index] = lmads[j*(ndims+1)+ndims];
				for(int k=0;k<ndims;k++){
					group->setStride(index,k,lmads[j*(ndims+1)+k]);
				}
				index++;
			}
			if(greads==0) group->rw = 1;
			if(gwrites==0) group->rw = 0;
			if(greads>0 && gwrites>0) group->rw=2;
			lgroups.push_back(group);
		}
	}
	else{
		//this should never happen. 
		cout<<"More groups than LMADs"<<endl;
	}

}

//How is loopvars be used?
void patch(Tree *tree,int loopvars[],long ubounds[],vector<LmadGroup *>& lgroups,vector<int>& groups,int nlmads,int ndims,int type){
	queue<Tree *> queue;
	queue.push(tree);
	
	//count in, and out.
	//but, it is useless?
	int inres=0,outres=0;
	for(int i=0;i<lgroups.size();i++){
		LmadGroup *group = lgroups[i];
		if(group->rw == 0){
			group->resno = inres;
			inres++;
		}
		if(group->rw==1){
			group->resno = outres;
			outres++;
		}
	}

	//patch AREAD and AWRITE.
	while(!queue.empty()){
		Tree *front = queue.front();
		queue.pop();
		if(front->tag==CSEUM_UNPY_AREAD || front->tag==CSEUM_UNPY_AWRITE){///array read or write.
			LmadGroup *group = lgroups[groups[front->idata]];
			int index = 0;
			while(group->ids[index]!=front->idata) index++;
			
			int nonzerodims = 0;
			for(int i=0;i<ndims;i++){
				if(group->gaddress[index*(ndims+1)+group->sortorder[i]]==0){
					nonzerodims=i;
					break;
				}
			}
			if(nonzerodims==0) nonzerodims = ndims;
			//store the coefficients of the array address expression into the field subs
			front->subs = new long[nonzerodims+2];
			front->loopvars = new int[nonzerodims];
			front->ndims = nonzerodims;	
			for(int i=0;i<nonzerodims;i++){
				front->subs[i] = group->gaddress[index*(ndims+1)+group->sortorder[i]];
				front->loopvars[i] = loopvars[group->sortorder[i]];	
			}
			front->subs[nonzerodims] = group->gaddress[index*(ndims+1)+ndims];
			front->subs[nonzerodims+1] = 0;
			
			//create tree
			Tree *child;
			if(group->rw==0) child = new Tree(CSEUM_INRES);
			if(group->rw==1) child = new Tree(CSEUM_OUTRES);
			//inherit group info?
			child->idata = group->resno;
			if(type==UNPY_FLOAT){
				child->gtype.basetype = CSEUM_DT_FLOAT;
			}else if(type==UNPY_DOUBLE){
				child->gtype.basetype = CSEUM_DT_DOUBLE;
			}
			child->gtype.dims = 2;
			child->gtype.components = 1;
			front->children.push_back(child);
		}else if(front->tag==CSEUM_FOR){
			int lvar = front->getChild(0)->getChild(0)->idata;
			int index = 0;
			for(int i=0;i<ndims;i++) if(loopvars[i]==lvar) index = i;
			front->getChild(1)->getChild(1)->idata = (int)ubounds[index];
		}
		for(int i=0;i<front->children.size();i++) queue.push(front->children[i]);
	}

}

//transfer LMAD to address *ptr
void transferLmadPtr(void *ptr,LmadGroup *group,long ubounds[],int type,bool fromLmad){
	int nonzerodims = 0;
	const int ndims = group->getNdims();
	const int nlmads = group->getNlmads();
	for(int i=0;i<ndims;i++){
		if(group->getStride(0,i)>0) nonzerodims++;
	}
	vector<long> tmax(nonzerodims);//what is tmax?
	//it seems like a redundant pad?
	
	for(int i=0;i<nonzerodims;i++){
		tmax[i] = 0;
		for(int j=0;j<nlmads;j++){
			int index = group->sortorder[i];
			long val = group->toffsets[j*ndims+index];
			if(val>tmax[i]) tmax[i] = val;
		}
	}
	
	//can only support 2 dimensional matrix?
	//cout<<"nonzerodims "<<nonzerodims<<endl;
	
	//nonzerodims: dimensions.
	if(nonzerodims==1){
		if(type==UNPY_FLOAT){
			float *fdata = (float *)ptr;
			float *lbase = (float *)(4*group->tbase);
			const int d1 = group->sortorder[0];
			const long u1 = ubounds[d1] + tmax[0];
			for(int j=0;j<nlmads;j++){
				for(int i=0;i<u1;i++){
					long gaddress = i*group->gaddress[j*(ndims+1)+d1];
					gaddress += group->gaddress[j*(ndims+1)+ndims];
					long caddress = i*group->getStride(j,d1);
					caddress += group->residues[j];
					if(fromLmad) fdata[gaddress] = lbase[caddress];
					else lbase[caddress] = fdata[gaddress];
				}
			}
		}
	}
	if(nonzerodims==2){
		if(type==UNPY_FLOAT){
			//cout<<"case float with 2 dims"<<endl;
			float *fdata = (float *)ptr;
			float *lbase = (float *)(group->tbase*4);
			const int d1 = group->sortorder[0];
			const int d2 = group->sortorder[1];
			const long u1 = ubounds[d1] + tmax[0];
			const long u2 = ubounds[d2] + tmax[1];
			for(int k=0;k<nlmads;k++){
				for(int i=0;i<u1;i++){
					for(int j=0;j<u2;j++){
						long gaddress = i*group->gaddress[k*(ndims+1)+d1];
						gaddress += j*group->gaddress[k*(ndims+1)+d2];
						gaddress += group->gaddress[k*(ndims+1)+ndims];
						long caddress = i*group->getStride(k,d1);
						caddress += j*group->getStride(k,d2);
						caddress += group->residues[k];
						if(fromLmad) fdata[gaddress] = lbase[caddress];
						else lbase[caddress] = fdata[gaddress];
					}
				}
			}
		}
		if(type==UNPY_DOUBLE){
			//cout<<"case double with 2 dims"<<endl;
			double *fdata = (double *)ptr;
			double *lbase = (double *)(group->tbase*8);
			const int d1 = group->sortorder[0];
			const int d2 = group->sortorder[1];
			const long u1 = ubounds[d1] + tmax[0];
			const long u2 = ubounds[d2] + tmax[1];
			for(int k=0;k<nlmads;k++){
				for(int i=0;i<u1;i++){
					for(int j=0;j<u2;j++){
						long gaddress = i*group->gaddress[k*(ndims+1)+d1];
						gaddress += j*group->gaddress[k*(ndims+1)+d2];
						gaddress += group->gaddress[k*(ndims+1)+ndims];
						long caddress = i*group->getStride(k,d1);
						caddress += j*group->getStride(k,d2);
						caddress += group->residues[k];
						if(fromLmad) fdata[gaddress] = lbase[caddress];
						else lbase[caddress] = fdata[gaddress];
					}
				}
			}
		}

	}
	
}


bool transferLmadsToGpu(vector<LmadGroup *>& lgroups,long ubounds[],CALdomain rect,int type){
	CALdevice device = unpyGetDevice();
	CALformat fmt;
	if(type==UNPY_FLOAT) fmt = CAL_FORMAT_FLOAT_1;
    if(type==UNPY_DOUBLE) fmt = CAL_FORMAT_DOUBLE_1;
    if(type==UNPY_INT) fmt = CAL_FORMAT_INT_1;
	for(int i=0;i<lgroups.size();i++){
		int height,width;
		if(lgroups[i]->rw==0){
			int size = (int)(lgroups[i]->computeSize(ubounds));
			height = size/8192+1;
			width = 8192;
		}else if(lgroups[i]->rw==1){
			height = rect.height;
			width = rect.width;
		}
		calResAllocLocal2D(&(lgroups[i]->res),device,width,height,fmt,0);
		CALresource remote;
		if(calResAllocRemote2D(&remote,&device,1,width,height,fmt,CAL_RESALLOC_CACHEABLE)!=CAL_RESULT_OK){
			calResAllocRemote2D(&remote,&device,1,width,height,fmt,0);
		}else{
			//cout<<"cacheable resource"<<endl;
		}
		
		//data is the memory we need to transfer LMADs onto.
		void *data;
		CALuint pitch;
		calResMap(&data,&pitch,remote,0);
		//cout<<"pitch is "<<pitch<<endl;
		transferLmadPtr(data,lgroups[i],ubounds,type,true);
		calResUnmap(remote);
		//do transfer
		CALcontext ctx;
		CALevent e;
		CALmem srcmem,dstmem;
		calCtxCreate(&ctx,device);
		calCtxGetMem(&srcmem,ctx,remote);
		calCtxGetMem(&dstmem,ctx,lgroups[i]->res);
		calMemCopy(&e,ctx,srcmem,dstmem,0);
		while(calCtxIsEventDone(ctx,e)==CAL_RESULT_PENDING);
		calCtxReleaseMem(ctx,srcmem);
		calCtxReleaseMem(ctx,dstmem);
		calCtxDestroy(ctx);
		calResFree(remote);
	}
}

bool transferLmadsFromGpu(vector<LmadGroup *>& lgroups,long ubounds[],CALdomain rect,int type){
	CALdevice device = unpyGetDevice();
	CALformat fmt;
	if(type==UNPY_FLOAT) fmt = CAL_FORMAT_FLOAT_1;
    if(type==UNPY_DOUBLE) fmt = CAL_FORMAT_DOUBLE_1;
    if(type==UNPY_INT) fmt = CAL_FORMAT_INT_1;
	for(int i=0;i<lgroups.size();i++){
		int height,width;
		if(lgroups[i]->rw==0){
			int size = (int)(lgroups[i]->computeSize(ubounds));
			height = size/8192+1;
			width = 8192;
		}else if(lgroups[i]->rw==1){
			height = rect.height;
			width = rect.width;
		}
		if(lgroups[i]->rw==1){
			CALresource remote;
			if(calResAllocRemote2D(&remote,&device,1,width,height,fmt,CAL_RESALLOC_CACHEABLE)!=CAL_RESULT_OK){
				calResAllocRemote2D(&remote,&device,1,width,height,fmt,0);
			}else{
				//cout<<"cacheable resource"<<endl;
			}	
			CALcontext ctx;
			CALevent e;
			CALmem srcmem,dstmem;
			calCtxCreate(&ctx,device);
			calCtxGetMem(&srcmem,ctx,lgroups[i]->res);
			calCtxGetMem(&dstmem,ctx,remote);
			calMemCopy(&e,ctx,srcmem,dstmem,0);
			while(calCtxIsEventDone(ctx,e)==CAL_RESULT_PENDING);
			calCtxReleaseMem(ctx,srcmem);
			calCtxReleaseMem(ctx,dstmem);
			calCtxDestroy(ctx);

			void *data;
			CALuint pitch;
			calResMap(&data,&pitch,remote,0);
			//cout<<"pitch is "<<pitch<<endl;
			transferLmadPtr(data,lgroups[i],ubounds,type,false);
			calResUnmap(remote);
			calResFree(remote);
		}
	}
}
void callog(const char *str){
	cout<<str<<endl;
}


#include<CL/cl.h>

typedef struct data_{
long size;
bool write;// this will determine if we can put it onto read-only memory.
long *data;
long **next;
} *data;

typedef struct X_Y_X_{
int x;
int y;
int z;
} Dimension;


typedef enum executionType_{
NVIDIA_GPU,
AMD_CPU
} ExecutionType;

typedef struct Conf_{
int dim;
Dimension dimsize[3]; //what is the grid dimension.
int sharedMemorySize;	//share memory size in KB
int globalMemorySize;	//global memory size in MB
Dimension groupSize;

//to be added.
ExecutionType execType; //currently we only support NVIDIA GPU.

} Configuration;

void deleteMemobjs(cl_mem memobjs[], int objNum){
	for (int i = 0; i < objNum; i++)
		clReleaseMemObject(memobjs[i];
}


//at present we asume we only use 1 device.
void getConfigurations(Configuration conf, cl_device_id){
	//get, local size
	//
}


//it is the kernel without using LMADs
bool OCLExec(char *kernel_string, data input, int inputNum, data output, int outputNum,
				double primitiveParms[], int primParmsNum, Configuration conf){
		
	//initiate OpenCL Context
	char chBuffer[1024];
	cl_uint ciDeviceCount;
	cl_device_id *devices;
	
	clGetPlatformInfo((cl_platform_id)CL_PLATFORM_NVIDIA, CL_PLATFORM_VERSION, sizeof(chBuffer), chBuffer, NULL);

	clGetDeviceIDs((cl_platform_id)CL_PLATFORM_NVIDIA, CL_DEVICE_TYPE_ALL, 0, NULL, &ciDeviceCount);

	if (ciDeviceCount == 0){
		cout<<"Don't have any OpenCL device."<<"\n";
		return false;
	}
	
	//initiate context
	cl_context context = clCreateContextFromType(NULL, CL_DEVICE_GPU, NULL, NULL, NULL);
	if (context = (cl_context)0){
		cout<<("Cannot create context.")<<"\n";
		return false;
	}
	
	//create command queue
	cl_command_queue cmd_queue = clCreateCommandQueue(context, devices[0], 0 , NULL);
	if (cmd_queue == (cl_command_queue)0){
		clReleaseContext(context);
		cout<<("Cannot create command queue.")<<"\n";
		free(devices);
		return false;
	}

	free(devices);	
	
	char* program_source = kernel_string;
	//initiate program, 
	cl_program program = clCreateProgramWithSource(context, 1, (const char**)&program_source, NULL, NULL);
	if (program == (cl_program)0){
		clReleaseCommandQueue(cmd_queue);
		clReleaseContext(context);
		cout<<("Cannot create program")<<"\n";
		return false;
	}

	//build the program
	if (clBuildProgram(program, 0. NULL, NULL, NULL, NULL) != CL_SUCCESS){
		clReleaseProgram(program);
		clReleaseCommandQueue(cmd_queue);
		clReleaseContext(context);
		cout<<("Cannot create program.")<<"\n";
		return false;
	}
	
	//create a kernel, the kernel name is "OCLExec"
	cl_kernel kernel = clCreateKernel(program, "OCLExec", NULL);
	if (kernel == (cl_kernel)0){
		clReleaseProgram(program);
		clReleaseCommandQueue(cmd_queue);
		clReleaseContext(context);
		cout<<("Cannot create kernel")<<"\n";
		return false;
	}
	


	//set work item dimens.

	//locate GPU memory, and copy data onto GPU
	//ipnut memory:
	cl_memory memobjs[inputNum];
	cl_uint err;
	for (int i = 0; i < inputNum; i ++){
		cl_mem_flags flags;
		int inner_err;
		if (conf.write){
			flags = CL_MEM_READ_WRITE;
		}else{
			flags = CL_MEM_READ_ONLY;
		}
		flags |= CL_MEM_COPY_HOST_PTR;
		memobjs[i] = clCreateBuffer(context, flags, input[i].size, input[i].data, inner_err); //TODO: add tiling here,
		//we ignore tiling to fit the global memory at present.
		dataptr = dataptr.next;
		err |= inner_err;		
	}
	
	//output memory, output memory are those memory that we don't read from them, but write to them.
	cl_memory outmemobjs[outputNum];
	err = 0;
	for (int i = 0; i < outputNum; i++){
		int inner_err;
		outmemobjs[i] = clCreateBuffer(context, CL_MEM_READ_WRITE, output[i].size, NULL, inner_err);
		dataptr = dataptr.next;
		err |= inner_err;
	}
	//check if successfully created memory objects.
	if (err != CL_SUCCESS){
		deleteMemobjs(memobjs, inputNum);
		deleteMemobjs(outmemobjs, outputNum);			
		cout<<"Cannot Allocate Memory Objects."<<"\n";
		return false;
	}
	
	/*	set the args. Currently the strategy is as follows:
		1. Issue as many threads as needed.
		2. parameter sequence: input memory objs, out mem objs, other elementary variables, shared memory allocaion.
	*/
	cl_uint idx = 0; //kernel argument index;
	err = 0;
	for (idx = 0; idx < inputNum; i++){
		err |= clSetkernelArg(kernel, idx, sizeof(cl_mem), (void*) memobjs[idx];
	}
	
	for (int i = 0; i < outputNum; i++){
		err |= clSetKernelArg(kernel, idx + i, sizeof(cl_mem), (void*) outmemobjs[i];
	}
	idx += outputNum;
	
	//set primitive kernel parameters:
	for (int i = 0; i < primParmNum; i++){
		err |= clSetKernelArg(kernel, idx + i, sizeof(double), &((double)primitiveParms)); 
	}
	
	if (err != CL_SUCCESS){
		cout<<"cannot Set kernel arguments.\n";
		deleteMemobjs(memobjs, inputNum);
		deleteMemobjs(outmemobjs, outputNum);
		clReleaseKernel(kernel);
		clReleaseProgram(program);
		clReleaseCommandQueue(cmd_queue);
		clReleaseContext(context);
		cout<<"Cannot set Kernel arguments.\n";
		return false;
	}
		
	//set work-item dimensions;
	//TODO:
	size_t global_work_size[];
	size_t local_work_size;
	
	
	//start kernel.
	err = clEnqueueNDRangeKernel(cmd_queue, kernel, 1, NULL, global_work_size, local_work_size, 0, NULL, NULL);
	if (err != CL_SUCCESS){
		deleteMemobjs(memobjs, inputNum);
		deleteMemobjs(outmemobjs, outputNum);
		clReleaseKernel(kernel);
		clReleaseProgram(program);
		clReleaseCommandQueue(cmd_queue);
		clReleaseContext(context);
		cout<<"Cannot execute kernel.\n";
		return false;
	}
	
	//read buffer:
	err = 0;
	for (int i = 0; i < outputNum; i++){
		err |= clEnqueueReadBuffer(cmd_queue, outmemobjs[i], CL_TRUE, 0, output[i].size, output[i].data, 0, NULL, NULL);
	}
	if (err != CL_SUCCESS){
		deleteMemobjs(memobjs, inputNum);
		deleteMemobjs(outmemobjs, outputNum);
		clReleaseKernel(kernel);
		clReleaseProgram(program);
		clReleaseCommandQueue(cmd_queue);
		clReleaseContext(context);
		cout<<"Cannot copy results from device.\n";
		return false;
	}
	
	//now finishing up the compiling.
	deleteMemobjs(memobjs, inputNum);
	deleteMemobjs(outmemobjs, outputNum);
	clReleaseKernel(kernel);
	clReleaseProgram(program);
	clReleaseCommandQueue(cmd_queue);
	clReleaseContext(context);
	cout<<"Finished executing kernel.\n";
	return true;
}




bool execOnGpu(long *lmads,bool *writes,long *bounds,string& treestr,int loopvars[],int pvars[],int domain[],int reginfo[],int nlmads,int ndims,int type){
	if(UDEBUG) cout<<"Entered execOnGPu"<<endl;
	if(!scaleLmads(lmads,nlmads,ndims,type)) return false; // scale? address/scale
	int npvars = 0;
	for(int i=0;i<ndims;i++) npvars += pvars[i];
	if(npvars!=2) return false;
	if(UDEBUG) cout<<"everything was RCSLMAD"<<endl;
	vector<int> groups(nlmads);
	vector<bool> overlaps(nlmads*nlmads);
	vector< pair<long,long> > minmax(nlmads);
	
	//the following computes the minimum memory address and the maximum.
	for(int i=0;i<nlmads;i++){
		long *lmad = &lmads[i*(ndims+1)];
		minmax[i].first = lmad[ndims];
		minmax[i].second = lmad[ndims];
		for(int j=0;j<ndims;j++){
			if(lmad[j]>0) minmax[i].second += (bounds[j]-1)*lmad[j];
			else minmax[i].first += (bounds[j]-1)*lmad[j];
		}
	}
	//cout<<"Min and max computed"<<endl;
	
	
	//the follwoing is to find the overlaps between lmads, and store it into a matrix, overlaps.
	for(int i=0;i<nlmads;i++){
		for(int j=0;j<nlmads;j++){
			if(i==j) overlaps[i*nlmads + j] = false;
			else{
				if(isOverlapping(minmax[i].first,minmax[i].second,minmax[j].first,minmax[j].second)){
					overlaps[i*nlmads+j] = true;
				}else{
					overlaps[i*nlmads+j] = false;
				}
			}
		}
	}
	//cout<<"Overlaps computed"<<endl;
	
	//groups
	//group lmads.
	//computeGroups is wrong
	//however, it tries to return the number of the groups
	int ngroups = computeGroups(overlaps,groups);
	vector<LmadGroup *> lgroups;
	formLmadGroups(lgroups,lmads,writes,groups,nlmads,ndims,ngroups);//form LMAD groups in lgroups.
	
	//AMD specific for older AMD hardware (check write restrictions for render backends)
	//now checks if it can do.
	for(int i=0;i<lgroups.size();i++){
		LmadGroup *group = lgroups[i];
		if(group->rw==2) return false;
		if(group->rw==1){
			if( group->getNlmads()>1) return false;
			for(int i=0;i<ndims;i++){
				if(group->getStride(0,i)>0 && pvars[i]==0) return false;
				if(pvars[i]==1 && group->getStride(0,i)==0) return false;
			}
		}
	}
	
	//calculate LMAD size by introduce loop bounds.
	//compute the size without any tiling and see if it fits
	//do it in two steps: init (expensive linear programming) and compute (not that expensive)
	long size = 0;
	for(int i=0;i<lgroups.size();i++) {
		lgroups[i]->initWithBounds(bounds);
		if(UDEBUG && lgroups[i]->isERL) cout<<"Found ERL"<<endl;
		size += lgroups[i]->computeSize(bounds);
	}
	if(type==UNPY_DOUBLE) size*=8;
	else size*=4;
	
	
	//build tree from IL.
	stringstream ss(treestr);
	Tree *tree = new Tree(CSEUM_PROGRAM);
	tree->children.push_back(new Tree(CSEUM_STMTLIST));
	Tree looptree(CSEUM_PFOR);
	ss>>looptree;
	tree->children[0]->children.push_back(&looptree);
	
	//split data regions to fit into the memory.
	//if we want to do pipelining, we can do it here.
	long maxsize = 250*1000*1000;//can be changed
	//check if it can be tiled to fit into the memory.
	while(size>maxsize){
		for(int i=0;i<lgroups.size();i++) if(!lgroups[i]->canTile()) return false;
		break;
	}	
	cout<<"Total size "<<size<<endl;
	if(size>maxsize) return false;
	
	//correct loop bounds and correct index expressions to GPU address expressions
	patch(tree,loopvars,bounds,lgroups,groups,nlmads,ndims,type);//Don't know patch yet.
	//cout<<(*tree)<<endl;
	
	//from now on, generate AMD IL code.
	//if we need to generate OpenCL code, we will do it here in compileTree
	string amdil = compileTree(tree,false,0,0,loopvars);//this will compile the tree into amdil.
	//cout<<amdil<<endl;
	
	
	CALobject obj = NULL;
	CALimage img = NULL;
	if(calclCompile(&obj,CAL_LANGUAGE_IL,amdil.c_str(),CAL_TARGET_770)!=CAL_RESULT_OK) return false;
	if(calclLink(&img,&obj,1)!=CAL_RESULT_OK) return false;
	//calclDisassembleImage(img,callog);
	long imax=0,jmax=0;
	for(int i=0;i<ndims;i++) {
		if(domain[0]==loopvars[i]) imax = bounds[i];
		if(domain[1]==loopvars[i]) jmax = bounds[i];
	}
	//cout<<"Domain is "<<imax<<" "<<jmax<<endl;
	CALdomain rect = {0,0,imax,jmax};
	
	//transferLMADsToGPU
	transferLmadsToGpu(lgroups,bounds,rect,type);
	
	
	CALcontext ctx;
	CALdevice device = unpyGetDevice();
	calCtxCreate(&ctx,device);
	CALmodule module;
	calModuleLoad(&module,ctx,img);
	vector<CALmem> mems(lgroups.size());
	vector<CALname> names(lgroups.size());
	for(int i=0;i<lgroups.size();i++){
		calCtxGetMem(&mems[i],ctx,lgroups[i]->res);
		char name[3];
		name[2] = '\0';
		//cout<<"Res no "<<lgroups[i]->resno<<endl;
		if(lgroups[i]->rw==0) name[0] = 'i';
		else if(lgroups[i]->rw==1) name[0] = 'o';
		name[1] = '0'+(char)lgroups[i]->resno;
		//cout<<"attaching to name "<<name<<endl;
		calModuleGetName(&names[i],ctx,module,name);
		calCtxSetMem(ctx,names[i],mems[i]);
	}
	CALfunc calfunc;
	calModuleGetEntry(&calfunc,ctx,module,"main");
	CALevent e;
	double t1 = unpyTimer();
	calCtxRunProgram(&e,ctx,calfunc,&rect);
	while(calCtxIsEventDone(ctx,e)==CAL_RESULT_PENDING);
	double t2 = unpyTimer();
	cout<<(t2-t1)<<endl;

	for(int i=0;i<mems.size();i++){
		calCtxReleaseMem(ctx,mems[i]);
	}
	calCtxDestroy(ctx);
	
	//here again.
	transferLmadsFromGpu(lgroups,bounds,rect,type);
	
	
	for(int i=0;i<lgroups.size();i++){
		calResFree(lgroups[i]->res);
	}
	
	return true;
}

