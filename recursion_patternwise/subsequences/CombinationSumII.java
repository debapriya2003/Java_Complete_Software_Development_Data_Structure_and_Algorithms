package recursion_patternwise.subsequences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSumII {

    /*
    =====================================================================================
    PROBLEM: COMBINATION SUM II
    -------------------------------------------------------------------------------------
    Given an array with duplicates and a target sum, return all unique combinations
    where sum equals target. Each number can be used only once.
    
    Differences from Combination Sum I:
    - Array contains duplicates
    - Each element can be used AT MOST once
    - Result should not have duplicate combinations
    
    Example:
    candidates = [10, 1, 2, 7, 6, 1, 5], target = 8
    Output: [[1,1,6], [1,2,5], [1,7], [2,6]]
    
    Example 2:
    candidates = [2, 5, 2, 1, 2], target = 5
    Output: [[1,2], [5]]
    
    Approach:
    1. Sort array (important for duplicate handling)
    2. Use index-based recursion (move to next index after using)
    3. Skip duplicates: if current element == previous && already used previous, skip
    4. Pass index+1 to prevent reusing same element
    
    Time Complexity: O(2^n) - explore all combinations
    Space Complexity: O(n) - recursion depth
    =====================================================================================
    */
    
    /**
     * Find all unique combinations that sum to target.
     * Each number can be used at most once.
     * 
     * @param candidates array with possible duplicates
     * @param target target sum
     * @return list of unique combinations
     */
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);  // Sort to group duplicates and enable pruning
        combineHelper(candidates, 0, target, new ArrayList<>(), result);
        return result;
    }
    
    /**
     * Recursive helper for combination sum II.
     * 
     * Algorithm:
     * 1. If target becomes 0, found valid combination
     * 2. If target becomes negative, invalid path
     * 3. For each candidate from index onwards:
     *    - Skip duplicates: if current == previous && we're not at start, skip
     *    - Use the candidate and recurse from index+1 (no reuse)
     *    - Backtrack
     * 4. Pruning: if candidate > remaining, break (array is sorted)
     * 
     * @param candidates sorted array with duplicates
     * @param index starting index
     * @param remaining remaining sum needed
     * @param current current combination
     * @param result all valid unique combinations
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
            
            // Skip duplicates: if this element is same as previous and we just tried previous
            // This avoids duplicate combinations
            if (i > index && candidates[i] == candidates[i - 1]) {
                continue;
            }
            
            // Choose the candidate
            current.add(candidate);
            
            // Recurse from next index (no reuse of same element)
            combineHelper(candidates, i + 1, remaining - candidate, current, result);
            
            // Backtrack
            current.remove(current.size() - 1);
        }
    }
    
    /**
     * Alternative implementation using explicit duplicate tracking.
     * 
     * @param candidates array with duplicates
     * @param target target sum
     * @return list of unique combinations
     */
    public static List<List<Integer>> combinationSum2Alternative(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(result, new ArrayList<>(), candidates, target, 0);
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
            // Skip duplicates
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }
            
            current.add(candidates[i]);
            // Move to i+1 (no reuse)
            backtrack(result, current, candidates, remaining - candidates[i], i + 1);
            current.remove(current.size() - 1);
        }
    }
    
    public static void main(String[] args) {
        // Test Case 1: With duplicates
        System.out.println("=== Test Case 1: With Duplicates ===");
        int[] candidates1 = {10, 1, 2, 7, 6, 1, 5};
        int target1 = 8;
        System.out.println("Candidates: " + Arrays.toString(candidates1));
        System.out.println("Target: " + target1);
        List<List<Integer>> result1 = combinationSum2(candidates1, target1);
        System.out.println("Unique combinations:");
        for (List<Integer> comb : result1) {
            System.out.println("  " + comb);
        }
        System.out.println();
        
        // Test Case 2: All same elements
        System.out.println("=== Test Case 2: All Same Elements ===");
        int[] candidates2 = {2, 2, 2, 2, 2, 2};
        int target2 = 8;
        System.out.println("Candidates: " + Arrays.toString(candidates2));
        System.out.println("Target: " + target2);
        List<List<Integer>> result2 = combinationSum2(candidates2, target2);
        System.out.println("Unique combinations: " + result2);
        System.out.println();
        
        // Test Case 3: No valid combinations
        System.out.println("=== Test Case 3: No Valid Combinations ===");
        int[] candidates3 = {1, 2, 3};
        int target3 = 10;
        System.out.println("Candidates: " + Arrays.toString(candidates3));
        System.out.println("Target: " + target3);
        List<List<Integer>> result3 = combinationSum2(candidates3, target3);
        System.out.println("Unique combinations: " + result3);
        System.out.println();
        
        // Test Case 4: Single valid combination
        System.out.println("=== Test Case 4: Single Valid Combination ===");
        int[] candidates4 = {1, 1, 1, 5};
        int target4 = 6;
        System.out.println("Candidates: " + Arrays.toString(candidates4));
        System.out.println("Target: " + target4);
        List<List<Integer>> result4 = combinationSum2(candidates4, target4);
        System.out.println("Unique combinations:");
        for (List<Integer> comb : result4) {
            System.out.println("  " + comb);
        }
        System.out.println();
        
        // Test Case 5: More duplicates
        System.out.println("=== Test Case 5: More Duplicates ===");
        int[] candidates5 = {2, 5, 2, 1, 2};
        int target5 = 5;
        System.out.println("Candidates: " + Arrays.toString(candidates5));
        System.out.println("Target: " + target5);
        List<List<Integer>> result5 = combinationSum2(candidates5, target5);
        System.out.println("Unique combinations:");
        for (List<Integer> comb : result5) {
            System.out.println("  " + comb);
        }
        System.out.println();
        
        // Test Case 6: Verify no duplicates in result
        System.out.println("=== Test Case 6: Verify Uniqueness ===");
        int[] candidates6 = {4, 4, 4, 1, 1, 1, 1};
        int target6 = 5;
        System.out.println("Candidates: " + Arrays.toString(candidates6));
        System.out.println("Target: " + target6);
        List<List<Integer>> result6 = combinationSum2(candidates6, target6);
        System.out.println("Unique combinations: " + result6);
        System.out.println("All unique? " + (result6.stream().distinct().count() == result6.size()));
    }
}
