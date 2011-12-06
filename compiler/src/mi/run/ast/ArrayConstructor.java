package mi.run.ast;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import mi.run.bytecode.Code;
import mi.run.bytecode.Instruction;
import mi.run.bytecode.LoadConstInstr;
import mi.run.bytecode.LoadStoreArrayInstr;
import mi.run.bytecode.LoadStoreInstr;
import mi.run.runtime.Value;

public class ArrayConstructor extends Expression
{
    public static class Item
    {
        public Expression key;
        public Expression value;
        
        public Item(Expression key, Expression value)
        {
            this.key = key;
            this.value = value;
        }
    }
    
    public HashMap<Expression, Expression> items;
    
    public ArrayConstructor()
    {
        items = new HashMap<Expression, Expression>();
    }

    @Override
    public String toString()
    {
        return "(ArrayConstructor " + items + ")";
    }
    
    @Override
    public void semanticCheck() throws Exception
    {
        Iterator it = items.entrySet().iterator();
        Map.Entry pair;
        while(it.hasNext())
        {
            pair = (Map.Entry)it.next();
            ((Expression)pair.getKey()).semanticCheck();
            ((Expression)pair.getValue()).semanticCheck();
        }
    }

    @Override
    public Node optimize() throws Exception
    {
        HashMap<Expression, Expression> optimized = new HashMap<Expression, Expression>();
        Iterator it = items.entrySet().iterator();
        Map.Entry pair;
        Expression key, val;
        while(it.hasNext())
        {
            pair = (Map.Entry)it.next();
            key = (Expression)((Expression)pair.getKey()).optimize();
            val = (Expression)((Expression)pair.getValue()).optimize();
            optimized.put(key, val);
        }
        items.clear();
        items = optimized;
        //
        return this;
    }

    @Override
    public Instruction genByteCode()
    {
        throw new UnsupportedOperationException("Not supported yet.");
        /*
        Instruction stream = new Instruction(Code.NOOP), stream2 = new Instruction(Code.NOOP);   // helpers
        Instruction first = stream, first2 = stream2;
        //
        Map.Entry pair;
        Expression eKey, eVal;
        Iterator it = items.entrySet().iterator();
        // 1. at first check all inner arrays => building array recursively from leafs to root
        while(it.hasNext())
        {
            pair = (Map.Entry)it.next();
            eKey = (Expression)pair.getKey();
            eVal = (Expression)pair.getValue();
            //
            if(eVal instanceof ArrayConstructor)
            {
                stream = stream.last().append(eVal.genByteCode());
                stream = stream.last().append(eKey.genByteCode());
                stream = stream.last().append(new LoadStoreArrayInstr(Code.STAI, 1, new Variable("[]", true)));
                it.remove();
            }
        }
        // 2. then check all other members of the current array
        // 2a. the constant part of the array push to the stack statically
        // 2b. then add other parts that needs to be computed
        HashMap<Object, Value> array = new HashMap<Object, Value>();
        Value val;
        Object key;
        it = items.entrySet().iterator();
        while(it.hasNext())
        {
            pair = (Map.Entry)it.next();
            key = getKey(eKey = (Expression)pair.getKey());
            val = getValue(eVal = (Expression)pair.getValue());
            //
            if((key != null) && (val != null))
                array.put(key, val);
            else
            {
                stream2 = stream2.last().append(eVal.genByteCode());
                stream2 = stream2.last().append(eKey.genByteCode());
                stream2 = stream2.last().append(new LoadStoreArrayInstr(Code.STAI, 1, new Variable("[]", true)));
            }
        }
        // prepend constant elements if any
        if(array.size() > 0)
        {
            stream = stream.last().append(new LoadConstInstr(array));
            stream = stream.last().append(new LoadStoreInstr(Code.STAV, new Variable("[]", true)));
        }
        // join second stream to the first
        stream = stream.last().append(first2);
        //
        // if it is not assign statement or foor loop, the value has to be kept on the stack!!
        if(!((parent instanceof ExpressionStatement) || ((parent instanceof ForStatement) && ((((ForStatement)parent).init == this) || (((ForStatement)parent).iterator == this)))))
        {
            if(items.isEmpty())
                stream.last().append(new LoadConstInstr(new HashMap<Object, Value>())); // empty array
            else
                stream.last().append(new LoadStoreInstr(Code.LDR, new Variable("[]", true)));
        }
        return first;
        */
    }
    
    private Object getKey(Expression key)
    {
        if(key instanceof BooleanAtom)
            return (((BooleanAtom)key).value ? 1 : 0);
        else if(key instanceof IntegerAtom)
            return ((IntegerAtom)key).value;
        else if(key instanceof RealAtom)
            return ((int)((RealAtom)key).value);
        else if(key instanceof StringAtom)
            return ((StringAtom)key).value;
        //
        return null;
    }
    
    private Value getValue(Expression val)
    {
        if(val instanceof BooleanAtom)
            return new Value(((BooleanAtom)val).value);
        else if(val instanceof IntegerAtom)
            return new Value(((IntegerAtom)val).value);
        else if(val instanceof RealAtom)
            return new Value(((RealAtom)val).value);
        else if(val instanceof StringAtom)
            return new Value(((StringAtom)val).value);
        //
        return null;
    }
}
