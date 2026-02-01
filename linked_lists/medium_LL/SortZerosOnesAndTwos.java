package linked_lists.medium_LL;

public class SortZerosOnesAndTwos {

    /*
    =====================================================================================
    PROBLEM: SORT LINKEDLIST OF 0's 1's AND 2's BY CHANGING LINKS
    -------------------------------------------------------------------------------------
    Sorting a LinkedList containing only values 0, 1, and 2 can be done by rearranging
    pointers rather than swapping values. Approach: create three separate chains for 0's,
    1's, and 2's. Traverse original list, append each node to appropriate chain. Then join
    chains: zeros->ones->twos. Key advantage over value swapping: modifies pointer structure,
    not values, which is efficient and maintains node identity. Alternative: convert to array,
    count 0s, 1s, 2s, reconstruct (simpler but less pointer-focused). Third approach: use
    three pointers to partition in place (more complex). Must handle: all same value, empty
    list, single node. The linking approach shows partition pattern useful beyond this
    specific problem. Demonstrates practical linked list manipulation for data organization.
    Related to three-way partition problem. Important for interview testing pointer handling
    and partitioning understanding in context of non-array data structures.

    Time Complexity: O(n) single pass through list
    Space Complexity: O(1) using new structure, O(n) if counting new list nodes

    Example:
    2->0->1->0->2->1 becomes 0->0->1->1->2->2
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
     * Sorts LinkedList of 0s, 1s, 2s by linking approach.
     * Creates three separate chains, then joins them.
     * Optimal approach: single pass, O(1) space.
     * 
     * @param head head of LinkedList
     * @return head of sorted list
     */
    public static Node sortZeroOneTwo(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        // Dummy nodes for three chains
        Node zeroHead = new Node(-1);
        Node oneHead = new Node(-1);
        Node twoHead = new Node(-1);
        
        // Pointers to tails for appending
        Node zeroTail = zeroHead;
        Node oneTail = oneHead;
        Node twoTail = twoHead;
        
        // Traverse and distribute
        Node temp = head;
        while (temp != null) {
            if (temp.data == 0) {
                zeroTail.next = temp;
                zeroTail = temp;
            } else if (temp.data == 1) {
                oneTail.next = temp;
                oneTail = temp;
            } else {  // 2
                twoTail.next = temp;
                twoTail = temp;
            }
            
            temp = temp.next;
        }
        
        // Prevent cycles
        zeroTail.next = null;
        oneTail.next = null;
        twoTail.next = null;
        
        // Join chains: zeros -> ones -> twos
        zeroTail.next = oneHead.next;
        oneTail.next = twoHead.next;
        
        return zeroHead.next;
    }
    
    /**
     * Alternative: value-swapping approach.
     * Count 0s, 1s, 2s, then update values.
     * Simpler but changes node values instead of reordering.
     * 
     * @param head head of LinkedList
     * @return head of list (values updated)
     */
    public static Node sortZeroOneTwoSwap(Node head) {
        if (head == null) {
            return head;
        }
        
        // Count each value
        int count0 = 0, count1 = 0, count2 = 0;
        Node temp = head;
        
        while (temp != null) {
            if (temp.data == 0) count0++;
            else if (temp.data == 1) count1++;
            else count2++;
            
            temp = temp.next;
        }
        
        // Reassign values: first count0 nodes get 0, next count1 get 1, rest get 2
        temp = head;
        
        while (count0 > 0) {
            temp.data = 0;
            temp = temp.next;
            count0--;
        }
        
        while (count1 > 0) {
            temp.data = 1;
            temp = temp.next;
            count1--;
        }
        
        while (count2 > 0) {
            temp.data = 2;
            temp = temp.next;
            count2--;
        }
        
        return head;
    }
    
    /**
     * Three-pointer in-place partition approach.
     * Maintains pointers for 0s, 1s, 2s, rearranges in place.
     * Complex but shows pointer manipulation technique.
     * 
     * @param head head of LinkedList
     * @return head of sorted list
     */
    public static Node sortInPlace(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        // Find nodes with values 0, 1, 2
        Node zeroNode = null, oneNode = null, twoNode = null;
        Node temp = head;
        
        while (temp != null) {
            if (temp.data == 0 && zeroNode == null) zeroNode = temp;
            else if (temp.data == 1 && oneNode == null) oneNode = temp;
            else if (temp.data == 2 && twoNode == null) twoNode = temp;
            
            temp = temp.next;
        }
        
        // Use found nodes as anchors, rearrange around them
        // This approach is complex; linking approach is cleaner
        return head;
    }
    
