package linked_lists.medium_LL;

public class LengthOfLoopInLinkedList {

    /*
    =====================================================================================
    PROBLEM: LENGTH OF LOOP IN LINKEDLIST
    -------------------------------------------------------------------------------------
    Calculating loop length is important for understanding cycle characteristics.
    Using Floyd's Cycle Detection, once slow and fast pointers meet inside cycle,
    keep one pointer fixed while moving other. Count steps until they meet again.
    This count is cycle length. Can also find cycle length by: starting from meeting
    point, moving one step at a time until returning to same node, counting steps.
    Alternatively, calculate length when identifying cycle start: traverse from start
    until returning. Essential for cycle analysis, removing cycles, and validating
    list integrity. Multiple implementations provide different perspectives on same
    problem. Cycle length combined with distance-to-start determines full cycle
    structure. Interview-tested for problem-solving approach and pointer manipulation
    understanding, often paired with cycle detection and start-point finding problems.

    Time Complexity: O(n) to detect cycle and measure length
    Space Complexity: O(1) optimal approach

    Example:
    1->2->3->4->5
         ^     |
         |_____|  (cycle: 3->4->5->3, length = 3)
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
     * Finds length of cycle using Floyd's algorithm.
     * When slow and fast meet, one stays fixed while other counts steps back.
     * Simple and elegant approach.
     * 
     * @param head head of LinkedList
     * @return cycle length, 0 if no cycle
     */
    public static int findCycleLengthFloyd(Node head) {
        if (head == null || head.next == null) {
            return 0;
        }
        
        Node slow = head;
        Node fast = head;
        
        // Detect cycle
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            // Cycle detected
            if (slow == fast) {
                // Count cycle length
                int cycleLength = 1;
                Node temp = slow.next;
                
                // Move until returning to slow
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
     * Alternative: finds cycle length by traversing from meeting point.
     * After meeting, keep incrementing pointer and count steps.
     * Clear logic for understanding cycle measurement.
     * 
     * @param head head of LinkedList
     * @return cycle length, 0 if no cycle
     */
    public static int findCycleLengthWithinCycle(Node head) {
        if (head == null || head.next == null) {
            return 0;
        }
        
        Node slow = head;
        Node fast = head;
        
        // Find meeting point
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            if (slow == fast) {
                // At meeting point, start counting
                int count = 0;
                Node temp = slow;
                
                do {
                    temp = temp.next;
                    count++;
                } while (temp != slow);
                
                return count;
            }
        }
        
        return 0;
    }
    
    /**
     * Finds cycle length using two-pointer separation method.
     * Maintains fixed gap between two pointers during cycle.
     * Demonstrates distance-based cycle measurement.
     * 
     * @param head head of LinkedList
     * @return cycle length, 0 if no cycle
     */
    public static int findCycleLengthWithGap(Node head) {
        if (head == null || head.next == null) {
            return 0;
        }
        
        Node slow = head;
        Node fast = head;
        
        // Detect cycle
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            if (slow == fast) {
                // Reset fast to head, move both one step
                fast = head;
                int cycleLength = 0;
                
                // When gap becomes 1, cycle length identified
                while (slow.next != fast) {
                    slow = slow.next;
                    fast = fast.next;
                    cycleLength++;
                }
                
                // Add 1 for gap between them
                return cycleLength + 1;
            }
        }
        
        return 0;
    }
    
