package arrays.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSumProblem {

    /*
    =====================================================================================
    PROBLEM: 3-SUM PROBLEM
    -------------------------------------------------------------------------------------
    Given an integer array nums, the task is to find all unique triplets (a, b, c) such
    that:
        a + b + c = 0

    The solution must not contain duplicate triplets.

    This problem is a classic extension of the 2-Sum problem and is frequently asked in
    interviews. The optimal approach uses sorting followed by a two-pointer technique.
    =====================================================================================
    */

    public static void main(String[] args) {

        int[] nums = {-1, 0, 1, 2, -1, -4};

        List<List<Integer>> result = threeSum(nums);

        System.out.println("Triplets with sum 0:");
        for (List<Integer> triplet : result) {
            System.out.println(triplet);
        }
    }

    /*
    =====================================================================================
    FUNCTION: threeSum
    -------------------------------------------------------------------------------------
    This function finds all unique triplets whose sum is zero.

    ALGORITHM:
    1. Sort the array
    2. Fix one element using index i
    3. Use two pointers (left and right) to find remaining two elements
    4. Skip duplicate elements to avoid repeated triplets

    WHY THIS WORKS:
    Sorting allows the two-pointer technique to efficiently move toward the required sum
    while maintaining uniqueness.

    Time Complexity  : O(n²)
    Space Complexity : O(1) (excluding output list)
    =====================================================================================
    */
    static List<List<Integer>> threeSum(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;

        if (n < 3)
            return result;

        Arrays.sort(nums);

        for (int i = 0; i < n - 2; i++) {

            // Skip duplicate values for the first element
            if (i > 0 && nums[i] == nums[i - 1])
                continue;

            int left = i + 1;
            int right = n - 1;

            while (left < right) {

                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {

                    result.add(Arrays.asList(
                            nums[i], nums[left], nums[right]
                    ));

                    // Skip duplicates for second element
                    while (left < right && nums[left] == nums[left + 1])
                        left++;

                    // Skip duplicates for third element
                    while (left < right && nums[right] == nums[right - 1])
                        right--;

                    left++;
                    right--;
                }
                else if (sum < 0) {
                    left++;
                }
                else {
                    right--;
                }
            }
        }

        return result;
    }
}

