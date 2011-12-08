package mi.run.ast;

import mi.run.bytecode.Code;
import mi.run.bytecode.Instruction;
import mi.run.bytecode.JumpInstr;
import mi.run.semantic.SymbolTable;
import mi.run.semantic.TypeCast;

public class IfStatement extends Statement
{
    public Expression condition;
    public Statement trueBody;
    public Statement falseBody;
    
    public IfStatement(Expression condition, Statement trueBody, Statement falseBody)
    {
        this.condition = condition;
        this.trueBody = trueBody;
        this.falseBody = falseBody;
    }
    
    @Override
    public String toString()
    {
        String str = "(IfStatement " + condition + " " + trueBody;
        if(falseBody != null) str += " " + falseBody;
        str += ")";
        return str;
    }
    
    @Override
    public void semanticCheck() throws Exception
    {
        SymbolTable.stepIn();
        condition.semanticCheck();
        if(condition.evalDatatype() != DataType.BOOL)
            throw new Exception("SEMANTIC ERROR: 'if-else' condition must be of bool type!");
        trueBody.semanticCheck();
        if(falseBody != null) falseBody.semanticCheck();
        SymbolTable.stepOut();
    }

    @Override
    public Node optimize() throws Exception
    {
        boolean optimized = false;
        condition = (Expression)condition.optimize();
        if(condition instanceof BooleanAtom)
        {
            optimized = true;
            if(((BooleanAtom)condition).value)
                falseBody = null;
            else
                trueBody = null;
        }
        //
        if(optimized)
        {
            if((trueBody == null) && (falseBody == null)) return null;
            if(trueBody  == null) return (Statement)falseBody.optimize();
            if(falseBody == null) return (Statement) trueBody.optimize();
        }
        trueBody = (Statement)trueBody.optimize();
        if(falseBody != null) falseBody = (Statement)falseBody.optimize();
        //
        return this;
    }

    @Override
    public Instruction genByteCode()
    {
        Instruction stream = condition.genByteCode();
        Instruction first = stream.first();
        JumpInstr jump_else = new JumpInstr(Code.JZ, null);
        stream = stream.last().append(jump_else);
        stream = stream.last().append(trueBody.genByteCode());
        //
        if(falseBody != null)
        {
            JumpInstr jump_behind = new JumpInstr(Code.JMP, null);
            stream = stream.last().append(jump_behind);
            Instruction falseBodyFirst = falseBody.genByteCode();
            stream = stream.last().append(falseBodyFirst);
            //
            jump_else.address = falseBodyFirst;
            jump_behind.address = stream.last();
            jump_behind.addr_offset = +1;
        }
        else
        {
            jump_else.address = stream.last();
            jump_else.addr_offset = +1;
        }
        return first;
    }
}
