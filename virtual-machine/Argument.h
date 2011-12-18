#ifndef _RUN_ARGUMENT_H_
#define _RUN_ARGUMENT_H_

#include <sstream>
#include <ostream>
#include <string>
#include <exception>
#include <vector>
#include <map>
using std::istringstream;
using std::ostringstream;
using std::string;
using std::ostream;
using std::vector;
using std::map;

#include "DataType.h"

class StructureSignature;

class Argument
{
	public:
		virtual string toString() const = 0 { }
		virtual int getType() const = 0 { }
};

class Integer : public Argument
{
	private:
		static ostringstream os;
		int m_value;

	public:
		Integer(int val)
		{
			m_value = val;
		}

		Integer(void *addr)
		{
			m_value = (*((int *)addr));
		}

		virtual int getType() const
		{
			return DataType::INTEGER;
		}

		int getValue() const
		{
			return m_value;
		}

		static Integer * parse(istringstream &is)
		{
			int val;
			is >> val;
			return new Integer(val);
		}

		virtual string toString() const
		{
			os.str("");
			os << m_value;
			return os.str();
		}
};

class Double : public Argument
{
	private:
		static ostringstream os;
		double m_value;

	public:
		Double(double val)
		{
			m_value = val;
		}

		Double(void *addr)
		{
			m_value = (*((double *)addr));
		}

		virtual int getType() const
		{
			return DataType::DOUBLE;
		}

		double getValue() const
		{
			return m_value;
		}

		static Double * parse(istringstream &is)
		{
			double val;
			is >> val;
			return new Double(val);
		}

		virtual string toString() const
		{
			os.str("");
			os << m_value;
			return os.str();
		}
};

class Boolean : public Argument
{
	private:
		bool m_value;

	public:
		Boolean(bool val)
		{
			m_value = val;
		}

		Boolean(void *addr)
		{
			m_value = (*((bool *)addr));
		}

		virtual int getType() const
		{
			return DataType::BOOLEAN;
		}

		bool getValue() const
		{
			return m_value;
		}

		static Boolean * parse(istringstream &is)
		{
			string val;
			is >> val;
			return new Boolean(val == "TRUE");
		}

		virtual string toString() const
		{
			return (m_value ? "TRUE" : "FALSE");
		}
};

class String : public Argument
{
	private:
		char *m_address;

	public:
		String()
		{
			//
		}

		String(void *addr)
		{
			m_address = ((char *)addr);
		}

		String(char *str)
		{
			m_address = str;
		}

		void setValue(char *str)
		{
			m_address = str;
		}

		virtual int getType() const
		{
			return DataType::STRING;
		}

		const char * getValue() const
		{
			return m_address;
		}

		char * getValue()
		{
			return m_address;
		}

		static String * parse(istringstream &is)
		{	// allocation of the string will be done in LDCS instruction
			string val;
			while(is.get() != '\"');
			getline(is, val, '\"');
			char *str = new char[val.length()+1];
			return new String(strcpy(str, val.c_str()));
		}

		virtual string toString() const
		{
			return ('"' + string(m_address) + '"');
		}
};

class Array : public Argument
{
	private:
		static ostringstream os;
		void *m_address;

	public:
		Array()
		{
			m_address = NULL;
		}

		Array(void *addr)
		{
			m_address = addr;
		}

		// returns base address of the array
		void * getAddress() const
		{
			return m_address;
		}

		// returns count of bytes that are needed for the allocation
		int getAllocSize() const
		{
			// [int]length + [int]type + (length*item_size)
			return ((2*sizeof(int)) + (getLength()*getItemSize()));
		}

		// returns length of the array
		int getLength() const
		{
			return (*((int *)m_address));
		}

		// returns type code of the array elements
		virtual int getType() const
		{
			return DataType::ARRAY;
		}

		// returns type code of the array elements
		int getItemType() const
		{
			return (*(((int *)m_address)+1));
		}

		// sets length of the array
		void setLength(int length) const
		{
			(*((int *)m_address)) = length;
		}

		// sets type code of the array elements
		void setType(int type)
		{
			(*(((int *)m_address)+1)) = type;
		}

		// returns size of item size
		int getItemSize() const
		{
			return DataType::getTypeSize(getItemType());
		}

		// returns pointer to an element of the array
		void * getElementAt(int index)
		{
			if(index >= getLength()) throw new std::exception("Array::getElementAt: Array index out of bound!");
			return (((int *)(((char *)m_address)+(index*getItemSize())))+1);
		}

