package recursion_patternwise.subsequences;

public class CheckIfSubsequenceWithSumK {

    /*
    =====================================================================================
    PROBLEM: CHECK IF THERE EXISTS A SUBSEQUENCE WITH SUM K
    -------------------------------------------------------------------------------------
    Given an array, check if there exists any subsequence that sums to K.
    Return true/false instead of counting all.
    
    Example:
    arr = [3, 34, 4, 12, 5], K = 9
    Output: true (subsequence [4, 5])
    
    arr = [3, 34, 4, 12, 5], K = 30
    Output: false (no subsequence sums to 30)
    
    Approach:
    1. Use recursion with early termination
    2. As soon as we find one valid subsequence, return true
    3. Prune branches that exceed the target sum
    
    Time Complexity: O(2^n) worst case, but early exit helps
    Space Complexity: O(n) - recursion depth
    =====================================================================================
    */
    
    /**
     * Check if subsequence with sum K exists.
     * Uses early termination for efficiency.
     * 
     * @param arr input array
     * @param K target sum
     * @return true if subsequence with sum K exists
     */
    public static boolean existsSubsequenceWithSum(int[] arr, int K) {
        return checkHelper(arr, 0, 0, K);
    }
    
    /**
     * Recursive helper with early termination.
     * 
     * Algorithm:
     * 1. Base case: if current sum equals K, return true (found)
     * 2. Base case: if index reaches end without finding, return false
     * 3. Try picking current element, if true, return true
     * 4. Try not picking current element, return result
     * 5. Early pruning: if sum exceeds K, can't form K (with positive numbers)
     * 
     * @param arr input array
     * @param index current index
     * @param currentSum current sum
     * @param target target sum
     * @return true if subsequence exists
     */
    private static boolean checkHelper(int[] arr, int index, int currentSum, int target) {
        // Found valid subsequence
        if (currentSum == target) {
            return true;
        }
        
        // Processed all elements without finding
        if (index == arr.length) {
            return false;
        }
        
        // Pruning: if sum exceeds target, don't explore further
        // (only valid if all elements are positive)
        if (currentSum > target) {
            return false;
        }
        
        // Pick current element
        if (checkHelper(arr, index + 1, currentSum + arr[index], target)) {
            return true;
        }
        
        // Don't pick current element
        return checkHelper(arr, index + 1, currentSum, target);
    }
    
    /**
     * Check using DP (bottom-up approach).
     * More efficient for large arrays.
     * 
     * @param arr input array
     * @param K target sum
     * @return true if subsequence with sum K exists
     */
    public static boolean existsSubsequenceWithSumDP(int[] arr, int K) {
        // dp[i] = true if sum i is possible
        boolean[] dp = new boolean[K + 1];
        dp[0] = true;  // Empty subsequence has sum 0
        
        for (int num : arr) {
            // Traverse from right to avoid using same element twice
            for (int i = K; i >= num; i--) {
                dp[i] = dp[i] || dp[i - num];
            }
        }
        
        return dp[K];
    }
    
    /**
     * Get a valid subsequence with sum K (if exists).
     * 
     * @param arr input array
     * @param K target sum
     * @return subsequence as array, or empty if not exists
     */
    public static int[] getSubsequenceWithSum(int[] arr, int K) {
        java.util.List<Integer> subseq = new java.util.ArrayList<>();
        if (findSubsequence(arr, 0, 0, K, subseq)) {
            int[] result = new int[subseq.size()];
            for (int i = 0; i < subseq.size(); i++) {
                result[i] = subseq.get(i);
            }
            return result;
        }
        return new int[0];
    }
    
    private static boolean findSubsequence(int[] arr, int index, int currentSum, int target, java.util.List<Integer> current) {
        if (currentSum == target) {
            return true;
        }
        
        if (index == arr.length) {
            return false;
        }
        
        if (currentSum > target) {
            return false;
        }
        
        // Pick current element
        current.add(arr[index]);
        if (findSubsequence(arr, index + 1, currentSum + arr[index], target, current)) {
            return true;
        }
        current.remove(current.size() - 1);
        
        // Don't pick current element
        return findSubsequence(arr, index + 1, currentSum, target, current);
    }
    
    /**
     * Print array for display.
     */
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
        // Test Case 1: Exists
        System.out.println("=== Test Case 1: Exists ===");
        int[] arr1 = {3, 34, 4, 12, 5};
        int K1 = 9;
        System.out.println("Array: " + arrayToString(arr1) + ", K = " + K1);
        System.out.println("Exists (Recursive): " + existsSubsequenceWithSum(arr1, K1));
        System.out.println("Exists (DP): " + existsSubsequenceWithSumDP(arr1, K1));
        System.out.println("Subsequence: " + arrayToString(getSubsequenceWithSum(arr1, K1)));
        System.out.println();
        
        // Test Case 2: Does not exist
        System.out.println("=== Test Case 2: Does Not Exist ===");
        int[] arr2 = {3, 34, 4, 12, 5};
        int K2 = 30;
        System.out.println("Array: " + arrayToString(arr2) + ", K = " + K2);
        System.out.println("Exists (Recursive): " + existsSubsequenceWithSum(arr2, K2));
        System.out.println("Exists (DP): " + existsSubsequenceWithSumDP(arr2, K2));
        System.out.println("Subsequence: " + arrayToString(getSubsequenceWithSum(arr2, K2)));
        System.out.println();
        
        // Test Case 3: K = 0 (empty subsequence)
        System.out.println("=== Test Case 3: K = 0 ===");
        int[] arr3 = {1, 2, 3};
        int K3 = 0;
        System.out.println("Array: " + arrayToString(arr3) + ", K = " + K3);
        System.out.println("Exists (Recursive): " + existsSubsequenceWithSum(arr3, K3));
        System.out.println("Exists (DP): " + existsSubsequenceWithSumDP(arr3, K3));
        System.out.println();
        
        // Test Case 4: Single element equals K
        System.out.println("=== Test Case 4: Single Element ===");
        int[] arr4 = {5};
        int K4 = 5;
        System.out.println("Array: " + arrayToString(arr4) + ", K = " + K4);
        System.out.println("Exists (Recursive): " + existsSubsequenceWithSum(arr4, K4));
        System.out.println("Exists (DP): " + existsSubsequenceWithSumDP(arr4, K4));
        System.out.println();
        
        // Test Case 5: Multiple elements sum
        System.out.println("=== Test Case 5: Multiple Elements ===");
        int[] arr5 = {1, 2, 7, 1, 5};
        int K5 = 10;
        System.out.println("Array: " + arrayToString(arr5) + ", K = " + K5);
        System.out.println("Exists (Recursive): " + existsSubsequenceWithSum(arr5, K5));
        System.out.println("Exists (DP): " + existsSubsequenceWithSumDP(arr5, K5));
        System.out.println("Subsequence: " + arrayToString(getSubsequenceWithSum(arr5, K5)));
        System.out.println();
        
        // Test Case 6: All elements needed
        System.out.println("=== Test Case 6: All Elements Needed ===");
        int[] arr6 = {1, 2, 3};
        int K6 = 6;
        System.out.println("Array: " + arrayToString(arr6) + ", K = " + K6);
        System.out.println("Exists (Recursive): " + existsSubsequenceWithSum(arr6, K6));
        System.out.println("Exists (DP): " + existsSubsequenceWithSumDP(arr6, K6));
        System.out.println("Subsequence: " + arrayToString(getSubsequenceWithSum(arr6, K6)));
    }
}
