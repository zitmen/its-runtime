package mi.run.ast;

import mi.run.bytecode.Instruction;

public class NewExpression extends Atom
{
    public Expression count;
    
    public NewExpression()
    {
        this.count = null;
    }
    
    public NewExpression(Expression count)
    {
        this.count = count;
    }
    
    @Override
    public String toString()
    {
        return "(NewExpression " + count + ")";
    }

    @Override
    public Node optimize() throws Exception
    {
        if(count != null)
        {
            count.optimize();
        }
        return this;
    }

    @Override
    public Instruction genByteCode()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void semanticCheck() throws Exception
    {
        if(count != null)
        {
            count.semanticCheck();
            if(count.evalDatatype() != DataType.INTEGER)
                throw new Exception("SEMANTIC ERROR: argument of NEW expression must be INTEGER");
        }
    }

    @Override
    public int evalDatatype()
    {
        return DataType.NEW_EXPR;
    }
    
}
