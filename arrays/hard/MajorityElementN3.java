package arrays.hard;

import java.util.ArrayList;
import java.util.List;

public class MajorityElementN3 {

    /*
    =====================================================================================
    PROBLEM: MAJORITY ELEMENT (APPEARS MORE THAN n/3 TIMES)
    -------------------------------------------------------------------------------------
    Given an integer array of size n, find all elements that appear more than ⌊n/3⌋ times.

    Important observations:
    • There can be at most TWO elements that appear more than n/3 times
    • A brute-force or HashMap-based approach is possible, but the optimal solution uses
      a modified version of Moore’s Voting Algorithm

    This problem is a natural extension of the majority element (> n/2 times) problem.
    =====================================================================================
    */

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 1, 1, 2, 2};

        List<Integer> result = findMajorityElements(arr);

        System.out.println("Majority elements (> n/3 times): " + result);
    }

    /*
    =====================================================================================
    FUNCTION: findMajorityElements
    -------------------------------------------------------------------------------------
    This function finds all elements that appear more than n/3 times using the extended
    Moore’s Voting Algorithm.

    CORE IDEA:
    • Since more than n/3 times → at most 2 elements can satisfy the condition
    • Maintain two candidates and two counters
    • First pass: find potential candidates by cancelling out different elements
    • Second pass: verify the actual frequency of candidates

    Time Complexity  : O(n)
    Space Complexity : O(1)
    =====================================================================================
    */
    static List<Integer> findMajorityElements(int[] arr) {

        int candidate1 = 0, candidate2 = 0;
        int count1 = 0, count2 = 0;

        // Phase 1: Find potential candidates
        for (int num : arr) {

            if (num == candidate1) {
                count1++;
            } 
            else if (num == candidate2) {
                count2++;
            } 
            else if (count1 == 0) {
                candidate1 = num;
                count1 = 1;
            } 
            else if (count2 == 0) {
                candidate2 = num;
                count2 = 1;
            } 
            else {
                count1--;
                count2--;
            }
        }

        // Phase 2: Verify candidates
        count1 = 0;
        count2 = 0;

        for (int num : arr) {
            if (num == candidate1)
                count1++;
            else if (num == candidate2)
                count2++;
        }

        List<Integer> result = new ArrayList<>();
        int threshold = arr.length / 3;

        if (count1 > threshold)
            result.add(candidate1);
        if (count2 > threshold)
            result.add(candidate2);

        return result;
    }
}
