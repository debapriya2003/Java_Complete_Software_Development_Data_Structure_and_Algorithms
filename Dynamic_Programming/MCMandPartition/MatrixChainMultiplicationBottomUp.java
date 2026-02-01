package Dynamic_Programming.MCMandPartition;

import java.util.*;

/**
 * MATRIX CHAIN MULTIPLICATION - BOTTOM-UP (DP-49)
 *
 * Problem & Approach:
 * Bottom-up tabulation for matrix chain multiplication. dp[i][j] stores min cost for multiplying i..j.
 * Fill table for increasing chain lengths (len = 2..n) and compute dp[i][j] by trying splits k in [i..j-1].
 *
 * Complexity: O(n^3) time, O(n^2) space.
 */
@SuppressWarnings("unused")
public class MatrixChainMultiplicationBottomUp {

    public static int mcm(int[] p) {
        int n = p.length - 1;
        int[][] dp = new int[n + 1][n + 1];
        for (int len = 2; len <= n; len++) {
            for (int i = 1; i + len - 1 <= n; i++) {
                int j = i + len - 1;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int cost = dp[i][k] + dp[k + 1][j] + p[i - 1] * p[k] * p[j];
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }
        return dp[1][n];
    }

    public static void main(String[] args) {
        System.out.println(mcm(new int[]{40,20,30,10,30})); // 26000
    }
}
