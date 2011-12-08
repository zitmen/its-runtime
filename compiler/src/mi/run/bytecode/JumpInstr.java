package mi.run.bytecode;

import mi.run.runtime.Interpreter;

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
        throw new Exception("NOT SUPPORTED!");
    }
}
