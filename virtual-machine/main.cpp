#include <iostream>
#include <exception>
#include <cmath>

#include "ProgramLoader.h"
#include "Interpreter.h"
#include "JITCompiler.h"

int main()
{
	// TODO: command line arguments!
	try
	{
		ProgramLoader loader("jit.run");
		std::cout << ":: Program ::\n======================\n";
		loader.printProgram(std::cout);
		Interpreter interpreter(loader.getProgram(), loader.getStructures(), loader.getFunctions());
		interpreter.setOption(Interpreter::Options::HeapSize, 32*1024*1024);	// 32MB
		interpreter.setOption(Interpreter::Options::StackSize, 32*1024*1024);	// 32MB
		interpreter.setOption(Interpreter::Options::GarbageCollector, 0.9);	// start GC if 90% of heap is full (set 0% for no GC)
		interpreter.setOption(Interpreter::Options::JITCompiler, 2);	// compile any function that has been executed at least 10 times (set non-positive value for no JIT Compiler)
		std::cout << ":: Execution ::\n======================" << std::endl;
		interpreter.run();
	}
	catch(std::exception *e)
	{
		std::cerr << e->what() << std::endl;
		delete e;
	}
	return 0;
}