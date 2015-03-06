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
grammar Python;

options {
output=AST;
ASTLabelType=CommonTree;
backtrack=true;
}


tokens{
LIST;
ANNOT;
STRING;
MODULE='Module';
NONE='None';
CONST = 'Const';
STMT = 'Stmt';
PYSTR = 'PyString';
DISCARD = 'Discard';
IF = 'If';
RETURN = 'Return';
GLOBAL = 'Global';
ASSIGN = 'Assign';
ASSNAME = 'AssName';
FUNCTION = 'Function';
CALLFUNC = 'CallFunc';
ASSATTR = 'AssAttr';
ADD = 'Add';
SUB = 'Sub';
MUL = 'Mul';
DIV = 'Div';
GETATTR = 'Getattr';
NAME = 'Name';
IMPORT='Import';
FROM='From';
FOR='For';
CLASS='UClass';
SLICE='Slice';
SUBSCRIPT='Subscript';
SUBSCRIPTG;
SUBSCRIPTS;
SLICEOBJ='Sliceobj';
COMPARE='Compare';
KEYWORD='Keyword';
CGT='\'>\'';
CLT='\'<\'';
CEQ='\'==\'';
CNEQ='\'!=\'';
CGTE='\'>=\'';
CLTE='\'<=\'';
COPY;
CFOR;
CIF;
RANGE;
LABEL;
GOTO;
CALLMETH;
DIM;
CONVERT;
PARFOR;
}

@header{
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

@lexer::header{
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
module	: 	MODULE^ '('! docString stmt ')'!
	;

docString	:	 pyconst 
	|	 NONE
	;

pyconst	:	CONST^  '('! (pyStr | INT | FLOAT | NONE) ')'!
	;
	
pyStr	:	 '\''! PYSTR^ '('! INT ')'! '\''! 
	;

stmt 	:	STMT '(' '[' (annotStmt)+ ']' ')'  -> ^(STMT annotStmt+) 
	|	STMT^ NONE 
	;

annotStmt 	:	 annot pyStmt -> ^(ANNOT annot pyStmt)
	|	pyStmt
	;

annot 	:	'Annot'! '('! pyStr ')'! 
	;
		
pyStmt 	:	assign
	|	stmt
	|   	defun
	|	global
	|	pyreturn
	|	pyif
	|	pyimport
	|	discardStmt
	|	pyfor
    |   pyclass
    |   NONE
	;

pyclass : CLASS '(' id '[' expr+ ']'  NONE pyStmt pyinit ')' -> ^(CLASS id ^(LIST expr+) pyStmt pyinit) 
    ;

pyinit : defun | NONE;

pyimport 	:	IMPORT '(' '[' ( '(' id idornone ')' )+ ']' ')' -> ^(IMPORT ^(LIST (id idornone)+))
	;
	
idornone:	NONE|id
	;

discardStmt	: DISCARD^ '('! expr ')'!
	;	
pyifhelper : expr pyStmt -> ^(LIST expr pyStmt)
    |   expr pyStmt+ -> ^(LIST expr ^(STMT pyStmt+))
    ;

pyif	:	IF '(' '[' ('(' pyifhelper ')' )+ ']' pyStmt ')' -> ^(IF  ^(LIST pyifhelper+) pyStmt)
    |	IF '(' '[' ('(' pyifhelper ')' )+ ']' pyStmt+ ')' -> ^(IF  ^(LIST pyifhelper+) ^(STMT pyStmt+))
	;

pyfor	:	FOR^ '('! assname callFunc stmt NONE ')'!
	;

pyreturn	: 	RETURN^ '('! expr ')'!
	;

global	:	GLOBAL '(' '[' id+ ']' ')' -> ^(GLOBAL ^(LIST id+))
	;

defun 	:  	FUNCTION^ '('! decorators id paramlist defaults! INT! docString! stmt ')'!
	;

decorators 	:	NONE 
                | 'Decorators' '(' '[' callFunc ']' ')' -> ^(LIST ^(callFunc))
	;

defaults	:	'['! ']'!
	;
		
paramlist	:	'[' id+ ']' -> ^(LIST id+)
	;

	
assign	:	ASSIGN '('  '[' (assnode)+ ']' expr ')' -> ^(ASSIGN ^(LIST assnode+) expr)
	;

assnode 	:	 assname | assattr | subscript  ;
assname	:	ASSNAME^ '('! id '\'OP_ASSIGN\'' ')'!
	;

assattr	:	ASSATTR^ '('! expr id '\'OP_ASSIGN\'' ')'!
	;
	
callFunc 	:	CALLFUNC '(' expr '[' expr* ']' NONE NONE ')' -> ^(CALLFUNC expr ^(LIST expr*) NONE NONE)
	;	
expr	:	add
	|	mul
	|	sub
    |   div
	| 	name
	|	getattr
	|	pyconst
	|	callFunc
    |   sliceobj
    |   subscript
    |   slice
    |   compare
	|   keyword
	;

add 	:	ADD^ '('! '('! expr expr ')'! ')'!
	;

sub 	:	SUB^ '('! '('! expr expr ')'! ')'!
	;

mul	:	MUL^ '('! '('! expr expr ')'! ')'!
	;
div :   DIV^ '('! '('! expr expr ')'! ')'!
    ;
compare_op : CGT | CLT | CEQ | CGTE | CLTE ;

compare :   COMPARE^ '('! expr '['! '('! compare_op expr ')'! ']'! ')'!
    ; 
name	:	NAME^ '('! id ')'! 
	;

getattr	:	GETATTR^ '('!expr id ')'!
	;

keyword : 	KEYWORD^ '('! id expr ')'!
	;
id 	:	'\''! ID '\''!
	;

subscript : SUBSCRIPT '(' expr '\'OP_ASSIGN\'' '[' expr+ ']' ')' -> ^(SUBSCRIPTS expr ^(LIST expr+))
    |   SUBSCRIPT '(' expr '\'OP_APPLY\'' '[' expr+ ']' ')' -> ^(SUBSCRIPTG expr ^(LIST expr+))
    ;

slice : SLICE '(' '[' expr '\'OP_ASSIGN\'' expr+ ']' ')' -> ^(SUBSCRIPTS expr ^(LIST ^(SLICEOBJ expr+)))
    | SLICE '(' '[' expr '\'OP_APPLY\'' expr+ ']' ')' -> ^(SUBSCRIPTG expr ^(LIST ^(SLICEOBJ expr+)))
    ;

sliceobj : SLICEOBJ '(' '[' expr+ ']' ')' -> ^(SLICEOBJ  expr+)
    ;

fragment
ALPHA 	:	('a'..'z')|('A'..'Z')|'_';

fragment
DIGIT	:	'0'..'9';

ID	:	ALPHA (ALPHA|DIGIT)*; 
INT 	:	('+'|'-')? DIGIT+;
FLOAT 	:	('+'|'-')? DIGIT* '.' DIGIT* ('e'INT)?
	|	('+'|'-')? ('0'..'9')* 'e'INT
	;
WS		: (' '|'\t'|','|'\n'|'\r')+ {skip();};
