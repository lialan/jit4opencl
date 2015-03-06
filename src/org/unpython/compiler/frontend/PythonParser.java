// $ANTLR 3.0.1 Python.g 2008-07-22 13:46:53

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
import java.util.Map;
import java.util.HashMap;

import org.antlr.runtime.tree.*;

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
public class PythonParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "LIST", "ANNOT", "STRING", "MODULE", "NONE", "CONST", "STMT", "PYSTR", "DISCARD", "IF", "RETURN", "GLOBAL", "ASSIGN", "ASSNAME", "FUNCTION", "CALLFUNC", "ASSATTR", "ADD", "SUB", "MUL", "DIV", "GETATTR", "NAME", "IMPORT", "FROM", "FOR", "CLASS", "SLICE", "SUBSCRIPT", "SUBSCRIPTG", "SUBSCRIPTS", "SLICEOBJ", "COMPARE", "KEYWORD", "CGT", "CLT", "CEQ", "CNEQ", "CGTE", "CLTE", "COPY", "CFOR", "CIF", "RANGE", "LABEL", "GOTO", "CALLMETH", "DIM", "CONVERT", "PARFOR", "INT", "FLOAT", "ID", "ALPHA", "DIGIT", "WS", "'('", "')'", "'\\''", "'['", "']'", "'Annot'", "'Decorators'", "'\\'OP_ASSIGN\\''", "'\\'OP_APPLY\\''"
    };
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
    public static final int ID=56;
    public static final int ANNOT=5;
    public static final int EOF=-1;
    public static final int SUBSCRIPTS=34;
    public static final int SLICEOBJ=35;
    public static final int IF=13;
    public static final int GETATTR=25;
    public static final int NAME=26;
    public static final int IMPORT=27;
    public static final int COPY=44;
    public static final int CGT=38;
    public static final int RETURN=14;
    public static final int CFOR=45;
    public static final int COMPARE=36;
    public static final int DIGIT=58;
    public static final int GOTO=49;
    public static final int CGTE=42;
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
    public static final int LIST=4;
    public static final int MUL=23;
    public static final int ALPHA=57;
    public static final int PYSTR=11;
    public static final int WS=59;
    public static final int DISCARD=12;
    public static final int LABEL=48;
    public static final int ASSATTR=20;
    public static final int SUBSCRIPT=32;
    public static final int SUBSCRIPTG=33;
    public static final int NONE=8;
    public static final int DIM=51;
    public static final int ASSIGN=16;
    public static final int CIF=46;
    public static final int CEQ=40;
    public static final int DIV=24;
    public static final int GLOBAL=15;
    public static final int FROM=28;
    public static final int STRING=6;

        public PythonParser(TokenStream input) {
            super(input);
            ruleMemo = new HashMap[98+1];
         }
        
    protected TreeAdaptor adaptor = new CommonTreeAdaptor();

    public void setTreeAdaptor(TreeAdaptor adaptor) {
        this.adaptor = adaptor;
    }
    public TreeAdaptor getTreeAdaptor() {
        return adaptor;
    }

    public String[] getTokenNames() { return tokenNames; }
    public String getGrammarFileName() { return "Python.g"; }


    public static class module_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start module
    // Python.g:124:1: module : MODULE '(' docString stmt ')' ;
    public final module_return module() throws RecognitionException {
        module_return retval = new module_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token MODULE1=null;
        Token char_literal2=null;
        Token char_literal5=null;
        docString_return docString3 = null;

        stmt_return stmt4 = null;


        CommonTree MODULE1_tree=null;
        CommonTree char_literal2_tree=null;
        CommonTree char_literal5_tree=null;

        try {
            // Python.g:124:8: ( MODULE '(' docString stmt ')' )
            // Python.g:124:11: MODULE '(' docString stmt ')'
            {
            root_0 = (CommonTree)adaptor.nil();

            MODULE1=(Token)input.LT(1);
            match(input,MODULE,FOLLOW_MODULE_in_module307); if (failed) return retval;
            if ( backtracking==0 ) {
            MODULE1_tree = (CommonTree)adaptor.create(MODULE1);
            root_0 = (CommonTree)adaptor.becomeRoot(MODULE1_tree, root_0);
            }
            char_literal2=(Token)input.LT(1);
            match(input,60,FOLLOW_60_in_module310); if (failed) return retval;
            pushFollow(FOLLOW_docString_in_module313);
            docString3=docString();
            _fsp--;
            if (failed) return retval;
            if ( backtracking==0 ) adaptor.addChild(root_0, docString3.getTree());
            pushFollow(FOLLOW_stmt_in_module315);
            stmt4=stmt();
            _fsp--;
            if (failed) return retval;
            if ( backtracking==0 ) adaptor.addChild(root_0, stmt4.getTree());
            char_literal5=(Token)input.LT(1);
            match(input,61,FOLLOW_61_in_module317); if (failed) return retval;

            }

            retval.stop = input.LT(-1);

            if ( backtracking==0 ) {
                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end module

    public static class docString_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start docString
    // Python.g:127:1: docString : ( pyconst | NONE );
    public final docString_return docString() throws RecognitionException {
        docString_return retval = new docString_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token NONE7=null;
        pyconst_return pyconst6 = null;


        CommonTree NONE7_tree=null;

        try {
            // Python.g:127:11: ( pyconst | NONE )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==CONST) ) {
                alt1=1;
            }
            else if ( (LA1_0==NONE) ) {
                alt1=2;
            }
            else {
                if (backtracking>0) {failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("127:1: docString : ( pyconst | NONE );", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // Python.g:127:14: pyconst
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_pyconst_in_docString329);
                    pyconst6=pyconst();
                    _fsp--;
                    if (failed) return retval;
                    if ( backtracking==0 ) adaptor.addChild(root_0, pyconst6.getTree());

                    }
                    break;
                case 2 :
                    // Python.g:128:5: NONE
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    NONE7=(Token)input.LT(1);
                    match(input,NONE,FOLLOW_NONE_in_docString336); if (failed) return retval;
                    if ( backtracking==0 ) {
                    NONE7_tree = (CommonTree)adaptor.create(NONE7);
                    adaptor.addChild(root_0, NONE7_tree);
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( backtracking==0 ) {
                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end docString

    public static class pyconst_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start pyconst
    // Python.g:131:1: pyconst : CONST '(' ( pyStr | INT | FLOAT | NONE ) ')' ;
    public final pyconst_return pyconst() throws RecognitionException {
        pyconst_return retval = new pyconst_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token CONST8=null;
        Token char_literal9=null;
        Token INT11=null;
        Token FLOAT12=null;
        Token NONE13=null;
        Token char_literal14=null;
        pyStr_return pyStr10 = null;


        CommonTree CONST8_tree=null;
        CommonTree char_literal9_tree=null;
        CommonTree INT11_tree=null;
        CommonTree FLOAT12_tree=null;
        CommonTree NONE13_tree=null;
        CommonTree char_literal14_tree=null;

        try {
            // Python.g:131:9: ( CONST '(' ( pyStr | INT | FLOAT | NONE ) ')' )
            // Python.g:131:11: CONST '(' ( pyStr | INT | FLOAT | NONE ) ')'
            {
            root_0 = (CommonTree)adaptor.nil();

            CONST8=(Token)input.LT(1);
            match(input,CONST,FOLLOW_CONST_in_pyconst346); if (failed) return retval;
            if ( backtracking==0 ) {
            CONST8_tree = (CommonTree)adaptor.create(CONST8);
            root_0 = (CommonTree)adaptor.becomeRoot(CONST8_tree, root_0);
            }
            char_literal9=(Token)input.LT(1);
            match(input,60,FOLLOW_60_in_pyconst350); if (failed) return retval;
            // Python.g:131:24: ( pyStr | INT | FLOAT | NONE )
            int alt2=4;
            switch ( input.LA(1) ) {
            case 62:
                {
                alt2=1;
                }
                break;
            case INT:
                {
                alt2=2;
                }
                break;
            case FLOAT:
                {
                alt2=3;
                }
                break;
            case NONE:
                {
                alt2=4;
                }
                break;
            default:
                if (backtracking>0) {failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("131:24: ( pyStr | INT | FLOAT | NONE )", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // Python.g:131:25: pyStr
                    {
                    pushFollow(FOLLOW_pyStr_in_pyconst354);
                    pyStr10=pyStr();
                    _fsp--;
                    if (failed) return retval;
                    if ( backtracking==0 ) adaptor.addChild(root_0, pyStr10.getTree());

                    }
                    break;
                case 2 :
                    // Python.g:131:33: INT
                    {
                    INT11=(Token)input.LT(1);
                    match(input,INT,FOLLOW_INT_in_pyconst358); if (failed) return retval;
                    if ( backtracking==0 ) {
                    INT11_tree = (CommonTree)adaptor.create(INT11);
                    adaptor.addChild(root_0, INT11_tree);
                    }

                    }
                    break;
                case 3 :
                    // Python.g:131:39: FLOAT
                    {
                    FLOAT12=(Token)input.LT(1);
                    match(input,FLOAT,FOLLOW_FLOAT_in_pyconst362); if (failed) return retval;
                    if ( backtracking==0 ) {
                    FLOAT12_tree = (CommonTree)adaptor.create(FLOAT12);
                    adaptor.addChild(root_0, FLOAT12_tree);
                    }

                    }
                    break;
                case 4 :
                    // Python.g:131:47: NONE
                    {
                    NONE13=(Token)input.LT(1);
                    match(input,NONE,FOLLOW_NONE_in_pyconst366); if (failed) return retval;
                    if ( backtracking==0 ) {
                    NONE13_tree = (CommonTree)adaptor.create(NONE13);
                    adaptor.addChild(root_0, NONE13_tree);
                    }

                    }
                    break;

            }

            char_literal14=(Token)input.LT(1);
            match(input,61,FOLLOW_61_in_pyconst369); if (failed) return retval;

            }

            retval.stop = input.LT(-1);

            if ( backtracking==0 ) {
                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end pyconst

    public static class pyStr_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start pyStr
    // Python.g:134:1: pyStr : '\\'' PYSTR '(' INT ')' '\\'' ;
    public final pyStr_return pyStr() throws RecognitionException {
        pyStr_return retval = new pyStr_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal15=null;
        Token PYSTR16=null;
        Token char_literal17=null;
        Token INT18=null;
        Token char_literal19=null;
        Token char_literal20=null;

        CommonTree char_literal15_tree=null;
        CommonTree PYSTR16_tree=null;
        CommonTree char_literal17_tree=null;
        CommonTree INT18_tree=null;
        CommonTree char_literal19_tree=null;
        CommonTree char_literal20_tree=null;

        try {
            // Python.g:134:7: ( '\\'' PYSTR '(' INT ')' '\\'' )
            // Python.g:134:10: '\\'' PYSTR '(' INT ')' '\\''
            {
            root_0 = (CommonTree)adaptor.nil();

            char_literal15=(Token)input.LT(1);
            match(input,62,FOLLOW_62_in_pyStr382); if (failed) return retval;
            PYSTR16=(Token)input.LT(1);
            match(input,PYSTR,FOLLOW_PYSTR_in_pyStr385); if (failed) return retval;
            if ( backtracking==0 ) {
            PYSTR16_tree = (CommonTree)adaptor.create(PYSTR16);
            root_0 = (CommonTree)adaptor.becomeRoot(PYSTR16_tree, root_0);
            }
            char_literal17=(Token)input.LT(1);
            match(input,60,FOLLOW_60_in_pyStr388); if (failed) return retval;
            INT18=(Token)input.LT(1);
            match(input,INT,FOLLOW_INT_in_pyStr391); if (failed) return retval;
            if ( backtracking==0 ) {
            INT18_tree = (CommonTree)adaptor.create(INT18);
            adaptor.addChild(root_0, INT18_tree);
            }
            char_literal19=(Token)input.LT(1);
            match(input,61,FOLLOW_61_in_pyStr393); if (failed) return retval;
            char_literal20=(Token)input.LT(1);
            match(input,62,FOLLOW_62_in_pyStr396); if (failed) return retval;

            }

            retval.stop = input.LT(-1);

            if ( backtracking==0 ) {
                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end pyStr

    public static class stmt_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start stmt
    // Python.g:137:1: stmt : ( STMT '(' '[' ( annotStmt )+ ']' ')' -> ^( STMT ( annotStmt )+ ) | STMT NONE );
    public final stmt_return stmt() throws RecognitionException {
        stmt_return retval = new stmt_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token STMT21=null;
        Token char_literal22=null;
        Token char_literal23=null;
        Token char_literal25=null;
        Token char_literal26=null;
        Token STMT27=null;
        Token NONE28=null;
        annotStmt_return annotStmt24 = null;


        CommonTree STMT21_tree=null;
        CommonTree char_literal22_tree=null;
        CommonTree char_literal23_tree=null;
        CommonTree char_literal25_tree=null;
        CommonTree char_literal26_tree=null;
        CommonTree STMT27_tree=null;
        CommonTree NONE28_tree=null;
        RewriteRuleTokenStream stream_STMT=new RewriteRuleTokenStream(adaptor,"token STMT");
        RewriteRuleTokenStream stream_64=new RewriteRuleTokenStream(adaptor,"token 64");
        RewriteRuleTokenStream stream_63=new RewriteRuleTokenStream(adaptor,"token 63");
        RewriteRuleTokenStream stream_60=new RewriteRuleTokenStream(adaptor,"token 60");
        RewriteRuleTokenStream stream_61=new RewriteRuleTokenStream(adaptor,"token 61");
        RewriteRuleSubtreeStream stream_annotStmt=new RewriteRuleSubtreeStream(adaptor,"rule annotStmt");
        try {
            // Python.g:137:7: ( STMT '(' '[' ( annotStmt )+ ']' ')' -> ^( STMT ( annotStmt )+ ) | STMT NONE )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==STMT) ) {
                int LA4_1 = input.LA(2);

                if ( (LA4_1==60) ) {
                    alt4=1;
                }
                else if ( (LA4_1==NONE) ) {
                    alt4=2;
                }
                else {
                    if (backtracking>0) {failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("137:1: stmt : ( STMT '(' '[' ( annotStmt )+ ']' ')' -> ^( STMT ( annotStmt )+ ) | STMT NONE );", 4, 1, input);

                    throw nvae;
                }
            }
            else {
                if (backtracking>0) {failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("137:1: stmt : ( STMT '(' '[' ( annotStmt )+ ']' ')' -> ^( STMT ( annotStmt )+ ) | STMT NONE );", 4, 0, input);

                throw nvae;
            }
            switch (alt4) {
                case 1 :
                    // Python.g:137:9: STMT '(' '[' ( annotStmt )+ ']' ')'
                    {
                    STMT21=(Token)input.LT(1);
                    match(input,STMT,FOLLOW_STMT_in_stmt409); if (failed) return retval;
                    if ( backtracking==0 ) stream_STMT.add(STMT21);

                    char_literal22=(Token)input.LT(1);
                    match(input,60,FOLLOW_60_in_stmt411); if (failed) return retval;
                    if ( backtracking==0 ) stream_60.add(char_literal22);

                    char_literal23=(Token)input.LT(1);
                    match(input,63,FOLLOW_63_in_stmt413); if (failed) return retval;
                    if ( backtracking==0 ) stream_63.add(char_literal23);

                    // Python.g:137:22: ( annotStmt )+
                    int cnt3=0;
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( (LA3_0==NONE||LA3_0==STMT||(LA3_0>=DISCARD && LA3_0<=ASSIGN)||LA3_0==FUNCTION||LA3_0==IMPORT||(LA3_0>=FOR && LA3_0<=CLASS)||LA3_0==65) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // Python.g:137:23: annotStmt
                    	    {
                    	    pushFollow(FOLLOW_annotStmt_in_stmt416);
                    	    annotStmt24=annotStmt();
                    	    _fsp--;
                    	    if (failed) return retval;
                    	    if ( backtracking==0 ) stream_annotStmt.add(annotStmt24.getTree());

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt3 >= 1 ) break loop3;
                    	    if (backtracking>0) {failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(3, input);
                                throw eee;
                        }
                        cnt3++;
                    } while (true);

                    char_literal25=(Token)input.LT(1);
                    match(input,64,FOLLOW_64_in_stmt420); if (failed) return retval;
                    if ( backtracking==0 ) stream_64.add(char_literal25);

                    char_literal26=(Token)input.LT(1);
                    match(input,61,FOLLOW_61_in_stmt422); if (failed) return retval;
                    if ( backtracking==0 ) stream_61.add(char_literal26);


                    // AST REWRITE
                    // elements: annotStmt, STMT
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    if ( backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 137:44: -> ^( STMT ( annotStmt )+ )
                    {
                        // Python.g:137:47: ^( STMT ( annotStmt )+ )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_STMT.next(), root_1);

                        if ( !(stream_annotStmt.hasNext()) ) {
                            throw new RewriteEarlyExitException();
                        }
                        while ( stream_annotStmt.hasNext() ) {
                            adaptor.addChild(root_1, stream_annotStmt.next());

                        }
                        stream_annotStmt.reset();

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    }

                    }
                    break;
                case 2 :
                    // Python.g:138:4: STMT NONE
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    STMT27=(Token)input.LT(1);
                    match(input,STMT,FOLLOW_STMT_in_stmt438); if (failed) return retval;
                    if ( backtracking==0 ) {
                    STMT27_tree = (CommonTree)adaptor.create(STMT27);
                    root_0 = (CommonTree)adaptor.becomeRoot(STMT27_tree, root_0);
                    }
                    NONE28=(Token)input.LT(1);
                    match(input,NONE,FOLLOW_NONE_in_stmt441); if (failed) return retval;
                    if ( backtracking==0 ) {
                    NONE28_tree = (CommonTree)adaptor.create(NONE28);
                    adaptor.addChild(root_0, NONE28_tree);
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( backtracking==0 ) {
                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end stmt

    public static class annotStmt_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start annotStmt
    // Python.g:141:1: annotStmt : ( annot pyStmt -> ^( ANNOT annot pyStmt ) | pyStmt );
    public final annotStmt_return annotStmt() throws RecognitionException {
        annotStmt_return retval = new annotStmt_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        annot_return annot29 = null;

        pyStmt_return pyStmt30 = null;

        pyStmt_return pyStmt31 = null;


        RewriteRuleSubtreeStream stream_annot=new RewriteRuleSubtreeStream(adaptor,"rule annot");
        RewriteRuleSubtreeStream stream_pyStmt=new RewriteRuleSubtreeStream(adaptor,"rule pyStmt");
        try {
            // Python.g:141:12: ( annot pyStmt -> ^( ANNOT annot pyStmt ) | pyStmt )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==65) ) {
                alt5=1;
            }
            else if ( (LA5_0==NONE||LA5_0==STMT||(LA5_0>=DISCARD && LA5_0<=ASSIGN)||LA5_0==FUNCTION||LA5_0==IMPORT||(LA5_0>=FOR && LA5_0<=CLASS)) ) {
                alt5=2;
            }
            else {
                if (backtracking>0) {failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("141:1: annotStmt : ( annot pyStmt -> ^( ANNOT annot pyStmt ) | pyStmt );", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // Python.g:141:15: annot pyStmt
                    {
                    pushFollow(FOLLOW_annot_in_annotStmt454);
                    annot29=annot();
                    _fsp--;
                    if (failed) return retval;
                    if ( backtracking==0 ) stream_annot.add(annot29.getTree());
                    pushFollow(FOLLOW_pyStmt_in_annotStmt456);
                    pyStmt30=pyStmt();
                    _fsp--;
                    if (failed) return retval;
                    if ( backtracking==0 ) stream_pyStmt.add(pyStmt30.getTree());

                    // AST REWRITE
                    // elements: pyStmt, annot
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    if ( backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 141:28: -> ^( ANNOT annot pyStmt )
                    {
                        // Python.g:141:31: ^( ANNOT annot pyStmt )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(ANNOT, "ANNOT"), root_1);

                        adaptor.addChild(root_1, stream_annot.next());
                        adaptor.addChild(root_1, stream_pyStmt.next());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    }

                    }
                    break;
                case 2 :
                    // Python.g:142:4: pyStmt
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_pyStmt_in_annotStmt471);
                    pyStmt31=pyStmt();
                    _fsp--;
                    if (failed) return retval;
                    if ( backtracking==0 ) adaptor.addChild(root_0, pyStmt31.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( backtracking==0 ) {
                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end annotStmt

    public static class annot_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start annot
    // Python.g:145:1: annot : 'Annot' '(' pyStr ')' ;
    public final annot_return annot() throws RecognitionException {
        annot_return retval = new annot_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token string_literal32=null;
        Token char_literal33=null;
        Token char_literal35=null;
        pyStr_return pyStr34 = null;


        CommonTree string_literal32_tree=null;
        CommonTree char_literal33_tree=null;
        CommonTree char_literal35_tree=null;

        try {
            // Python.g:145:8: ( 'Annot' '(' pyStr ')' )
            // Python.g:145:10: 'Annot' '(' pyStr ')'
            {
            root_0 = (CommonTree)adaptor.nil();

            string_literal32=(Token)input.LT(1);
            match(input,65,FOLLOW_65_in_annot482); if (failed) return retval;
            char_literal33=(Token)input.LT(1);
            match(input,60,FOLLOW_60_in_annot485); if (failed) return retval;
            pushFollow(FOLLOW_pyStr_in_annot488);
            pyStr34=pyStr();
            _fsp--;
            if (failed) return retval;
            if ( backtracking==0 ) adaptor.addChild(root_0, pyStr34.getTree());
            char_literal35=(Token)input.LT(1);
            match(input,61,FOLLOW_61_in_annot490); if (failed) return retval;

            }

            retval.stop = input.LT(-1);

            if ( backtracking==0 ) {
                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end annot

    public static class pyStmt_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start pyStmt
    // Python.g:148:1: pyStmt : ( assign | stmt | defun | global | pyreturn | pyif | pyimport | discardStmt | pyfor | pyclass | NONE );
    public final pyStmt_return pyStmt() throws RecognitionException {
        pyStmt_return retval = new pyStmt_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token NONE46=null;
        assign_return assign36 = null;

        stmt_return stmt37 = null;

        defun_return defun38 = null;

        global_return global39 = null;

        pyreturn_return pyreturn40 = null;

        pyif_return pyif41 = null;

        pyimport_return pyimport42 = null;

        discardStmt_return discardStmt43 = null;

        pyfor_return pyfor44 = null;

        pyclass_return pyclass45 = null;


        CommonTree NONE46_tree=null;

        try {
            // Python.g:148:9: ( assign | stmt | defun | global | pyreturn | pyif | pyimport | discardStmt | pyfor | pyclass | NONE )
            int alt6=11;
            switch ( input.LA(1) ) {
            case ASSIGN:
                {
                alt6=1;
                }
                break;
            case STMT:
                {
                alt6=2;
                }
                break;
            case FUNCTION:
                {
                alt6=3;
                }
                break;
            case GLOBAL:
                {
                alt6=4;
                }
                break;
            case RETURN:
                {
                alt6=5;
                }
                break;
            case IF:
                {
                alt6=6;
                }
                break;
            case IMPORT:
                {
                alt6=7;
                }
                break;
            case DISCARD:
                {
                alt6=8;
                }
                break;
            case FOR:
                {
                alt6=9;
                }
                break;
            case CLASS:
                {
                alt6=10;
                }
                break;
            case NONE:
                {
                alt6=11;
                }
                break;
            default:
                if (backtracking>0) {failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("148:1: pyStmt : ( assign | stmt | defun | global | pyreturn | pyif | pyimport | discardStmt | pyfor | pyclass | NONE );", 6, 0, input);

                throw nvae;
            }

            switch (alt6) {
                case 1 :
                    // Python.g:148:11: assign
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_assign_in_pyStmt505);
                    assign36=assign();
                    _fsp--;
                    if (failed) return retval;
                    if ( backtracking==0 ) adaptor.addChild(root_0, assign36.getTree());

                    }
                    break;
                case 2 :
                    // Python.g:149:4: stmt
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_stmt_in_pyStmt510);
                    stmt37=stmt();
                    _fsp--;
                    if (failed) return retval;
                    if ( backtracking==0 ) adaptor.addChild(root_0, stmt37.getTree());

                    }
                    break;
                case 3 :
                    // Python.g:150:7: defun
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_defun_in_pyStmt518);
                    defun38=defun();
                    _fsp--;
                    if (failed) return retval;
                    if ( backtracking==0 ) adaptor.addChild(root_0, defun38.getTree());

                    }
                    break;
                case 4 :
                    // Python.g:151:4: global
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_global_in_pyStmt523);
                    global39=global();
                    _fsp--;
                    if (failed) return retval;
                    if ( backtracking==0 ) adaptor.addChild(root_0, global39.getTree());

                    }
                    break;
                case 5 :
                    // Python.g:152:4: pyreturn
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_pyreturn_in_pyStmt528);
                    pyreturn40=pyreturn();
                    _fsp--;
                    if (failed) return retval;
                    if ( backtracking==0 ) adaptor.addChild(root_0, pyreturn40.getTree());

                    }
                    break;
                case 6 :
                    // Python.g:153:4: pyif
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_pyif_in_pyStmt533);
                    pyif41=pyif();
                    _fsp--;
                    if (failed) return retval;
                    if ( backtracking==0 ) adaptor.addChild(root_0, pyif41.getTree());

                    }
                    break;
                case 7 :
                    // Python.g:154:4: pyimport
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_pyimport_in_pyStmt538);
                    pyimport42=pyimport();
                    _fsp--;
                    if (failed) return retval;
                    if ( backtracking==0 ) adaptor.addChild(root_0, pyimport42.getTree());

                    }
                    break;
                case 8 :
                    // Python.g:155:4: discardStmt
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_discardStmt_in_pyStmt543);
                    discardStmt43=discardStmt();
                    _fsp--;
                    if (failed) return retval;
                    if ( backtracking==0 ) adaptor.addChild(root_0, discardStmt43.getTree());

                    }
                    break;
                case 9 :
                    // Python.g:156:4: pyfor
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_pyfor_in_pyStmt548);
                    pyfor44=pyfor();
                    _fsp--;
                    if (failed) return retval;
                    if ( backtracking==0 ) adaptor.addChild(root_0, pyfor44.getTree());

                    }
                    break;
                case 10 :
                    // Python.g:157:9: pyclass
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_pyclass_in_pyStmt558);
                    pyclass45=pyclass();
                    _fsp--;
                    if (failed) return retval;
                    if ( backtracking==0 ) adaptor.addChild(root_0, pyclass45.getTree());

                    }
                    break;
                case 11 :
                    // Python.g:158:9: NONE
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    NONE46=(Token)input.LT(1);
                    match(input,NONE,FOLLOW_NONE_in_pyStmt568); if (failed) return retval;
                    if ( backtracking==0 ) {
                    NONE46_tree = (CommonTree)adaptor.create(NONE46);
                    adaptor.addChild(root_0, NONE46_tree);
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( backtracking==0 ) {
                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end pyStmt

    public static class pyclass_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start pyclass
    // Python.g:161:1: pyclass : CLASS '(' id '[' ( expr )+ ']' NONE pyStmt pyinit ')' -> ^( CLASS id ^( LIST ( expr )+ ) pyStmt pyinit ) ;
    public final pyclass_return pyclass() throws RecognitionException {
        pyclass_return retval = new pyclass_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token CLASS47=null;
        Token char_literal48=null;
        Token char_literal50=null;
        Token char_literal52=null;
        Token NONE53=null;
        Token char_literal56=null;
        id_return id49 = null;

        expr_return expr51 = null;

        pyStmt_return pyStmt54 = null;

        pyinit_return pyinit55 = null;


        CommonTree CLASS47_tree=null;
        CommonTree char_literal48_tree=null;
        CommonTree char_literal50_tree=null;
        CommonTree char_literal52_tree=null;
        CommonTree NONE53_tree=null;
        CommonTree char_literal56_tree=null;
        RewriteRuleTokenStream stream_CLASS=new RewriteRuleTokenStream(adaptor,"token CLASS");
        RewriteRuleTokenStream stream_64=new RewriteRuleTokenStream(adaptor,"token 64");
        RewriteRuleTokenStream stream_63=new RewriteRuleTokenStream(adaptor,"token 63");
        RewriteRuleTokenStream stream_60=new RewriteRuleTokenStream(adaptor,"token 60");
        RewriteRuleTokenStream stream_NONE=new RewriteRuleTokenStream(adaptor,"token NONE");
        RewriteRuleTokenStream stream_61=new RewriteRuleTokenStream(adaptor,"token 61");
        RewriteRuleSubtreeStream stream_id=new RewriteRuleSubtreeStream(adaptor,"rule id");
        RewriteRuleSubtreeStream stream_pyStmt=new RewriteRuleSubtreeStream(adaptor,"rule pyStmt");
        RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");
        RewriteRuleSubtreeStream stream_pyinit=new RewriteRuleSubtreeStream(adaptor,"rule pyinit");
        try {
            // Python.g:161:9: ( CLASS '(' id '[' ( expr )+ ']' NONE pyStmt pyinit ')' -> ^( CLASS id ^( LIST ( expr )+ ) pyStmt pyinit ) )
            // Python.g:161:11: CLASS '(' id '[' ( expr )+ ']' NONE pyStmt pyinit ')'
            {
            CLASS47=(Token)input.LT(1);
            match(input,CLASS,FOLLOW_CLASS_in_pyclass578); if (failed) return retval;
            if ( backtracking==0 ) stream_CLASS.add(CLASS47);

            char_literal48=(Token)input.LT(1);
            match(input,60,FOLLOW_60_in_pyclass580); if (failed) return retval;
            if ( backtracking==0 ) stream_60.add(char_literal48);

            pushFollow(FOLLOW_id_in_pyclass582);
            id49=id();
            _fsp--;
            if (failed) return retval;
            if ( backtracking==0 ) stream_id.add(id49.getTree());
            char_literal50=(Token)input.LT(1);
            match(input,63,FOLLOW_63_in_pyclass584); if (failed) return retval;
            if ( backtracking==0 ) stream_63.add(char_literal50);

            // Python.g:161:28: ( expr )+
            int cnt7=0;
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==CONST||LA7_0==CALLFUNC||(LA7_0>=ADD && LA7_0<=NAME)||(LA7_0>=SLICE && LA7_0<=SUBSCRIPT)||(LA7_0>=SLICEOBJ && LA7_0<=KEYWORD)) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // Python.g:0:0: expr
            	    {
            	    pushFollow(FOLLOW_expr_in_pyclass586);
            	    expr51=expr();
            	    _fsp--;
            	    if (failed) return retval;
            	    if ( backtracking==0 ) stream_expr.add(expr51.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt7 >= 1 ) break loop7;
            	    if (backtracking>0) {failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(7, input);
                        throw eee;
                }
                cnt7++;
            } while (true);

            char_literal52=(Token)input.LT(1);
            match(input,64,FOLLOW_64_in_pyclass589); if (failed) return retval;
            if ( backtracking==0 ) stream_64.add(char_literal52);

            NONE53=(Token)input.LT(1);
            match(input,NONE,FOLLOW_NONE_in_pyclass592); if (failed) return retval;
            if ( backtracking==0 ) stream_NONE.add(NONE53);

            pushFollow(FOLLOW_pyStmt_in_pyclass594);
            pyStmt54=pyStmt();
            _fsp--;
            if (failed) return retval;
            if ( backtracking==0 ) stream_pyStmt.add(pyStmt54.getTree());
            pushFollow(FOLLOW_pyinit_in_pyclass596);
            pyinit55=pyinit();
            _fsp--;
            if (failed) return retval;
            if ( backtracking==0 ) stream_pyinit.add(pyinit55.getTree());
            char_literal56=(Token)input.LT(1);
            match(input,61,FOLLOW_61_in_pyclass598); if (failed) return retval;
            if ( backtracking==0 ) stream_61.add(char_literal56);


            // AST REWRITE
            // elements: pyinit, id, pyStmt, CLASS, expr
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 161:62: -> ^( CLASS id ^( LIST ( expr )+ ) pyStmt pyinit )
            {
                // Python.g:161:65: ^( CLASS id ^( LIST ( expr )+ ) pyStmt pyinit )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_CLASS.next(), root_1);

                adaptor.addChild(root_1, stream_id.next());
                // Python.g:161:76: ^( LIST ( expr )+ )
                {
                CommonTree root_2 = (CommonTree)adaptor.nil();
                root_2 = (CommonTree)adaptor.becomeRoot(adaptor.create(LIST, "LIST"), root_2);

                if ( !(stream_expr.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_expr.hasNext() ) {
                    adaptor.addChild(root_2, stream_expr.next());

                }
                stream_expr.reset();

                adaptor.addChild(root_1, root_2);
                }
                adaptor.addChild(root_1, stream_pyStmt.next());
                adaptor.addChild(root_1, stream_pyinit.next());

                adaptor.addChild(root_0, root_1);
                }

            }

            }

            }

            retval.stop = input.LT(-1);

            if ( backtracking==0 ) {
                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end pyclass

    public static class pyinit_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start pyinit
    // Python.g:164:1: pyinit : ( defun | NONE );
    public final pyinit_return pyinit() throws RecognitionException {
        pyinit_return retval = new pyinit_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token NONE58=null;
        defun_return defun57 = null;


        CommonTree NONE58_tree=null;

        try {
            // Python.g:164:8: ( defun | NONE )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==FUNCTION) ) {
                alt8=1;
            }
            else if ( (LA8_0==NONE) ) {
                alt8=2;
            }
            else {
                if (backtracking>0) {failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("164:1: pyinit : ( defun | NONE );", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // Python.g:164:10: defun
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_defun_in_pyinit631);
                    defun57=defun();
                    _fsp--;
                    if (failed) return retval;
                    if ( backtracking==0 ) adaptor.addChild(root_0, defun57.getTree());

                    }
                    break;
                case 2 :
                    // Python.g:164:18: NONE
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    NONE58=(Token)input.LT(1);
                    match(input,NONE,FOLLOW_NONE_in_pyinit635); if (failed) return retval;
                    if ( backtracking==0 ) {
                    NONE58_tree = (CommonTree)adaptor.create(NONE58);
                    adaptor.addChild(root_0, NONE58_tree);
                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( backtracking==0 ) {
                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end pyinit

    public static class pyimport_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start pyimport
    // Python.g:166:1: pyimport : IMPORT '(' '[' ( '(' id idornone ')' )+ ']' ')' -> ^( IMPORT ^( LIST ( id idornone )+ ) ) ;
    public final pyimport_return pyimport() throws RecognitionException {
        pyimport_return retval = new pyimport_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token IMPORT59=null;
        Token char_literal60=null;
        Token char_literal61=null;
        Token char_literal62=null;
        Token char_literal65=null;
        Token char_literal66=null;
        Token char_literal67=null;
        id_return id63 = null;

        idornone_return idornone64 = null;


        CommonTree IMPORT59_tree=null;
        CommonTree char_literal60_tree=null;
        CommonTree char_literal61_tree=null;
        CommonTree char_literal62_tree=null;
        CommonTree char_literal65_tree=null;
        CommonTree char_literal66_tree=null;
        CommonTree char_literal67_tree=null;
        RewriteRuleTokenStream stream_IMPORT=new RewriteRuleTokenStream(adaptor,"token IMPORT");
        RewriteRuleTokenStream stream_64=new RewriteRuleTokenStream(adaptor,"token 64");
        RewriteRuleTokenStream stream_63=new RewriteRuleTokenStream(adaptor,"token 63");
        RewriteRuleTokenStream stream_60=new RewriteRuleTokenStream(adaptor,"token 60");
        RewriteRuleTokenStream stream_61=new RewriteRuleTokenStream(adaptor,"token 61");
        RewriteRuleSubtreeStream stream_id=new RewriteRuleSubtreeStream(adaptor,"rule id");
        RewriteRuleSubtreeStream stream_idornone=new RewriteRuleSubtreeStream(adaptor,"rule idornone");
        try {
            // Python.g:166:11: ( IMPORT '(' '[' ( '(' id idornone ')' )+ ']' ')' -> ^( IMPORT ^( LIST ( id idornone )+ ) ) )
            // Python.g:166:13: IMPORT '(' '[' ( '(' id idornone ')' )+ ']' ')'
            {
            IMPORT59=(Token)input.LT(1);
            match(input,IMPORT,FOLLOW_IMPORT_in_pyimport644); if (failed) return retval;
            if ( backtracking==0 ) stream_IMPORT.add(IMPORT59);

            char_literal60=(Token)input.LT(1);
            match(input,60,FOLLOW_60_in_pyimport646); if (failed) return retval;
            if ( backtracking==0 ) stream_60.add(char_literal60);

            char_literal61=(Token)input.LT(1);
            match(input,63,FOLLOW_63_in_pyimport648); if (failed) return retval;
            if ( backtracking==0 ) stream_63.add(char_literal61);

            // Python.g:166:28: ( '(' id idornone ')' )+
            int cnt9=0;
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==60) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // Python.g:166:30: '(' id idornone ')'
            	    {
            	    char_literal62=(Token)input.LT(1);
            	    match(input,60,FOLLOW_60_in_pyimport652); if (failed) return retval;
            	    if ( backtracking==0 ) stream_60.add(char_literal62);

            	    pushFollow(FOLLOW_id_in_pyimport654);
            	    id63=id();
            	    _fsp--;
            	    if (failed) return retval;
            	    if ( backtracking==0 ) stream_id.add(id63.getTree());
            	    pushFollow(FOLLOW_idornone_in_pyimport656);
            	    idornone64=idornone();
            	    _fsp--;
            	    if (failed) return retval;
            	    if ( backtracking==0 ) stream_idornone.add(idornone64.getTree());
            	    char_literal65=(Token)input.LT(1);
            	    match(input,61,FOLLOW_61_in_pyimport658); if (failed) return retval;
            	    if ( backtracking==0 ) stream_61.add(char_literal65);


            	    }
            	    break;

            	default :
            	    if ( cnt9 >= 1 ) break loop9;
            	    if (backtracking>0) {failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(9, input);
                        throw eee;
                }
                cnt9++;
            } while (true);

            char_literal66=(Token)input.LT(1);
            match(input,64,FOLLOW_64_in_pyimport663); if (failed) return retval;
            if ( backtracking==0 ) stream_64.add(char_literal66);

            char_literal67=(Token)input.LT(1);
            match(input,61,FOLLOW_61_in_pyimport665); if (failed) return retval;
            if ( backtracking==0 ) stream_61.add(char_literal67);


            // AST REWRITE
            // elements: idornone, id, IMPORT
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 166:61: -> ^( IMPORT ^( LIST ( id idornone )+ ) )
            {
                // Python.g:166:64: ^( IMPORT ^( LIST ( id idornone )+ ) )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_IMPORT.next(), root_1);

                // Python.g:166:73: ^( LIST ( id idornone )+ )
                {
                CommonTree root_2 = (CommonTree)adaptor.nil();
                root_2 = (CommonTree)adaptor.becomeRoot(adaptor.create(LIST, "LIST"), root_2);

                if ( !(stream_idornone.hasNext()||stream_id.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_idornone.hasNext()||stream_id.hasNext() ) {
                    adaptor.addChild(root_2, stream_id.next());
                    adaptor.addChild(root_2, stream_idornone.next());

                }
                stream_idornone.reset();
                stream_id.reset();

                adaptor.addChild(root_1, root_2);
                }

                adaptor.addChild(root_0, root_1);
                }

            }

            }

            }

            retval.stop = input.LT(-1);

            if ( backtracking==0 ) {
                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end pyimport

    public static class idornone_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start idornone
    // Python.g:169:1: idornone : ( NONE | id );
    public final idornone_return idornone() throws RecognitionException {
        idornone_return retval = new idornone_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token NONE68=null;
        id_return id69 = null;


        CommonTree NONE68_tree=null;

        try {
            // Python.g:169:9: ( NONE | id )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==NONE) ) {
                alt10=1;
            }
            else if ( (LA10_0==62) ) {
                alt10=2;
            }
            else {
                if (backtracking>0) {failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("169:1: idornone : ( NONE | id );", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // Python.g:169:11: NONE
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    NONE68=(Token)input.LT(1);
                    match(input,NONE,FOLLOW_NONE_in_idornone692); if (failed) return retval;
                    if ( backtracking==0 ) {
                    NONE68_tree = (CommonTree)adaptor.create(NONE68);
                    adaptor.addChild(root_0, NONE68_tree);
                    }

                    }
                    break;
                case 2 :
                    // Python.g:169:16: id
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_id_in_idornone694);
                    id69=id();
                    _fsp--;
                    if (failed) return retval;
                    if ( backtracking==0 ) adaptor.addChild(root_0, id69.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( backtracking==0 ) {
                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end idornone

    public static class discardStmt_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start discardStmt
    // Python.g:172:1: discardStmt : DISCARD '(' expr ')' ;
    public final discardStmt_return discardStmt() throws RecognitionException {
        discardStmt_return retval = new discardStmt_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token DISCARD70=null;
        Token char_literal71=null;
        Token char_literal73=null;
        expr_return expr72 = null;


        CommonTree DISCARD70_tree=null;
        CommonTree char_literal71_tree=null;
        CommonTree char_literal73_tree=null;

        try {
            // Python.g:172:13: ( DISCARD '(' expr ')' )
            // Python.g:172:15: DISCARD '(' expr ')'
            {
            root_0 = (CommonTree)adaptor.nil();

            DISCARD70=(Token)input.LT(1);
            match(input,DISCARD,FOLLOW_DISCARD_in_discardStmt704); if (failed) return retval;
            if ( backtracking==0 ) {
            DISCARD70_tree = (CommonTree)adaptor.create(DISCARD70);
            root_0 = (CommonTree)adaptor.becomeRoot(DISCARD70_tree, root_0);
            }
            char_literal71=(Token)input.LT(1);
            match(input,60,FOLLOW_60_in_discardStmt707); if (failed) return retval;
            pushFollow(FOLLOW_expr_in_discardStmt710);
            expr72=expr();
            _fsp--;
            if (failed) return retval;
            if ( backtracking==0 ) adaptor.addChild(root_0, expr72.getTree());
            char_literal73=(Token)input.LT(1);
            match(input,61,FOLLOW_61_in_discardStmt712); if (failed) return retval;

            }

            retval.stop = input.LT(-1);

            if ( backtracking==0 ) {
                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end discardStmt

    public static class pyifhelper_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start pyifhelper
    // Python.g:174:1: pyifhelper : ( expr pyStmt -> ^( LIST expr pyStmt ) | expr ( pyStmt )+ -> ^( LIST expr ^( STMT ( pyStmt )+ ) ) );
    public final pyifhelper_return pyifhelper() throws RecognitionException {
        pyifhelper_return retval = new pyifhelper_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        expr_return expr74 = null;

        pyStmt_return pyStmt75 = null;

        expr_return expr76 = null;

        pyStmt_return pyStmt77 = null;


        RewriteRuleSubtreeStream stream_pyStmt=new RewriteRuleSubtreeStream(adaptor,"rule pyStmt");
        RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");
        try {
            // Python.g:174:12: ( expr pyStmt -> ^( LIST expr pyStmt ) | expr ( pyStmt )+ -> ^( LIST expr ^( STMT ( pyStmt )+ ) ) )
            int alt12=2;
            switch ( input.LA(1) ) {
            case ADD:
                {
                int LA12_1 = input.LA(2);

                if ( (synpred22()) ) {
                    alt12=1;
                }
                else if ( (true) ) {
                    alt12=2;
                }
                else {
                    if (backtracking>0) {failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("174:1: pyifhelper : ( expr pyStmt -> ^( LIST expr pyStmt ) | expr ( pyStmt )+ -> ^( LIST expr ^( STMT ( pyStmt )+ ) ) );", 12, 1, input);

                    throw nvae;
                }
                }
                break;
            case MUL:
                {
                int LA12_2 = input.LA(2);

                if ( (synpred22()) ) {
                    alt12=1;
                }
                else if ( (true) ) {
                    alt12=2;
                }
                else {
                    if (backtracking>0) {failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("174:1: pyifhelper : ( expr pyStmt -> ^( LIST expr pyStmt ) | expr ( pyStmt )+ -> ^( LIST expr ^( STMT ( pyStmt )+ ) ) );", 12, 2, input);

                    throw nvae;
                }
                }
                break;
            case SUB:
                {
                int LA12_3 = input.LA(2);

                if ( (synpred22()) ) {
                    alt12=1;
                }
                else if ( (true) ) {
                    alt12=2;
                }
                else {
                    if (backtracking>0) {failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("174:1: pyifhelper : ( expr pyStmt -> ^( LIST expr pyStmt ) | expr ( pyStmt )+ -> ^( LIST expr ^( STMT ( pyStmt )+ ) ) );", 12, 3, input);

                    throw nvae;
                }
                }
                break;
            case DIV:
                {
                int LA12_4 = input.LA(2);

                if ( (synpred22()) ) {
                    alt12=1;
                }
                else if ( (true) ) {
                    alt12=2;
                }
                else {
                    if (backtracking>0) {failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("174:1: pyifhelper : ( expr pyStmt -> ^( LIST expr pyStmt ) | expr ( pyStmt )+ -> ^( LIST expr ^( STMT ( pyStmt )+ ) ) );", 12, 4, input);

                    throw nvae;
                }
                }
                break;
            case NAME:
                {
                int LA12_5 = input.LA(2);

                if ( (synpred22()) ) {
                    alt12=1;
                }
                else if ( (true) ) {
                    alt12=2;
                }
                else {
                    if (backtracking>0) {failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("174:1: pyifhelper : ( expr pyStmt -> ^( LIST expr pyStmt ) | expr ( pyStmt )+ -> ^( LIST expr ^( STMT ( pyStmt )+ ) ) );", 12, 5, input);

                    throw nvae;
                }
                }
                break;
            case GETATTR:
                {
                int LA12_6 = input.LA(2);

                if ( (synpred22()) ) {
                    alt12=1;
                }
                else if ( (true) ) {
                    alt12=2;
                }
                else {
                    if (backtracking>0) {failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("174:1: pyifhelper : ( expr pyStmt -> ^( LIST expr pyStmt ) | expr ( pyStmt )+ -> ^( LIST expr ^( STMT ( pyStmt )+ ) ) );", 12, 6, input);

                    throw nvae;
                }
                }
                break;
            case CONST:
                {
                int LA12_7 = input.LA(2);

                if ( (synpred22()) ) {
                    alt12=1;
                }
                else if ( (true) ) {
                    alt12=2;
                }
                else {
                    if (backtracking>0) {failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("174:1: pyifhelper : ( expr pyStmt -> ^( LIST expr pyStmt ) | expr ( pyStmt )+ -> ^( LIST expr ^( STMT ( pyStmt )+ ) ) );", 12, 7, input);

                    throw nvae;
                }
                }
                break;
            case CALLFUNC:
                {
                int LA12_8 = input.LA(2);

                if ( (synpred22()) ) {
                    alt12=1;
                }
                else if ( (true) ) {
                    alt12=2;
                }
                else {
                    if (backtracking>0) {failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("174:1: pyifhelper : ( expr pyStmt -> ^( LIST expr pyStmt ) | expr ( pyStmt )+ -> ^( LIST expr ^( STMT ( pyStmt )+ ) ) );", 12, 8, input);

                    throw nvae;
                }
                }
                break;
            case SLICEOBJ:
                {
                int LA12_9 = input.LA(2);

                if ( (synpred22()) ) {
                    alt12=1;
                }
                else if ( (true) ) {
                    alt12=2;
                }
                else {
                    if (backtracking>0) {failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("174:1: pyifhelper : ( expr pyStmt -> ^( LIST expr pyStmt ) | expr ( pyStmt )+ -> ^( LIST expr ^( STMT ( pyStmt )+ ) ) );", 12, 9, input);

                    throw nvae;
                }
                }
                break;
            case SUBSCRIPT:
                {
                int LA12_10 = input.LA(2);

                if ( (synpred22()) ) {
                    alt12=1;
                }
                else if ( (true) ) {
                    alt12=2;
                }
                else {
                    if (backtracking>0) {failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("174:1: pyifhelper : ( expr pyStmt -> ^( LIST expr pyStmt ) | expr ( pyStmt )+ -> ^( LIST expr ^( STMT ( pyStmt )+ ) ) );", 12, 10, input);

                    throw nvae;
                }
                }
                break;
            case SLICE:
                {
                int LA12_11 = input.LA(2);

                if ( (synpred22()) ) {
                    alt12=1;
                }
                else if ( (true) ) {
                    alt12=2;
                }
                else {
                    if (backtracking>0) {failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("174:1: pyifhelper : ( expr pyStmt -> ^( LIST expr pyStmt ) | expr ( pyStmt )+ -> ^( LIST expr ^( STMT ( pyStmt )+ ) ) );", 12, 11, input);

                    throw nvae;
                }
                }
                break;
            case COMPARE:
                {
                int LA12_12 = input.LA(2);

                if ( (synpred22()) ) {
                    alt12=1;
                }
                else if ( (true) ) {
                    alt12=2;
                }
                else {
                    if (backtracking>0) {failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("174:1: pyifhelper : ( expr pyStmt -> ^( LIST expr pyStmt ) | expr ( pyStmt )+ -> ^( LIST expr ^( STMT ( pyStmt )+ ) ) );", 12, 12, input);

                    throw nvae;
                }
                }
                break;
            case KEYWORD:
                {
                int LA12_13 = input.LA(2);

                if ( (synpred22()) ) {
                    alt12=1;
                }
                else if ( (true) ) {
                    alt12=2;
                }
                else {
                    if (backtracking>0) {failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("174:1: pyifhelper : ( expr pyStmt -> ^( LIST expr pyStmt ) | expr ( pyStmt )+ -> ^( LIST expr ^( STMT ( pyStmt )+ ) ) );", 12, 13, input);

                    throw nvae;
                }
                }
                break;
            default:
                if (backtracking>0) {failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("174:1: pyifhelper : ( expr pyStmt -> ^( LIST expr pyStmt ) | expr ( pyStmt )+ -> ^( LIST expr ^( STMT ( pyStmt )+ ) ) );", 12, 0, input);

                throw nvae;
            }

            switch (alt12) {
                case 1 :
                    // Python.g:174:14: expr pyStmt
                    {
                    pushFollow(FOLLOW_expr_in_pyifhelper723);
                    expr74=expr();
                    _fsp--;
                    if (failed) return retval;
                    if ( backtracking==0 ) stream_expr.add(expr74.getTree());
                    pushFollow(FOLLOW_pyStmt_in_pyifhelper725);
                    pyStmt75=pyStmt();
                    _fsp--;
                    if (failed) return retval;
                    if ( backtracking==0 ) stream_pyStmt.add(pyStmt75.getTree());

                    // AST REWRITE
                    // elements: pyStmt, expr
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    if ( backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 174:26: -> ^( LIST expr pyStmt )
                    {
                        // Python.g:174:29: ^( LIST expr pyStmt )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(LIST, "LIST"), root_1);

                        adaptor.addChild(root_1, stream_expr.next());
                        adaptor.addChild(root_1, stream_pyStmt.next());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    }

                    }
                    break;
                case 2 :
                    // Python.g:175:9: expr ( pyStmt )+
                    {
                    pushFollow(FOLLOW_expr_in_pyifhelper745);
                    expr76=expr();
                    _fsp--;
                    if (failed) return retval;
                    if ( backtracking==0 ) stream_expr.add(expr76.getTree());
                    // Python.g:175:14: ( pyStmt )+
                    int cnt11=0;
                    loop11:
                    do {
                        int alt11=2;
                        int LA11_0 = input.LA(1);

                        if ( (LA11_0==NONE||LA11_0==STMT||(LA11_0>=DISCARD && LA11_0<=ASSIGN)||LA11_0==FUNCTION||LA11_0==IMPORT||(LA11_0>=FOR && LA11_0<=CLASS)) ) {
                            alt11=1;
                        }


                        switch (alt11) {
                    	case 1 :
                    	    // Python.g:0:0: pyStmt
                    	    {
                    	    pushFollow(FOLLOW_pyStmt_in_pyifhelper747);
                    	    pyStmt77=pyStmt();
                    	    _fsp--;
                    	    if (failed) return retval;
                    	    if ( backtracking==0 ) stream_pyStmt.add(pyStmt77.getTree());

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt11 >= 1 ) break loop11;
                    	    if (backtracking>0) {failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(11, input);
                                throw eee;
                        }
                        cnt11++;
                    } while (true);


                    // AST REWRITE
                    // elements: pyStmt, expr
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    if ( backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 175:22: -> ^( LIST expr ^( STMT ( pyStmt )+ ) )
                    {
                        // Python.g:175:25: ^( LIST expr ^( STMT ( pyStmt )+ ) )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(LIST, "LIST"), root_1);

                        adaptor.addChild(root_1, stream_expr.next());
                        // Python.g:175:37: ^( STMT ( pyStmt )+ )
                        {
                        CommonTree root_2 = (CommonTree)adaptor.nil();
                        root_2 = (CommonTree)adaptor.becomeRoot(adaptor.create(STMT, "STMT"), root_2);

                        if ( !(stream_pyStmt.hasNext()) ) {
                            throw new RewriteEarlyExitException();
                        }
                        while ( stream_pyStmt.hasNext() ) {
                            adaptor.addChild(root_2, stream_pyStmt.next());

                        }
                        stream_pyStmt.reset();

                        adaptor.addChild(root_1, root_2);
                        }

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( backtracking==0 ) {
                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end pyifhelper

    public static class pyif_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start pyif
    // Python.g:178:1: pyif : ( IF '(' '[' ( '(' pyifhelper ')' )+ ']' pyStmt ')' -> ^( IF ^( LIST ( pyifhelper )+ ) pyStmt ) | IF '(' '[' ( '(' pyifhelper ')' )+ ']' ( pyStmt )+ ')' -> ^( IF ^( LIST ( pyifhelper )+ ) ^( STMT ( pyStmt )+ ) ) );
    public final pyif_return pyif() throws RecognitionException {
        pyif_return retval = new pyif_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token IF78=null;
        Token char_literal79=null;
        Token char_literal80=null;
        Token char_literal81=null;
        Token char_literal83=null;
        Token char_literal84=null;
        Token char_literal86=null;
        Token IF87=null;
        Token char_literal88=null;
        Token char_literal89=null;
        Token char_literal90=null;
        Token char_literal92=null;
        Token char_literal93=null;
        Token char_literal95=null;
        pyifhelper_return pyifhelper82 = null;

        pyStmt_return pyStmt85 = null;

        pyifhelper_return pyifhelper91 = null;

        pyStmt_return pyStmt94 = null;


        CommonTree IF78_tree=null;
        CommonTree char_literal79_tree=null;
        CommonTree char_literal80_tree=null;
        CommonTree char_literal81_tree=null;
        CommonTree char_literal83_tree=null;
        CommonTree char_literal84_tree=null;
        CommonTree char_literal86_tree=null;
        CommonTree IF87_tree=null;
        CommonTree char_literal88_tree=null;
        CommonTree char_literal89_tree=null;
        CommonTree char_literal90_tree=null;
        CommonTree char_literal92_tree=null;
        CommonTree char_literal93_tree=null;
        CommonTree char_literal95_tree=null;
        RewriteRuleTokenStream stream_64=new RewriteRuleTokenStream(adaptor,"token 64");
        RewriteRuleTokenStream stream_63=new RewriteRuleTokenStream(adaptor,"token 63");
        RewriteRuleTokenStream stream_60=new RewriteRuleTokenStream(adaptor,"token 60");
        RewriteRuleTokenStream stream_61=new RewriteRuleTokenStream(adaptor,"token 61");
        RewriteRuleTokenStream stream_IF=new RewriteRuleTokenStream(adaptor,"token IF");
        RewriteRuleSubtreeStream stream_pyStmt=new RewriteRuleSubtreeStream(adaptor,"rule pyStmt");
        RewriteRuleSubtreeStream stream_pyifhelper=new RewriteRuleSubtreeStream(adaptor,"rule pyifhelper");
        try {
            // Python.g:178:6: ( IF '(' '[' ( '(' pyifhelper ')' )+ ']' pyStmt ')' -> ^( IF ^( LIST ( pyifhelper )+ ) pyStmt ) | IF '(' '[' ( '(' pyifhelper ')' )+ ']' ( pyStmt )+ ')' -> ^( IF ^( LIST ( pyifhelper )+ ) ^( STMT ( pyStmt )+ ) ) )
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==IF) ) {
                int LA16_1 = input.LA(2);

                if ( (synpred25()) ) {
                    alt16=1;
                }
                else if ( (true) ) {
                    alt16=2;
                }
                else {
                    if (backtracking>0) {failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("178:1: pyif : ( IF '(' '[' ( '(' pyifhelper ')' )+ ']' pyStmt ')' -> ^( IF ^( LIST ( pyifhelper )+ ) pyStmt ) | IF '(' '[' ( '(' pyifhelper ')' )+ ']' ( pyStmt )+ ')' -> ^( IF ^( LIST ( pyifhelper )+ ) ^( STMT ( pyStmt )+ ) ) );", 16, 1, input);

                    throw nvae;
                }
            }
            else {
                if (backtracking>0) {failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("178:1: pyif : ( IF '(' '[' ( '(' pyifhelper ')' )+ ']' pyStmt ')' -> ^( IF ^( LIST ( pyifhelper )+ ) pyStmt ) | IF '(' '[' ( '(' pyifhelper ')' )+ ']' ( pyStmt )+ ')' -> ^( IF ^( LIST ( pyifhelper )+ ) ^( STMT ( pyStmt )+ ) ) );", 16, 0, input);

                throw nvae;
            }
            switch (alt16) {
                case 1 :
                    // Python.g:178:8: IF '(' '[' ( '(' pyifhelper ')' )+ ']' pyStmt ')'
                    {
                    IF78=(Token)input.LT(1);
                    match(input,IF,FOLLOW_IF_in_pyif776); if (failed) return retval;
                    if ( backtracking==0 ) stream_IF.add(IF78);

                    char_literal79=(Token)input.LT(1);
                    match(input,60,FOLLOW_60_in_pyif778); if (failed) return retval;
                    if ( backtracking==0 ) stream_60.add(char_literal79);

                    char_literal80=(Token)input.LT(1);
                    match(input,63,FOLLOW_63_in_pyif780); if (failed) return retval;
                    if ( backtracking==0 ) stream_63.add(char_literal80);

                    // Python.g:178:19: ( '(' pyifhelper ')' )+
                    int cnt13=0;
                    loop13:
                    do {
                        int alt13=2;
                        int LA13_0 = input.LA(1);

                        if ( (LA13_0==60) ) {
                            alt13=1;
                        }


                        switch (alt13) {
                    	case 1 :
                    	    // Python.g:178:20: '(' pyifhelper ')'
                    	    {
                    	    char_literal81=(Token)input.LT(1);
                    	    match(input,60,FOLLOW_60_in_pyif783); if (failed) return retval;
                    	    if ( backtracking==0 ) stream_60.add(char_literal81);

                    	    pushFollow(FOLLOW_pyifhelper_in_pyif785);
                    	    pyifhelper82=pyifhelper();
                    	    _fsp--;
                    	    if (failed) return retval;
                    	    if ( backtracking==0 ) stream_pyifhelper.add(pyifhelper82.getTree());
                    	    char_literal83=(Token)input.LT(1);
                    	    match(input,61,FOLLOW_61_in_pyif787); if (failed) return retval;
                    	    if ( backtracking==0 ) stream_61.add(char_literal83);


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt13 >= 1 ) break loop13;
                    	    if (backtracking>0) {failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(13, input);
                                throw eee;
                        }
                        cnt13++;
                    } while (true);

                    char_literal84=(Token)input.LT(1);
                    match(input,64,FOLLOW_64_in_pyif792); if (failed) return retval;
                    if ( backtracking==0 ) stream_64.add(char_literal84);

                    pushFollow(FOLLOW_pyStmt_in_pyif794);
                    pyStmt85=pyStmt();
                    _fsp--;
                    if (failed) return retval;
                    if ( backtracking==0 ) stream_pyStmt.add(pyStmt85.getTree());
                    char_literal86=(Token)input.LT(1);
                    match(input,61,FOLLOW_61_in_pyif796); if (failed) return retval;
                    if ( backtracking==0 ) stream_61.add(char_literal86);


                    // AST REWRITE
                    // elements: IF, pyStmt, pyifhelper
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    if ( backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 178:57: -> ^( IF ^( LIST ( pyifhelper )+ ) pyStmt )
                    {
                        // Python.g:178:60: ^( IF ^( LIST ( pyifhelper )+ ) pyStmt )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_IF.next(), root_1);

                        // Python.g:178:66: ^( LIST ( pyifhelper )+ )
                        {
                        CommonTree root_2 = (CommonTree)adaptor.nil();
                        root_2 = (CommonTree)adaptor.becomeRoot(adaptor.create(LIST, "LIST"), root_2);

                        if ( !(stream_pyifhelper.hasNext()) ) {
                            throw new RewriteEarlyExitException();
                        }
                        while ( stream_pyifhelper.hasNext() ) {
                            adaptor.addChild(root_2, stream_pyifhelper.next());

                        }
                        stream_pyifhelper.reset();

                        adaptor.addChild(root_1, root_2);
                        }
                        adaptor.addChild(root_1, stream_pyStmt.next());

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    }

                    }
                    break;
                case 2 :
                    // Python.g:179:7: IF '(' '[' ( '(' pyifhelper ')' )+ ']' ( pyStmt )+ ')'
                    {
                    IF87=(Token)input.LT(1);
                    match(input,IF,FOLLOW_IF_in_pyif820); if (failed) return retval;
                    if ( backtracking==0 ) stream_IF.add(IF87);

                    char_literal88=(Token)input.LT(1);
                    match(input,60,FOLLOW_60_in_pyif822); if (failed) return retval;
                    if ( backtracking==0 ) stream_60.add(char_literal88);

                    char_literal89=(Token)input.LT(1);
                    match(input,63,FOLLOW_63_in_pyif824); if (failed) return retval;
                    if ( backtracking==0 ) stream_63.add(char_literal89);

                    // Python.g:179:18: ( '(' pyifhelper ')' )+
                    int cnt14=0;
                    loop14:
                    do {
                        int alt14=2;
                        int LA14_0 = input.LA(1);

                        if ( (LA14_0==60) ) {
                            alt14=1;
                        }


                        switch (alt14) {
                    	case 1 :
                    	    // Python.g:179:19: '(' pyifhelper ')'
                    	    {
                    	    char_literal90=(Token)input.LT(1);
                    	    match(input,60,FOLLOW_60_in_pyif827); if (failed) return retval;
                    	    if ( backtracking==0 ) stream_60.add(char_literal90);

                    	    pushFollow(FOLLOW_pyifhelper_in_pyif829);
                    	    pyifhelper91=pyifhelper();
                    	    _fsp--;
                    	    if (failed) return retval;
                    	    if ( backtracking==0 ) stream_pyifhelper.add(pyifhelper91.getTree());
                    	    char_literal92=(Token)input.LT(1);
                    	    match(input,61,FOLLOW_61_in_pyif831); if (failed) return retval;
                    	    if ( backtracking==0 ) stream_61.add(char_literal92);


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt14 >= 1 ) break loop14;
                    	    if (backtracking>0) {failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(14, input);
                                throw eee;
                        }
                        cnt14++;
                    } while (true);

                    char_literal93=(Token)input.LT(1);
                    match(input,64,FOLLOW_64_in_pyif836); if (failed) return retval;
                    if ( backtracking==0 ) stream_64.add(char_literal93);

                    // Python.g:179:45: ( pyStmt )+
                    int cnt15=0;
                    loop15:
                    do {
                        int alt15=2;
                        int LA15_0 = input.LA(1);

                        if ( (LA15_0==NONE||LA15_0==STMT||(LA15_0>=DISCARD && LA15_0<=ASSIGN)||LA15_0==FUNCTION||LA15_0==IMPORT||(LA15_0>=FOR && LA15_0<=CLASS)) ) {
                            alt15=1;
                        }


                        switch (alt15) {
                    	case 1 :
                    	    // Python.g:0:0: pyStmt
                    	    {
                    	    pushFollow(FOLLOW_pyStmt_in_pyif838);
                    	    pyStmt94=pyStmt();
                    	    _fsp--;
                    	    if (failed) return retval;
                    	    if ( backtracking==0 ) stream_pyStmt.add(pyStmt94.getTree());

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt15 >= 1 ) break loop15;
                    	    if (backtracking>0) {failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(15, input);
                                throw eee;
                        }
                        cnt15++;
                    } while (true);

                    char_literal95=(Token)input.LT(1);
                    match(input,61,FOLLOW_61_in_pyif841); if (failed) return retval;
                    if ( backtracking==0 ) stream_61.add(char_literal95);


                    // AST REWRITE
                    // elements: IF, pyifhelper, pyStmt
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    if ( backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 179:57: -> ^( IF ^( LIST ( pyifhelper )+ ) ^( STMT ( pyStmt )+ ) )
                    {
                        // Python.g:179:60: ^( IF ^( LIST ( pyifhelper )+ ) ^( STMT ( pyStmt )+ ) )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(stream_IF.next(), root_1);

                        // Python.g:179:66: ^( LIST ( pyifhelper )+ )
                        {
                        CommonTree root_2 = (CommonTree)adaptor.nil();
                        root_2 = (CommonTree)adaptor.becomeRoot(adaptor.create(LIST, "LIST"), root_2);

                        if ( !(stream_pyifhelper.hasNext()) ) {
                            throw new RewriteEarlyExitException();
                        }
                        while ( stream_pyifhelper.hasNext() ) {
                            adaptor.addChild(root_2, stream_pyifhelper.next());

                        }
                        stream_pyifhelper.reset();

                        adaptor.addChild(root_1, root_2);
                        }
                        // Python.g:179:86: ^( STMT ( pyStmt )+ )
                        {
                        CommonTree root_2 = (CommonTree)adaptor.nil();
                        root_2 = (CommonTree)adaptor.becomeRoot(adaptor.create(STMT, "STMT"), root_2);

                        if ( !(stream_pyStmt.hasNext()) ) {
                            throw new RewriteEarlyExitException();
                        }
                        while ( stream_pyStmt.hasNext() ) {
                            adaptor.addChild(root_2, stream_pyStmt.next());

                        }
                        stream_pyStmt.reset();

                        adaptor.addChild(root_1, root_2);
                        }

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( backtracking==0 ) {
                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end pyif

    public static class pyfor_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start pyfor
    // Python.g:182:1: pyfor : FOR '(' assname callFunc stmt NONE ')' ;
    public final pyfor_return pyfor() throws RecognitionException {
        pyfor_return retval = new pyfor_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token FOR96=null;
        Token char_literal97=null;
        Token NONE101=null;
        Token char_literal102=null;
        assname_return assname98 = null;

        callFunc_return callFunc99 = null;

        stmt_return stmt100 = null;


        CommonTree FOR96_tree=null;
        CommonTree char_literal97_tree=null;
        CommonTree NONE101_tree=null;
        CommonTree char_literal102_tree=null;

        try {
            // Python.g:182:7: ( FOR '(' assname callFunc stmt NONE ')' )
            // Python.g:182:9: FOR '(' assname callFunc stmt NONE ')'
            {
            root_0 = (CommonTree)adaptor.nil();

            FOR96=(Token)input.LT(1);
            match(input,FOR,FOLLOW_FOR_in_pyfor872); if (failed) return retval;
            if ( backtracking==0 ) {
            FOR96_tree = (CommonTree)adaptor.create(FOR96);
            root_0 = (CommonTree)adaptor.becomeRoot(FOR96_tree, root_0);
            }
            char_literal97=(Token)input.LT(1);
            match(input,60,FOLLOW_60_in_pyfor875); if (failed) return retval;
            pushFollow(FOLLOW_assname_in_pyfor878);
            assname98=assname();
            _fsp--;
            if (failed) return retval;
            if ( backtracking==0 ) adaptor.addChild(root_0, assname98.getTree());
            pushFollow(FOLLOW_callFunc_in_pyfor880);
            callFunc99=callFunc();
            _fsp--;
            if (failed) return retval;
            if ( backtracking==0 ) adaptor.addChild(root_0, callFunc99.getTree());
            pushFollow(FOLLOW_stmt_in_pyfor882);
            stmt100=stmt();
            _fsp--;
            if (failed) return retval;
            if ( backtracking==0 ) adaptor.addChild(root_0, stmt100.getTree());
            NONE101=(Token)input.LT(1);
            match(input,NONE,FOLLOW_NONE_in_pyfor884); if (failed) return retval;
            if ( backtracking==0 ) {
            NONE101_tree = (CommonTree)adaptor.create(NONE101);
            adaptor.addChild(root_0, NONE101_tree);
            }
            char_literal102=(Token)input.LT(1);
            match(input,61,FOLLOW_61_in_pyfor886); if (failed) return retval;

            }

            retval.stop = input.LT(-1);

            if ( backtracking==0 ) {
                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end pyfor

    public static class pyreturn_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start pyreturn
    // Python.g:185:1: pyreturn : RETURN '(' expr ')' ;
    public final pyreturn_return pyreturn() throws RecognitionException {
        pyreturn_return retval = new pyreturn_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token RETURN103=null;
        Token char_literal104=null;
        Token char_literal106=null;
        expr_return expr105 = null;


        CommonTree RETURN103_tree=null;
        CommonTree char_literal104_tree=null;
        CommonTree char_literal106_tree=null;

        try {
            // Python.g:185:10: ( RETURN '(' expr ')' )
            // Python.g:185:13: RETURN '(' expr ')'
            {
            root_0 = (CommonTree)adaptor.nil();

            RETURN103=(Token)input.LT(1);
            match(input,RETURN,FOLLOW_RETURN_in_pyreturn898); if (failed) return retval;
            if ( backtracking==0 ) {
            RETURN103_tree = (CommonTree)adaptor.create(RETURN103);
            root_0 = (CommonTree)adaptor.becomeRoot(RETURN103_tree, root_0);
            }
            char_literal104=(Token)input.LT(1);
            match(input,60,FOLLOW_60_in_pyreturn901); if (failed) return retval;
            pushFollow(FOLLOW_expr_in_pyreturn904);
            expr105=expr();
            _fsp--;
            if (failed) return retval;
            if ( backtracking==0 ) adaptor.addChild(root_0, expr105.getTree());
            char_literal106=(Token)input.LT(1);
            match(input,61,FOLLOW_61_in_pyreturn906); if (failed) return retval;

            }

            retval.stop = input.LT(-1);

            if ( backtracking==0 ) {
                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end pyreturn

    public static class global_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start global
    // Python.g:188:1: global : GLOBAL '(' '[' ( id )+ ']' ')' -> ^( GLOBAL ^( LIST ( id )+ ) ) ;
    public final global_return global() throws RecognitionException {
        global_return retval = new global_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token GLOBAL107=null;
        Token char_literal108=null;
        Token char_literal109=null;
        Token char_literal111=null;
        Token char_literal112=null;
        id_return id110 = null;


        CommonTree GLOBAL107_tree=null;
        CommonTree char_literal108_tree=null;
        CommonTree char_literal109_tree=null;
        CommonTree char_literal111_tree=null;
        CommonTree char_literal112_tree=null;
        RewriteRuleTokenStream stream_64=new RewriteRuleTokenStream(adaptor,"token 64");
        RewriteRuleTokenStream stream_GLOBAL=new RewriteRuleTokenStream(adaptor,"token GLOBAL");
        RewriteRuleTokenStream stream_63=new RewriteRuleTokenStream(adaptor,"token 63");
        RewriteRuleTokenStream stream_60=new RewriteRuleTokenStream(adaptor,"token 60");
        RewriteRuleTokenStream stream_61=new RewriteRuleTokenStream(adaptor,"token 61");
        RewriteRuleSubtreeStream stream_id=new RewriteRuleSubtreeStream(adaptor,"rule id");
        try {
            // Python.g:188:8: ( GLOBAL '(' '[' ( id )+ ']' ')' -> ^( GLOBAL ^( LIST ( id )+ ) ) )
            // Python.g:188:10: GLOBAL '(' '[' ( id )+ ']' ')'
            {
            GLOBAL107=(Token)input.LT(1);
            match(input,GLOBAL,FOLLOW_GLOBAL_in_global917); if (failed) return retval;
            if ( backtracking==0 ) stream_GLOBAL.add(GLOBAL107);

            char_literal108=(Token)input.LT(1);
            match(input,60,FOLLOW_60_in_global919); if (failed) return retval;
            if ( backtracking==0 ) stream_60.add(char_literal108);

            char_literal109=(Token)input.LT(1);
            match(input,63,FOLLOW_63_in_global921); if (failed) return retval;
            if ( backtracking==0 ) stream_63.add(char_literal109);

            // Python.g:188:25: ( id )+
            int cnt17=0;
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==62) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // Python.g:0:0: id
            	    {
            	    pushFollow(FOLLOW_id_in_global923);
            	    id110=id();
            	    _fsp--;
            	    if (failed) return retval;
            	    if ( backtracking==0 ) stream_id.add(id110.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt17 >= 1 ) break loop17;
            	    if (backtracking>0) {failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(17, input);
                        throw eee;
                }
                cnt17++;
            } while (true);

            char_literal111=(Token)input.LT(1);
            match(input,64,FOLLOW_64_in_global926); if (failed) return retval;
            if ( backtracking==0 ) stream_64.add(char_literal111);

            char_literal112=(Token)input.LT(1);
            match(input,61,FOLLOW_61_in_global928); if (failed) return retval;
            if ( backtracking==0 ) stream_61.add(char_literal112);


            // AST REWRITE
            // elements: GLOBAL, id
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 188:37: -> ^( GLOBAL ^( LIST ( id )+ ) )
            {
                // Python.g:188:40: ^( GLOBAL ^( LIST ( id )+ ) )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_GLOBAL.next(), root_1);

                // Python.g:188:49: ^( LIST ( id )+ )
                {
                CommonTree root_2 = (CommonTree)adaptor.nil();
                root_2 = (CommonTree)adaptor.becomeRoot(adaptor.create(LIST, "LIST"), root_2);

                if ( !(stream_id.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_id.hasNext() ) {
                    adaptor.addChild(root_2, stream_id.next());

                }
                stream_id.reset();

                adaptor.addChild(root_1, root_2);
                }

                adaptor.addChild(root_0, root_1);
                }

            }

            }

            }

            retval.stop = input.LT(-1);

            if ( backtracking==0 ) {
                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end global

    public static class defun_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start defun
    // Python.g:191:1: defun : FUNCTION '(' decorators id paramlist defaults INT docString stmt ')' ;
    public final defun_return defun() throws RecognitionException {
        defun_return retval = new defun_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token FUNCTION113=null;
        Token char_literal114=null;
        Token INT119=null;
        Token char_literal122=null;
        decorators_return decorators115 = null;

        id_return id116 = null;

        paramlist_return paramlist117 = null;

        defaults_return defaults118 = null;

        docString_return docString120 = null;

        stmt_return stmt121 = null;


        CommonTree FUNCTION113_tree=null;
        CommonTree char_literal114_tree=null;
        CommonTree INT119_tree=null;
        CommonTree char_literal122_tree=null;

        try {
            // Python.g:191:8: ( FUNCTION '(' decorators id paramlist defaults INT docString stmt ')' )
            // Python.g:191:12: FUNCTION '(' decorators id paramlist defaults INT docString stmt ')'
            {
            root_0 = (CommonTree)adaptor.nil();

            FUNCTION113=(Token)input.LT(1);
            match(input,FUNCTION,FOLLOW_FUNCTION_in_defun954); if (failed) return retval;
            if ( backtracking==0 ) {
            FUNCTION113_tree = (CommonTree)adaptor.create(FUNCTION113);
            root_0 = (CommonTree)adaptor.becomeRoot(FUNCTION113_tree, root_0);
            }
            char_literal114=(Token)input.LT(1);
            match(input,60,FOLLOW_60_in_defun957); if (failed) return retval;
            pushFollow(FOLLOW_decorators_in_defun960);
            decorators115=decorators();
            _fsp--;
            if (failed) return retval;
            if ( backtracking==0 ) adaptor.addChild(root_0, decorators115.getTree());
            pushFollow(FOLLOW_id_in_defun962);
            id116=id();
            _fsp--;
            if (failed) return retval;
            if ( backtracking==0 ) adaptor.addChild(root_0, id116.getTree());
            pushFollow(FOLLOW_paramlist_in_defun964);
            paramlist117=paramlist();
            _fsp--;
            if (failed) return retval;
            if ( backtracking==0 ) adaptor.addChild(root_0, paramlist117.getTree());
            pushFollow(FOLLOW_defaults_in_defun966);
            defaults118=defaults();
            _fsp--;
            if (failed) return retval;
            INT119=(Token)input.LT(1);
            match(input,INT,FOLLOW_INT_in_defun969); if (failed) return retval;
            pushFollow(FOLLOW_docString_in_defun972);
            docString120=docString();
            _fsp--;
            if (failed) return retval;
            pushFollow(FOLLOW_stmt_in_defun975);
            stmt121=stmt();
            _fsp--;
            if (failed) return retval;
            if ( backtracking==0 ) adaptor.addChild(root_0, stmt121.getTree());
            char_literal122=(Token)input.LT(1);
            match(input,61,FOLLOW_61_in_defun977); if (failed) return retval;

            }

            retval.stop = input.LT(-1);

            if ( backtracking==0 ) {
                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end defun

    public static class decorators_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start decorators
    // Python.g:194:1: decorators : ( NONE | 'Decorators' '(' '[' callFunc ']' ')' -> ^( LIST ^( callFunc ) ) );
    public final decorators_return decorators() throws RecognitionException {
        decorators_return retval = new decorators_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token NONE123=null;
        Token string_literal124=null;
        Token char_literal125=null;
        Token char_literal126=null;
        Token char_literal128=null;
        Token char_literal129=null;
        callFunc_return callFunc127 = null;


        CommonTree NONE123_tree=null;
        CommonTree string_literal124_tree=null;
        CommonTree char_literal125_tree=null;
        CommonTree char_literal126_tree=null;
        CommonTree char_literal128_tree=null;
        CommonTree char_literal129_tree=null;
        RewriteRuleTokenStream stream_66=new RewriteRuleTokenStream(adaptor,"token 66");
        RewriteRuleTokenStream stream_64=new RewriteRuleTokenStream(adaptor,"token 64");
        RewriteRuleTokenStream stream_63=new RewriteRuleTokenStream(adaptor,"token 63");
        RewriteRuleTokenStream stream_60=new RewriteRuleTokenStream(adaptor,"token 60");
        RewriteRuleTokenStream stream_61=new RewriteRuleTokenStream(adaptor,"token 61");
        RewriteRuleSubtreeStream stream_callFunc=new RewriteRuleSubtreeStream(adaptor,"rule callFunc");
        try {
            // Python.g:194:13: ( NONE | 'Decorators' '(' '[' callFunc ']' ')' -> ^( LIST ^( callFunc ) ) )
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==NONE) ) {
                alt18=1;
            }
            else if ( (LA18_0==66) ) {
                alt18=2;
            }
            else {
                if (backtracking>0) {failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("194:1: decorators : ( NONE | 'Decorators' '(' '[' callFunc ']' ')' -> ^( LIST ^( callFunc ) ) );", 18, 0, input);

                throw nvae;
            }
            switch (alt18) {
                case 1 :
                    // Python.g:194:15: NONE
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    NONE123=(Token)input.LT(1);
                    match(input,NONE,FOLLOW_NONE_in_decorators989); if (failed) return retval;
                    if ( backtracking==0 ) {
                    NONE123_tree = (CommonTree)adaptor.create(NONE123);
                    adaptor.addChild(root_0, NONE123_tree);
                    }

                    }
                    break;
                case 2 :
                    // Python.g:195:19: 'Decorators' '(' '[' callFunc ']' ')'
                    {
                    string_literal124=(Token)input.LT(1);
                    match(input,66,FOLLOW_66_in_decorators1010); if (failed) return retval;
                    if ( backtracking==0 ) stream_66.add(string_literal124);

                    char_literal125=(Token)input.LT(1);
                    match(input,60,FOLLOW_60_in_decorators1012); if (failed) return retval;
                    if ( backtracking==0 ) stream_60.add(char_literal125);

                    char_literal126=(Token)input.LT(1);
                    match(input,63,FOLLOW_63_in_decorators1014); if (failed) return retval;
                    if ( backtracking==0 ) stream_63.add(char_literal126);

                    pushFollow(FOLLOW_callFunc_in_decorators1016);
                    callFunc127=callFunc();
                    _fsp--;
                    if (failed) return retval;
                    if ( backtracking==0 ) stream_callFunc.add(callFunc127.getTree());
                    char_literal128=(Token)input.LT(1);
                    match(input,64,FOLLOW_64_in_decorators1018); if (failed) return retval;
                    if ( backtracking==0 ) stream_64.add(char_literal128);

                    char_literal129=(Token)input.LT(1);
                    match(input,61,FOLLOW_61_in_decorators1020); if (failed) return retval;
                    if ( backtracking==0 ) stream_61.add(char_literal129);


                    // AST REWRITE
                    // elements: callFunc
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    if ( backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 195:57: -> ^( LIST ^( callFunc ) )
                    {
                        // Python.g:195:60: ^( LIST ^( callFunc ) )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(LIST, "LIST"), root_1);

                        // Python.g:195:67: ^( callFunc )
                        {
                        CommonTree root_2 = (CommonTree)adaptor.nil();
                        root_2 = (CommonTree)adaptor.becomeRoot(stream_callFunc.nextNode(), root_2);

                        adaptor.addChild(root_1, root_2);
                        }

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( backtracking==0 ) {
                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end decorators

    public static class defaults_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start defaults
    // Python.g:198:1: defaults : '[' ']' ;
    public final defaults_return defaults() throws RecognitionException {
        defaults_return retval = new defaults_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal130=null;
        Token char_literal131=null;

        CommonTree char_literal130_tree=null;
        CommonTree char_literal131_tree=null;

        try {
            // Python.g:198:10: ( '[' ']' )
            // Python.g:198:12: '[' ']'
            {
            root_0 = (CommonTree)adaptor.nil();

            char_literal130=(Token)input.LT(1);
            match(input,63,FOLLOW_63_in_defaults1040); if (failed) return retval;
            char_literal131=(Token)input.LT(1);
            match(input,64,FOLLOW_64_in_defaults1043); if (failed) return retval;

            }

            retval.stop = input.LT(-1);

            if ( backtracking==0 ) {
                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end defaults

    public static class paramlist_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start paramlist
    // Python.g:201:1: paramlist : '[' ( id )+ ']' -> ^( LIST ( id )+ ) ;
    public final paramlist_return paramlist() throws RecognitionException {
        paramlist_return retval = new paramlist_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal132=null;
        Token char_literal134=null;
        id_return id133 = null;


        CommonTree char_literal132_tree=null;
        CommonTree char_literal134_tree=null;
        RewriteRuleTokenStream stream_64=new RewriteRuleTokenStream(adaptor,"token 64");
        RewriteRuleTokenStream stream_63=new RewriteRuleTokenStream(adaptor,"token 63");
        RewriteRuleSubtreeStream stream_id=new RewriteRuleSubtreeStream(adaptor,"rule id");
        try {
            // Python.g:201:11: ( '[' ( id )+ ']' -> ^( LIST ( id )+ ) )
            // Python.g:201:13: '[' ( id )+ ']'
            {
            char_literal132=(Token)input.LT(1);
            match(input,63,FOLLOW_63_in_paramlist1056); if (failed) return retval;
            if ( backtracking==0 ) stream_63.add(char_literal132);

            // Python.g:201:17: ( id )+
            int cnt19=0;
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==62) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // Python.g:0:0: id
            	    {
            	    pushFollow(FOLLOW_id_in_paramlist1058);
            	    id133=id();
            	    _fsp--;
            	    if (failed) return retval;
            	    if ( backtracking==0 ) stream_id.add(id133.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt19 >= 1 ) break loop19;
            	    if (backtracking>0) {failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(19, input);
                        throw eee;
                }
                cnt19++;
            } while (true);

            char_literal134=(Token)input.LT(1);
            match(input,64,FOLLOW_64_in_paramlist1061); if (failed) return retval;
            if ( backtracking==0 ) stream_64.add(char_literal134);


            // AST REWRITE
            // elements: id
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 201:25: -> ^( LIST ( id )+ )
            {
                // Python.g:201:28: ^( LIST ( id )+ )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(LIST, "LIST"), root_1);

                if ( !(stream_id.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_id.hasNext() ) {
                    adaptor.addChild(root_1, stream_id.next());

                }
                stream_id.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            }

            }

            retval.stop = input.LT(-1);

            if ( backtracking==0 ) {
                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end paramlist

    public static class assign_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start assign
    // Python.g:205:1: assign : ASSIGN '(' '[' ( assnode )+ ']' expr ')' -> ^( ASSIGN ^( LIST ( assnode )+ ) expr ) ;
    public final assign_return assign() throws RecognitionException {
        assign_return retval = new assign_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token ASSIGN135=null;
        Token char_literal136=null;
        Token char_literal137=null;
        Token char_literal139=null;
        Token char_literal141=null;
        assnode_return assnode138 = null;

        expr_return expr140 = null;


        CommonTree ASSIGN135_tree=null;
        CommonTree char_literal136_tree=null;
        CommonTree char_literal137_tree=null;
        CommonTree char_literal139_tree=null;
        CommonTree char_literal141_tree=null;
        RewriteRuleTokenStream stream_64=new RewriteRuleTokenStream(adaptor,"token 64");
        RewriteRuleTokenStream stream_63=new RewriteRuleTokenStream(adaptor,"token 63");
        RewriteRuleTokenStream stream_60=new RewriteRuleTokenStream(adaptor,"token 60");
        RewriteRuleTokenStream stream_61=new RewriteRuleTokenStream(adaptor,"token 61");
        RewriteRuleTokenStream stream_ASSIGN=new RewriteRuleTokenStream(adaptor,"token ASSIGN");
        RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");
        RewriteRuleSubtreeStream stream_assnode=new RewriteRuleSubtreeStream(adaptor,"rule assnode");
        try {
            // Python.g:205:8: ( ASSIGN '(' '[' ( assnode )+ ']' expr ')' -> ^( ASSIGN ^( LIST ( assnode )+ ) expr ) )
            // Python.g:205:10: ASSIGN '(' '[' ( assnode )+ ']' expr ')'
            {
            ASSIGN135=(Token)input.LT(1);
            match(input,ASSIGN,FOLLOW_ASSIGN_in_assign1082); if (failed) return retval;
            if ( backtracking==0 ) stream_ASSIGN.add(ASSIGN135);

            char_literal136=(Token)input.LT(1);
            match(input,60,FOLLOW_60_in_assign1084); if (failed) return retval;
            if ( backtracking==0 ) stream_60.add(char_literal136);

            char_literal137=(Token)input.LT(1);
            match(input,63,FOLLOW_63_in_assign1087); if (failed) return retval;
            if ( backtracking==0 ) stream_63.add(char_literal137);

            // Python.g:205:26: ( assnode )+
            int cnt20=0;
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==ASSNAME||LA20_0==ASSATTR||LA20_0==SUBSCRIPT) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // Python.g:205:27: assnode
            	    {
            	    pushFollow(FOLLOW_assnode_in_assign1090);
            	    assnode138=assnode();
            	    _fsp--;
            	    if (failed) return retval;
            	    if ( backtracking==0 ) stream_assnode.add(assnode138.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt20 >= 1 ) break loop20;
            	    if (backtracking>0) {failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(20, input);
                        throw eee;
                }
                cnt20++;
            } while (true);

            char_literal139=(Token)input.LT(1);
            match(input,64,FOLLOW_64_in_assign1094); if (failed) return retval;
            if ( backtracking==0 ) stream_64.add(char_literal139);

            pushFollow(FOLLOW_expr_in_assign1096);
            expr140=expr();
            _fsp--;
            if (failed) return retval;
            if ( backtracking==0 ) stream_expr.add(expr140.getTree());
            char_literal141=(Token)input.LT(1);
            match(input,61,FOLLOW_61_in_assign1098); if (failed) return retval;
            if ( backtracking==0 ) stream_61.add(char_literal141);


            // AST REWRITE
            // elements: ASSIGN, expr, assnode
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 205:50: -> ^( ASSIGN ^( LIST ( assnode )+ ) expr )
            {
                // Python.g:205:53: ^( ASSIGN ^( LIST ( assnode )+ ) expr )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_ASSIGN.next(), root_1);

                // Python.g:205:62: ^( LIST ( assnode )+ )
                {
                CommonTree root_2 = (CommonTree)adaptor.nil();
                root_2 = (CommonTree)adaptor.becomeRoot(adaptor.create(LIST, "LIST"), root_2);

                if ( !(stream_assnode.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_assnode.hasNext() ) {
                    adaptor.addChild(root_2, stream_assnode.next());

                }
                stream_assnode.reset();

                adaptor.addChild(root_1, root_2);
                }
                adaptor.addChild(root_1, stream_expr.next());

                adaptor.addChild(root_0, root_1);
                }

            }

            }

            }

            retval.stop = input.LT(-1);

            if ( backtracking==0 ) {
                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end assign

    public static class assnode_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start assnode
    // Python.g:208:1: assnode : ( assname | assattr | subscript );
    public final assnode_return assnode() throws RecognitionException {
        assnode_return retval = new assnode_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        assname_return assname142 = null;

        assattr_return assattr143 = null;

        subscript_return subscript144 = null;



        try {
            // Python.g:208:10: ( assname | assattr | subscript )
            int alt21=3;
            switch ( input.LA(1) ) {
            case ASSNAME:
                {
                alt21=1;
                }
                break;
            case ASSATTR:
                {
                alt21=2;
                }
                break;
            case SUBSCRIPT:
                {
                alt21=3;
                }
                break;
            default:
                if (backtracking>0) {failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("208:1: assnode : ( assname | assattr | subscript );", 21, 0, input);

                throw nvae;
            }

            switch (alt21) {
                case 1 :
                    // Python.g:208:13: assname
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_assname_in_assnode1125);
                    assname142=assname();
                    _fsp--;
                    if (failed) return retval;
                    if ( backtracking==0 ) adaptor.addChild(root_0, assname142.getTree());

                    }
                    break;
                case 2 :
                    // Python.g:208:23: assattr
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_assattr_in_assnode1129);
                    assattr143=assattr();
                    _fsp--;
                    if (failed) return retval;
                    if ( backtracking==0 ) adaptor.addChild(root_0, assattr143.getTree());

                    }
                    break;
                case 3 :
                    // Python.g:208:33: subscript
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_subscript_in_assnode1133);
                    subscript144=subscript();
                    _fsp--;
                    if (failed) return retval;
                    if ( backtracking==0 ) adaptor.addChild(root_0, subscript144.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( backtracking==0 ) {
                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end assnode

    public static class assname_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start assname
    // Python.g:209:1: assname : ASSNAME '(' id '\\'OP_ASSIGN\\'' ')' ;
    public final assname_return assname() throws RecognitionException {
        assname_return retval = new assname_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token ASSNAME145=null;
        Token char_literal146=null;
        Token string_literal148=null;
        Token char_literal149=null;
        id_return id147 = null;


        CommonTree ASSNAME145_tree=null;
        CommonTree char_literal146_tree=null;
        CommonTree string_literal148_tree=null;
        CommonTree char_literal149_tree=null;

        try {
            // Python.g:209:9: ( ASSNAME '(' id '\\'OP_ASSIGN\\'' ')' )
            // Python.g:209:11: ASSNAME '(' id '\\'OP_ASSIGN\\'' ')'
            {
            root_0 = (CommonTree)adaptor.nil();

            ASSNAME145=(Token)input.LT(1);
            match(input,ASSNAME,FOLLOW_ASSNAME_in_assname1142); if (failed) return retval;
            if ( backtracking==0 ) {
            ASSNAME145_tree = (CommonTree)adaptor.create(ASSNAME145);
            root_0 = (CommonTree)adaptor.becomeRoot(ASSNAME145_tree, root_0);
            }
            char_literal146=(Token)input.LT(1);
            match(input,60,FOLLOW_60_in_assname1145); if (failed) return retval;
            pushFollow(FOLLOW_id_in_assname1148);
            id147=id();
            _fsp--;
            if (failed) return retval;
            if ( backtracking==0 ) adaptor.addChild(root_0, id147.getTree());
            string_literal148=(Token)input.LT(1);
            match(input,67,FOLLOW_67_in_assname1150); if (failed) return retval;
            if ( backtracking==0 ) {
            string_literal148_tree = (CommonTree)adaptor.create(string_literal148);
            adaptor.addChild(root_0, string_literal148_tree);
            }
            char_literal149=(Token)input.LT(1);
            match(input,61,FOLLOW_61_in_assname1152); if (failed) return retval;

            }

            retval.stop = input.LT(-1);

            if ( backtracking==0 ) {
                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end assname

    public static class assattr_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start assattr
    // Python.g:212:1: assattr : ASSATTR '(' expr id '\\'OP_ASSIGN\\'' ')' ;
    public final assattr_return assattr() throws RecognitionException {
        assattr_return retval = new assattr_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token ASSATTR150=null;
        Token char_literal151=null;
        Token string_literal154=null;
        Token char_literal155=null;
        expr_return expr152 = null;

        id_return id153 = null;


        CommonTree ASSATTR150_tree=null;
        CommonTree char_literal151_tree=null;
        CommonTree string_literal154_tree=null;
        CommonTree char_literal155_tree=null;

        try {
            // Python.g:212:9: ( ASSATTR '(' expr id '\\'OP_ASSIGN\\'' ')' )
            // Python.g:212:11: ASSATTR '(' expr id '\\'OP_ASSIGN\\'' ')'
            {
            root_0 = (CommonTree)adaptor.nil();

            ASSATTR150=(Token)input.LT(1);
            match(input,ASSATTR,FOLLOW_ASSATTR_in_assattr1163); if (failed) return retval;
            if ( backtracking==0 ) {
            ASSATTR150_tree = (CommonTree)adaptor.create(ASSATTR150);
            root_0 = (CommonTree)adaptor.becomeRoot(ASSATTR150_tree, root_0);
            }
            char_literal151=(Token)input.LT(1);
            match(input,60,FOLLOW_60_in_assattr1166); if (failed) return retval;
            pushFollow(FOLLOW_expr_in_assattr1169);
            expr152=expr();
            _fsp--;
            if (failed) return retval;
            if ( backtracking==0 ) adaptor.addChild(root_0, expr152.getTree());
            pushFollow(FOLLOW_id_in_assattr1171);
            id153=id();
            _fsp--;
            if (failed) return retval;
            if ( backtracking==0 ) adaptor.addChild(root_0, id153.getTree());
            string_literal154=(Token)input.LT(1);
            match(input,67,FOLLOW_67_in_assattr1173); if (failed) return retval;
            if ( backtracking==0 ) {
            string_literal154_tree = (CommonTree)adaptor.create(string_literal154);
            adaptor.addChild(root_0, string_literal154_tree);
            }
            char_literal155=(Token)input.LT(1);
            match(input,61,FOLLOW_61_in_assattr1175); if (failed) return retval;

            }

            retval.stop = input.LT(-1);

            if ( backtracking==0 ) {
                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end assattr

    public static class callFunc_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start callFunc
    // Python.g:215:1: callFunc : CALLFUNC '(' expr '[' ( expr )* ']' NONE NONE ')' -> ^( CALLFUNC expr ^( LIST ( expr )* ) NONE NONE ) ;
    public final callFunc_return callFunc() throws RecognitionException {
        callFunc_return retval = new callFunc_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token CALLFUNC156=null;
        Token char_literal157=null;
        Token char_literal159=null;
        Token char_literal161=null;
        Token NONE162=null;
        Token NONE163=null;
        Token char_literal164=null;
        expr_return expr158 = null;

        expr_return expr160 = null;


        CommonTree CALLFUNC156_tree=null;
        CommonTree char_literal157_tree=null;
        CommonTree char_literal159_tree=null;
        CommonTree char_literal161_tree=null;
        CommonTree NONE162_tree=null;
        CommonTree NONE163_tree=null;
        CommonTree char_literal164_tree=null;
        RewriteRuleTokenStream stream_CALLFUNC=new RewriteRuleTokenStream(adaptor,"token CALLFUNC");
        RewriteRuleTokenStream stream_64=new RewriteRuleTokenStream(adaptor,"token 64");
        RewriteRuleTokenStream stream_63=new RewriteRuleTokenStream(adaptor,"token 63");
        RewriteRuleTokenStream stream_60=new RewriteRuleTokenStream(adaptor,"token 60");
        RewriteRuleTokenStream stream_NONE=new RewriteRuleTokenStream(adaptor,"token NONE");
        RewriteRuleTokenStream stream_61=new RewriteRuleTokenStream(adaptor,"token 61");
        RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");
        try {
            // Python.g:215:11: ( CALLFUNC '(' expr '[' ( expr )* ']' NONE NONE ')' -> ^( CALLFUNC expr ^( LIST ( expr )* ) NONE NONE ) )
            // Python.g:215:13: CALLFUNC '(' expr '[' ( expr )* ']' NONE NONE ')'
            {
            CALLFUNC156=(Token)input.LT(1);
            match(input,CALLFUNC,FOLLOW_CALLFUNC_in_callFunc1188); if (failed) return retval;
            if ( backtracking==0 ) stream_CALLFUNC.add(CALLFUNC156);

            char_literal157=(Token)input.LT(1);
            match(input,60,FOLLOW_60_in_callFunc1190); if (failed) return retval;
            if ( backtracking==0 ) stream_60.add(char_literal157);

            pushFollow(FOLLOW_expr_in_callFunc1192);
            expr158=expr();
            _fsp--;
            if (failed) return retval;
            if ( backtracking==0 ) stream_expr.add(expr158.getTree());
            char_literal159=(Token)input.LT(1);
            match(input,63,FOLLOW_63_in_callFunc1194); if (failed) return retval;
            if ( backtracking==0 ) stream_63.add(char_literal159);

            // Python.g:215:35: ( expr )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==CONST||LA22_0==CALLFUNC||(LA22_0>=ADD && LA22_0<=NAME)||(LA22_0>=SLICE && LA22_0<=SUBSCRIPT)||(LA22_0>=SLICEOBJ && LA22_0<=KEYWORD)) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // Python.g:0:0: expr
            	    {
            	    pushFollow(FOLLOW_expr_in_callFunc1196);
            	    expr160=expr();
            	    _fsp--;
            	    if (failed) return retval;
            	    if ( backtracking==0 ) stream_expr.add(expr160.getTree());

            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);

            char_literal161=(Token)input.LT(1);
            match(input,64,FOLLOW_64_in_callFunc1199); if (failed) return retval;
            if ( backtracking==0 ) stream_64.add(char_literal161);

            NONE162=(Token)input.LT(1);
            match(input,NONE,FOLLOW_NONE_in_callFunc1201); if (failed) return retval;
            if ( backtracking==0 ) stream_NONE.add(NONE162);

            NONE163=(Token)input.LT(1);
            match(input,NONE,FOLLOW_NONE_in_callFunc1203); if (failed) return retval;
            if ( backtracking==0 ) stream_NONE.add(NONE163);

            char_literal164=(Token)input.LT(1);
            match(input,61,FOLLOW_61_in_callFunc1205); if (failed) return retval;
            if ( backtracking==0 ) stream_61.add(char_literal164);


            // AST REWRITE
            // elements: NONE, expr, NONE, CALLFUNC, expr
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 215:59: -> ^( CALLFUNC expr ^( LIST ( expr )* ) NONE NONE )
            {
                // Python.g:215:62: ^( CALLFUNC expr ^( LIST ( expr )* ) NONE NONE )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_CALLFUNC.next(), root_1);

                adaptor.addChild(root_1, stream_expr.next());
                // Python.g:215:78: ^( LIST ( expr )* )
                {
                CommonTree root_2 = (CommonTree)adaptor.nil();
                root_2 = (CommonTree)adaptor.becomeRoot(adaptor.create(LIST, "LIST"), root_2);

                // Python.g:215:85: ( expr )*
                while ( stream_expr.hasNext() ) {
                    adaptor.addChild(root_2, stream_expr.next());

                }
                stream_expr.reset();

                adaptor.addChild(root_1, root_2);
                }
                adaptor.addChild(root_1, stream_NONE.next());
                adaptor.addChild(root_1, stream_NONE.next());

                adaptor.addChild(root_0, root_1);
                }

            }

            }

            }

            retval.stop = input.LT(-1);

            if ( backtracking==0 ) {
                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end callFunc

    public static class expr_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start expr
    // Python.g:217:1: expr : ( add | mul | sub | div | name | getattr | pyconst | callFunc | sliceobj | subscript | slice | compare | keyword );
    public final expr_return expr() throws RecognitionException {
        expr_return retval = new expr_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        add_return add165 = null;

        mul_return mul166 = null;

        sub_return sub167 = null;

        div_return div168 = null;

        name_return name169 = null;

        getattr_return getattr170 = null;

        pyconst_return pyconst171 = null;

        callFunc_return callFunc172 = null;

        sliceobj_return sliceobj173 = null;

        subscript_return subscript174 = null;

        slice_return slice175 = null;

        compare_return compare176 = null;

        keyword_return keyword177 = null;



        try {
            // Python.g:217:6: ( add | mul | sub | div | name | getattr | pyconst | callFunc | sliceobj | subscript | slice | compare | keyword )
            int alt23=13;
            switch ( input.LA(1) ) {
            case ADD:
                {
                alt23=1;
                }
                break;
            case MUL:
                {
                alt23=2;
                }
                break;
            case SUB:
                {
                alt23=3;
                }
                break;
            case DIV:
                {
                alt23=4;
                }
                break;
            case NAME:
                {
                alt23=5;
                }
                break;
            case GETATTR:
                {
                alt23=6;
                }
                break;
            case CONST:
                {
                alt23=7;
                }
                break;
            case CALLFUNC:
                {
                alt23=8;
                }
                break;
            case SLICEOBJ:
                {
                alt23=9;
                }
                break;
            case SUBSCRIPT:
                {
                alt23=10;
                }
                break;
            case SLICE:
                {
                alt23=11;
                }
                break;
            case COMPARE:
                {
                alt23=12;
                }
                break;
            case KEYWORD:
                {
                alt23=13;
                }
                break;
            default:
                if (backtracking>0) {failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("217:1: expr : ( add | mul | sub | div | name | getattr | pyconst | callFunc | sliceobj | subscript | slice | compare | keyword );", 23, 0, input);

                throw nvae;
            }

            switch (alt23) {
                case 1 :
                    // Python.g:217:8: add
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_add_in_expr1234);
                    add165=add();
                    _fsp--;
                    if (failed) return retval;
                    if ( backtracking==0 ) adaptor.addChild(root_0, add165.getTree());

                    }
                    break;
                case 2 :
                    // Python.g:218:4: mul
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_mul_in_expr1239);
                    mul166=mul();
                    _fsp--;
                    if (failed) return retval;
                    if ( backtracking==0 ) adaptor.addChild(root_0, mul166.getTree());

                    }
                    break;
                case 3 :
                    // Python.g:219:4: sub
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_sub_in_expr1244);
                    sub167=sub();
                    _fsp--;
                    if (failed) return retval;
                    if ( backtracking==0 ) adaptor.addChild(root_0, sub167.getTree());

                    }
                    break;
                case 4 :
                    // Python.g:220:9: div
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_div_in_expr1254);
                    div168=div();
                    _fsp--;
                    if (failed) return retval;
                    if ( backtracking==0 ) adaptor.addChild(root_0, div168.getTree());

                    }
                    break;
                case 5 :
                    // Python.g:221:5: name
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_name_in_expr1260);
                    name169=name();
                    _fsp--;
                    if (failed) return retval;
                    if ( backtracking==0 ) adaptor.addChild(root_0, name169.getTree());

                    }
                    break;
                case 6 :
                    // Python.g:222:4: getattr
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_getattr_in_expr1265);
                    getattr170=getattr();
                    _fsp--;
                    if (failed) return retval;
                    if ( backtracking==0 ) adaptor.addChild(root_0, getattr170.getTree());

                    }
                    break;
                case 7 :
                    // Python.g:223:4: pyconst
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_pyconst_in_expr1270);
                    pyconst171=pyconst();
                    _fsp--;
                    if (failed) return retval;
                    if ( backtracking==0 ) adaptor.addChild(root_0, pyconst171.getTree());

                    }
                    break;
                case 8 :
                    // Python.g:224:4: callFunc
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_callFunc_in_expr1275);
                    callFunc172=callFunc();
                    _fsp--;
                    if (failed) return retval;
                    if ( backtracking==0 ) adaptor.addChild(root_0, callFunc172.getTree());

                    }
                    break;
                case 9 :
                    // Python.g:225:9: sliceobj
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_sliceobj_in_expr1285);
                    sliceobj173=sliceobj();
                    _fsp--;
                    if (failed) return retval;
                    if ( backtracking==0 ) adaptor.addChild(root_0, sliceobj173.getTree());

                    }
                    break;
                case 10 :
                    // Python.g:226:9: subscript
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_subscript_in_expr1295);
                    subscript174=subscript();
                    _fsp--;
                    if (failed) return retval;
                    if ( backtracking==0 ) adaptor.addChild(root_0, subscript174.getTree());

                    }
                    break;
                case 11 :
                    // Python.g:227:9: slice
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_slice_in_expr1305);
                    slice175=slice();
                    _fsp--;
                    if (failed) return retval;
                    if ( backtracking==0 ) adaptor.addChild(root_0, slice175.getTree());

                    }
                    break;
                case 12 :
                    // Python.g:228:9: compare
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_compare_in_expr1315);
                    compare176=compare();
                    _fsp--;
                    if (failed) return retval;
                    if ( backtracking==0 ) adaptor.addChild(root_0, compare176.getTree());

                    }
                    break;
                case 13 :
                    // Python.g:229:6: keyword
                    {
                    root_0 = (CommonTree)adaptor.nil();

                    pushFollow(FOLLOW_keyword_in_expr1322);
                    keyword177=keyword();
                    _fsp--;
                    if (failed) return retval;
                    if ( backtracking==0 ) adaptor.addChild(root_0, keyword177.getTree());

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( backtracking==0 ) {
                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end expr

    public static class add_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start add
    // Python.g:232:1: add : ADD '(' '(' expr expr ')' ')' ;
    public final add_return add() throws RecognitionException {
        add_return retval = new add_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token ADD178=null;
        Token char_literal179=null;
        Token char_literal180=null;
        Token char_literal183=null;
        Token char_literal184=null;
        expr_return expr181 = null;

        expr_return expr182 = null;


        CommonTree ADD178_tree=null;
        CommonTree char_literal179_tree=null;
        CommonTree char_literal180_tree=null;
        CommonTree char_literal183_tree=null;
        CommonTree char_literal184_tree=null;

        try {
            // Python.g:232:6: ( ADD '(' '(' expr expr ')' ')' )
            // Python.g:232:8: ADD '(' '(' expr expr ')' ')'
            {
            root_0 = (CommonTree)adaptor.nil();

            ADD178=(Token)input.LT(1);
            match(input,ADD,FOLLOW_ADD_in_add1333); if (failed) return retval;
            if ( backtracking==0 ) {
            ADD178_tree = (CommonTree)adaptor.create(ADD178);
            root_0 = (CommonTree)adaptor.becomeRoot(ADD178_tree, root_0);
            }
            char_literal179=(Token)input.LT(1);
            match(input,60,FOLLOW_60_in_add1336); if (failed) return retval;
            char_literal180=(Token)input.LT(1);
            match(input,60,FOLLOW_60_in_add1339); if (failed) return retval;
            pushFollow(FOLLOW_expr_in_add1342);
            expr181=expr();
            _fsp--;
            if (failed) return retval;
            if ( backtracking==0 ) adaptor.addChild(root_0, expr181.getTree());
            pushFollow(FOLLOW_expr_in_add1344);
            expr182=expr();
            _fsp--;
            if (failed) return retval;
            if ( backtracking==0 ) adaptor.addChild(root_0, expr182.getTree());
            char_literal183=(Token)input.LT(1);
            match(input,61,FOLLOW_61_in_add1346); if (failed) return retval;
            char_literal184=(Token)input.LT(1);
            match(input,61,FOLLOW_61_in_add1349); if (failed) return retval;

            }

            retval.stop = input.LT(-1);

            if ( backtracking==0 ) {
                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end add

    public static class sub_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start sub
    // Python.g:235:1: sub : SUB '(' '(' expr expr ')' ')' ;
    public final sub_return sub() throws RecognitionException {
        sub_return retval = new sub_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token SUB185=null;
        Token char_literal186=null;
        Token char_literal187=null;
        Token char_literal190=null;
        Token char_literal191=null;
        expr_return expr188 = null;

        expr_return expr189 = null;


        CommonTree SUB185_tree=null;
        CommonTree char_literal186_tree=null;
        CommonTree char_literal187_tree=null;
        CommonTree char_literal190_tree=null;
        CommonTree char_literal191_tree=null;

        try {
            // Python.g:235:6: ( SUB '(' '(' expr expr ')' ')' )
            // Python.g:235:8: SUB '(' '(' expr expr ')' ')'
            {
            root_0 = (CommonTree)adaptor.nil();

            SUB185=(Token)input.LT(1);
            match(input,SUB,FOLLOW_SUB_in_sub1361); if (failed) return retval;
            if ( backtracking==0 ) {
            SUB185_tree = (CommonTree)adaptor.create(SUB185);
            root_0 = (CommonTree)adaptor.becomeRoot(SUB185_tree, root_0);
            }
            char_literal186=(Token)input.LT(1);
            match(input,60,FOLLOW_60_in_sub1364); if (failed) return retval;
            char_literal187=(Token)input.LT(1);
            match(input,60,FOLLOW_60_in_sub1367); if (failed) return retval;
            pushFollow(FOLLOW_expr_in_sub1370);
            expr188=expr();
            _fsp--;
            if (failed) return retval;
            if ( backtracking==0 ) adaptor.addChild(root_0, expr188.getTree());
            pushFollow(FOLLOW_expr_in_sub1372);
            expr189=expr();
            _fsp--;
            if (failed) return retval;
            if ( backtracking==0 ) adaptor.addChild(root_0, expr189.getTree());
            char_literal190=(Token)input.LT(1);
            match(input,61,FOLLOW_61_in_sub1374); if (failed) return retval;
            char_literal191=(Token)input.LT(1);
            match(input,61,FOLLOW_61_in_sub1377); if (failed) return retval;

            }

            retval.stop = input.LT(-1);

            if ( backtracking==0 ) {
                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end sub

    public static class mul_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start mul
    // Python.g:238:1: mul : MUL '(' '(' expr expr ')' ')' ;
    public final mul_return mul() throws RecognitionException {
        mul_return retval = new mul_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token MUL192=null;
        Token char_literal193=null;
        Token char_literal194=null;
        Token char_literal197=null;
        Token char_literal198=null;
        expr_return expr195 = null;

        expr_return expr196 = null;


        CommonTree MUL192_tree=null;
        CommonTree char_literal193_tree=null;
        CommonTree char_literal194_tree=null;
        CommonTree char_literal197_tree=null;
        CommonTree char_literal198_tree=null;

        try {
            // Python.g:238:5: ( MUL '(' '(' expr expr ')' ')' )
            // Python.g:238:7: MUL '(' '(' expr expr ')' ')'
            {
            root_0 = (CommonTree)adaptor.nil();

            MUL192=(Token)input.LT(1);
            match(input,MUL,FOLLOW_MUL_in_mul1388); if (failed) return retval;
            if ( backtracking==0 ) {
            MUL192_tree = (CommonTree)adaptor.create(MUL192);
            root_0 = (CommonTree)adaptor.becomeRoot(MUL192_tree, root_0);
            }
            char_literal193=(Token)input.LT(1);
            match(input,60,FOLLOW_60_in_mul1391); if (failed) return retval;
            char_literal194=(Token)input.LT(1);
            match(input,60,FOLLOW_60_in_mul1394); if (failed) return retval;
            pushFollow(FOLLOW_expr_in_mul1397);
            expr195=expr();
            _fsp--;
            if (failed) return retval;
            if ( backtracking==0 ) adaptor.addChild(root_0, expr195.getTree());
            pushFollow(FOLLOW_expr_in_mul1399);
            expr196=expr();
            _fsp--;
            if (failed) return retval;
            if ( backtracking==0 ) adaptor.addChild(root_0, expr196.getTree());
            char_literal197=(Token)input.LT(1);
            match(input,61,FOLLOW_61_in_mul1401); if (failed) return retval;
            char_literal198=(Token)input.LT(1);
            match(input,61,FOLLOW_61_in_mul1404); if (failed) return retval;

            }

            retval.stop = input.LT(-1);

            if ( backtracking==0 ) {
                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end mul

    public static class div_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start div
    // Python.g:240:1: div : DIV '(' '(' expr expr ')' ')' ;
    public final div_return div() throws RecognitionException {
        div_return retval = new div_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token DIV199=null;
        Token char_literal200=null;
        Token char_literal201=null;
        Token char_literal204=null;
        Token char_literal205=null;
        expr_return expr202 = null;

        expr_return expr203 = null;


        CommonTree DIV199_tree=null;
        CommonTree char_literal200_tree=null;
        CommonTree char_literal201_tree=null;
        CommonTree char_literal204_tree=null;
        CommonTree char_literal205_tree=null;

        try {
            // Python.g:240:5: ( DIV '(' '(' expr expr ')' ')' )
            // Python.g:240:9: DIV '(' '(' expr expr ')' ')'
            {
            root_0 = (CommonTree)adaptor.nil();

            DIV199=(Token)input.LT(1);
            match(input,DIV,FOLLOW_DIV_in_div1416); if (failed) return retval;
            if ( backtracking==0 ) {
            DIV199_tree = (CommonTree)adaptor.create(DIV199);
            root_0 = (CommonTree)adaptor.becomeRoot(DIV199_tree, root_0);
            }
            char_literal200=(Token)input.LT(1);
            match(input,60,FOLLOW_60_in_div1419); if (failed) return retval;
            char_literal201=(Token)input.LT(1);
            match(input,60,FOLLOW_60_in_div1422); if (failed) return retval;
            pushFollow(FOLLOW_expr_in_div1425);
            expr202=expr();
            _fsp--;
            if (failed) return retval;
            if ( backtracking==0 ) adaptor.addChild(root_0, expr202.getTree());
            pushFollow(FOLLOW_expr_in_div1427);
            expr203=expr();
            _fsp--;
            if (failed) return retval;
            if ( backtracking==0 ) adaptor.addChild(root_0, expr203.getTree());
            char_literal204=(Token)input.LT(1);
            match(input,61,FOLLOW_61_in_div1429); if (failed) return retval;
            char_literal205=(Token)input.LT(1);
            match(input,61,FOLLOW_61_in_div1432); if (failed) return retval;

            }

            retval.stop = input.LT(-1);

            if ( backtracking==0 ) {
                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end div

    public static class compare_op_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start compare_op
    // Python.g:242:1: compare_op : ( CGT | CLT | CEQ | CGTE | CLTE );
    public final compare_op_return compare_op() throws RecognitionException {
        compare_op_return retval = new compare_op_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token set206=null;

        CommonTree set206_tree=null;

        try {
            // Python.g:242:12: ( CGT | CLT | CEQ | CGTE | CLTE )
            // Python.g:
            {
            root_0 = (CommonTree)adaptor.nil();

            set206=(Token)input.LT(1);
            if ( (input.LA(1)>=CGT && input.LA(1)<=CEQ)||(input.LA(1)>=CGTE && input.LA(1)<=CLTE) ) {
                input.consume();
                if ( backtracking==0 ) adaptor.addChild(root_0, adaptor.create(set206));
                errorRecovery=false;failed=false;
            }
            else {
                if (backtracking>0) {failed=true; return retval;}
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recoverFromMismatchedSet(input,mse,FOLLOW_set_in_compare_op0);    throw mse;
            }


            }

            retval.stop = input.LT(-1);

            if ( backtracking==0 ) {
                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end compare_op

    public static class compare_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start compare
    // Python.g:244:1: compare : COMPARE '(' expr '[' '(' compare_op expr ')' ']' ')' ;
    public final compare_return compare() throws RecognitionException {
        compare_return retval = new compare_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token COMPARE207=null;
        Token char_literal208=null;
        Token char_literal210=null;
        Token char_literal211=null;
        Token char_literal214=null;
        Token char_literal215=null;
        Token char_literal216=null;
        expr_return expr209 = null;

        compare_op_return compare_op212 = null;

        expr_return expr213 = null;


        CommonTree COMPARE207_tree=null;
        CommonTree char_literal208_tree=null;
        CommonTree char_literal210_tree=null;
        CommonTree char_literal211_tree=null;
        CommonTree char_literal214_tree=null;
        CommonTree char_literal215_tree=null;
        CommonTree char_literal216_tree=null;

        try {
            // Python.g:244:9: ( COMPARE '(' expr '[' '(' compare_op expr ')' ']' ')' )
            // Python.g:244:13: COMPARE '(' expr '[' '(' compare_op expr ')' ']' ')'
            {
            root_0 = (CommonTree)adaptor.nil();

            COMPARE207=(Token)input.LT(1);
            match(input,COMPARE,FOLLOW_COMPARE_in_compare1472); if (failed) return retval;
            if ( backtracking==0 ) {
            COMPARE207_tree = (CommonTree)adaptor.create(COMPARE207);
            root_0 = (CommonTree)adaptor.becomeRoot(COMPARE207_tree, root_0);
            }
            char_literal208=(Token)input.LT(1);
            match(input,60,FOLLOW_60_in_compare1475); if (failed) return retval;
            pushFollow(FOLLOW_expr_in_compare1478);
            expr209=expr();
            _fsp--;
            if (failed) return retval;
            if ( backtracking==0 ) adaptor.addChild(root_0, expr209.getTree());
            char_literal210=(Token)input.LT(1);
            match(input,63,FOLLOW_63_in_compare1480); if (failed) return retval;
            char_literal211=(Token)input.LT(1);
            match(input,60,FOLLOW_60_in_compare1483); if (failed) return retval;
            pushFollow(FOLLOW_compare_op_in_compare1486);
            compare_op212=compare_op();
            _fsp--;
            if (failed) return retval;
            if ( backtracking==0 ) adaptor.addChild(root_0, compare_op212.getTree());
            pushFollow(FOLLOW_expr_in_compare1488);
            expr213=expr();
            _fsp--;
            if (failed) return retval;
            if ( backtracking==0 ) adaptor.addChild(root_0, expr213.getTree());
            char_literal214=(Token)input.LT(1);
            match(input,61,FOLLOW_61_in_compare1490); if (failed) return retval;
            char_literal215=(Token)input.LT(1);
            match(input,64,FOLLOW_64_in_compare1493); if (failed) return retval;
            char_literal216=(Token)input.LT(1);
            match(input,61,FOLLOW_61_in_compare1496); if (failed) return retval;

            }

            retval.stop = input.LT(-1);

            if ( backtracking==0 ) {
                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end compare

    public static class name_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start name
    // Python.g:246:1: name : NAME '(' id ')' ;
    public final name_return name() throws RecognitionException {
        name_return retval = new name_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token NAME217=null;
        Token char_literal218=null;
        Token char_literal220=null;
        id_return id219 = null;


        CommonTree NAME217_tree=null;
        CommonTree char_literal218_tree=null;
        CommonTree char_literal220_tree=null;

        try {
            // Python.g:246:6: ( NAME '(' id ')' )
            // Python.g:246:8: NAME '(' id ')'
            {
            root_0 = (CommonTree)adaptor.nil();

            NAME217=(Token)input.LT(1);
            match(input,NAME,FOLLOW_NAME_in_name1510); if (failed) return retval;
            if ( backtracking==0 ) {
            NAME217_tree = (CommonTree)adaptor.create(NAME217);
            root_0 = (CommonTree)adaptor.becomeRoot(NAME217_tree, root_0);
            }
            char_literal218=(Token)input.LT(1);
            match(input,60,FOLLOW_60_in_name1513); if (failed) return retval;
            pushFollow(FOLLOW_id_in_name1516);
            id219=id();
            _fsp--;
            if (failed) return retval;
            if ( backtracking==0 ) adaptor.addChild(root_0, id219.getTree());
            char_literal220=(Token)input.LT(1);
            match(input,61,FOLLOW_61_in_name1518); if (failed) return retval;

            }

            retval.stop = input.LT(-1);

            if ( backtracking==0 ) {
                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end name

    public static class getattr_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start getattr
    // Python.g:249:1: getattr : GETATTR '(' expr id ')' ;
    public final getattr_return getattr() throws RecognitionException {
        getattr_return retval = new getattr_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token GETATTR221=null;
        Token char_literal222=null;
        Token char_literal225=null;
        expr_return expr223 = null;

        id_return id224 = null;


        CommonTree GETATTR221_tree=null;
        CommonTree char_literal222_tree=null;
        CommonTree char_literal225_tree=null;

        try {
            // Python.g:249:9: ( GETATTR '(' expr id ')' )
            // Python.g:249:11: GETATTR '(' expr id ')'
            {
            root_0 = (CommonTree)adaptor.nil();

            GETATTR221=(Token)input.LT(1);
            match(input,GETATTR,FOLLOW_GETATTR_in_getattr1530); if (failed) return retval;
            if ( backtracking==0 ) {
            GETATTR221_tree = (CommonTree)adaptor.create(GETATTR221);
            root_0 = (CommonTree)adaptor.becomeRoot(GETATTR221_tree, root_0);
            }
            char_literal222=(Token)input.LT(1);
            match(input,60,FOLLOW_60_in_getattr1533); if (failed) return retval;
            pushFollow(FOLLOW_expr_in_getattr1535);
            expr223=expr();
            _fsp--;
            if (failed) return retval;
            if ( backtracking==0 ) adaptor.addChild(root_0, expr223.getTree());
            pushFollow(FOLLOW_id_in_getattr1537);
            id224=id();
            _fsp--;
            if (failed) return retval;
            if ( backtracking==0 ) adaptor.addChild(root_0, id224.getTree());
            char_literal225=(Token)input.LT(1);
            match(input,61,FOLLOW_61_in_getattr1539); if (failed) return retval;

            }

            retval.stop = input.LT(-1);

            if ( backtracking==0 ) {
                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end getattr

    public static class keyword_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start keyword
    // Python.g:252:1: keyword : KEYWORD '(' id expr ')' ;
    public final keyword_return keyword() throws RecognitionException {
        keyword_return retval = new keyword_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token KEYWORD226=null;
        Token char_literal227=null;
        Token char_literal230=null;
        id_return id228 = null;

        expr_return expr229 = null;


        CommonTree KEYWORD226_tree=null;
        CommonTree char_literal227_tree=null;
        CommonTree char_literal230_tree=null;

        try {
            // Python.g:252:9: ( KEYWORD '(' id expr ')' )
            // Python.g:252:12: KEYWORD '(' id expr ')'
            {
            root_0 = (CommonTree)adaptor.nil();

            KEYWORD226=(Token)input.LT(1);
            match(input,KEYWORD,FOLLOW_KEYWORD_in_keyword1551); if (failed) return retval;
            if ( backtracking==0 ) {
            KEYWORD226_tree = (CommonTree)adaptor.create(KEYWORD226);
            root_0 = (CommonTree)adaptor.becomeRoot(KEYWORD226_tree, root_0);
            }
            char_literal227=(Token)input.LT(1);
            match(input,60,FOLLOW_60_in_keyword1554); if (failed) return retval;
            pushFollow(FOLLOW_id_in_keyword1557);
            id228=id();
            _fsp--;
            if (failed) return retval;
            if ( backtracking==0 ) adaptor.addChild(root_0, id228.getTree());
            pushFollow(FOLLOW_expr_in_keyword1559);
            expr229=expr();
            _fsp--;
            if (failed) return retval;
            if ( backtracking==0 ) adaptor.addChild(root_0, expr229.getTree());
            char_literal230=(Token)input.LT(1);
            match(input,61,FOLLOW_61_in_keyword1561); if (failed) return retval;

            }

            retval.stop = input.LT(-1);

            if ( backtracking==0 ) {
                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end keyword

    public static class id_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start id
    // Python.g:254:1: id : '\\'' ID '\\'' ;
    public final id_return id() throws RecognitionException {
        id_return retval = new id_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token char_literal231=null;
        Token ID232=null;
        Token char_literal233=null;

        CommonTree char_literal231_tree=null;
        CommonTree ID232_tree=null;
        CommonTree char_literal233_tree=null;

        try {
            // Python.g:254:5: ( '\\'' ID '\\'' )
            // Python.g:254:7: '\\'' ID '\\''
            {
            root_0 = (CommonTree)adaptor.nil();

            char_literal231=(Token)input.LT(1);
            match(input,62,FOLLOW_62_in_id1572); if (failed) return retval;
            ID232=(Token)input.LT(1);
            match(input,ID,FOLLOW_ID_in_id1575); if (failed) return retval;
            if ( backtracking==0 ) {
            ID232_tree = (CommonTree)adaptor.create(ID232);
            adaptor.addChild(root_0, ID232_tree);
            }
            char_literal233=(Token)input.LT(1);
            match(input,62,FOLLOW_62_in_id1577); if (failed) return retval;

            }

            retval.stop = input.LT(-1);

            if ( backtracking==0 ) {
                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end id

    public static class subscript_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start subscript
    // Python.g:257:1: subscript : ( SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) ) | SUBSCRIPT '(' expr '\\'OP_APPLY\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) ) );
    public final subscript_return subscript() throws RecognitionException {
        subscript_return retval = new subscript_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token SUBSCRIPT234=null;
        Token char_literal235=null;
        Token string_literal237=null;
        Token char_literal238=null;
        Token char_literal240=null;
        Token char_literal241=null;
        Token SUBSCRIPT242=null;
        Token char_literal243=null;
        Token string_literal245=null;
        Token char_literal246=null;
        Token char_literal248=null;
        Token char_literal249=null;
        expr_return expr236 = null;

        expr_return expr239 = null;

        expr_return expr244 = null;

        expr_return expr247 = null;


        CommonTree SUBSCRIPT234_tree=null;
        CommonTree char_literal235_tree=null;
        CommonTree string_literal237_tree=null;
        CommonTree char_literal238_tree=null;
        CommonTree char_literal240_tree=null;
        CommonTree char_literal241_tree=null;
        CommonTree SUBSCRIPT242_tree=null;
        CommonTree char_literal243_tree=null;
        CommonTree string_literal245_tree=null;
        CommonTree char_literal246_tree=null;
        CommonTree char_literal248_tree=null;
        CommonTree char_literal249_tree=null;
        RewriteRuleTokenStream stream_67=new RewriteRuleTokenStream(adaptor,"token 67");
        RewriteRuleTokenStream stream_68=new RewriteRuleTokenStream(adaptor,"token 68");
        RewriteRuleTokenStream stream_64=new RewriteRuleTokenStream(adaptor,"token 64");
        RewriteRuleTokenStream stream_SUBSCRIPT=new RewriteRuleTokenStream(adaptor,"token SUBSCRIPT");
        RewriteRuleTokenStream stream_63=new RewriteRuleTokenStream(adaptor,"token 63");
        RewriteRuleTokenStream stream_60=new RewriteRuleTokenStream(adaptor,"token 60");
        RewriteRuleTokenStream stream_61=new RewriteRuleTokenStream(adaptor,"token 61");
        RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");
        try {
            // Python.g:257:11: ( SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) ) | SUBSCRIPT '(' expr '\\'OP_APPLY\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) ) )
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==SUBSCRIPT) ) {
                int LA26_1 = input.LA(2);

                if ( (LA26_1==60) ) {
                    switch ( input.LA(3) ) {
                    case ADD:
                        {
                        int LA26_3 = input.LA(4);

                        if ( (LA26_3==60) ) {
                            int LA26_16 = input.LA(5);

                            if ( (LA26_16==60) ) {
                                int LA26_29 = input.LA(6);

                                if ( (synpred52()) ) {
                                    alt26=1;
                                }
                                else if ( (true) ) {
                                    alt26=2;
                                }
                                else {
                                    if (backtracking>0) {failed=true; return retval;}
                                    NoViableAltException nvae =
                                        new NoViableAltException("257:1: subscript : ( SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) ) | SUBSCRIPT '(' expr '\\'OP_APPLY\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) ) );", 26, 29, input);

                                    throw nvae;
                                }
                            }
                            else {
                                if (backtracking>0) {failed=true; return retval;}
                                NoViableAltException nvae =
                                    new NoViableAltException("257:1: subscript : ( SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) ) | SUBSCRIPT '(' expr '\\'OP_APPLY\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) ) );", 26, 16, input);

                                throw nvae;
                            }
                        }
                        else {
                            if (backtracking>0) {failed=true; return retval;}
                            NoViableAltException nvae =
                                new NoViableAltException("257:1: subscript : ( SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) ) | SUBSCRIPT '(' expr '\\'OP_APPLY\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) ) );", 26, 3, input);

                            throw nvae;
                        }
                        }
                        break;
                    case MUL:
                        {
                        int LA26_4 = input.LA(4);

                        if ( (LA26_4==60) ) {
                            int LA26_17 = input.LA(5);

                            if ( (LA26_17==60) ) {
                                int LA26_30 = input.LA(6);

                                if ( (synpred52()) ) {
                                    alt26=1;
                                }
                                else if ( (true) ) {
                                    alt26=2;
                                }
                                else {
                                    if (backtracking>0) {failed=true; return retval;}
                                    NoViableAltException nvae =
                                        new NoViableAltException("257:1: subscript : ( SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) ) | SUBSCRIPT '(' expr '\\'OP_APPLY\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) ) );", 26, 30, input);

                                    throw nvae;
                                }
                            }
                            else {
                                if (backtracking>0) {failed=true; return retval;}
                                NoViableAltException nvae =
                                    new NoViableAltException("257:1: subscript : ( SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) ) | SUBSCRIPT '(' expr '\\'OP_APPLY\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) ) );", 26, 17, input);

                                throw nvae;
                            }
                        }
                        else {
                            if (backtracking>0) {failed=true; return retval;}
                            NoViableAltException nvae =
                                new NoViableAltException("257:1: subscript : ( SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) ) | SUBSCRIPT '(' expr '\\'OP_APPLY\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) ) );", 26, 4, input);

                            throw nvae;
                        }
                        }
                        break;
                    case SUB:
                        {
                        int LA26_5 = input.LA(4);

                        if ( (LA26_5==60) ) {
                            int LA26_18 = input.LA(5);

                            if ( (LA26_18==60) ) {
                                int LA26_31 = input.LA(6);

                                if ( (synpred52()) ) {
                                    alt26=1;
                                }
                                else if ( (true) ) {
                                    alt26=2;
                                }
                                else {
                                    if (backtracking>0) {failed=true; return retval;}
                                    NoViableAltException nvae =
                                        new NoViableAltException("257:1: subscript : ( SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) ) | SUBSCRIPT '(' expr '\\'OP_APPLY\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) ) );", 26, 31, input);

                                    throw nvae;
                                }
                            }
                            else {
                                if (backtracking>0) {failed=true; return retval;}
                                NoViableAltException nvae =
                                    new NoViableAltException("257:1: subscript : ( SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) ) | SUBSCRIPT '(' expr '\\'OP_APPLY\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) ) );", 26, 18, input);

                                throw nvae;
                            }
                        }
                        else {
                            if (backtracking>0) {failed=true; return retval;}
                            NoViableAltException nvae =
                                new NoViableAltException("257:1: subscript : ( SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) ) | SUBSCRIPT '(' expr '\\'OP_APPLY\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) ) );", 26, 5, input);

                            throw nvae;
                        }
                        }
                        break;
                    case DIV:
                        {
                        int LA26_6 = input.LA(4);

                        if ( (LA26_6==60) ) {
                            int LA26_19 = input.LA(5);

                            if ( (LA26_19==60) ) {
                                int LA26_32 = input.LA(6);

                                if ( (synpred52()) ) {
                                    alt26=1;
                                }
                                else if ( (true) ) {
                                    alt26=2;
                                }
                                else {
                                    if (backtracking>0) {failed=true; return retval;}
                                    NoViableAltException nvae =
                                        new NoViableAltException("257:1: subscript : ( SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) ) | SUBSCRIPT '(' expr '\\'OP_APPLY\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) ) );", 26, 32, input);

                                    throw nvae;
                                }
                            }
                            else {
                                if (backtracking>0) {failed=true; return retval;}
                                NoViableAltException nvae =
                                    new NoViableAltException("257:1: subscript : ( SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) ) | SUBSCRIPT '(' expr '\\'OP_APPLY\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) ) );", 26, 19, input);

                                throw nvae;
                            }
                        }
                        else {
                            if (backtracking>0) {failed=true; return retval;}
                            NoViableAltException nvae =
                                new NoViableAltException("257:1: subscript : ( SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) ) | SUBSCRIPT '(' expr '\\'OP_APPLY\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) ) );", 26, 6, input);

                            throw nvae;
                        }
                        }
                        break;
                    case NAME:
                        {
                        int LA26_7 = input.LA(4);

                        if ( (LA26_7==60) ) {
                            int LA26_20 = input.LA(5);

                            if ( (LA26_20==62) ) {
                                int LA26_33 = input.LA(6);

                                if ( (LA26_33==ID) ) {
                                    int LA26_57 = input.LA(7);

                                    if ( (LA26_57==62) ) {
                                        int LA26_65 = input.LA(8);

                                        if ( (LA26_65==61) ) {
                                            int LA26_70 = input.LA(9);

                                            if ( (LA26_70==67) ) {
                                                alt26=1;
                                            }
                                            else if ( (LA26_70==68) ) {
                                                alt26=2;
                                            }
                                            else {
                                                if (backtracking>0) {failed=true; return retval;}
                                                NoViableAltException nvae =
                                                    new NoViableAltException("257:1: subscript : ( SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) ) | SUBSCRIPT '(' expr '\\'OP_APPLY\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) ) );", 26, 70, input);

                                                throw nvae;
                                            }
                                        }
                                        else {
                                            if (backtracking>0) {failed=true; return retval;}
                                            NoViableAltException nvae =
                                                new NoViableAltException("257:1: subscript : ( SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) ) | SUBSCRIPT '(' expr '\\'OP_APPLY\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) ) );", 26, 65, input);

                                            throw nvae;
                                        }
                                    }
                                    else {
                                        if (backtracking>0) {failed=true; return retval;}
                                        NoViableAltException nvae =
                                            new NoViableAltException("257:1: subscript : ( SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) ) | SUBSCRIPT '(' expr '\\'OP_APPLY\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) ) );", 26, 57, input);

                                        throw nvae;
                                    }
                                }
                                else {
                                    if (backtracking>0) {failed=true; return retval;}
                                    NoViableAltException nvae =
                                        new NoViableAltException("257:1: subscript : ( SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) ) | SUBSCRIPT '(' expr '\\'OP_APPLY\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) ) );", 26, 33, input);

                                    throw nvae;
                                }
                            }
                            else {
                                if (backtracking>0) {failed=true; return retval;}
                                NoViableAltException nvae =
                                    new NoViableAltException("257:1: subscript : ( SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) ) | SUBSCRIPT '(' expr '\\'OP_APPLY\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) ) );", 26, 20, input);

                                throw nvae;
                            }
                        }
                        else {
                            if (backtracking>0) {failed=true; return retval;}
                            NoViableAltException nvae =
                                new NoViableAltException("257:1: subscript : ( SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) ) | SUBSCRIPT '(' expr '\\'OP_APPLY\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) ) );", 26, 7, input);

                            throw nvae;
                        }
                        }
                        break;
                    case GETATTR:
                        {
                        int LA26_8 = input.LA(4);

                        if ( (LA26_8==60) ) {
                            int LA26_21 = input.LA(5);

                            if ( (synpred52()) ) {
                                alt26=1;
                            }
                            else if ( (true) ) {
                                alt26=2;
                            }
                            else {
                                if (backtracking>0) {failed=true; return retval;}
                                NoViableAltException nvae =
                                    new NoViableAltException("257:1: subscript : ( SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) ) | SUBSCRIPT '(' expr '\\'OP_APPLY\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) ) );", 26, 21, input);

                                throw nvae;
                            }
                        }
                        else {
                            if (backtracking>0) {failed=true; return retval;}
                            NoViableAltException nvae =
                                new NoViableAltException("257:1: subscript : ( SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) ) | SUBSCRIPT '(' expr '\\'OP_APPLY\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) ) );", 26, 8, input);

                            throw nvae;
                        }
                        }
                        break;
                    case CONST:
                        {
                        int LA26_9 = input.LA(4);

                        if ( (LA26_9==60) ) {
                            switch ( input.LA(5) ) {
                            case 62:
                                {
                                int LA26_36 = input.LA(6);

                                if ( (LA26_36==PYSTR) ) {
                                    int LA26_58 = input.LA(7);

                                    if ( (LA26_58==60) ) {
                                        int LA26_66 = input.LA(8);

                                        if ( (LA26_66==INT) ) {
                                            int LA26_71 = input.LA(9);

                                            if ( (LA26_71==61) ) {
                                                int LA26_72 = input.LA(10);

                                                if ( (LA26_72==62) ) {
                                                    int LA26_73 = input.LA(11);

                                                    if ( (LA26_73==61) ) {
                                                        int LA26_59 = input.LA(12);

                                                        if ( (LA26_59==68) ) {
                                                            alt26=2;
                                                        }
                                                        else if ( (LA26_59==67) ) {
                                                            alt26=1;
                                                        }
                                                        else {
                                                            if (backtracking>0) {failed=true; return retval;}
                                                            NoViableAltException nvae =
                                                                new NoViableAltException("257:1: subscript : ( SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) ) | SUBSCRIPT '(' expr '\\'OP_APPLY\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) ) );", 26, 59, input);

                                                            throw nvae;
                                                        }
                                                    }
                                                    else {
                                                        if (backtracking>0) {failed=true; return retval;}
                                                        NoViableAltException nvae =
                                                            new NoViableAltException("257:1: subscript : ( SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) ) | SUBSCRIPT '(' expr '\\'OP_APPLY\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) ) );", 26, 73, input);

                                                        throw nvae;
                                                    }
                                                }
                                                else {
                                                    if (backtracking>0) {failed=true; return retval;}
                                                    NoViableAltException nvae =
                                                        new NoViableAltException("257:1: subscript : ( SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) ) | SUBSCRIPT '(' expr '\\'OP_APPLY\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) ) );", 26, 72, input);

                                                    throw nvae;
                                                }
                                            }
                                            else {
                                                if (backtracking>0) {failed=true; return retval;}
                                                NoViableAltException nvae =
                                                    new NoViableAltException("257:1: subscript : ( SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) ) | SUBSCRIPT '(' expr '\\'OP_APPLY\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) ) );", 26, 71, input);

                                                throw nvae;
                                            }
                                        }
                                        else {
                                            if (backtracking>0) {failed=true; return retval;}
                                            NoViableAltException nvae =
                                                new NoViableAltException("257:1: subscript : ( SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) ) | SUBSCRIPT '(' expr '\\'OP_APPLY\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) ) );", 26, 66, input);

                                            throw nvae;
                                        }
                                    }
                                    else {
                                        if (backtracking>0) {failed=true; return retval;}
                                        NoViableAltException nvae =
                                            new NoViableAltException("257:1: subscript : ( SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) ) | SUBSCRIPT '(' expr '\\'OP_APPLY\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) ) );", 26, 58, input);

                                        throw nvae;
                                    }
                                }
                                else {
                                    if (backtracking>0) {failed=true; return retval;}
                                    NoViableAltException nvae =
                                        new NoViableAltException("257:1: subscript : ( SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) ) | SUBSCRIPT '(' expr '\\'OP_APPLY\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) ) );", 26, 36, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case INT:
                                {
                                int LA26_37 = input.LA(6);

                                if ( (LA26_37==61) ) {
                                    int LA26_59 = input.LA(7);

                                    if ( (LA26_59==68) ) {
                                        alt26=2;
                                    }
                                    else if ( (LA26_59==67) ) {
                                        alt26=1;
                                    }
                                    else {
                                        if (backtracking>0) {failed=true; return retval;}
                                        NoViableAltException nvae =
                                            new NoViableAltException("257:1: subscript : ( SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) ) | SUBSCRIPT '(' expr '\\'OP_APPLY\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) ) );", 26, 59, input);

                                        throw nvae;
                                    }
                                }
                                else {
                                    if (backtracking>0) {failed=true; return retval;}
                                    NoViableAltException nvae =
                                        new NoViableAltException("257:1: subscript : ( SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) ) | SUBSCRIPT '(' expr '\\'OP_APPLY\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) ) );", 26, 37, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case FLOAT:
                                {
                                int LA26_38 = input.LA(6);

                                if ( (LA26_38==61) ) {
                                    int LA26_59 = input.LA(7);

                                    if ( (LA26_59==68) ) {
                                        alt26=2;
                                    }
                                    else if ( (LA26_59==67) ) {
                                        alt26=1;
                                    }
                                    else {
                                        if (backtracking>0) {failed=true; return retval;}
                                        NoViableAltException nvae =
                                            new NoViableAltException("257:1: subscript : ( SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) ) | SUBSCRIPT '(' expr '\\'OP_APPLY\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) ) );", 26, 59, input);

                                        throw nvae;
                                    }
                                }
                                else {
                                    if (backtracking>0) {failed=true; return retval;}
                                    NoViableAltException nvae =
                                        new NoViableAltException("257:1: subscript : ( SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) ) | SUBSCRIPT '(' expr '\\'OP_APPLY\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) ) );", 26, 38, input);

                                    throw nvae;
                                }
                                }
                                break;
                            case NONE:
                                {
                                int LA26_39 = input.LA(6);

                                if ( (LA26_39==61) ) {
                                    int LA26_59 = input.LA(7);

                                    if ( (LA26_59==68) ) {
                                        alt26=2;
                                    }
                                    else if ( (LA26_59==67) ) {
                                        alt26=1;
                                    }
                                    else {
                                        if (backtracking>0) {failed=true; return retval;}
                                        NoViableAltException nvae =
                                            new NoViableAltException("257:1: subscript : ( SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) ) | SUBSCRIPT '(' expr '\\'OP_APPLY\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) ) );", 26, 59, input);

                                        throw nvae;
                                    }
                                }
                                else {
                                    if (backtracking>0) {failed=true; return retval;}
                                    NoViableAltException nvae =
                                        new NoViableAltException("257:1: subscript : ( SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) ) | SUBSCRIPT '(' expr '\\'OP_APPLY\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) ) );", 26, 39, input);

                                    throw nvae;
                                }
                                }
                                break;
                            default:
                                if (backtracking>0) {failed=true; return retval;}
                                NoViableAltException nvae =
                                    new NoViableAltException("257:1: subscript : ( SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) ) | SUBSCRIPT '(' expr '\\'OP_APPLY\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) ) );", 26, 22, input);

                                throw nvae;
                            }

                        }
                        else {
                            if (backtracking>0) {failed=true; return retval;}
                            NoViableAltException nvae =
                                new NoViableAltException("257:1: subscript : ( SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) ) | SUBSCRIPT '(' expr '\\'OP_APPLY\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) ) );", 26, 9, input);

                            throw nvae;
                        }
                        }
                        break;
                    case CALLFUNC:
                        {
                        int LA26_10 = input.LA(4);

                        if ( (LA26_10==60) ) {
                            int LA26_23 = input.LA(5);

                            if ( (synpred52()) ) {
                                alt26=1;
                            }
                            else if ( (true) ) {
                                alt26=2;
                            }
                            else {
                                if (backtracking>0) {failed=true; return retval;}
                                NoViableAltException nvae =
                                    new NoViableAltException("257:1: subscript : ( SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) ) | SUBSCRIPT '(' expr '\\'OP_APPLY\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) ) );", 26, 23, input);

                                throw nvae;
                            }
                        }
                        else {
                            if (backtracking>0) {failed=true; return retval;}
                            NoViableAltException nvae =
                                new NoViableAltException("257:1: subscript : ( SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) ) | SUBSCRIPT '(' expr '\\'OP_APPLY\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) ) );", 26, 10, input);

                            throw nvae;
                        }
                        }
                        break;
                    case SLICEOBJ:
                        {
                        int LA26_11 = input.LA(4);

                        if ( (LA26_11==60) ) {
                            int LA26_24 = input.LA(5);

                            if ( (LA26_24==63) ) {
                                int LA26_42 = input.LA(6);

                                if ( (synpred52()) ) {
                                    alt26=1;
                                }
                                else if ( (true) ) {
                                    alt26=2;
                                }
                                else {
                                    if (backtracking>0) {failed=true; return retval;}
                                    NoViableAltException nvae =
                                        new NoViableAltException("257:1: subscript : ( SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) ) | SUBSCRIPT '(' expr '\\'OP_APPLY\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) ) );", 26, 42, input);

                                    throw nvae;
                                }
                            }
                            else {
                                if (backtracking>0) {failed=true; return retval;}
                                NoViableAltException nvae =
                                    new NoViableAltException("257:1: subscript : ( SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) ) | SUBSCRIPT '(' expr '\\'OP_APPLY\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) ) );", 26, 24, input);

                                throw nvae;
                            }
                        }
                        else {
                            if (backtracking>0) {failed=true; return retval;}
                            NoViableAltException nvae =
                                new NoViableAltException("257:1: subscript : ( SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) ) | SUBSCRIPT '(' expr '\\'OP_APPLY\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) ) );", 26, 11, input);

                            throw nvae;
                        }
                        }
                        break;
                    case SUBSCRIPT:
                        {
                        int LA26_12 = input.LA(4);

                        if ( (LA26_12==60) ) {
                            int LA26_25 = input.LA(5);

                            if ( (synpred52()) ) {
                                alt26=1;
                            }
                            else if ( (true) ) {
                                alt26=2;
                            }
                            else {
                                if (backtracking>0) {failed=true; return retval;}
                                NoViableAltException nvae =
                                    new NoViableAltException("257:1: subscript : ( SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) ) | SUBSCRIPT '(' expr '\\'OP_APPLY\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) ) );", 26, 25, input);

                                throw nvae;
                            }
                        }
                        else {
                            if (backtracking>0) {failed=true; return retval;}
                            NoViableAltException nvae =
                                new NoViableAltException("257:1: subscript : ( SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) ) | SUBSCRIPT '(' expr '\\'OP_APPLY\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) ) );", 26, 12, input);

                            throw nvae;
                        }
                        }
                        break;
                    case SLICE:
                        {
                        int LA26_13 = input.LA(4);

                        if ( (LA26_13==60) ) {
                            int LA26_26 = input.LA(5);

                            if ( (LA26_26==63) ) {
                                int LA26_45 = input.LA(6);

                                if ( (synpred52()) ) {
                                    alt26=1;
                                }
                                else if ( (true) ) {
                                    alt26=2;
                                }
                                else {
                                    if (backtracking>0) {failed=true; return retval;}
                                    NoViableAltException nvae =
                                        new NoViableAltException("257:1: subscript : ( SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) ) | SUBSCRIPT '(' expr '\\'OP_APPLY\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) ) );", 26, 45, input);

                                    throw nvae;
                                }
                            }
                            else {
                                if (backtracking>0) {failed=true; return retval;}
                                NoViableAltException nvae =
                                    new NoViableAltException("257:1: subscript : ( SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) ) | SUBSCRIPT '(' expr '\\'OP_APPLY\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) ) );", 26, 26, input);

                                throw nvae;
                            }
                        }
                        else {
                            if (backtracking>0) {failed=true; return retval;}
                            NoViableAltException nvae =
                                new NoViableAltException("257:1: subscript : ( SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) ) | SUBSCRIPT '(' expr '\\'OP_APPLY\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) ) );", 26, 13, input);

                            throw nvae;
                        }
                        }
                        break;
                    case COMPARE:
                        {
                        int LA26_14 = input.LA(4);

                        if ( (LA26_14==60) ) {
                            int LA26_27 = input.LA(5);

                            if ( (synpred52()) ) {
                                alt26=1;
                            }
                            else if ( (true) ) {
                                alt26=2;
                            }
                            else {
                                if (backtracking>0) {failed=true; return retval;}
                                NoViableAltException nvae =
                                    new NoViableAltException("257:1: subscript : ( SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) ) | SUBSCRIPT '(' expr '\\'OP_APPLY\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) ) );", 26, 27, input);

                                throw nvae;
                            }
                        }
                        else {
                            if (backtracking>0) {failed=true; return retval;}
                            NoViableAltException nvae =
                                new NoViableAltException("257:1: subscript : ( SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) ) | SUBSCRIPT '(' expr '\\'OP_APPLY\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) ) );", 26, 14, input);

                            throw nvae;
                        }
                        }
                        break;
                    case KEYWORD:
                        {
                        int LA26_15 = input.LA(4);

                        if ( (LA26_15==60) ) {
                            int LA26_28 = input.LA(5);

                            if ( (LA26_28==62) ) {
                                int LA26_48 = input.LA(6);

                                if ( (LA26_48==ID) ) {
                                    int LA26_64 = input.LA(7);

                                    if ( (LA26_64==62) ) {
                                        int LA26_69 = input.LA(8);

                                        if ( (synpred52()) ) {
                                            alt26=1;
                                        }
                                        else if ( (true) ) {
                                            alt26=2;
                                        }
                                        else {
                                            if (backtracking>0) {failed=true; return retval;}
                                            NoViableAltException nvae =
                                                new NoViableAltException("257:1: subscript : ( SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) ) | SUBSCRIPT '(' expr '\\'OP_APPLY\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) ) );", 26, 69, input);

                                            throw nvae;
                                        }
                                    }
                                    else {
                                        if (backtracking>0) {failed=true; return retval;}
                                        NoViableAltException nvae =
                                            new NoViableAltException("257:1: subscript : ( SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) ) | SUBSCRIPT '(' expr '\\'OP_APPLY\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) ) );", 26, 64, input);

                                        throw nvae;
                                    }
                                }
                                else {
                                    if (backtracking>0) {failed=true; return retval;}
                                    NoViableAltException nvae =
                                        new NoViableAltException("257:1: subscript : ( SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) ) | SUBSCRIPT '(' expr '\\'OP_APPLY\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) ) );", 26, 48, input);

                                    throw nvae;
                                }
                            }
                            else {
                                if (backtracking>0) {failed=true; return retval;}
                                NoViableAltException nvae =
                                    new NoViableAltException("257:1: subscript : ( SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) ) | SUBSCRIPT '(' expr '\\'OP_APPLY\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) ) );", 26, 28, input);

                                throw nvae;
                            }
                        }
                        else {
                            if (backtracking>0) {failed=true; return retval;}
                            NoViableAltException nvae =
                                new NoViableAltException("257:1: subscript : ( SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) ) | SUBSCRIPT '(' expr '\\'OP_APPLY\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) ) );", 26, 15, input);

                            throw nvae;
                        }
                        }
                        break;
                    default:
                        if (backtracking>0) {failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("257:1: subscript : ( SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) ) | SUBSCRIPT '(' expr '\\'OP_APPLY\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) ) );", 26, 2, input);

                        throw nvae;
                    }

                }
                else {
                    if (backtracking>0) {failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("257:1: subscript : ( SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) ) | SUBSCRIPT '(' expr '\\'OP_APPLY\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) ) );", 26, 1, input);

                    throw nvae;
                }
            }
            else {
                if (backtracking>0) {failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("257:1: subscript : ( SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) ) | SUBSCRIPT '(' expr '\\'OP_APPLY\\'' '[' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) ) );", 26, 0, input);

                throw nvae;
            }
            switch (alt26) {
                case 1 :
                    // Python.g:257:13: SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')'
                    {
                    SUBSCRIPT234=(Token)input.LT(1);
                    match(input,SUBSCRIPT,FOLLOW_SUBSCRIPT_in_subscript1588); if (failed) return retval;
                    if ( backtracking==0 ) stream_SUBSCRIPT.add(SUBSCRIPT234);

                    char_literal235=(Token)input.LT(1);
                    match(input,60,FOLLOW_60_in_subscript1590); if (failed) return retval;
                    if ( backtracking==0 ) stream_60.add(char_literal235);

                    pushFollow(FOLLOW_expr_in_subscript1592);
                    expr236=expr();
                    _fsp--;
                    if (failed) return retval;
                    if ( backtracking==0 ) stream_expr.add(expr236.getTree());
                    string_literal237=(Token)input.LT(1);
                    match(input,67,FOLLOW_67_in_subscript1594); if (failed) return retval;
                    if ( backtracking==0 ) stream_67.add(string_literal237);

                    char_literal238=(Token)input.LT(1);
                    match(input,63,FOLLOW_63_in_subscript1596); if (failed) return retval;
                    if ( backtracking==0 ) stream_63.add(char_literal238);

                    // Python.g:257:52: ( expr )+
                    int cnt24=0;
                    loop24:
                    do {
                        int alt24=2;
                        int LA24_0 = input.LA(1);

                        if ( (LA24_0==CONST||LA24_0==CALLFUNC||(LA24_0>=ADD && LA24_0<=NAME)||(LA24_0>=SLICE && LA24_0<=SUBSCRIPT)||(LA24_0>=SLICEOBJ && LA24_0<=KEYWORD)) ) {
                            alt24=1;
                        }


                        switch (alt24) {
                    	case 1 :
                    	    // Python.g:0:0: expr
                    	    {
                    	    pushFollow(FOLLOW_expr_in_subscript1598);
                    	    expr239=expr();
                    	    _fsp--;
                    	    if (failed) return retval;
                    	    if ( backtracking==0 ) stream_expr.add(expr239.getTree());

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt24 >= 1 ) break loop24;
                    	    if (backtracking>0) {failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(24, input);
                                throw eee;
                        }
                        cnt24++;
                    } while (true);

                    char_literal240=(Token)input.LT(1);
                    match(input,64,FOLLOW_64_in_subscript1601); if (failed) return retval;
                    if ( backtracking==0 ) stream_64.add(char_literal240);

                    char_literal241=(Token)input.LT(1);
                    match(input,61,FOLLOW_61_in_subscript1603); if (failed) return retval;
                    if ( backtracking==0 ) stream_61.add(char_literal241);


                    // AST REWRITE
                    // elements: expr, expr
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    if ( backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 257:66: -> ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) )
                    {
                        // Python.g:257:69: ^( SUBSCRIPTS expr ^( LIST ( expr )+ ) )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(SUBSCRIPTS, "SUBSCRIPTS"), root_1);

                        adaptor.addChild(root_1, stream_expr.next());
                        // Python.g:257:87: ^( LIST ( expr )+ )
                        {
                        CommonTree root_2 = (CommonTree)adaptor.nil();
                        root_2 = (CommonTree)adaptor.becomeRoot(adaptor.create(LIST, "LIST"), root_2);

                        if ( !(stream_expr.hasNext()) ) {
                            throw new RewriteEarlyExitException();
                        }
                        while ( stream_expr.hasNext() ) {
                            adaptor.addChild(root_2, stream_expr.next());

                        }
                        stream_expr.reset();

                        adaptor.addChild(root_1, root_2);
                        }

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    }

                    }
                    break;
                case 2 :
                    // Python.g:258:9: SUBSCRIPT '(' expr '\\'OP_APPLY\\'' '[' ( expr )+ ']' ')'
                    {
                    SUBSCRIPT242=(Token)input.LT(1);
                    match(input,SUBSCRIPT,FOLLOW_SUBSCRIPT_in_subscript1628); if (failed) return retval;
                    if ( backtracking==0 ) stream_SUBSCRIPT.add(SUBSCRIPT242);

                    char_literal243=(Token)input.LT(1);
                    match(input,60,FOLLOW_60_in_subscript1630); if (failed) return retval;
                    if ( backtracking==0 ) stream_60.add(char_literal243);

                    pushFollow(FOLLOW_expr_in_subscript1632);
                    expr244=expr();
                    _fsp--;
                    if (failed) return retval;
                    if ( backtracking==0 ) stream_expr.add(expr244.getTree());
                    string_literal245=(Token)input.LT(1);
                    match(input,68,FOLLOW_68_in_subscript1634); if (failed) return retval;
                    if ( backtracking==0 ) stream_68.add(string_literal245);

                    char_literal246=(Token)input.LT(1);
                    match(input,63,FOLLOW_63_in_subscript1636); if (failed) return retval;
                    if ( backtracking==0 ) stream_63.add(char_literal246);

                    // Python.g:258:47: ( expr )+
                    int cnt25=0;
                    loop25:
                    do {
                        int alt25=2;
                        int LA25_0 = input.LA(1);

                        if ( (LA25_0==CONST||LA25_0==CALLFUNC||(LA25_0>=ADD && LA25_0<=NAME)||(LA25_0>=SLICE && LA25_0<=SUBSCRIPT)||(LA25_0>=SLICEOBJ && LA25_0<=KEYWORD)) ) {
                            alt25=1;
                        }


                        switch (alt25) {
                    	case 1 :
                    	    // Python.g:0:0: expr
                    	    {
                    	    pushFollow(FOLLOW_expr_in_subscript1638);
                    	    expr247=expr();
                    	    _fsp--;
                    	    if (failed) return retval;
                    	    if ( backtracking==0 ) stream_expr.add(expr247.getTree());

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt25 >= 1 ) break loop25;
                    	    if (backtracking>0) {failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(25, input);
                                throw eee;
                        }
                        cnt25++;
                    } while (true);

                    char_literal248=(Token)input.LT(1);
                    match(input,64,FOLLOW_64_in_subscript1641); if (failed) return retval;
                    if ( backtracking==0 ) stream_64.add(char_literal248);

                    char_literal249=(Token)input.LT(1);
                    match(input,61,FOLLOW_61_in_subscript1643); if (failed) return retval;
                    if ( backtracking==0 ) stream_61.add(char_literal249);


                    // AST REWRITE
                    // elements: expr, expr
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    if ( backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 258:61: -> ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) )
                    {
                        // Python.g:258:64: ^( SUBSCRIPTG expr ^( LIST ( expr )+ ) )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(SUBSCRIPTG, "SUBSCRIPTG"), root_1);

                        adaptor.addChild(root_1, stream_expr.next());
                        // Python.g:258:82: ^( LIST ( expr )+ )
                        {
                        CommonTree root_2 = (CommonTree)adaptor.nil();
                        root_2 = (CommonTree)adaptor.becomeRoot(adaptor.create(LIST, "LIST"), root_2);

                        if ( !(stream_expr.hasNext()) ) {
                            throw new RewriteEarlyExitException();
                        }
                        while ( stream_expr.hasNext() ) {
                            adaptor.addChild(root_2, stream_expr.next());

                        }
                        stream_expr.reset();

                        adaptor.addChild(root_1, root_2);
                        }

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( backtracking==0 ) {
                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end subscript

    public static class slice_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start slice
    // Python.g:261:1: slice : ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) | SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) );
    public final slice_return slice() throws RecognitionException {
        slice_return retval = new slice_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token SLICE250=null;
        Token char_literal251=null;
        Token char_literal252=null;
        Token string_literal254=null;
        Token char_literal256=null;
        Token char_literal257=null;
        Token SLICE258=null;
        Token char_literal259=null;
        Token char_literal260=null;
        Token string_literal262=null;
        Token char_literal264=null;
        Token char_literal265=null;
        expr_return expr253 = null;

        expr_return expr255 = null;

        expr_return expr261 = null;

        expr_return expr263 = null;


        CommonTree SLICE250_tree=null;
        CommonTree char_literal251_tree=null;
        CommonTree char_literal252_tree=null;
        CommonTree string_literal254_tree=null;
        CommonTree char_literal256_tree=null;
        CommonTree char_literal257_tree=null;
        CommonTree SLICE258_tree=null;
        CommonTree char_literal259_tree=null;
        CommonTree char_literal260_tree=null;
        CommonTree string_literal262_tree=null;
        CommonTree char_literal264_tree=null;
        CommonTree char_literal265_tree=null;
        RewriteRuleTokenStream stream_67=new RewriteRuleTokenStream(adaptor,"token 67");
        RewriteRuleTokenStream stream_68=new RewriteRuleTokenStream(adaptor,"token 68");
        RewriteRuleTokenStream stream_SLICE=new RewriteRuleTokenStream(adaptor,"token SLICE");
        RewriteRuleTokenStream stream_64=new RewriteRuleTokenStream(adaptor,"token 64");
        RewriteRuleTokenStream stream_63=new RewriteRuleTokenStream(adaptor,"token 63");
        RewriteRuleTokenStream stream_60=new RewriteRuleTokenStream(adaptor,"token 60");
        RewriteRuleTokenStream stream_61=new RewriteRuleTokenStream(adaptor,"token 61");
        RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");
        try {
            // Python.g:261:7: ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) | SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) )
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==SLICE) ) {
                int LA29_1 = input.LA(2);

                if ( (LA29_1==60) ) {
                    int LA29_2 = input.LA(3);

                    if ( (LA29_2==63) ) {
                        switch ( input.LA(4) ) {
                        case ADD:
                            {
                            int LA29_4 = input.LA(5);

                            if ( (LA29_4==60) ) {
                                int LA29_17 = input.LA(6);

                                if ( (LA29_17==60) ) {
                                    int LA29_30 = input.LA(7);

                                    if ( (synpred55()) ) {
                                        alt29=1;
                                    }
                                    else if ( (true) ) {
                                        alt29=2;
                                    }
                                    else {
                                        if (backtracking>0) {failed=true; return retval;}
                                        NoViableAltException nvae =
                                            new NoViableAltException("261:1: slice : ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) | SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) );", 29, 30, input);

                                        throw nvae;
                                    }
                                }
                                else {
                                    if (backtracking>0) {failed=true; return retval;}
                                    NoViableAltException nvae =
                                        new NoViableAltException("261:1: slice : ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) | SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) );", 29, 17, input);

                                    throw nvae;
                                }
                            }
                            else {
                                if (backtracking>0) {failed=true; return retval;}
                                NoViableAltException nvae =
                                    new NoViableAltException("261:1: slice : ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) | SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) );", 29, 4, input);

                                throw nvae;
                            }
                            }
                            break;
                        case MUL:
                            {
                            int LA29_5 = input.LA(5);

                            if ( (LA29_5==60) ) {
                                int LA29_18 = input.LA(6);

                                if ( (LA29_18==60) ) {
                                    int LA29_31 = input.LA(7);

                                    if ( (synpred55()) ) {
                                        alt29=1;
                                    }
                                    else if ( (true) ) {
                                        alt29=2;
                                    }
                                    else {
                                        if (backtracking>0) {failed=true; return retval;}
                                        NoViableAltException nvae =
                                            new NoViableAltException("261:1: slice : ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) | SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) );", 29, 31, input);

                                        throw nvae;
                                    }
                                }
                                else {
                                    if (backtracking>0) {failed=true; return retval;}
                                    NoViableAltException nvae =
                                        new NoViableAltException("261:1: slice : ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) | SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) );", 29, 18, input);

                                    throw nvae;
                                }
                            }
                            else {
                                if (backtracking>0) {failed=true; return retval;}
                                NoViableAltException nvae =
                                    new NoViableAltException("261:1: slice : ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) | SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) );", 29, 5, input);

                                throw nvae;
                            }
                            }
                            break;
                        case SUB:
                            {
                            int LA29_6 = input.LA(5);

                            if ( (LA29_6==60) ) {
                                int LA29_19 = input.LA(6);

                                if ( (LA29_19==60) ) {
                                    int LA29_32 = input.LA(7);

                                    if ( (synpred55()) ) {
                                        alt29=1;
                                    }
                                    else if ( (true) ) {
                                        alt29=2;
                                    }
                                    else {
                                        if (backtracking>0) {failed=true; return retval;}
                                        NoViableAltException nvae =
                                            new NoViableAltException("261:1: slice : ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) | SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) );", 29, 32, input);

                                        throw nvae;
                                    }
                                }
                                else {
                                    if (backtracking>0) {failed=true; return retval;}
                                    NoViableAltException nvae =
                                        new NoViableAltException("261:1: slice : ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) | SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) );", 29, 19, input);

                                    throw nvae;
                                }
                            }
                            else {
                                if (backtracking>0) {failed=true; return retval;}
                                NoViableAltException nvae =
                                    new NoViableAltException("261:1: slice : ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) | SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) );", 29, 6, input);

                                throw nvae;
                            }
                            }
                            break;
                        case DIV:
                            {
                            int LA29_7 = input.LA(5);

                            if ( (LA29_7==60) ) {
                                int LA29_20 = input.LA(6);

                                if ( (LA29_20==60) ) {
                                    int LA29_33 = input.LA(7);

                                    if ( (synpred55()) ) {
                                        alt29=1;
                                    }
                                    else if ( (true) ) {
                                        alt29=2;
                                    }
                                    else {
                                        if (backtracking>0) {failed=true; return retval;}
                                        NoViableAltException nvae =
                                            new NoViableAltException("261:1: slice : ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) | SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) );", 29, 33, input);

                                        throw nvae;
                                    }
                                }
                                else {
                                    if (backtracking>0) {failed=true; return retval;}
                                    NoViableAltException nvae =
                                        new NoViableAltException("261:1: slice : ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) | SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) );", 29, 20, input);

                                    throw nvae;
                                }
                            }
                            else {
                                if (backtracking>0) {failed=true; return retval;}
                                NoViableAltException nvae =
                                    new NoViableAltException("261:1: slice : ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) | SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) );", 29, 7, input);

                                throw nvae;
                            }
                            }
                            break;
                        case NAME:
                            {
                            int LA29_8 = input.LA(5);

                            if ( (LA29_8==60) ) {
                                int LA29_21 = input.LA(6);

                                if ( (LA29_21==62) ) {
                                    int LA29_34 = input.LA(7);

                                    if ( (LA29_34==ID) ) {
                                        int LA29_58 = input.LA(8);

                                        if ( (LA29_58==62) ) {
                                            int LA29_66 = input.LA(9);

                                            if ( (LA29_66==61) ) {
                                                int LA29_71 = input.LA(10);

                                                if ( (LA29_71==68) ) {
                                                    alt29=2;
                                                }
                                                else if ( (LA29_71==67) ) {
                                                    alt29=1;
                                                }
                                                else {
                                                    if (backtracking>0) {failed=true; return retval;}
                                                    NoViableAltException nvae =
                                                        new NoViableAltException("261:1: slice : ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) | SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) );", 29, 71, input);

                                                    throw nvae;
                                                }
                                            }
                                            else {
                                                if (backtracking>0) {failed=true; return retval;}
                                                NoViableAltException nvae =
                                                    new NoViableAltException("261:1: slice : ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) | SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) );", 29, 66, input);

                                                throw nvae;
                                            }
                                        }
                                        else {
                                            if (backtracking>0) {failed=true; return retval;}
                                            NoViableAltException nvae =
                                                new NoViableAltException("261:1: slice : ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) | SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) );", 29, 58, input);

                                            throw nvae;
                                        }
                                    }
                                    else {
                                        if (backtracking>0) {failed=true; return retval;}
                                        NoViableAltException nvae =
                                            new NoViableAltException("261:1: slice : ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) | SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) );", 29, 34, input);

                                        throw nvae;
                                    }
                                }
                                else {
                                    if (backtracking>0) {failed=true; return retval;}
                                    NoViableAltException nvae =
                                        new NoViableAltException("261:1: slice : ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) | SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) );", 29, 21, input);

                                    throw nvae;
                                }
                            }
                            else {
                                if (backtracking>0) {failed=true; return retval;}
                                NoViableAltException nvae =
                                    new NoViableAltException("261:1: slice : ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) | SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) );", 29, 8, input);

                                throw nvae;
                            }
                            }
                            break;
                        case GETATTR:
                            {
                            int LA29_9 = input.LA(5);

                            if ( (LA29_9==60) ) {
                                int LA29_22 = input.LA(6);

                                if ( (synpred55()) ) {
                                    alt29=1;
                                }
                                else if ( (true) ) {
                                    alt29=2;
                                }
                                else {
                                    if (backtracking>0) {failed=true; return retval;}
                                    NoViableAltException nvae =
                                        new NoViableAltException("261:1: slice : ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) | SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) );", 29, 22, input);

                                    throw nvae;
                                }
                            }
                            else {
                                if (backtracking>0) {failed=true; return retval;}
                                NoViableAltException nvae =
                                    new NoViableAltException("261:1: slice : ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) | SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) );", 29, 9, input);

                                throw nvae;
                            }
                            }
                            break;
                        case CONST:
                            {
                            int LA29_10 = input.LA(5);

                            if ( (LA29_10==60) ) {
                                switch ( input.LA(6) ) {
                                case 62:
                                    {
                                    int LA29_37 = input.LA(7);

                                    if ( (LA29_37==PYSTR) ) {
                                        int LA29_59 = input.LA(8);

                                        if ( (LA29_59==60) ) {
                                            int LA29_67 = input.LA(9);

                                            if ( (LA29_67==INT) ) {
                                                int LA29_72 = input.LA(10);

                                                if ( (LA29_72==61) ) {
                                                    int LA29_73 = input.LA(11);

                                                    if ( (LA29_73==62) ) {
                                                        int LA29_74 = input.LA(12);

                                                        if ( (LA29_74==61) ) {
                                                            int LA29_60 = input.LA(13);

                                                            if ( (LA29_60==68) ) {
                                                                alt29=2;
                                                            }
                                                            else if ( (LA29_60==67) ) {
                                                                alt29=1;
                                                            }
                                                            else {
                                                                if (backtracking>0) {failed=true; return retval;}
                                                                NoViableAltException nvae =
                                                                    new NoViableAltException("261:1: slice : ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) | SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) );", 29, 60, input);

                                                                throw nvae;
                                                            }
                                                        }
                                                        else {
                                                            if (backtracking>0) {failed=true; return retval;}
                                                            NoViableAltException nvae =
                                                                new NoViableAltException("261:1: slice : ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) | SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) );", 29, 74, input);

                                                            throw nvae;
                                                        }
                                                    }
                                                    else {
                                                        if (backtracking>0) {failed=true; return retval;}
                                                        NoViableAltException nvae =
                                                            new NoViableAltException("261:1: slice : ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) | SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) );", 29, 73, input);

                                                        throw nvae;
                                                    }
                                                }
                                                else {
                                                    if (backtracking>0) {failed=true; return retval;}
                                                    NoViableAltException nvae =
                                                        new NoViableAltException("261:1: slice : ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) | SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) );", 29, 72, input);

                                                    throw nvae;
                                                }
                                            }
                                            else {
                                                if (backtracking>0) {failed=true; return retval;}
                                                NoViableAltException nvae =
                                                    new NoViableAltException("261:1: slice : ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) | SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) );", 29, 67, input);

                                                throw nvae;
                                            }
                                        }
                                        else {
                                            if (backtracking>0) {failed=true; return retval;}
                                            NoViableAltException nvae =
                                                new NoViableAltException("261:1: slice : ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) | SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) );", 29, 59, input);

                                            throw nvae;
                                        }
                                    }
                                    else {
                                        if (backtracking>0) {failed=true; return retval;}
                                        NoViableAltException nvae =
                                            new NoViableAltException("261:1: slice : ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) | SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) );", 29, 37, input);

                                        throw nvae;
                                    }
                                    }
                                    break;
                                case INT:
                                    {
                                    int LA29_38 = input.LA(7);

                                    if ( (LA29_38==61) ) {
                                        int LA29_60 = input.LA(8);

                                        if ( (LA29_60==68) ) {
                                            alt29=2;
                                        }
                                        else if ( (LA29_60==67) ) {
                                            alt29=1;
                                        }
                                        else {
                                            if (backtracking>0) {failed=true; return retval;}
                                            NoViableAltException nvae =
                                                new NoViableAltException("261:1: slice : ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) | SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) );", 29, 60, input);

                                            throw nvae;
                                        }
                                    }
                                    else {
                                        if (backtracking>0) {failed=true; return retval;}
                                        NoViableAltException nvae =
                                            new NoViableAltException("261:1: slice : ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) | SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) );", 29, 38, input);

                                        throw nvae;
                                    }
                                    }
                                    break;
                                case FLOAT:
                                    {
                                    int LA29_39 = input.LA(7);

                                    if ( (LA29_39==61) ) {
                                        int LA29_60 = input.LA(8);

                                        if ( (LA29_60==68) ) {
                                            alt29=2;
                                        }
                                        else if ( (LA29_60==67) ) {
                                            alt29=1;
                                        }
                                        else {
                                            if (backtracking>0) {failed=true; return retval;}
                                            NoViableAltException nvae =
                                                new NoViableAltException("261:1: slice : ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) | SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) );", 29, 60, input);

                                            throw nvae;
                                        }
                                    }
                                    else {
                                        if (backtracking>0) {failed=true; return retval;}
                                        NoViableAltException nvae =
                                            new NoViableAltException("261:1: slice : ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) | SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) );", 29, 39, input);

                                        throw nvae;
                                    }
                                    }
                                    break;
                                case NONE:
                                    {
                                    int LA29_40 = input.LA(7);

                                    if ( (LA29_40==61) ) {
                                        int LA29_60 = input.LA(8);

                                        if ( (LA29_60==68) ) {
                                            alt29=2;
                                        }
                                        else if ( (LA29_60==67) ) {
                                            alt29=1;
                                        }
                                        else {
                                            if (backtracking>0) {failed=true; return retval;}
                                            NoViableAltException nvae =
                                                new NoViableAltException("261:1: slice : ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) | SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) );", 29, 60, input);

                                            throw nvae;
                                        }
                                    }
                                    else {
                                        if (backtracking>0) {failed=true; return retval;}
                                        NoViableAltException nvae =
                                            new NoViableAltException("261:1: slice : ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) | SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) );", 29, 40, input);

                                        throw nvae;
                                    }
                                    }
                                    break;
                                default:
                                    if (backtracking>0) {failed=true; return retval;}
                                    NoViableAltException nvae =
                                        new NoViableAltException("261:1: slice : ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) | SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) );", 29, 23, input);

                                    throw nvae;
                                }

                            }
                            else {
                                if (backtracking>0) {failed=true; return retval;}
                                NoViableAltException nvae =
                                    new NoViableAltException("261:1: slice : ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) | SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) );", 29, 10, input);

                                throw nvae;
                            }
                            }
                            break;
                        case CALLFUNC:
                            {
                            int LA29_11 = input.LA(5);

                            if ( (LA29_11==60) ) {
                                int LA29_24 = input.LA(6);

                                if ( (synpred55()) ) {
                                    alt29=1;
                                }
                                else if ( (true) ) {
                                    alt29=2;
                                }
                                else {
                                    if (backtracking>0) {failed=true; return retval;}
                                    NoViableAltException nvae =
                                        new NoViableAltException("261:1: slice : ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) | SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) );", 29, 24, input);

                                    throw nvae;
                                }
                            }
                            else {
                                if (backtracking>0) {failed=true; return retval;}
                                NoViableAltException nvae =
                                    new NoViableAltException("261:1: slice : ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) | SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) );", 29, 11, input);

                                throw nvae;
                            }
                            }
                            break;
                        case SLICEOBJ:
                            {
                            int LA29_12 = input.LA(5);

                            if ( (LA29_12==60) ) {
                                int LA29_25 = input.LA(6);

                                if ( (LA29_25==63) ) {
                                    int LA29_43 = input.LA(7);

                                    if ( (synpred55()) ) {
                                        alt29=1;
                                    }
                                    else if ( (true) ) {
                                        alt29=2;
                                    }
                                    else {
                                        if (backtracking>0) {failed=true; return retval;}
                                        NoViableAltException nvae =
                                            new NoViableAltException("261:1: slice : ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) | SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) );", 29, 43, input);

                                        throw nvae;
                                    }
                                }
                                else {
                                    if (backtracking>0) {failed=true; return retval;}
                                    NoViableAltException nvae =
                                        new NoViableAltException("261:1: slice : ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) | SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) );", 29, 25, input);

                                    throw nvae;
                                }
                            }
                            else {
                                if (backtracking>0) {failed=true; return retval;}
                                NoViableAltException nvae =
                                    new NoViableAltException("261:1: slice : ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) | SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) );", 29, 12, input);

                                throw nvae;
                            }
                            }
                            break;
                        case SUBSCRIPT:
                            {
                            int LA29_13 = input.LA(5);

                            if ( (LA29_13==60) ) {
                                int LA29_26 = input.LA(6);

                                if ( (synpred55()) ) {
                                    alt29=1;
                                }
                                else if ( (true) ) {
                                    alt29=2;
                                }
                                else {
                                    if (backtracking>0) {failed=true; return retval;}
                                    NoViableAltException nvae =
                                        new NoViableAltException("261:1: slice : ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) | SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) );", 29, 26, input);

                                    throw nvae;
                                }
                            }
                            else {
                                if (backtracking>0) {failed=true; return retval;}
                                NoViableAltException nvae =
                                    new NoViableAltException("261:1: slice : ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) | SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) );", 29, 13, input);

                                throw nvae;
                            }
                            }
                            break;
                        case SLICE:
                            {
                            int LA29_14 = input.LA(5);

                            if ( (LA29_14==60) ) {
                                int LA29_27 = input.LA(6);

                                if ( (LA29_27==63) ) {
                                    int LA29_46 = input.LA(7);

                                    if ( (synpred55()) ) {
                                        alt29=1;
                                    }
                                    else if ( (true) ) {
                                        alt29=2;
                                    }
                                    else {
                                        if (backtracking>0) {failed=true; return retval;}
                                        NoViableAltException nvae =
                                            new NoViableAltException("261:1: slice : ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) | SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) );", 29, 46, input);

                                        throw nvae;
                                    }
                                }
                                else {
                                    if (backtracking>0) {failed=true; return retval;}
                                    NoViableAltException nvae =
                                        new NoViableAltException("261:1: slice : ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) | SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) );", 29, 27, input);

                                    throw nvae;
                                }
                            }
                            else {
                                if (backtracking>0) {failed=true; return retval;}
                                NoViableAltException nvae =
                                    new NoViableAltException("261:1: slice : ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) | SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) );", 29, 14, input);

                                throw nvae;
                            }
                            }
                            break;
                        case COMPARE:
                            {
                            int LA29_15 = input.LA(5);

                            if ( (LA29_15==60) ) {
                                int LA29_28 = input.LA(6);

                                if ( (synpred55()) ) {
                                    alt29=1;
                                }
                                else if ( (true) ) {
                                    alt29=2;
                                }
                                else {
                                    if (backtracking>0) {failed=true; return retval;}
                                    NoViableAltException nvae =
                                        new NoViableAltException("261:1: slice : ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) | SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) );", 29, 28, input);

                                    throw nvae;
                                }
                            }
                            else {
                                if (backtracking>0) {failed=true; return retval;}
                                NoViableAltException nvae =
                                    new NoViableAltException("261:1: slice : ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) | SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) );", 29, 15, input);

                                throw nvae;
                            }
                            }
                            break;
                        case KEYWORD:
                            {
                            int LA29_16 = input.LA(5);

                            if ( (LA29_16==60) ) {
                                int LA29_29 = input.LA(6);

                                if ( (LA29_29==62) ) {
                                    int LA29_49 = input.LA(7);

                                    if ( (LA29_49==ID) ) {
                                        int LA29_65 = input.LA(8);

                                        if ( (LA29_65==62) ) {
                                            int LA29_70 = input.LA(9);

                                            if ( (synpred55()) ) {
                                                alt29=1;
                                            }
                                            else if ( (true) ) {
                                                alt29=2;
                                            }
                                            else {
                                                if (backtracking>0) {failed=true; return retval;}
                                                NoViableAltException nvae =
                                                    new NoViableAltException("261:1: slice : ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) | SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) );", 29, 70, input);

                                                throw nvae;
                                            }
                                        }
                                        else {
                                            if (backtracking>0) {failed=true; return retval;}
                                            NoViableAltException nvae =
                                                new NoViableAltException("261:1: slice : ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) | SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) );", 29, 65, input);

                                            throw nvae;
                                        }
                                    }
                                    else {
                                        if (backtracking>0) {failed=true; return retval;}
                                        NoViableAltException nvae =
                                            new NoViableAltException("261:1: slice : ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) | SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) );", 29, 49, input);

                                        throw nvae;
                                    }
                                }
                                else {
                                    if (backtracking>0) {failed=true; return retval;}
                                    NoViableAltException nvae =
                                        new NoViableAltException("261:1: slice : ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) | SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) );", 29, 29, input);

                                    throw nvae;
                                }
                            }
                            else {
                                if (backtracking>0) {failed=true; return retval;}
                                NoViableAltException nvae =
                                    new NoViableAltException("261:1: slice : ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) | SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) );", 29, 16, input);

                                throw nvae;
                            }
                            }
                            break;
                        default:
                            if (backtracking>0) {failed=true; return retval;}
                            NoViableAltException nvae =
                                new NoViableAltException("261:1: slice : ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) | SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) );", 29, 3, input);

                            throw nvae;
                        }

                    }
                    else {
                        if (backtracking>0) {failed=true; return retval;}
                        NoViableAltException nvae =
                            new NoViableAltException("261:1: slice : ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) | SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) );", 29, 2, input);

                        throw nvae;
                    }
                }
                else {
                    if (backtracking>0) {failed=true; return retval;}
                    NoViableAltException nvae =
                        new NoViableAltException("261:1: slice : ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) | SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) );", 29, 1, input);

                    throw nvae;
                }
            }
            else {
                if (backtracking>0) {failed=true; return retval;}
                NoViableAltException nvae =
                    new NoViableAltException("261:1: slice : ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) | SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')' -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) ) );", 29, 0, input);

                throw nvae;
            }
            switch (alt29) {
                case 1 :
                    // Python.g:261:9: SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')'
                    {
                    SLICE250=(Token)input.LT(1);
                    match(input,SLICE,FOLLOW_SLICE_in_slice1671); if (failed) return retval;
                    if ( backtracking==0 ) stream_SLICE.add(SLICE250);

                    char_literal251=(Token)input.LT(1);
                    match(input,60,FOLLOW_60_in_slice1673); if (failed) return retval;
                    if ( backtracking==0 ) stream_60.add(char_literal251);

                    char_literal252=(Token)input.LT(1);
                    match(input,63,FOLLOW_63_in_slice1675); if (failed) return retval;
                    if ( backtracking==0 ) stream_63.add(char_literal252);

                    pushFollow(FOLLOW_expr_in_slice1677);
                    expr253=expr();
                    _fsp--;
                    if (failed) return retval;
                    if ( backtracking==0 ) stream_expr.add(expr253.getTree());
                    string_literal254=(Token)input.LT(1);
                    match(input,67,FOLLOW_67_in_slice1679); if (failed) return retval;
                    if ( backtracking==0 ) stream_67.add(string_literal254);

                    // Python.g:261:44: ( expr )+
                    int cnt27=0;
                    loop27:
                    do {
                        int alt27=2;
                        int LA27_0 = input.LA(1);

                        if ( (LA27_0==CONST||LA27_0==CALLFUNC||(LA27_0>=ADD && LA27_0<=NAME)||(LA27_0>=SLICE && LA27_0<=SUBSCRIPT)||(LA27_0>=SLICEOBJ && LA27_0<=KEYWORD)) ) {
                            alt27=1;
                        }


                        switch (alt27) {
                    	case 1 :
                    	    // Python.g:0:0: expr
                    	    {
                    	    pushFollow(FOLLOW_expr_in_slice1681);
                    	    expr255=expr();
                    	    _fsp--;
                    	    if (failed) return retval;
                    	    if ( backtracking==0 ) stream_expr.add(expr255.getTree());

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt27 >= 1 ) break loop27;
                    	    if (backtracking>0) {failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(27, input);
                                throw eee;
                        }
                        cnt27++;
                    } while (true);

                    char_literal256=(Token)input.LT(1);
                    match(input,64,FOLLOW_64_in_slice1684); if (failed) return retval;
                    if ( backtracking==0 ) stream_64.add(char_literal256);

                    char_literal257=(Token)input.LT(1);
                    match(input,61,FOLLOW_61_in_slice1686); if (failed) return retval;
                    if ( backtracking==0 ) stream_61.add(char_literal257);


                    // AST REWRITE
                    // elements: expr, expr
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    if ( backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 261:58: -> ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) )
                    {
                        // Python.g:261:61: ^( SUBSCRIPTS expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(SUBSCRIPTS, "SUBSCRIPTS"), root_1);

                        adaptor.addChild(root_1, stream_expr.next());
                        // Python.g:261:79: ^( LIST ^( SLICEOBJ ( expr )+ ) )
                        {
                        CommonTree root_2 = (CommonTree)adaptor.nil();
                        root_2 = (CommonTree)adaptor.becomeRoot(adaptor.create(LIST, "LIST"), root_2);

                        // Python.g:261:86: ^( SLICEOBJ ( expr )+ )
                        {
                        CommonTree root_3 = (CommonTree)adaptor.nil();
                        root_3 = (CommonTree)adaptor.becomeRoot(adaptor.create(SLICEOBJ, "SLICEOBJ"), root_3);

                        if ( !(stream_expr.hasNext()) ) {
                            throw new RewriteEarlyExitException();
                        }
                        while ( stream_expr.hasNext() ) {
                            adaptor.addChild(root_3, stream_expr.next());

                        }
                        stream_expr.reset();

                        adaptor.addChild(root_2, root_3);
                        }

                        adaptor.addChild(root_1, root_2);
                        }

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    }

                    }
                    break;
                case 2 :
                    // Python.g:262:7: SLICE '(' '[' expr '\\'OP_APPLY\\'' ( expr )+ ']' ')'
                    {
                    SLICE258=(Token)input.LT(1);
                    match(input,SLICE,FOLLOW_SLICE_in_slice1713); if (failed) return retval;
                    if ( backtracking==0 ) stream_SLICE.add(SLICE258);

                    char_literal259=(Token)input.LT(1);
                    match(input,60,FOLLOW_60_in_slice1715); if (failed) return retval;
                    if ( backtracking==0 ) stream_60.add(char_literal259);

                    char_literal260=(Token)input.LT(1);
                    match(input,63,FOLLOW_63_in_slice1717); if (failed) return retval;
                    if ( backtracking==0 ) stream_63.add(char_literal260);

                    pushFollow(FOLLOW_expr_in_slice1719);
                    expr261=expr();
                    _fsp--;
                    if (failed) return retval;
                    if ( backtracking==0 ) stream_expr.add(expr261.getTree());
                    string_literal262=(Token)input.LT(1);
                    match(input,68,FOLLOW_68_in_slice1721); if (failed) return retval;
                    if ( backtracking==0 ) stream_68.add(string_literal262);

                    // Python.g:262:41: ( expr )+
                    int cnt28=0;
                    loop28:
                    do {
                        int alt28=2;
                        int LA28_0 = input.LA(1);

                        if ( (LA28_0==CONST||LA28_0==CALLFUNC||(LA28_0>=ADD && LA28_0<=NAME)||(LA28_0>=SLICE && LA28_0<=SUBSCRIPT)||(LA28_0>=SLICEOBJ && LA28_0<=KEYWORD)) ) {
                            alt28=1;
                        }


                        switch (alt28) {
                    	case 1 :
                    	    // Python.g:0:0: expr
                    	    {
                    	    pushFollow(FOLLOW_expr_in_slice1723);
                    	    expr263=expr();
                    	    _fsp--;
                    	    if (failed) return retval;
                    	    if ( backtracking==0 ) stream_expr.add(expr263.getTree());

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt28 >= 1 ) break loop28;
                    	    if (backtracking>0) {failed=true; return retval;}
                                EarlyExitException eee =
                                    new EarlyExitException(28, input);
                                throw eee;
                        }
                        cnt28++;
                    } while (true);

                    char_literal264=(Token)input.LT(1);
                    match(input,64,FOLLOW_64_in_slice1726); if (failed) return retval;
                    if ( backtracking==0 ) stream_64.add(char_literal264);

                    char_literal265=(Token)input.LT(1);
                    match(input,61,FOLLOW_61_in_slice1728); if (failed) return retval;
                    if ( backtracking==0 ) stream_61.add(char_literal265);


                    // AST REWRITE
                    // elements: expr, expr
                    // token labels: 
                    // rule labels: retval
                    // token list labels: 
                    // rule list labels: 
                    if ( backtracking==0 ) {
                    retval.tree = root_0;
                    RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

                    root_0 = (CommonTree)adaptor.nil();
                    // 262:55: -> ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) )
                    {
                        // Python.g:262:58: ^( SUBSCRIPTG expr ^( LIST ^( SLICEOBJ ( expr )+ ) ) )
                        {
                        CommonTree root_1 = (CommonTree)adaptor.nil();
                        root_1 = (CommonTree)adaptor.becomeRoot(adaptor.create(SUBSCRIPTG, "SUBSCRIPTG"), root_1);

                        adaptor.addChild(root_1, stream_expr.next());
                        // Python.g:262:76: ^( LIST ^( SLICEOBJ ( expr )+ ) )
                        {
                        CommonTree root_2 = (CommonTree)adaptor.nil();
                        root_2 = (CommonTree)adaptor.becomeRoot(adaptor.create(LIST, "LIST"), root_2);

                        // Python.g:262:83: ^( SLICEOBJ ( expr )+ )
                        {
                        CommonTree root_3 = (CommonTree)adaptor.nil();
                        root_3 = (CommonTree)adaptor.becomeRoot(adaptor.create(SLICEOBJ, "SLICEOBJ"), root_3);

                        if ( !(stream_expr.hasNext()) ) {
                            throw new RewriteEarlyExitException();
                        }
                        while ( stream_expr.hasNext() ) {
                            adaptor.addChild(root_3, stream_expr.next());

                        }
                        stream_expr.reset();

                        adaptor.addChild(root_2, root_3);
                        }

                        adaptor.addChild(root_1, root_2);
                        }

                        adaptor.addChild(root_0, root_1);
                        }

                    }

                    }

                    }
                    break;

            }
            retval.stop = input.LT(-1);

            if ( backtracking==0 ) {
                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end slice

    public static class sliceobj_return extends ParserRuleReturnScope {
        CommonTree tree;
        public Object getTree() { return tree; }
    };

    // $ANTLR start sliceobj
    // Python.g:265:1: sliceobj : SLICEOBJ '(' '[' ( expr )+ ']' ')' -> ^( SLICEOBJ ( expr )+ ) ;
    public final sliceobj_return sliceobj() throws RecognitionException {
        sliceobj_return retval = new sliceobj_return();
        retval.start = input.LT(1);

        CommonTree root_0 = null;

        Token SLICEOBJ266=null;
        Token char_literal267=null;
        Token char_literal268=null;
        Token char_literal270=null;
        Token char_literal271=null;
        expr_return expr269 = null;


        CommonTree SLICEOBJ266_tree=null;
        CommonTree char_literal267_tree=null;
        CommonTree char_literal268_tree=null;
        CommonTree char_literal270_tree=null;
        CommonTree char_literal271_tree=null;
        RewriteRuleTokenStream stream_64=new RewriteRuleTokenStream(adaptor,"token 64");
        RewriteRuleTokenStream stream_63=new RewriteRuleTokenStream(adaptor,"token 63");
        RewriteRuleTokenStream stream_60=new RewriteRuleTokenStream(adaptor,"token 60");
        RewriteRuleTokenStream stream_SLICEOBJ=new RewriteRuleTokenStream(adaptor,"token SLICEOBJ");
        RewriteRuleTokenStream stream_61=new RewriteRuleTokenStream(adaptor,"token 61");
        RewriteRuleSubtreeStream stream_expr=new RewriteRuleSubtreeStream(adaptor,"rule expr");
        try {
            // Python.g:265:10: ( SLICEOBJ '(' '[' ( expr )+ ']' ')' -> ^( SLICEOBJ ( expr )+ ) )
            // Python.g:265:12: SLICEOBJ '(' '[' ( expr )+ ']' ')'
            {
            SLICEOBJ266=(Token)input.LT(1);
            match(input,SLICEOBJ,FOLLOW_SLICEOBJ_in_sliceobj1760); if (failed) return retval;
            if ( backtracking==0 ) stream_SLICEOBJ.add(SLICEOBJ266);

            char_literal267=(Token)input.LT(1);
            match(input,60,FOLLOW_60_in_sliceobj1762); if (failed) return retval;
            if ( backtracking==0 ) stream_60.add(char_literal267);

            char_literal268=(Token)input.LT(1);
            match(input,63,FOLLOW_63_in_sliceobj1764); if (failed) return retval;
            if ( backtracking==0 ) stream_63.add(char_literal268);

            // Python.g:265:29: ( expr )+
            int cnt30=0;
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( (LA30_0==CONST||LA30_0==CALLFUNC||(LA30_0>=ADD && LA30_0<=NAME)||(LA30_0>=SLICE && LA30_0<=SUBSCRIPT)||(LA30_0>=SLICEOBJ && LA30_0<=KEYWORD)) ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // Python.g:0:0: expr
            	    {
            	    pushFollow(FOLLOW_expr_in_sliceobj1766);
            	    expr269=expr();
            	    _fsp--;
            	    if (failed) return retval;
            	    if ( backtracking==0 ) stream_expr.add(expr269.getTree());

            	    }
            	    break;

            	default :
            	    if ( cnt30 >= 1 ) break loop30;
            	    if (backtracking>0) {failed=true; return retval;}
                        EarlyExitException eee =
                            new EarlyExitException(30, input);
                        throw eee;
                }
                cnt30++;
            } while (true);

            char_literal270=(Token)input.LT(1);
            match(input,64,FOLLOW_64_in_sliceobj1769); if (failed) return retval;
            if ( backtracking==0 ) stream_64.add(char_literal270);

            char_literal271=(Token)input.LT(1);
            match(input,61,FOLLOW_61_in_sliceobj1771); if (failed) return retval;
            if ( backtracking==0 ) stream_61.add(char_literal271);


            // AST REWRITE
            // elements: expr, SLICEOBJ
            // token labels: 
            // rule labels: retval
            // token list labels: 
            // rule list labels: 
            if ( backtracking==0 ) {
            retval.tree = root_0;
            RewriteRuleSubtreeStream stream_retval=new RewriteRuleSubtreeStream(adaptor,"token retval",retval!=null?retval.tree:null);

            root_0 = (CommonTree)adaptor.nil();
            // 265:43: -> ^( SLICEOBJ ( expr )+ )
            {
                // Python.g:265:46: ^( SLICEOBJ ( expr )+ )
                {
                CommonTree root_1 = (CommonTree)adaptor.nil();
                root_1 = (CommonTree)adaptor.becomeRoot(stream_SLICEOBJ.next(), root_1);

                if ( !(stream_expr.hasNext()) ) {
                    throw new RewriteEarlyExitException();
                }
                while ( stream_expr.hasNext() ) {
                    adaptor.addChild(root_1, stream_expr.next());

                }
                stream_expr.reset();

                adaptor.addChild(root_0, root_1);
                }

            }

            }

            }

            retval.stop = input.LT(-1);

            if ( backtracking==0 ) {
                retval.tree = (CommonTree)adaptor.rulePostProcessing(root_0);
                adaptor.setTokenBoundaries(retval.tree, retval.start, retval.stop);
            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return retval;
    }
    // $ANTLR end sliceobj

    // $ANTLR start synpred22
    public final void synpred22_fragment() throws RecognitionException {   
        // Python.g:174:14: ( expr pyStmt )
        // Python.g:174:14: expr pyStmt
        {
        pushFollow(FOLLOW_expr_in_synpred22723);
        expr();
        _fsp--;
        if (failed) return ;
        pushFollow(FOLLOW_pyStmt_in_synpred22725);
        pyStmt();
        _fsp--;
        if (failed) return ;

        }
    }
    // $ANTLR end synpred22

    // $ANTLR start synpred25
    public final void synpred25_fragment() throws RecognitionException {   
        // Python.g:178:8: ( IF '(' '[' ( '(' pyifhelper ')' )+ ']' pyStmt ')' )
        // Python.g:178:8: IF '(' '[' ( '(' pyifhelper ')' )+ ']' pyStmt ')'
        {
        match(input,IF,FOLLOW_IF_in_synpred25776); if (failed) return ;
        match(input,60,FOLLOW_60_in_synpred25778); if (failed) return ;
        match(input,63,FOLLOW_63_in_synpred25780); if (failed) return ;
        // Python.g:178:19: ( '(' pyifhelper ')' )+
        int cnt32=0;
        loop32:
        do {
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==60) ) {
                alt32=1;
            }


            switch (alt32) {
        	case 1 :
        	    // Python.g:178:20: '(' pyifhelper ')'
        	    {
        	    match(input,60,FOLLOW_60_in_synpred25783); if (failed) return ;
        	    pushFollow(FOLLOW_pyifhelper_in_synpred25785);
        	    pyifhelper();
        	    _fsp--;
        	    if (failed) return ;
        	    match(input,61,FOLLOW_61_in_synpred25787); if (failed) return ;

        	    }
        	    break;

        	default :
        	    if ( cnt32 >= 1 ) break loop32;
        	    if (backtracking>0) {failed=true; return ;}
                    EarlyExitException eee =
                        new EarlyExitException(32, input);
                    throw eee;
            }
            cnt32++;
        } while (true);

        match(input,64,FOLLOW_64_in_synpred25792); if (failed) return ;
        pushFollow(FOLLOW_pyStmt_in_synpred25794);
        pyStmt();
        _fsp--;
        if (failed) return ;
        match(input,61,FOLLOW_61_in_synpred25796); if (failed) return ;

        }
    }
    // $ANTLR end synpred25

    // $ANTLR start synpred52
    public final void synpred52_fragment() throws RecognitionException {   
        // Python.g:257:13: ( SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')' )
        // Python.g:257:13: SUBSCRIPT '(' expr '\\'OP_ASSIGN\\'' '[' ( expr )+ ']' ')'
        {
        match(input,SUBSCRIPT,FOLLOW_SUBSCRIPT_in_synpred521588); if (failed) return ;
        match(input,60,FOLLOW_60_in_synpred521590); if (failed) return ;
        pushFollow(FOLLOW_expr_in_synpred521592);
        expr();
        _fsp--;
        if (failed) return ;
        match(input,67,FOLLOW_67_in_synpred521594); if (failed) return ;
        match(input,63,FOLLOW_63_in_synpred521596); if (failed) return ;
        // Python.g:257:52: ( expr )+
        int cnt33=0;
        loop33:
        do {
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==CONST||LA33_0==CALLFUNC||(LA33_0>=ADD && LA33_0<=NAME)||(LA33_0>=SLICE && LA33_0<=SUBSCRIPT)||(LA33_0>=SLICEOBJ && LA33_0<=KEYWORD)) ) {
                alt33=1;
            }


            switch (alt33) {
        	case 1 :
        	    // Python.g:0:0: expr
        	    {
        	    pushFollow(FOLLOW_expr_in_synpred521598);
        	    expr();
        	    _fsp--;
        	    if (failed) return ;

        	    }
        	    break;

        	default :
        	    if ( cnt33 >= 1 ) break loop33;
        	    if (backtracking>0) {failed=true; return ;}
                    EarlyExitException eee =
                        new EarlyExitException(33, input);
                    throw eee;
            }
            cnt33++;
        } while (true);

        match(input,64,FOLLOW_64_in_synpred521601); if (failed) return ;
        match(input,61,FOLLOW_61_in_synpred521603); if (failed) return ;

        }
    }
    // $ANTLR end synpred52

    // $ANTLR start synpred55
    public final void synpred55_fragment() throws RecognitionException {   
        // Python.g:261:9: ( SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')' )
        // Python.g:261:9: SLICE '(' '[' expr '\\'OP_ASSIGN\\'' ( expr )+ ']' ')'
        {
        match(input,SLICE,FOLLOW_SLICE_in_synpred551671); if (failed) return ;
        match(input,60,FOLLOW_60_in_synpred551673); if (failed) return ;
        match(input,63,FOLLOW_63_in_synpred551675); if (failed) return ;
        pushFollow(FOLLOW_expr_in_synpred551677);
        expr();
        _fsp--;
        if (failed) return ;
        match(input,67,FOLLOW_67_in_synpred551679); if (failed) return ;
        // Python.g:261:44: ( expr )+
        int cnt34=0;
        loop34:
        do {
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==CONST||LA34_0==CALLFUNC||(LA34_0>=ADD && LA34_0<=NAME)||(LA34_0>=SLICE && LA34_0<=SUBSCRIPT)||(LA34_0>=SLICEOBJ && LA34_0<=KEYWORD)) ) {
                alt34=1;
            }


            switch (alt34) {
        	case 1 :
        	    // Python.g:0:0: expr
        	    {
        	    pushFollow(FOLLOW_expr_in_synpred551681);
        	    expr();
        	    _fsp--;
        	    if (failed) return ;

        	    }
        	    break;

        	default :
        	    if ( cnt34 >= 1 ) break loop34;
        	    if (backtracking>0) {failed=true; return ;}
                    EarlyExitException eee =
                        new EarlyExitException(34, input);
                    throw eee;
            }
            cnt34++;
        } while (true);

        match(input,64,FOLLOW_64_in_synpred551684); if (failed) return ;
        match(input,61,FOLLOW_61_in_synpred551686); if (failed) return ;

        }
    }
    // $ANTLR end synpred55

    public final boolean synpred55() {
        backtracking++;
        int start = input.mark();
        try {
            synpred55_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !failed;
        input.rewind(start);
        backtracking--;
        failed=false;
        return success;
    }
    public final boolean synpred52() {
        backtracking++;
        int start = input.mark();
        try {
            synpred52_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !failed;
        input.rewind(start);
        backtracking--;
        failed=false;
        return success;
    }
    public final boolean synpred25() {
        backtracking++;
        int start = input.mark();
        try {
            synpred25_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !failed;
        input.rewind(start);
        backtracking--;
        failed=false;
        return success;
    }
    public final boolean synpred22() {
        backtracking++;
        int start = input.mark();
        try {
            synpred22_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !failed;
        input.rewind(start);
        backtracking--;
        failed=false;
        return success;
    }


 

    public static final BitSet FOLLOW_MODULE_in_module307 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_module310 = new BitSet(new long[]{0x0000000000000300L});
    public static final BitSet FOLLOW_docString_in_module313 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_stmt_in_module315 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_module317 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pyconst_in_docString329 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NONE_in_docString336 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CONST_in_pyconst346 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_pyconst350 = new BitSet(new long[]{0x40C0000000000100L});
    public static final BitSet FOLLOW_pyStr_in_pyconst354 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_INT_in_pyconst358 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_FLOAT_in_pyconst362 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_NONE_in_pyconst366 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_pyconst369 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_62_in_pyStr382 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_PYSTR_in_pyStr385 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_pyStr388 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_INT_in_pyStr391 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_pyStr393 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_62_in_pyStr396 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STMT_in_stmt409 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_stmt411 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_63_in_stmt413 = new BitSet(new long[]{0x000000006805F500L,0x0000000000000002L});
    public static final BitSet FOLLOW_annotStmt_in_stmt416 = new BitSet(new long[]{0x000000006805F500L,0x0000000000000003L});
    public static final BitSet FOLLOW_64_in_stmt420 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_stmt422 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STMT_in_stmt438 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_NONE_in_stmt441 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_annot_in_annotStmt454 = new BitSet(new long[]{0x000000006805F500L});
    public static final BitSet FOLLOW_pyStmt_in_annotStmt456 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pyStmt_in_annotStmt471 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_annot482 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_annot485 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_pyStr_in_annot488 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_annot490 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assign_in_pyStmt505 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_stmt_in_pyStmt510 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_defun_in_pyStmt518 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_global_in_pyStmt523 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pyreturn_in_pyStmt528 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pyif_in_pyStmt533 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pyimport_in_pyStmt538 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_discardStmt_in_pyStmt543 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pyfor_in_pyStmt548 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pyclass_in_pyStmt558 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NONE_in_pyStmt568 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CLASS_in_pyclass578 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_pyclass580 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_id_in_pyclass582 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_63_in_pyclass584 = new BitSet(new long[]{0x0000003987E80200L});
    public static final BitSet FOLLOW_expr_in_pyclass586 = new BitSet(new long[]{0x0000003987E80200L,0x0000000000000001L});
    public static final BitSet FOLLOW_64_in_pyclass589 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_NONE_in_pyclass592 = new BitSet(new long[]{0x000000006805F500L});
    public static final BitSet FOLLOW_pyStmt_in_pyclass594 = new BitSet(new long[]{0x0000000000040100L});
    public static final BitSet FOLLOW_pyinit_in_pyclass596 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_pyclass598 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_defun_in_pyinit631 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NONE_in_pyinit635 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IMPORT_in_pyimport644 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_pyimport646 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_63_in_pyimport648 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_pyimport652 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_id_in_pyimport654 = new BitSet(new long[]{0x4000000000000100L});
    public static final BitSet FOLLOW_idornone_in_pyimport656 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_pyimport658 = new BitSet(new long[]{0x1000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_64_in_pyimport663 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_pyimport665 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NONE_in_idornone692 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_id_in_idornone694 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DISCARD_in_discardStmt704 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_discardStmt707 = new BitSet(new long[]{0x0000003987E80200L});
    public static final BitSet FOLLOW_expr_in_discardStmt710 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_discardStmt712 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_pyifhelper723 = new BitSet(new long[]{0x000000006805F500L});
    public static final BitSet FOLLOW_pyStmt_in_pyifhelper725 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_pyifhelper745 = new BitSet(new long[]{0x000000006805F500L});
    public static final BitSet FOLLOW_pyStmt_in_pyifhelper747 = new BitSet(new long[]{0x000000006805F502L});
    public static final BitSet FOLLOW_IF_in_pyif776 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_pyif778 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_63_in_pyif780 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_pyif783 = new BitSet(new long[]{0x0000003987E80200L});
    public static final BitSet FOLLOW_pyifhelper_in_pyif785 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_pyif787 = new BitSet(new long[]{0x1000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_64_in_pyif792 = new BitSet(new long[]{0x000000006805F500L});
    public static final BitSet FOLLOW_pyStmt_in_pyif794 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_pyif796 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_pyif820 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_pyif822 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_63_in_pyif824 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_pyif827 = new BitSet(new long[]{0x0000003987E80200L});
    public static final BitSet FOLLOW_pyifhelper_in_pyif829 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_pyif831 = new BitSet(new long[]{0x1000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_64_in_pyif836 = new BitSet(new long[]{0x000000006805F500L});
    public static final BitSet FOLLOW_pyStmt_in_pyif838 = new BitSet(new long[]{0x200000006805F500L});
    public static final BitSet FOLLOW_61_in_pyif841 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FOR_in_pyfor872 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_pyfor875 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_assname_in_pyfor878 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_callFunc_in_pyfor880 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_stmt_in_pyfor882 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_NONE_in_pyfor884 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_pyfor886 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RETURN_in_pyreturn898 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_pyreturn901 = new BitSet(new long[]{0x0000003987E80200L});
    public static final BitSet FOLLOW_expr_in_pyreturn904 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_pyreturn906 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GLOBAL_in_global917 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_global919 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_63_in_global921 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_id_in_global923 = new BitSet(new long[]{0x4000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_64_in_global926 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_global928 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_FUNCTION_in_defun954 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_defun957 = new BitSet(new long[]{0x0000000000000100L,0x0000000000000004L});
    public static final BitSet FOLLOW_decorators_in_defun960 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_id_in_defun962 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_paramlist_in_defun964 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_defaults_in_defun966 = new BitSet(new long[]{0x0040000000000000L});
    public static final BitSet FOLLOW_INT_in_defun969 = new BitSet(new long[]{0x0000000000000300L});
    public static final BitSet FOLLOW_docString_in_defun972 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_stmt_in_defun975 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_defun977 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NONE_in_decorators989 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_66_in_decorators1010 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_decorators1012 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_63_in_decorators1014 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_callFunc_in_decorators1016 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_64_in_decorators1018 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_decorators1020 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_63_in_defaults1040 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_64_in_defaults1043 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_63_in_paramlist1056 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_id_in_paramlist1058 = new BitSet(new long[]{0x4000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_64_in_paramlist1061 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASSIGN_in_assign1082 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_assign1084 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_63_in_assign1087 = new BitSet(new long[]{0x0000000100120000L});
    public static final BitSet FOLLOW_assnode_in_assign1090 = new BitSet(new long[]{0x0000000100120000L,0x0000000000000001L});
    public static final BitSet FOLLOW_64_in_assign1094 = new BitSet(new long[]{0x0000003987E80200L});
    public static final BitSet FOLLOW_expr_in_assign1096 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_assign1098 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assname_in_assnode1125 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assattr_in_assnode1129 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_subscript_in_assnode1133 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASSNAME_in_assname1142 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_assname1145 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_id_in_assname1148 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_assname1150 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_assname1152 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ASSATTR_in_assattr1163 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_assattr1166 = new BitSet(new long[]{0x0000003987E80200L});
    public static final BitSet FOLLOW_expr_in_assattr1169 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_id_in_assattr1171 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_assattr1173 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_assattr1175 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_CALLFUNC_in_callFunc1188 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_callFunc1190 = new BitSet(new long[]{0x0000003987E80200L});
    public static final BitSet FOLLOW_expr_in_callFunc1192 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_63_in_callFunc1194 = new BitSet(new long[]{0x0000003987E80200L,0x0000000000000001L});
    public static final BitSet FOLLOW_expr_in_callFunc1196 = new BitSet(new long[]{0x0000003987E80200L,0x0000000000000001L});
    public static final BitSet FOLLOW_64_in_callFunc1199 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_NONE_in_callFunc1201 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_NONE_in_callFunc1203 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_callFunc1205 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_add_in_expr1234 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_mul_in_expr1239 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_sub_in_expr1244 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_div_in_expr1254 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_name_in_expr1260 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_getattr_in_expr1265 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_pyconst_in_expr1270 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_callFunc_in_expr1275 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_sliceobj_in_expr1285 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_subscript_in_expr1295 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_slice_in_expr1305 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_compare_in_expr1315 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_keyword_in_expr1322 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ADD_in_add1333 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_add1336 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_add1339 = new BitSet(new long[]{0x0000003987E80200L});
    public static final BitSet FOLLOW_expr_in_add1342 = new BitSet(new long[]{0x0000003987E80200L});
    public static final BitSet FOLLOW_expr_in_add1344 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_add1346 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_add1349 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SUB_in_sub1361 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_sub1364 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_sub1367 = new BitSet(new long[]{0x0000003987E80200L});
    public static final BitSet FOLLOW_expr_in_sub1370 = new BitSet(new long[]{0x0000003987E80200L});
    public static final BitSet FOLLOW_expr_in_sub1372 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_sub1374 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_sub1377 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_MUL_in_mul1388 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_mul1391 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_mul1394 = new BitSet(new long[]{0x0000003987E80200L});
    public static final BitSet FOLLOW_expr_in_mul1397 = new BitSet(new long[]{0x0000003987E80200L});
    public static final BitSet FOLLOW_expr_in_mul1399 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_mul1401 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_mul1404 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_DIV_in_div1416 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_div1419 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_div1422 = new BitSet(new long[]{0x0000003987E80200L});
    public static final BitSet FOLLOW_expr_in_div1425 = new BitSet(new long[]{0x0000003987E80200L});
    public static final BitSet FOLLOW_expr_in_div1427 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_div1429 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_div1432 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_set_in_compare_op0 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_COMPARE_in_compare1472 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_compare1475 = new BitSet(new long[]{0x0000003987E80200L});
    public static final BitSet FOLLOW_expr_in_compare1478 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_63_in_compare1480 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_compare1483 = new BitSet(new long[]{0x00000DC000000000L});
    public static final BitSet FOLLOW_compare_op_in_compare1486 = new BitSet(new long[]{0x0000003987E80200L});
    public static final BitSet FOLLOW_expr_in_compare1488 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_compare1490 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_64_in_compare1493 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_compare1496 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NAME_in_name1510 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_name1513 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_id_in_name1516 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_name1518 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_GETATTR_in_getattr1530 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_getattr1533 = new BitSet(new long[]{0x0000003987E80200L});
    public static final BitSet FOLLOW_expr_in_getattr1535 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_id_in_getattr1537 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_getattr1539 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_KEYWORD_in_keyword1551 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_keyword1554 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_id_in_keyword1557 = new BitSet(new long[]{0x0000003987E80200L});
    public static final BitSet FOLLOW_expr_in_keyword1559 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_keyword1561 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_62_in_id1572 = new BitSet(new long[]{0x0100000000000000L});
    public static final BitSet FOLLOW_ID_in_id1575 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_62_in_id1577 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SUBSCRIPT_in_subscript1588 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_subscript1590 = new BitSet(new long[]{0x0000003987E80200L});
    public static final BitSet FOLLOW_expr_in_subscript1592 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_subscript1594 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_63_in_subscript1596 = new BitSet(new long[]{0x0000003987E80200L});
    public static final BitSet FOLLOW_expr_in_subscript1598 = new BitSet(new long[]{0x0000003987E80200L,0x0000000000000001L});
    public static final BitSet FOLLOW_64_in_subscript1601 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_subscript1603 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SUBSCRIPT_in_subscript1628 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_subscript1630 = new BitSet(new long[]{0x0000003987E80200L});
    public static final BitSet FOLLOW_expr_in_subscript1632 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_subscript1634 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_63_in_subscript1636 = new BitSet(new long[]{0x0000003987E80200L});
    public static final BitSet FOLLOW_expr_in_subscript1638 = new BitSet(new long[]{0x0000003987E80200L,0x0000000000000001L});
    public static final BitSet FOLLOW_64_in_subscript1641 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_subscript1643 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SLICE_in_slice1671 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_slice1673 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_63_in_slice1675 = new BitSet(new long[]{0x0000003987E80200L});
    public static final BitSet FOLLOW_expr_in_slice1677 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_slice1679 = new BitSet(new long[]{0x0000003987E80200L});
    public static final BitSet FOLLOW_expr_in_slice1681 = new BitSet(new long[]{0x0000003987E80200L,0x0000000000000001L});
    public static final BitSet FOLLOW_64_in_slice1684 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_slice1686 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SLICE_in_slice1713 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_slice1715 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_63_in_slice1717 = new BitSet(new long[]{0x0000003987E80200L});
    public static final BitSet FOLLOW_expr_in_slice1719 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_slice1721 = new BitSet(new long[]{0x0000003987E80200L});
    public static final BitSet FOLLOW_expr_in_slice1723 = new BitSet(new long[]{0x0000003987E80200L,0x0000000000000001L});
    public static final BitSet FOLLOW_64_in_slice1726 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_slice1728 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SLICEOBJ_in_sliceobj1760 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_sliceobj1762 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_63_in_sliceobj1764 = new BitSet(new long[]{0x0000003987E80200L});
    public static final BitSet FOLLOW_expr_in_sliceobj1766 = new BitSet(new long[]{0x0000003987E80200L,0x0000000000000001L});
    public static final BitSet FOLLOW_64_in_sliceobj1769 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_sliceobj1771 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_synpred22723 = new BitSet(new long[]{0x000000006805F500L});
    public static final BitSet FOLLOW_pyStmt_in_synpred22725 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IF_in_synpred25776 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_synpred25778 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_63_in_synpred25780 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_synpred25783 = new BitSet(new long[]{0x0000003987E80200L});
    public static final BitSet FOLLOW_pyifhelper_in_synpred25785 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_synpred25787 = new BitSet(new long[]{0x1000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_64_in_synpred25792 = new BitSet(new long[]{0x000000006805F500L});
    public static final BitSet FOLLOW_pyStmt_in_synpred25794 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_synpred25796 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SUBSCRIPT_in_synpred521588 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_synpred521590 = new BitSet(new long[]{0x0000003987E80200L});
    public static final BitSet FOLLOW_expr_in_synpred521592 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_synpred521594 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_63_in_synpred521596 = new BitSet(new long[]{0x0000003987E80200L});
    public static final BitSet FOLLOW_expr_in_synpred521598 = new BitSet(new long[]{0x0000003987E80200L,0x0000000000000001L});
    public static final BitSet FOLLOW_64_in_synpred521601 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_synpred521603 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_SLICE_in_synpred551671 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_synpred551673 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_63_in_synpred551675 = new BitSet(new long[]{0x0000003987E80200L});
    public static final BitSet FOLLOW_expr_in_synpred551677 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_synpred551679 = new BitSet(new long[]{0x0000003987E80200L});
    public static final BitSet FOLLOW_expr_in_synpred551681 = new BitSet(new long[]{0x0000003987E80200L,0x0000000000000001L});
    public static final BitSet FOLLOW_64_in_synpred551684 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_synpred551686 = new BitSet(new long[]{0x0000000000000002L});

}