package mi.run.ast;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import mi.run.bytecode.Code;
import mi.run.bytecode.Instruction;
import mi.run.semantic.SymbolTable;

public class StructureDefinition extends Node
{
    public String name;
    public ExpressionList declarations;
    public HashMap<String, DataType> variables;
    
    public StructureDefinition(String name)
    {
        this.name = name;
        declarations = new ExpressionList();
        variables = new HashMap<String, DataType>();
    }
    
    @Override
    public String toString()
    {
        return "(StructureDefinition " + name + " " + declarations + ")";
    }
    
    public String getSignature()
    {
        String sig = "STRUCTURE " + name + " [ ";
        Iterator it = variables.entrySet().iterator();
        Map.Entry pair;
        String fnName;
        DataType fnType;
        while(it.hasNext())
        {
            pair = (Entry)it.next();
            fnName = (String)pair.getKey();
            fnType = (DataType)pair.getValue();
            //
            sig += fnType.getSignature() + " ";
            sig += fnName + " ";
        }
        return sig + "]";
    }

    @Override
    public Node optimize() throws Exception
    {
        return this;
    }

    @Override
    public Instruction genByteCode()
    {
        return new Instruction(Code.NOOP);
    }

    @Override
    public void semanticCheck() throws Exception
    {
        SymbolTable.stepIn();
        declarations.semanticCheck();
        variables = SymbolTable.getThisScopeDeclarations();
        SymbolTable.stepOut();
    }
}
