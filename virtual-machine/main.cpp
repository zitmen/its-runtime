#include <iostream>
#include <sstream>
#include <exception>
#include <cmath>

#include "ProgramLoader.h"
#include "Interpreter.h"
#include "JITCompiler.h"

int main(int argc, char *argv[])
{
	double stack = 0;
	double heap = 0;
	double gc = 0;
	double jit = 0;
	string source = "";
	//
	std::istringstream is;
	for(int i = 1; i < argc; i++)
	{
		is.clear();
		is.str("");
		string token = argv[i];
		if(token.find("-stack=") == 0)
		{
			is.str(token.substr(7, token.length()-8));
			is >> stack;
			if(token[token.length()-1] == 'k')
				stack *= 1024.0;
			else if(token[token.length()-1] == 'M')
				stack *= 1024.0 * 1024.0;
		}
		else if(token.find("-heap=") == 0)
		{
			is.str(token.substr(6, token.length()-7));
			is >> heap;
			if(token[token.length()-1] == 'k')
				heap *= 1024.0;
			if(token[token.length()-1] == 'M')
				heap *= 1024.0 * 1024.0;
		}
		else if(token.find("-gc=") == 0)
		{
			is.str(token.substr(4, token.length()-4));
			is >> gc;
		}
		else if(token.find("-jit=") == 0)
		{
			is.str(token.substr(5, token.length()-5));
			is >> jit;
		}
		else
		{
			source = token;
		}
	}
	try
	{
		ProgramLoader loader(source);
		//std::cout << ":: Program ::\n======================\n";
		//loader.printProgram(std::cout);
		Interpreter interpreter(loader.getProgram(), loader.getStructures(), loader.getFunctions());
		if(heap > 0) interpreter.setOption(Interpreter::Options::HeapSize, heap);
		if(stack > 0) interpreter.setOption(Interpreter::Options::StackSize, stack);
		if(gc > 0) interpreter.setOption(Interpreter::Options::GarbageCollector, gc);
		if(jit > 0) interpreter.setOption(Interpreter::Options::JITCompiler, jit);
		//std::cout << ":: Execution ::\n======================" << std::endl;
		interpreter.run();
	}
	catch(std::exception *e)
	{
		std::cerr << e->what() << std::endl;
		delete e;
	}
	return 0;
}