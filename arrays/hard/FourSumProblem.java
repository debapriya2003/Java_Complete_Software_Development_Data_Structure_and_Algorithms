package arrays.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSumProblem {

    /*
    =====================================================================================
    PROBLEM: 4-SUM PROBLEM
    -------------------------------------------------------------------------------------
    Given an array of integers and a target value, the task is to find all unique
    quadruplets (a, b, c, d) such that:
        a + b + c + d = target

    Each quadruplet must be unique, and the solution should not contain duplicate
    quadruplets. This is a natural extension of the 2-Sum and 3-Sum problems and tests
    understanding of sorting, two pointers, and duplicate handling.
    =====================================================================================
    */

    public static void main(String[] args) {

        int[] nums = {1, 0, -1, 0, -2, 2};
        int target = 0;

        List<List<Integer>> result = fourSum(nums, target);

        System.out.println("Quadruplets with sum " + target + ":");
        for (List<Integer> quad : result) {
            System.out.println(quad);
        }
    }

    /*
    =====================================================================================
    FUNCTION: fourSum
    -------------------------------------------------------------------------------------
    This function finds all unique quadruplets that sum to the target value.

    ALGORITHM:
    1. Sort the array
    2. Fix the first element using index i
    3. Fix the second element using index j
    4. Use two pointers (left and right) to find remaining two elements
    5. Skip duplicates at each step to avoid repeated quadruplets

    Time Complexity  : O(n³)
    Space Complexity : O(1) (excluding output list)
    =====================================================================================
    */
    static List<List<Integer>> fourSum(int[] nums, int target) {

        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;

        if (n < 4)
            return result;

        Arrays.sort(nums);

        for (int i = 0; i < n - 3; i++) {

            // Skip duplicate elements for first number
            if (i > 0 && nums[i] == nums[i - 1])
                continue;

            for (int j = i + 1; j < n - 2; j++) {

                // Skip duplicate elements for second number
                if (j > i + 1 && nums[j] == nums[j - 1])
                    continue;

                int left = j + 1;
                int right = n - 1;

                while (left < right) {

                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];

                    if (sum == target) {

                        result.add(Arrays.asList(
                                nums[i], nums[j], nums[left], nums[right]
                        ));

                        // Skip duplicates for third number
                        while (left < right && nums[left] == nums[left + 1])
                            left++;

                        // Skip duplicates for fourth number
                        while (left < right && nums[right] == nums[right - 1])
                            right--;

                        left++;
                        right--;
                    }
                    else if (sum < target) {
                        left++;
                    }
                    else {
                        right--;
                    }
                }
            }
        }

        return result;
    }
}
