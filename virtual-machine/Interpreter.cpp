#include "Interpreter.h"

void Interpreter::_call(FunctionSignature *fn, const vector<Argument *> &args)
{
	// 0. push signature to call stack
	call_stack.push(fn);
	// 1. push IP
	memory->push(new Integer(IP + 1));
	// 2. set new IP
	IP = fn->pointer;
	// 3. push SFB
	memory->push(new Reference(memory->SFB));
	// 4. set new SFB
	memory->SFB = memory->SP;
	// 5. get values on the stack -- arguments of the function
	map<string, Argument *> values;
	for(size_t i = 1, im = args.size(); i < im; i++)
		values[((Variable *)args[i])->getName()] = ((Variable *)args[i])->getValue();
	// 6. setup the stack - 1st come parameters, 2nd declared variables; set SP
    for(map<string, Variable *>::iterator it = fn->variables.begin(); it != fn->variables.end(); ++it)
		it->second->setAddress(memory->push(it->second));
	// 7. set values on the stack -- arguments of the function (non-recursive)
    for(size_t i = 1, im = args.size(); i < im; i++)
        fn->variables[fn->arguments_ordering[i-1]]->setValue((Variable *)(args[i]));
	// 8. set values on the stack -- arguments of the function (recursive -- variable names are overlapping)
	map<string, Argument *>::iterator val_it;
	for(map<string, Variable *>::iterator it = fn->variables.begin(); it != fn->variables.end(); ++it)
		if((val_it = values.find(it->first)) != values.end())
			it->second->setValue(val_it->second);
}

void Interpreter::_ret()
{
	// 0. pop signature from call stack
	FunctionSignature *fn = call_stack.top(); call_stack.pop();
	// 1. restore SP to the state before call -- top was at SFB (there is still old SFB and IP - next steps)
	memory->SP = memory->SFB;
	// 2. restore SFB of previous stack fram
	memory->SFB = (*((void **)(memory->popAndGetTopValAddr(DataType::REFERENCE))));
	// 3. restore IP
	IP = (*((int *)(memory->popAndGetTopValAddr(DataType::INTEGER))));
	// 4. restore local variables
	int offset = 0;
	map<string, Variable *>::iterator it = fn->variables.end();
	do	// must be done this way, because reverse_iterator didn't work :(
	{
		--it;
		offset += it->second->getItemTypeSize();
		it->second->setAddress((void *)((char *)(memory->SP) - offset));
	} while(it != fn->variables.begin());
}

void Interpreter::_retv(const Variable *var)
{
	Argument *retval = var->getValue();
	_ret();
	memory->push(retval);
}

