#include "Interpreter.h"

void Interpreter::_call(const FunctionSignature *fn, const vector<Argument *> &args)
{
	//
}

void Interpreter::_invoke(const Variable *name, const vector<Argument *> &args)
{
	
}

void Interpreter::_jz(const Integer *to)
{
	//
}

void Interpreter::_jnz(const Integer *to)
{
	//
}

void Interpreter::_jmp(const Integer *to)
{
	//
}

void Interpreter::_ret()
{
	//
}

void Interpreter::_retv(const Variable *var)
{
	//
}

void Interpreter::_st(const Variable *dest, const Variable *src)
{
	//
}

void Interpreter::_sta()
{
	//
}

void Interpreter::_add(const Variable *dest, const Variable *op1, const Variable *op2)
{
	//
}

void Interpreter::_sub(const Variable *dest, const Variable *op1, const Variable *op2)
{
	//
}

void Interpreter::_mul(const Variable *dest, const Variable *op1, const Variable *op2)
{
	//
}

void Interpreter::_div(const Variable *dest, const Variable *op1, const Variable *op2)
{
	//
}

void Interpreter::_mod(const Variable *dest, const Variable *op1, const Variable *op2)
{
	//
}

void Interpreter::_and(const Variable *dest, const Variable *op1, const Variable *op2)
{
	//
}

void Interpreter::_or(const Variable *dest, const Variable *op1, const Variable *op2)
{
	//
}

void Interpreter::_xor(const Variable *dest, const Variable *op1, const Variable *op2)
{
	//
}

void Interpreter::_lsh(const Variable *dest, const Variable *op1, const Variable *op2)
{
	//
}

void Interpreter::_rsh(const Variable *dest, const Variable *op1, const Variable *op2)
{
	//
}

void Interpreter::_inc(const Variable *var)
{
	//
}

void Interpreter::_dec(const Variable *var)
{
	//
}

void Interpreter::_not(const Variable *dest, const Variable *src)
{
	//
}

void Interpreter::_neg(const Variable *dest, const Variable *src)
{
	//
}

void Interpreter::_minus(const Variable *dest, const Variable *src)
{
	//
}

void Interpreter::_ldci(const Variable *var, const Integer *constant)
{
	//
}

void Interpreter::_ldcb(const Variable *var, const Boolean *constant)
{
	//
}

void Interpreter::_ldcr(const Variable *var, const Double *constant)
{
	//
}

void Interpreter::_ldcs(const Variable *var, const String *constant)
{
	//
}

void Interpreter::_ldcn(const Variable *var, const Null *constant)
{
	//
}

void Interpreter::_new(const Variable *var, const Variable *size)
{
	//
}

void Interpreter::_lt(const Variable *dest, const Variable *op1, const Variable *op2)
{
	//
}

void Interpreter::_gt(const Variable *dest, const Variable *op1, const Variable *op2)
{
	//
}

void Interpreter::_lte(const Variable *dest, const Variable *op1, const Variable *op2)
{
	//
}

void Interpreter::_gte(const Variable *dest, const Variable *op1, const Variable *op2)
{
	//
}

void Interpreter::_eq(const Variable *dest, const Variable *op1, const Variable *op2)
{
	//
}

void Interpreter::_neq(const Variable *dest, const Variable *op1, const Variable *op2)
{
	//
}