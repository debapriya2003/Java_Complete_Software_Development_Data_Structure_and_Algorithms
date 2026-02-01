package heap.intro;
import java.util.PriorityQueue;

/*
=====================================================================================
INTRODUCTION TO PRIORITY QUEUES USING BINARY HEAPS
-------------------------------------------------------------------------------------
A Priority Queue is a special type of queue where each element has a priority.
Elements with higher priority are served before elements with lower priority.

In Java, PriorityQueue is implemented using a BINARY HEAP internally.

Key Properties:
• Insertion: O(log n)
• Deletion of highest/lowest priority: O(log n)
• Peek (min/max): O(1)

By default:
• Java PriorityQueue → MIN HEAP
=====================================================================================
*/

public class IntroductionToPriorityQueue {

    public static void main(String[] args) {

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // Insert elements
        pq.add(10);
        pq.add(5);
        pq.add(30);
        pq.add(2);

        System.out.println("Priority Queue (Min Heap behavior):");

        // Elements are removed based on priority (smallest first)
        while (!pq.isEmpty()) {
            System.out.print(pq.poll() + " ");
        }
    }
}

