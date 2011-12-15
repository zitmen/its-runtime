#include "JITCompiler.h"
#include "Interpreter.h"

void divByZeroEx()
{
	throw new std::exception("Runtime exception: Division by zero!");
}

void modByZeroEx()
{
	throw new std::exception("Runtime exception: Modulo by zero!");
}

void * heapAlloc(int size)
{
	return Interpreter::memory->alloc(size);
}

// ============================================================
// x86 Assembly - frequently used instructions
// ------------------------------------------------------------
// mov eax, 12345678h --> B8 78 56 34 12
// mov ebx, 12345678h --> BB 78 56 34 12
// mov ecx, 12345678h --> B9 78 56 34 12
// mov edx, 12345678h --> BA 78 56 34 12
// jmp _label         --> EB ?? (?? is 1B offset(=count of bytes to the instruction you're jumping to) to the _label; for longer jumps use long jump/call/...)
// jnz _label         --> doesn't work! --> use `jne`
// jne _label         --> 75 ?? --> 0x75 is opcode of `jnz`, but it still translates as `jne`...wtf?!
// --> if any argument is pointer without specification it is `dword ptr` --> 32b platform
// ============================================================

int JITCompiler::gen_prolog(char *code)
{
	// i don't need it, at least for now
	return 0;
}

int JITCompiler::gen_epilog(char *code)
{
	// i don't need it, at least for now; just `ret`
	const int code_len = 1;
	const char *precompiled = "\xC3";	//ret
	memcpy(code, precompiled, code_len);
	return code_len;
}

int JITCompiler::gen_call(char *code, FunctionSignature *fn, const vector<Argument *> &args)
{
	return 0;
	MemoryManager *memory = Interpreter::memory;
	// TODO
	//
	// 0. push signature to call stack
	//call_stack.push(fn);
	// 1. push IP
	//memory->push(new Integer(IP + 1));
	// 2. set new IP
	//IP = fn->pointer;
	// 3. push SFB
	memory->push(new Reference(memory->SFB));
	// 4. set new SFB
	memory->SFB = memory->SP;
	// 5. get arguments values from the stack before variables addresses will be changed
	vector<Argument *> values;
	for(size_t i = 1, im = args.size(); i < im; i++)
		values.push_back(((Variable *)args[i])->getValue());
	// 6. setup the stack - 1st come parameters, 2nd declared variables; set SP
    for(map<string, Variable *>::iterator it = fn->variables.begin(); it != fn->variables.end(); ++it)
		it->second->setAddress(memory->reserve(it->second));
	// 7. set values of arguments on the stack
	for(size_t i = 0, im = values.size(); i < im; i++)
        fn->variables[fn->arguments_ordering[i]]->setValue(values[i]);
}

int JITCompiler::gen_ret(char *code)
{
	return 0;
	MemoryManager *memory = Interpreter::memory;
	// TODO
	//
	// 0. pop signature from call stack
	FunctionSignature *fn;// = call_stack.top(); call_stack.pop();
	// 1. restore SP to the state before call -- top was at SFB (there is still old SFB and IP - next steps)
	memory->SP = memory->SFB;
	// 2. restore SFB of previous stack fram
	memory->SFB = (*((void **)(memory->popAndGetTopValAddr(DataType::REFERENCE))));
	// 3. restore IP
	//IP = (*((int *)(memory->popAndGetTopValAddr(DataType::INTEGER))));
	// 4. restore local variables
	int offset = 0;
	map<string, Variable *>::iterator it = fn->variables.end();
	do	// had to be done this way, because reverse_iterator didn't work :(
	{
		--it;
		offset += DataType::getTypeSize(it->second->getType());
		it->second->setAddress((void *)((char *)(memory->SP) - offset));
	} while(it != fn->variables.begin());
}

int JITCompiler::gen_retv(char *code, const Variable *var)
{
	return 0;
	// TODO
	Argument *retval = var->getValue();
	gen_ret(code);
	//memory->push(retval);
}

int JITCompiler::gen_invoke(char *code, Variable *name, const vector<Argument *> &args)
{
	return 0;
	// TODO
	MemoryManager *memory = Interpreter::memory;
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
		throw new std::exception(("JITCompiler::gen_invoke: routine '" + name->getName() + "' was not found!").c_str());
}

// Hint: asi podobne, jako pri generovani bytecodu
//	-- ukladat si adresy v retezu instrukci, kde ktera zacina a pak jen projit,
//     najit jumpy a nahradit adresy
//  -- to ma cenu resit az budu mit opravdovy generovani
int JITCompiler::gen_jz(char *code, const Integer *to)
{
	return 0;
	// TODO - jak prepocitat ten offset?? (const Integer to*)
	//if(ZF) IP = to->getValue();
	//else ++IP;
}

int JITCompiler::gen_jnz(char *code, const Integer *to)
{
	return 0;
	// TODO - jak prepocitat ten offset?? (const Integer to*)
	//if(!ZF) IP = to->getValue();
	//else ++IP;
}

int JITCompiler::gen_jmp(char *code, const Integer *to)
{
	return 0;
	// TODO - jak prepocitat ten offset?? (const Integer to*)
	//IP = to->getValue();
}

int JITCompiler::gen_pop(char *code, Variable *dest)
{
	return 0;
	// TODO
	//dest->setValue(memory->popAndGetTopValAddr(dest->getItemType()));
}

