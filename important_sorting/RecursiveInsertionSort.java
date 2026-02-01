package important_sorting;

/****************************************************************************************
 * FILE NAME : RecursiveInsertionSort.java
 * PURPOSE   : Explain Insertion Sort using recursion with theory and implementation
 * LEVEL     : Beginner (DSA + Exam Oriented)
 ****************************************************************************************/

import java.util.Arrays;

public class RecursiveInsertionSort {

    /*
    =====================================================================================
    🔷 RECURSIVE INSERTION SORT – COMPLETE THEORY
    =====================================================================================

    WHAT IS RECURSIVE INSERTION SORT?
    -------------------------------------------------------------------------------------
    Recursive Insertion Sort is a recursive form of the standard Insertion Sort algorithm.
    Instead of iterating through the array using loops, recursion is used to sort the array
    gradually. The idea is to sort the first (n-1) elements recursively and then insert the
    nth element into its correct position in the already sorted subarray.

    -------------------------------------------------------------------------------------
    HOW RECURSIVE INSERTION SORT WORKS
    -------------------------------------------------------------------------------------
    • Recursively sort the first (n-1) elements of the array
    • Take the last element (nth element)
    • Shift all elements greater than this element one position to the right
    • Insert the element at its correct position
    • Repeat until the base case is reached

    -------------------------------------------------------------------------------------
    BASE CASE AND RECURSIVE CASE
    -------------------------------------------------------------------------------------
    Base Case:
    • If the array size is 1, it is already sorted

    Recursive Case:
    • Sort the first (n-1) elements
    • Insert the last element into the sorted part

    -------------------------------------------------------------------------------------
    TIME AND SPACE COMPLEXITY
    -------------------------------------------------------------------------------------
    Best Case   : O(n²)
    Average    : O(n²)
    Worst Case : O(n²)
    Space      : O(n) → recursion call stack

    -------------------------------------------------------------------------------------
    IMPORTANT CHARACTERISTICS
    -------------------------------------------------------------------------------------
    • Stable sorting algorithm
    • In-place sorting (no extra array used)
    • Less efficient than iterative insertion sort
    • Useful for understanding recursion and divide logic
    • Rarely used in practical applications

    =====================================================================================
    */

    public static void main(String[] args) {

        int[] arr = {9, 5, 1, 4, 3};

        System.out.println("Original Array:");
        System.out.println(Arrays.toString(arr));

        recursiveInsertionSort(arr, arr.length);

        System.out.println("\nSorted Array:");
        System.out.println(Arrays.toString(arr));
    }

    /*
    =====================================================================================
    🔷 RECURSIVE INSERTION SORT IMPLEMENTATION
    -------------------------------------------------------------------------------------
    This function recursively sorts the array. It assumes that the first (n-1) elements
    are sorted and inserts the nth element into its correct position. The recursion
    continues until the base case of n = 1 is reached.
    =====================================================================================
    */
    static void recursiveInsertionSort(int[] arr, int n) {

        // Base case: single element is already sorted
        if (n <= 1)
            return;

        // Recursively sort first (n - 1) elements
        recursiveInsertionSort(arr, n - 1);

        // Insert last element at correct position
        int key = arr[n - 1];
        int j = n - 2;

        while (j >= 0 && arr[j] > key) {
            arr[j + 1] = arr[j];
            j--;
        }

        arr[j + 1] = key;
    }
}
