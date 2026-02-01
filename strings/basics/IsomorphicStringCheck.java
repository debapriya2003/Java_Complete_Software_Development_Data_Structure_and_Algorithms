package strings.basics;

public class IsomorphicStringCheck {

    /*
    =====================================================================================
    PROBLEM: ISOMORPHIC STRING CHECK
    -------------------------------------------------------------------------------------
    Two strings are isomorphic if the characters in one string can be replaced to get 
    the other. An isomorphism is a one-to-one character mapping where each character 
    in the first string must map to exactly one character in the second string and vice 
    versa. For example, "egg" and "add" are isomorphic because 'e'->'a', 'g'->'d'. 
    Use two hash maps to track bidirectional mappings and ensure consistency.

    Time Complexity: O(n)
    Space Complexity: O(1) - at most 26 lowercase letters

    Example:
    Input:  s = "egg", t = "add"
    Output: true (e->a, g->d creates valid mapping)
    =====================================================================================
    */
    
    /**
     * Checks if two strings are isomorphic by creating bidirectional character mappings.
     * Uses two hash maps: one maps characters from s to t, another maps t to s.
     * This ensures one-to-one mapping. For each character pair, checks if mapping
     * is consistent with previously established mappings.
     * 
     * @param s first string
     * @param t second string
     * @return true if strings are isomorphic, false otherwise
     */
    public static boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;
        
        java.util.Map<Character, Character> sToT = new java.util.HashMap<>();
        java.util.Map<Character, Character> tToS = new java.util.HashMap<>();
        
        for (int i = 0; i < s.length(); i++) {
            char sChar = s.charAt(i);
            char tChar = t.charAt(i);
            
            // Check if sChar has a mapping
            if (sToT.containsKey(sChar)) {
                if (sToT.get(sChar) != tChar) {
                    return false;  // Conflicting mapping
                }
            } else {
                sToT.put(sChar, tChar);
            }
            
            // Check if tChar has a mapping
            if (tToS.containsKey(tChar)) {
                if (tToS.get(tChar) != sChar) {
                    return false;  // Conflicting mapping
                }
            } else {
                tToS.put(tChar, sChar);
            }
        }
        return true;
    }
    
    /**
     * Alternative approach using character transformation pattern.
     * Transforms both strings to the same pattern of characters and compares.
     * Pattern represents the first occurrence of each character with increasing numbers.
     * 
     * @param s first string
     * @param t second string
     * @return true if strings are isomorphic
     */
    public static boolean isIsomorphicPattern(String s, String t) {
        if (s.length() != t.length()) return false;
        
        String patternS = getPattern(s);
        String patternT = getPattern(t);
        
        return patternS.equals(patternT);
    }
    
    /**
     * Helper function to convert string to a pattern.
     * Each unique character gets a numeric pattern based on first occurrence order.
     * For example, "egg" becomes "0 1 1" and "add" becomes "0 1 1".
     * 
     * @param str input string
     * @return pattern representation of string
     */
    private static String getPattern(String str) {
        java.util.Map<Character, Integer> map = new java.util.HashMap<>();
        StringBuilder pattern = new StringBuilder();
        int count = 0;
        
        for (char c : str.toCharArray()) {
            if (!map.containsKey(c)) {
                map.put(c, count++);
            }
            pattern.append(map.get(c)).append(" ");
        }
        return pattern.toString();
    }
    
    /**
     * Single array approach using character array mapping.
     * Uses 256-size array to map ASCII characters (or 26 for lowercase only).
     * More memory efficient than HashMap for limited character sets.
     * 
     * @param s first string
     * @param t second string
     * @return true if isomorphic
     */
    public static boolean isIsomorphicArray(String s, String t) {
        if (s.length() != t.length()) return false;
        
        int[] sMap = new int[256];
        int[] tMap = new int[256];
        
        for (int i = 0; i < s.length(); i++) {
            int sChar = s.charAt(i);
            int tChar = t.charAt(i);
            
            if (sMap[sChar] == 0 && tMap[tChar] == 0) {
                sMap[sChar] = tChar;
                tMap[tChar] = sChar;
            } else if (sMap[sChar] != tChar || tMap[tChar] != sChar) {
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        System.out.println("Is isomorphic \"egg\" and \"add\": " + 
            isIsomorphic("egg", "add"));                    // true
        System.out.println("Is isomorphic \"foo\" and \"bar\": " + 
            isIsomorphic("foo", "bar"));                    // false
        System.out.println("Is isomorphic \"paper\" and \"title\": " + 
            isIsomorphic("paper", "title"));                // true
        System.out.println("Is isomorphic \"badc\" and \"baba\": " + 
            isIsomorphic("badc", "baba"));                  // false
        System.out.println("Is isomorphic (pattern) \"egg\" and \"add\": " + 
            isIsomorphicPattern("egg", "add"));             // true
        System.out.println("Is isomorphic (array) \"paper\" and \"title\": " + 
            isIsomorphicArray("paper", "title"));           // true
    }
}
