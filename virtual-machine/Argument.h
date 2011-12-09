#ifndef _RUN_ARGUMENT_H_
#define _RUN_ARGUMENT_H_

#include <sstream>
#include <ostream>
#include <string>
using std::istringstream;
using std::ostringstream;
using std::string;
using std::ostream;

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

ostringstream Integer::os;

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

ostringstream Double::os;

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
		string m_value;

	public:
		String(string &val)
		{
			m_value = val;
		}

		string getValue() const
		{
			return m_value;
		}

		static Argument * parse(istringstream &is)
		{
			string val;
			is >> val;
			return new String(val.substr(1, val.length() - 2));
		}

		virtual string toString() const
		{
			return ('"' + m_value + '"');
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

class Variable : public Argument
{
	private:
		string m_name;
		void *m_address;

	public:
		Variable(string &name)
		{
			m_name = name;
		}

		void * getAddress() const
		{
			return m_address;
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