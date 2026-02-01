package linked_lists.doubly_linked_list;

public class InsertingNodeInDLL {

    /*
    =====================================================================================
    PROBLEM: INSERT A NODE IN DOUBLY LINKED LIST
    -------------------------------------------------------------------------------------
    Insertion in DLL requires updating both prev and next pointers, making it more 
    complex than singly LL. We need to insert at different positions: beginning (new 
    head), middle (between nodes), or end (new tail). Each position has specific pointer 
    updates needed. At beginning: new node's next = old head, old head's prev = new node. 
    At end: new node's prev = old tail, old tail's next = new node. In middle: update 4 
    pointers (new node's prev/next and surrounding nodes' pointers). Key challenge is 
    maintaining bidirectional consistency - every link must work both ways. Must handle 
    edge cases: empty list, inserting before head, after tail, and keeping head/tail updated.

    Time Complexity: O(1) at beginning/end, O(n) in middle
    Space Complexity: O(1)

    Example:
    Insert 15 at beginning: 20->30 becomes 15<->20->30
    Insert 25 at end: 20->30 becomes 20<->30<->25
    =====================================================================================
    */
    
    /**
     * Node class for Doubly Linked List.
     */
    public static class Node {
        public int data;
        public Node next;
        public Node prev;
        
        public Node(int data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }
    
    /**
     * Inserts a new node at the beginning of DLL.
     * New node becomes the head. O(1) operation.
     * Updates new node's next and old head's prev.
     * Handles empty list case.
     * 
     * @param head current head of DLL
     * @param data data for new node
     * @return new head of DLL
     */
    public static Node insertAtBeginning(Node head, int data) {
        Node newNode = new Node(data);
        
        // If list is empty
        if (head == null) {
            return newNode;
        }
        
        // Link new node to current head
        newNode.next = head;
        head.prev = newNode;
        
        return newNode;  // New node becomes head
    }
    
    /**
     * Inserts a new node at the end of DLL.
     * New node becomes the tail. O(n) operation as we traverse to find tail.
     * If list is empty, new node becomes head.
     * Otherwise, update tail's next and new node's prev.
     * 
     * @param head current head of DLL
     * @param data data for new node
     * @return head of DLL (unchanged if list not empty)
     */
    public static Node insertAtEnd(Node head, int data) {
        Node newNode = new Node(data);
        
        // If list is empty, new node becomes head
        if (head == null) {
            return newNode;
        }
        
        // Traverse to find tail
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        
        // Link tail to new node
        temp.next = newNode;
        newNode.prev = temp;
        
        return head;  // Head remains unchanged
    }
    
    /**
     * Inserts a new node at specific position in DLL.
     * Position is 0-indexed. Position 0 = before head.
     * Requires traversal to position. O(n) operation.
     * Updates all necessary pointers for bidirectional consistency.
     * 
     * @param head current head of DLL
     * @param data data for new node
     * @param position 0-indexed position
     * @return new head of DLL
     */
    public static Node insertAtPosition(Node head, int data, int position) {
        Node newNode = new Node(data);
        
        // Insert at beginning (position 0)
        if (position == 0) {
            newNode.next = head;
            if (head != null) {
                head.prev = newNode;
            }
            return newNode;
        }
        
        // Find node at position-1
        Node temp = head;
        int count = 0;
        
        while (temp != null && count < position - 1) {
            temp = temp.next;
            count++;
        }
        
        // If position is beyond list or temp is null
        if (temp == null) {
            System.out.println("Position out of bounds. Inserting at end.");
            return insertAtEnd(head, data);
        }
        
        // Insert new node between temp and temp.next
        newNode.next = temp.next;
        newNode.prev = temp;
        
        if (temp.next != null) {
            temp.next.prev = newNode;
        }
        
        temp.next = newNode;
        
        return head;
    }
    
    /**
     * Inserts a new node before a specific node value.
     * Searches for node with given value and inserts new node before it.
     * Maintains bidirectional pointers correctly.
     * 
     * @param head current head of DLL
     * @param value value of node before which to insert
     * @param newData data for new node
     * @return new head of DLL (may change if inserting before current head)
     */
    public static Node insertBeforeValue(Node head, int value, int newData) {
        Node newNode = new Node(newData);
        
        // If list is empty
        if (head == null) {
            return newNode;
        }
        
        // If inserting before head
        if (head.data == value) {
            newNode.next = head;
            head.prev = newNode;
            return newNode;
        }
        
        // Find node with given value
        Node temp = head;
        while (temp != null && temp.data != value) {
            temp = temp.next;
        }
        
        // If not found
        if (temp == null) {
            System.out.println("Value " + value + " not found in list.");
            return head;
        }
        
        // Insert new node before found node
        newNode.next = temp;
        newNode.prev = temp.prev;
        temp.prev.next = newNode;
        temp.prev = newNode;
        
        return head;
    }
    
