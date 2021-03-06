package mi.run.bytecode;

import java.util.HashMap;
import mi.run.ast.Operator;

public class Code
{
    public static final int JZ  =  1;    // jump if zero (on the top of the stack! not ZeroFlag!)
    public static final int JNZ =  2;    // jump if not zero (on the top of the stack! not ZeroFlag!)
    public static final int JMP =  3;    // unconditional jump
    public static final int RET =  4;    // return from a CALLed function
    public static final int RETV = 47;   // return from a CALLed function and respects that last value on the stack is returned value!
    public static final int POP  = 6;    // pops an item from the stack -throws it away without saving
    public static final int LD  =  7;    // load a variable -push to the stack
    public static final int ST  =  8;    // store to a variable -pop from the stack
    public static final int LDA =  9;    // load an array element -push to the stack
    public static final int STA = 10;    // store to an array element -pop from the stack
    
    public static final int ADD = 11;    // +
    public static final int SUB = 12;    // -
    public static final int MUL = 13;    // *
    public static final int DIV = 14;    // /
    public static final int MOD = 15;    // %
    public static final int AND = 16;    // &
    public static final int OR  = 17;    // |
    public static final int XOR = 18;    // ^
    public static final int LSH = 19;    // <<
    public static final int RSH = 20;    // >>
    public static final int INC = 21;    // ++
    public static final int DEC = 22;    // --
    public static final int CALL = 24;    // CALLing a function
    public static final int INVOKE = 25;    // INVOKEing a built-in routine
    
    public static final int LDCA = 29;    // load an array filled by constants -push to the stack
    public static final int LDCB = 30;    // load a boolean constant -push to the stack
    public static final int LDCI = 31;    // load an integer constant -push to the stack
    public static final int LDCR = 32;    // load a real constant -push to the stack
    public static final int LDCS = 33;    // load a string constant -push to the stack
    public static final int LDCN = 38;    // load a NULL constant -push to the stack
    
    public static final int LDZF = 34;    // load the Zero Flag -push to the stack
    public static final int LDSF = 35;    // load the Sign Flag -push to the stack
    public static final int LDNZF = 36;    // load the NOT(Zero Flag) -push to the stack
    public static final int LDNSF = 37;    // load the NOT(Sign Flag) -push to the stack
    
    public static final int LDR = 39;    // load a value and delete it from memory -push to the stack
    public static final int LDAI = 40;    // load an array element to the index that is precomputed a needs to be picked up from the stack -push to the stack
    public static final int STAI = 41;    // store to an array element to the index that is precomputed a needs to be picked up from the stack -pop from the stack
    public static final int STAIK = 42;    // store to an array element to the index that is precomputed a needs to be picked up from the stack -pop from the stack, !!BUT!! keeps value on the stack!! STAIK = STore Array Index Keep
    public static final int STAV = 43;    // store an array of varriables from stack to the memory (it the variable is already in the memory, then it saves it into the variable) -pop from the stack
    
    public static final int NOT = 44;     // logical inversion
    public static final int NEG = 45;     // bit inversion
    
    public static final int NOOP = 46;     // NO OPeration -- just a padding for easier implementation of some AST nodes generation
    
    public static final int NEW = 48;     // NEW -- for the purpose of allocation of memory
    
    public static final int MINUS = 49;     // unary minus -
    
    public static final int LT  = 50;    // <
    public static final int GT  = 51;    // >
    public static final int LTE = 52;    // <=
    public static final int GTE = 53;    // >=
    public static final int EQ  = 54;    // ==
    public static final int NEQ = 55;    // !=
    
    
    private static HashMap<Integer, String> codes = null;

    public static String codeToString(int code)
    {
        if(codes == null)
        {
            codes = new HashMap<Integer, String>();
            codes.put(JZ , "JZ");
            codes.put(JNZ, "JNZ");
            codes.put(JMP, "JMP");
            codes.put(RET, "RET");
            codes.put(RETV, "RETV");
            codes.put(POP, "POP");
            codes.put(LD , "LD");
            codes.put(ST , "ST");
            codes.put(LDA, "LDA");
            codes.put(STA, "STA");
            codes.put(ADD, "ADD");
            codes.put(SUB, "SUB");
            codes.put(MUL, "MUL");
            codes.put(DIV, "DIV");
            codes.put(MOD, "MOD");
            codes.put(AND, "AND");
            codes.put(OR , "OR");
            codes.put(XOR, "XOR");
            codes.put(LSH, "LSH");
            codes.put(RSH, "RSH");
            codes.put(INC, "INC");
            codes.put(DEC, "DEC");
            codes.put(CALL, "CALL");
            codes.put(INVOKE, "INVOKE");
            codes.put(LDCA, "LDCA");
            codes.put(LDCB, "LDCB");
            codes.put(LDCI, "LDCI");
            codes.put(LDCR, "LDCR");
            codes.put(LDCS, "LDCS");
            codes.put(LDCN, "LDCN");
            codes.put(LDZF, "LDZF");
            codes.put(LDSF, "LDSF");
            codes.put(LDNZF, "LDNZF");
            codes.put(LDNSF, "LDNSF");
            codes.put(LDR, "LDR");
            codes.put(LDAI, "LDAI");
            codes.put(STAI, "STAI");
            codes.put(STAIK, "STAIK");
            codes.put(STAV, "STAV");
            codes.put(NOT, "NOT");
            codes.put(NEG, "NEG");
            codes.put(NOOP, "NOOP");
            codes.put(NEW, "NEW");
            codes.put(MINUS, "MINUS");
            codes.put(LT, "LT");
            codes.put(GT, "GT");
            codes.put(LTE, "LTE");
            codes.put(GTE, "GTE");
            codes.put(EQ, "EQ");
            codes.put(NEQ, "NEQ");
        }
        return codes.get(code);
    }
    
    static public int getByOperator(int op, boolean unary)
    {
        switch(op)
        {
            case Operator.PLUS:
            case Operator.ADD_ASN: return ADD;
            case Operator.MINUS: if(unary) return MINUS;
            case Operator.SUB_ASN: return SUB;
            case Operator.MUL:
            case Operator.MUL_ASN: return MUL;
            case Operator.DIV:
            case Operator.DIV_ASN: return DIV;
            case Operator.MOD:
            case Operator.MOD_ASN: return MOD;
            case Operator.NEG: return NEG;
            case Operator.NOT: return NOT;
            case Operator.DEC: return DEC;
            case Operator.INC: return INC;
            case Operator.RSH:
            case Operator.RSH_ASN: return RSH;
            case Operator.LSH:
            case Operator.LSH_ASN: return LSH;
            case Operator.AND:
            case Operator.LOG_AND:
            case Operator.AND_ASN: return AND;
            case Operator.OR:
            case Operator.LOG_OR:
            case Operator.OR_ASN: return OR;
            case Operator.XOR:
            case Operator.XOR_ASN: return XOR;
            case Operator.ASN: return ST;
            //
            case Operator.LTE: return LTE;
            case Operator.LT:  return LT;
            case Operator.GTE: return GTE;
            case Operator.GT:  return GT;
            case Operator.EQ:  return EQ;
            case Operator.NEQ: return NEQ;
            //
            default: return -1;
        }
    }
}
