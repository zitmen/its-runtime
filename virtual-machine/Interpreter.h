#ifndef _RUN_INTERPRETER_H_
#define _RUN_INTERPRETER_H_

class Interpreter
{
	public:
		class Options { public: static enum { HeapSize, StackSize, GarbageCollector, JITCompiler }; };

	private:
		vector<Instruction *> *program;
		void *stack;
		void *heap;
		int IC;

	public:
		Interpreter(vector<Instruction *> *program)
		{
			this->program = program;
			IC = 0;
			stack = NULL;
			heap = NULL;
		}

		void setOption(int option, double val)
		{
			//
		}

		void run()
		{
			//
		}
};

#endif	// _RUN_INTERPRETER_H_