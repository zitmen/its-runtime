package mi.run.runtime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import mi.run.bytecode.Instruction;


public class Memory
{
    public Stack stack;
    public HashMap<String, Value> heap;
    public ArrayList<Instruction> code;
    
    public Memory()
    {
        stack = new Stack();
        heap = new HashMap<String, Value>();
        code = new ArrayList<Instruction>();
    }
    
    public static int temp_counter = 0;
    public static String genNewTempVarName()
    {
        return "@tmp_" + temp_counter;
    }
}
