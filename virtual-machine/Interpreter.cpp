#include "Interpreter.h"

void Interpreter::_call(FunctionSignature *fn, const vector<Argument *> &args)
{
	// 1. push IP
	memory->push(new Integer(IP + 1));
	// 2. set IP
	IP = fn->pointer;
	// 3. push SP
	memory->push(new Reference(memory->SP));
	// 4. setup the stack - 1st come parameters, 2nd declared variables; set SP
	for(map<string, Variable *>::iterator it = fn->variables.begin(); it != fn->variables.end(); ++it)
		it->second->setAddress(memory->push(it->second, false));
}

void Interpreter::_invoke(const Variable *name, const vector<Argument *> &args)
{	// TODO zbytek
	if(name->getName() == "println")
	{
		BuiltInRoutines::println((String *)args[0]);
	}
	else if(name->getName() == "int2str")
	{
		memory->push(BuiltInRoutines::int2str(memory, (Integer *)args[0]));
	}
	else if(name->getName() == "double2str")
	{
		memory->push(BuiltInRoutines::double2str(memory, (Double *)args[0]));
	}
	else if(name->getName() == "concat")
	{
		memory->push(BuiltInRoutines::concat(memory, (String *)args[0], (String *)args[1]));
	}
	else if(name->getName() == "sqrt")
	{
		memory->push(BuiltInRoutines::sqrt((Double *)args[0]));
	}
	else
		throw new std::exception(("Interpreter::_invoke: routine '" + name->getName() + "' was not found!").c_str());
	//
	++IP;
}

void Interpreter::_jz(const Integer *to)
{
	if(ZF) IP = to->getValue();
}

void Interpreter::_jnz(const Integer *to)
{
	if(!ZF) IP = to->getValue();
}

void Interpreter::_jmp(const Integer *to)
{
	IP = to->getValue();
}

void Interpreter::_ret()
{
	// 1. load SP - it automaticaly "deletes" local variables of the function that interpreter returns from
	memory->SP = ((Reference *)memory->pop(DataType::REFERENCE))->getValue();
	// 2. load IP
	IP = ((Integer *)memory->pop(DataType::INTEGER))->getValue();
}

void Interpreter::_retv(const Variable *var)
{
	_ret();
	memory->push(var);
}

void Interpreter::_pop(Variable *dest)
{
	dest->setValue(memory->pop(dest->getType()));
	//
	++IP;
}

void Interpreter::_st(Variable *dest, Variable *src)
{
	dest->setValue(src);
	//
	++IP;
}

void Interpreter::_sta()
{
	// TODO
}

void Interpreter::_add(Variable *dest, const Variable *op1, const Variable *op2)
{
	if(op1->getType() == DataType::INTEGER)
	{
		dest->setValue(new Integer(((Integer *)op1->getValue())->getValue() + ((Integer *)op2->getValue())->getValue()));
		ZF = (((Integer *)dest)->getValue() == 0);
	}
	else if(op1->getType() == DataType::DOUBLE)
	{
		dest->setValue(new Double(((Double *)op1->getValue())->getValue() + ((Double *)op2->getValue())->getValue()));
		ZF = (((Double *)dest)->getValue() == 0.0);
	}
	else
		throw new std::exception("Interpreter::_add: invalid data type!");
	//
	++IP;
}

void Interpreter::_sub(Variable *dest, const Variable *op1, const Variable *op2)
{
	if(op1->getType() == DataType::INTEGER)
	{
		dest->setValue(new Integer(((Integer *)op1->getValue())->getValue() - ((Integer *)op2->getValue())->getValue()));
		ZF = (((Integer *)dest)->getValue() == 0);
	}
	else if(op1->getType() == DataType::DOUBLE)
	{
		dest->setValue(new Double(((Double *)op1->getValue())->getValue() - ((Double *)op2->getValue())->getValue()));
		ZF = (((Double *)dest)->getValue() == 0.0);
	}
	else
		throw new std::exception("Interpreter::_sub: invalid data type!");
	//
	++IP;
}

