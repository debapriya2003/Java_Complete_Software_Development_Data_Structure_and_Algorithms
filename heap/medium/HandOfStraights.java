package heap.medium;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*
=====================================================================================
HAND OF STRAIGHTS
-------------------------------------------------------------------------------------
Problem:
You are given an array of integers representing cards in a hand and an integer
groupSize.

You need to determine whether it is possible to divide the cards into groups of
size groupSize such that:
• Each group consists of consecutive numbers
• Every card is used exactly once

Example:
hand = [1,2,3,6,2,3,4,7,8]
groupSize = 3

Valid grouping:
[1,2,3], [2,3,4], [6,7,8]
→ Output: true
=====================================================================================
*/

public class HandOfStraights {

    /*
    =====================================================================================
    FUNCTION: isNStraightHand
    -------------------------------------------------------------------------------------
    Determines whether the hand can be rearranged into valid consecutive groups.

    CORE IDEA (GREEDY):
    • Always start forming groups from the SMALLEST available card
    • Try to build a sequence of length = groupSize
    • If any required card is missing → return false

    DATA STRUCTURES USED:
    • HashMap → to store frequency of each card
    • Min Heap → to always access the smallest available card

    Time Complexity  : O(n log n)
    Space Complexity : O(n)
    =====================================================================================
    */
    public static boolean isNStraightHand(int[] hand, int groupSize) {

        // If total cards cannot be divided into equal groups
        if (hand.length % groupSize != 0) {
            return false;
        }

        // Step 1: Count frequency of each card
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int card : hand) {
            freqMap.put(card, freqMap.getOrDefault(card, 0) + 1);
        }

        // Step 2: Min Heap to get smallest card
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(freqMap.keySet());

        // Step 3: Form groups greedily
        while (!minHeap.isEmpty()) {

            int start = minHeap.peek(); // smallest available card

            // Try to build a group of size groupSize
            for (int card = start; card < start + groupSize; card++) {

                if (!freqMap.containsKey(card)) {
                    return false; // missing consecutive card
                }

                // Decrease frequency
                freqMap.put(card, freqMap.get(card) - 1);

                // Remove card from heap if exhausted
                if (freqMap.get(card) == 0) {
                    freqMap.remove(card);

                    // Important check: order must match heap top
                    if (card != minHeap.peek()) {
                        return false;
                    }
                    minHeap.poll();
                }
            }
        }

        return true;
    }

    /*
    =====================================================================================
    MAIN METHOD: DRIVER CODE
    -------------------------------------------------------------------------------------
    Demonstrates Hand of Straights validation.
    =====================================================================================
    */
    public static void main(String[] args) {

        int[] hand = {1, 2, 3, 6, 2, 3, 4, 7, 8};
        int groupSize = 3;

        boolean result = isNStraightHand(hand, groupSize);

        System.out.println("Hand: [1,2,3,6,2,3,4,7,8]");
        System.out.println("Group Size: " + groupSize);
        System.out.println("Can be rearranged into straight hands? " + result);
    }
}
