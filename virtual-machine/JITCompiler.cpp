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
	// TODO
	//
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
	//
	return 0;
}

int JITCompiler::gen_ret(char *code)
{
	// TODO
	//
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
	do	// had to be done this way, because reverse_iterator didn't work :(
	{
		--it;
		offset += DataType::getTypeSize(it->second->getType());
		it->second->setAddress((void *)((char *)(memory->SP) - offset));
	} while(it != fn->variables.begin());
	//
	return 0;
}

int JITCompiler::gen_retv(char *code, const Variable *var)
{
	// TODO
	Argument *retval = var->getValue();
	gen_ret(code);
	memory->push(retval);
	//
	return 0;
}

int JITCompiler::gen_invoke(char *code, Variable *name, const vector<Argument *> &args)
{
	// TODO
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
	//
	return 0;
}

// Hint: asi podobne, jako pri generovani bytecodu
//	-- ukladat si adresy v retezu instrukci, kde ktera zacina a pak jen projit,
//     najit jumpy a nahradit adresy
//  -- to ma cenu resit az budu mit opravdovy generovani
int JITCompiler::gen_jz(char *code, const Integer *to)
{
	// TODO - jak prepocitat ten offset?? (const Integer to*)
	if(ZF) IP = to->getValue();
	else ++IP;
	//
	return 0;
}

int JITCompiler::gen_jnz(char *code, const Integer *to)
{
	// TODO - jak prepocitat ten offset?? (const Integer to*)
	if(!ZF) IP = to->getValue();
	else ++IP;
	//
	return 0;
}

int JITCompiler::gen_jmp(char *code, const Integer *to)
{
	// TODO - jak prepocitat ten offset?? (const Integer to*)
	IP = to->getValue();
	//
	return 0;
}

int JITCompiler::gen_pop(char *code, Variable *dest)
{
	// TODO
	dest->setValue(memory->popAndGetTopValAddr(dest->getItemType()));
	//
	return 0;
}

int JITCompiler::gen_st(char *code, Variable *dest, Variable *src)
{
	void *x = dest->getAddress(), *y = src->getAddress();
	if(src->getItemType() == DataType::DOUBLE)	// 8B, FPU
	{
		__asm
		{
			; x = y
			mov eax, y
			fld qword ptr [eax]
			mov ebx, x
			fstp qword ptr [ebx]
		}
	}
	else if(src->getItemType() == DataType::BOOLEAN)	// 1B, ALU
	{
		__asm
		{
			; x = y
			mov eax, y
			mov al, byte ptr [eax]
			mov ebx, x
			mov byte ptr [ebx], al
		}
	}
	else	// INTEGER, REFERENCE, ARRAY, STRUCTURE, STRING -- 4B, ALU
	{
		__asm
		{
			; x = y
			mov eax, y
			mov eax, [eax]
			mov ebx, x
			mov [ebx], eax
		}
	}
	//
	return 0;
}

int JITCompiler::gen_ldzf_int(char *code, Variable *dest)
{
	void *zf = dest->getAddress();
	__asm
	{
		; update Zero-Flag inside of the interpreter
		pushf
		mov eax, 0
		pop ax	; flag register is only 2B
		mov ebx, 1	; mask for extracting ZF stored at bit 0
		and ebx, eax
		mov eax, zf
		mov [eax], ebx
	}
	//
	return 0;
}

int JITCompiler::gen_ldzf_double(char *code, Variable *dest)
{
	const int code_length = 0;
	void *zf = dest->getAddress();
	__asm
	{
		; update Zero-Flag inside of the machine (physical)
		fstsw ax        ; copy the status word to the AX register
		fwait           ; wait until the instruction is completed
		sahf            ; copy the condition bits in the CPU's flag register
	}
	//	then use the ZF detection for integers
	return gen_ldzf_int(code+code_length, dest);
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
	void *x = var->getAddress(), *zf = &ZF;
	if((var->getItemType() == DataType::INTEGER))
	{
		__asm
		{
			; x++
			mov ebx, x
			mov eax, [ebx]
			inc eax
			mov [ebx], eax
		}
	}
	else
		throw new std::exception("JITCompiler::gen_inc: invalid data type!");
	//
	return 0;
}

