<#--
 unPython : Python to C compiler
    Copyright (C) 2008  Rahul Garg

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License version 3 as published by
    the Free Software Foundation.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
    
    Rahul Garg grants some additional permissions as per GNU General Public License version 3 
  section 7. These additional permissions can be found in a file exceptions.txt.
-->
#include <Python.h>
#include <structmember.h>
#include "arrayobject.h"
#include <stdarg.h>

static PyArrayObject *unpython_slice(PyArrayObject *arr,int nd,...){
    int i;
    PyObject **indices = (PyObject **)malloc(sizeof(PyObject *)*nd);
    va_list argp;
    PyArrayObject *retval;
    va_start(argp,nd);
    for(i=0;i<nd;i++){
        int args = va_arg(argp,int);
        if(args==1){
            int arg1 = va_arg(argp,int);
            indices[i] = PyInt_FromLong(arg1);
        }else if(args==3){
            PyObject *arg1 = PyInt_FromLong(va_arg(argp,int));
            PyObject *arg2 = PyInt_FromLong(va_arg(argp,int));
            PyObject *arg3 = PyInt_FromLong(va_arg(argp,int));
            indices[i] = PySlice_New(arg1,arg2,arg3);
            //Py_DECREF(arg1);
            //Py_DECREF(arg2);
            //Py_DECREF(arg3);
        }
    }
    va_end(argp);
    PyObject *tup = PyTuple_New(nd);
    for(i=0;i<nd;i++) PyTuple_SetItem(tup,i,indices[i]);
    retval = (PyArrayObject *)PyObject_GetItem((PyObject *)arr,tup);
    Py_DECREF(tup);
    for(i=0;i<nd;i++) Py_DECREF(indices[i]);
    return retval;
}


static PyArrayObject *unpython_new(char *tc,int nd,...){
    int i;
    va_list argp;
    PyArrayObject *retval;
    PyObject *tup = PyTuple_New(nd);
    PyObject *str = Py_BuildValue("s",tc);
    va_start(argp,nd);
    for(i=0;i<nd;i++) PyTuple_SetItem(tup,i,PyInt_FromLong(va_arg(argp,int)));
    PyObject *numpy = PyImport_ImportModule("numpy");
    PyObject *empty = PyObject_GetAttrString(numpy,"empty");
    PyObject *args = Py_BuildValue("(00)",tup,str);
    retval = (PyArrayObject *)PyObject_CallObject(empty,args);
    Py_DECREF(numpy);
    Py_DECREF(empty);
    Py_DECREF(tup);
    Py_DECREF(str);
    va_end(argp);
    return retval;
}

<#list functions as fun>
${fun.rtype} ${fun.name}(<#list fun.params as param>${param.type} ${param.name}<#if param_index < fun.params?size-1>,</#if></#list>);
</#list>

<#list classes as klass>
typedef struct{
<#if (klass.superklass)?has_content>
${name}_${klass.superklass}Object unpython_${klass.superklass};
<#else>
PyObject_HEAD
</#if>
/* type specific fields go here*/
<#list klass.members as member>
${member.type} ${member.name};
</#list>
}${name}_${klass.name}Object;

<#list klass.methods as fun>
${fun.rtype} ${klass.name}_${fun.name}(<#list fun.params as param>${param.type} ${param.name}<#if param_index < fun.params?size-1>,</#if></#list>){
${fun.body}
}
PyObject *${klass.name}_${fun.name}_wrapper(PyObject *self,PyObject *args){
<#list fun.params as param>
<#if (param_index>0)>${param.type} ${param.name};</#if>
</#list>
${fun.rtype} unpython_returner;
${fun.wrapbody}
}
</#list>

static PyMethodDef ${klass.name}_methods[] = {
<#list klass.methods as meth>
    {"${meth.name}",(PyCFunction)${klass.name}_${meth.name}_wrapper,METH_VARARGS,NULL},
</#list>
    {NULL}
};

static PyMemberDef ${klass.name}_members[] = {
<#list klass.members as member>
    {"${member.name}",${member.code},offsetof(${name}_${klass.name}Object,${member.name}),0,NULL},
</#list>
    {NULL}
};

static int 
${klass.name}_init(<#list klass.init.params as param>${param.type} ${param.name}<#if param_index< klass.init.params?size -1>,</#if></#list>){
${klass.init.body}
return 0;
}


static PyTypeObject ${name}_${klass.name}Type = {
    PyObject_HEAD_INIT(NULL)
    0, /*ob_size*/
    "${name}.${klass.name}", /*tp_name*/
    sizeof(${name}_${klass.name}Object), /*tp_basicsize*/
    0, /*tp_itemsize*/
    0, /*tp_dealloc*/
    0, /*tp_print*/
    0, /*to_getattr*/
    0, /*tp_setattr*/
    0, /*tp_compare*/
    0, /*tp_repr*/
    0, /*tp_as_number*/
    0, /*tp_as_sequence*/
    0, /*tp_as_mapping*/
    0, /*tp_hash*/
    0, /*tp_call*/
    0, /*tp_str*/
    0, /*tp_getattro*/
    0, /*tp_setattro*/
    0, /*tp_as_buffer*/
    Py_TPFLAGS_DEFAULT, /*tp_flags*/
    NULL, /*tp_doc*/
    0, /*tp_traverse*/
    0, /* tp_clear */
    0, /* tp_richcompare */
    0, /* tp_weaklistoffset */
    0, /* tp_iter */
    0, /* tp_iternext */
    ${klass.name}_methods,             /* tp_methods */
    ${klass.name}_members,             /* tp_members */
    0, /* tp_getset */
    0, /* tp_base */
    0, /* tp_dict */
    0, /* tp_descr_get */
    0, /* tp_descr_set */
    0, /* tp_dictoffset */
    (initproc)${klass.name}_init,      /* tp_init */
    0, /* tp_alloc */
    0, /* tp_new */

};
</#list>

<#list functions as fun>
${fun.rtype} ${fun.name}(<#list fun.params as param>${param.type} ${param.name}<#if param_index < fun.params?size-1>,</#if></#list>){
${fun.body}
}
PyObject *${fun.name}_wrapper(PyObject *self,PyObject *args){
<#list fun.params as param>
${param.type} ${param.name};
</#list>
${fun.rtype} unpython_returner;
${fun.wrapbody}
}
</#list>

static PyMethodDef ${name}Methods[] = {
<#list functions as fun>
    {"${fun.name}",${fun.name}_wrapper,METH_VARARGS,NULL},
</#list>
    {NULL,NULL,0,NULL}
};


PyMODINIT_FUNC init${name}(){
    PyObject *module = Py_InitModule("${name}",${name}Methods);
<#list classes as klass>
<#if (klass.superklass)?has_content>
	${name}_${klass.name}Type.tp_base = &${name}_${klass.superklass}Type;
</#if>
    ${name}_${klass.name}Type.tp_new = PyType_GenericNew;
    if(PyType_Ready(&${name}_${klass.name}Type)<0) return;
    Py_INCREF(&${name}_${klass.name}Type);
    PyModule_AddObject(module,"${klass.name}",(PyObject *)&${name}_${klass.name}Type);
</#list>
}
