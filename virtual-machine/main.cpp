#include <iostream>
#include <exception>
#include <cmath>

#include "ProgramLoader.h"
#include "Interpreter.h"

int main()
{
	try
	{
		ProgramLoader loader("jit.run");
		//loader.printProgram(std::cout);
		Interpreter interpreter(loader.getProgram(), loader.getStructures(), loader.getFunctions());
		interpreter.setOption(Interpreter::Options::HeapSize, 1024*1024);	// 1MB
		interpreter.setOption(Interpreter::Options::StackSize, 1024*1024);	// 1MB
		interpreter.setOption(Interpreter::Options::GarbageCollector, 0);	// no GC
		interpreter.setOption(Interpreter::Options::JITCompiler, -1);	// no JIT
		interpreter.run();
	}
	catch(std::exception *e)
	{
		std::cerr << e->what() << std::endl;
		delete e;
	}
	return 0;
}