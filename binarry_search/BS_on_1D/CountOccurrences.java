package binarry_search.BS_on_1D;

public class CountOccurrences {

    /*
    =====================================================================================
    PROBLEM: COUNT OCCURRENCES OF A NUMBER IN A SORTED ARRAY
    -------------------------------------------------------------------------------------
    Given a sorted array that may contain duplicate elements, determine how many times
    a given target element appears in the array.

    Since the array is sorted, all occurrences of the target element will appear
    consecutively.

    Example:
    Array  = [1, 1, 2, 2, 2, 3, 4, 5, 5]
    Target = 2
    Output = 3

    This problem demonstrates the use of modified Binary Search to achieve
    better efficiency compared to linear traversal.
    =====================================================================================
    */

    public static void main(String[] args) {

        int[] arr = {1, 1, 2, 2, 2, 3, 4, 5, 5, 5, 5};

        System.out.println("Array: " + java.util.Arrays.toString(arr));

        System.out.println("Count of 2: " + countOccurrences(arr, 2));
        System.out.println("Count of 5: " + countOccurrences(arr, 5));
        System.out.println("Count of 1: " + countOccurrences(arr, 1));
        System.out.println("Count of 6: " + countOccurrences(arr, 6));
    }

    /*
    =====================================================================================
    FUNCTION: countOccurrences
    -------------------------------------------------------------------------------------
    This function calculates the number of times a target element appears in a
    sorted array.

    LOGIC:
    • Find the index of the first occurrence of the target
    • Find the index of the last occurrence of the target
    • Number of occurrences = lastIndex - firstIndex + 1

    If the target does not exist in the array, return 0.

    Time Complexity  : O(log n)
    Space Complexity : O(1)
    =====================================================================================
    */
    static int countOccurrences(int[] arr, int target) {

        int first = findFirst(arr, target);

        // If first occurrence is not found, target does not exist
        if (first == -1) {
            return 0;
        }

        int last = findLast(arr, target);

        return last - first + 1;
    }

    /*
    =====================================================================================
    FUNCTION: findFirst
    -------------------------------------------------------------------------------------
    Finds the FIRST (leftmost) occurrence of the target element using binary search.

    LOGIC:
    • Perform binary search
    • If target is found:
        - Store index
        - Move search to the LEFT to find earlier occurrence

    Returns index of first occurrence or -1 if not found.
    =====================================================================================
    */
    static int findFirst(int[] arr, int target) {

        int left = 0;
        int right = arr.length - 1;
        int result = -1;

        while (left <= right) {

            // Calculate mid safely
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                result = mid;       // store possible answer
                right = mid - 1;    // move left to find first occurrence
            }
            else if (arr[mid] < target) {
                left = mid + 1;     // target lies in right half
            }
            else {
                right = mid - 1;    // target lies in left half
            }
        }

        return result;
    }

    /*
    =====================================================================================
    FUNCTION: findLast
    -------------------------------------------------------------------------------------
    Finds the LAST (rightmost) occurrence of the target element using binary search.

    LOGIC:
    • Perform binary search
    • If target is found:
        - Store index
        - Move search to the RIGHT to find later occurrence

    Returns index of last occurrence or -1 if not found.
    =====================================================================================
    */
    static int findLast(int[] arr, int target) {

        int left = 0;
        int right = arr.length - 1;
        int result = -1;

        while (left <= right) {

            // Calculate mid safely
            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                result = mid;      // store possible answer
                left = mid + 1;    // move right to find last occurrence
            }
            else if (arr[mid] < target) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }

        return result;
    }
}
