package arrays.hard;

import java.util.Arrays;

public class MergeTwoSortedArraysWithoutExtraSpace {

    /*
    =====================================================================================
    PROBLEM: MERGE TWO SORTED ARRAYS WITHOUT EXTRA SPACE
    -------------------------------------------------------------------------------------
    Given two sorted arrays arr1[] and arr2[], the task is to merge them such that after
    merging, both arrays remain sorted. The merge must be done WITHOUT using any extra
    space (i.e., in-place).

    A brute-force approach using an extra array violates the constraint. The optimal
    solution uses the GAP method inspired by Shell Sort to compare elements at a certain
    distance and reduce that distance gradually.
    =====================================================================================
    */

    public static void main(String[] args) {

        int[] arr1 = {1, 4, 7, 8, 10};
        int[] arr2 = {2, 3, 9};

        merge(arr1, arr2);

        System.out.println("Array 1 after merge: " + Arrays.toString(arr1));
        System.out.println("Array 2 after merge: " + Arrays.toString(arr2));
    }

    /*
    =====================================================================================
    FUNCTION: merge
    -------------------------------------------------------------------------------------
    This function merges two sorted arrays using the GAP method.

    GAP METHOD IDEA:
    • Treat both arrays as a single combined array
    • Start with a large gap = ceil((n + m) / 2)
    • Compare elements at distance = gap and swap if needed
    • Reduce the gap until it becomes 0

    This ensures the arrays become sorted without extra space.

    Time Complexity  : O((n + m) log(n + m))
    Space Complexity : O(1)
    =====================================================================================
    */
    static void merge(int[] arr1, int[] arr2) {

        int n = arr1.length;
        int m = arr2.length;

        int gap = nextGap(n + m);

        while (gap > 0) {

            int i = 0;
            int j = gap;

            while (j < n + m) {

                // Case 1: both pointers in arr1
                if (i < n && j < n) {
                    if (arr1[i] > arr1[j]) {
                        swap(arr1, arr1, i, j);
                    }
                }
                // Case 2: i in arr1, j in arr2
                else if (i < n && j >= n) {
                    if (arr1[i] > arr2[j - n]) {
                        swap(arr1, arr2, i, j - n);
                    }
                }
                // Case 3: both pointers in arr2
                else {
                    if (arr2[i - n] > arr2[j - n]) {
                        swap(arr2, arr2, i - n, j - n);
                    }
                }

                i++;
                j++;
            }

            gap = nextGap(gap);
        }
    }

    /*
    =====================================================================================
    HELPER FUNCTION: nextGap
    -------------------------------------------------------------------------------------
    Computes the next gap value.
    =====================================================================================
    */
    static int nextGap(int gap) {
        if (gap <= 1)
            return 0;
        return (gap / 2) + (gap % 2);
    }

    /*
    =====================================================================================
    HELPER FUNCTION: swap
    -------------------------------------------------------------------------------------
    Swaps elements between two arrays.
    =====================================================================================
    */
    static void swap(int[] a, int[] b, int i, int j) {

        int temp = a[i];
        a[i] = b[j];
        b[j] = temp;
    }
}