int JITCompiler::gen_dec(char *code, Variable *var)
{
	void *x = var->getAddress(), *zf = &ZF;
	if((var->getItemType() == DataType::INTEGER))
	{
		__asm
		{
			; x--
			mov ebx, x
			mov eax, [ebx]
			dec eax
			mov [ebx], eax
		}
	}
	else
		throw new std::exception("JITCompiler::gen_dec: invalid data type!");
	//
	return 0;
}

int JITCompiler::gen_not(char *code, Variable *dest, const Variable *src)
{
	void *x = src->getAddress(), *y = dest->getAddress(), *zf = &ZF;
	if(src->getItemType() == DataType::BOOLEAN)
	{
		__asm
		{
			; y = !x
			mov eax, x
			mov al, byte ptr [eax]
			not al
			and al, 1
			mov ebx, y
			mov byte ptr [ebx], al
		}
	}
	else
		throw new std::exception("JITCompiler::gen_not: invalid data type!");
	//
	return 0;
}

int JITCompiler::gen_neg(char *code, Variable *dest, const Variable *src)
{
	void *x = src->getAddress(), *y = dest->getAddress(), *zf = &ZF;
	if(src->getItemType() == DataType::INTEGER)
	{
		__asm
		{
			; y = ~x
			mov eax, x
			mov eax, [eax]
			not eax
			mov ebx, y
			mov [ebx], eax
		}
		//
		return 0;
	}
	else if(src->getItemType() == DataType::BOOLEAN)
		return gen_not(code, dest, src);
	else
		throw new std::exception("JITCompiler::gen_neg: invalid data type!");
}

int JITCompiler::gen_minus(char *code, Variable *dest, const Variable *src)
{
	void *x = src->getAddress(), *y = dest->getAddress(), *zf = &ZF;
	if(src->getItemType() == DataType::INTEGER)
	{
		__asm
		{
			; y = -x
			mov eax, x
			mov eax, [eax]
			neg eax
			mov ebx, y
			mov [ebx], eax
		}
	}
	else if(src->getItemType() == DataType::DOUBLE)
	{
		__asm
		{
			; y = -x
			mov eax, x
			fld qword ptr [eax]
			fchs
			mov eax, y
			fstp qword ptr [eax]
		}
	}
	else
		throw new std::exception("JITCompiler::gen_minus: invalid data type!");
	//
	return 0;
}

int JITCompiler::gen_ldci(char *code, Variable *var, Integer *constant)
{
	void *x = var->getAddress();
	int i = constant->getValue();
	__asm
	{
		; x = i
		mov eax, x
		mov ebx, i
		mov [eax], ebx
	}
	//
	return 0;
}

int JITCompiler::gen_ldcb(char *code, Variable *var, Boolean *constant)
{
	void *x = var->getAddress();
	bool b = constant->getValue();
	__asm
	{
		; x = b
		mov eax, x
		mov bl, b
		mov byte ptr [eax], bl
	}
	//
	return 0;
}

int JITCompiler::gen_ldcr(char *code, Variable *var, Double *constant)
{
	void *x = var->getAddress();
	double d = constant->getValue();
	__asm
	{
		; x = d
		mov eax, x
		fld qword ptr [d]
		fstp qword ptr [eax]
	}
	//
	return 0;
}

int JITCompiler::gen_ldcs(char *code, Variable *var, String *constant)
{
	void *p_strlen = strlen, *p_heapAlloc = heapAlloc, *p_strcpy = strcpy;
	void *x = var->getAddress();
	char *s = constant->getValue();
	__asm
	{
		; ptr = heapAlloc(strlen(s))
		; strcpy(ptr, s);
		; x = ptr;
		mov eax, s
		push eax
		mov eax, p_strlen
		call eax
		add esp, 4	; pop function argument
		add eax, 2
		push eax
		mov eax, p_heapAlloc
		call eax
		add esp, 4	; pop function argument
		mov ebx, s
		push ebx	; constant string value
		push eax	; allocated space ptr
		mov eax, p_strcpy
		call eax
		add esp, 8	; pop function arguments (2*4B)
		mov ebx, x
		mov [ebx], eax	; string copied into the newly allocated memory block
	}
	//
	return 0;
}

