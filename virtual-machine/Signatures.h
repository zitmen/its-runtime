#ifndef _RUN_SIGNATURES_H_
#define _RUN_SIGNATURES_H_

#include <sstream>
#include <map>
#include <vector>
using std::vector;
using std::map;
using std::pair;
using std::istringstream;
using std::ostringstream;

#include "DataType.h"
#include "Argument.h"

class StructureSignature
{
	public:
		string name;
		vector<string> items_ordering;	// arguments int the order from the first to the last
		map<string, DataType *> items;

		~StructureSignature()
		{
			for(map<string, DataType *>::const_iterator it = items.begin(); it != items.end(); ++it)
				delete it->second;
			items.clear();
		}

		static StructureSignature * parse(istringstream &is)
		{	// STRUCTURE structure_name [ DATA_TYPE item_1 ... DATA_TYPE item_n ]
			DataType *type;
			string token;
			StructureSignature *st = new StructureSignature;
			is >> st->name;
			while(is.get() != '[');
			while(1)
			{
				is >> token;	// data type
				if(token == "]") break;
				type = DataType::parse(token);
				is >> token;	// name
				st->items[token] = type;
				st->items_ordering.push_back(token);
			}
			return st;
		}

		string toString() const
		{
			ostringstream os;
			os << "STRUCTURE " + name + " [ ";
			for(map<string, DataType *>::const_iterator it = items.begin(); it != items.end(); ++it)
				os << it->second->toString() << ' ' << it->first << ' ';
			os << ']';
			return os.str();
		}
};

class FunctionSignature
{
	public:
		int pointer;	// line number, where a function begins
		int length;		// number of instructions of a function
		string name;
		DataType *return_type;
		vector<string> arguments_ordering;	// arguments int the order from the left to rhe right
		map<string, DataType *> arguments;
		map<string, DataType *> declarations;	// declarations inside of a function
		map<string, Variable *> variables;	// list of all variables in a function

		~FunctionSignature()
		{
			/*
			for(map<string, DataType *>::iterator it = arguments.begin(); it != arguments.end(); ++it)
				delete it->second;
			arguments.clear();
			for(map<string, DataType *>::iterator it = declarations.begin(); it != declarations.end(); ++it)
				delete it->second;
			declarations.clear();
			for(map<string, Variable *>::iterator it = variables.begin(); it != variables.end(); ++it)
				delete it->second;
			variables.clear();
			*/
		}

		static FunctionSignature * parse(istringstream &is)
		{	// FUNCTION <RETURN_TYPE> function_name [ DATA_TYPE argument_1 ... DATA_TYPE argument_n ] [ DATA_TYPE declared_variable_1 ... DATA_TYPE declared_variable_2 ] @ line_where_function_starts
			DataType *type;
			string token;
			FunctionSignature *fn = new FunctionSignature;
			while(is.get() != '<');
			is >> token;
			fn->return_type = DataType::parse(token);
			is >> fn->name;
			while(is.get() != '[');
			while(1)
			{
				is >> token;	// data type
				if(token == "]") break;
				type = DataType::parse(token);
				is >> token;	// name
				fn->arguments[token] = type;
				fn->variables[token] = new Variable(token.c_str(), type);
				fn->arguments_ordering.push_back(token);
			}
			while(is.get() != '[');
			while(1)
			{
				is >> token;	// data type
				if(token == "]") break;
				type = DataType::parse(token);
				is >> token;	// name
				fn->declarations[token] = type;
				fn->variables[token] = new Variable(token.c_str(), type);
			}
			while(is.get() != '@');
			is >> fn->pointer;
			return fn;
		}

		string toString() const
		{
			ostringstream os;
			os << "FUNCTION <" + return_type->toString() + "> " + name + " [ ";
			for(map<string, DataType *>::const_iterator it = arguments.begin(); it != arguments.end(); ++it)
				os << it->second->toString() << ' ' << it->first << ' ';
			os << "] [ ";
			for(map<string, DataType *>::const_iterator it = declarations.begin(); it != declarations.end(); ++it)
				os << it->second->toString() << ' ' << it->first << ' ';
			os << "] @ " << pointer;
			return os.str();
		}
};

#endif	// _RUN_SIGNATURES_H_