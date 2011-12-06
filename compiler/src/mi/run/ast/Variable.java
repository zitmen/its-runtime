package mi.run.ast;

import java.util.ArrayList;
import mi.run.bytecode.Code;
import mi.run.bytecode.Instruction;
import mi.run.bytecode.LoadStoreArrayInstr;
import mi.run.bytecode.LoadStoreInstr;

public class Variable extends Atom
{
    public String name;
    public Expression value;
    public DataType type;
    public ArrayList<Expression> members;
    
    public Variable(String name)
    {
        value = null;
        this.name = name;
        members = new ArrayList<Expression>();
    }
    
    public Variable(String name, DataType type)
    {
        this.type = type;
        value = null;
        this.name = name;
        members = new ArrayList<Expression>();
    }
    
    public Variable()
    {
        type = null;
        value = null;
        name = null;
        members = new ArrayList<Expression>();
    }
    
    @Override
    public String toString()
    {
        return "(Variable " + name + " " + members + " " + type + " " + value + ")";
    }
    
    @Override
    public void semanticCheck() throws Exception
    {
        if(value != null)
            value.semanticCheck();
        //
        for(int i = 0, im = members.size(); i < im; i++)
            if(members.get(i) != null)
                members.get(i).semanticCheck();
    }

    @Override
    public Node optimize() throws Exception
    {
        if(value != null)
            value = (Expression)value.optimize();
        //
        for(int i = 0, im = members.size(); i < im; i++)
            if(members.get(i) != null)
                members.set(i, (Expression)members.get(i).optimize());
        //
        return this;
    }
    
    @Override
    public Instruction genByteCode()
    {
        throw new UnsupportedOperationException("Not supported yet.");
        /*
        if(isArray)
        {
            boolean dump_indices = false;
            for(int i = 0, im = members.size(); i < im; i++)
            {
                if(!(members.get(i) instanceof StringAtom) && !((members.get(i) instanceof IntegerAtom)))
                {
                    dump_indices = true;
                    break;
                }
            }
            if(dump_indices)    // dump + reverse indices (stack is LIFO)
            {
                Instruction stream = new Instruction(Code.NOOP);   // helper
                Instruction first = stream;
                for(int i = members.size() - 1; i >= 0; i--)
                    stream = stream.last().append(members.get(i).genByteCode());
                stream.last().append(new LoadStoreArrayInstr(Code.LDAI, members.size(), new Variable(name, isArray)));
                //
                return first;
            }
            else
                return new LoadStoreInstr(Code.LDA, this);
        }
        else
            return new LoadStoreInstr(Code.LD, this);
        */
    }
}
