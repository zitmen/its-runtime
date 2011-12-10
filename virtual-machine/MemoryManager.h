#ifndef _RUN_MEMORY_MANAGER_H_
#define _RUN_MEMORY_MANAGER_H_

#include <exception>
#include <cstring>

#include "DataType.h"

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

		bool fitsToHeap(int bytes)
		{
			return (((((char *)HB) + heap_size) - (((char *)HP) + bytes)) >= 0);
		}

		bool fitsToStack(int bytes)
		{
			return (((((char *)SB) + stack_size) - (((char *)SP) + bytes)) >= 0);
		}

		void * alloc(int bytes)
		{
			if(!fitsToHeap(bytes)) throw new std::exception("MemoryManager::alloc: allocated object couldn't fit into the memory - heap overflow!");
			void *retval = HP;
			HP = (((char *)HP) + bytes);
			return retval;
		}

		void * push(const Argument *arg)
		{
			int bytes = DataType::getTypeSize(arg->getType());
			if(!fitsToStack(bytes)) throw new std::exception("MemoryManager::push: pushed variable couldn't fit into the memory - stack overflow!");
			switch(arg->getType())
			{
				case DataType::ARRAY:     SP = ((Array *)arg)->getAddress(); break;
				case DataType::STRUCTURE: SP = ((Structure *)arg)->getAddress(); break;
				case DataType::REFERENCE: SP = ((Reference *)arg)->getValue(); break;
				case DataType::STRING:    SP = (void *)((String *)arg)->getValue(); break;
				case DataType::INTEGER:   (*((int *)SP))    = ((Integer *)arg)->getValue(); break;
				case DataType::DOUBLE:    (*((double *)SP)) = ((Double *)arg)->getValue(); break;
				case DataType::BOOLEAN:   (*((bool *)SP))   = ((Boolean *)arg)->getValue(); break;
				case DataType::FILE:      (*((FILE **)SP))  = ((File *)arg)->getValue(); break;
				default: throw new std::exception("MemoryManager::push: unsupported argument type!");
			}
			void *retval = SP;
			SP = (((char *)SP) + bytes);
			return retval;
		}

		void * push(const Variable *var, bool save_val = true)
		{
			int bytes = DataType::getTypeSize(var->getType());
			if(!fitsToStack(bytes)) throw new std::exception("MemoryManager::push: pushed variable couldn't fit into the memory - stack overflow!");
			if(save_val) memcpy(SP, var->getValue(), bytes);
			void *retval = SP;
			SP = (((char *)SP) + bytes);
			return retval;
		}

		Argument * pop(int data_type)
		{
			SP = (void *)(((char *)SP) - DataType::getTypeSize(data_type));
			if(SP < SB) throw new std::exception("MemoryManager::pop: stack underflow!");
			switch(data_type)
			{
				case DataType::ARRAY:     return new Array(SP);
				case DataType::STRUCTURE: return new Structure(SP);
				case DataType::REFERENCE: return new Reference(SP);
				case DataType::STRING:    return new String(SP);
				case DataType::INTEGER:   return new Integer(*((int *)SP));
				case DataType::DOUBLE:    return new Double(*((double *)SP));
				case DataType::BOOLEAN:   return new Boolean(*((bool *)SP));
				case DataType::FILE:      return new File(*((FILE **)SP));
				default: throw new std::exception("MemoryManager::pop: unsupported argument type!");
			}
		}
};

#endif	// _RUN_MEMORY_MANAGER_H_