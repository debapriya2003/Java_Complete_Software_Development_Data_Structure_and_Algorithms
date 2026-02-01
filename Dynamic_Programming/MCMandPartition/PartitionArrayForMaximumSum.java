package Dynamic_Programming.MCMandPartition;

import java.util.*;

/**
 * PARTITION ARRAY FOR MAXIMUM SUM (DP-54)
 *
 * Problem:
 * Given an array arr and integer k, partition array into subarrays of length at most k; you can
 * replace each subarray by its maximum value multiplied by subarray length. Return maximum sum.
 *
 * Approach:
 * DP: dp[i] = max sum for prefix ending at i. For j from 1..k, consider last block length j and
 * compute local max and dp[i] = max(dp[i], dp[i-j] + localMax * j).
 *
 * Complexity: O(n*k) time, O(n) space.
 */
@SuppressWarnings("unused")
public class PartitionArrayForMaximumSum {

    public static int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length;
        int[] dp = new int[n + 1]; // dp[0]=0
        for (int i = 1; i <= n; i++) {
            int localMax = 0;
            for (int len = 1; len <= k && i - len >= 0; len++) {
                localMax = Math.max(localMax, arr[i - len]);
                dp[i] = Math.max(dp[i], dp[i - len] + localMax * len);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(maxSumAfterPartitioning(new int[]{1,15,7,9,2,5,10}, 3)); // expected 84
    }
}