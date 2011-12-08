package mi.run.ast;

import java.util.ArrayList;
import mi.run.bytecode.Code;
import mi.run.bytecode.Instruction;
import mi.run.bytecode.LoadStoreInstr;
import mi.run.semantic.Functions;
import mi.run.semantic.Structures;
import mi.run.semantic.SymbolTable;
import mi.run.semantic.Variables;

public class Variable extends Atom
{
    public String name;
    public Expression value;
    public DataType type;
    public ArrayList<Expression> members;
    public boolean isThisdeclaration;
    
    public String uniqName;
    
    
    public Variable(String name)
    {
        value = null;
        this.name = name;
        members = new ArrayList<Expression>();
        isThisdeclaration = false;
    }
    
    public Variable(String name, DataType type)
    {
        this.type = type;
        value = null;
        this.name = name;
        members = new ArrayList<Expression>();
        isThisdeclaration = true;
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
            //
            if(Functions.actualFunction != null)    // inside of a function?
                uniqName = Variables.getVarFnUniqueName(name);
        }
        else
        {
            if(SymbolTable.isDeclaredInThisScope(name) != null)
                throw new Exception("SEMANTIC ERROR: redeclared identifier '" + name + "'!");
            // declare
            SymbolTable.declare(name, type);
            //
            if(Functions.actualFunction != null)    // inside of a function?
                uniqName = Variables.addVar(functionName = Functions.actualFunction, name, type);
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
        if(value != null)
        {
            Instruction stream = value.genByteCode();
            stream.last().append(new LoadStoreInstr(Code.ST, uniqName, value.resultVariable));
            return stream.first();
        }
        resultVariable = uniqName;
        return new Instruction(Code.NOOP);
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
