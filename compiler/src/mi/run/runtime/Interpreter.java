package mi.run.runtime;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import mi.run.ast.Program;
import mi.run.bytecode.Instruction;
import mi.run.semantic.Functions;

public final class Interpreter
{
    public Memory memory;  // Stack, Heap and Code
    public Program program;
    
    public int     SB; // Stack Base
    public int     IC; // Instruction Counter
    public boolean ZF; // Zero Flag -> true iff the last arithmetic operation's result was zero (= 0)
    public boolean SF; // Sign Flag -> true iff the last arithmetic operation's result was negative value (< 0)
    
    public Interpreter(Program program)
    {
        memory = new Memory();
        this.program = program;
        //
        Instruction instr = program.genByteCode();
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
        // first print out functions a structures signatures
        Iterator it = program.structures_signatures.entrySet().iterator();
        Map.Entry pair;
        while(it.hasNext())
        {
            pair = (Entry)it.next();
            stream.println(pair.getValue());
        }
        it = program.functions_signatures.entrySet().iterator();
        while(it.hasNext())
        {
            pair = (Entry)it.next();
            stream.println(((String)pair.getValue()) + " @ " + Functions.fnIC.get((String)pair.getKey()));
        }
        // then print out the actual byte-code
        stream.println("CODE");
        for(int i = 0, im = memory.code.size(); i < im; i++)
            stream.println(memory.code.get(i).toString());
    }
}
