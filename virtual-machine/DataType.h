#ifndef _RUN_DATA_TYPE_H_
#define _RUN_DATA_TYPE_H_

#include <cstdio>
#include <string>
using std::string;

class DataType
{
	public:
		static enum { INVALID, VOID, INTEGER, BOOLEAN, DOUBLE, FILE, ARRAY, STRING, STRUCTURE, REFERENCE };

		int type;
		string name;
		DataType *subtype;

		DataType(int type = INVALID, string name = "", DataType *subtype = NULL)
		{
			this->type = type;
			this->name = name;
			this->subtype = subtype;
		}

		~DataType()
		{
			delete subtype;
		}

		int getSize() const;

		static DataType * parse(const string &token)
		{
			DataType *type = new DataType;
			size_t ptr = token.find_first_of('<');
			if(ptr == string::npos)
				type->type = getTypeCode(token);
			else
			{
				type->type = getTypeCode(token.substr(0, ptr));
				if(type->type == DataType::STRUCTURE)
				{
					size_t endptr = token.find_first_of('>', ptr);
					type->name = token.substr(ptr + 1, endptr - ptr - 1);
				}
				else	// Array
					type->subtype = parse(token.substr(ptr + 1));
			}
			return type;
		}

		static int getTypeCode(const string &strVal)
		{
			int ptr = strVal.find_first_of('>');
			string tmp = ((ptr == string::npos) ? strVal : strVal.substr(0, ptr));
			if(tmp == "VOID") return VOID;
			if(tmp == "INTEGER") return INTEGER;
			if(tmp == "BOOLEAN") return BOOLEAN;
			if(tmp == "DOUBLE") return DOUBLE;
			if(tmp == "FILE") return FILE;
			if(tmp == "ARRAY") return ARRAY;
			if(tmp == "STRING") return STRING;
			if(tmp == "STRUCTURE") return STRUCTURE;
			if(tmp == "REFERENCE") return REFERENCE;
			return INVALID;
		}

		static int getTypeSize(int type)
		{
			switch(type)
			{
				case INTEGER:
					return sizeof(int);

				case BOOLEAN:
					return sizeof(bool);

				case FILE:
					return sizeof(::FILE *);
				
				case DOUBLE:
					return sizeof(double);

				case ARRAY:
				case STRING: 
				case STRUCTURE:
				case REFERENCE:
					return sizeof(void *);	// stored as a pointer

				default:
					return 0;
			}
		}

		string toString() const
		{
			string str;
			switch(type)
			{
				case INVALID: str = "INVALID"; break;
				case VOID: str = "VOID"; break;
				case INTEGER: str = "INTEGER"; break;
				case BOOLEAN: str = "BOOLEAN"; break;
				case DOUBLE: str = "DOUBLE"; break;
				case FILE: str = "FILE"; break;
				case ARRAY: str = "ARRAY<" + subtype->toString() + ">"; break;
				case STRING: str = "STRING"; break;
				case STRUCTURE: str = "STRUCTURE<" + name + ">"; break;
				case REFERENCE: str = "REFERENCE"; break;
			}
			return str;
		}
};

#endif	// _RUN_DATA_TYPE_H_