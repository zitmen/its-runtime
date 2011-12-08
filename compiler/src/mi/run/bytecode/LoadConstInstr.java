package mi.run.bytecode;

import java.util.ArrayList;
import mi.run.runtime.Interpreter;
import mi.run.runtime.Value;

public class LoadConstInstr extends Instruction
{   // LDCB,LDCI,LDCR,LDCS,LDCA,LDCN
    public Value value; // String, Real, Integer, Boolean, Array, Null
    
    public LoadConstInstr(boolean val)
    {
        super(Code.LDCB);
        value = new Value(val);
    }
    
    public LoadConstInstr(int val)
    {
        super(Code.LDCI);
        value = new Value(val);
    }
    
    public LoadConstInstr(double val)
    {
        super(Code.LDCR);
        value = new Value(val);
    }
    
    public LoadConstInstr(String val)
    {
        super(Code.LDCS);
        value = new Value(val);
    }
    
    public LoadConstInstr()
    {
        super(Code.LDCN);
        value = new Value();
    }
    
    public LoadConstInstr(ArrayList<Value> val)
    {
        super(Code.LDCA);
        value = new Value(val);
    }
    
    @Override
    public String toString()
    {
        String str = super.toString() + " ";
        try
        {
            switch(code)
            {
                case Code.LDCB: str += (value.toBool() ? "TRUE" : "FALSE"); break;
                case Code.LDCI: str += value.toInt(); break;
                case Code.LDCR: str += value.toReal(); break;
                case Code.LDCS: str += value.toStr(); break;
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
        switch(code)
        {
            case Code.LDCB:
                machine.memory.stack.push(value);
                machine.ZF = (value.toBool() == false);
                machine.SF = false;
                break;
                
            case Code.LDCI:
                machine.memory.stack.push(value);
                machine.ZF = (value.toInt() == 0);
                machine.SF = (value.toInt()  < 0);
                break;
                
            case Code.LDCR:
                machine.memory.stack.push(value);
                machine.ZF = (value.toReal() == 0.0);
                machine.SF = (value.toReal()  < 0.0);
                break;
                
            case Code.LDCS:
                machine.memory.stack.push(value);
                machine.ZF = false;
                machine.SF = false;
                break;
                
            case Code.LDCA:
                machine.memory.stack.push(value);
                machine.ZF = false;
                machine.SF = false;
                break;
                
            case Code.LDCN:
                machine.memory.stack.push(value);
                machine.ZF = true;
                machine.SF = false;
                break;
        }
        machine.IC++;
        return true;
    }
}
