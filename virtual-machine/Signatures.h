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
		map<string, DataType> items;

		static StructureSignature * parse(istringstream &is)
		{
			return NULL;
		}
};

class FunctionSignature
{
	public:
		int pointer;	// line number, where a function begins
		int length;		// number of instructions of a function
		string name;
		map<string, DataType> arguments;
		map<string, DataType> declarations;	// declarations inside of a function

		static FunctionSignature * parse(istringstream &is)
		{
			return NULL;
		}
};

#endif	// _RUN_SIGNATURES_H_