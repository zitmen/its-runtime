package mi.run.ast;

import mi.run.bytecode.Code;
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
            //
            // check for a non-positive value
            if(count instanceof IntegerAtom)
                if(((IntegerAtom)count).value <= 0)
                    throw new Exception("SEMANTIC ERROR: argument of NEW expression must be POSITIVE integer!");
        }
        return this;
    }

    @Override
    public Instruction genByteCode()
    {
        Instruction stream;
        if(count != null)
            stream = count.genByteCode();
        else
            stream = new Instruction(Code.NOOP);
        //
        Instruction first = stream.first();
        stream = stream.last().append(new Instruction(Code.NEW));
        return first;
    }

    @Override
    public void semanticCheck() throws Exception
    {
        if(count != null)
        {
            count.semanticCheck();
            if(count.evalDatatype() != DataType.INTEGER)
                throw new Exception("SEMANTIC ERROR: argument of NEW expression must be positive INTEGER!");
            //
            if(count instanceof IntegerAtom)
                if(((IntegerAtom)count).value <= 0)
                    throw new Exception("SEMANTIC ERROR: argument of NEW expression must be POSITIVE integer!");
        }
    }

    @Override
    public int evalDatatype()
    {
        return DataType.NEW_EXPR;
    }
    
}
