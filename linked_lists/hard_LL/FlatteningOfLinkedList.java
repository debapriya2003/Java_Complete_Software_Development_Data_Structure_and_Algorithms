package linked_lists.hard_LL;

import java.util.ArrayList;
import java.util.List;

public class FlatteningOfLinkedList {

    /*
    =====================================================================================
    PROBLEM: FLATTENING OF LINKED LIST
    -------------------------------------------------------------------------------------
    Given a linked list where each node has a pointer to a sub-list (down pointer),
    flatten the list such that all nodes are in single level.
    
    Example:
    5 -> 10 -> 19 -> 28
    |    |     |     |
    7    20    22    35
    |    |           |
    8    50    |     40
    |          |     |
    30         |     NULL
    
    After flattening:
    5 -> 7 -> 8 -> 10 -> 20 -> 22 -> 30 -> 19 -> 28 -> 35 -> 40
    (arranged in sorted order)
    
    Approach:
    1. If list is empty or has single node, return
    2. Merge current level with next level recursively
    3. Use merge two sorted lists technique
    4. Continue until all nodes are merged into one level
    
    Time Complexity: O(N * M) where N = nodes at each level, M = number of levels
    Space Complexity: O(1) if iterative merge, O(M) for recursion stack
    =====================================================================================
    */
    
    /**
     * Node class for flattened linked list.
     * Has both next (horizontal) and down (vertical) pointers.
     */
    public static class Node {
        public int data;
        public Node next;
        public Node down;
        
        public Node(int data) {
            this.data = data;
            this.next = null;
            this.down = null;
        }
    }
    
    /**
     * Flatten a linked list with down pointers.
     * Recursively merges each level with the next level.
     * 
     * Algorithm:
     * 1. If head is null or has no next, return head
     * 2. Recursively flatten rest of the list (next levels)
     * 3. Merge current head with flattened rest
     * 
     * @param head head of the list to flatten
     * @return head of flattened list
     */
    public static Node flatten(Node head) {
        // Base case: if head is null or single node at this level
        if (head == null || head.next == null) {
            return head;
        }
        
        // Recursively flatten the remaining list
        head.next = flatten(head.next);
        
        // Merge current level with flattened remaining
        head = mergeTwoLists(head, head.next);
        
        // After merging, clear the next pointer since all nodes are in down list
        head.next = null;
        
        return head;
    }
    
    /**
     * Merge two sorted linked lists (using down pointer for first list).
     * Merges list1 (vertical) with list2 (which becomes vertical after previous merge).
     * 
     * @param list1 first list (uses down pointer)
     * @param list2 second list (uses down pointer)
     * @return head of merged sorted list
     */
    private static Node mergeTwoLists(Node list1, Node list2) {
        // Create a dummy node to simplify merging
        Node dummy = new Node(0);
        Node current = dummy;
        
        // Merge while both lists have nodes
        while (list1 != null && list2 != null) {
            if (list1.data < list2.data) {
                current.down = list1;
                list1 = list1.down;
            } else {
                current.down = list2;
                list2 = list2.down;
            }
            current = current.down;
        }
        
        // Attach remaining nodes
        if (list1 != null) {
            current.down = list1;
        } else {
            current.down = list2;
        }
        
        return dummy.down;
    }
    
    /**
     * Helper method to create a flattened list structure.
     * Creates multiple sublists linked at different levels.
     * 
     * @return head of the multi-level list
     */
    public static Node createMultiLevelList() {
        // Create level 0 (horizontal): 5 -> 10 -> 19 -> 28
        Node head = new Node(5);
        head.next = new Node(10);
        head.next.next = new Node(19);
        head.next.next.next = new Node(28);
        
        // Create sublists for node 5: 7 -> 8 -> 30
        head.down = new Node(7);
        head.down.down = new Node(8);
        head.down.down.down = new Node(30);
        
        // Create sublists for node 10: 20 -> 50
        head.next.down = new Node(20);
        head.next.down.down = new Node(50);
        
        // Create sublists for node 19: 22
        head.next.next.down = new Node(22);
        
        // Create sublists for node 28: 35 -> 40
        head.next.next.next.down = new Node(35);
        head.next.next.next.down.down = new Node(40);
        
        return head;
    }
    
    /**
     * Helper method to print multi-level list structure.
     * 
     * @param head head of the list
     * @return string representation showing structure
     */
    public static String printMultiLevelList(Node head) {
        List<String> result = new ArrayList<>();
        Node current = head;
        
        while (current != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(current.data);
            
            Node down = current.down;
            while (down != null) {
                sb.append(" -> ").append(down.data);
                down = down.down;
            }
            
            result.add(sb.toString());
            current = current.next;
        }
        
        return String.join("\n", result);
    }
    
    /**
     * Helper method to print flattened list.
     * 
     * @param head head of the flattened list
     * @return string representation of list
     */
    public static String printFlatList(Node head) {
        StringBuilder sb = new StringBuilder();
        Node current = head;
        
        while (current != null) {
            sb.append(current.data);
            if (current.down != null) {
                sb.append(" -> ");
            }
            current = current.down;
        }
        
        return sb.toString();
    }
    
    public static void main(String[] args) {
        // Test Case 1: Standard multi-level list
        Node head = createMultiLevelList();
        System.out.println("Original Multi-level List:");
        System.out.println(printMultiLevelList(head));
        System.out.println();
        
        head = flatten(head);
        System.out.println("After Flattening:");
        System.out.println(printFlatList(head));
        System.out.println();
        
        // Test Case 2: Simple two-level list
        Node head2 = new Node(1);
        head2.next = new Node(2);
        head2.down = new Node(3);
        head2.next.down = new Node(4);
        head2.down.down = new Node(5);
        
        System.out.println("Original Multi-level List 2:");
        System.out.println(printMultiLevelList(head2));
        System.out.println();
        
        head2 = flatten(head2);
        System.out.println("After Flattening:");
        System.out.println(printFlatList(head2));
        System.out.println();
        
        // Test Case 3: Single level (no flattening needed)
        Node head3 = new Node(1);
        head3.next = new Node(2);
        head3.next.next = new Node(3);
        
        System.out.println("Original List (no sublists):");
        System.out.println(printMultiLevelList(head3));
        System.out.println();
        
        head3 = flatten(head3);
        System.out.println("After Flattening:");
        System.out.println(printFlatList(head3));
    }
}
