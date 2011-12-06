package mi.run.ast;

import mi.run.bytecode.Instruction;

public class NewExpression extends Atom
{
    public Expression count;
    
    public NewExpression(Expression count)
    {
        this.count = count;
    }
    
    @Override
    public String toString()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Node optimize() throws Exception
    {
        count.optimize();
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
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
