// $ANTLR 3.4 C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g 2011-12-06 21:30:05

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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "BOOLEAN", "COMMENT", "DECIMAL_LITERAL", "DIGIT", "DIGIT_SEQUENCE", "ESCAPE_SEQUENCE", "EXPONENT_PART", "FLOATING_SUFFIX", "FRACTIONAL_CONSTANT", "HEXADECIMAL_ESCAPE_SEQUENCE", "HEXADECIMAL_LITERAL", "HEX_DIGIT", "HEX_QUAD", "IDENTIFIER", "INTEGER", "INTEGER_SUFFIX", "LONG_SUFFIX", "NONDIGIT", "NONZERO_DIGIT", "NOT_STR_CHAR", "NULL", "OCTAL_DIGIT", "OCTAL_ESCAPE_SEQUENCE", "OCTAL_LITERAL", "REAL", "SIGN", "SIMPLE_ESCAPE_SEQUENCE", "STRING", "S_CHAR", "S_CHAR_SEQUENCE", "UNIVERSAL_CHARACTER_NAME", "UNSIGNED_SUFFIX", "WHITE_SPACE", "'!'", "'!='", "'%'", "'%='", "'&&'", "'&'", "'&='", "'('", "')'", "'*'", "'*='", "'+'", "'++'", "'+='", "','", "'-'", "'--'", "'-='", "'.'", "'/'", "'/='", "';'", "'<'", "'<<'", "'<<='", "'<='", "'='", "'=='", "'>'", "'>='", "'>>'", "'>>='", "'Array'", "'File'", "'String'", "'['", "']'", "'^'", "'^='", "'bool'", "'break'", "'continue'", "'do'", "'double'", "'else'", "'for'", "'if'", "'int'", "'new'", "'return'", "'struct'", "'void'", "'while'", "'{'", "'|'", "'|='", "'||'", "'}'", "'~'"
    };

    public static final int EOF=-1;
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
    public static final int T__88=88;
    public static final int T__89=89;
    public static final int T__90=90;
    public static final int T__91=91;
    public static final int T__92=92;
    public static final int T__93=93;
    public static final int T__94=94;
    public static final int T__95=95;
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
    public static final int NULL=24;
    public static final int OCTAL_DIGIT=25;
    public static final int OCTAL_ESCAPE_SEQUENCE=26;
    public static final int OCTAL_LITERAL=27;
    public static final int REAL=28;
    public static final int SIGN=29;
    public static final int SIMPLE_ESCAPE_SEQUENCE=30;
    public static final int STRING=31;
    public static final int S_CHAR=32;
    public static final int S_CHAR_SEQUENCE=33;
    public static final int UNIVERSAL_CHARACTER_NAME=34;
    public static final int UNSIGNED_SUFFIX=35;
    public static final int WHITE_SPACE=36;

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
        this.state.ruleMemo = new HashMap[124+1];
         

    }

    public String[] getTokenNames() { return CTalkParser.tokenNames; }
    public String getGrammarFileName() { return "C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g"; }



    // $ANTLR start "program"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:21:1: program returns [Program prg] : ( funcDef | structDef )* EOF ;
    public final Program program() throws RecognitionException {
        Program prg = null;

        int program_StartIndex = input.index();

        FunctionDefinition funcDef1 =null;

        StructureDefinition structDef2 =null;


         prg = new Program(); 
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 1) ) { return prg; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:23:2: ( ( funcDef | structDef )* EOF )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:23:4: ( funcDef | structDef )* EOF
            {
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:23:4: ( funcDef | structDef )*
            loop1:
            do {
                int alt1=3;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==IDENTIFIER||(LA1_0 >= 69 && LA1_0 <= 71)||LA1_0==76||LA1_0==80||LA1_0==84||LA1_0==88) ) {
                    alt1=1;
                }
                else if ( (LA1_0==87) ) {
                    alt1=2;
                }


                switch (alt1) {
            	case 1 :
            	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:24:4: funcDef
            	    {
            	    pushFollow(FOLLOW_funcDef_in_program65);
            	    funcDef1=funcDef();

            	    state._fsp--;
            	    if (state.failed) return prg;

            	    if ( state.backtracking==0 ) { prg.functions.add(funcDef1); funcDef1.parent = prg; }

            	    }
            	    break;
            	case 2 :
            	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:25:6: structDef
            	    {
            	    pushFollow(FOLLOW_structDef_in_program74);
            	    structDef2=structDef();

            	    state._fsp--;
            	    if (state.failed) return prg;

            	    if ( state.backtracking==0 ) { prg.structures.add(structDef2); structDef2.parent = prg; }

            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            match(input,EOF,FOLLOW_EOF_in_program83); if (state.failed) return prg;

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
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:29:1: funcDef returns [FunctionDefinition fn] : dataType IDENTIFIER '(' ( paramsList )? ')' blockStmt ;
    public final FunctionDefinition funcDef() throws RecognitionException {
        FunctionDefinition fn = null;

        int funcDef_StartIndex = input.index();

        Token IDENTIFIER4=null;
        DataType dataType3 =null;

        ExpressionList paramsList5 =null;

        BlockStatement blockStmt6 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 2) ) { return fn; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:30:2: ( dataType IDENTIFIER '(' ( paramsList )? ')' blockStmt )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:30:4: dataType IDENTIFIER '(' ( paramsList )? ')' blockStmt
            {
            pushFollow(FOLLOW_dataType_in_funcDef99);
            dataType3=dataType();

            state._fsp--;
            if (state.failed) return fn;

            IDENTIFIER4=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_funcDef101); if (state.failed) return fn;

            match(input,44,FOLLOW_44_in_funcDef103); if (state.failed) return fn;

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:30:28: ( paramsList )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==IDENTIFIER||(LA2_0 >= 69 && LA2_0 <= 71)||LA2_0==76||LA2_0==80||LA2_0==84||LA2_0==88) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:30:28: paramsList
                    {
                    pushFollow(FOLLOW_paramsList_in_funcDef105);
                    paramsList5=paramsList();

                    state._fsp--;
                    if (state.failed) return fn;

                    }
                    break;

            }


            match(input,45,FOLLOW_45_in_funcDef108); if (state.failed) return fn;

            pushFollow(FOLLOW_blockStmt_in_funcDef110);
            blockStmt6=blockStmt();

            state._fsp--;
            if (state.failed) return fn;

            if ( state.backtracking==0 ) { fn = new FunctionDefinition(dataType3, (IDENTIFIER4!=null?IDENTIFIER4.getText():null), paramsList5, blockStmt6); if(paramsList5 != null) paramsList5.parent = fn; dataType3.parent = fn; blockStmt6.parent = fn; }

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
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:34:1: paramsList returns [ExpressionList params] : type= dataType id= IDENTIFIER ( ',' type= dataType id= IDENTIFIER )* ;
    public final ExpressionList paramsList() throws RecognitionException {
        ExpressionList params = null;

        int paramsList_StartIndex = input.index();

        Token id=null;
        DataType type =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 3) ) { return params; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:35:2: (type= dataType id= IDENTIFIER ( ',' type= dataType id= IDENTIFIER )* )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:35:4: type= dataType id= IDENTIFIER ( ',' type= dataType id= IDENTIFIER )*
            {
            pushFollow(FOLLOW_dataType_in_paramsList132);
            type=dataType();

            state._fsp--;
            if (state.failed) return params;

            id=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_paramsList136); if (state.failed) return params;

            if ( state.backtracking==0 ) {
            			params = new ExpressionList();
            			Variable var = new Variable((id!=null?id.getText():null), type);
            			params.expressions.add(var);
            			var.parent = params;
            		}

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:42:3: ( ',' type= dataType id= IDENTIFIER )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==51) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:42:5: ',' type= dataType id= IDENTIFIER
            	    {
            	    match(input,51,FOLLOW_51_in_paramsList146); if (state.failed) return params;

            	    pushFollow(FOLLOW_dataType_in_paramsList150);
            	    type=dataType();

            	    state._fsp--;
            	    if (state.failed) return params;

            	    id=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_paramsList154); if (state.failed) return params;

            	    if ( state.backtracking==0 ) {
            	    				Variable var = new Variable((id!=null?id.getText():null), type);
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
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:51:1: stmt returns [Statement stmt] : ( ';' | declaration ';' | expr ';' | blockStmt | whileStmt | doWhileStmt | forStmt | ifStmt | breakStmt | continueStmt | returnStmt );
    public final Statement stmt() throws RecognitionException {
        Statement stmt = null;

        int stmt_StartIndex = input.index();

        ExpressionList declaration7 =null;

        Expression expr8 =null;

        BlockStatement blockStmt9 =null;

        WhileStatement whileStmt10 =null;

        DoWhileStatement doWhileStmt11 =null;

        ForStatement forStmt12 =null;

        IfStatement ifStmt13 =null;

        BreakStatement breakStmt14 =null;

        ContinueStatement continueStmt15 =null;

        ReturnStatement returnStmt16 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 4) ) { return stmt; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:52:2: ( ';' | declaration ';' | expr ';' | blockStmt | whileStmt | doWhileStmt | forStmt | ifStmt | breakStmt | continueStmt | returnStmt )
            int alt4=11;
            switch ( input.LA(1) ) {
            case 58:
                {
                alt4=1;
                }
                break;
            case 69:
            case 70:
            case 71:
            case 76:
            case 80:
            case 84:
            case 88:
                {
                alt4=2;
                }
                break;
            case IDENTIFIER:
                {
                int LA4_3 = input.LA(2);

                if ( ((LA4_3 >= 38 && LA4_3 <= 44)||(LA4_3 >= 46 && LA4_3 <= 50)||(LA4_3 >= 52 && LA4_3 <= 68)||LA4_3==72||(LA4_3 >= 74 && LA4_3 <= 75)||(LA4_3 >= 91 && LA4_3 <= 93)) ) {
                    alt4=3;
                }
                else if ( (LA4_3==IDENTIFIER) ) {
                    alt4=2;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return stmt;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 4, 3, input);

                    throw nvae;

                }
                }
                break;
            case BOOLEAN:
            case INTEGER:
            case NULL:
            case REAL:
            case STRING:
            case 37:
            case 44:
            case 48:
            case 52:
            case 85:
            case 95:
                {
                alt4=3;
                }
                break;
            case 90:
                {
                alt4=4;
                }
                break;
            case 89:
                {
                alt4=5;
                }
                break;
            case 79:
                {
                alt4=6;
                }
                break;
            case 82:
                {
                alt4=7;
                }
                break;
            case 83:
                {
                alt4=8;
                }
                break;
            case 77:
                {
                alt4=9;
                }
                break;
            case 78:
                {
                alt4=10;
                }
                break;
            case 86:
                {
                alt4=11;
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
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:52:4: ';'
                    {
                    match(input,58,FOLLOW_58_in_stmt180); if (state.failed) return stmt;

                    }
                    break;
                case 2 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:53:4: declaration ';'
                    {
                    pushFollow(FOLLOW_declaration_in_stmt185);
                    declaration7=declaration();

                    state._fsp--;
                    if (state.failed) return stmt;

                    match(input,58,FOLLOW_58_in_stmt187); if (state.failed) return stmt;

                    if ( state.backtracking==0 ) { stmt = new ExpressionStatement(declaration7); declaration7.parent = stmt; }

                    }
                    break;
                case 3 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:54:4: expr ';'
                    {
                    pushFollow(FOLLOW_expr_in_stmt194);
                    expr8=expr();

                    state._fsp--;
                    if (state.failed) return stmt;

                    match(input,58,FOLLOW_58_in_stmt196); if (state.failed) return stmt;

                    if ( state.backtracking==0 ) { stmt = new ExpressionStatement(expr8); expr8.parent = stmt; }

                    }
                    break;
                case 4 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:55:4: blockStmt
                    {
                    pushFollow(FOLLOW_blockStmt_in_stmt210);
                    blockStmt9=blockStmt();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) { stmt = blockStmt9; }

                    }
                    break;
                case 5 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:56:4: whileStmt
                    {
                    pushFollow(FOLLOW_whileStmt_in_stmt223);
                    whileStmt10=whileStmt();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) { stmt = whileStmt10; }

                    }
                    break;
                case 6 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:57:4: doWhileStmt
                    {
                    pushFollow(FOLLOW_doWhileStmt_in_stmt236);
                    doWhileStmt11=doWhileStmt();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) { stmt = doWhileStmt11; }

                    }
                    break;
                case 7 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:58:4: forStmt
                    {
                    pushFollow(FOLLOW_forStmt_in_stmt247);
                    forStmt12=forStmt();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) { stmt = forStmt12; }

                    }
                    break;
                case 8 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:59:4: ifStmt
                    {
                    pushFollow(FOLLOW_ifStmt_in_stmt262);
                    ifStmt13=ifStmt();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) { stmt = ifStmt13; }

                    }
                    break;
                case 9 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:60:4: breakStmt
                    {
                    pushFollow(FOLLOW_breakStmt_in_stmt278);
                    breakStmt14=breakStmt();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) { stmt = breakStmt14; }

                    }
                    break;
                case 10 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:61:4: continueStmt
                    {
                    pushFollow(FOLLOW_continueStmt_in_stmt291);
                    continueStmt15=continueStmt();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) { stmt = continueStmt15; }

                    }
                    break;
                case 11 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:62:4: returnStmt
                    {
                    pushFollow(FOLLOW_returnStmt_in_stmt301);
                    returnStmt16=returnStmt();

                    state._fsp--;
                    if (state.failed) return stmt;

                    if ( state.backtracking==0 ) { stmt = returnStmt16; }

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



    // $ANTLR start "dataType"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:65:1: dataType returns [DataType type] : ( 'Array' '<' arg= dataType '>' | 'int' | 'double' | 'File' | 'String' | IDENTIFIER | 'void' | 'bool' );
    public final DataType dataType() throws RecognitionException {
        DataType type = null;

        int dataType_StartIndex = input.index();

        Token IDENTIFIER17=null;
        DataType arg =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 5) ) { return type; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:66:2: ( 'Array' '<' arg= dataType '>' | 'int' | 'double' | 'File' | 'String' | IDENTIFIER | 'void' | 'bool' )
            int alt5=8;
            switch ( input.LA(1) ) {
            case 69:
                {
                alt5=1;
                }
                break;
            case 84:
                {
                alt5=2;
                }
                break;
            case 80:
                {
                alt5=3;
                }
                break;
            case 70:
                {
                alt5=4;
                }
                break;
            case 71:
                {
                alt5=5;
                }
                break;
            case IDENTIFIER:
                {
                alt5=6;
                }
                break;
            case 88:
                {
                alt5=7;
                }
                break;
            case 76:
                {
                alt5=8;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return type;}
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;

            }

            switch (alt5) {
                case 1 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:66:4: 'Array' '<' arg= dataType '>'
                    {
                    match(input,69,FOLLOW_69_in_dataType323); if (state.failed) return type;

                    match(input,59,FOLLOW_59_in_dataType325); if (state.failed) return type;

                    pushFollow(FOLLOW_dataType_in_dataType329);
                    arg=dataType();

                    state._fsp--;
                    if (state.failed) return type;

                    match(input,65,FOLLOW_65_in_dataType331); if (state.failed) return type;

                    if ( state.backtracking==0 ) { type = new DataType(DataType.ARRAY, arg); }

                    }
                    break;
                case 2 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:67:4: 'int'
                    {
                    match(input,84,FOLLOW_84_in_dataType338); if (state.failed) return type;

                    if ( state.backtracking==0 ) { type = new DataType(DataType.INTEGER); }

                    }
                    break;
                case 3 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:68:4: 'double'
                    {
                    match(input,80,FOLLOW_80_in_dataType368); if (state.failed) return type;

                    if ( state.backtracking==0 ) { type = new DataType(DataType.DOUBLE); }

                    }
                    break;
                case 4 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:69:4: 'File'
                    {
                    match(input,70,FOLLOW_70_in_dataType395); if (state.failed) return type;

                    if ( state.backtracking==0 ) { type = new DataType(DataType.FILE); }

                    }
                    break;
                case 5 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:70:4: 'String'
                    {
                    match(input,71,FOLLOW_71_in_dataType424); if (state.failed) return type;

                    if ( state.backtracking==0 ) { type = new DataType(DataType.STRING); }

                    }
                    break;
                case 6 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:71:4: IDENTIFIER
                    {
                    IDENTIFIER17=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_dataType451); if (state.failed) return type;

                    if ( state.backtracking==0 ) { type = new DataType(DataType.STRUCTURE, (IDENTIFIER17!=null?IDENTIFIER17.getText():null)); }

                    }
                    break;
                case 7 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:72:4: 'void'
                    {
                    match(input,88,FOLLOW_88_in_dataType463); if (state.failed) return type;

                    if ( state.backtracking==0 ) { type = new DataType(DataType.VOID); }

                    }
                    break;
                case 8 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:73:4: 'bool'
                    {
                    match(input,76,FOLLOW_76_in_dataType476); if (state.failed) return type;

                    if ( state.backtracking==0 ) { type = new DataType(DataType.BOOL); }

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
            if ( state.backtracking>0 ) { memoize(input, 5, dataType_StartIndex); }

        }
        return type;
    }
    // $ANTLR end "dataType"



    // $ANTLR start "declaration"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:76:1: declaration returns [ExpressionList decl] : type= dataType id= IDENTIFIER ( '=' e= expr )? ( ',' id= IDENTIFIER ( '=' e= expr )? )* ;
    public final ExpressionList declaration() throws RecognitionException {
        ExpressionList decl = null;

        int declaration_StartIndex = input.index();

        Token id=null;
        DataType type =null;

        Expression e =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 6) ) { return decl; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:77:2: (type= dataType id= IDENTIFIER ( '=' e= expr )? ( ',' id= IDENTIFIER ( '=' e= expr )? )* )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:77:4: type= dataType id= IDENTIFIER ( '=' e= expr )? ( ',' id= IDENTIFIER ( '=' e= expr )? )*
            {
            pushFollow(FOLLOW_dataType_in_declaration501);
            type=dataType();

            state._fsp--;
            if (state.failed) return decl;

            id=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_declaration505); if (state.failed) return decl;

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:77:32: ( '=' e= expr )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==63) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:77:33: '=' e= expr
                    {
                    match(input,63,FOLLOW_63_in_declaration508); if (state.failed) return decl;

                    pushFollow(FOLLOW_expr_in_declaration512);
                    e=expr();

                    state._fsp--;
                    if (state.failed) return decl;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {
            				decl = new ExpressionList();
            				Variable var = new Variable((id!=null?id.getText():null), type);
            				if(e != null)
            				{
            					var.value = e;
            					var.value.parent = var;
            				}
            				var.parent = decl;
            				decl.expressions.add(var);

            			}

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:90:3: ( ',' id= IDENTIFIER ( '=' e= expr )? )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==51) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:90:4: ',' id= IDENTIFIER ( '=' e= expr )?
            	    {
            	    match(input,51,FOLLOW_51_in_declaration524); if (state.failed) return decl;

            	    id=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_declaration528); if (state.failed) return decl;

            	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:90:22: ( '=' e= expr )?
            	    int alt7=2;
            	    int LA7_0 = input.LA(1);

            	    if ( (LA7_0==63) ) {
            	        alt7=1;
            	    }
            	    switch (alt7) {
            	        case 1 :
            	            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:90:23: '=' e= expr
            	            {
            	            match(input,63,FOLLOW_63_in_declaration531); if (state.failed) return decl;

            	            pushFollow(FOLLOW_expr_in_declaration535);
            	            e=expr();

            	            state._fsp--;
            	            if (state.failed) return decl;

            	            }
            	            break;

            	    }


            	    if ( state.backtracking==0 ) {
            	    	  			Variable var = new Variable((id!=null?id.getText():null), type);
            	    				if(e != null)
            	    				{
            	    					var.value = e;
            	    					var.value.parent = var;
            	    				}
            	    				var.parent = decl;
            	    				decl.expressions.add(var);
            	    		  	}

            	    }
            	    break;

            	default :
            	    break loop8;
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
            if ( state.backtracking>0 ) { memoize(input, 6, declaration_StartIndex); }

        }
        return decl;
    }
    // $ANTLR end "declaration"



    // $ANTLR start "structDef"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:104:1: structDef returns [StructureDefinition struct] : 'struct' id= IDENTIFIER '{' (type= dataType var= IDENTIFIER ';' )+ '}' ;
    public final StructureDefinition structDef() throws RecognitionException {
        StructureDefinition struct = null;

        int structDef_StartIndex = input.index();

        Token id=null;
        Token var=null;
        DataType type =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 7) ) { return struct; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:105:2: ( 'struct' id= IDENTIFIER '{' (type= dataType var= IDENTIFIER ';' )+ '}' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:105:4: 'struct' id= IDENTIFIER '{' (type= dataType var= IDENTIFIER ';' )+ '}'
            {
            match(input,87,FOLLOW_87_in_structDef564); if (state.failed) return struct;

            id=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_structDef568); if (state.failed) return struct;

            match(input,90,FOLLOW_90_in_structDef570); if (state.failed) return struct;

            if ( state.backtracking==0 ) { struct = new StructureDefinition((id!=null?id.getText():null)); }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:107:3: (type= dataType var= IDENTIFIER ';' )+
            int cnt9=0;
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==IDENTIFIER||(LA9_0 >= 69 && LA9_0 <= 71)||LA9_0==76||LA9_0==80||LA9_0==84||LA9_0==88) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:107:4: type= dataType var= IDENTIFIER ';'
            	    {
            	    pushFollow(FOLLOW_dataType_in_structDef582);
            	    type=dataType();

            	    state._fsp--;
            	    if (state.failed) return struct;

            	    var=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_structDef586); if (state.failed) return struct;

            	    match(input,58,FOLLOW_58_in_structDef588); if (state.failed) return struct;

            	    if ( state.backtracking==0 ) { struct.declarations.expressions.add(new Variable((var!=null?var.getText():null), type)); }

            	    }
            	    break;

            	default :
            	    if ( cnt9 >= 1 ) break loop9;
            	    if (state.backtracking>0) {state.failed=true; return struct;}
                        EarlyExitException eee =
                            new EarlyExitException(9, input);
                        throw eee;
                }
                cnt9++;
            } while (true);


            match(input,94,FOLLOW_94_in_structDef600); if (state.failed) return struct;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 7, structDef_StartIndex); }

        }
        return struct;
    }
    // $ANTLR end "structDef"



    // $ANTLR start "whileStmt"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:112:1: whileStmt returns [WhileStatement stmt] : 'while' '(' condition ')' body= stmt ;
    public final WhileStatement whileStmt() throws RecognitionException {
        WhileStatement stmt = null;

        int whileStmt_StartIndex = input.index();

        Statement body =null;

        Expression condition18 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 8) ) { return stmt; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:113:2: ( 'while' '(' condition ')' body= stmt )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:113:4: 'while' '(' condition ')' body= stmt
            {
            match(input,89,FOLLOW_89_in_whileStmt616); if (state.failed) return stmt;

            match(input,44,FOLLOW_44_in_whileStmt618); if (state.failed) return stmt;

            pushFollow(FOLLOW_condition_in_whileStmt620);
            condition18=condition();

            state._fsp--;
            if (state.failed) return stmt;

            match(input,45,FOLLOW_45_in_whileStmt622); if (state.failed) return stmt;

            pushFollow(FOLLOW_stmt_in_whileStmt626);
            body=stmt();

            state._fsp--;
            if (state.failed) return stmt;

            if ( state.backtracking==0 ) { stmt = new WhileStatement(condition18, body); condition18.parent = stmt; body.parent = stmt; }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 8, whileStmt_StartIndex); }

        }
        return stmt;
    }
    // $ANTLR end "whileStmt"



    // $ANTLR start "doWhileStmt"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:117:1: doWhileStmt returns [DoWhileStatement stmt] : 'do' body= stmt 'while' '(' condition ')' ';' ;
    public final DoWhileStatement doWhileStmt() throws RecognitionException {
        DoWhileStatement stmt = null;

        int doWhileStmt_StartIndex = input.index();

        Statement body =null;

        Expression condition19 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 9) ) { return stmt; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:118:2: ( 'do' body= stmt 'while' '(' condition ')' ';' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:118:4: 'do' body= stmt 'while' '(' condition ')' ';'
            {
            match(input,79,FOLLOW_79_in_doWhileStmt646); if (state.failed) return stmt;

            pushFollow(FOLLOW_stmt_in_doWhileStmt650);
            body=stmt();

            state._fsp--;
            if (state.failed) return stmt;

            match(input,89,FOLLOW_89_in_doWhileStmt652); if (state.failed) return stmt;

            match(input,44,FOLLOW_44_in_doWhileStmt654); if (state.failed) return stmt;

            pushFollow(FOLLOW_condition_in_doWhileStmt656);
            condition19=condition();

            state._fsp--;
            if (state.failed) return stmt;

            match(input,45,FOLLOW_45_in_doWhileStmt658); if (state.failed) return stmt;

            match(input,58,FOLLOW_58_in_doWhileStmt660); if (state.failed) return stmt;

            if ( state.backtracking==0 ) { stmt = new DoWhileStatement(condition19, body); condition19.parent = stmt; body.parent = stmt; }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 9, doWhileStmt_StartIndex); }

        }
        return stmt;
    }
    // $ANTLR end "doWhileStmt"



    // $ANTLR start "forStmt"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:122:1: forStmt returns [ForStatement stmt] : 'for' '(' (initE= expr |initD= declaration )? ';' (cond= expr )? ';' (iter= expr )? ')' body= stmt ;
    public final ForStatement forStmt() throws RecognitionException {
        ForStatement stmt = null;

        int forStmt_StartIndex = input.index();

        Expression initE =null;

        ExpressionList initD =null;

        Expression cond =null;

        Expression iter =null;

        Statement body =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 10) ) { return stmt; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:123:2: ( 'for' '(' (initE= expr |initD= declaration )? ';' (cond= expr )? ';' (iter= expr )? ')' body= stmt )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:123:4: 'for' '(' (initE= expr |initD= declaration )? ';' (cond= expr )? ';' (iter= expr )? ')' body= stmt
            {
            match(input,82,FOLLOW_82_in_forStmt680); if (state.failed) return stmt;

            match(input,44,FOLLOW_44_in_forStmt682); if (state.failed) return stmt;

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:123:14: (initE= expr |initD= declaration )?
            int alt10=3;
            switch ( input.LA(1) ) {
                case BOOLEAN:
                case INTEGER:
                case NULL:
                case REAL:
                case STRING:
                case 37:
                case 44:
                case 48:
                case 52:
                case 85:
                case 95:
                    {
                    alt10=1;
                    }
                    break;
                case IDENTIFIER:
                    {
                    int LA10_2 = input.LA(2);

                    if ( ((LA10_2 >= 38 && LA10_2 <= 44)||(LA10_2 >= 46 && LA10_2 <= 50)||(LA10_2 >= 52 && LA10_2 <= 68)||LA10_2==72||(LA10_2 >= 74 && LA10_2 <= 75)||(LA10_2 >= 91 && LA10_2 <= 93)) ) {
                        alt10=1;
                    }
                    else if ( (LA10_2==IDENTIFIER) ) {
                        alt10=2;
                    }
                    }
                    break;
                case 69:
                case 70:
                case 71:
                case 76:
                case 80:
                case 84:
                case 88:
                    {
                    alt10=2;
                    }
                    break;
            }

            switch (alt10) {
                case 1 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:123:15: initE= expr
                    {
                    pushFollow(FOLLOW_expr_in_forStmt687);
                    initE=expr();

                    state._fsp--;
                    if (state.failed) return stmt;

                    }
                    break;
                case 2 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:123:26: initD= declaration
                    {
                    pushFollow(FOLLOW_declaration_in_forStmt691);
                    initD=declaration();

                    state._fsp--;
                    if (state.failed) return stmt;

                    }
                    break;

            }


            match(input,58,FOLLOW_58_in_forStmt695); if (state.failed) return stmt;

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:123:54: (cond= expr )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==BOOLEAN||(LA11_0 >= IDENTIFIER && LA11_0 <= INTEGER)||LA11_0==NULL||LA11_0==REAL||LA11_0==STRING||LA11_0==37||LA11_0==44||LA11_0==48||LA11_0==52||LA11_0==85||LA11_0==95) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:123:54: cond= expr
                    {
                    pushFollow(FOLLOW_expr_in_forStmt699);
                    cond=expr();

                    state._fsp--;
                    if (state.failed) return stmt;

                    }
                    break;

            }


            match(input,58,FOLLOW_58_in_forStmt702); if (state.failed) return stmt;

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:123:69: (iter= expr )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==BOOLEAN||(LA12_0 >= IDENTIFIER && LA12_0 <= INTEGER)||LA12_0==NULL||LA12_0==REAL||LA12_0==STRING||LA12_0==37||LA12_0==44||LA12_0==48||LA12_0==52||LA12_0==85||LA12_0==95) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:123:69: iter= expr
                    {
                    pushFollow(FOLLOW_expr_in_forStmt706);
                    iter=expr();

                    state._fsp--;
                    if (state.failed) return stmt;

                    }
                    break;

            }


            match(input,45,FOLLOW_45_in_forStmt709); if (state.failed) return stmt;

            pushFollow(FOLLOW_stmt_in_forStmt713);
            body=stmt();

            state._fsp--;
            if (state.failed) return stmt;

            if ( state.backtracking==0 ) {
            				stmt = new ForStatement((initE != null ? initE : (initD != null ? initD : null)), cond, iter, body);
            				if(initE != null) initE.parent = stmt;
            				else if(initD != null) initD.parent = stmt;
            				if(cond != null) cond.parent = stmt;
            				if(iter != null) iter.parent = stmt;
            				body.parent = stmt;
            			}

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 10, forStmt_StartIndex); }

        }
        return stmt;
    }
    // $ANTLR end "forStmt"



    // $ANTLR start "ifStmt"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:134:1: ifStmt returns [IfStatement stmt] : 'if' '(' condition ')' true_stmt= stmt ( 'else' false_stmt= stmt )? ;
    public final IfStatement ifStmt() throws RecognitionException {
        IfStatement stmt = null;

        int ifStmt_StartIndex = input.index();

        Statement true_stmt =null;

        Statement false_stmt =null;

        Expression condition20 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 11) ) { return stmt; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:135:2: ( 'if' '(' condition ')' true_stmt= stmt ( 'else' false_stmt= stmt )? )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:135:4: 'if' '(' condition ')' true_stmt= stmt ( 'else' false_stmt= stmt )?
            {
            match(input,83,FOLLOW_83_in_ifStmt733); if (state.failed) return stmt;

            match(input,44,FOLLOW_44_in_ifStmt735); if (state.failed) return stmt;

            pushFollow(FOLLOW_condition_in_ifStmt737);
            condition20=condition();

            state._fsp--;
            if (state.failed) return stmt;

            match(input,45,FOLLOW_45_in_ifStmt739); if (state.failed) return stmt;

            pushFollow(FOLLOW_stmt_in_ifStmt743);
            true_stmt=stmt();

            state._fsp--;
            if (state.failed) return stmt;

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:135:42: ( 'else' false_stmt= stmt )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==81) ) {
                int LA13_1 = input.LA(2);

                if ( (synpred30_CTalk()) ) {
                    alt13=1;
                }
            }
            switch (alt13) {
                case 1 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:135:44: 'else' false_stmt= stmt
                    {
                    match(input,81,FOLLOW_81_in_ifStmt747); if (state.failed) return stmt;

                    pushFollow(FOLLOW_stmt_in_ifStmt751);
                    false_stmt=stmt();

                    state._fsp--;
                    if (state.failed) return stmt;

                    }
                    break;

            }


            if ( state.backtracking==0 ) { stmt = new IfStatement(condition20, true_stmt, false_stmt); condition20.parent = stmt; true_stmt.parent = stmt; if(false_stmt != null) false_stmt.parent = stmt; }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 11, ifStmt_StartIndex); }

        }
        return stmt;
    }
    // $ANTLR end "ifStmt"



    // $ANTLR start "condition"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:139:1: condition returns [Expression expr] : e= expr ;
    public final Expression condition() throws RecognitionException {
        Expression expr = null;

        int condition_StartIndex = input.index();

        Expression e =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 12) ) { return expr; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:140:2: (e= expr )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:140:4: e= expr
            {
            pushFollow(FOLLOW_expr_in_condition775);
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
            if ( state.backtracking>0 ) { memoize(input, 12, condition_StartIndex); }

        }
        return expr;
    }
    // $ANTLR end "condition"



    // $ANTLR start "breakStmt"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:144:1: breakStmt returns [BreakStatement stmt] : 'break' ';' ;
    public final BreakStatement breakStmt() throws RecognitionException {
        BreakStatement stmt = null;

        int breakStmt_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 13) ) { return stmt; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:145:2: ( 'break' ';' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:145:4: 'break' ';'
            {
            match(input,77,FOLLOW_77_in_breakStmt794); if (state.failed) return stmt;

            match(input,58,FOLLOW_58_in_breakStmt796); if (state.failed) return stmt;

            if ( state.backtracking==0 ) { stmt = new BreakStatement(); }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 13, breakStmt_StartIndex); }

        }
        return stmt;
    }
    // $ANTLR end "breakStmt"



    // $ANTLR start "continueStmt"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:149:1: continueStmt returns [ContinueStatement stmt] : 'continue' ';' ;
    public final ContinueStatement continueStmt() throws RecognitionException {
        ContinueStatement stmt = null;

        int continueStmt_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 14) ) { return stmt; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:150:2: ( 'continue' ';' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:150:4: 'continue' ';'
            {
            match(input,78,FOLLOW_78_in_continueStmt815); if (state.failed) return stmt;

            match(input,58,FOLLOW_58_in_continueStmt817); if (state.failed) return stmt;

            if ( state.backtracking==0 ) { stmt = new ContinueStatement(); }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 14, continueStmt_StartIndex); }

        }
        return stmt;
    }
    // $ANTLR end "continueStmt"



    // $ANTLR start "returnStmt"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:154:1: returnStmt returns [ReturnStatement stmt] : 'return' ( expr )? ';' ;
    public final ReturnStatement returnStmt() throws RecognitionException {
        ReturnStatement stmt = null;

        int returnStmt_StartIndex = input.index();

        Expression expr21 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 15) ) { return stmt; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:155:2: ( 'return' ( expr )? ';' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:155:4: 'return' ( expr )? ';'
            {
            match(input,86,FOLLOW_86_in_returnStmt836); if (state.failed) return stmt;

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:155:13: ( expr )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==BOOLEAN||(LA14_0 >= IDENTIFIER && LA14_0 <= INTEGER)||LA14_0==NULL||LA14_0==REAL||LA14_0==STRING||LA14_0==37||LA14_0==44||LA14_0==48||LA14_0==52||LA14_0==85||LA14_0==95) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:155:13: expr
                    {
                    pushFollow(FOLLOW_expr_in_returnStmt838);
                    expr21=expr();

                    state._fsp--;
                    if (state.failed) return stmt;

                    }
                    break;

            }


            match(input,58,FOLLOW_58_in_returnStmt841); if (state.failed) return stmt;

            if ( state.backtracking==0 ) { stmt = new ReturnStatement(expr21); if(expr21 != null) expr21.parent = stmt; }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 15, returnStmt_StartIndex); }

        }
        return stmt;
    }
    // $ANTLR end "returnStmt"



    // $ANTLR start "blockStmt"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:159:1: blockStmt returns [BlockStatement stmt] : '{' (block= stmt )* '}' ;
    public final BlockStatement blockStmt() throws RecognitionException {
        BlockStatement stmt = null;

        int blockStmt_StartIndex = input.index();

        Statement block =null;


         stmt = new BlockStatement(); 
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 16) ) { return stmt; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:161:2: ( '{' (block= stmt )* '}' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:161:4: '{' (block= stmt )* '}'
            {
            match(input,90,FOLLOW_90_in_blockStmt866); if (state.failed) return stmt;

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:161:8: (block= stmt )*
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( (LA15_0==BOOLEAN||(LA15_0 >= IDENTIFIER && LA15_0 <= INTEGER)||LA15_0==NULL||LA15_0==REAL||LA15_0==STRING||LA15_0==37||LA15_0==44||LA15_0==48||LA15_0==52||LA15_0==58||(LA15_0 >= 69 && LA15_0 <= 71)||(LA15_0 >= 76 && LA15_0 <= 80)||(LA15_0 >= 82 && LA15_0 <= 86)||(LA15_0 >= 88 && LA15_0 <= 90)||LA15_0==95) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:161:9: block= stmt
            	    {
            	    pushFollow(FOLLOW_stmt_in_blockStmt871);
            	    block=stmt();

            	    state._fsp--;
            	    if (state.failed) return stmt;

            	    if ( state.backtracking==0 ) { stmt.statements.add(block); block.parent = stmt; }

            	    }
            	    break;

            	default :
            	    break loop15;
                }
            } while (true);


            match(input,94,FOLLOW_94_in_blockStmt877); if (state.failed) return stmt;

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 16, blockStmt_StartIndex); }

        }
        return stmt;
    }
    // $ANTLR end "blockStmt"



    // $ANTLR start "expr"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:164:1: expr returns [Expression expr] : assignExpr ;
    public final Expression expr() throws RecognitionException {
        Expression expr = null;

        int expr_StartIndex = input.index();

        Expression assignExpr22 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 17) ) { return expr; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:165:2: ( assignExpr )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:165:4: assignExpr
            {
            pushFollow(FOLLOW_assignExpr_in_expr892);
            assignExpr22=assignExpr();

            state._fsp--;
            if (state.failed) return expr;

            if ( state.backtracking==0 ) { expr = assignExpr22; }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 17, expr_StartIndex); }

        }
        return expr;
    }
    // $ANTLR end "expr"



    // $ANTLR start "assignExpr"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:169:1: assignExpr returns [Expression expr] : l= logOrExpr (op= assignOp r= logOrExpr )* ;
    public final Expression assignExpr() throws RecognitionException {
        Expression expr = null;

        int assignExpr_StartIndex = input.index();

        Expression l =null;

        int op =0;

        Expression r =null;


         Stack<BinaryExpression> stack = new Stack<BinaryExpression>(); 
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 18) ) { return expr; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:171:2: (l= logOrExpr (op= assignOp r= logOrExpr )* )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:171:4: l= logOrExpr (op= assignOp r= logOrExpr )*
            {
            pushFollow(FOLLOW_logOrExpr_in_assignExpr920);
            l=logOrExpr();

            state._fsp--;
            if (state.failed) return expr;

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:171:16: (op= assignOp r= logOrExpr )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==40||LA16_0==43||LA16_0==47||LA16_0==50||LA16_0==54||LA16_0==57||LA16_0==61||LA16_0==63||LA16_0==68||LA16_0==75||LA16_0==92) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:171:17: op= assignOp r= logOrExpr
            	    {
            	    pushFollow(FOLLOW_assignOp_in_assignExpr925);
            	    op=assignOp();

            	    state._fsp--;
            	    if (state.failed) return expr;

            	    pushFollow(FOLLOW_logOrExpr_in_assignExpr929);
            	    r=logOrExpr();

            	    state._fsp--;
            	    if (state.failed) return expr;

            	    if ( state.backtracking==0 ) { stack.push(new BinaryExpression(op, null, r)); }

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
            if ( state.backtracking>0 ) { memoize(input, 18, assignExpr_StartIndex); }

        }
        return expr;
    }
    // $ANTLR end "assignExpr"



    // $ANTLR start "logOrExpr"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:193:1: logOrExpr returns [Expression expr] : l= logAndExpr ( '||' r= logAndExpr )* ;
    public final Expression logOrExpr() throws RecognitionException {
        Expression expr = null;

        int logOrExpr_StartIndex = input.index();

        Expression l =null;

        Expression r =null;


         Stack<BinaryExpression> stack = new Stack<BinaryExpression>(); 
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 19) ) { return expr; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:195:2: (l= logAndExpr ( '||' r= logAndExpr )* )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:195:4: l= logAndExpr ( '||' r= logAndExpr )*
            {
            pushFollow(FOLLOW_logAndExpr_in_logOrExpr961);
            l=logAndExpr();

            state._fsp--;
            if (state.failed) return expr;

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:195:17: ( '||' r= logAndExpr )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==93) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:195:18: '||' r= logAndExpr
            	    {
            	    match(input,93,FOLLOW_93_in_logOrExpr964); if (state.failed) return expr;

            	    pushFollow(FOLLOW_logAndExpr_in_logOrExpr968);
            	    r=logAndExpr();

            	    state._fsp--;
            	    if (state.failed) return expr;

            	    if ( state.backtracking==0 ) { stack.push(new BinaryExpression(Operator.LOG_OR, null, r)); }

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
            if ( state.backtracking>0 ) { memoize(input, 19, logOrExpr_StartIndex); }

        }
        return expr;
    }
    // $ANTLR end "logOrExpr"



    // $ANTLR start "logAndExpr"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:217:1: logAndExpr returns [Expression expr] : l= bitOrExpr ( '&&' r= bitOrExpr )* ;
    public final Expression logAndExpr() throws RecognitionException {
        Expression expr = null;

        int logAndExpr_StartIndex = input.index();

        Expression l =null;

        Expression r =null;


         Stack<BinaryExpression> stack = new Stack<BinaryExpression>(); 
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 20) ) { return expr; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:219:2: (l= bitOrExpr ( '&&' r= bitOrExpr )* )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:219:4: l= bitOrExpr ( '&&' r= bitOrExpr )*
            {
            pushFollow(FOLLOW_bitOrExpr_in_logAndExpr1000);
            l=bitOrExpr();

            state._fsp--;
            if (state.failed) return expr;

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:219:16: ( '&&' r= bitOrExpr )*
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==41) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:219:17: '&&' r= bitOrExpr
            	    {
            	    match(input,41,FOLLOW_41_in_logAndExpr1003); if (state.failed) return expr;

            	    pushFollow(FOLLOW_bitOrExpr_in_logAndExpr1007);
            	    r=bitOrExpr();

            	    state._fsp--;
            	    if (state.failed) return expr;

            	    if ( state.backtracking==0 ) { stack.push(new BinaryExpression(Operator.LOG_AND, null, r)); }

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
            if ( state.backtracking>0 ) { memoize(input, 20, logAndExpr_StartIndex); }

        }
        return expr;
    }
    // $ANTLR end "logAndExpr"



    // $ANTLR start "bitOrExpr"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:241:1: bitOrExpr returns [Expression expr] : l= bitXorExpr ( '|' r= bitXorExpr )* ;
    public final Expression bitOrExpr() throws RecognitionException {
        Expression expr = null;

        int bitOrExpr_StartIndex = input.index();

        Expression l =null;

        Expression r =null;


         Stack<BinaryExpression> stack = new Stack<BinaryExpression>(); 
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 21) ) { return expr; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:243:2: (l= bitXorExpr ( '|' r= bitXorExpr )* )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:243:4: l= bitXorExpr ( '|' r= bitXorExpr )*
            {
            pushFollow(FOLLOW_bitXorExpr_in_bitOrExpr1038);
            l=bitXorExpr();

            state._fsp--;
            if (state.failed) return expr;

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:243:17: ( '|' r= bitXorExpr )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==91) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:243:18: '|' r= bitXorExpr
            	    {
            	    match(input,91,FOLLOW_91_in_bitOrExpr1041); if (state.failed) return expr;

            	    pushFollow(FOLLOW_bitXorExpr_in_bitOrExpr1045);
            	    r=bitXorExpr();

            	    state._fsp--;
            	    if (state.failed) return expr;

            	    if ( state.backtracking==0 ) { stack.push(new BinaryExpression(Operator.OR, null, r)); }

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
            if ( state.backtracking>0 ) { memoize(input, 21, bitOrExpr_StartIndex); }

        }
        return expr;
    }
    // $ANTLR end "bitOrExpr"



    // $ANTLR start "bitXorExpr"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:265:1: bitXorExpr returns [Expression expr] : l= bitAndExpr ( '^' r= bitAndExpr )* ;
    public final Expression bitXorExpr() throws RecognitionException {
        Expression expr = null;

        int bitXorExpr_StartIndex = input.index();

        Expression l =null;

        Expression r =null;


         Stack<BinaryExpression> stack = new Stack<BinaryExpression>(); 
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 22) ) { return expr; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:267:2: (l= bitAndExpr ( '^' r= bitAndExpr )* )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:267:4: l= bitAndExpr ( '^' r= bitAndExpr )*
            {
            pushFollow(FOLLOW_bitAndExpr_in_bitXorExpr1076);
            l=bitAndExpr();

            state._fsp--;
            if (state.failed) return expr;

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:267:17: ( '^' r= bitAndExpr )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==74) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:267:18: '^' r= bitAndExpr
            	    {
            	    match(input,74,FOLLOW_74_in_bitXorExpr1079); if (state.failed) return expr;

            	    pushFollow(FOLLOW_bitAndExpr_in_bitXorExpr1083);
            	    r=bitAndExpr();

            	    state._fsp--;
            	    if (state.failed) return expr;

            	    if ( state.backtracking==0 ) { stack.push(new BinaryExpression(Operator.XOR, null, r)); }

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
            if ( state.backtracking>0 ) { memoize(input, 22, bitXorExpr_StartIndex); }

        }
        return expr;
    }
    // $ANTLR end "bitXorExpr"



    // $ANTLR start "bitAndExpr"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:289:1: bitAndExpr returns [Expression expr] : l= eqExpr ( '&' r= eqExpr )* ;
    public final Expression bitAndExpr() throws RecognitionException {
        Expression expr = null;

        int bitAndExpr_StartIndex = input.index();

        Expression l =null;

        Expression r =null;


         Stack<BinaryExpression> stack = new Stack<BinaryExpression>(); 
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 23) ) { return expr; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:291:2: (l= eqExpr ( '&' r= eqExpr )* )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:291:4: l= eqExpr ( '&' r= eqExpr )*
            {
            pushFollow(FOLLOW_eqExpr_in_bitAndExpr1114);
            l=eqExpr();

            state._fsp--;
            if (state.failed) return expr;

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:291:13: ( '&' r= eqExpr )*
            loop21:
            do {
                int alt21=2;
                int LA21_0 = input.LA(1);

                if ( (LA21_0==42) ) {
                    alt21=1;
                }


                switch (alt21) {
            	case 1 :
            	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:291:14: '&' r= eqExpr
            	    {
            	    match(input,42,FOLLOW_42_in_bitAndExpr1117); if (state.failed) return expr;

            	    pushFollow(FOLLOW_eqExpr_in_bitAndExpr1121);
            	    r=eqExpr();

            	    state._fsp--;
            	    if (state.failed) return expr;

            	    if ( state.backtracking==0 ) { stack.push(new BinaryExpression(Operator.AND, null, r)); }

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
            if ( state.backtracking>0 ) { memoize(input, 23, bitAndExpr_StartIndex); }

        }
        return expr;
    }
    // $ANTLR end "bitAndExpr"



    // $ANTLR start "eqExpr"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:313:1: eqExpr returns [Expression expr] : l= relExpr (op= eqOp r= relExpr )* ;
    public final Expression eqExpr() throws RecognitionException {
        Expression expr = null;

        int eqExpr_StartIndex = input.index();

        Expression l =null;

        int op =0;

        Expression r =null;


         Stack<BinaryExpression> stack = new Stack<BinaryExpression>(); 
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 24) ) { return expr; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:315:2: (l= relExpr (op= eqOp r= relExpr )* )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:315:4: l= relExpr (op= eqOp r= relExpr )*
            {
            pushFollow(FOLLOW_relExpr_in_eqExpr1152);
            l=relExpr();

            state._fsp--;
            if (state.failed) return expr;

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:315:14: (op= eqOp r= relExpr )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==38||LA22_0==64) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:315:15: op= eqOp r= relExpr
            	    {
            	    pushFollow(FOLLOW_eqOp_in_eqExpr1157);
            	    op=eqOp();

            	    state._fsp--;
            	    if (state.failed) return expr;

            	    pushFollow(FOLLOW_relExpr_in_eqExpr1161);
            	    r=relExpr();

            	    state._fsp--;
            	    if (state.failed) return expr;

            	    if ( state.backtracking==0 ) { stack.push(new BinaryExpression(op, null, r)); }

            	    }
            	    break;

            	default :
            	    break loop22;
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
            if ( state.backtracking>0 ) { memoize(input, 24, eqExpr_StartIndex); }

        }
        return expr;
    }
    // $ANTLR end "eqExpr"



    // $ANTLR start "relExpr"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:337:1: relExpr returns [Expression expr] : l= shiftExpr (op= relOp r= shiftExpr )* ;
    public final Expression relExpr() throws RecognitionException {
        Expression expr = null;

        int relExpr_StartIndex = input.index();

        Expression l =null;

        int op =0;

        Expression r =null;


         Stack<BinaryExpression> stack = new Stack<BinaryExpression>(); 
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 25) ) { return expr; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:339:2: (l= shiftExpr (op= relOp r= shiftExpr )* )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:339:4: l= shiftExpr (op= relOp r= shiftExpr )*
            {
            pushFollow(FOLLOW_shiftExpr_in_relExpr1192);
            l=shiftExpr();

            state._fsp--;
            if (state.failed) return expr;

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:339:16: (op= relOp r= shiftExpr )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==59||LA23_0==62||(LA23_0 >= 65 && LA23_0 <= 66)) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:339:17: op= relOp r= shiftExpr
            	    {
            	    pushFollow(FOLLOW_relOp_in_relExpr1197);
            	    op=relOp();

            	    state._fsp--;
            	    if (state.failed) return expr;

            	    pushFollow(FOLLOW_shiftExpr_in_relExpr1201);
            	    r=shiftExpr();

            	    state._fsp--;
            	    if (state.failed) return expr;

            	    if ( state.backtracking==0 ) { stack.push(new BinaryExpression(op, null, r)); }

            	    }
            	    break;

            	default :
            	    break loop23;
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
            if ( state.backtracking>0 ) { memoize(input, 25, relExpr_StartIndex); }

        }
        return expr;
    }
    // $ANTLR end "relExpr"



    // $ANTLR start "shiftExpr"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:361:1: shiftExpr returns [Expression expr] : l= addExpr (op= shiftOp r= addExpr )* ;
    public final Expression shiftExpr() throws RecognitionException {
        Expression expr = null;

        int shiftExpr_StartIndex = input.index();

        Expression l =null;

        int op =0;

        Expression r =null;


         Stack<BinaryExpression> stack = new Stack<BinaryExpression>(); 
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 26) ) { return expr; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:363:2: (l= addExpr (op= shiftOp r= addExpr )* )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:363:4: l= addExpr (op= shiftOp r= addExpr )*
            {
            pushFollow(FOLLOW_addExpr_in_shiftExpr1232);
            l=addExpr();

            state._fsp--;
            if (state.failed) return expr;

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:363:14: (op= shiftOp r= addExpr )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==60||LA24_0==67) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:363:15: op= shiftOp r= addExpr
            	    {
            	    pushFollow(FOLLOW_shiftOp_in_shiftExpr1237);
            	    op=shiftOp();

            	    state._fsp--;
            	    if (state.failed) return expr;

            	    pushFollow(FOLLOW_addExpr_in_shiftExpr1241);
            	    r=addExpr();

            	    state._fsp--;
            	    if (state.failed) return expr;

            	    if ( state.backtracking==0 ) { stack.push(new BinaryExpression(op, null, r)); }

            	    }
            	    break;

            	default :
            	    break loop24;
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
            if ( state.backtracking>0 ) { memoize(input, 26, shiftExpr_StartIndex); }

        }
        return expr;
    }
    // $ANTLR end "shiftExpr"



    // $ANTLR start "addExpr"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:385:1: addExpr returns [Expression expr] : l= mulExpr (op= addOp r= mulExpr )* ;
    public final Expression addExpr() throws RecognitionException {
        Expression expr = null;

        int addExpr_StartIndex = input.index();

        Expression l =null;

        int op =0;

        Expression r =null;


         Stack<BinaryExpression> stack = new Stack<BinaryExpression>(); 
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 27) ) { return expr; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:387:2: (l= mulExpr (op= addOp r= mulExpr )* )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:387:4: l= mulExpr (op= addOp r= mulExpr )*
            {
            pushFollow(FOLLOW_mulExpr_in_addExpr1272);
            l=mulExpr();

            state._fsp--;
            if (state.failed) return expr;

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:387:14: (op= addOp r= mulExpr )*
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( (LA25_0==48||LA25_0==52) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:387:15: op= addOp r= mulExpr
            	    {
            	    pushFollow(FOLLOW_addOp_in_addExpr1277);
            	    op=addOp();

            	    state._fsp--;
            	    if (state.failed) return expr;

            	    pushFollow(FOLLOW_mulExpr_in_addExpr1281);
            	    r=mulExpr();

            	    state._fsp--;
            	    if (state.failed) return expr;

            	    if ( state.backtracking==0 ) { stack.push(new BinaryExpression(op, null, r)); }

            	    }
            	    break;

            	default :
            	    break loop25;
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
            if ( state.backtracking>0 ) { memoize(input, 27, addExpr_StartIndex); }

        }
        return expr;
    }
    // $ANTLR end "addExpr"



    // $ANTLR start "mulExpr"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:409:1: mulExpr returns [Expression expr] : l= unaryExpr (op= mulOp r= unaryExpr )* ;
    public final Expression mulExpr() throws RecognitionException {
        Expression expr = null;

        int mulExpr_StartIndex = input.index();

        Expression l =null;

        int op =0;

        Expression r =null;


         Stack<BinaryExpression> stack = new Stack<BinaryExpression>(); 
        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 28) ) { return expr; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:411:2: (l= unaryExpr (op= mulOp r= unaryExpr )* )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:411:4: l= unaryExpr (op= mulOp r= unaryExpr )*
            {
            pushFollow(FOLLOW_unaryExpr_in_mulExpr1312);
            l=unaryExpr();

            state._fsp--;
            if (state.failed) return expr;

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:411:16: (op= mulOp r= unaryExpr )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( (LA26_0==39||LA26_0==46||LA26_0==56) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:411:17: op= mulOp r= unaryExpr
            	    {
            	    pushFollow(FOLLOW_mulOp_in_mulExpr1317);
            	    op=mulOp();

            	    state._fsp--;
            	    if (state.failed) return expr;

            	    pushFollow(FOLLOW_unaryExpr_in_mulExpr1321);
            	    r=unaryExpr();

            	    state._fsp--;
            	    if (state.failed) return expr;

            	    if ( state.backtracking==0 ) { stack.push(new BinaryExpression(op, null, r)); }

            	    }
            	    break;

            	default :
            	    break loop26;
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
            if ( state.backtracking>0 ) { memoize(input, 28, mulExpr_StartIndex); }

        }
        return expr;
    }
    // $ANTLR end "mulExpr"



    // $ANTLR start "unaryExpr"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:433:1: unaryExpr returns [Expression expr] : (op= unaryOp )? postfixExpr ;
    public final Expression unaryExpr() throws RecognitionException {
        Expression expr = null;

        int unaryExpr_StartIndex = input.index();

        Integer op =null;

        Expression postfixExpr23 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 29) ) { return expr; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:434:2: ( (op= unaryOp )? postfixExpr )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:434:4: (op= unaryOp )? postfixExpr
            {
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:434:4: (op= unaryOp )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==37||LA27_0==48||LA27_0==52||LA27_0==95) ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:434:5: op= unaryOp
                    {
                    pushFollow(FOLLOW_unaryOp_in_unaryExpr1347);
                    op=unaryOp();

                    state._fsp--;
                    if (state.failed) return expr;

                    }
                    break;

            }


            pushFollow(FOLLOW_postfixExpr_in_unaryExpr1351);
            postfixExpr23=postfixExpr();

            state._fsp--;
            if (state.failed) return expr;

            if ( state.backtracking==0 ) {
            			if(op != null)
            			{
            				expr = new UnaryExpression((int)op, postfixExpr23, Operator.UNKNOWN);
            				postfixExpr23.parent = expr;
            			}
            			else
            				expr = postfixExpr23;
            		}

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 29, unaryExpr_StartIndex); }

        }
        return expr;
    }
    // $ANTLR end "unaryExpr"



    // $ANTLR start "postfixExpr"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:446:1: postfixExpr returns [Expression expr] : atom (op= postfixOp )? ;
    public final Expression postfixExpr() throws RecognitionException {
        Expression expr = null;

        int postfixExpr_StartIndex = input.index();

        Integer op =null;

        Expression atom24 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 30) ) { return expr; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:447:2: ( atom (op= postfixOp )? )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:447:4: atom (op= postfixOp )?
            {
            pushFollow(FOLLOW_atom_in_postfixExpr1370);
            atom24=atom();

            state._fsp--;
            if (state.failed) return expr;

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:447:9: (op= postfixOp )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==49||LA28_0==53) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:447:10: op= postfixOp
                    {
                    pushFollow(FOLLOW_postfixOp_in_postfixExpr1375);
                    op=postfixOp();

                    state._fsp--;
                    if (state.failed) return expr;

                    }
                    break;

            }


            if ( state.backtracking==0 ) {
            			if(op != null)
            			{
            				expr = new UnaryExpression(Operator.UNKNOWN, atom24, (int)op);
            				atom24.parent = expr;
            			}
            			else
            				expr = atom24;
            		}

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 30, postfixExpr_StartIndex); }

        }
        return expr;
    }
    // $ANTLR end "postfixExpr"



    // $ANTLR start "atom"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:459:1: atom returns [Expression expr] : ( literal | '(' e= expr ')' | variable ( ( '[' index= expr ']' ) | ( '.' member= IDENTIFIER ) )* | funcCall | newExpr );
    public final Expression atom() throws RecognitionException {
        Expression expr = null;

        int atom_StartIndex = input.index();

        Token member=null;
        Expression e =null;

        Expression index =null;

        Expression literal25 =null;

        Variable variable26 =null;

        FunctionCall funcCall27 =null;

        Expression newExpr28 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 31) ) { return expr; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:460:2: ( literal | '(' e= expr ')' | variable ( ( '[' index= expr ']' ) | ( '.' member= IDENTIFIER ) )* | funcCall | newExpr )
            int alt30=5;
            switch ( input.LA(1) ) {
            case BOOLEAN:
            case INTEGER:
            case NULL:
            case REAL:
            case STRING:
                {
                alt30=1;
                }
                break;
            case 44:
                {
                alt30=2;
                }
                break;
            case IDENTIFIER:
                {
                int LA30_3 = input.LA(2);

                if ( (LA30_3==44) ) {
                    alt30=4;
                }
                else if ( (LA30_3==EOF||(LA30_3 >= 38 && LA30_3 <= 43)||(LA30_3 >= 45 && LA30_3 <= 68)||(LA30_3 >= 72 && LA30_3 <= 75)||(LA30_3 >= 91 && LA30_3 <= 93)) ) {
                    alt30=3;
                }
                else {
                    if (state.backtracking>0) {state.failed=true; return expr;}
                    NoViableAltException nvae =
                        new NoViableAltException("", 30, 3, input);

                    throw nvae;

                }
                }
                break;
            case 85:
                {
                alt30=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 30, 0, input);

                throw nvae;

            }

            switch (alt30) {
                case 1 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:460:4: literal
                    {
                    pushFollow(FOLLOW_literal_in_atom1397);
                    literal25=literal();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) { expr = literal25; }

                    }
                    break;
                case 2 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:462:4: '(' e= expr ')'
                    {
                    match(input,44,FOLLOW_44_in_atom1406); if (state.failed) return expr;

                    pushFollow(FOLLOW_expr_in_atom1410);
                    e=expr();

                    state._fsp--;
                    if (state.failed) return expr;

                    match(input,45,FOLLOW_45_in_atom1412); if (state.failed) return expr;

                    if ( state.backtracking==0 ) { expr = e; }

                    }
                    break;
                case 3 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:464:4: variable ( ( '[' index= expr ']' ) | ( '.' member= IDENTIFIER ) )*
                    {
                    pushFollow(FOLLOW_variable_in_atom1421);
                    variable26=variable();

                    state._fsp--;
                    if (state.failed) return expr;

                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:464:13: ( ( '[' index= expr ']' ) | ( '.' member= IDENTIFIER ) )*
                    loop29:
                    do {
                        int alt29=3;
                        int LA29_0 = input.LA(1);

                        if ( (LA29_0==72) ) {
                            alt29=1;
                        }
                        else if ( (LA29_0==55) ) {
                            alt29=2;
                        }


                        switch (alt29) {
                    	case 1 :
                    	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:464:15: ( '[' index= expr ']' )
                    	    {
                    	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:464:15: ( '[' index= expr ']' )
                    	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:464:16: '[' index= expr ']'
                    	    {
                    	    match(input,72,FOLLOW_72_in_atom1426); if (state.failed) return expr;

                    	    pushFollow(FOLLOW_expr_in_atom1430);
                    	    index=expr();

                    	    state._fsp--;
                    	    if (state.failed) return expr;

                    	    match(input,73,FOLLOW_73_in_atom1432); if (state.failed) return expr;

                    	    if ( state.backtracking==0 ) { variable26.members.add(index); index.parent = variable26; }

                    	    }


                    	    }
                    	    break;
                    	case 2 :
                    	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:465:17: ( '.' member= IDENTIFIER )
                    	    {
                    	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:465:17: ( '.' member= IDENTIFIER )
                    	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:465:18: '.' member= IDENTIFIER
                    	    {
                    	    match(input,55,FOLLOW_55_in_atom1456); if (state.failed) return expr;

                    	    member=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_atom1460); if (state.failed) return expr;

                    	    if ( state.backtracking==0 ) { variable26.members.add(new StringAtom((member!=null?member.getText():null))); }

                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop29;
                        }
                    } while (true);


                    if ( state.backtracking==0 ) { expr = variable26; }

                    }
                    break;
                case 4 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:467:4: funcCall
                    {
                    pushFollow(FOLLOW_funcCall_in_atom1475);
                    funcCall27=funcCall();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) { expr = funcCall27; }

                    }
                    break;
                case 5 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:469:4: newExpr
                    {
                    pushFollow(FOLLOW_newExpr_in_atom1484);
                    newExpr28=newExpr();

                    state._fsp--;
                    if (state.failed) return expr;

                    if ( state.backtracking==0 ) { expr = newExpr28; }

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
            if ( state.backtracking>0 ) { memoize(input, 31, atom_StartIndex); }

        }
        return expr;
    }
    // $ANTLR end "atom"



    // $ANTLR start "newExpr"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:473:1: newExpr returns [Expression expr] : 'new' ( '[' count= expr ']' )? ;
    public final Expression newExpr() throws RecognitionException {
        Expression expr = null;

        int newExpr_StartIndex = input.index();

        Expression count =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 32) ) { return expr; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:474:2: ( 'new' ( '[' count= expr ']' )? )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:474:4: 'new' ( '[' count= expr ']' )?
            {
            match(input,85,FOLLOW_85_in_newExpr1503); if (state.failed) return expr;

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:474:10: ( '[' count= expr ']' )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==72) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:474:11: '[' count= expr ']'
                    {
                    match(input,72,FOLLOW_72_in_newExpr1506); if (state.failed) return expr;

                    pushFollow(FOLLOW_expr_in_newExpr1510);
                    count=expr();

                    state._fsp--;
                    if (state.failed) return expr;

                    match(input,73,FOLLOW_73_in_newExpr1512); if (state.failed) return expr;

                    }
                    break;

            }


            if ( state.backtracking==0 ) { expr = new NewExpression(count); }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 32, newExpr_StartIndex); }

        }
        return expr;
    }
    // $ANTLR end "newExpr"



    // $ANTLR start "literal"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:478:1: literal returns [Expression expr] : ( STRING | INTEGER | REAL | BOOLEAN | NULL );
    public final Expression literal() throws RecognitionException {
        Expression expr = null;

        int literal_StartIndex = input.index();

        Token STRING29=null;
        Token INTEGER30=null;
        Token REAL31=null;
        Token BOOLEAN32=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 33) ) { return expr; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:479:2: ( STRING | INTEGER | REAL | BOOLEAN | NULL )
            int alt32=5;
            switch ( input.LA(1) ) {
            case STRING:
                {
                alt32=1;
                }
                break;
            case INTEGER:
                {
                alt32=2;
                }
                break;
            case REAL:
                {
                alt32=3;
                }
                break;
            case BOOLEAN:
                {
                alt32=4;
                }
                break;
            case NULL:
                {
                alt32=5;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return expr;}
                NoViableAltException nvae =
                    new NoViableAltException("", 32, 0, input);

                throw nvae;

            }

            switch (alt32) {
                case 1 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:479:4: STRING
                    {
                    STRING29=(Token)match(input,STRING,FOLLOW_STRING_in_literal1535); if (state.failed) return expr;

                    if ( state.backtracking==0 ) { expr = new StringAtom((STRING29!=null?STRING29.getText():null).substring(1, (STRING29!=null?STRING29.getText():null).length() - 1)); }

                    }
                    break;
                case 2 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:480:4: INTEGER
                    {
                    INTEGER30=(Token)match(input,INTEGER,FOLLOW_INTEGER_in_literal1543); if (state.failed) return expr;

                    if ( state.backtracking==0 ) { expr = new IntegerAtom(Integer.parseInt((INTEGER30!=null?INTEGER30.getText():null))); }

                    }
                    break;
                case 3 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:481:4: REAL
                    {
                    REAL31=(Token)match(input,REAL,FOLLOW_REAL_in_literal1550); if (state.failed) return expr;

                    if ( state.backtracking==0 ) { expr = new RealAtom(Double.parseDouble((REAL31!=null?REAL31.getText():null))); }

                    }
                    break;
                case 4 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:482:4: BOOLEAN
                    {
                    BOOLEAN32=(Token)match(input,BOOLEAN,FOLLOW_BOOLEAN_in_literal1560); if (state.failed) return expr;

                    if ( state.backtracking==0 ) { expr = new BooleanAtom(Boolean.parseBoolean((BOOLEAN32!=null?BOOLEAN32.getText():null))); }

                    }
                    break;
                case 5 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:483:4: NULL
                    {
                    match(input,NULL,FOLLOW_NULL_in_literal1567); if (state.failed) return expr;

                    if ( state.backtracking==0 ) { expr = new NullAtom(); }

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
            if ( state.backtracking>0 ) { memoize(input, 33, literal_StartIndex); }

        }
        return expr;
    }
    // $ANTLR end "literal"



    // $ANTLR start "variable"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:486:1: variable returns [Variable expr] : IDENTIFIER ;
    public final Variable variable() throws RecognitionException {
        Variable expr = null;

        int variable_StartIndex = input.index();

        Token IDENTIFIER33=null;

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 34) ) { return expr; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:487:2: ( IDENTIFIER )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:487:4: IDENTIFIER
            {
            IDENTIFIER33=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_variable1585); if (state.failed) return expr;

            if ( state.backtracking==0 ) { expr = new Variable((IDENTIFIER33!=null?IDENTIFIER33.getText():null)); }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 34, variable_StartIndex); }

        }
        return expr;
    }
    // $ANTLR end "variable"



    // $ANTLR start "funcCall"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:490:1: funcCall returns [FunctionCall expr] : IDENTIFIER '(' ( exprList )? ')' ;
    public final FunctionCall funcCall() throws RecognitionException {
        FunctionCall expr = null;

        int funcCall_StartIndex = input.index();

        Token IDENTIFIER34=null;
        ExpressionList exprList35 =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 35) ) { return expr; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:491:2: ( IDENTIFIER '(' ( exprList )? ')' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:491:4: IDENTIFIER '(' ( exprList )? ')'
            {
            IDENTIFIER34=(Token)match(input,IDENTIFIER,FOLLOW_IDENTIFIER_in_funcCall1603); if (state.failed) return expr;

            match(input,44,FOLLOW_44_in_funcCall1605); if (state.failed) return expr;

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:491:19: ( exprList )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==BOOLEAN||(LA33_0 >= IDENTIFIER && LA33_0 <= INTEGER)||LA33_0==NULL||LA33_0==REAL||LA33_0==STRING||LA33_0==37||LA33_0==44||LA33_0==48||LA33_0==52||LA33_0==85||LA33_0==95) ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:491:19: exprList
                    {
                    pushFollow(FOLLOW_exprList_in_funcCall1607);
                    exprList35=exprList();

                    state._fsp--;
                    if (state.failed) return expr;

                    }
                    break;

            }


            match(input,45,FOLLOW_45_in_funcCall1610); if (state.failed) return expr;

            if ( state.backtracking==0 ) { expr = new FunctionCall((IDENTIFIER34!=null?IDENTIFIER34.getText():null), exprList35); if(exprList35 != null) exprList35.parent = expr; }

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }

        finally {
        	// do for sure before leaving
            if ( state.backtracking>0 ) { memoize(input, 35, funcCall_StartIndex); }

        }
        return expr;
    }
    // $ANTLR end "funcCall"



    // $ANTLR start "exprList"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:495:1: exprList returns [ExpressionList exprList] : e= expr ( ',' e= expr )* ;
    public final ExpressionList exprList() throws RecognitionException {
        ExpressionList exprList = null;

        int exprList_StartIndex = input.index();

        Expression e =null;


        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 36) ) { return exprList; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:496:2: (e= expr ( ',' e= expr )* )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:496:4: e= expr ( ',' e= expr )*
            {
            pushFollow(FOLLOW_expr_in_exprList1632);
            e=expr();

            state._fsp--;
            if (state.failed) return exprList;

            if ( state.backtracking==0 ) {
            			exprList = new ExpressionList();
            			exprList.expressions.add(e);
            			e.parent = exprList;
            		}

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:502:3: ( ',' e= expr )*
            loop34:
            do {
                int alt34=2;
                int LA34_0 = input.LA(1);

                if ( (LA34_0==51) ) {
                    alt34=1;
                }


                switch (alt34) {
            	case 1 :
            	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:502:5: ',' e= expr
            	    {
            	    match(input,51,FOLLOW_51_in_exprList1642); if (state.failed) return exprList;

            	    pushFollow(FOLLOW_expr_in_exprList1646);
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
            	    break loop34;
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
            if ( state.backtracking>0 ) { memoize(input, 36, exprList_StartIndex); }

        }
        return exprList;
    }
    // $ANTLR end "exprList"



    // $ANTLR start "eqOp"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:510:1: eqOp returns [int operator] : ( '==' | '!=' );
    public final int eqOp() throws RecognitionException {
        int operator = 0;

        int eqOp_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 37) ) { return operator; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:511:2: ( '==' | '!=' )
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==64) ) {
                alt35=1;
            }
            else if ( (LA35_0==38) ) {
                alt35=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return operator;}
                NoViableAltException nvae =
                    new NoViableAltException("", 35, 0, input);

                throw nvae;

            }
            switch (alt35) {
                case 1 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:511:4: '=='
                    {
                    match(input,64,FOLLOW_64_in_eqOp1671); if (state.failed) return operator;

                    if ( state.backtracking==0 ) { operator = Operator. EQ; }

                    }
                    break;
                case 2 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:512:4: '!='
                    {
                    match(input,38,FOLLOW_38_in_eqOp1678); if (state.failed) return operator;

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
            if ( state.backtracking>0 ) { memoize(input, 37, eqOp_StartIndex); }

        }
        return operator;
    }
    // $ANTLR end "eqOp"



    // $ANTLR start "relOp"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:515:1: relOp returns [int operator] : ( '<' | '>' | '<=' | '>=' );
    public final int relOp() throws RecognitionException {
        int operator = 0;

        int relOp_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 38) ) { return operator; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:516:2: ( '<' | '>' | '<=' | '>=' )
            int alt36=4;
            switch ( input.LA(1) ) {
            case 59:
                {
                alt36=1;
                }
                break;
            case 65:
                {
                alt36=2;
                }
                break;
            case 62:
                {
                alt36=3;
                }
                break;
            case 66:
                {
                alt36=4;
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
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:516:4: '<'
                    {
                    match(input,59,FOLLOW_59_in_relOp1695); if (state.failed) return operator;

                    if ( state.backtracking==0 ) { operator = Operator.LT ; }

                    }
                    break;
                case 2 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:517:4: '>'
                    {
                    match(input,65,FOLLOW_65_in_relOp1703); if (state.failed) return operator;

                    if ( state.backtracking==0 ) { operator = Operator.GT ; }

                    }
                    break;
                case 3 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:518:4: '<='
                    {
                    match(input,62,FOLLOW_62_in_relOp1711); if (state.failed) return operator;

                    if ( state.backtracking==0 ) { operator = Operator.LTE; }

                    }
                    break;
                case 4 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:519:4: '>='
                    {
                    match(input,66,FOLLOW_66_in_relOp1718); if (state.failed) return operator;

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
            if ( state.backtracking>0 ) { memoize(input, 38, relOp_StartIndex); }

        }
        return operator;
    }
    // $ANTLR end "relOp"



    // $ANTLR start "shiftOp"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:522:1: shiftOp returns [int operator] : ( '<<' | '>>' );
    public final int shiftOp() throws RecognitionException {
        int operator = 0;

        int shiftOp_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 39) ) { return operator; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:523:2: ( '<<' | '>>' )
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==60) ) {
                alt37=1;
            }
            else if ( (LA37_0==67) ) {
                alt37=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return operator;}
                NoViableAltException nvae =
                    new NoViableAltException("", 37, 0, input);

                throw nvae;

            }
            switch (alt37) {
                case 1 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:523:4: '<<'
                    {
                    match(input,60,FOLLOW_60_in_shiftOp1735); if (state.failed) return operator;

                    if ( state.backtracking==0 ) { operator = Operator.LSH; }

                    }
                    break;
                case 2 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:524:4: '>>'
                    {
                    match(input,67,FOLLOW_67_in_shiftOp1742); if (state.failed) return operator;

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
            if ( state.backtracking>0 ) { memoize(input, 39, shiftOp_StartIndex); }

        }
        return operator;
    }
    // $ANTLR end "shiftOp"



    // $ANTLR start "addOp"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:527:1: addOp returns [int operator] : ( '+' | '-' );
    public final int addOp() throws RecognitionException {
        int operator = 0;

        int addOp_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 40) ) { return operator; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:528:2: ( '+' | '-' )
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
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:528:4: '+'
                    {
                    match(input,48,FOLLOW_48_in_addOp1759); if (state.failed) return operator;

                    if ( state.backtracking==0 ) { operator = Operator.PLUS;  }

                    }
                    break;
                case 2 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:529:4: '-'
                    {
                    match(input,52,FOLLOW_52_in_addOp1766); if (state.failed) return operator;

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
            if ( state.backtracking>0 ) { memoize(input, 40, addOp_StartIndex); }

        }
        return operator;
    }
    // $ANTLR end "addOp"



    // $ANTLR start "mulOp"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:532:1: mulOp returns [int operator] : ( '*' | '/' | '%' );
    public final int mulOp() throws RecognitionException {
        int operator = 0;

        int mulOp_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 41) ) { return operator; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:533:2: ( '*' | '/' | '%' )
            int alt39=3;
            switch ( input.LA(1) ) {
            case 46:
                {
                alt39=1;
                }
                break;
            case 56:
                {
                alt39=2;
                }
                break;
            case 39:
                {
                alt39=3;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return operator;}
                NoViableAltException nvae =
                    new NoViableAltException("", 39, 0, input);

                throw nvae;

            }

            switch (alt39) {
                case 1 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:533:4: '*'
                    {
                    match(input,46,FOLLOW_46_in_mulOp1784); if (state.failed) return operator;

                    if ( state.backtracking==0 ) { operator = Operator.MUL; }

                    }
                    break;
                case 2 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:534:4: '/'
                    {
                    match(input,56,FOLLOW_56_in_mulOp1791); if (state.failed) return operator;

                    if ( state.backtracking==0 ) { operator = Operator.DIV; }

                    }
                    break;
                case 3 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:535:4: '%'
                    {
                    match(input,39,FOLLOW_39_in_mulOp1798); if (state.failed) return operator;

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
            if ( state.backtracking>0 ) { memoize(input, 41, mulOp_StartIndex); }

        }
        return operator;
    }
    // $ANTLR end "mulOp"



    // $ANTLR start "assignOp"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:538:1: assignOp returns [int operator] : ( '+=' | '-=' | '%=' | '/=' | '*=' | '|=' | '&=' | '^=' | '>>=' | '<<=' | '=' );
    public final int assignOp() throws RecognitionException {
        int operator = 0;

        int assignOp_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 42) ) { return operator; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:539:2: ( '+=' | '-=' | '%=' | '/=' | '*=' | '|=' | '&=' | '^=' | '>>=' | '<<=' | '=' )
            int alt40=11;
            switch ( input.LA(1) ) {
            case 50:
                {
                alt40=1;
                }
                break;
            case 54:
                {
                alt40=2;
                }
                break;
            case 40:
                {
                alt40=3;
                }
                break;
            case 57:
                {
                alt40=4;
                }
                break;
            case 47:
                {
                alt40=5;
                }
                break;
            case 92:
                {
                alt40=6;
                }
                break;
            case 43:
                {
                alt40=7;
                }
                break;
            case 75:
                {
                alt40=8;
                }
                break;
            case 68:
                {
                alt40=9;
                }
                break;
            case 61:
                {
                alt40=10;
                }
                break;
            case 63:
                {
                alt40=11;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return operator;}
                NoViableAltException nvae =
                    new NoViableAltException("", 40, 0, input);

                throw nvae;

            }

            switch (alt40) {
                case 1 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:539:4: '+='
                    {
                    match(input,50,FOLLOW_50_in_assignOp1816); if (state.failed) return operator;

                    if ( state.backtracking==0 ) { operator = Operator.ADD_ASN; }

                    }
                    break;
                case 2 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:540:4: '-='
                    {
                    match(input,54,FOLLOW_54_in_assignOp1824); if (state.failed) return operator;

                    if ( state.backtracking==0 ) { operator = Operator.SUB_ASN; }

                    }
                    break;
                case 3 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:541:4: '%='
                    {
                    match(input,40,FOLLOW_40_in_assignOp1832); if (state.failed) return operator;

                    if ( state.backtracking==0 ) { operator = Operator.MOD_ASN; }

                    }
                    break;
                case 4 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:542:4: '/='
                    {
                    match(input,57,FOLLOW_57_in_assignOp1840); if (state.failed) return operator;

                    if ( state.backtracking==0 ) { operator = Operator.DIV_ASN; }

                    }
                    break;
                case 5 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:543:4: '*='
                    {
                    match(input,47,FOLLOW_47_in_assignOp1848); if (state.failed) return operator;

                    if ( state.backtracking==0 ) { operator = Operator.MUL_ASN; }

                    }
                    break;
                case 6 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:544:4: '|='
                    {
                    match(input,92,FOLLOW_92_in_assignOp1856); if (state.failed) return operator;

                    if ( state.backtracking==0 ) { operator = Operator. OR_ASN; }

                    }
                    break;
                case 7 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:545:4: '&='
                    {
                    match(input,43,FOLLOW_43_in_assignOp1864); if (state.failed) return operator;

                    if ( state.backtracking==0 ) { operator = Operator.AND_ASN; }

                    }
                    break;
                case 8 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:546:4: '^='
                    {
                    match(input,75,FOLLOW_75_in_assignOp1872); if (state.failed) return operator;

                    if ( state.backtracking==0 ) { operator = Operator.XOR_ASN; }

                    }
                    break;
                case 9 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:547:4: '>>='
                    {
                    match(input,68,FOLLOW_68_in_assignOp1880); if (state.failed) return operator;

                    if ( state.backtracking==0 ) { operator = Operator.RSH_ASN; }

                    }
                    break;
                case 10 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:548:4: '<<='
                    {
                    match(input,61,FOLLOW_61_in_assignOp1887); if (state.failed) return operator;

                    if ( state.backtracking==0 ) { operator = Operator.LSH_ASN; }

                    }
                    break;
                case 11 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:549:4: '='
                    {
                    match(input,63,FOLLOW_63_in_assignOp1894); if (state.failed) return operator;

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
            if ( state.backtracking>0 ) { memoize(input, 42, assignOp_StartIndex); }

        }
        return operator;
    }
    // $ANTLR end "assignOp"



    // $ANTLR start "unaryOp"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:552:1: unaryOp returns [Integer operator] : ( '~' | '!' | '-' | '+' );
    public final Integer unaryOp() throws RecognitionException {
        Integer operator = null;

        int unaryOp_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 43) ) { return operator; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:553:2: ( '~' | '!' | '-' | '+' )
            int alt41=4;
            switch ( input.LA(1) ) {
            case 95:
                {
                alt41=1;
                }
                break;
            case 37:
                {
                alt41=2;
                }
                break;
            case 52:
                {
                alt41=3;
                }
                break;
            case 48:
                {
                alt41=4;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return operator;}
                NoViableAltException nvae =
                    new NoViableAltException("", 41, 0, input);

                throw nvae;

            }

            switch (alt41) {
                case 1 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:553:4: '~'
                    {
                    match(input,95,FOLLOW_95_in_unaryOp1914); if (state.failed) return operator;

                    if ( state.backtracking==0 ) { operator = new Integer(Operator.NEG); }

                    }
                    break;
                case 2 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:554:4: '!'
                    {
                    match(input,37,FOLLOW_37_in_unaryOp1921); if (state.failed) return operator;

                    if ( state.backtracking==0 ) { operator = new Integer(Operator.NOT); }

                    }
                    break;
                case 3 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:555:4: '-'
                    {
                    match(input,52,FOLLOW_52_in_unaryOp1928); if (state.failed) return operator;

                    if ( state.backtracking==0 ) { operator = new Integer(Operator.MINUS); }

                    }
                    break;
                case 4 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:556:4: '+'
                    {
                    match(input,48,FOLLOW_48_in_unaryOp1935); if (state.failed) return operator;

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
            if ( state.backtracking>0 ) { memoize(input, 43, unaryOp_StartIndex); }

        }
        return operator;
    }
    // $ANTLR end "unaryOp"



    // $ANTLR start "postfixOp"
    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:559:1: postfixOp returns [Integer operator] : ( '++' | '--' );
    public final Integer postfixOp() throws RecognitionException {
        Integer operator = null;

        int postfixOp_StartIndex = input.index();

        try {
            if ( state.backtracking>0 && alreadyParsedRule(input, 44) ) { return operator; }

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:560:2: ( '++' | '--' )
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==49) ) {
                alt42=1;
            }
            else if ( (LA42_0==53) ) {
                alt42=2;
            }
            else {
                if (state.backtracking>0) {state.failed=true; return operator;}
                NoViableAltException nvae =
                    new NoViableAltException("", 42, 0, input);

                throw nvae;

            }
            switch (alt42) {
                case 1 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:560:4: '++'
                    {
                    match(input,49,FOLLOW_49_in_postfixOp1953); if (state.failed) return operator;

                    if ( state.backtracking==0 ) { operator = new Integer(Operator.INC); }

                    }
                    break;
                case 2 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:561:4: '--'
                    {
                    match(input,53,FOLLOW_53_in_postfixOp1960); if (state.failed) return operator;

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
            if ( state.backtracking>0 ) { memoize(input, 44, postfixOp_StartIndex); }

        }
        return operator;
    }
    // $ANTLR end "postfixOp"

    // $ANTLR start synpred30_CTalk
    public final void synpred30_CTalk_fragment() throws RecognitionException {
        Statement false_stmt =null;


        // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:135:44: ( 'else' false_stmt= stmt )
        // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:135:44: 'else' false_stmt= stmt
        {
        match(input,81,FOLLOW_81_in_synpred30_CTalk747); if (state.failed) return ;

        pushFollow(FOLLOW_stmt_in_synpred30_CTalk751);
        false_stmt=stmt();

        state._fsp--;
        if (state.failed) return ;

        }

    }
    // $ANTLR end synpred30_CTalk

    // Delegated rules

    public final boolean synpred30_CTalk() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred30_CTalk_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


 

    public static final BitSet FOLLOW_funcDef_in_program65 = new BitSet(new long[]{0x0000000000020000L,0x00000000019110E0L});
    public static final BitSet FOLLOW_structDef_in_program74 = new BitSet(new long[]{0x0000000000020000L,0x00000000019110E0L});
    public static final BitSet FOLLOW_EOF_in_program83 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dataType_in_funcDef99 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_IDENTIFIER_in_funcDef101 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_44_in_funcDef103 = new BitSet(new long[]{0x0000200000020000L,0x00000000011110E0L});
    public static final BitSet FOLLOW_paramsList_in_funcDef105 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_45_in_funcDef108 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_blockStmt_in_funcDef110 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dataType_in_paramsList132 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_IDENTIFIER_in_paramsList136 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_51_in_paramsList146 = new BitSet(new long[]{0x0000000000020000L,0x00000000011110E0L});
    public static final BitSet FOLLOW_dataType_in_paramsList150 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_IDENTIFIER_in_paramsList154 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_58_in_stmt180 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_declaration_in_stmt185 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_58_in_stmt187 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_stmt194 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_58_in_stmt196 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_blockStmt_in_stmt210 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_whileStmt_in_stmt223 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_doWhileStmt_in_stmt236 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_forStmt_in_stmt247 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ifStmt_in_stmt262 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_breakStmt_in_stmt278 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_continueStmt_in_stmt291 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_returnStmt_in_stmt301 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_69_in_dataType323 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_59_in_dataType325 = new BitSet(new long[]{0x0000000000020000L,0x00000000011110E0L});
    public static final BitSet FOLLOW_dataType_in_dataType329 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_dataType331 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_84_in_dataType338 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_80_in_dataType368 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_dataType395 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_71_in_dataType424 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_dataType451 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_88_in_dataType463 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_76_in_dataType476 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_dataType_in_declaration501 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_IDENTIFIER_in_declaration505 = new BitSet(new long[]{0x8008000000000002L});
    public static final BitSet FOLLOW_63_in_declaration508 = new BitSet(new long[]{0x0011102091060010L,0x0000000080200000L});
    public static final BitSet FOLLOW_expr_in_declaration512 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_51_in_declaration524 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_IDENTIFIER_in_declaration528 = new BitSet(new long[]{0x8008000000000002L});
    public static final BitSet FOLLOW_63_in_declaration531 = new BitSet(new long[]{0x0011102091060010L,0x0000000080200000L});
    public static final BitSet FOLLOW_expr_in_declaration535 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_87_in_structDef564 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_IDENTIFIER_in_structDef568 = new BitSet(new long[]{0x0000000000000000L,0x0000000004000000L});
    public static final BitSet FOLLOW_90_in_structDef570 = new BitSet(new long[]{0x0000000000020000L,0x00000000011110E0L});
    public static final BitSet FOLLOW_dataType_in_structDef582 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_IDENTIFIER_in_structDef586 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_58_in_structDef588 = new BitSet(new long[]{0x0000000000020000L,0x00000000411110E0L});
    public static final BitSet FOLLOW_94_in_structDef600 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_89_in_whileStmt616 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_44_in_whileStmt618 = new BitSet(new long[]{0x0011102091060010L,0x0000000080200000L});
    public static final BitSet FOLLOW_condition_in_whileStmt620 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_45_in_whileStmt622 = new BitSet(new long[]{0x0411102091060010L,0x00000000877DF0E0L});
    public static final BitSet FOLLOW_stmt_in_whileStmt626 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_79_in_doWhileStmt646 = new BitSet(new long[]{0x0411102091060010L,0x00000000877DF0E0L});
    public static final BitSet FOLLOW_stmt_in_doWhileStmt650 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_89_in_doWhileStmt652 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_44_in_doWhileStmt654 = new BitSet(new long[]{0x0011102091060010L,0x0000000080200000L});
    public static final BitSet FOLLOW_condition_in_doWhileStmt656 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_45_in_doWhileStmt658 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_58_in_doWhileStmt660 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_82_in_forStmt680 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_44_in_forStmt682 = new BitSet(new long[]{0x0411102091060010L,0x00000000813110E0L});
    public static final BitSet FOLLOW_expr_in_forStmt687 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_declaration_in_forStmt691 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_58_in_forStmt695 = new BitSet(new long[]{0x0411102091060010L,0x0000000080200000L});
    public static final BitSet FOLLOW_expr_in_forStmt699 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_58_in_forStmt702 = new BitSet(new long[]{0x0011302091060010L,0x0000000080200000L});
    public static final BitSet FOLLOW_expr_in_forStmt706 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_45_in_forStmt709 = new BitSet(new long[]{0x0411102091060010L,0x00000000877DF0E0L});
    public static final BitSet FOLLOW_stmt_in_forStmt713 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_83_in_ifStmt733 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_44_in_ifStmt735 = new BitSet(new long[]{0x0011102091060010L,0x0000000080200000L});
    public static final BitSet FOLLOW_condition_in_ifStmt737 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_45_in_ifStmt739 = new BitSet(new long[]{0x0411102091060010L,0x00000000877DF0E0L});
    public static final BitSet FOLLOW_stmt_in_ifStmt743 = new BitSet(new long[]{0x0000000000000002L,0x0000000000020000L});
    public static final BitSet FOLLOW_81_in_ifStmt747 = new BitSet(new long[]{0x0411102091060010L,0x00000000877DF0E0L});
    public static final BitSet FOLLOW_stmt_in_ifStmt751 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_condition775 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_77_in_breakStmt794 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_58_in_breakStmt796 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_78_in_continueStmt815 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_58_in_continueStmt817 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_86_in_returnStmt836 = new BitSet(new long[]{0x0411102091060010L,0x0000000080200000L});
    public static final BitSet FOLLOW_expr_in_returnStmt838 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_58_in_returnStmt841 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_90_in_blockStmt866 = new BitSet(new long[]{0x0411102091060010L,0x00000000C77DF0E0L});
    public static final BitSet FOLLOW_stmt_in_blockStmt871 = new BitSet(new long[]{0x0411102091060010L,0x00000000C77DF0E0L});
    public static final BitSet FOLLOW_94_in_blockStmt877 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_assignExpr_in_expr892 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_logOrExpr_in_assignExpr920 = new BitSet(new long[]{0xA244890000000002L,0x0000000010000810L});
    public static final BitSet FOLLOW_assignOp_in_assignExpr925 = new BitSet(new long[]{0x0011102091060010L,0x0000000080200000L});
    public static final BitSet FOLLOW_logOrExpr_in_assignExpr929 = new BitSet(new long[]{0xA244890000000002L,0x0000000010000810L});
    public static final BitSet FOLLOW_logAndExpr_in_logOrExpr961 = new BitSet(new long[]{0x0000000000000002L,0x0000000020000000L});
    public static final BitSet FOLLOW_93_in_logOrExpr964 = new BitSet(new long[]{0x0011102091060010L,0x0000000080200000L});
    public static final BitSet FOLLOW_logAndExpr_in_logOrExpr968 = new BitSet(new long[]{0x0000000000000002L,0x0000000020000000L});
    public static final BitSet FOLLOW_bitOrExpr_in_logAndExpr1000 = new BitSet(new long[]{0x0000020000000002L});
    public static final BitSet FOLLOW_41_in_logAndExpr1003 = new BitSet(new long[]{0x0011102091060010L,0x0000000080200000L});
    public static final BitSet FOLLOW_bitOrExpr_in_logAndExpr1007 = new BitSet(new long[]{0x0000020000000002L});
    public static final BitSet FOLLOW_bitXorExpr_in_bitOrExpr1038 = new BitSet(new long[]{0x0000000000000002L,0x0000000008000000L});
    public static final BitSet FOLLOW_91_in_bitOrExpr1041 = new BitSet(new long[]{0x0011102091060010L,0x0000000080200000L});
    public static final BitSet FOLLOW_bitXorExpr_in_bitOrExpr1045 = new BitSet(new long[]{0x0000000000000002L,0x0000000008000000L});
    public static final BitSet FOLLOW_bitAndExpr_in_bitXorExpr1076 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000400L});
    public static final BitSet FOLLOW_74_in_bitXorExpr1079 = new BitSet(new long[]{0x0011102091060010L,0x0000000080200000L});
    public static final BitSet FOLLOW_bitAndExpr_in_bitXorExpr1083 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000400L});
    public static final BitSet FOLLOW_eqExpr_in_bitAndExpr1114 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_42_in_bitAndExpr1117 = new BitSet(new long[]{0x0011102091060010L,0x0000000080200000L});
    public static final BitSet FOLLOW_eqExpr_in_bitAndExpr1121 = new BitSet(new long[]{0x0000040000000002L});
    public static final BitSet FOLLOW_relExpr_in_eqExpr1152 = new BitSet(new long[]{0x0000004000000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_eqOp_in_eqExpr1157 = new BitSet(new long[]{0x0011102091060010L,0x0000000080200000L});
    public static final BitSet FOLLOW_relExpr_in_eqExpr1161 = new BitSet(new long[]{0x0000004000000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_shiftExpr_in_relExpr1192 = new BitSet(new long[]{0x4800000000000002L,0x0000000000000006L});
    public static final BitSet FOLLOW_relOp_in_relExpr1197 = new BitSet(new long[]{0x0011102091060010L,0x0000000080200000L});
    public static final BitSet FOLLOW_shiftExpr_in_relExpr1201 = new BitSet(new long[]{0x4800000000000002L,0x0000000000000006L});
    public static final BitSet FOLLOW_addExpr_in_shiftExpr1232 = new BitSet(new long[]{0x1000000000000002L,0x0000000000000008L});
    public static final BitSet FOLLOW_shiftOp_in_shiftExpr1237 = new BitSet(new long[]{0x0011102091060010L,0x0000000080200000L});
    public static final BitSet FOLLOW_addExpr_in_shiftExpr1241 = new BitSet(new long[]{0x1000000000000002L,0x0000000000000008L});
    public static final BitSet FOLLOW_mulExpr_in_addExpr1272 = new BitSet(new long[]{0x0011000000000002L});
    public static final BitSet FOLLOW_addOp_in_addExpr1277 = new BitSet(new long[]{0x0011102091060010L,0x0000000080200000L});
    public static final BitSet FOLLOW_mulExpr_in_addExpr1281 = new BitSet(new long[]{0x0011000000000002L});
    public static final BitSet FOLLOW_unaryExpr_in_mulExpr1312 = new BitSet(new long[]{0x0100408000000002L});
    public static final BitSet FOLLOW_mulOp_in_mulExpr1317 = new BitSet(new long[]{0x0011102091060010L,0x0000000080200000L});
    public static final BitSet FOLLOW_unaryExpr_in_mulExpr1321 = new BitSet(new long[]{0x0100408000000002L});
    public static final BitSet FOLLOW_unaryOp_in_unaryExpr1347 = new BitSet(new long[]{0x0000100091060010L,0x0000000000200000L});
    public static final BitSet FOLLOW_postfixExpr_in_unaryExpr1351 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_atom_in_postfixExpr1370 = new BitSet(new long[]{0x0022000000000002L});
    public static final BitSet FOLLOW_postfixOp_in_postfixExpr1375 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_literal_in_atom1397 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_44_in_atom1406 = new BitSet(new long[]{0x0011102091060010L,0x0000000080200000L});
    public static final BitSet FOLLOW_expr_in_atom1410 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_45_in_atom1412 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_variable_in_atom1421 = new BitSet(new long[]{0x0080000000000002L,0x0000000000000100L});
    public static final BitSet FOLLOW_72_in_atom1426 = new BitSet(new long[]{0x0011102091060010L,0x0000000080200000L});
    public static final BitSet FOLLOW_expr_in_atom1430 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_73_in_atom1432 = new BitSet(new long[]{0x0080000000000002L,0x0000000000000100L});
    public static final BitSet FOLLOW_55_in_atom1456 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_IDENTIFIER_in_atom1460 = new BitSet(new long[]{0x0080000000000002L,0x0000000000000100L});
    public static final BitSet FOLLOW_funcCall_in_atom1475 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_newExpr_in_atom1484 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_85_in_newExpr1503 = new BitSet(new long[]{0x0000000000000002L,0x0000000000000100L});
    public static final BitSet FOLLOW_72_in_newExpr1506 = new BitSet(new long[]{0x0011102091060010L,0x0000000080200000L});
    public static final BitSet FOLLOW_expr_in_newExpr1510 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_73_in_newExpr1512 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_STRING_in_literal1535 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_INTEGER_in_literal1543 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_REAL_in_literal1550 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_BOOLEAN_in_literal1560 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_NULL_in_literal1567 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_variable1585 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_IDENTIFIER_in_funcCall1603 = new BitSet(new long[]{0x0000100000000000L});
    public static final BitSet FOLLOW_44_in_funcCall1605 = new BitSet(new long[]{0x0011302091060010L,0x0000000080200000L});
    public static final BitSet FOLLOW_exprList_in_funcCall1607 = new BitSet(new long[]{0x0000200000000000L});
    public static final BitSet FOLLOW_45_in_funcCall1610 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_expr_in_exprList1632 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_51_in_exprList1642 = new BitSet(new long[]{0x0011102091060010L,0x0000000080200000L});
    public static final BitSet FOLLOW_expr_in_exprList1646 = new BitSet(new long[]{0x0008000000000002L});
    public static final BitSet FOLLOW_64_in_eqOp1671 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_eqOp1678 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_59_in_relOp1695 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_65_in_relOp1703 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_62_in_relOp1711 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_66_in_relOp1718 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_60_in_shiftOp1735 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_67_in_shiftOp1742 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_48_in_addOp1759 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_52_in_addOp1766 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_46_in_mulOp1784 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_56_in_mulOp1791 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_mulOp1798 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_50_in_assignOp1816 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_54_in_assignOp1824 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_40_in_assignOp1832 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_57_in_assignOp1840 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_47_in_assignOp1848 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_92_in_assignOp1856 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_43_in_assignOp1864 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_75_in_assignOp1872 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_assignOp1880 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_61_in_assignOp1887 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_63_in_assignOp1894 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_95_in_unaryOp1914 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_unaryOp1921 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_52_in_unaryOp1928 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_48_in_unaryOp1935 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_postfixOp1953 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_53_in_postfixOp1960 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_81_in_synpred30_CTalk747 = new BitSet(new long[]{0x0411102091060010L,0x00000000877DF0E0L});
    public static final BitSet FOLLOW_stmt_in_synpred30_CTalk751 = new BitSet(new long[]{0x0000000000000002L});

}