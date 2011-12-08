package mi.run.bytecode;

import mi.run.runtime.Interpreter;

public class NewInstr extends Instruction
{   // NEW
    public String dest;
    public String countVar;
    
    public NewInstr(String dest, String countVar)
    {
        super(Code.NEW);
        this.countVar = countVar;
         this.dest = dest;
    }

    @Override
    public String toString()
    {
        String str = super.toString() + " " + dest;
        if(countVar != null)
            str += " " + countVar;
        return str;
    }
    
    @Override
    public boolean interpret(Interpreter machine) throws Exception
    {
        throw new Exception("NOT SUPPORTED!");
    }
}
