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

void * popAndGetTopValAddr(int type)
{
	return Interpreter::memory->popAndGetTopValAddr(type);
}

void pushVal(Argument *val, DataType *type)
{
	Interpreter::memory->push(val, type);
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

int JITCompiler::gen_pop(char *code, Variable *dest)
{
	if(dest->getItemType() == DataType::BOOLEAN)
	{
		const int code_len = 25;
		const char *precompiled = "\xB8????"		//mov eax, value(var->item_type)
								  "\x50"			//push eax
								  "\xB8????"		//mov eax, address(popAndGetTopValAddr)
								  "\xFF\xD0"		//call eax
								  "\x83\xC4\x04"	//add esi, 4	; pop function argument
								  "\x8A\x00"		//mov al, byte ptr [eax]
								  "\xBB????"		//mov ebx, address(dest)
								  "\x88\x03";		//mov byte ptr [ebx], al	; save into dest
		memcpy(code, precompiled, code_len);
		(*((int *)(code+1))) = dest->getItemType();
		(*((void **)(code+7))) = popAndGetTopValAddr;
		(*((void **)(code+19))) = dest->getAddress();
		return code_len;
	}
	else if(dest->getItemType() == DataType::DOUBLE)
	{
		const int code_len = 25;
		const char *precompiled = "\xB8????"		//mov eax, value(var->item_type)
								  "\x50"			//push eax
								  "\xB8????"		//mov eax, address(popAndGetTopValAddr)
								  "\xFF\xD0"		//call eax
								  "\x83\xC4\x04"	//add esi, 4	; pop function argument
								  "\xDD\x00"		//fld qword ptr[eax]
								  "\xB8????"		//mov eax, address(dest)
								  "\xDD\x18";		//fstp qword ptr [eax]	; save into dest
		memcpy(code, precompiled, code_len);
		(*((int *)(code+1))) = dest->getItemType();
		(*((void **)(code+7))) = popAndGetTopValAddr;
		(*((void **)(code+19))) = dest->getAddress();
		return code_len;
	}
	else	// 4B -- INTEGER, REFERENCE, ARRAY, STRUCTURE
	{
		const int code_len = 25;
		const char *precompiled = "\xB8????"		//mov eax, value(var->item_type)
								  "\x50"			//push eax
								  "\xB8????"		//mov eax, address(popAndGetTopValAddr)
								  "\xFF\xD0"		//call eax
								  "\x83\xC4\x04"	//add esi, 4	; pop function argument
								  "\x8B\x00"		//mov eax, [eax]
								  "\xBB????"		//mov ebx, address(dest)
								  "\x89\x03";		//mov [ebx], eax	; save into dest
		memcpy(code, precompiled, code_len);
		(*((int *)(code+1))) = dest->getItemType();
		(*((void **)(code+7))) = popAndGetTopValAddr;
		(*((void **)(code+19))) = dest->getAddress();
		return code_len;
	}
}

int JITCompiler::gen_invoke(char *code, Variable *name, const vector<Argument *> &args)
{
	MemoryManager *memory = Interpreter::memory;
	//
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
		const int code_len = 30;
		const char *precompiled = "\xB8????"			//mov eax, args[1] (String *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, address(BuiltInRoutines::openRFile)
								  "\xFF\xD0"			//call eax
								  "\xBB\x00\x00\x00\x00"//mov ebx, 0	; type = NULL
								  "\x53"				//push ebx
								  "\x50"				//push eax		; val = return value from openRFile (Argument *)
								  "\xB8????"			//mov eax, address(pushVal)
								  "\xFF\xD0"			//call eax		; pushVal(val, type);
								  "\x83\xC4\x0C";		//add esp, 12	; pop functions arguments from both openRFile and pushVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+7))) = BuiltInRoutines::openRFile;
		(*((void **)(code+21))) = pushVal;
		return code_len;
	}
	else if(name->getName() == "openWFile")
	{
		const int code_len = 36;
		const char *precompiled = "\xB8????"			//mov eax, args[2] (Boolean *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, args[1] (String *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, address(BuiltInRoutines::openWFile)
								  "\xFF\xD0"			//call eax
								  "\xBB\x00\x00\x00\x00"//mov ebx, 0	; type = NULL
								  "\x53"				//push ebx
								  "\x50"				//push eax		; val = return value from openWFile (Argument *)
								  "\xB8????"			//mov eax, address(pushVal)
								  "\xFF\xD0"			//call eax		; pushVal(val, type);
								  "\x83\xC4\x10";		//add esp, 16	; pop functions arguments from both openWFile and pushVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = ((Variable *)(args[2]))->getValue();
		(*((void **)(code+7))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+13))) = BuiltInRoutines::openWFile;
		(*((void **)(code+27))) = pushVal;
		return code_len;
	}
	else if(name->getName() == "closeFile")
	{
		const int code_len = 30;
		const char *precompiled = "\xB8????"			//mov eax, args[1] (File *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, address(BuiltInRoutines::closeFile)
								  "\xFF\xD0"			//call eax
								  "\xBB\x00\x00\x00\x00"//mov ebx, 0	; type = NULL
								  "\x53"				//push ebx
								  "\x50"				//push eax		; val = return value from closeFile (Argument *)
								  "\xB8????"			//mov eax, address(pushVal)
								  "\xFF\xD0"			//call eax		; pushVal(val, type);
								  "\x83\xC4\x0C";		//add esp, 12	; pop functions arguments from both closeFile and pushVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+7))) = BuiltInRoutines::closeFile;
		(*((void **)(code+21))) = pushVal;
		return code_len;
	}
	else if(name->getName() == "flushFile")
	{
		const int code_len = 30;
		const char *precompiled = "\xB8????"			//mov eax, args[1] (File *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, address(BuiltInRoutines::flushFile)
								  "\xFF\xD0"			//call eax
								  "\xBB\x00\x00\x00\x00"//mov ebx, 0	; type = NULL
								  "\x53"				//push ebx
								  "\x50"				//push eax		; val = return value from flushFile (Argument *)
								  "\xB8????"			//mov eax, address(pushVal)
								  "\xFF\xD0"			//call eax		; pushVal(val, type);
								  "\x83\xC4\x0C";		//add esp, 12	; pop functions arguments from both flushFile and pushVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+7))) = BuiltInRoutines::flushFile;
		(*((void **)(code+21))) = pushVal;
		return code_len;
	}
	else if(name->getName() == "printlnFile")
	{
		const int code_len = 36;
		const char *precompiled = "\xB8????"			//mov eax, args[2] (String *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, args[1] (File *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, address(BuiltInRoutines::printlnFile)
								  "\xFF\xD0"			//call eax
								  "\xBB\x00\x00\x00\x00"//mov ebx, 0	; type = NULL
								  "\x53"				//push ebx
								  "\x50"				//push eax		; val = return value from printlnFile (Argument *)
								  "\xB8????"			//mov eax, address(pushVal)
								  "\xFF\xD0"			//call eax		; pushVal(val, type);
								  "\x83\xC4\x10";		//add esp, 16	; pop functions arguments from both printlnFile and pushVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = ((Variable *)(args[2]))->getValue();
		(*((void **)(code+7))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+13))) = BuiltInRoutines::printlnFile;
		(*((void **)(code+27))) = pushVal;
		return code_len;
	}
	else if(name->getName() == "printFile")
	{
		const int code_len = 36;
		const char *precompiled = "\xB8????"			//mov eax, args[2] (String *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, args[1] (File *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, address(BuiltInRoutines::printFile)
								  "\xFF\xD0"			//call eax
								  "\xBB\x00\x00\x00\x00"//mov ebx, 0	; type = NULL
								  "\x53"				//push ebx
								  "\x50"				//push eax		; val = return value from printFile (Argument *)
								  "\xB8????"			//mov eax, address(pushVal)
								  "\xFF\xD0"			//call eax		; pushVal(val, type);
								  "\x83\xC4\x10";		//add esp, 16	; pop functions arguments from both printFile and pushVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = ((Variable *)(args[2]))->getValue();
		(*((void **)(code+7))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+13))) = BuiltInRoutines::printFile;
		(*((void **)(code+27))) = pushVal;
		return code_len;
	}
	else if(name->getName() == "println")
	{
		const int code_len = 30;
		const char *precompiled = "\xB8????"			//mov eax, args[1] (String *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, address(BuiltInRoutines::println)
								  "\xFF\xD0"			//call eax
								  "\xBB\x00\x00\x00\x00"//mov ebx, 0	; type = NULL
								  "\x53"				//push ebx
								  "\x50"				//push eax		; val = return value from print (Argument *)
								  "\xB8????"			//mov eax, address(pushVal)
								  "\xFF\xD0"			//call eax		; pushVal(val, type);
								  "\x83\xC4\x0C";		//add esp, 12	; pop functions arguments from both println and pushVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+7))) = BuiltInRoutines::println;
		(*((void **)(code+21))) = pushVal;
		return code_len;
	}
	else if(name->getName() == "print")
	{
		const int code_len = 30;
		const char *precompiled = "\xB8????"			//mov eax, args[1] (String *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, address(BuiltInRoutines::print)
								  "\xFF\xD0"			//call eax
								  "\xBB\x00\x00\x00\x00"//mov ebx, 0	; type = NULL
								  "\x53"				//push ebx
								  "\x50"				//push eax		; val = return value from print (Argument *)
								  "\xB8????"			//mov eax, address(pushVal)
								  "\xFF\xD0"			//call eax		; pushVal(val, type);
								  "\x83\xC4\x0C";		//add esp, 12	; pop functions arguments from both print and pushVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+7))) = BuiltInRoutines::print;
		(*((void **)(code+21))) = pushVal;
		return code_len;
	}
	else if(name->getName() == "inputFile")
	{
		const int code_len = 36;
		const char *precompiled = "\xB8????"			//mov eax, args[2] (File *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, args[1] (MemoryManager *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, address(BuiltInRoutines::inputFile)
								  "\xFF\xD0"			//call eax
								  "\xBB\x00\x00\x00\x00"//mov ebx, 0	; type = NULL
								  "\x53"				//push ebx
								  "\x50"				//push eax		; val = return value from inputFile (Argument *)
								  "\xB8????"			//mov eax, address(pushVal)
								  "\xFF\xD0"			//call eax		; pushVal(val, type);
								  "\x83\xC4\x10";		//add esp, 16	; pop functions arguments from both inputFile and pushVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = Interpreter::memory;
		(*((void **)(code+7))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+13))) = BuiltInRoutines::pow;
		(*((void **)(code+27))) = pushVal;
	}
	else if(name->getName() == "input")
	{
		const int code_len = 30;
		const char *precompiled = "\xB8????"			//mov eax, args[1] (MemoryManager *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, address(BuiltInRoutines::input)
								  "\xFF\xD0"			//call eax
								  "\xBB\x00\x00\x00\x00"//mov ebx, 0	; type = NULL
								  "\x53"				//push ebx
								  "\x50"				//push eax		; val = return value (Argument *)
								  "\xB8????"			//mov eax, address(pushVal)
								  "\xFF\xD0"			//call eax		; pushVal(val, type);
								  "\x83\xC4\x0C";		//add esp, 12	; pop functions arguments from both input and pushVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = Interpreter::memory;
		(*((void **)(code+7))) = BuiltInRoutines::input;
		(*((void **)(code+21))) = pushVal;
		return code_len;
	}
	else if(name->getName() == "eof")
	{
		const int code_len = 30;
		const char *precompiled = "\xB8????"			//mov eax, args[1] (File *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, address(BuiltInRoutines::eof)
								  "\xFF\xD0"			//call eax
								  "\xBB\x00\x00\x00\x00"//mov ebx, 0	; type = NULL
								  "\x53"				//push ebx
								  "\x50"				//push eax		; val = return value from eof (Argument *)
								  "\xB8????"			//mov eax, address(pushVal)
								  "\xFF\xD0"			//call eax		; pushVal(val, type);
								  "\x83\xC4\x0C";		//add esp, 12	; pop functions arguments from both eof and pushVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+7))) = BuiltInRoutines::eof;
		(*((void **)(code+21))) = pushVal;
		return code_len;
	}
	else if(name->getName() == "eoi")
	{
		const int code_len = 24;
		const char *precompiled = "\xB8????"			//mov eax, address(BuiltInRoutines::eoi)
								  "\xFF\xD0"			//call eax
								  "\xBB\x00\x00\x00\x00"//mov ebx, 0	; type = NULL
								  "\x53"				//push ebx
								  "\x50"				//push eax		; val = return value from eoi (Argument *)
								  "\xB8????"			//mov eax, address(pushVal)
								  "\xFF\xD0"			//call eax		; pushVal(val, type);
								  "\x83\xC4\x0C";		//add esp, 12	; pop functions arguments from both eoi and pushVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = BuiltInRoutines::eoi;
		(*((void **)(code+15))) = pushVal;
		return code_len;
	}
	else if(name->getName() == "pow")
	{
		const int code_len = 36;
		const char *precompiled = "\xB8????"			//mov eax, args[2] (Double *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, args[1] (Double *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, address(BuiltInRoutines::pow)
								  "\xFF\xD0"			//call eax
								  "\xBB\x00\x00\x00\x00"//mov ebx, 0	; type = NULL
								  "\x53"				//push ebx
								  "\x50"				//push eax		; val = return value from pow (Argument *)
								  "\xB8????"			//mov eax, address(pushVal)
								  "\xFF\xD0"			//call eax		; pushVal(val, type);
								  "\x83\xC4\x10";		//add esp, 16	; pop functions arguments from both pow and pushVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = ((Variable *)(args[2]))->getValue();
		(*((void **)(code+7))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+13))) = BuiltInRoutines::pow;
		(*((void **)(code+27))) = pushVal;
		return code_len;
	}
	else if(name->getName() == "sqrt")
	{
		const int code_len = 30;
		const char *precompiled = "\xB8????"			//mov eax, args[1] (Double *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, address(BuiltInRoutines::sqrt)
								  "\xFF\xD0"			//call eax
								  "\xBB\x00\x00\x00\x00"//mov ebx, 0	; type = NULL
								  "\x53"				//push ebx
								  "\x50"				//push eax		; val = return value from sqrt (Argument *)
								  "\xB8????"			//mov eax, address(pushVal)
								  "\xFF\xD0"			//call eax		; pushVal(val, type);
								  "\x83\xC4\x0C";		//add esp, 12	; pop functions arguments from both sqrt and pushVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+7))) = BuiltInRoutines::sqrt;
		(*((void **)(code+21))) = pushVal;
		return code_len;
	}
	else if(name->getName() == "log")
	{
		const int code_len = 30;
		const char *precompiled = "\xB8????"			//mov eax, args[1] (Double *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, address(BuiltInRoutines::log)
								  "\xFF\xD0"			//call eax
								  "\xBB\x00\x00\x00\x00"//mov ebx, 0	; type = NULL
								  "\x53"				//push ebx
								  "\x50"				//push eax		; val = return value from log (Argument *)
								  "\xB8????"			//mov eax, address(pushVal)
								  "\xFF\xD0"			//call eax		; pushVal(val, type);
								  "\x83\xC4\x0C";		//add esp, 12	; pop functions arguments from both log and pushVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+7))) = BuiltInRoutines::log;
		(*((void **)(code+21))) = pushVal;
		return code_len;
	}
	else if(name->getName() == "rand")
	{
		const int code_len = 30;
		const char *precompiled = "\xB8????"			//mov eax, args[1] (Integer *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, address(BuiltInRoutines::rand)
								  "\xFF\xD0"			//call eax
								  "\xBB\x00\x00\x00\x00"//mov ebx, 0	; type = NULL
								  "\x53"				//push ebx
								  "\x50"				//push eax		; val = return value from rand (Argument *)
								  "\xB8????"			//mov eax, address(pushVal)
								  "\xFF\xD0"			//call eax		; pushVal(val, type);
								  "\x83\xC4\x0C";		//add esp, 12	; pop functions arguments from both rand and pushVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+7))) = BuiltInRoutines::rand;
		(*((void **)(code+21))) = pushVal;
		return code_len;
	}
	else if(name->getName() == "indexOf")
	{
		const int code_len = 36;
		const char *precompiled = "\xB8????"			//mov eax, args[2] (String *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, args[1] (String *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, address(BuiltInRoutines::indexOf)
								  "\xFF\xD0"			//call eax
								  "\xBB\x00\x00\x00\x00"//mov ebx, 0	; type = NULL
								  "\x53"				//push ebx
								  "\x50"				//push eax		; val = return value from indexOf (Argument *)
								  "\xB8????"			//mov eax, address(pushVal)
								  "\xFF\xD0"			//call eax		; pushVal(val, type);
								  "\x83\xC4\x10";		//add esp, 16	; pop functions arguments from both indexOf and pushVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = ((Variable *)(args[2]))->getValue();
		(*((void **)(code+7))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+13))) = BuiltInRoutines::indexOf;
		(*((void **)(code+27))) = pushVal;
		return code_len;
	}
	else if(name->getName() == "lastIndexOf")
	{
		const int code_len = 36;
		const char *precompiled = "\xB8????"			//mov eax, args[2] (String *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, args[1] (String *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, address(BuiltInRoutines::lastIndexOf)
								  "\xFF\xD0"			//call eax
								  "\xBB\x00\x00\x00\x00"//mov ebx, 0	; type = NULL
								  "\x53"				//push ebx
								  "\x50"				//push eax		; val = return value from lastIndexOf (Argument *)
								  "\xB8????"			//mov eax, address(pushVal)
								  "\xFF\xD0"			//call eax		; pushVal(val, type);
								  "\x83\xC4\x10";		//add esp, 16	; pop functions arguments from both lastIndexOf and pushVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = ((Variable *)(args[2]))->getValue();
		(*((void **)(code+7))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+13))) = BuiltInRoutines::lastIndexOf;
		(*((void **)(code+27))) = pushVal;
		return code_len;
	}
	else if(name->getName() == "substring")
	{
		const int code_len = 48;
		const char *precompiled = "\xB8????"			//mov eax, args[3] (String *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, args[2] (String *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, args[1] (String *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, (MemoryManager *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, address(BuiltInRoutines::substring)
								  "\xFF\xD0"			//call eax
								  "\xBB\x00\x00\x00\x00"//mov ebx, 0	; type = NULL
								  "\x53"				//push ebx
								  "\x50"				//push eax		; val = return value from substring (Argument *)
								  "\xB8????"			//mov eax, address(pushVal)
								  "\xFF\xD0"			//call eax		; pushVal(val, type);
								  "\x83\xC4\x18";		//add esp, 24	; pop functions arguments from both substring and pushVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = ((Variable *)(args[3]))->getValue();
		(*((void **)(code+7))) = ((Variable *)(args[2]))->getValue();
		(*((void **)(code+13))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+19))) = Interpreter::memory;
		(*((void **)(code+25))) = BuiltInRoutines::substring;
		(*((void **)(code+39))) = pushVal;
		return code_len;
	}
	else if(name->getName() == "toLower")
	{
		const int code_len = 30;
		const char *precompiled = "\xB8????"			//mov eax, args[1] (String *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, address(BuiltInRoutines::toLower)
								  "\xFF\xD0"			//call eax
								  "\xBB\x00\x00\x00\x00"//mov ebx, 0	; type = NULL
								  "\x53"				//push ebx
								  "\x50"				//push eax		; val = return value from toLower (Argument *)
								  "\xB8????"			//mov eax, address(pushVal)
								  "\xFF\xD0"			//call eax		; pushVal(val, type);
								  "\x83\xC4\x0C";		//add esp, 12	; pop functions arguments from both toLower and pushVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+7))) = BuiltInRoutines::toLower;
		(*((void **)(code+21))) = pushVal;
		return code_len;
	}
	else if(name->getName() == "toUpper")
	{
		const int code_len = 30;
		const char *precompiled = "\xB8????"			//mov eax, args[1] (String *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, address(BuiltInRoutines::toUpper)
								  "\xFF\xD0"			//call eax
								  "\xBB\x00\x00\x00\x00"//mov ebx, 0	; type = NULL
								  "\x53"				//push ebx
								  "\x50"				//push eax		; val = return value from toUpper (Argument *)
								  "\xB8????"			//mov eax, address(pushVal)
								  "\xFF\xD0"			//call eax		; pushVal(val, type);
								  "\x83\xC4\x0C";		//add esp, 12	; pop functions arguments from both toUpper and pushVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+7))) = BuiltInRoutines::toUpper;
		(*((void **)(code+21))) = pushVal;
		return code_len;
	}
	else if(name->getName() == "trim")
	{
		const int code_len = 30;
		const char *precompiled = "\xB8????"			//mov eax, args[1] (String *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, address(BuiltInRoutines::trim)
								  "\xFF\xD0"			//call eax
								  "\xBB\x00\x00\x00\x00"//mov ebx, 0	; type = NULL
								  "\x53"				//push ebx
								  "\x50"				//push eax		; val = return value from trim (Argument *)
								  "\xB8????"			//mov eax, address(pushVal)
								  "\xFF\xD0"			//call eax		; pushVal(val, type);
								  "\x83\xC4\x0C";		//add esp, 12	; pop functions arguments from both trim and pushVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+7))) = BuiltInRoutines::trim;
		(*((void **)(code+21))) = pushVal;
		return code_len;
	}
	else if(name->getName() == "startsWith")
	{
		const int code_len = 36;
		const char *precompiled = "\xB8????"			//mov eax, args[2] (String *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, args[1] (String *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, address(BuiltInRoutines::startsWith)
								  "\xFF\xD0"			//call eax
								  "\xBB\x00\x00\x00\x00"//mov ebx, 0	; type = NULL
								  "\x53"				//push ebx
								  "\x50"				//push eax		; val = return value from startsWith (Argument *)
								  "\xB8????"			//mov eax, address(pushVal)
								  "\xFF\xD0"			//call eax		; pushVal(val, type);
								  "\x83\xC4\x10";		//add esp, 16	; pop functions arguments from both startsWith and pushVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = ((Variable *)(args[2]))->getValue();
		(*((void **)(code+7))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+13))) = BuiltInRoutines::startsWith;
		(*((void **)(code+27))) = pushVal;
		return code_len;
	}
	else if(name->getName() == "endsWith")
	{
		const int code_len = 36;
		const char *precompiled = "\xB8????"			//mov eax, args[2] (String *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, args[1] (String *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, address(BuiltInRoutines::endsWith)
								  "\xFF\xD0"			//call eax
								  "\xBB\x00\x00\x00\x00"//mov ebx, 0	; type = NULL
								  "\x53"				//push ebx
								  "\x50"				//push eax		; val = return value from endsWith (Argument *)
								  "\xB8????"			//mov eax, address(pushVal)
								  "\xFF\xD0"			//call eax		; pushVal(val, type);
								  "\x83\xC4\x10";		//add esp, 16	; pop functions arguments from both endsWith and pushVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = ((Variable *)(args[2]))->getValue();
		(*((void **)(code+7))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+13))) = BuiltInRoutines::endsWith;
		(*((void **)(code+27))) = pushVal;
		return code_len;
	}
	else if(name->getName() == "concat")
	{
		const int code_len = 42;
		const char *precompiled = "\xB8????"			//mov eax, args[2] (String *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, args[1] (String *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, (MemoryManager *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, address(BuiltInRoutines::concat)
								  "\xFF\xD0"			//call eax
								  "\xBB\x00\x00\x00\x00"//mov ebx, 0	; type = NULL
								  "\x53"				//push ebx
								  "\x50"				//push eax		; val = return value from concat (Argument *)
								  "\xB8????"			//mov eax, address(pushVal)
								  "\xFF\xD0"			//call eax		; pushVal(val, type);
								  "\x83\xC4\x14";		//add esp, 20	; pop functions arguments from both concat and pushVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = ((Variable *)(args[2]))->getValue();
		(*((void **)(code+7))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+13))) = Interpreter::memory;
		(*((void **)(code+19))) = BuiltInRoutines::concat;
		(*((void **)(code+33))) = pushVal;
		return code_len;
	}
	else if(name->getName() == "strlen")
	{
		const int code_len = 30;
		const char *precompiled = "\xB8????"			//mov eax, args[1] (String *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, address(BuiltInRoutines::strlen)
								  "\xFF\xD0"			//call eax
								  "\xBB\x00\x00\x00\x00"//mov ebx, 0	; type = NULL
								  "\x53"				//push ebx
								  "\x50"				//push eax		; val = return value from strlen (Argument *)
								  "\xB8????"			//mov eax, address(pushVal)
								  "\xFF\xD0"			//call eax		; pushVal(val, type);
								  "\x83\xC4\x0C";		//add esp, 12	; pop functions arguments from both strlen and pushVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+7))) = BuiltInRoutines::strlen;
		(*((void **)(code+21))) = pushVal;
		return code_len;
	}
	else if(name->getName() == "int2str")
	{
		const int code_len = 36;
		const char *precompiled = "\xB8????"			//mov eax, args[2] (Integer *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, args[1] (MemoryManager *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, address(BuiltInRoutines::int2str)
								  "\xFF\xD0"			//call eax
								  "\xBB\x00\x00\x00\x00"//mov ebx, 0	; type = NULL
								  "\x53"				//push ebx
								  "\x50"				//push eax		; val = return value from int2str (Argument *)
								  "\xB8????"			//mov eax, address(pushVal)
								  "\xFF\xD0"			//call eax		; pushVal(val, type);
								  "\x83\xC4\x10";		//add esp, 16	; pop functions arguments from both int2str and pushVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = Interpreter::memory;
		(*((void **)(code+7))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+13))) = BuiltInRoutines::int2str;
		(*((void **)(code+27))) = pushVal;
		return code_len;
	}
	else if(name->getName() == "double2str")
	{
		const int code_len = 36;
		const char *precompiled = "\xB8????"			//mov eax, args[2] (Double *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, args[1] (MemoryManager *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, address(BuiltInRoutines::double2str)
								  "\xFF\xD0"			//call eax
								  "\xBB\x00\x00\x00\x00"//mov ebx, 0	; type = NULL
								  "\x53"				//push ebx
								  "\x50"				//push eax		; val = return value from double2str (Argument *)
								  "\xB8????"			//mov eax, address(pushVal)
								  "\xFF\xD0"			//call eax		; pushVal(val, type);
								  "\x83\xC4\x10";		//add esp, 16	; pop functions arguments from both double2str and pushVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = Interpreter::memory;
		(*((void **)(code+7))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+13))) = BuiltInRoutines::double2str;
		(*((void **)(code+27))) = pushVal;
		return code_len;
	}
	else if(name->getName() == "str2int")
	{
		const int code_len = 30;
		const char *precompiled = "\xB8????"			//mov eax, args[1] (String *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, address(BuiltInRoutines::str2int)
								  "\xFF\xD0"			//call eax
								  "\xBB\x00\x00\x00\x00"//mov ebx, 0	; type = NULL
								  "\x53"				//push ebx
								  "\x50"				//push eax		; val = return value from str2int (Argument *)
								  "\xB8????"			//mov eax, address(pushVal)
								  "\xFF\xD0"			//call eax		; pushVal(val, type);
								  "\x83\xC4\x0C";		//add esp, 12	; pop functions arguments from both str2int and pushVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+7))) = BuiltInRoutines::str2int;
		(*((void **)(code+21))) = pushVal;
		return code_len;
	}
	else if(name->getName() == "double2int")
	{
		const int code_len = 30;
		const char *precompiled = "\xB8????"			//mov eax, args[1] (Double *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, address(BuiltInRoutines::double2int)
								  "\xFF\xD0"			//call eax
								  "\xBB\x00\x00\x00\x00"//mov ebx, 0	; type = NULL
								  "\x53"				//push ebx
								  "\x50"				//push eax		; val = return value from double2int (Argument *)
								  "\xB8????"			//mov eax, address(pushVal)
								  "\xFF\xD0"			//call eax		; pushVal(val, type);
								  "\x83\xC4\x0C";		//add esp, 12	; pop functions arguments from both double2int and pushVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+7))) = BuiltInRoutines::double2int;
		(*((void **)(code+21))) = pushVal;
		return code_len;
	}
	else if(name->getName() == "str2double")
	{
		const int code_len = 30;
		const char *precompiled = "\xB8????"			//mov eax, args[1] (String *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, address(BuiltInRoutines::str2double)
								  "\xFF\xD0"			//call eax
								  "\xBB\x00\x00\x00\x00"//mov ebx, 0	; type = NULL
								  "\x53"				//push ebx
								  "\x50"				//push eax		; val = return value from str2double (Argument *)
								  "\xB8????"			//mov eax, address(pushVal)
								  "\xFF\xD0"			//call eax		; pushVal(val, type);
								  "\x83\xC4\x0C";		//add esp, 12	; pop functions arguments from both str2double and pushVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+7))) = BuiltInRoutines::str2double;
		(*((void **)(code+21))) = pushVal;
		return code_len;
	}
	else if(name->getName() == "int2double")
	{
		const int code_len = 30;
		const char *precompiled = "\xB8????"			//mov eax, args[1] (Integer *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, address(BuiltInRoutines::int2double)
								  "\xFF\xD0"			//call eax
								  "\xBB\x00\x00\x00\x00"//mov ebx, 0	; type = NULL
								  "\x53"				//push ebx
								  "\x50"				//push eax		; val = return value from int2double (Argument *)
								  "\xB8????"			//mov eax, address(pushVal)
								  "\xFF\xD0"			//call eax		; pushVal(val, type);
								  "\x83\xC4\x0C";		//add esp, 12	; pop functions arguments from both int2double and pushVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+7))) = BuiltInRoutines::int2double;
		(*((void **)(code+21))) = pushVal;
		return code_len;
	}
	else
		throw new std::exception(("JITCompiler::gen_invoke: routine '" + name->getName() + "' was not found!").c_str());
}

