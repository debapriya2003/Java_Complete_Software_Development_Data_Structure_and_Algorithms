package stacks_queues.intro;

/**
 * Implement Queue using LinkedList
 * 
 * Queue: FIFO (First In First Out) data structure
 * 
 * LinkedList Implementation:
 * - Use singly linked list
 * - Maintain head pointer (for dequeue)
 * - Maintain tail pointer (for enqueue)
 * - Add at tail, remove from head
 * 
 * Operations:
 * 1. enqueue(x): Insert at tail - O(1)
 * 2. dequeue(): Remove from head - O(1)
 * 3. front(): View head element - O(1)
 * 4. rear(): View tail element - O(1)
 * 5. isEmpty(): Check if head is null - O(1)
 * 6. size(): Traverse list or maintain counter - O(1) with counter
 * 
 * Advantages:
 * - Dynamic size (no overflow)
 * - No wasted space (unlike circular queue)
 * - Clean and simple implementation
 * 
 * Time Complexity: O(1) for all operations
 * Space Complexity: O(n) where n is number of elements
 */

public class QueueLinkedList {
    
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
    private Node tail;
    private int size;
    
    /**
     * Constructor
     */
    public QueueLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }
    
    /**
     * Check if queue is empty
     */
    public boolean isEmpty() {
        return head == null;
    }
    
    /**
     * Get size of queue
     */
    public int getSize() {
        return size;
    }
    
    /**
     * Add element to rear of queue
     * Time: O(1)
     */
    public void enqueue(int x) {
        Node newNode = new Node(x);
        
        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }
    
    /**
     * Remove and return element from front of queue
     * Time: O(1)
     */
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue Underflow!");
            return -1;
        }
        
        int value = head.data;
        head = head.next;
        size--;
        
        if (isEmpty()) {
            tail = null;
        }
        
        return value;
    }
    
    /**
     * View front element
     * Time: O(1)
     */
    public int front() {
        if (isEmpty()) {
            System.out.println("Queue is empty!");
            return -1;
        }
        return head.data;
    }
    
    /**
     * View rear element
     * Time: O(1)
     */
    public int rear() {
        if (isEmpty()) {
            System.out.println("Queue is empty!");
            return -1;
        }
        return tail.data;
    }
    
    /**
     * Print all elements in queue
     */
    public void print() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }
        System.out.print("Queue (front to rear): ");
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        System.out.println("=== Queue Implementation using LinkedList ===\n");
        
        QueueLinkedList queue = new QueueLinkedList();
        
        System.out.println("Operations:");
        System.out.println("-".repeat(50));
        
        System.out.println("1. Enqueue 10, 20, 30, 40, 50");
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);
        queue.enqueue(40);
        queue.enqueue(50);
        queue.print();
        System.out.println("Size: " + queue.getSize());
        System.out.println();
        
        System.out.println("2. Check front and rear:");
        System.out.println("Front: " + queue.front());
        System.out.println("Rear: " + queue.rear());
        System.out.println();
        
        System.out.println("3. Dequeue 2 elements:");
        System.out.println("Dequeued: " + queue.dequeue());
        queue.print();
        System.out.println("Dequeued: " + queue.dequeue());
        queue.print();
        System.out.println("Size: " + queue.getSize());
        System.out.println();
        
        System.out.println("4. Enqueue 60, 70, 80");
        queue.enqueue(60);
        queue.enqueue(70);
        queue.enqueue(80);
        queue.print();
        System.out.println("Size: " + queue.getSize());
        System.out.println();
        
        System.out.println("5. Check front and rear:");
        System.out.println("Front: " + queue.front());
        System.out.println("Rear: " + queue.rear());
        System.out.println();
        
        System.out.println("6. Dequeue all elements:");
        while (!queue.isEmpty()) {
            System.out.println("Dequeued: " + queue.dequeue());
        }
        queue.print();
        System.out.println("Size: " + queue.getSize());
        System.out.println();
        
        System.out.println("7. Try dequeue from empty queue:");
        queue.dequeue();
        System.out.println("Try front on empty queue:");
        queue.front();
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("LINKEDLIST VISUALIZATION:");
        System.out.println("-".repeat(50));
        System.out.println("After enqueue(10), enqueue(20), enqueue(30):");
        System.out.println("head -> [10|next] -> [20|next] -> [30|null] <- tail");
        System.out.println("       (front)                      (rear)");
        
        System.out.println("\nAfter dequeue():");
        System.out.println("head -> [20|next] -> [30|null] <- tail");
        System.out.println("       (front)       (rear)");
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("COMPLEXITY ANALYSIS:");
        System.out.println("-".repeat(50));
        System.out.println("enqueue():  O(1) - Insert at tail");
        System.out.println("dequeue():  O(1) - Remove from head");
        System.out.println("front():    O(1) - Access head");
        System.out.println("rear():     O(1) - Access tail");
        System.out.println("isEmpty():  O(1) - Check if head is null");
        System.out.println("size():     O(1) - With counter");
        System.out.println("\nSpace Complexity: O(n)");
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("LINKEDLIST vs CIRCULAR ARRAY:");
        System.out.println("-".repeat(50));
        System.out.println("LinkedList Advantages:");
        System.out.println("  1. Dynamic size - no capacity limit");
        System.out.println("  2. No memory waste from fixed size");
        System.out.println("  3. Efficient for varying queue sizes");
        System.out.println("\nCircular Array Advantages:");
        System.out.println("  1. Cache friendly (contiguous memory)");
        System.out.println("  2. No extra memory for pointers");
        System.out.println("  3. Better for predictable, fixed sizes");
    }
}
