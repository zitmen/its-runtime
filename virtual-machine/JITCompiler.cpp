#include "JITCompiler.h"
#include "Interpreter.h"
#include "JITBuiltInRoutines.h"
#include <cstdarg>

/*

004A4374 E8 16 78 FA FF       call        getVal (44BB8Fh)		// zavolam funkci, ktera vraci double - vraci ho pomoci pushnuti na FPU zasobnik -- fld qword ptr [adresa]
004A4379 DD 5D E0             fstp        qword ptr [ebp-20h]  // ulozi navratovou hodnotu do lokalni promenne
    25: 	setVal(d);	// ted jdu volat funkci, kam double predavam jako parameter
004A437C 83 EC 08             sub         esp,8					// uvolnim misto na zasobniku
004A437F DD 45 E0             fld         qword ptr [ebp-20h]	// loadnu hodnotu z lokalni promenne na FPU zasobnik
004A4382 DD 1C 24             fstp        qword ptr [esp]		// vyzvednu hodnotu z FPU zasobniku a vlozim na ALU zasobnik do uvolneneho prostoru
004A4385 E8 E9 5C FA FF       call        setVal (44A073h)		// funkce si pak hodnotu z klasickeho zasobniku vyzvedne stejne jako z lokalni promenne

*/

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

void pushIntVal(int val, DataType *type)
{
	Interpreter::memory->push(new Integer(val), type);
}

void pushDblVal(double val, DataType *type)
{
	Interpreter::memory->push(new Double(val), type);
}

void pushBoolVal(bool val, DataType *type)
{
	Interpreter::memory->push(new Boolean(val), type);
}

void pushStrVal(char *val, DataType *type)
{
	Interpreter::memory->push(new String(val), type);
}

void pushPtrVal(void *val, DataType *type)
{
	Interpreter::memory->push(new Reference(val), type);
}

void callFn(JITCompiler *jitc, FunctionSignature *fn, int argc, ...)
{
	// 0. push signature to call stack
	jitc->call_stack->push(fn);
	// 1. push IP
	Interpreter::memory->push(new Integer(0));	// this is useles...it's just for compatibility with interpreter's calls
	// 2. push SFB
	Interpreter::memory->push(new Reference(Interpreter::memory->SFB));
	// 3. set new SFB
	Interpreter::memory->SFB = Interpreter::memory->SP;
	// 4. setup the stack - 1st come parameters, 2nd declared variables; set SP
    for(map<string, Variable *>::iterator it = fn->variables.begin(); it != fn->variables.end(); ++it)
		it->second->setAddress(Interpreter::memory->reserve(it->second));
	// 5. set values of arguments on the stack
	va_list args;
	va_start(args, argc);
	void *valPtr;
	for(int i = 0; i < argc; i++)
	{
		valPtr = va_arg(args, void*);
		fn->variables[fn->arguments_ordering[i]]->setValue(valPtr);
	}
	va_end(args);
	//
	// compile the called function first (unless it is a recursive call or it is already compiled)
	if(jitc->compiled_functions[fn->name] == NULL)
		jitc->compile(fn->name);
}

