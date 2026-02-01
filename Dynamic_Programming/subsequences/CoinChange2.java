package Dynamic_Programming.subsequences;

import java.util.*;

/**
 * COIN CHANGE 2 (DP-22)
 *
 * Problem:
 * Count the number of combinations to make up the amount using unlimited coins (order doesn't matter).
 *
 * Approach:
 * Unbounded knapsack counting variant. dp[a] = number of ways to make amount a. For each coin,
 * for a from coin..amount: dp[a] += dp[a-coin]. Iterate coins outer loop to avoid counting permutations.
 *
 * Time: O(n * amount), Space: O(amount)
 *
 * Example:
 * coins=[1,2,5], amount=5 -> 4 (ways: 5,2+2+1,2+1+1+1,1+1+1+1+1)
 */
@SuppressWarnings("unused")
public class CoinChange2 {

    public static long change(int amount, int[] coins) {
        long[] dp = new long[amount + 1]; dp[0] = 1;
        for (int coin : coins) for (int a = coin; a <= amount; a++) dp[a] += dp[a - coin];
        return dp[amount];
    }

    public static void main(String[] args) {
        System.out.println(change(5, new int[]{1,2,5})); // 4
    }
}
