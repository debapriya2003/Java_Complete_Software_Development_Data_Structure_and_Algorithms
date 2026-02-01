package heap.medium;
import java.util.PriorityQueue;

/*
=====================================================================================
KTH SMALLEST ELEMENT IN AN ARRAY (USING PRIORITY QUEUE)
-------------------------------------------------------------------------------------
Problem:
Given an unsorted array of integers and an integer K, find the Kth smallest element
in the array.

Important Notes:
• The Kth smallest element is NOT the Kth distinct element
• Sorting the array would take O(n log n) time
• A more efficient solution uses a Priority Queue (Max Heap)

Key Idea:
• Maintain a Max Heap of size K
• The heap stores the K smallest elements seen so far
• The largest among these K elements (heap root) is the answer
=====================================================================================
*/

public class KthSmallestElement {

    /*
    =====================================================================================
    FUNCTION: findKthSmallest
    -------------------------------------------------------------------------------------
    This function finds the Kth smallest element using a Priority Queue.

    ALGORITHM:
    1. Create a Max Heap using PriorityQueue with reverse order
    2. Traverse the array
    3. Add each element to the heap
    4. If heap size exceeds K, remove the largest element
    5. After traversal, the root of the heap is the Kth smallest element

    WHY THIS WORKS:
    • Heap always keeps the smallest K elements
    • The largest among them is exactly the Kth smallest overall

    Time Complexity  : O(n log k)
    Space Complexity : O(k)
    =====================================================================================
    */
    public static int findKthSmallest(int[] arr, int k) {

        // Max Heap using reverse order
        PriorityQueue<Integer> maxHeap =
                new PriorityQueue<>((a, b) -> b - a);

        for (int num : arr) {

            maxHeap.add(num);

            // Keep heap size limited to K
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        // Root of max heap is the Kth smallest element
        return maxHeap.peek();
    }

    /*
    =====================================================================================
    MAIN METHOD: DRIVER CODE
    -------------------------------------------------------------------------------------
    Demonstrates the working of Kth smallest element logic.
    =====================================================================================
    */
    public static void main(String[] args) {

        int[] arr = {7, 10, 4, 3, 20, 15};
        int k = 3;

        int result = findKthSmallest(arr, k);

        System.out.println("Array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }

        System.out.println("\nK = " + k);
        System.out.println("Kth smallest element = " + result);
    }
}

