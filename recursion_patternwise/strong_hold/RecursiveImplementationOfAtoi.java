package recursion_patternwise.strong_hold;

public class RecursiveImplementationOfAtoi {

    /*
    =====================================================================================
    PROBLEM: RECURSIVE IMPLEMENTATION OF ATOI (STRING TO INTEGER)
    -------------------------------------------------------------------------------------
    Convert a string to integer recursively. Handle leading whitespace, sign (+/-),
    and validate that conversion stops at first non-digit character.
    
    Example:
    "42" -> 42
    "   -42" -> -42
    "4193 with words" -> 4193
    "words and 987" -> 0
    "-91283472332" -> -2147483648 (overflow, return INT_MIN)
    
    Approach:
    1. Base case: if string is empty or index out of bounds, return 0
    2. Skip leading whitespace
    3. Handle sign (+ or -)
    4. Recursively build number from left to right
    5. Check for overflow and validate
    
    Time Complexity: O(n) - process each character once
    Space Complexity: O(n) - recursive call stack
    =====================================================================================
    */
    
    /**
     * Wrapper method to convert string to integer recursively.
     * 
     * @param s string to convert
     * @return integer value of string
     */
    public static int myAtoi(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        // State tracking: 0 = start, 1 = sign found, 2 = reading digits
        int[] state = {0};
        int[] index = {0};
        int[] sign = {1};
        
        return atoi(s, index, sign, state, 0);
    }
    
    /**
     * Recursive helper to convert string to integer.
     * 
     * Algorithm:
     * 1. Skip whitespace at beginning
     * 2. Handle optional sign (+ or -)
     * 3. Read digits recursively
     * 4. Check for overflow
     * 5. Return result with sign applied
     * 
     * @param s input string
     * @param index current index
     * @param sign sign flag (1 or -1)
     * @param state current state
     * @param result accumulated result
     * @return integer value
     */
    private static int atoi(String s, int[] index, int[] sign, int[] state, long result) {
        // Base case: reached end of string or non-digit character
        if (index[0] >= s.length()) {
            return (int) (sign[0] * result);
        }
        
        char ch = s.charAt(index[0]);
        
        // State 0: Skip whitespace
        if (state[0] == 0) {
            if (ch == ' ') {
                index[0]++;
                return atoi(s, index, sign, state, result);
            } else {
                state[0] = 1;
                // Don't increment index, process this character again
                return atoi(s, index, sign, state, result);
            }
        }
        
        // State 1: Handle sign
        if (state[0] == 1) {
            if (ch == '+') {
                sign[0] = 1;
                index[0]++;
                state[0] = 2;
                return atoi(s, index, sign, state, result);
            } else if (ch == '-') {
                sign[0] = -1;
                index[0]++;
                state[0] = 2;
                return atoi(s, index, sign, state, result);
            } else if (Character.isDigit(ch)) {
                state[0] = 2;
                // Don't increment index, process this digit
                return atoi(s, index, sign, state, result);
            } else {
                // Invalid character, return 0
                return 0;
            }
        }
        
        // State 2: Read digits
        if (state[0] == 2) {
            if (Character.isDigit(ch)) {
                int digit = ch - '0';
                result = result * 10 + digit;
                
                // Check for overflow
                if (sign[0] == 1 && result > Integer.MAX_VALUE) {
                    return Integer.MAX_VALUE;
                }
                if (sign[0] == -1 && result > (long) Integer.MAX_VALUE + 1) {
                    return Integer.MIN_VALUE;
                }
                
                index[0]++;
                return atoi(s, index, sign, state, result);
            } else {
                // Non-digit character, stop processing
                return (int) (sign[0] * result);
            }
        }
        
        return (int) (sign[0] * result);
    }
    
    /**
     * Alternative recursive approach with cleaner implementation.
     * 
     * @param s input string
     * @return integer value
     */
    public static int myAtoiSimple(String s) {
        // Trim leading spaces
        s = s.trim();
        
        if (s.length() == 0) {
            return 0;
        }
        
        return atoi(s, 0, 1, 0);
    }
    
