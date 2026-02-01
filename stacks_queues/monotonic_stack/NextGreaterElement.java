package stacks_queues.monotonic_stack;

import java.util.*;

/**
 * Next Greater Element
 * 
 * Problem: Given an array, find the next greater element for each element.
 *          For each element, find the first greater element to its right.
 *          If no greater element exists, output -1.
 * 
 * Example: [1, 5, 0, 3, 4, 5]
 * Output:  [5, -1, 3, 4, 5, -1]
 * 
 * Explanation:
 * 1 -> 5 (next element greater than 1)
 * 5 -> -1 (no element greater than 5 to the right)
 * 0 -> 3 (next element greater than 0)
 * 3 -> 4 (next element greater than 3)
 * 4 -> 5 (next element greater than 4)
 * 5 -> -1 (no element greater than 5)
 * 
 * Algorithm: Monotonic Stack (Right to Left)
 * 1. Process elements from RIGHT to LEFT
 * 2. Use a stack to maintain decreasing order
 * 3. For each element:
 *    - Pop elements <= current from stack
 *    - If stack not empty, top is next greater element
 *    - Push current element to stack
 * 
 * Time Complexity: O(n) - each element pushed/popped once
 * Space Complexity: O(n) - for stack and result array
 */

public class NextGreaterElement {
    
    /**
     * Find next greater element for each element in array
     */
    public static int[] nextGreaterElement(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();
        
        // Process from right to left
        for (int i = n - 1; i >= 0; i--) {
            // Pop elements that are <= current element
            while (!stack.isEmpty() && stack.peek() <= arr[i]) {
                stack.pop();
            }
            
            // If stack is empty, no greater element
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            
            // Push current element
            stack.push(arr[i]);
        }
        
        return result;
    }
    
    /**
     * Find next greater element with indices
     */
    public static int[] nextGreaterElementWithIndices(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>(); // Store indices
        
        // Process from right to left
        for (int i = n - 1; i >= 0; i--) {
            // Pop elements that are <= current element
            while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
                stack.pop();
            }
            
            // If stack is empty, no greater element
            result[i] = stack.isEmpty() ? -1 : arr[stack.peek()];
            
            // Push current index
            stack.push(i);
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        System.out.println("=== Next Greater Element ===\n");
        
        int[][] testCases = {
            {1, 5, 0, 3, 4, 5},
            {1, 2, 3, 4, 5},
            {5, 4, 3, 2, 1},
            {1, 1, 1, 1},
            {2, 1, 2, 4, 3}
        };
        
        System.out.println(String.format("%-30s | %-30s", "Array", "NGE"));
        System.out.println("-".repeat(65));
        
        for (int[] arr : testCases) {
            int[] result = nextGreaterElement(arr);
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
            "Index", "Element", "Stack (top→bottom)", "NGE"));
        System.out.println("-".repeat(65));
        
        for (int i = n - 1; i >= 0; i--) {
            System.out.print(String.format("%-8d | %-8d | ", i, arr[i]));
            
            // Pop elements <= current
            while (!stack.isEmpty() && stack.peek() <= arr[i]) {
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
        System.out.println("STEP-BY-STEP TRACE:");
        System.out.println("-".repeat(65));
        
        arr = new int[]{1, 5, 0, 3, 4, 5};
        stack = new Stack<>();
        
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("\nStep-by-step processing (right to left):\n");
        
        for (int i = n - 1; i >= 0; i--) {
            System.out.println("Step " + (n - i) + ": Index " + i + ", Element " + arr[i]);
            
            System.out.println("  Stack before: " + stack);
            
            int count = 0;
            while (!stack.isEmpty() && stack.peek() <= arr[i]) {
                int popped = stack.pop();
                System.out.println("    Pop " + popped + " (≤ " + arr[i] + ")");
                count++;
            }
            
            if (count == 0) {
                System.out.println("    No elements to pop");
            }
            
            if (stack.isEmpty()) {
                System.out.println("  Stack is empty → NGE = -1");
                result[i] = -1;
            } else {
                System.out.println("  Stack top " + stack.peek() + " > " + arr[i] + " → NGE = " + stack.peek());
                result[i] = stack.peek();
            }
            
            stack.push(arr[i]);
            System.out.println("  Push " + arr[i]);
            System.out.println("  Stack after: " + stack);
            System.out.println();
        }
        
        System.out.println("Final Result: " + Arrays.toString(result));
        
        System.out.println("\n" + "=".repeat(65));
        System.out.println("MONOTONIC STACK PROPERTIES:");
        System.out.println("-".repeat(65));
        System.out.println("1. Stack maintains DECREASING order from top to bottom");
        System.out.println("2. Process from RIGHT to LEFT");
        System.out.println("3. Pop all elements ≤ current element");
        System.out.println("4. Top of stack (if exists) is the NGE");
        System.out.println("5. After processing, push current element");
        
        System.out.println("\n" + "=".repeat(65));
        System.out.println("WHY THIS WORKS:");
        System.out.println("-".repeat(65));
        System.out.println("• When processing element i, all elements to the right are in stack");
        System.out.println("• Stack is in decreasing order");
        System.out.println("• If element ≤ current, it can never be NGE for any element");
        System.out.println("• First element > current in stack is the NGE");
        System.out.println("• Time: O(n) because each element pushed/popped once");
        
        System.out.println("\n" + "=".repeat(65));
        System.out.println("ANOTHER EXAMPLE: [2, 1, 2, 4, 3]");
        System.out.println("-".repeat(65));
        
        arr = new int[]{2, 1, 2, 4, 3};
        result = nextGreaterElement(arr);
        System.out.println("\nArray:  " + Arrays.toString(arr));
        System.out.println("Output: " + Arrays.toString(result));
        
        System.out.println("\nExplanation:");
        System.out.println("Index 0: 2 → 4 (next greater)");
        System.out.println("Index 1: 1 → 2 (next greater)");
        System.out.println("Index 2: 2 → 4 (next greater)");
        System.out.println("Index 3: 4 → -1 (no next greater)");
        System.out.println("Index 4: 3 → -1 (no next greater)");
    }
}
