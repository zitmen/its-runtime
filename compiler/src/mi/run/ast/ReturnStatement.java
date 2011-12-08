package mi.run.ast;

import mi.run.bytecode.Code;
import mi.run.bytecode.Instruction;
import mi.run.bytecode.ReturnInstr;
import mi.run.semantic.Functions;

public class ReturnStatement extends Statement
{
    public Expression returnValue;
    
    public ReturnStatement(Expression retval)
    {
        returnValue = retval;
    }
    
    private FunctionDefinition getFunction()
    {
        Node node = parent;
        while(node != null)
        {
            if(node instanceof FunctionDefinition)
                return (FunctionDefinition)node;
            node = node.parent;
        }
        return null;
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
        FunctionDefinition fn = getFunction();
        if(fn == null)
            throw new Exception("SEMANTIC ERROR: return statement must not be used outside of function scope!");
        //
        if(returnValue != null)
        {
            returnValue.semanticCheck();
            if(returnValue.evalDatatype() != fn.type.type)
                throw new Exception("SEMANTIC ERROR: function '" + fn.name + "' must return declared type!");
        }
        else if(fn.type.type != DataType.VOID)
            throw new Exception("SEMANTIC ERROR: function '" + fn.name + "' must return declared type!");
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
            stream = stream.last().append(new ReturnInstr(returnValue.resultVariable));
        }
        else
            stream = stream.last().append(new ReturnInstr());
        //
        return first;
    }
}
