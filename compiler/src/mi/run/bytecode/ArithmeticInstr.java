package mi.run.bytecode;

import mi.run.runtime.Interpreter;

public class ArithmeticInstr extends Instruction
{
    public String dest;
    public String op1;
    public String op2;
    
    public ArithmeticInstr(int code, String op)    // unary - INC, DEC
    {
        super(code);
        this.dest = op;
    }
    
    public ArithmeticInstr(int code, String dest, String op)    // unary - others
    {
        super(code);
        this.dest = dest;
        this.op1 = op;
        this.op2 = null;
    }
    
    public ArithmeticInstr(int code, String dest, String op1, String op2)   // binary
    {
        super(code);
        this.dest = dest;
        this.op1 = op1;
        this.op2 = op2;
    }
    
    @Override
    public String toString()
    {
        String str = super.toString() + " " + dest;
        if(op1 != null) str += " " + op1;
        if(op2 != null) str += " " + op2;
        return str;
    }
    
    @Override
    public boolean interpret(Interpreter machine) throws Exception
    {
        throw new Exception("NOT SUPPORTED!");
    }
}
