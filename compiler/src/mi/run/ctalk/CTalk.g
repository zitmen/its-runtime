grammar CTalk;

options {
	backtrack = true; 
	memoize = true;
}

@lexer::header {
	package mi.run.ctalk;
}

@header {
	package mi.run.ctalk;
	import mi.run.ast.*;
	import java.util.Stack;
}

/*------------------------------------------------------------------
 * PARSER RULES
 *------------------------------------------------------------------*/
program returns [Program prg]
	@init { $prg = new Program(); }
	:	(
			funcDef { $prg.functions.add($funcDef.fn); $funcDef.fn.parent = $prg; }
			| structDef { $prg.structures.add($structDef.struct); $structDef.struct.parent = $prg; }
		)* EOF
	;
	
funcDef returns [FunctionDefinition fn]
	:	dataType IDENTIFIER '(' paramsList? ')' blockStmt
		{ $fn = new FunctionDefinition($dataType.type, $IDENTIFIER.text, $paramsList.params, $blockStmt.stmt); if($paramsList.params != null) $paramsList.params.parent = $fn; $dataType.type.parent = $fn; $blockStmt.stmt.parent = $fn; }
	;
	
paramsList returns [ExpressionList params]
	:	type=dataType id=IDENTIFIER
		{
			$params = new ExpressionList();
			Variable var = new Variable($id.text, $type.type);
			$params.expressions.add(var);
			var.parent = $params;
		}
		( ',' type=dataType id=IDENTIFIER
			{
				Variable var = new Variable($id.text, $type.type);
				$params.expressions.add(var);
				var.parent = $params;
			}
		)*
	;
	
stmt returns [Statement stmt]
	:	';'
	|	declaration ';' { $stmt = new ExpressionStatement($declaration.decl); $declaration.decl.parent = $stmt; }
	|	expr ';'        { $stmt = new ExpressionStatement($expr.expr); $expr.expr.parent = $stmt; }
	|	blockStmt       { $stmt = $blockStmt.stmt; }
	|	whileStmt       { $stmt = $whileStmt.stmt; }
	|	doWhileStmt     { $stmt = $doWhileStmt.stmt; }
	|	forStmt         { $stmt = $forStmt.stmt; }
	|	ifStmt          { $stmt = $ifStmt.stmt; }
	|	breakStmt       { $stmt = $breakStmt.stmt; }
	|	continueStmt    { $stmt = $continueStmt.stmt; }
	|	returnStmt      { $stmt = $returnStmt.stmt; }
	;

dataType returns [DataType type]
	:	'Array' '<' arg=dataType '>' { $type = new DataType(DataType.ARRAY, $arg.type); }
	|	'int'                        { $type = new DataType(DataType.INTEGER); }
	|	'double'                     { $type = new DataType(DataType.DOUBLE); }
	|	'File'                       { $type = new DataType(DataType.FILE); }
	|	'String'                     { $type = new DataType(DataType.STRING); }
	|	IDENTIFIER					 { $type = new DataType(DataType.STRUCTURE, $IDENTIFIER.text); }
	|	'void'						 { $type = new DataType(DataType.VOID); }
	|	'bool'						 { $type = new DataType(DataType.BOOL); }
	;

declaration returns [ExpressionList decl]
	:	type=dataType id=IDENTIFIER ('=' e=expr)?
			{
				$decl = new ExpressionList();
				Variable var = new Variable($id.text, $type.type);
				if($e.expr != null)
				{
					var.value = $e.expr;
					var.value.parent = var;
				}
				var.parent = $decl;
				$decl.expressions.add(var);

			}
		(',' id=IDENTIFIER ('=' e=expr)?
	  		{
	  			Variable var = new Variable($id.text, $type.type);
				if($e.expr != null)
				{
					var.value = $e.expr;
					var.value.parent = var;
				}
				var.parent = $decl;
				$decl.expressions.add(var);
		  	}
		)*
	;

structDef returns [StructureDefinition struct]
	:	'struct' id=IDENTIFIER '{'
			{ $struct = new StructureDefinition($id.text); }
		(type=dataType var=IDENTIFIER ';'
			{ $struct.declarations.expressions.add(new Variable($var.text, $type.type)); }
		)+ '}'
	;
	
whileStmt returns [WhileStatement stmt]
	:	'while' '(' condition ')' body=stmt
		{ $stmt = new WhileStatement($condition.expr, $body.stmt); $condition.expr.parent = $stmt; $body.stmt.parent = $stmt; }
	;
	
