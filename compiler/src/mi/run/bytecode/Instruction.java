package mi.run.bytecode;

import java.text.DecimalFormat;
import java.util.HashMap;
import mi.run.runtime.Interpreter;
import mi.run.runtime.Value;

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
        switch(code)
        {
            case Code.LDSF:  machine.memory.stack.push(new Value( machine.SF)); break;
            case Code.LDZF:  machine.memory.stack.push(new Value( machine.ZF)); break;
            case Code.LDNSF: machine.memory.stack.push(new Value(!machine.SF)); break;
            case Code.LDNZF: machine.memory.stack.push(new Value(!machine.ZF)); break;
            case Code.RET:
            {
                if(machine.SB == 0)
                {
                    machine.memory.stack.clear();
                    return false;   // stop - return from the main function
                }
                // remove the stack-frame
                // pop all local variables and function arguments
                machine.memory.stack.setSize(machine.SB);
                // load heap
                machine.memory.heap = (HashMap<String, Value>)machine.memory.stack.pop();
                // load IC
                machine.IC = (Integer)machine.memory.stack.pop();
                // load SB
                machine.SB = (Integer)machine.memory.stack.pop();
                //
                return true;
            }
            case Code.RETV:
            {
                // remove the stack-frame
                Value retval = (Value)machine.memory.stack.pop();   // save return value
                //
                if(machine.SB == 0)
                {
                    machine.memory.stack.clear();
                    machine.memory.stack.push(retval);
                    return false;   // stop - return from the main function
                }
                //
                // pop all local variables and function arguments
                machine.memory.stack.setSize(machine.SB);
                // load heap
                machine.memory.heap = (HashMap<String, Value>)machine.memory.stack.pop();
                // load IC
                machine.IC = (Integer)machine.memory.stack.pop();
                // load SB
                machine.SB = (Integer)machine.memory.stack.pop();
                // push back the return value
                machine.memory.stack.push(retval);
                //
                return true;
            }
            case Code.POP: machine.memory.stack.pop(); break;
            case Code.NOT:
            {
                Value op = (Value)machine.memory.stack.pop();
                Value val = new Value(!op.toBool());
                machine.memory.stack.push(val);
                machine.ZF = (val.toBool() == false);
                machine.SF = false;
                break;
            }
            case Code.NEG:
            {
                Value op = (Value)machine.memory.stack.pop();
                Value val = new Value(~op.toInt());
                machine.memory.stack.push(val);
                machine.ZF = (val.toInt() == 0);
                machine.SF = (val.toInt()  < 0);
                break;
            }
            case Code.INC:
            {
                Value op = (Value)machine.memory.stack.peek();
                op.set(op.toInt() + 1);
                machine.ZF = (op.toInt() == 0);
                machine.SF = (op.toInt()  < 0);
                break;
            }
            case Code.DEC:
            {
                Value op = (Value)machine.memory.stack.peek();
                op.set(op.toInt() - 1);
                machine.ZF = (op.toInt() == 0);
                machine.SF = (op.toInt()  < 0);
                break;
            }
            case Code.ADD:
            {
                Value l = (Value)machine.memory.stack.pop();
                Value r = (Value)machine.memory.stack.pop();
                Value v;
                if((l.type() == Value.STRING) || (r.type() == Value.STRING))
                {
                    v = new Value(l.toStr() + r.toStr());
                    machine.ZF = false;
                    machine.SF = false;
                }
                else if((l.type() == Value.REAL) || (r.type() == Value.REAL))
                {
                    v = new Value(l.toReal() + r.toReal());
                    machine.ZF = (v.toReal() == 0.0);
                    machine.SF = (v.toReal()  < 0.0);
                }
                else
                {
                    v = new Value(l.toInt() + r.toInt());
                    machine.ZF = (v.toInt() == 0);
                    machine.SF = (v.toInt()  < 0);
                }
                machine.memory.stack.push(v);
                break;
            }
            case Code.SUB:
            {
                Value l = (Value)machine.memory.stack.pop();
                Value r = (Value)machine.memory.stack.pop();
                Value v;
                if((l.type() == Value.REAL) || (r.type() == Value.REAL))
                    v = new Value(l.toReal() - r.toReal());
                else
                    v = new Value(l.toInt() - r.toInt());
                machine.memory.stack.push(v);
                machine.ZF = (v.toReal() == 0.0);
                machine.SF = (v.toReal()  < 0.0);
                break;
            }
            case Code.MUL:
            {
                Value l = (Value)machine.memory.stack.pop();
                Value r = (Value)machine.memory.stack.pop();
                Value v;
                if((l.type() == Value.REAL) || (r.type() == Value.REAL))
                    v = new Value(l.toReal() * r.toReal());
                else
                    v = new Value(l.toInt() * r.toInt());
                machine.memory.stack.push(v);
                machine.ZF = (v.toReal() == 0.0);
                machine.SF = (v.toReal()  < 0.0);
                break;
            }
            case Code.DIV:
            {
                Value l = (Value)machine.memory.stack.pop();
                Value r = (Value)machine.memory.stack.pop();
                Value v;
                if((l.type() == Value.REAL) || (r.type() == Value.REAL))
                    v = new Value(l.toReal() / r.toReal());
                else
                {
                    int left = l.toInt(), right = r.toInt();
                    if(right == 0) throw new Exception("Runtime: Dividing by zero is not defined!");
                    v = new Value(left / right);
                }
                machine.memory.stack.push(v);
                machine.ZF = (v.toReal() == 0.0);
                machine.SF = (v.toReal()  < 0.0);
                break;
            }
            case Code.MOD:
            {
                Value l = (Value)machine.memory.stack.pop();
                Value r = (Value)machine.memory.stack.pop();
                Value v;
                int left = l.toInt(), right = r.toInt();
                if(right == 0) throw new Exception("Runtime: Moduling by zero is not defined!");
                v = new Value(left % right);
                machine.memory.stack.push(v);
                machine.ZF = (v.toInt() == 0);
                machine.SF = (v.toInt()  < 0);
                break;
            }
            case Code.AND:
            {
                Value l = (Value)machine.memory.stack.pop();
                Value r = (Value)machine.memory.stack.pop();
                Value v;
                if((l.type() == Value.INTEGER) || (r.type() == Value.INTEGER))
                    v = new Value(l.toInt() & r.toInt());
                else
                    v = new Value(l.toBool() && r.toBool());
                machine.memory.stack.push(v);
                machine.ZF = (v.toInt() == 0);
                machine.SF = (v.toInt()  < 0);
                break;
            }
            case Code.OR :
            {
                Value l = (Value)machine.memory.stack.pop();
                Value r = (Value)machine.memory.stack.pop();
                Value v;
                if((l.type() == Value.INTEGER) || (r.type() == Value.INTEGER))
                    v = new Value(l.toInt() | r.toInt());
                else
                    v = new Value(l.toBool() || r.toBool());
                machine.memory.stack.push(v);
                machine.ZF = (v.toInt() == 0);
                machine.SF = (v.toInt()  < 0);
                break;
            }
            case Code.XOR:
            {
                Value l = (Value)machine.memory.stack.pop();
                Value r = (Value)machine.memory.stack.pop();
                Value v;
                v = new Value(l.toInt() ^ r.toInt());
                machine.memory.stack.push(v);
                machine.ZF = (v.toInt() == 0);
                machine.SF = (v.toInt()  < 0);
                break;
            }
            case Code.LSH:
            {
                Value l = (Value)machine.memory.stack.pop();
                Value r = (Value)machine.memory.stack.pop();
                Value v;
                v = new Value(l.toInt() << r.toInt());
                machine.memory.stack.push(v);
                machine.ZF = (v.toInt() == 0);
                machine.SF = (v.toInt()  < 0);
                break;
            }
            case Code.RSH:
            {
                Value l = (Value)machine.memory.stack.pop();
                Value r = (Value)machine.memory.stack.pop();
                Value v;
                v = new Value(l.toInt() >> r.toInt());
                machine.memory.stack.push(v);
                machine.ZF = (v.toInt() == 0);
                machine.SF = (v.toInt()  < 0);
                break;
            }
            default:    // skip... (f.e. NOOP)
                break;
        }
        machine.IC++;
        return true;
    }
}
