package Dynamic_Programming.subsequences;

import java.util.*;

/**
 * SUBSET SUM EQUAL TO TARGET (DP-14)
 *
 * Problem:
 * Given an array of positive integers and a target sum, determine if there exists a subset
 * whose sum equals the target.
 *
 * Approach:
 * Classic 0/1 knapsack decision variant. Use DP table where dp[i][s] = can we achieve sum s
 * using first i elements. We can optimize to 1D array since dp[i] depends only on dp[i-1].
 *
 * Recurrence:
 * dp[s] = dp[s] || dp[s - arr[i]] (iterate s from target down to arr[i]).
 *
 * Time: O(n * target), Space: O(target)
 *
 * Example:
 * arr = [3, 34, 4, 12, 5, 2], target = 9 -> true (4+5)
 */
@SuppressWarnings("unused")
public class SubsetSumEqualToTarget {

    public static boolean subsetSum(int[] nums, int target) {
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int num : nums) {
            for (int s = target; s >= num; s--) {
                if (dp[s - num]) dp[s] = true;
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
        int[] arr = {3, 34, 4, 12, 5, 2};
        System.out.println(subsetSum(arr, 9)); // true
    }
}
