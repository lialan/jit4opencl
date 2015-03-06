/** Copyright [2009] [Rahul Garg] 
  * Licensed under the Apache License, Version 2.0 (the "License"); 
  * you may not use this file except in compliance with the License. 
  * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 
  * Unless required by applicable law or agreed to in writing, software distributed under the License 
  * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
  * See the License for the specific language governing permissions and limitations under the License. */
grammar Cseum;
options{
language=C;
output=AST;
ASTLabelType=pANTLR3_BASE_TREE;
backtrack=true;
}

tokens{
CS_DECL;
CS_ASSIGN;
CS_PROGRAM;
CS_STMTLIST;
CS_AREAD;
CS_AWRITE;
CS_SWIZ_READ;
CS_ATYPE;
CS_SWIZ_WRITE;
CS_CAST;
CS_LDS_TYPE;
}
@header{
/** Copyright [2009] [Rahul Garg] 
  * Licensed under the Apache License, Version 2.0 (the "License"); 
  * you may not use this file except in compliance with the License. 
  * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 
  * Unless required by applicable law or agreed to in writing, software distributed under the License 
  * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
  * See the License for the specific language governing permissions and limitations under the License. */
}
@lexer::header{
	/** Copyright [2009] [Rahul Garg] 
  * Licensed under the Apache License, Version 2.0 (the "License"); 
  * you may not use this file except in compliance with the License. 
  * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 
  * Unless required by applicable law or agreed to in writing, software distributed under the License 
  * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
  * See the License for the specific language governing permissions and limitations under the License. */

}
program: stmtlist -> ^(CS_PROGRAM stmtlist);
stmtlist:	'{' decl* stmt* '}' -> ^(CS_STMTLIST decl* stmt*);
stmt:	assign 
	| swhile
	| sfor
	| stmtlist
	| sif
	| fence
	; 
fence: (CS_FENCE_LDS | CS_FENCE_MEM | CS_FENCE_THREADS | CS_FENCE_SR) ';'!;
sif: CS_IF^ '('! expr ')'! s1=stmt (options{k=1;}:selse)? 
	;
selse: CS_ELSE^ stmt;
assign: CS_ID '=' expr ';' -> ^(CS_ASSIGN CS_ID expr)
	|	CS_GLOB '[' expr ']' '=' expr ';' -> ^(CS_ASSIGN ^(CS_AWRITE CS_GLOB expr) expr)
	|   CS_OUTPUT_BUF '=' expr ';' -> ^(CS_ASSIGN CS_OUTPUT_BUF expr)
	|	CS_ID '.' CS_SWIZZLE  '=' expr ';' -> ^(CS_ASSIGN ^(CS_SWIZ_WRITE CS_ID CS_SWIZZLE) expr)
	|	CS_GLOB '[' expr ']' '.' CS_SWIZZLE '=' expr ';' -> ^(CS_ASSIGN ^(CS_SWIZ_WRITE ^(CS_AWRITE CS_GLOB expr) CS_SWIZZLE) expr)
	|	CS_LDS '[' CS_CONST_INT ']' '=' expr ';' -> ^(CS_ASSIGN ^(CS_AWRITE CS_LDS CS_CONST_INT) expr)
	;
swhile:	CS_WHILE^ '('! expr ')'! stmt 
	;
sfor:	CS_FOR '(' expr? ';' expr? ';' expr? ')' stmt 
	-> ^(CS_FOR expr expr expr stmt)
	;
expr: 	CS_ID '=' expr -> ^(CS_ASSIGN CS_ID expr)
	| 	addexpr (('<'|'>'|'<='|'>='|'!='|'==')^ addexpr)* 
	|   '('! expr ')'!
	|    '(' basictype ')' expr -> ^(CS_CAST basictype expr)
	;
