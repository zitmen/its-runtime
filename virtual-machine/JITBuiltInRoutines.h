#ifndef _RUN_JIT_BUILT_IN_ROUTINES_H_
#define _RUN_JIT_BUILT_IN_ROUTINES_H_

#include <cstdio>
#include <cmath>
#include <cstring>
#include <cstdlib>
#include <ctime>

#include "MemoryManager.h"
#include "BuiltInRoutines.h"

class JITBuiltInRoutines
{
	public:
		static void * cloneArray(MemoryManager *mem, const void *arr)
		{
			int alloc_size = ((2 * sizeof(int)) + (DataType::getTypeSize((*(((int *)arr)+1))) * (*((int *)arr))));
			void *new_arr = mem->alloc(alloc_size);
			memcpy(new_arr, arr, alloc_size);
			return new_arr;
		}
    
		static void clearArray(void *arr)
		{
			int arr_size = (DataType::getTypeSize((*(((int *)arr)+1))) * (*((int *)arr)));
			memset(((char *)arr)+(2*sizeof(int)), 0, arr_size);
		}
    
		static int length(const void *arr)
		{
			return (*((int *)arr));
		}

		static FILE * openRFile(const char *fname)
		{
			return fopen(fname, "r");
		}
    
		static FILE * openWFile(const char *fname, bool append)
		{
			return fopen(fname, (append ? "a" : "w"));
		}
    
		static void closeFile(FILE *fp)
		{
			fclose(fp);
		}
    
		static void flushFile(FILE *fp)
		{
			fflush(fp);
		}
    
		static void printFile(FILE *fp, const char *str)
		{
			fputs(str, fp);
		}
    
		static void printlnFile(FILE *fp, const char *str)
		{
			fputs(str, fp);
			fputc('\n', fp);
		}

		static void print(const char *str)
		{
			puts(str);
		}
    
		static void println(const char *str)
		{
			puts(str);
			putc('\n', stdout);
		}
    
		static char * inputFile(MemoryManager *mem, FILE *fp)
		{
			char *tmp = new char[255];
			fscanf(fp, "%s", tmp);
			int len = ::strlen(tmp);
			char *str = (char *)mem->alloc(sizeof(char) * (len + 1));
			strcpy(str, tmp);
			delete [] tmp;
			return str;
		}
    
		static char * input(MemoryManager *mem)
		{
			char *tmp = new char[255];
			scanf("%s", tmp);
			int len = ::strlen(tmp);
			char *str = (char *)mem->alloc(sizeof(char) * (len + 1));
			strcpy(str, tmp);
			delete [] tmp;
			return str;
		}
    
		static bool eof(FILE *fp)
		{
			return (feof(fp) != 0);
		}
    
		static bool eoi()
		{
			return (feof(stdin) != 0);
		}

		static double pow(double x, double n)
		{
			return ::pow(x, n);
		}
    
		static double sqrt(double x)
		{
			return ::sqrt(x);
		}
    
		static double log(double x)
		{
			return ::log(x);
		}
    
		static int rand(int modul)
		{
			if(BuiltInRoutines::m_first_rand)
			{
				srand(BuiltInRoutines::m_rand_seed);
				BuiltInRoutines::m_first_rand = false;
			}
			return (::rand() % modul);
		}

		static int indexOf(const char *haystack, const char *needle)
        {
            return string(haystack).find(needle);
        }
        
        static int lastIndexOf(const char *haystack, const char *needle)
        {
			return string(haystack).rfind(needle);
        }

        static char * substring(MemoryManager *mem, const char *str, int start, int end)
        {
			char *tmp = new char[::strlen(str)+1];
			strcpy(tmp, string(str).substr(start, end - start + 1).c_str());
			int len = ::strlen(tmp);
			char *sstr = (char *)mem->alloc(sizeof(char) * (len + 1));
			strcpy(sstr, tmp);
			delete [] tmp;
			return sstr;
        }
        
        static char * toLower(MemoryManager *mem, const char *str)
        {
			int len = ::strlen(str);
			char *sstr = (char *)mem->alloc(sizeof(char) * (len + 1));
			for(int i = 0; i < len; i++)
				sstr[i] = tolower(str[i]);
			sstr[len] = '\0';
            return sstr;
        }
        
        static char * toUpper(MemoryManager *mem, const char *str)
        {
			int len = ::strlen(str);
			char *sstr = (char *)mem->alloc(sizeof(char) * (len + 1));
			for(int i = 0; i < len; i++)
				sstr[i] = toupper(str[i]);
			sstr[len] = '\0';
            return sstr;
        }
        
        static char * trim(MemoryManager *mem, const char *str)
        {
			int len = ::strlen(str);
			int start, end;
			for(int i = 0; i < len; i++)
			{
				if(!isspace(str[i]))
				{
					start = i;
					break;
				}
			}
			for(int i = len-1; i >= 0; i--)
			{
				if(!isspace(str[i]))
				{
					end = i;
					break;
				}
			}
			return substring(mem, str, start, end);
        }
        
        static bool startsWith(const char *str, const char *prefix)
        {
            return (indexOf(str, prefix) == 0);
        }
        
        static bool endsWith(const char *str, const char *suffix)
        {
			return (lastIndexOf(str, suffix) == (::strlen(str) - ::strlen(suffix)));
        }
        
        static char * concat(MemoryManager *mem, const char *str1, const char *str2)
        {
			int len1 = ::strlen(str1), len2 = ::strlen(str2);
			char *str = (char *)mem->alloc(sizeof(char) * (len1 + len2 + 1));
			strcpy(str, str1);
			strcat(str, str2);
            return str;
        }
        
        static int strlen(const char *str)
        {
			return ::strlen(str);
        }

		static char * int2str(MemoryManager *mem, int i)
		{
			char *tmp = new char[32];
			sprintf(tmp, "%d", i);
			char *str = (char *)mem->alloc(sizeof(char) * (::strlen(tmp) + 1));
			strcpy(str, tmp);
			delete [] tmp;
			return str;
		}
    
		static char * double2str(MemoryManager *mem, double d)
		{
			char *tmp = new char[32];
			sprintf(tmp, "%lf", d);
			char *str = (char *)mem->alloc(sizeof(char) * (::strlen(tmp) + 1));
			strcpy(str, tmp);
			delete [] tmp;
			return str;
		}
    
		static int str2int(const char *s)
		{
			int i;
			sscanf(s, "%d", &i);
			return i;
		}
    
		static int double2int(double d)
		{
			return int(d);
		}
    
		static double str2double(const char *s)
		{
			double d;
			sscanf(s, "%lf", &d);
			return d;
		}
    
		static double int2double(int i)
		{
			return double(i);
		}
};

#endif	// _RUN_JIT_BUILT_IN_ROUTINES_H_