int JITCompiler::gen_st(char *code, Variable *dest, Variable *src)
{
	if(src->getItemType() == DataType::DOUBLE)	// 8B, FPU
	{
		const int code_len = 14;
		const char *precompiled = "\xB8????"	//mov eax, address(src)
								  "\xDD\x00"	//fld qword ptr [eax]
								  "\xBB????"	//mov ebx, address(dest)
								  "\xDD\x1B";	//fstp qword ptr [ebx]
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = src->getAddress();
		(*((void **)(code+8))) = dest->getAddress();
		return code_len;
	}
	else if(src->getItemType() == DataType::BOOLEAN)	// 1B, ALU
	{
		const int code_len = 14;
		const char *precompiled = "\xB8????"	//mov eax, address(src)
								  "\x8A\x00"	//mov al, byte ptr [eax]
								  "\xBB????"	//mov ebx, address(dest)
								  "\x88\x03";	//mov byte ptr [ebx], al
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = src->getAddress();
		(*((void **)(code+8))) = dest->getAddress();
		return code_len;
	}
	else	// INTEGER, REFERENCE, ARRAY, STRUCTURE, STRING -- 4B, ALU
	{
		const int code_len = 14;
		const char *precompiled = "\xB8????"	//mov eax, address(src)
								  "\x8B\x00"	//mov eax, dword ptr [eax]
								  "\xBB????"	//mov ebx, address(dest)
								  "\x89\x03";	//mov [ebx], eax
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = src->getAddress();
		(*((void **)(code+8))) = dest->getAddress();
		return code_len;
	}
}

int JITCompiler::gen_ldzf_alu(char *code, Variable *dest)
{
	//
	if(dest->getItemType() != DataType::BOOLEAN) throw new std::exception("JITCompiler::gen_ldzf: invalid data type!");
	//
	// update Zero-Flag inside of the interpreter
	const int code_len = 22;
	const char *precompiled = "\x66\x9C"			//pushf
							  "\x66\x58"			//pop ax	; flag register is only 2Bytes
							  "\x66\xBB\x40\x00"	//mov bx, 64; mask for extracting ZF stored at bit 6
							  "\x66\x23\xD8"		//and bx, ax
							  "\x66\xC1\xEB\x06"	//shr bx, 6	; shift to the right to get 0 or 1
							  "\xB8????"			//mov eax, address(dest)
							  "\x88\x18";			//mov byte ptr [eax], bl
	memcpy(code, precompiled, code_len);
	(*((void **)(code+16))) = dest->getAddress();
	return code_len;
}

int JITCompiler::gen_ldzf_fpu(char *code, Variable *dest)
{
	// update Zero-Flag inside of the machine (physical)
	const int code_len = 4;
	const char *precompiled = "\x9B"		//wait		; wait until the running instruction is done (if any)
							  "\xDF\xE0"	//fnstsw ax	; copy the status word to the AX register
							  "\x9E";		//sahf		; copy the condition bits in the CPU's flag register
	memcpy(code, precompiled, code_len);
	// then use the ZF detection for integers
	return (code_len + gen_ldzf_alu(code+code_len, dest));
}

int JITCompiler::gen_add(char *code, Variable *dest, const Variable *op1, const Variable *op2)
{
	if(op1->getItemType() == DataType::INTEGER)
	{
		const int code_len = 23;
		const char *precompiled = "\xB8????"	//mov eax, address(op1)
								  "\x8B\x00"	//mov eax, [eax]
								  "\xBB????"	//mov ebx, address(op2)
								  "\x8B\x1B"	//mov ebx, [ebx]
								  "\x03\xD8"	//add ebx, eax
								  "\xB8????"	//mov eax, address(dest)
								  "\x89\x18";	//mov [eax], ebx
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = op1->getAddress();
		(*((void **)(code+8))) = op2->getAddress();
		(*((void **)(code+17))) = dest->getAddress();
		return code_len;
	}
	else if(op1->getItemType() == DataType::DOUBLE)
	{
		const int code_len = 23;
		const char *precompiled = "\xB8????"	//mov eax, address(op1)
								  "\xDD\x00"	//fld qword ptr [eax]
								  "\xB8????"	//mov eax, address(op2)
								  "\xDD\x00"	//fld qword ptr [eax]
								  "\xDE\xC1"	//faddp st(1),st
								  "\xB8????"	//mov eax, address(dest)
								  "\xDD\x18";	//fstp qword ptr [eax]
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = op1->getAddress();
		(*((void **)(code+8))) = op2->getAddress();
		(*((void **)(code+17))) = dest->getAddress();
		return code_len;
	}
	else
		throw new std::exception("JITCompiler::gen_add: invalid data type!");
}

int JITCompiler::gen_sub(char *code, Variable *dest, const Variable *op1, const Variable *op2)
{
	if(op1->getItemType() == DataType::INTEGER)
	{
		const int code_len = 23;
		const char *precompiled = "\xB8????"	//mov eax, address(op1)
								  "\x8B\x00"	//mov eax, [eax]
								  "\xBB????"	//mov ebx, address(op2)
								  "\x8B\x1B"	//mov ebx, [ebx]
								  "\x2B\xC3"	//sub eax, ebx
								  "\xBB????"	//mov ebx, address(dest)
								  "\x89\x03";	//mov [ebx], eax
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = op1->getAddress();
		(*((void **)(code+8))) = op2->getAddress();
		(*((void **)(code+17))) = dest->getAddress();
		return code_len;
	}
	else if(op1->getItemType() == DataType::DOUBLE)
	{
		const int code_len = 23;
		const char *precompiled = "\xB8????"	//mov eax, address(op1)
								  "\xDD\x00"	//fld qword ptr [eax]
								  "\xB8????"	//mov eax, address(op2)
								  "\xDD\x00"	//fld qword ptr [eax]
								  "\xDE\xE9"	//fsubp st(1),st
								  "\xB8????"	//mov eax, address(dest)
								  "\xDD\x18";	//fstp qword ptr [eax]
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = op1->getAddress();
		(*((void **)(code+8))) = op2->getAddress();
		(*((void **)(code+17))) = dest->getAddress();
		return code_len;
	}
	else
		throw new std::exception("JITCompiler::gen_sub: invalid data type!");
}

