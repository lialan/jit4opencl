#unPython : Python to C compiler
#Copyright (C) 2008  Rahul Garg
#This program is free software: you can redistribute it and/or modify
#it under the terms of the GNU General Public License version 3 as published by
#the Free Software Foundation.
#This program is distributed in the hope that it will be useful,
#but WITHOUT ANY WARRANTY; without even the implied warranty of
#MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
#GNU General Public License for more details.
#You should have received a copy of the GNU General Public License
#along with this program.  If not, see <http://www.gnu.org/licenses/>.
#Rahul Garg grants some additional permissions as per GNU General Public License 
#version 3 section 7. 
#These additional permissions can be found in a file exceptions.txt.
 
from org.unpython.compiler.types import *
from freemarker.template import *
from java.io import *
from java.util import ArrayList,HashMap,Map
from org.unpython.compiler.frontend import PythonParser
from java.lang import System

def mapType(pytype):
	if isintance(pytype,PyInt):
		return "int"
	elif isintance(pytype,PyDouble): 
		return "double"
	elif isinstance(pytype,PyFloat):
		return "float"
	elif isinstance(pytype,PyArrayObject):
		return "PyArrayObject *"
	return "PyObject *"

def buildFunCall(name,args):
	val = name + "("
	n = len(args)
	for i in range(n)):
		val = val + args[i]
		if i<n-1:
			val = val + ","
	val = val + ")"
	return val

def formatString(pytype):
	if isinstance(pytype,PyInt):
		return "i"
	elif isinstance(pytype,PyFloat):
		return "f"
	elif isinstance(pytype,PyDouble):
		return "d"
	elif isinstance(pytype,PyObjectType):
		return "O"
	else:
		return None

def genExprCode(expr,root):
	t = expr.getType()
	if t==PythonParser.ADD:
		lchild = expr.leftChild
		rchild = expr.rightChild
		lprimitive = PyUtils.isPrimitive(lchild,root)
		rprimitive = PyUtils.isPrimitive(rchild,root)
		if lprimitive and rprimitve:
			return genExprCode(lchild,root) + "+" + genExprCode(rchild,root)
		elif lprimitive:
			return buildFunCall("PyNumber_Add",
					[buildFunCall("Py_BuildValue",[formatString(lchild.type),
						genExprCode(lchild,root)],
						
class CodeGen(object):
	def __init__(self):
		self.cfg = Configuration()
		self.cfg.setClassForTemplateLoading(CodeGen,"")
		cfg.setObjectWrapper(DefaultObjectWrapper())
		try:
			mtemplate = cfg.getTemplate("module.ftl")
		except ex:
			ex.printStackTrace()
			System.exit(-1)




