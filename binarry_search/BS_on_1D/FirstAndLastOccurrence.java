package binarry_search.BS_on_1D;

public class FirstAndLastOccurrence {

    /*
    =====================================================================================
    PROBLEM: FIND FIRST AND LAST OCCURRENCE OF A NUMBER IN A SORTED ARRAY
    -------------------------------------------------------------------------------------
    Given a sorted array of integers (may contain duplicates) and a target value,
    determine the index of:
        • the first (leftmost) occurrence
        • the last (rightmost) occurrence

    If the target does not exist in the array, return -1.

    Example:
    Array  = [5, 7, 7, 8, 8, 10]
    Target = 8
    Output = First = 3, Last = 4

    This problem is a classic application of Binary Search modification.
    =====================================================================================
    */

    public static void main(String[] args) {

        int[] arr = {5, 7, 7, 8, 8, 10};

        System.out.println("Array: " + java.util.Arrays.toString(arr));

        System.out.println("First occurrence of 8: " + findFirst(arr, 8));
        System.out.println("Last occurrence of 8: " + findLast(arr, 8));

        int[] result = findFirstAndLast(arr, 7);
        System.out.println("First and Last of 7: [" + result[0] + ", " + result[1] + "]");

        System.out.println(
            "First and Last of 6: " +
            java.util.Arrays.toString(findFirstAndLast(arr, 6))
        );
    }

    /*
    =====================================================================================
    FUNCTION: findFirst
    -------------------------------------------------------------------------------------
    Finds the FIRST (leftmost) occurrence of the target value using Binary Search.

    LOGIC:
    • Perform binary search
    • When target is found:
        - store the index
        - continue searching in the LEFT half
    • This guarantees the earliest position is found

    Time Complexity  : O(log n)
    Space Complexity : O(1)
    =====================================================================================
    */
    static int findFirst(int[] arr, int target) {

        int left = 0;
        int right = arr.length - 1;
        int result = -1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                result = mid;       // possible first occurrence
                right = mid - 1;    // search left side
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

    /*
    =====================================================================================
    FUNCTION: findLast
    -------------------------------------------------------------------------------------
    Finds the LAST (rightmost) occurrence of the target value using Binary Search.

    LOGIC:
    • Perform binary search
    • When target is found:
        - store the index
        - continue searching in the RIGHT half
    • This guarantees the latest position is found

    Time Complexity  : O(log n)
    Space Complexity : O(1)
    =====================================================================================
    */
    static int findLast(int[] arr, int target) {

        int left = 0;
        int right = arr.length - 1;
        int result = -1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (arr[mid] == target) {
                result = mid;       // possible last occurrence
                left = mid + 1;     // search right side
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

    /*
    =====================================================================================
    FUNCTION: findFirstAndLast
    -------------------------------------------------------------------------------------
    Finds both the first and last occurrence of the target value.

    LOGIC:
    • Call findFirst() and findLast()
    • Return the result as an integer array [first, last]
    • If target is not present, both values will be -1

    Time Complexity  : O(log n)
    Space Complexity : O(1)
    =====================================================================================
    */
    static int[] findFirstAndLast(int[] arr, int target) {

        int first = findFirst(arr, target);
        int last = findLast(arr, target);

        return new int[]{first, last};
    }
}
