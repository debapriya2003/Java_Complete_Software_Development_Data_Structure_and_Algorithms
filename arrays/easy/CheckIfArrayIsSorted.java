package arrays.easy;
public class CheckIfArrayIsSorted {

    /*
    =====================================================================================
    PROBLEM: CHECK IF AN ARRAY IS SORTED
    -------------------------------------------------------------------------------------
    An array is said to be sorted if its elements are arranged in a specific order.
    Usually, this order is ascending (non-decreasing). To check whether an array is
    sorted, we compare each element with its next element. If at any point the current
    element is greater than the next one, the array is not sorted.

    This problem helps in understanding array traversal, comparisons, and early
    termination of loops. It is commonly used as a prerequisite check in many sorting
    and searching algorithms.
    =====================================================================================
    */

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5};   // Example input array

        boolean isSorted = checkSorted(arr);

        if (isSorted)
            System.out.println("Array is sorted");
        else
            System.out.println("Array is NOT sorted");
    }

    /*
    =====================================================================================
    FUNCTION: checkSorted
    -------------------------------------------------------------------------------------
    This function checks whether the given array is sorted in ascending order.
    The logic is simple:
    • Traverse the array from index 0 to n-2
    • Compare arr[i] with arr[i+1]
    • If arr[i] > arr[i+1], the array is not sorted
    • If no such case is found, the array is sorted

    Time Complexity  : O(n)
    Space Complexity : O(1)
    =====================================================================================
    */
    static boolean checkSorted(int[] arr) {

        // Traverse the array
        for (int i = 0; i < arr.length - 1; i++) {

            // If current element is greater than next, array is not sorted
            if (arr[i] > arr[i + 1]) {
                return false;
            }
        }

        // If no violation found, array is sorted
        return true;
    }
}
