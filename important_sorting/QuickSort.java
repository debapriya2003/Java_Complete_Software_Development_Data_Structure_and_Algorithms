package important_sorting;

/****************************************************************************************
 * FILE NAME : QuickSort.java
 * PURPOSE   : Explain Quick Sort algorithm with detailed theory and implementation
 * LEVEL     : Beginner to Intermediate (DSA + Exam Oriented)
 ****************************************************************************************/

import java.util.Arrays;

public class QuickSort {

    /*
    =====================================================================================
    🔷 QUICK SORT – COMPLETE THEORY
    =====================================================================================

    WHAT IS QUICK SORT?
    -------------------------------------------------------------------------------------
    Quick Sort is a highly efficient divide-and-conquer based sorting algorithm. It works
    by selecting an element called a pivot and rearranging the array such that all elements
    smaller than the pivot come before it and all elements greater than the pivot come
    after it. The pivot element then reaches its correct sorted position.

    -------------------------------------------------------------------------------------
    DIVIDE AND CONQUER STRATEGY
    -------------------------------------------------------------------------------------
    Quick Sort follows three steps:
    1. Divide   : Choose a pivot element and partition the array around it.
    2. Conquer  : Recursively apply Quick Sort to the left and right subarrays.
    3. Combine  : No explicit combining step is required because partitioning places the
                 pivot in its correct position.

    Unlike Merge Sort, Quick Sort does not need extra arrays for merging.

    -------------------------------------------------------------------------------------
    PIVOT SELECTION
    -------------------------------------------------------------------------------------
    The pivot can be chosen in different ways:
    • First element
    • Last element
    • Middle element
    • Random element

    The performance of Quick Sort heavily depends on pivot selection. A good pivot results
    in balanced partitions, while a poor pivot leads to unbalanced partitions.

    -------------------------------------------------------------------------------------
    TIME AND SPACE COMPLEXITY
    -------------------------------------------------------------------------------------
    Best Case   : O(n log n)
    Average    : O(n log n)
    Worst Case : O(n²) → occurs when pivot selection is poor (already sorted array)
    Space      : O(log n) → due to recursive call stack

    -------------------------------------------------------------------------------------
    IMPORTANT CHARACTERISTICS
    -------------------------------------------------------------------------------------
    • Very fast in practice
    • In-place sorting algorithm
    • Not a stable sort
    • Does not require extra memory like Merge Sort
    • Widely used in real-world applications

    =====================================================================================
    */

    public static void main(String[] args) {

        int[] arr = {10, 7, 8, 9, 1, 5};

        System.out.println("Original Array:");
        System.out.println(Arrays.toString(arr));

        quickSort(arr, 0, arr.length - 1);

        System.out.println("\nSorted Array:");
        System.out.println(Arrays.toString(arr));
    }

    /*
    =====================================================================================
    🔷 QUICK SORT FUNCTION (RECURSIVE DIVIDE PHASE)
    -------------------------------------------------------------------------------------
    This function recursively sorts the array using Quick Sort. It first partitions the
    array around a pivot element, placing the pivot in its correct position. Then it
    recursively applies Quick Sort to the left and right subarrays formed around the pivot.
    =====================================================================================
    */
    static void quickSort(int[] arr, int low, int high) {

        if (low < high) {

            // Partition the array and get pivot index
            int pivotIndex = partition(arr, low, high);

            // Recursively sort left subarray
            quickSort(arr, low, pivotIndex - 1);

            // Recursively sort right subarray
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    /*
    =====================================================================================
    🔷 PARTITION FUNCTION (CORE LOGIC)
    -------------------------------------------------------------------------------------
    This function places the pivot element at its correct sorted position. All elements
    smaller than the pivot are moved to the left, and all elements greater than the pivot
    are moved to the right. Here, the last element of the array is chosen as the pivot.
    =====================================================================================
    */
    static int partition(int[] arr, int low, int high) {

        int pivot = arr[high];   // Choosing last element as pivot
        int i = low - 1;         // Index of smaller element

        for (int j = low; j < high; j++) {

            // If current element is smaller than pivot
            if (arr[j] < pivot) {
                i++;

                // Swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Place pivot in correct position
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1; // Return pivot index
    }
}