int JITCompiler::gen_mul(char *code, Variable *dest, const Variable *op1, const Variable *op2)
{
	if(op1->getItemType() == DataType::INTEGER)
	{
		const int code_len = 23;
		const char *precompiled = "\xB8????"	//mov eax, address(op1)
								  "\x8B\x00"	//mov eax, [eax]
								  "\xBB????"	//mov ebx, address(op2)
								  "\x8B\x1B"	//mov ebx, [ebx]
								  "\xF7\xE3"	//mul ebx
								  "\xBB????"	//mov ebx, address(dest)
								  "\x89\x03";	//mov [ebx], eax
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = op1->getAddress();
		(*((void **)(code+8))) = op2->getAddress();
		(*((void **)(code+17))) = dest->getAddress();
		return code_len;
	}
	else if(op1->getItemType() == DataType::DOUBLE)
	{
		const int code_len = 23;
		const char *precompiled = "\xB8????"	//mov eax, address(op1)
								  "\xDD\x00"	//fld qword ptr [eax]
								  "\xB8????"	//mov eax, address(op2)
								  "\xDD\x00"	//fld qword ptr [eax] 
								  "\xDE\xC9"	//fmul
								  "\xB8????"	//mov eax, address(dest)
								  "\xDD\x18";	//fstp qword ptr [eax]
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = op1->getAddress();
		(*((void **)(code+8))) = op2->getAddress();
		(*((void **)(code+17))) = dest->getAddress();
		return code_len;
	}
	else
		throw new std::exception("JITCompiler::gen_mul: invalid data type!");
}

int JITCompiler::gen_div(char *code, Variable *dest, const Variable *op1, const Variable *op2)
{
	if(op1->getItemType() == DataType::INTEGER)
	{
		const int code_len = 37;
		const char *precompiled = "\xBB????"	//			mov ebx, address(op2)
								  "\x8B\x1B"	//			mov ebx, [ebx]
								  "\x83\xFB\x00"//			cmp ebx, 0
								  "\x75\x07"	//			jne _valid
								  "\xB8????"	//			mov eax, address(divByZeroEx)
								  "\xFF\xD0"	//			call eax
								  "\xB8????"	//_valid:	mov eax, address(op1)
								  "\x8B\x00"	//			mov eax, [eax]
								  "\x33\xD2"	//			xor edx, edx	;before division, the edx has to be set to zero
								  "\xF7\xF3"	//			div ebx
								  "\xBB????"	//			mov ebx, address(dest)
								  "\x89\x03";	//			mov [ebx], eax
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = op2->getAddress();
		(*((void **)(code+13))) = divByZeroEx;
		(*((void **)(code+20))) = op1->getAddress();
		(*((void **)(code+31))) = dest->getAddress();
		return code_len;
	}
	else if(op1->getItemType() == DataType::DOUBLE)
	{
		const int code_len = 23;
		const char *precompiled = "\xB8????"	//mov eax, address(op1)
								  "\xDD\x00"	//fld qword ptr [eax]
								  "\xB8????"	//mov eax, address(op2)
								  "\xDD\x00"	//fld qword ptr [eax]
								  "\xDE\xF9"	//fdivp
								  "\xB8????"	//mov eax, address(dest)
								  "\xDD\x18";	//fstp qword ptr [eax]
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = op1->getAddress();
		(*((void **)(code+8))) = op2->getAddress();
		(*((void **)(code+17))) = dest->getAddress();
		return code_len;
	}
	else
		throw new std::exception("JITCompiler::gen_div: invalid data type!");
}

int JITCompiler::gen_mod(char *code, Variable *dest, const Variable *op1, const Variable *op2)
{
	if(op1->getItemType() == DataType::INTEGER)
	{
		const int code_len = 37;
		const char *precompiled = "\xBB????"	//			mov ebx, address(op2)
								  "\x8B\x1B"	//			mov ebx, [ebx]
								  "\x83\xFB\x00"//			cmp ebx, 0
								  "\x75\x07"	//			jne _valid
								  "\xB8????"	//			mov eax, address(modByZeroEx)
								  "\xFF\xD0"	//			call eax
								  "\xB8????"	//_valid:	mov eax, address(op1)
								  "\x8B\x00"	//			mov eax, [eax]
								  "\x33\xD2"	//			xor edx, edx	;before division, the edx has to be set to zero
								  "\xF7\xF3"	//			div ebx
								  "\xBB????"	//			mov ebx, address(dest)
								  "\x89\x13";	//			mov [ebx], edx
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = op2->getAddress();
		(*((void **)(code+13))) = modByZeroEx;
		(*((void **)(code+20))) = op1->getAddress();
		(*((void **)(code+31))) = dest->getAddress();
		return code_len;
	}
	else
		throw new std::exception("JITCompiler::gen_mod: invalid data type!");
}

int JITCompiler::gen_and(char *code, Variable *dest, const Variable *op1, const Variable *op2)
{
	if(op1->getItemType() == DataType::INTEGER)
	{
		const int code_len = 23;
		const char *precompiled = "\xB8????"	//mov eax, address(op1)
								  "\x8B\x00"	//mov eax, [eax]
								  "\xBB????"	//mov ebx, address(op2)
								  "\x8B\x1B"	//mov ebx, [ebx]
								  "\x23\xC3"	//and eax, ebx
								  "\xBB????"	//mov ebx, address(dest)
								  "\x89\x03";	//mov [ebx], eax
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = op1->getAddress();
		(*((void **)(code+8))) = op2->getAddress();
		(*((void **)(code+17))) = dest->getAddress();
		return code_len;
	}
	else if((op1->getItemType() == DataType::BOOLEAN))
	{
		const int code_len = 23;
		const char *precompiled = "\xB8????"	//mov eax, address(op1)
								  "\x8A\x00"	//mov al, byte ptr [eax]
								  "\xBB????"	//mov ebx, address(op2)
								  "\x8A\x1B"	//mov bl, byte ptr [ebx]
								  "\x22\xC3"	//and al,bl
								  "\xBB????"	//mov ebx, address(dest)
								  "\x88\x03";	//mov byte ptr [ebx],al
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = op1->getAddress();
		(*((void **)(code+8))) = op2->getAddress();
		(*((void **)(code+17))) = dest->getAddress();
		return code_len;
	}
	else
		throw new std::exception("JITCompiler::gen_and: invalid data type!");
}

