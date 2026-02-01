package stacks_queues.intro;

import java.util.*;

/**
 * Implement Min Stack
 * 
 * Problem: Implement a stack that supports:
 * 1. push(x)
 * 2. pop()
 * 3. top()
 * 4. getMin() - return minimum element in O(1) time
 * 
 * Approaches:
 * 1. Brute Force: Iterate through stack to find min - O(n)
 * 2. Optimal: Maintain a min stack alongside main stack - O(1)
 * 
 * Min Stack Approach:
 * - Main stack: stores all elements
 * - Min stack: stores minimum values
 * - When pushing: push to main, push min(x, top of min stack) to min stack
 * - When popping: pop from both stacks
 * - getMin(): peek top of min stack
 * 
 * Example:
 * Push 3:   main=[3],      min=[3]
 * Push 2:   main=[3,2],    min=[3,2]
 * Push 4:   main=[3,2,4],  min=[3,2,2]
 * Push 1:   main=[3,2,4,1],min=[3,2,2,1]
 * getMin(): returns 1
 * Pop:      main=[3,2,4],  min=[3,2,2], getMin()=2
 * 
 * Space Trade-off:
 * - Extra space: O(n) for min stack
 * - Time benefit: O(1) getMin() instead of O(n)
 * 
 * Time Complexity: O(1) for all operations
 * Space Complexity: O(n)
 */

public class MinStack {
    
    /**
     * Approach 1: Using 2 Stacks
     */
    public static class MinStackTwoStacks {
        private Stack<Integer> main;
        private Stack<Integer> minStack;
        
        public MinStackTwoStacks() {
            main = new Stack<>();
            minStack = new Stack<>();
        }
        
        /**
         * Push element
         * Time: O(1)
         */
        public void push(int x) {
            main.push(x);
            
            if (minStack.isEmpty() || x <= minStack.peek()) {
                minStack.push(x);
            }
        }
        
        /**
         * Pop element
         * Time: O(1)
         */
        public int pop() {
            if (main.isEmpty()) {
                System.out.println("Stack Underflow!");
                return -1;
            }
            
            int value = main.pop();
            if (value == minStack.peek()) {
                minStack.pop();
            }
            
            return value;
        }
        
        /**
         * Get top element
         * Time: O(1)
         */
        public int top() {
            if (main.isEmpty()) {
                System.out.println("Stack is empty!");
                return -1;
            }
            return main.peek();
        }
        
        /**
         * Get minimum element
         * Time: O(1)
         */
        public int getMin() {
            if (minStack.isEmpty()) {
                System.out.println("Stack is empty!");
                return -1;
            }
            return minStack.peek();
        }
        
        public boolean isEmpty() {
            return main.isEmpty();
        }
        
        public int size() {
            return main.size();
        }
        
        public void print() {
            System.out.println("Main stack: " + main);
            System.out.println("Min stack:  " + minStack);
        }
    }
    
    /**
     * Approach 2: Using single stack with pairs (element, min)
     */
    public static class MinStackSingleStack {
        private Stack<Integer[]> stack;  // Stores [element, min at that point]
        
        public MinStackSingleStack() {
            stack = new Stack<>();
        }
        
        public void push(int x) {
            if (stack.isEmpty()) {
                stack.push(new Integer[]{x, x});
            } else {
                int currentMin = stack.peek()[1];
                stack.push(new Integer[]{x, Math.min(x, currentMin)});
            }
        }
        
        public int pop() {
            if (stack.isEmpty()) {
                System.out.println("Stack Underflow!");
                return -1;
            }
            return stack.pop()[0];
        }
        
        public int top() {
            if (stack.isEmpty()) {
                System.out.println("Stack is empty!");
                return -1;
            }
            return stack.peek()[0];
        }
        
        public int getMin() {
            if (stack.isEmpty()) {
                System.out.println("Stack is empty!");
                return -1;
            }
            return stack.peek()[1];
        }
        
        public boolean isEmpty() {
            return stack.isEmpty();
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== Min Stack Implementation ===\n");
        
        MinStackTwoStacks minStack = new MinStackTwoStacks();
        
        System.out.println("Operations:");
        System.out.println("-".repeat(50));
        
        System.out.println("1. Push 3, 2, 4, 1");
        minStack.push(3);
        minStack.push(2);
        minStack.push(4);
        minStack.push(1);
        minStack.print();
        System.out.println("Min: " + minStack.getMin());
        System.out.println();
        
        System.out.println("2. Pop: " + minStack.pop());
        minStack.print();
        System.out.println("Min: " + minStack.getMin());
        System.out.println();
        
        System.out.println("3. Push 0");
        minStack.push(0);
        minStack.print();
        System.out.println("Min: " + minStack.getMin());
        System.out.println();
        
        System.out.println("4. Pop: " + minStack.pop());
        System.out.println("Pop: " + minStack.pop());
        minStack.print();
        System.out.println("Min: " + minStack.getMin());
        System.out.println();
        
        System.out.println("5. Pop all:");
        while (!minStack.isEmpty()) {
            System.out.println("Pop: " + minStack.pop() + ", Min: " + minStack.getMin());
        }
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("DETAILED STEP-BY-STEP: Push 3, 2, 4, 1");
        System.out.println("-".repeat(50));
        
        MinStackTwoStacks stack = new MinStackTwoStacks();
        
        System.out.println("\nPush 3:");
        stack.push(3);
        System.out.println("  main=[3], min=[3]");
        System.out.println("  getMin()=3");
        
        System.out.println("\nPush 2:");
        stack.push(2);
        System.out.println("  2 < 3, so push 2 to min");
        System.out.println("  main=[3,2], min=[3,2]");
        System.out.println("  getMin()=2");
        
        System.out.println("\nPush 4:");
        stack.push(4);
        System.out.println("  4 > 2, so don't push to min");
        System.out.println("  main=[3,2,4], min=[3,2]");
        System.out.println("  getMin()=2");
        
        System.out.println("\nPush 1:");
        stack.push(1);
        System.out.println("  1 < 2, so push 1 to min");
        System.out.println("  main=[3,2,4,1], min=[3,2,1]");
        System.out.println("  getMin()=1");
        
        System.out.println("\nPop:");
        System.out.println("  pop()=1, also pop from min (1==getMin())");
        stack.pop();
        System.out.println("  main=[3,2,4], min=[3,2]");
        System.out.println("  getMin()=2");
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("COMPLEXITY ANALYSIS:");
        System.out.println("-".repeat(50));
        System.out.println("push():     O(1) - constant time operations");
        System.out.println("pop():      O(1) - constant time operations");
        System.out.println("top():      O(1) - peek from main stack");
        System.out.println("getMin():   O(1) - peek from min stack");
        System.out.println("\nSpace Complexity: O(n)");
        System.out.println("  - Main stack: n elements");
        System.out.println("  - Min stack: up to n elements");
        System.out.println("  - Total: O(n)");
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("TWO APPROACHES COMPARISON:");
        System.out.println("-".repeat(50));
        System.out.println("Approach 1: Two Stacks");
        System.out.println("  Space: 2n (may be optimized)");
        System.out.println("  Clean separation of concerns");
        System.out.println("\nApproach 2: Single Stack with Pairs");
        System.out.println("  Space: n pairs (slight overhead)");
        System.out.println("  Single data structure");
    }
}
