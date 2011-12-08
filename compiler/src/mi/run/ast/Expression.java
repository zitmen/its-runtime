package mi.run.ast;

public abstract class Expression extends Node
{
    public int exprDataType = DataType.INVALID;
    public String resultVariable = null;
    public String functionName;
    
    public abstract int evalDatatype();
}
