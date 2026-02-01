package linked_lists.medium_LL;

public class ReverseLinkedListRecursive {

    /*
    =====================================================================================
    PROBLEM: REVERSE A LINKEDLIST (RECURSIVE)
    -------------------------------------------------------------------------------------
    Reversing a LinkedList recursively involves reaching the end of list through 
    recursive calls, then reversing pointers while returning from recursion. Key insight:
    make recursive call to next node, then from base case, reverse pointer from next 
    back to current. To reverse link between current and next: next.next = current, 
    current.next = null (to avoid cycles). Recursive approach is elegant but uses O(n) 
    call stack space, making it less efficient than iterative for large lists. However,
    it beautifully demonstrates recursion and pointer manipulation. Works for all list 
    lengths. Requires careful handling of null checks and avoiding null pointer 
    exceptions when accessing next.next.

    Time Complexity: O(n) recursive traversal
    Space Complexity: O(n) call stack

    Example:
    Original: 1->2->3->null
    Reversed: 3->2->1->null
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
     * Reverses LinkedList recursively using direct recursive calls.
     * Base case: head is null or single node, return head.
     * Recursive case: reverse rest of list, then reverse link between current and next.
     * Returns new head (old tail).
     * 
     * @param head current head of LinkedList
     * @return new head of reversed list
     */
    public static Node reverseListRecursive(Node head) {
        // Base case: empty or single node
        if (head == null || head.next == null) {
            return head;
        }
        
        // Recursively reverse rest of list
        Node newHead = reverseListRecursive(head.next);
        
        // Reverse link from next to current
        head.next.next = head;  // Make next point back to current
        head.next = null;        // Current becomes tail
        
        return newHead;
    }
    
    /**
     * Alternative recursive approach using reference wrapper.
     * Allows reversing list while updating head reference.
     * Less common but demonstrates different recursion pattern.
     * 
     * @param head current head of LinkedList
     * @return new head
     */
    public static Node reverseListRecursiveWithReference(Node head) {
        Node[] newHead = new Node[1];
        reverseUtil(head, newHead);
        return newHead[0];
    }
    
    /**
     * Helper function for recursive reversal with reference.
     * Processes list and updates head reference at base case.
     * 
     * @param current current node
     * @param newHead array to store new head
     * @return tail of current portion
     */
    private static Node reverseUtil(Node current, Node[] newHead) {
        // Base case: reached end
        if (current == null) {
            return null;
        }
        
        // If single node, it becomes new head
        if (current.next == null) {
            newHead[0] = current;
            return current;
        }
        
        // Recursively reverse rest
        Node prev = reverseUtil(current.next, newHead);
        
        // Reverse link
        prev.next = current;
        current.next = null;
        
        return current;
    }
    
    /**
     * Recursive reversal with explicit helper function.
     * Clearer for understanding the recursion pattern.
     * Helper function handles the actual reversal logic.
     * 
     * @param head current head
     * @return new head
     */
    public static Node reverseRecursiveHelper(Node head) {
        if (head == null) {
            return null;
        }
        
        return reverseHelper(head);
    }
    
    /**
     * Core helper for recursive reversal.
     * Base case: single or no node.
     * Recursive case: reverse rest, then fix pointers.
     * 
     * @param head current node
     * @return new head (old tail)
     */
    private static Node reverseHelper(Node head) {
        // Base case
        if (head == null || head.next == null) {
            return head;
        }
        
        // Recursively reverse rest and get new head
        Node newHead = reverseHelper(head.next);
        
        // Reverse pointers
        head.next.next = head;
        head.next = null;
        
        return newHead;
    }
    
    /**
     * Tail-recursive approach for reversal.
     * More optimizable by compiler but still uses stack in Java.
     * Uses accumulator pattern.
     * 
     * @param head current head
     * @return new head
     */
    public static Node reverseTailRecursive(Node head) {
        return reverseTailRecursiveHelper(head, null);
    }
    
    /**
     * Helper for tail-recursive reversal.
     * Accumulates reversed portion in prev.
     * 
     * @param current current node
     * @param prev previous (reversed) portion
     * @return new head
     */
    private static Node reverseTailRecursiveHelper(Node current, Node prev) {
        // Base case: reached end
        if (current == null) {
            return prev;
        }
        
        // Save next before modification
        Node next = current.next;
        
        // Reverse link
        current.next = prev;
        
        // Recurse with current as new prev
        return reverseTailRecursiveHelper(next, current);
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
    
    public static void main(String[] args) {
        System.out.println("=== REVERSE RECURSIVE (BASIC) ===");
        Node head = createFromArray(new int[]{1, 2, 3, 4, 5});
        System.out.println("Original:");
        display(head);
        
        head = reverseListRecursive(head);
        System.out.println("Reversed:");
        display(head);
        
        System.out.println("\n=== REVERSE SINGLE NODE ===");
        head = new Node(42);
        System.out.println("Original:");
        display(head);
        
        head = reverseListRecursive(head);
        System.out.println("Reversed:");
        display(head);
        
        System.out.println("\n=== REVERSE EMPTY LIST ===");
        head = null;
        System.out.println("Original:");
        display(head);
        
        head = reverseListRecursive(head);
        System.out.println("Reversed:");
        display(head);
        
        System.out.println("\n=== REVERSE WITH REFERENCE ===");
        head = createFromArray(new int[]{1, 2, 3, 4, 5, 6});
        System.out.println("Original:");
        display(head);
        
        head = reverseListRecursiveWithReference(head);
        System.out.println("Reversed (with reference):");
        display(head);
        
        System.out.println("\n=== REVERSE HELPER VERSION ===");
        head = createFromArray(new int[]{10, 20, 30});
        System.out.println("Original:");
        display(head);
        
        head = reverseRecursiveHelper(head);
        System.out.println("Reversed (helper):");
        display(head);
        
        System.out.println("\n=== TAIL RECURSIVE REVERSAL ===");
        head = createFromArray(new int[]{1, 2, 3, 4, 5});
        System.out.println("Original:");
        display(head);
        
        head = reverseTailRecursive(head);
        System.out.println("Reversed (tail-recursive):");
        display(head);
        
        System.out.println("\n=== DOUBLE REVERSE TEST ===");
        head = createFromArray(new int[]{1, 2, 3, 4, 5});
        System.out.println("Original:");
        display(head);
        
        head = reverseListRecursive(head);
        System.out.println("First reverse:");
        display(head);
        
        head = reverseListRecursive(head);
        System.out.println("Double reversed (should match original):");
        display(head);
        
        System.out.println("\n=== TWO NODE LIST ===");
        head = createFromArray(new int[]{10, 20});
        System.out.println("Original:");
        display(head);
        
        head = reverseListRecursive(head);
        System.out.println("Reversed:");
        display(head);
    }
}
