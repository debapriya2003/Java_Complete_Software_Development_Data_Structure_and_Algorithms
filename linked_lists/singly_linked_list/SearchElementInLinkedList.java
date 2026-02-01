package linked_lists.singly_linked_list;

public class SearchElementInLinkedList {

    /*
    =====================================================================================
    PROBLEM: SEARCH AN ELEMENT IN THE LINKEDLIST
    -------------------------------------------------------------------------------------
    Searching in a LinkedList requires linear traversal from head until finding the 
    target element or reaching null. Since LinkedLists don't support random access, 
    binary search is impossible - we must do sequential search. Return found node's 
    position/reference or indicate not found. We can search by value (most common) or 
    by comparing custom criteria. Variations include: finding first/last occurrence, 
    counting occurrences, finding nodes with specific properties, searching with 
    predicates. Understanding search is fundamental before using found nodes for 
    operations like deletion or modification. This problem teaches traversal with 
    conditional stopping and returning meaningful information about search results.

    Time Complexity: O(n) in worst case (traverse entire list)
    Space Complexity: O(1) iterative, O(n) recursive

    Example:
    Search 30 in: 10->20->30->40
    Result: Found at position 2, return the node or index
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
     * Searches for element and returns the node containing it.
     * Traverses from head until finding matching element or reaching null.
     * Returns reference to found node (useful for subsequent operations)
     * or null if not found. Iterative approach.
     * 
     * @param head head of LinkedList
     * @param value value to search
     * @return node containing value or null if not found
     */
    public static Node searchElement(Node head, int value) {
        Node temp = head;
        
        // Traverse until finding value or reaching end
        while (temp != null) {
            if (temp.data == value) {
                return temp;  // Found
            }
            temp = temp.next;
        }
        
        return null;  // Not found
    }
    
    /**
     * Searches for element and returns position (0-indexed).
     * Returns -1 if element not found.
     * Position from beginning is useful for many operations.
     * 
     * @param head head of LinkedList
     * @param value value to search
     * @return position (0-indexed) or -1 if not found
     */
    public static int searchPosition(Node head, int value) {
        Node temp = head;
        int position = 0;
        
        while (temp != null) {
            if (temp.data == value) {
                return position;  // Found at this position
            }
            temp = temp.next;
            position++;
        }
        
        return -1;  // Not found
    }
    
    /**
     * Searches for element and returns position from end (0-indexed).
     * Position 0 = last node, position 1 = second to last, etc.
     * Returns -1 if not found.
     * Requires two passes: one to find element, one to get length.
     * 
     * @param head head of LinkedList
     * @param value value to search
     * @return position from end or -1 if not found
     */
    public static int searchPositionFromEnd(Node head, int value) {
        // First find total length
        int length = 0;
        Node temp = head;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        
        // Search for element
        temp = head;
        int positionFromBeginning = 0;
        while (temp != null) {
            if (temp.data == value) {
                // Calculate position from end
                return length - positionFromBeginning - 1;
            }
            temp = temp.next;
            positionFromBeginning++;
        }
        
        return -1;  // Not found
    }
    
    /**
     * Recursively searches for element in LinkedList.
     * Base case: head is null (not found) or head contains value.
     * Recursive case: search in rest of list.
     * Returns found node or null.
     * Uses call stack - O(n) space.
     * 
     * @param head head of LinkedList
     * @param value value to search
     * @return found node or null
     */
    public static Node searchRecursive(Node head, int value) {
        // Base case: empty list or not found
        if (head == null) {
            return null;
        }
        
        // Check current node
        if (head.data == value) {
            return head;
        }
        
        // Search in rest of list
        return searchRecursive(head.next, value);
    }
    
    /**
     * Counts how many times element appears in LinkedList.
     * Traverses entire list, incrementing count each time value matches.
     * Returns 0 if not found. Useful for determining occurrences.
     * 
     * @param head head of LinkedList
     * @param value value to count
     * @return number of occurrences
     */
    public static int countOccurrences(Node head, int value) {
        int count = 0;
        Node temp = head;
        
        while (temp != null) {
            if (temp.data == value) {
                count++;
            }
            temp = temp.next;
        }
        
        return count;
    }
    
    /**
     * Finds first node with value greater than given threshold.
     * Demonstrates searching with custom condition (not equality).
     * Returns found node or null if all elements <= threshold.
     * 
     * @param head head of LinkedList
     * @param threshold minimum value
     * @return first node with data > threshold, or null
     */
    public static Node findFirstGreaterThan(Node head, int threshold) {
        Node temp = head;
        
        while (temp != null) {
            if (temp.data > threshold) {
                return temp;
            }
            temp = temp.next;
        }
        
        return null;
    }
    
