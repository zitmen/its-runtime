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
        operand.semanticCheck();
        //
        exprDataType = operand.evalDatatype();
        switch(operator)
        {
            case Operator.NOT:
                if(exprDataType != DataType.BOOL)
                    throw new Exception("SEMANTIC ERROR: operator '!' must be used only with operand of bool type!");
                break;
                
            case Operator.NEG:
                if(exprDataType != DataType.INTEGER)
                    throw new Exception("SEMANTIC ERROR: operator '~' must be used only with operand of int type!");
                break;
                    
            case Operator.MINUS:
                if(exprDataType != DataType.INTEGER && exprDataType != DataType.DOUBLE)
                    throw new Exception("SEMANTIC ERROR: operator '-' must be used only with operand of int type or double type!");
                break;
                        
            case Operator.PLUS:
                if(exprDataType != DataType.INTEGER && exprDataType != DataType.DOUBLE)
                    throw new Exception("SEMANTIC ERROR: operator '+' must be used only with operand of int type or double type!");
                break;
                            
            case Operator.INC:
                if(exprDataType != DataType.INTEGER)
                    throw new Exception("SEMANTIC ERROR: operator '++' must be used only with operand of int type!");
                break;
                                
            case Operator.DEC:
                if(exprDataType != DataType.INTEGER)
                    throw new Exception("SEMANTIC ERROR: operator '--' must be used only with operand of int type!");
                break;
                
            default:
                throw new Exception("Operator '" + operator + "' is not supported!");
                //break;
        }
    }

    @Override
    public Node optimize() throws Exception
    {
        operand = (Expression)operand.optimize();
        if((operand instanceof StringAtom ) || (operand instanceof RealAtom) ||
           (operand instanceof IntegerAtom) || (operand instanceof BooleanAtom) || operand instanceof NullAtom)
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

    @Override
    public int evalDatatype()
    {
        if(exprDataType == DataType.INVALID)
            return (exprDataType = operand.evalDatatype());
        else
            return exprDataType;
    }
}
