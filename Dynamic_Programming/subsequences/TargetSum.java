package Dynamic_Programming.subsequences;

import java.util.*;

/**
 * TARGET SUM (DP-21)
 *
 * Problem:
 * Given nums and target S, assign + or - to each number to reach sum S. Return number of ways.
 *
 * Approach:
 * Convert to subset-sum: Let total = sum(nums). We need subset sum = (total + S)/2. Count subsets.
 * If (total + S) is odd or S > total, answer is 0.
 *
 * Time: O(n * target), Space: O(target)
 *
 * Example:
 * nums=[1,1,1,1,1], S=3 -> 5
 */
@SuppressWarnings("unused")
public class TargetSum {

    public static long findTargetSumWays(int[] nums, int S) {
        int total = 0; for (int v : nums) total += v;
        if ((total + S) % 2 != 0 || Math.abs(S) > total) return 0;
        int target = (total + S) / 2;
        return CountSubsetsWithSumK.countSubsets(nums, target);
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1,1,1}; System.out.println(findTargetSumWays(nums,3)); // 5
    }
}
