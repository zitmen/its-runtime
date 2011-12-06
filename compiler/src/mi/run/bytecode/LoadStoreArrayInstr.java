package mi.run.bytecode;

import mi.run.ast.Variable;
import mi.run.runtime.Interpreter;
import mi.run.runtime.Value;
import mi.run.semantic.TypeCast;

public class LoadStoreArrayInstr extends Instruction
{   //LDAI,STAI,STAIK
    public Variable array;
    public int indices;
    
    public LoadStoreArrayInstr(int code, int indices, Variable arr)
    {
        super(code);
        array = arr;
        this.indices = indices;
    }
    
    @Override
    public String toString()
    {
        return super.toString() + " " + indices + " " + array;
    }
    
    @Override
    public boolean interpret(Interpreter machine) throws Exception
    {
        array.members.clear();  // members are read from the stack, so it must be empty before the call
        //
        Value val;
        for(int i = 0; i < indices; i++)
        {
            val = (Value)machine.memory.stack.pop();
            if(val.type() == Value.STRING)
                array.members.add(TypeCast.createAtom(val.toStr()));
            else
                array.members.add(TypeCast.createAtom(val.toInt()));
        }
        //
        if(code == Code.STAIK)  // keep val on the stack?
            machine.memory.stack.push(machine.memory.stack.peek()); // duplicate the top-most value, because STA removes it
        //
        if((code == Code.LDAI) || (code == Code.LDA) || (code == Code.LD))
            code = Code.LDA;
        else
            code = Code.STA;
        //
        new LoadStoreInstr(code, array).interpret(machine);  // all other work is done in there (including IC++)
        return true;
    }
}
