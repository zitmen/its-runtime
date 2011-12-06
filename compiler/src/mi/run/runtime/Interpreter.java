package mi.run.runtime;

import java.io.PrintStream;
import mi.run.bytecode.Instruction;

public final class Interpreter
{
    public Memory memory;  // Stack, Heap and Code
    
    public int     SB; // Stack Base
    public int     IC; // Instruction Counter
    public boolean ZF; // Zero Flag -> true iff the last arithmetic operation's result was zero (= 0)
    public boolean SF; // Sign Flag -> true iff the last arithmetic operation's result was negative value (< 0)
    
    public Interpreter(Instruction code)
    {
        memory = new Memory();
        //
        Instruction instr = code;
        while(instr != null)
        {
            memory.code.add(instr);
            instr = instr.next;
        }
    }
    
    @SuppressWarnings("empty-statement")
    public void interpret() throws Exception
    {
        IC = 0;
        //try{
        while(memory.code.get(IC).interpret(this));
        //} catch(Exception ex)
        //{
          //  System.err.println("[@" + IC + "]" + ex.getMessage());
        //}
        //System.out.println("HEAP");
        //System.out.println(memory.heap);
    }

    public void printByteCode(PrintStream stream)
    {
        for(int i = 0, im = memory.code.size(); i < im; i++)
            stream.println(memory.code.get(i).toString());
    }
}
