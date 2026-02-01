package linked_lists.medium_LL;

public class ReverseLinkedListIterative {

    /*
    =====================================================================================
    PROBLEM: REVERSE A LINKEDLIST (ITERATIVE)
    -------------------------------------------------------------------------------------
    Reversing a LinkedList iteratively means changing all next pointers to point in 
    opposite direction. Unlike singly linked lists where we could swap values, we must 
    manipulate pointers. The iterative approach uses three pointers: prev (previous node),
    current (current node), and next (temporary storage for next node). For each node, 
    we redirect its next pointer to prev, then move all pointers one step forward. Key 
    challenge: saving next node before modifying current's next pointer to avoid losing 
    reference. This approach is efficient with O(1) space (excluding output) and O(n) 
    time. Works for any list length. After reversal, old head becomes tail (next = null)
    and old tail becomes new head.

    Time Complexity: O(n) single traversal
    Space Complexity: O(1) constant space

    Example:
    Original: 1->2->3->4->5->null
    Reversed: 5->4->3->2->1->null
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
     * Reverses LinkedList iteratively using three pointers.
     * Standard approach: save next, redirect current.next to prev, move all forward.
     * Maintains only 3 pointers regardless of list size.
     * Returns new head (old tail).
     * 
     * @param head current head of LinkedList
     * @return new head (reversed list)
     */
    public static Node reverseList(Node head) {
        Node prev = null;
        Node current = head;
        
        while (current != null) {
            // Save next node before modifying current
            Node nextTemp = current.next;
            
            // Redirect current's next to previous
            current.next = prev;
            
            // Move prev and current one step forward
            prev = current;
            current = nextTemp;
        }
        
        return prev;  // New head
    }
    
    /**
     * Alternative implementation with explicit variable names.
     * Clearer for understanding the pointer manipulation process.
     * 
     * @param head head of LinkedList
     * @return new head
     */
    public static Node reverseListExplicit(Node head) {
        Node previousNode = null;
        Node currentNode = head;
        
        while (currentNode != null) {
            // Store reference to next node
            Node nextNode = currentNode.next;
            
            // Reverse the link
            currentNode.next = previousNode;
            
            // Move to next node
            previousNode = currentNode;
            currentNode = nextNode;
        }
        
        return previousNode;
    }
    
    /**
     * Reverses list and returns new head via parameter.
     * Demonstrates alternative return pattern.
     * Modified version of traditional approach.
     * 
     * @param head current head
     * @param newHead array to store new head
     */
    public static void reverseListWithOutput(Node head, Node[] newHead) {
        Node prev = null;
        Node current = head;
        
        while (current != null) {
            Node next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        
        newHead[0] = prev;
    }
    
    /**
     * Reverses list between two positions.
     * Allows partial reversal of list.
     * More complex as we need to maintain connections outside reversed portion.
     * 
     * @param head head of LinkedList
     * @param position1 start position (1-indexed)
     * @param position2 end position (1-indexed)
     * @return new head
     */
    public static Node reverseBetween(Node head, int position1, int position2) {
        if (head == null || position1 >= position2) {
            return head;
        }
        
        // Create dummy node to simplify edge cases
        Node dummy = new Node(0);
        dummy.next = head;
        
        Node prev = dummy;
        
        // Move prev to node before position1
        for (int i = 1; i < position1; i++) {
            prev = prev.next;
        }
        
        // Start of portion to reverse
        Node current = prev.next;
        
        // Reverse the portion between position1 and position2
        for (int i = 0; i < position2 - position1; i++) {
            // Save next node
            Node next = current.next;
            
            // Reverse link
            current.next = next.next;
            next.next = prev.next;
            prev.next = next;
        }
        
        return dummy.next;
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
     * Helper to create LinkedList from array.
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
    
    /**
     * Helper to get length of LinkedList.
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
    
    public static void main(String[] args) {
        System.out.println("=== REVERSE SIMPLE LIST ===");
        Node head = createFromArray(new int[]{1, 2, 3, 4, 5});
        System.out.println("Original:");
        display(head);
        
        head = reverseList(head);
        System.out.println("Reversed:");
        display(head);
        
        System.out.println("\n=== REVERSE SINGLE NODE ===");
        head = new Node(42);
        System.out.println("Original:");
        display(head);
        
        head = reverseList(head);
        System.out.println("Reversed:");
        display(head);
        
        System.out.println("\n=== REVERSE EMPTY LIST ===");
        head = null;
        System.out.println("Original:");
        display(head);
        
        head = reverseList(head);
        System.out.println("Reversed:");
        display(head);
        
        System.out.println("\n=== REVERSE TWO NODES ===");
        head = createFromArray(new int[]{10, 20});
        System.out.println("Original:");
        display(head);
        
        head = reverseList(head);
        System.out.println("Reversed:");
        display(head);
        
        System.out.println("\n=== EXPLICIT VERSION ===");
        head = createFromArray(new int[]{1, 2, 3, 4, 5, 6});
        System.out.println("Original:");
        display(head);
        
        head = reverseListExplicit(head);
        System.out.println("Reversed (explicit):");
        display(head);
        
        System.out.println("\n=== REVERSE WITH OUTPUT PARAMETER ===");
        head = createFromArray(new int[]{10, 20, 30, 40});
        System.out.println("Original:");
        display(head);
        
        Node[] result = new Node[1];
        reverseListWithOutput(head, result);
        System.out.println("Reversed (with output):");
        display(result[0]);
        
        System.out.println("\n=== REVERSE BETWEEN POSITIONS ===");
        head = createFromArray(new int[]{1, 2, 3, 4, 5});
        System.out.println("Original:");
        display(head);
        
        head = reverseBetween(head, 2, 4);  // Reverse positions 2-4 (1-indexed)
        System.out.println("After reversing positions 2-4:");
        display(head);
        
        System.out.println("\n=== VERIFY REVERSAL - DOUBLE REVERSE ===");
        head = createFromArray(new int[]{1, 2, 3, 4, 5});
        System.out.println("Original:");
        display(head);
        
        head = reverseList(head);
        System.out.println("Reversed:");
        display(head);
        
        head = reverseList(head);
        System.out.println("Double reversed (should match original):");
        display(head);
    }
}
