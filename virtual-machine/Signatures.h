#ifndef _RUN_SIGNATURES_H_
#define _RUN_SIGNATURES_H_

#include <map>
using std::map;
using std::pair;

#include "DataType.h"

class StructureSignature
{
	public:
		string name;
		map<string, DataType *> items;

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
		map<string, DataType *> arguments;
		map<string, DataType *> declarations;	// declarations inside of a function

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
			}
			while(is.get() != '[');
			while(1)
			{
				is >> token;	// data type
				if(token == "]") break;
				type = DataType::parse(token);
				is >> token;	// name
				fn->declarations[token] = type;
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