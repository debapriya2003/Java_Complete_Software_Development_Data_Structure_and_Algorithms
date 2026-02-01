package Dynamic_Programming.subsequences;

import java.util.*;

/**
 * MINIMUM COINS (DP-20)
 *
 * Problem:
 * Given coin denominations and a target amount, find the minimum number of coins needed to make the amount.
 * If impossible, return -1.
 *
 * Approach:
 * Classic unbounded knapsack variant: dp[a] = minimum coins to make amount a. Initialize dp[0]=0 and
 * others = INF. For each coin, for a from coin..target: dp[a] = min(dp[a], 1 + dp[a-coin]).
 *
 * Time: O(n * amount), Space: O(amount)
 *
 * Example:
 * coins=[1,2,5], amount=11 -> 3 (5+5+1)
 */
public class MinimumCoins {

    public static int coinChange(int[] coins, int amount) {
        int INF = amount + 1;
        int[] dp = new int[amount + 1]; Arrays.fill(dp, INF);
        dp[0] = 0;
        for (int coin : coins) for (int a = coin; a <= amount; a++) dp[a] = Math.min(dp[a], 1 + dp[a - coin]);
        return dp[amount] == INF ? -1 : dp[amount];
    }

    public static void main(String[] args) {
        System.out.println(coinChange(new int[]{1,2,5}, 11)); // 3
    }
}
