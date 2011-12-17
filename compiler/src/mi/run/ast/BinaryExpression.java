package mi.run.ast;

import mi.run.bytecode.ArithmeticInstr;
import mi.run.bytecode.Code;
import mi.run.bytecode.Instruction;
import mi.run.bytecode.LoadStoreInstr;
import mi.run.semantic.Functions;
import mi.run.semantic.TypeCast;
import mi.run.semantic.Variables;

public class BinaryExpression extends Expression
{
    public int operator;
    public Expression leftOperand;
    public Expression rightOperand;
    
    public BinaryExpression(int op, Expression left, Expression right)
    {
        operator = op;
        leftOperand = left;
        rightOperand = right;
    }

    @Override
    public String toString()
    {
        return "(BinaryExpression " + operator + " " + leftOperand + " " + rightOperand + ")";
    }
    
    @Override
    public Node optimize() throws Exception
    {
        leftOperand  = (Expression)leftOperand.optimize();
        rightOperand = (Expression)rightOperand.optimize();
        //
        // left & right operands have to be atoms
        if(((leftOperand instanceof StringAtom ) || (leftOperand instanceof RealAtom) ||
           (leftOperand instanceof IntegerAtom) || (leftOperand instanceof BooleanAtom) || (leftOperand instanceof NullAtom)) &&
           ((rightOperand instanceof StringAtom ) || (rightOperand instanceof RealAtom) ||
           (rightOperand instanceof IntegerAtom) || (rightOperand instanceof BooleanAtom) || (rightOperand instanceof NullAtom)))
        {
            Atom atom = TypeCast.builtInArithmetic(operator, (Atom)leftOperand, (Atom)rightOperand);  // replace this by pre-computed node
            atom.functionName = functionName;
            return atom;
        }
        return this;
    }

    @Override
    public Instruction genByteCode()
    {
        if(rightOperand instanceof NewExpression)
            return rightOperand.genByteCode();
        //
        Instruction stream = rightOperand.genByteCode();
        Instruction first = stream.first();
        stream = stream.last().append(leftOperand.genByteCode());
        if(Operator.isAssign(operator))
            resultVariable = ((Variable)leftOperand).resultVariable;
        else if(Operator.isConditional(operator))
            resultVariable = Variables.addVar(functionName, "tmp", new DataType(DataType.BOOL));
        else
            resultVariable = Variables.addVar(functionName, "tmp", new DataType(leftOperand.exprDataType));
        //
        if(operator == Operator.ASN)
            stream = stream.last().append(new LoadStoreInstr(Code.ST, resultVariable, rightOperand.resultVariable));
        else
            stream = stream.last().append(new ArithmeticInstr(Code.getByOperator(operator, false), resultVariable, leftOperand.resultVariable, rightOperand.resultVariable));
        return first;
    }

    @Override
    public void semanticCheck() throws Exception
    {
        functionName = Functions.actualFunction;
        //
        leftOperand.semanticCheck();
        rightOperand.semanticCheck();
        //
        if(Operator.isAssign(operator))
        {   // left must be variable!
            if(!(leftOperand instanceof Variable))
                throw new Exception("SEMANTIC ERROR: An assigning expression must have variable as left operand!");
        }
        //
        exprDataType = leftOperand.evalDatatype();
        if((operator == Operator.ASN) && ((exprDataType == DataType.ARRAY) || (exprDataType == DataType.STRING) || (exprDataType == DataType.STRUCTURE)))
        {
            int type = rightOperand.evalDatatype();
            if((type != DataType.NEW_EXPR) && (type != DataType.NULL))
                exprDataType = DataType.INVALID;
            else if((type == DataType.NEW_EXPR) && ((exprDataType == DataType.ARRAY) || (exprDataType == DataType.STRING)))
            {
                if(((NewExpression)rightOperand).count == null)
                    throw new Exception("SEMANTIC ERROR: Array and String must be initialized by operator new[] (not only new)!");
            }
        }
        else
            exprDataType = DataType.INVALID;
        //
        if(exprDataType == DataType.INVALID)
        {
            if((exprDataType = leftOperand.evalDatatype()) != rightOperand.evalDatatype())
                throw new Exception("SEMANTIC ERROR: both operands of BinaryExpression must be of the same type!\n" + toString());
            if(Operator.isRelational(operator))
                exprDataType = DataType.BOOL;
        }
    }

    @Override
    public int evalDatatype()
    {
        if(exprDataType == DataType.INVALID)
        {
            exprDataType = leftOperand.evalDatatype();
            if(Operator.isRelational(operator))
                exprDataType = DataType.BOOL;
        }
        return exprDataType;
    }
}
