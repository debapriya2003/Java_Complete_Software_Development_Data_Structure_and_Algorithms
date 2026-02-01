package arrays.medium;

import java.util.HashSet;

public class LongestConsecutiveSequence {

    /*
    =====================================================================================
    PROBLEM: LONGEST CONSECUTIVE SEQUENCE IN AN ARRAY
    -------------------------------------------------------------------------------------
    Given an unsorted array of integers, the task is to find the length of the longest
    sequence of consecutive integers (numbers that follow each other without gaps).

    The sequence does not need to be contiguous in the array, but the numbers must be
    consecutive. For example, in the array {100, 4, 200, 1, 3, 2}, the longest consecutive
    sequence is {1, 2, 3, 4} with length 4.

    A brute-force or sorting-based approach would be inefficient. The optimal solution
    uses hashing to achieve linear time complexity.
    =====================================================================================
    */

    public static void main(String[] args) {

        int[] arr = {100, 4, 200, 1, 3, 2};

        int longest = longestConsecutive(arr);

        System.out.println("Length of longest consecutive sequence: " + longest);
    }

    /*
    =====================================================================================
    FUNCTION: longestConsecutive
    -------------------------------------------------------------------------------------
    This function finds the length of the longest consecutive sequence using a HashSet.

    CORE IDEA:
    • Insert all elements into a HashSet for O(1) lookup
    • A number can be the start of a sequence only if (number - 1) does not exist
    • From such a starting number, keep checking consecutive numbers
    • Track the maximum length encountered

    WHY THIS WORKS:
    Each element is processed at most once, ensuring linear time complexity.

    Time Complexity  : O(n)
    Space Complexity : O(n)
    =====================================================================================
    */
    static int longestConsecutive(int[] arr) {

        if (arr.length == 0)
            return 0;

        HashSet<Integer> set = new HashSet<>();

        // Insert all elements into the set
        for (int num : arr) {
            set.add(num);
        }

        int longestStreak = 0;

        // Check for start of sequences
        for (int num : set) {

            // Only start counting if num is the beginning of a sequence
            if (!set.contains(num - 1)) {

                int currentNum = num;
                int currentStreak = 1;

                // Count consecutive numbers
                while (set.contains(currentNum + 1)) {
                    currentNum++;
                    currentStreak++;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }
}

