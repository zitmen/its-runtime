package mi.run.ast;

import mi.run.bytecode.Instruction;

public class ExpressionStatement extends Statement
{
    public Expression expression;
    
    public ExpressionStatement(Expression expr)
    {
        expression = expr;
    }

    @Override
    public String toString()
    {
        return "(ExpressionStatement " + expression + ")";
    }
    
    @Override
    public void semanticCheck() throws Exception
    {
        expression.semanticCheck();
    }
    
    private boolean isAssign(int operator)
    {
        if(operator == Operator.ASN) return true;
        if(operator == Operator.ADD_ASN) return true;
        if(operator == Operator.SUB_ASN) return true;
        if(operator == Operator.MUL_ASN) return true;
        if(operator == Operator.DIV_ASN) return true;
        if(operator == Operator.MOD_ASN) return true;
        if(operator == Operator.OR_ASN) return true;
        if(operator == Operator.AND_ASN) return true;
        if(operator == Operator.XOR_ASN) return true;
        if(operator == Operator.LSH_ASN) return true;
        if(operator == Operator.RSH_ASN) return true;
        return false;
    }

    @Override
    public Node optimize() throws Exception
    {
        //
        expression = (Expression)expression.optimize();
        if((expression instanceof StringAtom ) || (expression instanceof RealAtom) ||
           (expression instanceof IntegerAtom) || (expression instanceof BooleanAtom) ||
           (expression instanceof UnaryExpression) || (expression instanceof ArrayConstructor) ||
           (expression instanceof Variable) ||
           ((expression instanceof BinaryExpression) && !isAssign(((BinaryExpression)expression).operator)))
        {   // f.e.: x; 3+5; i+y; 5; [0:'foo','bar':1]; ~x; --> those kind of 'statements' are useless
            return null;
        }
        return this;
    }

    @Override
    public Instruction genByteCode()
    {
        return expression.genByteCode();
    }
}
