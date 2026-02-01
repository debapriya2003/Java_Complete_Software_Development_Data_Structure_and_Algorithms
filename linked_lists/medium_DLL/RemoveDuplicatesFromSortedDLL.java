package linked_lists.medium_DLL;

public class RemoveDuplicatesFromSortedDLL {

    /*
    =====================================================================================
    PROBLEM: REMOVE DUPLICATES FROM SORTED DOUBLY LINKED LIST
    -------------------------------------------------------------------------------------
    Given a sorted doubly linked list, remove all duplicate nodes keeping only one instance
    of each value.
    
    Example:
    DLL: 1 <-> 1 <-> 2 <-> 2 <-> 2 <-> 3 <-> 4 <-> 4
    Result: 1 <-> 2 <-> 3 <-> 4
    
    Approach:
    1. Traverse the list from head
    2. For each node, check if next node has same data
    3. If duplicate found, skip the duplicate node(s)
    4. Update pointers accordingly to maintain DLL property
    
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
     * Remove all duplicate elements from a sorted doubly linked list.
     * 
     * Algorithm:
     * 1. Start from head node
     * 2. Compare current node with next node
     * 3. If data is same:
     *    - Skip the next node by updating pointers
     *    - Continue checking from current node
     * 4. If data is different:
     *    - Move to next node
     * 5. Continue until we reach the last node
     * 
     * @param head head of the sorted doubly linked list
     * @return head of the list after removing duplicates
     */
    public static Node removeDuplicates(Node head) {
        // Handle empty or single node list
        if (head == null || head.next == null) {
            return head;
        }
        
        Node current = head;
        
        while (current != null && current.next != null) {
            // If current node's data equals next node's data
            if (current.data == current.next.data) {
                // Get the node to skip
                Node nextNode = current.next;
                
                // Update current's next pointer to skip the duplicate
                current.next = nextNode.next;
                
                // Update the prev pointer of the next-next node if it exists
                if (nextNode.next != null) {
                    nextNode.next.prev = current;
                }
                
                // Don't move current, check again as there might be more duplicates
            } else {
                // Move to next node only if no duplicate
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
    
    /**
     * Helper method to verify backward traversal (DLL property).
     * 
     * @param head head of the list
     * @return string representation in reverse
     */
    public static String printDLLReverse(Node head) {
        // Find tail
        Node tail = head;
        if (tail != null) {
            while (tail.next != null) {
                tail = tail.next;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        Node current = tail;
        
        while (current != null) {
            sb.append(current.data);
            if (current.prev != null) {
                sb.append(" <-> ");
            }
            current = current.prev;
        }
        
        return sb.toString();
    }
    
    public static void main(String[] args) {
        // Test Case 1: Multiple consecutive duplicates
        int[] arr1 = {1, 1, 2, 2, 2, 3, 4, 4};
        Node head1 = createDLL(arr1);
        System.out.println("Original: " + printDLL(head1));
        head1 = removeDuplicates(head1);
        System.out.println("After removing duplicates: " + printDLL(head1));
        System.out.println("Reverse check: " + printDLLReverse(head1));
        System.out.println();
        
        // Test Case 2: No duplicates
        int[] arr2 = {1, 2, 3, 4, 5};
        Node head2 = createDLL(arr2);
        System.out.println("Original: " + printDLL(head2));
        head2 = removeDuplicates(head2);
        System.out.println("After removing duplicates: " + printDLL(head2));
        System.out.println();
        
        // Test Case 3: All elements are duplicates
        int[] arr3 = {5, 5, 5, 5};
        Node head3 = createDLL(arr3);
        System.out.println("Original: " + printDLL(head3));
        head3 = removeDuplicates(head3);
        System.out.println("After removing duplicates: " + printDLL(head3));
        System.out.println();
        
        // Test Case 4: Single node
        int[] arr4 = {10};
        Node head4 = createDLL(arr4);
        System.out.println("Original: " + printDLL(head4));
        head4 = removeDuplicates(head4);
        System.out.println("After removing duplicates: " + printDLL(head4));
        System.out.println();
        
        // Test Case 5: Duplicates at start and end
        int[] arr5 = {1, 1, 1, 2, 3, 3, 3};
        Node head5 = createDLL(arr5);
        System.out.println("Original: " + printDLL(head5));
        head5 = removeDuplicates(head5);
        System.out.println("After removing duplicates: " + printDLL(head5));
        System.out.println("Reverse check: " + printDLLReverse(head5));
    }
}
