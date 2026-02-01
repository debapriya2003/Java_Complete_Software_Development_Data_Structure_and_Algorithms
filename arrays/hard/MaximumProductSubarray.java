package arrays.hard;

public class MaximumProductSubarray {

    /*
    =====================================================================================
    PROBLEM: MAXIMUM PRODUCT SUBARRAY
    -------------------------------------------------------------------------------------
    Given an array of integers (which may contain positive numbers, negative numbers,
    and zeros), the task is to find the contiguous subarray that has the maximum product.

    Unlike maximum sum subarray (Kadane’s Algorithm), this problem is tricky because:
    • A negative number can turn a minimum product into a maximum product
    • Zero breaks the continuity of multiplication

    Therefore, we must track both maximum and minimum products at each step.
    =====================================================================================
    */

    public static void main(String[] args) {

        int[] arr = {2, 3, -2, 4};

        int maxProduct = maximumProduct(arr);

        System.out.println("Maximum product subarray = " + maxProduct);
    }

    /*
    =====================================================================================
    FUNCTION: maximumProduct
    -------------------------------------------------------------------------------------
    This function finds the maximum product of any contiguous subarray using a modified
    Kadane’s Algorithm.

    CORE IDEA:
    • Maintain two values at each index:
        - maxEndingHere → maximum product ending at current index
        - minEndingHere → minimum product ending at current index
    • Why minEndingHere?
        A negative number can turn the smallest product into the largest product.
    • At each step, update both values using the current element.

    FORMULA:
        maxEndingHere = max(arr[i], arr[i] * prevMax, arr[i] * prevMin)
        minEndingHere = min(arr[i], arr[i] * prevMax, arr[i] * prevMin)

    Time Complexity  : O(n)
    Space Complexity : O(1)
    =====================================================================================
    */
    static int maximumProduct(int[] arr) {

        int maxEndingHere = arr[0];
        int minEndingHere = arr[0];
        int maxSoFar = arr[0];

        for (int i = 1; i < arr.length; i++) {

            int current = arr[i];

            // Store previous max before updating
            int tempMax = maxEndingHere;

            maxEndingHere = Math.max(
                    current,
                    Math.max(current * maxEndingHere, current * minEndingHere)
            );

            minEndingHere = Math.min(
                    current,
                    Math.min(current * tempMax, current * minEndingHere)
            );

            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }

        return maxSoFar;
    }
}

