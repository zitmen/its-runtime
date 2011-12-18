package mi.run.ast;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import mi.run.bytecode.Code;
import mi.run.bytecode.Instruction;
import mi.run.semantic.Functions;
import mi.run.semantic.SymbolTable;
import mi.run.semantic.Variables;

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
        removeNulls();
    }

    @Override
    public String toString()
    {
        return "(FunctionDefinition " + type + " " + name + " " + parameters + " " + body.toString() + ")";
    }
    
    public String getSignature()
    {
        String sig = "FUNCTION <" + type.getSignature() + "> " + name + " [ ";
        // params
        for(int i = 0, im = parameters.expressions.size(); i < im; i++)
        {
            sig += ((Variable)parameters.expressions.get(i)).type.getSignature() + " ";
            sig += ((Variable)parameters.expressions.get(i)).uniqName + " ";
        }
        sig += "] [ ";
        // declared variables
        Iterator it = Variables.getVarsInFn(name).entrySet().iterator();
        Map.Entry pair;
        while(it.hasNext())
        {
            pair = (Entry)it.next();
            sig += ((DataType)pair.getValue()).getSignature() + " ";
            sig += ((String)pair.getKey()) + " ";
        }
        sig += "]";
        return sig;
    }
    
    @Override
    public Node optimize() throws Exception
    {
        parameters = (ExpressionList)parameters.optimize();
        body = (BlockStatement)body.optimize();
        return this;
    }

    @Override
    public Instruction genByteCode()
    {
        Instruction stream = new Instruction(Code.NOOP);   // helper
        Instruction first = stream.first();
        // body
        stream = stream.last().append(body.genByteCode());
        // RET iff body.last != (RET || RETV)
        stream = stream.last();
        if((stream.code != Code.RET) && (stream.code != Code.RETV))
            stream = stream.append(new Instruction(Code.RET));
        //
        return first;
    }

    @Override
    public void semanticCheck() throws Exception
    {
        Functions.actualFunction = name;
        SymbolTable.stepIn();
        parameters.semanticCheck();
        body.semanticCheck();
        SymbolTable.stepOut();
        Functions.actualFunction = null;
    }
}
