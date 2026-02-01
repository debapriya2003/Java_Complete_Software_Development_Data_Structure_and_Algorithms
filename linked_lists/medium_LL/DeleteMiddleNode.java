package linked_lists.medium_LL;

public class DeleteMiddleNode {

    /*
    =====================================================================================
    PROBLEM: DELETE THE MIDDLE NODE OF LINKEDLIST
    -------------------------------------------------------------------------------------
    Deleting middle node is different from accessing by position because we want the true
    middle: if even length, typically remove right-middle. Approach: use slow-fast pointers
    where slow moves 1 step, fast moves 2 steps. When fast reaches end, slow is at middle.
    Then make previous node point to middle.next, skipping middle. Must track previous node
    to modify its pointer. Alternative: find length, calculate middle position, use two-pass.
    Can also mark middle and use another pass to delete. Key challenge: getting reference to
    node before middle for pointer adjustment. Unlike Nth-from-end, middle changes with list
    length so position must be computed. Different behavior for odd/even lengths: odd has
    clear middle, even has two potential middles. Common approach picks right-middle for even.
    Useful in algorithms like finding median, partitioning, list processing operations.

    Time Complexity: O(n) single pass with slow-fast pointers
    Space Complexity: O(1) excluding result

    Example:
    1->2->3->4->5, delete middle (3): 1->2->4->5
    1->2->3->4, delete middle (2 or 3): typically 1->3->4
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
     * Deletes middle node using slow-fast pointers.
     * Slow moves 1 step, fast moves 2 steps.
     * When fast reaches end, slow is at or near middle.
     * For even length, deletes right-middle.
     * 
     * @param head head of LinkedList
     * @return head of modified list
     */
    public static Node deleteMiddle(Node head) {
        // Handle edge cases
        if (head == null || head.next == null) {
            return null;
        }
        
        // Special case: two nodes
        if (head.next.next == null) {
            head.next = null;
            return head;
        }
        
        Node slow = head;
        Node fast = head;
        Node prev = null;
        
        // Move fast by 2, slow by 1, track previous
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // Delete middle node
        if (prev != null) {
            prev.next = slow.next;
        }
        
        return head;
    }
    
    /**
     * Alternative: find length first, then calculate middle.
     * Two passes but very clear about which node is deleted.
     * 
     * @param head head of LinkedList
     * @return head of modified list
     */
    public static Node deleteMiddleTwoPass(Node head) {
        // Find length
        int length = 0;
        Node temp = head;
        
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        
        // Single node
        if (length == 1) {
            return null;
        }
        
        // Find middle position (0-indexed)
        int middlePos = length / 2;
        
        // If deleting head
        if (middlePos == 0) {
            return head.next;
        }
        
        // Find node before middle
        temp = head;
        for (int i = 0; i < middlePos - 1; i++) {
            temp = temp.next;
        }
        
        // Delete
        temp.next = temp.next.next;
        
        return head;
    }
    
    /**
     * Recursive approach to delete middle.
     * Traverses to end, counts back to find middle.
     * Uses O(n) stack space but elegant logic.
     * 
     * @param head head of LinkedList
     * @return head of modified list
     */
    public static Node deleteMiddleRecursive(Node head) {
        RecursiveResult result = deleteUtil(head);
        return result.head;
    }
    
    /**
     * Helper for recursive deletion.
     * Counts position from end, identifies middle.
     * 
     * @param node current node
     * @return result with modified head and position
     */
    private static RecursiveResult deleteUtil(Node node) {
        // Base case
        if (node == null) {
            return new RecursiveResult(null, 0);
        }
        
        // Single node
        if (node.next == null) {
            return new RecursiveResult(null, 1);
        }
        
        // Recurse
        RecursiveResult result = deleteUtil(node.next);
        result.position++;
        
        // Get length (will be calculated on way back)
        int nodeCount = result.position;
        
        // Calculate middle position
        int middlePos = (nodeCount + 1) / 2;
        
        // Check if current is middle to delete
        if (result.position == middlePos + 1) {
            node.next = node.next.next;
        }
        
        result.head = node;
        return result;
    }
    
    /**
     * Helper class for recursive deletion.
     */
    private static class RecursiveResult {
        Node head;
        int position;
        
        RecursiveResult(Node head, int position) {
            this.head = head;
            this.position = position;
        }
    }
    
