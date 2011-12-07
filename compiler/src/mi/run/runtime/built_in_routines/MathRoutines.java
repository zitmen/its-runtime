package mi.run.runtime.built_in_routines;

import java.util.Random;

public class MathRoutines
{
    private static Random _rand = null;
    
    public static double pow(double x, double n)
    {
        return Math.pow(x, n);
    }
    
    public static double sqrt(double x)
    {
        return Math.sqrt(x);
    }
    
    public static double log(double x)
    {
        return Math.log(x);
    }
    
    public static int rand(int modul)
    {
        if(_rand == null) _rand = new Random();
        return (_rand.nextInt() % modul);
    }
}
