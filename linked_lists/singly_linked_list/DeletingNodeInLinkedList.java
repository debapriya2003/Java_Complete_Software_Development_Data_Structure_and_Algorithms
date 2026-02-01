package linked_lists.singly_linked_list;

public class DeletingNodeInLinkedList {

    /*
    =====================================================================================
    PROBLEM: DELETING A NODE IN LINKEDLIST
    -------------------------------------------------------------------------------------
    Deletion is the opposite of insertion in LinkedList. We need to remove nodes from 
    different positions: beginning (head), middle (between nodes), or end (last node). 
    Deletion at beginning is O(1) requiring only head update. Deletion at middle/end 
    requires O(n) traversal to find node and its predecessor. The key is updating 
    pointers correctly: the node before deletion point's next must skip the deleted node 
    and point to the node after it. Must handle edge cases: empty list, deleting only 
    node, deleting non-existent node. After deletion, freed memory is garbage collected.

    Time Complexity: O(1) at beginning, O(n) at middle/end
    Space Complexity: O(1)

    Example:
    Delete head: 10->20->30 becomes 20->30
    Delete node 20: 10->20->30 becomes 10->30
    Delete end: 10->20->30 becomes 10->20
    =====================================================================================
    */
    
    /**
     * Node class for LinkedList.
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
     * Deletes the first node (head) from LinkedList.
     * O(1) operation - simply return head.next as new head.
     * If list is empty, returns null.
     * 
     * @param head current head of LinkedList
     * @return new head of LinkedList (old head.next)
     */
    public static Node deleteAtBeginning(Node head) {
        if (head == null) {
            System.out.println("List is empty. Nothing to delete.");
            return null;
        }
        
        System.out.println("Deleted node with data: " + head.data);
        return head.next;  // New head is old head's next
    }
    
    /**
     * Deletes the last node from LinkedList.
     * Requires traversal to find second-to-last node. O(n) operation.
     * If list is empty or has only one node, handle appropriately.
     * 
     * @param head current head of LinkedList
     * @return head of LinkedList (unchanged if list has more nodes)
     */
    public static Node deleteAtEnd(Node head) {
        if (head == null) {
            System.out.println("List is empty. Nothing to delete.");
            return null;
        }
        
        // If list has only one node
        if (head.next == null) {
            System.out.println("Deleted node with data: " + head.data);
            return null;
        }
        
        // Find second-to-last node
        Node temp = head;
        while (temp.next.next != null) {
            temp = temp.next;
        }
        
        System.out.println("Deleted node with data: " + temp.next.data);
        temp.next = null;  // Remove last node
        
        return head;
    }
    
    /**
     * Deletes node at specific position from LinkedList.
     * Position is 0-indexed. O(n) operation.
     * Handles edge cases: position 0 (delete head), position beyond length.
     * 
     * @param head current head of LinkedList
     * @param position 0-indexed position to delete
     * @return new head of LinkedList
     */
    public static Node deleteAtPosition(Node head, int position) {
        if (head == null) {
            System.out.println("List is empty. Nothing to delete.");
            return null;
        }
        
        // Delete at beginning (position 0)
        if (position == 0) {
            System.out.println("Deleted node with data: " + head.data);
            return head.next;
        }
        
        // Find node at position-1
        Node temp = head;
        int count = 0;
        
        while (temp != null && count < position - 1) {
            temp = temp.next;
            count++;
        }
        
        // If position is beyond list or next node doesn't exist
        if (temp == null || temp.next == null) {
            System.out.println("Position out of bounds.");
            return head;
        }
        
        // Delete node at position
        System.out.println("Deleted node with data: " + temp.next.data);
        temp.next = temp.next.next;
        
        return head;
    }
    
