// $ANTLR 3.0.1 Python.g 2008-07-22 13:46:54

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


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class PythonLexer extends Lexer {
    public static final int FUNCTION=18;
    public static final int CLASS=30;
    public static final int STMT=10;
    public static final int PARFOR=53;
    public static final int CONST=9;
    public static final int CLTE=43;
    public static final int FOR=29;
    public static final int SUB=22;
    public static final int FLOAT=55;
    public static final int SLICE=31;
    public static final int ANNOT=5;
    public static final int ID=56;
    public static final int SUBSCRIPTS=34;
    public static final int EOF=-1;
    public static final int SLICEOBJ=35;
    public static final int GETATTR=25;
    public static final int IF=13;
    public static final int T62=62;
    public static final int T63=63;
    public static final int T64=64;
    public static final int T65=65;
    public static final int NAME=26;
    public static final int IMPORT=27;
    public static final int T66=66;
    public static final int T67=67;
    public static final int T68=68;
    public static final int COPY=44;
    public static final int CGT=38;
    public static final int RETURN=14;
    public static final int CFOR=45;
    public static final int COMPARE=36;
    public static final int DIGIT=58;
    public static final int GOTO=49;
    public static final int T61=61;
    public static final int CGTE=42;
    public static final int T60=60;
    public static final int ADD=21;
    public static final int CLT=39;
    public static final int CALLMETH=50;
    public static final int KEYWORD=37;
    public static final int CONVERT=52;
    public static final int CALLFUNC=19;
    public static final int CNEQ=41;
    public static final int RANGE=47;
    public static final int INT=54;
    public static final int ASSNAME=17;
    public static final int MODULE=7;
    public static final int Tokens=69;
    public static final int LIST=4;
    public static final int MUL=23;
    public static final int ALPHA=57;
    public static final int PYSTR=11;
    public static final int WS=59;
    public static final int DISCARD=12;
    public static final int ASSATTR=20;
    public static final int LABEL=48;
    public static final int SUBSCRIPT=32;
    public static final int SUBSCRIPTG=33;
    public static final int NONE=8;
    public static final int ASSIGN=16;
    public static final int DIM=51;
    public static final int CIF=46;
    public static final int CEQ=40;
    public static final int DIV=24;
    public static final int FROM=28;
    public static final int GLOBAL=15;
    public static final int STRING=6;
    public PythonLexer() {;} 
    public PythonLexer(CharStream input) {
        super(input);
    }
    public String getGrammarFileName() { return "Python.g"; }

    // $ANTLR start MODULE
    public final void mMODULE() throws RecognitionException {
        try {
            int _type = MODULE;
            // Python.g:24:8: ( 'Module' )
            // Python.g:24:10: 'Module'
            {
            match("Module"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end MODULE

    // $ANTLR start NONE
    public final void mNONE() throws RecognitionException {
        try {
            int _type = NONE;
            // Python.g:25:6: ( 'None' )
            // Python.g:25:8: 'None'
            {
            match("None"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end NONE

    // $ANTLR start CONST
    public final void mCONST() throws RecognitionException {
        try {
            int _type = CONST;
            // Python.g:26:7: ( 'Const' )
            // Python.g:26:9: 'Const'
            {
            match("Const"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end CONST

    // $ANTLR start STMT
    public final void mSTMT() throws RecognitionException {
        try {
            int _type = STMT;
            // Python.g:27:6: ( 'Stmt' )
            // Python.g:27:8: 'Stmt'
            {
            match("Stmt"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end STMT

    // $ANTLR start PYSTR
    public final void mPYSTR() throws RecognitionException {
        try {
            int _type = PYSTR;
            // Python.g:28:7: ( 'PyString' )
            // Python.g:28:9: 'PyString'
            {
            match("PyString"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end PYSTR

    // $ANTLR start DISCARD
    public final void mDISCARD() throws RecognitionException {
        try {
            int _type = DISCARD;
            // Python.g:29:9: ( 'Discard' )
            // Python.g:29:11: 'Discard'
            {
            match("Discard"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end DISCARD

    // $ANTLR start IF
    public final void mIF() throws RecognitionException {
        try {
            int _type = IF;
            // Python.g:30:4: ( 'If' )
            // Python.g:30:6: 'If'
            {
            match("If"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end IF

    // $ANTLR start RETURN
    public final void mRETURN() throws RecognitionException {
        try {
            int _type = RETURN;
            // Python.g:31:8: ( 'Return' )
            // Python.g:31:10: 'Return'
            {
            match("Return"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RETURN

    // $ANTLR start GLOBAL
    public final void mGLOBAL() throws RecognitionException {
        try {
            int _type = GLOBAL;
            // Python.g:32:8: ( 'Global' )
            // Python.g:32:10: 'Global'
            {
            match("Global"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end GLOBAL

    // $ANTLR start ASSIGN
    public final void mASSIGN() throws RecognitionException {
        try {
            int _type = ASSIGN;
            // Python.g:33:8: ( 'Assign' )
            // Python.g:33:10: 'Assign'
            {
            match("Assign"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end ASSIGN

    // $ANTLR start ASSNAME
    public final void mASSNAME() throws RecognitionException {
        try {
            int _type = ASSNAME;
            // Python.g:34:9: ( 'AssName' )
            // Python.g:34:11: 'AssName'
            {
            match("AssName"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end ASSNAME

    // $ANTLR start FUNCTION
    public final void mFUNCTION() throws RecognitionException {
        try {
            int _type = FUNCTION;
            // Python.g:35:10: ( 'Function' )
            // Python.g:35:12: 'Function'
            {
            match("Function"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end FUNCTION

    // $ANTLR start CALLFUNC
    public final void mCALLFUNC() throws RecognitionException {
        try {
            int _type = CALLFUNC;
            // Python.g:36:10: ( 'CallFunc' )
            // Python.g:36:12: 'CallFunc'
            {
            match("CallFunc"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end CALLFUNC

    // $ANTLR start ASSATTR
    public final void mASSATTR() throws RecognitionException {
        try {
            int _type = ASSATTR;
            // Python.g:37:9: ( 'AssAttr' )
            // Python.g:37:11: 'AssAttr'
            {
            match("AssAttr"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end ASSATTR

    // $ANTLR start ADD
    public final void mADD() throws RecognitionException {
        try {
            int _type = ADD;
            // Python.g:38:5: ( 'Add' )
            // Python.g:38:7: 'Add'
            {
            match("Add"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end ADD

    // $ANTLR start SUB
    public final void mSUB() throws RecognitionException {
        try {
            int _type = SUB;
            // Python.g:39:5: ( 'Sub' )
            // Python.g:39:7: 'Sub'
            {
            match("Sub"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end SUB

    // $ANTLR start MUL
    public final void mMUL() throws RecognitionException {
        try {
            int _type = MUL;
            // Python.g:40:5: ( 'Mul' )
            // Python.g:40:7: 'Mul'
            {
            match("Mul"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end MUL

    // $ANTLR start DIV
    public final void mDIV() throws RecognitionException {
        try {
            int _type = DIV;
            // Python.g:41:5: ( 'Div' )
            // Python.g:41:7: 'Div'
            {
            match("Div"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end DIV

    // $ANTLR start GETATTR
    public final void mGETATTR() throws RecognitionException {
        try {
            int _type = GETATTR;
            // Python.g:42:9: ( 'Getattr' )
            // Python.g:42:11: 'Getattr'
            {
            match("Getattr"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end GETATTR

    // $ANTLR start NAME
    public final void mNAME() throws RecognitionException {
        try {
            int _type = NAME;
            // Python.g:43:6: ( 'Name' )
            // Python.g:43:8: 'Name'
            {
            match("Name"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end NAME

    // $ANTLR start IMPORT
    public final void mIMPORT() throws RecognitionException {
        try {
            int _type = IMPORT;
            // Python.g:44:8: ( 'Import' )
            // Python.g:44:10: 'Import'
            {
            match("Import"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end IMPORT

    // $ANTLR start FROM
    public final void mFROM() throws RecognitionException {
        try {
            int _type = FROM;
            // Python.g:45:6: ( 'From' )
            // Python.g:45:8: 'From'
            {
            match("From"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end FROM

    // $ANTLR start FOR
    public final void mFOR() throws RecognitionException {
        try {
            int _type = FOR;
            // Python.g:46:5: ( 'For' )
            // Python.g:46:7: 'For'
            {
            match("For"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end FOR

    // $ANTLR start CLASS
    public final void mCLASS() throws RecognitionException {
        try {
            int _type = CLASS;
            // Python.g:47:7: ( 'UClass' )
            // Python.g:47:9: 'UClass'
            {
            match("UClass"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end CLASS

    // $ANTLR start SLICE
    public final void mSLICE() throws RecognitionException {
        try {
            int _type = SLICE;
            // Python.g:48:7: ( 'Slice' )
            // Python.g:48:9: 'Slice'
            {
            match("Slice"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end SLICE

    // $ANTLR start SUBSCRIPT
    public final void mSUBSCRIPT() throws RecognitionException {
        try {
            int _type = SUBSCRIPT;
            // Python.g:49:11: ( 'Subscript' )
            // Python.g:49:13: 'Subscript'
            {
            match("Subscript"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end SUBSCRIPT

    // $ANTLR start SLICEOBJ
    public final void mSLICEOBJ() throws RecognitionException {
        try {
            int _type = SLICEOBJ;
            // Python.g:50:10: ( 'Sliceobj' )
            // Python.g:50:12: 'Sliceobj'
            {
            match("Sliceobj"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end SLICEOBJ

    // $ANTLR start COMPARE
    public final void mCOMPARE() throws RecognitionException {
        try {
            int _type = COMPARE;
            // Python.g:51:9: ( 'Compare' )
            // Python.g:51:11: 'Compare'
            {
            match("Compare"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end COMPARE

    // $ANTLR start KEYWORD
    public final void mKEYWORD() throws RecognitionException {
        try {
            int _type = KEYWORD;
            // Python.g:52:9: ( 'Keyword' )
            // Python.g:52:11: 'Keyword'
            {
            match("Keyword"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end KEYWORD

    // $ANTLR start CGT
    public final void mCGT() throws RecognitionException {
        try {
            int _type = CGT;
            // Python.g:53:5: ( '\\'>\\'' )
            // Python.g:53:7: '\\'>\\''
            {
            match("\'>\'"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end CGT

    // $ANTLR start CLT
    public final void mCLT() throws RecognitionException {
        try {
            int _type = CLT;
            // Python.g:54:5: ( '\\'<\\'' )
            // Python.g:54:7: '\\'<\\''
            {
            match("\'<\'"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end CLT

    // $ANTLR start CEQ
    public final void mCEQ() throws RecognitionException {
        try {
            int _type = CEQ;
            // Python.g:55:5: ( '\\'==\\'' )
            // Python.g:55:7: '\\'==\\''
            {
            match("\'==\'"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end CEQ

    // $ANTLR start CNEQ
    public final void mCNEQ() throws RecognitionException {
        try {
            int _type = CNEQ;
            // Python.g:56:6: ( '\\'!=\\'' )
            // Python.g:56:8: '\\'!=\\''
            {
            match("\'!=\'"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end CNEQ

    // $ANTLR start CGTE
    public final void mCGTE() throws RecognitionException {
        try {
            int _type = CGTE;
            // Python.g:57:6: ( '\\'>=\\'' )
            // Python.g:57:8: '\\'>=\\''
            {
            match("\'>=\'"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end CGTE

    // $ANTLR start CLTE
    public final void mCLTE() throws RecognitionException {
        try {
            int _type = CLTE;
            // Python.g:58:6: ( '\\'<=\\'' )
            // Python.g:58:8: '\\'<=\\''
            {
            match("\'<=\'"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end CLTE

    // $ANTLR start T60
    public final void mT60() throws RecognitionException {
        try {
            int _type = T60;
            // Python.g:59:5: ( '(' )
            // Python.g:59:7: '('
            {
            match('('); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T60

    // $ANTLR start T61
    public final void mT61() throws RecognitionException {
        try {
            int _type = T61;
            // Python.g:60:5: ( ')' )
            // Python.g:60:7: ')'
            {
            match(')'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T61

    // $ANTLR start T62
    public final void mT62() throws RecognitionException {
        try {
            int _type = T62;
            // Python.g:61:5: ( '\\'' )
            // Python.g:61:7: '\\''
            {
            match('\''); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T62

    // $ANTLR start T63
    public final void mT63() throws RecognitionException {
        try {
            int _type = T63;
            // Python.g:62:5: ( '[' )
            // Python.g:62:7: '['
            {
            match('['); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T63

    // $ANTLR start T64
    public final void mT64() throws RecognitionException {
        try {
            int _type = T64;
            // Python.g:63:5: ( ']' )
            // Python.g:63:7: ']'
            {
            match(']'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T64

    // $ANTLR start T65
    public final void mT65() throws RecognitionException {
        try {
            int _type = T65;
            // Python.g:64:5: ( 'Annot' )
            // Python.g:64:7: 'Annot'
            {
            match("Annot"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T65

    // $ANTLR start T66
    public final void mT66() throws RecognitionException {
        try {
            int _type = T66;
            // Python.g:65:5: ( 'Decorators' )
            // Python.g:65:7: 'Decorators'
            {
            match("Decorators"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T66

    // $ANTLR start T67
    public final void mT67() throws RecognitionException {
        try {
            int _type = T67;
            // Python.g:66:5: ( '\\'OP_ASSIGN\\'' )
            // Python.g:66:7: '\\'OP_ASSIGN\\''
            {
            match("\'OP_ASSIGN\'"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T67

    // $ANTLR start T68
    public final void mT68() throws RecognitionException {
        try {
            int _type = T68;
            // Python.g:67:5: ( '\\'OP_APPLY\\'' )
            // Python.g:67:7: '\\'OP_APPLY\\''
            {
            match("\'OP_APPLY\'"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T68

    // $ANTLR start ALPHA
    public final void mALPHA() throws RecognitionException {
        try {
            // Python.g:269:8: ( ( 'a' .. 'z' ) | ( 'A' .. 'Z' ) | '_' )
            int alt1=3;
            switch ( input.LA(1) ) {
            case 'a':
            case 'b':
            case 'c':
            case 'd':
            case 'e':
            case 'f':
            case 'g':
            case 'h':
            case 'i':
            case 'j':
            case 'k':
            case 'l':
            case 'm':
            case 'n':
            case 'o':
            case 'p':
            case 'q':
            case 'r':
            case 's':
            case 't':
            case 'u':
            case 'v':
            case 'w':
            case 'x':
            case 'y':
            case 'z':
                {
                alt1=1;
                }
                break;
            case 'A':
            case 'B':
            case 'C':
            case 'D':
            case 'E':
            case 'F':
            case 'G':
            case 'H':
            case 'I':
            case 'J':
            case 'K':
            case 'L':
            case 'M':
            case 'N':
            case 'O':
            case 'P':
            case 'Q':
            case 'R':
            case 'S':
            case 'T':
            case 'U':
            case 'V':
            case 'W':
            case 'X':
            case 'Y':
            case 'Z':
                {
                alt1=2;
                }
                break;
            case '_':
                {
                alt1=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("268:1: fragment ALPHA : ( ( 'a' .. 'z' ) | ( 'A' .. 'Z' ) | '_' );", 1, 0, input);

                throw nvae;
            }

            switch (alt1) {
                case 1 :
                    // Python.g:269:10: ( 'a' .. 'z' )
                    {
                    // Python.g:269:10: ( 'a' .. 'z' )
                    // Python.g:269:11: 'a' .. 'z'
                    {
                    matchRange('a','z'); 

                    }


                    }
                    break;
                case 2 :
                    // Python.g:269:21: ( 'A' .. 'Z' )
                    {
                    // Python.g:269:21: ( 'A' .. 'Z' )
                    // Python.g:269:22: 'A' .. 'Z'
                    {
                    matchRange('A','Z'); 

                    }


                    }
                    break;
                case 3 :
                    // Python.g:269:32: '_'
                    {
                    match('_'); 

                    }
                    break;

            }
        }
        finally {
        }
    }
    // $ANTLR end ALPHA

    // $ANTLR start DIGIT
    public final void mDIGIT() throws RecognitionException {
        try {
            // Python.g:272:7: ( '0' .. '9' )
            // Python.g:272:9: '0' .. '9'
            {
            matchRange('0','9'); 

            }

        }
        finally {
        }
    }
    // $ANTLR end DIGIT

    // $ANTLR start ID
    public final void mID() throws RecognitionException {
        try {
            int _type = ID;
            // Python.g:274:4: ( ALPHA ( ALPHA | DIGIT )* )
            // Python.g:274:6: ALPHA ( ALPHA | DIGIT )*
            {
            mALPHA(); 
            // Python.g:274:12: ( ALPHA | DIGIT )*
            loop2:
            do {
                int alt2=3;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='A' && LA2_0<='Z')||LA2_0=='_'||(LA2_0>='a' && LA2_0<='z')) ) {
                    alt2=1;
                }
                else if ( ((LA2_0>='0' && LA2_0<='9')) ) {
                    alt2=2;
                }


                switch (alt2) {
            	case 1 :
            	    // Python.g:274:13: ALPHA
            	    {
            	    mALPHA(); 

            	    }
            	    break;
            	case 2 :
            	    // Python.g:274:19: DIGIT
            	    {
            	    mDIGIT(); 

            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end ID

    // $ANTLR start INT
    public final void mINT() throws RecognitionException {
        try {
            int _type = INT;
            // Python.g:275:6: ( ( '+' | '-' )? ( DIGIT )+ )
            // Python.g:275:8: ( '+' | '-' )? ( DIGIT )+
            {
            // Python.g:275:8: ( '+' | '-' )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0=='+'||LA3_0=='-') ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // Python.g:
                    {
                    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse =
                            new MismatchedSetException(null,input);
                        recover(mse);    throw mse;
                    }


                    }
                    break;

            }

            // Python.g:275:19: ( DIGIT )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>='0' && LA4_0<='9')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // Python.g:275:19: DIGIT
            	    {
            	    mDIGIT(); 

            	    }
            	    break;

            	default :
            	    if ( cnt4 >= 1 ) break loop4;
                        EarlyExitException eee =
                            new EarlyExitException(4, input);
                        throw eee;
                }
                cnt4++;
            } while (true);


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end INT

    // $ANTLR start FLOAT
    public final void mFLOAT() throws RecognitionException {
        try {
            int _type = FLOAT;
            // Python.g:276:8: ( ( '+' | '-' )? ( DIGIT )* '.' ( DIGIT )* ( 'e' INT )? | ( '+' | '-' )? ( '0' .. '9' )* 'e' INT )
            int alt11=2;
            alt11 = dfa11.predict(input);
            switch (alt11) {
                case 1 :
                    // Python.g:276:10: ( '+' | '-' )? ( DIGIT )* '.' ( DIGIT )* ( 'e' INT )?
                    {
                    // Python.g:276:10: ( '+' | '-' )?
                    int alt5=2;
                    int LA5_0 = input.LA(1);

                    if ( (LA5_0=='+'||LA5_0=='-') ) {
                        alt5=1;
                    }
                    switch (alt5) {
                        case 1 :
                            // Python.g:
                            {
                            if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                                input.consume();

                            }
                            else {
                                MismatchedSetException mse =
                                    new MismatchedSetException(null,input);
                                recover(mse);    throw mse;
                            }


                            }
                            break;

                    }

                    // Python.g:276:21: ( DIGIT )*
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( ((LA6_0>='0' && LA6_0<='9')) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // Python.g:276:21: DIGIT
                    	    {
                    	    mDIGIT(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop6;
                        }
                    } while (true);

                    match('.'); 
                    // Python.g:276:32: ( DIGIT )*
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( ((LA7_0>='0' && LA7_0<='9')) ) {
                            alt7=1;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // Python.g:276:32: DIGIT
                    	    {
                    	    mDIGIT(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop7;
                        }
                    } while (true);

                    // Python.g:276:39: ( 'e' INT )?
                    int alt8=2;
                    int LA8_0 = input.LA(1);

                    if ( (LA8_0=='e') ) {
                        alt8=1;
                    }
                    switch (alt8) {
                        case 1 :
                            // Python.g:276:40: 'e' INT
                            {
                            match('e'); 
                            mINT(); 

                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // Python.g:277:4: ( '+' | '-' )? ( '0' .. '9' )* 'e' INT
                    {
                    // Python.g:277:4: ( '+' | '-' )?
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( (LA9_0=='+'||LA9_0=='-') ) {
                        alt9=1;
                    }
                    switch (alt9) {
                        case 1 :
                            // Python.g:
                            {
                            if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                                input.consume();

                            }
                            else {
                                MismatchedSetException mse =
                                    new MismatchedSetException(null,input);
                                recover(mse);    throw mse;
                            }


                            }
                            break;

                    }

                    // Python.g:277:15: ( '0' .. '9' )*
                    loop10:
                    do {
                        int alt10=2;
                        int LA10_0 = input.LA(1);

                        if ( ((LA10_0>='0' && LA10_0<='9')) ) {
                            alt10=1;
                        }


                        switch (alt10) {
                    	case 1 :
                    	    // Python.g:277:16: '0' .. '9'
                    	    {
                    	    matchRange('0','9'); 

                    	    }
                    	    break;

                    	default :
                    	    break loop10;
                        }
                    } while (true);

                    match('e'); 
                    mINT(); 

                    }
                    break;

            }
            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end FLOAT

    // $ANTLR start WS
    public final void mWS() throws RecognitionException {
        try {
            int _type = WS;
            // Python.g:279:5: ( ( ' ' | '\\t' | ',' | '\\n' | '\\r' )+ )
            // Python.g:279:7: ( ' ' | '\\t' | ',' | '\\n' | '\\r' )+
            {
            // Python.g:279:7: ( ' ' | '\\t' | ',' | '\\n' | '\\r' )+
            int cnt12=0;
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( ((LA12_0>='\t' && LA12_0<='\n')||LA12_0=='\r'||LA12_0==' '||LA12_0==',') ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // Python.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' '||input.LA(1)==',' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt12 >= 1 ) break loop12;
                        EarlyExitException eee =
                            new EarlyExitException(12, input);
                        throw eee;
                }
                cnt12++;
            } while (true);

            skip();

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end WS

    public void mTokens() throws RecognitionException {
        // Python.g:1:8: ( MODULE | NONE | CONST | STMT | PYSTR | DISCARD | IF | RETURN | GLOBAL | ASSIGN | ASSNAME | FUNCTION | CALLFUNC | ASSATTR | ADD | SUB | MUL | DIV | GETATTR | NAME | IMPORT | FROM | FOR | CLASS | SLICE | SUBSCRIPT | SLICEOBJ | COMPARE | KEYWORD | CGT | CLT | CEQ | CNEQ | CGTE | CLTE | T60 | T61 | T62 | T63 | T64 | T65 | T66 | T67 | T68 | ID | INT | FLOAT | WS )
        int alt13=48;
        alt13 = dfa13.predict(input);
        switch (alt13) {
            case 1 :
                // Python.g:1:10: MODULE
                {
                mMODULE(); 

                }
                break;
            case 2 :
                // Python.g:1:17: NONE
                {
                mNONE(); 

                }
                break;
            case 3 :
                // Python.g:1:22: CONST
                {
                mCONST(); 

                }
                break;
            case 4 :
                // Python.g:1:28: STMT
                {
                mSTMT(); 

                }
                break;
            case 5 :
                // Python.g:1:33: PYSTR
                {
                mPYSTR(); 

                }
                break;
            case 6 :
                // Python.g:1:39: DISCARD
                {
                mDISCARD(); 

                }
                break;
            case 7 :
                // Python.g:1:47: IF
                {
                mIF(); 

                }
                break;
            case 8 :
                // Python.g:1:50: RETURN
                {
                mRETURN(); 

                }
                break;
            case 9 :
                // Python.g:1:57: GLOBAL
                {
                mGLOBAL(); 

                }
                break;
            case 10 :
                // Python.g:1:64: ASSIGN
                {
                mASSIGN(); 

                }
                break;
            case 11 :
                // Python.g:1:71: ASSNAME
                {
                mASSNAME(); 

                }
                break;
            case 12 :
                // Python.g:1:79: FUNCTION
                {
                mFUNCTION(); 

                }
                break;
            case 13 :
                // Python.g:1:88: CALLFUNC
                {
                mCALLFUNC(); 

                }
                break;
            case 14 :
                // Python.g:1:97: ASSATTR
                {
                mASSATTR(); 

                }
                break;
            case 15 :
                // Python.g:1:105: ADD
                {
                mADD(); 

                }
                break;
            case 16 :
                // Python.g:1:109: SUB
                {
                mSUB(); 

                }
                break;
            case 17 :
                // Python.g:1:113: MUL
                {
                mMUL(); 

                }
                break;
            case 18 :
                // Python.g:1:117: DIV
                {
                mDIV(); 

                }
                break;
            case 19 :
                // Python.g:1:121: GETATTR
                {
                mGETATTR(); 

                }
                break;
            case 20 :
                // Python.g:1:129: NAME
                {
                mNAME(); 

                }
                break;
            case 21 :
                // Python.g:1:134: IMPORT
                {
                mIMPORT(); 

                }
                break;
            case 22 :
                // Python.g:1:141: FROM
                {
                mFROM(); 

                }
                break;
            case 23 :
                // Python.g:1:146: FOR
                {
                mFOR(); 

                }
                break;
            case 24 :
                // Python.g:1:150: CLASS
                {
                mCLASS(); 

                }
                break;
            case 25 :
                // Python.g:1:156: SLICE
                {
                mSLICE(); 

                }
                break;
            case 26 :
                // Python.g:1:162: SUBSCRIPT
                {
                mSUBSCRIPT(); 

                }
                break;
            case 27 :
                // Python.g:1:172: SLICEOBJ
                {
                mSLICEOBJ(); 

                }
                break;
            case 28 :
                // Python.g:1:181: COMPARE
                {
                mCOMPARE(); 

                }
                break;
            case 29 :
                // Python.g:1:189: KEYWORD
                {
                mKEYWORD(); 

                }
                break;
            case 30 :
                // Python.g:1:197: CGT
                {
                mCGT(); 

                }
                break;
            case 31 :
                // Python.g:1:201: CLT
                {
                mCLT(); 

                }
                break;
            case 32 :
                // Python.g:1:205: CEQ
                {
                mCEQ(); 

                }
                break;
            case 33 :
                // Python.g:1:209: CNEQ
                {
                mCNEQ(); 

                }
                break;
            case 34 :
                // Python.g:1:214: CGTE
                {
                mCGTE(); 

                }
                break;
            case 35 :
                // Python.g:1:219: CLTE
                {
                mCLTE(); 

                }
                break;
            case 36 :
                // Python.g:1:224: T60
                {
                mT60(); 

                }
                break;
            case 37 :
                // Python.g:1:228: T61
                {
                mT61(); 

                }
                break;
            case 38 :
                // Python.g:1:232: T62
                {
                mT62(); 

                }
                break;
            case 39 :
                // Python.g:1:236: T63
                {
                mT63(); 

                }
                break;
            case 40 :
                // Python.g:1:240: T64
                {
                mT64(); 

                }
                break;
            case 41 :
                // Python.g:1:244: T65
                {
                mT65(); 

                }
                break;
            case 42 :
                // Python.g:1:248: T66
                {
                mT66(); 

                }
                break;
            case 43 :
                // Python.g:1:252: T67
                {
                mT67(); 

                }
                break;
            case 44 :
                // Python.g:1:256: T68
                {
                mT68(); 

                }
                break;
            case 45 :
                // Python.g:1:260: ID
                {
                mID(); 

                }
                break;
            case 46 :
                // Python.g:1:263: INT
                {
                mINT(); 

                }
                break;
            case 47 :
                // Python.g:1:267: FLOAT
                {
                mFLOAT(); 

                }
                break;
            case 48 :
                // Python.g:1:273: WS
                {
                mWS(); 

                }
                break;

        }

    }


    protected DFA11 dfa11 = new DFA11(this);
    protected DFA13 dfa13 = new DFA13(this);
    static final String DFA11_eotS =
        "\5\uffff";
    static final String DFA11_eofS =
        "\5\uffff";
    static final String DFA11_minS =
        "\1\53\2\56\2\uffff";
    static final String DFA11_maxS =
        "\3\145\2\uffff";
    static final String DFA11_acceptS =
        "\3\uffff\1\1\1\2";
    static final String DFA11_specialS =
        "\5\uffff}>";
    static final String[] DFA11_transitionS = {
            "\1\1\1\uffff\1\1\1\3\1\uffff\12\2\53\uffff\1\4",
            "\1\3\1\uffff\12\2\53\uffff\1\4",
            "\1\3\1\uffff\12\2\53\uffff\1\4",
            "",
            ""
    };

    static final short[] DFA11_eot = DFA.unpackEncodedString(DFA11_eotS);
    static final short[] DFA11_eof = DFA.unpackEncodedString(DFA11_eofS);
    static final char[] DFA11_min = DFA.unpackEncodedStringToUnsignedChars(DFA11_minS);
    static final char[] DFA11_max = DFA.unpackEncodedStringToUnsignedChars(DFA11_maxS);
    static final short[] DFA11_accept = DFA.unpackEncodedString(DFA11_acceptS);
    static final short[] DFA11_special = DFA.unpackEncodedString(DFA11_specialS);
    static final short[][] DFA11_transition;

    static {
        int numStates = DFA11_transitionS.length;
        DFA11_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA11_transition[i] = DFA.unpackEncodedString(DFA11_transitionS[i]);
        }
    }

    class DFA11 extends DFA {

        public DFA11(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 11;
            this.eot = DFA11_eot;
            this.eof = DFA11_eof;
            this.min = DFA11_min;
            this.max = DFA11_max;
            this.accept = DFA11_accept;
            this.special = DFA11_special;
            this.transition = DFA11_transition;
        }
        public String getDescription() {
            return "276:1: FLOAT : ( ( '+' | '-' )? ( DIGIT )* '.' ( DIGIT )* ( 'e' INT )? | ( '+' | '-' )? ( '0' .. '9' )* 'e' INT );";
        }
    }
    static final String DFA13_eotS =
        "\1\uffff\15\24\1\67\4\uffff\1\24\2\uffff\1\71\2\uffff\14\24\1\110"+
        "\14\24\6\uffff\1\24\1\uffff\1\24\1\133\6\24\1\143\2\24\1\146\2\24"+
        "\1\uffff\5\24\1\160\3\24\1\164\2\24\5\uffff\1\24\1\uffff\1\171\1"+
        "\172\3\24\1\176\1\24\1\uffff\2\24\1\uffff\11\24\1\uffff\2\24\1\u008d"+
        "\1\uffff\2\24\1\uffff\1\24\2\uffff\1\24\1\u0093\1\24\1\uffff\1\24"+
        "\1\u0097\12\24\1\u00a2\1\24\1\uffff\2\24\1\uffff\1\u00a8\1\24\1"+
        "\uffff\3\24\1\uffff\3\24\1\u00b0\1\u00b1\1\u00b2\2\24\1\u00b5\1"+
        "\24\1\uffff\1\24\1\u00b8\1\24\3\uffff\1\u00ba\4\24\1\u00bf\1\24"+
        "\3\uffff\1\u00c1\1\u00c2\1\uffff\1\u00c3\1\24\1\uffff\1\u00c5\1"+
        "\uffff\1\u00c6\1\24\1\u00c8\1\u00c9\1\uffff\1\24\3\uffff\1\u00cb"+
        "\2\uffff\1\u00cc\2\uffff\1\24\2\uffff\1\u00ce\1\uffff";
    static final String DFA13_eofS =
        "\u00cf\uffff";
    static final String DFA13_minS =
        "\1\11\1\157\2\141\1\154\1\171\1\145\1\146\2\145\1\144\1\157\1\103"+
        "\1\145\1\41\4\uffff\1\53\1\uffff\2\56\2\uffff\1\144\1\154\1\156"+
        "\2\155\1\154\1\155\1\142\1\151\1\123\1\163\1\143\1\60\1\160\1\164"+
        "\1\157\1\164\1\163\1\144\2\156\1\157\1\162\1\154\1\171\1\120\2\47"+
        "\3\uffff\1\60\1\uffff\1\165\1\60\2\145\1\160\1\163\1\154\1\164\1"+
        "\60\1\143\1\164\1\60\1\143\1\157\1\uffff\1\157\1\165\1\142\1\141"+
        "\1\101\1\60\1\157\1\143\1\155\1\60\1\141\1\167\1\137\4\uffff\1\154"+
        "\1\uffff\2\60\1\141\1\164\1\106\1\60\1\143\1\uffff\1\145\1\162\1"+
        "\uffff\1\141\3\162\1\141\2\164\1\147\1\141\1\uffff\2\164\1\60\1"+
        "\uffff\1\163\1\157\1\101\1\145\2\uffff\1\162\1\60\1\165\1\uffff"+
        "\1\162\1\60\1\151\1\162\1\141\1\164\1\156\1\154\2\164\1\156\1\155"+
        "\1\60\1\151\1\uffff\1\163\1\162\1\120\1\60\1\145\1\uffff\1\156\1"+
        "\151\1\142\1\uffff\1\156\1\144\1\164\3\60\2\162\1\60\1\145\1\uffff"+
        "\1\157\1\60\1\144\3\uffff\1\60\1\143\1\160\1\152\1\147\1\60\1\157"+
        "\3\uffff\2\60\1\uffff\1\60\1\156\1\uffff\1\60\1\uffff\1\60\1\164"+
        "\2\60\1\uffff\1\162\3\uffff\1\60\2\uffff\1\60\2\uffff\1\163\2\uffff"+
        "\1\60\1\uffff";
    static final String DFA13_maxS =
        "\1\172\1\165\2\157\1\165\1\171\1\151\1\155\1\145\1\154\1\163\1\165"+
        "\1\103\1\145\1\117\4\uffff\1\71\1\uffff\2\145\2\uffff\1\144\1\154"+
        "\1\156\1\155\1\156\1\154\1\155\1\142\1\151\1\123\1\166\1\143\1\172"+
        "\1\160\1\164\1\157\1\164\1\163\1\144\2\156\1\157\1\162\1\154\1\171"+
        "\1\120\2\75\3\uffff\1\71\1\uffff\1\165\1\172\2\145\1\160\1\163\1"+
        "\154\1\164\1\172\1\143\1\164\1\172\1\143\1\157\1\uffff\1\157\1\165"+
        "\1\142\1\141\1\151\1\172\1\157\1\143\1\155\1\172\1\141\1\167\1\137"+
        "\4\uffff\1\154\1\uffff\2\172\1\141\1\164\1\106\1\172\1\143\1\uffff"+
        "\1\145\1\162\1\uffff\1\141\3\162\1\141\2\164\1\147\1\141\1\uffff"+
        "\2\164\1\172\1\uffff\1\163\1\157\1\101\1\145\2\uffff\1\162\1\172"+
        "\1\165\1\uffff\1\162\1\172\1\151\1\162\1\141\1\164\1\156\1\154\2"+
        "\164\1\156\1\155\1\172\1\151\1\uffff\1\163\1\162\1\123\1\172\1\145"+
        "\1\uffff\1\156\1\151\1\142\1\uffff\1\156\1\144\1\164\3\172\2\162"+
        "\1\172\1\145\1\uffff\1\157\1\172\1\144\3\uffff\1\172\1\143\1\160"+
        "\1\152\1\147\1\172\1\157\3\uffff\2\172\1\uffff\1\172\1\156\1\uffff"+
        "\1\172\1\uffff\1\172\1\164\2\172\1\uffff\1\162\3\uffff\1\172\2\uffff"+
        "\1\172\2\uffff\1\163\2\uffff\1\172\1\uffff";
    static final String DFA13_acceptS =
        "\17\uffff\1\44\1\45\1\47\1\50\1\uffff\1\55\2\uffff\1\57\1\60\34"+
        "\uffff\1\41\1\40\1\46\1\uffff\1\56\16\uffff\1\7\15\uffff\1\43\1"+
        "\37\1\42\1\36\1\uffff\1\21\7\uffff\1\20\2\uffff\1\22\11\uffff\1"+
        "\17\3\uffff\1\27\4\uffff\1\2\1\24\3\uffff\1\4\16\uffff\1\26\5\uffff"+
        "\1\3\3\uffff\1\31\12\uffff\1\51\3\uffff\1\54\1\53\1\1\7\uffff\1"+
        "\25\1\10\1\11\2\uffff\1\12\2\uffff\1\30\1\uffff\1\34\4\uffff\1\6"+
        "\1\uffff\1\23\1\16\1\13\1\uffff\1\35\1\15\1\uffff\1\33\1\5\1\uffff"+
        "\1\14\1\32\1\uffff\1\52";
    static final String DFA13_specialS =
        "\u00cf\uffff}>";
    static final String[] DFA13_transitionS = {
            "\2\30\2\uffff\1\30\22\uffff\1\30\6\uffff\1\16\1\17\1\20\1\uffff"+
            "\1\25\1\30\1\25\1\27\1\uffff\12\26\7\uffff\1\12\1\24\1\3\1\6"+
            "\1\24\1\13\1\11\1\24\1\7\1\24\1\15\1\24\1\1\1\2\1\24\1\5\1\24"+
            "\1\10\1\4\1\24\1\14\5\24\1\21\1\uffff\1\22\1\uffff\1\24\1\uffff"+
            "\4\24\1\23\25\24",
            "\1\31\5\uffff\1\32",
            "\1\34\15\uffff\1\33",
            "\1\36\15\uffff\1\35",
            "\1\41\7\uffff\1\37\1\40",
            "\1\42",
            "\1\44\3\uffff\1\43",
            "\1\45\6\uffff\1\46",
            "\1\47",
            "\1\51\6\uffff\1\50",
            "\1\53\11\uffff\1\54\4\uffff\1\52",
            "\1\57\2\uffff\1\56\2\uffff\1\55",
            "\1\60",
            "\1\61",
            "\1\65\32\uffff\1\63\1\66\1\64\20\uffff\1\62",
            "",
            "",
            "",
            "",
            "\1\27\1\uffff\1\27\2\uffff\12\70",
            "",
            "\1\27\1\uffff\12\26\53\uffff\1\27",
            "\1\27\1\uffff\12\26\53\uffff\1\27",
            "",
            "",
            "\1\72",
            "\1\73",
            "\1\74",
            "\1\75",
            "\1\76\1\77",
            "\1\100",
            "\1\101",
            "\1\102",
            "\1\103",
            "\1\104",
            "\1\106\2\uffff\1\105",
            "\1\107",
            "\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
            "\1\111",
            "\1\112",
            "\1\113",
            "\1\114",
            "\1\115",
            "\1\116",
            "\1\117",
            "\1\120",
            "\1\121",
            "\1\122",
            "\1\123",
            "\1\124",
            "\1\125",
            "\1\127\25\uffff\1\126",
            "\1\131\25\uffff\1\130",
            "",
            "",
            "",
            "\12\70",
            "",
            "\1\132",
            "\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
            "\1\134",
            "\1\135",
            "\1\136",
            "\1\137",
            "\1\140",
            "\1\141",
            "\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\22\24\1\142\7\24",
            "\1\144",
            "\1\145",
            "\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
            "\1\147",
            "\1\150",
            "",
            "\1\151",
            "\1\152",
            "\1\153",
            "\1\154",
            "\1\155\14\uffff\1\157\32\uffff\1\156",
            "\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
            "\1\161",
            "\1\162",
            "\1\163",
            "\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
            "\1\165",
            "\1\166",
            "\1\167",
            "",
            "",
            "",
            "",
            "\1\170",
            "",
            "\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
            "\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
            "\1\173",
            "\1\174",
            "\1\175",
            "\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
            "\1\177",
            "",
            "\1\u0080",
            "\1\u0081",
            "",
            "\1\u0082",
            "\1\u0083",
            "\1\u0084",
            "\1\u0085",
            "\1\u0086",
            "\1\u0087",
            "\1\u0088",
            "\1\u0089",
            "\1\u008a",
            "",
            "\1\u008b",
            "\1\u008c",
            "\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
            "",
            "\1\u008e",
            "\1\u008f",
            "\1\u0090",
            "\1\u0091",
            "",
            "",
            "\1\u0092",
            "\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
            "\1\u0094",
            "",
            "\1\u0095",
            "\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\16\24\1\u0096\13\24",
            "\1\u0098",
            "\1\u0099",
            "\1\u009a",
            "\1\u009b",
            "\1\u009c",
            "\1\u009d",
            "\1\u009e",
            "\1\u009f",
            "\1\u00a0",
            "\1\u00a1",
            "\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
            "\1\u00a3",
            "",
            "\1\u00a4",
            "\1\u00a5",
            "\1\u00a6\2\uffff\1\u00a7",
            "\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
            "\1\u00a9",
            "",
            "\1\u00aa",
            "\1\u00ab",
            "\1\u00ac",
            "",
            "\1\u00ad",
            "\1\u00ae",
            "\1\u00af",
            "\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
            "\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
            "\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
            "\1\u00b3",
            "\1\u00b4",
            "\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
            "\1\u00b6",
            "",
            "\1\u00b7",
            "\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
            "\1\u00b9",
            "",
            "",
            "",
            "\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
            "\1\u00bb",
            "\1\u00bc",
            "\1\u00bd",
            "\1\u00be",
            "\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
            "\1\u00c0",
            "",
            "",
            "",
            "\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
            "\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
            "",
            "\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
            "\1\u00c4",
            "",
            "\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
            "",
            "\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
            "\1\u00c7",
            "\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
            "\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
            "",
            "\1\u00ca",
            "",
            "",
            "",
            "\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
            "",
            "",
            "\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
            "",
            "",
            "\1\u00cd",
            "",
            "",
            "\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
            ""
    };

    static final short[] DFA13_eot = DFA.unpackEncodedString(DFA13_eotS);
    static final short[] DFA13_eof = DFA.unpackEncodedString(DFA13_eofS);
    static final char[] DFA13_min = DFA.unpackEncodedStringToUnsignedChars(DFA13_minS);
    static final char[] DFA13_max = DFA.unpackEncodedStringToUnsignedChars(DFA13_maxS);
    static final short[] DFA13_accept = DFA.unpackEncodedString(DFA13_acceptS);
    static final short[] DFA13_special = DFA.unpackEncodedString(DFA13_specialS);
    static final short[][] DFA13_transition;

    static {
        int numStates = DFA13_transitionS.length;
        DFA13_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA13_transition[i] = DFA.unpackEncodedString(DFA13_transitionS[i]);
        }
    }

    class DFA13 extends DFA {

        public DFA13(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 13;
            this.eot = DFA13_eot;
            this.eof = DFA13_eof;
            this.min = DFA13_min;
            this.max = DFA13_max;
            this.accept = DFA13_accept;
            this.special = DFA13_special;
            this.transition = DFA13_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( MODULE | NONE | CONST | STMT | PYSTR | DISCARD | IF | RETURN | GLOBAL | ASSIGN | ASSNAME | FUNCTION | CALLFUNC | ASSATTR | ADD | SUB | MUL | DIV | GETATTR | NAME | IMPORT | FROM | FOR | CLASS | SLICE | SUBSCRIPT | SLICEOBJ | COMPARE | KEYWORD | CGT | CLT | CEQ | CNEQ | CGTE | CLTE | T60 | T61 | T62 | T63 | T64 | T65 | T66 | T67 | T68 | ID | INT | FLOAT | WS );";
        }
    }
 

}