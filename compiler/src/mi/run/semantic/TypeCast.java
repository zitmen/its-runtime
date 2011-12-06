package mi.run.semantic;

import mi.run.ast.*;

public class TypeCast
{
    /*************************************************************
     * ATOM FACTORY
     *************************************************************/
    public static Atom createAtom(boolean val)
    {
        return new BooleanAtom(val);
    }
    
    public static Atom createAtom(int val)
    {
        return new IntegerAtom(val);
    }
    
    public static Atom createAtom(double val)
    {
        return new RealAtom(val);
    }
    
    public static Atom createAtom(String val)
    {
        return new StringAtom(val);
    }
    
    /*************************************************************
     * ARITHMETIC OPERATIONS WITH AUTOMATIC TYPE CONVERSIONS INSIDE
     *************************************************************/
    public static Atom builtInArithmetic(int op, Atom operand)
    {
        if(op == Operator.NOT)
        {
            BooleanAtom b = TypeCast.toBoolean(operand);
            b.value = !(b.value);
            return b;
        }
        else if(op == Operator.NEG)
        {
            IntegerAtom i = TypeCast.toInteger(operand);
            i.value = ~(i.value);
            return i;
        }
        else if(op == Operator.MINUS)
        {
            IntegerAtom i = TypeCast.toInteger(operand);
            i.value = -(i.value);
            return i;
        }
        else if(op == Operator.DEC)
        {
            IntegerAtom i = TypeCast.toInteger(operand);
            i.value -= 1;
            return i;
        }
        else if(op == Operator.PLUS)
        {
            IntegerAtom i = TypeCast.toInteger(operand);
            i.value = +(i.value);
            return i;
        }
        else if(op == Operator.INC)
        {
            IntegerAtom i = TypeCast.toInteger(operand);
            i.value += 1;
            return i;
        }
        return null;    // can't happen due to the previous semantic check
    }
    
