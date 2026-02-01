package heap.hard;

import java.util.PriorityQueue;

/*
=====================================================================================
FIND MEDIAN FROM DATA STREAM
-------------------------------------------------------------------------------------
Problem:
You are given a stream of integers arriving one by one.
After each insertion, you must be able to return the median of all elements seen so far.

Median Definition:
• If number of elements is odd → middle element
• If number of elements is even → average of two middle elements

Constraints:
• Stream is dynamic (unknown size)
• Efficient insertion and median retrieval required

Example:
Stream: 5, 15, 1, 3

Medians:
5       → 5
5,15    → (5+15)/2 = 10
1,5,15  → 5
1,3,5,15→ (3+5)/2 = 4
=====================================================================================
*/

public class FindMedianFromDataStream {

    /*
    =====================================================================================
    CLASS: MedianFinder
    -------------------------------------------------------------------------------------
    Maintains the running median using TWO HEAPS.

    DATA STRUCTURES:
    • Max Heap (leftHeap)  → stores the smaller half of numbers
    • Min Heap (rightHeap) → stores the larger half of numbers

    INVARIANTS:
    • Size difference between heaps is at most 1
    • MaxHeap may have one extra element (when count is odd)
    =====================================================================================
    */
    static class MedianFinder {

        private PriorityQueue<Integer> leftHeap;   // Max Heap
        private PriorityQueue<Integer> rightHeap;  // Min Heap

        /*
        ---------------------------------------------------------------------------------
        CONSTRUCTOR
        Initializes both heaps.
        ---------------------------------------------------------------------------------
        */
        public MedianFinder() {
            leftHeap = new PriorityQueue<>((a, b) -> b - a); // Max Heap
            rightHeap = new PriorityQueue<>();               // Min Heap
        }

        /*
        ---------------------------------------------------------------------------------
        FUNCTION: addNum
        ---------------------------------------------------------------------------------
        Adds a number from the stream into the data structure.

        ALGORITHM:
        1. Insert number into Max Heap
        2. Move largest from Max Heap to Min Heap
        3. Balance sizes so Max Heap has at most one extra element

        Time Complexity  : O(log n)
        Space Complexity : O(n)
        ---------------------------------------------------------------------------------
        */
        public void addNum(int num) {

            // Step 1: Add to max heap
            leftHeap.add(num);

            // Step 2: Balance ordering
            rightHeap.add(leftHeap.poll());

            // Step 3: Balance sizes
            if (rightHeap.size() > leftHeap.size()) {
                leftHeap.add(rightHeap.poll());
            }
        }

        /*
        ---------------------------------------------------------------------------------
        FUNCTION: findMedian
        ---------------------------------------------------------------------------------
        Returns the median of all elements seen so far.

        Time Complexity: O(1)
        ---------------------------------------------------------------------------------
        */
        public double findMedian() {

            // Odd number of elements
            if (leftHeap.size() > rightHeap.size()) {
                return leftHeap.peek();
            }

            // Even number of elements
            return (leftHeap.peek() + rightHeap.peek()) / 2.0;
        }
    }

    /*
    =====================================================================================
    MAIN METHOD: DRIVER CODE
    -------------------------------------------------------------------------------------
    Demonstrates median calculation from a data stream.
    =====================================================================================
    */
    public static void main(String[] args) {

        MedianFinder medianFinder = new MedianFinder();

        int[] stream = {5, 15, 1, 3};

        System.out.println("Running medians:\n");

        for (int num : stream) {
            medianFinder.addNum(num);
            System.out.println("Inserted: " + num +
                    " → Median = " + medianFinder.findMedian());
        }
    }
}
