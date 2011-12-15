#ifndef _RUN_INTERPRETER_H_
#define _RUN_INTERPRETER_H_

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

class Interpreter
{
	public:
		class Options { public: static enum { HeapSize, StackSize, GarbageCollector, JITCompiler }; };

	private:
		stack<FunctionSignature *> call_stack;
		double options[4];
		vector<Instruction *> *program;
		static map<string, StructureSignature *> *structures;
		static map<string, FunctionSignature *> *functions;
		map<string, int> fn_calls;	// counter of function calls; first=fn_name,second=counter
		int IP;	// Instruction Pointer -- points to an instruction after the currently processed one
		bool ZF;	// Zero Flag -- was the result of the previous arithmetic/bit/logic instruction zero?

	protected:
		void _call(FunctionSignature *fn, const vector<Argument *> &args);
		void _invoke(Variable *name, const vector<Argument *> &args);
		void _jz(const Integer *to);
		void _jnz(const Integer * to);
		void _jmp(const Integer * to);
		void _ret();
		void _retv(const Variable *var);
		void _pop(Variable *dest);
		void _st(Variable *dest, Variable *src);
		void _add(Variable *dest, const Variable *op1, const Variable *op2);
		void _sub(Variable *dest, const Variable *op1, const Variable *op2);
		void _mul(Variable *dest, const Variable *op1, const Variable *op2);
		void _div(Variable *dest, const Variable *op1, const Variable *op2);
		void _mod(Variable *dest, const Variable *op1, const Variable *op2);
		void _and(Variable *dest, const Variable *op1, const Variable *op2);
		void _or(Variable *dest, const Variable *op1, const Variable *op2);
		void _xor(Variable *dest, const Variable *op1, const Variable *op2);
		void _lsh(Variable *dest, const Variable *op1, const Variable *op2);
		void _rsh(Variable *dest, const Variable *op1, const Variable *op2);
		void _inc(Variable *var);
		void _dec(Variable *var);
		void _not(Variable *dest, const Variable *src);
		void _neg(Variable *dest, const Variable *src);
		void _minus(Variable *dest, const Variable *src);
		void _ldci(Variable *var, Integer *constant);
		void _ldcb(Variable *var, Boolean *constant);
		void _ldcr(Variable *var, Double *constant);
		void _ldcs(Variable *var, String *constant);
		void _ldcn(Variable *var, Reference *constant);
		void _new(Variable *var, const Variable *size);
		void _lt(Variable *dest, const Variable *op1, const Variable *op2);
		void _gt(Variable *dest, const Variable *op1, const Variable *op2);
		void _lte(Variable *dest, const Variable *op1, const Variable *op2);
		void _gte(Variable *dest, const Variable *op1, const Variable *op2);
		void _eq(Variable *dest, const Variable *op1, const Variable *op2);
		void _neq(Variable *dest, const Variable *op1, const Variable *op2);

	public:
		static MemoryManager *memory;

		static map<string, StructureSignature *> * getStructureSignatures()
		{
			if(structures == NULL) throw new std::exception("Interpreter::getStrustureSignatures: can't call this yet! First initialize interpreter.");
			return structures;
		}

		static map<string, FunctionSignature *> * getFunctionSignatures()
		{
			if(functions == NULL) throw new std::exception("Interpreter::getFunctionSignatures: can't call this yet! First initialize interpreter.");
			return functions;
		}

		Interpreter(vector<Instruction *> *program, map<string, StructureSignature *> *structures, map<string, FunctionSignature *> *functions)
		{
			this->program = program;
			this->structures = structures;
			this->functions = functions;
			memory = NULL;
			options[Options::HeapSize] = 32*1024*1024;	// 32MB
			options[Options::StackSize] = 32*1024*1024;	// 32MB
			options[Options::GarbageCollector] = 0.9;	// 90% of heap is full
			options[Options::JITCompiler] = 10;	// 10 iterations over 1 function
		}

		void setOption(int option, double val)
		{
			options[option] = val;
		}

		void init()
		{
			IP = 0;
			ZF = false;
			memory = new MemoryManager(options[Options::StackSize], options[Options::HeapSize], options[Options::GarbageCollector]);
			//
			if(functions)
				for(map<string, FunctionSignature *>::iterator it = functions->begin(); it != functions->end(); ++it)
					fn_calls.insert(pair<string, int>(it->first, 0));
		}

		void run()
		{
			init();
			Instruction first; first.code = InstructionCode::CALL; first.args.push_back(new Variable("main"));
			IP = program->size();	// it will jump out of program after return from main
			step(&first);
			while((IP >= 0) && (IP < program->size()))
				step(program->at(IP));
		}

