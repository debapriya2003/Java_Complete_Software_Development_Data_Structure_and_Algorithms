package stacks_queues.monotonic_stack;

import java.util.*;

/**
 * Next Greater Element 2 (Circular Array)
 * 
 * Problem: Given a circular array, find the next greater element.
 *          The array wraps around (after last element comes first).
 * 
 * Example: [1, 2, 1]
 * Output:  [2, -1, 2]
 * 
 * Explanation:
 * Index 0: Element 1 → next greater is 2 (at index 1)
 * Index 1: Element 2 → no greater element in circular array
 * Index 2: Element 1 → next greater is 2 (at index 1, wrapping around)
 * 
 * Algorithm: Monotonic Stack (Process 2*n times)
 * 1. Use modulo to handle circular nature
 * 2. Process elements twice (to handle circular wrapping)
 * 3. For second pass, skip if result already found
 * 4. Otherwise, same as regular NGE
 * 
 * Time Complexity: O(n) - process 2n times but each element pushed/popped once
 * Space Complexity: O(n) - for stack and result array
 */

public class NextGreaterElement2 {
    
    /**
     * Find next greater element in circular array
     */
    public static int[] nextGreaterElementCircular(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);
        Stack<Integer> stack = new Stack<>();
        
        // Process 2*n times to handle circular nature
        for (int i = 0; i < 2 * n; i++) {
            int idx = i % n;
            
            // Pop elements that are <= current
            while (!stack.isEmpty() && arr[stack.peek()] < arr[idx]) {
                result[stack.pop()] = arr[idx];
            }
            
            // Push on first pass only (to avoid reprocessing)
            if (i < n) {
                stack.push(idx);
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        System.out.println("=== Next Greater Element 2 (Circular Array) ===\n");
        
        int[][] testCases = {
            {1, 2, 1},
            {1, 2, 3, 4, 3},
            {5, 4, 3, 2, 1},
            {1, 1, 1, 1},
            {2, 10, 12, 1, 11}
        };
        
        System.out.println(String.format("%-35s | %-35s", "Circular Array", "NGE"));
        System.out.println("-".repeat(75));
        
        for (int[] arr : testCases) {
            int[] result = nextGreaterElementCircular(arr);
            System.out.println(String.format("%-35s | %-35s", 
                Arrays.toString(arr), Arrays.toString(result)));
        }
        
        System.out.println("\n" + "=".repeat(75));
        System.out.println("DETAILED EXAMPLE: [1, 2, 1]");
        System.out.println("-".repeat(75));
        
        int[] arr = {1, 2, 1};
        int n = arr.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);
        Stack<Integer> stack = new Stack<>();
        
        System.out.println("\nCircular array visualization:");
        System.out.println("Index: 0  1  2  | 0  1  2  | (wrapping)");
        System.out.println("Array: 1  2  1  | 1  2  1  | (for circular)");
        System.out.println();
        
        System.out.println("Processing 2*n = 6 iterations:\n");
        
        for (int i = 0; i < 2 * n; i++) {
            int idx = i % n;
            System.out.println("Iteration " + (i + 1) + ": Index " + idx + " (value " + arr[idx] + ")");
            System.out.println("  Stack before: " + stack);
            
            int count = 0;
            while (!stack.isEmpty() && arr[stack.peek()] < arr[idx]) {
                int popped = stack.pop();
                result[popped] = arr[idx];
                System.out.println("    Found NGE: arr[" + popped + "] = " + arr[popped] + 
                                 " → next greater = " + arr[idx]);
                count++;
            }
            
            if (count == 0) {
                System.out.println("    No elements to pop");
            }
            
            if (i < n) {
                stack.push(idx);
                System.out.println("  Push index " + idx);
            }
            
            System.out.println("  Stack after: " + stack);
            System.out.println();
        }
        
        System.out.println("Final Result: " + Arrays.toString(result));
        
        System.out.println("\n" + "=".repeat(75));
        System.out.println("STEP-BY-STEP VISUALIZATION:");
        System.out.println("-".repeat(75));
        
        arr = new int[]{1, 2, 1};
        result = new int[n];
        Arrays.fill(result, -1);
        stack = new Stack<>();
        
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("\nVisual representation of circular nature:");
        System.out.println("... → [1, 2, 1] → [1, 2, 1] → [1, 2, 1] → ...");
        System.out.println("      Original   After 1st  After 2nd wrap");
        
        System.out.println("\nDetailed trace:\n");
        
        for (int i = 0; i < 2 * n; i++) {
            int idx = i % n;
            System.out.print("Iteration " + (i + 1) + " (Index " + idx + "): ");
            
            while (!stack.isEmpty() && arr[stack.peek()] < arr[idx]) {
                int popped = stack.pop();
                result[popped] = arr[idx];
                System.out.print("found NGE for " + popped + ", ");
            }
            
            if (i < n) {
                stack.push(idx);
                System.out.print("pushed " + idx);
            }
            
            System.out.println();
        }
        
        System.out.println("\nFinal: " + Arrays.toString(result));
        
        System.out.println("\n" + "=".repeat(75));
        System.out.println("ANOTHER EXAMPLE: [2, 10, 12, 1, 11]");
        System.out.println("-".repeat(75));
        
        arr = new int[]{2, 10, 12, 1, 11};
        result = nextGreaterElementCircular(arr);
        
        System.out.println("\nArray:  " + Arrays.toString(arr));
        System.out.println("Output: " + Arrays.toString(result));
        
        System.out.println("\nExplanation:");
        System.out.println("Index 0: 2 → 10 (next greater)");
        System.out.println("Index 1: 10 → 12 (next greater)");
        System.out.println("Index 2: 12 → -1 (no greater, even after wrap)");
        System.out.println("Index 3: 1 → 11 (wrap around to 11)");
        System.out.println("Index 4: 11 → 2 (wrap to 2 after 11)");
        System.out.println("        Actually: 11 wraps back, no greater found");
        
        System.out.println("\n" + "=".repeat(75));
        System.out.println("KEY DIFFERENCES FROM REGULAR NGE:");
        System.out.println("-".repeat(75));
        System.out.println("1. Array wraps around (circular nature)");
        System.out.println("2. Process 2*n times instead of n");
        System.out.println("3. Use modulo (i % n) to map to original indices");
        System.out.println("4. Only push indices during first pass (i < n)");
        System.out.println("5. Allows elements to wrap around and find NGE");
        
        System.out.println("\n" + "=".repeat(75));
        System.out.println("WHY 2*n ITERATIONS:");
        System.out.println("-".repeat(75));
        System.out.println("• First n iterations: process array from start");
        System.out.println("• Second n iterations: wrap around to find NGE for remaining");
        System.out.println("• After 2*n iterations, all NGEs found or confirmed as -1");
        System.out.println("• Stack remains after processing guarantees no greater element");
    }
}
