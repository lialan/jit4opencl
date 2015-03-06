/*	By Xunhao.
*/


#ifndef "OCLCALL"
#def "OCLCALL"

#include <CL/cl.h>
#include "analysis.hpp"


bool OCLExec(char *kernel_string, data input, int inputNum, data output, int outputNum,
				double primitiveParms[], int primParmsNum, Configuration conf);

void deleteMemobjs(cl_mem memobjs[], int objNum){
	for (int i = 0; i < objNum; i++)
		clReleaseMemObject(memobjs[i];
}



#endif