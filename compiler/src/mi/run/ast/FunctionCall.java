package mi.run.ast;

import java.util.ArrayList;
import mi.run.bytecode.CallInstr;
import mi.run.bytecode.Code;
import mi.run.bytecode.Instruction;
import mi.run.semantic.Functions;
import mi.run.semantic.Variables;

public class FunctionCall extends Expression
{
    public FunctionDefinition function;
    public ExpressionList parameters;
    private boolean isDefined;  // if it's not then it's a built-in function
    private String callerName;
    private DataType return_type;
    
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
        callerName = Functions.actualFunction;
        removeNulls();
        parameters.semanticCheck();
        function = (FunctionDefinition)Functions.functions.get(functionName);
        int nParams;
        if(function == null)
        {   // check built-in functions
            function = Functions.builtInFunctions.get(functionName);
            if(function == null)
                throw new Exception("SEMANTIC ERROR: the function '" + functionName + "' does not exist!");
            //
            if(functionName.equals("cloneArray"))  // returns Array, but what item type?!
            {   // returned type is the same as type of the parameter
                return_type = ((Variable)(parameters.expressions.get(0))).type;
            }
            else
            {
                return_type = function.type;
            }
            isDefined = false;
        }
        else
        {
            return_type = function.type;
            isDefined = true;
        }
        // check arguments count
        nParams = 0;
        if((parameters.expressions == null) && (function.parameters.expressions != null))
            throw new Exception("SEMANTIC ERROR: the function '" + functionName + "' doesn't take 0 argument(s)!");
        else if((parameters.expressions != null) && (function.parameters.expressions == null))
            throw new Exception("SEMANTIC ERROR: the function '" + functionName + "' doesn't take " + (parameters.expressions.size()) + " argument(s)!");
        else if((nParams = parameters.expressions.size()) != function.parameters.expressions.size())
            throw new Exception("SEMANTIC ERROR: the function '" + functionName + "' doesn't take " + nParams + " argument(s)!");
        // check arguments types
        for(int i = 0; i < nParams; i++)
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
        stream = stream.last().append(new CallInstr((isDefined ? Code.CALL : Code.INVOKE), functionName, parameters.expressions));
        if(function.type.type == DataType.VOID)
        {   // void is never stored anywhere
            resultVariable = functionName;
        }
        else
        {   // others are stored at the stack
            resultVariable = Variables.addVar(callerName, "tmp", return_type);
            stream = stream.last().append(new CallInstr(Code.POP, resultVariable, new ArrayList<Expression>()));
        }
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
