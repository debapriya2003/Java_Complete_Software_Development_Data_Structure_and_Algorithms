package arrays.easy;
import java.util.HashMap;

public class LongestSubarraySumKPositivesNegatives {

    /*
    =====================================================================================
    PROBLEM: LONGEST SUBARRAY WITH SUM K (POSITIVES AND NEGATIVES)
    -------------------------------------------------------------------------------------
    Given an array containing both positive and negative integers and an integer K, the
    task is to find the length of the longest contiguous subarray whose sum is exactly K.

    The Sliding Window approach fails when negative numbers are present because the sum
    does not increase or decrease monotonically. Therefore, we use the Prefix Sum and
    HashMap technique to efficiently solve this problem in linear time.
    =====================================================================================
    */

    public static void main(String[] args) {

        int[] arr = {1, -1, 5, -2, 3};
        int k = 3;

        int longestLength = longestSubarrayWithSumK(arr, k);

        System.out.println(
                "Length of longest subarray with sum " + k + " = " + longestLength
        );
    }

    /*
    =====================================================================================
    FUNCTION: longestSubarrayWithSumK
    -------------------------------------------------------------------------------------
    This function finds the longest subarray with sum equal to K using prefix sums and a
    HashMap.

    CORE IDEA:
    • Maintain a running prefix sum
    • Store the first occurrence of each prefix sum in a HashMap
    • If (currentPrefixSum - K) exists in the map, then a subarray with sum K exists
    • Track the maximum length of such subarrays

    WHY THIS WORKS:
    If:
        prefixSum[j] - prefixSum[i] = K
    Then:
        subarray (i+1 to j) has sum K

    Time Complexity  : O(n)
    Space Complexity : O(n)
    =====================================================================================
    */
    static int longestSubarrayWithSumK(int[] arr, int k) {

        HashMap<Integer, Integer> prefixIndexMap = new HashMap<>();

        int prefixSum = 0;
        int maxLength = 0;

        for (int i = 0; i < arr.length; i++) {

            prefixSum += arr[i];

            // Case 1: Subarray from index 0 to i has sum K
            if (prefixSum == k) {
                maxLength = i + 1;
            }

            // Case 2: Subarray with sum K found using prefix difference
            if (prefixIndexMap.containsKey(prefixSum - k)) {
                int length = i - prefixIndexMap.get(prefixSum - k);
                maxLength = Math.max(maxLength, length);
            }

            // Store prefix sum only if it is not already present
            // (to ensure longest length)
            prefixIndexMap.putIfAbsent(prefixSum, i);
        }

        return maxLength;
    }
}
