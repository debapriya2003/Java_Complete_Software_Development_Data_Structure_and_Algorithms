package recursion_patternwise.subsequences;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis {

    /*
    =====================================================================================
    PROBLEM: GENERATE ALL VALID PARENTHESES COMBINATIONS
    -------------------------------------------------------------------------------------
    Given n pairs of parentheses, generate all valid combinations of n pairs of 
    well-formed parentheses.
    
    Example:
    n = 1: ["()"]
    n = 2: ["(())", "()()"]
    n = 3: ["((()))", "(()())", "(())()", "()(())", "()()()"]
    
    Approach:
    Use recursion with constraints:
    1. Count of open brackets available
    2. Count of close brackets available
    3. At any point, close brackets used <= open brackets used
    
    Algorithm:
    1. If open > 0, we can add '('
    2. If close > open (in current level), we can add ')'
    3. Base case: when both open and close = 0
    
    Time Complexity: O(4^n / sqrt(n)) - Catalan number
    Space Complexity: O(n) - recursion depth
    =====================================================================================
    */
    
    /**
     * Generate all valid parentheses combinations.
     * 
     * @param n number of pairs
     * @return list of valid parentheses combinations
     */
    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generateHelper(n, n, "", result);
        return result;
    }
    
    /**
     * Recursive helper to generate valid parentheses.
     * 
     * Algorithm:
     * 1. open: number of opening brackets still to be added
     * 2. close: number of closing brackets still to be added
     * 3. At each step, try adding '(' if open > 0
     * 4. Try adding ')' if close > open (ensures validity)
     * 
     * @param open remaining opening brackets to add
     * @param close remaining closing brackets to add
     * @param current current string being built
     * @param result list to store valid combinations
     */
    private static void generateHelper(int open, int close, String current, List<String> result) {
        // Base case: both open and close brackets used
        if (open == 0 && close == 0) {
            result.add(current);
            return;
        }
        
        // Add opening bracket if available
        if (open > 0) {
            generateHelper(open - 1, close, current + "(", result);
        }
        
        // Add closing bracket if more closing brackets remaining than opening
        // This ensures the string remains valid at each step
        if (close > open) {
            generateHelper(open, close - 1, current + ")", result);
        }
    }
    
    /**
     * Alternative approach using StringBuilder.
     * 
     * @param n number of pairs
     * @return list of valid parentheses combinations
     */
    public static List<String> generateParenthesisOptimized(int n) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        generateHelperOptimized(n, n, sb, result);
        return result;
    }
    
    private static void generateHelperOptimized(int open, int close, StringBuilder sb, List<String> result) {
        if (open == 0 && close == 0) {
            result.add(sb.toString());
            return;
        }
        
        if (open > 0) {
            sb.append('(');
            generateHelperOptimized(open - 1, close, sb, result);
            sb.deleteCharAt(sb.length() - 1);
        }
        
        if (close > open) {
            sb.append(')');
            generateHelperOptimized(open, close - 1, sb, result);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
    
    /**
     * Validate if parentheses string is valid.
     * 
     * @param s parentheses string
     * @return true if valid
     */
    public static boolean isValid(String s) {
        int count = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                count++;
            } else {
                count--;
            }
            if (count < 0) {
                return false;
            }
        }
        return count == 0;
    }
    
    public static void main(String[] args) {
        // Test Case 1: n = 1
        System.out.println("=== n = 1 ===");
        List<String> result1 = generateParenthesis(1);
        System.out.println("Valid parentheses: " + result1);
        System.out.println("Count: " + result1.size());
        System.out.println();
        
        // Test Case 2: n = 2
        System.out.println("=== n = 2 ===");
        List<String> result2 = generateParenthesis(2);
        System.out.println("Valid parentheses: " + result2);
        System.out.println("Count: " + result2.size() + " (expected: 2)");
        System.out.println();
        
        // Test Case 3: n = 3
        System.out.println("=== n = 3 ===");
        List<String> result3 = generateParenthesis(3);
        System.out.println("Valid parentheses: " + result3);
        System.out.println("Count: " + result3.size() + " (expected: 5)");
        System.out.println();
        
        // Test Case 4: n = 4
        System.out.println("=== n = 4 ===");
        List<String> result4 = generateParenthesis(4);
        System.out.println("Count: " + result4.size() + " (expected: 14)");
        System.out.println("First 5: " + result4.subList(0, Math.min(5, result4.size())));
        System.out.println();
        
        // Validate all results
        System.out.println("=== Validation ===");
        System.out.println("All n=3 combinations valid? ");
        for (String s : result3) {
            System.out.println("  " + s + ": " + isValid(s));
        }
        System.out.println();
        
        // Comparison with optimized
        System.out.println("=== Optimized Approach ===");
        List<String> opt = generateParenthesisOptimized(3);
        System.out.println("Match with original? " + result3.equals(opt));
        System.out.println("Optimized result: " + opt);
    }
}
