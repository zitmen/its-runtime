package mi.run.ast;

import mi.run.bytecode.Code;
import mi.run.bytecode.Instruction;
import mi.run.bytecode.LoadStoreInstr;
import mi.run.semantic.Functions;
import mi.run.semantic.TypeCast;

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
           (leftOperand instanceof IntegerAtom) || (leftOperand instanceof BooleanAtom)) &&
           ((rightOperand instanceof StringAtom ) || (rightOperand instanceof RealAtom) ||
           (rightOperand instanceof IntegerAtom) || (rightOperand instanceof BooleanAtom)))
        {
            return TypeCast.builtInArithmetic(operator, (Atom)leftOperand, (Atom)rightOperand);  // replace this by pre-computed node
        }
        return this;
    }

    @Override
    public Instruction genByteCode()
    {
        throw new UnsupportedOperationException("Not supported yet.");
        /*
        Variable var;
        Instruction stream, first, arr_stream;
        //
        if(operator == Operator.ASN)
        {
            stream = rightOperand.genByteCode();
            first = stream.first();
            var = (Variable)leftOperand;
            arr_stream = var.genByteCode();
            if(arr_stream.last().code == Code.LDAI) // array with non-constant indices
            {
                stream = stream.last().append(arr_stream);
                    //
                    // if it is not assign statement or foor loop, the value has to stay on the stack!!
                    if(!((parent instanceof ExpressionStatement) || ((parent instanceof ForStatement) && ((((ForStatement)parent).init == this) || (((ForStatement)parent).iterator == this)))))
                        stream.last().code = Code.STAIK;
                    else    // else simple store
                        stream.last().code = Code.STAI;
            }
            else // array with constant indices
            {
                stream = stream.last().append(new LoadStoreInstr(var.isArray ? Code.STA : Code.ST, var));
                //
                // if it is not assign statement or foor loop, the value has to stay on the stack!!
                if(!((parent instanceof ExpressionStatement) || ((parent instanceof ForStatement) && ((((ForStatement)parent).init == this) || (((ForStatement)parent).iterator == this)))))
                    stream = stream.last().append(new LoadStoreInstr(var.isArray ? Code.LDA : Code.LD, var));
            }
        }
        else if(operator == Operator.GT)
        {   // switch left and right operands
            stream = leftOperand.genByteCode();
            first = stream.first();
            stream = stream.last().append(rightOperand.genByteCode());
            //
            // (right - left) -> =0, >0, <0 --> SF iff <0
            stream = stream.last().append(new Instruction(Code.SUB));
            // replaces result on the stack by the Sign Flag
            stream = stream.last().append(new Instruction(Code.POP));
            stream = stream.last().append(new Instruction(Code.LDSF));
        }
        else if(operator == Operator.LTE)
        {   // switch left and right operands
            stream = leftOperand.genByteCode();
            first = stream.first();
            stream = stream.last().append(rightOperand.genByteCode());
            //
            // (right - left) -> =0, >0, <0 --> SF iff <0
            stream = stream.last().append(new Instruction(Code.SUB));
            // replaces result on the stack by the Sign Flag
            stream = stream.last().append(new Instruction(Code.POP));
            stream = stream.last().append(new Instruction(Code.LDNSF));
        }
        else
        {
            stream = rightOperand.genByteCode();
            first = stream.first();
            stream = stream.last().append(leftOperand.genByteCode());
            //
            if(operator == Operator.PLUS)
                stream = stream.last().append(new Instruction(Code.ADD));
            else if(operator == Operator.ADD_ASN)
            {
                stream = stream.last().append(new Instruction(Code.ADD));
                var = (Variable)leftOperand;
                arr_stream = var.genByteCode();
                if(arr_stream.last().code == Code.LDAI) // array with non-constant indices
                {
                    stream = stream.last().append(arr_stream);
                    //
                    // if it is not assign statement or foor loop, the value has to stay on the stack!!
                    if(!((parent instanceof ExpressionStatement) || ((parent instanceof ForStatement) && ((((ForStatement)parent).init == this) || (((ForStatement)parent).iterator == this)))))
                        stream.last().code = Code.STAIK;
                    else    // else simple store
                        stream.last().code = Code.STAI;
                }
                else // array with constant indices
                {
                    stream = stream.last().append(new LoadStoreInstr(var.isArray ? Code.STA : Code.ST, var));
                    //
                    // if it is not assign statement or foor loop, the value has to stay on the stack!!
                    if(!((parent instanceof ExpressionStatement) || ((parent instanceof ForStatement) && ((((ForStatement)parent).init == this) || (((ForStatement)parent).iterator == this)))))
                        stream = stream.last().append(new LoadStoreInstr(var.isArray ? Code.LDA : Code.LD, var));
                }
            }
            else if(operator == Operator.MINUS)
                stream = stream.last().append(new Instruction(Code.SUB));
            else if(operator == Operator.SUB_ASN)
            {
                stream = stream.last().append(new Instruction(Code.SUB));
                var = (Variable)leftOperand;
                arr_stream = var.genByteCode();
                if(arr_stream.last().code == Code.LDAI) // array with non-constant indices
                {
                    stream = stream.last().append(arr_stream);
                    //
                    // if it is not assign statement or foor loop, the value has to stay on the stack!!
                    if(!((parent instanceof ExpressionStatement) || ((parent instanceof ForStatement) && ((((ForStatement)parent).init == this) || (((ForStatement)parent).iterator == this)))))
                        stream.last().code = Code.STAIK;
                    else    // else simple store
                        stream.last().code = Code.STAI;
                }
                else // array with constant indices
                {
                    stream = stream.last().append(new LoadStoreInstr(var.isArray ? Code.STA : Code.ST, var));
                    //
                    // if it is not assign statement or foor loop, the value has to stay on the stack!!
                    if(!((parent instanceof ExpressionStatement) || ((parent instanceof ForStatement) && ((((ForStatement)parent).init == this) || (((ForStatement)parent).iterator == this)))))
                        stream = stream.last().append(new LoadStoreInstr(var.isArray ? Code.LDA : Code.LD, var));
                }
            }
            else if(operator == Operator.MUL)
                stream = stream.last().append(new Instruction(Code.MUL));
            else if(operator == Operator.MUL_ASN)
            {
                stream = stream.last().append(new Instruction(Code.MUL));
                var = (Variable)leftOperand;
                arr_stream = var.genByteCode();
                if(arr_stream.last().code == Code.LDAI) // array with non-constant indices
                {
                    stream = stream.last().append(arr_stream);
                    //
                    // if it is not assign statement or foor loop, the value has to stay on the stack!!
                    if(!((parent instanceof ExpressionStatement) || ((parent instanceof ForStatement) && ((((ForStatement)parent).init == this) || (((ForStatement)parent).iterator == this)))))
                        stream.last().code = Code.STAIK;
                    else    // else simple store
                        stream.last().code = Code.STAI;
                }
                else // array with constant indices
                {
                    stream = stream.last().append(new LoadStoreInstr(var.isArray ? Code.STA : Code.ST, var));
                    //
                    // if it is not assign statement or foor loop, the value has to stay on the stack!!
                    if(!((parent instanceof ExpressionStatement) || ((parent instanceof ForStatement) && ((((ForStatement)parent).init == this) || (((ForStatement)parent).iterator == this)))))
                        stream = stream.last().append(new LoadStoreInstr(var.isArray ? Code.LDA : Code.LD, var));
                }
            }
            else if(operator == Operator.DIV)
                stream = stream.last().append(new Instruction(Code.DIV));
            else if(operator == Operator.DIV_ASN)
            {
                stream = stream.last().append(new Instruction(Code.DIV));
                var = (Variable)leftOperand;
                arr_stream = var.genByteCode();
                if(arr_stream.last().code == Code.LDAI) // array with non-constant indices
                {
                    stream = stream.last().append(arr_stream);
                    //
                    // if it is not assign statement or foor loop, the value has to stay on the stack!!
                    if(!((parent instanceof ExpressionStatement) || ((parent instanceof ForStatement) && ((((ForStatement)parent).init == this) || (((ForStatement)parent).iterator == this)))))
                        stream.last().code = Code.STAIK;
                    else    // else simple store
                        stream.last().code = Code.STAI;
                }
                else // array with constant indices
                {
                    stream = stream.last().append(new LoadStoreInstr(var.isArray ? Code.STA : Code.ST, var));
                    //
                    // if it is not assign statement or foor loop, the value has to stay on the stack!!
                    if(!((parent instanceof ExpressionStatement) || ((parent instanceof ForStatement) && ((((ForStatement)parent).init == this) || (((ForStatement)parent).iterator == this)))))
                        stream = stream.last().append(new LoadStoreInstr(var.isArray ? Code.LDA : Code.LD, var));
                }
            }
            else if(operator == Operator.MOD)        
                stream = stream.last().append(new Instruction(Code.MOD));
            else if(operator == Operator.MOD_ASN)
            {
                stream = stream.last().append(new Instruction(Code.MOD));
                var = (Variable)leftOperand;
                arr_stream = var.genByteCode();
                if(arr_stream.last().code == Code.LDAI) // array with non-constant indices
                {
                    stream = stream.last().append(arr_stream);
                    //
                    // if it is not assign statement or foor loop, the value has to stay on the stack!!
                    if(!((parent instanceof ExpressionStatement) || ((parent instanceof ForStatement) && ((((ForStatement)parent).init == this) || (((ForStatement)parent).iterator == this)))))
                        stream.last().code = Code.STAIK;
                    else    // else simple store
                        stream.last().code = Code.STAI;
                }
                else // array with constant indices
                {
                    stream = stream.last().append(new LoadStoreInstr(var.isArray ? Code.STA : Code.ST, var));
                    //
                    // if it is not assign statement or foor loop, the value has to stay on the stack!!
                    if(!((parent instanceof ExpressionStatement) || ((parent instanceof ForStatement) && ((((ForStatement)parent).init == this) || (((ForStatement)parent).iterator == this)))))
                        stream = stream.last().append(new LoadStoreInstr(var.isArray ? Code.LDA : Code.LD, var));
                }
            }
            else if(operator == Operator.OR)        
                stream = stream.last().append(new Instruction(Code.OR));
            else if(operator == Operator.OR_ASN)
            {
                stream = stream.last().append(new Instruction(Code.OR));
                var = (Variable)leftOperand;
                arr_stream = var.genByteCode();
                if(arr_stream.last().code == Code.LDAI) // array with non-constant indices
                {
                    stream = stream.last().append(arr_stream);
                    //
                    // if it is not assign statement or foor loop, the value has to stay on the stack!!
                    if(!((parent instanceof ExpressionStatement) || ((parent instanceof ForStatement) && ((((ForStatement)parent).init == this) || (((ForStatement)parent).iterator == this)))))
                        stream.last().code = Code.STAIK;
                    else    // else simple store
                        stream.last().code = Code.STAI;
                }
                else // array with constant indices
                {
                    stream = stream.last().append(new LoadStoreInstr(var.isArray ? Code.STA : Code.ST, var));
                    //
                    // if it is not assign statement or foor loop, the value has to stay on the stack!!
                    if(!((parent instanceof ExpressionStatement) || ((parent instanceof ForStatement) && ((((ForStatement)parent).init == this) || (((ForStatement)parent).iterator == this)))))
                        stream = stream.last().append(new LoadStoreInstr(var.isArray ? Code.LDA : Code.LD, var));
                }
            }
            else if(operator == Operator.AND)
                stream = stream.last().append(new Instruction(Code.AND));
            else if(operator == Operator.AND_ASN)
            {
                stream = stream.last().append(new Instruction(Code.AND));
                var = (Variable)leftOperand;
                arr_stream = var.genByteCode();
                if(arr_stream.last().code == Code.LDAI) // array with non-constant indices
                {
                    stream = stream.last().append(arr_stream);
                    //
                    // if it is not assign statement or foor loop, the value has to stay on the stack!!
                    if(!((parent instanceof ExpressionStatement) || ((parent instanceof ForStatement) && ((((ForStatement)parent).init == this) || (((ForStatement)parent).iterator == this)))))
                        stream.last().code = Code.STAIK;
                    else    // else simple store
                        stream.last().code = Code.STAI;
                }
                else // array with constant indices
                {
                    stream = stream.last().append(new LoadStoreInstr(var.isArray ? Code.STA : Code.ST, var));
                    //
                    // if it is not assign statement or foor loop, the value has to stay on the stack!!
                    if(!((parent instanceof ExpressionStatement) || ((parent instanceof ForStatement) && ((((ForStatement)parent).init == this) || (((ForStatement)parent).iterator == this)))))
                        stream = stream.last().append(new LoadStoreInstr(var.isArray ? Code.LDA : Code.LD, var));
                }
            }
            else if(operator == Operator.XOR)
                stream = stream.last().append(new Instruction(Code.XOR));
            else if(operator == Operator.XOR_ASN)
            {
                stream = stream.last().append(new Instruction(Code.XOR));
                var = (Variable)leftOperand;
                arr_stream = var.genByteCode();
                if(arr_stream.last().code == Code.LDAI) // array with non-constant indices
                {
                    stream = stream.last().append(arr_stream);
                    //
                    // if it is not assign statement or foor loop, the value has to stay on the stack!!
                    if(!((parent instanceof ExpressionStatement) || ((parent instanceof ForStatement) && ((((ForStatement)parent).init == this) || (((ForStatement)parent).iterator == this)))))
                        stream.last().code = Code.STAIK;
                    else    // else simple store
                        stream.last().code = Code.STAI;
                }
                else // array with constant indices
                {
                    stream = stream.last().append(new LoadStoreInstr(var.isArray ? Code.STA : Code.ST, var));
                    //
                    // if it is not assign statement or foor loop, the value has to stay on the stack!!
                    if(!((parent instanceof ExpressionStatement) || ((parent instanceof ForStatement) && ((((ForStatement)parent).init == this) || (((ForStatement)parent).iterator == this)))))
                        stream = stream.last().append(new LoadStoreInstr(var.isArray ? Code.LDA : Code.LD, var));
                }
            }
            else if(operator == Operator.LOG_AND)
                stream = stream.last().append(new Instruction(Code.AND));   // same as bit AND
            else if(operator == Operator.LOG_OR)
                stream = stream.last().append(new Instruction(Code.OR));   // same as bit OR
            else if(operator == Operator.LSH)
                stream = stream.last().append(new Instruction(Code.LSH));
            else if(operator == Operator.LSH_ASN)
            {
                stream = stream.last().append(new Instruction(Code.LSH));
                var = (Variable)leftOperand;
                arr_stream = var.genByteCode();
                if(arr_stream.last().code == Code.LDAI) // array with non-constant indices
                {
                    stream = stream.last().append(arr_stream);
                    //
                    // if it is not assign statement or foor loop, the value has to stay on the stack!!
                    if(!((parent instanceof ExpressionStatement) || ((parent instanceof ForStatement) && ((((ForStatement)parent).init == this) || (((ForStatement)parent).iterator == this)))))
                        stream.last().code = Code.STAIK;
                    else    // else simple store
                        stream.last().code = Code.STAI;
                }
                else // array with constant indices
                {
                    stream = stream.last().append(new LoadStoreInstr(var.isArray ? Code.STA : Code.ST, var));
                    //
                    // if it is not assign statement or foor loop, the value has to stay on the stack!!
                    if(!((parent instanceof ExpressionStatement) || ((parent instanceof ForStatement) && ((((ForStatement)parent).init == this) || (((ForStatement)parent).iterator == this)))))
                        stream = stream.last().append(new LoadStoreInstr(var.isArray ? Code.LDA : Code.LD, var));
                }
            }
            else if(operator == Operator.RSH)
                stream = stream.last().append(new Instruction(Code.RSH));
            else if(operator == Operator.RSH_ASN)
            {
                stream = stream.last().append(new Instruction(Code.RSH));
                var = (Variable)leftOperand;
                arr_stream = var.genByteCode();
                if(arr_stream.last().code == Code.LDAI) // array with non-constant indices
                {
                    stream = stream.last().append(arr_stream);
                    //
                    // if it is not assign statement or foor loop, the value has to stay on the stack!!
                    if(!((parent instanceof ExpressionStatement) || ((parent instanceof ForStatement) && ((((ForStatement)parent).init == this) || (((ForStatement)parent).iterator == this)))))
                        stream.last().code = Code.STAIK;
                    else    // else simple store
                        stream.last().code = Code.STAI;
                }
                else // array with constant indices
                {
                    stream = stream.last().append(new LoadStoreInstr(var.isArray ? Code.STA : Code.ST, var));
                    //
                    // if it is not assign statement or foor loop, the value has to stay on the stack!!
                    if(!((parent instanceof ExpressionStatement) || ((parent instanceof ForStatement) && ((((ForStatement)parent).init == this) || (((ForStatement)parent).iterator == this)))))
                        stream = stream.last().append(new LoadStoreInstr(var.isArray ? Code.LDA : Code.LD, var));
                }
            }
            else if(operator == Operator.EQ)
            {   // (left - right) -> =0, >0, <0 --> ZF iff =0
                stream = stream.last().append(new Instruction(Code.SUB));
                // replaces result on the stack by the Zero Flag
                stream = stream.last().append(new Instruction(Code.POP));
                stream = stream.last().append(new Instruction(Code.LDZF));
            }
            else if(operator == Operator.NEQ)
            {
                // (left - right) -> =0, >0, <0 --> ZF iff <>0
                stream = stream.last().append(new Instruction(Code.SUB));
                // replaces result on the stack by the Zero Flag
                stream = stream.last().append(new Instruction(Code.POP));
                stream = stream.last().append(new Instruction(Code.LDNZF));
            }
            else if(operator == Operator.LT)
            {
                // (left - right) -> =0, >0, <0 --> SF iff <0
                stream = stream.last().append(new Instruction(Code.SUB));
                // replaces result on the stack by the Sign Flag
                stream = stream.last().append(new Instruction(Code.POP));
                stream = stream.last().append(new Instruction(Code.LDSF));
            }
            else if(operator == Operator.GTE)
            {
                // (left - right) -> =0, >0, <0 --> SF iff <0
                stream = stream.last().append(new Instruction(Code.SUB));
                // replaces result on the stack by the Sign Flag
                stream = stream.last().append(new Instruction(Code.POP));
                stream = stream.last().append(new Instruction(Code.LDNSF));
            }
        }
        return first;
        */
    }

    @Override
    public void semanticCheck() throws Exception
    {
        if(rightOperand instanceof FunctionCall)
        {
            String fnName = ((FunctionCall)rightOperand).functionName;
            if(Functions.builtInFunctions.get(fnName) == Boolean.FALSE)
                throw new Exception("Semantic error: function '" + fnName + "' doesn't return value! Therefore you can't assign it to a variable!");
        }
        //
        leftOperand.semanticCheck();
        rightOperand.semanticCheck();
    }
}