    /**
     * Version using dummy node for cleaner handling.
     * Dummy simplifies edge cases with head manipulation.
     * 
     * @param head head of LinkedList
     * @return head of modified list
     */
    public static Node deleteMiddleWithDummy(Node head) {
        // Handle single node
        if (head == null || head.next == null) {
            return null;
        }
        
        // Use dummy node
        Node dummy = new Node(0);
        dummy.next = head;
        
        Node slow = dummy;
        Node fast = dummy;
        
        // Move to find middle
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // Delete middle
        slow.next = slow.next.next;
        
        return dummy.next;
    }
    
    /**
     * Detailed version showing which middle is deleted.
     * Useful for understanding odd/even behavior.
     * 
     * @param head head of LinkedList
     * @return information about deleted node
     */
    public static DeletionInfo deleteMiddleDetailed(Node head) {
        if (head == null || head.next == null) {
            return new DeletionInfo(null, -1, -1);
        }
        
        Node slow = head;
        Node fast = head;
        Node prev = null;
        int position = 0;
        int deletedValue = -1;
        
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
            position++;
        }
        
        // Get deleted value before deletion
        deletedValue = slow.data;
        
        // Delete
        if (prev != null) {
            prev.next = slow.next;
        }
        
        return new DeletionInfo(head, position, deletedValue);
    }
    
    /**
     * Helper class for deletion information.
     */
    public static class DeletionInfo {
        public Node resultHead;
        public int deletedPosition;
        public int deletedValue;
        
        public DeletionInfo(Node resultHead, int deletedPosition, int deletedValue) {
            this.resultHead = resultHead;
            this.deletedPosition = deletedPosition;
            this.deletedValue = deletedValue;
        }
    }
    
    /**
     * Helper to display LinkedList.
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
        System.out.println("=== ODD LENGTH (5 NODES) ===");
        Node head = createFromArray(new int[]{1, 2, 3, 4, 5});
        System.out.println("Original:");
        display(head);
        System.out.println("Delete middle:");
        head = deleteMiddle(head);
        display(head);
        
        System.out.println("\n=== EVEN LENGTH (4 NODES) ===");
        head = createFromArray(new int[]{1, 2, 3, 4});
        System.out.println("Original:");
        display(head);
        System.out.println("Delete middle:");
        head = deleteMiddle(head);
        display(head);
        
        System.out.println("\n=== EVEN LENGTH (6 NODES) ===");
        head = createFromArray(new int[]{1, 2, 3, 4, 5, 6});
        System.out.println("Original:");
        display(head);
        System.out.println("Delete middle:");
        head = deleteMiddle(head);
        display(head);
        
        System.out.println("\n=== TWO NODES ===");
        head = createFromArray(new int[]{1, 2});
        System.out.println("Original:");
        display(head);
        System.out.println("Delete middle:");
        head = deleteMiddle(head);
        display(head);
        
        System.out.println("\n=== SINGLE NODE ===");
        head = new Node(42);
        System.out.println("Original:");
        display(head);
        System.out.println("Delete middle:");
        head = deleteMiddle(head);
        display(head);
        
        System.out.println("\n=== TWO-PASS APPROACH ===");
        head = createFromArray(new int[]{1, 2, 3, 4, 5});
        System.out.println("Original:");
        display(head);
        System.out.println("Delete middle (two-pass):");
        head = deleteMiddleTwoPass(head);
        display(head);
        
        System.out.println("\n=== RECURSIVE APPROACH ===");
        head = createFromArray(new int[]{1, 2, 3, 4, 5, 6, 7});
        System.out.println("Original:");
        display(head);
        System.out.println("Delete middle (recursive):");
        head = deleteMiddleRecursive(head);
        display(head);
        
        System.out.println("\n=== DETAILED DELETION INFO ===");
        head = createFromArray(new int[]{10, 20, 30, 40, 50});
        System.out.println("Original:");
        display(head);
        DeletionInfo info = deleteMiddleDetailed(head);
        System.out.println("Deleted value: " + info.deletedValue + " at position: " + info.deletedPosition);
        System.out.println("Result:");
        display(info.resultHead);
        
        System.out.println("\n=== WITH DUMMY NODE ===");
        head = createFromArray(new int[]{1, 2, 3, 4, 5});
        System.out.println("Original:");
        display(head);
        System.out.println("Delete middle (with dummy):");
        head = deleteMiddleWithDummy(head);
        display(head);
        
        System.out.println("\n=== LONG LIST ===");
        head = createFromArray(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11});
        System.out.println("Original:");
        display(head);
        System.out.println("Delete middle:");
        head = deleteMiddle(head);
        display(head);
    }
}
