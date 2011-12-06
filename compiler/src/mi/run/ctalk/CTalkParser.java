// $ANTLR 3.4 C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g 2011-12-06 04:16:08

	package mi.run.ctalk;
	import mi.run.ast.*;
	import java.util.Stack;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class CTalkParser extends Parser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "BOOLEAN", "COMMENT", "DECIMAL_LITERAL", "DIGIT", "DIGIT_SEQUENCE", "ESCAPE_SEQUENCE", "EXPONENT_PART", "FLOATING_SUFFIX", "FRACTIONAL_CONSTANT", "HEXADECIMAL_ESCAPE_SEQUENCE", "HEXADECIMAL_LITERAL", "HEX_DIGIT", "HEX_QUAD", "IDENTIFIER", "INTEGER", "INTEGER_SUFFIX", "LONG_SUFFIX", "NONDIGIT", "NONZERO_DIGIT", "NOT_STR_CHAR", "OCTAL_DIGIT", "OCTAL_ESCAPE_SEQUENCE", "OCTAL_LITERAL", "REAL", "SIGN", "SIMPLE_ESCAPE_SEQUENCE", "STRING", "S_CHAR", "S_CHAR_SEQUENCE", "UNIVERSAL_CHARACTER_NAME", "UNSIGNED_SUFFIX", "WHITE_SPACE", "'!'", "'!='", "'%'", "'%='", "'&&'", "'&'", "'&='", "'('", "')'", "'*'", "'*='", "'+'", "'++'", "'+='", "','", "'-'", "'--'", "'-='", "'.'", "'/'", "'/='", "':'", "';'", "'<'", "'<<'", "'<<='", "'<='", "'='", "'=='", "'>'", "'>='", "'>>'", "'>>='", "'['", "']'", "'^'", "'^='", "'break'", "'continue'", "'do'", "'else'", "'for'", "'function'", "'if'", "'return'", "'while'", "'{'", "'|'", "'|='", "'||'", "'}'", "'~'"
    };

    public static final int EOF=-1;
    public static final int T__36=36;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int T__39=39;
    public static final int T__40=40;
    public static final int T__41=41;
    public static final int T__42=42;
    public static final int T__43=43;
    public static final int T__44=44;
    public static final int T__45=45;
    public static final int T__46=46;
    public static final int T__47=47;
    public static final int T__48=48;
    public static final int T__49=49;
    public static final int T__50=50;
    public static final int T__51=51;
    public static final int T__52=52;
    public static final int T__53=53;
    public static final int T__54=54;
    public static final int T__55=55;
    public static final int T__56=56;
    public static final int T__57=57;
    public static final int T__58=58;
    public static final int T__59=59;
    public static final int T__60=60;
    public static final int T__61=61;
    public static final int T__62=62;
    public static final int T__63=63;
    public static final int T__64=64;
    public static final int T__65=65;
    public static final int T__66=66;
    public static final int T__67=67;
    public static final int T__68=68;
    public static final int T__69=69;
    public static final int T__70=70;
    public static final int T__71=71;
    public static final int T__72=72;
    public static final int T__73=73;
    public static final int T__74=74;
    public static final int T__75=75;
    public static final int T__76=76;
    public static final int T__77=77;
    public static final int T__78=78;
    public static final int T__79=79;
    public static final int T__80=80;
    public static final int T__81=81;
    public static final int T__82=82;
    public static final int T__83=83;
    public static final int T__84=84;
    public static final int T__85=85;
    public static final int T__86=86;
    public static final int T__87=87;
    public static final int BOOLEAN=4;
    public static final int COMMENT=5;
    public static final int DECIMAL_LITERAL=6;
    public static final int DIGIT=7;
    public static final int DIGIT_SEQUENCE=8;
    public static final int ESCAPE_SEQUENCE=9;
    public static final int EXPONENT_PART=10;
    public static final int FLOATING_SUFFIX=11;
    public static final int FRACTIONAL_CONSTANT=12;
    public static final int HEXADECIMAL_ESCAPE_SEQUENCE=13;
    public static final int HEXADECIMAL_LITERAL=14;
    public static final int HEX_DIGIT=15;
    public static final int HEX_QUAD=16;
    public static final int IDENTIFIER=17;
    public static final int INTEGER=18;
    public static final int INTEGER_SUFFIX=19;
    public static final int LONG_SUFFIX=20;
    public static final int NONDIGIT=21;
    public static final int NONZERO_DIGIT=22;
    public static final int NOT_STR_CHAR=23;
    public static final int OCTAL_DIGIT=24;
    public static final int OCTAL_ESCAPE_SEQUENCE=25;
    public static final int OCTAL_LITERAL=26;
    public static final int REAL=27;
    public static final int SIGN=28;
    public static final int SIMPLE_ESCAPE_SEQUENCE=29;
    public static final int STRING=30;
    public static final int S_CHAR=31;
    public static final int S_CHAR_SEQUENCE=32;
    public static final int UNIVERSAL_CHARACTER_NAME=33;
    public static final int UNSIGNED_SUFFIX=34;
    public static final int WHITE_SPACE=35;

    // delegates
    public Parser[] getDelegates() {
        return new Parser[] {};
    }

    // delegators


    public CTalkParser(TokenStream input) {
        this(input, new RecognizerSharedState());
    }
    public CTalkParser(TokenStream input, RecognizerSharedState state) {
        super(input, state);
        this.state.ruleMemo = new HashMap[108+1];
         

    }

    public String[] getTokenNames() { return CTalkParser.tokenNames; }
    public String getGrammarFileName() { return "C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g"; }



    // $ANTLR start "program"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:21:1: program returns [Program prg] : ( funcDef )* EOF ;
    public final Program program() throws RecognitionException {
        Program prg = null;

        int program_StartIndex = input.index();

        FunctionDefinition funcDef1 =null;


         prg = new Program(); 
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 1) ) { return prg; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:23:2: ( ( funcDef )* EOF )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:23:4: ( funcDef )* EOF
            {
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:23:4: ( funcDef )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==78) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:23:5: funcDef
            	    {
            	    pushFollow(FOLLOW_funcDef_in_program61);
            	    funcDef1=funcDef();

            	    state._fsp--;
            	    if (state.failed) return prg;

            	    if ( state.backtracking==0 ) { prg.functions.add(funcDef1); funcDef1.parent = prg; }

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            match(input,EOF,FOLLOW_EOF_in_program67); if (state.failed) return prg;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 1, program_StartIndex); }

        }
        return prg;
    }
    // $ANTLR end "program"



    // $ANTLR start "funcDef"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:26:1: funcDef returns [FunctionDefinition fn] : 'function' IDENTIFIER '(' ( paramsList )? ')' blockStmt ;
    public final FunctionDefinition funcDef() throws RecognitionException {
        FunctionDefinition fn = null;

        int funcDef_StartIndex = input.index();

        Token IDENTIFIER2=null;
        ExpressionList paramsList3 =null;

        BlockStatement blockStmt4 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 2) ) { return fn; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:27:2: ( 'function' IDENTIFIER '(' ( paramsList )? ')' blockStmt )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:27:4: 'function' IDENTIFIER '(' ( paramsList )? ')' blockStmt
            {
            match(input,78,FOLLOW_78_in_funcDef83); if (state.failed) return fn;

            IDENTIFIER2=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_funcDef85); if (state.failed) return fn;

            match(input,43,FOLLOW_43_in_funcDef87); if (state.failed) return fn;

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:27:30: ( paramsList )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==IDENTIFIER) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:27:30: paramsList
                    {
                    pushFollow(FOLLOW_paramsList_in_funcDef89);
                    paramsList3=paramsList();

                    state._fsp--;
                    if (state.failed) return fn;

                    }
                    break;

            }


            match(input,44,FOLLOW_44_in_funcDef92); if (state.failed) return fn;

            pushFollow(FOLLOW_blockStmt_in_funcDef94);
            blockStmt4=blockStmt();

            state._fsp--;
            if (state.failed) return fn;

            if ( state.backtracking==0 ) { fn = new FunctionDefinition((IDENTIFIER2!=null?IDENTIFIER2.getText():null), paramsList3, blockStmt4); if(paramsList3 != null) paramsList3.parent = fn; blockStmt4.parent = fn; }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 2, funcDef_StartIndex); }

        }
        return fn;
    }
    // $ANTLR end "funcDef"



    // $ANTLR start "paramsList"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:31:1: paramsList returns [ExpressionList params] : id= IDENTIFIER ( ',' id= IDENTIFIER )* ;
    public final ExpressionList paramsList() throws RecognitionException {
        ExpressionList params = null;

        int paramsList_StartIndex = input.index();

        Token id=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 3) ) { return params; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:32:2: (id= IDENTIFIER ( ',' id= IDENTIFIER )* )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:32:4: id= IDENTIFIER ( ',' id= IDENTIFIER )*
            {
            id=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_paramsList116); if (state.failed) return params;

            if ( state.backtracking==0 ) {
            			params = new ExpressionList();
            			Variable var = new Variable((id!=null?id.getText():null));
            			params.expressions.add(var);
            			var.parent = params;
            		}

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:39:3: ( ',' id= IDENTIFIER )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==50) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:39:5: ',' id= IDENTIFIER
            	    {
            	    match(input,50,FOLLOW_50_in_paramsList126); if (state.failed) return params;

            	    id=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_paramsList130); if (state.failed) return params;

            	    if ( state.backtracking==0 ) {
            	    				Variable var = new Variable((id!=null?id.getText():null));
            	    				params.expressions.add(var);
            	    				var.parent = params;
            	    			}

            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 3, paramsList_StartIndex); }

        }
        return params;
    }
    // $ANTLR end "paramsList"



    // $ANTLR start "stmt"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:48:1: stmt returns [Statement stmt] : ( ';' | expr ';' | blockStmt | whileStmt | doWhileStmt | forStmt | ifStmt | breakStmt | continueStmt | returnStmt );
    public final Statement stmt() throws RecognitionException {
        Statement stmt = null;

        int stmt_StartIndex = input.index();

        Expression expr5 =null;

        BlockStatement blockStmt6 =null;

        WhileStatement whileStmt7 =null;

        DoWhileStatement doWhileStmt8 =null;

        ForStatement forStmt9 =null;

        IfStatement ifStmt10 =null;

        BreakStatement breakStmt11 =null;

        ContinueStatement continueStmt12 =null;

        ReturnStatement returnStmt13 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 4) ) { return stmt; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:49:2: ( ';' | expr ';' | blockStmt | whileStmt | doWhileStmt | forStmt | ifStmt | breakStmt | continueStmt | returnStmt )
            int alt4=10;
            switch ( input.LA(1) ) {
            case 58:
                {
                alt4=1;
                }
                break;
            case BOOLEAN:
            case IDENTIFIER:
            case INTEGER:
            case REAL:
            case STRING:
            case 36:
            case 43:
            case 47:
            case 51:
            case 69:
            case 87:
                {
                alt4=2;
                }
                break;
            case 82:
                {
                alt4=3;
                }
                break;
            case 81:
                {
                alt4=4;
                }
                break;
            case 75:
                {
                alt4=5;
                }
                break;
            case 77:
                {
                alt4=6;
                }
                break;
            case 79:
                {
                alt4=7;
                }
                break;
            case 73:
                {
                alt4=8;
                }
                break;
            case 74:
                {
                alt4=9;
                }
                break;
            case 80:
                {
                alt4=10;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return stmt;}
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;

            }

            switch (alt4) {
                case 1 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:49:4: ';'
                    {
                    match(input,58,FOLLOW_58_in_stmt156); if (state.failed) return stmt;

                    }
                    break;
                case 2 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:50:4: expr ';'
                    {
                    pushFollow(FOLLOW_expr_in_stmt161);
                    expr5=expr();

                    state._fsp--;
                    if (state.failed) return stmt;

                    match(input,58,FOLLOW_58_in_stmt163); if (state.failed) return stmt;

                    if ( state.backtracking==0 ) { stmt = new ExpressionStatement(expr5); expr5.parent = stmt; }

                    }
                    break;
                case 3 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:51:4: blockStmt
                    {
                    pushFollow(FOLLOW_blockStmt_in_stmt174);
                    blockStmt6=blockStmt();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) { stmt = blockStmt6; }

                    }
                    break;
                case 4 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:52:4: whileStmt
                    {
                    pushFollow(FOLLOW_whileStmt_in_stmt184);
                    whileStmt7=whileStmt();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) { stmt = whileStmt7; }

                    }
                    break;
                case 5 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:53:4: doWhileStmt
                    {
                    pushFollow(FOLLOW_doWhileStmt_in_stmt194);
                    doWhileStmt8=doWhileStmt();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) { stmt = doWhileStmt8; }

                    }
                    break;
                case 6 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:54:4: forStmt
                    {
                    pushFollow(FOLLOW_forStmt_in_stmt202);
                    forStmt9=forStmt();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) { stmt = forStmt9; }

                    }
                    break;
                case 7 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:55:4: ifStmt
                    {
                    pushFollow(FOLLOW_ifStmt_in_stmt214);
                    ifStmt10=ifStmt();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) { stmt = ifStmt10; }

                    }
                    break;
                case 8 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:56:4: breakStmt
                    {
                    pushFollow(FOLLOW_breakStmt_in_stmt227);
                    breakStmt11=breakStmt();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) { stmt = breakStmt11; }

                    }
                    break;
                case 9 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:57:4: continueStmt
                    {
                    pushFollow(FOLLOW_continueStmt_in_stmt237);
                    continueStmt12=continueStmt();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) { stmt = continueStmt12; }

                    }
                    break;
                case 10 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:58:4: returnStmt
                    {
                    pushFollow(FOLLOW_returnStmt_in_stmt244);
                    returnStmt13=returnStmt();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) { stmt = returnStmt13; }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 4, stmt_StartIndex); }

        }
        return stmt;
    }
    // $ANTLR end "stmt"



    // $ANTLR start "whileStmt"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:61:1: whileStmt returns [WhileStatement stmt] : 'while' '(' condition ')' body= stmt ;
    public final WhileStatement whileStmt() throws RecognitionException {
        WhileStatement stmt = null;

        int whileStmt_StartIndex = input.index();

        Statement body =null;

        Expression condition14 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 5) ) { return stmt; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:62:2: ( 'while' '(' condition ')' body= stmt )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:62:4: 'while' '(' condition ')' body= stmt
            {
            match(input,81,FOLLOW_81_in_whileStmt264); if (state.failed) return stmt;

            match(input,43,FOLLOW_43_in_whileStmt266); if (state.failed) return stmt;

            pushFollow(FOLLOW_condition_in_whileStmt268);
            condition14=condition();

            state._fsp--;
            if (state.failed) return stmt;

            match(input,44,FOLLOW_44_in_whileStmt270); if (state.failed) return stmt;

            pushFollow(FOLLOW_stmt_in_whileStmt274);
            body=stmt();

            state._fsp--;
            if (state.failed) return stmt;

            if ( state.backtracking==0 ) { stmt = new WhileStatement(condition14, body); condition14.parent = stmt; body.parent = stmt; }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 5, whileStmt_StartIndex); }

        }
        return stmt;
    }
    // $ANTLR end "whileStmt"



    // $ANTLR start "doWhileStmt"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:66:1: doWhileStmt returns [DoWhileStatement stmt] : 'do' body= stmt 'while' '(' condition ')' ';' ;
    public final DoWhileStatement doWhileStmt() throws RecognitionException {
        DoWhileStatement stmt = null;

        int doWhileStmt_StartIndex = input.index();

        Statement body =null;

        Expression condition15 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 6) ) { return stmt; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:67:2: ( 'do' body= stmt 'while' '(' condition ')' ';' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:67:4: 'do' body= stmt 'while' '(' condition ')' ';'
            {
            match(input,75,FOLLOW_75_in_doWhileStmt294); if (state.failed) return stmt;

            pushFollow(FOLLOW_stmt_in_doWhileStmt298);
            body=stmt();

            state._fsp--;
            if (state.failed) return stmt;

            match(input,81,FOLLOW_81_in_doWhileStmt300); if (state.failed) return stmt;

            match(input,43,FOLLOW_43_in_doWhileStmt302); if (state.failed) return stmt;

            pushFollow(FOLLOW_condition_in_doWhileStmt304);
            condition15=condition();

            state._fsp--;
            if (state.failed) return stmt;

            match(input,44,FOLLOW_44_in_doWhileStmt306); if (state.failed) return stmt;

            match(input,58,FOLLOW_58_in_doWhileStmt308); if (state.failed) return stmt;

            if ( state.backtracking==0 ) { stmt = new DoWhileStatement(condition15, body); condition15.parent = stmt; body.parent = stmt; }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 6, doWhileStmt_StartIndex); }

        }
        return stmt;
    }
    // $ANTLR end "doWhileStmt"



    // $ANTLR start "forStmt"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:71:1: forStmt returns [ForStatement stmt] : 'for' '(' (init= expr )? ';' (cond= expr )? ';' (iter= expr )? ')' body= stmt ;
    public final ForStatement forStmt() throws RecognitionException {
        ForStatement stmt = null;

        int forStmt_StartIndex = input.index();

        Expression init =null;

        Expression cond =null;

        Expression iter =null;

        Statement body =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 7) ) { return stmt; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:72:2: ( 'for' '(' (init= expr )? ';' (cond= expr )? ';' (iter= expr )? ')' body= stmt )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:72:4: 'for' '(' (init= expr )? ';' (cond= expr )? ';' (iter= expr )? ')' body= stmt
            {
            match(input,77,FOLLOW_77_in_forStmt328); if (state.failed) return stmt;

            match(input,43,FOLLOW_43_in_forStmt330); if (state.failed) return stmt;

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:72:18: (init= expr )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==BOOLEAN||(LA5_0 >= IDENTIFIER && LA5_0 <= INTEGER)||LA5_0==REAL||LA5_0==STRING||LA5_0==36||LA5_0==43||LA5_0==47||LA5_0==51||LA5_0==69||LA5_0==87) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:72:18: init= expr
                    {
                    pushFollow(FOLLOW_expr_in_forStmt334);
                    init=expr();

                    state._fsp--;
                    if (state.failed) return stmt;

                    }
                    break;

            }


            match(input,58,FOLLOW_58_in_forStmt337); if (state.failed) return stmt;

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:72:33: (cond= expr )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==BOOLEAN||(LA6_0 >= IDENTIFIER && LA6_0 <= INTEGER)||LA6_0==REAL||LA6_0==STRING||LA6_0==36||LA6_0==43||LA6_0==47||LA6_0==51||LA6_0==69||LA6_0==87) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:72:33: cond= expr
                    {
                    pushFollow(FOLLOW_expr_in_forStmt341);
                    cond=expr();

                    state._fsp--;
                    if (state.failed) return stmt;

                    }
                    break;

            }


            match(input,58,FOLLOW_58_in_forStmt344); if (state.failed) return stmt;

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:72:48: (iter= expr )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==BOOLEAN||(LA7_0 >= IDENTIFIER && LA7_0 <= INTEGER)||LA7_0==REAL||LA7_0==STRING||LA7_0==36||LA7_0==43||LA7_0==47||LA7_0==51||LA7_0==69||LA7_0==87) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:72:48: iter= expr
                    {
                    pushFollow(FOLLOW_expr_in_forStmt348);
                    iter=expr();

                    state._fsp--;
                    if (state.failed) return stmt;

                    }
                    break;

            }


            match(input,44,FOLLOW_44_in_forStmt351); if (state.failed) return stmt;

            pushFollow(FOLLOW_stmt_in_forStmt355);
            body=stmt();

            state._fsp--;
            if (state.failed) return stmt;

            if ( state.backtracking==0 ) { stmt = new ForStatement(init, cond, iter, body); init.parent = stmt; cond.parent = stmt; iter.parent = stmt; body.parent = stmt; }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 7, forStmt_StartIndex); }

        }
        return stmt;
    }
    // $ANTLR end "forStmt"



    // $ANTLR start "ifStmt"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:76:1: ifStmt returns [IfStatement stmt] : 'if' '(' condition ')' true_stmt= stmt ( 'else' false_stmt= stmt )? ;
    public final IfStatement ifStmt() throws RecognitionException {
        IfStatement stmt = null;

        int ifStmt_StartIndex = input.index();

        Statement true_stmt =null;

        Statement false_stmt =null;

        Expression condition16 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 8) ) { return stmt; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:77:2: ( 'if' '(' condition ')' true_stmt= stmt ( 'else' false_stmt= stmt )? )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:77:4: 'if' '(' condition ')' true_stmt= stmt ( 'else' false_stmt= stmt )?
            {
            match(input,79,FOLLOW_79_in_ifStmt374); if (state.failed) return stmt;

            match(input,43,FOLLOW_43_in_ifStmt376); if (state.failed) return stmt;

            pushFollow(FOLLOW_condition_in_ifStmt378);
            condition16=condition();

            state._fsp--;
            if (state.failed) return stmt;

            match(input,44,FOLLOW_44_in_ifStmt380); if (state.failed) return stmt;

            pushFollow(FOLLOW_stmt_in_ifStmt384);
            true_stmt=stmt();

            state._fsp--;
            if (state.failed) return stmt;

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:77:42: ( 'else' false_stmt= stmt )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==76) ) {
                int LA8_1 = input.LA(2);

                if ( (synpred16_CTalk()) ) {
                    alt8=1;
                }
            }
            switch (alt8) {
                case 1 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:77:44: 'else' false_stmt= stmt
                    {
                    match(input,76,FOLLOW_76_in_ifStmt388); if (state.failed) return stmt;

                    pushFollow(FOLLOW_stmt_in_ifStmt392);
                    false_stmt=stmt();

                    state._fsp--;
                    if (state.failed) return stmt;

                    }
                    break;

            }


            if ( state.backtracking==0 ) { stmt = new IfStatement(condition16, true_stmt, false_stmt); condition16.parent = stmt; true_stmt.parent = stmt; if(false_stmt != null) false_stmt.parent = stmt; }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 8, ifStmt_StartIndex); }

        }
        return stmt;
    }
    // $ANTLR end "ifStmt"



    // $ANTLR start "condition"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:81:1: condition returns [Expression expr] : e= expr ;
    public final Expression condition() throws RecognitionException {
        Expression expr = null;

        int condition_StartIndex = input.index();

        Expression e =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 9) ) { return expr; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:82:2: (e= expr )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:82:4: e= expr
            {
            pushFollow(FOLLOW_expr_in_condition416);
            e=expr();

            state._fsp--;
            if (state.failed) return expr;

            if ( state.backtracking==0 ) { expr = e; }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 9, condition_StartIndex); }

        }
        return expr;
    }
    // $ANTLR end "condition"



    // $ANTLR start "breakStmt"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:86:1: breakStmt returns [BreakStatement stmt] : 'break' ';' ;
    public final BreakStatement breakStmt() throws RecognitionException {
        BreakStatement stmt = null;

        int breakStmt_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 10) ) { return stmt; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:87:2: ( 'break' ';' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:87:4: 'break' ';'
            {
            match(input,73,FOLLOW_73_in_breakStmt435); if (state.failed) return stmt;

            match(input,58,FOLLOW_58_in_breakStmt437); if (state.failed) return stmt;

            if ( state.backtracking==0 ) { stmt = new BreakStatement(); }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 10, breakStmt_StartIndex); }

        }
        return stmt;
    }
    // $ANTLR end "breakStmt"



    // $ANTLR start "continueStmt"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:91:1: continueStmt returns [ContinueStatement stmt] : 'continue' ';' ;
    public final ContinueStatement continueStmt() throws RecognitionException {
        ContinueStatement stmt = null;

        int continueStmt_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 11) ) { return stmt; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:92:2: ( 'continue' ';' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:92:4: 'continue' ';'
            {
            match(input,74,FOLLOW_74_in_continueStmt456); if (state.failed) return stmt;

            match(input,58,FOLLOW_58_in_continueStmt458); if (state.failed) return stmt;

            if ( state.backtracking==0 ) { stmt = new ContinueStatement(); }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 11, continueStmt_StartIndex); }

        }
        return stmt;
    }
    // $ANTLR end "continueStmt"



    // $ANTLR start "returnStmt"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:96:1: returnStmt returns [ReturnStatement stmt] : 'return' ( expr )? ';' ;
    public final ReturnStatement returnStmt() throws RecognitionException {
        ReturnStatement stmt = null;

        int returnStmt_StartIndex = input.index();

        Expression expr17 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 12) ) { return stmt; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:97:2: ( 'return' ( expr )? ';' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:97:4: 'return' ( expr )? ';'
            {
            match(input,80,FOLLOW_80_in_returnStmt477); if (state.failed) return stmt;

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:97:13: ( expr )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==BOOLEAN||(LA9_0 >= IDENTIFIER && LA9_0 <= INTEGER)||LA9_0==REAL||LA9_0==STRING||LA9_0==36||LA9_0==43||LA9_0==47||LA9_0==51||LA9_0==69||LA9_0==87) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:97:13: expr
                    {
                    pushFollow(FOLLOW_expr_in_returnStmt479);
                    expr17=expr();

                    state._fsp--;
                    if (state.failed) return stmt;

                    }
                    break;

            }


            match(input,58,FOLLOW_58_in_returnStmt482); if (state.failed) return stmt;

            if ( state.backtracking==0 ) { stmt = new ReturnStatement(expr17); if(expr17 != null) expr17.parent = stmt; }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 12, returnStmt_StartIndex); }

        }
        return stmt;
    }
    // $ANTLR end "returnStmt"



    // $ANTLR start "blockStmt"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:101:1: blockStmt returns [BlockStatement stmt] : '{' (block= stmt )* '}' ;
    public final BlockStatement blockStmt() throws RecognitionException {
        BlockStatement stmt = null;

        int blockStmt_StartIndex = input.index();

        Statement block =null;


         stmt = new BlockStatement(); 
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 13) ) { return stmt; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:103:2: ( '{' (block= stmt )* '}' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:103:4: '{' (block= stmt )* '}'
            {
            match(input,82,FOLLOW_82_in_blockStmt507); if (state.failed) return stmt;

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:103:8: (block= stmt )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==BOOLEAN||(LA10_0 >= IDENTIFIER && LA10_0 <= INTEGER)||LA10_0==REAL||LA10_0==STRING||LA10_0==36||LA10_0==43||LA10_0==47||LA10_0==51||LA10_0==58||LA10_0==69||(LA10_0 >= 73 && LA10_0 <= 75)||LA10_0==77||(LA10_0 >= 79 && LA10_0 <= 82)||LA10_0==87) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:103:9: block= stmt
            	    {
            	    pushFollow(FOLLOW_stmt_in_blockStmt512);
            	    block=stmt();

            	    state._fsp--;
            	    if (state.failed) return stmt;

            	    if ( state.backtracking==0 ) { stmt.statements.add(block); block.parent = stmt; }

            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);


            match(input,86,FOLLOW_86_in_blockStmt518); if (state.failed) return stmt;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 13, blockStmt_StartIndex); }

        }
        return stmt;
    }
    // $ANTLR end "blockStmt"



    // $ANTLR start "expr"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:106:1: expr returns [Expression expr] : assignExpr ;
    public final Expression expr() throws RecognitionException {
        Expression expr = null;

        int expr_StartIndex = input.index();

        Expression assignExpr18 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 14) ) { return expr; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:107:2: ( assignExpr )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:107:4: assignExpr
            {
            pushFollow(FOLLOW_assignExpr_in_expr533);
            assignExpr18=assignExpr();

            state._fsp--;
            if (state.failed) return expr;

            if ( state.backtracking==0 ) { expr = assignExpr18; }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 14, expr_StartIndex); }

        }
        return expr;
    }
    // $ANTLR end "expr"



    // $ANTLR start "assignExpr"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:111:1: assignExpr returns [Expression expr] : l= logOrExpr (op= assignOp r= logOrExpr )* ;
    public final Expression assignExpr() throws RecognitionException {
        Expression expr = null;

        int assignExpr_StartIndex = input.index();

        Expression l =null;

        int op =0;

        Expression r =null;


         Stack<BinaryExpression> stack = new Stack<BinaryExpression>(); 
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 15) ) { return expr; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:113:2: (l= logOrExpr (op= assignOp r= logOrExpr )* )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:113:4: l= logOrExpr (op= assignOp r= logOrExpr )*
            {
            pushFollow(FOLLOW_logOrExpr_in_assignExpr561);
            l=logOrExpr();

            state._fsp--;
            if (state.failed) return expr;

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:113:16: (op= assignOp r= logOrExpr )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==39||LA11_0==42||LA11_0==46||LA11_0==49||LA11_0==53||LA11_0==56||LA11_0==61||LA11_0==63||LA11_0==68||LA11_0==72||LA11_0==84) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:113:17: op= assignOp r= logOrExpr
            	    {
            	    pushFollow(FOLLOW_assignOp_in_assignExpr566);
            	    op=assignOp();

            	    state._fsp--;
            	    if (state.failed) return expr;

            	    pushFollow(FOLLOW_logOrExpr_in_assignExpr570);
            	    r=logOrExpr();

            	    state._fsp--;
            	    if (state.failed) return expr;

            	    if ( state.backtracking==0 ) { stack.push(new BinaryExpression(op, null, r)); }

            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);


            if ( state.backtracking==0 ) {
            			if(stack.size() > 0)
            			{
            				while(stack.size() > 1)
            				{
            					expr = stack.pop();
            					((BinaryExpression)expr).leftOperand = stack.peek().rightOperand;
            					stack.peek().rightOperand = expr;
            					((BinaryExpression)expr).leftOperand.parent = expr;
            					((BinaryExpression)expr).rightOperand.parent = expr;
            				}
            				expr = stack.pop();
            				((BinaryExpression)expr).leftOperand = l;
            				((BinaryExpression)expr).leftOperand.parent = expr;
            				((BinaryExpression)expr).rightOperand.parent = expr;
            			}
            			else
            				expr = l;
            		}

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 15, assignExpr_StartIndex); }

        }
        return expr;
    }
    // $ANTLR end "assignExpr"



    // $ANTLR start "logOrExpr"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:135:1: logOrExpr returns [Expression expr] : l= logAndExpr ( '||' r= logAndExpr )* ;
    public final Expression logOrExpr() throws RecognitionException {
        Expression expr = null;

        int logOrExpr_StartIndex = input.index();

        Expression l =null;

        Expression r =null;


         Stack<BinaryExpression> stack = new Stack<BinaryExpression>(); 
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 16) ) { return expr; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:137:2: (l= logAndExpr ( '||' r= logAndExpr )* )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:137:4: l= logAndExpr ( '||' r= logAndExpr )*
            {
            pushFollow(FOLLOW_logAndExpr_in_logOrExpr602);
            l=logAndExpr();

            state._fsp--;
            if (state.failed) return expr;

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:137:17: ( '||' r= logAndExpr )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==85) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:137:18: '||' r= logAndExpr
            	    {
            	    match(input,85,FOLLOW_85_in_logOrExpr605); if (state.failed) return expr;

            	    pushFollow(FOLLOW_logAndExpr_in_logOrExpr609);
            	    r=logAndExpr();

            	    state._fsp--;
            	    if (state.failed) return expr;

            	    if ( state.backtracking==0 ) { stack.push(new BinaryExpression(Operator.LOG_OR, null, r)); }

            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);


            if ( state.backtracking==0 ) {
            			if(stack.size() > 0)
            			{
            				while(stack.size() > 1)
            				{
            					expr = stack.pop();
            					((BinaryExpression)expr).leftOperand = stack.peek().rightOperand;
            					stack.peek().rightOperand = expr;
            					((BinaryExpression)expr).leftOperand.parent = expr;
            					((BinaryExpression)expr).rightOperand.parent = expr;
            				}
            				expr = stack.pop();
            				((BinaryExpression)expr).leftOperand = l;
            				((BinaryExpression)expr).leftOperand.parent = expr;
            				((BinaryExpression)expr).rightOperand.parent = expr;
            			}
            			else
            				expr = l;
            		}

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 16, logOrExpr_StartIndex); }

        }
        return expr;
    }
    // $ANTLR end "logOrExpr"



    // $ANTLR start "logAndExpr"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:159:1: logAndExpr returns [Expression expr] : l= bitOrExpr ( '&&' r= bitOrExpr )* ;
    public final Expression logAndExpr() throws RecognitionException {
        Expression expr = null;

        int logAndExpr_StartIndex = input.index();

        Expression l =null;

        Expression r =null;


         Stack<BinaryExpression> stack = new Stack<BinaryExpression>(); 
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 17) ) { return expr; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:161:2: (l= bitOrExpr ( '&&' r= bitOrExpr )* )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:161:4: l= bitOrExpr ( '&&' r= bitOrExpr )*
            {
            pushFollow(FOLLOW_bitOrExpr_in_logAndExpr641);
            l=bitOrExpr();

            state._fsp--;
            if (state.failed) return expr;

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:161:16: ( '&&' r= bitOrExpr )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==40) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:161:17: '&&' r= bitOrExpr
            	    {
            	    match(input,40,FOLLOW_40_in_logAndExpr644); if (state.failed) return expr;

            	    pushFollow(FOLLOW_bitOrExpr_in_logAndExpr648);
            	    r=bitOrExpr();

            	    state._fsp--;
            	    if (state.failed) return expr;

            	    if ( state.backtracking==0 ) { stack.push(new BinaryExpression(Operator.LOG_AND, null, r)); }

            	    }
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);


            if ( state.backtracking==0 ) {
            			if(stack.size() > 0)
            			{
            				while(stack.size() > 1)
            				{
            					expr = stack.pop();
            					((BinaryExpression)expr).leftOperand = stack.peek().rightOperand;
            					stack.peek().rightOperand = expr;
            					((BinaryExpression)expr).leftOperand.parent = expr;
            					((BinaryExpression)expr).rightOperand.parent = expr;
            				}
            				expr = stack.pop();
            				((BinaryExpression)expr).leftOperand = l;
            				((BinaryExpression)expr).leftOperand.parent = expr;
            				((BinaryExpression)expr).rightOperand.parent = expr;
            			}
            			else
            				expr = l;
            		}

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 17, logAndExpr_StartIndex); }

        }
        return expr;
    }
    // $ANTLR end "logAndExpr"



    // $ANTLR start "bitOrExpr"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:183:1: bitOrExpr returns [Expression expr] : l= bitXorExpr ( '|' r= bitXorExpr )* ;
    public final Expression bitOrExpr() throws RecognitionException {
        Expression expr = null;

        int bitOrExpr_StartIndex = input.index();

        Expression l =null;

        Expression r =null;


         Stack<BinaryExpression> stack = new Stack<BinaryExpression>(); 
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 18) ) { return expr; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:185:2: (l= bitXorExpr ( '|' r= bitXorExpr )* )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:185:4: l= bitXorExpr ( '|' r= bitXorExpr )*
            {
            pushFollow(FOLLOW_bitXorExpr_in_bitOrExpr679);
            l=bitXorExpr();

            state._fsp--;
            if (state.failed) return expr;

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:185:17: ( '|' r= bitXorExpr )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==83) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:185:18: '|' r= bitXorExpr
            	    {
            	    match(input,83,FOLLOW_83_in_bitOrExpr682); if (state.failed) return expr;

            	    pushFollow(FOLLOW_bitXorExpr_in_bitOrExpr686);
            	    r=bitXorExpr();

            	    state._fsp--;
            	    if (state.failed) return expr;

            	    if ( state.backtracking==0 ) { stack.push(new BinaryExpression(Operator.OR, null, r)); }

            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);


            if ( state.backtracking==0 ) {
            			if(stack.size() > 0)
            			{
            				while(stack.size() > 1)
            				{
            					expr = stack.pop();
            					((BinaryExpression)expr).leftOperand = stack.peek().rightOperand;
            					stack.peek().rightOperand = expr;
            					((BinaryExpression)expr).leftOperand.parent = expr;
            					((BinaryExpression)expr).rightOperand.parent = expr;
            				}
            				expr = stack.pop();
            				((BinaryExpression)expr).leftOperand = l;
            				((BinaryExpression)expr).leftOperand.parent = expr;
            				((BinaryExpression)expr).rightOperand.parent = expr;
            			}
            			else
            				expr = l;
            		}

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 18, bitOrExpr_StartIndex); }

        }
        return expr;
    }
    // $ANTLR end "bitOrExpr"



    // $ANTLR start "bitXorExpr"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:207:1: bitXorExpr returns [Expression expr] : l= bitAndExpr ( '^' r= bitAndExpr )* ;
    public final Expression bitXorExpr() throws RecognitionException {
        Expression expr = null;

        int bitXorExpr_StartIndex = input.index();

        Expression l =null;

        Expression r =null;


         Stack<BinaryExpression> stack = new Stack<BinaryExpression>(); 
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 19) ) { return expr; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:209:2: (l= bitAndExpr ( '^' r= bitAndExpr )* )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:209:4: l= bitAndExpr ( '^' r= bitAndExpr )*
            {
            pushFollow(FOLLOW_bitAndExpr_in_bitXorExpr717);
            l=bitAndExpr();

            state._fsp--;
            if (state.failed) return expr;

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:209:17: ( '^' r= bitAndExpr )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==71) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:209:18: '^' r= bitAndExpr
            	    {
            	    match(input,71,FOLLOW_71_in_bitXorExpr720); if (state.failed) return expr;

            	    pushFollow(FOLLOW_bitAndExpr_in_bitXorExpr724);
            	    r=bitAndExpr();

            	    state._fsp--;
            	    if (state.failed) return expr;

            	    if ( state.backtracking==0 ) { stack.push(new BinaryExpression(Operator.XOR, null, r)); }

            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);


            if ( state.backtracking==0 ) {
            			if(stack.size() > 0)
            			{
            				while(stack.size() > 1)
            				{
            					expr = stack.pop();
            					((BinaryExpression)expr).leftOperand = stack.peek().rightOperand;
            					stack.peek().rightOperand = expr;
            					((BinaryExpression)expr).leftOperand.parent = expr;
            					((BinaryExpression)expr).rightOperand.parent = expr;
            				}
            				expr = stack.pop();
            				((BinaryExpression)expr).leftOperand = l;
            				((BinaryExpression)expr).leftOperand.parent = expr;
            				((BinaryExpression)expr).rightOperand.parent = expr;
            			}
            			else
            				expr = l;
            		}

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 19, bitXorExpr_StartIndex); }

        }
        return expr;
    }
    // $ANTLR end "bitXorExpr"



    // $ANTLR start "bitAndExpr"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:231:1: bitAndExpr returns [Expression expr] : l= eqExpr ( '&' r= eqExpr )* ;
    public final Expression bitAndExpr() throws RecognitionException {
        Expression expr = null;

        int bitAndExpr_StartIndex = input.index();

        Expression l =null;

        Expression r =null;


         Stack<BinaryExpression> stack = new Stack<BinaryExpression>(); 
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 20) ) { return expr; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:233:2: (l= eqExpr ( '&' r= eqExpr )* )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:233:4: l= eqExpr ( '&' r= eqExpr )*
            {
            pushFollow(FOLLOW_eqExpr_in_bitAndExpr755);
            l=eqExpr();

            state._fsp--;
            if (state.failed) return expr;

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:233:13: ( '&' r= eqExpr )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==41) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:233:14: '&' r= eqExpr
            	    {
            	    match(input,41,FOLLOW_41_in_bitAndExpr758); if (state.failed) return expr;

            	    pushFollow(FOLLOW_eqExpr_in_bitAndExpr762);
            	    r=eqExpr();

            	    state._fsp--;
            	    if (state.failed) return expr;

            	    if ( state.backtracking==0 ) { stack.push(new BinaryExpression(Operator.AND, null, r)); }

            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);


            if ( state.backtracking==0 ) {
            			if(stack.size() > 0)
            			{
            				while(stack.size() > 1)
            				{
            					expr = stack.pop();
            					((BinaryExpression)expr).leftOperand = stack.peek().rightOperand;
            					stack.peek().rightOperand = expr;
            					((BinaryExpression)expr).leftOperand.parent = expr;
            					((BinaryExpression)expr).rightOperand.parent = expr;
            				}
            				expr = stack.pop();
            				((BinaryExpression)expr).leftOperand = l;
            				((BinaryExpression)expr).leftOperand.parent = expr;
            				((BinaryExpression)expr).rightOperand.parent = expr;
            			}
            			else
            				expr = l;
            		}

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 20, bitAndExpr_StartIndex); }

        }
        return expr;
    }
    // $ANTLR end "bitAndExpr"



    // $ANTLR start "eqExpr"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:255:1: eqExpr returns [Expression expr] : l= relExpr (op= eqOp r= relExpr )* ;
    public final Expression eqExpr() throws RecognitionException {
        Expression expr = null;

        int eqExpr_StartIndex = input.index();

        Expression l =null;

        int op =0;

        Expression r =null;


         Stack<BinaryExpression> stack = new Stack<BinaryExpression>(); 
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 21) ) { return expr; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:257:2: (l= relExpr (op= eqOp r= relExpr )* )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:257:4: l= relExpr (op= eqOp r= relExpr )*
            {
            pushFollow(FOLLOW_relExpr_in_eqExpr793);
            l=relExpr();

            state._fsp--;
            if (state.failed) return expr;

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:257:14: (op= eqOp r= relExpr )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==37||LA17_0==64) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:257:15: op= eqOp r= relExpr
            	    {
            	    pushFollow(FOLLOW_eqOp_in_eqExpr798);
            	    op=eqOp();

            	    state._fsp--;
            	    if (state.failed) return expr;

            	    pushFollow(FOLLOW_relExpr_in_eqExpr802);
            	    r=relExpr();

            	    state._fsp--;
            	    if (state.failed) return expr;

            	    if ( state.backtracking==0 ) { stack.push(new BinaryExpression(op, null, r)); }

            	    }
            	    break;

            	default :
            	    break loop17;
                }
            } while (true);


            if ( state.backtracking==0 ) {
            			if(stack.size() > 0)
            			{
            				while(stack.size() > 1)
            				{
            					expr = stack.pop();
            					((BinaryExpression)expr).leftOperand = stack.peek().rightOperand;
            					stack.peek().rightOperand = expr;
            					((BinaryExpression)expr).leftOperand.parent = expr;
            					((BinaryExpression)expr).rightOperand.parent = expr;
            				}
            				expr = stack.pop();
            				((BinaryExpression)expr).leftOperand = l;
            				((BinaryExpression)expr).leftOperand.parent = expr;
            				((BinaryExpression)expr).rightOperand.parent = expr;
            			}
            			else
            				expr = l;
            		}

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 21, eqExpr_StartIndex); }

        }
        return expr;
    }
    // $ANTLR end "eqExpr"



    // $ANTLR start "relExpr"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:279:1: relExpr returns [Expression expr] : l= shiftExpr (op= relOp r= shiftExpr )* ;
    public final Expression relExpr() throws RecognitionException {
        Expression expr = null;

        int relExpr_StartIndex = input.index();

        Expression l =null;

        int op =0;

        Expression r =null;


         Stack<BinaryExpression> stack = new Stack<BinaryExpression>(); 
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 22) ) { return expr; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:281:2: (l= shiftExpr (op= relOp r= shiftExpr )* )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:281:4: l= shiftExpr (op= relOp r= shiftExpr )*
            {
            pushFollow(FOLLOW_shiftExpr_in_relExpr833);
            l=shiftExpr();

            state._fsp--;
            if (state.failed) return expr;

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:281:16: (op= relOp r= shiftExpr )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==59||LA18_0==62||(LA18_0 >= 65 && LA18_0 <= 66)) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:281:17: op= relOp r= shiftExpr
            	    {
            	    pushFollow(FOLLOW_relOp_in_relExpr838);
            	    op=relOp();

            	    state._fsp--;
            	    if (state.failed) return expr;

            	    pushFollow(FOLLOW_shiftExpr_in_relExpr842);
            	    r=shiftExpr();

            	    state._fsp--;
            	    if (state.failed) return expr;

            	    if ( state.backtracking==0 ) { stack.push(new BinaryExpression(op, null, r)); }

            	    }
            	    break;

            	default :
            	    break loop18;
                }
            } while (true);


            if ( state.backtracking==0 ) {
            			if(stack.size() > 0)
            			{
            				while(stack.size() > 1)
            				{
            					expr = stack.pop();
            					((BinaryExpression)expr).leftOperand = stack.peek().rightOperand;
            					stack.peek().rightOperand = expr;
            					((BinaryExpression)expr).leftOperand.parent = expr;
            					((BinaryExpression)expr).rightOperand.parent = expr;
            				}
            				expr = stack.pop();
            				((BinaryExpression)expr).leftOperand = l;
            				((BinaryExpression)expr).leftOperand.parent = expr;
            				((BinaryExpression)expr).rightOperand.parent = expr;
            			}
            			else
            				expr = l;
            		}

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 22, relExpr_StartIndex); }

        }
        return expr;
    }
    // $ANTLR end "relExpr"



    // $ANTLR start "shiftExpr"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:303:1: shiftExpr returns [Expression expr] : l= addExpr (op= shiftOp r= addExpr )* ;
    public final Expression shiftExpr() throws RecognitionException {
        Expression expr = null;

        int shiftExpr_StartIndex = input.index();

        Expression l =null;

        int op =0;

        Expression r =null;


         Stack<BinaryExpression> stack = new Stack<BinaryExpression>(); 
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 23) ) { return expr; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:305:2: (l= addExpr (op= shiftOp r= addExpr )* )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:305:4: l= addExpr (op= shiftOp r= addExpr )*
            {
            pushFollow(FOLLOW_addExpr_in_shiftExpr873);
            l=addExpr();

            state._fsp--;
            if (state.failed) return expr;

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:305:14: (op= shiftOp r= addExpr )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==60||LA19_0==67) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:305:15: op= shiftOp r= addExpr
            	    {
            	    pushFollow(FOLLOW_shiftOp_in_shiftExpr878);
            	    op=shiftOp();

            	    state._fsp--;
            	    if (state.failed) return expr;

            	    pushFollow(FOLLOW_addExpr_in_shiftExpr882);
            	    r=addExpr();

            	    state._fsp--;
            	    if (state.failed) return expr;

            	    if ( state.backtracking==0 ) { stack.push(new BinaryExpression(op, null, r)); }

            	    }
            	    break;

            	default :
            	    break loop19;
                }
            } while (true);


            if ( state.backtracking==0 ) {
            			if(stack.size() > 0)
            			{
            				while(stack.size() > 1)
            				{
            					expr = stack.pop();
            					((BinaryExpression)expr).leftOperand = stack.peek().rightOperand;
            					stack.peek().rightOperand = expr;
            					((BinaryExpression)expr).leftOperand.parent = expr;
            					((BinaryExpression)expr).rightOperand.parent = expr;
            				}
            				expr = stack.pop();
            				((BinaryExpression)expr).leftOperand = l;
            				((BinaryExpression)expr).leftOperand.parent = expr;
            				((BinaryExpression)expr).rightOperand.parent = expr;
            			}
            			else
            				expr = l;
            		}

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 23, shiftExpr_StartIndex); }

        }
        return expr;
    }
    // $ANTLR end "shiftExpr"



    // $ANTLR start "addExpr"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:327:1: addExpr returns [Expression expr] : l= mulExpr (op= addOp r= mulExpr )* ;
    public final Expression addExpr() throws RecognitionException {
        Expression expr = null;

        int addExpr_StartIndex = input.index();

        Expression l =null;

        int op =0;

        Expression r =null;


         Stack<BinaryExpression> stack = new Stack<BinaryExpression>(); 
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 24) ) { return expr; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:329:2: (l= mulExpr (op= addOp r= mulExpr )* )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:329:4: l= mulExpr (op= addOp r= mulExpr )*
            {
            pushFollow(FOLLOW_mulExpr_in_addExpr913);
            l=mulExpr();

            state._fsp--;
            if (state.failed) return expr;

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:329:14: (op= addOp r= mulExpr )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==47||LA20_0==51) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:329:15: op= addOp r= mulExpr
            	    {
            	    pushFollow(FOLLOW_addOp_in_addExpr918);
            	    op=addOp();

            	    state._fsp--;
            	    if (state.failed) return expr;

            	    pushFollow(FOLLOW_mulExpr_in_addExpr922);
            	    r=mulExpr();

            	    state._fsp--;
            	    if (state.failed) return expr;

            	    if ( state.backtracking==0 ) { stack.push(new BinaryExpression(op, null, r)); }

            	    }
            	    break;

            	default :
            	    break loop20;
                }
            } while (true);


            if ( state.backtracking==0 ) {
            			if(stack.size() > 0)
            			{
            				while(stack.size() > 1)
            				{
            					expr = stack.pop();
            					((BinaryExpression)expr).leftOperand = stack.peek().rightOperand;
            					stack.peek().rightOperand = expr;
            					((BinaryExpression)expr).leftOperand.parent = expr;
            					((BinaryExpression)expr).rightOperand.parent = expr;
            				}
            				expr = stack.pop();
            				((BinaryExpression)expr).leftOperand = l;
            				((BinaryExpression)expr).leftOperand.parent = expr;
            				((BinaryExpression)expr).rightOperand.parent = expr;
            			}
            			else
            				expr = l;
            		}

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 24, addExpr_StartIndex); }

        }
        return expr;
    }
    // $ANTLR end "addExpr"



    // $ANTLR start "mulExpr"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:351:1: mulExpr returns [Expression expr] : l= unaryExpr (op= mulOp r= unaryExpr )* ;
    public final Expression mulExpr() throws RecognitionException {
        Expression expr = null;

        int mulExpr_StartIndex = input.index();

        Expression l =null;

        int op =0;

        Expression r =null;


         Stack<BinaryExpression> stack = new Stack<BinaryExpression>(); 
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 25) ) { return expr; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:353:2: (l= unaryExpr (op= mulOp r= unaryExpr )* )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:353:4: l= unaryExpr (op= mulOp r= unaryExpr )*
            {
            pushFollow(FOLLOW_unaryExpr_in_mulExpr953);
            l=unaryExpr();

            state._fsp--;
            if (state.failed) return expr;

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:353:16: (op= mulOp r= unaryExpr )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==38||LA21_0==45||LA21_0==55) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:353:17: op= mulOp r= unaryExpr
            	    {
            	    pushFollow(FOLLOW_mulOp_in_mulExpr958);
            	    op=mulOp();

            	    state._fsp--;
            	    if (state.failed) return expr;

            	    pushFollow(FOLLOW_unaryExpr_in_mulExpr962);
            	    r=unaryExpr();

            	    state._fsp--;
            	    if (state.failed) return expr;

            	    if ( state.backtracking==0 ) { stack.push(new BinaryExpression(op, null, r)); }

            	    }
            	    break;

            	default :
            	    break loop21;
                }
            } while (true);


            if ( state.backtracking==0 ) {
            			if(stack.size() > 0)
            			{
            				while(stack.size() > 1)
            				{
            					expr = stack.pop();
            					((BinaryExpression)expr).leftOperand = stack.peek().rightOperand;
            					stack.peek().rightOperand = expr;
            					((BinaryExpression)expr).leftOperand.parent = expr;
            					((BinaryExpression)expr).rightOperand.parent = expr;
            				}
            				expr = stack.pop();
            				((BinaryExpression)expr).leftOperand = l;
            				((BinaryExpression)expr).leftOperand.parent = expr;
            				((BinaryExpression)expr).rightOperand.parent = expr;
            			}
            			else
            				expr = l;
            		}

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 25, mulExpr_StartIndex); }

        }
        return expr;
    }
    // $ANTLR end "mulExpr"



    // $ANTLR start "unaryExpr"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:375:1: unaryExpr returns [Expression expr] : (op= unaryOp )? postfixExpr ;
    public final Expression unaryExpr() throws RecognitionException {
        Expression expr = null;

        int unaryExpr_StartIndex = input.index();

        Integer op =null;

        Expression postfixExpr19 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 26) ) { return expr; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:376:2: ( (op= unaryOp )? postfixExpr )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:376:4: (op= unaryOp )? postfixExpr
            {
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:376:4: (op= unaryOp )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==36||LA22_0==47||LA22_0==51||LA22_0==87) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:376:5: op= unaryOp
                    {
                    pushFollow(FOLLOW_unaryOp_in_unaryExpr988);
                    op=unaryOp();

                    state._fsp--;
                    if (state.failed) return expr;

                    }
                    break;

            }


            pushFollow(FOLLOW_postfixExpr_in_unaryExpr992);
            postfixExpr19=postfixExpr();

            state._fsp--;
            if (state.failed) return expr;

            if ( state.backtracking==0 ) {
            			if(op != null)
            			{
            				expr = new UnaryExpression((int)op, postfixExpr19, Operator.UNKNOWN);
            				postfixExpr19.parent = expr;
            			}
            			else
            				expr = postfixExpr19;
            		}

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 26, unaryExpr_StartIndex); }

        }
        return expr;
    }
    // $ANTLR end "unaryExpr"



    // $ANTLR start "postfixExpr"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:388:1: postfixExpr returns [Expression expr] : atom (op= postfixOp )? ;
    public final Expression postfixExpr() throws RecognitionException {
        Expression expr = null;

        int postfixExpr_StartIndex = input.index();

        Integer op =null;

        Expression atom20 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 27) ) { return expr; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:389:2: ( atom (op= postfixOp )? )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:389:4: atom (op= postfixOp )?
            {
            pushFollow(FOLLOW_atom_in_postfixExpr1011);
            atom20=atom();

            state._fsp--;
            if (state.failed) return expr;

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:389:9: (op= postfixOp )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==48||LA23_0==52) ) {
                alt23=1;
            }
            switch (alt23) {
                case 1 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:389:10: op= postfixOp
                    {
                    pushFollow(FOLLOW_postfixOp_in_postfixExpr1016);
                    op=postfixOp();

                    state._fsp--;
                    if (state.failed) return expr;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {
            			if(op != null)
            			{
            				expr = new UnaryExpression(Operator.UNKNOWN, atom20, (int)op);
            				atom20.parent = expr;
            			}
            			else
            				expr = atom20;
            		}

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 27, postfixExpr_StartIndex); }

        }
        return expr;
    }
    // $ANTLR end "postfixExpr"



    // $ANTLR start "atom"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:401:1: atom returns [Expression expr] : ( literal | '(' e= expr ')' | variable ( ( '[' index= expr ']' ) | ( '.' member= IDENTIFIER ) )* | funcCall );
    public final Expression atom() throws RecognitionException {
        Expression expr = null;

        int atom_StartIndex = input.index();

        Token member=null;
        Expression e =null;

        Expression index =null;

        Expression literal21 =null;

        Variable variable22 =null;

        FunctionCall funcCall23 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 28) ) { return expr; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:402:2: ( literal | '(' e= expr ')' | variable ( ( '[' index= expr ']' ) | ( '.' member= IDENTIFIER ) )* | funcCall )
            int alt25=4;
            switch ( input.LA(1) ) {
            case BOOLEAN:
            case INTEGER:
            case REAL:
            case STRING:
            case 69:
                {
                alt25=1;
                }
                break;
            case 43:
                {
                alt25=2;
                }
                break;
            case IDENTIFIER:
                {
                int LA25_3 = input.LA(2);

                if ( (LA25_3==43) ) {
                    alt25=4;
                }
                else if ( (LA25_3==EOF||(LA25_3 >= 37 && LA25_3 <= 42)||(LA25_3 >= 44 && LA25_3 <= 72)||(LA25_3 >= 83 && LA25_3 <= 85)) ) {
                    alt25=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 25, 3, input);

                    throw nvae;

                }
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 25, 0, input);

                throw nvae;

            }

            switch (alt25) {
                case 1 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:402:4: literal
                    {
                    pushFollow(FOLLOW_literal_in_atom1038);
                    literal21=literal();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) { expr = literal21; }

                    }
                    break;
                case 2 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:404:4: '(' e= expr ')'
                    {
                    match(input,43,FOLLOW_43_in_atom1047); if (state.failed) return expr;

                    pushFollow(FOLLOW_expr_in_atom1051);
                    e=expr();

                    state._fsp--;
                    if (state.failed) return expr;

                    match(input,44,FOLLOW_44_in_atom1053); if (state.failed) return expr;

                    if ( state.backtracking==0 ) { expr = e; }

                    }
                    break;
                case 3 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:406:4: variable ( ( '[' index= expr ']' ) | ( '.' member= IDENTIFIER ) )*
                    {
                    pushFollow(FOLLOW_variable_in_atom1062);
                    variable22=variable();

                    state._fsp--;
                    if (state.failed) return expr;

                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:406:13: ( ( '[' index= expr ']' ) | ( '.' member= IDENTIFIER ) )*
                    loop24:
                    do {
                        int alt24=3;
                        int LA24_0 = input.LA(1);

                        if ( (LA24_0==69) ) {
                            alt24=1;
                        }
                        else if ( (LA24_0==54) ) {
                            alt24=2;
                        }


                        switch (alt24) {
                    	case 1 :
                    	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:406:15: ( '[' index= expr ']' )
                    	    {
                    	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:406:15: ( '[' index= expr ']' )
                    	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:406:16: '[' index= expr ']'
                    	    {
                    	    match(input,69,FOLLOW_69_in_atom1067); if (state.failed) return expr;

                    	    pushFollow(FOLLOW_expr_in_atom1071);
                    	    index=expr();

                    	    state._fsp--;
                    	    if (state.failed) return expr;

                    	    match(input,70,FOLLOW_70_in_atom1073); if (state.failed) return expr;

                    	    if ( state.backtracking==0 ) { variable22.isArray = true; variable22.members.add(index); }

                    	    }


                    	    }
                    	    break;
                    	case 2 :
                    	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:407:17: ( '.' member= IDENTIFIER )
                    	    {
                    	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:407:17: ( '.' member= IDENTIFIER )
                    	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:407:18: '.' member= IDENTIFIER
                    	    {
                    	    match(input,54,FOLLOW_54_in_atom1097); if (state.failed) return expr;

                    	    member=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_atom1101); if (state.failed) return expr;

                    	    if ( state.backtracking==0 ) { variable22.isArray = true; variable22.members.add(new StringAtom((member!=null?member.getText():null))); }

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop24;
                        }
                    } while (true);


                    if ( state.backtracking==0 ) { expr = variable22; }

                    }
                    break;
                case 4 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:409:4: funcCall
                    {
                    pushFollow(FOLLOW_funcCall_in_atom1116);
                    funcCall23=funcCall();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) { expr = funcCall23; }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 28, atom_StartIndex); }

        }
        return expr;
    }
    // $ANTLR end "atom"



    // $ANTLR start "literal"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:413:1: literal returns [Expression expr] : ( STRING | INTEGER | REAL | BOOLEAN | arrayConstructor );
    public final Expression literal() throws RecognitionException {
        Expression expr = null;

        int literal_StartIndex = input.index();

        Token STRING24=null;
        Token INTEGER25=null;
        Token REAL26=null;
        Token BOOLEAN27=null;
        ArrayConstructor arrayConstructor28 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 29) ) { return expr; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:414:2: ( STRING | INTEGER | REAL | BOOLEAN | arrayConstructor )
            int alt26=5;
            switch ( input.LA(1) ) {
            case STRING:
                {
                alt26=1;
                }
                break;
            case INTEGER:
                {
                alt26=2;
                }
                break;
            case REAL:
                {
                alt26=3;
                }
                break;
            case BOOLEAN:
                {
                alt26=4;
                }
                break;
            case 69:
                {
                alt26=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 26, 0, input);

                throw nvae;

            }

            switch (alt26) {
                case 1 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:414:4: STRING
                    {
                    STRING24=(Token)match(input,STRING,FOLLOW_STRING_in_literal1137); if (state.failed) return expr;

                    if ( state.backtracking==0 ) { expr = new StringAtom((STRING24!=null?STRING24.getText():null).substring(1, (STRING24!=null?STRING24.getText():null).length() - 1)); }

                    }
                    break;
                case 2 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:415:4: INTEGER
                    {
                    INTEGER25=(Token)match(input,INTEGER,FOLLOW_INTEGER_in_literal1145); if (state.failed) return expr;

                    if ( state.backtracking==0 ) { expr = new IntegerAtom(Integer.parseInt((INTEGER25!=null?INTEGER25.getText():null))); }

                    }
                    break;
                case 3 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:416:4: REAL
                    {
                    REAL26=(Token)match(input,REAL,FOLLOW_REAL_in_literal1152); if (state.failed) return expr;

                    if ( state.backtracking==0 ) { expr = new RealAtom(Double.parseDouble((REAL26!=null?REAL26.getText():null))); }

                    }
                    break;
                case 4 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:417:4: BOOLEAN
                    {
                    BOOLEAN27=(Token)match(input,BOOLEAN,FOLLOW_BOOLEAN_in_literal1162); if (state.failed) return expr;

                    if ( state.backtracking==0 ) { expr = new BooleanAtom(Boolean.parseBoolean((BOOLEAN27!=null?BOOLEAN27.getText():null))); }

                    }
                    break;
                case 5 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:418:4: arrayConstructor
                    {
                    pushFollow(FOLLOW_arrayConstructor_in_literal1169);
                    arrayConstructor28=arrayConstructor();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) { expr = arrayConstructor28; }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 29, literal_StartIndex); }

        }
        return expr;
    }
    // $ANTLR end "literal"



    // $ANTLR start "variable"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:421:1: variable returns [Variable expr] : IDENTIFIER ;
    public final Variable variable() throws RecognitionException {
        Variable expr = null;

        int variable_StartIndex = input.index();

        Token IDENTIFIER29=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 30) ) { return expr; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:422:2: ( IDENTIFIER )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:422:4: IDENTIFIER
            {
            IDENTIFIER29=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_variable1187); if (state.failed) return expr;

            if ( state.backtracking==0 ) { expr = new Variable((IDENTIFIER29!=null?IDENTIFIER29.getText():null)); }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 30, variable_StartIndex); }

        }
        return expr;
    }
    // $ANTLR end "variable"



    // $ANTLR start "arrayConstructor"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:425:1: arrayConstructor returns [ArrayConstructor expr] : '[' (e= arrayElem ( ',' e= arrayElem )* )? ']' ;
    public final ArrayConstructor arrayConstructor() throws RecognitionException {
        ArrayConstructor expr = null;

        int arrayConstructor_StartIndex = input.index();

        ArrayConstructor.Item e =null;


         expr = new ArrayConstructor(); 
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 31) ) { return expr; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:427:2: ( '[' (e= arrayElem ( ',' e= arrayElem )* )? ']' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:427:4: '[' (e= arrayElem ( ',' e= arrayElem )* )? ']'
            {
            match(input,69,FOLLOW_69_in_arrayConstructor1211); if (state.failed) return expr;

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:427:8: (e= arrayElem ( ',' e= arrayElem )* )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==BOOLEAN||(LA28_0 >= IDENTIFIER && LA28_0 <= INTEGER)||LA28_0==REAL||LA28_0==STRING||LA28_0==36||LA28_0==43||LA28_0==47||LA28_0==51||LA28_0==69||LA28_0==87) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:427:10: e= arrayElem ( ',' e= arrayElem )*
                    {
                    pushFollow(FOLLOW_arrayElem_in_arrayConstructor1217);
                    e=arrayElem();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) { expr.items.put(e.key, e.value); }

                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:427:73: ( ',' e= arrayElem )*
                    loop27:
                    do {
                        int alt27=2;
                        int LA27_0 = input.LA(1);

                        if ( (LA27_0==50) ) {
                            alt27=1;
                        }


                        switch (alt27) {
                    	case 1 :
                    	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:427:75: ',' e= arrayElem
                    	    {
                    	    match(input,50,FOLLOW_50_in_arrayConstructor1223); if (state.failed) return expr;

                    	    pushFollow(FOLLOW_arrayElem_in_arrayConstructor1227);
                    	    e=arrayElem();

                    	    state._fsp--;
                    	    if (state.failed) return expr;

                    	    if ( state.backtracking==0 ) { expr.items.put(e.key, e.value); }

                    	    }
                    	    break;

                    	default :
                    	    break loop27;
                        }
                    } while (true);


                    }
                    break;

            }


            match(input,70,FOLLOW_70_in_arrayConstructor1237); if (state.failed) return expr;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 31, arrayConstructor_StartIndex); }

        }
        return expr;
    }
    // $ANTLR end "arrayConstructor"



    // $ANTLR start "arrayElem"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:430:1: arrayElem returns [ArrayConstructor.Item entry] : key= expr ':' val= expr ;
    public final ArrayConstructor.Item arrayElem() throws RecognitionException {
        ArrayConstructor.Item entry = null;

        int arrayElem_StartIndex = input.index();

        Expression key =null;

        Expression val =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 32) ) { return entry; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:431:2: (key= expr ':' val= expr )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:431:4: key= expr ':' val= expr
            {
            pushFollow(FOLLOW_expr_in_arrayElem1255);
            key=expr();

            state._fsp--;
            if (state.failed) return entry;

            match(input,57,FOLLOW_57_in_arrayElem1257); if (state.failed) return entry;

            pushFollow(FOLLOW_expr_in_arrayElem1261);
            val=expr();

            state._fsp--;
            if (state.failed) return entry;

            if ( state.backtracking==0 ) { entry = new ArrayConstructor.Item(key, val); }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 32, arrayElem_StartIndex); }

        }
        return entry;
    }
    // $ANTLR end "arrayElem"



    // $ANTLR start "funcCall"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:435:1: funcCall returns [FunctionCall expr] : IDENTIFIER '(' ( exprList )? ')' ;
    public final FunctionCall funcCall() throws RecognitionException {
        FunctionCall expr = null;

        int funcCall_StartIndex = input.index();

        Token IDENTIFIER30=null;
        ExpressionList exprList31 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 33) ) { return expr; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:436:2: ( IDENTIFIER '(' ( exprList )? ')' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:436:4: IDENTIFIER '(' ( exprList )? ')'
            {
            IDENTIFIER30=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_funcCall1281); if (state.failed) return expr;

            match(input,43,FOLLOW_43_in_funcCall1283); if (state.failed) return expr;

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:436:19: ( exprList )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==BOOLEAN||(LA29_0 >= IDENTIFIER && LA29_0 <= INTEGER)||LA29_0==REAL||LA29_0==STRING||LA29_0==36||LA29_0==43||LA29_0==47||LA29_0==51||LA29_0==69||LA29_0==87) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:436:19: exprList
                    {
                    pushFollow(FOLLOW_exprList_in_funcCall1285);
                    exprList31=exprList();

                    state._fsp--;
                    if (state.failed) return expr;

                    }
                    break;

            }


            match(input,44,FOLLOW_44_in_funcCall1288); if (state.failed) return expr;

            if ( state.backtracking==0 ) { expr = new FunctionCall((IDENTIFIER30!=null?IDENTIFIER30.getText():null), exprList31); if(exprList31 != null) exprList31.parent = expr; }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 33, funcCall_StartIndex); }

        }
        return expr;
    }
    // $ANTLR end "funcCall"



    // $ANTLR start "exprList"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:440:1: exprList returns [ExpressionList exprList] : e= expr ( ',' e= expr )* ;
    public final ExpressionList exprList() throws RecognitionException {
        ExpressionList exprList = null;

        int exprList_StartIndex = input.index();

        Expression e =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 34) ) { return exprList; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:441:2: (e= expr ( ',' e= expr )* )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:441:4: e= expr ( ',' e= expr )*
            {
            pushFollow(FOLLOW_expr_in_exprList1310);
            e=expr();

            state._fsp--;
            if (state.failed) return exprList;

            if ( state.backtracking==0 ) {
            			exprList = new ExpressionList();
            			exprList.expressions.add(e);
            			e.parent = exprList;
            		}

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:447:3: ( ',' e= expr )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( (LA30_0==50) ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:447:5: ',' e= expr
            	    {
            	    match(input,50,FOLLOW_50_in_exprList1320); if (state.failed) return exprList;

            	    pushFollow(FOLLOW_expr_in_exprList1324);
            	    e=expr();

            	    state._fsp--;
            	    if (state.failed) return exprList;

            	    if ( state.backtracking==0 ) {
            	    				exprList.expressions.add(e);
            	    				e.parent = exprList;
            	    			}

            	    }
            	    break;

            	default :
            	    break loop30;
                }
            } while (true);


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 34, exprList_StartIndex); }

        }
        return exprList;
    }
    // $ANTLR end "exprList"



    // $ANTLR start "eqOp"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:455:1: eqOp returns [int operator] : ( '==' | '!=' );
    public final int eqOp() throws RecognitionException {
        int operator = 0;

        int eqOp_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 35) ) { return operator; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:456:2: ( '==' | '!=' )
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==64) ) {
                alt31=1;
            }
            else if ( (LA31_0==37) ) {
                alt31=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return operator;}
                NoViableAltException nvae =
                    new NoViableAltException("", 31, 0, input);

                throw nvae;

            }
            switch (alt31) {
                case 1 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:456:4: '=='
                    {
                    match(input,64,FOLLOW_64_in_eqOp1349); if (state.failed) return operator;

                    if ( state.backtracking==0 ) { operator = Operator. EQ; }

                    }
                    break;
                case 2 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:457:4: '!='
                    {
                    match(input,37,FOLLOW_37_in_eqOp1356); if (state.failed) return operator;

                    if ( state.backtracking==0 ) { operator = Operator.NEQ; }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 35, eqOp_StartIndex); }

        }
        return operator;
    }
    // $ANTLR end "eqOp"



    // $ANTLR start "relOp"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:460:1: relOp returns [int operator] : ( '<' | '>' | '<=' | '>=' );
    public final int relOp() throws RecognitionException {
        int operator = 0;

        int relOp_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 36) ) { return operator; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:461:2: ( '<' | '>' | '<=' | '>=' )
            int alt32=4;
            switch ( input.LA(1) ) {
            case 59:
                {
                alt32=1;
                }
                break;
            case 65:
                {
                alt32=2;
                }
                break;
            case 62:
                {
                alt32=3;
                }
                break;
            case 66:
                {
                alt32=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return operator;}
                NoViableAltException nvae =
                    new NoViableAltException("", 32, 0, input);

                throw nvae;

            }

            switch (alt32) {
                case 1 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:461:4: '<'
                    {
                    match(input,59,FOLLOW_59_in_relOp1373); if (state.failed) return operator;

                    if ( state.backtracking==0 ) { operator = Operator.LT ; }

                    }
                    break;
                case 2 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:462:4: '>'
                    {
                    match(input,65,FOLLOW_65_in_relOp1381); if (state.failed) return operator;

                    if ( state.backtracking==0 ) { operator = Operator.GT ; }

                    }
                    break;
                case 3 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:463:4: '<='
                    {
                    match(input,62,FOLLOW_62_in_relOp1389); if (state.failed) return operator;

                    if ( state.backtracking==0 ) { operator = Operator.LTE; }

                    }
                    break;
                case 4 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:464:4: '>='
                    {
                    match(input,66,FOLLOW_66_in_relOp1396); if (state.failed) return operator;

                    if ( state.backtracking==0 ) { operator = Operator.GTE; }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 36, relOp_StartIndex); }

        }
        return operator;
    }
    // $ANTLR end "relOp"



    // $ANTLR start "shiftOp"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:467:1: shiftOp returns [int operator] : ( '<<' | '>>' );
    public final int shiftOp() throws RecognitionException {
        int operator = 0;

        int shiftOp_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 37) ) { return operator; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:468:2: ( '<<' | '>>' )
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==60) ) {
                alt33=1;
            }
            else if ( (LA33_0==67) ) {
                alt33=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return operator;}
                NoViableAltException nvae =
                    new NoViableAltException("", 33, 0, input);

                throw nvae;

            }
            switch (alt33) {
                case 1 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:468:4: '<<'
                    {
                    match(input,60,FOLLOW_60_in_shiftOp1413); if (state.failed) return operator;

                    if ( state.backtracking==0 ) { operator = Operator.LSH; }

                    }
                    break;
                case 2 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:469:4: '>>'
                    {
                    match(input,67,FOLLOW_67_in_shiftOp1420); if (state.failed) return operator;

                    if ( state.backtracking==0 ) { operator = Operator.RSH; }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 37, shiftOp_StartIndex); }

        }
        return operator;
    }
    // $ANTLR end "shiftOp"



    // $ANTLR start "addOp"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:472:1: addOp returns [int operator] : ( '+' | '-' );
    public final int addOp() throws RecognitionException {
        int operator = 0;

        int addOp_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 38) ) { return operator; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:473:2: ( '+' | '-' )
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==47) ) {
                alt34=1;
            }
            else if ( (LA34_0==51) ) {
                alt34=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return operator;}
                NoViableAltException nvae =
                    new NoViableAltException("", 34, 0, input);

                throw nvae;

            }
            switch (alt34) {
                case 1 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:473:4: '+'
                    {
                    match(input,47,FOLLOW_47_in_addOp1437); if (state.failed) return operator;

                    if ( state.backtracking==0 ) { operator = Operator.PLUS;  }

                    }
                    break;
                case 2 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:474:4: '-'
                    {
                    match(input,51,FOLLOW_51_in_addOp1444); if (state.failed) return operator;

                    if ( state.backtracking==0 ) { operator = Operator.MINUS; }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 38, addOp_StartIndex); }

        }
        return operator;
    }
    // $ANTLR end "addOp"



    // $ANTLR start "mulOp"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:477:1: mulOp returns [int operator] : ( '*' | '/' | '%' );
    public final int mulOp() throws RecognitionException {
        int operator = 0;

        int mulOp_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 39) ) { return operator; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:478:2: ( '*' | '/' | '%' )
            int alt35=3;
            switch ( input.LA(1) ) {
            case 45:
                {
                alt35=1;
                }
                break;
            case 55:
                {
                alt35=2;
                }
                break;
            case 38:
                {
                alt35=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return operator;}
                NoViableAltException nvae =
                    new NoViableAltException("", 35, 0, input);

                throw nvae;

            }

            switch (alt35) {
                case 1 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:478:4: '*'
                    {
                    match(input,45,FOLLOW_45_in_mulOp1462); if (state.failed) return operator;

                    if ( state.backtracking==0 ) { operator = Operator.MUL; }

                    }
                    break;
                case 2 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:479:4: '/'
                    {
                    match(input,55,FOLLOW_55_in_mulOp1469); if (state.failed) return operator;

                    if ( state.backtracking==0 ) { operator = Operator.DIV; }

                    }
                    break;
                case 3 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:480:4: '%'
                    {
                    match(input,38,FOLLOW_38_in_mulOp1476); if (state.failed) return operator;

                    if ( state.backtracking==0 ) { operator = Operator.MOD; }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 39, mulOp_StartIndex); }

        }
        return operator;
    }
    // $ANTLR end "mulOp"



    // $ANTLR start "assignOp"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:483:1: assignOp returns [int operator] : ( '+=' | '-=' | '%=' | '/=' | '*=' | '|=' | '&=' | '^=' | '>>=' | '<<=' | '=' );
    public final int assignOp() throws RecognitionException {
        int operator = 0;

        int assignOp_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 40) ) { return operator; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:484:2: ( '+=' | '-=' | '%=' | '/=' | '*=' | '|=' | '&=' | '^=' | '>>=' | '<<=' | '=' )
            int alt36=11;
            switch ( input.LA(1) ) {
            case 49:
                {
                alt36=1;
                }
                break;
            case 53:
                {
                alt36=2;
                }
                break;
            case 39:
                {
                alt36=3;
                }
                break;
            case 56:
                {
                alt36=4;
                }
                break;
            case 46:
                {
                alt36=5;
                }
                break;
            case 84:
                {
                alt36=6;
                }
                break;
            case 42:
                {
                alt36=7;
                }
                break;
            case 72:
                {
                alt36=8;
                }
                break;
            case 68:
                {
                alt36=9;
                }
                break;
            case 61:
                {
                alt36=10;
                }
                break;
            case 63:
                {
                alt36=11;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return operator;}
                NoViableAltException nvae =
                    new NoViableAltException("", 36, 0, input);

                throw nvae;

            }

            switch (alt36) {
                case 1 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:484:4: '+='
                    {
                    match(input,49,FOLLOW_49_in_assignOp1494); if (state.failed) return operator;

                    if ( state.backtracking==0 ) { operator = Operator.ADD_ASN; }

                    }
                    break;
                case 2 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:485:4: '-='
                    {
                    match(input,53,FOLLOW_53_in_assignOp1502); if (state.failed) return operator;

                    if ( state.backtracking==0 ) { operator = Operator.SUB_ASN; }

                    }
                    break;
                case 3 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:486:4: '%='
                    {
                    match(input,39,FOLLOW_39_in_assignOp1510); if (state.failed) return operator;

                    if ( state.backtracking==0 ) { operator = Operator.MOD_ASN; }

                    }
                    break;
                case 4 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:487:4: '/='
                    {
                    match(input,56,FOLLOW_56_in_assignOp1518); if (state.failed) return operator;

                    if ( state.backtracking==0 ) { operator = Operator.DIV_ASN; }

                    }
                    break;
                case 5 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:488:4: '*='
                    {
                    match(input,46,FOLLOW_46_in_assignOp1526); if (state.failed) return operator;

                    if ( state.backtracking==0 ) { operator = Operator.MUL_ASN; }

                    }
                    break;
                case 6 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:489:4: '|='
                    {
                    match(input,84,FOLLOW_84_in_assignOp1534); if (state.failed) return operator;

                    if ( state.backtracking==0 ) { operator = Operator. OR_ASN; }

                    }
                    break;
                case 7 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:490:4: '&='
                    {
                    match(input,42,FOLLOW_42_in_assignOp1542); if (state.failed) return operator;

                    if ( state.backtracking==0 ) { operator = Operator.AND_ASN; }

                    }
                    break;
                case 8 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:491:4: '^='
                    {
                    match(input,72,FOLLOW_72_in_assignOp1550); if (state.failed) return operator;

                    if ( state.backtracking==0 ) { operator = Operator.XOR_ASN; }

                    }
                    break;
                case 9 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:492:4: '>>='
                    {
                    match(input,68,FOLLOW_68_in_assignOp1558); if (state.failed) return operator;

                    if ( state.backtracking==0 ) { operator = Operator.RSH_ASN; }

                    }
                    break;
                case 10 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:493:4: '<<='
                    {
                    match(input,61,FOLLOW_61_in_assignOp1565); if (state.failed) return operator;

                    if ( state.backtracking==0 ) { operator = Operator.LSH_ASN; }

                    }
                    break;
                case 11 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:494:4: '='
                    {
                    match(input,63,FOLLOW_63_in_assignOp1572); if (state.failed) return operator;

                    if ( state.backtracking==0 ) { operator = Operator.ASN; }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 40, assignOp_StartIndex); }

        }
        return operator;
    }
    // $ANTLR end "assignOp"



    // $ANTLR start "unaryOp"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:497:1: unaryOp returns [Integer operator] : ( '~' | '!' | '-' | '+' );
    public final Integer unaryOp() throws RecognitionException {
        Integer operator = null;

        int unaryOp_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 41) ) { return operator; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:498:2: ( '~' | '!' | '-' | '+' )
            int alt37=4;
            switch ( input.LA(1) ) {
            case 87:
                {
                alt37=1;
                }
                break;
            case 36:
                {
                alt37=2;
                }
                break;
            case 51:
                {
                alt37=3;
                }
                break;
            case 47:
                {
                alt37=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return operator;}
                NoViableAltException nvae =
                    new NoViableAltException("", 37, 0, input);

                throw nvae;

            }

            switch (alt37) {
                case 1 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:498:4: '~'
                    {
                    match(input,87,FOLLOW_87_in_unaryOp1592); if (state.failed) return operator;

                    if ( state.backtracking==0 ) { operator = new Integer(Operator.NEG); }

                    }
                    break;
                case 2 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:499:4: '!'
                    {
                    match(input,36,FOLLOW_36_in_unaryOp1599); if (state.failed) return operator;

                    if ( state.backtracking==0 ) { operator = new Integer(Operator.NOT); }

                    }
                    break;
                case 3 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:500:4: '-'
                    {
                    match(input,51,FOLLOW_51_in_unaryOp1606); if (state.failed) return operator;

                    if ( state.backtracking==0 ) { operator = new Integer(Operator.MINUS); }

                    }
                    break;
                case 4 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:501:4: '+'
                    {
                    match(input,47,FOLLOW_47_in_unaryOp1613); if (state.failed) return operator;

                    if ( state.backtracking==0 ) { operator = new Integer(Operator.PLUS); }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 41, unaryOp_StartIndex); }

        }
        return operator;
    }
    // $ANTLR end "unaryOp"



    // $ANTLR start "postfixOp"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:504:1: postfixOp returns [Integer operator] : ( '++' | '--' );
    public final Integer postfixOp() throws RecognitionException {
        Integer operator = null;

        int postfixOp_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 42) ) { return operator; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:505:2: ( '++' | '--' )
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==48) ) {
                alt38=1;
            }
            else if ( (LA38_0==52) ) {
                alt38=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return operator;}
                NoViableAltException nvae =
                    new NoViableAltException("", 38, 0, input);

                throw nvae;

            }
            switch (alt38) {
                case 1 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:505:4: '++'
                    {
                    match(input,48,FOLLOW_48_in_postfixOp1631); if (state.failed) return operator;

                    if ( state.backtracking==0 ) { operator = new Integer(Operator.INC); }

                    }
                    break;
                case 2 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:506:4: '--'
                    {
                    match(input,52,FOLLOW_52_in_postfixOp1638); if (state.failed) return operator;

                    if ( state.backtracking==0 ) { operator = new Integer(Operator.DEC); }

                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 42, postfixOp_StartIndex); }

        }
        return operator;
    }
    // $ANTLR end "postfixOp"

    // $ANTLR start synpred16_CTalk
    public final void synpred16_CTalk_fragment() throws RecognitionException {
        Statement false_stmt =null;


        // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:77:44: ( 'else' false_stmt= stmt )
        // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\ctalk-interpreter\\src\\mi\\run\\ctalk\\CTalk.g:77:44: 'else' false_stmt= stmt
        {
        match(input,76,FOLLOW_76_in_synpred16_CTalk388); if (state.failed) return ;

        pushFollow(FOLLOW_stmt_in_synpred16_CTalk392);
        false_stmt=stmt();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred16_CTalk

    // Delegated rules

    public final boolean synpred16_CTalk() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred16_CTalk_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


 

    public static final BitSet FOLLOW_funcDef_in_program61 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_EOF_in_program67 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_78_in_funcDef83 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_IDENTIFIER_in_funcDef85 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_43_in_funcDef87 = new BitSet(new long[]{0x0000100000020000L});
    public static final BitSet FOLLOW_paramsList_in_funcDef89 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_44_in_funcDef92 = new BitSet(new long[]{0x0000000000000000L,0x0000000000040000L});
    public static final BitSet FOLLOW_blockStmt_in_funcDef94 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_paramsList116 = new BitSet(new long[]{0x0004000000000002L});
    public static final BitSet FOLLOW_50_in_paramsList126 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_IDENTIFIER_in_paramsList130 = new BitSet(new long[]{0x0004000000000002L});
    public static final BitSet FOLLOW_58_in_stmt156 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_stmt161 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_58_in_stmt163 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_blockStmt_in_stmt174 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_whileStmt_in_stmt184 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_doWhileStmt_in_stmt194 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_forStmt_in_stmt202 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifStmt_in_stmt214 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_breakStmt_in_stmt227 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_continueStmt_in_stmt237 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_returnStmt_in_stmt244 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_81_in_whileStmt264 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_43_in_whileStmt266 = new BitSet(new long[]{0x0008881048060010L,0x0000000000800020L});
    public static final BitSet FOLLOW_condition_in_whileStmt268 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_44_in_whileStmt270 = new BitSet(new long[]{0x0408881048060010L,0x000000000087AE20L});
    public static final BitSet FOLLOW_stmt_in_whileStmt274 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_75_in_doWhileStmt294 = new BitSet(new long[]{0x0408881048060010L,0x000000000087AE20L});
    public static final BitSet FOLLOW_stmt_in_doWhileStmt298 = new BitSet(new long[]{0x0000000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_81_in_doWhileStmt300 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_43_in_doWhileStmt302 = new BitSet(new long[]{0x0008881048060010L,0x0000000000800020L});
    public static final BitSet FOLLOW_condition_in_doWhileStmt304 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_44_in_doWhileStmt306 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_58_in_doWhileStmt308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_77_in_forStmt328 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_43_in_forStmt330 = new BitSet(new long[]{0x0408881048060010L,0x0000000000800020L});
    public static final BitSet FOLLOW_expr_in_forStmt334 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_58_in_forStmt337 = new BitSet(new long[]{0x0408881048060010L,0x0000000000800020L});
    public static final BitSet FOLLOW_expr_in_forStmt341 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_58_in_forStmt344 = new BitSet(new long[]{0x0008981048060010L,0x0000000000800020L});
    public static final BitSet FOLLOW_expr_in_forStmt348 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_44_in_forStmt351 = new BitSet(new long[]{0x0408881048060010L,0x000000000087AE20L});
    public static final BitSet FOLLOW_stmt_in_forStmt355 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_79_in_ifStmt374 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_43_in_ifStmt376 = new BitSet(new long[]{0x0008881048060010L,0x0000000000800020L});
    public static final BitSet FOLLOW_condition_in_ifStmt378 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_44_in_ifStmt380 = new BitSet(new long[]{0x0408881048060010L,0x000000000087AE20L});
    public static final BitSet FOLLOW_stmt_in_ifStmt384 = new BitSet(new long[]{0x0000000000000002L,0x0000000000001000L});
    public static final BitSet FOLLOW_76_in_ifStmt388 = new BitSet(new long[]{0x0408881048060010L,0x000000000087AE20L});
    public static final BitSet FOLLOW_stmt_in_ifStmt392 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_condition416 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_73_in_breakStmt435 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_58_in_breakStmt437 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_74_in_continueStmt456 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_58_in_continueStmt458 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_80_in_returnStmt477 = new BitSet(new long[]{0x0408881048060010L,0x0000000000800020L});
    public static final BitSet FOLLOW_expr_in_returnStmt479 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_58_in_returnStmt482 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_82_in_blockStmt507 = new BitSet(new long[]{0x0408881048060010L,0x0000000000C7AE20L});
    public static final BitSet FOLLOW_stmt_in_blockStmt512 = new BitSet(new long[]{0x0408881048060010L,0x0000000000C7AE20L});
    public static final BitSet FOLLOW_86_in_blockStmt518 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignExpr_in_expr533 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_logOrExpr_in_assignExpr561 = new BitSet(new long[]{0xA122448000000002L,0x0000000000100110L});
    public static final BitSet FOLLOW_assignOp_in_assignExpr566 = new BitSet(new long[]{0x0008881048060010L,0x0000000000800020L});
    public static final BitSet FOLLOW_logOrExpr_in_assignExpr570 = new BitSet(new long[]{0xA122448000000002L,0x0000000000100110L});
    public static final BitSet FOLLOW_logAndExpr_in_logOrExpr602 = new BitSet(new long[]{0x0000000000000002L,0x0000000000200000L});
    public static final BitSet FOLLOW_85_in_logOrExpr605 = new BitSet(new long[]{0x0008881048060010L,0x0000000000800020L});
    public static final BitSet FOLLOW_logAndExpr_in_logOrExpr609 = new BitSet(new long[]{0x0000000000000002L,0x0000000000200000L});
    public static final BitSet FOLLOW_bitOrExpr_in_logAndExpr641 = new BitSet(new long[]{0x0000010000000002L});
    public static final BitSet FOLLOW_40_in_logAndExpr644 = new BitSet(new long[]{0x0008881048060010L,0x0000000000800020L});
    public static final BitSet FOLLOW_bitOrExpr_in_logAndExpr648 = new BitSet(new long[]{0x0000010000000002L});
    public static final BitSet FOLLOW_bitXorExpr_in_bitOrExpr679 = new BitSet(new long[]{0x0000000000000002L,0x0000000000080000L});
    public static final BitSet FOLLOW_83_in_bitOrExpr682 = new BitSet(new long[]{0x0008881048060010L,0x0000000000800020L});
    public static final BitSet FOLLOW_bitXorExpr_in_bitOrExpr686 = new BitSet(new long[]{0x0000000000000002L,0x0000000000080000L});
    public static final BitSet FOLLOW_bitAndExpr_in_bitXorExpr717 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_bitXorExpr720 = new BitSet(new long[]{0x0008881048060010L,0x0000000000800020L});
    public static final BitSet FOLLOW_bitAndExpr_in_bitXorExpr724 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000080L});
    public static final BitSet FOLLOW_eqExpr_in_bitAndExpr755 = new BitSet(new long[]{0x0000020000000002L});
    public static final BitSet FOLLOW_41_in_bitAndExpr758 = new BitSet(new long[]{0x0008881048060010L,0x0000000000800020L});
    public static final BitSet FOLLOW_eqExpr_in_bitAndExpr762 = new BitSet(new long[]{0x0000020000000002L});
    public static final BitSet FOLLOW_relExpr_in_eqExpr793 = new BitSet(new long[]{0x0000002000000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_eqOp_in_eqExpr798 = new BitSet(new long[]{0x0008881048060010L,0x0000000000800020L});
    public static final BitSet FOLLOW_relExpr_in_eqExpr802 = new BitSet(new long[]{0x0000002000000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_shiftExpr_in_relExpr833 = new BitSet(new long[]{0x4800000000000002L,0x0000000000000006L});
    public static final BitSet FOLLOW_relOp_in_relExpr838 = new BitSet(new long[]{0x0008881048060010L,0x0000000000800020L});
    public static final BitSet FOLLOW_shiftExpr_in_relExpr842 = new BitSet(new long[]{0x4800000000000002L,0x0000000000000006L});
    public static final BitSet FOLLOW_addExpr_in_shiftExpr873 = new BitSet(new long[]{0x1000000000000002L,0x0000000000000008L});
    public static final BitSet FOLLOW_shiftOp_in_shiftExpr878 = new BitSet(new long[]{0x0008881048060010L,0x0000000000800020L});
    public static final BitSet FOLLOW_addExpr_in_shiftExpr882 = new BitSet(new long[]{0x1000000000000002L,0x0000000000000008L});
    public static final BitSet FOLLOW_mulExpr_in_addExpr913 = new BitSet(new long[]{0x0008800000000002L});
    public static final BitSet FOLLOW_addOp_in_addExpr918 = new BitSet(new long[]{0x0008881048060010L,0x0000000000800020L});
    public static final BitSet FOLLOW_mulExpr_in_addExpr922 = new BitSet(new long[]{0x0008800000000002L});
    public static final BitSet FOLLOW_unaryExpr_in_mulExpr953 = new BitSet(new long[]{0x0080204000000002L});
    public static final BitSet FOLLOW_mulOp_in_mulExpr958 = new BitSet(new long[]{0x0008881048060010L,0x0000000000800020L});
    public static final BitSet FOLLOW_unaryExpr_in_mulExpr962 = new BitSet(new long[]{0x0080204000000002L});
    public static final BitSet FOLLOW_unaryOp_in_unaryExpr988 = new BitSet(new long[]{0x0000080048060010L,0x0000000000000020L});
    public static final BitSet FOLLOW_postfixExpr_in_unaryExpr992 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atom_in_postfixExpr1011 = new BitSet(new long[]{0x0011000000000002L});
    public static final BitSet FOLLOW_postfixOp_in_postfixExpr1016 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_literal_in_atom1038 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_atom1047 = new BitSet(new long[]{0x0008881048060010L,0x0000000000800020L});
    public static final BitSet FOLLOW_expr_in_atom1051 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_44_in_atom1053 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_in_atom1062 = new BitSet(new long[]{0x0040000000000002L,0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_atom1067 = new BitSet(new long[]{0x0008881048060010L,0x0000000000800020L});
    public static final BitSet FOLLOW_expr_in_atom1071 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_70_in_atom1073 = new BitSet(new long[]{0x0040000000000002L,0x0000000000000020L});
    public static final BitSet FOLLOW_54_in_atom1097 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_IDENTIFIER_in_atom1101 = new BitSet(new long[]{0x0040000000000002L,0x0000000000000020L});
    public static final BitSet FOLLOW_funcCall_in_atom1116 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_literal1137 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTEGER_in_literal1145 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REAL_in_literal1152 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOLEAN_in_literal1162 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_arrayConstructor_in_literal1169 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_variable1187 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_69_in_arrayConstructor1211 = new BitSet(new long[]{0x0008881048060010L,0x0000000000800060L});
    public static final BitSet FOLLOW_arrayElem_in_arrayConstructor1217 = new BitSet(new long[]{0x0004000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_50_in_arrayConstructor1223 = new BitSet(new long[]{0x0008881048060010L,0x0000000000800020L});
    public static final BitSet FOLLOW_arrayElem_in_arrayConstructor1227 = new BitSet(new long[]{0x0004000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_70_in_arrayConstructor1237 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_arrayElem1255 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_57_in_arrayElem1257 = new BitSet(new long[]{0x0008881048060010L,0x0000000000800020L});
    public static final BitSet FOLLOW_expr_in_arrayElem1261 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_funcCall1281 = new BitSet(new long[]{0x0000080000000000L});
    public static final BitSet FOLLOW_43_in_funcCall1283 = new BitSet(new long[]{0x0008981048060010L,0x0000000000800020L});
    public static final BitSet FOLLOW_exprList_in_funcCall1285 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_44_in_funcCall1288 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_exprList1310 = new BitSet(new long[]{0x0004000000000002L});
    public static final BitSet FOLLOW_50_in_exprList1320 = new BitSet(new long[]{0x0008881048060010L,0x0000000000800020L});
    public static final BitSet FOLLOW_expr_in_exprList1324 = new BitSet(new long[]{0x0004000000000002L});
    public static final BitSet FOLLOW_64_in_eqOp1349 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_eqOp1356 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_59_in_relOp1373 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_relOp1381 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_62_in_relOp1389 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_66_in_relOp1396 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_60_in_shiftOp1413 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_shiftOp1420 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_47_in_addOp1437 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_51_in_addOp1444 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_45_in_mulOp1462 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_55_in_mulOp1469 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_mulOp1476 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_assignOp1494 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_53_in_assignOp1502 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_assignOp1510 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_56_in_assignOp1518 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_assignOp1526 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_84_in_assignOp1534 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_42_in_assignOp1542 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_72_in_assignOp1550 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_assignOp1558 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_61_in_assignOp1565 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_63_in_assignOp1572 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_87_in_unaryOp1592 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_unaryOp1599 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_51_in_unaryOp1606 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_47_in_unaryOp1613 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_48_in_postfixOp1631 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_52_in_postfixOp1638 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_76_in_synpred16_CTalk388 = new BitSet(new long[]{0x0408881048060010L,0x000000000087AE20L});
    public static final BitSet FOLLOW_stmt_in_synpred16_CTalk392 = new BitSet(new long[]{0x0000000000000002L});

}