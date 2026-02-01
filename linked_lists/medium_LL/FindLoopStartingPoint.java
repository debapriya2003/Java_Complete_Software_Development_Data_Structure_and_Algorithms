package linked_lists.medium_LL;

public class FindLoopStartingPoint {

    /*
    =====================================================================================
    PROBLEM: FIND THE STARTING POINT OF LOOP IN LINKEDLIST
    -------------------------------------------------------------------------------------
    Finding where a cycle begins requires mathematical insight. Using Floyd's Cycle 
    Detection, when slow and fast pointers meet inside cycle, the cycle start position
    can be found by this elegant approach: reset one pointer to head, keep other at 
    meeting point, move both one step at a time. Where they meet next is cycle start!
    This works because distance from head to cycle start equals distance from meeting
    point back to cycle start. Alternative: calculate cycle length, use two-pointer 
    with gap of cycle length, meet at cycle start. Or use HashSet to track all nodes,
    find first node whose next is already visited. Essential for breaking cycles in
    complex data structures. Interview-tested algorithm demonstrating deep understanding
    of cycle detection and mathematical reasoning about pointer movements in lists.

    Time Complexity: O(n) using Floyd's algorithm
    Space Complexity: O(1) optimal approach, O(n) for HashSet

    Example:
    1->2->3->4->5
         ^     |
         |_____|  (5 points to 2, cycle starts at 2)
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
     * Finds starting point of cycle using Floyd's algorithm.
     * When cycle detected, reset one pointer to head, move both one step.
     * Meeting point is cycle start. Mathematical property: distance from
     * head to cycle start equals distance from meeting point to cycle start.
     * 
     * @param head head of LinkedList
     * @return node where cycle starts, null if no cycle
     */
    public static Node findCycleStart(Node head) {
        if (head == null || head.next == null) {
            return null;
        }
        
        Node slow = head;
        Node fast = head;
        
        // Detect cycle using Floyd's algorithm
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            // Cycle detected
            if (slow == fast) {
                // Reset one pointer to head
                Node ptr1 = head;
                Node ptr2 = slow;
                
                // Move both one step until they meet
                while (ptr1 != ptr2) {
                    ptr1 = ptr1.next;
                    ptr2 = ptr2.next;
                }
                
                // Meeting point is cycle start
                return ptr1;
            }
        }
        