int JITCompiler::gen_jz(char *code, const Integer *to)
{
	const int code_len = 10;
	const char *precompiled = "\xB8????"	//mov eax, address(ZF)
							  "\x80\x38\x01"//cmp byte ptr [eax], 1
							  "\x74?";		//je value(to)
	memcpy(code, precompiled, code_len);
	(*((void **)(code+1))) = pZF;
	(*((int *)(code+9))) = (char)(to->getValue());
	hlp_jump_loc.push_back(code+9);
	return code_len;
}

int JITCompiler::gen_jnz(char *code, const Integer *to)
{
	const int code_len = 10;
	const char *precompiled = "\xB8????"	//mov eax, address(ZF)
							  "\x80\x38\x00"//cmp byte ptr [eax], 0
							  "\x75?";		//jne value(to)
	memcpy(code, precompiled, code_len);
	(*((void **)(code+1))) = pZF;
	(*((int *)(code+9))) = (char)(to->getValue());
	hlp_jump_loc.push_back(code+9);
	return code_len;
}

int JITCompiler::gen_jmp(char *code, const Integer *to)
{
	const int code_len = 2;
	const char *precompiled = "\xEB?";	//jmp value(to)
	memcpy(code, precompiled, code_len);
	(*((int *)(code+1))) = (char)(to->getValue());
	hlp_jump_loc.push_back(code+1);
	return code_len;
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
	// TODO: instead of pushf use lahf (Load Flags into AH Register - 1Byte)
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
		const int code_len = 27;
		const char *precompiled = "\xB8????"	//mov eax, address(src)
								  "\x8A\x00"	//mov al, byte ptr [eax]
								  "\xF6\xD0"	//not al
								  "\x24\x01"	//and al, 1
								  "\xBB????"	//mov ebx, address(dest)
								  "\x88\x03"	//mov byte ptr [ebx],al
								  // save ZF
								  "\x34\x01"	//			xor al,1	; negate LSB (al is 1 if comparison was successful - for ZF, I need the exact opposite)
								  "\xBB????"	//			mov ebx, address(ZF)
								  "\x88\x03";	//			mov byte ptr [ebx], al
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = src->getAddress();
		(*((void **)(code+12))) = dest->getAddress();
		(*((void **)(code+21))) = pZF;
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
	if(var->getItemType() == DataType::ARRAY)
	{
		const int code_len = 45;
		const char *precompiled = // ; x = heapAlloc(sizeof(var))
								  "\xB8????"		//mov eax, value(var->item_size)
								  "\xB9????"		//mov ecx, address(size)
								  "\x8B\x09"		//mov ecx, [ecx]
								  "\xF7\xE1"		//mul ecx
								  "\x83\xC0\x08"	//add eax, 8
								  "\x50"			//push eax
								  "\xB8????"		//mov eax, address(heapAlloc)
								  "\xFF\xD0"		//call eax
								  "\x83\xC6\x04"	//add esi, 4	; pop function argument
								  "\xBB????"		//mov ebx, address(var)
								  "\x89\x03"		//mov [ebx], eax	; save ptr into var
								  // ; x->length = len
								  // ; x->item_type = item_type
								  "\x89\x08"		//mov [eax], ecx
								  "\xBB????"		//mov ebx, value(var->item_type)
								  "\x89\x58\x04";	//mov [eax+4], ebx
		memcpy(code, precompiled, code_len);
		(*((int *)(code+1))) = var->getItemTypeSize();
		(*((void **)(code+6))) = size->getAddress();
		(*((void **)(code+19))) = heapAlloc;
		(*((void **)(code+29))) = var->getAddress();
		(*((int *)(code+38))) = var->getItemDataType()->subtype->type;
		return code_len;
	}
	else	// structure
	{
		const int code_len = 45;
		const char *precompiled = "\xB8????"		//mov eax, value(var->item_size)
								  "\x50"			//push eax
								  "\xB8????"		//mov eax, address(heapAlloc)
								  "\xFF\xD0"		//call eax
								  "\x83\xC6\x04"	//add esi, 4	; pop function argument
								  "\xBB????"		//mov ebx, address(var)
								  "\x89\x03";		//mov [ebx], eax	; save ptr into var
		memcpy(code, precompiled, code_len);
		(*((int *)(code+1))) = var->getItemTypeSize();
		(*((void **)(code+7))) = heapAlloc;
		(*((void **)(code+17))) = var->getAddress();
		return code_len;
	}
}

