package mi.run.runtime.built_in_routines;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Scanner;

public class IORoutines
{
    public static HashMap<BufferedReader, Scanner> fScanners = new HashMap<BufferedReader, Scanner>();
    public static Scanner iScanner = new Scanner(System.in);
    
    public static BufferedReader openRFile(String fname)
    {
        try {
             BufferedReader reader = new BufferedReader(new FileReader(fname));
             fScanners.put(reader, new Scanner(reader));
             return reader;
        } catch (Exception ex) {
            return null;
        }
    }
    
    public static BufferedWriter openWFile(String fname, boolean append)
    {
        try {
            return new BufferedWriter(new FileWriter(fname, append));
        } catch (Exception ex) {
            return null;
        }
    }
    
    public static void closeFile(BufferedReader f)
    {
        try {
            f.close();
            fScanners.remove(f);
        } catch (Exception ex) {
            //
        }
    }
    
    public static void closeFile(BufferedWriter f)
    {
        try {
            f.close();
        } catch (Exception ex) {
            //
        }
    }
    
    public static void flushFile(BufferedWriter f)
    {
        try {
            f.flush();
        } catch (Exception ex) {
            //
        }
    }
    
    public static void printFile(BufferedWriter f, String str)
    {
        try {
            f.write(str, 0, str.length());
        } catch (Exception ex) {
            //
        }
    }
    
    public static void printlnFile(BufferedWriter f, String str)
    {
        try {
            f.write(str, 0, str.length());
            f.newLine();
        } catch (Exception ex) {
            //
        }
    }
    
    public static void print(String str)
    {
        System.out.print(str);
    }
    
    public static void println(String str)
    {
        System.out.println(str);
    }
    
    public static String inputFile(BufferedReader f)
    {
        try {
            return fScanners.get(f).next();
        } catch(Exception ex) {
            //
        }
        return "";
    }
    
    public static String input()
    {
        try {
            return iScanner.next();
        } catch(Exception ex) {
            //
        }
        return "";
    }
    
    public static boolean eof(BufferedReader f)
    {
        return !fScanners.get(f).hasNext();
    }
    
    public static boolean eoi()
    {
        return !iScanner.hasNext();
    }
}
