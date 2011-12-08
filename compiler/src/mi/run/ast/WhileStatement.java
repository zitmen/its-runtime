package mi.run.ast;

import mi.run.bytecode.Code;
import mi.run.bytecode.Instruction;
import mi.run.bytecode.JumpInstr;
import mi.run.semantic.FlowControl;
import mi.run.semantic.SymbolTable;
import mi.run.semantic.TypeCast;

public class WhileStatement extends Statement
{
    public Expression condition;
    public Statement body;
    
    public WhileStatement(Expression condition, Statement body)
    {
        this.condition = condition;
        this.body = body;
    }

    @Override
    public String toString()
    {
        return "(WhileStatement " + condition + " " + body.toString() + ")";
    }
    
    @Override
    public void semanticCheck() throws Exception
    {
        SymbolTable.stepIn();
        condition.semanticCheck();
        if(condition.evalDatatype() != DataType.BOOL)
            throw new Exception("SEMANTIC ERROR: 'while' condition must be of bool type!");
        body.semanticCheck();
        SymbolTable.stepOut();
    }

    @Override
    public Node optimize() throws Exception
    {
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
        Instruction stream = condition.genByteCode();
        Instruction first = stream.first();
        JumpInstr jump_behind = new JumpInstr(Code.JZ, null);
        stream = stream.last().append(jump_behind);
        stream = stream.last().append(body.genByteCode());
        stream = stream.last().append(new JumpInstr(Code.JMP, first.goForwardNoops()));
        jump_behind.address = stream.last();
        jump_behind.addr_offset = +1;
        //
        JumpInstr jmp;
        for(int i = 0, im = FlowControl.breakStatements.size(); i < im; i++)
        {
            jmp = FlowControl.breakStatements.get(i);
            jmp.address = stream.last().goBackNoops();
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
