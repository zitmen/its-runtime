package mi.run.ast;

import mi.run.bytecode.Code;
import mi.run.bytecode.Instruction;
import mi.run.bytecode.JumpInstr;
import mi.run.semantic.FlowControl;
import mi.run.semantic.TypeCast;

public class DoWhileStatement extends Statement
{
    public Expression condition;
    public Statement body;
    
    public DoWhileStatement(Expression condition, Statement body)
    {
        this.condition = condition;
        this.body = body;
    }
    
    @Override
    public String toString()
    {
        return "(DoWhileStatement " + condition + " " + body + ")";
    }
    
    @Override
    public void semanticCheck() throws Exception
    {
        condition.semanticCheck();
        body.semanticCheck();
    }

    @Override
    public Node optimize() throws Exception
    {
        condition = (Expression)condition.optimize();
        if((condition instanceof StringAtom ) || (condition instanceof RealAtom) ||
           (condition instanceof IntegerAtom) || (condition instanceof BooleanAtom))
        {
            if(TypeCast.toBoolean((Atom)condition).value == false)
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
        Instruction stream = body.genByteCode();
        Instruction first = stream.first();
        stream = stream.last().append(condition.genByteCode());
        stream = stream.last().append(new JumpInstr(Code.JNZ, first.goForwardNoops()));
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
