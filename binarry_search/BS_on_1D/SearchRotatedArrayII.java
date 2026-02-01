package binarry_search.BS_on_1D;

public class SearchRotatedArrayII {

    /*
    =====================================================================================
    PROBLEM: SEARCH IN ROTATED SORTED ARRAY (WITH DUPLICATES)
    -------------------------------------------------------------------------------------
    A rotated sorted array is an array that was originally sorted in ascending order
    and then rotated at some unknown pivot.

    In this variation, the array MAY CONTAIN DUPLICATE ELEMENTS.

    The task is to determine whether a given target element exists in the array.

    Example:
    Array  = [1, 0, 1, 1, 1]
    Target = 0
    Output = true

    Due to the presence of duplicates, this problem is slightly more complex than
    the unique-elements version.
    =====================================================================================
    */

    public static void main(String[] args) {

        int[] arr1 = {1, 0, 1, 1, 1};
        System.out.println("Array: " + java.util.Arrays.toString(arr1));
        System.out.println("Search 0: " + search(arr1, 0));
        System.out.println("Search 2: " + search(arr1, 2));

        int[] arr2 = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1};
        System.out.println("Array: " + java.util.Arrays.toString(arr2));
        System.out.println("Search 2: " + search(arr2, 2));
    }

    /*
    =====================================================================================
    FUNCTION: search
    -------------------------------------------------------------------------------------
    Searches for a target element in a rotated sorted array that may contain duplicates.

    KEY CHALLENGE:
    • Duplicates can make it impossible to determine which half is sorted

    SOLUTION:
    • When arr[left] == arr[mid] == arr[right]:
        - Shrink the search space by incrementing left and decrementing right

    GENERAL LOGIC:
    1. Check if mid element is the target
    2. Handle duplicates explicitly
    3. Identify the sorted half
    4. Narrow search space accordingly

    Time Complexity:
    • Average Case : O(log n)
    • Worst Case   : O(n)

    Space Complexity: O(1)
    =====================================================================================
    */
    static boolean search(int[] arr, int target) {

        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {

            // Calculate mid safely
            int mid = left + (right - left) / 2;

            // Target found
            if (arr[mid] == target) {
                return true;
            }

            // Case: duplicates on both ends and mid
            // Cannot determine sorted half, shrink search space
            if (arr[left] == arr[mid] && arr[mid] == arr[right]) {
                left++;
                right--;
                continue;
            }

            // Check if left half is sorted
            if (arr[left] <= arr[mid]) {

                // Target lies in sorted left half
                if (target >= arr[left] && target < arr[mid]) {
                    right = mid - 1;
                }
                else {
                    left = mid + 1;
                }
            }
            else {
                // Right half is sorted

                // Target lies in sorted right half
                if (target > arr[mid] && target <= arr[right]) {
                    left = mid + 1;
                }
                else {
                    right = mid - 1;
                }
            }
        }

        // Target not found
        return false;
    }
}
