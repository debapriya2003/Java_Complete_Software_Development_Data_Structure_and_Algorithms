package heap.medium;


import java.util.PriorityQueue;

/*
=====================================================================================
KTH LARGEST ELEMENT IN AN ARRAY (USING PRIORITY QUEUE)
-------------------------------------------------------------------------------------
Problem:
Given an unsorted array of integers and an integer K, find the Kth largest element
in the array.

Important Notes:
• The Kth largest element is NOT the Kth distinct element
• Sorting the array would take O(n log n) time
• A more efficient solution uses a Priority Queue (Min Heap)

Key Idea:
• Maintain a Min Heap of size K
• The heap stores the K largest elements seen so far
• The smallest among these K elements (heap root) is the answer
=====================================================================================
*/

public class KthLargestElement {

    /*
    =====================================================================================
    FUNCTION: findKthLargest
    -------------------------------------------------------------------------------------
    This function finds the Kth largest element using a Priority Queue.

    ALGORITHM:
    1. Create a Min Heap (PriorityQueue in Java)
    2. Traverse the array
    3. Add each element to the heap
    4. If heap size exceeds K, remove the smallest element
    5. After traversal, the root of the heap is the Kth largest element

    WHY THIS WORKS:
    • Heap always keeps the largest K elements
    • The smallest among them is exactly the Kth largest overall

    Time Complexity  : O(n log k)
    Space Complexity : O(k)
    =====================================================================================
    */
    public static int findKthLargest(int[] arr, int k) {

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int num : arr) {

            minHeap.add(num);

            // Keep heap size limited to K
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // Root of min heap is the Kth largest element
        return minHeap.peek();
    }

    /*
    =====================================================================================
    MAIN METHOD: DRIVER CODE
    -------------------------------------------------------------------------------------
    Demonstrates the working of Kth largest element logic.
    =====================================================================================
    */
    public static void main(String[] args) {

        int[] arr = {3, 2, 1, 5, 6, 4};
        int k = 2;

        int result = findKthLargest(arr, k);

        System.out.println("Array:");
        for (int num : arr) {
            System.out.print(num + " ");
        }

        System.out.println("\nK = " + k);
        System.out.println("Kth largest element = " + result);
    }
}

