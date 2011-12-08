package mi.run.bytecode;

import mi.run.runtime.Interpreter;

public class LoadStoreInstr extends Instruction
{   //LD,ST,LDA,STA,LDR,STAV
    public String dest;
    public String src;
    
    public LoadStoreInstr(int code, String dest, String src)
    {
        super(code);
        this.dest = dest;
        this.src = src;
    }
    
    @Override
    public String toString()
    {
        return super.toString() + " " + dest + " " + src;
    }
    
    @Override
    public boolean interpret(Interpreter machine) throws Exception
    {
        throw new Exception("NOT SUPPORTED!");
    }
}