void Interpreter::_invoke(Variable *name, const vector<Argument *> &args)
{
	if(name->getName() == "cloneArray")
	{
		memory->push(BuiltInRoutines::cloneArray(memory, (Array *)(((Variable *)args[1])->getValue())));
	}
	else if(name->getName() == "clearArray")
	{
		BuiltInRoutines::clearArray((Array *)(((Variable *)args[1])->getValue()));
	}
	else if(name->getName() == "length")
	{
		memory->push(BuiltInRoutines::length((Array *)(((Variable *)args[1])->getValue())));
	}
	else if(name->getName() == "openRFile")
	{
		memory->push(BuiltInRoutines::openRFile((String *)(((Variable *)args[1])->getValue())));
	}
	else if(name->getName() == "openWFile")
	{
		memory->push(BuiltInRoutines::openWFile((String *)(((Variable *)args[1])->getValue()), (Boolean *)(((Variable *)args[2])->getValue())));
	}
	else if(name->getName() == "closeFile")
	{
		BuiltInRoutines::closeFile((File *)(((Variable *)args[1])->getValue()));
	}
	else if(name->getName() == "flushFile")
	{
		BuiltInRoutines::flushFile((File *)(((Variable *)args[1])->getValue()));
	}
	else if(name->getName() == "printlnFile")
	{
		BuiltInRoutines::printlnFile((File *)(((Variable *)args[1])->getValue()), (String *)(((Variable *)args[2])->getValue()));
	}
	else if(name->getName() == "printFile")
	{
		BuiltInRoutines::printFile((File *)(((Variable *)args[1])->getValue()), (String *)(((Variable *)args[2])->getValue()));
	}
	else if(name->getName() == "println")
	{
		BuiltInRoutines::println((String *)(((Variable *)args[1])->getValue()));
	}
	else if(name->getName() == "print")
	{
		BuiltInRoutines::print((String *)(((Variable *)args[1])->getValue()));
	}
	else if(name->getName() == "inputFile")
	{
		memory->push(BuiltInRoutines::inputFile(memory, (File *)(((Variable *)args[1])->getValue())));
	}
	else if(name->getName() == "input")
	{
		memory->push(BuiltInRoutines::input(memory));
	}
	else if(name->getName() == "eof")
	{
		memory->push(BuiltInRoutines::eof((File *)(((Variable *)args[1])->getValue())));
	}
	else if(name->getName() == "eoi")
	{
		memory->push(BuiltInRoutines::eoi());
	}
	else if(name->getName() == "pow")
	{
		memory->push(BuiltInRoutines::pow((Double *)(((Variable *)args[1])->getValue()), (Double *)(((Variable *)args[2])->getValue())));
	}
	else if(name->getName() == "sqrt")
	{
		memory->push(BuiltInRoutines::sqrt((Double *)(((Variable *)args[1])->getValue())));
	}
	else if(name->getName() == "log")
	{
		memory->push(BuiltInRoutines::log((Double *)(((Variable *)args[1])->getValue())));
	}
	else if(name->getName() == "rand")
	{
		memory->push(BuiltInRoutines::rand((Integer *)(((Variable *)args[1])->getValue())));
	}
	else if(name->getName() == "indexOf")
	{
		memory->push(BuiltInRoutines::indexOf((String *)(((Variable *)args[1])->getValue()), (String *)(((Variable *)args[2])->getValue())));
	}
	else if(name->getName() == "lastIndexOf")
	{
		memory->push(BuiltInRoutines::lastIndexOf((String *)(((Variable *)args[1])->getValue()), (String *)(((Variable *)args[2])->getValue())));
	}
	else if(name->getName() == "substring")
	{
		memory->push(BuiltInRoutines::substring(memory, (String *)(((Variable *)args[1])->getValue()), (Integer *)(((Variable *)args[2])->getValue()), (Integer *)(((Variable *)args[3])->getValue())));
	}
	else if(name->getName() == "toLower")
	{
		memory->push(BuiltInRoutines::toLower(memory, (String *)(((Variable *)args[1])->getValue())));
	}
	else if(name->getName() == "toUpper")
	{
		memory->push(BuiltInRoutines::toUpper(memory, (String *)(((Variable *)args[1])->getValue())));
	}
	else if(name->getName() == "trim")
	{
		memory->push(BuiltInRoutines::trim(memory, (String *)(((Variable *)args[1])->getValue())));
	}
	else if(name->getName() == "startsWith")
	{
		memory->push(BuiltInRoutines::startsWith((String *)(((Variable *)args[1])->getValue()), (String *)(((Variable *)args[2])->getValue())));
	}
	else if(name->getName() == "endsWith")
	{
		memory->push(BuiltInRoutines::endsWith((String *)(((Variable *)args[1])->getValue()), (String *)(((Variable *)args[2])->getValue())));
	}
	else if(name->getName() == "concat")
	{
		memory->push(BuiltInRoutines::concat(memory, (String *)(((Variable *)args[1])->getValue()), (String *)(((Variable *)args[2])->getValue())));
	}
	else if(name->getName() == "strlen")
	{
		memory->push(BuiltInRoutines::strlen((String *)(((Variable *)args[1])->getValue())));
	}
	else if(name->getName() == "int2str")
	{
		memory->push(BuiltInRoutines::int2str(memory, (Integer *)(((Variable *)args[1])->getValue())));
	}
	else if(name->getName() == "double2str")
	{
		memory->push(BuiltInRoutines::double2str(memory, (Double *)(((Variable *)args[1])->getValue())));
	}
	else if(name->getName() == "str2int")
	{
		memory->push(BuiltInRoutines::str2int((String *)(((Variable *)args[1])->getValue())));
	}
	else if(name->getName() == "double2int")
	{
		memory->push(BuiltInRoutines::double2int((Double *)(((Variable *)args[1])->getValue())));
	}
	else if(name->getName() == "str2double")
	{
		memory->push(BuiltInRoutines::str2double((String *)(((Variable *)args[1])->getValue())));
	}
	else if(name->getName() == "int2double")
	{
		memory->push(BuiltInRoutines::int2double((Integer *)(((Variable *)args[1])->getValue())));
	}
	else
		throw new std::exception(("Interpreter::_invoke: routine '" + name->getName() + "' was not found!").c_str());
	//
	++IP;
}

