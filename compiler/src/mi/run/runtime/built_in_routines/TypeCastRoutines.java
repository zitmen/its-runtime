package mi.run.runtime.built_in_routines;

import mi.run.runtime.Value;

public class TypeCastRoutines
{
    public static String int2str(int i)
    {
        return string(new Value(i));
    }
    
    public static String double2str(double d)
    {
        return string(new Value(d));
    }
    
    public static int str2int(String s)
    {
        return integer(new Value(s));
    }
    
    public static int double2int(double d)
    {
        return integer(new Value(d));
    }
    
    public static double str2double(String s)
    {
        return real(new Value(s));
    }
    
    public static double int2double(int i)
    {
        return real(new Value(i));
    }
    
    private static String string(Value val)
    {
        try {
            return val.toStr();
        } catch (Exception ex) {
            return "";
        }
    }

    private static int integer(Value val)
    {
        try {
            return val.toInt();
        } catch (Exception ex) {
            return 0;
        }
    }

    private static double real(Value val)
    {
        try {
            return val.toReal();
        } catch (Exception ex) {
            return 0.0;
        }
    }
}
