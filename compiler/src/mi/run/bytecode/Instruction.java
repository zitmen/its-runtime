package mi.run.bytecode;

import java.text.DecimalFormat;
import mi.run.runtime.Interpreter;

public class Instruction
{
    public Instruction next;
    public Instruction prev;
    public int code;
    
    private int address;
    
    public Instruction(int c)
    {
        address = -1;
        code = c;
        next = null;
        prev = null;
    }

    public int getIC()  // Instruction Counter
    {
        if(address < 0)
        {
            Instruction i = first();
            i.address = 0;
            while(i.next != null)
            {
                i.next.address = i.address + 1;
                i = i.next;
            }
        }
        return address;
    }
    
    public Instruction append(Instruction i)
    {
        if(i == null) return this;
        next = i;
        i.prev = this;
        return next;
    }
    
    public Instruction prepend(Instruction i)
    {
        if(i == null) return this;
        prev = i;
        i.next = this;
        return prev;
    }
    
    public Instruction first()
    {
        Instruction f = this;
        while(f.prev != null)
            f = f.prev;
        return f;
    }
    
    public Instruction last()
    {
        Instruction l = this;
        while(l.next != null)
            l = l.next;
        return l;
    }
    
    public Instruction goForwardNoops()
    {
        Instruction l = this;
        while((l.next != null) && (l.code == Code.NOOP))
            l = l.next;
        return l;
    }
    
    public Instruction goBackNoops()
    {
        Instruction l = this;
        while((l.prev != null) && (l.code == Code.NOOP))
            l = l.prev;
        return l;
    }
    
    private static DecimalFormat formatter = null;
    
    @Override
    public String toString()
    {
        if(formatter == null)
            formatter = new DecimalFormat("000");
        //
        return formatter.format(getIC()) + ": " + Code.codeToString(code);
    }
    
    public boolean interpret(Interpreter machine) throws Exception
    {
        throw new Exception("NOT SUPPORTED!");
    }
}
