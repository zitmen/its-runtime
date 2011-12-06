package mi.run.ast;

import mi.run.bytecode.CallInstr;
import mi.run.bytecode.Code;
import mi.run.bytecode.Instruction;
import mi.run.semantic.Functions;

public class FunctionCall extends Expression
{
    public String functionName;
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
        {   // check arguments count
            if((nParams = parameters.expressions.size()) != Functions.builtInFunctionsArgs.get(functionName))
                throw new Exception("Semantic error: the function '" + functionName + "' doesn't take " + nParams + " argument(s)!");
            else
                isDefined = false;
        }
        else
        {
            isDefined = true;
            // check arguments count
            if((nParams = parameters.expressions.size()) != function.parameters.expressions.size())
                throw new Exception("Semantic error: the function '" + functionName + "' doesn't take " + nParams + " argument(s)!");
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
        if(isDefined)
        {
            stream.last().append(new CallInstr(Code.CALL, functionName));
            /* DO NOT OPRIMIZE THIS, BECAUSE I WOULD HAVE TO DO CHECK IF ANY FLOW PATH EIGHTER RETURNS A VALUE OR NOT! worst case scenario: there will be some not assigned values on the stack
             * The simpliest solution to this is to declare static Interpret::RETVAL and return values via this variable (an alternative to the AX register on x86 machines).
                    // if it is ExpressionStatement or ForStatement, the value could be removed from the stack
                    if((parent instanceof ExpressionStatement) || ((parent instanceof ForStatement) && ((((ForStatement)parent).init == this) || (((ForStatement)parent).iterator == this))))
                        stream = stream.last().append(new Instruction(Code.POP));
             */
        }
        else
        {
            stream.last().append(new CallInstr(Code.INVOKE, functionName));
            // if it is not ExpressionStatement, the return value (if any) could be removed from the stack
            if(parent instanceof ExpressionStatement)
                if(Functions.builtInFunctions.get(functionName) == Boolean.TRUE)
                    stream = stream.last().append(new Instruction(Code.POP));
        }
        return first;
    }
}
