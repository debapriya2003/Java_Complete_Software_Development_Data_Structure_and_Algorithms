package Dynamic_Programming.subsequences;

import java.util.*;

/**
 * UNBOUNDED KNAPSACK (DP-23)
 *
 * Problem:
 * Given weights and values and capacity W, maximize value with unlimited copies of each item.
 *
 * Approach:
 * dp[w] = max value achievable with capacity w. For each item, iterate w from weight..W and
 * do dp[w] = max(dp[w], dp[w - wt] + val).
 *
 * Time: O(n * W), Space: O(W)
 *
 * Example:
 * wt=[2,3,4], val=[4,5,6], W=8 -> best value by taking multiple copies.
 */
@SuppressWarnings("unused")
public class UnboundedKnapsack {

    public static int unboundedKnapSack(int W, int[] wt, int[] val) {
        int[] dp = new int[W + 1];
        for (int i = 0; i < wt.length; i++) {
            for (int w = wt[i]; w <= W; w++) dp[w] = Math.max(dp[w], dp[w - wt[i]] + val[i]);
        }
        return dp[W];
    }

    public static void main(String[] args) {
        System.out.println(unboundedKnapSack(8, new int[]{2,3,4}, new int[]{4,5,6}));
    }
}
