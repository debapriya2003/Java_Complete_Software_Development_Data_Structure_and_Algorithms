package recursion_patternwise.all_combo_hard;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {

    /*
    =====================================================================================
    PROBLEM: PALINDROME PARTITIONING
    -------------------------------------------------------------------------------------
    Given a string, partition it such that every substring in the partition is a palindrome.
    Return all possible palindrome partitioning of the string.
    
    Example:
    s = "nitin"
    Output: [["n","i","t","i","n"], ["n","iti","n"]]
    
    s = "ab"
    Output: [["a","b"]]
    
    Approach:
    1. Use backtracking with index-based recursion
    2. At each index, try all possible partitions starting from that index
    3. If substring is palindrome, add to current partition and recurse
    4. Backtrack and try next partition point
    
    Time Complexity: O(N * 2^N) where N is length of string
    Space Complexity: O(N) - recursion depth
    =====================================================================================
    */
    
    /**
     * Get all palindrome partitions of string.
     * 
     * @param s input string
     * @return list of all palindrome partitions
     */
    public static List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        partitionHelper(s, 0, new ArrayList<>(), result);
        return result;
    }
    
    /**
     * Recursive helper for palindrome partitioning.
     * 
     * Algorithm:
     * 1. Base case: if index reaches end, add current partition to result
     * 2. For each possible end index from current to end:
     *    - Check if substring from current to end is palindrome
     *    - If yes, add to partition and recurse
     *    - Backtrack
     * 
     * @param s input string
     * @param index current starting index
     * @param current current partition
     * @param result all partitions
     */
    private static void partitionHelper(String s, int index, List<String> current, List<List<String>> result) {
        // Base case: partitioned entire string
        if (index == s.length()) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        // Try all possible partitions from index onwards
        for (int end = index; end < s.length(); end++) {
            // Check if substring is palindrome
            if (isPalindrome(s, index, end)) {
                // Add to current partition
                current.add(s.substring(index, end + 1));
                
                // Recurse for remaining string
                partitionHelper(s, end + 1, current, result);
                
                // Backtrack
                current.remove(current.size() - 1);
            }
        }
    }
    
    /**
     * Check if substring is palindrome.
     * 
     * @param s string
     * @param start start index
     * @param end end index
     * @return true if palindrome
     */
    private static boolean isPalindrome(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
    
    /**
     * Optimized version with palindrome memoization.
     * 
     * @param s input string
     * @return list of palindrome partitions
     */
    public static List<List<String>> partitionOptimized(String s) {
        int n = s.length();
        // Precompute palindrome matrix
        boolean[][] isPalin = new boolean[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i <= 1 || isPalin[i + 1][j - 1])) {
                    isPalin[i][j] = true;
                }
            }
        }
        
        List<List<String>> result = new ArrayList<>();
        partitionOptimizedHelper(s, 0, isPalin, new ArrayList<>(), result);
        return result;
    }
    
    private static void partitionOptimizedHelper(String s, int index, boolean[][] isPalin, List<String> current, List<List<String>> result) {
        if (index == s.length()) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        for (int end = index; end < s.length(); end++) {
            if (isPalin[index][end]) {
                current.add(s.substring(index, end + 1));
                partitionOptimizedHelper(s, end + 1, isPalin, current, result);
                current.remove(current.size() - 1);
            }
        }
    }
    
    public static void main(String[] args) {
        // Test Case 1: "nitin"
        System.out.println("=== Test Case 1: \"nitin\" ===");
        String s1 = "nitin";
        List<List<String>> result1 = partition(s1);
        System.out.println("String: " + s1);
        System.out.println("Palindrome partitions:");
        for (List<String> partition : result1) {
            System.out.println("  " + partition);
        }
        System.out.println("Count: " + result1.size());
        System.out.println();
        
        // Test Case 2: "ab"
        System.out.println("=== Test Case 2: \"ab\" ===");
        String s2 = "ab";
        List<List<String>> result2 = partition(s2);
        System.out.println("String: " + s2);
        System.out.println("Palindrome partitions: " + result2);
        System.out.println();
        
        // Test Case 3: "a"
        System.out.println("=== Test Case 3: \"a\" ===");
        String s3 = "a";
        List<List<String>> result3 = partition(s3);
        System.out.println("String: " + s3);
        System.out.println("Palindrome partitions: " + result3);
        System.out.println();
        
        // Test Case 4: "aab"
        System.out.println("=== Test Case 4: \"aab\" ===");
        String s4 = "aab";
        List<List<String>> result4 = partition(s4);
        System.out.println("String: " + s4);
        System.out.println("Palindrome partitions:");
        for (List<String> p : result4) {
            System.out.println("  " + p);
        }
        System.out.println();
        
        // Verify optimized approach
        System.out.println("=== Verification ===");
        List<List<String>> opt = partitionOptimized(s1);
        System.out.println("Optimized matches original? " + result1.equals(opt));
    }
}
