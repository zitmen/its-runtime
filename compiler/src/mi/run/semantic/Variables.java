package mi.run.semantic;

import java.util.HashMap;
import mi.run.ast.DataType;

public class Variables
{
    private static HashMap<String, HashMap<String, DataType> > vars = new HashMap<String, HashMap<String, DataType> >();
    private static HashMap<String, String> uniqueNames = new HashMap<String, String>();
    private static int uniqueCounter = 0;
    
    public static String addVar(String fnName, String varName, DataType varType)
    {
        HashMap<String, DataType> fnVars = vars.get(fnName);
        if(fnVars == null)
        {
            fnVars = new HashMap<String, DataType>();
            fnVars.put(varName, varType);
            vars.put(fnName, fnVars);
        }
        else
            fnVars.put(varName, varType);
        //
        String uniqName = "var" + uniqueCounter;
        uniqueNames.put(varName, uniqName);
        uniqueCounter++;
        //
        return uniqName;
    }

    public static String getVarFnUniqueName(String name)
    {
        return uniqueNames.get(name);
    }
    
    public static HashMap<String, DataType> getVarsInFn(String fnName)
    {
        return vars.get(fnName);
    }
}
