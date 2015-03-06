/*	By Xunhao.

*/
#define DEBUG true

#include "OCLCall.hpp"


/*	This method creates the OpenCL running context and handles OpenCL Execution.
	it requires the generated kernel be input as kernel_string, and the Configuration
	file.
 */
bool OCLExec(char *kernel_string, Configuration conf){
		
	if (conf.canDo == false){
		if(DEBUG)
			cout<<"The configuration says that it cannot be executed on gpu. Fallback.\n";
		return false;
	}
	
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

	//set global item dimens.
	size_t global_work_size[conf.griddim];
	for (int i =0; i < conf.globaldim; i++){
		global_work_size[i] = conf.gdim[i];
	}
	
	//set local work dimens:
	size_t local_work_size[conf.blockdim];
	for (int i = 0; i < conf.blockdim; i ++){
		local_work_size[i] = conf.blocksize[i];
	}
	

	//locate GPU memory, and copy data onto GPU
	//input memory:
	//TODO: rewrite here
	cl_memory memobjs[conf.nRCSLMAD];
	cl_uint err;
	for (int i = 0; i < conf.nRCSLMAD; i ++){
		cl_mem_flags flags;
		int inner_err;
		if (conf.RCSLMADs.write){
			flags = CL_MEM_READ_WRITE;
		}else{
			flags = CL_MEM_READ_ONLY;
		}
		flags |= CL_MEM_COPY_HOST_PTR;
		
		//TODO: add tiling here
		memobjs[i] = clCreateBuffer(context, flags, input[i].size, input[i].data, inner_err); 
		//we ignore tiling to fit the global memory at present.
		dataptr = dataptr.next;
		err |= inner_err;		
	}
	
	/*
	//output memory, output memory are those memory that we don't read from them, but write to them.
	cl_memory outmemobjs[outputNum];
	err = 0;
	for (int i = 0; i < outputNum; i++){
		int inner_err;
		outmemobjs[i] = clCreateBuffer(context, CL_MEM_READ_WRITE, output[i].size, NULL, inner_err);
		dataptr = dataptr.next;
		err |= inner_err;
	}
	*/
	
	
	//check if successfully created memory objects.
	if (err != CL_SUCCESS){
		deleteMemobjs(memobjs, nRCSLMAD);
		cout<<"Cannot Allocate Memory Objects."<<"\n";
		return false;
	}
	
	/*	set the args. 
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
	int shortSize = sizeof(short);
	int intSize = sizeof(int);
	int floatSize = sizeof(float);
	int doubleSize = sizeof(double);
	
	//add primitive parameters
	for (int i = 0; i < conf.nPrim; i++){
		int size = 0;
		if (conf.primType[i] == SHORT)
			size = shortSize;
		else if (conf.primType[i] == INTEGER)
			size = intSize;
		else if (conf.primType[i] = FLOAT)
			size = floatSize;
		else if (conf.primType[i] = DOUBLE)
			size = doubleSize;
		err |= clSetKernelArg(kernel, idx + i, size, &conf.primitives[i]); 
	}
	
	if (err != CL_SUCCESS){
		cout<<"cannot Set kernel arguments.\n";
		deleteMemobjs(memobjs, conf.nRCSLMAD);
		clReleaseKernel(kernel);
		clReleaseProgram(program);
		clReleaseCommandQueue(cmd_queue);
		clReleaseContext(context);
		cout<<"Cannot set Kernel arguments.\n";
		return false;
	}
		
	//start kernel.
	err = clEnqueueNDRangeKernel(cmd_queue, kernel, 1, NULL, global_work_size, local_work_size, 0, NULL, NULL);
	if (err != CL_SUCCESS){
		deleteMemobjs(memobjs, conf.nRCSLMAD);
		clReleaseKernel(kernel);
		clReleaseProgram(program);
		clReleaseCommandQueue(cmd_queue);
		clReleaseContext(context);
		cout<<"Cannot execute kernel.\n";
		return false;
	}
	
	//read buffer:
	err = 0;
	for (int i = 0; i < conf.nRCSLMAD; i++){
		//we only read those changed RCSLMADs
		if (conf.RCSLMADs[i].write)
			err |= clEnqueueReadBuffer(cmd_queue, outmemobjs[i], CL_TRUE, 0, output[i].size, output[i].data, 0, NULL, NULL);
	}
	if (err != CL_SUCCESS){
		deleteMemobjs(memobjs, conf.nRCSLMAD);
		clReleaseKernel(kernel);
		clReleaseProgram(program);
		clReleaseCommandQueue(cmd_queue);
		clReleaseContext(context);
		cout<<"Cannot copy results from device.\n";
		return false;
	}
	
	//now finishing up the compiling.
	deleteMemobjs(memobjs, conf.nRCSLMAD);
	clReleaseKernel(kernel);
	clReleaseProgram(program);
	clReleaseCommandQueue(cmd_queue);
	clReleaseContext(context);
	cout<<"Finished executing kernel.\n";
	return true;
}
