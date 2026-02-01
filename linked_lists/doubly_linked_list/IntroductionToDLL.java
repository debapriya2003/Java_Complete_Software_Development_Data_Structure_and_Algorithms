package linked_lists.doubly_linked_list;

public class IntroductionToDLL {

    /*
    =====================================================================================
    PROBLEM: INTRODUCTION TO DOUBLY LINKED LIST - NODE STRUCTURE AND REPRESENTATION
    -------------------------------------------------------------------------------------
    A Doubly Linked List (DLL) extends the singly LinkedList with bidirectional traversal.
    Each node contains: data (value), next pointer (to next node), and previous pointer 
    (to previous node). This allows traversal in both directions: forward and backward. 
    The first node (head) has no previous, the last node (tail) has no next. DLLs enable 
    efficient insertion/deletion at both ends and bidirectional search. Memory overhead 
    increases compared to singly LL due to extra pointer. DLLs are useful when we need 
    to traverse backwards (like browser history, undo/redo operations, LRU cache). Unlike 
    singly LL, finding previous node doesn't require traversal - it's directly accessible.

    Time Complexity: Understanding bidirectional structure
    Space Complexity: O(n) with extra pointer per node

    Example:
    DLL: null <- 10 <-> 20 <-> 30 <-> null
    Head = 10, Tail = 30
    =====================================================================================
    */
    
    /**
     * Node class for Doubly Linked List.
     * Contains data, next pointer (to next node), and prev pointer (to previous node).
     * Allows bidirectional traversal and direct access to predecessor.
     * 
     * @param <T> generic type for node data
     */
    public static class Node<T> {
        public T data;
        public Node<T> next;
        public Node<T> prev;
        
        /**
         * Constructor to create a node with given data.
         * Initially both next and prev pointers are null.
         * 
         * @param data value to store in node
         */
        public Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
        
        /**
         * Constructor to create node with data and linking.
         * 
         * @param data value to store
         * @param prev reference to previous node
         * @param next reference to next node
         */
        public Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
        
        /**
         * Returns string representation of node data.
         * 
         * @return string representation of data
         */
        @Override
        public String toString() {
            return data.toString();
        }
    }
    
    /**
     * Doubly LinkedList class to manage DLL operations.
     * Maintains references to both head and tail for efficient operations.
     * Supports traversal in both directions.
     * 
     * @param <T> generic type for list elements
     */
    public static class DoublyLinkedList<T> {
        public Node<T> head;
        public Node<T> tail;
        
        /**
         * Constructor creates empty DLL (both head and tail null).
         */
        public DoublyLinkedList() {
            this.head = null;
            this.tail = null;
        }
        
        /**
         * Creates DLL with single initial node.
         * Both head and tail point to same node initially.
         * 
         * @param data data for first node
         */
        public DoublyLinkedList(T data) {
            Node<T> node = new Node<>(data);
            this.head = node;
            this.tail = node;
        }
        
        /**
         * Displays the DLL in forward direction (head to tail).
         * Shows: null <- data1 <-> data2 <-> data3 -> null
         */
        public void displayForward() {
            Node<T> temp = head;
            System.out.print("Forward:  null <- ");
            
            while (temp != null) {
                System.out.print(temp.data);
                if (temp.next != null) {
                    System.out.print(" <-> ");
                }
                temp = temp.next;
            }
            
            System.out.println(" -> null");
        }
        
        /**
         * Displays the DLL in backward direction (tail to head).
         * Demonstrates reverse traversal capability of DLL.
         */
        public void displayBackward() {
            Node<T> temp = tail;
            System.out.print("Backward: null -> ");
            
            while (temp != null) {
                System.out.print(temp.data);
                if (temp.prev != null) {
                    System.out.print(" <-> ");
                }
                temp = temp.prev;
            }
            
            System.out.println(" <- null");
        }
        
        /**
         * Displays DLL with detailed pointer information.
         * Shows prev pointer, data, and next pointer for each node.
         */
        public void displayDetailed() {
            Node<T> temp = head;
            int position = 0;
            
            while (temp != null) {
                T prevData = temp.prev != null ? temp.prev.data : null;
                T nextData = temp.next != null ? temp.next.data : null;
                
                System.out.println("Position " + position + ": prev=" + prevData + 
                    ", data=" + temp.data + ", next=" + nextData);
                
                temp = temp.next;
                position++;
            }
        }
    }
    
    /**
     * Demonstrates DLL node structure and connections.
     * Shows how prev and next pointers create bidirectional links.
     */
    public static void demonstrateNodeStructure() {
        System.out.println("=== DLL NODE STRUCTURE DEMONSTRATION ===\n");
        
        // Create individual nodes
        Node<Integer> node1 = new Node<>(10);
        Node<Integer> node2 = new Node<>(20);
        Node<Integer> node3 = new Node<>(30);
        
        System.out.println("Created three independent nodes:");
        System.out.println("node1: prev=" + node1.prev + ", data=" + node1.data + ", next=" + node1.next);
        System.out.println("node2: prev=" + node2.prev + ", data=" + node2.data + ", next=" + node2.next);
        System.out.println("node3: prev=" + node3.prev + ", data=" + node3.data + ", next=" + node3.next);
        
        // Link nodes bidirectionally
        node1.next = node2;
        node2.prev = node1;
        
        node2.next = node3;
        node3.prev = node2;
        
        System.out.println("\nAfter bidirectional linking:");
        System.out.println("Structure: null <- 10 <-> 20 <-> 30 -> null");
        
        // Forward traversal from node1
        System.out.println("\nForward traversal from node1:");
        Node<Integer> temp = node1;
        while (temp != null) {
            System.out.print(temp.data);
            if (temp.next != null) {
                System.out.print(" <-> ");
            }
            temp = temp.next;
        }
        System.out.println();
        
        // Backward traversal from node3
        System.out.println("\nBackward traversal from node3:");
        temp = node3;
        while (temp != null) {
            System.out.print(temp.data);
            if (temp.prev != null) {
                System.out.print(" <-> ");
            }
            temp = temp.prev;
        }
        System.out.println();
    }
    
