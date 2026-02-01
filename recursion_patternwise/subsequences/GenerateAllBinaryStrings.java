package recursion_patternwise.subsequences;

import java.util.ArrayList;
import java.util.List;

public class GenerateAllBinaryStrings {

    /*
    =====================================================================================
    PROBLEM: GENERATE ALL BINARY STRINGS
    -------------------------------------------------------------------------------------
    Generate all binary strings of length n (strings containing only 0s and 1s).
    
    Example:
    n = 2
    Output: "00", "01", "10", "11"
    
    n = 3
    Output: "000", "001", "010", "011", "100", "101", "110", "111"
    
    Approach:
    1. Use recursion to build strings by adding 0 or 1
    2. Base case: if length reaches n, add to result
    3. Recursive case: try adding 0 and then 1
    4. Total strings generated: 2^n
    
    Time Complexity: O(2^n) - generate all 2^n strings
    Space Complexity: O(n) - recursion depth, excluding output
    =====================================================================================
    */
    
    /**
     * Generate all binary strings of length n.
     * 
     * @param n length of binary strings
     * @return list of all binary strings
     */
    public static List<String> generateBinaryStrings(int n) {
        List<String> result = new ArrayList<>();
        generateHelper(n, "", result);
        return result;
    }
    
    /**
     * Helper method to generate binary strings recursively.
     * 
     * @param n remaining length to fill
     * @param current current string being built
     * @param result list to store results
     */
    private static void generateHelper(int n, String current, List<String> result) {
        // Base case: if string is complete
        if (n == 0) {
            result.add(current);
            return;
        }
        
        // Add 0 and recurse
        generateHelper(n - 1, current + "0", result);
        
        // Add 1 and recurse
        generateHelper(n - 1, current + "1", result);
    }
    
    /**
     * Alternative approach using StringBuilder for efficiency.
     * 
     * @param n length of binary strings
     * @return list of all binary strings
     */
    public static List<String> generateBinaryStringsOptimized(int n) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        generateHelperOptimized(n, sb, result);
        return result;
    }
    
    private static void generateHelperOptimized(int n, StringBuilder sb, List<String> result) {
        if (n == 0) {
            result.add(sb.toString());
            return;
        }
        
        // Add 0
        sb.append('0');
        generateHelperOptimized(n - 1, sb, result);
        sb.deleteCharAt(sb.length() - 1);
        
        // Add 1
        sb.append('1');
        generateHelperOptimized(n - 1, sb, result);
        sb.deleteCharAt(sb.length() - 1);
    }
    
    /**
     * Iterative approach using bit manipulation.
     * 
     * @param n length of binary strings
     * @return list of all binary strings
     */
    public static List<String> generateBinaryStringsIterative(int n) {
        List<String> result = new ArrayList<>();
        int total = 1 << n;  // 2^n
        
        for (int i = 0; i < total; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = n - 1; j >= 0; j--) {
                if ((i & (1 << j)) != 0) {
                    sb.append('1');
                } else {
                    sb.append('0');
                }
            }
            result.add(sb.toString());
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        // Test Case 1: n = 1
        System.out.println("=== n = 1 ===");
        List<String> result1 = generateBinaryStrings(1);
        System.out.println("All binary strings of length 1: " + result1);
        System.out.println("Count: " + result1.size() + " (expected: 2)");
        System.out.println();
        
        // Test Case 2: n = 2
        System.out.println("=== n = 2 ===");
        List<String> result2 = generateBinaryStrings(2);
        System.out.println("All binary strings of length 2: " + result2);
        System.out.println("Count: " + result2.size() + " (expected: 4)");
        System.out.println();
        
        // Test Case 3: n = 3
        System.out.println("=== n = 3 ===");
        List<String> result3 = generateBinaryStrings(3);
        System.out.println("All binary strings of length 3: " + result3);
        System.out.println("Count: " + result3.size() + " (expected: 8)");
        System.out.println();
        
        // Test Case 4: n = 4
        System.out.println("=== n = 4 ===");
        List<String> result4 = generateBinaryStrings(4);
        System.out.println("Count: " + result4.size() + " (expected: 16)");
        System.out.println("First 5: " + result4.subList(0, 5));
        System.out.println();
        
        // Comparison of approaches
        System.out.println("=== Approach Comparison (n=3) ===");
        List<String> opt = generateBinaryStringsOptimized(3);
        List<String> iter = generateBinaryStringsIterative(3);
        System.out.println("Optimized approach: " + opt);
        System.out.println("Iterative approach: " + iter);
        System.out.println("Both match? " + result3.equals(opt) + ", " + result3.equals(iter));
    }
}
