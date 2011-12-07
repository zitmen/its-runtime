package mi.run.ast;

import mi.run.bytecode.Code;
import mi.run.bytecode.Instruction;
import mi.run.bytecode.LoadConstInstr;

public class NullAtom extends Atom
{
    public NullAtom()
    {
        //
    }
    
    @Override
    public String toString()
    {
        return "(NullAtom)";
    }
    
    @Override
    public void semanticCheck()
    {
        //
    }

    @Override
    public Node optimize()
    {
        return this;
    }

    @Override
    public Instruction genByteCode()
    {
        //
        // if it is not assign statement or foor loop, the value has to be kept on the stack!!
        if(!((parent instanceof ExpressionStatement) || ((parent instanceof ForStatement) && ((((ForStatement)parent).init == this) || (((ForStatement)parent).iterator == this)))))
            return new LoadConstInstr(0);
        else
            return new Instruction(Code.NOOP);
    }
    
    @Override
    public int evalDatatype()
    {
        return DataType.NULL;
    }
}
