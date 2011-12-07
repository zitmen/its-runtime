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
        
        public static String concat(String str1, String str2)
        {
            return str1.concat(str2);
        }
        
        public static int strlen(String str)
        {
            return str.length();
        }
}
