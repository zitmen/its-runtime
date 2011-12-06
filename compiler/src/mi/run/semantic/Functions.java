package mi.run.semantic;

import java.util.ArrayList;
import java.util.HashMap;
import mi.run.ast.FunctionCall;
import mi.run.ast.FunctionDefinition;

public class Functions
{
    public static HashMap<String, ArrayList<FunctionCall> > fnCallers = new HashMap<String, ArrayList<FunctionCall> >();    // who calls the function?
    public static HashMap<String, FunctionDefinition> functions = new HashMap<String, FunctionDefinition>();    // all fn definitions
    public static HashMap<String, Boolean> builtInFunctions = new HashMap<String, Boolean>();   // does the function return a value?
    public static HashMap<String, Integer> builtInFunctionsArgs = new HashMap<String, Integer>();   // how many arguments does the function have?
    public static HashMap<String, Integer> fnIC = new HashMap<String, Integer>();   // what IC jump to, when calling a function?
    
    public static void init()
    {
        builtInFunctionsArgs.put("indexOf", 2);
        builtInFunctionsArgs.put("lastIndexOf", 2);
        builtInFunctionsArgs.put("substring", 3);
        builtInFunctionsArgs.put("toLower", 1);
        builtInFunctionsArgs.put("toUpper", 1);
        builtInFunctionsArgs.put("trim", 1);
        builtInFunctionsArgs.put("startsWith", 2);
        builtInFunctionsArgs.put("endsWith", 2);
        //
        builtInFunctionsArgs.put("string", 1);
        builtInFunctionsArgs.put("integer", 1);
        builtInFunctionsArgs.put("real", 1);
        //
        builtInFunctionsArgs.put("length", 1);
        //
        builtInFunctionsArgs.put("cloneArray", 1);
        builtInFunctionsArgs.put("clearArray", 1);
        builtInFunctionsArgs.put("containsKey", 2);
        builtInFunctionsArgs.put("getKeys", 1);
        //
        builtInFunctionsArgs.put("openRFile", 1);
        builtInFunctionsArgs.put("openWFile", 2);
        builtInFunctionsArgs.put("closeFile", 1);
        builtInFunctionsArgs.put("flushFile", 1);
        builtInFunctionsArgs.put("printFile", 2);
        builtInFunctionsArgs.put("printlnFile", 2);
        builtInFunctionsArgs.put("print", 1);
        builtInFunctionsArgs.put("println", 1);
        builtInFunctionsArgs.put("inputFile", 1);
        builtInFunctionsArgs.put("input", 0);
        builtInFunctionsArgs.put("eof", 1);
        builtInFunctionsArgs.put("eoi", 0);
        //
        builtInFunctionsArgs.put("isString", 1);
        builtInFunctionsArgs.put("isArray", 1);
        builtInFunctionsArgs.put("isInteger", 1);
        builtInFunctionsArgs.put("isReal", 1);
        builtInFunctionsArgs.put("isFile", 1);
        //
        builtInFunctionsArgs.put("pow", 2);
        builtInFunctionsArgs.put("sqrt", 1);
        builtInFunctionsArgs.put("log", 1);
        //
        //
        //
        //
        //
        builtInFunctions.put("indexOf", Boolean.TRUE);
        builtInFunctions.put("lastIndexOf", Boolean.TRUE);
        builtInFunctions.put("substring", Boolean.TRUE);
        builtInFunctions.put("toLower", Boolean.TRUE);
        builtInFunctions.put("toUpper", Boolean.TRUE);
        builtInFunctions.put("trim", Boolean.TRUE);
        builtInFunctions.put("startsWith", Boolean.TRUE);
        builtInFunctions.put("endsWith", Boolean.TRUE);
        //
        builtInFunctions.put("string", Boolean.TRUE);
        builtInFunctions.put("integer", Boolean.TRUE);
        builtInFunctions.put("real", Boolean.TRUE);
        //
        builtInFunctions.put("length", Boolean.TRUE);
        //
        builtInFunctions.put("cloneArray", Boolean.TRUE);
        builtInFunctions.put("clearArray", Boolean.FALSE);
        builtInFunctions.put("containsKey", Boolean.TRUE);
        builtInFunctions.put("getKeys", Boolean.TRUE);
        //
        builtInFunctions.put("openRFile", Boolean.TRUE);
        builtInFunctions.put("openWFile", Boolean.TRUE);
        builtInFunctions.put("closeFile", Boolean.FALSE);
        builtInFunctions.put("flushFile", Boolean.FALSE);
        builtInFunctions.put("printFile", Boolean.FALSE);
        builtInFunctions.put("printlnFile", Boolean.FALSE);
        builtInFunctions.put("print", Boolean.FALSE);
        builtInFunctions.put("println", Boolean.FALSE);
        builtInFunctions.put("inputFile", Boolean.TRUE);
        builtInFunctions.put("input", Boolean.TRUE);
        builtInFunctions.put("eof", Boolean.TRUE);
        builtInFunctions.put("eoi", Boolean.TRUE);
        //
        builtInFunctions.put("isString", Boolean.TRUE);
        builtInFunctions.put("isArray", Boolean.TRUE);
        builtInFunctions.put("isInteger", Boolean.TRUE);
        builtInFunctions.put("isReal", Boolean.TRUE);
        builtInFunctions.put("isFile", Boolean.TRUE);
        //
        builtInFunctions.put("pow", Boolean.TRUE);
        builtInFunctions.put("sqrt", Boolean.TRUE);
        builtInFunctions.put("log", Boolean.TRUE);
    }
}
