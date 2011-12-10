#include "InstructionSet.h"
#include "Argument.h"
#include "BuiltInRoutines.h"

map<string, int> * InstructionCode::convertor = NULL;

ostringstream Integer::os;
ostringstream Double::os;
ostringstream Array::os;
ostringstream Structure::os;
ostringstream File::os;
ostringstream Reference::os;

bool BuiltInRoutines::m_first_rand = true;
time_t BuiltInRoutines::m_rand_seed = time(NULL);
map<string, DataType *> BuiltInRoutines::routinesList;