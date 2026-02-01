package heap.medium;
import java.util.PriorityQueue;

/*
=====================================================================================
SORT A K-SORTED ARRAY (USING PRIORITY QUEUE)
-------------------------------------------------------------------------------------
Problem:
An array is called K-sorted if every element is at most K positions away from its
correct position in the sorted order.

In other words:
• Each element may be displaced by at most K indices from where it should be
  in the fully sorted array.

Example:
Input:  [6, 5, 3, 2, 8, 10, 9]
K = 3
Output: [2, 3, 5, 6, 8, 9, 10]

Why Priority Queue?
• Sorting normally takes O(n log n)
• But since displacement is limited to K, we can do better
• Using a Min Heap of size (K + 1), we achieve O(n log k)
=====================================================================================
*/

public class SortKSortedArray {

    /*
    =====================================================================================
    FUNCTION: sortKSortedArray
    -------------------------------------------------------------------------------------
    This function sorts a K-sorted array using a Min Heap (Priority Queue).

    ALGORITHM:
    1. Create a Min Heap (PriorityQueue)
    2. Insert the first (K + 1) elements into the heap
    3. For each remaining element:
        • Extract the minimum from heap and place it in the array
        • Insert the next element into the heap
    4. After traversal, empty the heap and place remaining elements

    WHY THIS WORKS:
    • The smallest element among any K+1 window must belong to the current index
    • Heap efficiently finds this smallest element

    Time Complexity  : O(n log k)
    Space Complexity : O(k)
    =====================================================================================
    */
    public static void sortKSortedArray(int[] arr, int k) {

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        int index = 0;

        // Step 1: Insert first K+1 elements
        for (int i = 0; i <= k && i < arr.length; i++) {
            minHeap.add(arr[i]);
        }

        // Step 2: Process remaining elements
        for (int i = k + 1; i < arr.length; i++) {
            arr[index++] = minHeap.poll();
            minHeap.add(arr[i]);
        }

        // Step 3: Empty the heap
        while (!minHeap.isEmpty()) {
            arr[index++] = minHeap.poll();
        }
    }

    /*
    =====================================================================================
    MAIN METHOD: DRIVER CODE
    -------------------------------------------------------------------------------------
    Demonstrates sorting of a K-sorted array.
    =====================================================================================
    */
    public static void main(String[] args) {

        int[] arr = {6, 5, 3, 2, 8, 10, 9};
        int k = 3;

        System.out.println("Original array:");
        printArray(arr);

        sortKSortedArray(arr, k);

        System.out.println("\nSorted array:");
        printArray(arr);
    }

    /*
    =====================================================================================
    HELPER FUNCTION: printArray
    =====================================================================================
    */
    static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}

