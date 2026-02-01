package linked_lists.singly_linked_list;

public class FindLengthOfLinkedList {

    /*
    =====================================================================================
    PROBLEM: FIND THE LENGTH OF THE LINKEDLIST [LEARN TRAVERSAL]
    -------------------------------------------------------------------------------------
    Finding the length of a LinkedList is fundamental operation requiring full traversal. 
    Start from head node and follow next pointers until reaching null. Count each node 
    visited. This teaches key concept of LinkedList traversal - the only way to access 
    nodes is by following pointers sequentially from head. Unlike arrays with random 
    access, we must visit every node. Traversal is basis for all LinkedList operations: 
    searching, inserting, deleting, reversing, etc. This problem solidifies understanding 
    of pointer following and null termination. We explore iterative approach, recursive 
    approach, and variations like finding length while collecting information about nodes.

    Time Complexity: O(n) where n is number of nodes (must visit all)
    Space Complexity: O(1) iterative, O(n) recursive (call stack)

    Example:
    LinkedList: 10 -> 20 -> 30 -> null
    Length: 3 (visit 3 nodes before reaching null)
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
     * Finds length of LinkedList using iterative traversal.
     * Starts from head and follows next pointers, incrementing counter
     * until reaching null. This is the standard approach, efficient and clear.
     * Demonstrates basic linked list traversal pattern.
     * 
     * @param head head of LinkedList
     * @return number of nodes in list
     */
    public static int findLength(Node head) {
        int length = 0;
        Node temp = head;
        
        // Traverse until reaching null
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        
        return length;
    }
    
    /**
     * Finds length using recursive approach.
     * Base case: if head is null, return 0 (no nodes).
     * Recursive case: return 1 + length of rest of list.
     * Elegant but uses call stack - O(n) extra space.
     * Less efficient than iterative for very long lists.
     * 
     * @param head head of LinkedList
     * @return number of nodes
     */
    public static int findLengthRecursive(Node head) {
        // Base case: empty list or reached end
        if (head == null) {
            return 0;
        }
        
        // Recursive case: 1 node + length of remaining list
        return 1 + findLengthRecursive(head.next);
    }
    
    /**
     * Finds length while also calculating sum of all elements.
     * Demonstrates collecting additional information during traversal.
     * Returns custom object containing both metrics.
     * Useful for multiple measurements in single pass.
     * 
     * @param head head of LinkedList
     * @return array [length, sum] or [0, 0] if empty
     */
    public static int[] getLengthAndSum(Node head) {
        int length = 0;
        int sum = 0;
        Node temp = head;
        
        while (temp != null) {
            length++;
            sum += temp.data;
            temp = temp.next;
        }
        
        return new int[]{length, sum};
    }
    
    /**
     * Finds length with minimum element.
     * Traverses list counting nodes and finding minimum value.
     * Demonstrates combining length finding with data collection.
     * 
     * @param head head of LinkedList
     * @return array [length, minElement] or [0, Integer.MAX_VALUE] if empty
     */
    public static int[] getLengthAndMin(Node head) {
        if (head == null) {
            return new int[]{0, Integer.MAX_VALUE};
        }
        
        int length = 0;
        int min = Integer.MAX_VALUE;
        Node temp = head;
        
        while (temp != null) {
            length++;
            min = Math.min(min, temp.data);
            temp = temp.next;
        }
        
        return new int[]{length, min};
    }
    
    /**
     * Finds length at specific distance from end.
     * Traverses entire list first to get length,
     * then verifies distance from end.
     * Useful for accessing nodes from the end.
     * 
     * @param head head of LinkedList
     * @param distanceFromEnd distance from last node (0 = last node)
     * @return node at that distance or null if out of bounds
     */
    public static Node getNodeAtDistanceFromEnd(Node head, int distanceFromEnd) {
        int length = findLength(head);
        
        // Position from beginning = length - 1 - distanceFromEnd
        int positionFromBeginning = length - 1 - distanceFromEnd;
        
        if (positionFromBeginning < 0) {
            return null;  // Out of bounds
        }
        
        // Traverse to position
        Node temp = head;
        int count = 0;
        
        while (count < positionFromBeginning && temp != null) {
            temp = temp.next;
            count++;
        }
        
        return temp;
    }
    
