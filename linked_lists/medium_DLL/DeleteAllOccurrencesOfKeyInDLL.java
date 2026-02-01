package linked_lists.medium_DLL;

public class DeleteAllOccurrencesOfKeyInDLL {

    /*
    =====================================================================================
    PROBLEM: DELETE ALL OCCURRENCES OF A KEY IN DOUBLY LINKED LIST
    -------------------------------------------------------------------------------------
    Given a doubly linked list and a key, delete all nodes containing the key.
    
    Example:
    DLL: 1 <-> 2 <-> 3 <-> 2 <-> 4 <-> 2 <-> 5
    Key: 2
    Result: 1 <-> 3 <-> 4 <-> 5
    
    Approach:
    1. Traverse the list from head
    2. For each node matching the key:
       - Update pointers to skip current node
       - Handle edge cases (head, tail, middle)
    3. Continue until end of list
    
    Time Complexity: O(n) - single pass through list
    Space Complexity: O(1) - no extra space
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
     * Delete all occurrences of a key in the doubly linked list.
     * 
     * Algorithm:
     * 1. Traverse from head to tail
     * 2. For each node:
     *    - If data matches key:
     *      - If it's head: move head to next node, set prev to null
     *      - If it's tail: move tail to prev node, set next to null
     *      - Otherwise: connect prev's next to current's next
     *    - Continue to next node
     * 
     * @param head head of the doubly linked list
     * @param key value to be deleted
     * @return new head of the list after deletion
     */
    public static Node deleteAllOccurrences(Node head, int key) {
        // Handle empty list
        if (head == null) {
            return null;
        }
        
        // Traverse and delete all occurrences
        Node current = head;
        
        while (current != null) {
            if (current.data == key) {
                // Get next node before we delete current
                Node next = current.next;
                
                if (current.prev != null) {
                    // Current is not head
                    current.prev.next = current.next;
                } else {
                    // Current is head
                    head = current.next;
                }
                
                if (current.next != null) {
                    // Current is not tail
                    current.next.prev = current.prev;
                }
                
                // Move to next node
                current = next;
            } else {
                // Move to next node
                current = current.next;
            }
        }
        
        return head;
    }
    
    /**
     * Helper method to create doubly linked list from array.
     * 
     * @param arr array of integers
     * @return head of the created list
     */
    public static Node createDLL(int[] arr) {
        if (arr.length == 0) return null;
        
        Node head = new Node(arr[0]);
        Node current = head;
        
        for (int i = 1; i < arr.length; i++) {
            Node newNode = new Node(arr[i]);
            current.next = newNode;
            newNode.prev = current;
            current = newNode;
        }
        
        return head;
    }
    
    /**
     * Helper method to print doubly linked list.
     * 
     * @param head head of the list
     * @return string representation of list
     */
    public static String printDLL(Node head) {
        StringBuilder sb = new StringBuilder();
        Node current = head;
        
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(" <-> ");
            }
            current = current.next;
        }
        
        return sb.toString();
    }
    
    public static void main(String[] args) {
        // Test Case 1: Delete from middle
        int[] arr1 = {1, 2, 3, 2, 4, 2, 5};
        Node head1 = createDLL(arr1);
        System.out.println("Original: " + printDLL(head1));
        head1 = deleteAllOccurrences(head1, 2);
        System.out.println("After deleting 2: " + printDLL(head1));
        System.out.println();
        
        // Test Case 2: Delete head
        int[] arr2 = {1, 1, 2, 3};
        Node head2 = createDLL(arr2);
        System.out.println("Original: " + printDLL(head2));
        head2 = deleteAllOccurrences(head2, 1);
        System.out.println("After deleting 1: " + printDLL(head2));
        System.out.println();
        
        // Test Case 3: Delete tail
        int[] arr3 = {1, 2, 3, 3, 3};
        Node head3 = createDLL(arr3);
        System.out.println("Original: " + printDLL(head3));
        head3 = deleteAllOccurrences(head3, 3);
        System.out.println("After deleting 3: " + printDLL(head3));
        System.out.println();
        
        // Test Case 4: Delete all nodes
        int[] arr4 = {5, 5, 5};
        Node head4 = createDLL(arr4);
        System.out.println("Original: " + printDLL(head4));
        head4 = deleteAllOccurrences(head4, 5);
        System.out.println("After deleting 5: " + (head4 == null ? "null" : printDLL(head4)));
    }
}
