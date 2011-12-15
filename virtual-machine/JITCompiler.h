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
		vector<Instruction *> *program;
		map<string, FunctionSignature *> *functions;
		map<string, char *> compiled_functions;

	protected:
		int gen_prolog(char *code);
		int gen_epilog(char *code);
		int gen_call(char *code, FunctionSignature *fn, const vector<Argument *> &args);
		int gen_invoke(char *code, Variable *name, const vector<Argument *> &args);
		int gen_jz(char *code, const Integer *to);
		int gen_jnz(char *code, const Integer * to);
		int gen_jmp(char *code, const Integer * to);
		int gen_ret(char *code);
		int gen_retv(char *code, const Variable *var);
		int gen_pop(char *code, Variable *dest);
		int gen_st(char *code, Variable *dest, Variable *src);
		int gen_ldzf_alu(char *code, Variable *dest);
		int gen_ldzf_fpu(char *code, Variable *dest);
		int gen_add(char *code, Variable *dest, const Variable *op1, const Variable *op2);
		int gen_sub(char *code, Variable *dest, const Variable *op1, const Variable *op2);
		int gen_mul(char *code, Variable *dest, const Variable *op1, const Variable *op2);
		int gen_div(char *code, Variable *dest, const Variable *op1, const Variable *op2);
		int gen_mod(char *code, Variable *dest, const Variable *op1, const Variable *op2);
		int gen_and(char *code, Variable *dest, const Variable *op1, const Variable *op2);
		int gen_or(char *code, Variable *dest, const Variable *op1, const Variable *op2);
		int gen_xor(char *code, Variable *dest, const Variable *op1, const Variable *op2);
		int gen_lsh(char *code, Variable *dest, const Variable *op1, const Variable *op2);
		int gen_rsh(char *code, Variable *dest, const Variable *op1, const Variable *op2);
		int gen_inc(char *code, Variable *var);
		int gen_dec(char *code, Variable *var);
		int gen_not(char *code, Variable *dest, const Variable *src);
		int gen_neg(char *code, Variable *dest, const Variable *src);
		int gen_minus(char *code, Variable *dest, const Variable *src);
		int gen_ldci(char *code, Variable *var, Integer *constant);
		int gen_ldcb(char *code, Variable *var, Boolean *constant);
		int gen_ldcr(char *code, Variable *var, Double *constant);
		int gen_ldcs(char *code, Variable *var, String *constant);
		int gen_ldcn(char *code, Variable *var, Reference *constant);
		int gen_new(char *code, Variable *var, const Variable *size);
		int gen_lt(char *code, Variable *dest, const Variable *op1, const Variable *op2);
		int gen_gt(char *code, Variable *dest, const Variable *op1, const Variable *op2);
		int gen_lte(char *code, Variable *dest, const Variable *op1, const Variable *op2);
		int gen_gte(char *code, Variable *dest, const Variable *op1, const Variable *op2);
		int gen_eq(char *code, Variable *dest, const Variable *op1, const Variable *op2);
		int gen_neq(char *code, Variable *dest, const Variable *op1, const Variable *op2);

	public:
		JITCompiler(vector<Instruction *> *program, map<string, FunctionSignature *> *functions)
		{
			this->program = program;
			this->functions = functions;
		}

		~JITCompiler()
		{
			for(map<string, char *>::iterator it = compiled_functions.begin(); it != compiled_functions.end(); ++it)
				if(it->second != NULL)
					delete [] it->second;
		}

		void compile(const string &fnName)
		{
			char *code = new char[4096];	// 4kB
			int length = 0;
			//
			length += gen_prolog(code+length);
			// TODO
			// for i from
			// start = functions->find(fnName)->second->pointer
			// to
			// end = ??? -- seradit funkce podle pointeru (ve vektoru) a do dalsi mapy <string, int> nacpat length kazde funkce!
			length += gen_epilog(code+length);
			//
			compiled_functions.insert(pair<string, char *>(fnName, code));
		}

		void run(const string &fnName)
		{
			// compile (if it has not been done yet)
			if(compiled_functions.find(fnName) == compiled_functions.end())
				compile(fnName);
			// run
			typedef void (*compiled_program)(void);
			compiled_program exec = (compiled_program)compiled_functions[fnName];
			exec();
		}
};

#endif	// _RUN_JIT_COMPILER_H_