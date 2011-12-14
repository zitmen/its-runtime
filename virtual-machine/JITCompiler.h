#ifndef _RUN_JIT_COMPILER_H_
#define _RUN_JIT_COMPILER_H_

#include <vector>
#include <map>
#include <exception>
#include <stack>
using std::stack;
using std::map;
using std::vector;

#include "Signatures.h"
#include "Argument.h"
#include "BuiltInRoutines.h"
#include "MemoryManager.h"
#include "InstructionSet.h"

class JITCompiler
{
	private:
		stack<FunctionSignature *> call_stack;
		MemoryManager *memory;
		double options[4];
		vector<Instruction *> *program;
		static map<string, StructureSignature *> *structures;
		static map<string, FunctionSignature *> *functions;
		int IP;	// Instruction Pointer -- points to an instruction after the currently processed one

	public:
		bool ZF;	// Zero Flag -- was the result of the previous arithmetic/bit/logic instruction zero?

	//protected:
	public:
		void gen_call(FunctionSignature *fn, const vector<Argument *> &args);
		void gen_invoke(Variable *name, const vector<Argument *> &args);
		void gen_jz(const Integer *to);
		void gen_jnz(const Integer * to);
		void gen_jmp(const Integer * to);
		void gen_ret();
		void gen_retv(const Variable *var);
		void gen_pop(Variable *dest);
		void gen_st(Variable *dest, Variable *src);
		void gen_ldzf_int(Variable *dest);
		void gen_ldzf_double(Variable *dest);
		void gen_add(Variable *dest, const Variable *op1, const Variable *op2);
		void gen_sub(Variable *dest, const Variable *op1, const Variable *op2);
		void gen_mul(Variable *dest, const Variable *op1, const Variable *op2);
		void gen_div(Variable *dest, const Variable *op1, const Variable *op2);
		void gen_mod(Variable *dest, const Variable *op1, const Variable *op2);
		void gen_and(Variable *dest, const Variable *op1, const Variable *op2);
		void gen_or(Variable *dest, const Variable *op1, const Variable *op2);
		void gen_xor(Variable *dest, const Variable *op1, const Variable *op2);
		void gen_lsh(Variable *dest, const Variable *op1, const Variable *op2);
		void gen_rsh(Variable *dest, const Variable *op1, const Variable *op2);
		void gen_inc(Variable *var);
		void gen_dec(Variable *var);
		void gen_not(Variable *dest, const Variable *src);
		void gen_neg(Variable *dest, const Variable *src);
		void gen_minus(Variable *dest, const Variable *src);
		void gen_ldci(Variable *var, Integer *constant);
		void gen_ldcb(Variable *var, Boolean *constant);
		void gen_ldcr(Variable *var, Double *constant);
		void gen_ldcs(Variable *var, String *constant);
		void gen_ldcn(Variable *var, Reference *constant);
		void gen_new(Variable *var, const Variable *size);
		void gen_lt(Variable *dest, const Variable *op1, const Variable *op2);
		void gen_gt(Variable *dest, const Variable *op1, const Variable *op2);
		void gen_lte(Variable *dest, const Variable *op1, const Variable *op2);
		void gen_gte(Variable *dest, const Variable *op1, const Variable *op2);
		void gen_eq(Variable *dest, const Variable *op1, const Variable *op2);
		void gen_neq(Variable *dest, const Variable *op1, const Variable *op2);

	public:
		// TODO
};

#endif	// _RUN_JIT_COMPILER_H_