int JITCompiler::gen_lt(char *code, Variable *dest, const Variable *op1, const Variable *op2)
{
	if(op1->getItemType() == DataType::INTEGER)
	{
		const int code_len = 40;
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
								  "\x88\x03"	//		mov byte ptr [ebx], al
								  // save ZF
								  "\x34\x01"	//		xor al,1	; negate LSB (al is 1 if comparison was successful - for ZF, I need the exact opposite)
								  "\xBB????"	//		mov ebx, address(ZF)
								  "\x88\x03";	//		mov byte ptr [ebx], al
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = op2->getAddress();
		(*((void **)(code+8))) = op1->getAddress();
		(*((void **)(code+25))) = dest->getAddress();
		(*((void **)(code+34))) = pZF;
		return code_len;
	}
	else if(op1->getItemType() == DataType::DOUBLE)
	{
		const int code_len = 46;
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
								  "\x88\x03"			//				mov byte ptr [ebx], al
								  // save ZF
								  "\x34\x01"			//				xor al,1	; negate LSB (al is 1 if comparison was successful - for ZF, I need the exact opposite)
								  "\xBB????"			//				mov ebx, address(ZF)
								  "\x88\x03";			//				mov byte ptr [ebx], al
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = op2->getAddress();
		(*((void **)(code+8))) = op1->getAddress();
		(*((void **)(code+31))) = dest->getAddress();
		(*((void **)(code+40))) = pZF;
		return code_len;
	}
	else
		throw new std::exception("JITCompiler::gen_lt: invalid data type!");
}