    public static Atom builtInArithmetic(int op, Atom left, Atom right) throws Exception
    {
        // categorize by operations, then by data types
        if(op == Operator.MINUS)
        {
            if((left instanceof RealAtom) || (right instanceof RealAtom))   // Real
            {
                return new RealAtom(((RealAtom)left).value - ((RealAtom)right).value);
            }
            else    // String, Boolean, Integer
            {
                IntegerAtom l = TypeCast.toInteger(left);
                IntegerAtom r = TypeCast.toInteger(right);
                return new IntegerAtom(l.value - r.value);
            }
        }
        else if(op == Operator.SUB_ASN)
        {
            if((left instanceof RealAtom) || (right instanceof RealAtom))   // Real
            {
                ((RealAtom)left).value -= ((RealAtom)right).value;
                return left;
            }
            else    // String, Boolean, Integer
            {
                IntegerAtom l = TypeCast.toInteger(left);
                IntegerAtom r = TypeCast.toInteger(right);
                l.value -= r.value;
                return l;
            }
        }
        else if(op == Operator.MUL)
        {
            if((left instanceof RealAtom) || (right instanceof RealAtom))   // Real
            {
                return new RealAtom(((RealAtom)left).value * ((RealAtom)right).value);
            }
            else    // String, Boolean, Integer
            {
                IntegerAtom l = TypeCast.toInteger(left);
                IntegerAtom r = TypeCast.toInteger(right);
                return new IntegerAtom(l.value * r.value);
            }
        }
        else if(op == Operator.MUL_ASN)
        {
            if((left instanceof RealAtom) || (right instanceof RealAtom))   // Real
            {
                ((RealAtom)left).value *= ((RealAtom)right).value;
                return left;
            }
            else    // String, Boolean, Integer
            {
                IntegerAtom l = TypeCast.toInteger(left);
                IntegerAtom r = TypeCast.toInteger(right);
                l.value *= r.value;
                return l;
            }
        }
        else if(op == Operator.DIV)
        {
            if((left instanceof RealAtom) || (right instanceof RealAtom))   // Real
            {
                return new RealAtom(((RealAtom)left).value / ((RealAtom)right).value);
            }
            else    // String, Boolean, Integer
            {
                IntegerAtom l = TypeCast.toInteger(left);
                IntegerAtom r = TypeCast.toInteger(right);
                if(r.value == 0) throw new Exception("Dividing by zero is not defined!");
                return new IntegerAtom(l.value / r.value);
            }
        }
        else if(op == Operator.DIV_ASN)
        {
            if((left instanceof RealAtom) || (right instanceof RealAtom))   // Real
            {
                ((RealAtom)left).value /= ((RealAtom)right).value;
                return left;
            }
            else    // String, Boolean, Integer
            {
                IntegerAtom l = TypeCast.toInteger(left);
                IntegerAtom r = TypeCast.toInteger(right);
                if(r.value == 0) throw new Exception("Dividing by zero is not defined!");
                l.value /= r.value;
                return l;
            }
        }
        else if(op == Operator.MOD)        
        {
            // String, Boolean, Integer, Real
            IntegerAtom l = TypeCast.toInteger(left);
            IntegerAtom r = TypeCast.toInteger(right);
            if(r.value == 0) throw new Exception("Moduling by zero is not defined!");
            return new IntegerAtom(l.value % r.value);
        }
        else if(op == Operator.MOD_ASN)
        {
            // String, Boolean, Integer, Real
            IntegerAtom l = TypeCast.toInteger(left);
            IntegerAtom r = TypeCast.toInteger(right);
            if(r.value == 0) throw new Exception("Moduling by zero is not defined!");
            l.value %= r.value;
            return l;
        }
        else if(op == Operator.OR)        
        {
            // String, Boolean, Integer, Real
            IntegerAtom l = TypeCast.toInteger(left);
            IntegerAtom r = TypeCast.toInteger(right);
            return new IntegerAtom(l.value | r.value);
        }
        else if(op == Operator.OR_ASN)
        {
            // String, Boolean, Integer, Real
            IntegerAtom l = TypeCast.toInteger(left);
            IntegerAtom r = TypeCast.toInteger(right);
            l.value |= r.value;
            return l;
        }
        else if(op == Operator.AND)
        {
            // String, Boolean, Integer, Real
            IntegerAtom l = TypeCast.toInteger(left);
            IntegerAtom r = TypeCast.toInteger(right);
            return new IntegerAtom(l.value & r.value);
        }
        else if(op == Operator.AND_ASN)
        {
            // String, Boolean, Integer, Real
            IntegerAtom l = TypeCast.toInteger(left);
            IntegerAtom r = TypeCast.toInteger(right);
            l.value &= r.value;
            return l;
        }
        else if(op == Operator.XOR)
        {
            // String, Boolean, Integer, Real
            IntegerAtom l = TypeCast.toInteger(left);
            IntegerAtom r = TypeCast.toInteger(right);
            return new IntegerAtom(l.value ^ r.value);
        }
        else if(op == Operator.XOR_ASN)
        {
            // String, Boolean, Integer, Real
            IntegerAtom l = TypeCast.toInteger(left);
            IntegerAtom r = TypeCast.toInteger(right);
            l.value ^= r.value;
            return l;
        }
        else if(op == Operator.LOG_AND)
        {
            // String, Boolean, Integer, Real
            BooleanAtom l = TypeCast.toBoolean(left);
            BooleanAtom r = TypeCast.toBoolean(right);
            return new BooleanAtom(l.value && r.value);
        }
        else if(op == Operator.LOG_OR)
        {
            // String, Boolean, Integer, Real
            BooleanAtom l = TypeCast.toBoolean(left);
            BooleanAtom r = TypeCast.toBoolean(right);
            return new BooleanAtom(l.value || r.value);
        }
        else if(op == Operator.LSH)
        {
            // String, Boolean, Integer, Real
            IntegerAtom l = TypeCast.toInteger(left);
            IntegerAtom r = TypeCast.toInteger(right);
            return new IntegerAtom(l.value << r.value);
        }
        else if(op == Operator.LSH_ASN)
        {
            // String, Boolean, Integer, Real
            IntegerAtom l = TypeCast.toInteger(left);
            IntegerAtom r = TypeCast.toInteger(right);
            l.value <<= r.value;
            return l;
        }
        else if(op == Operator.RSH)
        {
            // String, Boolean, Integer, Real
            IntegerAtom l = TypeCast.toInteger(left);
            IntegerAtom r = TypeCast.toInteger(right);
            return new IntegerAtom(l.value >> r.value);
        }
        else if(op == Operator.RSH_ASN)
        {
            // String, Boolean, Integer, Real
            IntegerAtom l = TypeCast.toInteger(left);
            IntegerAtom r = TypeCast.toInteger(right);
            l.value >>= r.value;
            return l;
        }
        else if(op == Operator.EQ)
        {
            if((left instanceof StringAtom) || (right instanceof StringAtom))   // String
            {
                StringAtom l = TypeCast.toString(left);
                StringAtom r = TypeCast.toString(right);
                return new BooleanAtom(l.value.equals(r.value));
            }
            else    // Integer, Real, Boolean --> convert any of them to Real and compare -- [short code]
            {
                RealAtom l = TypeCast.toReal(left);
                RealAtom r = TypeCast.toReal(right);
                return new BooleanAtom(l.value == r.value);
            }
        }
        else if(op == Operator.NEQ)
        {
            if((left instanceof StringAtom) || (right instanceof StringAtom))   // String
            {
                StringAtom l = TypeCast.toString(left);
                StringAtom r = TypeCast.toString(right);
                return new BooleanAtom(!l.value.equals(r.value));
            }
            else    // Integer, Real, Boolean --> convert any of them to Real and compare -- [short code]
            {
                RealAtom l = TypeCast.toReal(left);
                RealAtom r = TypeCast.toReal(right);
                return new BooleanAtom(l.value != r.value);
            }
        }
        else if(op == Operator.GT)
        {
            if((left instanceof StringAtom) || (right instanceof StringAtom))   // String
            {
                StringAtom l = TypeCast.toString(left);
                StringAtom r = TypeCast.toString(right);
                return new BooleanAtom(l.value.compareTo(r.value) > 0);
            }
            else    // Integer, Real, Boolean --> convert any of them to Real and compare -- [short code]
            {
                RealAtom l = TypeCast.toReal(left);
                RealAtom r = TypeCast.toReal(right);
                return new BooleanAtom(l.value > r.value);
            }
        }
        else if(op == Operator.LT)
        {
            if((left instanceof StringAtom) || (right instanceof StringAtom))   // String
            {
                StringAtom l = TypeCast.toString(left);
                StringAtom r = TypeCast.toString(right);
                return new BooleanAtom(l.value.compareTo(r.value) < 0);
            }
            else    // Integer, Real, Boolean --> convert any of them to Real and compare -- [short code]
            {
                RealAtom l = TypeCast.toReal(left);
                RealAtom r = TypeCast.toReal(right);
                return new BooleanAtom(l.value < r.value);
            }
        }
        else if(op == Operator.GTE)
        {
            if((left instanceof StringAtom) || (right instanceof StringAtom))   // String
            {
                StringAtom l = TypeCast.toString(left);
                StringAtom r = TypeCast.toString(right);
                return new BooleanAtom(l.value.compareTo(r.value) >= 0);
            }
            else    // Integer, Real, Boolean --> convert any of them to Real and compare -- [short code]
            {
                RealAtom l = TypeCast.toReal(left);
                RealAtom r = TypeCast.toReal(right);
                return new BooleanAtom(l.value >= r.value);
            }
        }
        else if(op == Operator.LTE)
        {
            if((left instanceof StringAtom) || (right instanceof StringAtom))   // String
            {
                StringAtom l = TypeCast.toString(left);
                StringAtom r = TypeCast.toString(right);
                return new BooleanAtom(l.value.compareTo(r.value) <= 0);
            }
            else    // Integer, Real, Boolean --> convert any of them to Real and compare -- [short code]
            {
                RealAtom l = TypeCast.toReal(left);
                RealAtom r = TypeCast.toReal(right);
                return new BooleanAtom(l.value <= r.value);
            }
        }
        else if(op == Operator.ASN)
        {
            if(left instanceof StringAtom)
            {
                left = TypeCast.toString(right);
                return left;
            }
            else if(left instanceof RealAtom)
            {
                left = TypeCast.toReal(right);
                return left;
            }
            else if(left instanceof IntegerAtom)
            {
                left = TypeCast.toInteger(right);
                return left;
            }
            else    // BooleanAtom
            {
                left = TypeCast.toBoolean(right);
                return left;
            }
        }
        else if(op == Operator.PLUS)
        {
            if((left instanceof StringAtom) || (right instanceof StringAtom))   // String
            {
                StringAtom l = TypeCast.toString(left);
                StringAtom r = TypeCast.toString(right);
                return new StringAtom(l.value + r.value);
            }
            else if((left instanceof RealAtom) || (right instanceof RealAtom))   // Real
            {
                RealAtom l = TypeCast.toReal(left);
                RealAtom r = TypeCast.toReal(right);
                return new RealAtom(l.value + r.value);
            }
            else    // Integer, Boolean
            {
                IntegerAtom l = TypeCast.toInteger(left);
                IntegerAtom r = TypeCast.toInteger(right);
                return new IntegerAtom(l.value + r.value);
            }
        }
        else if(op == Operator.ADD_ASN)
        {
            if((left instanceof StringAtom) || (right instanceof StringAtom))   // String
            {
                StringAtom l = TypeCast.toString(left);
                StringAtom r = TypeCast.toString(right);
                l.value += r.value;
                return l;
            }
            else if((left instanceof RealAtom) || (right instanceof RealAtom))   // Real
            {
                RealAtom l = TypeCast.toReal(left);
                RealAtom r = TypeCast.toReal(right);
                l.value += r.value;
                return l;
            }
            else    // Integer, Boolean
            {
                IntegerAtom l = TypeCast.toInteger(left);
                IntegerAtom r = TypeCast.toInteger(right);
                l.value += r.value;
                return l;
            }
        }
        return null;    // can't happen due to the previous semantic check
    }
    
