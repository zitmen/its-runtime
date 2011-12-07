package mi.run.runtime;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.HashMap;

public final class Value
{
    static public final int INTEGER = 1;
    static public final int REAL    = 2;
    static public final int BOOLEAN = 3;
    static public final int STRING  = 4;
    static public final int RFILE   = 5;
    static public final int WFILE   = 6;
    static public final int ARRAY   = 7;
    
    int type;       // data type
    //
    int iVal;       // Integer
    double rVal;    // Real
    boolean bVal;   // Boolean
    String sVal;    // String
    BufferedReader frVal;   // File reader
    BufferedWriter fwVal;   // File Writer
    ArrayList<Value> aVal;   // Array

    public Value(int val)
    {
        set(val);
    }
    public Value(double val)
    {
        set(val);
    }
    
    public Value(boolean val)
    {
        set(val);
    }
    
    public Value(String val)
    {
        set(val);
    }
    
    public Value(BufferedReader fr, BufferedWriter fw)
    {
        set(fr);
        set(fw);
    }
    
    public Value(BufferedReader val)
    {
        set(val);
    }
    
    public Value(BufferedWriter val)
    {
        set(val);
    }
    
    public Value(ArrayList<Value> val)
    {
        set(val);
    }
    
    public int type()
    {
        return type;
    }
    
    public void set(int i)
    {
        type = INTEGER;
        iVal = i;
    }
    
    public void set(double r)
    {
        type = REAL;
        rVal = r;
    }
    
    public void set(boolean b)
    {
        type = BOOLEAN;
        bVal = b;
    }
    
    public void set(String s)
    {
        type = STRING;
        sVal = s;
    }
    
    public void set(BufferedReader f)
    {
        type = RFILE;
        frVal = f;
    }
    
    public void set(BufferedWriter f)
    {
        type = WFILE;
        fwVal = f;
    }
    
    public void set(ArrayList<Value> a)
    {
        type = ARRAY;
        aVal = a;
    }
    
    public int toInt() throws Exception
    {
        switch(type)
        {
            case INTEGER: return iVal;
            case REAL:    return iVal = (int)rVal;
            case BOOLEAN: return iVal = bVal ? 1 : 0;
            case STRING:  return iVal = Integer.parseInt(sVal);
            case ARRAY:   throw new Exception("Runtime: illegal conversion ARRAY -> INTEGER");
            case RFILE:
            case WFILE:   throw new Exception("Runtime: illegal conversion FILE -> INTEGER");
        }
        return 0;   // can't happen
    }
    
    public double toReal() throws Exception
    {
        switch(type)
        {
            case INTEGER: return rVal = (double)iVal;
            case REAL:    return rVal;
            case BOOLEAN: return rVal = bVal ? 1.0 : 0.0;
            case STRING:  return rVal = Double.parseDouble(sVal);
            case ARRAY:   throw new Exception("Runtime: illegal conversion ARRAY -> REAL");
            case RFILE:
            case WFILE:   throw new Exception("Runtime: illegal conversion FILE -> REAL");
        }
        return 0.0;   // can't happen
    }
    
    public boolean toBool() throws Exception
    {
        switch(type)
        {
            case INTEGER: return bVal = (iVal != 0);
            case REAL:    return bVal = (rVal != 0.0);
            case BOOLEAN: return bVal;
            case STRING:  return bVal = Boolean.parseBoolean(sVal);
            case ARRAY:   throw new Exception("Runtime: illegal conversion ARRAY -> BOOLEAN");
            case RFILE:
            case WFILE:   throw new Exception("Runtime: illegal conversion FILE -> BOOLEAN");
        }
        return false;   // can't happen
    }
    
    public String toStr() throws Exception
    {
        switch(type)
        {
            case INTEGER: return sVal = String.valueOf(iVal);
            case REAL:    return sVal = String.valueOf(rVal);
            case BOOLEAN: return sVal = String.valueOf(bVal);
            case STRING:  return sVal;
            case ARRAY:   return sVal = this.aVal.toString();
            case RFILE:
            case WFILE:   throw new Exception("Runtime: illegal conversion FILE -> STRING");
        }
        return null;   // can't happen
    }
    
    public BufferedReader toRFile() throws Exception
    {
        switch(type)
        {
            case INTEGER: throw new Exception("Runtime: illegal conversion INTEGER -> FILE");
            case REAL:    throw new Exception("Runtime: illegal conversion REAL -> FILE");
            case BOOLEAN: throw new Exception("Runtime: illegal conversion BOOLEAN -> FILE");
            case STRING:  throw new Exception("Runtime: illegal conversion STRING -> FILE");
            case ARRAY:   throw new Exception("Runtime: illegal conversion ARRAY -> FILE");
            case RFILE:   return frVal;
        }
        return null;   // WFILE
    }
    
    public BufferedWriter toWFile() throws Exception
    {
        switch(type)
        {
            case INTEGER: throw new Exception("Runtime: illegal conversion INTEGER -> FILE");
            case REAL:    throw new Exception("Runtime: illegal conversion REAL -> FILE");
            case BOOLEAN: throw new Exception("Runtime: illegal conversion BOOLEAN -> FILE");
            case STRING:  throw new Exception("Runtime: illegal conversion STRING -> FILE");
            case ARRAY:   throw new Exception("Runtime: illegal conversion ARRAY -> FILE");
            case WFILE:    return fwVal;
        }
        return null;   // RFILE
    }
    
    public ArrayList<Value> toArray() throws Exception
    {
        switch(type)
        {
            case INTEGER:
            case REAL:
            case BOOLEAN:
            case STRING:
            case RFILE:
            case WFILE:
                if(aVal == null) aVal = new ArrayList<Value>();
                aVal.clear();
                aVal.add(this);
            case ARRAY:
                return aVal;
        }
        return null;   // can't happen
    }
    
    @Override
    public String toString()
    {
        switch(type)
        {
            case INTEGER: return "(INTEGER: " + iVal + ")";
            case REAL:    return "(REAL: " + rVal + ")";
            case BOOLEAN: return "(BOOLEAN: " + bVal + ")";
            case STRING:  return "(STRING: " + sVal + ")";
            case ARRAY:   return "(ARRAY: " + aVal.toString() + ")";
            case RFILE:   return "(FILE: [READ])";
            case WFILE:   return "(FILE: [WRITE])";
        }
        return null;    // can't happen
    }
    
    @Override
    @SuppressWarnings("RedundantStringConstructorCall")
    public Value clone()
    {
        switch(type)
        {
            case INTEGER: return new Value(iVal);
            case REAL   : return new Value(rVal);
            case BOOLEAN: return new Value(bVal);
            case STRING : return new Value(sVal.substring(0));  // substring is there bocause i need copy the string, not the reference
            case ARRAY  : // ad semantics: arrays are not copied; there is function cloneArray
            case RFILE  :
            case WFILE  :
                return this;
        }
        return null;    // can't happen
    }
}
