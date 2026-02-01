package binarry_search.BS_on_1D;

public class SearchRotatedArrayI {

    /*
    =====================================================================================
    PROBLEM: SEARCH IN ROTATED SORTED ARRAY (UNIQUE ELEMENTS)
    -------------------------------------------------------------------------------------
    A rotated sorted array is an array that was originally sorted in ascending order
    but then rotated at an unknown pivot.

    The array contains UNIQUE elements.

    The task is to search for a given target element and return its index.
    If the target is not present, return -1.

    Example:
    Array  = [4, 5, 6, 7, 0, 1, 2]
    Target = 0
    Output = 4

    This problem is a classic variation of Binary Search on a modified sorted array.
    =====================================================================================
    */

    public static void main(String[] args) {

        int[] arr1 = {4, 5, 6, 7, 0, 1, 2};
        System.out.println("Array: " + java.util.Arrays.toString(arr1));
        System.out.println("Search 0: " + search(arr1, 0));
        System.out.println("Search 3: " + search(arr1, 3));

        int[] arr2 = {1};
        System.out.println("Array: " + java.util.Arrays.toString(arr2));
        System.out.println("Search 1: " + search(arr2, 1));
    }

    /*
    =====================================================================================
    FUNCTION: search
    -------------------------------------------------------------------------------------
    Searches for the target element in a rotated sorted array using Binary Search.

    KEY OBSERVATION:
    • At least one half of the array (left or right) is always sorted
    • Determine the sorted half and check whether the target lies within it

    LOGIC:
    1. Find mid element
    2. If mid == target → return index
    3. Identify which half is sorted
    4. Narrow search space accordingly

    Time Complexity  : O(log n)
    Space Complexity : O(1)
    =====================================================================================
    */
    static int search(int[] arr, int target) {

        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {

            // Calculate mid safely
            int mid = left + (right - left) / 2;

            // Target found
            if (arr[mid] == target) {
                return mid;
            }

            // Check if left half is sorted
            if (arr[left] <= arr[mid]) {

                // Target lies within sorted left half
                if (target >= arr[left] && target < arr[mid]) {
                    right = mid - 1;
                }
                else {
                    left = mid + 1;
                }
            }
            else {
                // Right half is sorted

                // Target lies within sorted right half
                if (target > arr[mid] && target <= arr[right]) {
                    left = mid + 1;
                }
                else {
                    right = mid - 1;
                }
            }
        }

        // Target not found
        return -1;
    }
}