doWhileStmt returns [DoWhileStatement stmt]
	:	'do' body=stmt 'while' '(' condition ')' ';'
		{ $stmt = new DoWhileStatement($condition.expr, $body.stmt); $condition.expr.parent = $stmt; $body.stmt.parent = $stmt; }
	;
	
forStmt returns [ForStatement stmt]
	:	'for' '(' (initE=expr|initD=declaration)? ';' cond=expr? ';' iter=expr? ')' body=stmt
			{
				$stmt = new ForStatement(($initE.expr != null ? $initE.expr : ($initD.decl != null ? $initD.decl : null)), $cond.expr, $iter.expr, $body.stmt);
				if($initE.expr != null) $initE.expr.parent = $stmt;
				else if($initD.decl != null) $initD.decl.parent = $stmt;
				if($cond.expr != null) $cond.expr.parent = $stmt;
				if($iter.expr != null) $iter.expr.parent = $stmt;
				$body.stmt.parent = $stmt;
			}
	;

ifStmt returns [IfStatement stmt]
	:	'if' '(' condition ')' true_stmt=stmt ( 'else' false_stmt=stmt )?
		{ $stmt = new IfStatement($condition.expr, $true_stmt.stmt, $false_stmt.stmt); $condition.expr.parent = $stmt; $true_stmt.stmt.parent = $stmt; if($false_stmt.stmt != null) $false_stmt.stmt.parent = $stmt; }
	;

condition returns [Expression expr]
	:	e=expr
		{ $expr = $e.expr; }
	;

breakStmt returns [BreakStatement stmt]
	:	'break' ';'
		{ stmt = new BreakStatement(); }
	;

continueStmt returns [ContinueStatement stmt]
	:	'continue' ';'
		{ stmt = new ContinueStatement(); }
	;

returnStmt returns [ReturnStatement stmt]
	:	'return' expr? ';'
		{ $stmt = new ReturnStatement($expr.expr); if($expr.expr != null) $expr.expr.parent = $stmt; }
	;

blockStmt returns [BlockStatement stmt]
	@init { $stmt = new BlockStatement(); }
	:	'{' (block=stmt { $stmt.statements.add($block.stmt); $block.stmt.parent = $stmt; })* '}'
	;

expr returns [Expression expr]
	:	assignExpr
		{ $expr = $assignExpr.expr; }
	;
	
assignExpr returns [Expression expr]
	@init { Stack<BinaryExpression> stack = new Stack<BinaryExpression>(); }
	:	l=logOrExpr (op=assignOp r=logOrExpr { stack.push(new BinaryExpression(op, null, $r.expr)); })*
		{
			if(stack.size() > 0)
			{
				while(stack.size() > 1)
				{
					$expr = stack.pop();
					((BinaryExpression)$expr).leftOperand = stack.peek().rightOperand;
					stack.peek().rightOperand = $expr;
					((BinaryExpression)$expr).leftOperand.parent = $expr;
					((BinaryExpression)$expr).rightOperand.parent = $expr;
				}
				$expr = stack.pop();
				((BinaryExpression)$expr).leftOperand = $l.expr;
				((BinaryExpression)$expr).leftOperand.parent = $expr;
				((BinaryExpression)$expr).rightOperand.parent = $expr;
			}
			else
				$expr = $l.expr;
		}
	;
	
logOrExpr returns [Expression expr]
	@init { Stack<BinaryExpression> stack = new Stack<BinaryExpression>(); }
	:	l=logAndExpr ('||' r=logAndExpr { stack.push(new BinaryExpression(Operator.LOG_OR, null, $r.expr)); })*
		{
			if(stack.size() > 0)
			{
				while(stack.size() > 1)
				{
					$expr = stack.pop();
					((BinaryExpression)$expr).leftOperand = stack.peek().rightOperand;
					stack.peek().rightOperand = $expr;
					((BinaryExpression)$expr).leftOperand.parent = $expr;
					((BinaryExpression)$expr).rightOperand.parent = $expr;
				}
				$expr = stack.pop();
				((BinaryExpression)$expr).leftOperand = $l.expr;
				((BinaryExpression)$expr).leftOperand.parent = $expr;
				((BinaryExpression)$expr).rightOperand.parent = $expr;
			}
			else
				$expr = $l.expr;
		}
	;
	
