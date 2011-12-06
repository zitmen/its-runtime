package mi.run.ast;

import mi.run.bytecode.Instruction;

public class DataType extends Node
{
    public static final int ARRAY     = 1;
    public static final int INTEGER   = 2;
    public static final int DOUBLE    = 3;
    public static final int STRING    = 4;
    public static final int FILE      = 5;
    public static final int STRUCTURE = 6;
    public static final int VOID      = 7;
    public static final int BOOL      = 8;
    
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
    public String toString()
    {
        throw new UnsupportedOperationException("Not supported yet.");
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
