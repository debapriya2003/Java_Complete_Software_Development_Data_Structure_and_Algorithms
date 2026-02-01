package recursion_patternwise.subsequences;

import java.util.ArrayList;
import java.util.List;

public class CountSubsequencesWithSumK {

    /*
    =====================================================================================
    PROBLEM: COUNT ALL SUBSEQUENCES WITH SUM K
    -------------------------------------------------------------------------------------
    Given an array and a target sum K, count the number of subsequences that sum to K.
    
    Example:
    arr = [1, 2, 1], K = 2
    Subsequences with sum 2: [2], [1, 1]
    Count = 2
    
    arr = [1, 1, 1, 1], K = 2
    Subsequences with sum 2: [1, 1] at indices (0,1), (0,2), (0,3), (1,2), (1,3), (2,3)
    Count = 6
    
    Approach:
    Use recursion with backtracking:
    1. Pick current element and add to sum, recurse
    2. Don't pick current element, recurse
    3. Count valid subsequences where sum == K at the end
    
    Time Complexity: O(2^n) - explore all subsequences
    Space Complexity: O(n) - recursion call stack
    =====================================================================================
    */
    
    /**
     * Count subsequences with sum equal to K.
     * 
     * @param arr input array
     * @param K target sum
     * @return number of subsequences with sum K
     */
    public static long countSubsequencesWithSum(int[] arr, int K) {
        return countHelper(arr, 0, 0, K);
    }
    
    /**
     * Recursive helper to count subsequences.
     * 
     * Algorithm:
     * 1. Base case: when index reaches end of array
     *    - If current sum equals K, return 1 (found valid subsequence)
     *    - Else return 0
     * 2. Pick current element: add to sum, recurse with next index
     * 3. Don't pick current element: recurse without adding to sum
     * 4. Return sum of both cases
     * 
     * @param arr input array
     * @param index current index
     * @param currentSum current sum so far
     * @param target target sum K
     * @return count of valid subsequences
     */
    private static long countHelper(int[] arr, int index, int currentSum, int target) {
        // Base case: processed all elements
        if (index == arr.length) {
            // Valid subsequence found if sum equals target
            return currentSum == target ? 1 : 0;
        }
        
        // Pick current element
        long pick = countHelper(arr, index + 1, currentSum + arr[index], target);
        
        // Don't pick current element
        long notPick = countHelper(arr, index + 1, currentSum, target);
        
        return pick + notPick;
    }
    
    /**
     * Count using memoization (optimization).
     * Stores results of subproblems to avoid recalculation.
     * 
     * @param arr input array
     * @param K target sum
     * @return count of subsequences with sum K
     */
    public static long countSubsequencesWithSumMemo(int[] arr, int K) {
        // Map to store: (index, currentSum) -> count
        // Using simple recursion for clarity; can optimize with DP
        return countHelperMemo(arr, 0, 0, K, new java.util.HashMap<>());
    }
    
    private static long countHelperMemo(int[] arr, int index, int currentSum, int target, java.util.Map<String, Long> memo) {
        String key = index + "," + currentSum;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        
        if (index == arr.length) {
            return currentSum == target ? 1 : 0;
        }
        
        long pick = countHelperMemo(arr, index + 1, currentSum + arr[index], target, memo);
        long notPick = countHelperMemo(arr, index + 1, currentSum, target, memo);
        
        long result = pick + notPick;
        memo.put(key, result);
        return result;
    }
    
    /**
     * Get all subsequences with sum K (for verification).
     * 
     * @param arr input array
     * @param K target sum
     * @return list of subsequences with sum K
     */
    public static List<List<Integer>> getSubsequencesWithSum(int[] arr, int K) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        findSubsequences(arr, 0, 0, K, current, result);
        return result;
    }
    
    private static void findSubsequences(int[] arr, int index, int currentSum, int target, List<Integer> current, List<List<Integer>> result) {
        // Base case
        if (index == arr.length) {
            if (currentSum == target) {
                result.add(new ArrayList<>(current));
            }
            return;
        }
        
        // Pick
        current.add(arr[index]);
        findSubsequences(arr, index + 1, currentSum + arr[index], target, current, result);
        current.remove(current.size() - 1);
        
        // Don't pick
        findSubsequences(arr, index + 1, currentSum, target, current, result);
    }
    
    public static void main(String[] args) {
        // Test Case 1: Simple case
        System.out.println("=== Test Case 1 ===");
        int[] arr1 = {1, 2, 1};
        int K1 = 2;
        System.out.println("Array: [1, 2, 1], K = " + K1);
        long count1 = countSubsequencesWithSum(arr1, K1);
        System.out.println("Count: " + count1);
        System.out.println("Subsequences: " + getSubsequencesWithSum(arr1, K1));
        System.out.println();
        
        // Test Case 2: All ones
        System.out.println("=== Test Case 2 ===");
        int[] arr2 = {1, 1, 1, 1};
        int K2 = 2;
        System.out.println("Array: [1, 1, 1, 1], K = " + K2);
        long count2 = countSubsequencesWithSum(arr2, K2);
        System.out.println("Count: " + count2 + " (expected: 6 = C(4,2))");
        System.out.println();
        
        // Test Case 3: Single element
        System.out.println("=== Test Case 3 ===");
        int[] arr3 = {5};
        int K3 = 5;
        System.out.println("Array: [5], K = " + K3);
        long count3 = countSubsequencesWithSum(arr3, K3);
        System.out.println("Count: " + count3 + " (expected: 1)");
        System.out.println();
        
        // Test Case 4: No valid subsequence
        System.out.println("=== Test Case 4 ===");
        int[] arr4 = {2, 4, 6};
        int K4 = 5;
        System.out.println("Array: [2, 4, 6], K = " + K4);
        long count4 = countSubsequencesWithSum(arr4, K4);
        System.out.println("Count: " + count4 + " (expected: 0)");
        System.out.println();
        
        // Test Case 5: All valid
        System.out.println("=== Test Case 5 ===");
        int[] arr5 = {1, 2, 3};
        int K5 = 3;
        System.out.println("Array: [1, 2, 3], K = " + K5);
        long count5 = countSubsequencesWithSum(arr5, K5);
        System.out.println("Count: " + count5);
        System.out.println("Subsequences: " + getSubsequencesWithSum(arr5, K5));
        System.out.println();
        
        // Test Case 6: Larger array
        System.out.println("=== Test Case 6 ===");
        int[] arr6 = {1, 1, 1, 2, 2, 3};
        int K6 = 4;
        System.out.println("Array: [1, 1, 1, 2, 2, 3], K = " + K6);
        long count6 = countSubsequencesWithSum(arr6, K6);
        System.out.println("Count: " + count6);
        System.out.println("Subsequences: " + getSubsequencesWithSum(arr6, K6));
        System.out.println();
        
        // Test Case 7: K = 0 (empty subsequence)
        System.out.println("=== Test Case 7 ===");
        int[] arr7 = {1, 2, 3};
        int K7 = 0;
        System.out.println("Array: [1, 2, 3], K = " + K7);
        long count7 = countSubsequencesWithSum(arr7, K7);
        System.out.println("Count: " + count7 + " (expected: 1 - empty subsequence)");
    }
}
