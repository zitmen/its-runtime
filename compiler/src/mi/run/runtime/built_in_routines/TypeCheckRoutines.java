package mi.run.runtime.built_in_routines;

import mi.run.runtime.Value;

public class TypeCheckRoutines
{
    public static boolean isString(Value val)
    {
        return (val.type() == Value.STRING);
    }
    
    public static boolean isArray(Value val)
    {
        return (val.type() == Value.ARRAY);
    }
    
    public static boolean isInteger(Value val)
    {
        return (val.type() == Value.INTEGER);
    }
    
    public static boolean isReal(Value val)
    {
        return (val.type() == Value.REAL);
    }
    
    public static boolean isFile(Value val)
    {
        return ((val.type() == Value.RFILE) || (val.type() == Value.WFILE));
    }
}
