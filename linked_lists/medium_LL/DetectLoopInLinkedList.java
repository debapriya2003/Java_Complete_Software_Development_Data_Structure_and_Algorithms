package linked_lists.medium_LL;

public class DetectLoopInLinkedList {

    /*
    =====================================================================================
    PROBLEM: DETECT A LOOP IN LINKEDLIST
    -------------------------------------------------------------------------------------
    Detecting cycles (loops) in LinkedList is crucial for preventing infinite traversals
    and memory issues. Floyd's Cycle Detection Algorithm (tortoise-hare) uses two 
    pointers: slow moves 1 step, fast moves 2 steps. If they meet, cycle exists. This
    works because in circular list, fast pointer will eventually lap slow pointer.
    Alternative approaches: using HashSet to track visited nodes (O(n) space) or 
    mathematics-based detection. Floyd's algorithm is optimal: O(n) time, O(1) space.
    Can detect cycle start position by resetting one pointer to head and moving both
    at same speed. When they meet again, that's cycle start. Essential for linked list
    safety, often appears in interviews testing deep algorithm understanding.

    Time Complexity: O(n) Floyd's algorithm
    Space Complexity: O(1) for Floyd's, O(n) for HashSet approach

    Example:
    1->2->3->4->5
         ^     |
         |_____|  (5 points back to 2, cycle exists)
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
     * Detects cycle using Floyd's Cycle Detection (Tortoise-Hare).
     * Slow pointer moves 1 step, fast pointer moves 2 steps.
     * If they meet, cycle exists. If fast reaches null, no cycle.
     * 
     * @param head head of LinkedList
     * @return true if cycle exists, false otherwise
     */
    public static boolean detectCycleFloyd(Node head) {
        if (head == null || head.next == null) {
            return false;
        }
        
        Node slow = head;
        Node fast = head;
        
        // Move slow by 1, fast by 2
        while (fast != null && fast.next != null) {
            slow = slow.next;           // Move 1 step
            fast = fast.next.next;      // Move 2 steps
            
            // If they meet, cycle detected
            if (slow == fast) {
                return true;
            }
        }
        
        // Fast reached end, no cycle
        return false;
    }
    
    /**
     * Detects cycle using HashSet to track visited nodes.
     * Space-intensive but straightforward approach.
     * 
     * @param head head of LinkedList
     * @return true if cycle exists, false otherwise
     */
    public static boolean detectCycleHashSet(Node head) {
        if (head == null || head.next == null) {
            return false;
        }
        
        java.util.HashSet<Node> visited = new java.util.HashSet<>();
        Node temp = head;
        
        // Traverse and track visited nodes
        while (temp != null) {
            // If node already visited, cycle exists
            if (visited.contains(temp)) {
                return true;
            }
            
            visited.add(temp);
            temp = temp.next;
        }
        
        // Traversed without revisiting, no cycle
        return false;
    }
    
    /**
     * Alternative cycle detection by checking next chain.
     * Uses recursive approach to find depth limit.
     * If traversal depth exceeds list length, cycle exists.
     * 
     * @param head head of LinkedList
     * @return true if cycle exists, false otherwise
     */
    public static boolean detectCycleDepthCheck(Node head) {
        if (head == null || head.next == null) {
            return false;
        }
        
        // Calculate potential list length
        int maxLength = 0;
        Node temp = head;
        
        // Count nodes during traversal
        while (temp != null && maxLength < 1000000) {
            temp = temp.next;
            maxLength++;
        }
        
        // If we didn't reach null within reasonable limit, cycle likely exists
        return temp != null;
    }
    
    /**
     * Detects cycle and identifies cycle length.
     * Uses Floyd's algorithm first, then measures cycle.
     * 
     * @param head head of LinkedList
     * @return cycle length, 0 if no cycle
     */
    public static int detectCycleAndGetLength(Node head) {
        if (head == null || head.next == null) {
            return 0;
        }
        
        Node slow = head;
        Node fast = head;
        
        // Detect cycle using Floyd's algorithm
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            if (slow == fast) {
                // Cycle detected, measure length
                int cycleLength = 1;
                Node temp = slow.next;
                
                while (temp != slow) {
                    cycleLength++;
                    temp = temp.next;
                }
                
                return cycleLength;
            }
        }
        
