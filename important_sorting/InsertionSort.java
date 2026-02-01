package important_sorting;
/****************************************************************************************
 * FILE NAME : InsertionSort.java
 * PURPOSE   : Explain Insertion Sort algorithm with detailed theory and implementation
 * LEVEL     : Beginner (DSA + Exam Oriented)
 ****************************************************************************************/

import java.util.Arrays;

public class InsertionSort {

    /*
    =====================================================================================
    🔷 INSERTION SORT – COMPLETE THEORY
    =====================================================================================

    WHAT IS INSERTION SORT?
    -------------------------------------------------------------------------------------
    Insertion Sort is a simple comparison-based sorting algorithm that builds the final
    sorted array one element at a time. It works in a way similar to how we sort playing
    cards in our hands. The array is divided into two parts: a sorted part and an unsorted
    part. Initially, the sorted part contains only the first element, and the unsorted
    part contains the remaining elements.

    -------------------------------------------------------------------------------------
    HOW INSERTION SORT WORKS (INTUITION)
    -------------------------------------------------------------------------------------
    • Start from the second element of the array
    • Compare the current element with elements before it
    • Shift all larger elements one position to the right
    • Insert the current element at its correct position
    • Repeat until the entire array is sorted

    After each pass, the left portion of the array remains sorted.

    -------------------------------------------------------------------------------------
    WHY IT IS CALLED “INSERTION” SORT?
    -------------------------------------------------------------------------------------
    Each element from the unsorted part is picked and inserted into its correct position
    in the sorted part of the array. This insertion process gives the algorithm its name.

    -------------------------------------------------------------------------------------
    TIME AND SPACE COMPLEXITY
    -------------------------------------------------------------------------------------
    Best Case  : O(n)     → when the array is already sorted
    Average   : O(n²)
    Worst Case: O(n²)    → when the array is sorted in reverse order
    Space     : O(1)     → in-place sorting algorithm

    -------------------------------------------------------------------------------------
    IMPORTANT CHARACTERISTICS
    -------------------------------------------------------------------------------------
    • Simple and intuitive
    • Stable sorting algorithm
    • In-place (no extra memory required)
    • Efficient for small datasets
    • Performs well on nearly sorted arrays

    =====================================================================================
    */

    public static void main(String[] args) {

        int[] arr = {9, 5, 1, 4, 3};

        System.out.println("Original Array:");
        System.out.println(Arrays.toString(arr));

        insertionSort(arr);

        System.out.println("\nSorted Array:");
        System.out.println(Arrays.toString(arr));
    }

    /*
    =====================================================================================
    🔷 INSERTION SORT IMPLEMENTATION
    -------------------------------------------------------------------------------------
    This function sorts the given array using Insertion Sort. The algorithm assumes that
    the first element is already sorted. Then, it picks elements one by one from the
    unsorted part and places them at the correct position in the sorted part by shifting
    larger elements to the right. This process continues until the entire array is sorted.
    =====================================================================================
    */
    static void insertionSort(int[] arr) {

        int n = arr.length;

        // Loop through unsorted part of the array
        for (int i = 1; i < n; i++) {

            int key = arr[i];     // Element to be inserted
            int j = i - 1;

            // Shift elements of sorted part to the right
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }

            // Insert the key at correct position
            arr[j + 1] = key;
        }
    }
}
