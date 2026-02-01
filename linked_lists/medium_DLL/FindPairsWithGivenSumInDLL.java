package linked_lists.medium_DLL;

import java.util.ArrayList;
import java.util.List;

public class FindPairsWithGivenSumInDLL {

    /*
    =====================================================================================
    PROBLEM: FIND PAIRS WITH GIVEN SUM IN DOUBLY LINKED LIST
    -------------------------------------------------------------------------------------
    Given a doubly linked list and a sum, find all pairs of nodes whose data sum equals
    the given sum. Each pair should be printed/returned only once.
    
    Example:
    DLL: 1 <-> 2 <-> 3 <-> 4 <-> 5
    Sum: 6
    Pairs: (1,5), (2,4), (3,3) - but 3,3 is invalid as we need two different nodes
    Result: (1,5), (2,4)
    
    Approach 1: Two Pointer Technique (Optimal)
    1. Place one pointer at head and another at tail
    2. If sum equals target, add pair and move pointers
    3. If sum < target, move left pointer forward
    4. If sum > target, move right pointer backward
    
    Time Complexity: O(n) - single pass with two pointers
    Space Complexity: O(1) excluding output space
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
     * Pair class to store pairs of nodes.
     */
    public static class Pair {
        public int first;
        public int second;
        
        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
        
        @Override
        public String toString() {
            return "(" + first + ", " + second + ")";
        }
    }
    
    /**
     * Find all pairs in doubly linked list with given sum using two-pointer technique.
     * 
     * Algorithm:
     * 1. Initialize left pointer at head and right pointer at tail
     * 2. While left < right:
     *    - Calculate sum of current nodes
     *    - If sum equals target: add pair and move both pointers
     *    - If sum < target: move left pointer forward (increase sum)
     *    - If sum > target: move right pointer backward (decrease sum)
     * 
     * @param head head of the doubly linked list
     * @param target target sum to find
     * @return list of pairs that sum to target
     */
    public static List<Pair> findPairsWithSum(Node head, int target) {
        List<Pair> pairs = new ArrayList<>();
        
        // Handle empty or single node list
        if (head == null || head.next == null) {
            return pairs;
        }
        
        // Find tail
        Node tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        
        // Two pointer approach
        Node left = head;
        Node right = tail;
        
        while (left != null && right != null && left != right && left.prev != right) {
            int sum = left.data + right.data;
            
            if (sum == target) {
                // Found a pair
                pairs.add(new Pair(left.data, right.data));
                // Move both pointers
                left = left.next;
                right = right.prev;
            } else if (sum < target) {
                // Sum is less, move left pointer forward to increase sum
                left = left.next;
            } else {
                // Sum is more, move right pointer backward to decrease sum
                right = right.prev;
            }
        }
        
        return pairs;
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
    
    public static void main(String[] args) {
        // Test Case 1: Standard case with multiple pairs
        int[] arr1 = {1, 2, 3, 4, 5};
        Node head1 = createDLL(arr1);
        System.out.println("DLL: " + printDLL(head1));
        List<Pair> pairs1 = findPairsWithSum(head1, 6);
        System.out.println("Pairs with sum 6: " + pairs1);
        System.out.println();
        
        // Test Case 2: No pairs found
        int[] arr2 = {1, 2, 3};
        Node head2 = createDLL(arr2);
        System.out.println("DLL: " + printDLL(head2));
        List<Pair> pairs2 = findPairsWithSum(head2, 10);
        System.out.println("Pairs with sum 10: " + pairs2);
        System.out.println();
        
        // Test Case 3: Pairs with larger list
        int[] arr3 = {1, 2, 3, 4, 5, 6, 7};
        Node head3 = createDLL(arr3);
        System.out.println("DLL: " + printDLL(head3));
        List<Pair> pairs3 = findPairsWithSum(head3, 8);
        System.out.println("Pairs with sum 8: " + pairs3);
        System.out.println();
        
        // Test Case 4: All elements same
        int[] arr4 = {5, 5, 5, 5};
        Node head4 = createDLL(arr4);
        System.out.println("DLL: " + printDLL(head4));
        List<Pair> pairs4 = findPairsWithSum(head4, 10);
        System.out.println("Pairs with sum 10: " + pairs4);
    }
}
