#include "Argument.h"
#include "Interpreter.h"

void *
Variable::getAddress() const
{
	int offset = 0;
	char *addr = (char *)((m_base == NULL) ? m_address : m_base->getAddress());
	DataType *type = ((m_base == NULL) ? m_type : m_base->m_type);
	if(m_index.empty()) return ((void *)addr);
	//
	// indexing for arrays and structures
	Variable *var = NULL;
	if(m_index[0]->getType() == DataType::INTEGER)	// Array
	{	// it's always Variable
		//                  size of the data type * offset
		int ith = ((Integer *)((Variable *)m_index[0])->getValue())->getValue();
		Array *arr = new Array(*((Array **)addr));
		if(ith >= arr->getLength()) throw new std::exception("Variable::getAddress: array index out of bounds!");
		offset = (2 * sizeof(int)) + (arr->getItemSize() * ith);
		type = type->subtype;
	}
	else if(m_index[0]->getType() == DataType::STRING)	// Structure
	{	// it's always String
		StructureSignature *ssig = Interpreter::getStructureSignatures()->find(type->name)->second;
		// go through items and all items before the one, that i'm indexing, skip (=add offset for each skipped item)
		for(size_t m = 0, mm = ssig->items_ordering.size(); m < mm; m++)
		{
			if(ssig->items_ordering[m] == ((String *)m_index[0])->getValue())
			{
				type = ssig->items[ssig->items_ordering[m]];
				break;
			}
			offset += DataType::getTypeSize(ssig->items[ssig->items_ordering[m]]->type);
		}
	}
	else
		throw new std::exception("Variable::getAddress: unknown index type!");
	//
	var = new Variable("", type);
	var->setAddress(((char *)(*((void **)addr))) + offset);
	//
	for(size_t i = 1, im = m_index.size(); i < im; i++)
		var->m_index.push_back(m_index[i]);
	//
	addr = (char *)var->getAddress();
	delete var;
	if((addr - offset) == NULL)
		throw new std::exception("Variable::getAddress: Null pointer exception!");
	return ((void *)addr);
}

int
Variable::getItemType() const
{
	return getItemDataType()->type;
}

DataType *
Variable::getItemDataType() const
{
	DataType *type = ((m_base == NULL) ? m_type : m_base->m_type);
	//
	//if((getType() != DataType::ARRAY) && (getType() != DataType::STRUCTURE))	--> not use this condition, because I might want to get reference
	if(m_index.empty()) return type;
	//
	// indexing for arrays and structures
	Variable *var = NULL;
	if(m_index[0]->getType() == DataType::INTEGER)	// Array
	{
		var = new Variable("", type->subtype);
	}
	else if(m_index[0]->getType() == DataType::STRING)	// Structure
	{	// it's always String
		StructureSignature *ssig = Interpreter::getStructureSignatures()->find(type->name)->second;
		var = new Variable("", ssig->items[((String *)m_index[0])->getValue()]);
	}
	//
	for(size_t i = 1, im = m_index.size(); i < im; i++)
		var->m_index.push_back(m_index[i]);
	//
	DataType * dType = var->getItemDataType();
	delete var;
	return dType;
}

int
Variable::getItemTypeSize() const
{
	DataType *type = ((m_base == NULL) ? m_type : m_base->m_type);
	//
	//if((getType() != DataType::ARRAY) && (getType() != DataType::STRUCTURE))	--> not use this condition, because I might want to get reference
	if(m_index.empty()) return type->getSize();
	//
	// indexing for arrays and structures
	Variable *var = NULL;
	if(m_index[0]->getType() == DataType::INTEGER)	// Array
	{
		var = new Variable("", type->subtype);
	}
	else if(m_index[0]->getType() == DataType::STRING)	// Structure
	{	// it's always String
		StructureSignature *ssig = Interpreter::getStructureSignatures()->find(type->name)->second;
		var = new Variable("", ssig->items[((String *)m_index[0])->getValue()]);
	}
	//
	for(size_t i = 1, im = m_index.size(); i < im; i++)
		var->m_index.push_back(m_index[i]);
	//
	int typeSize = var->getItemTypeSize();
	delete var;
	return typeSize;
}