void Interpreter::_jz(const Integer *to)
{
	if(ZF) IP = to->getValue();
	else ++IP;
}

void Interpreter::_jnz(const Integer *to)
{
	if(!ZF) IP = to->getValue();
	else ++IP;
}

void Interpreter::_jmp(const Integer *to)
{
	IP = to->getValue();
}

void Interpreter::_pop(Variable *dest)
{	// do not pop!
	// -- f.e.: call sqrt x; pop tmp1; call sqrt y; pop tmp2; --> variable tmp1 would be overwritten
	dest->setValue(memory->peekAndGetTopValAddr(dest->getItemType()));
	//
	++IP;
}

void Interpreter::_st(Variable *dest, Variable *src)
{
	dest->setValue(src);
	//
	++IP;
}

void Interpreter::_add(Variable *dest, const Variable *op1, const Variable *op2)
{
	if(op1->getItemType() == DataType::INTEGER)
	{
		dest->setValue(new Integer(((Integer *)(op1->getValue()))->getValue() + ((Integer *)(op2->getValue()))->getValue()));
		ZF = (((Integer *)(dest->getValue()))->getValue() == 0);
	}
	else if(op1->getItemType() == DataType::DOUBLE)
	{
		dest->setValue(new Double(((Double *)(op1->getValue()))->getValue() + ((Double *)(op2->getValue()))->getValue()));
		ZF = (((Double *)(dest->getValue()))->getValue() == 0.0);
	}
	else
		throw new std::exception("Interpreter::_add: invalid data type!");
	//
	++IP;
}

void Interpreter::_sub(Variable *dest, const Variable *op1, const Variable *op2)
{
	if(op1->getItemType() == DataType::INTEGER)
	{
		dest->setValue(new Integer(((Integer *)(op1->getValue()))->getValue() - ((Integer *)(op2->getValue()))->getValue()));
		ZF = (((Integer *)(dest->getValue()))->getValue() == 0);
	}
	else if(op1->getItemType() == DataType::DOUBLE)
	{
		dest->setValue(new Double(((Double *)(op1->getValue()))->getValue() - ((Double *)(op2->getValue()))->getValue()));
		ZF = (((Double *)(dest->getValue()))->getValue() == 0.0);
	}
	else
		throw new std::exception("Interpreter::_sub: invalid data type!");
	//
	++IP;
}

void Interpreter::_mul(Variable *dest, const Variable *op1, const Variable *op2)
{
	if(op1->getItemType() == DataType::INTEGER)
	{
		dest->setValue(new Integer(((Integer *)(op1->getValue()))->getValue() * ((Integer *)(op2->getValue()))->getValue()));
		ZF = (((Integer *)(dest->getValue()))->getValue() == 0);
	}
	else if(op1->getItemType() == DataType::DOUBLE)
	{
		dest->setValue(new Double(((Double *)(op1->getValue()))->getValue() * ((Double *)(op2->getValue()))->getValue()));
		ZF = (((Double *)(dest->getValue()))->getValue() == 0.0);
	}
	else
		throw new std::exception("Interpreter::_mul: invalid data type!");
	//
	++IP;
}

void Interpreter::_div(Variable *dest, const Variable *op1, const Variable *op2)
{
	if(op1->getItemType() == DataType::INTEGER)
	{
		if(((Integer *)(op2->getValue()))->getValue() == 0) throw new std::exception("Interpreter::_div: division be zero!");
		dest->setValue(new Integer(((Integer *)(op1->getValue()))->getValue() / ((Integer *)(op2->getValue()))->getValue()));
		ZF = (((Integer *)(dest->getValue()))->getValue() == 0);
	}
	else if(op1->getItemType() == DataType::DOUBLE)
	{
		dest->setValue(new Double(((Double *)(op1->getValue()))->getValue() / ((Double *)(op2->getValue()))->getValue()));
		ZF = (((Double *)(dest->getValue()))->getValue() == 0.0);
	}
	else
		throw new std::exception("Interpreter::_div: invalid data type!");
	//
	++IP;
}

