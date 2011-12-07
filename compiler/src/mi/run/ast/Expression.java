package mi.run.ast;

public abstract class Expression extends Node
{
    public int exprDataType = DataType.INVALID;
    
    public abstract int evalDatatype();
}
