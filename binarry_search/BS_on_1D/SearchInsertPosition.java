package binarry_search.BS_on_1D;

public class SearchInsertPosition {

    /*
    =====================================================================================
    PROBLEM: SEARCH INSERT POSITION IN A SORTED ARRAY
    -------------------------------------------------------------------------------------
    Given a sorted array of distinct integers and a target value, determine the index
    where the target should be inserted so that the array remains sorted.

    RULE:
    • If the target exists in the array, return its index
    • If the target does not exist, return the position where it can be inserted

    Example:
    Array  = [1, 3, 5, 6]
    Target = 2
    Output = 1

    This problem is a direct application of the Lower Bound concept
    using Binary Search.
    =====================================================================================
    */

    public static void main(String[] args) {

        int[] arr = {1, 3, 5, 6};

        System.out.println("Array: " + java.util.Arrays.toString(arr));

        System.out.println("Insert position of 5: " + searchInsert(arr, 5));
        System.out.println("Insert position of 2: " + searchInsert(arr, 2));
        System.out.println("Insert position of 7: " + searchInsert(arr, 7));
        System.out.println("Insert position of 0: " + searchInsert(arr, 0));
    }

    /*
    =====================================================================================
    FUNCTION: searchInsert
    -------------------------------------------------------------------------------------
    Finds the index where the target value should be inserted.

    KEY IDEA:
    • The required position is the first index where arr[index] ≥ target
    • This is identical to finding the LOWER BOUND of the target

    LOGIC:
    • Perform binary search on the array
    • If arr[mid] < target:
        - move to the RIGHT
    • Otherwise:
        - mid may be the answer, move to the LEFT

    Time Complexity  : O(log n)
    Space Complexity : O(1)
    =====================================================================================
    */
    static int searchInsert(int[] arr, int target) {

        int left = 0;
        int right = arr.length;

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (arr[mid] < target) {
                left = mid + 1;
            }
            else {
                right = mid;
            }
        }

        // left gives the correct insert position
        return left;
    }
}