int JITCompiler::gen_gt(char *code, Variable *dest, const Variable *op1, const Variable *op2)
{
	if(op1->getItemType() == DataType::INTEGER)
	{
		const int code_len = 40;
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
								  "\x88\x03"	//		mov byte ptr [ebx], al
								  // save ZF
								  "\x34\x01"	//		xor al,1	; negate LSB (al is 1 if comparison was successful - for ZF, I need the exact opposite)
								  "\xBB????"	//		mov ebx, address(ZF)
								  "\x88\x03";	//		mov byte ptr [ebx], al
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = op2->getAddress();
		(*((void **)(code+8))) = op1->getAddress();
		(*((void **)(code+25))) = dest->getAddress();
		(*((void **)(code+34))) = pZF;
		return code_len;
	}
	else if(op1->getItemType() == DataType::DOUBLE)
	{
		const int code_len = 46;
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
								  "\x88\x03"			//				mov byte ptr [ebx], al
								  // save ZF
								  "\x34\x01"			//				xor al,1	; negate LSB (al is 1 if comparison was successful - for ZF, I need the exact opposite)
								  "\xBB????"			//				mov ebx, address(ZF)
								  "\x88\x03";			//				mov byte ptr [ebx], al
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = op2->getAddress();
		(*((void **)(code+8))) = op1->getAddress();
		(*((void **)(code+31))) = dest->getAddress();
		(*((void **)(code+40))) = pZF;
		return code_len;
	}
	else
		throw new std::exception("JITCompiler::gen_gt: invalid data type!");
}