        return 0; // No cycle
    }
    
    /**
     * Helper function to display LinkedList (non-circular portion).
     * Stops after fixed iterations to avoid infinite loop.
     * 
     * @param head head of LinkedList
     */
    public static void displayPartial(Node head) {
        System.out.print("LinkedList: ");
        Node temp = head;
        int count = 0;
        
        while (temp != null && count < 10) {
            System.out.print(temp.data + " -> ");
            temp = temp.next;
            count++;
        }
        
        if (temp != null) {
            System.out.println("...(cycle detected)");
        } else {
            System.out.println("null");
        }
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
     * Helper to create LinkedList with cycle.
     * Connects last node to node at given position.
     * 
     * @param arr array of integers
     * @param cyclePosition position to connect cycle to (0-indexed)
     * @return head of created LinkedList
     */
    public static Node createCycleList(int[] arr, int cyclePosition) {
        if (arr.length == 0) {
            return null;
        }
        
        Node head = new Node(arr[0]);
        Node temp = head;
        Node cycleNode = head;
        
        for (int i = 1; i < arr.length; i++) {
            temp.next = new Node(arr[i]);
            temp = temp.next;
            
            // Track position for cycle creation
            if (i == cyclePosition) {
                cycleNode = temp;
            }
        }
        
        // Create cycle by connecting last to middle node
        if (cyclePosition < arr.length) {
            temp.next = cycleNode;
        }
        
        return head;
    }
    
    public static void main(String[] args) {
        System.out.println("=== NO CYCLE TEST ===");
        Node head = createFromArray(new int[]{1, 2, 3, 4, 5});
        System.out.println("List without cycle:");
        displayPartial(head);
        System.out.println("Floyd's Detection: " + detectCycleFloyd(head));
        System.out.println("HashSet Detection: " + detectCycleHashSet(head));
        System.out.println("Cycle Length: " + detectCycleAndGetLength(head));
        
        System.out.println("\n=== CYCLE AT POSITION 2 ===");
        head = createCycleList(new int[]{1, 2, 3, 4, 5}, 2);
        System.out.println("List with cycle at position 2 (3->4->5->3):");
        displayPartial(head);
        System.out.println("Floyd's Detection: " + detectCycleFloyd(head));
        System.out.println("HashSet Detection: " + detectCycleHashSet(head));
        System.out.println("Cycle Length: " + detectCycleAndGetLength(head));
        
        System.out.println("\n=== SELF LOOP ===");
        head = new Node(42);
        head.next = head;  // Points to itself
        System.out.println("Single node pointing to itself:");
        displayPartial(head);
        System.out.println("Floyd's Detection: " + detectCycleFloyd(head));
        System.out.println("HashSet Detection: " + detectCycleHashSet(head));
        System.out.println("Cycle Length: " + detectCycleAndGetLength(head));
        
        System.out.println("\n=== EMPTY AND SINGLE NODE ===");
        System.out.println("Empty list - Floyd: " + detectCycleFloyd(null));
        System.out.println("Empty list - HashSet: " + detectCycleHashSet(null));
        
        Node single = new Node(100);
        System.out.println("Single node - Floyd: " + detectCycleFloyd(single));
        System.out.println("Single node - HashSet: " + detectCycleHashSet(single));
        
        System.out.println("\n=== TWO NODES WITH CYCLE ===");
        Node n1 = new Node(10);
        Node n2 = new Node(20);
        n1.next = n2;
        n2.next = n1;  // Cycle between two nodes
        System.out.println("Two nodes pointing to each other:");
        displayPartial(n1);
        System.out.println("Floyd's Detection: " + detectCycleFloyd(n1));
        System.out.println("Cycle Length: " + detectCycleAndGetLength(n1));
        
        System.out.println("\n=== CYCLE AT START ===");
        head = createCycleList(new int[]{1, 2, 3, 4, 5}, 0);
        System.out.println("List with cycle at position 0 (back to 1):");
        displayPartial(head);
        System.out.println("Floyd's Detection: " + detectCycleFloyd(head));
        System.out.println("Cycle Length: " + detectCycleAndGetLength(head));
    }
}