    /**
     * Two-pointer approach to find node at distance from end.
     * More efficient than computing length separately.
     * Move first pointer distanceFromEnd steps, then move both
     * pointers until first reaches end.
     * Second pointer then points to desired node.
     * 
     * @param head head of LinkedList
     * @param distanceFromEnd distance from end
     * @return node at distance from end or null
     */
    public static Node getNodeAtDistanceFromEndTwoPointer(Node head, int distanceFromEnd) {
        Node first = head;
        Node second = head;
        
        // Move first pointer distanceFromEnd steps ahead
        for (int i = 0; i < distanceFromEnd; i++) {
            if (first == null) {
                return null;  // Not enough nodes
            }
            first = first.next;
        }
        
        // If first pointer is now null, position was out of range
        if (first == null) {
            return null;
        }
        
        // Move both pointers until first reaches end
        while (first.next != null) {
            first = first.next;
            second = second.next;
        }
        
        return second;
    }
    
    /**
     * Counts nodes while printing each during traversal.
     * Demonstrates interactive traversal for debugging.
     * 
     * @param head head of LinkedList
     * @return length
     */
    public static int findLengthWithPrint(Node head) {
        int length = 0;
        Node temp = head;
        
        System.out.print("Traversing: ");
        while (temp != null) {
            System.out.print(temp.data + " -> ");
            length++;
            temp = temp.next;
        }
        System.out.println("null");
        
        return length;
    }
    
    /**
     * Helper to display LinkedList.
     * 
     * @param head head of LinkedList
     */
    public static void display(Node head) {
        if (head == null) {
            System.out.println("LinkedList: null (empty)");
            return;
        }
        
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
        System.out.println("=== FIND LENGTH - ITERATIVE ===");
        Node head = createFromArray(new int[]{10, 20, 30, 40});
        display(head);
        System.out.println("Length: " + findLength(head));
        
        System.out.println("\n=== FIND LENGTH - RECURSIVE ===");
        System.out.println("Length: " + findLengthRecursive(head));
        
        System.out.println("\n=== LENGTH WITH TRAVERSAL PRINTING ===");
        int len = findLengthWithPrint(head);
        System.out.println("Total length: " + len);
        
        System.out.println("\n=== EMPTY LIST ===");
        Node emptyList = null;
        System.out.println("Length of empty list: " + findLength(emptyList));
        
        System.out.println("\n=== SINGLE NODE ===");
        Node singleNode = new Node(42);
        System.out.println("Length: " + findLength(singleNode));
        
        System.out.println("\n=== LENGTH AND SUM ===");
        head = createFromArray(new int[]{10, 20, 30, 40});
        display(head);
        int[] lengthAndSum = getLengthAndSum(head);
        System.out.println("Length: " + lengthAndSum[0] + ", Sum: " + lengthAndSum[1]);
        
        System.out.println("\n=== LENGTH AND MIN ===");
        head = createFromArray(new int[]{50, 20, 80, 10, 40});
        display(head);
        int[] lengthAndMin = getLengthAndMin(head);
        System.out.println("Length: " + lengthAndMin[0] + ", Min: " + lengthAndMin[1]);
        
        System.out.println("\n=== NODE AT DISTANCE FROM END ===");
        head = createFromArray(new int[]{10, 20, 30, 40, 50});
        display(head);
        System.out.println("Total length: " + findLength(head));
        
        Node nodeAtDistance = getNodeAtDistanceFromEnd(head, 0);
        System.out.println("Node at distance 0 from end (last node): " + 
            (nodeAtDistance != null ? nodeAtDistance.data : "null"));
        
        nodeAtDistance = getNodeAtDistanceFromEnd(head, 2);
        System.out.println("Node at distance 2 from end: " + 
            (nodeAtDistance != null ? nodeAtDistance.data : "null"));
        
        System.out.println("\n=== TWO-POINTER APPROACH ===");
        nodeAtDistance = getNodeAtDistanceFromEndTwoPointer(head, 1);
        System.out.println("Node at distance 1 from end (two-pointer): " + 
            (nodeAtDistance != null ? nodeAtDistance.data : "null"));
        
        System.out.println("\n=== LARGE LIST ===");
        int[] largeArr = new int[1000];
        for (int i = 0; i < 1000; i++) {
            largeArr[i] = i;
        }
        Node largeList = createFromArray(largeArr);
        System.out.println("Length of large list (1000 nodes): " + findLength(largeList));
    }
}
