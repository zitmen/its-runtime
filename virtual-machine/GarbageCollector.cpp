#include "GarbageCollector.h"

#include "Interpreter.h"
#include "DataType.h"

void
GarbageCollector::_discover(void **ref)
{
	char *cref;
	cref = (char *)(*((char **)ref));
	if(ref_types[ref]->type == DataType::ARRAY)
	{
		int subtype = ref_types[ref]->subtype->type;
		if((subtype == DataType::ARRAY) || (subtype == DataType::STRING) ||
			(subtype == DataType::STRUCTURE) || (subtype == DataType::REFERENCE))	// is subtype reference?
		{
			int length = (*((int *)cref));
			int item_size = DataType::getTypeSize(*((int *)(cref + sizeof(int))));
			cref = cref + (2 * sizeof(int));
			for(int i = 0; i < length; i++, cref += item_size)
			{
				references.push_back((void **)cref);
				ref_types[(void **)cref] = ref_types[ref]->subtype;
			}
		}
	}
	else if(ref_types[ref]->type == DataType::STRUCTURE)
	{
		StructureSignature *ssig = Interpreter::getStructureSignatures()->find(ref_types[ref]->name)->second;
		// go through items and all items before the one, that i'm indexing, skip (=add offset for each skipped item)
		int type;
		for(size_t m = 0, mm = ssig->items_ordering.size(); m < mm; m++)
		{
			type = ssig->items[ssig->items_ordering[m]]->type;
			if((type == DataType::ARRAY) || (type == DataType::STRING) ||
				(type == DataType::STRUCTURE) || (type == DataType::REFERENCE))	// is item's type reference?
			{
				references.push_back((void **)cref);
				ref_types[(void **)cref] = ssig->items[ssig->items_ordering[m]];
			}
			cref += DataType::getTypeSize(type);
		}
	}
}