int JITCompiler::gen_lte(char *code, Variable *dest, const Variable *op1, const Variable *op2)
{
	if(op1->getItemType() == DataType::INTEGER)
	{
		const int code_len = 40;
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
								  "\x88\x03"	//		mov byte ptr [ebx], al
								  // save ZF
								  "\x34\x01"	//		xor al,1	; negate LSB (al is 1 if comparison was successful - for ZF, I need the exact opposite)
								  "\xBB????"	//		mov ebx, address(ZF)
								  "\x88\x03";	//		mov byte ptr [ebx], al
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = op2->getAddress();
		(*((void **)(code+8))) = op1->getAddress();
		(*((void **)(code+25))) = dest->getAddress();
		(*((void **)(code+34))) = pZF;
		return code_len;
	}
	else if(op1->getItemType() == DataType::DOUBLE)
	{
		const int code_len = 46;
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
								  // save ZF
								  "\x34\x01"			//				xor al,1	; negate LSB (al is 1 if comparison was successful - for ZF, I need the exact opposite)
								  "\xBB????"			//				mov ebx, address(ZF)
								  "\x88\x03";			//				mov byte ptr [ebx], al
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = op2->getAddress();
		(*((void **)(code+8))) = op1->getAddress();
		(*((void **)(code+31))) = dest->getAddress();
		(*((void **)(code+40))) = pZF;
		return code_len;
	}
	else
		throw new std::exception("JITCompiler::gen_lte: invalid data type!");
}

