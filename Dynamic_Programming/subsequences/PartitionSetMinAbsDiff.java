package Dynamic_Programming.subsequences;

import java.util.*;

/**
 * PARTITION SET INTO 2 SUBSETS WITH MIN ABSOLUTE SUM DIFF (DP-16)
 *
 * Problem:
 * Partition the set into two subsets such that the absolute difference of subset sums is minimized.
 *
 * Approach:
 * Compute achievable subset sums up to total/2 using subset-sum DP. The best partition splits
 * closest to total/2. Answer = total - 2*s where s is the achievable sum nearest to total/2.
 *
 * Time: O(n * total), Space: O(total)
 *
 * Example:
 * nums = [1,6,11,5] -> total=23, best s=11 -> diff = 23 - 2*11 = 1
 */
@SuppressWarnings("unused")
public class PartitionSetMinAbsDiff {

    public static int minSubsetDiff(int[] nums) {
        int total = 0; for (int v : nums) total += v;
        int target = total / 2;
        boolean[] dp = new boolean[target + 1]; dp[0] = true;
        for (int num : nums) for (int s = target; s >= num; s--) if (dp[s - num]) dp[s] = true;
        for (int s = target; s >= 0; s--) if (dp[s]) return total - 2 * s;
        return total; // fallback
    }

    public static void main(String[] args) {
        int[] nums = {1,6,11,5};
        System.out.println(minSubsetDiff(nums)); // 1
    }
}