int JITCompiler::gen_ldcn(char *code, Variable *var, Reference *constant)
{
	return gen_ldci(code, var, new Integer(0));
}

int JITCompiler::gen_new(char *code, Variable *var, const Variable *size)
{	// TODO: not tested yet!
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
	//
	return 0;
}

int JITCompiler::gen_lt(char *code, Variable *dest, const Variable *op1, const Variable *op2)
{
	void *x = op1->getAddress(), *y = op2->getAddress(), *z = dest->getAddress(), *zf = &ZF;
	if(op1->getItemType() == DataType::INTEGER)
	{
		__asm
		{
			; z = x < y
			mov ebx, y
			mov ebx, [ebx]
			mov eax, x
			mov eax, [eax]
			cmp eax, ebx
			jl _lt
			mov al, 0
			jmp _cont
_lt:		mov al, 1
_cont:		mov ebx, z
			mov byte ptr [ebx], al
		}
	}
	else if(op1->getItemType() == DataType::DOUBLE)
	{
		__asm
		{
			; z = x < y
			mov eax, y
			fld qword ptr [eax]
			mov eax, x
			fld qword ptr [eax]
			fcompp
			fnstsw ax	; copy flags to AX
			and ax, 256	; gen C0 flag (8th bit; C0 = compare less than - carry flag)
			jnz _less
			mov al, 0
			jmp _continue
_less:		mov al, 1
_continue:	mov ebx, z
			mov byte ptr [ebx], al
		}
	}
	else
		throw new std::exception("JITCompiler::gen_lt: invalid data type!");
	//
	return 0;
}

int JITCompiler::gen_gt(char *code, Variable *dest, const Variable *op1, const Variable *op2)
{
	void *x = op1->getAddress(), *y = op2->getAddress(), *z = dest->getAddress(), *zf = &ZF;
	if(op1->getItemType() == DataType::INTEGER)
	{
		__asm
		{
			; z = x > y
			mov ebx, y
			mov ebx, [ebx]
			mov eax, x
			mov eax, [eax]
			cmp eax, ebx
			jg _gt
			mov al, 0
			jmp _cont
_gt:		mov al, 1
_cont:		mov ebx, z
			mov byte ptr [ebx], al
		}
	}
	else if(op1->getItemType() == DataType::DOUBLE)
	{
		__asm
		{
			; z = x > y
			mov eax, y
			fld qword ptr [eax]
			mov eax, x
			fld qword ptr [eax]
			fcompp
			fnstsw ax	; copy flags to AX
			and ax, 0x4100	; gen C0 and C3 flags (8th bit - C0 = compare less than - carry flag; 14th bit - C3 = compare equal - zero flag)
			jz _greater
			mov al, 0
			jmp _continue
_greater:	mov al, 1
_continue:	mov ebx, z
			mov byte ptr [ebx], al
		}
	}
	else
		throw new std::exception("JITCompiler::gen_gt: invalid data type!");
	//
	return 0;
}

int JITCompiler::gen_lte(char *code, Variable *dest, const Variable *op1, const Variable *op2)
{
	void *x = op1->getAddress(), *y = op2->getAddress(), *z = dest->getAddress(), *zf = &ZF;
	if(op1->getItemType() == DataType::INTEGER)
	{
		__asm
		{
			; z = x <= y
			mov ebx, y
			mov ebx, [ebx]
			mov eax, x
			mov eax, [eax]
			cmp eax, ebx
			jle _lte
			mov al, 0
			jmp _cont
_lte:		mov al, 1
_cont:		mov ebx, z
			mov byte ptr [ebx], al
		}
	}
	else if(op1->getItemType() == DataType::DOUBLE)
	{
		__asm
		{
			; z = x <= y
			mov eax, y
			fld qword ptr [eax]
			mov eax, x
			fld qword ptr [eax]
			fcompp
			fnstsw ax	; copy flags to AX
			and ax, 0x4100	; gen C0 and C3 flags (8th bit - C0 = compare less than - carry flag; 14th bit - C3 = compare equal - zero flag)
			jnz _less_eq
			mov al, 0
			jmp _continue
_less_eq:	mov al, 1
_continue:	mov ebx, z
			mov byte ptr [ebx], al
		}
	}
	else
		throw new std::exception("JITCompiler::gen_lte: invalid data type!");
	//
	return 0;
}

