package arrays.easy;
public class LargestElementInArray {
    /*
    =====================================================================================
    PROBLEM: FIND THE LARGEST ELEMENT IN AN ARRAY
    -------------------------------------------------------------------------------------
    Given an array of integers, the task is to find the maximum (largest) element present
    in the array. This is one of the most fundamental array problems and is often used as
    a building block for more complex algorithms such as sorting, searching, and array
    optimizations.

    The problem is solved by traversing the array once and keeping track of the largest
    value found so far. No sorting or additional data structures are required.
    =====================================================================================
    */

    public static void main(String[] args) {

        int[] arr = {12, 45, 7, 89, 23};

        int largest = findLargest(arr);

        System.out.println("Largest element in the array: " + largest);
    }

    /*
    =====================================================================================
    FUNCTION: findLargest
    -------------------------------------------------------------------------------------
    This function finds the largest element in the array.

    Logic:
    • Assume the first element is the largest initially
    • Traverse the array from the second element onwards
    • Compare each element with the current largest value
    • Update the largest value whenever a bigger element is found
    • After traversal, the largest value is obtained

    Time Complexity  : O(n)
    Space Complexity : O(1)
    =====================================================================================
    */
    static int findLargest(int[] arr) {

        int max = arr[0];   // Assume first element is the largest

        for (int i = 1; i < arr.length; i++) {

            // Update max if a larger element is found
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        return max;
    }
}
