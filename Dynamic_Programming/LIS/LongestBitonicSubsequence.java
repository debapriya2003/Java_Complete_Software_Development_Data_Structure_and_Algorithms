package Dynamic_Programming.LIS;

import java.util.*;

/**
 * LONGEST BITONIC SUBSEQUENCE (DP-46)
 *
 * Problem:
 * A bitonic subsequence first increases then decreases. Find the length of the longest one.
 *
 * Approach:
 * Compute LIS length ending at each index (left[i]) and LIS length starting at each index on reversed array
 * (right[i] which is effectively LDS from i). The longest bitonic length using i as peak is left[i] + right[i] - 1.
 *
 * Complexity: O(n^2) time using DP O(n) space, can be optimized for LIS parts to O(n log n) if needed.
 */
public class LongestBitonicSubsequence {

    public static int longestBitonic(int[] nums) {
        int n = nums.length; if (n == 0) return 0;
        int[] inc = new int[n]; Arrays.fill(inc, 1);
        for (int i = 0; i < n; i++) for (int j = 0; j < i; j++) if (nums[j] < nums[i]) inc[i] = Math.max(inc[i], inc[j] + 1);
        int[] dec = new int[n]; Arrays.fill(dec, 1);
        for (int i = n - 1; i >= 0; i--) for (int j = n - 1; j > i; j--) if (nums[j] < nums[i]) dec[i] = Math.max(dec[i], dec[j] + 1);
        int best = 1;
        for (int i = 0; i < n; i++) best = Math.max(best, inc[i] + dec[i] - 1);
        return best;
    }

    public static void main(String[] args) {
        System.out.println(longestBitonic(new int[]{1,11,2,10,4,5,2,1})); // 6 (1,2,10,4,2,1)
    }
}
