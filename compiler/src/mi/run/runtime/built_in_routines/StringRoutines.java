package mi.run.runtime.built_in_routines;

import mi.run.runtime.Value;

public class StringRoutines
{
        public static int indexOf(String haystack, String needle)
        {
            return haystack.indexOf(needle);
        }
        
        public static int lastIndexOf(String haystack, String needle)
        {
            return haystack.lastIndexOf(needle);
        }
        
        public static String substring(String str, int start, int end)
        {
            return str.substring(start, end);
        }
        
        public static String toLower(String str)
        {
            return str.toLowerCase();
        }
        
        public static String toUpper(String str)
        {
            return str.toUpperCase();
        }
        
        public static String trim(String str)
        {
            return str.trim();
        }
        
        public static boolean startsWith(String str, String prefix)
        {
            return str.startsWith(prefix);
        }
        
        public static boolean endsWith(String str, String suffix)
        {
            return str.endsWith(suffix);
        }
        
        public static String string(Value val)
        {
            try {
                return val.toStr();
            } catch (Exception ex) {
                return "";
            }
        }
        
        public static int integer(Value val)
        {
            try {
                return val.toInt();
            } catch (Exception ex) {
                return 0;
            }
        }
        
        public static double real(Value val)
        {
            try {
                return val.toReal();
            } catch (Exception ex) {
                return 0.0;
            }
        }
        
        public static int length(String str)
        {
            return str.length();
        }
}