    /**
     * Finds node with maximum value in LinkedList.
     * Traverses entire list comparing each element.
     * Returns node with max value or null if empty.
     * 
     * @param head head of LinkedList
     * @return node with maximum data value
     */
    public static Node findMaxElement(Node head) {
        if (head == null) {
            return null;
        }
        
        Node maxNode = head;
        Node temp = head.next;
        
        while (temp != null) {
            if (temp.data > maxNode.data) {
                maxNode = temp;
            }
            temp = temp.next;
        }
        
        return maxNode;
    }
    
    /**
     * Finds element closest to target value.
     * Traverses list tracking minimum distance seen so far.
     * Returns node closest to target.
     * 
     * @param head head of LinkedList
     * @param target target value
     * @return node with value closest to target
     */
    public static Node findClosestToTarget(Node head, int target) {
        if (head == null) {
            return null;
        }
        
        Node closest = head;
        int minDistance = Math.abs(head.data - target);
        Node temp = head.next;
        
        while (temp != null) {
            int distance = Math.abs(temp.data - target);
            if (distance < minDistance) {
                minDistance = distance;
                closest = temp;
            }
            temp = temp.next;
        }
        
        return closest;
    }
    
    /**
     * Checks if LinkedList contains the element (boolean search).
     * Returns true if found, false otherwise.
     * Useful for quick existence check.
     * 
     * @param head head of LinkedList
     * @param value value to check
     * @return true if value exists in list
     */
    public static boolean contains(Node head, int value) {
        return searchElement(head, value) != null;
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
        System.out.println("=== SEARCH BY VALUE ===");
        Node head = createFromArray(new int[]{10, 20, 30, 40, 50});
        display(head);
        
        Node found = searchElement(head, 30);
        System.out.println("Search 30: " + (found != null ? "Found node with data " + found.data : "Not found"));
        
        found = searchElement(head, 25);
        System.out.println("Search 25: " + (found != null ? "Found" : "Not found"));
        
        System.out.println("\n=== SEARCH BY POSITION ===");
        int pos = searchPosition(head, 40);
        System.out.println("Search 40: Position = " + pos);
        
        pos = searchPosition(head, 10);
        System.out.println("Search 10: Position = " + pos);
        
        pos = searchPosition(head, 99);
        System.out.println("Search 99: Position = " + pos);
        
        System.out.println("\n=== SEARCH FROM END ===");
        int posFromEnd = searchPositionFromEnd(head, 30);
        System.out.println("Position of 30 from end: " + posFromEnd);
        
        posFromEnd = searchPositionFromEnd(head, 50);
        System.out.println("Position of 50 from end: " + posFromEnd);
        
        System.out.println("\n=== RECURSIVE SEARCH ===");
        found = searchRecursive(head, 20);
        System.out.println("Recursive search 20: " + (found != null ? "Found" : "Not found"));
        
        System.out.println("\n=== COUNT OCCURRENCES ===");
        head = createFromArray(new int[]{10, 20, 30, 20, 40, 20});
        display(head);
        System.out.println("Count of 20: " + countOccurrences(head, 20));
        System.out.println("Count of 10: " + countOccurrences(head, 10));
        System.out.println("Count of 99: " + countOccurrences(head, 99));
        
        System.out.println("\n=== FIND GREATER THAN ===");
        head = createFromArray(new int[]{10, 5, 30, 15, 40});
        display(head);
        Node greaterNode = findFirstGreaterThan(head, 20);
        System.out.println("First element > 20: " + 
            (greaterNode != null ? greaterNode.data : "Not found"));
        
        System.out.println("\n=== FIND MAX ELEMENT ===");
        Node maxNode = findMaxElement(head);
        System.out.println("Maximum element: " + (maxNode != null ? maxNode.data : "Not found"));
        
        System.out.println("\n=== FIND CLOSEST TO TARGET ===");
        head = createFromArray(new int[]{10, 20, 30, 40, 50});
        display(head);
        Node closest = findClosestToTarget(head, 25);
        System.out.println("Closest to 25: " + (closest != null ? closest.data : "Not found"));
        
        System.out.println("\n=== CONTAINS CHECK ===");
        System.out.println("Contains 30: " + contains(head, 30));
        System.out.println("Contains 35: " + contains(head, 35));
        
        System.out.println("\n=== EMPTY LIST SEARCH ===");
        Node emptyList = null;
        System.out.println("Search in empty list: " + 
            (searchElement(emptyList, 10) != null ? "Found" : "Not found"));
    }
}
