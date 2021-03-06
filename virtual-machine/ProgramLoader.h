#ifndef _RUN_PROGRAM_LOADER_H_
#define _RUN_PROGRAM_LOADER_H_

#include <exception>
#include <fstream>
#include <sstream>
#include <vector>
#include <string>
#include <map>
using std::exception;
using std::vector;
using std::string;
using std::ifstream;
using std::istringstream;
using std::getline;
using std::map;
using std::pair;

#include "InstructionSet.h"
#include "Signatures.h"
#include "BuiltInRoutines.h"

class ProgramLoader
{
	private:
		vector<Instruction *> m_program;
		map<string, StructureSignature *> m_structures;
		map<string, FunctionSignature *> m_functions;
		map<string, Variable *> m_variables;	// list of all variables in the program

	public:
		ProgramLoader(const string &source)
		{
			BuiltInRoutines::init();
			load(source);
		}

		~ProgramLoader()
		{
			/*
			for(vector<Instruction *>::iterator it = m_program.begin(); it != m_program.end(); ++it)
				delete (*it);
			m_program.clear();
			for(map<string, StructureSignature *>::iterator it = m_structures.begin(); it != m_structures.end(); ++it)
				delete it->second;
			m_structures.clear();
			for(map<string, FunctionSignature *>::iterator it = m_functions.begin(); it != m_functions.end(); ++it)
				delete it->second;
			m_functions.clear();
			*/
		}

		void load(const string &source)
		{
			ifstream fin(source.c_str(), std::ios_base::in);
			if(!fin.is_open()) throw new exception(("ProgramLoader::load: File '" + source + "' couldn't be open!").c_str());
			//
			string token;
			istringstream line;
			StructureSignature *structSig;
			FunctionSignature *funcSig;
			while(1)
			{
				getline(fin, token);
				if(fin.eof()) break;
				line.clear();
				line.str(token);
				//
				line >> token;
				if(token.length() == 0) continue;
				if(token == "STRUCTURE")
				{
					structSig = StructureSignature::parse(line);
					m_structures[structSig->name] = structSig;
				}
				else if(token == "FUNCTION")
				{
					funcSig = FunctionSignature::parse(line);
					m_functions[funcSig->name] = funcSig;
				}
				else if(token == "CODE")	// only informative, that signature declarations are done
				{	// make list of all variables in the program
					for(map<string, FunctionSignature *>::iterator it = m_functions.begin(); it != m_functions.end(); ++it)
					{
						m_variables[it->first] = new Variable(it->second->name.c_str(), it->second->return_type, true);	// return value of a function
						for(map<string, Variable *>::iterator jt = it->second->variables.begin(); jt != it->second->variables.end(); ++jt)
							m_variables[jt->first] = jt->second;
					}
					// then add return variables for built-in functions
					for(map<string, DataType *>::iterator it = BuiltInRoutines::routinesList.begin(); it != BuiltInRoutines::routinesList.end(); ++it)
						m_variables[it->first] = new Variable(it->first.c_str(), it->second, true);	// return value of a function
				}
				else	// instruction
					m_program.push_back(Instruction::parse(m_variables, token, line));
			}
			//
			fin.close();
		}

		vector<Instruction *> * getProgram()
		{
			return &m_program;
		}

		map<string, StructureSignature *> * getStructures()
		{
			return &m_structures;
		}

		map<string, FunctionSignature *> * getFunctions()
		{
			return &m_functions;
		}

		void printProgram(ostream &os) const
		{
			for(map<string, StructureSignature *>::const_iterator it = m_structures.begin(); it != m_structures.end(); ++it)
				os << it->second->toString() << '\n';
			for(map<string, FunctionSignature *>::const_iterator it = m_functions.begin(); it != m_functions.end(); ++it)
				os << it->second->toString() << '\n';
			os << "CODE\n";
			for(size_t i = 0, im = m_program.size(); i < im; i++)
			{
				os << i << ':' << InstructionCode::getStrCode(m_program[i]->code);
				for(size_t a = 0, am = m_program[i]->args.size(); a < am; a++)
					os << ' ' << m_program[i]->args[a]->toString();
				os << '\n';
			}
		}
};

#endif	// _RUN_PROGRAM_LOADER_H_