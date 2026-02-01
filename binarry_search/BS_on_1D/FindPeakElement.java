package binarry_search.BS_on_1D;

public class FindPeakElement {

    /*
    =====================================================================================
    PROBLEM: FIND A PEAK ELEMENT IN AN ARRAY
    -------------------------------------------------------------------------------------
    A peak element is an element that is strictly greater than its neighboring elements.

    Definition:
    An element arr[i] is a peak if:
        arr[i] > arr[i - 1]  AND  arr[i] > arr[i + 1]

    IMPORTANT:
    • The first and last elements are also considered peaks
      because they have only one neighbor.

    Example:
    Array  = [1, 2, 3, 1]
    Peak   = 3
    Index  = 2

    This problem demonstrates the application of Binary Search on
    an unsorted array using directional comparison.
    =====================================================================================
    */

    public static void main(String[] args) {

        int[] arr1 = {1, 2, 3, 1};
        System.out.println("Array: " + java.util.Arrays.toString(arr1));
        System.out.println("Peak element index: " + findPeakElement(arr1));

        int[] arr2 = {1, 2, 1, 3, 5, 4, 6, 8, 7};
        System.out.println("Array: " + java.util.Arrays.toString(arr2));
        System.out.println("Peak element index: " + findPeakElement(arr2));

        int[] arr3 = {10, 5, 3};
        System.out.println("Array: " + java.util.Arrays.toString(arr3));
        System.out.println("Peak element index: " + findPeakElement(arr3));
    }

    /*
    =====================================================================================
    FUNCTION: findPeakElement
    -------------------------------------------------------------------------------------
    This function finds the index of any one peak element using Binary Search.

    KEY IDEA:
    • If arr[mid] > arr[mid + 1], a peak exists on the LEFT side
    • Otherwise, a peak exists on the RIGHT side
    • This guarantees a solution due to the problem constraints

    WHY IT WORKS:
    • Every array has at least one peak
    • Binary search reduces search space by half each time

    Time Complexity  : O(log n)
    Space Complexity : O(1)
    =====================================================================================
    */
    static int findPeakElement(int[] arr) {

        int left = 0;
        int right = arr.length - 1;

        // Binary search to locate a peak
        while (left < right) {

            // Calculate mid safely
            int mid = left + (right - left) / 2;

            // If mid is greater than right neighbor,
            // then a peak exists on the left side
            if (arr[mid] > arr[mid + 1]) {
                right = mid;
            }
            else {
                // Otherwise, peak exists on the right side
                left = mid + 1;
            }
        }

        // left == right points to a peak element
        return left;
    }

    /*
    =====================================================================================
    FUNCTION: findPeakElementAlternative
    -------------------------------------------------------------------------------------
    This is an alternative approach that explicitly checks neighbors.

    LOGIC:
    • First, check boundary elements
    • Then apply binary search on remaining elements
    • At each step:
        - If arr[mid] is greater than both neighbors → peak found
        - Move search direction based on slope

    This method is more intuitive but slightly more verbose.

    Time Complexity  : O(log n)
    Space Complexity : O(1)
    =====================================================================================
    */
    static int findPeakElementAlternative(int[] arr) {

        // Single element array is always a peak
        if (arr.length == 1 || arr[0] > arr[1]) {
            return 0;
        }

        // Check last element
        if (arr[arr.length - 1] > arr[arr.length - 2]) {
            return arr.length - 1;
        }

        int left = 1;
        int right = arr.length - 2;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            // Check if mid is a peak
            if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1]) {
                return mid;
            }
            // If slope is increasing, move right
            else if (arr[mid] < arr[mid + 1]) {
                left = mid + 1;
            }
            // Otherwise, move left
            else {
                right = mid - 1;
            }
        }

        return -1; // Should never happen due to problem guarantees
    }
}
