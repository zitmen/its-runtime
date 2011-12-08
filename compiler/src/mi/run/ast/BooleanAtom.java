package mi.run.ast;

import mi.run.bytecode.Instruction;
import mi.run.bytecode.LoadConstInstr;
import mi.run.semantic.Functions;
import mi.run.semantic.Variables;

public class BooleanAtom extends Atom
{
    public boolean value;
    
    public BooleanAtom(boolean val)
    {
        value = val;
    }
    
    @Override
    public String toString()
    {
        return "(BooleanAtom " + value + ")";
    }
    
    @Override
    public void semanticCheck()
    {
        functionName = Functions.actualFunction;
    }

    @Override
    public Node optimize()
    {
        return this;
    }

    @Override
    public Instruction genByteCode()
    {
        return new LoadConstInstr(resultVariable = Variables.addVar(functionName, "tmp", new DataType(DataType.BOOL)), value);
    }

    @Override
    public int evalDatatype()
    {
        return DataType.BOOL;
    }
}
