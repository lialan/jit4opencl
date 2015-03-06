lexer grammar Pystring;
@header {
package org.unpython.compiler.frontend;
}

T6 : '\n' ;
T7 : '\'' ;
T8 : '\"' ;
T9 : '\'\'\'' ;
T10 : '\\' ;

// $ANTLR src "../src/org/unpython/compiler/frontend/Pystring.g" 46
ALPHA	:	('a'..'z')|('A'..'Z')|'-'|'>'|' '|','|':'|'@'|('0'..'9')| ')'|'('|'['|']';	
// $ANTLR src "../src/org/unpython/compiler/frontend/Pystring.g" 47
PREFIX	:	'r'|'u'|'ur'|'R'|'U'|'UR'|'Ur'|'uR';
