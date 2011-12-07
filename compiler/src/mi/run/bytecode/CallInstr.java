package mi.run.bytecode;

import java.util.HashMap;
import java.util.Stack;
import mi.run.runtime.Interpreter;
import mi.run.runtime.Value;
import mi.run.runtime.built_in_routines.ArrayRoutines;
import mi.run.runtime.built_in_routines.IORoutines;
import mi.run.runtime.built_in_routines.MathRoutines;
import mi.run.runtime.built_in_routines.StringRoutines;
import mi.run.runtime.built_in_routines.TypeCastRoutines;
import mi.run.semantic.Functions;

public class CallInstr extends Instruction
{   // CALL, INVOKE
    public String functionName;
    
    public CallInstr(int code, String fnName)
    {
        super(code);
        functionName = fnName;
    }

    @Override
    public String toString()
    {
        return super.toString() + " " + functionName;
    }
    
    private Value pop(Interpreter machine)
    {
        return (Value)machine.memory.stack.pop();
    }
    
    private void push(Interpreter machine, Value val)
    {
        machine.memory.stack.push(val);
    }
    
    @Override
    public boolean interpret(Interpreter machine) throws Exception
    {
        switch(code)
        {
            case Code.INVOKE:   // built-in routines
            {
                if(functionName.equals("indexOf"))
                    push(machine, new Value(StringRoutines.indexOf(pop(machine).toStr(), pop(machine).toStr())));
                else if(functionName.equals("lastIndexOf"))
                    push(machine, new Value(StringRoutines.indexOf(pop(machine).toStr(), pop(machine).toStr())));
                else if(functionName.equals("substring"))
                    push(machine, new Value(StringRoutines.substring(pop(machine).toStr(), pop(machine).toInt(), pop(machine).toInt())));
                else if(functionName.equals("toLower"))
                    push(machine, new Value(StringRoutines.toLower(pop(machine).toStr())));
                else if(functionName.equals("toUpper"))
                    push(machine, new Value(StringRoutines.toUpper(pop(machine).toStr())));
                else if(functionName.equals("trim"))
                    push(machine, new Value(StringRoutines.trim(pop(machine).toStr())));
                else if(functionName.equals("startsWith"))
                    push(machine, new Value(StringRoutines.startsWith(pop(machine).toStr(), pop(machine).toStr())));
                else if(functionName.equals("endsWith"))
                    push(machine, new Value(StringRoutines.endsWith(pop(machine).toStr(), pop(machine).toStr())));
                else if(functionName.equals("strlen"))
                    push(machine, new Value(StringRoutines.strlen(pop(machine).toStr())));
                else if(functionName.equals("int2str"))
                    push(machine, new Value(TypeCastRoutines.int2str(pop(machine).toInt())));
                else if(functionName.equals("double2str"))
                    push(machine, new Value(TypeCastRoutines.double2str(pop(machine).toReal())));
                else if(functionName.equals("str2int"))
                    push(machine, new Value(TypeCastRoutines.str2int(pop(machine).toStr())));
                else if(functionName.equals("double2int"))
                    push(machine, new Value(TypeCastRoutines.double2int(pop(machine).toReal())));
                else if(functionName.equals("int2double"))
                    push(machine, new Value(TypeCastRoutines.int2double(pop(machine).toInt())));
                else if(functionName.equals("str2double"))
                    push(machine, new Value(TypeCastRoutines.str2double(pop(machine).toStr())));
                else if(functionName.equals("cloneArray"))
                    push(machine, new Value(ArrayRoutines.cloneArray(pop(machine).toArray())));
                else if(functionName.equals("clearArray"))  // no retval
                    ArrayRoutines.clearArray(pop(machine).toArray());
                else if(functionName.equals("length"))
                    push(machine, new Value(ArrayRoutines.length(pop(machine).toArray())));
                else if(functionName.equals("openRFile"))
                    push(machine, new Value(IORoutines.openRFile(pop(machine).toStr())));
                else if(functionName.equals("openWFile"))
                    push(machine, new Value(IORoutines.openWFile(pop(machine).toStr(), pop(machine).toBool())));
                else if(functionName.equals("closeFile"))
                {
                    Value val = pop(machine);
                    if(val.type() == Value.RFILE)
                        IORoutines.closeFile(val.toRFile());  // no retval
                    else
                        IORoutines.closeFile(val.toWFile());  // no retval
                }
                else if(functionName.equals("flushFile"))
                    IORoutines.flushFile(pop(machine).toWFile());   // no retval
                else if(functionName.equals("printFile"))
                    IORoutines.printFile(pop(machine).toWFile(), pop(machine).toStr()); // no retval
                else if(functionName.equals("printlnFile"))
                    IORoutines.printlnFile(pop(machine).toWFile(), pop(machine).toStr());   // no retval
                else if(functionName.equals("print"))
                    IORoutines.print(pop(machine).toStr()); // no retval
                else if(functionName.equals("println"))
                    IORoutines.println(pop(machine).toStr());   // no retval
                else if(functionName.equals("inputFile"))
                    push(machine, new Value(IORoutines.inputFile(pop(machine).toRFile())));
                else if(functionName.equals("input"))
                    push(machine, new Value(IORoutines.input()));
                else if(functionName.equals("eoi"))
                    push(machine, new Value(IORoutines.eoi()));
                else if(functionName.equals("eof"))
                    push(machine, new Value(IORoutines.eof(pop(machine).toRFile())));
                else if(functionName.equals("pow"))
                    push(machine, new Value(MathRoutines.pow(pop(machine).toReal(), pop(machine).toReal())));
                else if(functionName.equals("sqrt"))
                    push(machine, new Value(MathRoutines.sqrt(pop(machine).toReal())));
                else if(functionName.equals("log"))
                    push(machine, new Value(MathRoutines.log(pop(machine).toReal())));
                else if(functionName.equals("rand"))
                    push(machine, new Value(MathRoutines.rand(pop(machine).toInt())));
                else
                    throw new Exception("Runtime error: function '" + functionName + "' doesn't exist!");
                //
                machine.IC++;
                break;
            }
            case Code.CALL: // user-defined functions
            {
                Stack<Value> args = new Stack<Value>();
                // important! pick fn args from the stack and set them on the top of the stack after the insertion of the control data
                for(int i = 0, im = Functions.functions.get(functionName).parameters.expressions.size(); i < im; i++)
                    args.push((Value)machine.memory.stack.pop());
                //
                // create a new stack-frame
                // --save &set SB
                machine.memory.stack.push(machine.SB);
                machine.SB = machine.memory.stack.size() + 2;
                // --save IC
                machine.memory.stack.push(machine.IC + 1);
                // --save heap
                machine.memory.stack.push(machine.memory.heap);
                machine.memory.heap = new HashMap<String, Value>();
                // set IC
                machine.IC = Functions.fnIC.get(functionName);
                //
                // push args!
                while(!args.empty())
                    machine.memory.stack.push((Value)args.pop());
                //
                break;
            }
        }
        return true;
    }
}
