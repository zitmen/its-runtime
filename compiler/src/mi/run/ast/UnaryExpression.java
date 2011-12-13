package mi.run.ast;

import mi.run.bytecode.ArithmeticInstr;
import mi.run.bytecode.Code;
import mi.run.bytecode.Instruction;
import mi.run.semantic.Functions;
import mi.run.semantic.TypeCast;
import mi.run.semantic.Variables;

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
        functionName = Functions.actualFunction;
        //
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
        {
            Atom atom = TypeCast.builtInArithmetic(operator, (Atom)operand);  // replace this by pre-computed node
            atom.functionName = functionName;
            return atom;
        }
        return this;
    }

    @Override
    public Instruction genByteCode()
    {
        Instruction stream = operand.genByteCode();
        Instruction first = stream.first();
        if(operator == Operator.PLUS)
        {
            resultVariable = operand.resultVariable;
            return first;
        }
        if((operator == Operator.DEC) || (operator == Operator.INC))
            stream = stream.last().append(new ArithmeticInstr(Code.getByOperator(operator, true), resultVariable = operand.resultVariable));
        else
            stream = stream.last().append(new ArithmeticInstr(Code.getByOperator(operator, true), resultVariable = Variables.addVar(functionName, "tmp", new DataType(operand.exprDataType)), operand.resultVariable));
        //
        return first;
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