    /**
     * Deletes node with specific value from LinkedList.
     * Searches for first occurrence of value and deletes it.
     * Returns head of LinkedList (which may change if head is deleted).
     * 
     * @param head current head of LinkedList
     * @param value data to search and delete
     * @return new head of LinkedList
     */
    public static Node deleteByValue(Node head, int value) {
        if (head == null) {
            System.out.println("List is empty.");
            return null;
        }
        
        // If head contains the value
        if (head.data == value) {
            System.out.println("Deleted node with data: " + head.data);
            return head.next;
        }
        
        // Find node with value
        Node temp = head;
        while (temp.next != null && temp.next.data != value) {
            temp = temp.next;
        }
        
        // If not found
        if (temp.next == null) {
            System.out.println("Value " + value + " not found in list.");
            return head;
        }
        
        // Delete node
        System.out.println("Deleted node with data: " + temp.next.data);
        temp.next = temp.next.next;
        
        return head;
    }
    
    /**
     * Deletes all nodes with specific value from LinkedList.
     * Removes all occurrences, not just first one.
     * 
     * @param head current head of LinkedList
     * @param value data to search and delete
     * @return new head of LinkedList
     */
    public static Node deleteAllByValue(Node head, int value) {
        // Delete from beginning while head has the value
        while (head != null && head.data == value) {
            System.out.println("Deleted node with data: " + head.data);
            head = head.next;
        }
        
        if (head == null) {
            return null;
        }
        
        // Delete from middle/end
        Node temp = head;
        while (temp.next != null) {
            if (temp.next.data == value) {
                System.out.println("Deleted node with data: " + temp.next.data);
                temp.next = temp.next.next;
            } else {
                temp = temp.next;
            }
        }
        
        return head;
    }
    
    /**
     * Clears entire LinkedList by setting head to null.
     * All nodes become garbage collectable.
     * 
     * @param head current head
     * @return null (empty list)
     */
    public static Node deleteEntireList(Node head) {
        if (head == null) {
            System.out.println("List is already empty.");
            return null;
        }
        
        System.out.println("Cleared entire list.");
        return null;
    }
    
    /**
     * Helper function to display LinkedList.
     * 
     * @param head head of LinkedList
     */
    public static void display(Node head) {
        if (head == null) {
            System.out.println("LinkedList: null (empty)");
            return;
        }
        
        System.out.print("LinkedList: ");
        Node temp = head;
        
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        
        System.out.println("null");
    }
    
    /**
     * Helper function to get length of LinkedList.
     * 
     * @param head head of LinkedList
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
    
    /**
     * Helper to create LinkedList from array for testing.
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
        System.out.println("=== DELETE AT BEGINNING ===");
        Node head = createFromArray(new int[]{10, 20, 30, 40});
        display(head);
        
        head = deleteAtBeginning(head);
        display(head);
        
        System.out.println("\n=== DELETE AT END ===");
        head = createFromArray(new int[]{10, 20, 30, 40});
        display(head);
        
        head = deleteAtEnd(head);
        display(head);
        
        head = deleteAtEnd(head);
        display(head);
        
        System.out.println("\n=== DELETE AT POSITION ===");
        head = createFromArray(new int[]{10, 20, 30, 40, 50});
        display(head);
        
        head = deleteAtPosition(head, 2);
        display(head);
        System.out.println("Length: " + getLength(head));
        
        System.out.println("\n=== DELETE BY VALUE ===");
        head = createFromArray(new int[]{10, 20, 30, 40, 30});
        display(head);
        
        head = deleteByValue(head, 30);
        display(head);
        
        System.out.println("\n=== DELETE ALL BY VALUE ===");
        head = createFromArray(new int[]{10, 20, 10, 30, 10});
        display(head);
        
        head = deleteAllByValue(head, 10);
        display(head);
        
        System.out.println("\n=== DELETE SINGLE NODE ===");
        head = new Node(42);
        display(head);
        head = deleteAtBeginning(head);
        display(head);
        
        System.out.println("\n=== CLEAR ENTIRE LIST ===");
        head = createFromArray(new int[]{1, 2, 3, 4, 5});
        display(head);
        head = deleteEntireList(head);
        display(head);
    }
}
