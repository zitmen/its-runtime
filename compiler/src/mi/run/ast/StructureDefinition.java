package mi.run.ast;

import java.util.HashMap;
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

    @Override
    public Node optimize() throws Exception
    {
        return this;
    }

    @Override
    public Instruction genByteCode()
    {
        throw new UnsupportedOperationException("Not supported yet.");
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
