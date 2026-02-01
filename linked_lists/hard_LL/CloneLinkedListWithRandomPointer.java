package linked_lists.hard_LL;

import java.util.HashMap;
import java.util.Map;

public class CloneLinkedListWithRandomPointer {

    /*
    =====================================================================================
    PROBLEM: CLONE A LINKED LIST WITH RANDOM AND NEXT POINTER
    -------------------------------------------------------------------------------------
    Each node in a linked list has two pointers: next and random.
    The next pointer points to the next node in the list.
    The random pointer can point to any node in the list or null.
    Clone the list such that the cloned list is independent copy.
    
    Example:
    Original:
    1 -> 2 -> 3 -> null
    |    |    |
    random pointers...
    
    Cloned list should have identical structure but different nodes.
    
    Approach 1: HashMap (Optimal for most cases)
    1. First pass: Create all nodes and store mapping (original -> clone)
    2. Second pass: Assign next and random pointers using mapping
    
    Approach 2: Interleave Method (Space O(1) without counting output)
    1. Insert cloned node after each original node
    2. Assign random pointers to cloned nodes
    3. Separate the two lists
    
    Time Complexity: O(n) for both approaches
    Space Complexity: O(n) for Approach 1, O(1) excluding output for Approach 2
    =====================================================================================
    */
    
    /**
     * Node class with both next and random pointers.
     */
    public static class Node {
        public int data;
        public Node next;
        public Node random;
        
        public Node(int data) {
            this.data = data;
            this.next = null;
            this.random = null;
        }
    }
    
    /**
     * Clone linked list using HashMap approach.
     * Uses hash map to track mapping between original and cloned nodes.
     * 
     * Algorithm:
     * 1. First pass: Create all cloned nodes, store in map
     * 2. Second pass: Assign next and random pointers to cloned nodes
     * 
     * @param head head of original list
     * @return head of cloned list
     */
    public static Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        
        // Map to store mapping: original node -> cloned node
        Map<Node, Node> map = new HashMap<>();
        
        // First pass: Create all nodes
        Node current = head;
        while (current != null) {
            map.put(current, new Node(current.data));
            current = current.next;
        }
        
        // Second pass: Assign pointers
        current = head;
        while (current != null) {
            Node clonedNode = map.get(current);
            // Set next pointer
            clonedNode.next = map.get(current.next);
            // Set random pointer
            clonedNode.random = map.get(current.random);
            current = current.next;
        }
        
