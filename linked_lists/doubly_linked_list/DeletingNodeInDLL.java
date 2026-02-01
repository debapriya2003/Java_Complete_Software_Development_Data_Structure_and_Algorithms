package linked_lists.doubly_linked_list;

public class DeletingNodeInDLL {

    /*
    =====================================================================================
    PROBLEM: DELETE A NODE IN DOUBLY LINKED LIST
    -------------------------------------------------------------------------------------
    Deletion in DLL requires updating both prev and next pointers of neighboring nodes, 
    making it slightly more complex than insertion. We can delete from different 
    positions: beginning (remove head), middle (remove between nodes), or end (remove 
    tail). Key advantage over singly LL: if we have reference to node to delete, we can 
    delete it in O(1) time by updating its neighbors' pointers, without traversing. At 
    beginning: update head and new head's prev to null. At end: update tail and new 
    tail's next to null. In middle: update node before's next and node after's prev. 
    Must maintain bidirectional consistency and update head/tail references when needed.

    Time Complexity: O(1) if node reference known, O(n) for search+delete
    Space Complexity: O(1)

    Example:
    Delete head: 10<->20->30 becomes 20->30
    Delete node 20: 10<->20<->30 becomes 10->30
    Delete tail: 10<->20<->30 becomes 10<->20
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
     * Deletes the first node (head) from DLL.
     * O(1) operation - update head and new head's prev pointer.
     * Handles empty list and single node cases.
     * 
     * @param head current head of DLL
     * @return new head of DLL
     */
    public static Node deleteAtBeginning(Node head) {
        if (head == null) {
            System.out.println("List is empty. Nothing to delete.");
            return null;
        }
        
        System.out.println("Deleted node with data: " + head.data);
        
        // If only one node
        if (head.next == null) {
            return null;
        }
        
        // Update new head's prev pointer
        Node newHead = head.next;
        newHead.prev = null;
        
        return newHead;
    }
    
    /**
     * Deletes the last node (tail) from DLL.
     * Requires traversal to find second-to-last node. O(n) operation.
     * Updates new tail's next pointer to null.
     * Handles empty list and single node cases.
     * 
     * @param head current head of DLL
     * @return head of DLL (unchanged if list has more nodes)
     */
    public static Node deleteAtEnd(Node head) {
        if (head == null) {
            System.out.println("List is empty. Nothing to delete.");
            return null;
        }
        
        // If only one node
        if (head.next == null) {
            System.out.println("Deleted node with data: " + head.data);
            return null;
        }
        
        // Find second-to-last node
        Node temp = head;
        while (temp.next.next != null) {
            temp = temp.next;
        }
        
        System.out.println("Deleted node with data: " + temp.next.data);
        temp.next = null;  // Remove link to last node
        
        return head;
    }
    
    /**
     * Deletes node at specific position in DLL.
     * Position is 0-indexed. O(n) operation for traversal.
     * Updates all pointers correctly for bidirectional consistency.
     * 
     * @param head current head of DLL
     * @param position 0-indexed position to delete
     * @return new head of DLL
     */
    public static Node deleteAtPosition(Node head, int position) {
        if (head == null) {
            System.out.println("List is empty. Nothing to delete.");
            return null;
        }
        
        // Delete at beginning
        if (position == 0) {
            return deleteAtBeginning(head);
        }
        
        // Find node at position
        Node temp = head;
        int count = 0;
        
        while (temp != null && count < position) {
            temp = temp.next;
            count++;
        }
        
        // If position is beyond list
        if (temp == null) {
            System.out.println("Position out of bounds.");
            return head;
        }
        
        System.out.println("Deleted node with data: " + temp.data);
        
        // Delete node and update pointers
        if (temp.prev != null) {
            temp.prev.next = temp.next;
        }
        
        if (temp.next != null) {
            temp.next.prev = temp.prev;
        }
        
        return head;
    }
    
    /**
     * Deletes a specific node given its reference (O(1) operation).
     * This is a major advantage of DLL over singly LL.
     * Simply update its neighbors' pointers.
     * Cannot delete if node reference is unknown (requires search).
     * 
     * @param head current head of DLL
     * @param node node to delete
     * @return new head of DLL (may change if deleting head)
     */
    public static Node deleteNode(Node head, Node node) {
        if (node == null) {
            System.out.println("Node is null.");
            return head;
        }
        
        System.out.println("Deleted node with data: " + node.data);
        
        // If deleting head
        if (node == head) {
            if (node.next != null) {
                node.next.prev = null;
            }
            return node.next;
        }
        
        // If deleting tail
        if (node.next == null) {
            node.prev.next = null;
            return head;
        }
        
        // If deleting middle node
        node.prev.next = node.next;
        node.next.prev = node.prev;
        
        return head;
    }
    
    /**
     * Deletes first node with specific value.
     * Searches for value and deletes that node.
     * Returns updated head of DLL.
     * 
     * @param head current head of DLL
     * @param value data to search and delete
     * @return new head of DLL
     */
    public static Node deleteByValue(Node head, int value) {
        if (head == null) {
            System.out.println("List is empty.");
            return null;
        }
        
        // If head contains value
        if (head.data == value) {
            return deleteAtBeginning(head);
        }
        
        // Find node with value
        Node temp = head;
        while (temp != null && temp.data != value) {
            temp = temp.next;
        }
        
        // If not found
        if (temp == null) {
            System.out.println("Value " + value + " not found in list.");
            return head;
        }
        
        // Delete found node
        return deleteNode(head, temp);
    }
    
