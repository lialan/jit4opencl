#include "tree.hpp"
#include <stack>

void discoverChildren(Tree *tree,LoopStruct *loop){
	queue<Tree *> q;
	for(int i=0;i<tree->children.size();i++) q.push(tree->children[i]);
	while(!q.empty()){
		Tree *front = q.front();
		q.pop();
		if(front->tag==CSEUM_PFOR || front->tag==CSEUM_FOR){
			LoopStruct *child = new LoopStruct;
			child->loopid = front->getChild(0)->idata;
			if(front->tag==CSEUM_PFOR) child->isParallel=true;
			else child->isParallel = false;
			child->parent = loop;
			loop->children->push_back(child);
			discoverChildren(front,child);
		}else{
			for(int i=0;i<front->children.size();i++) q.push(front->children[i]);
		}
	}
}


//arg must be program
LoopStruct *discoverLoop(Tree *tree){
	LoopStruct *loops = new LoopStruct;
	loops->parent = NULL;
	loops->unroll = 0;
	loops->isParallel = true;
	loops->loopid = tree->getChild(0)->getChild(0)->getChild(0)->idata;
	discoverChildren(tree->getChild(0)->getChild(0),loops);
}

