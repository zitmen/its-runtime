#ifndef _RUN_JIT_COMPILER_H_
#define _RUN_JIT_COMPILER_H_

#include <vector>
#include <map>
#include <exception>
#include <stack>
#include <algorithm>
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
		map<string, vector<char *> > fillCalls;

		bool *pZF;	// pointer to Zero Flag inside of the parent interpreter; it's automatically set by compiled code at instructions neg,lt,gt,lte,gte,eq,neq

		// temporary help structures for code generating -- any content is valid only during a compilation time of a single function
		stack<map<int, int> *> hlp_addresses;	// addresses used for jumps -> first=address inside of a bytecode, second=address inside of a compiled code
		stack<vector<char *> *> hlp_jump_loc;	// address insode of a compiled code, where are stored addresses of jump to bytecode --> those needs to be transformed to compiled code addresses during a compilation

		static bool functionsComparator(FunctionSignature *a, FunctionSignature *b)
		{
			return (a->pointer < b->pointer);
		}

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

		bool isConditionalJump(Instruction *instr)
		{
			return ((instr->code == InstructionCode::JZ) || (instr->code == InstructionCode::JNZ));
		}

	public:
		stack<FunctionSignature *> *call_stack;
		map<string, char *> compiled_functions;

		JITCompiler(vector<Instruction *> *program, map<string, FunctionSignature *> *functions, stack<FunctionSignature *> *call_stack, bool *ZF)
		{
			this->program = program;
			this->functions = functions;
			this->call_stack = call_stack;
			pZF = ZF;
			//
			// get length of functions
			if(functions)
			{
				vector<FunctionSignature *> fns;
				for(map<string, FunctionSignature *>::iterator it = functions->begin(); it != functions->end(); ++it)
					fns.push_back(it->second);
				std::sort(fns.begin(), fns.end(), JITCompiler::functionsComparator);
				for(size_t i = 0, im = fns.size() - 1; i < im; i++)
					fns[i]->length = fns[i+1]->pointer - fns[i]->pointer;
				fns[fns.size()-1]->length = program->size() - fns[fns.size()-1]->pointer;
			}
		}

		~JITCompiler()
		{
			for(map<string, char *>::iterator it = compiled_functions.begin(); it != compiled_functions.end(); ++it)
				if(it->second != NULL)
					delete [] it->second;
		}

		int compileInstruction(Instruction *instr, char *code)
		{
			switch(instr->code)
			{
				case InstructionCode::JZ:     return gen_jz(code, (Integer *)instr->args[0]);
				case InstructionCode::JNZ:    return gen_jnz(code, (Integer *)instr->args[0]);
				case InstructionCode::JMP:    return gen_jmp(code, (Integer *)instr->args[0]);
				case InstructionCode::RET:    return gen_ret(code);
				case InstructionCode::RETV:   return gen_retv(code, (Variable *)instr->args[0]);
				case InstructionCode::POP:    return gen_pop(code, (Variable *)instr->args[0]);
				case InstructionCode::ST:     return gen_st(code, (Variable *)instr->args[0], (Variable *)instr->args[1]);
				case InstructionCode::ADD:    return gen_add(code, (Variable *)instr->args[0], (Variable *)instr->args[1], (Variable *)instr->args[2]);
				case InstructionCode::SUB:    return gen_sub(code, (Variable *)instr->args[0], (Variable *)instr->args[1], (Variable *)instr->args[2]);
				case InstructionCode::MUL:    return gen_mul(code, (Variable *)instr->args[0], (Variable *)instr->args[1], (Variable *)instr->args[2]);
				case InstructionCode::DIV:    return gen_div(code, (Variable *)instr->args[0], (Variable *)instr->args[1], (Variable *)instr->args[2]);
				case InstructionCode::MOD:    return gen_mod(code, (Variable *)instr->args[0], (Variable *)instr->args[1], (Variable *)instr->args[2]);
				case InstructionCode::AND:    return gen_and(code, (Variable *)instr->args[0], (Variable *)instr->args[1], (Variable *)instr->args[2]);
				case InstructionCode::OR:     return gen_or(code, (Variable *)instr->args[0], (Variable *)instr->args[1], (Variable *)instr->args[2]);
				case InstructionCode::XOR:    return gen_xor(code, (Variable *)instr->args[0], (Variable *)instr->args[1], (Variable *)instr->args[2]);
				case InstructionCode::LSH:    return gen_lsh(code, (Variable *)instr->args[0], (Variable *)instr->args[1], (Variable *)instr->args[2]);
				case InstructionCode::RSH:    return gen_rsh(code, (Variable *)instr->args[0], (Variable *)instr->args[1], (Variable *)instr->args[2]);
				case InstructionCode::INC:    return gen_inc(code, (Variable *)instr->args[0]);
				case InstructionCode::DEC:    return gen_dec(code, (Variable *)instr->args[0]);
				case InstructionCode::NOT:    return gen_not(code, (Variable *)instr->args[0], (Variable *)instr->args[1]);
				case InstructionCode::NEG:    return gen_neg(code, (Variable *)instr->args[0], (Variable *)instr->args[1]);
				case InstructionCode::MINUS:  return gen_minus(code, (Variable *)instr->args[0], (Variable *)instr->args[1]);
				case InstructionCode::CALL:   return gen_call(code, functions->find(((Variable *)instr->args[0])->getName())->second, instr->args);
				case InstructionCode::INVOKE: return gen_invoke(code, (Variable *)instr->args[0], instr->args);
				case InstructionCode::LDCI:   return gen_ldci(code, (Variable *)instr->args[0], (Integer *)instr->args[1]);
				case InstructionCode::LDCB:   return gen_ldcb(code, (Variable *)instr->args[0], (Boolean *)instr->args[1]);
				case InstructionCode::LDCR:   return gen_ldcr(code, (Variable *)instr->args[0], (Double *)instr->args[1]);
				case InstructionCode::LDCS:   return gen_ldcs(code, (Variable *)instr->args[0], (String *)instr->args[1]);
				case InstructionCode::LDCN:   return gen_ldcn(code, (Variable *)instr->args[0], (Reference *)instr->args[1]);
				case InstructionCode::NEW:    return gen_new(code, (Variable *)instr->args[0], (Variable *)instr->args[1]);
				case InstructionCode::LT:     return gen_lt(code, (Variable *)instr->args[0], (Variable *)instr->args[1], (Variable *)instr->args[2]);
				case InstructionCode::GT:     return gen_gt(code, (Variable *)instr->args[0], (Variable *)instr->args[1], (Variable *)instr->args[2]);
				case InstructionCode::LTE:    return gen_lte(code, (Variable *)instr->args[0], (Variable *)instr->args[1], (Variable *)instr->args[2]);
				case InstructionCode::GTE:    return gen_gte(code, (Variable *)instr->args[0], (Variable *)instr->args[1], (Variable *)instr->args[2]);
				case InstructionCode::EQ:     return gen_eq(code, (Variable *)instr->args[0], (Variable *)instr->args[1], (Variable *)instr->args[2]);
				case InstructionCode::NEQ:    return gen_neq(code, (Variable *)instr->args[0], (Variable *)instr->args[1], (Variable *)instr->args[2]);
				default: throw new std::exception("JITCompiler::compileInstruction: invalid instruction code!");
			}
		}

		void compile(const string &fnName)
		{
			int capacity = 4096, limit = 4000;	// 4kiB, 4kB
			char *code = new char[capacity];
			int length = 0;
			hlp_addresses.push(new map<int, int>());
			hlp_jump_loc.push(new vector<char *>());
			//
			length += gen_prolog(code+length);
			//
			for(int i = functions->find(fnName)->second->pointer, im = i + functions->find(fnName)->second->length; i < im; i++)
			{
				(*(hlp_addresses.top()))[i] = length;
				length += compileInstruction(program->at(i), code+length);
				if(length > limit)
				{	// realloc
					capacity *= 2; limit = capacity - 100;
					char *new_code = new char[capacity];
					memcpy(new_code, code, length);
					delete [] code;
					code = new_code;
				}
			}
			length += gen_epilog(code+length);
			//
			// go through the compiled code and replace all jump addresses
			char *jmp_address;
			for(size_t i = 0, im = (*(hlp_jump_loc.top())).size(); i < im; i++)
			{
				jmp_address = code + (*(hlp_addresses.top()))[(*((int *)((*(hlp_jump_loc.top()))[i])))];
				(*((int *)((*(hlp_jump_loc.top()))[i]))) = (int)jmp_address;
			}
			//
			compiled_functions[fnName] = code;
			for(size_t i = 0, im = fillCalls[fnName].size(); i < im; i++)
				(*((void **)(fillCalls[fnName][i]))) = compiled_functions[fnName];
			//
			delete hlp_addresses.top(); hlp_addresses.pop();
			delete hlp_jump_loc.top(); hlp_jump_loc.pop();
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