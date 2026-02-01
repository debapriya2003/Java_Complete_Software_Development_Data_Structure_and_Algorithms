package arrays.hard;

import java.util.HashMap;

public class LargestSubarrayWithZeroSum {

    /*
    =====================================================================================
    PROBLEM: LARGEST SUBARRAY WITH ZERO SUM
    -------------------------------------------------------------------------------------
    Given an array of integers (which may include positive, negative, and zero values),
    the task is to find the length of the longest contiguous subarray whose sum is zero.

    A brute-force approach using nested loops would take O(n²) time. The optimal approach
    uses prefix sums and a HashMap to solve the problem efficiently in linear time.
    =====================================================================================
    */

    public static void main(String[] args) {

        int[] arr = {15, -2, 2, -8, 1, 7, 10, 23};

        int length = largestZeroSumSubarray(arr);

        System.out.println("Length of largest subarray with zero sum = " + length);
    }

    /*
    =====================================================================================
    FUNCTION: largestZeroSumSubarray
    -------------------------------------------------------------------------------------
    This function finds the length of the longest subarray with sum equal to zero using
    prefix sums and a HashMap.

    CORE IDEA:
    • Maintain a running prefix sum
    • Store the first occurrence index of each prefix sum
    • If the same prefix sum appears again, the elements in between sum to zero
    • Track the maximum length found

    MATHEMATICAL BASIS:
        If prefixSum[j] == prefixSum[i]
        ⇒ subarray (i+1 to j) has sum 0

    Time Complexity  : O(n)
    Space Complexity : O(n)
    =====================================================================================
    */
    static int largestZeroSumSubarray(int[] arr) {

        HashMap<Integer, Integer> map = new HashMap<>();

        int prefixSum = 0;
        int maxLength = 0;

        // Prefix sum 0 at index -1 to handle subarrays starting from index 0
        map.put(0, -1);

        for (int i = 0; i < arr.length; i++) {

            prefixSum += arr[i];

            // If prefix sum seen before, zero-sum subarray exists
            if (map.containsKey(prefixSum)) {
                int length = i - map.get(prefixSum);
                maxLength = Math.max(maxLength, length);
            }
            // Store first occurrence only
            else {
                map.put(prefixSum, i);
            }
        }

        return maxLength;
    }
}

