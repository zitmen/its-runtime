#ifndef _RUN_MEMORY_MANAGER_H_
#define _RUN_MEMORY_MANAGER_H_

#include <exception>

class MemoryManager
{
	private:
		int stack_size;
		int heap_size;
		void *stack;
		void *heap;

	public:
		void *SB;	// Stack Base
		void *SP;	// Stack Pointer - top of the stack
		void *HB;	// Heap Base
		void *HP;	// Heap Pointer - forst non-allocated place on the heap

		MemoryManager(int stackSize, int heapSize)
		{
			stack = heap = NULL;
			try
			{
				SB = SP = stack = new char[stack_size = stackSize];
				HB = HP = heap  = new char[heap_size = heapSize];
			}
			catch(const std::bad_alloc &e)
			{
				if(stack != NULL) delete [] stack;
				throw new std::exception("MemoryManager: couldn't allocate memory for stack and heap!");
			}
		}

		~MemoryManager()
		{
			if(stack != NULL) delete [] stack;
			if(heap != NULL) delete [] heap;
		}

		bool fits(int bytes)
		{
			return (((((char *)HB) + heap_size) - (((char *)HP) + bytes)) >= 0);
		}

		void * alloc(int bytes)
		{
			if(!fits(bytes)) throw new std::exception("MemoryManager::alloc: allocated object couldn't fit into the memory!");
			void *retval = HP;
			HP = (((char *)HP) + bytes);
			return retval;
		}
};

#endif	// _RUN_MEMORY_MANAGER_H_