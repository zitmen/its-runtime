package mi.run.ast;

import mi.run.bytecode.Instruction;

public class DataType extends Node
{
    public static final int INVALID   = -1;
    public static final int ARRAY     =  1;
    public static final int INTEGER   =  2;
    public static final int DOUBLE    =  3;
    public static final int STRING    =  4;
    public static final int FILE      =  5;
    public static final int STRUCTURE =  6;
    public static final int VOID      =  7;
    public static final int BOOL      =  8;
    public static final int NEW_EXPR  =  9;
    public static final int NULL      = 10;
    
    public int type;
    public DataType arg;
    public Expression length;
    public String name;
    
    public DataType(int type)
    {
        this.type = type;
        length = null;
    }
    
    public DataType(int type, DataType arg)
    {
        this.type = type;
        this.arg = arg;
        name = null;
        length = null;
    }
    
    public DataType(int type, String name)
    {
        this.type = type;
        this.name = name;
        arg = null;
        length = null;
    }
    
    @Override
    public boolean equals(Object o)
    {
        if(!(o instanceof DataType)) return false;
        DataType t = (DataType)o;
        if(type != t.type) return false;
        if(!name.equals(t.name)) return false;
        if(arg != null)
        {
            if(t.arg == null) return false;
            if(!arg.equals(t.arg)) return false;
        }
        if(length != t.length) return false;
        return true;
    }
    
    @Override
    public String toString()
    {
        String str = "(DataType type=";
        switch(type)
        {
            case DataType.ARRAY: str += "ARRAY"; break;
            case DataType.BOOL: str += "BOOL"; break;
            case DataType.DOUBLE: str += "DOUBLE"; break;
            case DataType.FILE: str += "FILE"; break;
            case DataType.INTEGER: str += "INTEGER"; break;
            case DataType.STRING: str += "STRING"; break;
            case DataType.STRUCTURE: str += "STRUCTURE"; break;
            case DataType.VOID: str += "VOID"; break;
        }
        return str + " name=" + (name==null?"null":name) + " arg=" + (arg==null?"null":arg) + " len=" + (length==null?"null":length) + ")";
    }
    
    public String getSignature()
    {
        String sig;
        switch(type)
        {
            case DataType.ARRAY: sig = "ARRAY<" + arg + ">"; break;
            case DataType.BOOL: sig = "BOOL"; break;
            case DataType.DOUBLE: sig = "DOUBLE"; break;
            case DataType.FILE: sig = "FILE"; break;
            case DataType.INTEGER: sig = "INTEGER"; break;
            case DataType.STRING: sig = "STRING"; break;
            case DataType.STRUCTURE: sig = "STRUCTURE<" + name + ">"; break;
            case DataType.VOID: sig = "VOID"; break;
            default: sig = "ERROR"; break;
        }
        return sig;
    }

    @Override
    public Node optimize() throws Exception
    {
        return this;
    }

    @Override
    public Instruction genByteCode()
    {
        return null;
    }

    @Override
    public void semanticCheck() throws Exception
    {
        //
    }
}
