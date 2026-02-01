package binarry_search.BS_on_1D;

public class SingleElement {

    /*
    =====================================================================================
    PROBLEM: FIND THE SINGLE NON-DUPLICATE ELEMENT IN A SORTED ARRAY
    -------------------------------------------------------------------------------------
    Given a sorted array where:
    • Every element appears exactly twice
    • Except for one element that appears only once

    The task is to find that single (unique) element.

    Example:
    Array  = [1, 1, 2, 3, 3, 4, 4, 8, 8]
    Output = 2

    This problem is efficiently solved using Binary Search by exploiting
    index parity (even–odd positioning of pairs).
    =====================================================================================
    */

    public static void main(String[] args) {

        int[] arr1 = {1, 1, 2, 3, 3, 4, 4, 8, 8};
        System.out.println("Array: " + java.util.Arrays.toString(arr1));
        System.out.println("Single element: " + singleNonDuplicate(arr1));

        int[] arr2 = {3, 3, 7, 7, 10, 11, 11};
        System.out.println("Array: " + java.util.Arrays.toString(arr2));
        System.out.println("Single element: " + singleNonDuplicate(arr2));

        int[] arr3 = {1};
        System.out.println("Array: " + java.util.Arrays.toString(arr3));
        System.out.println("Single element: " + singleNonDuplicate(arr3));
    }

    /*
    =====================================================================================
    FUNCTION: singleNonDuplicate
    -------------------------------------------------------------------------------------
    Finds the element that appears only once in a sorted array where all
    other elements appear exactly twice.

    KEY OBSERVATION:
    • Before the single element:
        - Pairs start at EVEN indices (0, 2, 4, ...)
    • After the single element:
        - Pairs start at ODD indices (1, 3, 5, ...)

    LOGIC:
    • Always make mid index EVEN
    • If arr[mid] == arr[mid + 1]:
        - The single element lies in the RIGHT half
    • Otherwise:
        - The single element lies in the LEFT half

    Time Complexity  : O(log n)
    Space Complexity : O(1)
    =====================================================================================
    */
    static int singleNonDuplicate(int[] arr) {

        int left = 0;
        int right = arr.length - 1;

        while (left < right) {

            // Calculate mid safely
            int mid = left + (right - left) / 2;

            // Ensure mid is even for pair comparison
            if (mid % 2 == 1) {
                mid--;
            }

            // Check if the pair at mid is valid
            if (arr[mid] == arr[mid + 1]) {
                // Pair is intact → single element is on the right side
                left = mid + 2;
            }
            else {
                // Pair is broken → single element is on the left side
                right = mid;
            }
        }

        // left points to the single non-duplicate element
        return arr[left];
    }
}
