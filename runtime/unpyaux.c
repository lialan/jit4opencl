#include <unpython.h>
#include <sys/time.h>
#include <stdlib.h>
#include <stdio.h>
static int unpyGpuEnable = 0;
int unpyIsGpuEnabled(){ return unpyGpuEnable;}
void unpyEnableGpu(){ unpyGpuEnable = 1;}
void unpyDisableGpu(){ unpyGpuEnable = 0;}


static CALdevice device;
CALdevice unpyGetDevice(){return device;}

void unpyInitGpu(){
	if(calInit()!=CAL_RESULT_OK) printf("problem with cal init\n");
	if(calDeviceOpen(&device,0)!=CAL_RESULT_OK) printf("problem with device init\n");
	else printf("opened device\n");
}

void unpyCloseGpu(){
	calDeviceClose(device);
	calShutdown();
}

double unpyTimer(){
	struct timeval tv;
	gettimeofday(&tv,NULL);
	return (double)tv.tv_sec + (1.0e-6)*tv.tv_usec;
}


void unpyLog(char *str){
	printf("%s\n",str);
}

static int unpyEnrollEnable = 0;
int unpyIsUnrollEnabled(){return unpyEnrollEnable;}
void unpyEnableUnroll(){ unpyEnrollEnable = 1;}
void unpyDisableUnroll(){unpyEnrollEnable = 0;}
