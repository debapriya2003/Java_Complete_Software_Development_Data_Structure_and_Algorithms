package linked_lists.doubly_linked_list;

public class ReverseDLL {

    /*
    =====================================================================================
    PROBLEM: REVERSE A DOUBLY LINKED LIST
    -------------------------------------------------------------------------------------
    Reversing a DLL means changing the direction of traversal - the list reads same 
    whether traversing from head or tail, but pointers are reversed. Head becomes tail 
    and tail becomes head. For each node, swap its next and prev pointers. After complete 
    reversal, the old tail becomes new head. This is useful for operations like playing 
    songs in reverse order, stepping through undo stack, reversing browser history, etc. 
    Unlike singly LL where we need temporary nodes to reverse, DLL reversal is more 
    elegant since we swap both pointers. Key insight: traverse entire list, swapping 
    next and prev for each node, then update head to old tail. Can be done iteratively 
    or recursively. Must be careful with null checks when dealing with pointers.

    Time Complexity: O(n) single pass through all nodes
    Space Complexity: O(1) iterative, O(n) recursive (call stack)

    Example:
    Original: null <- 10 <-> 20 <-> 30 -> null
    Reversed: null <- 30 <-> 20 <-> 10 -> null
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
     * Reverses DLL using iterative approach.
     * Traverses from head to tail, swapping next and prev for each node.
     * Updates head to point to old tail (new head).
     * O(n) time, O(1) space. Most efficient approach.
     * 
     * @param head current head of DLL
     * @return new head (old tail)
     */
    public static Node reverseDLL(Node head) {
        if (head == null) {
            return null;
        }
        
        Node temp = head;
        Node newHead = null;
        
        // Traverse and swap pointers for each node
        while (temp != null) {
            // Swap next and prev
            Node tempNext = temp.next;
            temp.next = temp.prev;
            temp.prev = tempNext;
            
            // newHead will be set to the last node (new head after reversal)
            newHead = temp;
            
            // Move to next node (which is now in prev due to swap)
            temp = tempNext;
        }
        
        return newHead;
    }
    
    /**
     * Alternative iterative approach using explicit pointer swapping.
     * Clearer variable names for understanding the swapping process.
     * Same time/space complexity as reverseDLL but more readable.
     * 
     * @param head current head of DLL
     * @return new head
     */
    public static Node reverseDLLExplicit(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        Node current = head;
        Node newHead = null;
        
        while (current != null) {
            // Store next node before modification
            Node nextNode = current.next;
            
            // Swap pointers
            Node prevNode = current.prev;
            current.prev = current.next;
            current.next = prevNode;
            
            // Move to next (using stored nextNode)
            newHead = current;
            current = nextNode;
        }
        
        return newHead;
    }
    
    /**
     * Reverses DLL using recursive approach.
     * Elegant but uses O(n) call stack space.
     * First recursively reverses rest of list, then swaps pointers.
     * 
     * @param head current head of DLL
     * @return new head
     */
    public static Node reverseDLLRecursive(Node head) {
        // Base case: empty or single node
        if (head == null || head.next == null) {
            return head;
        }
        
        // Recursively reverse rest of list
        Node newHead = reverseDLLRecursive(head.next);
        
        // After recursion returns, head is last to be reversed
        // Swap pointers for current node
        head.next.prev = head.next.next;
        head.next.next = head;
        head.prev = head.next;
        head.next = null;
        
        return newHead;
    }
    
    /**
     * Simpler recursive approach with helper function.
     * Reverses by recursing to end, then swapping pointers on way back.
     * 
     * @param head current head of DLL
     * @return new head
     */
    public static Node reverseDLLRecursiveHelper(Node head) {
        if (head == null) {
            return null;
        }
        
        reverseDLLRecursiveUtil(head);
        
        // Find new head (old tail)
        Node temp = head;
        while (temp.prev != null) {
            temp = temp.prev;
        }
        
        return temp;
    }
    
    /**
     * Utility function for recursive reversal.
     * Swaps prev and next pointers.
     * 
     * @param node current node
     */
    private static void reverseDLLRecursiveUtil(Node node) {
        if (node == null) {
            return;
        }
        
        // First recurse to end
        if (node.next != null) {
            reverseDLLRecursiveUtil(node.next);
        }
        
        // Then swap pointers (on way back from recursion)
        Node temp = node.next;
        node.next = node.prev;
        node.prev = temp;
    }
    
    /**
     * Reverses specific portion of DLL (from position1 to position2).
     * Demonstrates partial reversal capability.
     * 
     * @param head head of DLL
     * @param position1 start position (0-indexed)
     * @param position2 end position (0-indexed)
     * @return new head
     */
    public static Node reverseBetweenPositions(Node head, int position1, int position2) {
        if (head == null || position1 >= position2) {
            return head;
        }
        
        // Find nodes at position1 and position2
        Node curr = head;
        for (int i = 0; i < position1 && curr != null; i++) {
            curr = curr.next;
        }
        
        if (curr == null) {
            return head;
        }
        
        Node start = curr;
        for (int i = position1; i < position2 && curr.next != null; i++) {
            curr = curr.next;
        }
        
        Node end = curr;
        
        // Reverse between start and end
        Node temp = start;
        Node next;
        
        while (temp != end && temp != null) {
            next = temp.next;
            
            // Swap pointers
            Node prevNode = temp.prev;
            temp.prev = temp.next;
            temp.next = prevNode;
            
            temp = next;
        }
        
        // Don't forget last node
        if (temp != null) {
            Node prevNode = temp.prev;
            temp.prev = temp.next;
            temp.next = prevNode;
        }
        
        return head;
    }
    
