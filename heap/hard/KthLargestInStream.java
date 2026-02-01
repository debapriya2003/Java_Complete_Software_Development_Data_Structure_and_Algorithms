package heap.hard;
import java.util.PriorityQueue;

/*
=====================================================================================
KTH LARGEST ELEMENT IN A STREAM OF RUNNING INTEGERS
-------------------------------------------------------------------------------------
Problem:
You are given a stream of integers coming one by one.
After each insertion, you must be able to return the Kth largest element seen so far.

Important Points:
• The stream is dynamic (numbers arrive continuously)
• You should NOT sort the entire stream every time
• Efficient real-time processing is required

Example:
Stream: 4, 5, 8, 2
K = 3

After inserting:
4   → not enough elements
5   → not enough elements
8   → Kth largest = 4
2   → Kth largest = 4
=====================================================================================
*/

public class KthLargestInStream {

    /*
    =====================================================================================
    CLASS: KthLargest
    -------------------------------------------------------------------------------------
    Maintains the Kth largest element dynamically using a Min Heap.

    CORE IDEA:
    • Maintain a Min Heap of size K
    • Heap always stores the K largest elements seen so far
    • The root of the Min Heap is the Kth largest element
    =====================================================================================
    */
    static class KthLargest {

        private PriorityQueue<Integer> minHeap;
        private int k;

        /*
        ---------------------------------------------------------------------------------
        CONSTRUCTOR
        Initializes the heap with the given value of K.
        ---------------------------------------------------------------------------------
        */
        public KthLargest(int k) {
            this.k = k;
            minHeap = new PriorityQueue<>();
        }

        /*
        ---------------------------------------------------------------------------------
        FUNCTION: add
        Adds a new number from the stream and returns the current Kth largest element.

        ALGORITHM's:
        1. Add element to Min Heap
        2. If heap size exceeds K, remove the smallest element
        3. Heap root gives the Kth largest value

        Time Complexity  : O(log K) per insertion
        Space Complexity : O(K)
        ---------------------------------------------------------------------------------
        */
        public Integer add(int val) {

            minHeap.add(val);

            if (minHeap.size() > k) {
                minHeap.poll();
            }

            // If fewer than K elements exist, Kth largest is undefined
            if (minHeap.size() < k) {
                return null;
            }

            return minHeap.peek();
        }
    }

    /*
    =====================================================================================
    MAIN METHOD: DRIVER CODE
    -------------------------------------------------------------------------------------
    Demonstrates Kth largest element in a running integer stream.
    =====================================================================================
    */
    public static void main(String[] args) {

        int k = 3;
        KthLargest kthLargest = new KthLargest(k);

        int[] stream = {4, 5, 8, 2, 10, 9};

        System.out.println("Stream processing (K = " + k + "):\n");

        for (int num : stream) {
            Integer result = kthLargest.add(num);
            System.out.print("Inserted: " + num + " → ");

            if (result == null) {
                System.out.println("Kth largest not available yet");
            } else {
                System.out.println("Kth largest = " + result);
            }
        }
    }
}

