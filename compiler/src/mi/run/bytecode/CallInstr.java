package mi.run.bytecode;

import java.util.ArrayList;
import mi.run.ast.Expression;
import mi.run.runtime.Interpreter;

public class CallInstr extends Instruction
{   // CALL, INVOKE
    public String functionName;
    public ArrayList<Expression> parameters;
    
    public CallInstr(int code, String fnName, ArrayList<Expression> params)
    {
        super(code);
        functionName = fnName;
        parameters = params;
    }

    @Override
    public String toString()
    {
        String str = super.toString() + " " + functionName;
        for(int i = 0, im = parameters.size(); i < im; i++)
            str += " " + parameters.get(i).resultVariable;
        return str;
    }
    
    @Override
    public boolean interpret(Interpreter machine) throws Exception
    {
        throw new Exception("NOT SUPPORTED!");
    }
}
