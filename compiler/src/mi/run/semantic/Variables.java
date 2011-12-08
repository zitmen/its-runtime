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
        String uniqName = "var" + uniqueCounter;
        uniqueNames.put(varName, uniqName);
        uniqueCounter++;
        //
        HashMap<String, DataType> fnVars = vars.get(fnName);
        if(fnVars == null)
        {
            fnVars = new HashMap<String, DataType>();
            vars.put(fnName, fnVars);
        }
        fnVars.put(uniqName, varType);
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