    /**
     * Deletes all nodes with specific value.
     * Removes all occurrences from DLL.
     * 
     * @param head current head of DLL
     * @param value value to delete
     * @return new head of DLL
     */
    public static Node deleteAllByValue(Node head, int value) {
        // Delete from beginning while head has value
        while (head != null && head.data == value) {
            System.out.println("Deleted node with data: " + head.data);
            head = head.next;
            if (head != null) {
                head.prev = null;
            }
        }
        
        if (head == null) {
            return null;
        }
        
        // Delete from rest of list
        Node temp = head.next;
        while (temp != null) {
            if (temp.data == value) {
                System.out.println("Deleted node with data: " + temp.data);
                temp.prev.next = temp.next;
                if (temp.next != null) {
                    temp.next.prev = temp.prev;
                }
                temp = temp.next;
            } else {
                temp = temp.next;
            }
        }
        
        return head;
    }
    
    /**
     * Clears entire DLL.
     * 
     * @param head current head
     * @return null
     */
    public static Node deleteEntireList(Node head) {
        if (head == null) {
            System.out.println("List is already empty.");
            return null;
        }
        
        System.out.println("Cleared entire list.");
        return null;
    }
    
    /**
     * Helper to display DLL forward.
     * 
     * @param head head of DLL
     */
    public static void displayForward(Node head) {
        if (head == null) {
            System.out.println("Forward: null <- [] -> null");
            return;
        }
        
        System.out.print("Forward:  null <- ");
        Node temp = head;
        
        while (temp != null) {
            System.out.print(temp.data);
            if (temp.next != null) {
                System.out.print(" <-> ");
            }
            temp = temp.next;
        }
        
        System.out.println(" -> null");
    }
    
    /**
     * Helper to display DLL backward.
     * 
     * @param head head of DLL
     */
    public static void displayBackward(Node head) {
        if (head == null) {
            System.out.println("Backward: null -> [] <- null");
            return;
        }
        
        // Find tail
        Node tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        
        System.out.print("Backward: null -> ");
        Node temp = tail;
        
        while (temp != null) {
            System.out.print(temp.data);
            if (temp.prev != null) {
                System.out.print(" <-> ");
            }
            temp = temp.prev;
        }
        
        System.out.println(" <- null");
    }
    
    /**
     * Helper to create DLL from array.
     * 
     * @param arr array of integers
     * @return head of created DLL
     */
    public static Node createFromArray(int[] arr) {
        if (arr.length == 0) {
            return null;
        }
        
        Node head = new Node(arr[0]);
        Node temp = head;
        
        for (int i = 1; i < arr.length; i++) {
            Node newNode = new Node(arr[i]);
            temp.next = newNode;
            newNode.prev = temp;
            temp = newNode;
        }
        
        return head;
    }
    
    /**
     * Helper to get length of DLL.
     * 
     * @param head head of DLL
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
        System.out.println("=== DELETE AT BEGINNING ===");
        Node head = createFromArray(new int[]{10, 20, 30, 40});
        displayForward(head);
        displayBackward(head);
        
        head = deleteAtBeginning(head);
        displayForward(head);
        displayBackward(head);
        
        System.out.println("\n=== DELETE AT END ===");
        head = createFromArray(new int[]{10, 20, 30, 40});
        displayForward(head);
        
        head = deleteAtEnd(head);
        displayForward(head);
        
        System.out.println("\n=== DELETE AT POSITION ===");
        head = createFromArray(new int[]{10, 20, 30, 40, 50});
        displayForward(head);
        
        head = deleteAtPosition(head, 2);
        displayForward(head);
        displayBackward(head);
        System.out.println("Length: " + getLength(head));
        
        System.out.println("\n=== DELETE SPECIFIC NODE (O(1)) ===");
        head = createFromArray(new int[]{10, 20, 30, 40});
        displayForward(head);
        
        // Find node with value 20
        Node temp = head;
        while (temp != null && temp.data != 20) {
            temp = temp.next;
        }
        
        if (temp != null) {
            head = deleteNode(head, temp);
            displayForward(head);
            displayBackward(head);
        }
        
        System.out.println("\n=== DELETE BY VALUE ===");
        head = createFromArray(new int[]{10, 20, 30, 40, 30});
        displayForward(head);
        
        head = deleteByValue(head, 30);
        displayForward(head);
        
        System.out.println("\n=== DELETE ALL BY VALUE ===");
        head = createFromArray(new int[]{10, 20, 10, 30, 10});
        displayForward(head);
        
        head = deleteAllByValue(head, 10);
        displayForward(head);
        displayBackward(head);
        
        System.out.println("\n=== DELETE SINGLE NODE ===");
        head = new Node(42);
        displayForward(head);
        head = deleteAtBeginning(head);
        displayForward(head);
    }
}
