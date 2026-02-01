package arrays.easy;
public class RemoveDuplicatesFromSortedArray {

    /*
    =====================================================================================
    PROBLEM: REMOVE DUPLICATES FROM A SORTED ARRAY
    -------------------------------------------------------------------------------------
    Given a sorted array, the task is to remove duplicate elements such that each unique
    element appears only once. The relative order of the elements must be maintained, and
    the operation should be done in-place without using extra memory.

    Since the array is already sorted, all duplicate elements will appear next to each
    other. This property allows us to solve the problem efficiently using the two-pointer
    technique.
    =====================================================================================
    */

    public static void main(String[] args) {

        int[] arr = {1, 1, 2, 2, 2, 3, 4, 4, 5};

        int newLength = removeDuplicates(arr);

        System.out.print("Array after removing duplicates: ");
        for (int i = 0; i < newLength; i++) {
            System.out.print(arr[i] + " ");
        }

        System.out.println("\nNew length of array: " + newLength);
    }

    /*
    =====================================================================================
    FUNCTION: removeDuplicates
    -------------------------------------------------------------------------------------
    This function removes duplicate elements from a sorted array in-place.

    Logic:
    • Use two pointers: one slow pointer (i) and one fast pointer (j)
    • The slow pointer tracks the position of the last unique element
    • Traverse the array using the fast pointer
    • When a new unique element is found, move it next to the last unique element
    • Increment the slow pointer accordingly

    After traversal, elements from index 0 to (i) form the array without duplicates.

    Time Complexity  : O(n)
    Space Complexity : O(1)
    =====================================================================================
    */
    static int removeDuplicates(int[] arr) {

        if (arr.length == 0)
            return 0;

        int i = 0; // Slow pointer for unique elements

        for (int j = 1; j < arr.length; j++) {

            // If a new unique element is found
            if (arr[j] != arr[i]) {
                i++;
                arr[i] = arr[j];
            }
        }

        // Length of array without duplicates
        return i + 1;
    }
}