int JITCompiler::gen_gte(char *code, Variable *dest, const Variable *op1, const Variable *op2)
{
	void *x = op1->getAddress(), *y = op2->getAddress(), *z = dest->getAddress(), *zf = &ZF;
	if(op1->getItemType() == DataType::INTEGER)
	{
		__asm
		{
			; z = x >= y
			mov ebx, y
			mov ebx, [ebx]
			mov eax, x
			mov eax, [eax]
			cmp eax, ebx
			jge _gte
			mov al, 0
			jmp _cont
_gte:		mov al, 1
_cont:		mov ebx, z
			mov byte ptr [ebx], al
		}
	}
	else if(op1->getItemType() == DataType::DOUBLE)
	{
		__asm
		{
			; z = x >= y
			mov eax, y
			fld qword ptr [eax]
			mov eax, x
			fld qword ptr [eax]
			fcompp
			fnstsw ax	; copy flags to AX
			and ax, 256	; gen C0 and C3 flags (8th bit - C0 = compare less than - carry flag)
			jz _great_eq
			mov al, 0
			jmp _continue
_great_eq:	mov al, 1
_continue:	mov ebx, z
			mov byte ptr [ebx], al
		}
	}
	else
		throw new std::exception("JITCompiler::gen_gte: invalid data type!");
	//
	return 0;
}

int JITCompiler::gen_eq(char *code, Variable *dest, const Variable *op1, const Variable *op2)
{
	void *x = op1->getAddress(), *y = op2->getAddress(), *z = dest->getAddress(), *zf = &ZF;
	if(op1->getItemType() == DataType::BOOLEAN)
	{
		__asm
		{
			; z = x == y
			mov ebx, y
			mov bl, byte ptr [ebx]
			mov eax, x
			mov al, byte ptr [eax]
			cmp al, bl
			je _eq_b
			mov al, 0
			jmp _cont_b
_eq_b:		mov al, 1
_cont_b:	mov ebx, z
			mov byte ptr [ebx], al
		}
	}
	else if(op1->getItemType() == DataType::INTEGER)
	{
		__asm
		{
			; z = x == y
			mov ebx, y
			mov ebx, [ebx]
			mov eax, x
			mov eax, [eax]
			cmp eax, ebx
			je _eq_i
			mov al, 0
			jmp _cont_i
_eq_i:		mov al, 1
_cont_i:	mov ebx, z
			mov byte ptr [ebx], al
		}
	}
	else if(op1->getItemType() == DataType::DOUBLE)
	{
		__asm
		{
			; z = x == y
			mov eax, y
			fld qword ptr [eax]
			mov eax, x
			fld qword ptr [eax]
			fcompp
			fnstsw ax	; copy flags to AX
			and ax, 0x4000	; gen C3 flag (14th bit - C3 = compare equal - zero flag)
			jnz _eq_d
			mov al, 0
			jmp _cont_d
_eq_d:		mov al, 1
_cont_d:	mov ebx, z
			mov byte ptr [ebx], al
		}
	}
	else
		throw new std::exception("JITCompiler::gen_eq: invalid data type!");
	//
	return 0;
}

int JITCompiler::gen_neq(char *code, Variable *dest, const Variable *op1, const Variable *op2)
{
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
		throw new std::exception("JITCompiler::gen_neq: invalid data type!");
	//
	return 0;
}