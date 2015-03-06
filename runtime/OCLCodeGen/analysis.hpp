#ifndef ANALYSIS_HPP
#define ANALYSIS
#include "../tree.hpp"
/*Analysis Header File.*/

#define KB (1<<10)
#define FLOOR(X) (X-(int)(X) <= 0 ? (int)(X+1) : (int)(X))
#define CEILING(X) (X-(int)(X) > 0 ? (int)(X+1) : (int)(X))



/*	new data structure for LMAD
*/ 
typedef struct LMAD_{
	int ndim;
	long base;
	long *strides;
	long *bounds;
	long *groupBounds;
	long size;//must be updated.
} LMAD;



/*	This method converts the original LMAD format into a better format.
*/
LMAD ConvertLMAD(long* lmads, int ndim, long* nbound, int nlmads){
	*LMAD lmads = new LMAD[nlmads];
	long* strideptr = lmads;
	long* boundptr = nbound;
	for (int i = 0; i < nlmads; i++){
		lmads[ndim].ndim = ndim;
		lmads[ndim].strides = new long[ndim];
		lmads[ndim].bounds = new long[ndim];
		lmads[ndim].groupBounds = new long[ndim];
		for (int j = 0; j < ndim; j ++){
			lmads[ndim].strides[j] = strideptr;
			strideptr++;
			
			lmads[ndim].bounds = boundptr;
			boundptr++;
		}
		lmads[ndim].base = strideptr;
		strideptr++;
	}
}


typedef struct Configuration_{
	int globaldim; // global work item size
	int gdimsize[3]; // 1 ~ 3
	int griddim; // 1 ~ 3
	int gridsize[3]; //x, y, z, respectively
	int threadsize; // number of threads per grid
	int unrollfact; // unrolling factor
	
	//more to come
	bool canDo = true; // identifies if it can be transfered into GPU code.
	int blockdim; // what's the block dimension?
	int blocksize[3]; // x, y, z, respectively
	
	int nlmads; // number of lmads
	LMAD *lmads[10]; // currently we set it up to 10
	
	int nPrim; // number of Primitive variables
	int primitives[10]; // currently we set it up to 10
	int primType[10]; // 10 primitive types
	
} Configuration;


/*it is fixed when running.
or?
*/
typedef struct GPUInfo_{
	unsigned int SharedMemPerMP; // shared memory per MP has
	unsigned int RegFilePerMP; // register file size per MP has
	unsigned int DRAMSize; // global memory size
	unsigned int ProcessorPerMP; // number of processors in each MP
	int SMLatency; // the memory latency to access shared memory
	int WarpSize = 32; // number of threads per warp
	int MaxWarpSize = 24; // how many warps can be issued concurrently 
}GPUInfo;



typedef struct ProgramInfo_{
	int regSize; //register size
	int SMsize; //shared memory usage
	int loopNestLevel; //how many levels of nested loops are there? Of course it should be a perfect loop nest.
	int bound[3]; //boundary for each levels of loops.
	Tree *tree; // loop tree structure
	
	LMAD *lmads;
	int nlmads;
	int type;
	
	//primitive variables
	//*prims saves the id number of the primitive variables.
	int nprims;
	int *prims;
	int *primtype;//type of each primitive
	
	//group memory size 
	
	
}ProgramInfo;




#endif