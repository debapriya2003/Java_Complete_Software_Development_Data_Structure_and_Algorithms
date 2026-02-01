package stacks_queues.intro;

/**
 * Implement Stack using Arrays
 * 
 * Stack: LIFO (Last In First Out) data structure
 * 
 * Operations:
 * 1. push(x): Add element to top of stack - O(1)
 * 2. pop(): Remove and return top element - O(1)
 * 3. peek(): View top element without removing - O(1)
 * 4. isEmpty(): Check if stack is empty - O(1)
 * 5. size(): Return number of elements - O(1)
 * 
 * Array Implementation:
 * - Use array to store elements
 * - Maintain a pointer 'top' to track top of stack
 * - Initially top = -1 (empty stack)
 * 
 * Time Complexity: O(1) for all operations
 * Space Complexity: O(n) where n is max capacity
 */

public class StackArray {
    private int[] arr;
    private int top;
    private int capacity;
    
    /**
     * Constructor to initialize stack with given capacity
     */
    public StackArray(int capacity) {
        this.capacity = capacity;
        this.arr = new int[capacity];
        this.top = -1;
    }
    
    /**
     * Check if stack is empty
     */
    public boolean isEmpty() {
        return top == -1;
    }
    
    /**
     * Check if stack is full
     */
    public boolean isFull() {
        return top == capacity - 1;
    }
    
    /**
     * Get current size of stack
     */
    public int size() {
        return top + 1;
    }
    
    /**
     * Push element onto stack
     */
    public void push(int x) {
        if (isFull()) {
            System.out.println("Stack Overflow!");
            return;
        }
        arr[++top] = x;
    }
    
    /**
     * Pop element from stack
     */
    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack Underflow!");
            return -1;
        }
        return arr[top--];
    }
    
    /**
     * Peek at top element
     */
    public int peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return -1;
        }
        return arr[top];
    }
    
    /**
     * Print all elements in stack
     */
    public void print() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return;
        }
        System.out.print("Stack: ");
        for (int i = 0; i <= top; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        System.out.println("=== Stack Implementation using Array ===\n");
        
        StackArray stack = new StackArray(5);
        
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
        
        System.out.println("2. Peek at top element: " + stack.peek());
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
        
        System.out.println("5. Check if full: " + stack.isFull());
        System.out.println("Check if empty: " + stack.isEmpty());
        System.out.println();
        
        System.out.println("6. Pop all elements:");
        while (!stack.isEmpty()) {
            System.out.println("Popped: " + stack.pop());
        }
        stack.print();
        System.out.println();
        
        System.out.println("7. Try pop from empty stack:");
        stack.pop();
        System.out.println();
        
        System.out.println("8. Try push to full stack:");
        StackArray fullStack = new StackArray(3);
        fullStack.push(1);
        fullStack.push(2);
        fullStack.push(3);
        fullStack.print();
        fullStack.push(4);  // This will show overflow
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("COMPLEXITY ANALYSIS:");
        System.out.println("-".repeat(50));
        System.out.println("push():     O(1)");
        System.out.println("pop():      O(1)");
        System.out.println("peek():     O(1)");
        System.out.println("isEmpty():  O(1)");
        System.out.println("size():     O(1)");
        System.out.println("\nSpace Complexity: O(n)");
    }
}