logAndExpr returns [Expression expr]
	@init { Stack<BinaryExpression> stack = new Stack<BinaryExpression>(); }
	:	l=bitOrExpr ('&&' r=bitOrExpr { stack.push(new BinaryExpression(Operator.LOG_AND, null, $r.expr)); })*
		{
			if(stack.size() > 0)
			{
				while(stack.size() > 1)
				{
					$expr = stack.pop();
					((BinaryExpression)$expr).leftOperand = stack.peek().rightOperand;
					stack.peek().rightOperand = $expr;
					((BinaryExpression)$expr).leftOperand.parent = $expr;
					((BinaryExpression)$expr).rightOperand.parent = $expr;
				}
				$expr = stack.pop();
				((BinaryExpression)$expr).leftOperand = $l.expr;
				((BinaryExpression)$expr).leftOperand.parent = $expr;
				((BinaryExpression)$expr).rightOperand.parent = $expr;
			}
			else
				$expr = $l.expr;
		}
	;

bitOrExpr returns [Expression expr]
	@init { Stack<BinaryExpression> stack = new Stack<BinaryExpression>(); }
	:	l=bitXorExpr ('|' r=bitXorExpr { stack.push(new BinaryExpression(Operator.OR, null, $r.expr)); })*
		{
			if(stack.size() > 0)
			{
				while(stack.size() > 1)
				{
					$expr = stack.pop();
					((BinaryExpression)$expr).leftOperand = stack.peek().rightOperand;
					stack.peek().rightOperand = $expr;
					((BinaryExpression)$expr).leftOperand.parent = $expr;
					((BinaryExpression)$expr).rightOperand.parent = $expr;
				}
				$expr = stack.pop();
				((BinaryExpression)$expr).leftOperand = $l.expr;
				((BinaryExpression)$expr).leftOperand.parent = $expr;
				((BinaryExpression)$expr).rightOperand.parent = $expr;
			}
			else
				$expr = $l.expr;
		}
	;

bitXorExpr returns [Expression expr]
	@init { Stack<BinaryExpression> stack = new Stack<BinaryExpression>(); }
	:	l=bitAndExpr ('^' r=bitAndExpr { stack.push(new BinaryExpression(Operator.XOR, null, $r.expr)); })*
		{
			if(stack.size() > 0)
			{
				while(stack.size() > 1)
				{
					$expr = stack.pop();
					((BinaryExpression)$expr).leftOperand = stack.peek().rightOperand;
					stack.peek().rightOperand = $expr;
					((BinaryExpression)$expr).leftOperand.parent = $expr;
					((BinaryExpression)$expr).rightOperand.parent = $expr;
				}
				$expr = stack.pop();
				((BinaryExpression)$expr).leftOperand = $l.expr;
				((BinaryExpression)$expr).leftOperand.parent = $expr;
				((BinaryExpression)$expr).rightOperand.parent = $expr;
			}
			else
				$expr = $l.expr;
		}
	;

bitAndExpr returns [Expression expr]
	@init { Stack<BinaryExpression> stack = new Stack<BinaryExpression>(); }
	:	l=eqExpr ('&' r=eqExpr { stack.push(new BinaryExpression(Operator.AND, null, $r.expr)); })*
		{
			if(stack.size() > 0)
			{
				while(stack.size() > 1)
				{
					$expr = stack.pop();
					((BinaryExpression)$expr).leftOperand = stack.peek().rightOperand;
					stack.peek().rightOperand = $expr;
					((BinaryExpression)$expr).leftOperand.parent = $expr;
					((BinaryExpression)$expr).rightOperand.parent = $expr;
				}
				$expr = stack.pop();
				((BinaryExpression)$expr).leftOperand = $l.expr;
				((BinaryExpression)$expr).leftOperand.parent = $expr;
				((BinaryExpression)$expr).rightOperand.parent = $expr;
			}
			else
				$expr = $l.expr;
		}
	;

eqExpr returns [Expression expr]
	@init { Stack<BinaryExpression> stack = new Stack<BinaryExpression>(); }
	:	l=relExpr (op=eqOp r=relExpr { stack.push(new BinaryExpression(op, null, $r.expr)); })*
		{
			if(stack.size() > 0)
			{
				while(stack.size() > 1)
				{
					$expr = stack.pop();
					((BinaryExpression)$expr).leftOperand = stack.peek().rightOperand;
					stack.peek().rightOperand = $expr;
					((BinaryExpression)$expr).leftOperand.parent = $expr;
					((BinaryExpression)$expr).rightOperand.parent = $expr;
				}
				$expr = stack.pop();
				((BinaryExpression)$expr).leftOperand = $l.expr;
				((BinaryExpression)$expr).leftOperand.parent = $expr;
				((BinaryExpression)$expr).rightOperand.parent = $expr;
			}
			else
				$expr = $l.expr;
		}
	;

