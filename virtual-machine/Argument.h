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

		double getValue() const
		{
			return m_value;
		}

		static Argument * parse(istringstream &is)
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

		bool getValue() const
		{
			return m_value;
		}

		static Argument * parse(istringstream &is)
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

		String(char *str)
		{
			m_address = str;
		}

		const char * getValue() const
		{
			return m_address;
		}

		static Argument * parse(istringstream &is)	// TODO: alokace na halde programu??!!
		{
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

		/*
		??? getValue() const
		{
			//
		}
		*/

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
		int getType() const
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

class Null : public Argument
{
	private:
		void *m_value;

	public:
		Null()
		{
			m_value = NULL;
		}

		void * getValue() const
		{
			return m_value;
		}

		static Argument * parse(istringstream &is)
		{
			string val;
			is >> val;
			return new Null();
		}

		virtual string toString() const
		{
			return "NULL";
		}
};

class File : public Argument
{
	private:
		static ostringstream os;
		FILE *m_value;

	public:
		File(FILE *fp)
		{
			m_value = fp;
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
		void *m_address;

	public:
		Variable(const char *name)
		{
			m_name = name;
		}

		Variable(string &name)
		{
			m_name = name;
		}

		void * getAddress() const
		{
			return m_address;
		}

		string getName() const
		{
			return m_name;
		}

		string getValue() const
		{
			return m_name;
		}

		static Argument * parse(istringstream &is)
		{
			string name;
			is >> name;
			return new Variable(name);
		}

		virtual string toString() const
		{
			return m_name;
		}
};

#endif	// _RUN_ARGUMENT_H_