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
		map<string, char *> compiled_functions;

		bool *pZF;	// pointer to Zero Flag inside of the parent interpreter; it's automatically set by compiled code at instructions neg,lt,gt,lte,gte,eq,neq

		// temporary help structures for code generating -- any content is valid only during a compilation time of a single function
		map<char, char> hlp_addresses;	// addresses used for jumps -> first=address inside of a bytecode, second=address inside of a compiled code
		vector<char *> hlp_jump_loc;	// address insode of a compiled code, where are stored addresses of jump to bytecode --> those needs to be transformed to compiled code addresses during a compilation

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
		JITCompiler(vector<Instruction *> *program, map<string, FunctionSignature *> *functions, bool *ZF)
		{
			this->program = program;
			this->functions = functions;
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
				case InstructionCode::JZ:     gen_jz(code, (Integer *)instr->args[0]); break;
				case InstructionCode::JNZ:    gen_jnz(code, (Integer *)instr->args[0]); break;
				case InstructionCode::JMP:    gen_jmp(code, (Integer *)instr->args[0]); break;
				case InstructionCode::RET:    gen_ret(code); break;
				case InstructionCode::RETV:   gen_retv(code, (Variable *)instr->args[0]); break;
				case InstructionCode::POP:    gen_pop(code, (Variable *)instr->args[0]); break;
				case InstructionCode::ST:     gen_st(code, (Variable *)instr->args[0], (Variable *)instr->args[1]); break;
				case InstructionCode::ADD:    gen_add(code, (Variable *)instr->args[0], (Variable *)instr->args[1], (Variable *)instr->args[2]); break;
				case InstructionCode::SUB:    gen_sub(code, (Variable *)instr->args[0], (Variable *)instr->args[1], (Variable *)instr->args[2]); break;
				case InstructionCode::MUL:    gen_mul(code, (Variable *)instr->args[0], (Variable *)instr->args[1], (Variable *)instr->args[2]); break;
				case InstructionCode::DIV:    gen_div(code, (Variable *)instr->args[0], (Variable *)instr->args[1], (Variable *)instr->args[2]); break;
				case InstructionCode::MOD:    gen_mod(code, (Variable *)instr->args[0], (Variable *)instr->args[1], (Variable *)instr->args[2]); break;
				case InstructionCode::AND:    gen_and(code, (Variable *)instr->args[0], (Variable *)instr->args[1], (Variable *)instr->args[2]); break;
				case InstructionCode::OR:     gen_or(code, (Variable *)instr->args[0], (Variable *)instr->args[1], (Variable *)instr->args[2]); break;
				case InstructionCode::XOR:    gen_xor(code, (Variable *)instr->args[0], (Variable *)instr->args[1], (Variable *)instr->args[2]); break;
				case InstructionCode::LSH:    gen_lsh(code, (Variable *)instr->args[0], (Variable *)instr->args[1], (Variable *)instr->args[2]); break;
				case InstructionCode::RSH:    gen_rsh(code, (Variable *)instr->args[0], (Variable *)instr->args[1], (Variable *)instr->args[2]); break;
				case InstructionCode::INC:    gen_inc(code, (Variable *)instr->args[0]); break;
				case InstructionCode::DEC:    gen_dec(code, (Variable *)instr->args[0]); break;
				case InstructionCode::NOT:    gen_not(code, (Variable *)instr->args[0], (Variable *)instr->args[1]); break;
				case InstructionCode::NEG:    gen_neg(code, (Variable *)instr->args[0], (Variable *)instr->args[1]); break;
				case InstructionCode::MINUS:  gen_minus(code, (Variable *)instr->args[0], (Variable *)instr->args[1]); break;
				case InstructionCode::CALL:   gen_call(code, functions->find(((Variable *)instr->args[0])->getName())->second, instr->args); break;
				case InstructionCode::INVOKE: gen_invoke(code, (Variable *)instr->args[0], instr->args); break;
				case InstructionCode::LDCI:   gen_ldci(code, (Variable *)instr->args[0], (Integer *)instr->args[1]); break;
				case InstructionCode::LDCB:   gen_ldcb(code, (Variable *)instr->args[0], (Boolean *)instr->args[1]); break;
				case InstructionCode::LDCR:   gen_ldcr(code, (Variable *)instr->args[0], (Double *)instr->args[1]); break;
				case InstructionCode::LDCS:   gen_ldcs(code, (Variable *)instr->args[0], (String *)instr->args[1]); break;
				case InstructionCode::LDCN:   gen_ldcn(code, (Variable *)instr->args[0], (Reference *)instr->args[1]); break;
				case InstructionCode::NEW:    gen_new(code, (Variable *)instr->args[0], (Variable *)instr->args[1]); break;
				case InstructionCode::LT:     gen_lt(code, (Variable *)instr->args[0], (Variable *)instr->args[1], (Variable *)instr->args[2]); break;
				case InstructionCode::GT:     gen_gt(code, (Variable *)instr->args[0], (Variable *)instr->args[1], (Variable *)instr->args[2]); break;
				case InstructionCode::LTE:    gen_lte(code, (Variable *)instr->args[0], (Variable *)instr->args[1], (Variable *)instr->args[2]); break;
				case InstructionCode::GTE:    gen_gte(code, (Variable *)instr->args[0], (Variable *)instr->args[1], (Variable *)instr->args[2]); break;
				case InstructionCode::EQ:     gen_eq(code, (Variable *)instr->args[0], (Variable *)instr->args[1], (Variable *)instr->args[2]); break;
				case InstructionCode::NEQ:    gen_neq(code, (Variable *)instr->args[0], (Variable *)instr->args[1], (Variable *)instr->args[2]); break;
				default: throw new std::exception("JITCompiler::compileInstruction: invalid instruction code!");
			}
		}

		void compile(const string &fnName)
		{
			int capacity = 4096, limit = 4000;	// 4kiB, 4kB
			char *code = new char[capacity];
			int length = 0;
			hlp_addresses.clear();
			hlp_jump_loc.clear();
			//
			length += gen_prolog(code+length);
			//
			for(int i = functions->find(fnName)->second->pointer, im = i + functions->find(fnName)->second->length; i < im; i++)
			{
				hlp_addresses[(char)i] = (char)length;
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
			for(size_t i = 0, im = hlp_jump_loc.size(); i < im; i++)
			{
				jmp_address = code + hlp_addresses[(*((char *)(hlp_jump_loc[i])))];
				(*((char *)(hlp_jump_loc[i]))) = char(jmp_address - hlp_jump_loc[i] - 1);	// 1B offset! (1-complement)
			}
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