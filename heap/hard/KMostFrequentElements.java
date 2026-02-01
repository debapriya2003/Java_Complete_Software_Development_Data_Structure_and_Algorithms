package heap.hard;
import java.util.*;

/*
=====================================================================================
K MOST FREQUENT ELEMENTS
-------------------------------------------------------------------------------------
Problem:
Given an integer array nums and an integer k, return the k elements that appear
most frequently in the array.

Important Notes:
• Order of output does not matter
• Elements may appear in any order
• Frequencies decide priority, not values

Example:
Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
=====================================================================================
*/

public class KMostFrequentElements {

    /*
    =====================================================================================
    FUNCTION: topKFrequent
    -------------------------------------------------------------------------------------
    Returns the k most frequent elements from the array.

    CORE IDEA:
    • Count frequency of each element using HashMap
    • Use a Min Heap of size K based on frequency
    • Heap root always stores the least frequent among top K
    • If heap size exceeds K, remove smallest frequency element

    WHY MIN HEAP?
    • We want to efficiently discard lower-frequency elements
    • Heap size never exceeds K → efficient

    Time Complexity  : O(n log k)
    Space Complexity : O(n)
    =====================================================================================
    */
    public static int[] topKFrequent(int[] nums, int k) {

        // Step 1: Count frequencies
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // Step 2: Min Heap based on frequency
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap =
                new PriorityQueue<>((a, b) -> a.getValue() - b.getValue());

        // Step 3: Keep only top K frequent elements
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            minHeap.add(entry);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // Step 4: Extract result
        int[] result = new int[k];
        int index = 0;
        while (!minHeap.isEmpty()) {
            result[index++] = minHeap.poll().getKey();
        }

        return result;
    }

    /*
    =====================================================================================
    MAIN METHOD: DRIVER CODE
    -------------------------------------------------------------------------------------
    Demonstrates K most frequent elements.
    =====================================================================================
    */
    public static void main(String[] args) {

        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;

        int[] result = topKFrequent(nums, k);

        System.out.println("Input array:");
        System.out.println(Arrays.toString(nums));

        System.out.println("K = " + k);
        System.out.println("K most frequent elements:");
        System.out.println(Arrays.toString(result));
    }
}

