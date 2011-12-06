package mi.run.semantic;

import java.util.ArrayList;
import mi.run.bytecode.JumpInstr;

public class FlowControl
{
    public static ArrayList<JumpInstr> breakStatements = new ArrayList<JumpInstr>();
    public static ArrayList<JumpInstr> continueStatements = new ArrayList<JumpInstr>();
}
