package heap.medium;
import java.util.PriorityQueue;

/*
=====================================================================================
MERGE M SORTED LISTS (USING PRIORITY QUEUE)
-------------------------------------------------------------------------------------
Problem:
Given M sorted lists (arrays or linked lists), merge them into a single sorted list.

Why Priority Queue?
• Each list is already sorted
• We always want the smallest current element among all lists
• A Min Heap allows us to extract the minimum efficiently

Key Idea:
• Insert the first element of each list into a Min Heap
• Repeatedly extract the smallest element
• Insert the next element from the same list into the heap
=====================================================================================
*/

public class MergeMSortedLists {

    /*
    =====================================================================================
    HELPER CLASS: Node
    -------------------------------------------------------------------------------------
    Represents an element in a list along with:
    • value      → actual data
    • listIndex  → which list it belongs to
    • elementIndex → index inside that list
    =====================================================================================
    */
    static class Node {
        int value;
        int listIndex;
        int elementIndex;

        Node(int value, int listIndex, int elementIndex) {
            this.value = value;
            this.listIndex = listIndex;
            this.elementIndex = elementIndex;
        }
    }

    /*
    =====================================================================================
    FUNCTION: mergeSortedLists
    -------------------------------------------------------------------------------------
    Merges M sorted arrays into one sorted array.

    ALGORITHM:
    1. Create a Min Heap based on node value
    2. Insert first element of each list into the heap
    3. While heap is not empty:
        • Extract minimum element
        • Add it to result
        • Insert next element from the same list (if exists)

    Time Complexity  : O(N log M)
    Space Complexity : O(M)

    Where:
    • N = total number of elements across all lists
    • M = number of lists
    =====================================================================================
    */
    public static int[] mergeSortedLists(int[][] lists) {

        PriorityQueue<Node> minHeap =
                new PriorityQueue<>((a, b) -> a.value - b.value);

        int totalSize = 0;

        // Insert first element of each list
        for (int i = 0; i < lists.length; i++) {
            if (lists[i].length > 0) {
                minHeap.add(new Node(lists[i][0], i, 0));
                totalSize += lists[i].length;
            }
        }

        int[] result = new int[totalSize];
        int index = 0;

        // Extract-min and insert next element
        while (!minHeap.isEmpty()) {

            Node current = minHeap.poll();
            result[index++] = current.value;

            int nextIndex = current.elementIndex + 1;
            if (nextIndex < lists[current.listIndex].length) {
                minHeap.add(new Node(
                        lists[current.listIndex][nextIndex],
                        current.listIndex,
                        nextIndex
                ));
            }
        }

        return result;
    }

    /*
    =====================================================================================
    MAIN METHOD: DRIVER CODE
    -------------------------------------------------------------------------------------
    Demonstrates merging of M sorted lists.
    =====================================================================================
    */
    public static void main(String[] args) {

        int[][] lists = {
                {1, 4, 5},
                {1, 3, 4},
                {2, 6}
        };

        int[] merged = mergeSortedLists(lists);

        System.out.println("Merged Sorted List:");
        for (int num : merged) {
            System.out.print(num + " ");
        }
    }
}