		virtual string toString() const
		{
			os.str("");
			os << std::hex << "0x" << m_address;
			return os.str();
		}
};

class Structure : public Argument
{
	private:
		static ostringstream os;
		void *m_address;
		StructureSignature *m_struct_sig;

	public:
		Structure(void *addr = NULL)
		{
			m_address = addr;
		}

		void setSignature(StructureSignature *sig)
		{
			m_struct_sig = sig;
		}

		// returns base address of the array
		void * getAddress() const
		{
			return m_address;
		}
/*
		// returns count of bytes that are needed for the allocation
		int getAllocSize() const
		{
			// [int]length + [int]type + (length*item_size)
			return ((2*sizeof(int)) + (getLength()*getItemSize()));
		}

		// returns length of the array
		int getLength() const
		{
			return (*((int *)m_address));
		}
*/
		// returns type code of the array elements
		virtual int getType() const
		{
			return DataType::STRUCTURE;
		}
/*
		// returns size of item size
		int getItemSize() const
		{
			return DataType::getTypeSize(getType());
		}

		// returns pointer to an element of the array
		void * getElementAt(int index)
		{
			if(index >= getLength()) throw new std::exception("Array::getElementAt: Array index out of bound!");
			return (((int *)(((char *)m_address)+(index*getItemSize())))+1);
		}
*/
		virtual string toString() const
		{
			os.str("");
			os << std::hex << "0x" << m_address;
			return os.str();
		}
};

class Reference : public Argument
{
	private:
		static ostringstream os;
		void *m_value;

	public:
		Reference(void *value = NULL)
		{
			m_value = value;
		}

		virtual int getType() const
		{
			return DataType::REFERENCE;
		}

		void * getValue() const
		{
			return m_value;
		}

		static Reference * parse(istringstream &is)
		{	// only reference that is parsed is NULL
			string val;
			is >> val;
			return new Reference();
		}

		virtual string toString() const
		{
			os.str("");
			os << "REFERENCE[0x" << std::hex << m_value << "]";
			return os.str();
		}
};

class File : public Argument
{
	private:
		static ostringstream os;
		FILE *m_value;

	public:
		File(void *addr)
		{
			m_value = ((FILE *)addr);
		}

		File(FILE *fp)
		{
			m_value = fp;
		}

		virtual int getType() const
		{
			return DataType::FILE;
		}

		FILE * getValue() const
		{
			return m_value;
		}

		virtual string toString() const
		{
			os.str("");
			os << "FILE[" << m_value << "]";
			return  os.str();
		}
};

class Variable : public Argument
{
	friend class JITCompiler;

	private:
		string m_name;
		DataType *m_type;
		void *m_address;
		bool m_retval;
		vector<Argument *> m_index;
		Variable *m_base;

	public:
		Variable(const char *name, DataType *type = NULL, bool retval = false)	// retval specifies if it is return value of a function or not
		{
			m_name = name;
			m_type = type;
			m_address = NULL;
			m_retval = retval;
			m_base = NULL;
		}

		DataType * getDataType() const
		{
			return ((m_base == NULL) ? m_type : m_base->getDataType());
		}

		virtual int getType() const
		{
			return getDataType()->type;
		}

		DataType * getItemDataType() const;
		int getItemType() const;
		int getItemTypeSize() const;

		void * getVarAddress() const
		{
			return ((m_base == NULL) ? m_address : m_base->getAddress());
		}

		void * getAddress() const;

		void setAddress(void *addr)
		{
			m_address = addr;
		}

		string & getName()
		{
			return m_name;
		}

		void setValue(void *val_ptr)
		{
			switch(getItemType())
			{
				case DataType::ARRAY:      setValue(new Array(*((void **)val_ptr))); break;
				case DataType::STRUCTURE:  setValue(new Structure(*((void **)val_ptr))); break;
				case DataType::REFERENCE:  setValue(new Reference(*((void **)val_ptr))); break;
				case DataType::STRING:     setValue(new String(*((char **)val_ptr))); break;
				case DataType::INTEGER:    setValue(new Integer(*((int *)val_ptr))); break;
				case DataType::DOUBLE:     setValue(new Double(*((double *)val_ptr))); break;
				case DataType::BOOLEAN:    setValue(new Boolean(*((bool *)val_ptr))); break;
				case DataType::FILE:       setValue(new File(*((FILE **)val_ptr))); break;
				default: throw new std::exception("Argument::setValue: unsupported argument type!");
			}
		}

