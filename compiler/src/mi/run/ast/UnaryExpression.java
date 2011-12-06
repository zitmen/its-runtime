package mi.run.ast;

import mi.run.bytecode.Code;
import mi.run.bytecode.Instruction;
import mi.run.bytecode.LoadConstInstr;
import mi.run.semantic.TypeCast;

public class UnaryExpression extends Atom
{
    public int operator;
    public Expression operand;
    public boolean prefix;
    
    public UnaryExpression(int pre, Expression op, int post)
    {
        if(pre != Operator.UNKNOWN)
        {
            prefix = true;
            operator = pre;
        }
        else
        {
            prefix = false;
            operator = post;
        }
        operand = op;
    }
    
    @Override
    public String toString()
    {
        if(prefix)
            return "(UnaryExpression " + operator + " " + operand + " null)";
        else
            return "(UnaryExpression null " + operand + " " + operator + ")";
    }
    
    @Override
    public void semanticCheck() throws Exception
    {
        if((operator != Operator.NOT) && (operator != Operator.NEG) && (operator != Operator.MINUS) &&
           (operator != Operator.DEC) && (operator != Operator.INC) && (operator != Operator.PLUS))
            throw new Exception("Operator '" + operator + "' is not supported!");
        //
        operand.semanticCheck();
    }

    @Override
    public Node optimize() throws Exception
    {
        operand = (Expression)operand.optimize();
        if((operand instanceof StringAtom ) || (operand instanceof RealAtom) ||
           (operand instanceof IntegerAtom) || (operand instanceof BooleanAtom))
            return TypeCast.builtInArithmetic(operator, (Atom)operand);  // replace this by pre-computed node
        return this;
    }

    @Override
    public Instruction genByteCode()
    {
        if(operator == Operator.NOT)
        {
            Instruction stream = operand.genByteCode();
            Instruction first = stream.first();
            stream = stream.last().append(new Instruction(Code.NOT));
            //
            // if it is ExpressionStatement or ForStatement, the value could be removed from the stack
            if((parent instanceof ExpressionStatement) || ((parent instanceof ForStatement) && ((((ForStatement)parent).init == this) || (((ForStatement)parent).iterator == this))))
                stream = stream.last().append(new Instruction(Code.POP));
            //
            return first;
        }
        else if(operator == Operator.NEG)
        {
            Instruction stream = operand.genByteCode();
            Instruction first = stream.first();
            stream = stream.last().append(new Instruction(Code.NEG));
            //
            // if it is ExpressionStatement or ForStatement, the value could be removed from the stack
            if((parent instanceof ExpressionStatement) || ((parent instanceof ForStatement) && ((((ForStatement)parent).init == this) || (((ForStatement)parent).iterator == this))))
                stream = stream.last().append(new Instruction(Code.POP));
            //
            return first;
        }
        else if(operator == Operator.MINUS)
        {
            Instruction stream = new LoadConstInstr(0);
            Instruction first = stream;
            stream = stream.last().append(operand.genByteCode());
            stream = stream.last().append(new Instruction(Code.SUB));
            //
            // if it is ExpressionStatement or ForStatement, the value could be removed from the stack
            if((parent instanceof ExpressionStatement) || ((parent instanceof ForStatement) && ((((ForStatement)parent).init == this) || (((ForStatement)parent).iterator == this))))
                stream = stream.last().append(new Instruction(Code.POP));
            //
            return first;
        }
        else if(operator == Operator.DEC)
        {
            Instruction stream = operand.genByteCode();
            Instruction first = stream.first();
            stream = stream.last().append(new Instruction(Code.DEC));
            //
            // if it is ExpressionStatement or ForStatement, the value could be removed from the stack
            if((parent instanceof ExpressionStatement) || ((parent instanceof ForStatement) && ((((ForStatement)parent).init == this) || (((ForStatement)parent).iterator == this))))
                stream = stream.last().append(new Instruction(Code.POP));
            //
            return first;
        }
        else if(operator == Operator.PLUS)
        {
            Instruction stream = operand.genByteCode();
            Instruction first = stream.first();
            //
            // if it is ExpressionStatement or ForStatement, the value could be removed from the stack
            if((parent instanceof ExpressionStatement) || ((parent instanceof ForStatement) && ((((ForStatement)parent).init == this) || (((ForStatement)parent).iterator == this))))
                stream = stream.last().append(new Instruction(Code.POP));
            //
            return first;
        }
        else if(operator == Operator.INC)
        {
            Instruction stream = operand.genByteCode();
            Instruction first = stream.first();
            stream = stream.last().append(new Instruction(Code.INC));
            //
            // if it is ExpressionStatement or ForStatement, the value could be removed from the stack
            if((parent instanceof ExpressionStatement) || ((parent instanceof ForStatement) && ((((ForStatement)parent).init == this) || (((ForStatement)parent).iterator == this))))
                stream = stream.last().append(new Instruction(Code.POP));
            //
            return first;
        }
        return null;    // can't happen due to the previous semantic check
    }
}
