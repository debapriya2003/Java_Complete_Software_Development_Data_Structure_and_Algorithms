package important_sorting;
/****************************************************************************************
 * FILE NAME : MergeSort.java
 * PURPOSE   : Explain Merge Sort algorithm with detailed theory and implementation
 * LEVEL     : Beginner to Intermediate (DSA + Exam Oriented)
 ****************************************************************************************/

import java.util.Arrays;

public class MergeSort {

    /*
    =====================================================================================
    🔷 MERGE SORT – COMPLETE THEORY
    =====================================================================================

    WHAT IS MERGE SORT?
    -------------------------------------------------------------------------------------
    Merge Sort is a divide-and-conquer based sorting algorithm. Instead of sorting the
    array directly, it divides the array into smaller subarrays, sorts them recursively,
    and then merges the sorted subarrays to produce the final sorted array. This approach
    makes Merge Sort very efficient and predictable in performance.

    -------------------------------------------------------------------------------------
    DIVIDE AND CONQUER STRATEGY
    -------------------------------------------------------------------------------------
    Merge Sort follows three main steps:
    1. Divide   : Split the array into two halves.
    2. Conquer  : Recursively sort both halves.
    3. Combine  : Merge the two sorted halves into one sorted array.

    The division continues until subarrays of size one are obtained, as a single-element
    array is always considered sorted.

    -------------------------------------------------------------------------------------
    WHY MERGE SORT IS IMPORTANT?
    -------------------------------------------------------------------------------------
    Unlike Bubble Sort or Insertion Sort, Merge Sort guarantees O(n log n) time complexity
    in all cases (best, average, and worst). This makes it suitable for sorting large
    datasets. It is also a stable sorting algorithm, meaning it preserves the relative
    order of equal elements.

    -------------------------------------------------------------------------------------
    TIME AND SPACE COMPLEXITY
    -------------------------------------------------------------------------------------
    Best Case   : O(n log n)
    Average    : O(n log n)
    Worst Case : O(n log n)
    Space      : O(n)  → requires extra memory for merging

    -------------------------------------------------------------------------------------
    IMPORTANT CHARACTERISTICS
    -------------------------------------------------------------------------------------
    • Divide-and-conquer algorithm
    • Stable sorting algorithm
    • Not in-place (uses extra memory)
    • Consistent performance
    • Used internally in many standard libraries for sorting large data

    =====================================================================================
    */

    public static void main(String[] args) {

        int[] arr = {38, 27, 43, 3, 9, 82, 10};

        System.out.println("Original Array:");
        System.out.println(Arrays.toString(arr));

        mergeSort(arr, 0, arr.length - 1);

        System.out.println("\nSorted Array:");
        System.out.println(Arrays.toString(arr));
    }

    /*
    =====================================================================================
    🔷 MERGE SORT FUNCTION (DIVIDE PHASE)
    -------------------------------------------------------------------------------------
    This function divides the array into two halves using recursion. It calculates the
    middle index and recursively calls itself for the left and right halves. Once the
    subarrays are reduced to size one, the merge function is called to combine them in
    sorted order.
    =====================================================================================
    */
    static void mergeSort(int[] arr, int left, int right) {

        if (left >= right)
            return; // Base case: single element

        int mid = left + (right - left) / 2;

        // Recursively sort left half
        mergeSort(arr, left, mid);

        // Recursively sort right half
        mergeSort(arr, mid + 1, right);

        // Merge sorted halves
        merge(arr, left, mid, right);
    }

    /*
    =====================================================================================
    🔷 MERGE FUNCTION (COMBINE PHASE)
    -------------------------------------------------------------------------------------
    This function merges two sorted subarrays into a single sorted array. The first
    subarray ranges from left to mid, and the second ranges from mid+1 to right. Temporary
    arrays are used to store the elements, and a comparison-based merging process ensures
    the final order is sorted.
    =====================================================================================
    */
    static void merge(int[] arr, int left, int mid, int right) {

        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Temporary arrays
        int[] L = new int[n1];
        int[] R = new int[n2];

        // Copy data into temporary arrays
        for (int i = 0; i < n1; i++)
            L[i] = arr[left + i];

        for (int j = 0; j < n2; j++)
            R[j] = arr[mid + 1 + j];

        int i = 0, j = 0, k = left;

        // Merge the temporary arrays
        while (i < n1 && j < n2) {

            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of L[]
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        // Copy remaining elements of R[]
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
}