        // No cycle found
        return null;
    }
    
    /**
     * Finds cycle start using HashSet approach.
     * Tracks all visited nodes, returns node whose next is already visited.
     * Simpler logic but uses O(n) space.
     * 
     * @param head head of LinkedList
     * @return node where cycle starts, null if no cycle
     */
    public static Node findCycleStartHashSet(Node head) {
        if (head == null || head.next == null) {
            return null;
        }
        
        java.util.HashSet<Node> visited = new java.util.HashSet<>();
        Node temp = head;
        
        while (temp != null) {
            if (visited.contains(temp)) {
                // Found cycle, return start
                return temp;
            }
            
            visited.add(temp);
            temp = temp.next;
        }
        
        return null;
    }
    
    /**
     * Finds cycle start using length-based approach.
     * First find cycle length, then use two pointers with gap.
     * More complex but demonstrates cycle length calculation.
     * 
     * @param head head of LinkedList
     * @return node where cycle starts, null if no cycle
     */
    public static Node findCycleStartWithLength(Node head) {
        if (head == null || head.next == null) {
            return null;
        }
        
        Node slow = head;
        Node fast = head;
        
        // Detect cycle
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            if (slow == fast) {
                // Calculate cycle length
                int cycleLength = 1;
                Node temp = slow.next;
                
                while (temp != slow) {
                    cycleLength++;
                    temp = temp.next;
                }
                
                // Create two pointers with gap of cycle length
                Node ptr1 = head;
                Node ptr2 = head;
                
                // Move ptr2 ahead by cycle length
                for (int i = 0; i < cycleLength; i++) {
                    ptr2 = ptr2.next;
                }
                
                // Move both until they meet at cycle start
                while (ptr1 != ptr2) {
                    ptr1 = ptr1.next;
                    ptr2 = ptr2.next;
                }
                
                return ptr1;
            }
        }
        
        return null;
    }
    
    /**
     * Finds cycle start and returns detailed information.
     * Returns object containing start node, cycle length, and distance to start.
     * 
     * @param head head of LinkedList
     * @return CycleInfo with cycle details, null if no cycle
     */
    public static CycleInfo findCycleStartWithDetails(Node head) {
        if (head == null || head.next == null) {
            return null;
        }
        
        Node slow = head;
        Node fast = head;
        
        // Detect cycle
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            if (slow == fast) {
                // Calculate cycle length
                int cycleLength = 1;
                Node temp = slow.next;
                
                while (temp != slow) {
                    cycleLength++;
                    temp = temp.next;
                }
                
                // Find cycle start
                Node ptr1 = head;
                Node ptr2 = slow;
                int distanceToStart = 0;
                
                while (ptr1 != ptr2) {
                    ptr1 = ptr1.next;
                    ptr2 = ptr2.next;
                    distanceToStart++;
                }
                
                return new CycleInfo(ptr1, cycleLength, distanceToStart);
            }
        }
        
        return null;
    }
    
    /**
     * Helper class to store cycle information.
     */
    public static class CycleInfo {
        public Node cycleStart;
        public int cycleLength;
        public int distanceToStart;
        
        public CycleInfo(Node cycleStart, int cycleLength, int distanceToStart) {
            this.cycleStart = cycleStart;
            this.cycleLength = cycleLength;
            this.distanceToStart = distanceToStart;
        }
    }
    
    /**
     * Helper function to display LinkedList.
     * Shows cycle information if detected.
     * 
     * @param head head of LinkedList
     */
    public static void displayWithCycleInfo(Node head) {
        CycleInfo info = findCycleStartWithDetails(head);
        
        if (info == null) {
            System.out.println("List has no cycle");
        } else {
            System.out.println("Cycle detected!");
            System.out.println("  Cycle starts at node: " + info.cycleStart.data);
            System.out.println("  Cycle length: " + info.cycleLength);
            System.out.println("  Distance from head to cycle start: " + info.distanceToStart);
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
            
            if (i == cyclePosition) {
                cycleNode = temp;
            }
        }
        
        // Create cycle
        if (cyclePosition < arr.length) {
            temp.next = cycleNode;
        }
        
        return head;
    }
    
    public static void main(String[] args) {
        System.out.println("=== CYCLE AT POSITION 2 (value 3) ===");
        Node head = createCycleList(new int[]{1, 2, 3, 4, 5}, 2);
        Node cycleStart = findCycleStart(head);
        System.out.println("Cycle starts at node with value: " + (cycleStart != null ? cycleStart.data : "null"));
        displayWithCycleInfo(head);
        
        System.out.println("\n=== CYCLE AT POSITION 0 (value 1) ===");
        head = createCycleList(new int[]{1, 2, 3, 4, 5}, 0);
        cycleStart = findCycleStart(head);
        System.out.println("Cycle starts at node with value: " + (cycleStart != null ? cycleStart.data : "null"));
        displayWithCycleInfo(head);
        
        System.out.println("\n=== CYCLE AT POSITION 1 (value 2) ===");
        head = createCycleList(new int[]{1, 2, 3, 4, 5, 6, 7}, 1);
        cycleStart = findCycleStart(head);
        System.out.println("Cycle starts at node with value: " + (cycleStart != null ? cycleStart.data : "null"));
        displayWithCycleInfo(head);
        
        System.out.println("\n=== NO CYCLE ===");
        head = createFromArray(new int[]{1, 2, 3, 4, 5});
        cycleStart = findCycleStart(head);
        System.out.println("Cycle starts at node with value: " + (cycleStart != null ? cycleStart.data : "null"));
        displayWithCycleInfo(head);
        
        System.out.println("\n=== SELF LOOP ===");
        head = new Node(42);
        head.next = head;
        cycleStart = findCycleStart(head);
        System.out.println("Cycle starts at node with value: " + (cycleStart != null ? cycleStart.data : "null"));
        displayWithCycleInfo(head);
        
        System.out.println("\n=== COMPARE APPROACHES: CYCLE AT POS 3 ===");
        head = createCycleList(new int[]{10, 20, 30, 40, 50}, 3);
        System.out.println("Floyd's approach: " + (findCycleStart(head) != null ? findCycleStart(head).data : "null"));
        System.out.println("HashSet approach: " + (findCycleStartHashSet(head) != null ? findCycleStartHashSet(head).data : "null"));
        System.out.println("Length-based approach: " + (findCycleStartWithLength(head) != null ? findCycleStartWithLength(head).data : "null"));
        
        System.out.println("\n=== LONG LIST WITH LATE CYCLE ===");
        head = createCycleList(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 7);
        cycleStart = findCycleStart(head);
        System.out.println("Cycle starts at node with value: " + (cycleStart != null ? cycleStart.data : "null"));
        displayWithCycleInfo(head);
    }
}
