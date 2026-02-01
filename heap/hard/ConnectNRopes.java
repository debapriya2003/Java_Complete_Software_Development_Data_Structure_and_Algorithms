package heap.hard;
import java.util.PriorityQueue;

/*
=====================================================================================
CONNECT N ROPES WITH MINIMUM COST
-------------------------------------------------------------------------------------
Problem:
You are given N ropes, each with a certain length.
The cost of connecting two ropes is equal to the sum of their lengths.

When two ropes are connected:
• They form a new rope whose length is the sum of both
• This new rope can be connected again

Goal:
Find the minimum total cost required to connect all ropes into a single rope.

Example:
Ropes = [4, 3, 2, 6]

Optimal strategy:
1. Connect 2 and 3 → cost = 5
2. Connect 4 and 5 → cost = 9
3. Connect 6 and 9 → cost = 15
Total cost = 5 + 9 + 15 = 29
=====================================================================================
*/

public class ConnectNRopes {

    /*
    =====================================================================================
    FUNCTION: minCost
    -------------------------------------------------------------------------------------
    Computes the minimum cost to connect all ropes.

    GREEDY STRATEGY:
    • Always connect the two SHORTEST ropes first
    • This minimizes the incremental cost at every step

    DATA STRUCTURE USED:
    • Min Heap (Priority Queue)

    ALGORITHM:
    1. Insert all rope lengths into a Min Heap
    2. While heap size > 1:
        - Remove two smallest ropes
        - Add their sum to total cost
        - Insert the combined rope back into heap
    3. Return total cost

    Time Complexity  : O(n log n)
    Space Complexity : O(n)
    =====================================================================================
    */
    public static int minCost(int[] ropes) {

        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Step 1: Add all ropes to min heap
        for (int rope : ropes) {
            minHeap.add(rope);
        }

        int totalCost = 0;

        // Step 2: Combine ropes until one remains
        while (minHeap.size() > 1) {

            int first = minHeap.poll();
            int second = minHeap.poll();

            int cost = first + second;
            totalCost += cost;

            // Add the combined rope back
            minHeap.add(cost);
        }

        return totalCost;
    }

    /*
    =====================================================================================
    MAIN METHOD: DRIVER CODE
    -------------------------------------------------------------------------------------
    Demonstrates minimum cost rope connection.
    =====================================================================================
    */
    public static void main(String[] args) {

        int[] ropes = {4, 3, 2, 6};

        int result = minCost(ropes);

        System.out.println("Rope lengths: [4, 3, 2, 6]");
        System.out.println("Minimum cost to connect all ropes = " + result);
    }
}

