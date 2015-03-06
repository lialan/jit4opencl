import compiler
from compiler.ast import *
import sys
import subprocess
import dumpast
import os

def dumpIL(argv):
	for arg in argv[1:]:
		ast = compiler.parseFile(arg)
		walker = dumpast.ASTPrinter()
		compiler.visitor.walk(ast,walker)
		pykname = arg[:-2]+'pyk'
		pykf = open(pykname,'w')
		pykf.write(repr(ast)+'\n')
		pykf.flush()
		pykf.close()
		strf = open(arg[:-2]+'str','w')
		for string in walker.strings:
			strf.write(repr(string)+'\n')
		strf.flush()
		strf.close()

def compile(argv):
	for arg in argv[1:]:
		base = arg[:-3]
		cfile = base+'.cpp'
		sofile = base+'.so' #Windows users should change this to dll
		if os.path.exists(cfile): 
			os.remove(cfile)
		if os.path.exists(sofile): 
			os.remove(sofile)
		
		subprocess.call(['java','-ea','org.unpython.newscala.Unpython',base])
		
		if not os.path.exists(cfile):
			return -1
		
		subprocess.call(['g++','-O3','-fopenmp','-I/usr/include/python2.5', '-I/usr/lib/python2.5/site-packages/numpy/core/include/numpy',cfile,'-shared','-fPIC','-I/home/rahul/unpython/include','/home/rahul/unpython/runtime/execGpu.so','-I/usr/local/atical/include','/home/rahul/unpython/runtime/unpyaux.so','-o',sofile])
		
		if not os.path.exists(sofile):
			print 'could not create',sofile
			return -1
		return 0

def removeIL(argv):
	for arg in argv[1:]:
		base = arg[:-3]
		strfile = base+'.str'
		pykfile = base+'.pyk'
        if os.path.exists(strfile):
            os.remove(strfile)
        if os.path.exists(pykfile):
            os.remove(pykfile)

dumpIL(sys.argv)
success = compile(sys.argv)
removeIL(sys.argv)
sys.exit(success)
