package arrays.hard;

public class ReversePairs {

    /*
    =====================================================================================
    PROBLEM: REVERSE PAIRS
    -------------------------------------------------------------------------------------
    Given an integer array nums, a reverse pair is a pair of indices (i, j) such that:
        i < j  AND  nums[i] > 2 * nums[j]

    The task is to count the total number of such reverse pairs in the array.

    A brute-force approach checks all pairs and takes O(n²) time, which is inefficient
    for large inputs. The optimal solution uses a modified Merge Sort to reduce the time
    complexity to O(n log n).
    =====================================================================================
    */

    public static void main(String[] args) {

        int[] arr = {1, 3, 2, 3, 1};

        int count = reversePairs(arr);

        System.out.println("Number of reverse pairs: " + count);
    }

    /*
    =====================================================================================
    FUNCTION: reversePairs
    -------------------------------------------------------------------------------------
    Wrapper function that initiates the merge-sort-based reverse pair counting process.

    Time Complexity  : O(n log n)
    Space Complexity : O(n)
    =====================================================================================
    */
    static int reversePairs(int[] arr) {

        if (arr.length <= 1)
            return 0;

        return mergeSort(arr, 0, arr.length - 1);
    }

    /*
    =====================================================================================
    FUNCTION: mergeSort
    -------------------------------------------------------------------------------------
    Recursively divides the array and counts reverse pairs during merging.

    Steps:
    • Divide array into two halves
    • Count reverse pairs in left half
    • Count reverse pairs in right half
    • Count cross reverse pairs
    • Merge the two sorted halves
    =====================================================================================
    */
    static int mergeSort(int[] arr, int left, int right) {

        if (left >= right)
            return 0;

        int mid = left + (right - left) / 2;
        int count = 0;

        count += mergeSort(arr, left, mid);
        count += mergeSort(arr, mid + 1, right);
        count += countPairs(arr, left, mid, right);
        merge(arr, left, mid, right);

        return count;
    }

    /*
    =====================================================================================
    FUNCTION: countPairs
    -------------------------------------------------------------------------------------
    Counts reverse pairs where:
        left <= i <= mid
        mid + 1 <= j <= right

    Since both halves are sorted, we can use two pointers to count efficiently.

    CONDITION:
        arr[i] > 2 * arr[j]

    Time Complexity: O(n) per merge
    =====================================================================================
    */
    static int countPairs(int[] arr, int left, int mid, int right) {

        int count = 0;
        int j = mid + 1;

        for (int i = left; i <= mid; i++) {
            while (j <= right && (long) arr[i] > 2L * arr[j]) {
                j++;
            }
            count += (j - (mid + 1));
        }

        return count;
    }

    /*
    =====================================================================================
    FUNCTION: merge
    -------------------------------------------------------------------------------------
    Standard merge function used in Merge Sort.
    =====================================================================================
    */
    static void merge(int[] arr, int left, int mid, int right) {

        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j])
                temp[k++] = arr[i++];
            else
                temp[k++] = arr[j++];
        }

        while (i <= mid)
            temp[k++] = arr[i++];

        while (j <= right)
            temp[k++] = arr[j++];

        for (int p = 0; p < temp.length; p++) {
            arr[left + p] = temp[p];
        }
    }
}
