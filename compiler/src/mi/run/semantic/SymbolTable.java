package mi.run.semantic;

import java.util.HashMap;
import java.util.Stack;
import mi.run.ast.DataType;

public class SymbolTable
{
    private static Stack<HashMap<String, DataType> > m_table = new Stack<HashMap<String, DataType> >();
    
    public static void declare(String name, DataType type)
    {
        m_table.peek().put(name, type);
    }
    
    public static void stepIn()
    {
        m_table.push(new HashMap<String, DataType>());
    }
    
    public static void stepOut()
    {
        m_table.pop();
    }
    
    public static DataType isDeclared(String name)
    {
        DataType retval = null;
        for(HashMap<String, DataType> map : m_table)
        {
            if((retval = (DataType)map.get(name))!= null)
                break;
        }
        return retval;
    }
    
    public static DataType isDeclaredInThisScope(String name)
    {
        return m_table.peek().get(name);
    }

    public static HashMap<String, DataType> getThisScopeDeclarations()
    {
        return m_table.peek();
    }
}
