#ifndef OCLCODEGEN
#define OCLCODEGEN

#include "analysis.hpp"

static void genHeader();

static void genGetID(stringstream &ss, ProgramInfo pinfo);

static void genMemoryLoad(stringstream &ss, ProgramInfo pinfo);

static void genBody(stringstream &ss, ProgramInfo pinfo);

static void genUnrolledBody(stringstream &ss, ProgramInfo pinfo);

static void genMemStore(stringstream &ss, ProgramInfo pinfo);

static void genTail(stringstream &ss, ProgramInfo pinfo);

static int[] getIDExpression(ProgramInfo pinfo);

static string TAGHelper(int tag);

static string compileBody(Tree *tree, ProgramInfo pinfo);

static string arrayMapper(Tree* tree);

#endif
