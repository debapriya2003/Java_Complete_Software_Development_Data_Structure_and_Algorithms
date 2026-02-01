package linked_lists.medium_LL;

public class SegregateOddEvenNodes {

    /*
    =====================================================================================
    PROBLEM: SEGREGATE ODD AND EVEN NODES IN LINKEDLIST
    -------------------------------------------------------------------------------------
    Segregating odd and even positioned nodes means rearranging list so all odd-positioned
    nodes come before even-positioned nodes (or vice versa) while maintaining relative
    order within each group. Position counting: 1-indexed (first node is position 1, second
    is position 2, etc.). Approach: maintain separate odd and even lists, traverse original
    list, append to respective lists, then join. Alternative: in-place manipulation with
    careful pointer handling. Key insight: must maintain separate head pointers for odd and
    even chains, then connect them. Works for any length list. Must handle edge cases: empty,
    single node, all odd/even. Common in interview testing understanding of pointer chains
    and list manipulation. Demonstrates pattern useful for partitioning problems. Important
    to preserve relative order within odd and even groups, not just separate them arbitrarily.

    Time Complexity: O(n) single pass with pointer assignments
    Space Complexity: O(1) excluding output, or O(n) if counting new structure

    Example:
    1->2->3->4->5->6 becomes 1->3->5->2->4->6 (odd positions first)
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
     * Segregates odd and even positioned nodes.
     * Maintains separate chains for odd and even, then joins.
     * Single pass, clear logic.
     * 
     * @param head head of LinkedList
     * @return head of segregated list
     */
    public static Node segregateOddEven(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        Node oddHead = null;
        Node oddTail = null;
        Node evenHead = null;
        Node evenTail = null;
        
        Node temp = head;
        int position = 1;
        
        // Traverse and segregate
        while (temp != null) {
            if (position % 2 == 1) {
                // Odd position
                if (oddHead == null) {
                    oddHead = oddTail = temp;
                } else {
                    oddTail.next = temp;
                    oddTail = temp;
                }
            } else {
                // Even position
                if (evenHead == null) {
                    evenHead = evenTail = temp;
                } else {
                    evenTail.next = temp;
                    evenTail = temp;
                }
            }
            
            temp = temp.next;
            position++;
        }
        
        // Join chains
        if (oddTail != null) {
            oddTail.next = evenHead;
        }
        
        // Terminate last node
        if (evenTail != null) {
            evenTail.next = null;
        }
        
        return oddHead;
    }
    
    /**
     * Alternative: segregate even and odd (even first).
     * Useful for different ordering requirements.
     * 
     * @param head head of LinkedList
     * @return head of segregated list (even positions first)
     */
    public static Node segregateEvenOdd(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        Node evenHead = null;
        Node evenTail = null;
        Node oddHead = null;
        Node oddTail = null;
        
        Node temp = head;
        int position = 1;
        
        while (temp != null) {
            if (position % 2 == 0) {
                // Even position
                if (evenHead == null) {
                    evenHead = evenTail = temp;
                } else {
                    evenTail.next = temp;
                    evenTail = temp;
                }
            } else {
                // Odd position
                if (oddHead == null) {
                    oddHead = oddTail = temp;
                } else {
                    oddTail.next = temp;
                    oddTail = temp;
                }
            }
            
            temp = temp.next;
            position++;
        }
        
        // Join
        if (evenTail != null) {
            evenTail.next = oddHead;
        }
        
        if (oddTail != null) {
            oddTail.next = null;
        }
        
        return evenHead != null ? evenHead : oddHead;
    }
    