int JITCompiler::gen_or(char *code, Variable *dest, const Variable *op1, const Variable *op2)
{
	if(op1->getItemType() == DataType::INTEGER)
	{
		const int code_len = 23;
		const char *precompiled = "\xB8????"	//mov eax, address(op1)
								  "\x8B\x00"	//mov eax, [eax]
								  "\xBB????"	//mov ebx, address(op2)
								  "\x8B\x1B"	//mov ebx, [ebx]
								  "\x0B\xC3"	//or eax, ebx
								  "\xBB????"	//mov ebx, address(dest)
								  "\x89\x03";	//mov [ebx], eax
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = op1->getAddress();
		(*((void **)(code+8))) = op2->getAddress();
		(*((void **)(code+17))) = dest->getAddress();
		return code_len;
	}
	else if((op1->getItemType() == DataType::BOOLEAN))
	{
		const int code_len = 23;
		const char *precompiled = "\xB8????"	//mov eax, address(op1)
								  "\x8A\x00"	//mov al, byte ptr [eax]
								  "\xBB????"	//mov ebx, address(op2)
								  "\x8A\x1B"	//mov bl, byte ptr [ebx]
								  "\x0A\xC3"	//or al, bl
								  "\xBB????"	//mov ebx, address(dest)
								  "\x88\x03";	//mov byte ptr [ebx],al
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = op1->getAddress();
		(*((void **)(code+8))) = op2->getAddress();
		(*((void **)(code+17))) = dest->getAddress();
		return code_len;
	}
	else
		throw new std::exception("JITCompiler::gen_or: invalid data type!");
}

int JITCompiler::gen_xor(char *code, Variable *dest, const Variable *op1, const Variable *op2)
{
	if(op1->getItemType() == DataType::INTEGER)
	{
		const int code_len = 23;
		const char *precompiled = "\xB8????"	//mov eax, address(op1)
								  "\x8B\x00"	//mov eax, [eax]
								  "\xBB????"	//mov ebx, address(op2)
								  "\x8B\x1B"	//mov ebx, [ebx]
								  "\x33\xC3"	//xor eax, ebx
								  "\xBB????"	//mov ebx, address(dest)
								  "\x89\x03";	//mov [ebx], eax
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = op1->getAddress();
		(*((void **)(code+8))) = op2->getAddress();
		(*((void **)(code+17))) = dest->getAddress();
		return code_len;
	}
	else if((op1->getItemType() == DataType::BOOLEAN))
	{
		const int code_len = 23;
		const char *precompiled = "\xB8????"	//mov eax, address(op1)
								  "\x8A\x00"	//mov al, byte ptr [eax]
								  "\xBB????"	//mov ebx, address(op2)
								  "\x8A\x1B"	//mov bl, byte ptr [ebx]
								  "\x32\xC3"	//xor al, bl
								  "\xBB????"	//mov ebx, address(dest)
								  "\x88\x03";	//mov byte ptr [ebx],al
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = op1->getAddress();
		(*((void **)(code+8))) = op2->getAddress();
		(*((void **)(code+17))) = dest->getAddress();
		return code_len;
	}
	else
		throw new std::exception("JITCompiler::gen_xor: invalid data type!");
}

int JITCompiler::gen_lsh(char *code, Variable *dest, const Variable *op1, const Variable *op2)
{
	if(op1->getItemType() == DataType::INTEGER)
	{
		const int code_len = 23;
		const char *precompiled = "\xB8????"	//mov eax, address(op1)
								  "\x8B\x00"	//mov eax, [eax]
								  "\xB9????"	//mov ecx, address(op2)
								  "\x8B\x09"	//mov ecx, [ecx]
								  "\xD3\xE0"	//shl eax, cl
								  "\xBB????"	//mov ebx, address(dest)
								  "\x89\x03";	//mov [ebx], eax
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = op1->getAddress();
		(*((void **)(code+8))) = op2->getAddress();
		(*((void **)(code+17))) = dest->getAddress();
		return code_len;
	}
	else
		throw new std::exception("JITCompiler::gen_lsh: invalid data type!");
}

int JITCompiler::gen_rsh(char *code, Variable *dest, const Variable *op1, const Variable *op2)
{
	if(op1->getItemType() == DataType::INTEGER)
	{
		const int code_len = 23;
		const char *precompiled = "\xB8????"	//mov eax, address(op1)
								  "\x8B\x00"	//mov eax, [eax]
								  "\xB9????"	//mov ecx, address(op2)
								  "\x8B\x09"	//mov ecx, [ecx]
								  "\xD3\xE8"	//shr eax, cl
								  "\xBB????"	//mov ebx, address(dest)
								  "\x89\x03";	//mov [ebx], eax
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = op1->getAddress();
		(*((void **)(code+8))) = op2->getAddress();
		(*((void **)(code+17))) = dest->getAddress();
		return code_len;
	}
	else
		throw new std::exception("JITCompiler::gen_rsh: invalid data type!");
}

int JITCompiler::gen_inc(char *code, Variable *var)
{
	if(var->getItemType() == DataType::INTEGER)
	{
		const int code_len = 7;
		const char *precompiled = "\xB8????"	//mov eax, address(var)
								  "\xFF\x00";	//inc dword ptr [eax]
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = var->getAddress();
		return code_len;
	}
	else
		throw new std::exception("JITCompiler::gen_inc: invalid data type!");
}

int JITCompiler::gen_dec(char *code, Variable *var)
{
	if(var->getItemType() == DataType::INTEGER)
	{
		const int code_len = 7;
		const char *precompiled = "\xB8????"	//mov eax, address(var)
								  "\xFF\x08";	//dec dword ptr [eax]
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = var->getAddress();
		return code_len;
	}
	else
		throw new std::exception("JITCompiler::gen_dec: invalid data type!");
}

int JITCompiler::gen_not(char *code, Variable *dest, const Variable *src)
{
	if((src->getItemType() == DataType::BOOLEAN))
	{
		const int code_len = 18;
		const char *precompiled = "\xB8????"	//mov eax, address(src)
								  "\x8A\x00"	//mov al, byte ptr [eax]
								  "\xF6\xD0"	//not al
								  "\x24\x01"	//and al, 1
								  "\xBB????"	//mov ebx, address(dest)
								  "\x88\x03";	//mov byte ptr [ebx],al
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = src->getAddress();
		(*((void **)(code+12))) = dest->getAddress();
		return code_len;
	}
	else
		throw new std::exception("JITCompiler::gen_not: invalid data type!");
}

