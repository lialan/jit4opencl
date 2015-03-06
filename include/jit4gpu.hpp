#include <string>
enum {UNPY_RO,UNPY_RW,UNPY_WO};
enum {UNPY_INT, UNPY_FLOAT, UNPY_DOUBLE};

extern bool execOnGpu(long *lmads,bool *writes,long *bounds,std::string& tree,int loopvars[],int pvars[],int domain[],int reginfo[],int nlmads,int ndims,int type=UNPY_FLOAT);

