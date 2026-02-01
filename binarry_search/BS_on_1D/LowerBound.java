package binarry_search.BS_on_1D;

public class LowerBound {

    /*
    =====================================================================================
    PROBLEM: FIND LOWER BOUND IN A SORTED ARRAY
    -------------------------------------------------------------------------------------
    The LOWER BOUND of a target element is defined as the index of the
    first element in the sorted array that is greater than or equal to
    the given target value.

    If all elements in the array are smaller than the target, the lower
    bound is equal to the length of the array.

    Example:
    Array  = [1, 2, 2, 3, 3, 3, 5, 7, 9]
    Target = 3
    Output = 3   (arr[3] = 3)

    Lower Bound is frequently used in searching problems involving
    ranges, frequency counts, and binary search optimizations.
    =====================================================================================
    */

    public static void main(String[] args) {

        int[] arr = {1, 2, 2, 3, 3, 3, 5, 7, 9};

        System.out.println("Array: " + java.util.Arrays.toString(arr));

        System.out.println("Lower bound of 3: " + lowerBound(arr, 3));
        System.out.println("Lower bound of 2: " + lowerBound(arr, 2));
        System.out.println("Lower bound of 1: " + lowerBound(arr, 1));
        System.out.println("Lower bound of 4: " + lowerBound(arr, 4));
        System.out.println("Lower bound of 10: " + lowerBound(arr, 10));
    }

    /*
    =====================================================================================
    FUNCTION: lowerBound
    -------------------------------------------------------------------------------------
    Finds the index of the first element that is greater than or equal
    to the target value.

    LOGIC:
    • Use binary search on the entire array
    • If arr[mid] < target:
        - the lower bound must be on the RIGHT side
    • Otherwise:
        - mid could be the answer, move LEFT to find earlier position

    RETURN VALUE:
    • Index of lower bound
    • If target is larger than all elements, returns arr.length

    Time Complexity  : O(log n)
    Space Complexity : O(1)
    =====================================================================================
    */
    static int lowerBound(int[] arr, int target) {

        int left = 0;
        int right = arr.length;

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (arr[mid] < target) {
                left = mid + 1;   // search right half
            }
            else {
                right = mid;     // search left half including mid
            }
        }

        // left points to the lower bound index
        return left;
    }
}