    /*************************************************************
     * BOOLEAN
     *************************************************************/
    public static BooleanAtom toBoolean(Atom from)
    {
        if(from instanceof BooleanAtom)
            return (BooleanAtom)from;
        else if(from instanceof IntegerAtom)
            return new BooleanAtom(((IntegerAtom)from).value != 0);
        else if(from instanceof RealAtom)
            return new BooleanAtom(((RealAtom)from).value != 0.0);
        else if(from instanceof StringAtom)
            return new BooleanAtom((((StringAtom)from).value != null) && (!((StringAtom)from).value.equals("")));
        else
            return null;
    }
    
    /*************************************************************
     * INTEGER
     *************************************************************/
    public static IntegerAtom toInteger(Atom from)
    {
        if(from instanceof BooleanAtom)
            return new IntegerAtom(((BooleanAtom)from).value ? 1 : 0);
        else if(from instanceof IntegerAtom)
            return (IntegerAtom)from;
        else if(from instanceof RealAtom)
            return new IntegerAtom((int)((RealAtom)from).value);
        else if(from instanceof StringAtom)
            return new IntegerAtom(Integer.parseInt(((StringAtom)from).value));
        else
            return null;
    }
    
    /*************************************************************
     * REAL
     *************************************************************/
    public static RealAtom toReal(Atom from)
    {
        if(from instanceof BooleanAtom)
            return new RealAtom(((BooleanAtom)from).value ? 1.0 : 0.0);
        else if(from instanceof IntegerAtom)
            return new RealAtom((double)((IntegerAtom)from).value);
        else if(from instanceof RealAtom)
            return (RealAtom)from;
        else if(from instanceof StringAtom)
            return new RealAtom(Double.parseDouble(((StringAtom)from).value));
        else
            return null;
    }
    
    /*************************************************************
     * STRING
     *************************************************************/
    public static StringAtom toString(Atom from)
    {
        if(from instanceof BooleanAtom)
            return new StringAtom(Boolean.toString(((BooleanAtom)from).value));
        else if(from instanceof IntegerAtom)
            return new StringAtom(Integer.toString(((IntegerAtom)from).value));
        else if(from instanceof RealAtom)
            return new StringAtom(Double.toString(((RealAtom)from).value));
        else if(from instanceof StringAtom)
            return (StringAtom)from;
        else
            return null;
    }
}