addexpr: mulexpr (('+'|'-')^ mulexpr)* ;
mulexpr:	atom (('*'|'/'|'%')^ atom)*;
atom: CS_ID '.' CS_SWIZZLE -> ^(CS_SWIZ_READ CS_ID CS_SWIZZLE)
	|	CS_ID 
	| CS_CONST_INT 
	| CS_CONST_FLOAT 
	| CS_CONST_DOUBLE
	| CS_INPUT_BUF '[' expr ',' CS_CONST_INT ',' CS_CONST_INT ']' -> ^(CS_AREAD CS_INPUT_BUF expr CS_CONST_INT CS_CONST_INT)
	| CS_INPUT_BUF '[' expr ']' -> ^(CS_AREAD CS_INPUT_BUF expr)
	| CS_GLOB '[' expr ']' -> ^(CS_AREAD CS_GLOB expr)
	| CS_LDS '[' expr ']' -> ^(CS_AREAD CS_LDS expr)
	| CS_VATID '.' CS_SWIZZLE -> ^(CS_SWIZ_READ CS_VATID CS_SWIZZLE)
	| CS_VTID '.' CS_SWIZZLE -> ^(CS_SWIZ_READ CS_VTID CS_SWIZZLE)
	;
decl:	basictype CS_ID (',' CS_ID)* ';' -> ^(CS_DECL basictype CS_ID CS_ID*) 
	|   basictype CS_OUTPUT_BUF (',' CS_OUTPUT_BUF)* ';' -> ^(CS_DECL basictype CS_OUTPUT_BUF CS_OUTPUT_BUF*)
	|	atype CS_INPUT_BUF (',' CS_INPUT_BUF)* ';' -> ^(CS_DECL atype CS_INPUT_BUF CS_INPUT_BUF*)
	|   atype CS_GLOB ';' -> ^(CS_DECL atype CS_GLOB)
	|   ldstype CS_LDS ';' -> ^(CS_DECL ldstype CS_LDS)
	| 	CS_NUM_THREADS CS_CONST_INT ';' -> ^(CS_DECL CS_CONST_INT CS_NUM_THREADS)
	;   
basictype: CS_DT_FLOAT 
	| CS_DT_FLOAT2 
	| CS_DT_FLOAT4 
	| CS_DT_DOUBLE 
	| CS_DT_DOUBLE2 
	| CS_DT_INT 
	| CS_DT_INT2
	| CS_DT_INT4
	;
atype: basictype '[]' -> ^(CS_ATYPE basictype);
ldstype: basictype '[' CS_CONST_INT ']' -> ^(CS_LDS_TYPE basictype CS_CONST_INT);
CS_DT_FLOAT: 'float';
CS_DT_FLOAT2: 'float2';
CS_DT_FLOAT4: 'float4';
CS_DT_DOUBLE: 'double';
CS_DT_DOUBLE2: 'double2';
CS_DT_INT: 'int';
CS_DT_INT2: 'int2';
CS_DT_INT4: 'int4';
CS_VATID: 'vaTid';
CS_VTID: 'vTid';
CS_CONST_INT:	'0'..'9'+;
CS_CONST_FLOAT:	'0'..'9'+ '.' '0'..'9'* 'f';
CS_CONST_DOUBLE: '0'..'9'+ '.' '0'..'9'*;
CS_WHILE: 'while';
CS_FOR: 'for';
CS_IF: 'if';
CS_ELSE: 'else';
CS_SWIZZLE: ('x'|'y'|'z'|'w');
CS_INPUT_BUF: 'i0'|'i1'|'i2'|'i3'|'i4'|'i5'|'i6'|'i7';
CS_OUTPUT_BUF: 'o0'|'o1'|'o2'|'o3'|'o4'|'o5'|'o6'|'o7';
CS_GLOB: 'g';
CS_LDS: 'lds';
CS_FENCE_LDS: 'fence_lds';
CS_FENCE_MEM: 'fence_memory';
CS_FENCE_SR: 'fence_sr';
CS_FENCE_THREADS: 'fence_threads';
CS_NUM_THREADS: 'dcl_num_thread_per_grp';
CS_ID:	(('a'..'z')|('A'..'Z')) (('a'..'z')|('0'..'9')|'_'|('A'..'Z'))*;
CS_WS:	(' '|'\t'|'\n'|'\r')+ {$channel=HIDDEN;};
CS_EQ: '=';
CS_NEQ: '!=';
CS_LT: '<';
CS_GT: '>';
CS_GTE: '>=';
CS_LTE: '<=';
CS_PLUS: '+';
CS_MINUS: '-';
CS_MUL: '*';
CS_DIV: '/';
CS_MOD: '%';
