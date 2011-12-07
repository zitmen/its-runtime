package mi.run.runtime.built_in_routines;

import java.util.ArrayList;
import mi.run.runtime.Value;

public class ArrayRoutines
{
    public static ArrayList<Value> cloneArray(ArrayList<Value> arr)
    {
        ArrayList<Value> new_arr = new ArrayList<Value>();
        //
        Value val;
        for(int i = 0, im = arr.size(); i < im; i++)
        {
            try {
                val = (Value)arr.get(i);
                switch(val.type())
                {
                    case Value.INTEGER: new_arr.add(new Value(val.toInt())); break;
                    case Value.REAL   : new_arr.add(new Value(val.toReal())); break;
                    case Value.BOOLEAN: new_arr.add(new Value(val.toBool())); break;
                    case Value.STRING : new_arr.add(new Value(val.toStr().substring(0))); break;
                    case Value.ARRAY  : new_arr.add(new Value(cloneArray(val.toArray()))); break;
                    case Value.RFILE  :
                    case Value.WFILE  : new_arr.add(val); break;
                }
            } catch (Exception ex) {
                //
            }
        }
        return new_arr;
    }
    
    public static void clearArray(ArrayList<Value> arr)
    {
        arr.clear();
    }
    
    public static int length(ArrayList<Value> arr)
    {
        return arr.size();
    }
}