    /**
     * Inserts a new node after a specific node value.
     * Searches for node with given value and inserts after it.
     * Updates all necessary pointers.
     * 
     * @param head current head of DLL
     * @param value value of node after which to insert
     * @param newData data for new node
     * @return head of DLL
     */
    public static Node insertAfterValue(Node head, int value, int newData) {
        Node newNode = new Node(newData);
        
        // Find node with given value
        Node temp = head;
        while (temp != null && temp.data != value) {
            temp = temp.next;
        }
        
        // If not found
        if (temp == null) {
            System.out.println("Value " + value + " not found in list.");
            return head;
        }
        
        // Insert new node after found node
        newNode.next = temp.next;
        newNode.prev = temp;
        
        if (temp.next != null) {
            temp.next.prev = newNode;
        }
        
        temp.next = newNode;
        
        return head;
    }
    
    /**
     * Inserts a new node in a sorted DLL (ascending order).
     * Maintains sorted property while inserting.
     * Useful for maintaining sorted doubly linked lists.
     * 
     * @param head current head of sorted DLL
     * @param data data for new node
     * @return new head of DLL
     */
    public static Node insertSorted(Node head, int data) {
        Node newNode = new Node(data);
        
        // If list is empty or new data < head
        if (head == null || head.data >= data) {
            newNode.next = head;
            if (head != null) {
                head.prev = newNode;
            }
            return newNode;
        }
        
        // Find correct position
        Node temp = head;
        while (temp.next != null && temp.next.data < data) {
            temp = temp.next;
        }
        
        // Insert new node
        newNode.next = temp.next;
        newNode.prev = temp;
        
        if (temp.next != null) {
            temp.next.prev = newNode;
        }
        
        temp.next = newNode;
        
        return head;
    }
    
    /**
     * Helper function to display DLL forward.
     * 
     * @param head head of DLL
     */
    public static void displayForward(Node head) {
        if (head == null) {
            System.out.println("DLL: null <- [] -> null");
            return;
        }
        
        System.out.print("Forward:  null <- ");
        Node temp = head;
        
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
     * Helper function to display DLL backward.
     * 
     * @param head head of DLL (used to find tail)
     */
    public static void displayBackward(Node head) {
        if (head == null) {
            System.out.println("Backward: null -> [] <- null");
            return;
        }
        
        // Find tail
        Node tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        
        System.out.print("Backward: null -> ");
        Node temp = tail;
        
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
     * Helper to create DLL from array.
     * 
     * @param arr array of integers
     * @return head of created DLL
     */
    public static Node createFromArray(int[] arr) {
        if (arr.length == 0) {
            return null;
        }
        
        Node head = new Node(arr[0]);
        Node temp = head;
        
        for (int i = 1; i < arr.length; i++) {
            Node newNode = new Node(arr[i]);
            temp.next = newNode;
            newNode.prev = temp;
            temp = newNode;
        }
        
        return head;
    }
    
    /**
     * Helper to get length of DLL.
     * 
     * @param head head of DLL
     * @return length
     */
    public static int getLength(Node head) {
        int count = 0;
        Node temp = head;
        
        while (temp != null) {
            count++;
            temp = temp.next;
        }
        
        return count;
    }
    
    public static void main(String[] args) {
        System.out.println("=== INSERT AT BEGINNING ===");
        Node head = null;
        head = insertAtBeginning(head, 30);
        displayForward(head);
        
        head = insertAtBeginning(head, 20);
        displayForward(head);
        displayBackward(head);
        
        head = insertAtBeginning(head, 10);
        displayForward(head);
        displayBackward(head);
        
        System.out.println("\n=== INSERT AT END ===");
        head = new Node(10);
        head.next = new Node(20);
        head.next.prev = head;
        displayForward(head);
        
        head = insertAtEnd(head, 30);
        displayForward(head);
        displayBackward(head);
        
        head = insertAtEnd(head, 40);
        displayForward(head);
        
        System.out.println("\n=== INSERT AT POSITION ===");
        head = createFromArray(new int[]{10, 20, 30, 40});
        displayForward(head);
        
        head = insertAtPosition(head, 15, 1);
        displayForward(head);
        displayBackward(head);
        
        System.out.println("\n=== INSERT BEFORE VALUE ===");
        head = createFromArray(new int[]{10, 20, 30});
        displayForward(head);
        
        head = insertBeforeValue(head, 20, 15);
        displayForward(head);
        displayBackward(head);
        
        System.out.println("\n=== INSERT AFTER VALUE ===");
        head = createFromArray(new int[]{10, 20, 30});
        displayForward(head);
        
        head = insertAfterValue(head, 20, 25);
        displayForward(head);
        displayBackward(head);
        
        System.out.println("\n=== INSERT SORTED ===");
        head = null;
        int[] unsortedData = {5, 2, 8, 1, 9};
        for (int data : unsortedData) {
            head = insertSorted(head, data);
        }
        displayForward(head);
        displayBackward(head);
        System.out.println("List length: " + getLength(head));
    }
}
