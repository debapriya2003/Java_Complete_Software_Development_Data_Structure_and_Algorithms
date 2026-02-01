package arrays.medium;

import java.util.HashMap;

public class CountSubarraysWithGivenSum {

    /*
    =====================================================================================
    PROBLEM: COUNT SUBARRAYS WITH GIVEN SUM
    -------------------------------------------------------------------------------------
    Given an array of integers (which may include positive, negative, and zero values)
    and an integer K, the task is to count the total number of contiguous subarrays whose
    sum is exactly equal to K.

    A brute-force approach using nested loops would take O(n²) time. To optimize this,
    we use the Prefix Sum technique combined with HashMap to achieve linear time
    complexity.
    =====================================================================================
    */

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, -2, 5, -3, 1};
        int k = 3;

        int count = countSubarrays(arr, k);

        System.out.println("Number of subarrays with sum " + k + " = " + count);
    }

    /*
    =====================================================================================
    FUNCTION: countSubarrays
    -------------------------------------------------------------------------------------
    This function counts the number of subarrays whose sum equals K using prefix sums
    and a HashMap.

    CORE IDEA:
    • Maintain a running prefix sum while traversing the array
    • Store how many times each prefix sum has occurred in a HashMap
    • If (currentPrefixSum - K) exists in the map, it means a subarray ending at the
      current index has sum K
    • Add the frequency of (prefixSum - K) to the count

    MATHEMATICAL BASIS:
        If prefixSum[j] - prefixSum[i] = K
        ⇒ subarray (i+1 to j) has sum K

    Time Complexity  : O(n)
    Space Complexity : O(n)
    =====================================================================================
    */
    static int countSubarrays(int[] arr, int k) {

        HashMap<Integer, Integer> prefixSumCount = new HashMap<>();

        int prefixSum = 0;
        int count = 0;

        // Important initialization:
        // prefix sum 0 occurs once before starting
        prefixSumCount.put(0, 1);

        for (int num : arr) {

            prefixSum += num;

            // Check if subarray with sum K exists
            if (prefixSumCount.containsKey(prefixSum - k)) {
                count += prefixSumCount.get(prefixSum - k);
            }

            // Store/update current prefix sum frequency
            prefixSumCount.put(prefixSum,
                    prefixSumCount.getOrDefault(prefixSum, 0) + 1);
        }

        return count;
    }
}
