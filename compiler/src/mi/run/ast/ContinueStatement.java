package mi.run.ast;

import mi.run.bytecode.Code;
import mi.run.bytecode.Instruction;
import mi.run.bytecode.JumpInstr;
import mi.run.semantic.FlowControl;

public class ContinueStatement extends Statement
{
    @Override
    public String toString()
    {
        return "(ContinueStatement)";
    }
    
    @Override
    public void semanticCheck() throws Exception
    {
        Node n = this;
        while(n.parent != null)
        {
            if((n.parent instanceof WhileStatement  ) ||
               (n.parent instanceof DoWhileStatement) ||
               (n.parent instanceof ForStatement))
                return;
            //
            n = n.parent;
        }
        throw new Exception("'continue' must not be used outside of a loop!");
    }

    @Override
    public Node optimize()
    {
        return this;
    }

    @Override
    public Instruction genByteCode()
    {
        JumpInstr jmp = new JumpInstr(Code.JMP, null);
        FlowControl.continueStatements.add(jmp);
        return jmp;
    }
}