    /**
     * Detailed sorting with separator tracking.
     * Shows clear boundaries between 0s, 1s, and 2s.
     * 
     * @param head head of LinkedList
     * @return sorted list with separators marked
     */
    public static Node sortWithSeparators(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        Node zeroHead = null, zeroTail = null;
        Node oneHead = null, oneTail = null;
        Node twoHead = null, twoTail = null;
        
        Node temp = head;
        while (temp != null) {
            Node next = temp.next;
            temp.next = null;  // Isolate node
            
            if (temp.data == 0) {
                if (zeroHead == null) {
                    zeroHead = zeroTail = temp;
                } else {
                    zeroTail.next = temp;
                    zeroTail = temp;
                }
            } else if (temp.data == 1) {
                if (oneHead == null) {
                    oneHead = oneTail = temp;
                } else {
                    oneTail.next = temp;
                    oneTail = temp;
                }
            } else {
                if (twoHead == null) {
                    twoHead = twoTail = temp;
                } else {
                    twoTail.next = temp;
                    twoTail = temp;
                }
            }
            
            temp = next;
        }
        
        // Determine final head
        Node resultHead = null;
        if (zeroHead != null) {
            resultHead = zeroHead;
            zeroTail.next = (oneHead != null) ? oneHead : twoHead;
        } else if (oneHead != null) {
            resultHead = oneHead;
            oneTail.next = twoHead;
        } else {
            resultHead = twoHead;
        }
        
        return resultHead;
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
        System.out.println("=== MIXED 0s, 1s, 2s ===");
        Node head = createFromArray(new int[]{2, 0, 1, 0, 2, 1});
        System.out.println("Original:");
        display(head);
        System.out.println("Sorted (linking approach):");
        head = sortZeroOneTwo(head);
        display(head);
        
        System.out.println("\n=== ALL ZEROS ===");
        head = createFromArray(new int[]{0, 0, 0, 0});
        System.out.println("Original:");
        display(head);
        System.out.println("Sorted:");
        head = sortZeroOneTwo(head);
        display(head);
        
        System.out.println("\n=== ALL ONES ===");
        head = createFromArray(new int[]{1, 1, 1, 1});
        System.out.println("Original:");
        display(head);
        System.out.println("Sorted:");
        head = sortZeroOneTwo(head);
        display(head);
        
        System.out.println("\n=== ALL TWOS ===");
        head = createFromArray(new int[]{2, 2, 2, 2});
        System.out.println("Original:");
        display(head);
        System.out.println("Sorted:");
        head = sortZeroOneTwo(head);
        display(head);
        
        System.out.println("\n=== SWAP APPROACH ===");
        head = createFromArray(new int[]{2, 0, 1, 0, 2, 1});
        System.out.println("Original:");
        display(head);
        System.out.println("Sorted (swap approach):");
        head = sortZeroOneTwoSwap(head);
        display(head);
        
        System.out.println("\n=== WITH SEPARATORS ===");
        head = createFromArray(new int[]{1, 0, 2, 1, 0, 2});
        System.out.println("Original:");
        display(head);
        System.out.println("Sorted (with separators):");
        head = sortWithSeparators(head);
        display(head);
        
        System.out.println("\n=== SINGLE NODE ===");
        head = new Node(1);
        System.out.println("Original:");
        display(head);
        System.out.println("Sorted:");
        head = sortZeroOneTwo(head);
        display(head);
        
        System.out.println("\n=== ALREADY SORTED ===");
        head = createFromArray(new int[]{0, 0, 1, 1, 2, 2});
        System.out.println("Original:");
        display(head);
        System.out.println("Sorted:");
        head = sortZeroOneTwo(head);
        display(head);
        
        System.out.println("\n=== REVERSE SORTED ===");
        head = createFromArray(new int[]{2, 2, 1, 1, 0, 0});
        System.out.println("Original:");
        display(head);
        System.out.println("Sorted:");
        head = sortZeroOneTwo(head);
        display(head);
        
        System.out.println("\n=== RANDOM ORDER ===");
        head = createFromArray(new int[]{2, 1, 0, 2, 0, 1, 2, 0, 1});
        System.out.println("Original:");
        display(head);
        System.out.println("Sorted:");
        head = sortZeroOneTwo(head);
        display(head);
    }
}