void Interpreter::_mul(Variable *dest, const Variable *op1, const Variable *op2)
{
	if(op1->getType() == DataType::INTEGER)
	{
		dest->setValue(new Integer(((Integer *)op1->getValue())->getValue() * ((Integer *)op2->getValue())->getValue()));
		ZF = (((Integer *)dest)->getValue() == 0);
	}
	else if(op1->getType() == DataType::DOUBLE)
	{
		dest->setValue(new Double(((Double *)op1->getValue())->getValue() * ((Double *)op2->getValue())->getValue()));
		ZF = (((Double *)dest)->getValue() == 0.0);
	}
	else
		throw new std::exception("Interpreter::_mul: invalid data type!");
	//
	++IP;
}

void Interpreter::_div(Variable *dest, const Variable *op1, const Variable *op2)
{
	if(op1->getType() == DataType::INTEGER)
	{
		dest->setValue(new Integer(((Integer *)op1->getValue())->getValue() / ((Integer *)op2->getValue())->getValue()));
		ZF = (((Integer *)dest)->getValue() == 0);
	}
	else if(op1->getType() == DataType::DOUBLE)
	{
		dest->setValue(new Double(((Double *)op1->getValue())->getValue() / ((Double *)op2->getValue())->getValue()));
		ZF = (((Double *)dest)->getValue() == 0.0);
	}
	else
		throw new std::exception("Interpreter::_div: invalid data type!");
	//
	++IP;
}

void Interpreter::_mod(Variable *dest, const Variable *op1, const Variable *op2)
{
	if(op1->getType() == DataType::INTEGER)
	{
		dest->setValue(new Integer(((Integer *)op1->getValue())->getValue() % ((Integer *)op2->getValue())->getValue()));
		ZF = (((Integer *)dest)->getValue() == 0);
	}
	else
		throw new std::exception("Interpreter::_mod: invalid data type!");
	//
	++IP;
}

void Interpreter::_and(Variable *dest, const Variable *op1, const Variable *op2)
{
	if(op1->getType() == DataType::INTEGER)
	{
		dest->setValue(new Integer(((Integer *)op1->getValue())->getValue() & ((Integer *)op2->getValue())->getValue()));
		ZF = (((Integer *)dest)->getValue() == 0);
	}
	else if(op1->getType() == DataType::BOOLEAN)
	{
		dest->setValue(new Boolean(((Boolean *)op1->getValue())->getValue() & ((Boolean *)op2->getValue())->getValue()));
		ZF = ((Boolean *)dest)->getValue();
	}
	else
		throw new std::exception("Interpreter::_and: invalid data type!");
	//
	++IP;
}

void Interpreter::_or(Variable *dest, const Variable *op1, const Variable *op2)
{
	if(op1->getType() == DataType::INTEGER)
	{
		dest->setValue(new Integer(((Integer *)op1->getValue())->getValue() | ((Integer *)op2->getValue())->getValue()));
		ZF = (((Integer *)dest)->getValue() == 0);
	}
	else if(op1->getType() == DataType::BOOLEAN)
	{
		dest->setValue(new Boolean(((Boolean *)op1->getValue())->getValue() | ((Boolean *)op2->getValue())->getValue()));
		ZF = ((Boolean *)dest)->getValue();
	}
	else
		throw new std::exception("Interpreter::_or: invalid data type!");
	//
	++IP;
}

void Interpreter::_xor(Variable *dest, const Variable *op1, const Variable *op2)
{
	if(op1->getType() == DataType::INTEGER)
	{
		dest->setValue(new Integer(((Integer *)op1->getValue())->getValue() ^ ((Integer *)op2->getValue())->getValue()));
		ZF = (((Integer *)dest)->getValue() == 0);
	}
	else if(op1->getType() == DataType::BOOLEAN)
	{
		dest->setValue(new Boolean(((Boolean *)op1->getValue())->getValue() ^ ((Boolean *)op2->getValue())->getValue()));
		ZF = ((Boolean *)dest)->getValue();
	}
	else
		throw new std::exception("Interpreter::_xor: invalid data type!");
	//
	++IP;
}

