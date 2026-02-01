package arrays.easy;
public class LongestSubarrayWithSumKPositives {

    /*
    =====================================================================================
    PROBLEM: LONGEST SUBARRAY WITH SUM K (POSITIVE ELEMENTS ONLY)
    -------------------------------------------------------------------------------------
    Given an array of positive integers and an integer K, the task is to find the length
    of the longest contiguous subarray whose sum is exactly equal to K.

    Since all elements are positive, we can use the Sliding Window (Two Pointer) technique.
    The window expands by moving the right pointer to increase the sum and shrinks by
    moving the left pointer to reduce the sum when it exceeds K. This avoids nested loops
    and provides an optimal solution.
    =====================================================================================
    */

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 1, 1, 1, 1, 4, 2, 3};
        int k = 3;

        int longestLength = longestSubarrayWithSumK(arr, k);

        System.out.println("Length of longest subarray with sum " + k + " = " + longestLength);
    }

    /*
    =====================================================================================
    FUNCTION: longestSubarrayWithSumK
    -------------------------------------------------------------------------------------
    This function finds the longest subarray whose sum is exactly K using the Sliding
    Window technique.

    Logic:
    • Use two pointers (left and right)
    • Expand the window by moving right and adding arr[right] to sum
    • If sum exceeds K, shrink the window from the left
    • If sum equals K, update the maximum length
    • Continue until the end of the array

    This approach works efficiently because all elements are positive.

    Time Complexity  : O(n)
    Space Complexity : O(1)
    =====================================================================================
    */
    static int longestSubarrayWithSumK(int[] arr, int k) {

        int left = 0;
        int sum = 0;
        int maxLength = 0;

        for (int right = 0; right < arr.length; right++) {

            // Add current element to sum
            sum += arr[right];

            // Shrink window if sum exceeds K
            while (sum > k && left <= right) {
                sum -= arr[left];
                left++;
            }

            // Check if sum equals K
            if (sum == k) {
                maxLength = Math.max(maxLength, right - left + 1);
            }
        }

        return maxLength;
    }
}
