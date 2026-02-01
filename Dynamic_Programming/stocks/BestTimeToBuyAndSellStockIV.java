package Dynamic_Programming.stocks;

import java.util.*;

/**
 * BEST TIME TO BUY AND SELL STOCK IV (DP-38)
 *
 * Problem:
 * At most k transactions allowed. Maximize profit.
 *
 * Approach:
 * Dynamic programming: if k >= n/2, reduce to unlimited transactions. Otherwise use DP with
 * dp[i][j] representing max profit using at most i transactions up to day j. Use optimized loop
 * where we maintain maxDiff = max(maxDiff, dp[i-1][j] - price[j]).
 *
 * Complexity: O(k * n) time, O(k * n) space (can be optimized to O(k * n) with rolling arrays).
 *
 * Example:
 * prices=[3,2,6,5,0,3], k=2 -> max profit = 7
 */
@SuppressWarnings("unused")
public class BestTimeToBuyAndSellStockIV {

    public static int maxProfit(int k, int[] prices) {
        int n = prices.length;
        if (n == 0 || k == 0) return 0;
        if (k >= n / 2) {
            // unlimited transactions
            int profit = 0;
            for (int i = 1; i < n; i++) if (prices[i] > prices[i - 1]) profit += prices[i] - prices[i - 1];
            return profit;
        }
        int[][] dp = new int[k + 1][n];
        for (int i = 1; i <= k; i++) {
            int maxDiff = -prices[0];
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.max(dp[i][j - 1], prices[j] + maxDiff);
                maxDiff = Math.max(maxDiff, dp[i - 1][j] - prices[j]);
            }
        }
        return dp[k][n - 1];
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(2, new int[]{3,2,6,5,0,3})); // 7
    }
}