void Interpreter::_mod(Variable *dest, const Variable *op1, const Variable *op2)
{
	if(op1->getItemType() == DataType::INTEGER)
	{
		if(((Integer *)(op2->getValue()))->getValue() == 0) throw new std::exception("Interpreter::_mod: division be zero!");
		dest->setValue(new Integer(((Integer *)(op1->getValue()))->getValue() % ((Integer *)(op2->getValue()))->getValue()));
		ZF = (((Integer *)(dest->getValue()))->getValue() == 0);
	}
	else
		throw new std::exception("Interpreter::_mod: invalid data type!");
	//
	++IP;
}

void Interpreter::_and(Variable *dest, const Variable *op1, const Variable *op2)
{
	if(op1->getItemType() == DataType::INTEGER)
	{
		dest->setValue(new Integer(((Integer *)(op1->getValue()))->getValue() & ((Integer *)(op2->getValue()))->getValue()));
		ZF = (((Integer *)(dest->getValue()))->getValue() == 0);
	}
	else if(op1->getItemType() == DataType::BOOLEAN)
	{
		dest->setValue(new Boolean(((Boolean *)(op1->getValue()))->getValue() & ((Boolean *)(op2->getValue()))->getValue()));
		ZF = (((Boolean *)(dest->getValue()))->getValue() == false);
	}
	else
		throw new std::exception("Interpreter::_and: invalid data type!");
	//
	++IP;
}

void Interpreter::_or(Variable *dest, const Variable *op1, const Variable *op2)
{
	if(op1->getItemType() == DataType::INTEGER)
	{
		dest->setValue(new Integer(((Integer *)(op1->getValue()))->getValue() | ((Integer *)(op2->getValue()))->getValue()));
		ZF = (((Integer *)(dest->getValue()))->getValue() == 0);
	}
	else if(op1->getItemType() == DataType::BOOLEAN)
	{
		dest->setValue(new Boolean(((Boolean *)(op1->getValue()))->getValue() | ((Boolean *)(op2->getValue()))->getValue()));
		ZF = (((Boolean *)(dest->getValue()))->getValue() == false);
	}
	else
		throw new std::exception("Interpreter::_or: invalid data type!");
	//
	++IP;
}

void Interpreter::_xor(Variable *dest, const Variable *op1, const Variable *op2)
{
	if(op1->getItemType() == DataType::INTEGER)
	{
		dest->setValue(new Integer(((Integer *)(op1->getValue()))->getValue() ^ ((Integer *)(op2->getValue()))->getValue()));
		ZF = (((Integer *)(dest->getValue()))->getValue() == 0);
	}
	else if(op1->getItemType() == DataType::BOOLEAN)
	{
		dest->setValue(new Boolean(((Boolean *)(op1->getValue()))->getValue() ^ ((Boolean *)(op2->getValue()))->getValue()));
		ZF = (((Boolean *)(dest->getValue()))->getValue() == false);
	}
	else
		throw new std::exception("Interpreter::_xor: invalid data type!");
	//
	++IP;
}

void Interpreter::_lsh(Variable *dest, const Variable *op1, const Variable *op2)
{
	if(op1->getItemType() == DataType::INTEGER)
	{
		dest->setValue(new Integer(((Integer *)(op1->getValue()))->getValue() << ((Integer *)(op2->getValue()))->getValue()));
		ZF = (((Integer *)(dest->getValue()))->getValue() == 0);
	}
	else if(op1->getItemType() == DataType::BOOLEAN)
	{
		dest->setValue(new Boolean(((Boolean *)(op1->getValue()))->getValue() << ((Boolean *)(op2->getValue()))->getValue()));
		ZF = (((Boolean *)(dest->getValue()))->getValue() == false);
	}
	else
		throw new std::exception("Interpreter::_lsh: invalid data type!");
	//
	++IP;
}

void Interpreter::_rsh(Variable *dest, const Variable *op1, const Variable *op2)
{
	if(op1->getItemType() == DataType::INTEGER)
	{
		dest->setValue(new Integer(((Integer *)(op1->getValue()))->getValue() >> ((Integer *)(op2->getValue()))->getValue()));
		ZF = (((Integer *)(dest->getValue()))->getValue() == 0);
	}
	else if(op1->getItemType() == DataType::BOOLEAN)
	{
		dest->setValue(new Boolean(((Boolean *)(op1->getValue()))->getValue() >> ((Boolean *)(op2->getValue()))->getValue()));
		ZF = (((Boolean *)(dest->getValue()))->getValue() == false);
	}
	else
		throw new std::exception("Interpreter::_rsh: invalid data type!");
	//
	++IP;
}

