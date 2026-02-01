package binarry_search.BS_on_1D;

public class UpperBound {

    /*
    =====================================================================================
    PROBLEM: FIND UPPER BOUND IN A SORTED ARRAY
    -------------------------------------------------------------------------------------
    The UPPER BOUND of a target element is defined as the index of the
    first element in the sorted array that is strictly greater than
    the given target value.

    If all elements in the array are less than or equal to the target,
    the upper bound is equal to the length of the array.

    Example:
    Array  = [1, 2, 2, 3, 3, 3, 5, 7, 9]
    Target = 3
    Output = 6   (arr[6] = 5)

    Upper Bound is commonly used in frequency counting and range-based
    binary search problems.
    =====================================================================================
    */

    public static void main(String[] args) {

        int[] arr = {1, 2, 2, 3, 3, 3, 5, 7, 9};

        System.out.println("Array: " + java.util.Arrays.toString(arr));

        System.out.println("Upper bound of 3: " + upperBound(arr, 3));
        System.out.println("Upper bound of 2: " + upperBound(arr, 2));
        System.out.println("Upper bound of 1: " + upperBound(arr, 1));
        System.out.println("Upper bound of 4: " + upperBound(arr, 4));
        System.out.println("Upper bound of 9: " + upperBound(arr, 9));
    }

    /*
    =====================================================================================
    FUNCTION: upperBound
    -------------------------------------------------------------------------------------
    Finds the index of the first element that is strictly greater than
    the target value.

    LOGIC:
    • Use binary search over the sorted array
    • If arr[mid] ≤ target:
        - the upper bound must be on the RIGHT side
    • Otherwise:
        - mid could be the answer, move LEFT to find an earlier index

    RETURN VALUE:
    • Index of upper bound
    • If target is greater than or equal to all elements, returns arr.length

    Time Complexity  : O(log n)
    Space Complexity : O(1)
    =====================================================================================
    */
    static int upperBound(int[] arr, int target) {

        int left = 0;
        int right = arr.length;

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (arr[mid] <= target) {
                left = mid + 1;   // move right
            }
            else {
                right = mid;     // move left
            }
        }

        // left points to the upper bound index
        return left;
    }
}
