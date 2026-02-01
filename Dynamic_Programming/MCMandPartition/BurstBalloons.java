package Dynamic_Programming.MCMandPartition;

import java.util.*;

/**
 * BURST BALLOONS (DP-51)
 *
 * Problem:
 * Given balloons with values, bursting a balloon gives coins equal to val[left]*val[i]*val[right].
 * Find max coins by optimally bursting balloons.
 *
 * Approach:
 * Interval DP: pad nums with 1 on both ends. dp[i][j] = max coins obtainable by bursting balloons
 * strictly between i and j. Try choosing last balloon k between i and j and combine.
 *
 * Complexity: O(n^3) time, O(n^2) space.
 */
@SuppressWarnings("unused")
public class BurstBalloons {

    public static int maxCoins(int[] nums) {
        int n = nums.length;
        int[] a = new int[n + 2];
        a[0] = 1; a[n + 1] = 1;
        System.arraycopy(nums, 0, a, 1, n);
        int[][] dp = new int[n + 2][n + 2];
        for (int len = 2; len < n + 2; len++) {
            for (int i = 0; i + len < n + 2; i++) {
                int j = i + len;
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], a[i] * a[k] * a[j] + dp[i][k] + dp[k][j]);
                }
            }
        }
        return dp[0][n + 1];
    }

    public static void main(String[] args) {
        System.out.println(maxCoins(new int[]{3,1,5,8})); // expected 167
    }
}
