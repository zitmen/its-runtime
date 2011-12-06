package mi.run.ctalk;

import java.io.FileOutputStream;
import java.io.PrintStream;
import org.antlr.runtime.*;
import mi.run.ast.*;
import mi.run.runtime.Interpreter;
import mi.run.semantic.Functions;

public class Ctalk
{
    public static void main(String[] args) throws Exception
    {
        //try
        //{
            if(args.length != 2)
            {
                System.err.println("Usage: java -jar CTalk.jar source.ctalk bytecode.run");
                System.exit(1);
            }
            CTalkLexer lex = new CTalkLexer(new ANTLRFileStream(args[0]));
            CommonTokenStream tokens = new CommonTokenStream(lex);
            CTalkParser parser = new CTalkParser(tokens);
            Program program = parser.program();
            System.out.println("AST = " + program + '\n');
            //
            Functions.init();   // init the built-in functions -- IMPORTANT!!! otherwise it throws semantic errors that system functions were not defined!
            System.out.println("\nSEMANTIC CHECK");
            program.semanticCheck();   // semantic check: are all called functions defined?
            System.out.println("OPTIMIZING AST");
            program.optimize(); // optimizations at statements/expressions level: constant-folding, dead-code elimination
            System.out.println("Optimized AST = " + program + '\n');
            //
            Interpreter machine = new Interpreter(program.genByteCode());
            System.out.println("BYTECODE");
            //
            FileOutputStream out = new FileOutputStream(args[1]);
            PrintStream printer = new PrintStream(out);
            machine.printByteCode(printer);
            printer.close();
        //}
        //catch(Exception ex)
        //{
        //    System.err.println(ex.getMessage());
        //}
    }
}
