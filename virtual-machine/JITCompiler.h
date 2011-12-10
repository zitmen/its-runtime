#ifndef _RUN_JIT_COMPILER_H_
#define _RUN_JIT_COMPILER_H_

class JITCompiler
{
	public:
		// TODO
		/*
		typedef int (*func)(int, int);
		char *compiledCode = new char[32];
		strcpy(compiledCode, "\x55\x8b\xec\xb8????\xff\xd0\x8b\x45\x08\x8b\x55\x0c\x03\xc2\x5d\xc3");
		int addr = (int)(&notify);
		memcpy(compiledCode + 4, &addr, 4);
		func f = (func)compiledCode;
		printf("2+3=%d\n", f(2, 3));
		delete [] compiledCode;
		*/
};

#endif	// _RUN_JIT_COMPILER_H_