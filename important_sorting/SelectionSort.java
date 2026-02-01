package important_sorting;

/****************************************************************************************
 * FILE NAME : SelectionSort.java
 * PURPOSE   : Explain Selection Sort algorithm with detailed theory and implementation
 * LEVEL     : Beginner (DSA + Exam Oriented)
 ****************************************************************************************/

import java.util.Arrays;

public class SelectionSort {

    /*
    =====================================================================================
    🔷 SELECTION SORT – COMPLETE THEORY
    =====================================================================================

    WHAT IS SELECTION SORT?
    -------------------------------------------------------------------------------------
    Selection Sort is a simple comparison-based sorting algorithm. It works by repeatedly
    selecting the smallest element from the unsorted part of the array and placing it at
    the beginning of the array. The array is divided into two parts: a sorted part at the
    beginning and an unsorted part at the end. With each iteration, the size of the sorted
    part increases by one.

    -------------------------------------------------------------------------------------
    HOW SELECTION SORT WORKS (INTUITION)
    -------------------------------------------------------------------------------------
    • Start from the first position
    • Find the minimum element in the unsorted portion
    • Swap it with the element at the current position
    • Move the boundary of the sorted part one step forward
    • Repeat until the entire array is sorted

    Unlike Bubble Sort, Selection Sort performs at most one swap per pass.

    -------------------------------------------------------------------------------------
    TIME AND SPACE COMPLEXITY
    -------------------------------------------------------------------------------------
    Best Case   : O(n²)
    Average    : O(n²)
    Worst Case : O(n²)
    Space      : O(1) → in-place sorting algorithm

    -------------------------------------------------------------------------------------
    IMPORTANT CHARACTERISTICS
    -------------------------------------------------------------------------------------
    • Simple and easy to implement
    • In-place sorting algorithm
    • Not a stable sort (by default)
    • Performs fewer swaps than Bubble Sort
    • Inefficient for large datasets

    =====================================================================================
    */

    public static void main(String[] args) {

        int[] arr = {64, 25, 12, 22, 11};

        System.out.println("Original Array:");
        System.out.println(Arrays.toString(arr));

        selectionSort(arr);

        System.out.println("\nSorted Array:");
        System.out.println(Arrays.toString(arr));
    }

    /*
    =====================================================================================
    🔷 SELECTION SORT IMPLEMENTATION
    -------------------------------------------------------------------------------------
    This function sorts the array using Selection Sort. In each iteration, it finds the
    smallest element from the unsorted part of the array and swaps it with the first
    element of the unsorted part. This process continues until the entire array becomes
    sorted.
    =====================================================================================
    */
    static void selectionSort(int[] arr) {

        int n = arr.length;

        // Traverse through all array elements
        for (int i = 0; i < n - 1; i++) {

            // Find the index of the minimum element
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }

            // Swap the found minimum element with the first element
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
    }
}