    /**
     * Comprehensive method returning cycle length and position info.
     * Useful for detailed cycle analysis.
     * 
     * @param head head of LinkedList
     * @return CycleLengthInfo with detailed measurements
     */
    public static CycleLengthInfo findCompleteCycleInfo(Node head) {
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
                // Measure cycle length
                int cycleLength = 1;
                Node temp = slow.next;
                
                while (temp != slow) {
                    cycleLength++;
                    temp = temp.next;
                }
                
                // Find cycle start and distance
                Node ptr1 = head;
                Node ptr2 = slow;
                int distanceToStart = 0;
                
                while (ptr1 != ptr2) {
                    ptr1 = ptr1.next;
                    ptr2 = ptr2.next;
                    distanceToStart++;
                }
                
                // Count total nodes until cycle repeats
                int totalNodesBeforeCycle = distanceToStart;
                int totalNodes = totalNodesBeforeCycle + cycleLength;
                
                return new CycleLengthInfo(cycleLength, distanceToStart, totalNodes);
            }
        }
        
        return null;
    }
    
    /**
     * Helper class for detailed cycle information.
     */
    public static class CycleLengthInfo {
        public int cycleLength;
        public int distanceToStart;
        public int totalNodes;
        
        public CycleLengthInfo(int cycleLength, int distanceToStart, int totalNodes) {
            this.cycleLength = cycleLength;
            this.distanceToStart = distanceToStart;
            this.totalNodes = totalNodes;
        }
        
        @Override
        public String toString() {
            return String.format("Cycle Length: %d, Distance to Start: %d, Total Nodes: %d",
                    cycleLength, distanceToStart, totalNodes);
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
     * 
     * @param arr array of integers
     * @param cyclePosition position to create cycle at
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
        
        if (cyclePosition < arr.length) {
            temp.next = cycleNode;
        }
        
        return head;
    }
    
    /**
     * Helper to display cycle information.
     * 
     * @param head head of LinkedList
     */
    public static void displayCycleInfo(Node head) {
        CycleLengthInfo info = findCompleteCycleInfo(head);
        
        if (info == null) {
            System.out.println("No cycle detected");
        } else {
            System.out.println(info);
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== CYCLE LENGTH 3 (POSITIONS 2-4) ===");
        Node head = createCycleList(new int[]{1, 2, 3, 4, 5}, 2);
        System.out.println("Floyd's method: " + findCycleLengthFloyd(head));
        System.out.println("Within cycle method: " + findCycleLengthWithinCycle(head));
        System.out.println("Gap method: " + findCycleLengthWithGap(head));
        displayCycleInfo(head);
        
        System.out.println("\n=== CYCLE LENGTH 5 (POSITIONS 0-4) ===");
        head = createCycleList(new int[]{1, 2, 3, 4, 5}, 0);
        System.out.println("Floyd's method: " + findCycleLengthFloyd(head));
        System.out.println("Within cycle method: " + findCycleLengthWithinCycle(head));
        System.out.println("Gap method: " + findCycleLengthWithGap(head));
        displayCycleInfo(head);
        
        System.out.println("\n=== CYCLE LENGTH 1 (SELF LOOP) ===");
        head = new Node(42);
        head.next = head;
        System.out.println("Floyd's method: " + findCycleLengthFloyd(head));
        System.out.println("Within cycle method: " + findCycleLengthWithinCycle(head));
        System.out.println("Gap method: " + findCycleLengthWithGap(head));
        displayCycleInfo(head);
        
        System.out.println("\n=== CYCLE LENGTH 2 (TWO-NODE CYCLE) ===");
        Node n1 = new Node(10);
        Node n2 = new Node(20);
        n1.next = n2;
        n2.next = n1;
        head = n1;
        System.out.println("Floyd's method: " + findCycleLengthFloyd(head));
        System.out.println("Within cycle method: " + findCycleLengthWithinCycle(head));
        System.out.println("Gap method: " + findCycleLengthWithGap(head));
        displayCycleInfo(head);
        
        System.out.println("\n=== NO CYCLE ===");
        head = createFromArray(new int[]{1, 2, 3, 4, 5});
        System.out.println("Floyd's method: " + findCycleLengthFloyd(head));
        System.out.println("Within cycle method: " + findCycleLengthWithinCycle(head));
        System.out.println("Gap method: " + findCycleLengthWithGap(head));
        displayCycleInfo(head);
        
        System.out.println("\n=== LARGE LIST WITH CYCLE LENGTH 4 ===");
        head = createCycleList(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 6);
        System.out.println("Floyd's method: " + findCycleLengthFloyd(head));
        System.out.println("Within cycle method: " + findCycleLengthWithinCycle(head));
        System.out.println("Gap method: " + findCycleLengthWithGap(head));
        displayCycleInfo(head);
        
        System.out.println("\n=== SINGLE NODE, NO CYCLE ===");
        head = new Node(100);
        System.out.println("Floyd's method: " + findCycleLengthFloyd(head));
        System.out.println("Within cycle method: " + findCycleLengthWithinCycle(head));
        System.out.println("Gap method: " + findCycleLengthWithGap(head));
        displayCycleInfo(head);
        
        System.out.println("\n=== CYCLE LENGTH 6 ===");
        head = createCycleList(new int[]{1, 2, 3, 4, 5, 6, 7}, 1);
        System.out.println("Floyd's method: " + findCycleLengthFloyd(head));
        System.out.println("Within cycle method: " + findCycleLengthWithinCycle(head));
        System.out.println("Gap method: " + findCycleLengthWithGap(head));
        displayCycleInfo(head);
    }
}