    /**
     * Helper to display DLL forward.
     * 
     * @param head head of DLL
     */
    public static void displayForward(Node head) {
        if (head == null) {
            System.out.println("Forward: null <- [] -> null");
            return;
        }
        
        System.out.print("Forward:  null <- ");
        Node temp = head;
        
        while (temp != null) {
            System.out.print(temp.data);
            if (temp.next != null) {
                System.out.print(" <-> ");
            }
            temp = temp.next;
        }
        
        System.out.println(" -> null");
    }
    
    /**
     * Helper to display DLL backward.
     * 
     * @param head head of DLL
     */
    public static void displayBackward(Node head) {
        if (head == null) {
            System.out.println("Backward: null -> [] <- null");
            return;
        }
        
        // Find tail
        Node tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        
        System.out.print("Backward: null -> ");
        Node temp = tail;
        
        while (temp != null) {
            System.out.print(temp.data);
            if (temp.prev != null) {
                System.out.print(" <-> ");
            }
            temp = temp.prev;
        }
        
        System.out.println(" <- null");
    }
    
    /**
     * Helper to create DLL from array.
     * 
     * @param arr array of integers
     * @return head of created DLL
     */
    public static Node createFromArray(int[] arr) {
        if (arr.length == 0) {
            return null;
        }
        
        Node head = new Node(arr[0]);
        Node temp = head;
        
        for (int i = 1; i < arr.length; i++) {
            Node newNode = new Node(arr[i]);
            temp.next = newNode;
            newNode.prev = temp;
            temp = newNode;
        }
        
        return head;
    }
    
    /**
     * Helper to get length of DLL.
     * 
     * @param head head of DLL
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
     * Verifies if DLL is correctly reversed by checking pointer consistency.
     * 
     * @param head head of DLL
     * @return true if all pointers are consistent, false otherwise
     */
    public static boolean verifyDLL(Node head) {
        Node curr = head;
        
        while (curr != null) {
            // Check if prev.next points to current
            if (curr.prev != null && curr.prev.next != curr) {
                return false;
            }
            
            // Check if next.prev points to current
            if (curr.next != null && curr.next.prev != curr) {
                return false;
            }
            
            curr = curr.next;
        }
        
        return true;
    }
    
    public static void main(String[] args) {
        System.out.println("=== REVERSE DLL - ITERATIVE ===");
        Node head = createFromArray(new int[]{10, 20, 30, 40, 50});
        System.out.println("Original:");
        displayForward(head);
        displayBackward(head);
        
        head = reverseDLL(head);
        System.out.println("\nAfter reversal:");
        displayForward(head);
        displayBackward(head);
        System.out.println("DLL valid: " + verifyDLL(head));
        
        System.out.println("\n=== REVERSE DLL - EXPLICIT ===");
        head = createFromArray(new int[]{1, 2, 3, 4, 5});
        System.out.println("Original:");
        displayForward(head);
        
        head = reverseDLLExplicit(head);
        System.out.println("After reversal:");
        displayForward(head);
        displayBackward(head);
        
        System.out.println("\n=== REVERSE DLL - RECURSIVE ===");
        head = createFromArray(new int[]{100, 200, 300});
        System.out.println("Original:");
        displayForward(head);
        
        head = reverseDLLRecursive(head);
        System.out.println("After reversal:");
        displayForward(head);
        displayBackward(head);
        
        System.out.println("\n=== REVERSE DLL - RECURSIVE HELPER ===");
        head = createFromArray(new int[]{5, 10, 15, 20});
        System.out.println("Original:");
        displayForward(head);
        
        head = reverseDLLRecursiveHelper(head);
        System.out.println("After reversal:");
        displayForward(head);
        
        System.out.println("\n=== REVERSE BETWEEN POSITIONS ===");
        head = createFromArray(new int[]{10, 20, 30, 40, 50});
        System.out.println("Original:");
        displayForward(head);
        
        head = reverseBetweenPositions(head, 1, 3);
        System.out.println("After reversing positions 1-3:");
        displayForward(head);
        
        System.out.println("\n=== SINGLE NODE ===");
        head = new Node(42);
        System.out.println("Original:");
        displayForward(head);
        
        head = reverseDLL(head);
        System.out.println("After reversal:");
        displayForward(head);
        
        System.out.println("\n=== EMPTY LIST ===");
        head = null;
        System.out.println("Original:");
        displayForward(head);
        
        head = reverseDLL(head);
        System.out.println("After reversal:");
        displayForward(head);
    }
}
