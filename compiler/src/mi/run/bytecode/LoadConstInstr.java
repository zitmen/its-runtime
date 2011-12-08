package mi.run.bytecode;

import java.util.ArrayList;
import mi.run.runtime.Interpreter;
import mi.run.runtime.Value;

public class LoadConstInstr extends Instruction
{   // LDCB,LDCI,LDCR,LDCS,LDCA,LDCN
    public Value value; // String, Real, Integer, Boolean, Array, Null
    public String dest;
    
    public LoadConstInstr(String var, boolean val)
    {
        super(Code.LDCB);
        value = new Value(val);
        dest = var;
    }
    
    public LoadConstInstr(String var, int val)
    {
        super(Code.LDCI);
        value = new Value(val);
        dest = var;
    }
    
    public LoadConstInstr(String var, double val)
    {
        super(Code.LDCR);
        value = new Value(val);
        dest = var;
    }
    
    public LoadConstInstr(String var, String val)
    {
        super(Code.LDCS);
        value = new Value(val);
        dest = var;
    }
    
    public LoadConstInstr(String var)
    {
        super(Code.LDCN);
        value = new Value();
        dest = var;
    }
    
    public LoadConstInstr(String var, ArrayList<Value> val)
    {
        super(Code.LDCA);
        value = new Value(val);
        dest = var;
    }
    
    @Override
    public String toString()
    {
        String str = super.toString() + " " + dest + " ";
        try
        {
            switch(code)
            {
                case Code.LDCB: str += (value.toBool() ? "TRUE" : "FALSE"); break;
                case Code.LDCI: str += value.toInt(); break;
                case Code.LDCR: str += value.toReal(); break;
                case Code.LDCS: str += "\"" + value.toStr() + "\""; break;
                case Code.LDCN: str += "NULL"; break;
                default: str += value.toString(); break;
            }
        }
        catch(Exception e)
        {
            str += value.toString();
        }
        return str;
    }
    
    @Override
    public boolean interpret(Interpreter machine) throws Exception
    {
        throw new Exception("NOT SUPPORTED!");
    }
}
