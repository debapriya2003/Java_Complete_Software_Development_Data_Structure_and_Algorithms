package arrays.easy;
public class LinearSearch {

    /*
    =====================================================================================
    PROBLEM: LINEAR SEARCH
    -------------------------------------------------------------------------------------
    Linear Search is the simplest searching algorithm. It works by sequentially checking
    each element of the array until the desired element is found or the entire array is
    traversed. This algorithm does not require the array to be sorted and can be applied
    to any list of elements.

    Although Linear Search is easy to implement and understand, it is inefficient for
    large datasets because it may need to compare the target with every element in the
    array.
    =====================================================================================
    */

    public static void main(String[] args) {

        int[] arr = {5, 3, 8, 4, 2};
        int target = 4;

        int index = linearSearch(arr, target);

        if (index != -1)
            System.out.println("Element found at index: " + index);
        else
            System.out.println("Element not found in the array");
    }

    /*
    =====================================================================================
    FUNCTION: linearSearch
    -------------------------------------------------------------------------------------
    This function performs a linear search on the given array.

    Logic:
    • Traverse the array from the first element to the last
    • Compare each element with the target value
    • If a match is found, return its index
    • If the loop ends without finding the element, return -1

    Time Complexity  : O(n)
    Space Complexity : O(1)
    =====================================================================================
    */
    static int linearSearch(int[] arr, int target) {

        for (int i = 0; i < arr.length; i++) {

            // Check if current element matches the target
            if (arr[i] == target) {
                return i;
            }
        }

        // Element not found
        return -1;
    }
}
