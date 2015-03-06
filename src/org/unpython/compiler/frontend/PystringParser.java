// $ANTLR 3.0.1 ../src/org/unpython/compiler/frontend/Pystring.g 2008-05-27 19:57:42

package org.unpython.compiler.frontend;
import java.util.ArrayList;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

public class PystringParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "PREFIX", "ALPHA", "'\\n'", "'\\''", "'\\\"'", "'\\'\\'\\''", "'\\\\'"
    };
    public static final int PREFIX=4;
    public static final int EOF=-1;
    public static final int ALPHA=5;

        public PystringParser(TokenStream input) {
            super(input);
        }
        

    public String[] getTokenNames() { return tokenNames; }
    public String getGrammarFileName() { return "../src/org/unpython/compiler/frontend/Pystring.g"; }



    // $ANTLR start literals
    // ../src/org/unpython/compiler/frontend/Pystring.g:12:1: literals returns [ArrayList<String> list] : ( literal '\\n' )* ;
    public final ArrayList<String> literals() throws RecognitionException {
        ArrayList<String> list = null;

        literal_return literal1 = null;



        	list = new ArrayList<String>();

        try {
            // ../src/org/unpython/compiler/frontend/Pystring.g:16:2: ( ( literal '\\n' )* )
            // ../src/org/unpython/compiler/frontend/Pystring.g:16:5: ( literal '\\n' )*
            {
            // ../src/org/unpython/compiler/frontend/Pystring.g:16:5: ( literal '\\n' )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==PREFIX||(LA1_0>=7 && LA1_0<=9)) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // ../src/org/unpython/compiler/frontend/Pystring.g:16:6: literal '\\n'
            	    {
            	    pushFollow(FOLLOW_literal_in_literals38);
            	    literal1=literal();
            	    _fsp--;

            	    match(input,6,FOLLOW_6_in_literals40); 
            	    list.add(input.toString(literal1.start,literal1.stop));

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return list;
    }
    // $ANTLR end literals

    public static class literal_return extends ParserRuleReturnScope {
    };

    // $ANTLR start literal
    // ../src/org/unpython/compiler/frontend/Pystring.g:19:1: literal : ( PREFIX )? ( shortstring | longstring ) ;
    public final literal_return literal() throws RecognitionException {
        literal_return retval = new literal_return();
        retval.start = input.LT(1);

        try {
            // ../src/org/unpython/compiler/frontend/Pystring.g:19:10: ( ( PREFIX )? ( shortstring | longstring ) )
            // ../src/org/unpython/compiler/frontend/Pystring.g:19:12: ( PREFIX )? ( shortstring | longstring )
            {
            // ../src/org/unpython/compiler/frontend/Pystring.g:19:12: ( PREFIX )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==PREFIX) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // ../src/org/unpython/compiler/frontend/Pystring.g:19:12: PREFIX
                    {
                    match(input,PREFIX,FOLLOW_PREFIX_in_literal55); 

                    }
                    break;

            }

            // ../src/org/unpython/compiler/frontend/Pystring.g:19:20: ( shortstring | longstring )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( ((LA3_0>=7 && LA3_0<=8)) ) {
                alt3=1;
            }
            else if ( (LA3_0==9) ) {
                alt3=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("19:20: ( shortstring | longstring )", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // ../src/org/unpython/compiler/frontend/Pystring.g:19:21: shortstring
                    {
                    pushFollow(FOLLOW_shortstring_in_literal59);
                    shortstring();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // ../src/org/unpython/compiler/frontend/Pystring.g:19:35: longstring
                    {
                    pushFollow(FOLLOW_longstring_in_literal63);
                    longstring();
                    _fsp--;


                    }
                    break;

            }


            }

            retval.stop = input.LT(-1);

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end literal


    // $ANTLR start shortstring
    // ../src/org/unpython/compiler/frontend/Pystring.g:22:1: shortstring : ( ( '\\'' ( shortstringitem )* '\\'' ) | ( '\\\"' ( shortstringitem )* '\\\"' ) );
    public final void shortstring() throws RecognitionException {
        try {
            // ../src/org/unpython/compiler/frontend/Pystring.g:22:13: ( ( '\\'' ( shortstringitem )* '\\'' ) | ( '\\\"' ( shortstringitem )* '\\\"' ) )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==7) ) {
                alt6=1;
            }
            else if ( (LA6_0==8) ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("22:1: shortstring : ( ( '\\'' ( shortstringitem )* '\\'' ) | ( '\\\"' ( shortstringitem )* '\\\"' ) );", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // ../src/org/unpython/compiler/frontend/Pystring.g:22:15: ( '\\'' ( shortstringitem )* '\\'' )
                    {
                    // ../src/org/unpython/compiler/frontend/Pystring.g:22:15: ( '\\'' ( shortstringitem )* '\\'' )
                    // ../src/org/unpython/compiler/frontend/Pystring.g:22:16: '\\'' ( shortstringitem )* '\\''
                    {
                    match(input,7,FOLLOW_7_in_shortstring76); 
                    // ../src/org/unpython/compiler/frontend/Pystring.g:22:21: ( shortstringitem )*
                    loop4:
                    do {
                        int alt4=2;
                        int LA4_0 = input.LA(1);

                        if ( (LA4_0==ALPHA||LA4_0==10) ) {
                            alt4=1;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // ../src/org/unpython/compiler/frontend/Pystring.g:22:21: shortstringitem
                    	    {
                    	    pushFollow(FOLLOW_shortstringitem_in_shortstring78);
                    	    shortstringitem();
                    	    _fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop4;
                        }
                    } while (true);

                    match(input,7,FOLLOW_7_in_shortstring81); 

                    }


                    }
                    break;
                case 2 :
                    // ../src/org/unpython/compiler/frontend/Pystring.g:22:46: ( '\\\"' ( shortstringitem )* '\\\"' )
                    {
                    // ../src/org/unpython/compiler/frontend/Pystring.g:22:46: ( '\\\"' ( shortstringitem )* '\\\"' )
                    // ../src/org/unpython/compiler/frontend/Pystring.g:22:47: '\\\"' ( shortstringitem )* '\\\"'
                    {
                    match(input,8,FOLLOW_8_in_shortstring87); 
                    // ../src/org/unpython/compiler/frontend/Pystring.g:22:52: ( shortstringitem )*
                    loop5:
                    do {
                        int alt5=2;
                        int LA5_0 = input.LA(1);

                        if ( (LA5_0==ALPHA||LA5_0==10) ) {
                            alt5=1;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // ../src/org/unpython/compiler/frontend/Pystring.g:22:52: shortstringitem
                    	    {
                    	    pushFollow(FOLLOW_shortstringitem_in_shortstring89);
                    	    shortstringitem();
                    	    _fsp--;


                    	    }
                    	    break;

                    	default :
                    	    break loop5;
                        }
                    } while (true);

                    match(input,8,FOLLOW_8_in_shortstring92); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end shortstring


    // $ANTLR start longstring
    // ../src/org/unpython/compiler/frontend/Pystring.g:25:1: longstring : '\\'\\'\\'' ( longstringitem )* '\\'\\'\\'' ;
    public final void longstring() throws RecognitionException {
        try {
            // ../src/org/unpython/compiler/frontend/Pystring.g:25:13: ( '\\'\\'\\'' ( longstringitem )* '\\'\\'\\'' )
            // ../src/org/unpython/compiler/frontend/Pystring.g:25:15: '\\'\\'\\'' ( longstringitem )* '\\'\\'\\''
            {
            match(input,9,FOLLOW_9_in_longstring104); 
            // ../src/org/unpython/compiler/frontend/Pystring.g:25:24: ( longstringitem )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( ((LA7_0>=ALPHA && LA7_0<=6)||LA7_0==10) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // ../src/org/unpython/compiler/frontend/Pystring.g:25:24: longstringitem
            	    {
            	    pushFollow(FOLLOW_longstringitem_in_longstring106);
            	    longstringitem();
            	    _fsp--;


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

            match(input,9,FOLLOW_9_in_longstring109); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end longstring


    // $ANTLR start shortstringitem
    // ../src/org/unpython/compiler/frontend/Pystring.g:28:1: shortstringitem : ( shortstringchar | escapeseq );
    public final void shortstringitem() throws RecognitionException {
        try {
            // ../src/org/unpython/compiler/frontend/Pystring.g:29:2: ( shortstringchar | escapeseq )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==ALPHA) ) {
                alt8=1;
            }
            else if ( (LA8_0==10) ) {
                alt8=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("28:1: shortstringitem : ( shortstringchar | escapeseq );", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // ../src/org/unpython/compiler/frontend/Pystring.g:29:4: shortstringchar
                    {
                    pushFollow(FOLLOW_shortstringchar_in_shortstringitem120);
                    shortstringchar();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // ../src/org/unpython/compiler/frontend/Pystring.g:29:22: escapeseq
                    {
                    pushFollow(FOLLOW_escapeseq_in_shortstringitem124);
                    escapeseq();
                    _fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end shortstringitem


    // $ANTLR start longstringitem
    // ../src/org/unpython/compiler/frontend/Pystring.g:32:1: longstringitem : ( longstringchar | escapeseq );
    public final void longstringitem() throws RecognitionException {
        try {
            // ../src/org/unpython/compiler/frontend/Pystring.g:33:2: ( longstringchar | escapeseq )
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( ((LA9_0>=ALPHA && LA9_0<=6)) ) {
                alt9=1;
            }
            else if ( (LA9_0==10) ) {
                alt9=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("32:1: longstringitem : ( longstringchar | escapeseq );", 9, 0, input);

                throw nvae;
            }
            switch (alt9) {
                case 1 :
                    // ../src/org/unpython/compiler/frontend/Pystring.g:33:4: longstringchar
                    {
                    pushFollow(FOLLOW_longstringchar_in_longstringitem136);
                    longstringchar();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // ../src/org/unpython/compiler/frontend/Pystring.g:33:21: escapeseq
                    {
                    pushFollow(FOLLOW_escapeseq_in_longstringitem140);
                    escapeseq();
                    _fsp--;


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end longstringitem


    // $ANTLR start shortstringchar
    // ../src/org/unpython/compiler/frontend/Pystring.g:36:1: shortstringchar : ALPHA ;
    public final void shortstringchar() throws RecognitionException {
        try {
            // ../src/org/unpython/compiler/frontend/Pystring.g:36:17: ( ALPHA )
            // ../src/org/unpython/compiler/frontend/Pystring.g:36:19: ALPHA
            {
            match(input,ALPHA,FOLLOW_ALPHA_in_shortstringchar150); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end shortstringchar


    // $ANTLR start escapeseq
    // ../src/org/unpython/compiler/frontend/Pystring.g:38:1: escapeseq : '\\\\' ALPHA ;
    public final void escapeseq() throws RecognitionException {
        try {
            // ../src/org/unpython/compiler/frontend/Pystring.g:39:2: ( '\\\\' ALPHA )
            // ../src/org/unpython/compiler/frontend/Pystring.g:39:4: '\\\\' ALPHA
            {
            match(input,10,FOLLOW_10_in_escapeseq161); 
            match(input,ALPHA,FOLLOW_ALPHA_in_escapeseq163); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end escapeseq


    // $ANTLR start longstringchar
    // ../src/org/unpython/compiler/frontend/Pystring.g:41:1: longstringchar : ( shortstringchar | '\\n' );
    public final void longstringchar() throws RecognitionException {
        try {
            // ../src/org/unpython/compiler/frontend/Pystring.g:42:2: ( shortstringchar | '\\n' )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==ALPHA) ) {
                alt10=1;
            }
            else if ( (LA10_0==6) ) {
                alt10=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("41:1: longstringchar : ( shortstringchar | '\\n' );", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // ../src/org/unpython/compiler/frontend/Pystring.g:42:4: shortstringchar
                    {
                    pushFollow(FOLLOW_shortstringchar_in_longstringchar173);
                    shortstringchar();
                    _fsp--;


                    }
                    break;
                case 2 :
                    // ../src/org/unpython/compiler/frontend/Pystring.g:42:20: '\\n'
                    {
                    match(input,6,FOLLOW_6_in_longstringchar175); 

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end longstringchar


 

    public static final BitSet FOLLOW_literal_in_literals38 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_6_in_literals40 = new BitSet(new long[]{0x0000000000000392L});
    public static final BitSet FOLLOW_PREFIX_in_literal55 = new BitSet(new long[]{0x0000000000000380L});
    public static final BitSet FOLLOW_shortstring_in_literal59 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_longstring_in_literal63 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_7_in_shortstring76 = new BitSet(new long[]{0x00000000000004A0L});
    public static final BitSet FOLLOW_shortstringitem_in_shortstring78 = new BitSet(new long[]{0x00000000000004A0L});
    public static final BitSet FOLLOW_7_in_shortstring81 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_8_in_shortstring87 = new BitSet(new long[]{0x0000000000000520L});
    public static final BitSet FOLLOW_shortstringitem_in_shortstring89 = new BitSet(new long[]{0x0000000000000520L});
    public static final BitSet FOLLOW_8_in_shortstring92 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_9_in_longstring104 = new BitSet(new long[]{0x0000000000000660L});
    public static final BitSet FOLLOW_longstringitem_in_longstring106 = new BitSet(new long[]{0x0000000000000660L});
    public static final BitSet FOLLOW_9_in_longstring109 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_shortstringchar_in_shortstringitem120 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_escapeseq_in_shortstringitem124 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_longstringchar_in_longstringitem136 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_escapeseq_in_longstringitem140 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ALPHA_in_shortstringchar150 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_10_in_escapeseq161 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_ALPHA_in_escapeseq163 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_shortstringchar_in_longstringchar173 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_6_in_longstringchar175 = new BitSet(new long[]{0x0000000000000002L});

}