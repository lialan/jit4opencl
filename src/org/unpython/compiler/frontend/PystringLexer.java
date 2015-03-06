// $ANTLR 3.0.1 ../src/org/unpython/compiler/frontend/Pystring.g 2008-05-27 19:57:43

package org.unpython.compiler.frontend;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class PystringLexer extends Lexer {
    public static final int PREFIX=4;
    public static final int T6=6;
    public static final int T7=7;
    public static final int T10=10;
    public static final int T8=8;
    public static final int T9=9;
    public static final int Tokens=11;
    public static final int EOF=-1;
    public static final int ALPHA=5;
    public PystringLexer() {;} 
    public PystringLexer(CharStream input) {
        super(input);
    }
    public String getGrammarFileName() { return "../src/org/unpython/compiler/frontend/Pystring.g"; }

    // $ANTLR start T6
    public final void mT6() throws RecognitionException {
        try {
            int _type = T6;
            // ../src/org/unpython/compiler/frontend/Pystring.g:6:4: ( '\\n' )
            // ../src/org/unpython/compiler/frontend/Pystring.g:6:6: '\\n'
            {
            match('\n'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T6

    // $ANTLR start T7
    public final void mT7() throws RecognitionException {
        try {
            int _type = T7;
            // ../src/org/unpython/compiler/frontend/Pystring.g:7:4: ( '\\'' )
            // ../src/org/unpython/compiler/frontend/Pystring.g:7:6: '\\''
            {
            match('\''); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T7

    // $ANTLR start T8
    public final void mT8() throws RecognitionException {
        try {
            int _type = T8;
            // ../src/org/unpython/compiler/frontend/Pystring.g:8:4: ( '\\\"' )
            // ../src/org/unpython/compiler/frontend/Pystring.g:8:6: '\\\"'
            {
            match('\"'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T8

    // $ANTLR start T9
    public final void mT9() throws RecognitionException {
        try {
            int _type = T9;
            // ../src/org/unpython/compiler/frontend/Pystring.g:9:4: ( '\\'\\'\\'' )
            // ../src/org/unpython/compiler/frontend/Pystring.g:9:6: '\\'\\'\\''
            {
            match("\'\'\'"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T9

    // $ANTLR start T10
    public final void mT10() throws RecognitionException {
        try {
            int _type = T10;
            // ../src/org/unpython/compiler/frontend/Pystring.g:10:5: ( '\\\\' )
            // ../src/org/unpython/compiler/frontend/Pystring.g:10:7: '\\\\'
            {
            match('\\'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T10

    // $ANTLR start ALPHA
    public final void mALPHA() throws RecognitionException {
        try {
            int _type = ALPHA;
            // ../src/org/unpython/compiler/frontend/Pystring.g:46:7: ( ( 'a' .. 'z' ) | ( 'A' .. 'Z' ) | '-' | '>' | ' ' | ',' | ':' | '@' | ( '0' .. '9' ) | ')' | '(' | '[' | ']' )
            int alt1=13;
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
            case '-':
                {
                alt1=3;
                }
                break;
            case '>':
                {
                alt1=4;
                }
                break;
            case ' ':
                {
                alt1=5;
                }
                break;
            case ',':
                {
                alt1=6;
                }
                break;
            case ':':
                {
                alt1=7;
                }
                break;
            case '@':
                {
                alt1=8;
                }
                break;
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                {
                alt1=9;
                }
                break;
            case ')':
                {
                alt1=10;
                }
                break;
            case '(':
                {
                alt1=11;
                }
                break;
            case '[':
                {
                alt1=12;
                }
                break;
            case ']':
                {
                alt1=13;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("46:1: ALPHA : ( ( 'a' .. 'z' ) | ( 'A' .. 'Z' ) | '-' | '>' | ' ' | ',' | ':' | '@' | ( '0' .. '9' ) | ')' | '(' | '[' | ']' );", 1, 0, input);

                throw nvae;
            }

            switch (alt1) {
                case 1 :
                    // ../src/org/unpython/compiler/frontend/Pystring.g:46:9: ( 'a' .. 'z' )
                    {
                    // ../src/org/unpython/compiler/frontend/Pystring.g:46:9: ( 'a' .. 'z' )
                    // ../src/org/unpython/compiler/frontend/Pystring.g:46:10: 'a' .. 'z'
                    {
                    matchRange('a','z'); 

                    }


                    }
                    break;
                case 2 :
                    // ../src/org/unpython/compiler/frontend/Pystring.g:46:20: ( 'A' .. 'Z' )
                    {
                    // ../src/org/unpython/compiler/frontend/Pystring.g:46:20: ( 'A' .. 'Z' )
                    // ../src/org/unpython/compiler/frontend/Pystring.g:46:21: 'A' .. 'Z'
                    {
                    matchRange('A','Z'); 

                    }


                    }
                    break;
                case 3 :
                    // ../src/org/unpython/compiler/frontend/Pystring.g:46:31: '-'
                    {
                    match('-'); 

                    }
                    break;
                case 4 :
                    // ../src/org/unpython/compiler/frontend/Pystring.g:46:35: '>'
                    {
                    match('>'); 

                    }
                    break;
                case 5 :
                    // ../src/org/unpython/compiler/frontend/Pystring.g:46:39: ' '
                    {
                    match(' '); 

                    }
                    break;
                case 6 :
                    // ../src/org/unpython/compiler/frontend/Pystring.g:46:43: ','
                    {
                    match(','); 

                    }
                    break;
                case 7 :
                    // ../src/org/unpython/compiler/frontend/Pystring.g:46:47: ':'
                    {
                    match(':'); 

                    }
                    break;
                case 8 :
                    // ../src/org/unpython/compiler/frontend/Pystring.g:46:51: '@'
                    {
                    match('@'); 

                    }
                    break;
                case 9 :
                    // ../src/org/unpython/compiler/frontend/Pystring.g:46:55: ( '0' .. '9' )
                    {
                    // ../src/org/unpython/compiler/frontend/Pystring.g:46:55: ( '0' .. '9' )
                    // ../src/org/unpython/compiler/frontend/Pystring.g:46:56: '0' .. '9'
                    {
                    matchRange('0','9'); 

                    }


                    }
                    break;
                case 10 :
                    // ../src/org/unpython/compiler/frontend/Pystring.g:46:67: ')'
                    {
                    match(')'); 

                    }
                    break;
                case 11 :
                    // ../src/org/unpython/compiler/frontend/Pystring.g:46:71: '('
                    {
                    match('('); 

                    }
                    break;
                case 12 :
                    // ../src/org/unpython/compiler/frontend/Pystring.g:46:75: '['
                    {
                    match('['); 

                    }
                    break;
                case 13 :
                    // ../src/org/unpython/compiler/frontend/Pystring.g:46:79: ']'
                    {
                    match(']'); 

                    }
                    break;

            }
            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end ALPHA

    // $ANTLR start PREFIX
    public final void mPREFIX() throws RecognitionException {
        try {
            int _type = PREFIX;
            // ../src/org/unpython/compiler/frontend/Pystring.g:47:8: ( 'r' | 'u' | 'ur' | 'R' | 'U' | 'UR' | 'Ur' | 'uR' )
            int alt2=8;
            switch ( input.LA(1) ) {
            case 'r':
                {
                alt2=1;
                }
                break;
            case 'u':
                {
                switch ( input.LA(2) ) {
                case 'R':
                    {
                    alt2=8;
                    }
                    break;
                case 'r':
                    {
                    alt2=3;
                    }
                    break;
                default:
                    alt2=2;}

                }
                break;
            case 'R':
                {
                alt2=4;
                }
                break;
            case 'U':
                {
                switch ( input.LA(2) ) {
                case 'r':
                    {
                    alt2=7;
                    }
                    break;
                case 'R':
                    {
                    alt2=6;
                    }
                    break;
                default:
                    alt2=5;}

                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("47:1: PREFIX : ( 'r' | 'u' | 'ur' | 'R' | 'U' | 'UR' | 'Ur' | 'uR' );", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // ../src/org/unpython/compiler/frontend/Pystring.g:47:10: 'r'
                    {
                    match('r'); 

                    }
                    break;
                case 2 :
                    // ../src/org/unpython/compiler/frontend/Pystring.g:47:14: 'u'
                    {
                    match('u'); 

                    }
                    break;
                case 3 :
                    // ../src/org/unpython/compiler/frontend/Pystring.g:47:18: 'ur'
                    {
                    match("ur"); 


                    }
                    break;
                case 4 :
                    // ../src/org/unpython/compiler/frontend/Pystring.g:47:23: 'R'
                    {
                    match('R'); 

                    }
                    break;
                case 5 :
                    // ../src/org/unpython/compiler/frontend/Pystring.g:47:27: 'U'
                    {
                    match('U'); 

                    }
                    break;
                case 6 :
                    // ../src/org/unpython/compiler/frontend/Pystring.g:47:31: 'UR'
                    {
                    match("UR"); 


                    }
                    break;
                case 7 :
                    // ../src/org/unpython/compiler/frontend/Pystring.g:47:36: 'Ur'
                    {
                    match("Ur"); 


                    }
                    break;
                case 8 :
                    // ../src/org/unpython/compiler/frontend/Pystring.g:47:41: 'uR'
                    {
                    match("uR"); 


                    }
                    break;

            }
            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end PREFIX

    public void mTokens() throws RecognitionException {
        // ../src/org/unpython/compiler/frontend/Pystring.g:1:8: ( T6 | T7 | T8 | T9 | T10 | ALPHA | PREFIX )
        int alt3=7;
        switch ( input.LA(1) ) {
        case '\n':
            {
            alt3=1;
            }
            break;
        case '\'':
            {
            int LA3_2 = input.LA(2);

            if ( (LA3_2=='\'') ) {
                alt3=4;
            }
            else {
                alt3=2;}
            }
            break;
        case '\"':
            {
            alt3=3;
            }
            break;
        case '\\':
            {
            alt3=5;
            }
            break;
        case 'r':
            {
            alt3=6;
            }
            break;
        case 'R':
            {
            alt3=6;
            }
            break;
        case ' ':
        case '(':
        case ')':
        case ',':
        case '-':
        case '0':
        case '1':
        case '2':
        case '3':
        case '4':
        case '5':
        case '6':
        case '7':
        case '8':
        case '9':
        case ':':
        case '>':
        case '@':
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
        case 'S':
        case 'T':
        case 'V':
        case 'W':
        case 'X':
        case 'Y':
        case 'Z':
        case '[':
        case ']':
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
        case 's':
        case 't':
        case 'v':
        case 'w':
        case 'x':
        case 'y':
        case 'z':
            {
            alt3=6;
            }
            break;
        case 'u':
            {
            int LA3_8 = input.LA(2);

            if ( (LA3_8=='R'||LA3_8=='r') ) {
                alt3=7;
            }
            else {
                alt3=6;}
            }
            break;
        case 'U':
            {
            int LA3_9 = input.LA(2);

            if ( (LA3_9=='R'||LA3_9=='r') ) {
                alt3=7;
            }
            else {
                alt3=6;}
            }
            break;
        default:
            NoViableAltException nvae =
                new NoViableAltException("1:1: Tokens : ( T6 | T7 | T8 | T9 | T10 | ALPHA | PREFIX );", 3, 0, input);

            throw nvae;
        }

        switch (alt3) {
            case 1 :
                // ../src/org/unpython/compiler/frontend/Pystring.g:1:10: T6
                {
                mT6(); 

                }
                break;
            case 2 :
                // ../src/org/unpython/compiler/frontend/Pystring.g:1:13: T7
                {
                mT7(); 

                }
                break;
            case 3 :
                // ../src/org/unpython/compiler/frontend/Pystring.g:1:16: T8
                {
                mT8(); 

                }
                break;
            case 4 :
                // ../src/org/unpython/compiler/frontend/Pystring.g:1:19: T9
                {
                mT9(); 

                }
                break;
            case 5 :
                // ../src/org/unpython/compiler/frontend/Pystring.g:1:22: T10
                {
                mT10(); 

                }
                break;
            case 6 :
                // ../src/org/unpython/compiler/frontend/Pystring.g:1:26: ALPHA
                {
                mALPHA(); 

                }
                break;
            case 7 :
                // ../src/org/unpython/compiler/frontend/Pystring.g:1:32: PREFIX
                {
                mPREFIX(); 

                }
                break;

        }

    }


 

}