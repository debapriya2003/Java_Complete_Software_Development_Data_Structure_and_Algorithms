package arrays.medium;

public class KadanesAlgorithm {

    /*
    =====================================================================================
    PROBLEM: MAXIMUM SUBARRAY SUM (KADANE'S ALGORITHM)
    -------------------------------------------------------------------------------------
    Given an array of integers (which may contain positive, negative, and zero values),
    the task is to find the maximum possible sum of any contiguous subarray.

    Kadane’s Algorithm is an optimized dynamic programming approach that solves this
    problem in linear time by deciding at each index whether to extend the current
    subarray or start a new subarray.
    =====================================================================================
    */

    public static void main(String[] args) {

        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

        int maxSum = maxSubarraySum(arr);

        System.out.println("Maximum subarray sum = " + maxSum);
    }

    /*
    =====================================================================================
    FUNCTION: maxSubarraySum
    -------------------------------------------------------------------------------------
    This function implements Kadane’s Algorithm to find the maximum subarray sum.

    CORE IDEA:
    • Maintain a running sum (currentSum)
    • At each index, decide:
        - Start a new subarray from current element
        - OR extend the existing subarray
    • Track the maximum sum found so far

    DECISION FORMULA:
        currentSum = max(arr[i], currentSum + arr[i])
        maxSum     = max(maxSum, currentSum)

    Time Complexity  : O(n)
    Space Complexity : O(1)
    =====================================================================================
    */
    static int maxSubarraySum(int[] arr) {

        int currentSum = arr[0];
        int maxSum = arr[0];

        for (int i = 1; i < arr.length; i++) {

            // Decide whether to start new subarray or extend previous
            currentSum = Math.max(arr[i], currentSum + arr[i]);

            // Update maximum sum found so far
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }
}

