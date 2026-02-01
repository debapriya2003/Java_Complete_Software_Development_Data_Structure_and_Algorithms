package linked_lists.medium_LL;

public class AddOneToNumberInLinkedList {

    /*
    =====================================================================================
    PROBLEM: ADD 1 TO A NUMBER REPRESENTED BY LINKEDLIST
    -------------------------------------------------------------------------------------
    A LinkedList can represent a number with each node containing single digit (0-9), most
    significant digit at head. Adding 1 means incrementing least significant digit (tail)
    with carry handling. Approach: (1) Reverse list, add 1 to head with carry, reverse back
    (O(n) time, modifies structure). (2) Recursive: traverse to end, add 1 during recursion
    unwinding, handle carry (O(n) space stack). (3) Find last non-9 node, increment it,
    set all 9s after to 0 (O(n) time, O(1) space, elegant). Edge case: all 9s become
    10...0 (need new head). Key insight: carry propagation from right to left. Similar
    problems involve adding numbers directly. Most elegant approach avoids reversal by
    finding rightmost non-9 digit, incrementing it, zeroing rest. Must handle: single
    digit, all 9s (need new node), single node. Interview problem testing digit manipulation,
    carry handling, and pointer management. Shows how classical arithmetic adapts to linked
    structure with different access patterns. Related to arithmetic on linked structures.

    Time Complexity: O(n) all approaches
    Space Complexity: O(1) optimal, O(n) recursive

    Example:
    List: 9->9->9 represents 999, add 1: 1->0->0->0 (1000)
    List: 1->2->3 represents 123, add 1: 1->2->4 (124)
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
     * Adds 1 by reversing, incrementing head, reversing back.
     * Clear logic but modifies list structure temporarily.
     * 
     * @param head head of LinkedList
     * @return head of list with 1 added
     */
    public static Node addOne(Node head) {
        // Reverse list
        head = reverseList(head);
        
        // Add 1 with carry handling
        int carry = 1;
        Node temp = head;
        
        while (temp != null && carry > 0) {
            int sum = temp.data + carry;
            temp.data = sum % 10;
            carry = sum / 10;
            
            // If last node and carry remains, need new node
            if (temp.next == null && carry > 0) {
                temp.next = new Node(carry);
                carry = 0;
            }
            
            temp = temp.next;
        }
        
        // Reverse back
        head = reverseList(head);
        
        return head;
    }
    
    /**
     * Adds 1 using recursive approach.
     * Traverses to end, adds during unwinding, handles carry.
     * Uses O(n) stack space but elegant recursion.
     * 
     * @param head head of LinkedList
     * @return head of list with 1 added
     */
    public static Node addOneRecursive(Node head) {
        RecursiveResult result = addOneUtil(head);
        
        // If carry remains, need new head
        if (result.carry > 0) {
            Node newHead = new Node(result.carry);
            newHead.next = head;
            return newHead;
        }
        
        return head;
    }
    
    /**
     * Helper for recursive addition.
     * Returns carry from recursive unwinding.
     * 
     * @param node current node
     * @return result with carry
     */
    private static RecursiveResult addOneUtil(Node node) {
        // Base case: reached end
        if (node == null) {
            return new RecursiveResult(1);
        }
        
        // Recurse to end
        RecursiveResult result = addOneUtil(node.next);
        
        // Add carry to current
        int sum = node.data + result.carry;
        node.data = sum % 10;
        result.carry = sum / 10;
        
        return result;
    }
    
    /**
     * Helper class for recursive result.
     */
    private static class RecursiveResult {
        int carry;
        
        RecursiveResult(int carry) {
            this.carry = carry;
        }
    }
    
    /**
     * Optimal approach: find last non-9 node, increment, zero rest.
     * Avoids reversal, handles carry intelligently.
     * Most efficient approach.
     * 
     * @param head head of LinkedList
     * @return head of list with 1 added
     */
    public static Node addOneOptimal(Node head) {
        // Find last non-9 node
        Node lastNonNine = null;
        Node temp = head;
        
        while (temp != null) {
            if (temp.data != 9) {
                lastNonNine = temp;
            }
            temp = temp.next;
        }
        
        // Case 1: No non-9 node (all 9s)
        if (lastNonNine == null) {
            Node newHead = new Node(1);
            // Set all 9s to 0
            temp = head;
            while (temp != null) {
                temp.data = 0;
                temp = temp.next;
            }
            newHead.next = head;
            return newHead;
        }
        
        // Case 2: Found non-9 node
        // Increment it
        lastNonNine.data++;
        
        // Set all 9s after it to 0
        temp = lastNonNine.next;
        while (temp != null) {
            temp.data = 0;
            temp = temp.next;
        }
        
        return head;
    }
    
