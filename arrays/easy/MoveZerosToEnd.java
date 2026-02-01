package arrays.easy;
public class MoveZerosToEnd {

    /*
    =====================================================================================
    PROBLEM: MOVE ALL ZEROS TO THE END OF THE ARRAY
    -------------------------------------------------------------------------------------
    Given an array of integers, the task is to move all the zero elements to the end of the
    array while maintaining the relative order of the non-zero elements.

    This problem is commonly asked in coding interviews and tests array manipulation,
    two-pointer technique, and in-place operations. The challenge is to perform this
    operation efficiently without using extra space.
    =====================================================================================
    */

    public static void main(String[] args) {

        int[] arr = {0, 1, 0, 3, 12};

        moveZeros(arr);

        System.out.print("Array after moving zeros to end: ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }

    /*
    =====================================================================================
    FUNCTION: moveZeros
    -------------------------------------------------------------------------------------
    This function moves all zero elements to the end of the array using the two-pointer
    approach.

    Logic:
    • Use a pointer 'index' to track the position of the next non-zero element
    • Traverse the array
    • When a non-zero element is found, place it at arr[index] and increment index
    • After processing all elements, fill the remaining positions with zeros

    This approach preserves order and runs in linear time.

    Time Complexity  : O(n)
    Space Complexity : O(1)
    =====================================================================================
    */
    static void moveZeros(int[] arr) {

        int index = 0;

        // Move non-zero elements forward
        for (int num : arr) {
            if (num != 0) {
                arr[index] = num;
                index++;
            }
        }

        // Fill remaining positions with zeros
        while (index < arr.length) {
            arr[index] = 0;
            index++;
        }
    }
}
