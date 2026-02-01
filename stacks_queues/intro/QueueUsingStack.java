package stacks_queues.intro;

import java.util.*;

/**
 * Implement Queue using Stack
 * 
 * Problem: Implement a queue using stack(s).
 * 
 * Approach 1: Using 2 Stacks
 * - s1: Main stack (input stack)
 * - s2: Temp stack (output stack)
 * 
 * Operations:
 * 1. enqueue(x): Push to s1
 *    - Time: O(1)
 * 2. dequeue(): 
 *    - If s2 is not empty, pop from s2
 *    - Else, move all from s1 to s2, then pop from s2
 *    - Time: O(1) amortized
 * 3. front(): 
 *    - If s2 is not empty, peek s2
 *    - Else, move all from s1 to s2, then peek s2
 *    - Time: O(1) amortized
 * 
 * Key Idea:
 * - s1 maintains insertion order (bottom to top = oldest to newest)
 * - s2 reverses the order (top to bottom = oldest to newest)
 * - Dequeue operations pop from s2
 * 
 * Total Time:
 * - enqueue(): O(1)
 * - dequeue(): O(1) amortized
 * - front(): O(1) amortized
 */

public class QueueUsingStack {
    private Stack<Integer> s1;  // Input stack
    private Stack<Integer> s2;  // Output stack
    
    /**
     * Constructor
     */
    public QueueUsingStack() {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }
    
    /**
     * Add element to queue
     * Time: O(1)
     */
    public void enqueue(int x) {
        s1.push(x);
    }
    
    /**
     * Helper: Move all elements from s1 to s2
     */
    private void moveS1ToS2() {
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }
    }
    
    /**
     * Remove and return front element
     * Time: O(1) amortized
     */
    public int dequeue() {
        moveS1ToS2();
        
        if (s2.isEmpty()) {
            System.out.println("Queue Underflow!");
            return -1;
        }
        return s2.pop();
    }
    
    /**
     * View front element
     * Time: O(1) amortized
     */
    public int front() {
        moveS1ToS2();
        
        if (s2.isEmpty()) {
            System.out.println("Queue is empty!");
            return -1;
        }
        return s2.peek();
    }
    
    /**
     * Check if queue is empty
     */
    public boolean isEmpty() {
        return s1.isEmpty() && s2.isEmpty();
    }
    
    /**
     * Get size of queue
     */
    public int size() {
        return s1.size() + s2.size();
    }
    
    /**
     * Print all elements
     */
    public void print() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }
        System.out.print("Queue (front to rear): ");
        
        // Print s2 first (these will dequeue first)
        Stack<Integer> tempS2 = new Stack<>();
        while (!s2.isEmpty()) {
            int x = s2.pop();
            System.out.print(x + " ");
            tempS2.push(x);
        }
        while (!tempS2.isEmpty()) {
            s2.push(tempS2.pop());
        }
        
        // Print s1
        Stack<Integer> tempS1 = new Stack<>();
        while (!s1.isEmpty()) {
            int x = s1.pop();
            tempS1.push(x);
        }
        while (!tempS1.isEmpty()) {
            int x = tempS1.pop();
            System.out.print(x + " ");
            s1.push(x);
        }
        
        System.out.println();
    }
    
    public static void main(String[] args) {
        System.out.println("=== Queue Implementation using Stack ===\n");
        
        QueueUsingStack queue = new QueueUsingStack();
        
        System.out.println("Operations:");
        System.out.println("-".repeat(50));
        
        System.out.println("1. Enqueue 10, 20, 30, 40, 50");
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.enqueue(40);
        queue.enqueue(50);
        queue.print();
        System.out.println("Size: " + queue.size());
        System.out.println();
        
        System.out.println("2. Front element: " + queue.front());
        System.out.println();
        
        System.out.println("3. Dequeue 2 elements:");
        System.out.println("Dequeued: " + queue.dequeue());
        queue.print();
        System.out.println("Dequeued: " + queue.dequeue());
        queue.print();
        System.out.println();
        
        System.out.println("4. Enqueue 60, 70");
        queue.enqueue(60);
        queue.enqueue(70);
        queue.print();
        System.out.println("Size: " + queue.size());
        System.out.println();
        
        System.out.println("5. Dequeue all elements:");
        while (!queue.isEmpty()) {
            System.out.println("Dequeued: " + queue.dequeue());
        }
        queue.print();
        System.out.println();
        
        System.out.println("6. Try dequeue from empty queue:");
        queue.dequeue();
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("DETAILED EXAMPLE: Enqueue 10, 20, 30");
        System.out.println("-".repeat(50));
        
        QueueUsingStack q = new QueueUsingStack();
        
        System.out.println("\nEnqueue 10, 20, 30:");
        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        System.out.println("s1 (top to bottom): 30, 20, 10");
        System.out.println("s2 (top to bottom): empty");
        q.print();
        
        System.out.println("\nDequeue (1st call):");
        System.out.println("  moveS1ToS2(): Transfer [10, 20, 30] from s1 to s2");
        System.out.println("  s1 becomes: empty");
        System.out.println("  s2 becomes: 10, 20, 30 (top to bottom)");
        System.out.println("  Return s2.pop() = 10");
        System.out.println("Remaining: 20, 30");
        q.dequeue();
        q.print();
        
        System.out.println("\nDequeue (2nd call):");
        System.out.println("  s2 is not empty, so s1->s2 transfer skipped");
        System.out.println("  Return s2.pop() = 20");
        q.dequeue();
        q.print();
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("HOW IT WORKS:");
        System.out.println("-".repeat(50));
        System.out.println("Stack is LIFO, Queue is FIFO");
        System.out.println("2 Stacks used:");
        System.out.println("  s1: Stores new elements (input stack)");
        System.out.println("  s2: Reversed order for FIFO (output stack)");
        System.out.println("\nTransferring s1 to s2 reverses the order,");
        System.out.println("converting LIFO to FIFO behavior");
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("COMPLEXITY ANALYSIS:");
        System.out.println("-".repeat(50));
        System.out.println("enqueue():  O(1)");
        System.out.println("dequeue():  O(1) amortized");
        System.out.println("front():    O(1) amortized");
        System.out.println("isEmpty():  O(1)");
        System.out.println("\nSpace Complexity: O(n)");
        
        System.out.println("\nAmortized Analysis:");
        System.out.println("- Each element is moved at most once");
        System.out.println("- Total n enqueues and n dequeues = O(n) total");
        System.out.println("- Average per operation = O(n)/2n = O(1)");
    }
}
