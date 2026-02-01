package stacks_queues.intro;

/**
 * Implement Queue using Arrays
 * 
 * Queue: FIFO (First In First Out) data structure
 * 
 * Operations:
 * 1. enqueue(x): Add element to rear - O(1)
 * 2. dequeue(): Remove and return front element - O(1) amortized
 * 3. front(): View front element - O(1)
 * 4. rear(): View rear element - O(1)
 * 5. isEmpty(): Check if queue is empty - O(1)
 * 6. size(): Return number of elements - O(1)
 * 
 * Array Implementation (Circular Queue):
 * - Use array with front and rear pointers
 * - Implement as circular to reuse space
 * - Initially front = 0, rear = -1
 * 
 * Problems with Linear Queue:
 * - After dequeue, front pointer moves, wasting space
 * - Solution: Use circular queue
 * 
 * Circular Queue Benefits:
 * - Reuses empty space at beginning
 * - More efficient space utilization
 * 
 * Time Complexity: O(1) for all operations
 * Space Complexity: O(n) where n is capacity
 */

public class QueueArray {
    private int[] arr;
    private int front;
    private int rear;
    private int size;
    private int capacity;
    
    /**
     * Constructor to initialize queue with given capacity
     */
    public QueueArray(int capacity) {
        this.capacity = capacity;
        this.arr = new int[capacity];
        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }
    
    /**
     * Check if queue is empty
     */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * Check if queue is full
     */
    public boolean isFull() {
        return size == capacity;
    }
    
    /**
     * Get current size of queue
     */
    public int getSize() {
        return size;
    }
    
    /**
     * Add element to rear of queue
     */
    public void enqueue(int x) {
        if (isFull()) {
            System.out.println("Queue Overflow!");
            return;
        }
        rear = (rear + 1) % capacity;  // Circular increment
        arr[rear] = x;
        size++;
    }
    
    /**
     * Remove and return element from front of queue
     */
    public int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue Underflow!");
            return -1;
        }
        int element = arr[front];
        front = (front + 1) % capacity;  // Circular increment
        size--;
        return element;
    }
    
    /**
     * View front element
     */
    public int front() {
        if (isEmpty()) {
            System.out.println("Queue is empty!");
            return -1;
        }
        return arr[front];
    }
    
    /**
     * View rear element
     */
    public int rear_element() {
        if (isEmpty()) {
            System.out.println("Queue is empty!");
            return -1;
        }
        return arr[rear];
    }
    
    /**
     * Print all elements in queue
     */
    public void print() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }
        System.out.print("Queue: ");
        int count = 0;
        int i = front;
        while (count < size) {
            System.out.print(arr[i] + " ");
            i = (i + 1) % capacity;
            count++;
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        System.out.println("=== Queue Implementation using Array (Circular) ===\n");
        
        QueueArray queue = new QueueArray(5);
        
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
        System.out.println("Rear: " + queue.rear_element());
        System.out.println();
        
        System.out.println("3. Dequeue 2 elements:");
        System.out.println("Dequeued: " + queue.dequeue());
        System.out.println("Dequeued: " + queue.dequeue());
        queue.print();
        System.out.println("Size: " + queue.getSize());
        System.out.println();
        
        System.out.println("4. Enqueue 60, 70 (demonstrates circular nature):");
        queue.enqueue(60);
        queue.enqueue(70);
        queue.print();
        System.out.println("Size: " + queue.getSize());
        System.out.println();
        
        System.out.println("5. Check if full: " + queue.isFull());
        System.out.println("Check if empty: " + queue.isEmpty());
        System.out.println();
        
        System.out.println("6. Dequeue all elements:");
        while (!queue.isEmpty()) {
            System.out.println("Dequeued: " + queue.dequeue());
        }
        queue.print();
        System.out.println();
        
        System.out.println("7. Try dequeue from empty queue:");
        queue.dequeue();
        System.out.println();
        
        System.out.println("8. Try enqueue to full queue:");
        QueueArray fullQueue = new QueueArray(3);
        fullQueue.enqueue(1);
        fullQueue.enqueue(2);
        fullQueue.enqueue(3);
        fullQueue.print();
        fullQueue.enqueue(4);  // This will show overflow
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("CIRCULAR QUEUE VISUALIZATION:");
        System.out.println("-".repeat(50));
        System.out.println("Capacity: 5");
        System.out.println("After enqueue: [10, 20, 30, 40, 50]");
        System.out.println("After dequeue 2: [_, _, 30, 40, 50]");
        System.out.println("After enqueue 60, 70: [70, 60, 30, 40, 50] (circular)");
        System.out.println("Index:            [0,  1,  2,  3,  4]");
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("COMPLEXITY ANALYSIS:");
        System.out.println("-".repeat(50));
        System.out.println("enqueue():  O(1)");
        System.out.println("dequeue():  O(1)");
        System.out.println("front():    O(1)");
        System.out.println("rear():     O(1)");
        System.out.println("isEmpty():  O(1)");
        System.out.println("size():     O(1)");
        System.out.println("\nSpace Complexity: O(n)");
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ADVANTAGES OF CIRCULAR QUEUE:");
        System.out.println("-".repeat(50));
        System.out.println("1. Reuses wasted space from dequeue operations");
        System.out.println("2. Better memory utilization");
        System.out.println("3. Efficient for fixed-size queue implementations");
    }
}
