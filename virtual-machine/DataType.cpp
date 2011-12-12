#include "DataType.h"
#include "Interpreter.h"

int
DataType::getSize() const
{
	if(type == STRUCTURE)
	{
		int size = 0;
		StructureSignature *ssig = Interpreter::getStructureSignatures()->find(name)->second;
		for(map<string, DataType *>::iterator it = ssig->items.begin(); it != ssig->items.end(); ++it)
			size += getTypeSize(it->second->type);
		return size;
	}
	else if(subtype != NULL)	// Array
		return getTypeSize(subtype->type);
	else	// primitive types
		return getTypeSize(type);
}