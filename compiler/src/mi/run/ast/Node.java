package mi.run.ast;

import mi.run.bytecode.Instruction;

public abstract class Node
{
    public Node parent;
    
    @Override
    public abstract String toString();
    public abstract Node optimize() throws Exception;
    public abstract Instruction genByteCode();
    public abstract void semanticCheck() throws Exception;
}