void Interpreter::_inc(Variable *var)
{
	if(var->getItemType() == DataType::INTEGER)
	{
		var->setValue(new Integer(((Integer *)var->getValue())->getValue() + 1));
		ZF = (((Integer *)((Variable *)var)->getValue())->getValue() == 0);
	}
	else
		throw new std::exception("Interpreter::_inc: invalid data type!");
	//
	++IP;
}

void Interpreter::_dec(Variable *var)
{
	if(var->getItemType() == DataType::INTEGER)
	{
		var->setValue(new Integer(((Integer *)var->getValue())->getValue() - 1));
		ZF = (((Integer *)((Variable *)var)->getValue())->getValue() == 0);
	}
	else
		throw new std::exception("Interpreter::_dec: invalid data type!");
	//
	++IP;
}

void Interpreter::_not(Variable *dest, const Variable *src)
{
	if(src->getItemType() == DataType::BOOLEAN)
	{
		dest->setValue(new Boolean(!(((Boolean *)(src->getValue()))->getValue())));
		ZF = (((Boolean *)(dest->getValue()))->getValue() == false);
	}
	else
		throw new std::exception("Interpreter::_not: invalid data type!");
	//
	++IP;
}

void Interpreter::_neg(Variable *dest, const Variable *src)
{
	if(src->getItemType() == DataType::INTEGER)
	{
		dest->setValue(new Integer(~(((Integer *)(src->getValue()))->getValue())));
		ZF = (((Integer *)(dest->getValue()))->getValue() == 0);
	}
	else if(src->getItemType() == DataType::BOOLEAN)
	{
		dest->setValue(new Boolean(~(((Boolean *)(src->getValue()))->getValue())));
		ZF = (((Boolean *)(dest->getValue()))->getValue() == false);
	}
	else
		throw new std::exception("Interpreter::_neg: invalid data type!");
	//
	++IP;
}

void Interpreter::_minus(Variable *dest, const Variable *src)
{
	if(src->getItemType() == DataType::INTEGER)
	{
		dest->setValue(new Integer(-(((Integer *)(src->getValue()))->getValue())));
		ZF = (((Integer *)(dest->getValue()))->getValue() == 0);
	}
	else if(src->getItemType() == DataType::DOUBLE)
	{
		dest->setValue(new Double(-(((Double *)(src->getValue()))->getValue())));
		ZF = (((Double *)(dest->getValue()))->getValue() == 0.0);
	}
	else
		throw new std::exception("Interpreter::_minus: invalid data type!");
	//
	++IP;
}

void Interpreter::_ldci(Variable *var, Integer *constant)
{
	var->setValue(constant);
	ZF = (constant->getValue() == 0);
	//
	++IP;
}

void Interpreter::_ldcb(Variable *var, Boolean *constant)
{
	var->setValue(constant);
	ZF = (constant->getValue() == false);
	//
	++IP;
}

void Interpreter::_ldcr(Variable *var, Double *constant)
{
	var->setValue(constant);
	ZF = (constant->getValue() == 0.0);
	//
	++IP;
}

void Interpreter::_ldcs(Variable *var, String *constant)
{	// string is now allocated on system heap, because it was parse before the interpreter was running
	// --> it must be reallocated into MemoryManager's heap
	int len = strlen(constant->getValue());
	char *str = (char *)memory->alloc(sizeof(char) * (len + 1));
	strcpy(str, constant->getValue());
	constant->setValue(str);
	var->setValue(constant);
	//
	++IP;
}

void Interpreter::_ldcn(Variable *var, Reference *constant)
{
	var->setValue(constant);
	ZF = true;
	//
	++IP;
}

void Interpreter::_new(Variable *var, const Variable *size)
{
	if(var->getItemType() == DataType::ARRAY)
	{
		int length = ((Integer *)size->getValue())->getValue();
		var->setValue(new Reference(memory->alloc((2 * sizeof(int)) + (var->getItemTypeSize() * length))));
		// set length and item type
		Array *arr = (Array *)(var->getValue());
		arr->setLength(length);
		arr->setType(var->getItemDataType()->subtype->type);
	}
	else	// structure
	{
		var->setValue(new Reference(memory->alloc(var->getItemTypeSize())));
	}
	//
	++IP;
}

