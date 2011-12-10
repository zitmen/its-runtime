#ifndef _RUN_ARGUMENT_H_
#define _RUN_ARGUMENT_H_

#include <sstream>
#include <ostream>
#include <string>
#include <exception>
using std::istringstream;
using std::ostringstream;
using std::string;
using std::ostream;

#include "DataType.h"

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
			is >> val;
			val = val.substr(1, val.length() - 2);
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
			return (*(((int *)m_address)+1));
		}

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

	public:
		Structure(void *addr = NULL)
		{
			m_address = addr;
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
	private:
		string m_name;
		DataType *m_type;
		void *m_address;
		bool m_retval;

	public:
		Variable(const char *name, DataType *type = NULL, bool retval = false)	// retval specifies if it is return value of a function or not
		{
			m_name = name;
			m_type = type;
			m_address = NULL;
			m_retval = retval;
		}

		virtual int getType() const
		{
			return m_type->type;
		}

		void * getAddress() const
		{
			return m_address;
		}

		void setAddress(void *addr)
		{
			m_address = addr;
		}

		string & getName()
		{
			return m_name;
		}

		void setValue(Argument *val)
		{
			switch(val->getType())
			{
				case DataType::ARRAY:      (*((void **)m_address)) = ((Array *)val)->getAddress(); break;
				case DataType::STRUCTURE:  (*((void **)m_address)) = ((Structure *)val)->getAddress(); break;
				case DataType::REFERENCE:  (*((void **)m_address)) = ((Reference *)val)->getValue(); break;
				case DataType::STRING:     (*((void **)m_address)) = (void *)((String *)val)->getValue(); break;
				case DataType::INTEGER:    (*((int *)m_address)) = ((Integer *)val)->getValue(); break;
				case DataType::DOUBLE:     (*((double *)m_address)) = ((Double *)val)->getValue(); break;
				case DataType::BOOLEAN:    (*((bool *)m_address)) = ((Boolean *)val)->getValue(); break;
				case DataType::FILE:       (*((FILE **)m_address)) = ((File *)val)->getValue(); break;
				default: throw new std::exception("Argument::setValue: unsupported argument type!");
			}
		}

		void setValue(Variable *var)
		{
			switch(var->getType())
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
			switch(getType())
			{
				case DataType::INTEGER: return new Integer(getAddress()); break;
				case DataType::DOUBLE: return new Double(getAddress()); break;
				case DataType::BOOLEAN: return new Boolean(getAddress()); break;
				case DataType::REFERENCE: return new Reference((void *)(*((void **)(getAddress())))); break;
				case DataType::FILE: return new File((FILE *)(*((FILE **)(getAddress())))); break;
				case DataType::STRING: return new String((char *)(*((char **)(getAddress())))); break;
				case DataType::ARRAY: return new Array((void *)(*((void **)(getAddress())))); break;
				//case DataType::STRUCTURE: return new Structure((void *)(*((void **)(getAddress())))); break;
				default: throw new std::exception("Variable::getValue: invalid type!");
			}
		}

		static Variable * parse(istringstream &is)
		{
			string name;
			is >> name;
			return new Variable(name.c_str());
		}

		virtual string toString() const
		{
			return m_name;
		}
};

#endif	// _RUN_ARGUMENT_H_