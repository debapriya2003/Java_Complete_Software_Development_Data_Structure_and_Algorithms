package Dynamic_Programming.LIS;

/**
 * LONGEST INCREASING SUBSEQUENCE - LENGTH (DP-41)
 *
 * Problem:
 * Given an integer array, return the length of the longest strictly increasing subsequence (LIS).
 *
 * Approach:
 * Efficient O(n log n) patience sorting method: maintain an array `tails` where tails[len] stores the
 * smallest possible tail value for an increasing subsequence of length `len+1`. For each number x,
 * binary search position in tails to replace or extend. The length of filled tails is the LIS length.
 *
 * Complexity: O(n log n) time, O(n) space.
 *
 * Example:
 * nums = [10,9,2,5,3,7,101,18] -> LIS length = 4 (e.g., [2,3,7,101])
 */
public class LongestIncreasingSubsequenceLength {

    public static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] tails = new int[nums.length];
        int size = 0;
        for (int x : nums) {
            int i = java.util.Arrays.binarySearch(tails, 0, size, x);
            if (i < 0) i = -(i + 1);
            tails[i] = x;
            if (i == size) size++;
        }
        return size;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLIS(new int[]{10,9,2,5,3,7,101,18})); // 4
    }
}
