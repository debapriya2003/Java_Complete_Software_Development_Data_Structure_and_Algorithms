package Dynamic_Programming.subsequences;

import java.util.*;

/**
 * COUNT PARTITIONS WITH GIVEN DIFFERENCE (DP-18)
 *
 * Problem:
 * Given an array and difference D, count the number of ways to split it into two subsets S1 and S2
 * such that sum(S1) - sum(S2) = D.
 *
 * Approach:
 * Let total = sum(nums). Then sum(S1) = (D + total)/2 must be an integer; reduce to counting subsets
 * with sum = (D + total)/2 (similar to DP-17). If (D + total) is odd or negative, return 0.
 *
 * Time: O(n * target), Space: O(target)
 *
 * Example:
 * nums=[1,1,2,3], D=1 -> target=(1+7)/2=4 -> subsets summing to 4: 2 ([1,3] and [1,3] with other 1)
 */
@SuppressWarnings("unused")
public class CountPartitionsWithGivenDifference {

    public static long countPartitions(int[] nums, int D) {
        int total = 0; for (int v : nums) total += v;
        if ((D + total) % 2 != 0 || D > total) return 0;
        int target = (D + total) / 2;
        return CountSubsetsWithSumK.countSubsets(nums, target);
    }

    public static void main(String[] args) {
        int[] nums = {1,1,2,3}; System.out.println(countPartitions(nums,1)); // 2
    }
}