    /**
     * Simple recursive helper.
     * 
     * @param s string
     * @param index current index
     * @param sign sign multiplier
     * @param result accumulated result
     * @return integer value
     */
    private static int atoi(String s, int index, int sign, long result) {
        // Base case: end of string or non-digit
        if (index >= s.length() || !Character.isDigit(s.charAt(index))) {
            return (int) (sign * result);
        }
        
        // Handle overflow before recursion
        int digit = s.charAt(index) - '0';
        result = result * 10 + digit;
        
        if (sign == 1 && result > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        if (sign == -1 && result > (long) Integer.MAX_VALUE + 1) {
            return Integer.MIN_VALUE;
        }
        
        return atoi(s, index + 1, sign, result);
    }
    
    /**
     * Most concise recursive implementation.
     * Handles sign and leading whitespace externally.
     * 
     * @param s input string
     * @return integer value
     */
    public static int myAtoiClean(String s) {
        int trimmedIndex = 0;
        while (trimmedIndex < s.length() && s.charAt(trimmedIndex) == ' ') {
            trimmedIndex++;
        }
        
        if (trimmedIndex >= s.length()) {
            return 0;
        }
        
        int sign = 1;
        if (s.charAt(trimmedIndex) == '-') {
            sign = -1;
            trimmedIndex++;
        } else if (s.charAt(trimmedIndex) == '+') {
            trimmedIndex++;
        }
        
        // Now recursively convert remaining string
        return atoiHelper(s, trimmedIndex, sign, 0);
    }
    
    private static int atoiHelper(String s, int index, int sign, long result) {
        if (index >= s.length() || !Character.isDigit(s.charAt(index))) {
            // Check overflow
            if (sign == 1) {
                return result > Integer.MAX_VALUE ? Integer.MAX_VALUE : (int) result;
            } else {
                return result > (long) Integer.MAX_VALUE + 1 ? Integer.MIN_VALUE : (int) (sign * result);
            }
        }
        
        result = result * 10 + (s.charAt(index) - '0');
        return atoiHelper(s, index + 1, sign, result);
    }
    
    public static void main(String[] args) {
        // Test Case 1: Positive number
        String s1 = "42";
        System.out.println("Input: \"" + s1 + "\"");
        System.out.println("Output: " + myAtoiClean(s1));
        System.out.println();
        
        // Test Case 2: Negative number with whitespace
        String s2 = "   -42";
        System.out.println("Input: \"" + s2 + "\"");
        System.out.println("Output: " + myAtoiClean(s2));
        System.out.println();
        
        // Test Case 3: Number with trailing words
        String s3 = "4193 with words";
        System.out.println("Input: \"" + s3 + "\"");
        System.out.println("Output: " + myAtoiClean(s3));
        System.out.println();
        
        // Test Case 4: Invalid string starting with words
        String s4 = "words and 987";
        System.out.println("Input: \"" + s4 + "\"");
        System.out.println("Output: " + myAtoiClean(s4));
        System.out.println();
        
        // Test Case 5: Overflow
        String s5 = "-91283472332";
        System.out.println("Input: \"" + s5 + "\"");
        System.out.println("Output: " + myAtoiClean(s5));
        System.out.println("Expected: " + Integer.MIN_VALUE);
        System.out.println();
        
        // Test Case 6: Only whitespace
        String s6 = "   ";
        System.out.println("Input: \"" + s6 + "\"");
        System.out.println("Output: " + myAtoiClean(s6));
        System.out.println();
        
        // Test Case 7: With plus sign
        String s7 = "+123";
        System.out.println("Input: \"" + s7 + "\"");
        System.out.println("Output: " + myAtoiClean(s7));
        System.out.println();
        
        // Test Case 8: Zero
        String s8 = "0";
        System.out.println("Input: \"" + s8 + "\"");
        System.out.println("Output: " + myAtoiClean(s8));
    }
}
