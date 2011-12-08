package mi.run.ast;

import mi.run.bytecode.CallInstr;
import mi.run.bytecode.Code;
import mi.run.bytecode.Instruction;
import mi.run.semantic.Functions;

public class FunctionCall extends Expression
{
    public FunctionDefinition function;
    public ExpressionList parameters;
    private boolean isDefined;  // if it's not then it's a built-in function
    
    private void removeNulls()
    {
        functionName = ((functionName == null) ? new String() : functionName);
        parameters = ((parameters == null) ? (new ExpressionList()) : parameters);
    }
    
    public FunctionCall(String name, ExpressionList params)
    {
        functionName = name;
        parameters = params;
    }

    @Override
    public String toString()
    {
        removeNulls();
        return "(FunctionCall " + functionName + " " + parameters + ")";
    }
    
    @Override
    public void semanticCheck() throws Exception
    {
        removeNulls();
        parameters.semanticCheck();
        function = (FunctionDefinition)Functions.functions.get(functionName);
        int nParams;
        if(function == null)
        {   // check built-in functions
            function = Functions.builtInFunctions.get(functionName);
            if(function == null)
                throw new Exception("SEMANTIC ERROR: the function '" + functionName + "' does not exist!");
            isDefined = false;
        }
        else
        {
            isDefined = true;
        }
        // check arguments count
        if((nParams = parameters.expressions.size()) != function.parameters.expressions.size())
            throw new Exception("SEMANTIC ERROR: the function '" + functionName + "' doesn't take " + nParams + " argument(s)!");
        // check arguments types
        for(int i = 0, im = parameters.expressions.size(); i < im; i++)
        {
            if(parameters.expressions.get(i).evalDatatype() != function.parameters.expressions.get(i).evalDatatype())
                throw new Exception("SEMANTIC ERROR: the function '" + functionName + "' doesn't take " + (i+1) + ". argument of type that was used!");
        }
    }

    @Override
    public Node optimize() throws Exception
    {
        removeNulls();
        parameters = (ExpressionList)parameters.optimize();
        return this;
    }

    @Override
    public Instruction genByteCode()
    {
        removeNulls();
        //
        Instruction stream = parameters.genByteCode(true);  // params will be saved on the stack
        Instruction first = stream.first();
        stream.last().append(new CallInstr((isDefined ? Code.CALL : Code.INVOKE), functionName, parameters.expressions));
        resultVariable = function.name;
        return first;
    }

    @Override
    public int evalDatatype()
    {
        if(exprDataType == DataType.INVALID)
        {
            if(function == null)
                function = (FunctionDefinition)Functions.functions.get(functionName);
            //
            return (exprDataType = function.type.type);
        }
        else
            return exprDataType;
    }
}
