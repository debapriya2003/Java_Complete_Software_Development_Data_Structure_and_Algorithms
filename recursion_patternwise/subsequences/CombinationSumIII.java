package recursion_patternwise.subsequences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unused")
public class CombinationSumIII {

    /*
    =====================================================================================
    PROBLEM: COMBINATION SUM III
    -------------------------------------------------------------------------------------
    Find all combinations of k numbers that sum to n. Each number from 1 to 9 is used
    at most once.
    
    Constraints:
    - Use only digits 1-9
    - Each digit used at most once
    - Find combinations of exactly k numbers
    - Sum equals n
    
    Example 1:
    k = 3, n = 7
    Output: [[1,2,4]]
    Explanation: 1 + 2 + 4 = 7, and there's only one combination with k=3
    
    Example 2:
    k = 3, n = 9
    Output: [[1,2,6], [1,3,5], [2,3,4]]
    Explanation: Three combinations with k=3 and sum=9
    
    Approach:
    1. Use recursion to build combinations
    2. Start from digit 1 to 9
    3. Keep track of:
       - Current sum
       - Remaining count of numbers needed
       - Current combination
    4. At each step, try digits from start onwards
    5. Valid combination: when k numbers chosen and sum == n
    
    Time Complexity: O(C(9, k) * k) - combination of 9 taken k
    Space Complexity: O(k) - recursion depth
    =====================================================================================
    */
    
    /**
     * Find all combinations of k numbers from 1-9 that sum to n.
     * 
     * @param k number of digits in combination
     * @param n target sum
     * @return list of all valid combinations
     */
    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        combineHelper(1, k, n, new ArrayList<>(), result);
        return result;
    }
    
    /**
     * Recursive helper to find combinations.
     * 
     * Algorithm:
     * 1. Base case: if k numbers selected:
     *    - If sum equals n, found valid combination
     *    - Otherwise, not valid
     * 2. Base case: if current digit > 9, no more digits to choose
     * 3. For each digit from current to 9:
     *    - Add digit to combination
     *    - Recurse with next digit and k-1 count
     *    - Backtrack
     * 4. Pruning: skip if digit > remaining sum or too many iterations
     * 
     * @param start starting digit (1-9)
     * @param k remaining count of numbers needed
     * @param remaining remaining sum needed
     * @param current current combination
     * @param result all valid combinations
     */
    private static void combineHelper(int start, int k, int remaining, List<Integer> current, List<List<Integer>> result) {
        // Base case: found valid combination
        if (k == 0) {
            if (remaining == 0) {
                result.add(new ArrayList<>(current));
            }
            return;
        }
        
        // Base case: not enough digits left (start > 9 means exhausted all digits)
        if (start > 9) {
            return;
        }
        
        // Pruning: if remaining sum is negative, can't form valid combination
        if (remaining < 0) {
            return;
        }
        
        // Try each digit from start to 9
        for (int digit = start; digit <= 9; digit++) {
            // Pruning: if digit is greater than remaining, all further digits are larger
            if (digit > remaining) {
                break;
            }
            
            // Choose the digit
            current.add(digit);
            
            // Recurse with next digit (digit+1) and k-1
            combineHelper(digit + 1, k - 1, remaining - digit, current, result);
            
            // Backtrack
            current.remove(current.size() - 1);
        }
    }
    
    /**
     * Alternative implementation with different parameter order.
     * 
     * @param k number of digits
     * @param n target sum
     * @return list of combinations
     */
    public static List<List<Integer>> combinationSum3Alternative(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        backtrack(result, current, 1, k, n);
        return result;
    }
    
    private static void backtrack(List<List<Integer>> result, List<Integer> current, int start, int k, int n) {
        // Base case: collected k numbers
        if (k == 0) {
            if (n == 0) {
                result.add(new ArrayList<>(current));
            }
            return;
        }
        
        // Try digits from start to 9
        for (int digit = start; digit <= 9; digit++) {
            // Pruning: if digit > n, all further are larger
            if (digit > n) {
                break;
            }
            
            current.add(digit);
            backtrack(result, current, digit + 1, k - 1, n - digit);
            current.remove(current.size() - 1);
        }
    }
    
    public static void main(String[] args) {
        // Test Case 1: k=3, n=7
        System.out.println("=== Test Case 1: k=3, n=7 ===");
        List<List<Integer>> result1 = combinationSum3(3, 7);
        System.out.println("Combinations:");
        for (List<Integer> comb : result1) {
            System.out.println("  " + comb + " (sum: " + comb.stream().mapToInt(Integer::intValue).sum() + ")");
        }
        System.out.println("Count: " + result1.size());
        System.out.println();
        
        // Test Case 2: k=3, n=9
        System.out.println("=== Test Case 2: k=3, n=9 ===");
        List<List<Integer>> result2 = combinationSum3(3, 9);
        System.out.println("Combinations:");
        for (List<Integer> comb : result2) {
            System.out.println("  " + comb + " (sum: " + comb.stream().mapToInt(Integer::intValue).sum() + ")");
        }
        System.out.println("Count: " + result2.size() + " (expected: 3)");
        System.out.println();
        
        // Test Case 3: k=4, n=1 (impossible)
        System.out.println("=== Test Case 3: k=4, n=1 (Impossible) ===");
        List<List<Integer>> result3 = combinationSum3(4, 1);
        System.out.println("Combinations: " + result3 + " (expected empty)");
        System.out.println();
        
        // Test Case 4: k=2, n=18
        System.out.println("=== Test Case 4: k=2, n=18 ===");
        List<List<Integer>> result4 = combinationSum3(2, 18);
        System.out.println("Combinations:");
        for (List<Integer> comb : result4) {
            System.out.println("  " + comb + " (sum: " + comb.stream().mapToInt(Integer::intValue).sum() + ")");
        }
        System.out.println();
        
        // Test Case 5: k=1, n=5
        System.out.println("=== Test Case 5: k=1, n=5 ===");
        List<List<Integer>> result5 = combinationSum3(1, 5);
        System.out.println("Combinations: " + result5);
        System.out.println();
        
        // Test Case 6: k=9, n=45 (all digits)
        System.out.println("=== Test Case 6: k=9, n=45 (All Digits) ===");
        List<List<Integer>> result6 = combinationSum3(9, 45);
        System.out.println("Combinations: " + result6);
        System.out.println("Sum: " + result6.get(0).stream().mapToInt(Integer::intValue).sum());
        System.out.println();
        
        // Verification: Alternative approach gives same results
        System.out.println("=== Verification ===");
        List<List<Integer>> alt2 = combinationSum3Alternative(3, 9);
        System.out.println("Match with alternative? " + result2.equals(alt2));
    }
}
