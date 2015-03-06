
#include <assert>
#include "tree.hpp"


/*	new data structure for LMAD
*/ 
typedef struct LMAD_{
	int ndim;
	long base;
	long *strides;
	long *bounds;
	long *groupBounds;
} LMAD;



/*	This method converts the original LMAD format into a better format.
*/
LMAD ConvertLMAD(long* lmads, int ndim, long* nbound){
	*LMAD lmads = new LMAD[ndim];
	long* strideptr = lmads;
	long* boundptr = nbound;
	for (int i = 0; i < ndim; i++){
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


/*	This method finds out a proper boundary for each group.
	It takes as input the size of the shared memory, the register file
	size, andthe LMAD.
	
	The result is written into LMAD.groupBounds. Return true if it can successfully
	deploy the kernel.
	
	TODO: if we need to change the way we do optimization, we will do it here.
*/
bool calculateGroupBound(LMAD *lmads, int smsize/*in KB*/, int regfilesize/*in KB*/, int type){
	//convert into bytes.
	smsize << 10;
	regfilesize << 10;
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