    /**
     * Explains advantages and disadvantages of DLL vs Singly LL.
     */
    public static void compareWithSinglyLL() {
        System.out.println("\n=== DOUBLY LL vs SINGLY LL ===\n");
        
        System.out.println("ADVANTAGES of Doubly LL:");
        System.out.println("1. Bidirectional traversal - can go forward and backward");
        System.out.println("2. Direct access to previous node - no need to traverse");
        System.out.println("3. Efficient deletion - O(1) if node reference known");
        System.out.println("4. Easier implementation of certain algorithms");
        System.out.println("5. Better for operations requiring backward navigation");
        
        System.out.println("\nDISADVANTAGES of Doubly LL:");
        System.out.println("1. Extra memory for prev pointer - ~2x pointer overhead");
        System.out.println("2. More complex pointer manipulation");
        System.out.println("3. More pointers to update during insertion/deletion");
        System.out.println("4. Slightly slower for simple forward-only traversal");
    }
    
    /**
     * Explains use cases where DLL is preferred over Singly LL.
     */
    public static void explainUseCases() {
        System.out.println("\n=== DLL USE CASES ===\n");
        
        System.out.println("1. Browser History: Navigate forward and backward");
        System.out.println("2. Undo/Redo: Efficiently move between states");
        System.out.println("3. LRU Cache: Remove least recently used (access prev/next efficiently)");
        System.out.println("4. Music Playlist: Play next and previous songs");
        System.out.println("5. Text Editor: Navigate and edit in both directions");
        System.out.println("6. Windows/Tabs: Switch between opened windows");
    }
    
    /**
     * Helper function to create DLL from array.
     * 
     * @param arr array of integers
     * @return head of created DLL
     */
    public static Node<Integer> createFromArray(int[] arr) {
        if (arr.length == 0) {
            return null;
        }
        
        Node<Integer> head = new Node<>(arr[0]);
        Node<Integer> temp = head;
        
        for (int i = 1; i < arr.length; i++) {
            Node<Integer> newNode = new Node<>(arr[i]);
            temp.next = newNode;
            newNode.prev = temp;
            temp = newNode;
        }
        
        return head;
    }
    
    /**
     * Helper to get tail of DLL from head.
     * 
     * @param head head of DLL
     * @return tail node
     */
    public static Node<Integer> getTail(Node<Integer> head) {
        Node<Integer> temp = head;
        
        while (temp != null && temp.next != null) {
            temp = temp.next;
        }
        
        return temp;
    }
    
    /**
     * Helper to count nodes in DLL.
     * 
     * @param head head of DLL
     * @return number of nodes
     */
    public static int getLength(Node<Integer> head) {
        int count = 0;
        Node<Integer> temp = head;
        
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        
        return count;
    }
    
    public static void main(String[] args) {
        // Demonstrate node structure
        demonstrateNodeStructure();
        
        // Compare with singly
        compareWithSinglyLL();
        
        // Use cases
        explainUseCases();
        
        // Create DLL using class
        System.out.println("\n=== DLL CLASS DEMONSTRATION ===\n");
        
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>(10);
        
        Node<Integer> node2 = new Node<>(20);
        list.head.next = node2;
        node2.prev = list.head;
        
        Node<Integer> node3 = new Node<>(30);
        node2.next = node3;
        node3.prev = node2;
        
        list.tail = node3;
        
        System.out.println("Created Doubly Linked List:");
        list.displayForward();
        list.displayBackward();
        
        System.out.println("\nDetailed view:");
        list.displayDetailed();
        
        System.out.println("\nList length: " + getLength(list.head));
        
        // Create DLL from array
        System.out.println("\n=== DLL FROM ARRAY ===\n");
        Node<Integer> head = createFromArray(new int[]{1, 2, 3, 4, 5});
        Node<Integer> tail = getTail(head);
        
        System.out.println("Created DLL from array [1, 2, 3, 4, 5]:");
        System.out.print("Forward:  null <- ");
        Node<Integer> temp = head;
        while (temp != null) {
            System.out.print(temp.data + (temp.next != null ? " <-> " : ""));
            temp = temp.next;
        }
        System.out.println(" -> null");
        
        System.out.print("Backward: null -> ");
        temp = tail;
        while (temp != null) {
            System.out.print(temp.data + (temp.prev != null ? " <-> " : ""));
            temp = temp.prev;
        }
        System.out.println(" <- null");
    }
}
