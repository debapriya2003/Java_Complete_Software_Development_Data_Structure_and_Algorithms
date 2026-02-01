package arrays.easy;
public class LeftRotateByDPlaces {

    /*
    =====================================================================================
    PROBLEM: LEFT ROTATE AN ARRAY BY D PLACES
    -------------------------------------------------------------------------------------
    Given an array of size N and an integer D, the task is to rotate the array to the left
    by D positions. Left rotation means that elements are shifted towards the left, and
    the elements that move out from the beginning are placed at the end of the array.

    This problem is important for understanding array manipulation and rotation logic.
    The optimal solution uses the reversal algorithm to achieve rotation efficiently.
    =====================================================================================
    */

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        int d = 2;

        leftRotate(arr, d);

        System.out.print("Array after left rotation by " + d + " places: ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }

    /*
    =====================================================================================
    FUNCTION: leftRotate
    -------------------------------------------------------------------------------------
    This function left rotates the array by D places using the Reversal Algorithm.

    Steps:
    • Reverse the first D elements
    • Reverse the remaining N-D elements
    • Reverse the entire array

    This method works because reversing segments rearranges the elements into their
    correct rotated positions.

    Time Complexity  : O(n)
    Space Complexity : O(1)
    =====================================================================================
    */
    static void leftRotate(int[] arr, int d) {

        int n = arr.length;
        d = d % n;  // Handle cases where d > n

        reverse(arr, 0, d - 1);
        reverse(arr, d, n - 1);
        reverse(arr, 0, n - 1);
    }

    /*
    =====================================================================================
    FUNCTION: reverse
    -------------------------------------------------------------------------------------
    This helper function reverses elements of the array between indices start and end.

    Logic:
    • Swap elements from both ends
    • Move inward until start crosses end

    This is a standard array reversal technique used in many array-based problems.
    =====================================================================================
    */
    static void reverse(int[] arr, int start, int end) {

        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;

            start++;
            end--;
        }
    }
}
