// $ANTLR 3.4 C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g 2011-12-06 21:30:05

	package mi.run.ctalk;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked"})
public class CTalkLexer extends Lexer {
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
    // delegators
    public Lexer[] getDelegates() {
        return new Lexer[] {};
    }

    public CTalkLexer() {} 
    public CTalkLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public CTalkLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);
    }
    public String getGrammarFileName() { return "C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g"; }

    // $ANTLR start "T__37"
    public final void mT__37() throws RecognitionException {
        try {
            int _type = T__37;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:6:7: ( '!' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:6:9: '!'
            {
            match('!'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__37"

    // $ANTLR start "T__38"
    public final void mT__38() throws RecognitionException {
        try {
            int _type = T__38;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:7:7: ( '!=' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:7:9: '!='
            {
            match("!="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__38"

    // $ANTLR start "T__39"
    public final void mT__39() throws RecognitionException {
        try {
            int _type = T__39;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:8:7: ( '%' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:8:9: '%'
            {
            match('%'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__39"

    // $ANTLR start "T__40"
    public final void mT__40() throws RecognitionException {
        try {
            int _type = T__40;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:9:7: ( '%=' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:9:9: '%='
            {
            match("%="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__40"

    // $ANTLR start "T__41"
    public final void mT__41() throws RecognitionException {
        try {
            int _type = T__41;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:10:7: ( '&&' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:10:9: '&&'
            {
            match("&&"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__41"

    // $ANTLR start "T__42"
    public final void mT__42() throws RecognitionException {
        try {
            int _type = T__42;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:11:7: ( '&' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:11:9: '&'
            {
            match('&'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__42"

    // $ANTLR start "T__43"
    public final void mT__43() throws RecognitionException {
        try {
            int _type = T__43;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:12:7: ( '&=' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:12:9: '&='
            {
            match("&="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__43"

    // $ANTLR start "T__44"
    public final void mT__44() throws RecognitionException {
        try {
            int _type = T__44;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:13:7: ( '(' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:13:9: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__44"

    // $ANTLR start "T__45"
    public final void mT__45() throws RecognitionException {
        try {
            int _type = T__45;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:14:7: ( ')' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:14:9: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__45"

    // $ANTLR start "T__46"
    public final void mT__46() throws RecognitionException {
        try {
            int _type = T__46;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:15:7: ( '*' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:15:9: '*'
            {
            match('*'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__46"

    // $ANTLR start "T__47"
    public final void mT__47() throws RecognitionException {
        try {
            int _type = T__47;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:16:7: ( '*=' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:16:9: '*='
            {
            match("*="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__47"

    // $ANTLR start "T__48"
    public final void mT__48() throws RecognitionException {
        try {
            int _type = T__48;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:17:7: ( '+' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:17:9: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__48"

    // $ANTLR start "T__49"
    public final void mT__49() throws RecognitionException {
        try {
            int _type = T__49;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:18:7: ( '++' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:18:9: '++'
            {
            match("++"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__49"

    // $ANTLR start "T__50"
    public final void mT__50() throws RecognitionException {
        try {
            int _type = T__50;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:19:7: ( '+=' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:19:9: '+='
            {
            match("+="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__50"

    // $ANTLR start "T__51"
    public final void mT__51() throws RecognitionException {
        try {
            int _type = T__51;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:20:7: ( ',' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:20:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__51"

    // $ANTLR start "T__52"
    public final void mT__52() throws RecognitionException {
        try {
            int _type = T__52;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:21:7: ( '-' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:21:9: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__52"

    // $ANTLR start "T__53"
    public final void mT__53() throws RecognitionException {
        try {
            int _type = T__53;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:22:7: ( '--' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:22:9: '--'
            {
            match("--"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__53"

    // $ANTLR start "T__54"
    public final void mT__54() throws RecognitionException {
        try {
            int _type = T__54;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:23:7: ( '-=' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:23:9: '-='
            {
            match("-="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__54"

    // $ANTLR start "T__55"
    public final void mT__55() throws RecognitionException {
        try {
            int _type = T__55;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:24:7: ( '.' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:24:9: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__55"

    // $ANTLR start "T__56"
    public final void mT__56() throws RecognitionException {
        try {
            int _type = T__56;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:25:7: ( '/' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:25:9: '/'
            {
            match('/'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__56"

    // $ANTLR start "T__57"
    public final void mT__57() throws RecognitionException {
        try {
            int _type = T__57;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:26:7: ( '/=' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:26:9: '/='
            {
            match("/="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__57"

    // $ANTLR start "T__58"
    public final void mT__58() throws RecognitionException {
        try {
            int _type = T__58;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:27:7: ( ';' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:27:9: ';'
            {
            match(';'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__58"

    // $ANTLR start "T__59"
    public final void mT__59() throws RecognitionException {
        try {
            int _type = T__59;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:28:7: ( '<' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:28:9: '<'
            {
            match('<'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__59"

    // $ANTLR start "T__60"
    public final void mT__60() throws RecognitionException {
        try {
            int _type = T__60;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:29:7: ( '<<' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:29:9: '<<'
            {
            match("<<"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__60"

    // $ANTLR start "T__61"
    public final void mT__61() throws RecognitionException {
        try {
            int _type = T__61;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:30:7: ( '<<=' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:30:9: '<<='
            {
            match("<<="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__61"

    // $ANTLR start "T__62"
    public final void mT__62() throws RecognitionException {
        try {
            int _type = T__62;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:31:7: ( '<=' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:31:9: '<='
            {
            match("<="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__62"

    // $ANTLR start "T__63"
    public final void mT__63() throws RecognitionException {
        try {
            int _type = T__63;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:32:7: ( '=' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:32:9: '='
            {
            match('='); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__63"

    // $ANTLR start "T__64"
    public final void mT__64() throws RecognitionException {
        try {
            int _type = T__64;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:33:7: ( '==' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:33:9: '=='
            {
            match("=="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__64"

    // $ANTLR start "T__65"
    public final void mT__65() throws RecognitionException {
        try {
            int _type = T__65;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:34:7: ( '>' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:34:9: '>'
            {
            match('>'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__65"

    // $ANTLR start "T__66"
    public final void mT__66() throws RecognitionException {
        try {
            int _type = T__66;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:35:7: ( '>=' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:35:9: '>='
            {
            match(">="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__66"

    // $ANTLR start "T__67"
    public final void mT__67() throws RecognitionException {
        try {
            int _type = T__67;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:36:7: ( '>>' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:36:9: '>>'
            {
            match(">>"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__67"

    // $ANTLR start "T__68"
    public final void mT__68() throws RecognitionException {
        try {
            int _type = T__68;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:37:7: ( '>>=' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:37:9: '>>='
            {
            match(">>="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__68"

    // $ANTLR start "T__69"
    public final void mT__69() throws RecognitionException {
        try {
            int _type = T__69;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:38:7: ( 'Array' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:38:9: 'Array'
            {
            match("Array"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__69"

    // $ANTLR start "T__70"
    public final void mT__70() throws RecognitionException {
        try {
            int _type = T__70;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:39:7: ( 'File' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:39:9: 'File'
            {
            match("File"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__70"

    // $ANTLR start "T__71"
    public final void mT__71() throws RecognitionException {
        try {
            int _type = T__71;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:40:7: ( 'String' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:40:9: 'String'
            {
            match("String"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__71"

    // $ANTLR start "T__72"
    public final void mT__72() throws RecognitionException {
        try {
            int _type = T__72;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:41:7: ( '[' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:41:9: '['
            {
            match('['); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__72"

    // $ANTLR start "T__73"
    public final void mT__73() throws RecognitionException {
        try {
            int _type = T__73;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:42:7: ( ']' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:42:9: ']'
            {
            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__73"

    // $ANTLR start "T__74"
    public final void mT__74() throws RecognitionException {
        try {
            int _type = T__74;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:43:7: ( '^' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:43:9: '^'
            {
            match('^'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__74"

    // $ANTLR start "T__75"
    public final void mT__75() throws RecognitionException {
        try {
            int _type = T__75;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:44:7: ( '^=' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:44:9: '^='
            {
            match("^="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__75"

    // $ANTLR start "T__76"
    public final void mT__76() throws RecognitionException {
        try {
            int _type = T__76;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:45:7: ( 'bool' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:45:9: 'bool'
            {
            match("bool"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__76"

    // $ANTLR start "T__77"
    public final void mT__77() throws RecognitionException {
        try {
            int _type = T__77;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:46:7: ( 'break' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:46:9: 'break'
            {
            match("break"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__77"

    // $ANTLR start "T__78"
    public final void mT__78() throws RecognitionException {
        try {
            int _type = T__78;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:47:7: ( 'continue' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:47:9: 'continue'
            {
            match("continue"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__78"

    // $ANTLR start "T__79"
    public final void mT__79() throws RecognitionException {
        try {
            int _type = T__79;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:48:7: ( 'do' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:48:9: 'do'
            {
            match("do"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__79"

    // $ANTLR start "T__80"
    public final void mT__80() throws RecognitionException {
        try {
            int _type = T__80;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:49:7: ( 'double' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:49:9: 'double'
            {
            match("double"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__80"

    // $ANTLR start "T__81"
    public final void mT__81() throws RecognitionException {
        try {
            int _type = T__81;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:50:7: ( 'else' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:50:9: 'else'
            {
            match("else"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__81"

    // $ANTLR start "T__82"
    public final void mT__82() throws RecognitionException {
        try {
            int _type = T__82;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:51:7: ( 'for' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:51:9: 'for'
            {
            match("for"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__82"

    // $ANTLR start "T__83"
    public final void mT__83() throws RecognitionException {
        try {
            int _type = T__83;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:52:7: ( 'if' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:52:9: 'if'
            {
            match("if"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__83"

    // $ANTLR start "T__84"
    public final void mT__84() throws RecognitionException {
        try {
            int _type = T__84;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:53:7: ( 'int' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:53:9: 'int'
            {
            match("int"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__84"

    // $ANTLR start "T__85"
    public final void mT__85() throws RecognitionException {
        try {
            int _type = T__85;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:54:7: ( 'new' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:54:9: 'new'
            {
            match("new"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__85"

    // $ANTLR start "T__86"
    public final void mT__86() throws RecognitionException {
        try {
            int _type = T__86;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:55:7: ( 'return' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:55:9: 'return'
            {
            match("return"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__86"

    // $ANTLR start "T__87"
    public final void mT__87() throws RecognitionException {
        try {
            int _type = T__87;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:56:7: ( 'struct' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:56:9: 'struct'
            {
            match("struct"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__87"

    // $ANTLR start "T__88"
    public final void mT__88() throws RecognitionException {
        try {
            int _type = T__88;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:57:7: ( 'void' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:57:9: 'void'
            {
            match("void"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__88"

    // $ANTLR start "T__89"
    public final void mT__89() throws RecognitionException {
        try {
            int _type = T__89;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:58:7: ( 'while' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:58:9: 'while'
            {
            match("while"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__89"

    // $ANTLR start "T__90"
    public final void mT__90() throws RecognitionException {
        try {
            int _type = T__90;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:59:7: ( '{' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:59:9: '{'
            {
            match('{'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__90"

    // $ANTLR start "T__91"
    public final void mT__91() throws RecognitionException {
        try {
            int _type = T__91;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:60:7: ( '|' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:60:9: '|'
            {
            match('|'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__91"

    // $ANTLR start "T__92"
    public final void mT__92() throws RecognitionException {
        try {
            int _type = T__92;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:61:7: ( '|=' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:61:9: '|='
            {
            match("|="); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__92"

    // $ANTLR start "T__93"
    public final void mT__93() throws RecognitionException {
        try {
            int _type = T__93;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:62:7: ( '||' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:62:9: '||'
            {
            match("||"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__93"

    // $ANTLR start "T__94"
    public final void mT__94() throws RecognitionException {
        try {
            int _type = T__94;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:63:7: ( '}' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:63:9: '}'
            {
            match('}'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__94"

    // $ANTLR start "T__95"
    public final void mT__95() throws RecognitionException {
        try {
            int _type = T__95;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:64:7: ( '~' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:64:9: '~'
            {
            match('~'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "T__95"

    // $ANTLR start "BOOLEAN"
    public final void mBOOLEAN() throws RecognitionException {
        try {
            int _type = BOOLEAN;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:568:8: ( 'true' | 'false' )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='t') ) {
                alt1=1;
            }
            else if ( (LA1_0=='f') ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;

            }
            switch (alt1) {
                case 1 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:568:10: 'true'
                    {
                    match("true"); 



                    }
                    break;
                case 2 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:568:19: 'false'
                    {
                    match("false"); 



                    }
                    break;

            }
            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "BOOLEAN"

    // $ANTLR start "NULL"
    public final void mNULL() throws RecognitionException {
        try {
            int _type = NULL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:570:5: ( 'null' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:570:7: 'null'
            {
            match("null"); 



            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NULL"

    // $ANTLR start "IDENTIFIER"
    public final void mIDENTIFIER() throws RecognitionException {
        try {
            int _type = IDENTIFIER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:572:11: ( NONDIGIT ( NONDIGIT | DIGIT )* )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:572:13: NONDIGIT ( NONDIGIT | DIGIT )*
            {
            mNONDIGIT(); 


            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:572:22: ( NONDIGIT | DIGIT )*
            loop2:
            do {
                int alt2=3;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0 >= 'A' && LA2_0 <= 'Z')||LA2_0=='\\'||LA2_0=='_'||(LA2_0 >= 'a' && LA2_0 <= 'z')) ) {
                    alt2=1;
                }
                else if ( ((LA2_0 >= '0' && LA2_0 <= '9')) ) {
                    alt2=2;
                }


                switch (alt2) {
            	case 1 :
            	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:572:24: NONDIGIT
            	    {
            	    mNONDIGIT(); 


            	    }
            	    break;
            	case 2 :
            	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:572:35: DIGIT
            	    {
            	    mDIGIT(); 


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "IDENTIFIER"

    // $ANTLR start "NONDIGIT"
    public final void mNONDIGIT() throws RecognitionException {
        try {
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:573:18: ( ( UNIVERSAL_CHARACTER_NAME | '_' | 'a' .. 'z' | 'A' .. 'Z' ) )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:573:20: ( UNIVERSAL_CHARACTER_NAME | '_' | 'a' .. 'z' | 'A' .. 'Z' )
            {
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:573:20: ( UNIVERSAL_CHARACTER_NAME | '_' | 'a' .. 'z' | 'A' .. 'Z' )
            int alt3=4;
            switch ( input.LA(1) ) {
            case '\\':
                {
                alt3=1;
                }
                break;
            case '_':
                {
                alt3=2;
                }
                break;
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
                alt3=3;
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
                alt3=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;

            }

            switch (alt3) {
                case 1 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:573:22: UNIVERSAL_CHARACTER_NAME
                    {
                    mUNIVERSAL_CHARACTER_NAME(); 


                    }
                    break;
                case 2 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:573:49: '_'
                    {
                    match('_'); 

                    }
                    break;
                case 3 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:573:55: 'a' .. 'z'
                    {
                    matchRange('a','z'); 

                    }
                    break;
                case 4 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:573:66: 'A' .. 'Z'
                    {
                    matchRange('A','Z'); 

                    }
                    break;

            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NONDIGIT"

    // $ANTLR start "DIGIT"
    public final void mDIGIT() throws RecognitionException {
        try {
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:574:15: ( '0' .. '9' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:
            {
            if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DIGIT"

    // $ANTLR start "UNIVERSAL_CHARACTER_NAME"
    public final void mUNIVERSAL_CHARACTER_NAME() throws RecognitionException {
        try {
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:575:34: ( ( ( '\\\\u' HEX_QUAD ) | ( '\\\\U' HEX_QUAD HEX_QUAD ) ) )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:575:36: ( ( '\\\\u' HEX_QUAD ) | ( '\\\\U' HEX_QUAD HEX_QUAD ) )
            {
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:575:36: ( ( '\\\\u' HEX_QUAD ) | ( '\\\\U' HEX_QUAD HEX_QUAD ) )
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0=='\\') ) {
                int LA4_1 = input.LA(2);

                if ( (LA4_1=='u') ) {
                    alt4=1;
                }
                else if ( (LA4_1=='U') ) {
                    alt4=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 4, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 4, 0, input);

                throw nvae;

            }
            switch (alt4) {
                case 1 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:575:38: ( '\\\\u' HEX_QUAD )
                    {
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:575:38: ( '\\\\u' HEX_QUAD )
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:575:40: '\\\\u' HEX_QUAD
                    {
                    match("\\u"); 



                    mHEX_QUAD(); 


                    }


                    }
                    break;
                case 2 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:575:59: ( '\\\\U' HEX_QUAD HEX_QUAD )
                    {
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:575:59: ( '\\\\U' HEX_QUAD HEX_QUAD )
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:575:61: '\\\\U' HEX_QUAD HEX_QUAD
                    {
                    match("\\U"); 



                    mHEX_QUAD(); 


                    mHEX_QUAD(); 


                    }


                    }
                    break;

            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "UNIVERSAL_CHARACTER_NAME"

    // $ANTLR start "HEX_QUAD"
    public final void mHEX_QUAD() throws RecognitionException {
        try {
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:576:18: ( HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:576:20: HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
            {
            mHEX_DIGIT(); 


            mHEX_DIGIT(); 


            mHEX_DIGIT(); 


            mHEX_DIGIT(); 


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "HEX_QUAD"

    // $ANTLR start "HEX_DIGIT"
    public final void mHEX_DIGIT() throws RecognitionException {
        try {
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:577:19: ( ( '0' .. '9' | 'a' .. 'f' | 'A' .. 'F' ) )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:
            {
            if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'F')||(input.LA(1) >= 'a' && input.LA(1) <= 'f') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "HEX_DIGIT"

    // $ANTLR start "OCTAL_DIGIT"
    public final void mOCTAL_DIGIT() throws RecognitionException {
        try {
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:578:21: ( '0' .. '7' )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:
            {
            if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OCTAL_DIGIT"

    // $ANTLR start "STRING"
    public final void mSTRING() throws RecognitionException {
        try {
            int _type = STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:580:7: ( ( ( ( 'L' )? '\\\"' ( S_CHAR_SEQUENCE )? '\\\"' ) | ( ( 'L' )? '\\'' ( S_CHAR_SEQUENCE )? '\\'' ) ) )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:580:9: ( ( ( 'L' )? '\\\"' ( S_CHAR_SEQUENCE )? '\\\"' ) | ( ( 'L' )? '\\'' ( S_CHAR_SEQUENCE )? '\\'' ) )
            {
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:580:9: ( ( ( 'L' )? '\\\"' ( S_CHAR_SEQUENCE )? '\\\"' ) | ( ( 'L' )? '\\'' ( S_CHAR_SEQUENCE )? '\\'' ) )
            int alt9=2;
            switch ( input.LA(1) ) {
            case 'L':
                {
                int LA9_1 = input.LA(2);

                if ( (LA9_1=='\"') ) {
                    alt9=1;
                }
                else if ( (LA9_1=='\'') ) {
                    alt9=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 9, 1, input);

                    throw nvae;

                }
                }
                break;
            case '\"':
                {
                alt9=1;
                }
                break;
            case '\'':
                {
                alt9=2;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 9, 0, input);

                throw nvae;

            }

            switch (alt9) {
                case 1 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:580:11: ( ( 'L' )? '\\\"' ( S_CHAR_SEQUENCE )? '\\\"' )
                    {
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:580:11: ( ( 'L' )? '\\\"' ( S_CHAR_SEQUENCE )? '\\\"' )
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:580:13: ( 'L' )? '\\\"' ( S_CHAR_SEQUENCE )? '\\\"'
                    {
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:580:13: ( 'L' )?
                    int alt5=2;
                    int LA5_0 = input.LA(1);

                    if ( (LA5_0=='L') ) {
                        alt5=1;
                    }
                    switch (alt5) {
                        case 1 :
                            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:580:15: 'L'
                            {
                            match('L'); 

                            }
                            break;

                    }


                    match('\"'); 

                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:580:27: ( S_CHAR_SEQUENCE )?
                    int alt6=2;
                    int LA6_0 = input.LA(1);

                    if ( ((LA6_0 >= '\u0000' && LA6_0 <= '\t')||(LA6_0 >= '\u000B' && LA6_0 <= '\f')||(LA6_0 >= '\u000E' && LA6_0 <= '!')||(LA6_0 >= '#' && LA6_0 <= '&')||(LA6_0 >= '(' && LA6_0 <= '\uFFFF')) ) {
                        alt6=1;
                    }
                    switch (alt6) {
                        case 1 :
                            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:580:29: S_CHAR_SEQUENCE
                            {
                            mS_CHAR_SEQUENCE(); 


                            }
                            break;

                    }


                    match('\"'); 

                    }


                    }
                    break;
                case 2 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:580:57: ( ( 'L' )? '\\'' ( S_CHAR_SEQUENCE )? '\\'' )
                    {
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:580:57: ( ( 'L' )? '\\'' ( S_CHAR_SEQUENCE )? '\\'' )
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:580:59: ( 'L' )? '\\'' ( S_CHAR_SEQUENCE )? '\\''
                    {
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:580:59: ( 'L' )?
                    int alt7=2;
                    int LA7_0 = input.LA(1);

                    if ( (LA7_0=='L') ) {
                        alt7=1;
                    }
                    switch (alt7) {
                        case 1 :
                            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:580:61: 'L'
                            {
                            match('L'); 

                            }
                            break;

                    }


                    match('\''); 

                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:580:73: ( S_CHAR_SEQUENCE )?
                    int alt8=2;
                    int LA8_0 = input.LA(1);

                    if ( ((LA8_0 >= '\u0000' && LA8_0 <= '\t')||(LA8_0 >= '\u000B' && LA8_0 <= '\f')||(LA8_0 >= '\u000E' && LA8_0 <= '!')||(LA8_0 >= '#' && LA8_0 <= '&')||(LA8_0 >= '(' && LA8_0 <= '\uFFFF')) ) {
                        alt8=1;
                    }
                    switch (alt8) {
                        case 1 :
                            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:580:75: S_CHAR_SEQUENCE
                            {
                            mS_CHAR_SEQUENCE(); 


                            }
                            break;

                    }


                    match('\''); 

                    }


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "STRING"

    // $ANTLR start "SIMPLE_ESCAPE_SEQUENCE"
    public final void mSIMPLE_ESCAPE_SEQUENCE() throws RecognitionException {
        try {
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:581:32: ( ( '\\\\\\'' | '\\\\\\\"' | '\\\\\\?' | '\\\\\\\\' | '\\\\a' | '\\\\b' | '\\\\f' | '\\\\n' | '\\\\r' | '\\\\t' | '\\\\v' ) )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:581:34: ( '\\\\\\'' | '\\\\\\\"' | '\\\\\\?' | '\\\\\\\\' | '\\\\a' | '\\\\b' | '\\\\f' | '\\\\n' | '\\\\r' | '\\\\t' | '\\\\v' )
            {
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:581:34: ( '\\\\\\'' | '\\\\\\\"' | '\\\\\\?' | '\\\\\\\\' | '\\\\a' | '\\\\b' | '\\\\f' | '\\\\n' | '\\\\r' | '\\\\t' | '\\\\v' )
            int alt10=11;
            int LA10_0 = input.LA(1);

            if ( (LA10_0=='\\') ) {
                switch ( input.LA(2) ) {
                case '\'':
                    {
                    alt10=1;
                    }
                    break;
                case '\"':
                    {
                    alt10=2;
                    }
                    break;
                case '\u0000':
                    {
                    alt10=3;
                    }
                    break;
                case '\\':
                    {
                    alt10=4;
                    }
                    break;
                case 'a':
                    {
                    alt10=5;
                    }
                    break;
                case 'b':
                    {
                    alt10=6;
                    }
                    break;
                case 'f':
                    {
                    alt10=7;
                    }
                    break;
                case 'n':
                    {
                    alt10=8;
                    }
                    break;
                case 'r':
                    {
                    alt10=9;
                    }
                    break;
                case 't':
                    {
                    alt10=10;
                    }
                    break;
                case 'v':
                    {
                    alt10=11;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 10, 1, input);

                    throw nvae;

                }

            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;

            }
            switch (alt10) {
                case 1 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:581:36: '\\\\\\''
                    {
                    match("\\'"); 



                    }
                    break;
                case 2 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:581:45: '\\\\\\\"'
                    {
                    match("\\\""); 



                    }
                    break;
                case 3 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:581:54: '\\\\\\?'
                    {
                    match("\\?"); 



                    }
                    break;
                case 4 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:581:63: '\\\\\\\\'
                    {
                    match("\\\\"); 



                    }
                    break;
                case 5 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:581:72: '\\\\a'
                    {
                    match("\\a"); 



                    }
                    break;
                case 6 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:581:80: '\\\\b'
                    {
                    match("\\b"); 



                    }
                    break;
                case 7 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:581:88: '\\\\f'
                    {
                    match("\\f"); 



                    }
                    break;
                case 8 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:581:96: '\\\\n'
                    {
                    match("\\n"); 



                    }
                    break;
                case 9 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:581:104: '\\\\r'
                    {
                    match("\\r"); 



                    }
                    break;
                case 10 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:581:112: '\\\\t'
                    {
                    match("\\t"); 



                    }
                    break;
                case 11 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:581:120: '\\\\v'
                    {
                    match("\\v"); 



                    }
                    break;

            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SIMPLE_ESCAPE_SEQUENCE"

    // $ANTLR start "OCTAL_ESCAPE_SEQUENCE"
    public final void mOCTAL_ESCAPE_SEQUENCE() throws RecognitionException {
        try {
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:582:31: ( '\\\\' OCTAL_DIGIT ( OCTAL_DIGIT ( OCTAL_DIGIT )? )? )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:582:33: '\\\\' OCTAL_DIGIT ( OCTAL_DIGIT ( OCTAL_DIGIT )? )?
            {
            match('\\'); 

            mOCTAL_DIGIT(); 


            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:582:50: ( OCTAL_DIGIT ( OCTAL_DIGIT )? )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( ((LA12_0 >= '0' && LA12_0 <= '7')) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:582:52: OCTAL_DIGIT ( OCTAL_DIGIT )?
                    {
                    mOCTAL_DIGIT(); 


                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:582:64: ( OCTAL_DIGIT )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( ((LA11_0 >= '0' && LA11_0 <= '7')) ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:
                            {
                            if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
                                input.consume();
                            }
                            else {
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                recover(mse);
                                throw mse;
                            }


                            }
                            break;

                    }


                    }
                    break;

            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OCTAL_ESCAPE_SEQUENCE"

    // $ANTLR start "HEXADECIMAL_ESCAPE_SEQUENCE"
    public final void mHEXADECIMAL_ESCAPE_SEQUENCE() throws RecognitionException {
        try {
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:583:37: ( '\\\\x' ( HEX_DIGIT )+ )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:583:39: '\\\\x' ( HEX_DIGIT )+
            {
            match("\\x"); 



            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:583:45: ( HEX_DIGIT )+
            int cnt13=0;
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( ((LA13_0 >= '0' && LA13_0 <= '9')||(LA13_0 >= 'A' && LA13_0 <= 'F')||(LA13_0 >= 'a' && LA13_0 <= 'f')) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'F')||(input.LA(1) >= 'a' && input.LA(1) <= 'f') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt13 >= 1 ) break loop13;
                        EarlyExitException eee =
                            new EarlyExitException(13, input);
                        throw eee;
                }
                cnt13++;
            } while (true);


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "HEXADECIMAL_ESCAPE_SEQUENCE"

    // $ANTLR start "ESCAPE_SEQUENCE"
    public final void mESCAPE_SEQUENCE() throws RecognitionException {
        try {
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:584:25: ( ( SIMPLE_ESCAPE_SEQUENCE | OCTAL_ESCAPE_SEQUENCE | HEXADECIMAL_ESCAPE_SEQUENCE ) )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:584:27: ( SIMPLE_ESCAPE_SEQUENCE | OCTAL_ESCAPE_SEQUENCE | HEXADECIMAL_ESCAPE_SEQUENCE )
            {
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:584:27: ( SIMPLE_ESCAPE_SEQUENCE | OCTAL_ESCAPE_SEQUENCE | HEXADECIMAL_ESCAPE_SEQUENCE )
            int alt14=3;
            int LA14_0 = input.LA(1);

            if ( (LA14_0=='\\') ) {
                switch ( input.LA(2) ) {
                case '\u0000':
                case '\"':
                case '\'':
                case '\\':
                case 'a':
                case 'b':
                case 'f':
                case 'n':
                case 'r':
                case 't':
                case 'v':
                    {
                    alt14=1;
                    }
                    break;
                case 'x':
                    {
                    alt14=3;
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
                    {
                    alt14=2;
                    }
                    break;
                default:
                    NoViableAltException nvae =
                        new NoViableAltException("", 14, 1, input);

                    throw nvae;

                }

            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 14, 0, input);

                throw nvae;

            }
            switch (alt14) {
                case 1 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:584:29: SIMPLE_ESCAPE_SEQUENCE
                    {
                    mSIMPLE_ESCAPE_SEQUENCE(); 


                    }
                    break;
                case 2 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:584:54: OCTAL_ESCAPE_SEQUENCE
                    {
                    mOCTAL_ESCAPE_SEQUENCE(); 


                    }
                    break;
                case 3 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:584:78: HEXADECIMAL_ESCAPE_SEQUENCE
                    {
                    mHEXADECIMAL_ESCAPE_SEQUENCE(); 


                    }
                    break;

            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "ESCAPE_SEQUENCE"

    // $ANTLR start "S_CHAR"
    public final void mS_CHAR() throws RecognitionException {
        try {
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:585:16: ( ( ( NOT_STR_CHAR )+ | ESCAPE_SEQUENCE | UNIVERSAL_CHARACTER_NAME ) )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:585:18: ( ( NOT_STR_CHAR )+ | ESCAPE_SEQUENCE | UNIVERSAL_CHARACTER_NAME )
            {
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:585:18: ( ( NOT_STR_CHAR )+ | ESCAPE_SEQUENCE | UNIVERSAL_CHARACTER_NAME )
            int alt16=3;
            int LA16_0 = input.LA(1);

            if ( ((LA16_0 >= '\u0000' && LA16_0 <= '\t')||(LA16_0 >= '\u000B' && LA16_0 <= '\f')||(LA16_0 >= '\u000E' && LA16_0 <= '!')||(LA16_0 >= '#' && LA16_0 <= '&')||(LA16_0 >= '(' && LA16_0 <= '[')||(LA16_0 >= ']' && LA16_0 <= '\uFFFF')) ) {
                alt16=1;
            }
            else if ( (LA16_0=='\\') ) {
                int LA16_2 = input.LA(2);

                if ( (LA16_2=='\u0000'||LA16_2=='\"'||LA16_2=='\''||(LA16_2 >= '0' && LA16_2 <= '7')||LA16_2=='\\'||(LA16_2 >= 'a' && LA16_2 <= 'b')||LA16_2=='f'||LA16_2=='n'||LA16_2=='r'||LA16_2=='t'||LA16_2=='v'||LA16_2=='x') ) {
                    alt16=2;
                }
                else if ( (LA16_2=='U'||LA16_2=='u') ) {
                    alt16=3;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 16, 2, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;

            }
            switch (alt16) {
                case 1 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:585:20: ( NOT_STR_CHAR )+
                    {
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:585:20: ( NOT_STR_CHAR )+
                    int cnt15=0;
                    loop15:
                    do {
                        int alt15=2;
                        int LA15_0 = input.LA(1);

                        if ( ((LA15_0 >= '\u0000' && LA15_0 <= '\t')||(LA15_0 >= '\u000B' && LA15_0 <= '\f')||(LA15_0 >= '\u000E' && LA15_0 <= '!')||(LA15_0 >= '#' && LA15_0 <= '&')||(LA15_0 >= '(' && LA15_0 <= '[')||(LA15_0 >= ']' && LA15_0 <= '\uFFFF')) ) {
                            alt15=1;
                        }


                        switch (alt15) {
                    	case 1 :
                    	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:
                    	    {
                    	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\f')||(input.LA(1) >= '\u000E' && input.LA(1) <= '!')||(input.LA(1) >= '#' && input.LA(1) <= '&')||(input.LA(1) >= '(' && input.LA(1) <= '[')||(input.LA(1) >= ']' && input.LA(1) <= '\uFFFF') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    if ( cnt15 >= 1 ) break loop15;
                                EarlyExitException eee =
                                    new EarlyExitException(15, input);
                                throw eee;
                        }
                        cnt15++;
                    } while (true);


                    }
                    break;
                case 2 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:585:40: ESCAPE_SEQUENCE
                    {
                    mESCAPE_SEQUENCE(); 


                    }
                    break;
                case 3 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:585:58: UNIVERSAL_CHARACTER_NAME
                    {
                    mUNIVERSAL_CHARACTER_NAME(); 


                    }
                    break;

            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "S_CHAR"

    // $ANTLR start "S_CHAR_SEQUENCE"
    public final void mS_CHAR_SEQUENCE() throws RecognitionException {
        try {
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:586:25: ( ( S_CHAR )+ )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:586:27: ( S_CHAR )+
            {
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:586:27: ( S_CHAR )+
            int cnt17=0;
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( ((LA17_0 >= '\u0000' && LA17_0 <= '\t')||(LA17_0 >= '\u000B' && LA17_0 <= '\f')||(LA17_0 >= '\u000E' && LA17_0 <= '!')||(LA17_0 >= '#' && LA17_0 <= '&')||(LA17_0 >= '(' && LA17_0 <= '\uFFFF')) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:586:29: S_CHAR
            	    {
            	    mS_CHAR(); 


            	    }
            	    break;

            	default :
            	    if ( cnt17 >= 1 ) break loop17;
                        EarlyExitException eee =
                            new EarlyExitException(17, input);
                        throw eee;
                }
                cnt17++;
            } while (true);


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "S_CHAR_SEQUENCE"

    // $ANTLR start "NOT_STR_CHAR"
    public final void mNOT_STR_CHAR() throws RecognitionException {
        try {
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:587:22: (~ ( '\\\"' | '\\'' | '\\\\' | '\\n' | '\\r' ) )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:
            {
            if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\f')||(input.LA(1) >= '\u000E' && input.LA(1) <= '!')||(input.LA(1) >= '#' && input.LA(1) <= '&')||(input.LA(1) >= '(' && input.LA(1) <= '[')||(input.LA(1) >= ']' && input.LA(1) <= '\uFFFF') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NOT_STR_CHAR"

    // $ANTLR start "REAL"
    public final void mREAL() throws RecognitionException {
        try {
            int _type = REAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:590:5: ( ( ( FRACTIONAL_CONSTANT ( EXPONENT_PART )? ( FLOATING_SUFFIX )? ) | ( DIGIT_SEQUENCE EXPONENT_PART ( FLOATING_SUFFIX )? ) ) )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:590:7: ( ( FRACTIONAL_CONSTANT ( EXPONENT_PART )? ( FLOATING_SUFFIX )? ) | ( DIGIT_SEQUENCE EXPONENT_PART ( FLOATING_SUFFIX )? ) )
            {
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:590:7: ( ( FRACTIONAL_CONSTANT ( EXPONENT_PART )? ( FLOATING_SUFFIX )? ) | ( DIGIT_SEQUENCE EXPONENT_PART ( FLOATING_SUFFIX )? ) )
            int alt21=2;
            alt21 = dfa21.predict(input);
            switch (alt21) {
                case 1 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:590:9: ( FRACTIONAL_CONSTANT ( EXPONENT_PART )? ( FLOATING_SUFFIX )? )
                    {
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:590:9: ( FRACTIONAL_CONSTANT ( EXPONENT_PART )? ( FLOATING_SUFFIX )? )
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:590:11: FRACTIONAL_CONSTANT ( EXPONENT_PART )? ( FLOATING_SUFFIX )?
                    {
                    mFRACTIONAL_CONSTANT(); 


                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:590:31: ( EXPONENT_PART )?
                    int alt18=2;
                    int LA18_0 = input.LA(1);

                    if ( (LA18_0=='E'||LA18_0=='e') ) {
                        alt18=1;
                    }
                    switch (alt18) {
                        case 1 :
                            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:590:33: EXPONENT_PART
                            {
                            mEXPONENT_PART(); 


                            }
                            break;

                    }


                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:590:50: ( FLOATING_SUFFIX )?
                    int alt19=2;
                    int LA19_0 = input.LA(1);

                    if ( (LA19_0=='F'||LA19_0=='L'||LA19_0=='f'||LA19_0=='l') ) {
                        alt19=1;
                    }
                    switch (alt19) {
                        case 1 :
                            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:
                            {
                            if ( input.LA(1)=='F'||input.LA(1)=='L'||input.LA(1)=='f'||input.LA(1)=='l' ) {
                                input.consume();
                            }
                            else {
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                recover(mse);
                                throw mse;
                            }


                            }
                            break;

                    }


                    }


                    }
                    break;
                case 2 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:590:75: ( DIGIT_SEQUENCE EXPONENT_PART ( FLOATING_SUFFIX )? )
                    {
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:590:75: ( DIGIT_SEQUENCE EXPONENT_PART ( FLOATING_SUFFIX )? )
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:590:77: DIGIT_SEQUENCE EXPONENT_PART ( FLOATING_SUFFIX )?
                    {
                    mDIGIT_SEQUENCE(); 


                    mEXPONENT_PART(); 


                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:590:106: ( FLOATING_SUFFIX )?
                    int alt20=2;
                    int LA20_0 = input.LA(1);

                    if ( (LA20_0=='F'||LA20_0=='L'||LA20_0=='f'||LA20_0=='l') ) {
                        alt20=1;
                    }
                    switch (alt20) {
                        case 1 :
                            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:
                            {
                            if ( input.LA(1)=='F'||input.LA(1)=='L'||input.LA(1)=='f'||input.LA(1)=='l' ) {
                                input.consume();
                            }
                            else {
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                recover(mse);
                                throw mse;
                            }


                            }
                            break;

                    }


                    }


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "REAL"

    // $ANTLR start "EXPONENT_PART"
    public final void mEXPONENT_PART() throws RecognitionException {
        try {
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:591:23: ( ( 'e' | 'E' ) ( SIGN )? DIGIT_SEQUENCE )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:591:25: ( 'e' | 'E' ) ( SIGN )? DIGIT_SEQUENCE
            {
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:591:39: ( SIGN )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0=='+'||LA22_0=='-') ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:
                    {
                    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                        input.consume();
                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;
                    }


                    }
                    break;

            }


            mDIGIT_SEQUENCE(); 


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "EXPONENT_PART"

    // $ANTLR start "FRACTIONAL_CONSTANT"
    public final void mFRACTIONAL_CONSTANT() throws RecognitionException {
        try {
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:592:29: ( ( ( ( DIGIT_SEQUENCE )? '.' DIGIT_SEQUENCE ) | ( DIGIT_SEQUENCE '.' ) ) )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:592:31: ( ( ( DIGIT_SEQUENCE )? '.' DIGIT_SEQUENCE ) | ( DIGIT_SEQUENCE '.' ) )
            {
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:592:31: ( ( ( DIGIT_SEQUENCE )? '.' DIGIT_SEQUENCE ) | ( DIGIT_SEQUENCE '.' ) )
            int alt24=2;
            alt24 = dfa24.predict(input);
            switch (alt24) {
                case 1 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:592:33: ( ( DIGIT_SEQUENCE )? '.' DIGIT_SEQUENCE )
                    {
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:592:33: ( ( DIGIT_SEQUENCE )? '.' DIGIT_SEQUENCE )
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:592:35: ( DIGIT_SEQUENCE )? '.' DIGIT_SEQUENCE
                    {
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:592:35: ( DIGIT_SEQUENCE )?
                    int alt23=2;
                    int LA23_0 = input.LA(1);

                    if ( ((LA23_0 >= '0' && LA23_0 <= '9')) ) {
                        alt23=1;
                    }
                    switch (alt23) {
                        case 1 :
                            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:592:37: DIGIT_SEQUENCE
                            {
                            mDIGIT_SEQUENCE(); 


                            }
                            break;

                    }


                    match('.'); 

                    mDIGIT_SEQUENCE(); 


                    }


                    }
                    break;
                case 2 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:592:78: ( DIGIT_SEQUENCE '.' )
                    {
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:592:78: ( DIGIT_SEQUENCE '.' )
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:592:80: DIGIT_SEQUENCE '.'
                    {
                    mDIGIT_SEQUENCE(); 


                    match('.'); 

                    }


                    }
                    break;

            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "FRACTIONAL_CONSTANT"

    // $ANTLR start "DIGIT_SEQUENCE"
    public final void mDIGIT_SEQUENCE() throws RecognitionException {
        try {
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:593:24: ( ( DIGIT )+ )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:593:26: ( DIGIT )+
            {
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:593:26: ( DIGIT )+
            int cnt25=0;
            loop25:
            do {
                int alt25=2;
                int LA25_0 = input.LA(1);

                if ( ((LA25_0 >= '0' && LA25_0 <= '9')) ) {
                    alt25=1;
                }


                switch (alt25) {
            	case 1 :
            	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt25 >= 1 ) break loop25;
                        EarlyExitException eee =
                            new EarlyExitException(25, input);
                        throw eee;
                }
                cnt25++;
            } while (true);


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DIGIT_SEQUENCE"

    // $ANTLR start "SIGN"
    public final void mSIGN() throws RecognitionException {
        try {
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:594:14: ( ( '+' | '-' ) )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:
            {
            if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "SIGN"

    // $ANTLR start "FLOATING_SUFFIX"
    public final void mFLOATING_SUFFIX() throws RecognitionException {
        try {
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:595:25: ( ( 'f' | 'l' | 'F' | 'L' ) )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:
            {
            if ( input.LA(1)=='F'||input.LA(1)=='L'||input.LA(1)=='f'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "FLOATING_SUFFIX"

    // $ANTLR start "INTEGER"
    public final void mINTEGER() throws RecognitionException {
        try {
            int _type = INTEGER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:597:8: ( ( DECIMAL_LITERAL | OCTAL_LITERAL | HEXADECIMAL_LITERAL ) ( INTEGER_SUFFIX )? )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:597:10: ( DECIMAL_LITERAL | OCTAL_LITERAL | HEXADECIMAL_LITERAL ) ( INTEGER_SUFFIX )?
            {
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:597:10: ( DECIMAL_LITERAL | OCTAL_LITERAL | HEXADECIMAL_LITERAL )
            int alt26=3;
            int LA26_0 = input.LA(1);

            if ( ((LA26_0 >= '1' && LA26_0 <= '9')) ) {
                alt26=1;
            }
            else if ( (LA26_0=='0') ) {
                int LA26_2 = input.LA(2);

                if ( (LA26_2=='X'||LA26_2=='x') ) {
                    alt26=3;
                }
                else {
                    alt26=2;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 26, 0, input);

                throw nvae;

            }
            switch (alt26) {
                case 1 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:597:12: DECIMAL_LITERAL
                    {
                    mDECIMAL_LITERAL(); 


                    }
                    break;
                case 2 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:597:30: OCTAL_LITERAL
                    {
                    mOCTAL_LITERAL(); 


                    }
                    break;
                case 3 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:597:46: HEXADECIMAL_LITERAL
                    {
                    mHEXADECIMAL_LITERAL(); 


                    }
                    break;

            }


            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:597:68: ( INTEGER_SUFFIX )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0=='L'||LA27_0=='U'||LA27_0=='l'||LA27_0=='u') ) {
                alt27=1;
            }
            switch (alt27) {
                case 1 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:597:70: INTEGER_SUFFIX
                    {
                    mINTEGER_SUFFIX(); 


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "INTEGER"

    // $ANTLR start "INTEGER_SUFFIX"
    public final void mINTEGER_SUFFIX() throws RecognitionException {
        try {
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:598:24: ( ( UNSIGNED_SUFFIX ( LONG_SUFFIX )? | LONG_SUFFIX ( UNSIGNED_SUFFIX )? ) )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:598:26: ( UNSIGNED_SUFFIX ( LONG_SUFFIX )? | LONG_SUFFIX ( UNSIGNED_SUFFIX )? )
            {
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:598:26: ( UNSIGNED_SUFFIX ( LONG_SUFFIX )? | LONG_SUFFIX ( UNSIGNED_SUFFIX )? )
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0=='U'||LA30_0=='u') ) {
                alt30=1;
            }
            else if ( (LA30_0=='L'||LA30_0=='l') ) {
                alt30=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 30, 0, input);

                throw nvae;

            }
            switch (alt30) {
                case 1 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:598:28: UNSIGNED_SUFFIX ( LONG_SUFFIX )?
                    {
                    mUNSIGNED_SUFFIX(); 


                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:598:44: ( LONG_SUFFIX )?
                    int alt28=2;
                    int LA28_0 = input.LA(1);

                    if ( (LA28_0=='L'||LA28_0=='l') ) {
                        alt28=1;
                    }
                    switch (alt28) {
                        case 1 :
                            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:
                            {
                            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                                input.consume();
                            }
                            else {
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                recover(mse);
                                throw mse;
                            }


                            }
                            break;

                    }


                    }
                    break;
                case 2 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:598:63: LONG_SUFFIX ( UNSIGNED_SUFFIX )?
                    {
                    mLONG_SUFFIX(); 


                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:598:75: ( UNSIGNED_SUFFIX )?
                    int alt29=2;
                    int LA29_0 = input.LA(1);

                    if ( (LA29_0=='U'||LA29_0=='u') ) {
                        alt29=1;
                    }
                    switch (alt29) {
                        case 1 :
                            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:
                            {
                            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                                input.consume();
                            }
                            else {
                                MismatchedSetException mse = new MismatchedSetException(null,input);
                                recover(mse);
                                throw mse;
                            }


                            }
                            break;

                    }


                    }
                    break;

            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "INTEGER_SUFFIX"

    // $ANTLR start "DECIMAL_LITERAL"
    public final void mDECIMAL_LITERAL() throws RecognitionException {
        try {
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:599:25: ( NONZERO_DIGIT ( DIGIT )* )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:599:27: NONZERO_DIGIT ( DIGIT )*
            {
            mNONZERO_DIGIT(); 


            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:599:41: ( DIGIT )*
            loop31:
            do {
                int alt31=2;
                int LA31_0 = input.LA(1);

                if ( ((LA31_0 >= '0' && LA31_0 <= '9')) ) {
                    alt31=1;
                }


                switch (alt31) {
            	case 1 :
            	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop31;
                }
            } while (true);


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "DECIMAL_LITERAL"

    // $ANTLR start "OCTAL_LITERAL"
    public final void mOCTAL_LITERAL() throws RecognitionException {
        try {
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:600:23: ( '0' ( OCTAL_DIGIT )* )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:600:25: '0' ( OCTAL_DIGIT )*
            {
            match('0'); 

            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:600:29: ( OCTAL_DIGIT )*
            loop32:
            do {
                int alt32=2;
                int LA32_0 = input.LA(1);

                if ( ((LA32_0 >= '0' && LA32_0 <= '7')) ) {
                    alt32=1;
                }


                switch (alt32) {
            	case 1 :
            	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '7') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop32;
                }
            } while (true);


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "OCTAL_LITERAL"

    // $ANTLR start "HEXADECIMAL_LITERAL"
    public final void mHEXADECIMAL_LITERAL() throws RecognitionException {
        try {
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:601:29: ( ( '0x' | '0X' ) ( HEX_DIGIT )+ )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:601:31: ( '0x' | '0X' ) ( HEX_DIGIT )+
            {
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:601:31: ( '0x' | '0X' )
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0=='0') ) {
                int LA33_1 = input.LA(2);

                if ( (LA33_1=='x') ) {
                    alt33=1;
                }
                else if ( (LA33_1=='X') ) {
                    alt33=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 33, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 33, 0, input);

                throw nvae;

            }
            switch (alt33) {
                case 1 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:601:33: '0x'
                    {
                    match("0x"); 



                    }
                    break;
                case 2 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:601:40: '0X'
                    {
                    match("0X"); 



                    }
                    break;

            }


            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:601:47: ( HEX_DIGIT )+
            int cnt34=0;
            loop34:
            do {
                int alt34=2;
                int LA34_0 = input.LA(1);

                if ( ((LA34_0 >= '0' && LA34_0 <= '9')||(LA34_0 >= 'A' && LA34_0 <= 'F')||(LA34_0 >= 'a' && LA34_0 <= 'f')) ) {
                    alt34=1;
                }


                switch (alt34) {
            	case 1 :
            	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:
            	    {
            	    if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'F')||(input.LA(1) >= 'a' && input.LA(1) <= 'f') ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt34 >= 1 ) break loop34;
                        EarlyExitException eee =
                            new EarlyExitException(34, input);
                        throw eee;
                }
                cnt34++;
            } while (true);


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "HEXADECIMAL_LITERAL"

    // $ANTLR start "NONZERO_DIGIT"
    public final void mNONZERO_DIGIT() throws RecognitionException {
        try {
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:602:23: ( ( '1' .. '9' ) )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:
            {
            if ( (input.LA(1) >= '1' && input.LA(1) <= '9') ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "NONZERO_DIGIT"

    // $ANTLR start "UNSIGNED_SUFFIX"
    public final void mUNSIGNED_SUFFIX() throws RecognitionException {
        try {
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:603:25: ( ( 'u' | 'U' ) )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:
            {
            if ( input.LA(1)=='U'||input.LA(1)=='u' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "UNSIGNED_SUFFIX"

    // $ANTLR start "LONG_SUFFIX"
    public final void mLONG_SUFFIX() throws RecognitionException {
        try {
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:604:21: ( ( 'l' | 'L' ) )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:
            {
            if ( input.LA(1)=='L'||input.LA(1)=='l' ) {
                input.consume();
            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;
            }


            }


        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "LONG_SUFFIX"

    // $ANTLR start "WHITE_SPACE"
    public final void mWHITE_SPACE() throws RecognitionException {
        try {
            int _type = WHITE_SPACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:606:12: ( ( ' ' | '\\t' | '\\n' | '\\r' )+ )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:606:14: ( ' ' | '\\t' | '\\n' | '\\r' )+
            {
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:606:14: ( ' ' | '\\t' | '\\n' | '\\r' )+
            int cnt35=0;
            loop35:
            do {
                int alt35=2;
                int LA35_0 = input.LA(1);

                if ( ((LA35_0 >= '\t' && LA35_0 <= '\n')||LA35_0=='\r'||LA35_0==' ') ) {
                    alt35=1;
                }


                switch (alt35) {
            	case 1 :
            	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:
            	    {
            	    if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();
            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt35 >= 1 ) break loop35;
                        EarlyExitException eee =
                            new EarlyExitException(35, input);
                        throw eee;
                }
                cnt35++;
            } while (true);


             _channel = HIDDEN; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "WHITE_SPACE"

    // $ANTLR start "COMMENT"
    public final void mCOMMENT() throws RecognitionException {
        try {
            int _type = COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:608:8: ( ( ( '/*' ( options {greedy=false; } : . )* '*/' ) | ( '//' (~ ( '\\n' | '\\r' ) )* ) ) )
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:608:10: ( ( '/*' ( options {greedy=false; } : . )* '*/' ) | ( '//' (~ ( '\\n' | '\\r' ) )* ) )
            {
            // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:608:10: ( ( '/*' ( options {greedy=false; } : . )* '*/' ) | ( '//' (~ ( '\\n' | '\\r' ) )* ) )
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0=='/') ) {
                int LA38_1 = input.LA(2);

                if ( (LA38_1=='*') ) {
                    alt38=1;
                }
                else if ( (LA38_1=='/') ) {
                    alt38=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 38, 1, input);

                    throw nvae;

                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 38, 0, input);

                throw nvae;

            }
            switch (alt38) {
                case 1 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:608:12: ( '/*' ( options {greedy=false; } : . )* '*/' )
                    {
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:608:12: ( '/*' ( options {greedy=false; } : . )* '*/' )
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:608:14: '/*' ( options {greedy=false; } : . )* '*/'
                    {
                    match("/*"); 



                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:608:19: ( options {greedy=false; } : . )*
                    loop36:
                    do {
                        int alt36=2;
                        int LA36_0 = input.LA(1);

                        if ( (LA36_0=='*') ) {
                            int LA36_1 = input.LA(2);

                            if ( (LA36_1=='/') ) {
                                alt36=2;
                            }
                            else if ( ((LA36_1 >= '\u0000' && LA36_1 <= '.')||(LA36_1 >= '0' && LA36_1 <= '\uFFFF')) ) {
                                alt36=1;
                            }


                        }
                        else if ( ((LA36_0 >= '\u0000' && LA36_0 <= ')')||(LA36_0 >= '+' && LA36_0 <= '\uFFFF')) ) {
                            alt36=1;
                        }


                        switch (alt36) {
                    	case 1 :
                    	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:608:46: .
                    	    {
                    	    matchAny(); 

                    	    }
                    	    break;

                    	default :
                    	    break loop36;
                        }
                    } while (true);


                    match("*/"); 



                    }


                    }
                    break;
                case 2 :
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:608:60: ( '//' (~ ( '\\n' | '\\r' ) )* )
                    {
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:608:60: ( '//' (~ ( '\\n' | '\\r' ) )* )
                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:608:62: '//' (~ ( '\\n' | '\\r' ) )*
                    {
                    match("//"); 



                    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:608:67: (~ ( '\\n' | '\\r' ) )*
                    loop37:
                    do {
                        int alt37=2;
                        int LA37_0 = input.LA(1);

                        if ( ((LA37_0 >= '\u0000' && LA37_0 <= '\t')||(LA37_0 >= '\u000B' && LA37_0 <= '\f')||(LA37_0 >= '\u000E' && LA37_0 <= '\uFFFF')) ) {
                            alt37=1;
                        }


                        switch (alt37) {
                    	case 1 :
                    	    // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:
                    	    {
                    	    if ( (input.LA(1) >= '\u0000' && input.LA(1) <= '\t')||(input.LA(1) >= '\u000B' && input.LA(1) <= '\f')||(input.LA(1) >= '\u000E' && input.LA(1) <= '\uFFFF') ) {
                    	        input.consume();
                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop37;
                        }
                    } while (true);


                    }


                    }
                    break;

            }


             _channel = HIDDEN; 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        	// do for sure before leaving
        }
    }
    // $ANTLR end "COMMENT"

    public void mTokens() throws RecognitionException {
        // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:8: ( T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | T__86 | T__87 | T__88 | T__89 | T__90 | T__91 | T__92 | T__93 | T__94 | T__95 | BOOLEAN | NULL | IDENTIFIER | STRING | REAL | INTEGER | WHITE_SPACE | COMMENT )
        int alt39=67;
        alt39 = dfa39.predict(input);
        switch (alt39) {
            case 1 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:10: T__37
                {
                mT__37(); 


                }
                break;
            case 2 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:16: T__38
                {
                mT__38(); 


                }
                break;
            case 3 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:22: T__39
                {
                mT__39(); 


                }
                break;
            case 4 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:28: T__40
                {
                mT__40(); 


                }
                break;
            case 5 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:34: T__41
                {
                mT__41(); 


                }
                break;
            case 6 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:40: T__42
                {
                mT__42(); 


                }
                break;
            case 7 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:46: T__43
                {
                mT__43(); 


                }
                break;
            case 8 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:52: T__44
                {
                mT__44(); 


                }
                break;
            case 9 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:58: T__45
                {
                mT__45(); 


                }
                break;
            case 10 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:64: T__46
                {
                mT__46(); 


                }
                break;
            case 11 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:70: T__47
                {
                mT__47(); 


                }
                break;
            case 12 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:76: T__48
                {
                mT__48(); 


                }
                break;
            case 13 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:82: T__49
                {
                mT__49(); 


                }
                break;
            case 14 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:88: T__50
                {
                mT__50(); 


                }
                break;
            case 15 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:94: T__51
                {
                mT__51(); 


                }
                break;
            case 16 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:100: T__52
                {
                mT__52(); 


                }
                break;
            case 17 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:106: T__53
                {
                mT__53(); 


                }
                break;
            case 18 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:112: T__54
                {
                mT__54(); 


                }
                break;
            case 19 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:118: T__55
                {
                mT__55(); 


                }
                break;
            case 20 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:124: T__56
                {
                mT__56(); 


                }
                break;
            case 21 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:130: T__57
                {
                mT__57(); 


                }
                break;
            case 22 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:136: T__58
                {
                mT__58(); 


                }
                break;
            case 23 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:142: T__59
                {
                mT__59(); 


                }
                break;
            case 24 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:148: T__60
                {
                mT__60(); 


                }
                break;
            case 25 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:154: T__61
                {
                mT__61(); 


                }
                break;
            case 26 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:160: T__62
                {
                mT__62(); 


                }
                break;
            case 27 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:166: T__63
                {
                mT__63(); 


                }
                break;
            case 28 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:172: T__64
                {
                mT__64(); 


                }
                break;
            case 29 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:178: T__65
                {
                mT__65(); 


                }
                break;
            case 30 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:184: T__66
                {
                mT__66(); 


                }
                break;
            case 31 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:190: T__67
                {
                mT__67(); 


                }
                break;
            case 32 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:196: T__68
                {
                mT__68(); 


                }
                break;
            case 33 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:202: T__69
                {
                mT__69(); 


                }
                break;
            case 34 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:208: T__70
                {
                mT__70(); 


                }
                break;
            case 35 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:214: T__71
                {
                mT__71(); 


                }
                break;
            case 36 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:220: T__72
                {
                mT__72(); 


                }
                break;
            case 37 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:226: T__73
                {
                mT__73(); 


                }
                break;
            case 38 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:232: T__74
                {
                mT__74(); 


                }
                break;
            case 39 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:238: T__75
                {
                mT__75(); 


                }
                break;
            case 40 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:244: T__76
                {
                mT__76(); 


                }
                break;
            case 41 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:250: T__77
                {
                mT__77(); 


                }
                break;
            case 42 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:256: T__78
                {
                mT__78(); 


                }
                break;
            case 43 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:262: T__79
                {
                mT__79(); 


                }
                break;
            case 44 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:268: T__80
                {
                mT__80(); 


                }
                break;
            case 45 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:274: T__81
                {
                mT__81(); 


                }
                break;
            case 46 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:280: T__82
                {
                mT__82(); 


                }
                break;
            case 47 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:286: T__83
                {
                mT__83(); 


                }
                break;
            case 48 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:292: T__84
                {
                mT__84(); 


                }
                break;
            case 49 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:298: T__85
                {
                mT__85(); 


                }
                break;
            case 50 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:304: T__86
                {
                mT__86(); 


                }
                break;
            case 51 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:310: T__87
                {
                mT__87(); 


                }
                break;
            case 52 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:316: T__88
                {
                mT__88(); 


                }
                break;
            case 53 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:322: T__89
                {
                mT__89(); 


                }
                break;
            case 54 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:328: T__90
                {
                mT__90(); 


                }
                break;
            case 55 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:334: T__91
                {
                mT__91(); 


                }
                break;
            case 56 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:340: T__92
                {
                mT__92(); 


                }
                break;
            case 57 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:346: T__93
                {
                mT__93(); 


                }
                break;
            case 58 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:352: T__94
                {
                mT__94(); 


                }
                break;
            case 59 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:358: T__95
                {
                mT__95(); 


                }
                break;
            case 60 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:364: BOOLEAN
                {
                mBOOLEAN(); 


                }
                break;
            case 61 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:372: NULL
                {
                mNULL(); 


                }
                break;
            case 62 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:377: IDENTIFIER
                {
                mIDENTIFIER(); 


                }
                break;
            case 63 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:388: STRING
                {
                mSTRING(); 


                }
                break;
            case 64 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:395: REAL
                {
                mREAL(); 


                }
                break;
            case 65 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:400: INTEGER
                {
                mINTEGER(); 


                }
                break;
            case 66 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:408: WHITE_SPACE
                {
                mWHITE_SPACE(); 


                }
                break;
            case 67 :
                // C:\\Documents and Settings\\Martin\\My Documents\\NetBeansProjects\\its-runtime\\compiler\\src\\mi\\run\\ctalk\\CTalk.g:1:420: COMMENT
                {
                mCOMMENT(); 


                }
                break;

        }

    }


    protected DFA21 dfa21 = new DFA21(this);
    protected DFA24 dfa24 = new DFA24(this);
    protected DFA39 dfa39 = new DFA39(this);
    static final String DFA21_eotS =
        "\4\uffff";
    static final String DFA21_eofS =
        "\4\uffff";
    static final String DFA21_minS =
        "\2\56\2\uffff";
    static final String DFA21_maxS =
        "\1\71\1\145\2\uffff";
    static final String DFA21_acceptS =
        "\2\uffff\1\1\1\2";
    static final String DFA21_specialS =
        "\4\uffff}>";
    static final String[] DFA21_transitionS = {
            "\1\2\1\uffff\12\1",
            "\1\2\1\uffff\12\1\13\uffff\1\3\37\uffff\1\3",
            "",
            ""
    };

    static final short[] DFA21_eot = DFA.unpackEncodedString(DFA21_eotS);
    static final short[] DFA21_eof = DFA.unpackEncodedString(DFA21_eofS);
    static final char[] DFA21_min = DFA.unpackEncodedStringToUnsignedChars(DFA21_minS);
    static final char[] DFA21_max = DFA.unpackEncodedStringToUnsignedChars(DFA21_maxS);
    static final short[] DFA21_accept = DFA.unpackEncodedString(DFA21_acceptS);
    static final short[] DFA21_special = DFA.unpackEncodedString(DFA21_specialS);
    static final short[][] DFA21_transition;

    static {
        int numStates = DFA21_transitionS.length;
        DFA21_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA21_transition[i] = DFA.unpackEncodedString(DFA21_transitionS[i]);
        }
    }

    class DFA21 extends DFA {

        public DFA21(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 21;
            this.eot = DFA21_eot;
            this.eof = DFA21_eof;
            this.min = DFA21_min;
            this.max = DFA21_max;
            this.accept = DFA21_accept;
            this.special = DFA21_special;
            this.transition = DFA21_transition;
        }
        public String getDescription() {
            return "590:7: ( ( FRACTIONAL_CONSTANT ( EXPONENT_PART )? ( FLOATING_SUFFIX )? ) | ( DIGIT_SEQUENCE EXPONENT_PART ( FLOATING_SUFFIX )? ) )";
        }
    }
    static final String DFA24_eotS =
        "\3\uffff\1\4\1\uffff";
    static final String DFA24_eofS =
        "\5\uffff";
    static final String DFA24_minS =
        "\2\56\1\uffff\1\60\1\uffff";
    static final String DFA24_maxS =
        "\2\71\1\uffff\1\71\1\uffff";
    static final String DFA24_acceptS =
        "\2\uffff\1\1\1\uffff\1\2";
    static final String DFA24_specialS =
        "\5\uffff}>";
    static final String[] DFA24_transitionS = {
            "\1\2\1\uffff\12\1",
            "\1\3\1\uffff\12\1",
            "",
            "\12\2",
            ""
    };

    static final short[] DFA24_eot = DFA.unpackEncodedString(DFA24_eotS);
    static final short[] DFA24_eof = DFA.unpackEncodedString(DFA24_eofS);
    static final char[] DFA24_min = DFA.unpackEncodedStringToUnsignedChars(DFA24_minS);
    static final char[] DFA24_max = DFA.unpackEncodedStringToUnsignedChars(DFA24_maxS);
    static final short[] DFA24_accept = DFA.unpackEncodedString(DFA24_acceptS);
    static final short[] DFA24_special = DFA.unpackEncodedString(DFA24_specialS);
    static final short[][] DFA24_transition;

    static {
        int numStates = DFA24_transitionS.length;
        DFA24_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA24_transition[i] = DFA.unpackEncodedString(DFA24_transitionS[i]);
        }
    }

    class DFA24 extends DFA {

        public DFA24(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 24;
            this.eot = DFA24_eot;
            this.eof = DFA24_eof;
            this.min = DFA24_min;
            this.max = DFA24_max;
            this.accept = DFA24_accept;
            this.special = DFA24_special;
            this.transition = DFA24_transition;
        }
        public String getDescription() {
            return "592:31: ( ( ( DIGIT_SEQUENCE )? '.' DIGIT_SEQUENCE ) | ( DIGIT_SEQUENCE '.' ) )";
        }
    }
    static final String DFA39_eotS =
        "\1\uffff\1\55\1\57\1\62\2\uffff\1\64\1\67\1\uffff\1\72\1\73\1\77"+
        "\1\uffff\1\102\1\104\1\107\3\46\2\uffff\1\114\13\46\1\uffff\1\136"+
        "\2\uffff\1\46\1\uffff\1\46\1\uffff\2\140\25\uffff\1\144\5\uffff"+
        "\1\146\1\uffff\3\46\2\uffff\3\46\1\156\3\46\1\162\7\46\3\uffff\1"+
        "\46\1\uffff\2\140\4\uffff\7\46\1\uffff\1\46\1\u0083\1\46\1\uffff"+
        "\1\u0085\1\u0086\7\46\1\u008e\1\46\1\u0090\3\46\1\u0094\1\uffff"+
        "\1\46\2\uffff\1\u0096\2\46\1\u0099\1\46\1\u009b\1\u009c\1\uffff"+
        "\1\46\1\uffff\1\u009e\2\46\1\uffff\1\u009b\1\uffff\2\46\1\uffff"+
        "\1\u00a3\2\uffff\1\u00a4\1\uffff\1\46\1\u00a6\1\u00a7\1\u00a8\2"+
        "\uffff\1\46\3\uffff\1\u00aa\1\uffff";
    static final String DFA39_eofS =
        "\u00ab\uffff";
    static final String DFA39_minS =
        "\1\11\2\75\1\46\2\uffff\1\75\1\53\1\uffff\1\55\1\60\1\52\1\uffff"+
        "\1\74\2\75\1\162\1\151\1\164\2\uffff\1\75\3\157\1\154\1\141\1\146"+
        "\2\145\1\164\1\157\1\150\1\uffff\1\75\2\uffff\1\162\1\uffff\1\42"+
        "\1\uffff\2\56\25\uffff\1\75\5\uffff\1\75\1\uffff\1\162\1\154\1\162"+
        "\2\uffff\1\157\1\145\1\156\1\60\1\163\1\162\1\154\1\60\1\164\1\167"+
        "\1\154\1\164\1\162\2\151\3\uffff\1\165\1\uffff\2\56\4\uffff\1\141"+
        "\1\145\1\151\1\154\1\141\1\164\1\142\1\uffff\1\145\1\60\1\163\1"+
        "\uffff\2\60\1\154\2\165\1\144\1\154\1\145\1\171\1\60\1\156\1\60"+
        "\1\153\1\151\1\154\1\60\1\uffff\1\145\2\uffff\1\60\1\162\1\143\1"+
        "\60\1\145\2\60\1\uffff\1\147\1\uffff\1\60\1\156\1\145\1\uffff\1"+
        "\60\1\uffff\1\156\1\164\1\uffff\1\60\2\uffff\1\60\1\uffff\1\165"+
        "\3\60\2\uffff\1\145\3\uffff\1\60\1\uffff";
    static final String DFA39_maxS =
        "\1\176\3\75\2\uffff\2\75\1\uffff\1\75\1\71\1\75\1\uffff\2\75\1\76"+
        "\1\162\1\151\1\164\2\uffff\1\75\1\162\2\157\1\154\1\157\1\156\1"+
        "\165\1\145\1\164\1\157\1\150\1\uffff\1\174\2\uffff\1\162\1\uffff"+
        "\1\47\1\uffff\2\145\25\uffff\1\75\5\uffff\1\75\1\uffff\1\162\1\154"+
        "\1\162\2\uffff\1\157\1\145\1\156\1\172\1\163\1\162\1\154\1\172\1"+
        "\164\1\167\1\154\1\164\1\162\2\151\3\uffff\1\165\1\uffff\2\145\4"+
        "\uffff\1\141\1\145\1\151\1\154\1\141\1\164\1\142\1\uffff\1\145\1"+
        "\172\1\163\1\uffff\2\172\1\154\2\165\1\144\1\154\1\145\1\171\1\172"+
        "\1\156\1\172\1\153\1\151\1\154\1\172\1\uffff\1\145\2\uffff\1\172"+
        "\1\162\1\143\1\172\1\145\2\172\1\uffff\1\147\1\uffff\1\172\1\156"+
        "\1\145\1\uffff\1\172\1\uffff\1\156\1\164\1\uffff\1\172\2\uffff\1"+
        "\172\1\uffff\1\165\3\172\2\uffff\1\145\3\uffff\1\172\1\uffff";
    static final String DFA39_acceptS =
        "\4\uffff\1\10\1\11\2\uffff\1\17\3\uffff\1\26\6\uffff\1\44\1\45\14"+
        "\uffff\1\66\1\uffff\1\72\1\73\1\uffff\1\76\1\uffff\1\77\2\uffff"+
        "\1\102\1\2\1\1\1\4\1\3\1\5\1\7\1\6\1\13\1\12\1\15\1\16\1\14\1\21"+
        "\1\22\1\20\1\23\1\100\1\25\1\103\1\24\1\uffff\1\32\1\27\1\34\1\33"+
        "\1\36\1\uffff\1\35\3\uffff\1\47\1\46\17\uffff\1\70\1\71\1\67\1\uffff"+
        "\1\101\2\uffff\1\31\1\30\1\40\1\37\7\uffff\1\53\3\uffff\1\57\20"+
        "\uffff\1\56\1\uffff\1\60\1\61\7\uffff\1\42\1\uffff\1\50\3\uffff"+
        "\1\55\1\uffff\1\75\2\uffff\1\64\1\uffff\1\74\1\41\1\uffff\1\51\4"+
        "\uffff\1\65\1\43\1\uffff\1\54\1\62\1\63\1\uffff\1\52";
    static final String DFA39_specialS =
        "\u00ab\uffff}>";
    static final String[] DFA39_transitionS = {
            "\2\53\2\uffff\1\53\22\uffff\1\53\1\1\1\50\2\uffff\1\2\1\3\1"+
            "\50\1\4\1\5\1\6\1\7\1\10\1\11\1\12\1\13\1\52\11\51\1\uffff\1"+
            "\14\1\15\1\16\1\17\2\uffff\1\20\4\46\1\21\5\46\1\47\6\46\1\22"+
            "\7\46\1\23\1\46\1\24\1\25\1\46\1\uffff\1\46\1\26\1\27\1\30\1"+
            "\31\1\32\2\46\1\33\4\46\1\34\3\46\1\35\1\36\1\45\1\46\1\37\1"+
            "\40\3\46\1\41\1\42\1\43\1\44",
            "\1\54",
            "\1\56",
            "\1\60\26\uffff\1\61",
            "",
            "",
            "\1\63",
            "\1\65\21\uffff\1\66",
            "",
            "\1\70\17\uffff\1\71",
            "\12\74",
            "\1\76\4\uffff\1\76\15\uffff\1\75",
            "",
            "\1\100\1\101",
            "\1\103",
            "\1\105\1\106",
            "\1\110",
            "\1\111",
            "\1\112",
            "",
            "",
            "\1\113",
            "\1\115\2\uffff\1\116",
            "\1\117",
            "\1\120",
            "\1\121",
            "\1\123\15\uffff\1\122",
            "\1\124\7\uffff\1\125",
            "\1\126\17\uffff\1\127",
            "\1\130",
            "\1\131",
            "\1\132",
            "\1\133",
            "",
            "\1\134\76\uffff\1\135",
            "",
            "",
            "\1\137",
            "",
            "\1\50\4\uffff\1\50",
            "",
            "\1\74\1\uffff\12\141\13\uffff\1\74\37\uffff\1\74",
            "\1\74\1\uffff\10\142\2\74\13\uffff\1\74\37\uffff\1\74",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\143",
            "",
            "",
            "",
            "",
            "",
            "\1\145",
            "",
            "\1\147",
            "\1\150",
            "\1\151",
            "",
            "",
            "\1\152",
            "\1\153",
            "\1\154",
            "\12\46\7\uffff\32\46\1\uffff\1\46\2\uffff\1\46\1\uffff\24\46"+
            "\1\155\5\46",
            "\1\157",
            "\1\160",
            "\1\161",
            "\12\46\7\uffff\32\46\1\uffff\1\46\2\uffff\1\46\1\uffff\32\46",
            "\1\163",
            "\1\164",
            "\1\165",
            "\1\166",
            "\1\167",
            "\1\170",
            "\1\171",
            "",
            "",
            "",
            "\1\172",
            "",
            "\1\74\1\uffff\12\141\13\uffff\1\74\37\uffff\1\74",
            "\1\74\1\uffff\10\142\2\74\13\uffff\1\74\37\uffff\1\74",
            "",
            "",
            "",
            "",
            "\1\173",
            "\1\174",
            "\1\175",
            "\1\176",
            "\1\177",
            "\1\u0080",
            "\1\u0081",
            "",
            "\1\u0082",
            "\12\46\7\uffff\32\46\1\uffff\1\46\2\uffff\1\46\1\uffff\32\46",
            "\1\u0084",
            "",
            "\12\46\7\uffff\32\46\1\uffff\1\46\2\uffff\1\46\1\uffff\32\46",
            "\12\46\7\uffff\32\46\1\uffff\1\46\2\uffff\1\46\1\uffff\32\46",
            "\1\u0087",
            "\1\u0088",
            "\1\u0089",
            "\1\u008a",
            "\1\u008b",
            "\1\u008c",
            "\1\u008d",
            "\12\46\7\uffff\32\46\1\uffff\1\46\2\uffff\1\46\1\uffff\32\46",
            "\1\u008f",
            "\12\46\7\uffff\32\46\1\uffff\1\46\2\uffff\1\46\1\uffff\32\46",
            "\1\u0091",
            "\1\u0092",
            "\1\u0093",
            "\12\46\7\uffff\32\46\1\uffff\1\46\2\uffff\1\46\1\uffff\32\46",
            "",
            "\1\u0095",
            "",
            "",
            "\12\46\7\uffff\32\46\1\uffff\1\46\2\uffff\1\46\1\uffff\32\46",
            "\1\u0097",
            "\1\u0098",
            "\12\46\7\uffff\32\46\1\uffff\1\46\2\uffff\1\46\1\uffff\32\46",
            "\1\u009a",
            "\12\46\7\uffff\32\46\1\uffff\1\46\2\uffff\1\46\1\uffff\32\46",
            "\12\46\7\uffff\32\46\1\uffff\1\46\2\uffff\1\46\1\uffff\32\46",
            "",
            "\1\u009d",
            "",
            "\12\46\7\uffff\32\46\1\uffff\1\46\2\uffff\1\46\1\uffff\32\46",
            "\1\u009f",
            "\1\u00a0",
            "",
            "\12\46\7\uffff\32\46\1\uffff\1\46\2\uffff\1\46\1\uffff\32\46",
            "",
            "\1\u00a1",
            "\1\u00a2",
            "",
            "\12\46\7\uffff\32\46\1\uffff\1\46\2\uffff\1\46\1\uffff\32\46",
            "",
            "",
            "\12\46\7\uffff\32\46\1\uffff\1\46\2\uffff\1\46\1\uffff\32\46",
            "",
            "\1\u00a5",
            "\12\46\7\uffff\32\46\1\uffff\1\46\2\uffff\1\46\1\uffff\32\46",
            "\12\46\7\uffff\32\46\1\uffff\1\46\2\uffff\1\46\1\uffff\32\46",
            "\12\46\7\uffff\32\46\1\uffff\1\46\2\uffff\1\46\1\uffff\32\46",
            "",
            "",
            "\1\u00a9",
            "",
            "",
            "",
            "\12\46\7\uffff\32\46\1\uffff\1\46\2\uffff\1\46\1\uffff\32\46",
            ""
    };

    static final short[] DFA39_eot = DFA.unpackEncodedString(DFA39_eotS);
    static final short[] DFA39_eof = DFA.unpackEncodedString(DFA39_eofS);
    static final char[] DFA39_min = DFA.unpackEncodedStringToUnsignedChars(DFA39_minS);
    static final char[] DFA39_max = DFA.unpackEncodedStringToUnsignedChars(DFA39_maxS);
    static final short[] DFA39_accept = DFA.unpackEncodedString(DFA39_acceptS);
    static final short[] DFA39_special = DFA.unpackEncodedString(DFA39_specialS);
    static final short[][] DFA39_transition;

    static {
        int numStates = DFA39_transitionS.length;
        DFA39_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA39_transition[i] = DFA.unpackEncodedString(DFA39_transitionS[i]);
        }
    }

    class DFA39 extends DFA {

        public DFA39(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 39;
            this.eot = DFA39_eot;
            this.eof = DFA39_eof;
            this.min = DFA39_min;
            this.max = DFA39_max;
            this.accept = DFA39_accept;
            this.special = DFA39_special;
            this.transition = DFA39_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | T__68 | T__69 | T__70 | T__71 | T__72 | T__73 | T__74 | T__75 | T__76 | T__77 | T__78 | T__79 | T__80 | T__81 | T__82 | T__83 | T__84 | T__85 | T__86 | T__87 | T__88 | T__89 | T__90 | T__91 | T__92 | T__93 | T__94 | T__95 | BOOLEAN | NULL | IDENTIFIER | STRING | REAL | INTEGER | WHITE_SPACE | COMMENT );";
        }
    }
 

}