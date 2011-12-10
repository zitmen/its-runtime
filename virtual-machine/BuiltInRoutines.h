#ifndef _RUN_BUILT_IN_ROUTINES_H_
#define _RUN_BUILT_IN_ROUTINES_H_

#include <cstdio>
#include <cmath>
#include <cstring>
#include <cstdlib>
#include <ctime>

#include "Argument.h"
#include "MemoryManager.h"

// All these methods are returning some value, but it's need to be stored on the stack in _invoke() method.
// It's not done here, because some methods are calling each other and it would be stupid to push and pop those values.
// + it would not work, because functions don't pop arguments from the stack
class BuiltInRoutines
{
	private:
		static bool m_first_rand;
		static time_t m_rand_seed;

	public:
		static Array * cloneArray(MemoryManager *mem, const Array *arr)
		{
			Array *new_arr = new Array(mem->alloc(arr->getAllocSize()));
			memcpy(new_arr->getAddress(), arr->getAddress(), new_arr->getAllocSize());
			return new_arr;
		}
    
		static void clearArray(Array *arr)
		{
			memset(arr->getElementAt(0), 0, arr->getLength()*arr->getItemSize());
		}
    
		static Integer * length(const Array *arr)
		{
			return new Integer(arr->getLength());
		}

		static File * openRFile(const String *fname)
		{
			return new File(fopen(fname->getValue(), "r"));
		}
    
		static File * openWFile(const String *fname, const Boolean *append)
		{
			return new File(fopen(fname->getValue(), (append->getValue() ? "a" : "w")));
		}
    
		static void closeFile(const File *fp)
		{
			fclose(fp->getValue());
		}
    
		static void flushFile(const File *fp)
		{
			fflush(fp->getValue());
		}
    
		static void printFile(const File *fp, const String *str)
		{
			fprintf(fp->getValue(), "%s", str->getValue());
		}
    
		static void printlnFile(const File *fp, const String *str)
		{
			fprintf(fp->getValue(), "%s\n", str->getValue());
		}

		static void print(const String *str)
		{
			printf("%s", str->getValue());
		}
    
		static void println(const String *str)
		{
			printf("%s\n", str->getValue());
		}
    
		static String * inputFile(const File *fp)
		{
			char *str = new char[255];
			fscanf(fp->getValue(), "%s", str);
			return new String(str);
		}
    
		static String * input()
		{
			char *str = new char[255];
			scanf("%s", str);
			return new String(str);
		}
    
		static Boolean * eof(const File *fp)
		{
			return new Boolean(feof(fp->getValue()) != 0);
		}
    
		static Boolean * eoi()
		{
			return new Boolean(feof(stdin) != 0);
		}

		static Double * pow(const Double *x, const Double *n)
		{
			return new Double(::pow(x->getValue(), n->getValue()));
		}
    
		static Double * sqrt(const Double *x)
		{
			return new Double(::sqrt(x->getValue()));
		}
    
		static Double * log(const Double *x)
		{
			return new Double(::log(x->getValue()));
		}
    
		static Integer * rand(const Integer *modul)
		{
			if(m_first_rand)
			{
				srand(m_rand_seed);
				m_first_rand = false;
			}
			return new Integer(::rand() % modul->getValue());
		}

		static Integer * indexOf(const String *haystack, const String *needle)
        {
            return new Integer(string(haystack->getValue()).find(needle->getValue()));
        }
        
        static Integer * lastIndexOf(const String *haystack, const String *needle)
        {
			return new Integer(string(haystack->getValue()).rfind(needle->getValue()));
        }
        
        static String * substring(const String *str, const Integer *start, const Integer *end)
        {
			char *sstr = new char[strlen(str)->getValue()+1];
			return new String(strcpy(sstr, string(str->getValue()).substr(start->getValue(), end->getValue() - start->getValue() + 1).c_str()));
        }
        
        static String * toLower(const String *str)
        {
			const char *cstr = str->getValue();
			char *sstr = new char[strlen(str)->getValue()+1];
			int len = ::strlen(cstr);
			for(int i = 0; i < len; i++)
				sstr[i] = tolower(cstr[i]);
			sstr[len] = '\0';
            return new String(sstr);
        }
        
        static String * toUpper(const String *str)
        {
            const char *cstr = str->getValue();
			char *sstr = new char[strlen(str)->getValue()+1];
			int len = ::strlen(cstr);
			for(int i = 0; i < len; i++)
				sstr[i] = toupper(cstr[i]);
			sstr[len] = '\0';
            return new String(sstr);
        }
        
        static String * trim(const String *str)
        {
			const char *cstr = str->getValue();
			int len = ::strlen(cstr);
			int start, end;
			for(int i = 0; i < len; i++)
			{
				if(!isspace(cstr[i]))
				{
					start = i;
					break;
				}
			}
			for(int i = len-1; i >= 0; i--)
			{
				if(!isspace(cstr[i]))
				{
					end = i;
					break;
				}
			}
			return substring(str, new Integer(start), new Integer(end));
        }
        
        static Boolean * startsWith(const String *str, const String *prefix)
        {
            return new Boolean(indexOf(str, prefix) == 0);
        }
        
        static Boolean * endsWith(const String *str, const String *suffix)
        {
			return new Boolean(lastIndexOf(str, suffix)->getValue() == (strlen(str)->getValue() - strlen(suffix)->getValue()));
        }
        
        static String * concat(const String *str1, const String *str2)
        {
			char *str = new char[strlen(str1)->getValue()+strlen(str2)->getValue()+1];
			strcpy(str, str1->getValue());
			strcat(str, str2->getValue());
            return new String(str);
        }
        
        static Integer * strlen(const String *str)
        {
			return new Integer(::strlen(str->getValue()));
        }

		static String * int2str(const Integer *i)
		{
			char *str = new char[32];
			sprintf(str, "%d", i->getValue());
			return new String(str);
		}
    
		static String * double2str(const Double *d)
		{
			char *str = new char[32];
			sprintf(str, "%f", d->getValue());
			return new String(str);
		}
    
		static Integer * str2int(const String *s)
		{
			int i;
			sscanf(s->getValue(), "%d", &i);
			return new Integer(i);
		}
    
		static Integer * double2int(const Double *d)
		{
			return new Integer(d->getValue());
		}
    
		static Double * str2double(const String *s)
		{
			double d;
			sscanf(s->getValue(), "%lf", &d);
			return new Double(d);
		}
    
		static Double *int2double(const Integer *i)
		{
			return new Double(i->getValue());
		}
};

#endif	// _RUN_BUILT_IN_ROUTINES_H_