void Interpreter::_lsh(Variable *dest, const Variable *op1, const Variable *op2)
{
	if(op1->getType() == DataType::INTEGER)
	{
		dest->setValue(new Integer(((Integer *)op1->getValue())->getValue() << ((Integer *)op2->getValue())->getValue()));
		ZF = (((Integer *)dest)->getValue() == 0);
	}
	else if(op1->getType() == DataType::BOOLEAN)
	{
		dest->setValue(new Boolean(((Boolean *)op1->getValue())->getValue() << ((Boolean *)op2->getValue())->getValue()));
		ZF = ((Boolean *)dest)->getValue();
	}
	else
		throw new std::exception("Interpreter::_lsh: invalid data type!");
	//
	++IP;
}

void Interpreter::_rsh(Variable *dest, const Variable *op1, const Variable *op2)
{
	if(op1->getType() == DataType::INTEGER)
	{
		dest->setValue(new Integer(((Integer *)op1->getValue())->getValue() >> ((Integer *)op2->getValue())->getValue()));
		ZF = (((Integer *)dest)->getValue() == 0);
	}
	else if(op1->getType() == DataType::BOOLEAN)
	{
		dest->setValue(new Boolean(((Boolean *)op1->getValue())->getValue() >> ((Boolean *)op2->getValue())->getValue()));
		ZF = ((Boolean *)dest)->getValue();
	}
	else
		throw new std::exception("Interpreter::_rsh: invalid data type!");
	//
	++IP;
}

void Interpreter::_inc(Variable *var)
{
	if(var->getType() == DataType::INTEGER)
	{
		var->setValue(new Integer(((Integer *)var->getValue())->getValue() + 1));
		ZF = (((Integer *)var)->getValue() == 0);
	}
	else
		throw new std::exception("Interpreter::_inc: invalid data type!");
	//
	++IP;
}

void Interpreter::_dec(Variable *var)
{
	if(var->getType() == DataType::INTEGER)
	{
		var->setValue(new Integer(((Integer *)var->getValue())->getValue() - 1));
		ZF = (((Integer *)var)->getValue() == 0);
	}
	else
		throw new std::exception("Interpreter::_dec: invalid data type!");
	//
	++IP;
}

void Interpreter::_not(Variable *dest, const Variable *src)
{
	if(src->getType() == DataType::BOOLEAN)
	{
		dest->setValue(new Boolean(!(((Boolean *)src->getValue())->getValue())));
		ZF = ((Boolean *)dest)->getValue();
	}
	else
		throw new std::exception("Interpreter::_not: invalid data type!");
	//
	++IP;
}

void Interpreter::_neg(Variable *dest, const Variable *src)
{
	if(src->getType() == DataType::INTEGER)
	{
		dest->setValue(new Integer(~(((Integer *)src->getValue())->getValue())));
		ZF = (((Integer *)dest)->getValue() == 0);
	}
	else if(src->getType() == DataType::BOOLEAN)
	{
		dest->setValue(new Boolean(~(((Boolean *)src->getValue())->getValue())));
		ZF = ((Boolean *)dest)->getValue();
	}
	else
		throw new std::exception("Interpreter::_neg: invalid data type!");
	//
	++IP;
}

