package mi.run.ast;

import java.util.ArrayList;
import mi.run.bytecode.Code;
import mi.run.bytecode.Instruction;
import mi.run.bytecode.LoadStoreArrayInstr;
import mi.run.bytecode.LoadStoreInstr;
import mi.run.semantic.Structures;
import mi.run.semantic.SymbolTable;

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
        if(type == null)
        {
            if((type = SymbolTable.isDeclared(name)) == null)
                throw new Exception("SEMANTIC ERROR: undeclared identifier '" + name + "'!");
        }
        else
        {
            if(SymbolTable.isDeclaredInThisScope(name) != null)
                throw new Exception("SEMANTIC ERROR: redeclared identifier '" + name + "'!");
            // insert
            SymbolTable.declare(name, type);
        }
        type.semanticCheck();
        exprDataType = type.type;
        //
        StructureDefinition structDef;
        int tid;
        DataType t = type;
        for(int i = 0, im = members.size(); i < im; i++)
        {
            if(members.get(i) != null)
            {
                members.get(i).semanticCheck();
                tid = members.get(i).evalDatatype();
                switch(tid)
                {
                    case DataType.INTEGER:
                        if((t == null) || (t.type != DataType.ARRAY))
                            throw new Exception("SEMANTIC ERROR: invalid index!\n" + toString());
                        t = t.arg;
                        exprDataType = t.type;
                        break;
                        
                    case DataType.STRING:
                        if((t == null) || (t.type != DataType.STRUCTURE) || (!(members.get(i) instanceof StringAtom)))
                            throw new Exception("SEMANTIC ERROR: invalid " + i + ". index!\n" + toString());
                        // TODO: check structure of Structure !!!
                        //String tmp_str = ((StringAtom)members.get(i)).value;
                        //StructureDefinition tmp_struct = Structures.structures.get
                        //if()
                        if((structDef = Structures.structures.get(t.name)) == null)
                            throw new Exception("SEMANTIC ERROR: undefined data type " + t.name + "!");
                        t = structDef.variables.get(((StringAtom)members.get(i)).value);
                        if(t == null)
                            throw new Exception("SEMANTIC ERROR: invalid " + i + ". index!\n" + toString());
                        exprDataType = t.type;
                        break;
                        
                    default:
                        throw new Exception("SEMANTIC ERROR: invalid index type!");
                }
            }
        }
        //
        if(value != null)
        {
            //value.semanticCheck();
            //
            // for simple type checking and value semantic-checking: use BinaryExpression
            BinaryExpression expr = new BinaryExpression(Operator.ASN, new Variable(name), value);
            expr.semanticCheck();
        }
    }

    @Override
    public Node optimize() throws Exception
    {
        if(value != null)
            value = (Expression)value.optimize();
        //
        if(type != null)
            type.optimize();
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
    
    @Override
    public int evalDatatype()
    {
        if(exprDataType == DataType.INVALID)
        {
            exprDataType = type.type;
            DataType t = type;
            for(int i = 0, im = members.size(); i < im; i++)
            {
                if(members.get(i) != null)
                {
                    switch(members.get(i).evalDatatype())
                    {
                        case DataType.INTEGER:
                            t = t.arg;
                            exprDataType = t.type;
                            break;

                        case DataType.STRING:
                            t = Structures.structures.get(t.name).variables.get(((StringAtom)members.get(i)).value);
                            exprDataType = t.type;
                            break;
                    }
                }
            }
        }
        return exprDataType;
    }
}
