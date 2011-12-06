package mi.run.ast;

import mi.run.bytecode.Code;
import mi.run.bytecode.Instruction;
import mi.run.bytecode.LoadStoreInstr;

public class FunctionDefinition extends Node
{
    public DataType type;
    public String name;
    public ExpressionList parameters;
    public BlockStatement body;
    
    private void removeNulls()
    {
        type = ((type == null) ? new DataType(DataType.VOID) : type);
        name = ((name == null) ? new String() : name);
        parameters = ((parameters == null) ? (new ExpressionList()) : parameters);
        body = ((body == null) ? (new BlockStatement()) : body);
    }
    
    public FunctionDefinition(DataType type, String name, ExpressionList parameters, BlockStatement body)
    {
        this.type = type;
        this.name = name;
        this.parameters = parameters;
        this.body = body;
    }

    @Override
    public String toString()
    {
        removeNulls();
        return "(FunctionDefinition " + type + " " + name + " " + parameters + " " + body.toString() + ")";
    }
    
    @Override
    public Node optimize() throws Exception
    {
        removeNulls();
        parameters = (ExpressionList)parameters.optimize();
        body = (BlockStatement)body.optimize();
        return this;
    }

    @Override
    public Instruction genByteCode()
    {
        removeNulls();
        // all parameters are strictly 'flat' Variables (no arrays) --> it's defined in syntax
        // -- can't just call parameters.genByteCode(), because it would generate LOAD instructions
        //    and here are needed STORE instructions
        Instruction stream = new Instruction(Code.NOOP);   // helper
        Instruction first = stream;
        if(parameters != null)
        {
            for (int i = 0, im = parameters.expressions.size(); i < im; i++)
                stream = stream.last().append(new LoadStoreInstr(Code.ST, (Variable)parameters.expressions.get(i)));
        }
        // body
        stream = stream.last().append(body.genByteCode());
        // RET iff body.last != RET
        stream = stream.last();
        if(stream.code != Code.RET)
            stream = stream.append(new Instruction(Code.RET));
        //
        return first;
    }

    @Override
    public void semanticCheck() throws Exception
    {
        removeNulls();
        parameters.semanticCheck();
        body.semanticCheck();
    }
}