int JITCompiler::gen_neg(char *code, Variable *dest, const Variable *src)
{
	if(src->getItemType() == DataType::INTEGER)
	{
		const int code_len = 16;
		const char *precompiled = "\xB8????"	//mov eax, address(src)
								  "\x8B\x00"	//mov eax, [eax]
								  "\xF7\xD0"	//not eax  
								  "\xBB????"	//mov ebx, address(dest)
								  "\x89\x03";	//mov [ebx], eax
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = src->getAddress();
		(*((void **)(code+10))) = dest->getAddress();
		return code_len;
	}
	else if(src->getItemType() == DataType::BOOLEAN)
		return gen_not(code, dest, src);
	else
		throw new std::exception("JITCompiler::gen_neg: invalid data type!");
}

int JITCompiler::gen_minus(char *code, Variable *dest, const Variable *src)
{
	if(src->getItemType() == DataType::INTEGER)
	{
		const int code_len = 16;
		const char *precompiled = "\xB8????"	//mov eax, address(src)
								  "\x8B\x00"	//mov eax, [eax]
								  "\xF7\xD8"	//neg eax  
								  "\xBB????"	//mov ebx, address(dest)
								  "\x89\x03";	//mov [ebx], eax
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = src->getAddress();
		(*((void **)(code+10))) = dest->getAddress();
		return code_len;
	}
	else if(src->getItemType() == DataType::DOUBLE)
	{
		const int code_len = 16;
		const char *precompiled = "\xB8????"	//mov eax, address(src)
								  "\xDD\x00"	//fld qword ptr [eax]  
								  "\xD9\xE0"	//fchs
								  "\xB8????"	//mov eax, address(dest)
								  "\xDD\x18";	//fstp qword ptr [eax]
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = src->getAddress();
		(*((void **)(code+10))) = dest->getAddress();
		return code_len;
	}
	else
		throw new std::exception("JITCompiler::gen_minus: invalid data type!");
	//
	return 0;
}

int JITCompiler::gen_ldci(char *code, Variable *var, Integer *constant)
{
	const int code_len = 12;
	const char *precompiled = "\xB8????"	//mov eax, address(var)
							  "\xBB????"	//mov ebx, value(constant)
							  "\x89\x18";	//mov [eax], ebx  
	memcpy(code, precompiled, code_len);
	(*((void **)(code+1))) = var->getAddress();
	(*((int *)(code+6))) = constant->getValue();
	return code_len;
}

int JITCompiler::gen_ldcb(char *code, Variable *var, Boolean *constant)
{
	const int code_len = 9;
	const char *precompiled = "\xB8????"	//mov eax, address(var)
							  "\xB3?"		//mov bl, value(constant)
							  "\x88\x18";	//mov byte ptr [eax], bl
	memcpy(code, precompiled, code_len);
	(*((void **)(code+1))) = var->getAddress();
	(*((bool *)(code+6))) = constant->getValue();
	return code_len;
}

int JITCompiler::gen_ldcr(char *code, Variable *var, Double *constant)
{
	// this is kind of hack used in exploits :)
	// -- save double value inside of the code and use labels for addressing the constant
	const int code_len = 23;
	const char *precompiled = "\xEB\x08"	//_const:	jump _instr
							  "????????"	//			; value(constant)
							  "\xB8????"	//_instr:	mov eax, address(var)
							  "\xDD\x05????"//			fld qword ptr [_const]
							  "\xDD\x18";	//			fstp qword ptr [eax]
	memcpy(code, precompiled, code_len);
	(*((double *)(code+2))) = constant->getValue();
	(*((void **)(code+11))) = var->getAddress();
	(*((void **)(code+17))) = (code+2);
	return code_len;
}

int JITCompiler::gen_ldcs(char *code, Variable *var, String *constant)
{
	const int code_len = 52;
	const char *precompiled = "\xB8????"		//mov eax, value(constant)
							  "\x50"			//push eax
							  "\xB8????"		//mov eax, address(strlen)
							  "\xFF\xD0"		//call eax
							  "\x83\xC4\x04"	//add esp, 4	; pop function argument
							  "\x40"			//inc eax		; strlen + 1B for ending zero
							  "\x50"			//push eax
							  "\xB8????"		//mov eax, address(heapAlloc)
							  "\xFF\xD0"		//call eax
							  "\x83\xC4\x04"	//add esp, 4	; pop function argument
							  "\xBB????"		//mov ebx, value(constant)
							  "\x53"			//push ebx		; constant string value
							  "\x50"			//push eax		; allocated space ptr
							  "\xB8????"		//mov eax, address(strcpy)
							  "\xFF\xD0"		//call eax		; pop function arguments (2*4Bytes)
							  "\x83\xC4\x08"	//add esp, 8
							  "\xBB????"		//mov ebx, address(var)
							  "\x89\x03";		//mov dword ptr [ebx],eax	; string copied into the newly allocated memory block
	memcpy(code, precompiled, code_len);
	(*((char **)(code+1))) = constant->getValue();
	(*((void **)(code+7))) = strlen;
	(*((void **)(code+19))) = heapAlloc;
	(*((char **)(code+29))) = constant->getValue();
	(*((void **)(code+36))) = strcpy;
	(*((void **)(code+46))) = var->getAddress();
	return code_len;
}

int JITCompiler::gen_ldcn(char *code, Variable *var, Reference *constant)
{
	return gen_ldci(code, var, new Integer(0));
}

