package arrays.medium;

public class SortArrayOfZerosOnesAndTwos {

    /*
    =====================================================================================
    PROBLEM: SORT AN ARRAY OF 0's, 1's AND 2's
    -------------------------------------------------------------------------------------
    Given an array consisting only of 0s, 1s, and 2s, the task is to sort the array
    without using any sorting algorithm like Arrays.sort().

    This problem is also known as the Dutch National Flag problem. The goal is to arrange
    all 0s first, followed by all 1s, and then all 2s, using a single traversal of the
    array and constant extra space.
    =====================================================================================
    */

    public static void main(String[] args) {

        int[] arr = {2, 0, 2, 1, 1, 0};

        sortColors(arr);

        System.out.print("Sorted array: ");
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }

    /*
    =====================================================================================
    FUNCTION: sortColors
    -------------------------------------------------------------------------------------
    This function sorts the array using the Dutch National Flag Algorithm.

    LOGIC:
    • Maintain three pointers:
        low   → boundary for 0s
        mid   → current element
        high  → boundary for 2s

    RULES:
    • If arr[mid] == 0 → swap with arr[low], increment low & mid
    • If arr[mid] == 1 → move mid forward
    • If arr[mid] == 2 → swap with arr[high], decrement high

    This ensures all elements are sorted in one pass.

    Time Complexity  : O(n)
    Space Complexity : O(1)
    =====================================================================================
    */
    static void sortColors(int[] arr) {

        int low = 0;
        int mid = 0;
        int high = arr.length - 1;

        while (mid <= high) {

            if (arr[mid] == 0) {
                swap(arr, low, mid);
                low++;
                mid++;
            } 
            else if (arr[mid] == 1) {
                mid++;
            } 
            else { // arr[mid] == 2
                swap(arr, mid, high);
                high--;
            }
        }
    }

    /*
    =====================================================================================
    HELPER FUNCTION: swap
    -------------------------------------------------------------------------------------
    Swaps two elements in the array.
    =====================================================================================
    */
    static void swap(int[] arr, int i, int j) {

        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