void Interpreter::_lt(Variable *dest, const Variable *op1, const Variable *op2)
{
	if(op1->getItemType() == DataType::INTEGER)
		dest->setValue(new Boolean(((Integer *)(op1->getValue()))->getValue() < ((Integer *)(op2->getValue()))->getValue()));
	else if(op1->getItemType() == DataType::DOUBLE)
		dest->setValue(new Boolean(((Double *)(op1->getValue()))->getValue() < ((Double *)(op2->getValue()))->getValue()));
	else
		throw new std::exception("Interpreter::_lt: invalid data type!");
	//
	ZF = (((Boolean *)(dest->getValue()))->getValue() == false);
	++IP;
}

void Interpreter::_gt(Variable *dest, const Variable *op1, const Variable *op2)
{
	if(op1->getItemType() == DataType::INTEGER)
		dest->setValue(new Boolean(((Integer *)(op1->getValue()))->getValue() > ((Integer *)(op2->getValue()))->getValue()));
	else if(op1->getItemType() == DataType::DOUBLE)
		dest->setValue(new Boolean(((Double *)(op1->getValue()))->getValue() > ((Double *)(op2->getValue()))->getValue()));
	else
		throw new std::exception("Interpreter::_gt: invalid data type!");
	//
	ZF = (((Boolean *)(dest->getValue()))->getValue() == false);
	++IP;
}

void Interpreter::_lte(Variable *dest, const Variable *op1, const Variable *op2)
{
	if(op1->getItemType() == DataType::INTEGER)
		dest->setValue(new Boolean(((Integer *)(op1->getValue()))->getValue() <= ((Integer *)(op2->getValue()))->getValue()));
	else if(op1->getItemType() == DataType::DOUBLE)
		dest->setValue(new Boolean(((Double *)(op1->getValue()))->getValue() <= ((Double *)(op2->getValue()))->getValue()));
	else
		throw new std::exception("Interpreter::_lte: invalid data type!");
	//
	ZF = (((Boolean *)(dest->getValue()))->getValue() == false);
	++IP;
}

void Interpreter::_gte(Variable *dest, const Variable *op1, const Variable *op2)
{
	if(op1->getItemType() == DataType::INTEGER)
		dest->setValue(new Boolean(((Integer *)(op1->getValue()))->getValue() >= ((Integer *)(op2->getValue()))->getValue()));
	else if(op1->getItemType() == DataType::DOUBLE)
		dest->setValue(new Boolean(((Double *)(op1->getValue()))->getValue() >= ((Double *)(op2->getValue()))->getValue()));
	else
		throw new std::exception("Interpreter::_gte: invalid data type!");
	//
	ZF = (((Boolean *)(dest->getValue()))->getValue() == false);
	++IP;
}

void Interpreter::_eq(Variable *dest, const Variable *op1, const Variable *op2)
{
	if(op1->getItemType() == DataType::INTEGER)
		dest->setValue(new Boolean(((Integer *)(op1->getValue()))->getValue() == ((Integer *)(op2->getValue()))->getValue()));
	else if(op1->getItemType() == DataType::DOUBLE)
		dest->setValue(new Boolean(((Double *)(op1->getValue()))->getValue() == ((Double *)(op2->getValue()))->getValue()));
	else if(op1->getItemType() == DataType::BOOLEAN)
		dest->setValue(new Boolean(((Boolean *)(op1->getValue()))->getValue() == ((Boolean *)(op2->getValue()))->getValue()));
	else
		throw new std::exception("Interpreter::_eq: invalid data type!");
	//
	ZF = (((Boolean *)(dest->getValue()))->getValue() == false);
	++IP;
}

void Interpreter::_neq(Variable *dest, const Variable *op1, const Variable *op2)
{
	if(op1->getItemType() == DataType::INTEGER)
		dest->setValue(new Boolean(((Integer *)(op1->getValue()))->getValue() != ((Integer *)(op2->getValue()))->getValue()));
	else if(op1->getItemType() == DataType::DOUBLE)
		dest->setValue(new Boolean(((Double *)(op1->getValue()))->getValue() != ((Double *)(op2->getValue()))->getValue()));
	else if(op1->getItemType() == DataType::BOOLEAN)
		dest->setValue(new Boolean(((Boolean *)(op1->getValue()))->getValue() != ((Boolean *)(op2->getValue()))->getValue()));
	else
		throw new std::exception("Interpreter::_neq: invalid data type!");
	//
	ZF = (((Boolean *)(dest->getValue()))->getValue() == false);
	++IP;
}