		void setValue(Argument *val)
		{
			switch(val->getType())
			{
				case DataType::ARRAY:      (*((void **)getAddress())) = ((Array *)val)->getAddress(); break;
				case DataType::STRUCTURE:  (*((void **)getAddress())) = ((Structure *)val)->getAddress(); break;
				case DataType::REFERENCE:  (*((void **)getAddress())) = ((Reference *)val)->getValue(); break;
				case DataType::STRING:     (*((void **)getAddress())) = (void *)((String *)val)->getValue(); break;
				case DataType::INTEGER:    (*((int *)getAddress())) = ((Integer *)val)->getValue(); break;
				case DataType::DOUBLE:     (*((double *)getAddress())) = ((Double *)val)->getValue(); break;
				case DataType::BOOLEAN:    (*((bool *)getAddress())) = ((Boolean *)val)->getValue(); break;
				case DataType::FILE:       (*((FILE **)getAddress())) = ((File *)val)->getValue(); break;
				default: throw new std::exception("Argument::setValue: unsupported argument type!");
			}
		}

		void setValue(Variable *var)
		{
			switch(var->getItemType())
			{
				case DataType::ARRAY:
				case DataType::STRUCTURE:
				case DataType::REFERENCE:
				case DataType::STRING:
				case DataType::INTEGER:
				case DataType::DOUBLE:
				case DataType::BOOLEAN:
				case DataType::FILE:
					setValue(var->getValue());
					break;

				default:
					throw new std::exception("Argument::setValue: unsupported argument type!");
			}
		}

		Argument * getValue() const
		{
			switch(getItemType())
			{
				case DataType::INTEGER: return new Integer(getAddress()); break;
				case DataType::DOUBLE: return new Double(getAddress()); break;
				case DataType::BOOLEAN: return new Boolean(getAddress()); break;
				case DataType::REFERENCE: return new Reference((void *)(*((void **)(getAddress())))); break;
				case DataType::FILE: return new File((FILE *)(*((FILE **)(getAddress())))); break;
				case DataType::STRING: return new String((char *)(*((char **)(getAddress())))); break;
				case DataType::ARRAY: return new Array((void *)(*((void **)(getAddress())))); break;
                case DataType::STRUCTURE: return new Structure((void *)(*((void **)(getAddress())))); break;
				default: throw new std::exception("Variable::getValue: invalid type!");
			}
		}

		static Variable * parse(istringstream &is, map<string, Variable *> *variables = NULL)
		{
			string name;
			is >> name;
			Variable *var = new Variable(name.c_str()), *tmp;
			char c;
			while(!isalnum(is.peek()))
			{	// f.e.: var0 [ @a var1 ] => var0 is struct, var0.a is array, var.a[var1] is variable that we lookin' for
				c = is.get();
				if(is.eof()) break;
				if(isspace(c)) continue;
				if(c == '[')
				{
					while(1)
					{
						is >> name;
						if(name == "]") break;
						if(name[0] == '@')
						{	// struct element
							name = name.substr(1);
							char *strval = new char[name.length()+1];
							var->m_index.push_back(new String(strcpy(strval, name.c_str())));
						}
						else
						{	// array element
							if(variables == NULL) throw new std::exception("Variable::parse: unknown index!");
							tmp = new Variable(name.c_str(), new DataType(DataType::INTEGER));
							tmp->m_base = variables->find(tmp->m_name)->second;
							var->m_index.push_back(tmp);
						}
					}
				}
				else
				{	// some other character, that is not part of variable token
					is.putback(c);
					break;
				}
			}
			if(variables != NULL) var->m_base = variables->find(var->m_name)->second;
			return var;
		}

		virtual string toString() const
		{
			string str = m_name;
			if(m_index.size() > 0)
			{
				str += " [ ";
				for(size_t i = 0, im = m_index.size(); i < im; i++)
				{
					if(m_index[i]->getType() == DataType::INTEGER)
						str += ((Variable *)m_index[i])->getName() + ' ';
					else
					{
						str += '@';
						str += ((String *)m_index[i])->getValue();
						str += ' ';
					}
				}
				str += ']';
			}
			return str;
		}
};

#endif	// _RUN_ARGUMENT_H_