package mi.run.semantic;

import java.util.ArrayList;
import java.util.HashMap;
import mi.run.ast.DataType;
import mi.run.ast.ExpressionList;
import mi.run.ast.FunctionCall;
import mi.run.ast.FunctionDefinition;
import mi.run.ast.Variable;

public class Functions
{
    public static HashMap<String, ArrayList<FunctionCall> > fnCallers = new HashMap<String, ArrayList<FunctionCall> >();    // who calls the function?
    //
    public static HashMap<String, FunctionDefinition> functions = new HashMap<String, FunctionDefinition>();    // all fn definitions
    public static HashMap<String, Integer> fnIC = new HashMap<String, Integer>();   // what IC jump to, when calling a function?
    //
    public static HashMap<String, FunctionDefinition> builtInFunctions = new HashMap<String, FunctionDefinition>();   // all built-in fn definitions
    //
    public static String actualFunction = null;
    
    public static void init()
    {
        //
        // StringRoutines
        ExpressionList params = new ExpressionList();
        params.expressions.add(new Variable("haystack", new DataType(DataType.STRING)));
        params.expressions.add(new Variable("needle", new DataType(DataType.STRING)));
        builtInFunctions.put("indexOf", new FunctionDefinition(new DataType(DataType.INTEGER), "indexOf", params, null));
        //
        params = new ExpressionList();
        params.expressions.add(new Variable("haystack", new DataType(DataType.STRING)));
        params.expressions.add(new Variable("needle", new DataType(DataType.STRING)));
        builtInFunctions.put("lastIndexOf", new FunctionDefinition(new DataType(DataType.INTEGER), "lastIndexOf", params, null));
        //
        params = new ExpressionList();
        params.expressions.add(new Variable("str", new DataType(DataType.STRING)));
        params.expressions.add(new Variable("start", new DataType(DataType.INTEGER)));
        params.expressions.add(new Variable("end", new DataType(DataType.INTEGER)));
        builtInFunctions.put("substring", new FunctionDefinition(new DataType(DataType.STRING), "substring", params, null));
        //
        params = new ExpressionList();
        params.expressions.add(new Variable("str", new DataType(DataType.STRING)));
        builtInFunctions.put("toLower", new FunctionDefinition(new DataType(DataType.STRING), "toLower", params, null));
        //
        params = new ExpressionList();
        params.expressions.add(new Variable("str", new DataType(DataType.STRING)));
        builtInFunctions.put("toUpper", new FunctionDefinition(new DataType(DataType.STRING), "toUpper", params, null));
        //
        params = new ExpressionList();
        params.expressions.add(new Variable("str", new DataType(DataType.STRING)));
        builtInFunctions.put("trim", new FunctionDefinition(new DataType(DataType.STRING), "trim", params, null));
        //
        params = new ExpressionList();
        params.expressions.add(new Variable("str", new DataType(DataType.STRING)));
        params.expressions.add(new Variable("prefix", new DataType(DataType.STRING)));
        builtInFunctions.put("startsWith", new FunctionDefinition(new DataType(DataType.BOOL), "startsWith", params, null));
        //
        params = new ExpressionList();
        params.expressions.add(new Variable("str", new DataType(DataType.STRING)));
        params.expressions.add(new Variable("suffix", new DataType(DataType.STRING)));
        builtInFunctions.put("endsWith", new FunctionDefinition(new DataType(DataType.BOOL), "endsWith", params, null));
        //
        params = new ExpressionList();
        params.expressions.add(new Variable("str1", new DataType(DataType.STRING)));
        params.expressions.add(new Variable("str2", new DataType(DataType.STRING)));
        builtInFunctions.put("concat", new FunctionDefinition(new DataType(DataType.STRING), "concat", params, null));
        //
        params = new ExpressionList();
        params.expressions.add(new Variable("str", new DataType(DataType.STRING)));
        builtInFunctions.put("strlen", new FunctionDefinition(new DataType(DataType.INTEGER), "strlen", params, null));
        //
        // TypeCastRoutines
        params = new ExpressionList();
        params.expressions.add(new Variable("i", new DataType(DataType.INTEGER)));
        builtInFunctions.put("int2str", new FunctionDefinition(new DataType(DataType.STRING), "int2str", params, null));
        //
        params = new ExpressionList();
        params.expressions.add(new Variable("s", new DataType(DataType.STRING)));
        builtInFunctions.put("str2int", new FunctionDefinition(new DataType(DataType.INTEGER), "str2int", params, null));
        //
        params = new ExpressionList();
        params.expressions.add(new Variable("d", new DataType(DataType.DOUBLE)));
        builtInFunctions.put("double2str", new FunctionDefinition(new DataType(DataType.STRING), "double2str", params, null));
        //
        params = new ExpressionList();
        params.expressions.add(new Variable("s", new DataType(DataType.STRING)));
        builtInFunctions.put("str2double", new FunctionDefinition(new DataType(DataType.DOUBLE), "str2double", params, null));
        //
        params = new ExpressionList();
        params.expressions.add(new Variable("i", new DataType(DataType.INTEGER)));
        builtInFunctions.put("int2double", new FunctionDefinition(new DataType(DataType.DOUBLE), "int2double", params, null));
        //
        params = new ExpressionList();
        params.expressions.add(new Variable("d", new DataType(DataType.DOUBLE)));
        builtInFunctions.put("double2int", new FunctionDefinition(new DataType(DataType.INTEGER), "double2int", params, null));
        //
        // ArrayRoutines
        params = new ExpressionList();
        params.expressions.add(new Variable("arr", new DataType(DataType.ARRAY)));
        builtInFunctions.put("cloneArray", new FunctionDefinition(new DataType(DataType.ARRAY), "cloneArray", params, null));
        //
        params = new ExpressionList();
        params.expressions.add(new Variable("arr", new DataType(DataType.ARRAY)));
        builtInFunctions.put("clearArray", new FunctionDefinition(new DataType(DataType.VOID), "clearArray", params, null));
        //
        params = new ExpressionList();
        params.expressions.add(new Variable("arr", new DataType(DataType.ARRAY)));
        builtInFunctions.put("length", new FunctionDefinition(new DataType(DataType.INTEGER), "length", params, null));
        //
        // IORoutines
        params = new ExpressionList();
        params.expressions.add(new Variable("fname", new DataType(DataType.STRING)));
        builtInFunctions.put("openRFile", new FunctionDefinition(new DataType(DataType.FILE), "openRFile", params, null));
        //
        params = new ExpressionList();
        params.expressions.add(new Variable("fname", new DataType(DataType.STRING)));
        params.expressions.add(new Variable("append", new DataType(DataType.BOOL)));
        builtInFunctions.put("openWFile", new FunctionDefinition(new DataType(DataType.FILE), "openWFile", params, null));
        //
        params = new ExpressionList();
        params.expressions.add(new Variable("f", new DataType(DataType.FILE)));
        builtInFunctions.put("closeFile", new FunctionDefinition(new DataType(DataType.VOID), "closeFile", params, null));
        //
        params = new ExpressionList();
        params.expressions.add(new Variable("f", new DataType(DataType.FILE)));
        builtInFunctions.put("flushFile", new FunctionDefinition(new DataType(DataType.VOID), "flushFile", params, null));
        //
        params = new ExpressionList();
        params.expressions.add(new Variable("f", new DataType(DataType.FILE)));
        params.expressions.add(new Variable("str", new DataType(DataType.STRING)));
        builtInFunctions.put("printFile", new FunctionDefinition(new DataType(DataType.VOID), "printFile", params, null));
        //
        params = new ExpressionList();
        params.expressions.add(new Variable("f", new DataType(DataType.FILE)));
        params.expressions.add(new Variable("str", new DataType(DataType.STRING)));
        builtInFunctions.put("printlnFile", new FunctionDefinition(new DataType(DataType.VOID), "printlnFile", params, null));
        //
        params = new ExpressionList();
        params.expressions.add(new Variable("str", new DataType(DataType.STRING)));
        builtInFunctions.put("print", new FunctionDefinition(new DataType(DataType.VOID), "print", params, null));
        //
        params = new ExpressionList();
        params.expressions.add(new Variable("str", new DataType(DataType.STRING)));
        builtInFunctions.put("println", new FunctionDefinition(new DataType(DataType.VOID), "println", params, null));
        //
        params = new ExpressionList();
        params.expressions.add(new Variable("f", new DataType(DataType.FILE)));
        builtInFunctions.put("inputFile", new FunctionDefinition(new DataType(DataType.STRING), "inputFile", params, null));
        //
        params = new ExpressionList();
        builtInFunctions.put("input", new FunctionDefinition(new DataType(DataType.STRING), "input", params, null));
        //
        params = new ExpressionList();
        params.expressions.add(new Variable("f", new DataType(DataType.FILE)));
        builtInFunctions.put("eof", new FunctionDefinition(new DataType(DataType.BOOL), "eof", params, null));
        //
        params = new ExpressionList();
        builtInFunctions.put("eoi", new FunctionDefinition(new DataType(DataType.BOOL), "eoi", params, null));
        //
        // MathRoutines
        params = new ExpressionList();
        params.expressions.add(new Variable("x", new DataType(DataType.DOUBLE)));
        params.expressions.add(new Variable("n", new DataType(DataType.DOUBLE)));
        builtInFunctions.put("pow", new FunctionDefinition(new DataType(DataType.DOUBLE), "pow", params, null));
        //
        params = new ExpressionList();
        params.expressions.add(new Variable("x", new DataType(DataType.DOUBLE)));
        builtInFunctions.put("sqrt", new FunctionDefinition(new DataType(DataType.DOUBLE), "sqrt", params, null));
        //
        params = new ExpressionList();
        params.expressions.add(new Variable("x", new DataType(DataType.DOUBLE)));
        builtInFunctions.put("log", new FunctionDefinition(new DataType(DataType.DOUBLE), "log", params, null));
        //
        params = new ExpressionList();
        params.expressions.add(new Variable("modul", new DataType(DataType.DOUBLE)));
        builtInFunctions.put("rand", new FunctionDefinition(new DataType(DataType.INTEGER), "rand", params, null));
    }
}
