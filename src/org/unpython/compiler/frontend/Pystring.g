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
grammar Pystring;

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
import java.util.ArrayList;
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

literals returns [ArrayList<String> list]    
@init{
	$list = new ArrayList<String>();
}
	:  (literal '\n' {$list.add($literal.text);})*
	;

literal 	:	PREFIX? (shortstring | longstring) 
	;

shortstring	:	('\'' shortstringitem* '\'') | ('\"' shortstringitem* '\"')
	;

longstring 	:	'\'\'\'' longstringitem* '\'\'\''
	;

shortstringitem
	:	shortstringchar | escapeseq
	;	

longstringitem
	:	longstringchar | escapeseq
	;

shortstringchar	:	ALPHA
	;
escapeseq 
	:	'\\' ALPHA
	;
longstringchar
	:	shortstringchar|'\n'
	;	


ALPHA	:	('a'..'z')|('A'..'Z')|'-'|'>'|' '|','|':'|'@'|('0'..'9')| ')'|'('|'['|']';	
PREFIX	:	'r'|'u'|'ur'|'R'|'U'|'UR'|'Ur'|'uR';