    /**
     * Adds 1 using in-place modification from end.
     * Single pass from head to end.
     * 
     * @param head head of LinkedList
     * @return head of list with 1 added
     */
    public static Node addOneInPlace(Node head) {
        // Handle empty
        if (head == null) {
            return new Node(1);
        }
        
        // Add 1 from tail
        int carry = addHelper(head);
        
        // If carry remains, add new node at head
        if (carry > 0) {
            Node newHead = new Node(carry);
            newHead.next = head;
            return newHead;
        }
        
        return head;
    }
    
    /**
     * Helper to propagate carry backwards.
     * 
     * @param node current node
     * @return carry to propagate up
     */
    private static int addHelper(Node node) {
        // Base case: reached end
        if (node == null) {
            return 1;
        }
        
        // Recurse to next
        int carry = addHelper(node.next);
        
        // Add carry to current
        int sum = node.data + carry;
        node.data = sum % 10;
        
        return sum / 10;
    }
    
    /**
     * Reverses LinkedList for helper function.
     * 
     * @param head head to reverse
     * @return new head
     */
    private static Node reverseList(Node head) {
        Node prev = null;
        Node current = head;
        
        while (current != null) {
            Node next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        
        return prev;
    }
    
    /**
     * Helper to display LinkedList.
     * 
     * @param head head of LinkedList
     */
    public static void display(Node head) {
        System.out.print("Number: ");
        Node temp = head;
        
        while (temp != null) {
            System.out.print(temp.data);
            temp = temp.next;
        }
        
        System.out.println();
    }
    
    /**
     * Helper to create LinkedList from array.
     * 
     * @param arr array of digits
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
        System.out.println("=== SIMPLE NUMBER 123 ===");
        Node head = createFromArray(new int[]{1, 2, 3});
        System.out.print("Original: ");
        display(head);
        head = addOne(head);
        System.out.print("Add 1:    ");
        display(head);
        
        System.out.println("\n=== ALL 9s (999) ===");
        head = createFromArray(new int[]{9, 9, 9});
        System.out.print("Original: ");
        display(head);
        head = addOne(head);
        System.out.print("Add 1:    ");
        display(head);
        
        System.out.println("\n=== ENDING WITH 9 (129) ===");
        head = createFromArray(new int[]{1, 2, 9});
        System.out.print("Original: ");
        display(head);
        head = addOne(head);
        System.out.print("Add 1:    ");
        display(head);
        
        System.out.println("\n=== SINGLE DIGIT 5 ===");
        head = new Node(5);
        System.out.print("Original: ");
        display(head);
        head = addOne(head);
        System.out.print("Add 1:    ");
        display(head);
        
        System.out.println("\n=== SINGLE DIGIT 9 ===");
        head = new Node(9);
        System.out.print("Original: ");
        display(head);
        head = addOne(head);
        System.out.print("Add 1:    ");
        display(head);
        
        System.out.println("\n=== OPTIMAL APPROACH (999) ===");
        head = createFromArray(new int[]{9, 9, 9});
        System.out.print("Original: ");
        display(head);
        head = addOneOptimal(head);
        System.out.print("Add 1:    ");
        display(head);
        
        System.out.println("\n=== RECURSIVE APPROACH (456) ===");
        head = createFromArray(new int[]{4, 5, 6});
        System.out.print("Original: ");
        display(head);
        head = addOneRecursive(head);
        System.out.print("Add 1:    ");
        display(head);
        
        System.out.println("\n=== IN-PLACE APPROACH (789) ===");
        head = createFromArray(new int[]{7, 8, 9});
        System.out.print("Original: ");
        display(head);
        head = addOneInPlace(head);
        System.out.print("Add 1:    ");
        display(head);
        
        System.out.println("\n=== MULTIPLE CARRIES (1999) ===");
        head = createFromArray(new int[]{1, 9, 9, 9});
        System.out.print("Original: ");
        display(head);
        head = addOne(head);
        System.out.print("Add 1:    ");
        display(head);
        
        System.out.println("\n=== LARGE NUMBER ===");
        head = createFromArray(new int[]{9, 9, 9, 9, 8});
        System.out.print("Original: ");
        display(head);
        head = addOneOptimal(head);
        System.out.print("Add 1:    ");
        display(head);
    }
}
