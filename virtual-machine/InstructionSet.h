#ifndef _RUN_INSTRUCTION_SET_H_
#define _RUN_INSTRUCTION_SET_H_

#include <vector>
#include <map>
#include <string>
using std::vector;
using std::map;
using std::pair;
using std::string;

#include "Argument.h"
#include "Signatures.h"

class InstructionCode
{
	private:
		static map<string, int> *convertor;

	public:
		static enum
		{
			INVALID,	// invalid instruction code
			JZ,		// Jump if Zero
			JNZ,	// Jump if Not Zero
			JMP,	// JuMP
			RET,	// RETurn
			RETV,	// RETurn Value
			POP,	// POP from the stack
			ST,		// STore
			STA,	// STore Array - stores array's or structure's items by indices(Integer) or member names(String)
			ADD,	// ADD
			SUB,	// SUBtract
			MUL,	// MULtiply
			DIV,	// DIVide
			MOD,	// MODulo
			AND,	// AND
			OR,		// OR
			XOR,	// XOR
			LSH,	// Left SHift
			RSH,	// Right SHift
			INC,	// INCrement
			DEC,	// DECrement
			NOT,	// NOT - logical(!)
			NEG,	// NEG - bitwise(~)
			MINUS,	// MINUS - unary
			CALL,	// CALL user defined function
			INVOKE,	// INVOKE predefined routine
			LDCI,	// LoaD Constant Integer
			LDCB,	// LoaD Constant Bool
			LDCR,	// LoaD Constant Real
			LDCS,	// LoaD Constant String
			LDCN,	// LoaD Constant Null
			NEW,	// NEW instance of a structure or of an array
			LT,		// Less Than
			GT,		// Greater Than
			LTE,	// Less Than or Equal
			GTE,	// Greater Than or Equal
			EQ,		// EQual
			NEQ		// Not EQual
		};

		static string getStrCode(int code)
		{
			static string convertor[] =
			{
				"INVALID", "JZ", "JNZ", "JMP", "RET", "RETV", "POP", "ST", "STA", "ADD", "SUB", "MUL", "DIV", "MOD", "AND",
				"OR", "XOR", "LSH", "RSH", "INC", "DEC", "NOT", "NEG", "MINUS", "CALL", "INVOKE", "LDCI", "LDCB",
				"LDCR", "LDCS", "LDCN", "NEW", "LT", "GT", "LTE", "GTE", "EQ", "NEQ"
			};
			return convertor[code];
		}

		static int getCode(const string &strCode)
		{
			if(convertor == NULL)
			{
				convertor = new map<string, int>();
				convertor->insert(std::pair<string, int>("JZ", JZ));
				convertor->insert(std::pair<string, int>("JNZ", JNZ));
				convertor->insert(std::pair<string, int>("JMP", JMP));
				convertor->insert(std::pair<string, int>("RET", RET));
				convertor->insert(std::pair<string, int>("RETV", RETV));
				convertor->insert(std::pair<string, int>("POP", POP));
				convertor->insert(std::pair<string, int>("ST", ST));
				convertor->insert(std::pair<string, int>("STA", STA));
				convertor->insert(std::pair<string, int>("ADD", ADD));
				convertor->insert(std::pair<string, int>("SUB", SUB));
				convertor->insert(std::pair<string, int>("MUL", MUL));
				convertor->insert(std::pair<string, int>("DIV", DIV));
				convertor->insert(std::pair<string, int>("MOD", MOD));
				convertor->insert(std::pair<string, int>("AND", AND));
				convertor->insert(std::pair<string, int>("OR", OR));
				convertor->insert(std::pair<string, int>("XOR", XOR));
				convertor->insert(std::pair<string, int>("LSH", LSH));
				convertor->insert(std::pair<string, int>("RSH", RSH));
				convertor->insert(std::pair<string, int>("INC", INC));
				convertor->insert(std::pair<string, int>("DEC", DEC));
				convertor->insert(std::pair<string, int>("NOT", NOT));
				convertor->insert(std::pair<string, int>("NEG", NEG));
				convertor->insert(std::pair<string, int>("MINUS", MINUS));
				convertor->insert(std::pair<string, int>("CALL", CALL));
				convertor->insert(std::pair<string, int>("INVOKE", INVOKE));
				convertor->insert(std::pair<string, int>("LDCI", LDCI));
				convertor->insert(std::pair<string, int>("LDCR", LDCR));
				convertor->insert(std::pair<string, int>("LDCS", LDCS));
				convertor->insert(std::pair<string, int>("LDCB", LDCB));
				convertor->insert(std::pair<string, int>("LDCN", LDCN));
				convertor->insert(std::pair<string, int>("NEW", NEW));
				convertor->insert(std::pair<string, int>("LT", LT));
				convertor->insert(std::pair<string, int>("GT", GT));
				convertor->insert(std::pair<string, int>("LTE", LTE));
				convertor->insert(std::pair<string, int>("GTE", GTE));
				convertor->insert(std::pair<string, int>("EQ", EQ));
				convertor->insert(std::pair<string, int>("NEQ", NEQ));
			}
			return convertor->find(strCode)->second;
		}
};

