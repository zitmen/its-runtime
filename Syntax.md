# Syntax rules #
### ANTLR's LL(`*`) parser grammar ###
```
/*------------------------------------------------------------------
 * PARSER RULES
 *------------------------------------------------------------------*/
program: (funcDef | structDef)* EOF
funcDef: dataType IDENTIFIER '(' paramsList? ')' blockStmt
paramsList: dataType IDENTIFIER (',' dataType IDENTIFIER)*
stmt: ';' | declaration ';' | expr ';' | blockStmt | whileStmt | doWhileStmt | forStmt | ifStmt | breakStmt | continueStmt | returnStmt
dataType: 'Array' '<' dataType '>' | 'int' | 'double' | 'File' | 'String' | IDENTIFIER | 'void'
declaration: dataType IDENTIFIER ('=' expr)? (',' IDENTIFIER ('=' expr)? )*
structDef: 'struct' IDENTIFIER '{' declaration+ '}'
whileStmt: 'while' '(' condition ')' stmt
doWhileStmt: 'do' stmt 'while' '(' condition ')' ';'
forStmt: 'for' '(' (expr|declaration)? ';' expr? ';' expr? ')' stmt
ifStmt:	'if' '(' condition ')' stmt ( 'else' stmt )?
condition: expr
breakStmt: 'break' ';'
continueStmt: 'continue' ';'
returnStmt: 'return' expr? ';'
blockStmt: '{' (stmt)* '}'
expr: assignExpr
assignExpr: logOrExpr (assignOp logOrExpr)*
logOrExpr: logAndExpr ('||' logAndExpr)*
logAndExpr: bitOrExpr ('&&' bitOrExpr)*
bitOrExpr: bitXorExpr ('|' bitXorExpr)*
bitXorExpr: bitAndExpr ('^' bitAndExpr)*
bitAndExpr: eqExpr ('&' eqExpr)*
eqExpr:	relExpr (eqOp relExpr)*
relExpr: shiftExpr (relOp shiftExpr)*
shiftExpr: addExpr (shiftOp addExpr)*
addExpr: mulExpr (addOp mulExpr)*
mulExpr: unaryExpr (mulOp unaryExpr)*
unaryExpr: (unaryOp)? postfixExpr
postfixExpr: atom (postfixOp)?
atom: literal |	'(' expr ')' |	variable ('[' expr ']')* | funcCall | newExpr
newExpr: 'new' ('[' expr ']')?
literal: STRING | INTEGER | REAL | BOOLEAN | NULL
variable: IDENTIFIER
funcCall: IDENTIFIER '(' exprList? ')'
exprList: expr (',' expr)*
eqOp: '==' | '!='
relOp: '<' | '>' | '<=' | '>='
shiftOp: '<<' | '>>'
addOp: '+' | '-'
mulOp: '*' | '/' | '%'
assignOp: '+=' | '-=' | '%=' | '/=' | '*=' | '|=' | '&=' | '^=' | '>>=' | '<<=' | '='
unaryOp: '~' | '!' | '-' | '+'
postfixOp: '++' | '--'

/*------------------------------------------------------------------
 * LEXER RULES
 *------------------------------------------------------------------*/
BOOLEAN: 'true' | 'false';
NULL: 'null';

IDENTIFIER: NONDIGIT ( NONDIGIT | DIGIT )* ;
fragment NONDIGIT: ( UNIVERSAL_CHARACTER_NAME | '_' | 'a'..'z' | 'A'..'Z' ) ;
fragment DIGIT: '0'..'9' ;
fragment UNIVERSAL_CHARACTER_NAME: ( ( '\\u' HEX_QUAD ) | ( '\\U' HEX_QUAD HEX_QUAD ) ) ;
fragment HEX_QUAD: HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT ;
fragment HEX_DIGIT: ( '0'..'9' | 'a'..'f' | 'A'..'F' ) ;
fragment OCTAL_DIGIT: '0'..'7' ;

STRING: ( ( ( 'L' )? '\"' ( S_CHAR_SEQUENCE )? '\"' ) | ( ( 'L' )? '\'' ( S_CHAR_SEQUENCE )? '\'' ) ) ;
fragment SIMPLE_ESCAPE_SEQUENCE: ( '\\\'' | '\\\"' | '\\\?' | '\\\\' | '\\a' | '\\b' | '\\f' | '\\n' | '\\r' | '\\t' | '\\v' ) ;
fragment OCTAL_ESCAPE_SEQUENCE: '\\' OCTAL_DIGIT ( OCTAL_DIGIT OCTAL_DIGIT? )? ;
fragment HEXADECIMAL_ESCAPE_SEQUENCE: '\\x' ( HEX_DIGIT )+ ;
fragment ESCAPE_SEQUENCE: ( SIMPLE_ESCAPE_SEQUENCE | OCTAL_ESCAPE_SEQUENCE | HEXADECIMAL_ESCAPE_SEQUENCE ) ;
fragment S_CHAR: ( ( NOT_STR_CHAR )+ | ESCAPE_SEQUENCE | UNIVERSAL_CHARACTER_NAME ) ;
fragment S_CHAR_SEQUENCE: ( S_CHAR )+ ;
fragment NOT_STR_CHAR: ~( '\"' | '\'' | '\\' | '\n' | '\r' ) ;


REAL: ( ( FRACTIONAL_CONSTANT ( EXPONENT_PART )? ( FLOATING_SUFFIX )? ) | ( DIGIT_SEQUENCE EXPONENT_PART ( FLOATING_SUFFIX )? ) ) ;
fragment EXPONENT_PART: ( 'e' | 'E' ) ( SIGN )? DIGIT_SEQUENCE ;
fragment FRACTIONAL_CONSTANT: ( ( ( DIGIT_SEQUENCE )? '.' DIGIT_SEQUENCE ) | ( DIGIT_SEQUENCE '.' ) ) ;
fragment DIGIT_SEQUENCE: ( DIGIT )+ ;
fragment SIGN: ( '+' | '-' ) ;
fragment FLOATING_SUFFIX: ( 'f' | 'l' | 'F' | 'L' ) ;

INTEGER: ( DECIMAL_LITERAL | OCTAL_LITERAL | HEXADECIMAL_LITERAL ) ( INTEGER_SUFFIX )? ;
fragment INTEGER_SUFFIX: ( UNSIGNED_SUFFIX ( LONG_SUFFIX )? | LONG_SUFFIX ( UNSIGNED_SUFFIX )? ) ;
fragment DECIMAL_LITERAL: NONZERO_DIGIT ( DIGIT )* ;
fragment OCTAL_LITERAL: '0' ( OCTAL_DIGIT )* ;
fragment HEXADECIMAL_LITERAL: ( '0x' | '0X' ) ( HEX_DIGIT )+ ;
fragment NONZERO_DIGIT: ( '1'..'9' ) ;
fragment UNSIGNED_SUFFIX: ( 'u' | 'U' ) ;
fragment LONG_SUFFIX: ( 'l' | 'L' ) ;

WHITE_SPACE: ( ' ' | '\t' | '\n' | '\r' )+ { $channel = HIDDEN; } ;

COMMENT: ( ( '/*' (options {greedy=false;} : . )* '*/' ) | ( '//' ( ~( '\n' | '\r' ) )* ) )  { $channel = HIDDEN; } ;

```