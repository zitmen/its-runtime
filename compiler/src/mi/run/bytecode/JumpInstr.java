package mi.run.bytecode;

import mi.run.runtime.Interpreter;
import mi.run.runtime.Value;

public class JumpInstr extends Instruction
{   // JZ,JNZ,JMP
    public Instruction address;
    public int addr_offset; // for changing an address of the jump in a specified direction (off > 0)->next(), (off < 0)->prev()
    
    public JumpInstr(int code, Instruction addr)
    {
        super(code);
        address = addr;
        addr_offset = 0;
    }
    
    @Override
    public String toString()
    {
        return super.toString() + " " + (address.getIC() + addr_offset);
    }
    
    @Override
    public boolean interpret(Interpreter machine) throws Exception
    {
        switch(code)
        {
            case Code.JZ : machine.IC = (!((Value)machine.memory.stack.pop()).toBool()) ? (address.getIC() + addr_offset) : (machine.IC + 1); break;
            case Code.JNZ: machine.IC = ( ((Value)machine.memory.stack.pop()).toBool()) ? (address.getIC() + addr_offset) : (machine.IC + 1); break;
            case Code.JMP: machine.IC = address.getIC() + addr_offset; break;
        }
        return true;
    }
}
