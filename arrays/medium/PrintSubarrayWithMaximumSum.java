package arrays.medium;

public class PrintSubarrayWithMaximumSum {

    /*
    =====================================================================================
    PROBLEM: PRINT SUBARRAY WITH MAXIMUM SUBARRAY SUM
    -------------------------------------------------------------------------------------
    Given an array of integers (positive, negative, or zero), the task is to find the
    contiguous subarray that has the maximum possible sum and print that subarray.

    This problem is an extension of Kadane’s Algorithm. While the basic Kadane’s Algorithm
    finds only the maximum sum, this extended version also keeps track of the starting
    and ending indices of the subarray that produces this maximum sum.
    =====================================================================================
    */

    public static void main(String[] args) {

        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

        printMaxSubarray(arr);
    }

    /*
    =====================================================================================
    FUNCTION: printMaxSubarray
    -------------------------------------------------------------------------------------
    This function applies the extended Kadane’s Algorithm to determine both:
    • The maximum subarray sum
    • The actual subarray that gives this sum

    LOGIC:
    • Maintain currentSum and maxSum
    • Track a temporary start index for the current subarray
    • Reset currentSum when it becomes negative
    • Update start and end indices whenever a new maximum is found

    Time Complexity  : O(n)
    Space Complexity : O(1)
    =====================================================================================
    */
    static void printMaxSubarray(int[] arr) {

        int currentSum = 0;
        int maxSum = Integer.MIN_VALUE;

        int start = 0;      // Start index of max subarray
        int end = 0;        // End index of max subarray
        int tempStart = 0;  // Temporary start index

        for (int i = 0; i < arr.length; i++) {

            currentSum += arr[i];

            // Update maxSum and indices if better sum found
            if (currentSum > maxSum) {
                maxSum = currentSum;
                start = tempStart;
                end = i;
            }

            // Reset if currentSum becomes negative
            if (currentSum < 0) {
                currentSum = 0;
                tempStart = i + 1;
            }
        }

        System.out.println("Maximum Subarray Sum = " + maxSum);
        System.out.print("Subarray: ");

        for (int i = start; i <= end; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
