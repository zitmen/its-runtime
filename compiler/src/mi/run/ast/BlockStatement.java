package mi.run.ast;

import mi.run.bytecode.Code;
import mi.run.bytecode.Instruction;

public class BlockStatement extends Statement
{
    public java.util.ArrayList<Statement> statements;
    
    public BlockStatement()
    {
        statements = new java.util.ArrayList<Statement>();
    }

    @Override
    public String toString()
    {
        return "(BlockStatement " + statements + ")";
    }
    
   @Override
    public void semanticCheck() throws Exception
    {
        for(int i = 0; i < statements.size(); i++)
            statements.get(i).semanticCheck();
    }

    @Override
    public Node optimize() throws Exception
    {
        Statement stmt;
        for(int i = 0; i < statements.size(); )
        {
            stmt = (Statement)statements.get(i).optimize();
            if(stmt == null)
                statements.remove(i);
            else
            {
                statements.set(i, stmt);
                i++;
            }
        }
        if(statements.isEmpty()) return null;
        return this;
    }

    @Override
    public Instruction genByteCode()
    {
        Instruction stream = new Instruction(Code.NOOP);    // helper
        Instruction first = stream;
        for(int i = 0; i < statements.size(); i++)
            stream = stream.last().append(statements.get(i).genByteCode());
        //
        return first;
    }
}