int JITCompiler::gen_new(char *code, Variable *var, const Variable *size)
{
	return 0;
	// TODO: not tested yet!
	int item_size = var->getItemTypeSize();
	void *p_heapAlloc = heapAlloc;
	void *x = var->getAddress();
	//
	if(var->getItemType() == DataType::ARRAY)
	{
		void *len = size->getAddress();
		int item_type = var->getItemDataType()->subtype->type;
		__asm
		{
			; x = heapAlloc(item_size * length + 8)
			mov eax, item_size
			mov ecx, len
			mov ecx, [ecx]
			mul ecx
			add eax, 8
			push eax
			mov eax, p_heapAlloc
			call eax
			mov ebx, x
			mov [ebx], eax
			; x->length = len
			; x->item_type = item_type
			mov [eax], ecx
			mov ebx, item_type
			mov [eax + 4], ebx
		}
	}
	else	// structure
	{
		int item_size = var->getItemTypeSize();
		void *p_heapAlloc = heapAlloc;
		void *x = var->getAddress();
		__asm
		{
			; ptr = heapAlloc(sizeof(x))
			; x = ptr
			mov eax, item_size
			push eax
			mov eax, p_heapAlloc
			call eax
			add esi, 4	; pop function argument
			mov ebx, x
			mov [ebx], eax	; save ptr into x
		}
	}
}

int JITCompiler::gen_lt(char *code, Variable *dest, const Variable *op1, const Variable *op2)
{
	if(op1->getItemType() == DataType::INTEGER)
	{
		const int code_len = 31;
		const char *precompiled = "\xBB????"	//		mov ebx, address(op2)
								  "\x8B\x1B"	//		mov ebx, [ebx]
								  "\xB8????"	//		mov eax, address(op1)
								  "\x8B\x00"	//		mov eax, [eax]
								  "\x3B\xC3"	//		cmp eax, ebx
								  "\x7C\x04"	//		jl _lt
								  "\xB0\x00"	//		mov al, 0
								  "\xEB\x02"	//		jmp _cont
								  "\xB0\x01"	//_lt:	mov al, 1
								  "\xBB????"	//_cont:mov ebx, address(dest)
								  "\x88\x03";	//		mov byte ptr [ebx], al
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = op2->getAddress();
		(*((void **)(code+8))) = op1->getAddress();
		(*((void **)(code+25))) = dest->getAddress();
		return code_len;
	}
	else if(op1->getItemType() == DataType::DOUBLE)
	{
		const int code_len = 37;
		const char *precompiled = "\xB8????"			//				mov eax, address(op2)
								  "\xDD\x00"			//				fld qword ptr [eax]
								  "\xB8????"			//				mov eax, address(op1)
								  "\xDD\x00"			//				fld qword ptr [eax]
								  "\xDE\xD9"			//				fcompp
								  "\xDF\xE0"			//				fnstsw ax	; copy flags to AX
								  "\x66\x25\x00\x01"	//				and ax, 100h; gen C0 flag (8th bit; C0 = compare less than - carry flag)
								  "\x75\x04"			//				jne _less
								  "\xB0\x00"			//				mov al, 0
								  "\xEB\x02"			//				jmp _continue
								  "\xB0\x01"			//_less:		mov al, 1
								  "\xBB????"			//_continue:	mov ebx, address(dest)
								  "\x88\x03";			//				mov byte ptr [ebx], al
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = op2->getAddress();
		(*((void **)(code+8))) = op1->getAddress();
		(*((void **)(code+31))) = dest->getAddress();
		return code_len;
	}
	else
		throw new std::exception("JITCompiler::gen_lt: invalid data type!");
}

int JITCompiler::gen_gt(char *code, Variable *dest, const Variable *op1, const Variable *op2)
{
	if(op1->getItemType() == DataType::INTEGER)
	{
		const int code_len = 31;
		const char *precompiled = "\xBB????"	//		mov ebx, address(op2)
								  "\x8B\x1B"	//		mov ebx, [ebx]
								  "\xB8????"	//		mov eax, address(op1)
								  "\x8B\x00"	//		mov eax, [eax]
								  "\x3B\xC3"	//		cmp eax, ebx
								  "\x7F\x04"	//		jg _gt
								  "\xB0\x00"	//		mov al, 0
								  "\xEB\x02"	//		jmp _cont
								  "\xB0\x01"	//_gt:	mov al, 1
								  "\xBB????"	//_cont:mov ebx, address(dest)
								  "\x88\x03";	//		mov byte ptr [ebx], al
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = op2->getAddress();
		(*((void **)(code+8))) = op1->getAddress();
		(*((void **)(code+25))) = dest->getAddress();
		return code_len;
	}
	else if(op1->getItemType() == DataType::DOUBLE)
	{
		const int code_len = 37;
		const char *precompiled = "\xB8????"			//				mov eax, address(op2)
								  "\xDD\x00"			//				fld qword ptr [eax]
								  "\xB8????"			//				mov eax, address(op1)
								  "\xDD\x00"			//				fld qword ptr [eax]
								  "\xDE\xD9"			//				fcompp
								  "\xDF\xE0"			//				fnstsw ax	; copy flags to AX
								  "\x66\x25\x00\x41"	//				and ax, 4100h; gen C0 and C3 flags (8th bit - C0 = compare less than - carry flag; 14th bit - C3 = compare equal - zero flag)
								  "\x74\x04"			//				je _greater
								  "\xB0\x00"			//				mov al, 0
								  "\xEB\x02"			//				jmp _continue
								  "\xB0\x01"			//_greater:		mov al, 1
								  "\xBB????"			//_continue:	mov ebx, address(dest)
								  "\x88\x03";			//				mov byte ptr [ebx], al
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = op2->getAddress();
		(*((void **)(code+8))) = op1->getAddress();
		(*((void **)(code+31))) = dest->getAddress();
		return code_len;
	}
	else
		throw new std::exception("JITCompiler::gen_gt: invalid data type!");
}

