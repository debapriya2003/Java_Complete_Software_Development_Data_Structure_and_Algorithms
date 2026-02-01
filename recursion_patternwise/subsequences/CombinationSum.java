package recursion_patternwise.subsequences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum {

    /*
    =====================================================================================
    PROBLEM: COMBINATION SUM
    -------------------------------------------------------------------------------------
    Given an array of distinct integers and a target sum, return all combinations 
    where the sum equals the target. Each number can be used unlimited times.
    
    Example:
    candidates = [2, 3, 6, 7], target = 7
    Output: [[2,2,3], [7]]
    Explanation: 2 and 3 sum to 7, and 7 is itself a valid target
    
    Example 2:
    candidates = [2, 3, 5], target = 8
    Output: [[2,2,2,2], [2,3,3], [3,5]]
    
    Key Difference from Subset Sum:
    - Elements can be reused multiple times
    - Order doesn't matter (combinations, not permutations)
    
    Approach:
    1. Use index-based recursion (loop from current index to end)
    2. Since elements are reusable, don't move to next element immediately
    3. If sum exceeds target, prune that branch
    4. When sum equals target, add to result
    
    Time Complexity: O(2^(T/M)) where T is target, M is minimum element
    Space Complexity: O(T/M) - recursion depth
    =====================================================================================
    */
    
    /**
     * Find all combinations that sum to target.
     * Each number can be used unlimited times.
     * 
     * @param candidates array of distinct integers
     * @param target target sum
     * @return list of combinations summing to target
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);  // Sort for optimization
        combineHelper(candidates, 0, target, new ArrayList<>(), result);
        return result;
    }
    
    /**
     * Recursive helper for combination sum.
     * 
     * Algorithm:
     * 1. If target becomes 0, found valid combination
     * 2. If target becomes negative, invalid path
     * 3. For each candidate starting from index:
     *    - If candidate <= remaining target, use it and recurse from same index
     *    - This allows reusing same element
     * 4. Backtrack and try next candidate
     * 
     * @param candidates sorted array
     * @param index starting index
     * @param remaining remaining sum needed
     * @param current current combination
     * @param result all valid combinations
     */
    private static void combineHelper(int[] candidates, int index, int remaining, List<Integer> current, List<List<Integer>> result) {
        // Base case: found valid combination
        if (remaining == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        // Base case: remaining is negative (invalid)
        if (remaining < 0) {
            return;
        }
        
        // Try each candidate from index onwards
        for (int i = index; i < candidates.length; i++) {
            int candidate = candidates[i];
            
            // Pruning: if candidate > remaining, all further candidates are larger
            if (candidate > remaining) {
                break;
            }
            
            // Choose the candidate
            current.add(candidate);
            
            // Recurse from same index (allows reuse)
            combineHelper(candidates, i, remaining - candidate, current, result);
            
            // Backtrack
            current.remove(current.size() - 1);
        }
    }
    
    /**
     * Alternative implementation with different structure.
     * 
     * @param candidates array of distinct integers
     * @param target target sum
     * @return list of combinations
     */
    public static List<List<Integer>> combinationSumAlternative(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        Arrays.sort(candidates);
        
        backtrack(result, current, candidates, target, 0);
        return result;
    }
    
    private static void backtrack(List<List<Integer>> result, List<Integer> current, int[] candidates, int remaining, int start) {
        if (remaining < 0) {
            return;
        }
        
        if (remaining == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        for (int i = start; i < candidates.length; i++) {
            current.add(candidates[i]);
            // Key: stay at i (not i+1) to allow reuse
            backtrack(result, current, candidates, remaining - candidates[i], i);
            current.remove(current.size() - 1);
        }
    }
    
    public static void main(String[] args) {
        // Test Case 1: Basic example
        System.out.println("=== Test Case 1 ===");
        int[] candidates1 = {2, 3, 6, 7};
        int target1 = 7;
        System.out.println("Candidates: " + Arrays.toString(candidates1) + ", Target: " + target1);
        List<List<Integer>> result1 = combinationSum(candidates1, target1);
        System.out.println("Combinations:");
        for (List<Integer> comb : result1) {
            System.out.println("  " + comb);
        }
        System.out.println();
        
        // Test Case 2: Multiple same elements in result
        System.out.println("=== Test Case 2 ===");
        int[] candidates2 = {2, 3, 5};
        int target2 = 8;
        System.out.println("Candidates: " + Arrays.toString(candidates2) + ", Target: " + target2);
        List<List<Integer>> result2 = combinationSum(candidates2, target2);
        System.out.println("Combinations:");
        for (List<Integer> comb : result2) {
            System.out.println("  " + comb);
        }
        System.out.println();
        
        // Test Case 3: Single candidate
        System.out.println("=== Test Case 3 ===");
        int[] candidates3 = {2};
        int target3 = 1;
        System.out.println("Candidates: " + Arrays.toString(candidates3) + ", Target: " + target3);
        List<List<Integer>> result3 = combinationSum(candidates3, target3);
        System.out.println("Combinations: " + result3 + " (expected empty)");
        System.out.println();
        
        // Test Case 4: Can use same element multiple times
        System.out.println("=== Test Case 4 ===");
        int[] candidates4 = {2};
        int target4 = 1;
        System.out.println("Candidates: " + Arrays.toString(candidates4) + ", Target: " + target4);
        List<List<Integer>> result4 = combinationSum(candidates4, target4);
        System.out.println("Combinations: " + result4);
        System.out.println();
        
        // Test Case 5: Larger values
        System.out.println("=== Test Case 5 ===");
        int[] candidates5 = {10};
        int target5 = 1;
        System.out.println("Candidates: " + Arrays.toString(candidates5) + ", Target: " + target5);
        List<List<Integer>> result5 = combinationSum(candidates5, target5);
        System.out.println("Combinations: " + result5);
        System.out.println();
        
        // Test Case 6: Many combinations
        System.out.println("=== Test Case 6 ===");
        int[] candidates6 = {1, 2, 3};
        int target6 = 4;
        System.out.println("Candidates: " + Arrays.toString(candidates6) + ", Target: " + target6);
        List<List<Integer>> result6 = combinationSum(candidates6, target6);
        System.out.println("Combinations:");
        for (List<Integer> comb : result6) {
            System.out.println("  " + comb);
        }
    }
}
