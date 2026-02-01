package linked_lists.singly_linked_list;

public class IntroductionToLinkedList {

    /*
    =====================================================================================
    PROBLEM: INTRODUCTION TO LINKEDLIST - NODE STRUCTURE AND REPRESENTATION
    -------------------------------------------------------------------------------------
    A LinkedList is a linear data structure where elements (nodes) are linked together 
    using pointers/references instead of contiguous memory like arrays. Each node contains:
    1. Data (value) - the actual information stored
    2. Next pointer - reference to the next node in the list
    The first node is called head, and the last node's next pointer is null. LinkedLists 
    are dynamic, allowing efficient insertion/deletion at known positions. Unlike arrays, 
    they don't support random access but offer better insertion/deletion performance. 
    This foundational concept is crucial for understanding more complex data structures.

    Time Complexity: Understanding traversal fundamentals
    Space Complexity: O(1) for understanding structure

    Example:
    LinkedList: 10 -> 20 -> 30 -> null
    Each arrow represents a node's next pointer
    =====================================================================================
    */
    
    /**
     * Node class represents a single element in the LinkedList.
     * Contains data (generic type T) and reference to next node.
     * This is the fundamental building block of a LinkedList.
     * Supports any data type through generics.
     * 
     * @param <T> generic type for node data
     */
    public static class Node<T> {
        public T data;
        public Node<T> next;
        
        /**
         * Constructor to create a node with given data.
         * Initially, next pointer is null (not linked to anything).
         * 
         * @param data value to store in node
         */
        public Node(T data) {
            this.data = data;
            this.next = null;
        }
        
        /**
         * Constructor to create node with data and next pointer.
         * Used when creating nodes that are already linked.
         * 
         * @param data value to store
         * @param next reference to next node
         */
        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }
        
        /**
         * Returns string representation of node data.
         * Useful for printing and debugging.
         * 
         * @return string representation of data
         */
        @Override
        public String toString() {
            return data.toString();
        }
    }
    
    /**
     * Simple LinkedList class to manage nodes and operations.
     * Maintains reference to head node only (entire list is reachable from head).
     * Provides basic operations on LinkedList.
     * 
     * @param <T> generic type for list elements
     */
    public static class LinkedList<T> {
        public Node<T> head;
        
        /**
         * Constructor creates empty LinkedList (head = null).
         */
        public LinkedList() {
            this.head = null;
        }
        
        /**
         * Creates LinkedList with initial node.
         * 
         * @param data data for first node
         */
        public LinkedList(T data) {
            this.head = new Node<>(data);
        }
        
        /**
         * Displays the entire LinkedList from head to null.
         * Traverses through each node using next pointer.
         * Shows structure: data1 -> data2 -> data3 -> null
         */
        public void display() {
            Node<T> temp = head;
            System.out.print("LinkedList: ");
            
            while (temp != null) {
                System.out.print(temp.data + " -> ");
                temp = temp.next;
            }
            
            System.out.println("null");
        }
        
        /**
         * Alternative display format showing more details.
         * Shows node addresses and structure information.
         */
        public void displayDetailed() {
            Node<T> temp = head;
            int position = 0;
            
            while (temp != null) {
                System.out.println("Position " + position + ": Data = " + temp.data + 
                    ", Next = " + (temp.next == null ? "null" : temp.next.data));
                temp = temp.next;
                position++;
            }
        }
    }
    
    /**
     * Demonstrates LinkedList node structure and basic operations.
     * Shows how nodes are created and linked together manually.
     */
    public static void demonstrateNodeStructure() {
        System.out.println("=== NODE STRUCTURE DEMONSTRATION ===\n");
        
        // Create individual nodes
        Node<Integer> node1 = new Node<>(10);
        Node<Integer> node2 = new Node<>(20);
        Node<Integer> node3 = new Node<>(30);
        
        System.out.println("Created three independent nodes:");
        System.out.println("node1: data=" + node1.data + ", next=" + node1.next);
        System.out.println("node2: data=" + node2.data + ", next=" + node2.next);
        System.out.println("node3: data=" + node3.data + ", next=" + node3.next);
        
        // Link nodes together
        node1.next = node2;
        node2.next = node3;
        
        System.out.println("\nAfter linking:");
        System.out.println("node1 -> node2 -> node3 -> null");
        
        // Traverse and display
        System.out.println("\nTraversing from node1:");
        Node<Integer> temp = node1;
        while (temp != null) {
            System.out.print(temp.data);
            if (temp.next != null) {
                System.out.print(" -> ");
            }
            temp = temp.next;
        }
        System.out.println(" -> null");
    }
    
    /**
     * Explains memory layout and pointer concept.
     * Shows how LinkedList differs from array in memory.
     */
    public static void explainMemoryLayout() {
        System.out.println("\n=== MEMORY LAYOUT COMPARISON ===\n");
        
        System.out.println("ARRAY (Contiguous Memory):");
        System.out.println("Memory: [10][20][30]");
        System.out.println("Index:   0   1   2");
        System.out.println("Access: O(1) random access using index\n");
        
        System.out.println("LINKEDLIST (Scattered Memory):");
        System.out.println("Node(10)@1000 -> Node(20)@2000 -> Node(30)@3000 -> null");
        System.out.println("Pointers:    ^         ^              ^");
        System.out.println("Access: O(n) must traverse from head");
    }
    
    /**
     * Shows advantages and disadvantages of LinkedList.
     */
    public static void compareWithArray() {
        System.out.println("\n=== LINKEDLIST VS ARRAY ===\n");
        
        System.out.println("ADVANTAGES of LinkedList:");
        System.out.println("1. Dynamic size - can grow/shrink");
        System.out.println("2. Efficient insertion/deletion at known position - O(1)");
        System.out.println("3. No memory waste due to over-allocation");
        System.out.println("4. No need to shift elements");
        
        System.out.println("\nDISADVANTAGES of LinkedList:");
        System.out.println("1. No random access - must traverse");
        System.out.println("2. Extra memory for pointers");
        System.out.println("3. Cache unfriendly - scattered in memory");
        System.out.println("4. Insertion/deletion requires traversal");
    }
    
    /**
     * Helper function to get length of LinkedList by traversal.
     * 
     * @param head head of LinkedList
     * @return number of nodes
     */
    public static int getLength(Node<?> head) {
        int length = 0;
        Node<?> temp = head;
        
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        
        return length;
    }
    
    public static void main(String[] args) {
        // Demonstrate node structure
        demonstrateNodeStructure();
        
        // Explain memory
        explainMemoryLayout();
        
        // Compare with array
        compareWithArray();
        
        // Create LinkedList using class
        System.out.println("\n=== LINKEDLIST CLASS DEMONSTRATION ===\n");
        
        LinkedList<Integer> list = new LinkedList<>(10);
        list.head.next = new Node<>(20);
        list.head.next.next = new Node<>(30);
        
        System.out.println("Created LinkedList:");
        list.display();
        
        System.out.println("\nDetailed view:");
        list.displayDetailed();
        
        System.out.println("\nLength of list: " + getLength(list.head));
        
        // Create another list with strings
        LinkedList<String> stringList = new LinkedList<>("A");
        stringList.head.next = new Node<>("B");
        stringList.head.next.next = new Node<>("C");
        
        System.out.println("\nString LinkedList:");
        stringList.display();
    }
}