int JITCompiler::gen_lte(char *code, Variable *dest, const Variable *op1, const Variable *op2)
{
	if(op1->getItemType() == DataType::INTEGER)
	{
		const int code_len = 31;
		const char *precompiled = "\xBB????"	//		mov ebx, address(op2)
								  "\x8B\x1B"	//		mov ebx, [ebx]
								  "\xB8????"	//		mov eax, address(op1)
								  "\x8B\x00"	//		mov eax, [eax]
								  "\x3B\xC3"	//		cmp eax, ebx
								  "\x7E\x04"	//		jle _lte
								  "\xB0\x00"	//		mov al, 0
								  "\xEB\x02"	//		jmp _cont
								  "\xB0\x01"	//_lte:	mov al, 1
								  "\xBB????"	//_cont:mov ebx, address(dest)
								  "\x88\x03";	//		mov byte ptr [ebx], al
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = op2->getAddress();
		(*((void **)(code+8))) = op1->getAddress();
		(*((void **)(code+25))) = dest->getAddress();
		return code_len;
	}
	else if(op1->getItemType() == DataType::DOUBLE)
	{
		const int code_len = 37;
		const char *precompiled = "\xB8????"			//				mov eax, address(op2)
								  "\xDD\x00"			//				fld qword ptr [eax]
								  "\xB8????"			//				mov eax, address(op1)
								  "\xDD\x00"			//				fld qword ptr [eax]
								  "\xDE\xD9"			//				fcompp
								  "\xDF\xE0"			//				fnstsw ax	; copy flags to AX
								  "\x66\x25\x00\x41"	//				and ax, 4100h; gen C0 and C3 flags (8th bit - C0 = compare less than - carry flag; 14th bit - C3 = compare equal - zero flag)
								  "\x75\x04"			//				jne _less_eq
								  "\xB0\x00"			//				mov al, 0
								  "\xEB\x02"			//				jmp _continue
								  "\xB0\x01"			//_greater:		mov al, 1
								  "\xBB????"			//_continue:	mov ebx, address(dest)
								  "\x88\x03";			//				mov byte ptr [ebx], al
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = op2->getAddress();
		(*((void **)(code+8))) = op1->getAddress();
		(*((void **)(code+31))) = dest->getAddress();
		return code_len;
	}
	else
		throw new std::exception("JITCompiler::gen_lte: invalid data type!");
}

int JITCompiler::gen_gte(char *code, Variable *dest, const Variable *op1, const Variable *op2)
{
	if(op1->getItemType() == DataType::INTEGER)
	{
		const int code_len = 31;
		const char *precompiled = "\xBB????"	//		mov ebx, address(op2)
								  "\x8B\x1B"	//		mov ebx, [ebx]
								  "\xB8????"	//		mov eax, address(op1)
								  "\x8B\x00"	//		mov eax, [eax]
								  "\x3B\xC3"	//		cmp eax, ebx
								  "\x7D\x04"	//		jge _gte
								  "\xB0\x00"	//		mov al, 0
								  "\xEB\x02"	//		jmp _cont
								  "\xB0\x01"	//_gte:	mov al, 1
								  "\xBB????"	//_cont:mov ebx, address(dest)
								  "\x88\x03";	//		mov byte ptr [ebx], al
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = op2->getAddress();
		(*((void **)(code+8))) = op1->getAddress();
		(*((void **)(code+25))) = dest->getAddress();
		return code_len;
	}
	else if(op1->getItemType() == DataType::DOUBLE)
	{
		const int code_len = 37;
		const char *precompiled = "\xB8????"			//				mov eax, address(op2)
								  "\xDD\x00"			//				fld qword ptr [eax]
								  "\xB8????"			//				mov eax, address(op1)
								  "\xDD\x00"			//				fld qword ptr [eax]
								  "\xDE\xD9"			//				fcompp
								  "\xDF\xE0"			//				fnstsw ax	; copy flags to AX
								  "\x66\x25\x00\x01"	//				and ax, 100h; gen C0 flag (8th bit; C0 = compare less than - carry flag)
								  "\x74\x04"			//				je _great_eq
								  "\xB0\x00"			//				mov al, 0
								  "\xEB\x02"			//				jmp _continue
								  "\xB0\x01"			//_less:		mov al, 1
								  "\xBB????"			//_continue:	mov ebx, address(dest)
								  "\x88\x03";			//				mov byte ptr [ebx], al
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = op2->getAddress();
		(*((void **)(code+8))) = op1->getAddress();
		(*((void **)(code+31))) = dest->getAddress();
		return code_len;
	}
	else
		throw new std::exception("JITCompiler::gen_gte: invalid data type!");
}

int JITCompiler::gen_eq(char *code, Variable *dest, const Variable *op1, const Variable *op2)
{
	if((op1->getItemType() == DataType::BOOLEAN))
	{
		const int code_len = 31;
		const char *precompiled = "\xBB????"	//			mov ebx, address(op2)
								  "\x8A\x1B"	//			mov bl, byte ptr [ebx]
								  "\xB8????"	//			mov eax, address(op1)
								  "\x8A\x00"	//			mov al, byte ptr [eax]
								  "\x3A\xC3"	//			cmp al, bl
								  "\x74\x04"	//			je _eq_b
								  "\xB0\x00"	//			mov al, 0
								  "\xEB\x02"	//			jmp _cont_b
								  "\xB0\x01"	//_eq_b:	mov al, 1
								  "\xBB????"	//_cont_b:	mov ebx, address(dest)
								  "\x88\x03";	//			mov byte ptr [ebx], al
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = op2->getAddress();
		(*((void **)(code+8))) = op1->getAddress();
		(*((void **)(code+25))) = dest->getAddress();
		return code_len;
	}
	else if(op1->getItemType() == DataType::INTEGER)
	{
		const int code_len = 31;
		const char *precompiled = "\xBB????"	//			mov ebx, address(op2)
								  "\x8B\x1B"	//			mov ebx, [ebx]
								  "\xB8????"	//			mov eax, address(op1)
								  "\x8B\x00"	//			mov eax, [eax]
								  "\x3B\xC3"	//			cmp eax, ebx
								  "\x74\x04"	//			je _eq_i
								  "\xB0\x00"	//			mov al, 0
								  "\xEB\x02"	//			jmp _cont_i
								  "\xB0\x01"	//_eq_i:	mov al, 1
								  "\xBB????"	//_cont_i:	mov ebx, address(dest)
								  "\x88\x03";	//			mov byte ptr [ebx], al
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = op2->getAddress();
		(*((void **)(code+8))) = op1->getAddress();
		(*((void **)(code+25))) = dest->getAddress();
		return code_len;
	}
	else if(op1->getItemType() == DataType::DOUBLE)
	{
		const int code_len = 37;
		const char *precompiled = "\xB8????"			//			mov eax, address(op2)
								  "\xDD\x00"			//			fld qword ptr [eax]
								  "\xB8????"			//			mov eax, address(op1)
								  "\xDD\x00"			//			fld qword ptr [eax]
								  "\xDE\xD9"			//			fcompp
								  "\xDF\xE0"			//			fnstsw ax		; copy flags to AX
								  "\x66\x25\x00\x40"	//			and ax, 4000h	; gen C3 flag (14th bit - C3 = compare equal - zero flag)
								  "\x75\x04"			//			jne _eq_d
								  "\xB0\x00"			//			mov al, 0
								  "\xEB\x02"			//			jmp _cont_d
								  "\xB0\x01"			//_eq_d:	mov al, 1
								  "\xBB????"			//_cont_d:	mov ebx, address(dest)
								  "\x88\x03";			//			mov byte ptr [ebx], al
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = op2->getAddress();
		(*((void **)(code+8))) = op1->getAddress();
		(*((void **)(code+31))) = dest->getAddress();
		return code_len;
	}
	else
		throw new std::exception("JITCompiler::gen_eq: invalid data type!");
}