void Interpreter::_minus(Variable *dest, const Variable *src)
{
	if(src->getType() == DataType::INTEGER)
	{
		dest->setValue(new Integer(-(((Integer *)src->getValue())->getValue())));
		ZF = (((Integer *)dest)->getValue() == 0);
	}
	else if(src->getType() == DataType::DOUBLE)
	{
		dest->setValue(new Double(-(((Double *)src->getValue())->getValue())));
		ZF = (((Double *)dest)->getValue() == 0.0);
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
	ZF = constant->getValue();
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
	char *str = (char *)memory->alloc(sizeof(int) + sizeof(char) * (len + 1));
	(*((int *)str)) = len;
	strcpy(str + sizeof(int), constant->getValue());
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
	//TODO
}

void Interpreter::_lt(Variable *dest, const Variable *op1, const Variable *op2)
{
	if(op1->getType() == DataType::INTEGER)
		dest->setValue(new Boolean(((Integer *)op1->getValue())->getValue() < ((Integer *)op2->getValue())->getValue()));
	else if(op1->getType() == DataType::DOUBLE)
		dest->setValue(new Boolean(((Double *)op1->getValue())->getValue() < ((Double *)op2->getValue())->getValue()));
	else
		throw new std::exception("Interpreter::_lt: invalid data type!");
	//
	ZF = ((Boolean *)dest)->getValue();
	++IP;
}

void Interpreter::_gt(Variable *dest, const Variable *op1, const Variable *op2)
{
	if(op1->getType() == DataType::INTEGER)
		dest->setValue(new Boolean(((Integer *)op1->getValue())->getValue() > ((Integer *)op2->getValue())->getValue()));
	else if(op1->getType() == DataType::DOUBLE)
		dest->setValue(new Boolean(((Double *)op1->getValue())->getValue() > ((Double *)op2->getValue())->getValue()));
	else
		throw new std::exception("Interpreter::_gt: invalid data type!");
	//
	ZF = ((Boolean *)dest)->getValue();
	++IP;
}

void Interpreter::_lte(Variable *dest, const Variable *op1, const Variable *op2)
{
	if(op1->getType() == DataType::INTEGER)
		dest->setValue(new Boolean(((Integer *)op1->getValue())->getValue() <= ((Integer *)op2->getValue())->getValue()));
	else if(op1->getType() == DataType::DOUBLE)
		dest->setValue(new Boolean(((Double *)op1->getValue())->getValue() <= ((Double *)op2->getValue())->getValue()));
	else
		throw new std::exception("Interpreter::_lte: invalid data type!");
	//
	ZF = ((Boolean *)dest)->getValue();
	++IP;
}

void Interpreter::_gte(Variable *dest, const Variable *op1, const Variable *op2)
{
	if(op1->getType() == DataType::INTEGER)
		dest->setValue(new Boolean(((Integer *)op1->getValue())->getValue() >= ((Integer *)op2->getValue())->getValue()));
	else if(op1->getType() == DataType::DOUBLE)
		dest->setValue(new Boolean(((Double *)op1->getValue())->getValue() >= ((Double *)op2->getValue())->getValue()));
	else
		throw new std::exception("Interpreter::_gte: invalid data type!");
	//
	ZF = ((Boolean *)dest)->getValue();
	++IP;
}

void Interpreter::_eq(Variable *dest, const Variable *op1, const Variable *op2)
{
	if(op1->getType() == DataType::INTEGER)
		dest->setValue(new Boolean(((Integer *)op1->getValue())->getValue() == ((Integer *)op2->getValue())->getValue()));
	else if(op1->getType() == DataType::DOUBLE)
		dest->setValue(new Boolean(((Double *)op1->getValue())->getValue() == ((Double *)op2->getValue())->getValue()));
	else if(op1->getType() == DataType::BOOLEAN)
		dest->setValue(new Boolean(((Boolean *)op1->getValue())->getValue() == ((Boolean *)op2->getValue())->getValue()));
	else
		throw new std::exception("Interpreter::_eq: invalid data type!");
	//
	ZF = ((Boolean *)dest)->getValue();
	++IP;
}

void Interpreter::_neq(Variable *dest, const Variable *op1, const Variable *op2)
{
	if(op1->getType() == DataType::INTEGER)
		dest->setValue(new Boolean(((Integer *)op1->getValue())->getValue() != ((Integer *)op2->getValue())->getValue()));
	else if(op1->getType() == DataType::DOUBLE)
		dest->setValue(new Boolean(((Double *)op1->getValue())->getValue() != ((Double *)op2->getValue())->getValue()));
	else if(op1->getType() == DataType::BOOLEAN)
		dest->setValue(new Boolean(((Boolean *)op1->getValue())->getValue() != ((Boolean *)op2->getValue())->getValue()));
	else
		throw new std::exception("Interpreter::_neq: invalid data type!");
	//
	ZF = ((Boolean *)dest)->getValue();
	++IP;
}