package linked_lists.singly_linked_list;

public class InsertingNodeInLinkedList {

    /*
    =====================================================================================
    PROBLEM: INSERTING A NODE IN LINKEDLIST
    -------------------------------------------------------------------------------------
    Insertion is one of the fundamental operations on LinkedList. We need to insert a 
    new node at different positions: beginning (before head), middle (between nodes), or 
    end (after last node). Each operation has different time complexity. Insertion at 
    beginning is O(1), at middle/end requires O(n) traversal to find position. The key 
    is understanding how to update pointers correctly to maintain list integrity. We must 
    update: new node's next pointer and the previous node's next pointer to point to new 
    node. Always handle edge cases like empty list, inserting at head, inserting beyond list.

    Time Complexity: O(1) at beginning, O(n) at middle/end
    Space Complexity: O(1)

    Example:
    Insert 15 at beginning: 10->20->30 becomes 15->10->20->30
    Insert 25 at position 2: 10->20->30 becomes 10->20->25->30
    =====================================================================================
    */
    
    /**
     * Node class for LinkedList (same as Introduction).
     */
    public static class Node {
        public int data;
        public Node next;
        
        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
    
    /**
     * Inserts a new node at the beginning of LinkedList.
     * The new node becomes the head. O(1) operation.
     * Simply update new node's next to point to old head,
     * and return new node as new head.
     * 
     * @param head current head of LinkedList
     * @param data data for new node
     * @return new head of LinkedList
     */
    public static Node insertAtBeginning(Node head, int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        return newNode;  // New node becomes head
    }
    
    /**
     * Inserts a new node at the end of LinkedList.
     * Requires traversal to find last node. O(n) operation.
     * If list is empty, new node becomes head.
     * Otherwise, find last node (whose next is null) and
     * update its next to point to new node.
     * 
     * @param head current head of LinkedList
     * @param data data for new node
     * @return head of LinkedList (unchanged if list not empty)
     */
    public static Node insertAtEnd(Node head, int data) {
        Node newNode = new Node(data);
        
        // If list is empty, new node becomes head
        if (head == null) {
            return newNode;
        }
        
        // Traverse to find last node
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        
        // Link last node to new node
        temp.next = newNode;
        
        return head;  // Head remains unchanged
    }
    
    /**
     * Inserts a new node at a specific position in LinkedList.
     * Position is 0-indexed. Position 0 = before head,
     * Position n = after nth node. O(n) operation.
     * Handles edge cases: empty list, position 0, position >= length.
     * 
     * @param head current head of LinkedList
     * @param data data for new node
     * @param position 0-indexed position to insert
     * @return new head of LinkedList
     */
    public static Node insertAtPosition(Node head, int data, int position) {
        Node newNode = new Node(data);
        
        // Insert at beginning (position 0)
        if (position == 0) {
            newNode.next = head;
            return newNode;
        }
        
        // Find node at position-1
        Node temp = head;
        int count = 0;
        
        while (temp != null && count < position - 1) {
            temp = temp.next;
            count++;
        }
        
        // If position is beyond list length or temp is null
        if (temp == null) {
            System.out.println("Position out of bounds. Inserting at end.");
            return insertAtEnd(head, data);
        }
        
        // Insert new node between temp and temp.next
        newNode.next = temp.next;
        temp.next = newNode;
        
        return head;
    }
    
    /**
     * Inserts a new node after a specific node value.
     * Searches for node with given value and inserts after it.
     * If value not found, option to insert at end or at beginning.
     * 
     * @param head current head of LinkedList
     * @param value data of node after which to insert
     * @param newData data for new node
     * @return head of LinkedList
     */
    public static Node insertAfterValue(Node head, int value, int newData) {
        Node newNode = new Node(newData);
        
        // Find node with given value
        Node temp = head;
        while (temp != null && temp.data != value) {
            temp = temp.next;
        }
        
        // If not found, print message
        if (temp == null) {
            System.out.println("Value " + value + " not found in list.");
            return head;
        }
        
        // Insert new node after found node
        newNode.next = temp.next;
        temp.next = newNode;
        
        return head;
    }
    
    /**
     * Inserts sorted new node in a sorted LinkedList.
     * Maintains sorted order while inserting.
     * Useful for maintaining sorted lists with insertions.
     * 
     * @param head current head of sorted LinkedList
     * @param data data for new node
     * @return new head of LinkedList
     */
    public static Node insertSorted(Node head, int data) {
        Node newNode = new Node(data);
        
        // If list is empty or new data is smaller than head
        if (head == null || head.data >= data) {
            newNode.next = head;
            return newNode;
        }
        
        // Find correct position to insert
        Node temp = head;
        while (temp.next != null && temp.next.data < data) {
            temp = temp.next;
        }
        
        // Insert new node
        newNode.next = temp.next;
        temp.next = newNode;
        
        return head;
    }
    
    /**
     * Helper function to display LinkedList.
     * 
     * @param head head of LinkedList
     */
    public static void display(Node head) {
        System.out.print("LinkedList: ");
        Node temp = head;
        
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        
        System.out.println("null");
    }
    
    /**
     * Helper function to count number of nodes in LinkedList.
     * 
     * @param head head of LinkedList
     * @return length of list
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
    
    /**
     * Helper function to create LinkedList from array.
     * Useful for testing various insertion operations.
     * 
     * @param arr array of integers
     * @return head of created LinkedList
     */
    public static Node createFromArray(int[] arr) {
        if (arr.length == 0) {
            return null;
        }
        
        Node head = new Node(arr[0]);
        Node temp = head;
        
        for (int i = 1; i < arr.length; i++) {
            temp.next = new Node(arr[i]);
            temp = temp.next;
        }
        
        return head;
    }
    
    public static void main(String[] args) {
        System.out.println("=== INSERT AT BEGINNING ===");
        Node head = null;
        head = insertAtBeginning(head, 30);
        display(head);
        
        head = insertAtBeginning(head, 20);
        display(head);
        
        head = insertAtBeginning(head, 10);
        display(head);
        
        System.out.println("\n=== INSERT AT END ===");
        head = new Node(10);
        head.next = new Node(20);
        display(head);
        
        head = insertAtEnd(head, 30);
        display(head);
        
        head = insertAtEnd(head, 40);
        display(head);
        
        System.out.println("\n=== INSERT AT POSITION ===");
        head = createFromArray(new int[]{10, 20, 30, 40});
        display(head);
        
        head = insertAtPosition(head, 15, 1);
        display(head);
        
        head = insertAtPosition(head, 25, 3);
        display(head);
        
        System.out.println("\n=== INSERT AFTER VALUE ===");
        head = createFromArray(new int[]{10, 20, 30});
        display(head);
        
        head = insertAfterValue(head, 20, 25);
        display(head);
        
        System.out.println("\n=== INSERT SORTED ===");
        head = null;
        int[] unsortedData = {5, 2, 8, 1, 9};
        for (int data : unsortedData) {
            head = insertSorted(head, data);
        }
        display(head);
        
        System.out.println("\n=== LENGTH AFTER OPERATIONS ===");
        System.out.println("List length: " + getLength(head));
    }
}
