#include <algorithm>
#include "analysis.hpp"



/*By Xunhao. This File contains analysis on the parameters.
*/



/*	analysis calculates the Grid configuration, thread size, computation task.
	It will call other analysis
*/
static Configuration GridAnalysis(ProgramInfo pinfo, GPUInfo gpuinfo){
	Configuration conf;
	
	//calculate #warp 
	int warps = 0;
	int maxWarpSize = FLOOR((float)gpuinfo.sharedMemPerMP / (pinfo.SMSize * gpuinfo.WarpSize));
	if (maxWarpSize >= gpuinfo.MaxWarpSize){
		warps = gpuinfo.MaxWarpSize;
	}else{
		warps = maxWarpSize;
	}
	
	//mininal warp size will always be less than max warp size, otherwise, will quit.
	int minWarpSize = CEILING((float)gpuinfo.SMLatency * gpuinfo.ProcessorPerMP / gpuinfo.WarpSize);
	
	//after getting the result of this method, you should check first about the boolean value to see
	//if it can be done on GPU.
	if (minWarpSize > maxWarpSize){
		conf.canDo = false;
		return conf;
	}
	
	//number of maximum threads
	int nmaxthreads = gpuinfo.WarpSize * warps;
	
	//TODO:

	decideGridSize(pinfo, gpuinfo, Configuration);
	
	
	
}

/*	Decide grid configuration -- grid size. grid size [a] = bound_a / block_a
*/
static void decideGridSize(ProgramInfo pinfo, GPUInfo gpuinfo, Configuration &conf){
	for (int i = 0; i < conf.griddim; i ++){
		conf.gridsize[i] = pinfo.bound[i]/conf.blocksize[i];
	}
}



/*	Decide the maximum unrolling factor.
	input parameters: blocksize, regsize, SMsize
*/
static unsigned int decideUnrollingFactor(ProgramInfo pinfo, GPUInfo gpuinfo, Configuration &conf, int nmaxthreads){
	unsigned int ufact = 1;
	unsigned int regRest = 1;
	unsigned int smRest = 1;
	
	//first we calculate the thread size
	int threadsize = 1;
	for (int i = 0; i < conf.blockdim; i++){
		threadsize *= pinfo.blocksize[i];
	}
	
	//register restriction
	regRest = FLOOR((float)threadsize/nmaxwarps);

	//shared memory restriction
	//TODO
	
	
	//find the minimum:
	ufact = min(regRest, smRest);
	return ufact;
}





/*	analysis the total transfer ratio. 
	TODO: Warning, this must be changed. if rcslmad will transfer a rectangle 
	memory.
*/
static  int getRCSLMADSize(LMAD *lmads, int nlmads){
	int size = 0;
	LMAD *lmadptr;
	for (int i = 0; i < nlmads; i++){
		int tempsize = 1;
		for (int j = 0; j < lmads[0].ndim; j++){
			tempsize *= lmads[i].bounds[j];
		}
		size += tempsize;
	}
	return size;
}

static Configuration RatioAnalysis(ProgramInfo pinfo, GPUInfo gpuinfo, Configuration conf){
	//the total size to be transferred. 
	//we can calculate the sum of all RCSLMADs
	
	//figure out the block size?
	
	//test to see if the block size can be fit into the shared memory.
	
	//write to configuration
}



/*	This method finds out a proper boundary for each group.
	It takes as input the size of the shared memory, the register file
	size, andthe LMAD.
	
	The result is written into LMAD.groupBounds. Return true if it can successfully
	deploy the kernel.
	
	TODO: if we need to change the way we do optimization, we will do it here.
*/
bool calculateGroupBound(LMAD *lmads, int type, ProgramInfo pinfo){
	//convert into bytes.
	int smsize = pinfo.SMsize << 10;
	int regfilesize = pinfo.regSize << 10;
	//scaling:
	int scale = 0;
	if (type==UNPY_FLOAT){
		smsize >> 2;
		regfilesize >>2;
	}else if (type==UNPY_FLOAT || type == UNPY_INT){
		smsize >> 4;
		regfilesize >> 4;
	}else return false;
	
	//find a tiling parameter set.
	//TODO: if we want to change the way we do optimization, we will 
	//start to change from here.
	
	/**********************************************/
	//A tentative idea is to try 16*k:
	int bound = 0;
	int lastbound = 0;
	for (int i = 0; i < 16; i++){
		bound = k * 16;
		int value = calculateLMADSize(lmads, nlmads, bound);
		if (value > smsize) break;
		lastbound = bound;
	}
	//by now lastbound is the boundary.
	if (lastbound == 0){
		return false;
	}
	
	//assign groupbound for lmads:
	for (int i = 0; i < nlmads; i++){
		for (int j = 0; j < ndims; j++){
			lmads[i].groupbound[j] = lastbound;
		}
	}
	/**********************************************/
	
	return true;//successfully assigned group bounds;
}


/*	This will calculate the size of the LMAD according to bound.*/
int calculateLMADSize(LMAD *lmads, int nlmads, int bound){
	int size = 0;
	for (int i = 0; i < nlmads; i++){
		int onesize = 1;
		for (int j = 0; j < lmads[0].ndim; j ++){
			size *= lmads[i].bounds[j];
		}
		size += onesize;
	}
	return size;
}




/*	This Method will test if it is a perfect loop nest.
	It will take as an input the tree structure, with root 
	as a Parallel loop.(CSEUM_PFOR)
*/
int OCLisPerfectLoopNestLevel(Tree *tree){
	Tree * root = tree;
	
	assert (root->tag = CSEUM_PFOR);
	
	Tree* child;
	int level = 1;
	while (root->children.size() == 1){
		child = root->children[0];
		if (child->tag != CSEUM_PFOR){
			break;
		}
		root = child;
		level++;
	}
	
	return level;
}


