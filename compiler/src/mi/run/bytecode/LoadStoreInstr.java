package mi.run.bytecode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import mi.run.ast.Atom;
import mi.run.ast.IntegerAtom;
import mi.run.ast.StringAtom;
import mi.run.ast.Variable;
import mi.run.runtime.Interpreter;
import mi.run.runtime.Value;

public class LoadStoreInstr extends Instruction
{   //LD,ST,LDA,STA,LDR,STAV
    public Variable variable;
    
    public LoadStoreInstr(int code, Variable var)
    {
        super(code);
        variable = var;
    }
    
    @Override
    public String toString()
    {
        return super.toString() + " " + variable.uniqName;
    }
    
    @Override
    public boolean interpret(Interpreter machine) throws Exception
    {
        throw new Exception("NOT SUPPORTED!");
/*
        switch(code)
        {
            case Code.LD:
            {
                Value val = machine.memory.heap.get(variable.name);
                if(val == null)
                {
                    val = new Value(0);
                    machine.memory.heap.put(variable.name, val);
                }
                machine.memory.stack.push(val);
                break;
            }
            case Code.LDR:
            {
                Value val = machine.memory.heap.get(variable.name);
                machine.memory.heap.remove(variable.name);
                machine.memory.stack.push((val == null) ? (new Value(0)) : val);
                break;
            }
            case Code.ST:
            {
                machine.memory.heap.put(variable.name, ((Value)machine.memory.stack.pop()).clone());
                break;
            }
            case Code.LDA:
            {
                // save value from the memory to the top of the stack
                // -- memory can be multidimensional array --> if there is any dimension missing, create it
                //  -- save init value 0
                Value arr = (Value)machine.memory.heap.get(variable.name);
                if(arr == null)
                {
                    arr = new Value(new HashMap<Object, Value>());
                    machine.memory.heap.put(variable.name, arr);
                }
                HashMap<Object, Value> map = arr.toArray();
                Atom atom = (Atom)variable.members.get(0);
                for(int m = 0, mm = variable.members.size() - 1; m < mm; m++)
                {
                    if(atom instanceof StringAtom)
                        arr = map.get(((StringAtom)atom).value);
                    else    // IntegerAtom
                        arr = map.get(((IntegerAtom)atom).value);
                    //
                    if(arr == null)
                    {
                        arr = new Value(new HashMap<Object, Value>());
                        if(atom instanceof StringAtom)
                            map.put(((StringAtom)atom).value, arr);
                        else    // IntegerAtom
                            map.put(((IntegerAtom)atom).value, arr);
                    }
                    //
                    map = arr.toArray();
                    atom = (Atom)variable.members.get(m+1);
                }
                //
                if(atom instanceof StringAtom)
                {
                    arr = map.get(((StringAtom)atom).value);
                    if(arr == null)
                    {
                        arr = new Value(0);
                        map.put(((StringAtom)atom).value, arr);
                    }
                }
                else    // IntegerAtom
                {
                    arr = map.get(((IntegerAtom)atom).value);
                    if(arr == null)
                    {
                        arr = new Value(0);
                        map.put(((IntegerAtom)atom).value, arr);
                    }
                }
                machine.memory.stack.push(arr);
                break;
            }
            case Code.STA:
            {
                // save value from the top of the stack into the memory
                // -- memory can be multidimensional array --> if there is any dimension missing, create it
                //  -- save value
                Value arr = (Value)machine.memory.heap.get(variable.name);
                if(arr == null)
                {
                    arr = new Value(new HashMap<Object, Value>());
                    machine.memory.heap.put(variable.name, arr);
                }
                HashMap<Object, Value> map = new HashMap<Object, Value>();
                Atom atom = new StringAtom(variable.name);
                for(int m = 0, mm = variable.members.size(); m < mm; m++)
                {
                    map = arr.toArray();
                    atom = (Atom)variable.members.get(m);
                    if(atom instanceof StringAtom)
                        arr = map.get(((StringAtom)atom).value);
                    else    // IntegerAtom
                        arr = map.get(((IntegerAtom)atom).value);
                    //
                    if(arr == null)
                    {
                        arr = new Value(new HashMap<Object, Value>());
                        if(atom instanceof StringAtom)
                            map.put(((StringAtom)atom).value, arr);
                        else    // IntegerAtom
                            map.put(((IntegerAtom)atom).value, arr);
                    }
                }
                if(atom instanceof StringAtom)
                    map.put(((StringAtom)atom).value, ((Value)machine.memory.stack.pop()).clone());
                else    // IntegerAtom
                    map.put(((IntegerAtom)atom).value, ((Value)machine.memory.stack.pop()).clone());
                break;
            }
            case Code.STAV:
            {
                // save value from the top of the stack into the memory
                // -- if the variable is already in memory, it rewrites indices that contain something
                Value arr = (Value)machine.memory.heap.get(variable.name);
                Value arr_stack = (Value)machine.memory.stack.pop();
                if(arr == null)
                {
                    arr = new Value(new HashMap<Object, Value>());
                    machine.memory.heap.put(variable.name, arr_stack);
                }
                else
                {
                    HashMap<Object, Value> map = arr.toArray();
                    HashMap<Object, Value> map_stack = arr_stack.toArray();
                    Iterator it = map_stack.entrySet().iterator();
                    Map.Entry pair;
                    while(it.hasNext())
                    {
                        pair = (Map.Entry)it.next();
                        map.put(pair.getKey(), (Value)pair.getValue());
                    }
                }
                break;
            }
        }
        machine.IC++;
        return true;
 */
    }
}
