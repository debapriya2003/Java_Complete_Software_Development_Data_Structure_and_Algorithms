package linked_lists.medium_LL;

public class MiddleOfLinkedList {

    /*
    =====================================================================================
    PROBLEM: MIDDLE OF A LINKEDLIST (TORTOISE-HARE METHOD)
    -------------------------------------------------------------------------------------
    Find the middle node of a LinkedList without knowing its length beforehand. The 
    tortoise-hare method uses two pointers moving at different speeds: slow (moves 1 
    step) and fast (moves 2 steps). When fast pointer reaches end, slow pointer points 
    to middle. This elegant approach requires only single traversal without calculating 
    length first. For even-length lists, middle can be defined as first node of second 
    half. Also called Floyd's cycle detection algorithm when adapted. Time complexity 
    is O(n) with O(1) space - much better than two-pass approach. Method works for any 
    list length including single node, empty list, and circular references.

    Time Complexity: O(n) single pass with two pointers
    Space Complexity: O(1) constant space

    Example:
    LinkedList: 1->2->3->4->5
    Middle: 3 (5 nodes, middle is at position 2)
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
     * Finds middle node using tortoise-hare (slow-fast) method.
     * Slow pointer moves 1 step, fast moves 2 steps.
     * When fast reaches end, slow is at middle.
     * Works correctly for both odd and even length lists.
     * 
     * @param head head of LinkedList
     * @return node at middle position
     */
    public static Node findMiddle(Node head) {
        if (head == null) {
            return null;
        }
        
        Node slow = head;
        Node fast = head;
        
        // Move slow by 1, fast by 2
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return slow;  // Middle node
    }
    
    /**
     * Alternative tortoise-hare with explicit tracking.
     * More verbose but clearer for understanding.
     * 
     * @param head head of LinkedList
     * @return node at middle position
     */
    public static Node findMiddleExplicit(Node head) {
        if (head == null) {
            return null;
        }
        
        Node slow = head;
        Node fast = head;
        
        // Continue until fast pointer reaches end
        while (fast != null && fast.next != null) {
            slow = slow.next;      // Move 1 step
            fast = fast.next.next;  // Move 2 steps
        }
        
        return slow;
    }
    
    /**
     * Finds middle and returns middle value.
     * Similar to findMiddle but returns value instead of node.
     * 
     * @param head head of LinkedList
     * @return middle value or -1 if empty
     */
    public static int getMiddleValue(Node head) {
        Node middle = findMiddle(head);
        return middle != null ? middle.data : -1;
    }
    
    /**
     * Finds both middle node and previous node to middle.
     * Useful if we need to perform operations before middle node.
     * Returns array [previous node, middle node].
     * 
     * @param head head of LinkedList
     * @return array with [prevToMiddle, middle] nodes
     */
    public static Node[] findMiddleWithPrevious(Node head) {
        if (head == null) {
            return new Node[]{null, null};
        }
        
        Node slow = head;
        Node fast = head;
        Node prevSlow = null;
        
        while (fast != null && fast.next != null) {
            prevSlow = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        
        return new Node[]{prevSlow, slow};
    }
    
    /**
     * Finds middle of odd-length list (single middle element).
     * For even-length lists, returns first element of second half.
     * This is the standard definition.
     * 
     * @param head head of LinkedList
     * @return middle node
     */
    public static Node findMiddleOddLength(Node head) {
        return findMiddle(head);
    }
    
    /**
     * Two-pass approach: find length, then find middle.
     * Less elegant but demonstrates alternative approach.
     * O(n) time, O(1) space but requires 2 traversals.
     * 
     * @param head head of LinkedList
     * @return middle node
     */
    public static Node findMiddleTwoPass(Node head) {
        if (head == null) {
            return null;
        }
        
        // First pass: count length
        int length = 0;
        Node temp = head;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        
        // Second pass: reach middle
        int midPosition = length / 2;
        temp = head;
        for (int i = 0; i < midPosition; i++) {
            temp = temp.next;
        }
        
        return temp;
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
        System.out.println("=== ODD LENGTH LIST ===");
        Node head = createFromArray(new int[]{1, 2, 3, 4, 5});
        display(head);
        
        Node middle = findMiddle(head);
        System.out.println("Middle: " + (middle != null ? middle.data : "null"));
        
        System.out.println("\n=== EVEN LENGTH LIST ===");
        head = createFromArray(new int[]{1, 2, 3, 4, 5, 6});
        display(head);
        
        middle = findMiddle(head);
        System.out.println("Middle (first of second half): " + (middle != null ? middle.data : "null"));
        
        System.out.println("\n=== SINGLE NODE ===");
        head = new Node(42);
        display(head);
        
        middle = findMiddle(head);
        System.out.println("Middle: " + (middle != null ? middle.data : "null"));
        
        System.out.println("\n=== TWO NODES ===");
        head = createFromArray(new int[]{10, 20});
        display(head);
        
        middle = findMiddle(head);
        System.out.println("Middle (first of second half): " + (middle != null ? middle.data : "null"));
        
        System.out.println("\n=== EMPTY LIST ===");
        head = null;
        display(head);
        
        middle = findMiddle(head);
        System.out.println("Middle: " + (middle != null ? middle.data : "null"));
        
        System.out.println("\n=== LARGE LIST ===");
        int[] largeArr = new int[101];
        for (int i = 0; i < 101; i++) {
            largeArr[i] = i + 1;
        }
        head = createFromArray(largeArr);
        System.out.println("Large list length: " + getLength(head));
        
        middle = findMiddle(head);
        System.out.println("Middle of list with 101 nodes: " + (middle != null ? middle.data : "null"));
        
        System.out.println("\n=== TWO-PASS APPROACH ===");
        head = createFromArray(new int[]{10, 20, 30, 40});
        display(head);
        
        middle = findMiddleTwoPass(head);
        System.out.println("Middle (two-pass): " + (middle != null ? middle.data : "null"));
        
        System.out.println("\n=== MIDDLE WITH PREVIOUS NODE ===");
        head = createFromArray(new int[]{1, 2, 3, 4, 5});
        display(head);
        
        Node[] result = findMiddleWithPrevious(head);
        System.out.println("Previous to middle: " + (result[0] != null ? result[0].data : "null"));
        System.out.println("Middle: " + (result[1] != null ? result[1].data : "null"));
    }
}
