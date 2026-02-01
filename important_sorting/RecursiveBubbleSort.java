package important_sorting;
/****************************************************************************************
 * FILE NAME : RecursiveBubbleSort.java
 * PURPOSE   : Explain Bubble Sort using recursion with theory and implementation
 * LEVEL     : Beginner (DSA + Exam Oriented)
 ****************************************************************************************/

import java.util.Arrays;

public class RecursiveBubbleSort {

    /*
    =====================================================================================
    🔷 RECURSIVE BUBBLE SORT – COMPLETE THEORY
    =====================================================================================

    WHAT IS RECURSIVE BUBBLE SORT?
    -------------------------------------------------------------------------------------
    Recursive Bubble Sort is a recursive version of the traditional Bubble Sort algorithm.
    Instead of using loops to repeat passes over the array, recursion is used to reduce the
    problem size step by step. In each recursive call, the largest element in the current
    range is moved to its correct position at the end of the array.

    -------------------------------------------------------------------------------------
    HOW RECURSIVE BUBBLE SORT WORKS
    -------------------------------------------------------------------------------------
    • Perform one pass of Bubble Sort to push the largest element to the end
    • Reduce the array size by one
    • Recursively apply Bubble Sort on the remaining array
    • Stop when array size becomes 1 (base case)

    This mirrors the iterative logic but replaces the outer loop with recursion.

    -------------------------------------------------------------------------------------
    BASE CASE AND RECURSIVE CASE
    -------------------------------------------------------------------------------------
    Base Case:
    • If the size of the array is 1, it is already sorted

    Recursive Case:
    • Bubble the largest element to the end
    • Call the function again with size reduced by 1

    -------------------------------------------------------------------------------------
    TIME AND SPACE COMPLEXITY
    -------------------------------------------------------------------------------------
    Best Case   : O(n²)
    Average    : O(n²)
    Worst Case : O(n²)
    Space      : O(n) → due to recursion stack

    -------------------------------------------------------------------------------------
    IMPORTANT CHARACTERISTICS
    -------------------------------------------------------------------------------------
    • Stable sorting algorithm
    • In-place sorting (no extra array used)
    • Slower than iterative version due to recursion overhead
    • Mainly used for understanding recursion, not practical usage

    =====================================================================================
    */

    public static void main(String[] args) {

        int[] arr = {5, 1, 4, 2, 8};

        System.out.println("Original Array:");
        System.out.println(Arrays.toString(arr));

        recursiveBubbleSort(arr, arr.length);

        System.out.println("\nSorted Array:");
        System.out.println(Arrays.toString(arr));
    }

    /*
    =====================================================================================
    🔷 RECURSIVE BUBBLE SORT IMPLEMENTATION
    -------------------------------------------------------------------------------------
    This function sorts the array using recursion. In each recursive call, a single pass
    of Bubble Sort is performed to move the largest element to the end of the array.
    Then the function calls itself with the size reduced by one. The recursion stops
    when the size becomes 1.
    =====================================================================================
    */
    static void recursiveBubbleSort(int[] arr, int n) {

        // Base case: array of size 1 is already sorted
        if (n == 1)
            return;

        // One pass of bubble sort
        for (int i = 0; i < n - 1; i++) {

            if (arr[i] > arr[i + 1]) {
                // Swap adjacent elements
                int temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
            }
        }

        // Recursive call for remaining array
        recursiveBubbleSort(arr, n - 1);
    }
}
