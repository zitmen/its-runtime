package mi.run.ast;

import mi.run.bytecode.Instruction;
import mi.run.bytecode.NewInstr;
import mi.run.semantic.Functions;
import mi.run.semantic.Variables;

public class NewExpression extends Atom
{
    public Expression count;
    
    public NewExpression()
    {
        this.count = new IntegerAtom(1);
    }
    
    public NewExpression(Expression count)
    {
        if(count != null)
            this.count = count;
        else
            this.count = new IntegerAtom(1);
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
        Instruction stream, first;
        stream = count.genByteCode();
        first = stream.first();
        //
        if(parent instanceof BinaryExpression)
        {
            stream = stream.last().append(((BinaryExpression)parent).leftOperand.genByteCode());
            stream = stream.last().append(new NewInstr(((BinaryExpression)parent).leftOperand.resultVariable, count.resultVariable));
        }
        else    // Variable
        {
            stream = stream.last().append(new NewInstr(((Variable)parent).resultVariable, count.resultVariable));
        }
        //
        return first;
    }

    @Override
    public void semanticCheck() throws Exception
    {
        if(parent instanceof BinaryExpression)
        {
            if(((BinaryExpression)parent).operator != Operator.ASN)
                throw new Exception("SEMANTIC ERROR: NEW expression must always be right operator of a assign expression!");
        }
        else if(!(parent instanceof Variable))
        {
            throw new Exception("SEMANTIC ERROR: NEW expression must always be right operator of a assign expression!");
        }
        //
        functionName = Functions.actualFunction;
        //
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
