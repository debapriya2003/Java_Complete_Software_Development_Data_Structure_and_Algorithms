package stacks_queues.intro;

import java.util.*;

/**
 * Implement Stack using Queue
 * 
 * Problem: Implement a stack using queue(s).
 * 
 * Approach: Using 2 Queues
 * - q1: Stores all elements (LIFO behavior)
 * - q2: Helper queue for temporary storage
 * 
 * Operations:
 * 1. push(x): Enqueue to q2, move all from q1 to q2, swap q1 and q2
 *    - Time: O(n)
 * 2. pop(): Dequeue from q1
 *    - Time: O(1)
 * 3. top(): Peek from q1
 *    - Time: O(1)
 * 4. isEmpty(): Check if q1 is empty
 *    - Time: O(1)
 * 
 * Alternative Approach: Using 1 Queue
 * - When pushing, first enqueue new element
 * - Then dequeue all n elements and enqueue them back
 * - This moves new element to back (which becomes top of stack)
 * - Time: O(n) for each push
 * 
 * Total Time:
 * - push(): O(n)
 * - pop(): O(1)
 * - top(): O(1)
 */

public class StackUsingQueue {
    private Queue<Integer> q1;
    private Queue<Integer> q2;
    
    /**
     * Constructor
     */
    public StackUsingQueue() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }
    
    /**
     * Push element to stack (expensive operation)
     * Time: O(n)
     */
    public void push(int x) {
        // Enqueue to q2
        q2.offer(x);
        
        // Move all elements from q1 to q2
        while (!q1.isEmpty()) {
            q2.offer(q1.poll());
        }
        
        // Swap q1 and q2
        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;
    }
    
    /**
     * Pop element from stack
     * Time: O(1)
     */
    public int pop() {
        if (q1.isEmpty()) {
            System.out.println("Stack Underflow!");
            return -1;
        }
        return q1.poll();
    }
    
    /**
     * Get top element
     * Time: O(1)
     */
    public int top() {
        if (q1.isEmpty()) {
            System.out.println("Stack is empty!");
            return -1;
        }
        return q1.peek();
    }
    
    /**
     * Check if stack is empty
     */
    public boolean isEmpty() {
        return q1.isEmpty();
    }
    
    /**
     * Get size of stack
     */
    public int size() {
        return q1.size();
    }
    
    /**
     * Print all elements
     */
    public void print() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return;
        }
        System.out.print("Stack (top to bottom): ");
        Object[] elements = q1.toArray();
        for (Object e : elements) {
            System.out.print(e + " ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        System.out.println("=== Stack Implementation using Queue ===\n");
        
        StackUsingQueue stack = new StackUsingQueue();
        
        System.out.println("Operations:");
        System.out.println("-".repeat(50));
        
        System.out.println("1. Push 10, 20, 30, 40, 50");
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);
        stack.push(50);
        stack.print();
        System.out.println("Size: " + stack.size());
        System.out.println();
        
        System.out.println("2. Top element: " + stack.top());
        System.out.println();
        
        System.out.println("3. Pop elements:");
        System.out.println("Popped: " + stack.pop());
        stack.print();
        System.out.println("Popped: " + stack.pop());
        stack.print();
        System.out.println();
        
        System.out.println("4. Push 60, 70");
        stack.push(60);
        stack.push(70);
        stack.print();
        System.out.println("Size: " + stack.size());
        System.out.println();
        
        System.out.println("5. Pop all elements:");
        while (!stack.isEmpty()) {
            System.out.println("Popped: " + stack.pop());
        }
        stack.print();
        System.out.println();
        
        System.out.println("6. Try pop from empty stack:");
        stack.pop();
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("DETAILED EXAMPLE: Push 10, 20, 30");
        System.out.println("-".repeat(50));
        
        StackUsingQueue s = new StackUsingQueue();
        
        System.out.println("\nPush 10:");
        System.out.println("  1. q2.offer(10) -> q2 = [10]");
        System.out.println("  2. Move q1 to q2 -> q2 = [10]");
        System.out.println("  3. Swap -> q1 = [10], q2 = []");
        s.push(10);
        s.print();
        
        System.out.println("\nPush 20:");
        System.out.println("  1. q2.offer(20) -> q2 = [20]");
        System.out.println("  2. Move q1 to q2 -> q2 = [20, 10]");
        System.out.println("  3. Swap -> q1 = [20, 10], q2 = []");
        s.push(20);
        s.print();
        
        System.out.println("\nPush 30:");
        System.out.println("  1. q2.offer(30) -> q2 = [30]");
        System.out.println("  2. Move q1 to q2 -> q2 = [30, 20, 10]");
        System.out.println("  3. Swap -> q1 = [30, 20, 10], q2 = []");
        s.push(30);
        s.print();
        
        System.out.println("\nPop from [30, 20, 10]:");
        System.out.println("  q1.poll() -> Returns 30 (top of stack)");
        System.out.println("  Remaining: " + s.top());
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("COMPLEXITY ANALYSIS:");
        System.out.println("-".repeat(50));
        System.out.println("push():     O(n) - Need to move all elements");
        System.out.println("pop():      O(1)");
        System.out.println("top():      O(1)");
        System.out.println("isEmpty():  O(1)");
        System.out.println("\nSpace Complexity: O(n)");
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("KEY INSIGHT:");
        System.out.println("-".repeat(50));
        System.out.println("Queue is FIFO, Stack is LIFO");
        System.out.println("To simulate stack behavior:");
        System.out.println("- New element must reach front for pop");
        System.out.println("- This requires moving all existing elements");
        System.out.println("- Hence O(n) time complexity for push");
    }
}
