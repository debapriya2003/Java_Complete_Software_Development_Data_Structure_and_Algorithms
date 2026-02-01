package arrays.medium;

import java.util.Arrays;

public class NextPermutation {

    /*
    =====================================================================================
    PROBLEM: NEXT PERMUTATION
    -------------------------------------------------------------------------------------
    Given an array of integers representing a permutation, the task is to rearrange the
    array into the next lexicographically greater permutation. If such a permutation
    does not exist (i.e., the array is in descending order), rearrange it into the
    smallest possible permutation (sorted in ascending order).

    This problem tests understanding of permutations, array traversal from right to left,
    and in-place manipulation.
    =====================================================================================
    */

    public static void main(String[] args) {

        int[] arr = {1, 2, 3};

        nextPermutation(arr);

        System.out.println("Next permutation:");
        System.out.println(Arrays.toString(arr));
    }

    /*
    =====================================================================================
    FUNCTION: nextPermutation
    -------------------------------------------------------------------------------------
    This function transforms the given array into its next permutation in-place.

    ALGORITHM STEPS:
    1. Traverse from right and find the first index i such that arr[i] < arr[i+1]
       (this is the "break point")
    2. If no such index exists, reverse the entire array (last permutation case)
    3. Otherwise, find the smallest element greater than arr[i] to the right of i
    4. Swap these two elements
    5. Reverse the subarray to the right of index i

    This guarantees the next lexicographically smallest permutation.

    Time Complexity  : O(n)
    Space Complexity : O(1)
    =====================================================================================
    */
    static void nextPermutation(int[] arr) {

        int n = arr.length;
        int index = -1;

        // Step 1: Find the break point
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] < arr[i + 1]) {
                index = i;
                break;
            }
        }

        // Step 2: If no break point, reverse entire array
        if (index == -1) {
            reverse(arr, 0, n - 1);
            return;
        }

        // Step 3: Find element just greater than arr[index]
        for (int i = n - 1; i > index; i--) {
            if (arr[i] > arr[index]) {
                swap(arr, i, index);
                break;
            }
        }

        // Step 4: Reverse the suffix
        reverse(arr, index + 1, n - 1);
    }

    /*
    =====================================================================================
    HELPER FUNCTION: reverse
    -------------------------------------------------------------------------------------
    Reverses elements of the array between given indices.
    =====================================================================================
    */
    static void reverse(int[] arr, int start, int end) {

        while (start < end) {
            swap(arr, start, end);
            start++;
            end--;
        }
    }

    /*
    =====================================================================================
    HELPER FUNCTION: swap
    -------------------------------------------------------------------------------------
    Swaps two elements in the array.
    =====================================================================================
    */
    static void swap(int[] arr, int i, int j) {

        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}

