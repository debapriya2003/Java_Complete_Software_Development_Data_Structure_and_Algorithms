package arrays.medium;

public class MajorityElement {

    /*
    =====================================================================================
    PROBLEM: FIND MAJORITY ELEMENT
    -------------------------------------------------------------------------------------
    Given an array of integers of size n, a majority element is defined as an element
    that appears more than n/2 times in the array.

    It is guaranteed in some problems that a majority element exists. A brute-force
    solution using nested loops or hashing is possible, but the optimal solution is
    Moore’s Voting Algorithm, which works in linear time and constant space.
    =====================================================================================
    */

    public static void main(String[] args) {

        int[] arr = {2, 2, 1, 1, 1, 2, 2};

        int majority = findMajorityElement(arr);

        System.out.println("Majority element: " + majority);
    }

    /*
    =====================================================================================
    FUNCTION: findMajorityElement
    -------------------------------------------------------------------------------------
    This function finds the majority element using Moore’s Voting Algorithm.

    CORE IDEA:
    • Maintain a candidate element and a count
    • If count becomes 0, choose the current element as the new candidate
    • Increment count if the current element matches the candidate
    • Decrement count otherwise

    WHY THIS WORKS:
    Since the majority element appears more than n/2 times, it will always survive the
    cancellation process and remain as the final candidate.

    Time Complexity  : O(n)
    Space Complexity : O(1)
    =====================================================================================
    */
    static int findMajorityElement(int[] arr) {

        int candidate = 0;
        int count = 0;

        // Phase 1: Find potential candidate
        for (int num : arr) {

            if (count == 0) {
                candidate = num;
                count = 1;
            } else if (num == candidate) {
                count++;
            } else {
                count--;
            }
        }

        // Phase 2 (Optional): Verify candidate
        // Required only if majority element is NOT guaranteed
        int frequency = 0;
        for (int num : arr) {
            if (num == candidate)
                frequency++;
        }

        if (frequency > arr.length / 2)
            return candidate;
        else
            return -1; // No majority element
    }
}

