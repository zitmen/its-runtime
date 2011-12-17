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
	public:
		static bool m_first_rand;
		static time_t m_rand_seed;

		static map<string, DataType *> routinesList;	// first is routine name, second is returned datatype

		static void init()
		{
			routinesList["cloneArray"] = new DataType(DataType::ARRAY);
			routinesList["clearArray"] = new DataType(DataType::VOID);
			routinesList["length"] = new DataType(DataType::INTEGER);
			routinesList["openRFile"] = new DataType(DataType::FILE);
			routinesList["openWFile"] = new DataType(DataType::FILE);
			routinesList["closeFile"] = new DataType(DataType::VOID);
			routinesList["flushFile"] = new DataType(DataType::VOID);
			routinesList["printFile"] = new DataType(DataType::VOID);
			routinesList["printlnFile"] = new DataType(DataType::VOID);
			routinesList["print"] = new DataType(DataType::VOID);
			routinesList["println"] = new DataType(DataType::VOID);
			routinesList["inputFile"] = new DataType(DataType::STRING);
			routinesList["input"] = new DataType(DataType::STRING);
			routinesList["eof"] = new DataType(DataType::VOID);
			routinesList["eoi"] = new DataType(DataType::VOID);
			routinesList["pow"] = new DataType(DataType::DOUBLE);
			routinesList["sqrt"] = new DataType(DataType::DOUBLE);
			routinesList["log"] = new DataType(DataType::DOUBLE);
			routinesList["rand"] = new DataType(DataType::INTEGER);
			routinesList["indexOf"] = new DataType(DataType::INTEGER);
			routinesList["lastIndexOf"] = new DataType(DataType::INTEGER);
			routinesList["substring"] = new DataType(DataType::STRING);
			routinesList["toLower"] = new DataType(DataType::STRING);
			routinesList["toUpper"] = new DataType(DataType::STRING);
			routinesList["trim"] = new DataType(DataType::STRING);
			routinesList["startsWith"] = new DataType(DataType::BOOLEAN);
			routinesList["endsWith"] = new DataType(DataType::BOOLEAN);
			routinesList["concat"] = new DataType(DataType::STRING);
			routinesList["strlen"] = new DataType(DataType::INTEGER);
			routinesList["int2str"] = new DataType(DataType::STRING);
			routinesList["double2str"] = new DataType(DataType::STRING);
			routinesList["str2int"] = new DataType(DataType::INTEGER);
			routinesList["double2int"] = new DataType(DataType::INTEGER);
			routinesList["str2double"] = new DataType(DataType::DOUBLE);
			routinesList["int2double"] = new DataType(DataType::DOUBLE);
		}

		static Array * cloneArray(MemoryManager *mem, const Array *arr)
		{
			Array *new_arr = new Array(mem->alloc(arr->getAllocSize()));
			memcpy(new_arr->getAddress(), arr->getAddress(), arr->getAllocSize());
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
    
		static String * inputFile(MemoryManager *mem, const File *fp)
		{
			char *tmp = new char[255];
			fscanf(fp->getValue(), "%s", tmp);
			int len = ::strlen(tmp);
			char *str = (char *)mem->alloc(sizeof(char) * (len + 1));
			strcpy(str, tmp);
			delete [] tmp;
			return new String(str);
		}
    
		static String * input(MemoryManager *mem)
		{
			char *tmp = new char[255];
			scanf("%s", tmp);
			int len = ::strlen(tmp);
			char *str = (char *)mem->alloc(sizeof(char) * (len + 1));
			strcpy(str, tmp);
			delete [] tmp;
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
        
        static String * substring(MemoryManager *mem, const String *str, const Integer *start, const Integer *end)
        {
			char *tmp = new char[strlen(str)->getValue()+1];
			strcpy(tmp, string(str->getValue()).substr(start->getValue(), end->getValue() - start->getValue() + 1).c_str());
			int len = ::strlen(tmp);
			char *sstr = (char *)mem->alloc(sizeof(char) * (len + 1));
			strcpy(sstr, tmp);
			delete [] tmp;
			return new String(sstr);
        }
        
        static String * toLower(MemoryManager *mem, const String *str)
        {
			const char *cstr = str->getValue();
			int len = ::strlen(cstr);
			char *sstr = (char *)mem->alloc(sizeof(char) * (len + 1));
			for(int i = 0; i < len; i++)
				sstr[i] = tolower(cstr[i]);
			sstr[len] = '\0';
            return new String(sstr);
        }
        
        static String * toUpper(MemoryManager *mem, const String *str)
        {
            const char *cstr = str->getValue();
			int len = ::strlen(cstr);
			char *sstr = (char *)mem->alloc(sizeof(char) * (len + 1));
			for(int i = 0; i < len; i++)
				sstr[i] = toupper(cstr[i]);
			sstr[len] = '\0';
            return new String(sstr);
        }
        
        static String * trim(MemoryManager *mem, const String *str)
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
			return substring(mem, str, new Integer(start), new Integer(end));
        }
        
        static Boolean * startsWith(const String *str, const String *prefix)
        {
            return new Boolean(indexOf(str, prefix)->getValue() == 0);
        }
        
        static Boolean * endsWith(const String *str, const String *suffix)
        {
			return new Boolean(lastIndexOf(str, suffix)->getValue() == (strlen(str)->getValue() - strlen(suffix)->getValue()));
        }
        
        static String * concat(MemoryManager *mem, const String *str1, const String *str2)
        {
			int len1 = ::strlen(str1->getValue()), len2 = ::strlen(str2->getValue());
			char *str = (char *)mem->alloc(sizeof(char) * (len1 + len2 + 1));
			strcpy(str, str1->getValue());
			strcat(str, str2->getValue());
            return new String(str);
        }
        
        static Integer * strlen(const String *str)
        {
			return new Integer(::strlen(str->getValue()));
        }

		static String * int2str(MemoryManager *mem, const Integer *i)
		{
			char *tmp = new char[32];
			sprintf(tmp, "%d", i->getValue());
			char *str = (char *)mem->alloc(sizeof(char) * (::strlen(tmp) + 1));
			strcpy(str, tmp);
			delete [] tmp;
			return new String(str);
		}
    
		static String * double2str(MemoryManager *mem, const Double *d)
		{
			char *tmp = new char[32];
			sprintf(tmp, "%lf", d->getValue());
			char *str = (char *)mem->alloc(sizeof(char) * (::strlen(tmp) + 1));
			strcpy(str, tmp);
			delete [] tmp;
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