package binarry_search.BS_on_1D;

public class FloorAndCeil {

    /*
    =====================================================================================
    PROBLEM: FIND FLOOR AND CEIL OF A TARGET IN A SORTED ARRAY
    -------------------------------------------------------------------------------------
    Given a sorted array and a target value, find:

    • FLOOR  → Greatest element less than or equal to the target
    • CEIL   → Smallest element greater than or equal to the target

    If such an element does not exist, return -1.

    Example:
    Array  = [1, 3, 5, 7, 9, 11, 13]
    Target = 6
    Floor  = 5
    Ceil   = 7

    This problem is commonly solved using Binary Search due to the
    sorted nature of the array.
    =====================================================================================
    */

    public static void main(String[] args) {

        int[] arr = {1, 3, 5, 7, 9, 11, 13};

        System.out.println("Array: " + java.util.Arrays.toString(arr));

        System.out.println("Floor of 6: " + floor(arr, 6));
        System.out.println("Ceil of 6: " + ceil(arr, 6));

        System.out.println("Floor of 1: " + floor(arr, 1));
        System.out.println("Ceil of 1: " + ceil(arr, 1));

        System.out.println("Floor of 14: " + floor(arr, 14));
        System.out.println("Ceil of 14: " + ceil(arr, 14));
    }

    /*
    =====================================================================================
    FUNCTION: floor
    -------------------------------------------------------------------------------------
    Finds the FLOOR of the target element.

    DEFINITION:
    Floor is the greatest element in the array that is less than or equal to the target.

    LOGIC:
    • Perform binary search
    • If arr[mid] ≤ target:
        - store it as a possible floor
        - move to the RIGHT to find a larger valid value
    • If arr[mid] > target:
        - move to the LEFT

    Time Complexity  : O(log n)
    Space Complexity : O(1)
    =====================================================================================
    */
    static int floor(int[] arr, int target) {

        int left = 0;
        int right = arr.length - 1;
        int result = -1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (arr[mid] <= target) {
                result = arr[mid];   // possible floor value
                left = mid + 1;      // try to find a larger valid floor
            }
            else {
                right = mid - 1;
            }
        }

        return result;
    }

    /*
    =====================================================================================
    FUNCTION: ceil
    -------------------------------------------------------------------------------------
    Finds the CEIL of the target element.

    DEFINITION:
    Ceil is the smallest element in the array that is greater than or equal to the target.

    LOGIC:
    • Perform binary search
    • If arr[mid] ≥ target:
        - store it as a possible ceil
        - move to the LEFT to find a smaller valid value
    • If arr[mid] < target:
        - move to the RIGHT

    Time Complexity  : O(log n)
    Space Complexity : O(1)
    =====================================================================================
    */
    static int ceil(int[] arr, int target) {

        int left = 0;
        int right = arr.length - 1;
        int result = -1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (arr[mid] >= target) {
                result = arr[mid];   // possible ceil value
                right = mid - 1;     // try to find a smaller valid ceil
            }
            else {
                left = mid + 1;
            }
        }

        return result;
    }
}
