package Dynamic_Programming.subsequences;

import java.util.*;

/**
 * PARTITION EQUAL SUBSET SUM (DP-15)
 *
 * Problem:
 * Given a set of positive integers, determine if it can be partitioned into two subsets
 * with equal sum.
 *
 * Approach:
 * This reduces to checking whether a subset exists with sum = totalSum/2. If totalSum is
 * odd, answer is false. Use the subset-sum DP (1D optimization as above).
 *
 * Time: O(n * sum), Space: O(sum)
 *
 * Example:
 * nums = [1,5,11,5] -> true (11 can be split as {1,5,5} and {11})
 */
@SuppressWarnings("unused")
public class PartitionEqualSubsetSum {

    public static boolean canPartition(int[] nums) {
        int sum = 0; for (int v : nums) sum += v;
        if ((sum & 1) == 1) return false;
        return SubsetSumEqualToTarget.subsetSum(nums, sum / 2);
    }

    public static void main(String[] args) {
        int[] nums = {1,5,11,5};
        System.out.println(canPartition(nums)); // true
    }
}