		void step(const Instruction *instr)
		{
			switch(instr->code)
			{
				case InstructionCode::JZ: _jz((Integer *)instr->args[0]); break;
				case InstructionCode::JNZ: _jnz((Integer *)instr->args[0]); break;
				case InstructionCode::JMP: _jmp((Integer *)instr->args[0]); break;
				case InstructionCode::RET: _ret(); break;
				case InstructionCode::RETV: _retv((Variable *)instr->args[0]); break;
				case InstructionCode::POP: _pop((Variable *)instr->args[0]); break;
				case InstructionCode::ST: _st((Variable *)instr->args[0], (Variable *)instr->args[1]); break;
				case InstructionCode::ADD: _add((Variable *)instr->args[0], (Variable *)instr->args[1], (Variable *)instr->args[2]); break;
				case InstructionCode::SUB: _sub((Variable *)instr->args[0], (Variable *)instr->args[1], (Variable *)instr->args[2]); break;
				case InstructionCode::MUL: _mul((Variable *)instr->args[0], (Variable *)instr->args[1], (Variable *)instr->args[2]); break;
				case InstructionCode::DIV: _div((Variable *)instr->args[0], (Variable *)instr->args[1], (Variable *)instr->args[2]); break;
				case InstructionCode::MOD: _mod((Variable *)instr->args[0], (Variable *)instr->args[1], (Variable *)instr->args[2]); break;
				case InstructionCode::AND: _and((Variable *)instr->args[0], (Variable *)instr->args[1], (Variable *)instr->args[2]); break;
				case InstructionCode::OR: _or((Variable *)instr->args[0], (Variable *)instr->args[1], (Variable *)instr->args[2]); break;
				case InstructionCode::XOR: _xor((Variable *)instr->args[0], (Variable *)instr->args[1], (Variable *)instr->args[2]); break;
				case InstructionCode::LSH: _lsh((Variable *)instr->args[0], (Variable *)instr->args[1], (Variable *)instr->args[2]); break;
				case InstructionCode::RSH: _rsh((Variable *)instr->args[0], (Variable *)instr->args[1], (Variable *)instr->args[2]); break;
				case InstructionCode::INC: _inc((Variable *)instr->args[0]); break;
				case InstructionCode::DEC: _dec((Variable *)instr->args[0]); break;
				case InstructionCode::NOT: _not((Variable *)instr->args[0], (Variable *)instr->args[1]); break;
				case InstructionCode::NEG: _neg((Variable *)instr->args[0], (Variable *)instr->args[1]); break;
				case InstructionCode::MINUS: _minus((Variable *)instr->args[0], (Variable *)instr->args[1]); break;
				case InstructionCode::CALL: _call(functions->find(((Variable *)instr->args[0])->getName())->second, instr->args); break;
				case InstructionCode::INVOKE: _invoke((Variable *)instr->args[0], instr->args); break;
				case InstructionCode::LDCI: _ldci((Variable *)instr->args[0], (Integer *)instr->args[1]); break;
				case InstructionCode::LDCB: _ldcb((Variable *)instr->args[0], (Boolean *)instr->args[1]); break;
				case InstructionCode::LDCR: _ldcr((Variable *)instr->args[0], (Double *)instr->args[1]); break;
				case InstructionCode::LDCS: _ldcs((Variable *)instr->args[0], (String *)instr->args[1]); break;
				case InstructionCode::LDCN: _ldcn((Variable *)instr->args[0], (Reference *)instr->args[1]); break;
				case InstructionCode::NEW: _new((Variable *)instr->args[0], (Variable *)instr->args[1]); break;
				case InstructionCode::LT: _lt((Variable *)instr->args[0], (Variable *)instr->args[1], (Variable *)instr->args[2]); break;
				case InstructionCode::GT: _gt((Variable *)instr->args[0], (Variable *)instr->args[1], (Variable *)instr->args[2]); break;
				case InstructionCode::LTE: _lte((Variable *)instr->args[0], (Variable *)instr->args[1], (Variable *)instr->args[2]); break;
				case InstructionCode::GTE: _gte((Variable *)instr->args[0], (Variable *)instr->args[1], (Variable *)instr->args[2]); break;
				case InstructionCode::EQ: _eq((Variable *)instr->args[0], (Variable *)instr->args[1], (Variable *)instr->args[2]); break;
				case InstructionCode::NEQ: _neq((Variable *)instr->args[0], (Variable *)instr->args[1], (Variable *)instr->args[2]); break;
				default: throw new std::exception("Interpreter::step: invalid instruction code!");
			}
		}
};

#endif	// _RUN_INTERPRETER_H_