class Instruction
{
	public:
		int code;
		vector<Argument *> args;

		~Instruction()
		{	// DO NOT DELETE! IT'll BE DELETED IN FUNCTION's ARGS AND DECLS!
			/*for(vector<Argument *>::iterator it = args.begin(); it != args.end(); ++it)
				delete (*it);*/
			args.clear();
		}

		static Instruction * parse(map<string, Variable *> &variables, string &token, istringstream &is)
		{	// LINE: INSTRUCTION_CODE ARGUMENT1 ARGUMENT2 ... ARGUMENTn
			// line number is ignored (it's saved in token argument)
			Instruction *instr = new Instruction;
			is.get();	// ':'
			is >> token;
			instr->code = InstructionCode::getCode(token);
			switch(instr->code)
			{
				// 1 argument (constant)
				// -- integer
				case InstructionCode::JZ:
				case InstructionCode::JNZ:
				case InstructionCode::JMP:
					instr->args.push_back(Integer::parse(is));
					break;

				// 2 arguments (variable and constant)
				// -- integer
				case InstructionCode::LDCI:
					instr->args.push_back(variables[Variable::parse(is)->getName()]);
					instr->args.push_back(Integer::parse(is));
					break;

				// -- boolean
				case InstructionCode::LDCB:
					instr->args.push_back(variables[Variable::parse(is)->getName()]);
					instr->args.push_back(Boolean::parse(is));
					break;

				// -- double
				case InstructionCode::LDCR:
					instr->args.push_back(variables[Variable::parse(is)->getName()]);
					instr->args.push_back(Double::parse(is));
					break;

				// -- string
				case InstructionCode::LDCS:
					instr->args.push_back(variables[Variable::parse(is)->getName()]);
					instr->args.push_back(String::parse(is));
					break;

				// -- null
				case InstructionCode::LDCN:
					instr->args.push_back(variables[Variable::parse(is)->getName()]);
					instr->args.push_back(Reference::parse(is));
					break;

				// 0 arguments
				case InstructionCode::RET:
					break;

				// 1 argument (variable)
				case InstructionCode::RETV:
				case InstructionCode::INC:
				case InstructionCode::DEC:
					instr->args.push_back(variables[Variable::parse(is)->getName()]);
					break;

				// 3 arguments (variables)
				case InstructionCode::ST:
				case InstructionCode::ADD:
				case InstructionCode::SUB:
				case InstructionCode::MUL:
				case InstructionCode::DIV:
				case InstructionCode::MOD:
				case InstructionCode::AND:
				case InstructionCode::OR:
				case InstructionCode::XOR:
				case InstructionCode::LSH:
				case InstructionCode::RSH:
				case InstructionCode::NOT:
				case InstructionCode::NEG:
				case InstructionCode::MINUS:
				case InstructionCode::NEW:
				case InstructionCode::LT:
				case InstructionCode::GT:
				case InstructionCode::LTE:
				case InstructionCode::GTE:
				case InstructionCode::EQ:
				case InstructionCode::NEQ:
					instr->args.push_back(variables[Variable::parse(is)->getName()]);
					instr->args.push_back(variables[Variable::parse(is)->getName()]);
					instr->args.push_back(variables[Variable::parse(is)->getName()]);
					break;

				// Unknown count of arguments (all variables)
				case InstructionCode::STA:
				case InstructionCode::CALL:
				case InstructionCode::INVOKE:
				{
					Variable *arg;
					instr->args.push_back(Variable::parse(is));	// function/routine name
					while(1)
					{	// arguments
						arg = variables[Variable::parse(is)->getName()];
						if(!is.good()) break;
						instr->args.push_back(arg);
					}
					break;
				}

				// INVALID
				default:
					throw new std::exception(("Instruction::parse: Unknown instruction '" + token + "'!").c_str());
			}
			return instr;
		}
};

#endif	// _RUN_INSTRUCTION_SET_H_