int JITCompiler::gen_gte(char *code, Variable *dest, const Variable *op1, const Variable *op2)
{
	if(op1->getItemType() == DataType::INTEGER)
	{
		const int code_len = 40;
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
								  "\x88\x03"	//		mov byte ptr [ebx], al
								  // save ZF
								  "\x34\x01"	//		xor al,1	; negate LSB (al is 1 if comparison was successful - for ZF, I need the exact opposite)
								  "\xBB????"	//		mov ebx, address(ZF)
								  "\x88\x03";	//		mov byte ptr [ebx], al
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = op2->getAddress();
		(*((void **)(code+8))) = op1->getAddress();
		(*((void **)(code+25))) = dest->getAddress();
		(*((void **)(code+34))) = pZF;
		return code_len;
	}
	else if(op1->getItemType() == DataType::DOUBLE)
	{
		const int code_len = 46;
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
								  "\x88\x03"			//				mov byte ptr [ebx], al
								  // save ZF
								  "\x34\x01"			//				xor al,1	; negate LSB (al is 1 if comparison was successful - for ZF, I need the exact opposite)
								  "\xBB????"			//				mov ebx, address(ZF)
								  "\x88\x03";			//				mov byte ptr [ebx], al
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = op2->getAddress();
		(*((void **)(code+8))) = op1->getAddress();
		(*((void **)(code+31))) = dest->getAddress();
		(*((void **)(code+40))) = pZF;
		return code_len;
	}
	else
		throw new std::exception("JITCompiler::gen_gte: invalid data type!");
}

