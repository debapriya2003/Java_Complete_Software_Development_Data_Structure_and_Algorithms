package Dynamic_Programming.subsequences;

import java.util.*;

/**
 * ROD CUTTING PROBLEM I (DP-24)
 *
 * Problem:
 * Given a rod of length n and prices for each length, determine the maximum revenue by cutting
 * the rod into pieces. This is equivalent to unbounded knapsack where lengths are weights.
 *
 * Approach:
 * dp[len] = max revenue for rod length len. For each cut length i (1..n), dp[len] = max(dp[len], price[i-1] + dp[len - i]).
 *
 * Time: O(n^2), Space: O(n)
 *
 * Example:
 * prices = [1,5,8,9], n=4 -> best = 10 (2+2 or 1+3 etc depending on prices)
 */
@SuppressWarnings("unused")
public class RodCuttingProblem {

    public static int rodCutting(int[] price, int n) {
        int[] dp = new int[n + 1]; dp[0] = 0;
        for (int len = 1; len <= n; len++) {
            int best = 0;
            for (int cut = 1; cut <= len; cut++) best = Math.max(best, price[cut - 1] + dp[len - cut]);
            dp[len] = best;
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(rodCutting(new int[]{1,5,8,9}, 4)); // 10
    }
}
