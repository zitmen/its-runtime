package mi.run.ast;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import mi.run.bytecode.Code;
import mi.run.bytecode.Instruction;
import mi.run.semantic.Functions;

public class Program extends Node
{
    public java.util.ArrayList<StructureDefinition> structures;
    public java.util.ArrayList<FunctionDefinition> functions;
    
    public Program()
    {
        functions  = new java.util.ArrayList<FunctionDefinition>();
        structures = new java.util.ArrayList<StructureDefinition>();
    }

    @Override
    public String toString()
    {
        return "(Program " + structures + functions + ")";
    }
    
    @Override
    public Node optimize() throws Exception
    {
        for(int i = 0, im = functions.size(); i < im; i++)
            functions.set(i, (FunctionDefinition)functions.get(i).optimize());
        //
        return this;
    }

    @Override
    public Instruction genByteCode()
    {
        // TODO: struktury --nejak musim uvest jejich popis!!
        throw new UnsupportedOperationException("Not supported yet.");
        /*
        // "main" function is the first, then other functions following
        //
        HashMap<String, Instruction> fnCode = new HashMap<String, Instruction>();
        for(int i = 0, im = functions.size(); i < im; i++)
            fnCode.put(functions.get(i).name, functions.get(i).genByteCode());
        //
        Instruction stream, first;
        Functions.fnIC.put("main", 0);
        stream = removeNoOps(fnCode.get("main"));
        first = stream.first();
        //
        Iterator it = fnCode.entrySet().iterator();
        Map.Entry pair;
        String fnName;
        while(it.hasNext())
        {
            pair = (Entry)it.next();
            fnName = (String)pair.getKey();
            if(!fnName.equals("main"))
            {
                stream = stream.last();
                Functions.fnIC.put(fnName, stream.getIC() + 1);
                stream = stream.last().append(removeNoOps(fnCode.get(fnName)));
            }
        }
        return first;
        */
    }
    
    private Instruction removeNoOps(Instruction instr)
    {
        if(instr == null) return instr;
        //
        while(true)
        {
            if(instr.code == Code.NOOP) // if NOOP, delete it
            {
                if(instr.prev != null) instr.prev.next = instr.next;
                if(instr.next != null) instr.next.prev = instr.prev;
            }
            if(instr.next == null) break;
            instr = instr.next;
            
        }
        return instr.first();
    }

    public void buildFunctionsTable() throws Exception
    {
        FunctionDefinition fnDef;
        for(int i = 0, im = functions.size(); i < im; i++)
        {
            fnDef = functions.get(i);
            if(Functions.functions.get(fnDef.name) == null)
                Functions.functions.put(fnDef.name, fnDef);
            else
                throw new Exception("Semantic error: redefined function '" + fnDef.name + "'!");
        }
        //
        if(Functions.functions.get("main") == null) // entry point!?
            throw new Exception("Semantic error: missing entry point! You must define function 'main'!");
    }

    @Override
    public void semanticCheck() throws Exception
    {
        buildFunctionsTable();  // save defined functions into the symbol table
        //
        for(int i = 0, im = functions.size(); i < im; i++)
            functions.get(i).semanticCheck();
        //
        if(Functions.functions.get("main") == null)
            throw new Exception("Missing entry point -- function main() has not been defined!");
    }
}
