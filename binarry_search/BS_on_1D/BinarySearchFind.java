package binarry_search.BS_on_1D;

/*
=====================================================================================
BINARY SEARCH IN A SORTED ARRAY
-------------------------------------------------------------------------------------
Binary Search is an efficient searching algorithm that works only on SORTED arrays.
Instead of scanning elements one by one (like Linear Search), it repeatedly divides
the search space into half, thereby reducing the time complexity significantly.

Key Idea:
• Compare the target with the middle element
• If equal → target found
• If target is smaller → search left half
• If target is larger → search right half
=====================================================================================
*/

public class BinarySearchFind {

    /*
    =====================================================================================
    FUNCTION: binarySearch
    -------------------------------------------------------------------------------------
    This function checks whether a given target element exists in a sorted array.

    Returns:
    • true  → if target exists
    • false → if target does not exist

    Time Complexity  : O(log n)
    Space Complexity : O(1)

    WHY O(log n)?
    At each step, the search space is reduced to half.
    =====================================================================================
    */
    public static boolean binarySearch(int[] arr, int target) {

        int left = 0;
        int right = arr.length - 1;

        // Continue searching while the search space is valid
        while (left <= right) {

            // Prevents integer overflow
            int mid = left + (right - left) / 2;

            // Case 1: Target found
            if (arr[mid] == target) {
                return true;
            }
            // Case 2: Target is greater → search right half
            else if (arr[mid] < target) {
                left = mid + 1;
            }
            // Case 3: Target is smaller → search left half
            else {
                right = mid - 1;
            }
        }

        // Target not found
        return false;
    }

    /*
    =====================================================================================
    FUNCTION: binarySearchIndex
    -------------------------------------------------------------------------------------
    This function returns the INDEX of the target element in a sorted array.

    Returns:
    • index of target → if found
    • -1              → if not found

    This version is preferred in most interview problems.
    =====================================================================================
    */
    public static int binarySearchIndex(int[] arr, int target) {

        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                return mid;          // Target found
            }
            else if (arr[mid] < target) {
                left = mid + 1;      // Search right half
            }
            else {
                right = mid - 1;     // Search left half
            }
        }

        return -1; // Target not present
    }

    /*
    =====================================================================================
    MAIN METHOD: DRIVER CODE
    -------------------------------------------------------------------------------------
    Demonstrates usage of both binary search functions.
    =====================================================================================
    */
    public static void main(String[] args) {

        int[] arr = {1, 3, 5, 7, 9, 11, 13};

        System.out.println("Array: " + java.util.Arrays.toString(arr));

        System.out.println("Find 7: " + binarySearch(arr, 7));
        System.out.println("Find 7 (index): " + binarySearchIndex(arr, 7));

        System.out.println("Find 8: " + binarySearch(arr, 8));
        System.out.println("Find 8 (index): " + binarySearchIndex(arr, 8));
    }
}
