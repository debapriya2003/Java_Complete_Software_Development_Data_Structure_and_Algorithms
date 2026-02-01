package arrays.hard;

public class CountInversions {

    /*
    =====================================================================================
    PROBLEM: COUNT INVERSIONS IN AN ARRAY
    -------------------------------------------------------------------------------------
    An inversion in an array is a pair of indices (i, j) such that:
        i < j  AND  arr[i] > arr[j]

    Inversions indicate how far the array is from being sorted. A sorted array has
    zero inversions, while a reverse-sorted array has the maximum number of inversions.

    A brute-force solution checks all pairs and runs in O(n²) time. The optimal solution
    uses a modified Merge Sort algorithm to count inversions efficiently in O(n log n).
    =====================================================================================
    */

    public static void main(String[] args) {

        int[] arr = {5, 3, 2, 4, 1};

        int inversions = countInversions(arr);

        System.out.println("Number of inversions: " + inversions);
    }

    /*
    =====================================================================================
    FUNCTION: countInversions
    -------------------------------------------------------------------------------------
    This function acts as a wrapper that calls the merge-sort-based inversion counter.

    Time Complexity  : O(n log n)
    Space Complexity : O(n)
    =====================================================================================
    */
    static int countInversions(int[] arr) {

        if (arr.length <= 1)
            return 0;

        int[] temp = new int[arr.length];
        return mergeSortAndCount(arr, temp, 0, arr.length - 1);
    }

    /*
    =====================================================================================
    FUNCTION: mergeSortAndCount
    -------------------------------------------------------------------------------------
    This function recursively divides the array into halves and counts inversions during
    the merge step.

    Logic:
    • Divide array into two halves
    • Count inversions in left half
    • Count inversions in right half
    • Count split inversions while merging

    Total inversions = left + right + split inversions
    =====================================================================================
    */
    static int mergeSortAndCount(int[] arr, int[] temp, int left, int right) {

        int mid, invCount = 0;

        if (left < right) {

            mid = (left + right) / 2;

            // Count inversions in left half
            invCount += mergeSortAndCount(arr, temp, left, mid);

            // Count inversions in right half
            invCount += mergeSortAndCount(arr, temp, mid + 1, right);

            // Count split inversions
            invCount += mergeAndCount(arr, temp, left, mid, right);
        }

        return invCount;
    }

    /*
    =====================================================================================
    FUNCTION: mergeAndCount
    -------------------------------------------------------------------------------------
    This function merges two sorted subarrays and counts split inversions.

    SPLIT INVERSION LOGIC:
    If arr[i] > arr[j] (where i is in left subarray and j is in right subarray),
    then all remaining elements from i to mid will also be greater than arr[j].

    Number of inversions added:
        (mid - i + 1)

    This is the key optimization that avoids O(n²).
    =====================================================================================
    */
    static int mergeAndCount(int[] arr, int[] temp,
                             int left, int mid, int right) {

        int i = left;      // Pointer for left subarray
        int j = mid + 1;   // Pointer for right subarray
        int k = left;      // Pointer for temp array
        int invCount = 0;

        while (i <= mid && j <= right) {

            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
                invCount += (mid - i + 1); // Count inversions
            }
        }

        // Copy remaining elements
        while (i <= mid)
            temp[k++] = arr[i++];

        while (j <= right)
            temp[k++] = arr[j++];

        // Copy merged elements back to original array
        for (i = left; i <= right; i++)
            arr[i] = temp[i];

        return invCount;
    }
}