relExpr returns [Expression expr]
	@init { Stack<BinaryExpression> stack = new Stack<BinaryExpression>(); }
	:	l=shiftExpr (op=relOp r=shiftExpr { stack.push(new BinaryExpression(op, null, $r.expr)); })*
		{
			if(stack.size() > 0)
			{
				while(stack.size() > 1)
				{
					$expr = stack.pop();
					((BinaryExpression)$expr).leftOperand = stack.peek().rightOperand;
					stack.peek().rightOperand = $expr;
					((BinaryExpression)$expr).leftOperand.parent = $expr;
					((BinaryExpression)$expr).rightOperand.parent = $expr;
				}
				$expr = stack.pop();
				((BinaryExpression)$expr).leftOperand = $l.expr;
				((BinaryExpression)$expr).leftOperand.parent = $expr;
				((BinaryExpression)$expr).rightOperand.parent = $expr;
			}
			else
				$expr = $l.expr;
		}
	;

shiftExpr returns [Expression expr]
	@init { Stack<BinaryExpression> stack = new Stack<BinaryExpression>(); }
	:	l=addExpr (op=shiftOp r=addExpr { stack.push(new BinaryExpression(op, null, $r.expr)); })*
		{
			if(stack.size() > 0)
			{
				while(stack.size() > 1)
				{
					$expr = stack.pop();
					((BinaryExpression)$expr).leftOperand = stack.peek().rightOperand;
					stack.peek().rightOperand = $expr;
					((BinaryExpression)$expr).leftOperand.parent = $expr;
					((BinaryExpression)$expr).rightOperand.parent = $expr;
				}
				$expr = stack.pop();
				((BinaryExpression)$expr).leftOperand = $l.expr;
				((BinaryExpression)$expr).leftOperand.parent = $expr;
				((BinaryExpression)$expr).rightOperand.parent = $expr;
			}
			else
				$expr = $l.expr;
		}
	;

addExpr returns [Expression expr]
	@init { Stack<BinaryExpression> stack = new Stack<BinaryExpression>(); }
	:	l=mulExpr (op=addOp r=mulExpr { stack.push(new BinaryExpression(op, null, $r.expr)); })*
		{
			if(stack.size() > 0)
			{
				while(stack.size() > 1)
				{
					$expr = stack.pop();
					((BinaryExpression)$expr).leftOperand = stack.peek().rightOperand;
					stack.peek().rightOperand = $expr;
					((BinaryExpression)$expr).leftOperand.parent = $expr;
					((BinaryExpression)$expr).rightOperand.parent = $expr;
				}
				$expr = stack.pop();
				((BinaryExpression)$expr).leftOperand = $l.expr;
				((BinaryExpression)$expr).leftOperand.parent = $expr;
				((BinaryExpression)$expr).rightOperand.parent = $expr;
			}
			else
				$expr = $l.expr;
		}
	;

mulExpr returns [Expression expr]
	@init { Stack<BinaryExpression> stack = new Stack<BinaryExpression>(); }
	:	l=unaryExpr (op=mulOp r=unaryExpr { stack.push(new BinaryExpression(op, null, $r.expr)); })*
		{
			if(stack.size() > 0)
			{
				while(stack.size() > 1)
				{
					$expr = stack.pop();
					((BinaryExpression)$expr).leftOperand = stack.peek().rightOperand;
					stack.peek().rightOperand = $expr;
					((BinaryExpression)$expr).leftOperand.parent = $expr;
					((BinaryExpression)$expr).rightOperand.parent = $expr;
				}
				$expr = stack.pop();
				((BinaryExpression)$expr).leftOperand = $l.expr;
				((BinaryExpression)$expr).leftOperand.parent = $expr;
				((BinaryExpression)$expr).rightOperand.parent = $expr;
			}
			else
				$expr = $l.expr;
		}
	;

unaryExpr returns [Expression expr]
	:	(op=unaryOp)? postfixExpr
		{
			if($op.operator != null)
			{
				$expr = new UnaryExpression((int)$unaryOp.operator, $postfixExpr.expr, Operator.UNKNOWN);
				$postfixExpr.expr.parent = $expr;
			}
			else
				$expr = $postfixExpr.expr;
		}
	;

postfixExpr returns [Expression expr]
	:	atom (op=postfixOp)?
		{
			if($op.operator != null)
			{
				$expr = new UnaryExpression(Operator.UNKNOWN, $atom.expr, (int)$postfixOp.operator);
				$atom.expr.parent = $expr;
			}
			else
				$expr = $atom.expr;
		}
	;
	
