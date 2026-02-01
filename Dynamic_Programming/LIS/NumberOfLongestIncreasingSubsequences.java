package Dynamic_Programming.LIS;

import java.util.Arrays;

/**
 * NUMBER OF LONGEST INCREASING SUBSEQUENCES (DP-47)
 *
 * Problem:
 * Given an integer array, find the number of distinct longest increasing subsequences.
 *
 * Approach:
 * Use O(n^2) DP: len[i] = length of LIS ending at i, cnt[i] = number of LIS ending at i with that length.
 * For each j<i: if nums[j] < nums[i]
 *  - if len[j] + 1 > len[i]: len[i] = len[j] + 1, cnt[i] = cnt[j]
 *  - else if len[j] + 1 == len[i]: cnt[i] += cnt[j]
 * Track global maximum length and sum counts for indices achieving it.
 *
 * Complexity: O(n^2) time, O(n) space.
 */
public class NumberOfLongestIncreasingSubsequences {

    public static int findNumberOfLIS(int[] nums) {
        int n = nums.length; if (n == 0) return 0;
        int[] len = new int[n]; int[] cnt = new int[n];
        Arrays.fill(len, 1); Arrays.fill(cnt, 1);
        int maxLen = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    if (len[j] + 1 > len[i]) {
                        len[i] = len[j] + 1;
                        cnt[i] = cnt[j];
                    } else if (len[j] + 1 == len[i]) {
                        cnt[i] += cnt[j];
                    }
                }
            }
            maxLen = Math.max(maxLen, len[i]);
        }
        int ans = 0;
        for (int i = 0; i < n; i++) if (len[i] == maxLen) ans += cnt[i];
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(findNumberOfLIS(new int[]{1,3,5,4,7})); // 2
    }
}
