lexer grammar Python;
@header {
/** unPython : Python to C compiler
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
*/
package org.unpython.compiler.frontend;
}

MODULE : 'Module' ;
NONE : 'None' ;
CONST : 'Const' ;
STMT : 'Stmt' ;
PYSTR : 'PyString' ;
DISCARD : 'Discard' ;
IF : 'If' ;
RETURN : 'Return' ;
GLOBAL : 'Global' ;
ASSIGN : 'Assign' ;
ASSNAME : 'AssName' ;
FUNCTION : 'Function' ;
CALLFUNC : 'CallFunc' ;
ASSATTR : 'AssAttr' ;
ADD : 'Add' ;
SUB : 'Sub' ;
MUL : 'Mul' ;
DIV : 'Div' ;
GETATTR : 'Getattr' ;
NAME : 'Name' ;
IMPORT : 'Import' ;
FROM : 'From' ;
FOR : 'For' ;
CLASS : 'UClass' ;
SLICE : 'Slice' ;
SUBSCRIPT : 'Subscript' ;
SLICEOBJ : 'Sliceobj' ;
COMPARE : 'Compare' ;
KEYWORD : 'Keyword' ;
CGT : '\'>\'' ;
CLT : '\'<\'' ;
CEQ : '\'==\'' ;
CNEQ : '\'!=\'' ;
CGTE : '\'>=\'' ;
CLTE : '\'<=\'' ;
T60 : '(' ;
T61 : ')' ;
T62 : '\'' ;
T63 : '[' ;
T64 : ']' ;
T65 : 'Annot' ;
T66 : 'Decorators' ;
T67 : '\'OP_ASSIGN\'' ;
T68 : '\'OP_APPLY\'' ;

// $ANTLR src "Python.g" 268
fragment
ALPHA 	:	('a'..'z')|('A'..'Z')|'_';

// $ANTLR src "Python.g" 271
fragment
DIGIT	:	'0'..'9';

// $ANTLR src "Python.g" 274
ID	:	ALPHA (ALPHA|DIGIT)*; 
// $ANTLR src "Python.g" 275
INT 	:	('+'|'-')? DIGIT+;
// $ANTLR src "Python.g" 276
FLOAT 	:	('+'|'-')? DIGIT* '.' DIGIT* ('e'INT)?
	|	('+'|'-')? ('0'..'9')* 'e'INT
	;
// $ANTLR src "Python.g" 279
WS		: (' '|'\t'|','|'\n'|'\r')+ {skip();};