        return map.get(head);
    }
    
    /**
     * Clone linked list using interleaving method.
     * Space efficient approach that modifies original temporarily.
     * 
     * Algorithm:
     * 1. Create cloned node after each original node
     * 2. Set random pointers for cloned nodes
     * 3. Separate the original and cloned lists
     * 
     * @param head head of original list
     * @return head of cloned list
     */
    public static Node copyRandomListInterleave(Node head) {
        if (head == null) {
            return null;
        }
        
        // Step 1: Insert cloned node after each original node
        Node current = head;
        while (current != null) {
            Node cloned = new Node(current.data);
            cloned.next = current.next;
            current.next = cloned;
            current = cloned.next;
        }
        
        // Step 2: Assign random pointers to cloned nodes
        current = head;
        while (current != null) {
            if (current.random != null) {
                current.next.random = current.random.next;
            }
            current = current.next.next;
        }
        
        // Step 3: Separate the two lists
        Node clonedHead = head.next;
        Node originalCurrent = head;
        Node clonedCurrent = clonedHead;
        
        while (originalCurrent != null) {
            originalCurrent.next = originalCurrent.next.next;
            if (clonedCurrent.next != null) {
                clonedCurrent.next = clonedCurrent.next.next;
            }
            originalCurrent = originalCurrent.next;
            clonedCurrent = clonedCurrent.next;
        }
        
        return clonedHead;
    }
    
    /**
     * Helper method to create a linked list with random pointers.
     * Returns list: 1 -> 2 -> 3 with specific random pointers.
     * 
     * @return head of list
     */
    public static Node createListWithRandomPointer() {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        
        // Set next pointers
        node1.next = node2;
        node2.next = node3;
        node3.next = null;
        
        // Set random pointers
        node1.random = node3;      // 1's random points to 3
        node2.random = node1;      // 2's random points to 1
        node3.random = node2;      // 3's random points to 2
        
        return node1;
    }
    
    /**
     * Helper method to create another list with random pointers.
     * 
     * @return head of list
     */
    public static Node createAnotherList() {
        Node node1 = new Node(7);
        Node node2 = new Node(13);
        Node node3 = new Node(11);
        Node node4 = new Node(10);
        Node node5 = new Node(1);
        
        // Set next pointers
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = null;
        
        // Set random pointers
        node1.random = null;       // 7's random is null
        node2.random = node1;      // 13's random points to 7
        node3.random = node5;      // 11's random points to 1
        node4.random = node3;      // 10's random points to 11
        node5.random = node1;      // 1's random points to 7
        
        return node1;
    }
    
    /**
     * Print list with random pointers.
     * 
     * @param head head of list
     * @return string representation
     */
    public static String printListWithRandom(Node head) {
        StringBuilder sb = new StringBuilder();
        Node current = head;
        
        while (current != null) {
            sb.append("Node ").append(current.data)
              .append(" -> next: ");
            
            if (current.next != null) {
                sb.append(current.next.data);
            } else {
                sb.append("null");
            }
            
            sb.append(", random: ");
            if (current.random != null) {
                sb.append(current.random.data);
            } else {
                sb.append("null");
            }
            
            sb.append("\n");
            current = current.next;
        }
        
        return sb.toString();
    }
    
    /**
     * Verify if two lists are identical in structure and values.
     * 
     * @param original original list
     * @param cloned cloned list
     * @return true if structures match, false otherwise
     */
    public static boolean verifyClone(Node original, Node cloned) {
        Node o = original;
        Node c = cloned;
        
        while (o != null && c != null) {
            if (o.data != c.data) {
                return false;
            }
            if ((o.next == null && c.next != null) || (o.next != null && c.next == null)) {
                return false;
            }
            if ((o.random == null && c.random != null) || (o.random != null && c.random == null)) {
                return false;
            }
            o = o.next;
            c = c.next;
        }
        
        return o == null && c == null;
    }
    
    public static void main(String[] args) {
        // Test Case 1: Using HashMap approach
        System.out.println("=== Test Case 1: HashMap Approach ===");
        Node head1 = createListWithRandomPointer();
        System.out.println("Original List:");
        System.out.println(printListWithRandom(head1));
        
        Node cloned1 = copyRandomList(head1);
        System.out.println("Cloned List:");
        System.out.println(printListWithRandom(cloned1));
        System.out.println("Verification: " + verifyClone(head1, cloned1));
        System.out.println("Are they different objects? " + (head1 != cloned1));
        System.out.println();
        
        // Test Case 2: Using Interleaving approach
        System.out.println("=== Test Case 2: Interleaving Approach ===");
        Node head2 = createListWithRandomPointer();
        System.out.println("Original List:");
        System.out.println(printListWithRandom(head2));
        
        Node cloned2 = copyRandomListInterleave(head2);
        System.out.println("Cloned List:");
        System.out.println(printListWithRandom(cloned2));
        System.out.println("Verification: " + verifyClone(head2, cloned2));
        System.out.println("Are they different objects? " + (head2 != cloned2));
        System.out.println();
        
        // Test Case 3: Another test case
        System.out.println("=== Test Case 3: Another List ===");
        Node head3 = createAnotherList();
        System.out.println("Original List:");
        System.out.println(printListWithRandom(head3));
        
        Node cloned3 = copyRandomList(head3);
        System.out.println("Cloned List:");
        System.out.println(printListWithRandom(cloned3));
        System.out.println("Verification: " + verifyClone(head3, cloned3));
    }
}
