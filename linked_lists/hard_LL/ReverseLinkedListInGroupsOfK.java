package linked_lists.hard_LL;

public class ReverseLinkedListInGroupsOfK {

    /*
    =====================================================================================
    PROBLEM: REVERSE LINKED LIST IN GROUPS OF GIVEN SIZE K
    -------------------------------------------------------------------------------------
    Reverse the nodes of a linked list k at a time and return its modified list.
    If the number of nodes is not a multiple of k then left-out nodes should be unchanged.
    
    Example:
    Original LL: 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8, K = 3
    Group 1 (1,2,3) -> reverse to (3,2,1)
    Group 2 (4,5,6) -> reverse to (6,5,4)
    Group 3 (7,8) -> only 2 nodes, not divisible by k, keep unchanged
    Result: 3 -> 2 -> 1 -> 6 -> 5 -> 4 -> 7 -> 8
    
    Approach:
    1. For each group of k nodes:
       - Check if k nodes exist
       - If yes, reverse those k nodes
       - Connect the reversed group with next group
       - If no, stop and keep remaining as is
    2. Use recursion or iteration with pointers
    
    Time Complexity: O(n) - traverse each node once
    Space Complexity: O(1) if iterative, O(n/k) if recursive (call stack)
    =====================================================================================
    */
    
    /**
     * Node class for Singly Linked List.
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
     * Reverse linked list in groups of k nodes.
     * Uses recursive approach with helper method.
     * 
     * Algorithm:
     * 1. Find the kth node in current group
     * 2. If kth node is null, no group to reverse, return head
     * 3. Save the (k+1)th node
     * 4. Reverse the current group of k nodes
     * 5. Recursively process remaining groups
     * 6. Connect reversed group to next group
     * 
     * @param head head of the linked list
     * @param k size of each group to reverse
     * @return head of the modified list
     */
    public static Node reverseKGroup(Node head, int k) {
        if (head == null || k <= 1) {
            return head;
        }
        
        // Check if there are at least k nodes starting from head
        Node kthNode = head;
        for (int i = 1; i < k && kthNode != null; i++) {
            kthNode = kthNode.next;
        }
        
        // If there are less than k nodes, return head as is
        if (kthNode == null) {
            return head;
        }
        
        // Save the node after kth node
        Node nextHead = kthNode.next;
        
        // Disconnect the kth node to mark end of current group
        kthNode.next = null;
        
        // Reverse the current group
        Node reversedHead = reverse(head);
        
        // The new head of this group is the reversed head
        // Connect to the recursively reversed remaining list
        head.next = reverseKGroup(nextHead, k);
        
        return reversedHead;
    }
    
    /**
     * Reverse a linked list.
     * 
     * @param head head of list to reverse
     * @return head of reversed list
     */
    private static Node reverse(Node head) {
        Node prev = null;
        Node current = head;
        
        while (current != null) {
            Node nextTemp = current.next;
            current.next = prev;
            prev = current;
            current = nextTemp;
        }
        
        return prev;
    }
    
    /**
     * Helper method to create linked list from array.
     * 
     * @param arr array of integers
     * @return head of the created list
     */
    public static Node createLinkedList(int[] arr) {
        if (arr.length == 0) return null;
        
        Node head = new Node(arr[0]);
        Node current = head;
        
        for (int i = 1; i < arr.length; i++) {
            current.next = new Node(arr[i]);
            current = current.next;
        }
        
        return head;
    }
    
    /**
     * Helper method to print linked list.
     * 
     * @param head head of the list
     * @return string representation of list
     */
    public static String printLL(Node head) {
        StringBuilder sb = new StringBuilder();
        Node current = head;
        
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(" -> ");
            }
            current = current.next;
        }
        
        return sb.toString();
    }
    
    public static void main(String[] args) {
        // Test Case 1: Standard case
        int[] arr1 = {1, 2, 3, 4, 5, 6, 7, 8};
        Node head1 = createLinkedList(arr1);
        System.out.println("Original: " + printLL(head1));
        head1 = reverseKGroup(head1, 3);
        System.out.println("After reversing in groups of 3: " + printLL(head1));
        System.out.println();
        
        // Test Case 2: K = 2
        int[] arr2 = {1, 2, 3, 4, 5};
        Node head2 = createLinkedList(arr2);
        System.out.println("Original: " + printLL(head2));
        head2 = reverseKGroup(head2, 2);
        System.out.println("After reversing in groups of 2: " + printLL(head2));
        System.out.println();
        
        // Test Case 3: K > length of list
        int[] arr3 = {1, 2, 3};
        Node head3 = createLinkedList(arr3);
        System.out.println("Original: " + printLL(head3));
        head3 = reverseKGroup(head3, 5);
        System.out.println("After reversing in groups of 5: " + printLL(head3));
        System.out.println();
        
        // Test Case 4: K = 1 (no change)
        int[] arr4 = {1, 2, 3, 4};
        Node head4 = createLinkedList(arr4);
        System.out.println("Original: " + printLL(head4));
        head4 = reverseKGroup(head4, 1);
        System.out.println("After reversing in groups of 1: " + printLL(head4));
        System.out.println();
        
        // Test Case 5: K = length (reverse entire list)
        int[] arr5 = {1, 2, 3, 4};
        Node head5 = createLinkedList(arr5);
        System.out.println("Original: " + printLL(head5));
        head5 = reverseKGroup(head5, 4);
        System.out.println("After reversing in groups of 4: " + printLL(head5));
    }
}