int JITCompiler::gen_neq(char *code, Variable *dest, const Variable *op1, const Variable *op2)
{
/*
	void *x = op1->getAddress(), *y = op2->getAddress(), *z = dest->getAddress(), *zf = &ZF;
	if(op1->getItemType() == DataType::BOOLEAN)
	{
		__asm
		{
			; z = x != y
			mov ebx, y
			mov bl, byte ptr [ebx]
			mov eax, x
			mov al, byte ptr [eax]
			cmp al, bl
			jne _neq_b
			mov al, 0
			jmp _cont_b
_neq_b:		mov al, 1
_cont_b:	mov ebx, z
			mov byte ptr [ebx], al
		}
	}
	else if(op1->getItemType() == DataType::INTEGER)
	{
		__asm
		{
			; z = x != y
			mov ebx, y
			mov ebx, [ebx]
			mov eax, x
			mov eax, [eax]
			cmp eax, ebx
			jne _neq_i
			mov al, 0
			jmp _cont_i
_neq_i:		mov al, 1
_cont_i:	mov ebx, z
			mov byte ptr [ebx], al
		}
	}
	else if(op1->getItemType() == DataType::DOUBLE)
	{
		__asm
		{
			; z = x != y
			mov eax, y
			fld qword ptr [eax]
			mov eax, x
			fld qword ptr [eax]
			fcompp
			fnstsw ax	; copy flags to AX
			and ax, 0x4000	; gen C3 flag (14th bit - C3 = compare equal - zero flag)
			jz _neq_d
			mov al, 0
			jmp _cont_d
_neq_d:		mov al, 1
_cont_d:	mov ebx, z
			mov byte ptr [ebx], al
		}
	}
	else
*/
	if((op1->getItemType() == DataType::BOOLEAN))
	{
		const int code_len = 31;
		const char *precompiled = "\xBB????"	//			mov ebx, address(op2)
								  "\x8A\x1B"	//			mov bl, byte ptr [ebx]
								  "\xB8????"	//			mov eax, address(op1)
								  "\x8A\x00"	//			mov al, byte ptr [eax]
								  "\x3A\xC3"	//			cmp al, bl
								  "\x75\x04"	//			jne _neq_b
								  "\xB0\x00"	//			mov al, 0
								  "\xEB\x02"	//			jmp _cont_b
								  "\xB0\x01"	//_neq_b:	mov al, 1
								  "\xBB????"	//_cont_b:	mov ebx, address(dest)
								  "\x88\x03";	//			mov byte ptr [ebx], al
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = op2->getAddress();
		(*((void **)(code+8))) = op1->getAddress();
		(*((void **)(code+25))) = dest->getAddress();
		return code_len;
	}
	else if(op1->getItemType() == DataType::INTEGER)
	{
		const int code_len = 31;
		const char *precompiled = "\xBB????"	//			mov ebx, address(op2)
								  "\x8B\x1B"	//			mov ebx, [ebx]
								  "\xB8????"	//			mov eax, address(op1)
								  "\x8B\x00"	//			mov eax, [eax]
								  "\x3B\xC3"	//			cmp eax, ebx
								  "\x75\x04"	//			jne _neq_i
								  "\xB0\x00"	//			mov al, 0
								  "\xEB\x02"	//			jmp _cont_i
								  "\xB0\x01"	//_neq_i:	mov al, 1
								  "\xBB????"	//_cont_i:	mov ebx, address(dest)
								  "\x88\x03";	//			mov byte ptr [ebx], al
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = op2->getAddress();
		(*((void **)(code+8))) = op1->getAddress();
		(*((void **)(code+25))) = dest->getAddress();
		return code_len;
	}
	else if(op1->getItemType() == DataType::DOUBLE)
	{
		const int code_len = 37;
		const char *precompiled = "\xB8????"			//			mov eax, address(op2)
								  "\xDD\x00"			//			fld qword ptr [eax]
								  "\xB8????"			//			mov eax, address(op1)
								  "\xDD\x00"			//			fld qword ptr [eax]
								  "\xDE\xD9"			//			fcompp
								  "\xDF\xE0"			//			fnstsw ax		; copy flags to AX
								  "\x66\x25\x00\x40"	//			and ax, 4000h	; gen C3 flag (14th bit - C3 = compare equal - zero flag)
								  "\x74\x04"			//			je _neq_d
								  "\xB0\x00"			//			mov al, 0
								  "\xEB\x02"			//			jmp _cont_d
								  "\xB0\x01"			//_neq_d:	mov al, 1
								  "\xBB????"			//_cont_d:	mov ebx, address(dest)
								  "\x88\x03";			//			mov byte ptr [ebx], al
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = op2->getAddress();
		(*((void **)(code+8))) = op1->getAddress();
		(*((void **)(code+31))) = dest->getAddress();
		return code_len;
	}
	else
		throw new std::exception("JITCompiler::gen_neq: invalid data type!");
}