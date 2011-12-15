#include <iostream>
#include <exception>
#include <cmath>

#include "ProgramLoader.h"
#include "Interpreter.h"
#include "JITCompiler.h"

int main()
{
	try
	{
		JITCompiler jit;
		int ix = 255, iy = 3, iz;
		Variable x1("x", new DataType(DataType::INTEGER)); x1.setAddress(&ix);
		Variable y1("y", new DataType(DataType::INTEGER)); y1.setAddress(&iy);
		Variable z1("z", new DataType(DataType::INTEGER)); z1.setAddress(&iz);
		//
		double dx = 4.0, dy = 4.0, dz;
		Variable x2("x", new DataType(DataType::DOUBLE)); x2.setAddress(&dx);
		Variable y2("y", new DataType(DataType::DOUBLE)); y2.setAddress(&dy);
		Variable z2("z", new DataType(DataType::DOUBLE)); z2.setAddress(&dz);
		//
		bool bx = false, by = true, bz;
		Variable x3("x", new DataType(DataType::BOOLEAN)); x3.setAddress(&bx);
		Variable y3("y", new DataType(DataType::BOOLEAN)); y3.setAddress(&by);
		Variable z3("z", new DataType(DataType::BOOLEAN)); z3.setAddress(&bz);
		//
		char *str;
		Variable z4("str", new DataType(DataType::STRING)); z4.setAddress(&str);
		//
		Interpreter interpreter(NULL, NULL, NULL);
		interpreter.init();
		//
		typedef void (*compiled_program)(void);
		char *compiled = new char[4096];	// 4kB
		int length = 0;
		length += jit.gen_prolog(compiled+length);
		//length += jit.gen_st(compiled+length, &z1, &x1);
		//length += jit.gen_st(compiled+length, &z2, &x2);
		//length += jit.gen_st(compiled+length, &z3, &x3);
		//length += jit.gen_neq(compiled+length, &x3, &x1, &y1);
		//length += jit.gen_neq(compiled+length, &y3, &x2, &y2);
		//length += jit.gen_neq(compiled+length, &z3, &x3, &y3);
		//length += jit.gen_inc(compiled+length, &x1);
		//length += jit.gen_dec(compiled+length, &y1);
		//length += jit.gen_xor(compiled+length, &z3, &x3, &y3);
		//length += jit.gen_xor(compiled+length, &z3, &x3, &y3);
		//length += jit.gen_ldzf_alu(compiled+length, &x3);
		length += jit.gen_sub(compiled+length, &z2, &x2, &y2);
		length += jit.gen_ldzf_fpu(compiled+length, &y3);
		length += jit.gen_epilog(compiled+length);
		//
		compiled_program exec = (compiled_program)compiled;
		exec();
		//printf("iz=%d\n", iz);
		//printf("ix=%d\n", ix);
		//printf("iy=%d\n", iy);
		//printf("dz=%f\n", dz);
		//printf("bx=%d\n", bx);
		printf("by=%d\n", by);
		//printf("bz=%d\n", bz);
		//printf("str=%s\n", str);
		//
		delete [] compiled;
		//
/*
		Interpreter interpreter(NULL, NULL, NULL);
		interpreter.init();
		//
		char *str;
		Variable z4("z", new DataType(DataType::STRING)); z4.setAddress(&str);
		jit.gen_ldcs(&z4, new String("Ahoj, tady Pepa!"));
		printf("str=%s\n", str);
*/
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