int JITCompiler::gen_eq(char *code, Variable *dest, const Variable *op1, const Variable *op2)
{
	if((op1->getItemType() == DataType::BOOLEAN))
	{
		const int code_len = 40;
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
								  "\x88\x03"	//			mov byte ptr [ebx], al
								  // save ZF
								  "\x34\x01"	//			xor al,1	; negate LSB (al is 1 if comparison was successful - for ZF, I need the exact opposite)
								  "\xBB????"	//			mov ebx, address(ZF)
								  "\x88\x03";	//			mov byte ptr [ebx], al
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = op2->getAddress();
		(*((void **)(code+8))) = op1->getAddress();
		(*((void **)(code+25))) = dest->getAddress();
		(*((void **)(code+34))) = pZF;
		return code_len;
	}
	else if(op1->getItemType() == DataType::INTEGER)
	{
		const int code_len = 40;
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
								  "\x88\x03"	//			mov byte ptr [ebx], al
								  // save ZF
								  "\x34\x01"	//			xor al,1	; negate LSB (al is 1 if comparison was successful - for ZF, I need the exact opposite)
								  "\xBB????"	//			mov ebx, address(ZF)
								  "\x88\x03";	//			mov byte ptr [ebx], al
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = op2->getAddress();
		(*((void **)(code+8))) = op1->getAddress();
		(*((void **)(code+25))) = dest->getAddress();
		(*((void **)(code+34))) = pZF;
		return code_len;
	}
	else if(op1->getItemType() == DataType::DOUBLE)
	{
		const int code_len = 46;
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
								  "\x88\x03"			//			mov byte ptr [ebx], al
								  // save ZF
								  "\x34\x01"			//				xor al,1	; negate LSB (al is 1 if comparison was successful - for ZF, I need the exact opposite)
								  "\xBB????"			//				mov ebx, address(ZF)
								  "\x88\x03";			//				mov byte ptr [ebx], al
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = op2->getAddress();
		(*((void **)(code+8))) = op1->getAddress();
		(*((void **)(code+31))) = dest->getAddress();
		(*((void **)(code+40))) = pZF;
		return code_len;
	}
	else
		throw new std::exception("JITCompiler::gen_eq: invalid data type!");
}

