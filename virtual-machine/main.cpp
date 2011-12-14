#include <iostream>
#include <exception>
#include <cmath>

#include "ProgramLoader.h"
#include "Interpreter.h"

double dbl()
{
	double x = 5.0, y = 3.0, z;
	z = x / y;
	printf("z=%f\n", z);
	return z;
}
#include "JITCompiler.h"
int main()
{
	try
	{
		JITCompiler jit;
		int ix = 5, iy = 3, iz;
		Variable x1("x", new DataType(DataType::INTEGER)); x1.setAddress(&ix);
		Variable y1("y", new DataType(DataType::INTEGER)); y1.setAddress(&iy);
		Variable z1("z", new DataType(DataType::INTEGER)); z1.setAddress(&iz);
		jit.gen_ldci(&z1, new Integer(15));
		printf("iz=%d\n", iz);
		//
		double dx = 5.0, dy = 2.0, dz;
		Variable x2("x", new DataType(DataType::DOUBLE)); x2.setAddress(&dx);
		Variable y2("y", new DataType(DataType::DOUBLE)); y2.setAddress(&dy);
		Variable z2("z", new DataType(DataType::DOUBLE)); z2.setAddress(&dz);
		jit.gen_ldcr(&z2, new Double(3.1415));
		printf("dz=%f\n", dz);
		//
		bool bx = false, by = true, bz;
		Variable x3("x", new DataType(DataType::BOOLEAN)); x3.setAddress(&bx);
		Variable y3("y", new DataType(DataType::BOOLEAN)); y3.setAddress(&by);
		Variable z3("z", new DataType(DataType::BOOLEAN)); z3.setAddress(&bz);
		jit.gen_ldcb(&z3, new Boolean(false));
		printf("bz=%d\n", bz);
	}
	catch(std::exception *e)
	{
		std::cerr << e->what() << std::endl;
		delete e;
	}
	/*
	try
	{
		ProgramLoader loader("jit.run");
		std::cout << ":: Program ::\n======================\n";
		loader.printProgram(std::cout);
		Interpreter interpreter(loader.getProgram(), loader.getStructures(), loader.getFunctions());
		interpreter.setOption(Interpreter::Options::HeapSize, 32*1024*1024);	// 32MB
		interpreter.setOption(Interpreter::Options::StackSize, 32*1024*1024);	// 32MB
		interpreter.setOption(Interpreter::Options::GarbageCollector, 0.9);	// start GC if 90% of heap is full (set 0% for no GC)
		interpreter.setOption(Interpreter::Options::JITCompiler, -1);	// no JIT
		std::cout << ":: Execution ::\n======================" << std::endl;
		interpreter.run();
	}
	catch(std::exception *e)
	{
		std::cerr << e->what() << std::endl;
		delete e;
	}
	*/
	return 0;
}