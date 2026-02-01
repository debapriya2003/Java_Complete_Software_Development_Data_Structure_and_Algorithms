package stacks_queues.monotonic_stack;

import java.util.*;

/**
 * Next Smaller Element
 * 
 * Problem: Find the next smaller element for each element in array.
 *          For each element, find the first smaller element to its right.
 *          If no smaller element exists, output -1.
 * 
 * Example: [1, 5, 0, 3, 4, 5]
 * Output:  [-1, 0, -1, -1, -1, -1]
 * 
 * Explanation:
 * 1 -> -1 (no smaller to right)
 * 5 -> 0 (next smaller element)
 * 0 -> -1 (no smaller)
 * 3 -> -1 (no smaller to right)
 * 4 -> -1 (no smaller)
 * 5 -> -1 (no smaller)
 * 
 * Algorithm: Monotonic Stack (Right to Left)
 * Similar to NGE but maintains INCREASING order (opposite)
 * 1. Process elements from RIGHT to LEFT
 * 2. Use a stack to maintain increasing order
 * 3. For each element:
 *    - Pop elements >= current from stack
 *    - If stack not empty, top is next smaller element
 *    - Push current element to stack
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

public class NextSmallerElement {
    
    /**
     * Find next smaller element for each element
     */
    public static int[] nextSmallerElement(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();
        
        // Process from right to left
        for (int i = n - 1; i >= 0; i--) {
            // Pop elements >= current (we want smaller)
            while (!stack.isEmpty() && stack.peek() >= arr[i]) {
                stack.pop();
            }
            
            // Top of stack is next smaller
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            
            // Push current element
            stack.push(arr[i]);
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        System.out.println("=== Next Smaller Element ===\n");
        
        int[][] testCases = {
            {1, 5, 0, 3, 4, 5},
            {1, 2, 3, 4, 5},
            {5, 4, 3, 2, 1},
            {1, 1, 1, 1},
            {2, 1, 2, 4, 3}
        };
        
        System.out.println(String.format("%-30s | %-30s", "Array", "NSE"));
        System.out.println("-".repeat(65));
        
        for (int[] arr : testCases) {
            int[] result = nextSmallerElement(arr);
            System.out.println(String.format("%-30s | %-30s", 
                Arrays.toString(arr), Arrays.toString(result)));
        }
        
        System.out.println("\n" + "=".repeat(65));
        System.out.println("DETAILED EXAMPLE: [1, 5, 0, 3, 4, 5]");
        System.out.println("-".repeat(65));
        
        int[] arr = {1, 5, 0, 3, 4, 5};
        int n = arr.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();
        
        System.out.println("\nProcessing from right to left:\n");
        System.out.println(String.format("%-8s | %-8s | %-20s | %-20s", 
            "Index", "Element", "Stack (top→bottom)", "NSE"));
        System.out.println("-".repeat(65));
        
        for (int i = n - 1; i >= 0; i--) {
            System.out.print(String.format("%-8d | %-8d | ", i, arr[i]));
            
            // Pop elements >= current
            while (!stack.isEmpty() && stack.peek() >= arr[i]) {
                stack.pop();
            }
            
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(arr[i]);
            
            List<Integer> stackList = new ArrayList<>(stack);
            Collections.reverse(stackList);
            System.out.println(String.format("%-20s | %-20s", 
                stackList, result[i]));
        }
        
        System.out.println("\nFinal Result: " + Arrays.toString(result));
        
        System.out.println("\n" + "=".repeat(65));
        System.out.println("COMPARISON: NGE vs NSE");
        System.out.println("-".repeat(65));
        
        System.out.println("\nNGE (Next Greater Element):");
        System.out.println("  Stack Order: DECREASING (top has largest elements)");
        System.out.println("  Pop Condition: stack.peek() <= current");
        System.out.println("  Result: First element > current");
        
        System.out.println("\nNSE (Next Smaller Element):");
        System.out.println("  Stack Order: INCREASING (top has smallest elements)");
        System.out.println("  Pop Condition: stack.peek() >= current");
        System.out.println("  Result: First element < current");
        
        System.out.println("\n" + "=".repeat(65));
        System.out.println("DETAILED TRACE: [1, 5, 0, 3, 4, 5]");
        System.out.println("-".repeat(65));
        
        arr = new int[]{1, 5, 0, 3, 4, 5};
        stack = new Stack<>();
        
        System.out.println("\nArray: " + Arrays.toString(arr));
        System.out.println("\nStep-by-step (right to left):\n");
        
        for (int i = n - 1; i >= 0; i--) {
            System.out.println("Step " + (n - i) + ": Index " + i + ", Element " + arr[i]);
            System.out.println("  Stack before: " + stack);
            
            int count = 0;
            while (!stack.isEmpty() && stack.peek() >= arr[i]) {
                int popped = stack.pop();
                System.out.println("    Pop " + popped + " (≥ " + arr[i] + ")");
                count++;
            }
            
            if (count == 0) {
                System.out.println("    No elements to pop");
            }
            
            if (stack.isEmpty()) {
                System.out.println("  Stack is empty → NSE = -1");
                result[i] = -1;
            } else {
                System.out.println("  Stack top " + stack.peek() + " < " + arr[i] + " → NSE = " + stack.peek());
                result[i] = stack.peek();
            }
            
            stack.push(arr[i]);
            System.out.println("  Push " + arr[i]);
            System.out.println("  Stack after: " + stack);
            System.out.println();
        }
        
        System.out.println("Final Result: " + Arrays.toString(result));
        
        System.out.println("\n" + "=".repeat(65));
        System.out.println("ANOTHER EXAMPLE: [2, 1, 2, 4, 3]");
        System.out.println("-".repeat(65));
        
        arr = new int[]{2, 1, 2, 4, 3};
        result = nextSmallerElement(arr);
        
        System.out.println("\nArray:  " + Arrays.toString(arr));
        System.out.println("Output: " + Arrays.toString(result));
        
        System.out.println("\nExplanation:");
        System.out.println("Index 0: 2 → 1 (next smaller)");
        System.out.println("Index 1: 1 → -1 (no smaller)");
        System.out.println("Index 2: 2 → -1 (no smaller to right)");
        System.out.println("Index 3: 4 → 3 (next smaller)");
        System.out.println("Index 4: 3 → -1 (no smaller to right)");
        
        System.out.println("\n" + "=".repeat(65));
        System.out.println("KEY POINTS:");
        System.out.println("-".repeat(65));
        System.out.println("1. Stack maintains INCREASING order (unlike NGE)");
        System.out.println("2. Pop elements ≥ current (unlike NGE which pops ≤)");
        System.out.println("3. First element < current is the NSE");
        System.out.println("4. Time Complexity: O(n)");
        System.out.println("5. Space Complexity: O(n)");
        
        System.out.println("\n" + "=".repeat(65));
        System.out.println("MONOTONIC STACK PATTERN:");
        System.out.println("-".repeat(65));
        System.out.println("NGE:  Decreasing stack + Pop ≤  → Next Greater");
        System.out.println("NSE:  Increasing stack + Pop ≥  → Next Smaller");
        System.out.println("PGE:  Decreasing stack + Pop ≤  → Previous Greater");
        System.out.println("PSE:  Increasing stack + Pop ≥  → Previous Smaller");
        System.out.println("\nPattern: Higher value → Pop smaller elements (decreasing)");
        System.out.println("         Smaller value → Pop larger elements (increasing)");
    }
}
