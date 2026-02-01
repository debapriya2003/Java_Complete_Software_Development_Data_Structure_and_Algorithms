package binarry_search.BS_on_1D;

/*
=====================================================================================
COUNT NUMBER OF TIMES A SORTED ARRAY HAS BEEN ROTATED
-------------------------------------------------------------------------------------
A sorted array rotated k times will have its MINIMUM element at index k.

Key Observation:
• In a rotated sorted array, the minimum element is the "pivot"
• Number of rotations = index of the minimum element
• Binary Search can be used to find this pivot in O(log n)

Example:
[3, 4, 5, 1, 2]
Minimum = 1 at index 3 → rotated 3 times
=====================================================================================
*/

public class CountArrayRotations {

    /*
    =====================================================================================
    FUNCTION: countRotations
    -------------------------------------------------------------------------------------
    Finds how many times a sorted array has been rotated.

    LOGIC:
    • If array is already sorted → rotations = 0
    • Use binary search to locate the minimum element
    • Compare mid element with right boundary to decide search space

    Time Complexity  : O(log n)
    Space Complexity : O(1)
    =====================================================================================
    */
    public static int countRotations(int[] arr) {

        int left = 0;
        int right = arr.length - 1;

        while (left < right) {

            // Case 1: Subarray is already sorted
            // The smallest element is at index 'left'
            if (arr[left] < arr[right]) {
                return left;
            }

            int mid = left + (right - left) / 2;

            // Case 2: Minimum lies in the right half
            if (arr[mid] > arr[right]) {
                left = mid + 1;
            }
            // Case 3: Minimum lies in the left half (including mid)
            else {
                right = mid;
            }
        }

        // At loop end, left == right → index of minimum element
        return left;
    }

    /*
    =====================================================================================
    MAIN METHOD: DRIVER CODE
    -------------------------------------------------------------------------------------
    Demonstrates different rotation scenarios.
    =====================================================================================
    */
    public static void main(String[] args) {

        int[] arr1 = {3, 4, 5, 1, 2};
        System.out.println("Array: " + java.util.Arrays.toString(arr1));
        System.out.println("Rotations: " + countRotations(arr1)); // 3

        int[] arr2 = {1, 2, 3, 4, 5};
        System.out.println("Array: " + java.util.Arrays.toString(arr2));
        System.out.println("Rotations: " + countRotations(arr2)); // 0

        int[] arr3 = {5, 1, 2, 3, 4};
        System.out.println("Array: " + java.util.Arrays.toString(arr3));
        System.out.println("Rotations: " + countRotations(arr3)); // 1

        int[] arr4 = {2, 3, 4, 5, 1};
        System.out.println("Array: " + java.util.Arrays.toString(arr4));
        System.out.println("Rotations: " + countRotations(arr4)); // 4
    }
}
