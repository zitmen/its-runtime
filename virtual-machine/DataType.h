#ifndef _RUN_DATA_TYPE_H_
#define _RUN_DATA_TYPE_H_

class DataType
{
	public:
		static enum { INVALID, VOID, INTEGER, DOUBLE, FILE, ARRAY, STRING, STRUCTURE };

		int type;
		DataType *subtype;

		DataType()
		{
			type = INVALID;
			subtype = NULL;
		}

		~DataType()
		{
			delete subtype;
		}

		static DataType * parse(const string &token)
		{
			DataType *type = new DataType;
			size_t ptr = token.find_first_of('<');
			if(ptr == string::npos)
				type->type = getTypeCode(token);
			else
			{
				type->type = getTypeCode(token.substr(0, ptr));
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
			if(tmp == "DOUBLE") return DOUBLE;
			if(tmp == "FILE") return FILE;
			if(tmp == "ARRAY") return ARRAY;
			if(tmp == "STRING") return STRING;
			if(tmp == "STRUCTURE") return STRUCTURE;
			return INVALID;
		}

		string toString() const
		{
			string str;
			switch(type)
			{
				case INVALID: str = "INVALID"; break;
				case VOID: str = "VOID"; break;
				case INTEGER: str = "INTEGER"; break;
				case DOUBLE: str = "DOUBLE"; break;
				case FILE: str = "FILE"; break;
				case ARRAY: str = "ARRAY<" + subtype->toString() + ">"; break;
				case STRING: str = "STRING"; break;
				case STRUCTURE: str = "STRUCTURE<" + subtype->toString() + ">"; break;
			}
			return str;
		}
};

#endif	// _RUN_DATA_TYPE_H_