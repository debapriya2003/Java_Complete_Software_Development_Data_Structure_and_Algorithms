package arrays.easy;
public class FindMissingNumber {

    /*
    =====================================================================================
    PROBLEM: FIND MISSING NUMBER IN AN ARRAY
    -------------------------------------------------------------------------------------
    Given an array containing N−1 distinct numbers taken from the range 1 to N, exactly
    one number is missing. The task is to find the missing number.

    This problem is commonly solved using a mathematical approach based on the sum of the
    first N natural numbers. It avoids nested loops and provides an optimal solution with
    linear time complexity and constant space complexity.
    =====================================================================================
    */

    public static void main(String[] args) {

        int[] arr = {1, 2, 4, 5};  // Missing number is 3
        int n = 5;               // Range is from 1 to 5

        int missing = findMissingNumber(arr, n);

        System.out.println("Missing number is: " + missing);
    }

    /*
    =====================================================================================
    FUNCTION: findMissingNumber
    -------------------------------------------------------------------------------------
    This function finds the missing number using the sum formula:
        Sum of first N natural numbers = N × (N + 1) / 2

    Steps:
    • Calculate expected sum of numbers from 1 to N
    • Calculate actual sum of elements in the array
    • Subtract actual sum from expected sum
    • The result is the missing number

    Time Complexity  : O(n)
    Space Complexity : O(1)
    =====================================================================================
    */
    static int findMissingNumber(int[] arr, int n) {

        int expectedSum = n * (n + 1) / 2;
        int actualSum = 0;

        // Calculate sum of array elements
        for (int num : arr) {
            actualSum += num;
        }

        // Missing number
        return expectedSum - actualSum;
    }
}