atom returns [Expression expr]
	:	literal
		{ $expr = $literal.expr; }
	|	'(' e=expr ')'
		{ $expr = $e.expr; }
	|	variable ( ('[' index=expr ']' { $variable.expr.members.add($index.expr); $index.expr.parent = $variable.expr; }) |
	               ('.' member=IDENTIFIER { $variable.expr.members.add(new StringAtom($member.text)); }) )*
		{ $expr = $variable.expr; }
	|	funcCall
		{ $expr = $funcCall.expr; }
	|	newExpr
		{ $expr = $newExpr.expr; }
	;

newExpr returns [Expression expr]
	:	'new' ('[' count=expr ']')?
		{ $expr = new NewExpression($count.expr); }
	;
		
literal returns [Expression expr]
	:	STRING  { $expr = new StringAtom($STRING.text.substring(1, $STRING.text.length() - 1)); }
	|	INTEGER { $expr = new IntegerAtom(Integer.parseInt($INTEGER.text)); }
	|	REAL    { $expr = new RealAtom(Double.parseDouble($REAL.text)); }
	|	BOOLEAN { $expr = new BooleanAtom(Boolean.parseBoolean($BOOLEAN.text)); }
	|	NULL	{ $expr = new NullAtom(); }
	;
	
variable returns [Variable expr]
	:	IDENTIFIER { $expr = new Variable($IDENTIFIER.text); }
	;
	
funcCall returns [FunctionCall expr]
	:	IDENTIFIER '(' exprList? ')'
		{ expr = new FunctionCall($IDENTIFIER.text, $exprList.exprList); if($exprList.exprList != null) $exprList.exprList.parent = $expr; }
	;
	
exprList returns [ExpressionList exprList]
	:	e=expr
		{
			$exprList = new ExpressionList();
			$exprList.expressions.add($e.expr);
			$e.expr.parent = $exprList;
		}
		( ',' e=expr
			{
				$exprList.expressions.add($e.expr);
				$e.expr.parent = $exprList;
			}
		)*
	;

eqOp returns [int operator]
	:	'==' { $operator = Operator. EQ; }
	|	'!=' { $operator = Operator.NEQ; }
	;

relOp returns [int operator]
	:	'<'  { $operator = Operator.LT ; }
	|	'>'  { $operator = Operator.GT ; }
	|	'<=' { $operator = Operator.LTE; }
	|	'>=' { $operator = Operator.GTE; }
	;

shiftOp returns [int operator]
	:	'<<' { $operator = Operator.LSH; }
	|	'>>' { $operator = Operator.RSH; }
	;

addOp returns [int operator]
	:	'+' { $operator = Operator.PLUS;  }
	|	'-' { $operator = Operator.MINUS; }
	;
	
mulOp returns [int operator]
	:	'*' { $operator = Operator.MUL; }
	|	'/' { $operator = Operator.DIV; }
	|	'%' { $operator = Operator.MOD; }
	;
	
assignOp returns [int operator]
	:	'+='  { $operator = Operator.ADD_ASN; }
	|	'-='  { $operator = Operator.SUB_ASN; }
	|	'%='  { $operator = Operator.MOD_ASN; }
	|	'/='  { $operator = Operator.DIV_ASN; }
	|	'*='  { $operator = Operator.MUL_ASN; }
	|	'|='  { $operator = Operator. OR_ASN; }
	|	'&='  { $operator = Operator.AND_ASN; }
	|	'^='  { $operator = Operator.XOR_ASN; }
	|	'>>=' { $operator = Operator.RSH_ASN; }
	|	'<<=' { $operator = Operator.LSH_ASN; }
	|	'='   { $operator = Operator.ASN; }
	;
	
unaryOp returns [Integer operator]
	:	'~' { $operator = new Integer(Operator.NEG); }
	|	'!' { $operator = new Integer(Operator.NOT); }
	|	'-' { $operator = new Integer(Operator.MINUS); }
	|	'+' { $operator = new Integer(Operator.PLUS); }
	;
	
postfixOp returns [Integer operator]
	:	'++' { $operator = new Integer(Operator.INC); }
	|	'--' { $operator = new Integer(Operator.DEC); }
	;


/*------------------------------------------------------------------
 * LEXER RULES
 *------------------------------------------------------------------*/
BOOLEAN: 'true' | 'false';

NULL:	'null';

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