int JITCompiler::gen_neq(char *code, Variable *dest, const Variable *op1, const Variable *op2)
{
	if((op1->getItemType() == DataType::BOOLEAN))
	{
		const int code_len = 40;
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
								  "\x88\x03"	//			mov byte ptr [ebx], al
								  // save ZF
								  "\x34\x01"	//			xor al,1	; negate LSB (al is 1 if comparison was successful - for ZF, I need the exact opposite)
								  "\xBB????"	//			mov ebx, address(ZF)
								  "\x88\x03";	//			mov byte ptr [ebx], al
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = op2->getAddress();
		(*((void **)(code+8))) = op1->getAddress();
		(*((void **)(code+25))) = dest->getAddress();
		(*((void **)(code+34))) = pZF;
		return code_len;
	}
	else if(op1->getItemType() == DataType::INTEGER)
	{
		const int code_len = 40;
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
								  "\x88\x03"	//			mov byte ptr [ebx], al
								  // save ZF
								  "\x34\x01"	//			xor al,1	; negate LSB (al is 1 if comparison was successful - for ZF, I need the exact opposite)
								  "\xBB????"	//			mov ebx, address(ZF)
								  "\x88\x03";	//			mov byte ptr [ebx], al
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = op2->getAddress();
		(*((void **)(code+8))) = op1->getAddress();
		(*((void **)(code+25))) = dest->getAddress();
		(*((void **)(code+34))) = pZF;
		return code_len;
	}
	else if(op1->getItemType() == DataType::DOUBLE)
	{
		const int code_len = 46;
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
								  "\x88\x03"			//			mov byte ptr [ebx], al
								  // save ZF
								  "\x34\x01"			//				xor al,1	; negate LSB (al is 1 if comparison was successful - for ZF, I need the exact opposite)
								  "\xBB????"			//				mov ebx, address(ZF)
								  "\x88\x03";			//				mov byte ptr [ebx], al
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = op2->getAddress();
		(*((void **)(code+8))) = op1->getAddress();
		(*((void **)(code+31))) = dest->getAddress();
		(*((void **)(code+40))) = pZF;
		return code_len;
	}
	else
		throw new std::exception("JITCompiler::gen_neq: invalid data type!");
}