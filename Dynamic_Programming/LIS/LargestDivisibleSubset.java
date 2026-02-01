package Dynamic_Programming.LIS;

import java.util.*;

/**
 * LARGEST DIVISIBLE SUBSET (DP-44)
 *
 * Problem:
 * Given a set of distinct positive integers, find the largest subset such that for every pair (Si, Sj) in the subset,
 * either Si % Sj == 0 or Sj % Si == 0.
 *
 * Approach:
 * Sort ascending. dp[i] = size of largest divisible subset ending at i. prev[i] stores predecessor index.
 * For each i, check all j<i and if nums[i] % nums[j] == 0 and dp[j] + 1 > dp[i], update.
 * Reconstruct subset by following prev from max dp index.
 *
 * Complexity: O(n^2) time, O(n) space.
 */
public class LargestDivisibleSubset {

    public static List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        Arrays.sort(nums);
        int n = nums.length;
        int[] dp = new int[n]; Arrays.fill(dp, 1);
        int[] prev = new int[n]; Arrays.fill(prev, -1);
        int maxSize = 1, maxIdx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    prev[i] = j;
                }
            }
            if (dp[i] > maxSize) { maxSize = dp[i]; maxIdx = i; }
        }
        int idx = maxIdx;
        while (idx != -1) { res.add(nums[idx]); idx = prev[idx]; }
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        System.out.println(largestDivisibleSubset(new int[]{1,2,4,8})); // [1,2,4,8]
    }
}
