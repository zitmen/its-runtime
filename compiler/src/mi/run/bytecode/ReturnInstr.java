package mi.run.bytecode;

import mi.run.runtime.Interpreter;

public class ReturnInstr extends Instruction
{
    public String val;
    
    public ReturnInstr()
    {
        super(Code.RET);
        this.val = null;
    }
    
    public ReturnInstr(String val)
    {
        super(Code.RETV);
        this.val = val;
    }
    
    @Override
    public String toString()
    {
        String str = super.toString();
        if(val != null) str += " " + val;
        return str;
    }
    
    @Override
    public boolean interpret(Interpreter machine) throws Exception
    {
        throw new Exception("NOT SUPPORTED!");
    }
}
