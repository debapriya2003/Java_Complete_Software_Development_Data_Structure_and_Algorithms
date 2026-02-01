package recursion_patternwise.subsequences;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SuppressWarnings("unused")
public class SubsetSumI {

    /*
    =====================================================================================
    PROBLEM: SUBSET SUM - I (Check if subset with sum K exists)
    -------------------------------------------------------------------------------------
    Given an array and a target sum K, determine if there exists a subset that sums to K.
    Return true if exists, false otherwise. Each element can be used at most once.
    
    Example:
    arr = [3, 34, 4, 12, 5], K = 9
    Output: true (subset [4, 5])
    
    arr = [3, 34, 4, 12, 5], K = 30
    Output: false
    
    Note: This is different from Subset Sum II which requires finding all subsets.
    
    Approach 1: Recursion (Pick/Not-Pick)
    - Time: O(2^n), Space: O(n)
    
    Approach 2: Dynamic Programming
    - Create DP array where dp[i] = true if sum i is possible
    - Time: O(n*K), Space: O(K)
    - More efficient for large n and reasonable K
    
    Time Complexity: O(2^n) recursive, O(n*K) DP
    Space Complexity: O(n) recursive, O(K) DP
    =====================================================================================
    */
    
    /**
     * Check if subset with sum K exists using recursion.
     * 
     * @param arr input array
     * @param K target sum
     * @return true if subset exists, false otherwise
     */
    public static boolean subsetSumRecursive(int[] arr, int K) {
        return checkSubset(arr, 0, K);
    }
    
    /**
     * Recursive helper to check subset existence.
     * 
     * Algorithm:
     * 1. Base case: if K becomes 0, found valid subset
     * 2. Base case: if index reaches end or K becomes negative, return false
     * 3. Pick current element and recurse with K - arr[index]
     * 4. If pick succeeds, return true
     * 5. Otherwise, don't pick and recurse
     * 
     * @param arr input array
     * @param index current index
     * @param remaining remaining sum
     * @return true if subset with remaining sum exists
     */
    private static boolean checkSubset(int[] arr, int index, int remaining) {
        // Base case: found valid subset
        if (remaining == 0) {
            return true;
        }
        
        // Base case: reached end without finding
        if (index == arr.length || remaining < 0) {
            return false;
        }
        
        // Pick current element
        if (checkSubset(arr, index + 1, remaining - arr[index])) {
            return true;
        }
        
        // Don't pick current element
        return checkSubset(arr, index + 1, remaining);
    }
    
    /**
     * Check if subset with sum K exists using DP.
     * More efficient approach.
     * 
     * Algorithm:
     * 1. Create DP array of size K+1
     * 2. dp[i] = true if sum i is achievable
     * 3. Base: dp[0] = true (empty subset)
     * 4. For each number in array:
     *    - Traverse from K to number (avoid using same element twice)
     *    - If dp[i - number] is true, then dp[i] becomes true
     * 
     * @param arr input array
     * @param K target sum
     * @return true if subset exists
     */
    public static boolean subsetSumDP(int[] arr, int K) {
        // dp[i] = true if sum i is possible
        boolean[] dp = new boolean[K + 1];
        dp[0] = true;  // Empty subset has sum 0
        
        // Process each element
        for (int num : arr) {
            // Traverse from right to left to avoid using same element twice
            for (int i = K; i >= num; i--) {
                dp[i] = dp[i] || dp[i - num];
            }
        }
        
        return dp[K];
    }
    
    /**
     * Get a subset with sum K (if exists).
     * 
     * @param arr input array
     * @param K target sum
     * @return subset as array, or empty if not exists\n     */
    public static int[] getSubsetWithSum(int[] arr, int K) {
        List<Integer> subset = new ArrayList<>();
        if (findSubset(arr, 0, K, subset)) {
            int[] result = new int[subset.size()];
            for (int i = 0; i < subset.size(); i++) {
                result[i] = subset.get(i);
            }
            return result;
        }
        return new int[0];
    }
    
    private static boolean findSubset(int[] arr, int index, int remaining, List<Integer> current) {
        if (remaining == 0) {
            return true;
        }
        
        if (index == arr.length || remaining < 0) {
            return false;
        }
        
        current.add(arr[index]);
        if (findSubset(arr, index + 1, remaining - arr[index], current)) {
            return true;
        }
        current.remove(current.size() - 1);
        
        return findSubset(arr, index + 1, remaining, current);
    }
    
    private static String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
    
    public static void main(String[] args) {
        // Test Case 1: Subset exists
        System.out.println("=== Test Case 1: Subset Exists ===");
        int[] arr1 = {3, 34, 4, 12, 5};
        int K1 = 9;
        System.out.println("Array: " + arrayToString(arr1) + ", K = " + K1);
        System.out.println("Exists (Recursive): " + subsetSumRecursive(arr1, K1));
        System.out.println("Exists (DP): " + subsetSumDP(arr1, K1));
        System.out.println("Subset: " + arrayToString(getSubsetWithSum(arr1, K1)));
        System.out.println();
        
        // Test Case 2: Subset doesn't exist
        System.out.println("=== Test Case 2: Subset Does Not Exist ===");
        int[] arr2 = {3, 34, 4, 12, 5};
        int K2 = 30;
        System.out.println("Array: " + arrayToString(arr2) + ", K = " + K2);
        System.out.println("Exists (Recursive): " + subsetSumRecursive(arr2, K2));
        System.out.println("Exists (DP): " + subsetSumDP(arr2, K2));
        System.out.println();
        
        // Test Case 3: K = 0 (empty subset)
        System.out.println("=== Test Case 3: K = 0 (Empty Subset) ===");
        int[] arr3 = {1, 2, 3};
        int K3 = 0;
        System.out.println("Array: " + arrayToString(arr3) + ", K = " + K3);
        System.out.println("Exists (Recursive): " + subsetSumRecursive(arr3, K3));
        System.out.println("Exists (DP): " + subsetSumDP(arr3, K3));
        System.out.println();
        
        // Test Case 4: Single element
        System.out.println("=== Test Case 4: Single Element ===");
        int[] arr4 = {5};
        int K4 = 5;
        System.out.println("Array: " + arrayToString(arr4) + ", K = " + K4);
        System.out.println("Exists (Recursive): " + subsetSumRecursive(arr4, K4));
        System.out.println("Exists (DP): " + subsetSumDP(arr4, K4));
        System.out.println();
        
        // Test Case 5: Larger array
        System.out.println("=== Test Case 5: Larger Array ===");
        int[] arr5 = {1, 5, 11, 5};
        int K5 = 11;
        System.out.println("Array: " + arrayToString(arr5) + ", K = " + K5);
        System.out.println("Exists (Recursive): " + subsetSumRecursive(arr5, K5));
        System.out.println("Exists (DP): " + subsetSumDP(arr5, K5));
        System.out.println("Subset: " + arrayToString(getSubsetWithSum(arr5, K5)));
        System.out.println();
        
        // Test Case 6: All elements needed
        System.out.println("=== Test Case 6: All Elements Needed ===");
        int[] arr6 = {1, 2, 3, 4};
        int K6 = 10;
        System.out.println("Array: " + arrayToString(arr6) + ", K = " + K6);
        System.out.println("Exists (Recursive): " + subsetSumRecursive(arr6, K6));
        System.out.println("Exists (DP): " + subsetSumDP(arr6, K6));
        System.out.println("Subset: " + arrayToString(getSubsetWithSum(arr6, K6)));
    }
}
