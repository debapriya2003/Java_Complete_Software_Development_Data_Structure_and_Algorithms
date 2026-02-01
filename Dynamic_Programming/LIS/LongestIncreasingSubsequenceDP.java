package Dynamic_Programming.LIS;

import java.util.Arrays;

/**
 * LONGEST INCREASING SUBSEQUENCE - DP O(n^2) (DP-43)
 *
 * Problem:
 * Same LIS length but using classic DP O(n^2) approach (useful for teaching and counting variants).
 *
 * Approach:
 * dp[i] = length of LIS ending at i. dp[i] = 1 + max(dp[j]) for all j<i with nums[j] < nums[i].
 * Track max over dp.
 *
 * Complexity: O(n^2) time, O(n) space.
 */
public class LongestIncreasingSubsequenceDP {

    public static int lengthOfLIS(int[] nums) {
        int n = nums.length; if (n == 0) return 0;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int best = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            best = Math.max(best, dp[i]);
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLIS(new int[]{10,9,2,5,3,7,101,18})); // 4
    }
}
