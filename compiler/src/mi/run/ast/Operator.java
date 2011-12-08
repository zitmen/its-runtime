package mi.run.ast;

public class Operator
{
    public static final int UNKNOWN =  0;  // ???
    public static final int PLUS    =  1;  // +
    public static final int MINUS   =  2;  // -
    public static final int MUL     =  3;  // *
    public static final int DIV     =  4;  // /
    public static final int MOD     =  5;  // %
    public static final int INC     =  6;  // ++
    public static final int DEC     =  7;  // --
    public static final int ADD_ASN =  8;  // +=
    public static final int SUB_ASN =  9;  // -=
    public static final int MUL_ASN = 10;  // *=
    public static final int DIV_ASN = 11;  // /=
    public static final int MOD_ASN = 12;  // %=
    public static final int OR      = 13;   // |
    public static final int AND     = 14;  // &
    public static final int XOR     = 15;  // ^
    public static final int OR_ASN  = 16;   // |=
    public static final int AND_ASN = 17;  // &=
    public static final int XOR_ASN = 18;  // ^=
    public static final int LSH     = 19;  // <<
    public static final int RSH     = 20;  // >>
    public static final int LSH_ASN = 21;  // <<=
    public static final int RSH_ASN = 22;  // >>=
    public static final int NOT     = 23;  // !
    public static final int NEG     = 24;  // ~
    public static final int LOG_AND = 25;  // &&
    public static final int LOG_OR  = 26;  // ||
    public static final int ASN     = 27;  // =
    public static final int EQ      = 28;  // ==
    public static final int NEQ     = 29;  // !=
    public static final int GT      = 30;  // >
    public static final int LT      = 31;  // <
    public static final int GTE     = 32;  // >=
    public static final int LTE     = 33;  // <=
    
    public static boolean isRelational(int op)
    {
        return ((op == EQ) || (op == NEQ) || (op == GT) || (op == LT) || (op == GTE) || (op == LTE));
    }

    static boolean isAssign(int op)
    {
        return ((op == ASN) || (op == AND_ASN) || (op == OR_ASN) || (op == LSH_ASN) || (op == RSH_ASN) || (op == XOR_ASN) ||
                (op == ADD_ASN) || (op == SUB_ASN) || (op == MUL_ASN) || (op == DIV_ASN) || (op == MOD_ASN));
    }
}
