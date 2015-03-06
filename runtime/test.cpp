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
#include <fstream>
#include <cstdlib>
#include <map>
#include <sys/time.h>
using namespace std;

double my_timer(){
	struct timeval tv;
	gettimeofday(&tv,NULL);
	return tv.tv_sec + 1.0e-6*tv.tv_usec;
}
void my_print(const char *text){
	cout<<text;
}

int main(int argc,char **argv){
	stringstream kernel;
	ifstream fname(argv[1]);
	Tree tree(CSEUM_PROGRAM);
	fname>>tree;
	cout<<tree<<endl;
	float *A,*B,*C;
	int size = atoi(argv[2]);
	int stride = atoi(argv[3]);
	A = new float[size*size*stride];
	B = new float[size*size*stride];
	C = new float[size*size*stride];
	cout<<"A aligned "<<((int)A%16 == 0)<<endl;
	cout<<"B aligned "<<((int)B%16 == 0)<<endl;
	cout<<"C aligned "<<((int)C%16 == 0)<<endl;
	
	double t1 = my_timer();
	ArrayInfo ainfo(2,CSEUM_DT_FLOAT),binfo(2,CSEUM_DT_FLOAT),cinfo(2,CSEUM_DT_FLOAT);
	
	ainfo.setStride(0,size*stride*sizeof(float));
	ainfo.setStride(1,stride*sizeof(float));
	ainfo.setUpper(0,size);
	ainfo.setUpper(1,size);
	ainfo.setBase((void *)A);

	binfo.setStride(0,size*stride*sizeof(float));
	binfo.setStride(1,stride*sizeof(float));
	binfo.setUpper(0,size);
	binfo.setUpper(1,size);
	binfo.setBase((void *)B);

	cinfo.setStride(0,size*stride*sizeof(float));
	cinfo.setStride(1,stride*sizeof(float));
	cinfo.setUpper(0,size);
	cinfo.setUpper(1,size);
	cinfo.setBase((void *)C);

	ArrayInfo sum(0,CSEUM_DT_FLOAT), c1(0,CSEUM_DT_INT), c2(0,CSEUM_DT_INT), c3(0,CSEUM_DT_INT);
	map<int,ArrayInfo*> symtable;
	symtable[0] = &sum;
	symtable[1] = &c1;
	symtable[2] = &c2;
	symtable[3] = &c3;
	symtable[4] = &cinfo;
	symtable[5] = &ainfo;
	symtable[6] = &binfo;

	execOnGpu(tree,symtable);
	double t2 = my_timer();
	cout<<(t2-t1)<<endl;
	cout<<"Gflops "<<(2.0e-9*size*size*size/(t2-t1))<<endl;
	delete[] A;
	delete[] B;
	delete[] C;
	
}