    /**
     * Segregate by odd/even values instead of positions.
     * Useful variant: separates nodes with odd values from even values.
     * 
     * @param head head of LinkedList
     * @return head with odd values first
     */
    public static Node segregateOddEvenValues(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        Node oddHead = null;
        Node oddTail = null;
        Node evenHead = null;
        Node evenTail = null;
        
        Node temp = head;
        
        while (temp != null) {
            if (temp.data % 2 == 1) {
                // Odd value
                if (oddHead == null) {
                    oddHead = oddTail = temp;
                } else {
                    oddTail.next = temp;
                    oddTail = temp;
                }
            } else {
                // Even value
                if (evenHead == null) {
                    evenHead = evenTail = temp;
                } else {
                    evenTail.next = temp;
                    evenTail = temp;
                }
            }
            
            temp = temp.next;
        }
        
        // Join
        if (oddTail != null) {
            oddTail.next = evenHead;
        }
        
        if (evenTail != null) {
            evenTail.next = null;
        }
        
        return oddHead;
    }
    
    /**
     * In-place segregation using pointer swapping.
     * More complex but avoids extra structures.
     * Works by rearranging existing pointers.
     * 
     * @param head head of LinkedList
     * @return segregated list head
     */
    public static Node segregateInPlace(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        Node evenHead = new Node(0);  // Dummy node
        Node oddHead = new Node(0);   // Dummy node
        
        Node even = evenHead;
        Node odd = oddHead;
        Node temp = head;
        int position = 1;
        
        while (temp != null) {
            if (position % 2 == 1) {
                odd.next = temp;
                odd = odd.next;
            } else {
                even.next = temp;
                even = even.next;
            }
            
            temp = temp.next;
            position++;
        }
        
        // Prevent cycles
        even.next = null;
        odd.next = evenHead.next;
        
        return oddHead.next;
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
        System.out.println("=== SEGREGATE ODD EVEN POSITIONS ===");
        Node head = createFromArray(new int[]{1, 2, 3, 4, 5, 6});
        System.out.println("Original:");
        display(head);
        System.out.println("Segregated (odd first):");
        display(segregateOddEven(head));
        
        System.out.println("\n=== SEGREGATE EVEN ODD POSITIONS ===");
        head = createFromArray(new int[]{1, 2, 3, 4, 5, 6});
        System.out.println("Original:");
        display(head);
        System.out.println("Segregated (even first):");
        display(segregateEvenOdd(head));
        
        System.out.println("\n=== SEGREGATE ODD EVEN VALUES ===");
        head = createFromArray(new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        System.out.println("Original:");
        display(head);
        System.out.println("Segregated by values (odd values first):");
        display(segregateOddEvenValues(head));
        
        System.out.println("\n=== IN-PLACE SEGREGATION ===");
        head = createFromArray(new int[]{10, 20, 30, 40, 50});
        System.out.println("Original:");
        display(head);
        System.out.println("In-place segregated (odd positions first):");
        display(segregateInPlace(head));
        
        System.out.println("\n=== ODD LENGTH LIST ===");
        head = createFromArray(new int[]{1, 2, 3, 4, 5});
        System.out.println("Original:");
        display(head);
        System.out.println("Segregated (odd first):");
        display(segregateOddEven(head));
        
        System.out.println("\n=== SINGLE NODE ===");
        head = new Node(42);
        System.out.println("Original:");
        display(head);
        System.out.println("Segregated (odd first):");
        display(segregateOddEven(head));
        
        System.out.println("\n=== TWO NODES ===");
        head = createFromArray(new int[]{1, 2});
        System.out.println("Original:");
        display(head);
        System.out.println("Segregated (odd first):");
        display(segregateOddEven(head));
        System.out.println("Segregated (even first):");
        display(segregateEvenOdd(head));
        
        System.out.println("\n=== ALL ODD VALUES ===");
        head = createFromArray(new int[]{1, 3, 5, 7, 9});
        System.out.println("Original:");
        display(head);
        System.out.println("Segregated by values:");
        display(segregateOddEvenValues(head));
        
        System.out.println("\n=== ALL EVEN VALUES ===");
        head = createFromArray(new int[]{2, 4, 6, 8, 10});
        System.out.println("Original:");
        display(head);
        System.out.println("Segregated by values:");
        display(segregateOddEvenValues(head));
        
        System.out.println("\n=== LONG LIST ===");
        head = createFromArray(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10});
        System.out.println("Original:");
        display(head);
        System.out.println("Segregated (odd positions first):");
        display(segregateOddEven(head));
    }
}
