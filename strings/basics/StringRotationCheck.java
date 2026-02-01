package strings.basics;

public class StringRotationCheck {

    /*
    =====================================================================================
    PROBLEM: CHECK WHETHER ONE STRING IS A ROTATION OF ANOTHER
    -------------------------------------------------------------------------------------
    Given two strings s1 and s2, determine if s2 is a rotation of s1. A rotation means 
    taking some characters from the beginning of s1 and moving them to the end to form 
    s2. For example, "abcd" rotated becomes "bcda", "cdab", "dabc". The most elegant 
    solution uses the observation that if s2 is a rotation of s1, then s2 will always 
    be a substring of s1 + s1.

    Time Complexity: O(n) using KMP algorithm for substring search
    Space Complexity: O(1)

    Example:
    Input:  s1 = "abcd", s2 = "cdab"
    Output: true (s2 is s1 rotated by 2 positions)
    =====================================================================================
    */
    
    /**
     * Checks if s2 is a rotation of s1 using the concatenation method.
     * Key insight: if s2 is rotation of s1, it will be a substring of s1 + s1.
     * For example, if s1="abcd", s1+s1="abcdabcd" contains all rotations: "bcda", "cdab", "dabc".
     * First checks length equality, then checks if s2 exists in s1+s1.
     * 
     * @param s1 original string
     * @param s2 string to check if it's rotation
     * @return true if s2 is rotation of s1, false otherwise
     */
    public static boolean isRotation(String s1, String s2) {
        // Length check: rotations must have same length
        if (s1.length() != s2.length()) {
            return false;
        }
        
        // Check if s2 is substring of s1 + s1
        String combined = s1 + s1;
        return combined.contains(s2);
    }
    
    /**
     * Alternative approach by manually checking all possible rotations.
     * Rotates s1 character by character and compares with s2. More intuitive
     * but less efficient than the concatenation method.
     * 
     * @param s1 original string
     * @param s2 string to check
     * @return true if s2 is rotation of s1
     */
    public static boolean isRotationManual(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        
        // Check all possible rotations
        for (int i = 0; i < s1.length(); i++) {
            // Rotate s1 by i positions
            String rotated = s1.substring(i) + s1.substring(0, i);
            if (rotated.equals(s2)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Helper function to find rotation point if strings are rotations of each other.
     * Returns the number of positions rotated, or -1 if not rotations.
     * Useful for determining how much rotation was applied.
     * 
     * @param s1 original string
     * @param s2 potentially rotated string
     * @return rotation amount (0 to length-1), or -1 if not rotation
     */
    public static int findRotationPoint(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return -1;
        }
        
        String combined = s1 + s1;
        int index = combined.indexOf(s2);
        
        return (index != -1) ? index : -1;
    }
    
    /**
     * Optimized check using indexOf with explicit boundaries.
     * Does not create combined string if lengths differ, more memory efficient.
     * 
     * @param s1 original string
     * @param s2 string to check
     * @return true if s2 is rotation of s1
     */
    public static boolean isRotationOptimized(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length()) {
            return false;
        }
        
        if (s1.length() == 0) {
            return true;  // Both empty strings
        }
        
        return (s1 + s1).indexOf(s2) != -1;
    }
    
    public static void main(String[] args) {
        System.out.println("Is \"abcd\" rotation of \"cdab\": " + 
            isRotation("abcd", "cdab"));                    // true
        System.out.println("Is \"abcd\" rotation of \"acbd\": " + 
            isRotation("abcd", "acbd"));                    // false
        System.out.println("Is \"waterbottle\" rotation of \"erbottlewat\": " + 
            isRotation("waterbottle", "erbottlewat"));      // true
        System.out.println("Is \"abcd\" rotation of \"bcda\": " + 
            isRotation("abcd", "bcda"));                    // true
        
        System.out.println("Rotation point of \"abcd\" and \"cdab\": " + 
            findRotationPoint("abcd", "cdab"));             // 2
        System.out.println("Rotation point of \"abcd\" and \"bcda\": " + 
            findRotationPoint("abcd", "bcda"));             // 1
        
        System.out.println("Manual check \"abcd\" and \"dabc\": " + 
            isRotationManual("abcd", "dabc"));              // true
    }
}
