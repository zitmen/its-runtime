package mi.run.runtime.built_in_routines;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import mi.run.runtime.Value;

public class ArrayRoutines
{
    public static HashMap<Object, Value> cloneArray(HashMap<Object, Value> arr)
    {
        HashMap<Object, Value> new_arr = new HashMap<Object, Value>();
        //
        Iterator it = arr.entrySet().iterator();
        Map.Entry pair;
        Object key;
        Value val;
        while(it.hasNext())
        {
            try {
                pair = (Map.Entry)it.next();
                key = pair.getKey();
                val = (Value)pair.getValue();
                switch(val.type())
                {
                    case Value.INTEGER: new_arr.put(key, new Value(val.toInt())); break;
                    case Value.REAL   : new_arr.put(key, new Value(val.toReal())); break;
                    case Value.BOOLEAN: new_arr.put(key, new Value(val.toBool())); break;
                    case Value.STRING : new_arr.put(key, new Value(val.toStr().substring(0))); break;
                    case Value.ARRAY  : new_arr.put(key, new Value(cloneArray(val.toArray()))); break;
                    case Value.RFILE  :
                    case Value.WFILE  : new_arr.put(key, val); break;
                }
            } catch (Exception ex) {
                //
            }
        }
        return new_arr;
    }
    
    public static void clearArray(HashMap<Object, Value> arr)
    {
        arr.clear();
    }
    
    public static boolean containsKey(HashMap<Object, Value> arr, Value key)
    {
        try {
            return arr.containsKey(key.type() == Value.STRING ? key.toStr() : key.toInt());
        } catch (Exception ex) {
            //
        }
        return false;
    }
    
    public static HashMap<Object, Value> getKeys(HashMap<Object, Value> arr)
    {
        Object [] keys = arr.keySet().toArray();
        HashMap<Object, Value> k = new HashMap<Object, Value>();
        for(int i = 0, im = keys.length; i < im; i++)
        {
            if(keys[i] instanceof String)
                k.put((Integer)i, new Value((String)keys[i]));
            else
                k.put((Integer)i, new Value((Integer)keys[i]));
        }
        return k;
    }
    
    public static int length(HashMap<Object, Value> arr)
    {
        return arr.size();
    }
}
