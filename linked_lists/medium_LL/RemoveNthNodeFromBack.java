package linked_lists.medium_LL;

public class RemoveNthNodeFromBack {

    /*
    =====================================================================================
    PROBLEM: REMOVE Nth NODE FROM END OF LINKED LIST
    -------------------------------------------------------------------------------------
    Given the head of a singly linked list, remove the Nth node from the end
    of the list and return the head of the modified list.

    KEY CHALLENGE:
    • We must delete a node without direct backward traversal
    • Need access to the node BEFORE the one to be removed

    OPTIMAL APPROACH:
    • Use two pointers (fast and slow)
    • Maintain a gap of N nodes between them
    • When fast reaches the end, slow will be just before the target node

    A dummy node is used to handle edge cases such as deleting the head.

    Example:
    Input : 1 → 2 → 3 → 4 → 5, n = 2
    Output: 1 → 2 → 3 → 5

    Time Complexity  : O(n)
    Space Complexity : O(1)
    =====================================================================================
    */

    /* ============================ NODE DEFINITION ============================ */

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    /* ====================== OPTIMAL TWO-POINTER SOLUTION ====================== */

    /*
    =====================================================================================
    FUNCTION: removeNthFromEnd
    -------------------------------------------------------------------------------------
    Removes the Nth node from the end using a single-pass two-pointer approach.

    STEPS:
    1. Create a dummy node pointing to head
    2. Move fast pointer (n + 1) steps ahead
    3. Move both pointers together until fast reaches end
    4. Slow pointer will be just before the node to delete
    5. Adjust links to remove the node

    WHY DUMMY NODE?
    • Simplifies deletion when head itself must be removed

    Time Complexity  : O(n)
    Space Complexity : O(1)
    =====================================================================================
    */
    static Node removeNthFromEnd(Node head, int n) {

        Node dummy = new Node(0);
        dummy.next = head;

        Node fast = dummy;
        Node slow = dummy;

        // Move fast pointer n+1 steps ahead
        for (int i = 0; i <= n; i++) {
            if (fast == null) {
                return head; // n > length
            }
            fast = fast.next;
        }

        // Move both pointers until fast reaches end
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // Delete target node
        slow.next = slow.next.next;

        return dummy.next;
    }

    /* ====================== TWO-PASS APPROACH (FOR CLARITY) ====================== */

    /*
    =====================================================================================
    FUNCTION: removeNthFromEndTwoPass
    -------------------------------------------------------------------------------------
    Removes the Nth node from end using length calculation.

    STEPS:
    1. Calculate length of the list
    2. Convert Nth-from-end to position-from-start
    3. Traverse and delete the node

    Time Complexity  : O(n)
    Space Complexity : O(1)
    =====================================================================================
    */
    static Node removeNthFromEndTwoPass(Node head, int n) {

        int length = 0;
        Node temp = head;

        while (temp != null) {
            length++;
            temp = temp.next;
        }

        // If head needs to be removed
        if (length == n) {
            return head.next;
        }

        temp = head;
        for (int i = 0; i < length - n - 1; i++) {
            temp = temp.next;
        }

        temp.next = temp.next.next;
        return head;
    }

    /* ====================== HELPER METHODS ====================== */

    static Node createFromArray(int[] arr) {

        if (arr.length == 0) return null;

        Node head = new Node(arr[0]);
        Node temp = head;

        for (int i = 1; i < arr.length; i++) {
            temp.next = new Node(arr[i]);
            temp = temp.next;
        }

        return head;
    }

    static void display(Node head) {

        while (head != null) {
            System.out.print(head.data + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }

    /* ============================ DRIVER CODE ============================ */

    public static void main(String[] args) {

        System.out.println("=== REMOVE 2nd FROM END ===");
        Node head = createFromArray(new int[]{1, 2, 3, 4, 5});
        display(head);
        head = removeNthFromEnd(head, 2);
        display(head);

        System.out.println("\n=== REMOVE LAST NODE ===");
        head = createFromArray(new int[]{1, 2, 3, 4, 5});
        display(head);
        head = removeNthFromEnd(head, 1);
        display(head);

        System.out.println("\n=== REMOVE HEAD NODE ===");
        head = createFromArray(new int[]{1, 2, 3, 4, 5});
        display(head);
        head = removeNthFromEnd(head, 5);
        display(head);

        System.out.println("\n=== SINGLE NODE LIST ===");
        head = new Node(10);
        display(head);
        head = removeNthFromEnd(head, 1);
        display(head);

        System.out.println("\n=== TWO-PASS APPROACH ===");
        head = createFromArray(new int[]{1, 2, 3, 4, 5});
        display(head);
        head = removeNthFromEndTwoPass(head, 3);
        display(head);
    }
}
