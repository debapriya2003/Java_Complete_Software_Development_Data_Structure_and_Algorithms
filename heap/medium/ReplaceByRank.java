package heap.medium;
import java.util.Arrays;
import java.util.HashMap;

/*
=====================================================================================
REPLACE EACH ARRAY ELEMENT BY ITS CORRESPONDING RANK
-------------------------------------------------------------------------------------
Problem:
Given an array of integers, replace each element with its rank when the array is
sorted in ascending order.

Rules:
• The smallest element gets rank 1
• Equal elements receive the SAME rank
• Ranks increase consecutively (no gaps)

Example:
Input : [20, 15, 26, 2, 98, 6]
Sorted: [2, 6, 15, 20, 26, 98]
Ranks :  1  2  3   4   5   6
Output: [4, 3, 5, 1, 6, 2]

This problem is commonly used to test:
• Hashing
• Sorting
• Mapping values to compressed coordinates
=====================================================================================
*/

public class ReplaceByRank {

    /*
    =====================================================================================
    FUNCTION: replaceByRank
    -------------------------------------------------------------------------------------
    This function replaces each element of the array with its rank.

    ALGORITHM:
    1. Create a copy of the original array
    2. Sort the copied array
    3. Assign ranks using a HashMap (value → rank)
    4. Traverse original array and replace elements using the map

    WHY HASHMAP?
    • Allows O(1) lookup of rank for each value
    • Handles duplicate values correctly

    Time Complexity  : O(n log n)
    Space Complexity : O(n)
    =====================================================================================
    */
    public static int[] replaceByRank(int[] arr) {

        int n = arr.length;

        // Step 1: Copy original array
        int[] sorted = arr.clone();

        // Step 2: Sort the copied array
        Arrays.sort(sorted);

        // Step 3: Assign ranks
        HashMap<Integer, Integer> rankMap = new HashMap<>();
        int rank = 1;

        for (int i = 0; i < n; i++) {
            // Assign rank only if element not already ranked (handles duplicates)
            if (!rankMap.containsKey(sorted[i])) {
                rankMap.put(sorted[i], rank);
                rank++;
            }
        }

        // Step 4: Replace original elements with their ranks
        for (int i = 0; i < n; i++) {
            arr[i] = rankMap.get(arr[i]);
        }

        return arr;
    }

    /*
    =====================================================================================
    MAIN METHOD: DRIVER CODE
    -------------------------------------------------------------------------------------
    Demonstrates replacement of array elements by their ranks.
    =====================================================================================
    */
    public static void main(String[] args) {

        int[] arr = {20, 15, 26, 2, 98, 6};

        System.out.println("Original Array:");
        System.out.println(Arrays.toString(arr));

        replaceByRank(arr);

        System.out.println("Array After Replacing Elements with Ranks:");
        System.out.println(Arrays.toString(arr));
    }
}
