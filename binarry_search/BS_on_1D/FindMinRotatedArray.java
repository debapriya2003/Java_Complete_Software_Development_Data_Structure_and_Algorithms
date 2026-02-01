package binarry_search.BS_on_1D;

public class FindMinRotatedArray {

    /*
    =====================================================================================
    PROBLEM: FIND MINIMUM ELEMENT IN A ROTATED SORTED ARRAY
    -------------------------------------------------------------------------------------
    A rotated sorted array is an array that was originally sorted in ascending order
    but then rotated at some pivot unknown to us.

    Example:
    Original Array : [1, 2, 3, 4, 5]
    Rotated Array  : [3, 4, 5, 1, 2]

    The task is to find the minimum element in such an array efficiently.

    This problem is important because it demonstrates the use of Binary Search
    on modified sorted structures.
    =====================================================================================
    */

    public static void main(String[] args) {

        int[] arr1 = {3, 4, 5, 1, 2};
        System.out.println("Array: " + java.util.Arrays.toString(arr1));
        System.out.println("Minimum: " + findMin(arr1));

        int[] arr2 = {2, 1};
        System.out.println("Array: " + java.util.Arrays.toString(arr2));
        System.out.println("Minimum: " + findMin(arr2));

        int[] arr3 = {1, 2, 3, 4, 5};
        System.out.println("Array: " + java.util.Arrays.toString(arr3));
        System.out.println("Minimum: " + findMin(arr3));
    }

    /*
    =====================================================================================
    FUNCTION: findMin
    -------------------------------------------------------------------------------------
    This function finds the minimum element in a rotated sorted array using
    Binary Search.

    KEY OBSERVATIONS:
    • If the array is already sorted, the first element is the minimum
    • The minimum element is the only element smaller than its previous element
    • Binary search helps reduce time complexity from O(n) to O(log n)

    Time Complexity  : O(log n)
    Space Complexity : O(1)
    =====================================================================================
    */
    static int findMin(int[] arr) {

        int left = 0;
        int right = arr.length - 1;

        // Continue binary search while search space is valid
        while (left < right) {

            // If subarray is already sorted, leftmost element is minimum
            if (arr[left] < arr[right]) {
                return arr[left];
            }

            // Calculate mid safely to avoid overflow
            int mid = left + (right - left) / 2;

            // Case 1: mid element is smaller than its previous element
            // This means mid itself is the minimum
            if (mid > 0 && arr[mid] < arr[mid - 1]) {
                return arr[mid];
            }

            // Case 2: mid element is greater than next element
            // This means next element is the minimum
            if (mid < arr.length - 1 && arr[mid] > arr[mid + 1]) {
                return arr[mid + 1];
            }

            // Decide which half to continue searching
            if (arr[mid] > arr[right]) {
                // Minimum lies in the right half
                left = mid + 1;
            } else {
                // Minimum lies in the left half including mid
                right = mid;
            }
        }

        // When left == right, it points to the minimum element
        return arr[left];
    }
}
