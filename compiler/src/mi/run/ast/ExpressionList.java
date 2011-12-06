package mi.run.ast;

import mi.run.bytecode.Code;
import mi.run.bytecode.Instruction;

public class ExpressionList extends Expression
{
    public java.util.ArrayList<Expression> expressions;
    
    public ExpressionList()
    {
        expressions = new java.util.ArrayList<Expression>();
    }

    @Override
    public String toString()
    {
        return "(ExpressionList " + expressions + ")";
    }
    
    @Override
    public void semanticCheck() throws Exception
    {
        for(int i = 0; i < expressions.size(); i++)
            expressions.get(i).semanticCheck();
    }

    @Override
    public Node optimize() throws Exception
    {
        for(int i = 0; i < expressions.size(); i++)
            expressions.set(i, (Expression)expressions.get(i).optimize());
        //
        return this;
    }
    
    public Instruction genByteCode(boolean reversed)
    {
        Instruction stream = new Instruction(Code.NOOP);
        Instruction first = stream.first();
        if(reversed)
        {
            for(int i = expressions.size() - 1; i >= 0; i--)
                stream = stream.last().append(expressions.get(i).genByteCode());
        }
        else
        {
            for(int i = 0, im = expressions.size(); i < im; i++)
                stream = stream.last().append(expressions.get(i).genByteCode());
        }
        //
        return first;
    }

    @Override
    public Instruction genByteCode()
    {
        return genByteCode(false);
    }
}
