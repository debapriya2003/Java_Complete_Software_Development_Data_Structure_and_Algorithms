package important_sorting;

/****************************************************************************************
 * FILE NAME : BubbleSort.java
 * PURPOSE   : Explain Bubble Sort algorithm with detailed theory and implementation
 * LEVEL     : Beginner (DSA + Exam Oriented)
 ****************************************************************************************/

import java.util.Arrays;

public class BubbleSort {

    /*
    =====================================================================================
    🔷 BUBBLE SORT – COMPLETE THEORY
    =====================================================================================

    WHAT IS BUBBLE SORT?
    -------------------------------------------------------------------------------------
    Bubble Sort is a simple comparison-based sorting algorithm. It works by repeatedly
    comparing adjacent elements in an array and swapping them if they are in the wrong
    order. With each pass, the largest (or smallest) element “bubbles up” to its correct
    position at the end of the array, similar to how air bubbles rise to the surface.

    This algorithm is mainly used for educational purposes because it is easy to
    understand and implement, though it is not efficient for large datasets.

    -------------------------------------------------------------------------------------
    HOW BUBBLE SORT WORKS (INTUITION)
    -------------------------------------------------------------------------------------
    • Compare the first two elements
    • Swap them if they are in the wrong order
    • Move to the next pair and repeat
    • After one full pass, the largest element reaches the end
    • Repeat for the remaining unsorted part of the array

    After each pass, one element is placed in its correct sorted position.

    -------------------------------------------------------------------------------------
    WHY IT IS CALLED “BUBBLE” SORT?
    -------------------------------------------------------------------------------------
    During each pass, larger elements gradually move toward the end of the array, just
    like bubbles rising upward in water. This visual behavior gives the algorithm its
    name.

    -------------------------------------------------------------------------------------
    TIME AND SPACE COMPLEXITY
    -------------------------------------------------------------------------------------
    Best Case  : O(n)     → when array is already sorted (with optimization)
    Average   : O(n²)
    Worst Case: O(n²)
    Space     : O(1)     → in-place sorting algorithm

    -------------------------------------------------------------------------------------
    IMPORTANT CHARACTERISTICS
    -------------------------------------------------------------------------------------
    • Simple and easy to implement
    • Stable sorting algorithm
    • In-place (no extra memory required)
    • Inefficient for large inputs
    • Used mainly for learning sorting fundamentals

    =====================================================================================
    */

    public static void main(String[] args) {

        int[] arr = {5, 1, 4, 2, 8};

        System.out.println("Original Array:");
        System.out.println(Arrays.toString(arr));

        bubbleSort(arr);

        System.out.println("\nSorted Array:");
        System.out.println(Arrays.toString(arr));
    }

    /*
    =====================================================================================
    🔷 BUBBLE SORT IMPLEMENTATION
    -------------------------------------------------------------------------------------
    This function sorts the given array using Bubble Sort. The outer loop represents
    the number of passes. The inner loop compares adjacent elements and swaps them if
    they are in the wrong order. An optimization is included using a boolean flag to
    stop the algorithm early if the array becomes sorted before completing all passes.
    =====================================================================================
    */
    static void bubbleSort(int[] arr) {

        int n = arr.length;
        boolean swapped;

        // Outer loop for number of passes
        for (int i = 0; i < n - 1; i++) {

            swapped = false;

            // Inner loop for comparisons
            for (int j = 0; j < n - 1 - i; j++) {

                // Compare adjacent elements
                if (arr[j] > arr[j + 1]) {

                    // Swap if they are in wrong order
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    swapped = true;
                }
            }

            // If no swaps occurred, array is already sorted
            if (!swapped)
                break;
        }
    }
}

