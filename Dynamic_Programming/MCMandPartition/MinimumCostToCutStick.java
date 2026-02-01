package Dynamic_Programming.MCMandPartition;

import java.util.*;

/**
 * MINIMUM COST TO CUT THE STICK (DP-50)
 *
 * Problem:
 * Given a stick of length n and cuts positions, find min cost to perform all cuts where cost of a cut
 * = length of the current stick being cut.
 *
 * Approach:
 * Typical interval DP. Sort cuts and add boundaries 0 and n. Let dp[i][j] = min cost to cut between cuts[i] and cuts[j].
 * Try cut at k between i and j: cost = cuts[j] - cuts[i] + dp[i][k] + dp[k][j]. Fill increasing interval lengths.
 *
 * Complexity: O(m^3) time for m cuts, O(m^2) space.
 */
public class MinimumCostToCutStick {

    public static int minCost(int n, int[] cuts) {
        int m = cuts.length;
        int[] c = new int[m + 2]; c[0] = 0; c[m + 1] = n;
        System.arraycopy(cuts, 0, c, 1, m);
        Arrays.sort(c);
        int[][] dp = new int[m + 2][m + 2];
        for (int len = 2; len < m + 2; len++) {
            for (int i = 0; i + len < m + 2; i++) {
                int j = i + len;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i + 1; k < j; k++) {
                    int cost = c[j] - c[i] + dp[i][k] + dp[k][j];
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
                if (dp[i][j] == Integer.MAX_VALUE) dp[i][j] = 0;
            }
        }
        return dp[0][m + 1];
    }

    public static void main(String[] args) {
        System.out.println(minCost(7, new int[]{1,3,4,5})); // expected 16
    }
}
