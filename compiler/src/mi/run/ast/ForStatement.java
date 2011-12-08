package mi.run.ast;

import mi.run.bytecode.Code;
import mi.run.bytecode.Instruction;
import mi.run.bytecode.JumpInstr;
import mi.run.semantic.FlowControl;
import mi.run.semantic.SymbolTable;
import mi.run.semantic.TypeCast;

public class ForStatement extends Statement
{
    public Expression init;
    public Expression condition;
    public Expression iterator;
    public Statement body;
    
    public ForStatement(Expression init, Expression condition, Expression iterator, Statement body)
    {
        this.init = init;
        this.condition = condition;
        this.iterator = iterator;
        this.body = body;
    }

    @Override
    public String toString()
    {
        return "(ForStatement " + init + " " + condition + " " + iterator + " " + body.toString() + ")";
    }
    
    @Override
    public void semanticCheck() throws Exception
    {
        SymbolTable.stepIn();
        init.semanticCheck();
        condition.semanticCheck();
        if(condition.evalDatatype() != DataType.BOOL)
            throw new Exception("SEMANTIC ERROR: 'for' condition must be of bool type!");
        iterator.semanticCheck();
        body.semanticCheck();
        SymbolTable.stepOut();
    }

    @Override
    public Node optimize() throws Exception
    {
        init = (Expression)init.optimize();
        iterator = (Expression)iterator.optimize();
        condition = (Expression)condition.optimize();
        if(condition instanceof BooleanAtom)
        {
            if(((BooleanAtom)condition).value == false)
                return null;
        }
        //
        body = (Statement)body.optimize();
        if(body == null) return null;
        //
        return this;
    }

    @Override
    public Instruction genByteCode()
    {
        Instruction stream = init.genByteCode();
        Instruction first = stream.first();
        Instruction cond = condition.genByteCode();
        stream = stream.last().append(cond);
        JumpInstr jump_behind = new JumpInstr(Code.JZ, null);
        stream = stream.last().append(jump_behind);
        stream = stream.last().append(body.genByteCode());
        stream = stream.last().append(iterator.genByteCode());
        stream = stream.last().append(new JumpInstr(Code.JMP, cond.goForwardNoops()));
        jump_behind.address = stream.last().goBackNoops();
        jump_behind.addr_offset = +1;
        //
        JumpInstr jmp;
        for(int i = 0, im = FlowControl.breakStatements.size(); i < im; i++)
        {
            jmp = FlowControl.breakStatements.get(i);
            jmp.address = stream.last();
            jmp.addr_offset = +1;
        }
        FlowControl.breakStatements.clear();
        //
        for(int i = 0, im = FlowControl.continueStatements.size(); i < im; i++)
        {
            jmp = FlowControl.continueStatements.get(i);
            jmp.address = first;
        }
        FlowControl.continueStatements.clear();
        //
        return first;
    }
}
