package stacks_queues.intro;

/**
 * Implement Stack using LinkedList
 * 
 * Stack: LIFO (Last In First Out) data structure
 * 
 * LinkedList Implementation:
 * - Use singly linked list
 * - Maintain head pointer to top of stack
 * - Add/remove from beginning for O(1) operations
 * 
 * Operations:
 * 1. push(x): Insert at beginning - O(1)
 * 2. pop(): Remove from beginning - O(1)
 * 3. peek(): View element at beginning - O(1)
 * 4. isEmpty(): Check if head is null - O(1)
 * 5. size(): Traverse list - O(n) or maintain counter
 * 
 * Advantages over Array:
 * - Dynamic size (no overflow)
 * - No wasted space
 * - Better for unknown size
 * 
 * Time Complexity: O(1) for all operations except size()
 * Space Complexity: O(n) where n is number of elements
 */

public class StackLinkedList {
    
    /**
     * Node class for LinkedList
     */
    private class Node {
        int data;
        Node next;
        
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
    
    private Node head;
    private int size;
    
    /**
     * Constructor
     */
    public StackLinkedList() {
        this.head = null;
        this.size = 0;
    }
    
    /**
     * Check if stack is empty
     */
    public boolean isEmpty() {
        return head == null;
    }
    
    /**
     * Get size of stack
     */
    public int getSize() {
        return size;
    }
    
    /**
     * Push element onto stack
     * Time: O(1)
     */
    public void push(int x) {
        Node newNode = new Node(x);
        newNode.next = head;
        head = newNode;
        size++;
    }
    
    /**
     * Pop element from stack
     * Time: O(1)
     */
    public int pop() {
        if (isEmpty()) {
            System.out.println("Stack Underflow!");
            return -1;
        }
        int value = head.data;
        head = head.next;
        size--;
        return value;
    }
    
    /**
     * Peek at top element
     * Time: O(1)
     */
    public int peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty!");
            return -1;
        }
        return head.data;
    }
    
    /**
     * Print all elements in stack
     */
    public void print() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return;
        }
        System.out.print("Stack (top to bottom): ");
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        System.out.println("=== Stack Implementation using LinkedList ===\n");
        
        StackLinkedList stack = new StackLinkedList();
        
        System.out.println("Operations:");
        System.out.println("-".repeat(50));
        
        System.out.println("1. Push 10, 20, 30, 40, 50");
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);
        stack.push(50);
        stack.print();
        System.out.println("Size: " + stack.getSize());
        System.out.println();
        
        System.out.println("2. Peek at top element: " + stack.peek());
        System.out.println();
        
        System.out.println("3. Pop elements:");
        System.out.println("Popped: " + stack.pop());
        stack.print();
        System.out.println("Popped: " + stack.pop());
        stack.print();
        System.out.println();
        
        System.out.println("4. Push 60, 70, 80");
        stack.push(60);
        stack.push(70);
        stack.push(80);
        stack.print();
        System.out.println("Size: " + stack.getSize());
        System.out.println();
        
        System.out.println("5. Check if empty: " + stack.isEmpty());
        System.out.println();
        
        System.out.println("6. Pop all elements:");
        while (!stack.isEmpty()) {
            System.out.println("Popped: " + stack.pop());
        }
        stack.print();
        System.out.println("Size: " + stack.getSize());
        System.out.println();
        
        System.out.println("7. Try pop from empty stack:");
        stack.pop();
        System.out.println("Try peek on empty stack:");
        stack.peek();
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("LINKEDLIST VISUALIZATION:");
        System.out.println("-".repeat(50));
        System.out.println("After push(50), push(40), push(30), push(20), push(10):");
        System.out.println("Head -> [50|next] -> [40|next] -> [30|next] -> [20|next] -> [10|null]");
        System.out.println("         (top)");
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("COMPLEXITY ANALYSIS:");
        System.out.println("-".repeat(50));
        System.out.println("push():     O(1) - Insert at head");
        System.out.println("pop():      O(1) - Remove from head");
        System.out.println("peek():     O(1) - Access head");
        System.out.println("isEmpty():  O(1) - Check if head is null");
        System.out.println("size():     O(1) - With counter (O(n) without)");
        System.out.println("\nSpace Complexity: O(n)");
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("LINKEDLIST vs ARRAY:");
        System.out.println("-".repeat(50));
        System.out.println("LinkedList Advantages:");
        System.out.println("  1. Dynamic size - no overflow issues");
        System.out.println("  2. No wasted space");
        System.out.println("  3. Better for unknown size");
        System.out.println("\nLinkedList Disadvantages:");
        System.out.println("  1. Extra memory for next pointer");
        System.out.println("  2. Cache unfriendly");
        System.out.println("  3. Size() is O(n) without counter");
    }
}
