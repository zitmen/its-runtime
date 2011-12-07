package mi.run.bytecode;

import java.util.ArrayList;
import java.util.HashMap;
import mi.run.runtime.Interpreter;
import mi.run.runtime.Value;

public class LoadConstInstr extends Instruction
{   // LDCB,LDCI,LDCR,LDCS,LDCA
    public Value value; // String, Real, Integer, Boolean, Variable
    
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
    
    public LoadConstInstr(ArrayList<Value> val)
    {
        super(Code.LDCA);
        value = new Value(val);
    }
    
    @Override
    public String toString()
    {
        return super.toString() + " " + value.toString();
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
        }
        machine.IC++;
        return true;
    }
}
