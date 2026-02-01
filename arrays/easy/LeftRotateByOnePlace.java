package arrays.easy;
public class LeftRotateByOnePlace {

    /*
    =====================================================================================
    PROBLEM: LEFT ROTATE AN ARRAY BY ONE PLACE
    -------------------------------------------------------------------------------------
    Left rotating an array by one place means shifting every element of the array one
    position to the left. The first element of the array moves to the last position.

    This problem helps in understanding basic array manipulation and is often used as
    a stepping stone to more complex rotation problems like rotation by D places.
    =====================================================================================
    */

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5};

        leftRotateByOne(arr);

        System.out.print("Array after left rotation by one place: ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }

    /*
    =====================================================================================
    FUNCTION: leftRotateByOne
    -------------------------------------------------------------------------------------
    This function performs a left rotation by one position.

    Logic:
    • Store the first element in a temporary variable
    • Shift all remaining elements one position to the left
    • Place the stored element at the end of the array

    Time Complexity  : O(n)
    Space Complexity : O(1)
    =====================================================================================
    */
    static void leftRotateByOne(int[] arr) {

        int first = arr[0];

        for (int i = 0; i < arr.length - 1; i++) {
            arr[i] = arr[i + 1];
        }

        arr[arr.length - 1] = first;
    }
}

