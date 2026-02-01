package arrays.medium;

import java.util.HashMap;
import java.util.Map;

public class TwoSumProblem {

    /*
    =====================================================================================
    PROBLEM: TWO SUM PROBLEM
    -------------------------------------------------------------------------------------
    Given an array of integers and a target value, the task is to find two distinct
    elements in the array such that their sum is equal to the given target.

    The problem requires returning the indices (or values) of the two numbers. A brute-
    force solution using nested loops would take O(n²) time. The optimal solution uses
    hashing to achieve linear time complexity.
    =====================================================================================
    */

    public static void main(String[] args) {

        int[] arr = {2, 7, 11, 15};
        int target = 9;

        int[] result = twoSum(arr, target);

        if (result.length == 2) {
            System.out.println("Indices: " + result[0] + ", " + result[1]);
            System.out.println("Values: " + arr[result[0]] + ", " + arr[result[1]]);
        } else {
            System.out.println("No two elements add up to the target");
        }
    }

    /*
    =====================================================================================
    FUNCTION: twoSum
    -------------------------------------------------------------------------------------
    This function finds two indices whose values add up to the target sum using HashMap.

    LOGIC:
    • Create a HashMap to store numbers and their indices
    • Traverse the array
    • For each element, calculate the complement (target - current element)
    • If complement exists in the map, return the indices
    • Otherwise, store the current element and its index in the map

    Time Complexity  : O(n)
    Space Complexity : O(n)
    =====================================================================================
    */
    static int[] twoSum(int[] arr, int target) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {

            int complement = target - arr[i];

            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }

            map.put(arr[i], i);
        }

        return new int[]{}; // No solution found
    }
}

