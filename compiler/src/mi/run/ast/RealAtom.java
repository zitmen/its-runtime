package mi.run.ast;

import mi.run.bytecode.Code;
import mi.run.bytecode.Instruction;
import mi.run.bytecode.LoadConstInstr;
import mi.run.semantic.Functions;
import mi.run.semantic.Variables;

public class RealAtom extends Atom
{
    public double value;
    
    public RealAtom(double val)
    {
        value = val;
    }

    @Override
    public String toString()
    {
        return "(RealAtom " + value + ")";
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
        return new LoadConstInstr(resultVariable = Variables.addVar(functionName, "tmp", new DataType(DataType.DOUBLE)), value);
        /*
        //
        // if it is not assign statement or foor loop, the value has to be kept on the stack!!
        if(!((parent instanceof ExpressionStatement) || ((parent instanceof ForStatement) && ((((ForStatement)parent).init == this) || (((ForStatement)parent).iterator == this)))))
            return new LoadConstInstr(value);
        else
            return new Instruction(Code.NOOP);
        */
    }
    
    @Override
    public int evalDatatype()
    {
        return DataType.DOUBLE;
    }
}