void retFromFn(stack<FunctionSignature *> *call_stack)
{
	// 0. pop signature from call stack
	FunctionSignature *fn = call_stack->top(); call_stack->pop();
	// 1. restore SP to the state before call -- top was at SFB (there is still old SFB and IP - next steps)
	Interpreter::memory->SP = Interpreter::memory->SFB;
	// 2. restore SFB of previous stack fram
	Interpreter::memory->SFB = (*((void **)(Interpreter::memory->popAndGetTopValAddr(DataType::REFERENCE))));
	// 3. restore IP
	int IP = (*((int *)(Interpreter::memory->popAndGetTopValAddr(DataType::INTEGER))));	// this is useles...it's just for compatibility with interpreter's calls
	// 4. restore local variables
	int offset = 0;
	map<string, Variable *>::iterator it = fn->variables.end();
	do	// had to be done this way, because reverse_iterator didn't work :(
	{
		--it;
		offset += DataType::getTypeSize(it->second->getType());
		it->second->setAddress((void *)((char *)(Interpreter::memory->SP) - offset));
	} while(it != fn->variables.begin());
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
{	// i don't need it, at least for now
	return 0;
}

int JITCompiler::gen_epilog(char *code)
{	// i don't need it, at least for now
	return 0;
}

int JITCompiler::gen_call(char *code, FunctionSignature *fn, const vector<Argument *> &args)
{
	const int args_init_len = 5;
	const char *args_init = "\xBB????";		//mov ebx, address(SFB)
	//
	const int args_len = 8;
	const char *args_precompiled = "\xB8????"	//mov eax, offset(args[?]) ; take address(offset), because it is all Variables; no constant there
								   "\x03\x03"	//add eax, [ebx]	; absolute address of args[?]
								   "\x50";		//push eax
	//
	const int code_len = 35;
	const char *precompiled = "\xB8????"	//mov eax, value(args.size()-1) ; 0th argument is name of the called function
							  "\x50"		//push eax
							  "\xB8????"	//mov eax, address(fn)
							  "\x50"		//push eax
							  "\xB8????"	//mov eax, address(this) ; (JITCompiler *)
							  "\x50"		//push eax
							  "\xB8????"	//mov eax, address(callFn)
							  "\xFF\xD0"	//call eax
							  "\xB8????"	//mov eax, address(called_function)	; compiled_functions[fn->name]
							  "\xFF\xD0"	//call eax
							  "\x83\xC4?";	//add esp, ?	; pop all (args.size()-1)+3 arguments
	//
	int len = 0;
	// function arguments
	if(args.size() > 1)
	{
		memcpy(code+len, args_init, args_init_len);
		(*((void **)(code+len+1))) = &(Interpreter::memory->SFB);
		len += args_init_len;
	}
	for(size_t a = 1, am = args.size(); a < am; a++)
	{
		memcpy(code+len, args_precompiled, args_len);
		(*((void **)(code+len+1))) = (void *)((char *)(((Variable *)(args[a]))->getAddress()) - ((char *)Interpreter::memory->SFB));	// offset
		len += args_len;
	}
	// the rest of the code
	memcpy(code+len, precompiled, code_len);
	(*((int *)(code+len+1))) = int(args.size())-1;
	(*((void **)(code+len+7))) = fn;
	(*((void **)(code+len+13))) = this;
	(*((void **)(code+len+19))) = callFn;
	(*((void **)(code+len+26))) = compiled_functions[fn->name];
	(*((char *)(code+len+34))) = (char)((args.size()+2)*4);
	//
	if(compiled_functions[fn->name] == NULL)
		fillCalls[fn->name].push_back(code+len+26);
	//
	len += code_len;
	return len;
}

int JITCompiler::gen_ret(char *code)
{
	const int code_len = 17;
	const char *precompiled = "\xB8????"	//mov eax, address(call_stack)
							  "\x50"		//push eax
							  "\xB8????"	//mov eax, address(retFromFn)
							  "\xFF\xD0"	//call eax
							  "\x83\xC4\x04"//add esp, 4	; pop function argument
							  "\xC3";		//ret
	memcpy(code, precompiled, code_len);
	(*((void **)(code+1))) = call_stack;
	(*((void **)(code+7))) = retFromFn;
	return code_len;
}

int JITCompiler::gen_retv(char *code, const Variable *var)
{
	const int code_len = 48;
	const char *precompiled = "\xB8????"		//mov eax, [type] ; (DataType *)
							  "\x50"			//push eax
							  "\xBB????"		//mov ebx, address(SFB)
							  "\xB8????"		//mov eax, offset(var)	; points to a return value
							  "\x03\x03"		//add eax, [ebx]	; absolute address of var
							  "\x8B\x00"		//mov eax, [eax]	; get value rather then pointer -- co DOUBLE!!!!!??? zasobnik FPU? nevraci se tady neco jinyho nez v invoke?
							  "\x50"			//push eax
							  "\xB8????"		//mov eax, address(call_stack)
							  "\x50"			//push eax
							  "\xB8????"		//mov eax, address(retFromFn) ; take only 1 argument so others still lays on the stack prepared for pushXXXVal call
							  "\xFF\xD0"		//call eax
							  "\x83\xC4\x04"	//add esp, 4	; pop retFromFn argument
							  "\xB8????"		//mov eax, address(pushXXXVal)
							  "\xFF\xD0"		//call eax
							  "\x83\xC4\x08"	//add esp, 8	; pop pushXXXVal arguments
							  "\xC3";			//ret
	memcpy(code, precompiled, code_len);
	(*((void **)(code+7))) = &(Interpreter::memory->SFB);
	(*((void **)(code+12))) = (void *)((char *)(var->getAddress()) - ((char *)Interpreter::memory->SFB));	// offset
	(*((void **)(code+22))) = call_stack;
	(*((void **)(code+28))) = retFromFn;
	// of what type is the retval?
	if(var->getDataType()->type == DataType::INTEGER)
	{
		(*((void **)(code+1))) = NULL;
		(*((void **)(code+38))) = pushIntVal;
	}
	else if(var->getDataType()->type == DataType::BOOLEAN)
	{
		(*((void **)(code+1))) = NULL;
		(*((void **)(code+38))) = pushBoolVal;
	}
	else if(var->getDataType()->type == DataType::DOUBLE)
	{
		(*((void **)(code+1))) = NULL;
		(*((void **)(code+38))) = pushDblVal;
	}
	else if(var->getDataType()->type == DataType::STRING)
	{
		(*((void **)(code+1))) = NULL;
		(*((void **)(code+38))) = pushStrVal;
	}
	else	// REFERENCE: ARRAY or STRUCTURE
	{
		(*((void **)(code+1))) = var->getDataType();
		(*((void **)(code+38))) = pushPtrVal;
	}
	return code_len;
}

int JITCompiler::gen_pop(char *code, Variable *dest)
{
	if(dest->getItemType() == DataType::BOOLEAN)
	{
		const int code_len = 32;
		const char *precompiled = "\xB8????"		//mov eax, value(var->item_type)
								  "\x50"			//push eax
								  "\xB8????"		//mov eax, address(popAndGetTopValAddr)
								  "\xFF\xD0"		//call eax
								  "\x83\xC4\x04"	//add esi, 4	; pop function argument
								  "\x8A\x00"		//mov al, byte ptr [eax]
								  "\xB9????"		//mov ecx, address(SFB)
								  "\xBB????"		//mov ebx, offset(dest)
								  "\x03\x19"		//add ebx, [ecx]	; absolute address of dest
								  "\x88\x03";		//mov byte ptr [ebx], al	; save into dest
		memcpy(code, precompiled, code_len);
		(*((int *)(code+1))) = dest->getItemType();
		(*((void **)(code+7))) = popAndGetTopValAddr;
		(*((void **)(code+19))) = &(Interpreter::memory->SFB);
		(*((void **)(code+24))) = (void *)(((char *)(dest->getAddress())) - ((char *)Interpreter::memory->SFB));	// offset
		return code_len;
	}
	else if(dest->getItemType() == DataType::DOUBLE)
	{
		const int code_len = 32;
		const char *precompiled = "\xB8????"		//mov eax, value(var->item_type)
								  "\x50"			//push eax
								  "\xB8????"		//mov eax, address(popAndGetTopValAddr)
								  "\xFF\xD0"		//call eax
								  "\x83\xC4\x04"	//add esi, 4	; pop function argument
								  "\xDD\x00"		//fld qword ptr[eax]
								  "\xBB????"		//mov ebx, address(SFB)
								  "\xB8????"		//mov eax, offset(dest)
								  "\x03\x03"		//add eax, [ebx]	; absolute address of dest
								  "\xDD\x18";		//fstp qword ptr [eax]	; save into dest
		memcpy(code, precompiled, code_len);
		(*((int *)(code+1))) = dest->getItemType();
		(*((void **)(code+7))) = popAndGetTopValAddr;
		(*((void **)(code+19))) = &(Interpreter::memory->SFB);
		(*((void **)(code+24))) = (void *)(((char *)(dest->getAddress())) - ((char *)Interpreter::memory->SFB));	// offset
		return code_len;
	}
	else	// 4B -- INTEGER, REFERENCE, ARRAY, STRUCTURE
	{
		const int code_len = 32;
		const char *precompiled = "\xB8????"		//mov eax, value(var->item_type)
								  "\x50"			//push eax
								  "\xB8????"		//mov eax, address(popAndGetTopValAddr)
								  "\xFF\xD0"		//call eax
								  "\x83\xC4\x04"	//add esi, 4	; pop function argument
								  "\x8B\x00"		//mov eax, [eax]
								  "\xB9????"		//mov ecx, address(SFB)
								  "\xBB????"		//mov ebx, offset(dest)
								  "\x03\x19"		//add ebx, [ecx]	; absolute address of dest
								  "\x89\x03";		//mov [ebx], eax	; save into dest
		memcpy(code, precompiled, code_len);
		(*((int *)(code+1))) = dest->getItemType();
		(*((void **)(code+7))) = popAndGetTopValAddr;
		(*((void **)(code+19))) = &(Interpreter::memory->SFB);
		(*((void **)(code+24))) = (void *)(((char *)(dest->getAddress())) - ((char *)Interpreter::memory->SFB));	// offset
		return code_len;
	}
}

int JITCompiler::gen_invoke(char *code, Variable *name, const vector<Argument *> &args)
{
	if(name->getName() == "cloneArray")
	{
		const int code_len = 36;
		const char *precompiled = "\xB8????"		//mov eax, args[1] (Array *)
								  "\x50"			//push eax
								  "\xB8????"		//mov eax, (MemoryManager *)
								  "\x50"			//push eax
								  "\xB8????"		//mov eax, address(BuiltInRoutines::cloneArray)
								  "\xFF\xD0"		//call eax
								  "\xBB????"		//mov ebx, address(var->data_type)
								  "\x53"			//push ebx
								  "\x50"			//push eax		; val = return value from cloneArray (Argument *)
								  "\xB8????"		//mov eax, address(pushPtrVal)
								  "\xFF\xD0"		//call eax		; pushPtrVal(val, type);
								  "\x83\xC4\x0C";	//add esp, 12	; pop functions arguments from both cloneArray and pushPtrVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+7))) = Interpreter::memory;
		(*((void **)(code+13))) = BuiltInRoutines::cloneArray;
		(*((void **)(code+20))) = ((Variable *)(args[1]))->getDataType();
		(*((void **)(code+27))) = pushPtrVal;
		return code_len;
	}
	else if(name->getName() == "clearArray")
	{
		const int code_len = 16;
		const char *precompiled = "\xB8????"			//mov eax, args[1] (Array *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, address(BuiltInRoutines::clearArray)
								  "\xFF\xD0"			//call eax
								  "\x83\xC4\x04";		//add esp, 4	; pop functions arguments from clearArray
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+7))) = BuiltInRoutines::clearArray;
		return code_len;
	}
	else if(name->getName() == "length")
	{
		const int code_len = 30;
		const char *precompiled = "\xB8????"			//mov eax, args[1] (Array *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, address(BuiltInRoutines::length)
								  "\xFF\xD0"			//call eax
								  "\xBB\x00\x00\x00\x00"//mov ebx, 0	; type = NULL
								  "\x53"				//push ebx
								  "\x50"				//push eax		; val = return value from length (Argument *)
								  "\xB8????"			//mov eax, address(pushIntVal)
								  "\xFF\xD0"			//call eax		; pushIntVal(val, type);
								  "\x83\xC4\x0C";		//add esp, 12	; pop functions arguments from both length and pushIntVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+7))) = BuiltInRoutines::length;
		(*((void **)(code+21))) = pushIntVal;
		return code_len;
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
								  "\xB8????"			//mov eax, address(pushPtrVal)
								  "\xFF\xD0"			//call eax		; pushPtrVal(val, type);
								  "\x83\xC4\x0C";		//add esp, 12	; pop functions arguments from both openRFile and pushPtrVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+7))) = BuiltInRoutines::openRFile;
		(*((void **)(code+21))) = pushPtrVal;
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
								  "\xB8????"			//mov eax, address(pushPtrVal)
								  "\xFF\xD0"			//call eax		; pushPtrVal(val, type);
								  "\x83\xC4\x10";		//add esp, 16	; pop functions arguments from both openWFile and pushPtrVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = ((Variable *)(args[2]))->getValue();
		(*((void **)(code+7))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+13))) = BuiltInRoutines::openWFile;
		(*((void **)(code+27))) = pushPtrVal;
		return code_len;
	}
	else if(name->getName() == "closeFile")
	{
		const int code_len = 16;
		const char *precompiled = "\xB8????"			//mov eax, args[1] (File *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, address(BuiltInRoutines::closeFile)
								  "\xFF\xD0"			//call eax
								  "\x83\xC4\x04";		//add esp, 4	; pop functions arguments from flushFile
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+7))) = BuiltInRoutines::closeFile;
		return code_len;
	}
	else if(name->getName() == "flushFile")
	{
		const int code_len = 16;
		const char *precompiled = "\xB8????"			//mov eax, args[1] (File *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, address(BuiltInRoutines::flushFile)
								  "\xFF\xD0"			//call eax
								  "\x83\xC4\x04";		//add esp, 4	; pop functions arguments from flushFile
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+7))) = BuiltInRoutines::flushFile;
		return code_len;
	}
	else if(name->getName() == "printlnFile")
	{
		const int code_len = 22;
		const char *precompiled = "\xB8????"			//mov eax, args[2] (String *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, args[1] (File *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, address(BuiltInRoutines::printlnFile)
								  "\xFF\xD0"			//call eax
								  "\x83\xC4\x08";		//add esp, 8	; pop functions arguments from printlnFile
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = ((Variable *)(args[2]))->getValue();
		(*((void **)(code+7))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+13))) = BuiltInRoutines::printlnFile;
		return code_len;
	}
	else if(name->getName() == "printFile")
	{
		const int code_len = 22;
		const char *precompiled = "\xB8????"			//mov eax, args[2] (String *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, args[1] (File *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, address(BuiltInRoutines::printFile)
								  "\xFF\xD0"			//call eax
								  "\x83\xC4\x08";		//add esp, 8	; pop functions arguments from printFile
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = ((Variable *)(args[2]))->getValue();
		(*((void **)(code+7))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+13))) = BuiltInRoutines::printFile;
		return code_len;
	}
	else if(name->getName() == "println")
	{
		const int code_len = 16;
		const char *precompiled = "\xB8????"			//mov eax, args[1] (String *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, address(BuiltInRoutines::println)
								  "\xFF\xD0"			//call eax
								  "\x83\xC4\x04";		//add esp, 4	; pop functions arguments from println
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+7))) = BuiltInRoutines::println;
		return code_len;
	}
	else if(name->getName() == "print")
	{
		const int code_len = 16;
		const char *precompiled = "\xB8????"			//mov eax, args[1] (String *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, address(BuiltInRoutines::print)
								  "\xFF\xD0"			//call eax
								  "\x83\xC4\x04";		//add esp, 4	; pop functions arguments from print
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+7))) = BuiltInRoutines::print;
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
								  "\xB8????"			//mov eax, address(pushStrVal)
								  "\xFF\xD0"			//call eax		; pushStrVal(val, type);
								  "\x83\xC4\x10";		//add esp, 16	; pop functions arguments from both inputFile and pushStrVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = Interpreter::memory;
		(*((void **)(code+7))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+13))) = BuiltInRoutines::pow;
		(*((void **)(code+27))) = pushStrVal;
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
								  "\xB8????"			//mov eax, address(pushStrVal)
								  "\xFF\xD0"			//call eax		; pushStrVal(val, type);
								  "\x83\xC4\x0C";		//add esp, 12	; pop functions arguments from both input and pushStrVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = Interpreter::memory;
		(*((void **)(code+7))) = BuiltInRoutines::input;
		(*((void **)(code+21))) = pushStrVal;
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
								  "\xB8????"			//mov eax, address(pushBoolVal)
								  "\xFF\xD0"			//call eax		; pushBoolVal(val, type);
								  "\x83\xC4\x0C";		//add esp, 12	; pop functions arguments from both eof and pushBoolVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+7))) = BuiltInRoutines::eof;
		(*((void **)(code+21))) = pushBoolVal;
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
								  "\xB8????"			//mov eax, address(pushBoolVal)
								  "\xFF\xD0"			//call eax		; pushVal(val, type);
								  "\x83\xC4\x0C";		//add esp, 12	; pop functions arguments from both eoi and pushBoolVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = BuiltInRoutines::eoi;
		(*((void **)(code+15))) = pushBoolVal;
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
								  "\xB8????"			//mov eax, address(pushDblVal)
								  "\xFF\xD0"			//call eax		; pushDblVal(val, type);
								  "\x83\xC4\x10";		//add esp, 16	; pop functions arguments from both pow and pushDblVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = ((Variable *)(args[2]))->getValue();
		(*((void **)(code+7))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+13))) = BuiltInRoutines::pow;
		(*((void **)(code+27))) = pushDblVal;
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
								  "\xB8????"			//mov eax, address(pushDblVal)
								  "\xFF\xD0"			//call eax		; pushDblVal(val, type);
								  "\x83\xC4\x0C";		//add esp, 12	; pop functions arguments from both sqrt and pushDblVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+7))) = BuiltInRoutines::sqrt;
		(*((void **)(code+21))) = pushDblVal;
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
								  "\xB8????"			//mov eax, address(pushDblVal)
								  "\xFF\xD0"			//call eax		; pushVal(val, type);
								  "\x83\xC4\x0C";		//add esp, 12	; pop functions arguments from both log and pushDblVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+7))) = BuiltInRoutines::log;
		(*((void **)(code+21))) = pushDblVal;
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
								  "\xB8????"			//mov eax, address(pushIntVal)
								  "\xFF\xD0"			//call eax		; pushIntVal(val, type);
								  "\x83\xC4\x0C";		//add esp, 12	; pop functions arguments from both rand and pushIntVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+7))) = BuiltInRoutines::rand;
		(*((void **)(code+21))) = pushIntVal;
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
								  "\xB8????"			//mov eax, address(pushIntVal)
								  "\xFF\xD0"			//call eax		; pushIntVal(val, type);
								  "\x83\xC4\x10";		//add esp, 16	; pop functions arguments from both indexOf and pushIntVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = ((Variable *)(args[2]))->getValue();
		(*((void **)(code+7))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+13))) = BuiltInRoutines::indexOf;
		(*((void **)(code+27))) = pushIntVal;
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
								  "\xB8????"			//mov eax, address(pushIntVal)
								  "\xFF\xD0"			//call eax		; pushIntVal(val, type);
								  "\x83\xC4\x10";		//add esp, 16	; pop functions arguments from both lastIndexOf and pushIntVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = ((Variable *)(args[2]))->getValue();
		(*((void **)(code+7))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+13))) = BuiltInRoutines::lastIndexOf;
		(*((void **)(code+27))) = pushIntVal;
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
								  "\xB8????"			//mov eax, address(pushStrVal)
								  "\xFF\xD0"			//call eax		; pushStrVal(val, type);
								  "\x83\xC4\x18";		//add esp, 24	; pop functions arguments from both substring and pushStrVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = ((Variable *)(args[3]))->getValue();
		(*((void **)(code+7))) = ((Variable *)(args[2]))->getValue();
		(*((void **)(code+13))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+19))) = Interpreter::memory;
		(*((void **)(code+25))) = BuiltInRoutines::substring;
		(*((void **)(code+39))) = pushStrVal;
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
								  "\xB8????"			//mov eax, address(pushStrVal)
								  "\xFF\xD0"			//call eax		; pushStrVal(val, type);
								  "\x83\xC4\x0C";		//add esp, 12	; pop functions arguments from both toLower and pushStrVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+7))) = BuiltInRoutines::toLower;
		(*((void **)(code+21))) = pushStrVal;
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
								  "\xB8????"			//mov eax, address(pushStrVal)
								  "\xFF\xD0"			//call eax		; pushStrVal(val, type);
								  "\x83\xC4\x0C";		//add esp, 12	; pop functions arguments from both toUpper and pushStrVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+7))) = BuiltInRoutines::toUpper;
		(*((void **)(code+21))) = pushStrVal;
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
								  "\xB8????"			//mov eax, address(pushStrVal)
								  "\xFF\xD0"			//call eax		; pushStrVal(val, type);
								  "\x83\xC4\x0C";		//add esp, 12	; pop functions arguments from both trim and pushStrVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+7))) = BuiltInRoutines::trim;
		(*((void **)(code+21))) = pushStrVal;
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
								  "\xB8????"			//mov eax, address(pushBoolVal)
								  "\xFF\xD0"			//call eax		; pushBoolVal(val, type);
								  "\x83\xC4\x10";		//add esp, 16	; pop functions arguments from both startsWith and pushBoolVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = ((Variable *)(args[2]))->getValue();
		(*((void **)(code+7))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+13))) = BuiltInRoutines::startsWith;
		(*((void **)(code+27))) = pushBoolVal;
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
								  "\xB8????"			//mov eax, address(pushBoolVal)
								  "\xFF\xD0"			//call eax		; pushBoolVal(val, type);
								  "\x83\xC4\x10";		//add esp, 16	; pop functions arguments from both endsWith and pushBoolVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = ((Variable *)(args[2]))->getValue();
		(*((void **)(code+7))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+13))) = BuiltInRoutines::endsWith;
		(*((void **)(code+27))) = pushBoolVal;
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
								  "\xB8????"			//mov eax, address(pushStrVal)
								  "\xFF\xD0"			//call eax		; pushStrVal(val, type);
								  "\x83\xC4\x14";		//add esp, 20	; pop functions arguments from both concat and pushStrVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = ((Variable *)(args[2]))->getValue();
		(*((void **)(code+7))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+13))) = Interpreter::memory;
		(*((void **)(code+19))) = BuiltInRoutines::concat;
		(*((void **)(code+33))) = pushStrVal;
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
								  "\xB8????"			//mov eax, address(pushIntVal)
								  "\xFF\xD0"			//call eax		; pushVal(val, type);
								  "\x83\xC4\x0C";		//add esp, 12	; pop functions arguments from both strlen and pushIntVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+7))) = BuiltInRoutines::strlen;
		(*((void **)(code+21))) = pushIntVal;
		return code_len;
	}
	else if(name->getName() == "int2str")
	{
		const int code_len = 45;
		const char *precompiled = "\xB8????"			//mov eax, offset(args[1])
								  "\xBB????"			//mov ebx, address(SFB)
								  "\x03\x03"			//add eax, [ebx]	; absolute address of var
								  "\x8B\x00"			//mov eax, [eax]	; value of var
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, (MemoryManager *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, address(JITBuiltInRoutines::int2str)
								  "\xFF\xD0"			//call eax
								  "\xBB\x00\x00\x00\x00"//mov ebx, 0	; type = NULL
								  "\x53"				//push ebx
								  "\x50"				//push eax		; val = return value from int2str (Argument *)
								  "\xB8????"			//mov eax, address(pushVal)
								  "\xFF\xD0"			//call eax		; pushVal(val, type);
								  "\x83\xC4\x10";		//add esp, 16	; pop functions arguments from both int2str and pushStrVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = (void *)(((char *)((Variable *)(args[1]))->getAddress()) - ((char *)Interpreter::memory->SFB));	// offset
		(*((void **)(code+6))) = &(Interpreter::memory->SFB);
		(*((void **)(code+16))) = Interpreter::memory;
		(*((void **)(code+22))) = JITBuiltInRoutines::int2str;
		(*((void **)(code+36))) = pushStrVal;
		return code_len;
	}
	else if(name->getName() == "double2str")
	{
		const int code_len = 50;
		const char *precompiled = "\xB8????"			//mov eax, offset(args[1])
								  "\xBB????"			//mov ebx, address(SFB)
								  "\x03\x03"			//add eax, [ebx]	; absolute address of var
								  "\xDD\x00"			//fld qword ptr [eax]	; save argument to the FPU stack (can't move it directly on the ALU stack)
								  "\x83\xEC\x08"		//sub esp, 8	; 'alloc' room on system stack for argument of type double
								  "\xDD\x1C\x24"		//fstp qword ptr [esp]	; save retval from FPU stack to the ALU stack as parameter for pushDblVal
								  "\xB8????"			//mov eax, args[1] (MemoryManager *)
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, address(BuiltInRoutines::double2str)
								  "\xFF\xD0"			//call eax
								  "\xBB\x00\x00\x00\x00"//mov ebx, 0	; type = NULL
								  "\x53"				//push ebx
								  "\x50"				//push eax		; val = return value from double2str (Argument *)
								  "\xB8????"			//mov eax, address(pushStrVal)
								  "\xFF\xD0"			//call eax		; pushVal(val, type);
								  "\x83\xC4\x14";		//add esp, 20	; pop functions arguments from both double2str and pushStrVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = (void *)(((char *)((Variable *)(args[1]))->getAddress()) - ((char *)Interpreter::memory->SFB));	// offset
		(*((void **)(code+6))) = &(Interpreter::memory->SFB);
		(*((void **)(code+21))) = Interpreter::memory;
		(*((void **)(code+27))) = JITBuiltInRoutines::double2str;
		(*((void **)(code+41))) = pushStrVal;
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
								  "\xB8????"			//mov eax, address(pushIntVal)
								  "\xFF\xD0"			//call eax		; pushVal(val, type);
								  "\x83\xC4\x0C";		//add esp, 12	; pop functions arguments from both str2int and pushIntVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+7))) = BuiltInRoutines::str2int;
		(*((void **)(code+21))) = pushIntVal;
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
								  "\xB8????"			//mov eax, address(pushIntVal)
								  "\xFF\xD0"			//call eax		; pushVal(val, type);
								  "\x83\xC4\x0C";		//add esp, 12	; pop functions arguments from both double2int and pushIntVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+7))) = BuiltInRoutines::double2int;
		(*((void **)(code+21))) = pushIntVal;
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
								  "\xB8????"			//mov eax, address(pushDblVal)
								  "\xFF\xD0"			//call eax		; pushVal(val, type);
								  "\x83\xC4\x0C";		//add esp, 12	; pop functions arguments from both str2double and pushDblVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = ((Variable *)(args[1]))->getValue();
		(*((void **)(code+7))) = BuiltInRoutines::str2double;
		(*((void **)(code+21))) = pushDblVal;
		return code_len;
	}
	else if(name->getName() == "int2double")
	{
		const int code_len = 44;
		const char *precompiled = "\xB8????"			//mov eax, offset(args[1])
								  "\xBB????"			//mov ebx, address(SFB)
								  "\x03\x03"			//add eax, [ebx]	; absolute address of var
								  "\x8B\x00"			//mov eax, [eax]	; value of var
								  "\x50"				//push eax
								  "\xB8????"			//mov eax, address(BuiltInRoutines::int2double)
								  "\xFF\xD0"			//call eax
								  "\xBB\x00\x00\x00\x00"//mov ebx, 0	; type = NULL
								  "\x53"				//push ebx
								  "\x83\xEC\x08"		//sub esp, 8	; 'alloc' room on system stack for double retval
								  "\xDD\x1C\x24"		//fstp qword ptr [esp]	; save retval from FPU stack to the ALU stack as parameter for pushDblVal
								  "\xB8????"			//mov eax, address(pushDblVal)	; value argument is saved on FPU stack!
								  "\xFF\xD0"			//call eax		; pushDblVal(val, type);
								  "\x83\xC4\x10";		//add esp, 16	; pop functions arguments from both int2double and pushDblVal
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = (void *)(((char *)((Variable *)(args[1]))->getAddress()) - ((char *)Interpreter::memory->SFB));	// offset
		(*((void **)(code+6))) = &(Interpreter::memory->SFB);
		(*((void **)(code+16))) = JITBuiltInRoutines::int2double;
		(*((void **)(code+35))) = pushDblVal;
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
	(*(hlp_jump_loc.top())).push_back(code+9);
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
	(*(hlp_jump_loc.top())).push_back(code+9);
	return code_len;
}

int JITCompiler::gen_jmp(char *code, const Integer *to)
{
	const int code_len = 2;
	const char *precompiled = "\xEB?";	//jmp value(to)
	memcpy(code, precompiled, code_len);
	(*((int *)(code+1))) = (char)(to->getValue());
	(*(hlp_jump_loc.top())).push_back(code+1);
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
		const int code_len = 34;
		const char *precompiled = "\xB9????"	//mov ecx, address(SFB)
								  "\xB8????"	//mov eax, offset(op1)
								  "\x03\x01"	//add eax, [ecx]	; absolute address of op1
								  "\x8B\x00"	//mov eax, [eax]
								  "\xBB????"	//mov ebx, offset(op2)
								  "\x03\x19"	//add ebx, [ecx]	; absolute address of op2
								  "\x8B\x1B"	//mov ebx, [ebx]
								  "\x2B\xC3"	//sub eax, ebx
								  "\xBB????"	//mov ebx, offset(dest)
								  "\x03\x19"	//add ebx, [ecx]	; absolute address of dest
								  "\x89\x03";	//mov [ebx], eax
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = &(Interpreter::memory->SFB);
		(*((void **)(code+6))) = (void *)(((char *)(op1->getAddress())) - ((char *)Interpreter::memory->SFB));	// offset
		(*((void **)(code+15))) = (void *)(((char *)(op2->getAddress())) - ((char *)Interpreter::memory->SFB));	// offset
		(*((void **)(code+26))) = (void *)(((char *)(dest->getAddress())) - ((char *)Interpreter::memory->SFB));	// offset
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
		const int code_len = 34;
		const char *precompiled = "\xB9????"	//mov ecx, address(SFB)
								  "\xB8????"	//mov eax, offset(op1)
								  "\x03\x01"	//add eax, [ecx]	; absolute address of op1
								  "\x8B\x00"	//mov eax, [eax]
								  "\xBB????"	//mov ebx, offset(op2)
								  "\x03\x19"	//add ebx, [ecx]	; absolute address of op2
								  "\x8B\x1B"	//mov ebx, [ebx]
								  "\xF7\xE3"	//mul ebx
								  "\xBB????"	//mov ebx, offset(dest)
								  "\x03\x19"	//add ebx, [ecx]	; absolute address of dest
								  "\x89\x03";	//mov [ebx], eax
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = &(Interpreter::memory->SFB);
		(*((void **)(code+6))) = (void *)(((char *)(op1->getAddress())) - ((char *)Interpreter::memory->SFB));	// offset
		(*((void **)(code+15))) = (void *)(((char *)(op2->getAddress())) - ((char *)Interpreter::memory->SFB));	// offset
		(*((void **)(code+26))) = (void *)(((char *)(dest->getAddress())) - ((char *)Interpreter::memory->SFB));	// offset
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
	const int code_len = 19;
	const char *precompiled = "\xBB????"	//mov ebx, address(SFB)
							  "\xB8????"	//mov eax, offset(var)
							  "\x03\x03"	//add eax, [ebx]	; absolute address of var
							  "\xBB????"	//mov ebx, value(constant)
							  "\x89\x18";	//mov [eax], ebx  
	memcpy(code, precompiled, code_len);
	(*((void **)(code+1))) = &(Interpreter::memory->SFB);
	(*((void **)(code+6))) = (void *)((char *)(var->getAddress()) - ((char *)Interpreter::memory->SFB));	// offset
	(*((int *)(code+13))) = constant->getValue();
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
		const int code_len = 51;
		const char *precompiled = "\xB9????"	//		mov ecx, address(SFB)
								  "\xBB????"	//		mov ebx, offset(op2)
								  "\x03\x19"	//		add ebx, [ecx]	; absolute address of op2
								  "\x8B\x1B"	//		mov ebx, [ebx]
								  "\xB8????"	//		mov eax, offset(op1)
								  "\x03\x01"	//		add eax, [ecx]	; absolute address of op1
								  "\x8B\x00"	//		mov eax, [eax]
								  "\x3B\xC3"	//		cmp eax, ebx
								  "\x7E\x04"	//		jle _lte
								  "\xB0\x00"	//		mov al, 0
								  "\xEB\x02"	//		jmp _cont
								  "\xB0\x01"	//_lte:	mov al, 1
								  "\xBB????"	//_cont:mov ebx, offset(dest)
								  "\x03\x19"	//		add ebx, [ecx]	; absolute address of dest
								  "\x88\x03"	//		mov byte ptr [ebx], al
								  // save ZF
								  "\x34\x01"	//		xor al,1	; negate LSB (al is 1 if comparison was successful - for ZF, I need the exact opposite)
								  "\xBB????"	//		mov ebx, address(ZF)
								  "\x88\x03";	//		mov byte ptr [ebx], al
		memcpy(code, precompiled, code_len);
		(*((void **)(code+1))) = &(Interpreter::memory->SFB);
		(*((void **)(code+6))) = (void *)(((char *)(op2->getAddress())) - ((char *)Interpreter::memory->SFB));	// offset
		(*((void **)(code+15))) = (void *)(((char *)(op1->getAddress())) - ((char *)Interpreter::memory->SFB));	// offset
		(*((void **)(code+34))) = (void *)(((char *)(dest->getAddress())) - ((char *)Interpreter::memory->SFB));	// offset
		(*((void **)(code+45))) = pZF;
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