package mi.run.ast;

import mi.run.bytecode.Code;
import mi.run.bytecode.Instruction;

public class ReturnStatement extends Statement
{
    public Expression returnValue;
    
    public ReturnStatement(Expression retval)
    {
        returnValue = retval;
    }

    @Override
    public String toString()
    {
        if(returnValue != null)
            return "(ReturnStatement " + returnValue + ")";
        else
            return "(ReturnStatement)";
    }
    
    @Override
    public void semanticCheck() throws Exception
    {
        if(returnValue != null)
            returnValue.semanticCheck();
    }

    @Override
    public Node optimize() throws Exception
    {
        if(returnValue != null)
            returnValue = (Expression)returnValue.optimize();
        return this;
    }

    @Override
    public Instruction genByteCode()
    {
        Instruction stream = new Instruction(Code.NOOP);
        Instruction first = stream;
        if(returnValue != null)
        {
            stream = stream.last().append(returnValue.genByteCode()); // result is on the stack
            stream = stream.last().append(new Instruction(Code.RETV));
        }
        else
            stream = stream.last().append(new Instruction(Code.RET));
        //
        return first;
    }
}
