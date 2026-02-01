package recursion_patternwise.subsequences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SubsetSumII {

    /*
    =====================================================================================
    PROBLEM: SUBSET SUM - II (Find all unique subsets with given sum)
    -------------------------------------------------------------------------------------
    Given an array with duplicates, find all unique subsets that sum to K.
    Each element can be used at most once in each subset.
    
    Example:
    arr = [4, 1, 0, 2, 2, 4], K = 5
    Output: [[0,1,4], [1,2,2], [1,4], [2,2,1], [0,2,4,4]...]
    Note: Return only unique subsets (no duplicate subsets)
    
    Difference from Subset Sum I:
    - Need to return all subsets, not just check existence
    - Array has duplicates, need to avoid duplicate subsets
    - Each element at most once (no unlimited reuse)
    
    Approach:
    1. Sort array to group duplicates
    2. Use index-based recursion with duplicate skipping
    3. Use Set to avoid storing duplicate subsets
    4. At each step, skip duplicate elements
    
    Time Complexity: O(n * 2^n) - generate all subsets
    Space Complexity: O(n) - recursion depth
    =====================================================================================
    */
    
    /**
     * Find all unique subsets with given sum.
     * 
     * @param arr input array (may have duplicates)
     * @param K target sum
     * @return list of unique subsets with sum K
     */
    public static List<List<Integer>> subsetSumII(int[] arr, int K) {
        Arrays.sort(arr);  // Sort to group duplicates
        Set<List<Integer>> resultSet = new HashSet<>();  // To avoid duplicates
        List<List<Integer>> result = new ArrayList<>();
        
        findSubsets(arr, 0, K, new ArrayList<>(), resultSet);
        result.addAll(resultSet);
        
        return result;
    }
    
    /**
     * Recursive helper using Set to avoid duplicates.
     * 
     * Algorithm:
     * 1. Base case: if sum becomes 0, add current subset to set
     * 2. Base case: if sum becomes negative or index reaches end, return
     * 3. For each element from current index:
     *    - Pick it and recurse
     *    - Backtrack
     * 4. Set automatically handles duplicate subsets
     * 
     * @param arr sorted array
     * @param index current index
     * @param remaining remaining sum
     * @param current current subset
     * @param result set of unique subsets
     */
    private static void findSubsets(int[] arr, int index, int remaining, List<Integer> current, Set<List<Integer>> result) {
        if (remaining == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        if (remaining < 0 || index == arr.length) {
            return;
        }
        
        for (int i = index; i < arr.length; i++) {
            if (arr[i] > remaining) {
                break;  // Pruning: all further elements are larger
            }
            
            current.add(arr[i]);
            findSubsets(arr, i + 1, remaining - arr[i], current, result);
            current.remove(current.size() - 1);
        }
    }
    
    /**
     * Alternative approach using explicit duplicate tracking.
     * More efficient than using Set.
     * 
     * @param arr input array
     * @param K target sum
     * @return list of unique subsets
     */
    public static List<List<Integer>> subsetSumIIAlternative(int[] arr, int K) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(arr);
        backtrack(arr, 0, K, new ArrayList<>(), result);
        return result;
    }
    
    private static void backtrack(int[] arr, int index, int remaining, List<Integer> current, List<List<Integer>> result) {
        if (remaining == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        if (remaining < 0 || index == arr.length) {
            return;
        }
        
        Set<Integer> used = new HashSet<>();  // Track used values in this iteration
        
        for (int i = index; i < arr.length; i++) {
            int num = arr[i];
            
            // Skip if we've already used this value at this level
            if (used.contains(num)) {
                continue;
            }
            used.add(num);
            
            if (num > remaining) {
                break;
            }
            
            current.add(num);
            backtrack(arr, i + 1, remaining - num, current, result);
            current.remove(current.size() - 1);
        }
    }
    
    public static void main(String[] args) {
        // Test Case 1: With duplicates
        System.out.println("=== Test Case 1: With Duplicates ===");
        int[] arr1 = {4, 1, 0, 2, 2, 4};
        int K1 = 5;
        System.out.println("Array: " + Arrays.toString(arr1) + ", K = " + K1);
        List<List<Integer>> result1 = subsetSumII(arr1, K1);
        System.out.println("Unique subsets with sum " + K1 + ":");
        for (List<Integer> subset : result1) {
            System.out.println("  " + subset);
        }
        System.out.println("Count: " + result1.size());
        System.out.println();
        
        // Test Case 2: All same elements
        System.out.println("=== Test Case 2: All Same Elements ===");
        int[] arr2 = {2, 2, 2, 2};
        int K2 = 4;
        System.out.println("Array: " + Arrays.toString(arr2) + ", K = " + K2);
        List<List<Integer>> result2 = subsetSumII(arr2, K2);
        System.out.println("Unique subsets:");
        for (List<Integer> subset : result2) {
            System.out.println("  " + subset);
        }
        System.out.println();
        
        // Test Case 3: No valid subsets
        System.out.println("=== Test Case 3: No Valid Subsets ===");
        int[] arr3 = {5, 6, 7};
        int K3 = 2;
        System.out.println("Array: " + Arrays.toString(arr3) + ", K = " + K3);
        List<List<Integer>> result3 = subsetSumII(arr3, K3);
        System.out.println("Unique subsets: " + result3);
        System.out.println();
        
        // Test Case 4: Single element
        System.out.println("=== Test Case 4: Single Element ===");
        int[] arr4 = {3};
        int K4 = 3;
        System.out.println("Array: " + Arrays.toString(arr4) + ", K = " + K4);
        List<List<Integer>> result4 = subsetSumII(arr4, K4);
        System.out.println("Unique subsets:");
        for (List<Integer> subset : result4) {
            System.out.println("  " + subset);
        }
        System.out.println();
        
        // Test Case 5: K = 0 (empty subset)
        System.out.println("=== Test Case 5: K = 0 (Empty Subset) ===");
        int[] arr5 = {1, 0, 2, 0};
        int K5 = 0;
        System.out.println("Array: " + Arrays.toString(arr5) + ", K = " + K5);
        List<List<Integer>> result5 = subsetSumII(arr5, K5);
        System.out.println("Unique subsets:");
        for (List<Integer> subset : result5) {
            System.out.println("  " + subset);
        }
        System.out.println();
        
        // Test Case 6: Verify uniqueness
        System.out.println("=== Test Case 6: Verify Uniqueness ===");
        int[] arr6 = {1, 1, 2, 2, 2};
        int K6 = 3;
        System.out.println("Array: " + Arrays.toString(arr6) + ", K = " + K6);
        List<List<Integer>> result6 = subsetSumIIAlternative(arr6, K6);
        System.out.println("Unique subsets:");
        for (List<Integer> subset : result6) {
            System.out.println("  " + subset);
        }
        System.out.println("All unique? " + (result6.stream().distinct().count() == result6.size()));
    }
}
