package linked_lists.hard_LL;

public class RotateLinkedList {

    /*
    =====================================================================================
    PROBLEM: ROTATE A LINKED LIST
    -------------------------------------------------------------------------------------
    Rotate the list to the right by k places.
    
    Example:
    Original LL: 1 -> 2 -> 3 -> 4 -> 5, k = 2
    After rotating right by 2: 4 -> 5 -> 1 -> 2 -> 3
    Explanation: Move last 2 elements to front
    
    Example 2:
    Original LL: 0 -> 1 -> 2, k = 4
    After rotating right by 4: 2 -> 0 -> 1
    Explanation: k % length = 4 % 3 = 1, rotate by 1
    
    Approach:
    1. Find length of list
    2. Normalize k: k = k % length (handle k > length)
    3. Find node at position (length - k) - 1
    4. Break the list at this position
    5. Connect the broken part to form rotation
    
    Time Complexity: O(n) - traverse list twice
    Space Complexity: O(1) - only pointers
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
     * Rotate linked list to the right by k places.
     * 
     * Algorithm:
     * 1. Find the length of the list (traverse to find tail)
     * 2. If k == 0 or k % length == 0, no rotation needed
     * 3. Normalize k: k = k % length
     * 4. Find node at position (length - k - 1)
     * 5. Make tail point to head (create circle)
     * 6. Break circle at new position
     * 
     * @param head head of the linked list
     * @param k number of positions to rotate
     * @return head of rotated list
     */
    public static Node rotateRight(Node head, int k) {
        // Handle edge cases
        if (head == null || head.next == null || k == 0) {
            return head;
        }
        
        // Step 1: Find length and tail
        Node tail = head;
        int length = 1;
        
        while (tail.next != null) {
            tail = tail.next;
            length++;
        }
        
        // Step 2: Normalize k
        k = k % length;
        if (k == 0) {
            return head;
        }
        
        // Step 3: Find node at position (length - k - 1)
        // We need to find the node before which we should break
        Node current = head;
        for (int i = 0; i < length - k - 1; i++) {
            current = current.next;
        }
        
        // Step 4: Break and rotate
        Node newHead = current.next;  // New head after rotation
        current.next = null;          // Break the list
        tail.next = head;             // Connect old tail to old head
        
        return newHead;
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
        // Test Case 1: Standard rotation
        int[] arr1 = {1, 2, 3, 4, 5};
        Node head1 = createLinkedList(arr1);
        System.out.println("Original: " + printLL(head1));
        head1 = rotateRight(head1, 2);
        System.out.println("After rotating right by 2: " + printLL(head1));
        System.out.println();
        
        // Test Case 2: k > length
        int[] arr2 = {0, 1, 2};
        Node head2 = createLinkedList(arr2);
        System.out.println("Original: " + printLL(head2));
        head2 = rotateRight(head2, 4);
        System.out.println("After rotating right by 4: " + printLL(head2));
        System.out.println();
        
        // Test Case 3: k = 0 (no rotation)
        int[] arr3 = {1, 2, 3, 4};
        Node head3 = createLinkedList(arr3);
        System.out.println("Original: " + printLL(head3));
        head3 = rotateRight(head3, 0);
        System.out.println("After rotating right by 0: " + printLL(head3));
        System.out.println();
        
        // Test Case 4: k = length (full rotation, no change)
        int[] arr4 = {1, 2, 3, 4};
        Node head4 = createLinkedList(arr4);
        System.out.println("Original: " + printLL(head4));
        head4 = rotateRight(head4, 4);
        System.out.println("After rotating right by 4: " + printLL(head4));
        System.out.println();
        
        // Test Case 5: Single node
        int[] arr5 = {1};
        Node head5 = createLinkedList(arr5);
        System.out.println("Original: " + printLL(head5));
        head5 = rotateRight(head5, 1);
        System.out.println("After rotating right by 1: " + printLL(head5));
        System.out.println();
        
        // Test Case 6: Rotate by 1
        int[] arr6 = {1, 2, 3, 4, 5};
        Node head6 = createLinkedList(arr6);
        System.out.println("Original: " + printLL(head6));
        head6 = rotateRight(head6, 1);
        System.out.println("After rotating right by 1: " + printLL(head6));
    }
}
