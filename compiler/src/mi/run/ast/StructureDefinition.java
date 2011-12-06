package mi.run.ast;

import mi.run.bytecode.Instruction;

public class StructureDefinition extends Node
{
    public String name;
    public ExpressionList declarations;
    
    public StructureDefinition(String name)
    {
        this.name = name;
        declarations = new ExpressionList();
    }
    
    @Override
    public String toString()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Node optimize() throws Exception
    {
        return this;
    }

    @Override
    public Instruction genByteCode()
    {
        return null;
    }

    @Override
    public void semanticCheck() throws Exception
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
