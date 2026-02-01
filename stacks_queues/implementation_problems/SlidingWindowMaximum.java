package stacks_queues.implementation_problems;

import java.util.*;

/**
 * Sliding Window Maximum
 * 
 * Problem: Given array and window size k, find maximum in each sliding window.
 * 
 * Example: nums = [1, 3, -1, -3, 5, 3, 6, 7], k = 3
 * 
 * Windows:
 * [1, 3, -1] → 3
 * [3, -1, -3] → 3
 * [-1, -3, 5] → 5
 * [-3, 5, 3] → 5
 * [5, 3, 6] → 6
 * [3, 6, 7] → 7
 * 
 * Output: [3, 3, 5, 5, 6, 7]
 * 
 * Algorithm: Deque (Monotonic Queue)
 * ====================================
 * 1. Maintain deque of indices in decreasing order of values
 * 2. For each element:
 *    - Remove indices outside current window
 *    - Remove indices from back if smaller than current
 *    - Add current index to back
 *    - Front of deque is max of current window
 * 3. Start adding to result from window size k
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(k) for deque
 */

public class SlidingWindowMaximum {
    
    /**
     * Find maximum in each sliding window using deque
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        
        int n = nums.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> deque = new LinkedList<>();
        
        for (int i = 0; i < n; i++) {
            // Remove indices outside current window
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }
            
            // Remove smaller elements from back
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            
            // Add current index
            deque.offerLast(i);
            
            // Add to result when window is formed
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }
        
        return result;
    }
    
    /**
     * Alternative: Using priority queue (min-heap) - O(n log n)
     */
    public static int[] maxSlidingWindowHeap(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        
        int n = nums.length;
        int[] result = new int[n - k + 1];
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> nums[b] - nums[a]);
        
        for (int i = 0; i < n; i++) {
            maxHeap.offer(i);
            
            // Remove indices outside window
            while (!maxHeap.isEmpty() && maxHeap.peek() <= i - k) {
                maxHeap.poll();
            }
            
            // Add result when window is formed
            if (i >= k - 1) {
                result[i - k + 1] = nums[maxHeap.peek()];
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        System.out.println("=== Sliding Window Maximum ===\n");
        
        int[][] testCases = {
            {1, 3, -1, -3, 5, 3, 6, 7}
        };
        int[] kValues = {3};
        
        for (int t = 0; t < testCases.length; t++) {
            int[] nums = testCases[t];
            int k = kValues[t];
            
            int[] result = maxSlidingWindow(nums, k);
            
            System.out.println("Array: " + Arrays.toString(nums));
            System.out.println("Window size (k): " + k);
            System.out.println("Result: " + Arrays.toString(result));
            System.out.println();
        }
        
        System.out.println("=".repeat(70));
        System.out.println("DETAILED EXAMPLE: [1, 3, -1, -3, 5, 3, 6, 7], k=3");
        System.out.println("-".repeat(70));
        
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        
        System.out.println("\nVisualizing sliding windows:\n");
        System.out.println(String.format("%-20s | %-15s | %-10s", "Window", "Elements", "Max"));
        System.out.println("-".repeat(50));
        
        for (int i = 0; i <= nums.length - k; i++) {
            int max = Integer.MIN_VALUE;
            StringBuilder window = new StringBuilder("[");
            
            for (int j = i; j < i + k; j++) {
                if (j > i) window.append(", ");
                window.append(nums[j]);
                max = Math.max(max, nums[j]);
            }
            window.append("]");
            
            System.out.println(String.format("%-20s | %-15s | %-10d", 
                "Indices " + i + "-" + (i + k - 1), window.toString(), max));
        }
        
        System.out.println("\n\n" + "=".repeat(70));
        System.out.println("DEQUE PROCESSING TRACE:");
        System.out.println("-".repeat(70));
        
        nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        k = 3;
        
        Deque<Integer> deque = new LinkedList<>();
        int[] result = new int[nums.length - k + 1];
        int resultIdx = 0;
        
        System.out.println("\nStep-by-step processing:\n");
        
        for (int i = 0; i < nums.length; i++) {
            System.out.println("Step " + (i + 1) + ": Processing index " + i + ", value " + nums[i]);
            System.out.println("  Deque before: " + deque);
            
            // Remove indices outside window
            int removedCount = 0;
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                int removed = deque.pollFirst();
                System.out.println("    Remove " + removed + " (outside window [" + (i - k + 1) + ", " + i + "])");
                removedCount++;
            }
            if (removedCount == 0) {
                System.out.println("    No indices outside window");
            }
            
            // Remove smaller elements from back
            int poppedCount = 0;
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                int popped = deque.pollLast();
                System.out.println("    Pop " + popped + " (value " + nums[popped] + " < " + nums[i] + ")");
                poppedCount++;
            }
            if (poppedCount == 0) {
                System.out.println("    No smaller elements at back");
            }
            
            // Add current index
            deque.offerLast(i);
            System.out.println("  Push " + i);
            System.out.println("  Deque after: " + deque);
            
            // Add result
            if (i >= k - 1) {
                int max = nums[deque.peekFirst()];
                result[resultIdx++] = max;
                System.out.println("  Window complete! Max = " + max);
            }
            
            System.out.println();
        }
        
        System.out.println("Final Result: " + Arrays.toString(result));
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("MONOTONIC DEQUE PROPERTIES:");
        System.out.println("-".repeat(70));
        System.out.println("1. Stores indices (not values)");
        System.out.println("2. Maintains DECREASING order of values");
        System.out.println("3. Front index always points to maximum");
        System.out.println("4. Remove front if index outside window");
        System.out.println("5. Remove back if value < current (no future use)");
        System.out.println("6. Add current index to back");
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("MORE TEST CASES:");
        System.out.println("-".repeat(70));
        
        int[][] moreCases = {
            {1},
            {1, -1},
            {9, 11},
            {3, 3, 3, 3}
        };
        int[] moreK = {1, 1, 2, 2};
        
        for (int t = 0; t < moreCases.length; t++) {
            int[] arr = moreCases[t];
            int kVal = moreK[t];
            int[] res = maxSlidingWindow(arr, kVal);
            System.out.println("Array: " + Arrays.toString(arr) + ", k=" + kVal);
            System.out.println("Result: " + Arrays.toString(res) + "\n");
        }
        
        System.out.println("=".repeat(70));
        System.out.println("TIME COMPLEXITY COMPARISON:");
        System.out.println("-".repeat(70));
        System.out.println("Deque (Optimal):        O(n) - each index added/removed once");
        System.out.println("Priority Queue:         O(n log n) - log n per operation");
        System.out.println("Brute Force:            O(n × k) - recalculate max each window");
        System.out.println("Segment Tree:           O(n + m log n) - with preprocessing");
    }
}
