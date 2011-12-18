#ifndef _RUN_GARBAGE_COLLECTOR_H_
#define _RUN_GARBAGE_COLLECTOR_H_

#include <list>
#include <map>
using std::list;
using std::map;

class DataType;

/* Moving Mark-and-Sweep Garbage Collector */
class GarbageCollector
{
	private:
		list<void **> references;
		map<void **, DataType *> ref_types;
		map<void **, void **> interchange;

		void _discover(void **ref);

	protected:
		void discoverReferences()
		{
			for(list<void **>::iterator it = references.begin(); it != references.end(); ++it)
				_discover(*it);
		}

	public:
		void addReference(void **ref, DataType *type)
		{
			references.push_back(ref);
			ref_types[ref] = type;
		}

		void cleanUp(void **heap_ptr, void **HB, void **HP, int heap_size)
		{
			printf("Garbage Collector: cleaning the heap.\n");
			//
			interchange.clear();
			//
			char *heap_old = (*((char **)heap_ptr));
			char *heap_new = new char[heap_size];
			char *_HP = heap_new;
			// go through references and copy referenced blocks into the new heap
			char *ref;
			int block_size;
			for(list<void **>::iterator it = references.begin(); it != references.end(); ++it)
			{
				if(interchange.find((void **)**it) != interchange.end())	// if the referenced block was already moved, update reference only
				{
					(*((void **)(*it))) = interchange[(void **)**it];
					continue;
				}
				//
				ref = (char *)(*((void **)(*it)));
				if((ref < heap_old) || (ref > (heap_old + heap_size))) continue;
				ref = ref - sizeof(int);	// there is saved block_size
				block_size = (*((int *)ref));
				memcpy(_HP, ref, block_size);
				interchange[(void **)**it] = (void **)(_HP + sizeof(int));	// save for updating other references
				(*((void **)(*it))) = interchange[(void **)**it];
				_HP = _HP + block_size;
				//
				_discover((void **)(*it));	// update references saved on the heap! (circle references and hidden references)
			}
			// return new heap and destroy the old one
			(*HB) = (*heap_ptr) = (void *)heap_new;
			(*HP) = (void *)_HP;
			delete [] heap_old;
		}
};

#endif	// _RUN_GARBAGE_COLLECTOR_H_