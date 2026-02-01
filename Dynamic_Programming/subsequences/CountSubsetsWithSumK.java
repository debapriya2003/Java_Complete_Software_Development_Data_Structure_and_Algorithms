package Dynamic_Programming.subsequences;

import java.util.*;

/**
 * COUNT SUBSETS WITH SUM K (DP-17)
 *
 * Problem:
 * Count the number of subsets whose sum is exactly K.
 *
 * Approach:
 * Use DP where dp[s] holds the count of ways to reach sum s. For each number num, iterate s
 * from K down to num and do dp[s] += dp[s - num]. Use long if counts can be large.
 *
 * Time: O(n * K), Space: O(K)
 *
 * Example:
 * nums = [1,1,1], K=2 -> 3 ways
 */
@SuppressWarnings("unused")
public class CountSubsetsWithSumK {

    public static long countSubsets(int[] nums, int K) {
        long[] dp = new long[K + 1]; dp[0] = 1;
        for (int num : nums) {
            for (int s = K; s >= num; s--) dp[s] += dp[s - num];
        }
        return dp[K];
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1}; System.out.println(countSubsets(nums,2)); // 3
    }
}
