package linked_lists.medium_LL;

public class FindIntersectionPointOfYLinkedList {

    /*
    =====================================================================================
    PROBLEM: FIND INTERSECTION POINT OF Y-SHAPED LINKED LISTS
    -------------------------------------------------------------------------------------
    Two singly linked lists may intersect at some point, forming a Y-shaped structure.
    After the intersection point, both lists share the same nodes (same memory reference).

    The task is to find the FIRST node at which both linked lists intersect.

    IMPORTANT:
    • Intersection is based on NODE REFERENCE, not node value
    • After intersection, all nodes are common

    COMMON APPROACHES:
    1) HashSet approach        → O(n + m) time, O(n) space
    2) Length-based approach  → O(n + m) time, O(1) space
    3) Two-pointer approach   → O(n + m) time, O(1) space (BEST)

    Example:
    List1: 4 → 1 → 8 → 4 → 5
    List2:       5 → 0 → 1 → 8 → 4 → 5
                         ↑
                     Intersection at node 8
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

    /* ====================== OPTIMAL TWO-POINTER APPROACH ====================== */

    /*
    =====================================================================================
    FUNCTION: findIntersectionTwoPointer
    -------------------------------------------------------------------------------------
    Finds the intersection node using the TWO-POINTER technique.

    KEY IDEA:
    • Traverse both lists
    • When one pointer reaches the end, redirect it to the head of the other list
    • If lists intersect, pointers will meet at the intersection node
    • If not, both will eventually become null

    WHY IT WORKS:
    Both pointers traverse exactly (n + m) nodes.

    Time Complexity  : O(n + m)
    Space Complexity : O(1)
    =====================================================================================
    */
    static Node findIntersectionTwoPointer(Node head1, Node head2) {

        if (head1 == null || head2 == null) {
            return null;
        }

        Node ptr1 = head1;
        Node ptr2 = head2;

        while (ptr1 != ptr2) {
            ptr1 = (ptr1 == null) ? head2 : ptr1.next;
            ptr2 = (ptr2 == null) ? head1 : ptr2.next;
        }

        return ptr1;   // Either intersection node or null
    }

    /* ====================== LENGTH-BASED APPROACH ====================== */

    /*
    =====================================================================================
    FUNCTION: findIntersectionLengthBased
    -------------------------------------------------------------------------------------
    Finds intersection by aligning both lists to the same remaining length.

    STEPS:
    1. Calculate lengths of both lists
    2. Advance the longer list by |len1 - len2|
    3. Move both pointers together until they meet

    Time Complexity  : O(n + m)
    Space Complexity : O(1)
    =====================================================================================
    */
    static Node findIntersectionLengthBased(Node head1, Node head2) {

        int len1 = getLength(head1);
        int len2 = getLength(head2);

        Node ptr1 = head1;
        Node ptr2 = head2;

        if (len1 > len2) {
            for (int i = 0; i < len1 - len2; i++) ptr1 = ptr1.next;
        } else {
            for (int i = 0; i < len2 - len1; i++) ptr2 = ptr2.next;
        }

        while (ptr1 != null && ptr2 != null) {
            if (ptr1 == ptr2) return ptr1;
            ptr1 = ptr1.next;
            ptr2 = ptr2.next;
        }

        return null;
    }

    /* ====================== HASHSET APPROACH ====================== */

    /*
    =====================================================================================
    FUNCTION: findIntersectionHashSet
    -------------------------------------------------------------------------------------
    Stores nodes of the first list in a HashSet and checks nodes of second list.

    Time Complexity  : O(n + m)
    Space Complexity : O(n)
    =====================================================================================
    */
    static Node findIntersectionHashSet(Node head1, Node head2) {

        java.util.HashSet<Node> set = new java.util.HashSet<>();

        while (head1 != null) {
            set.add(head1);
            head1 = head1.next;
        }

        while (head2 != null) {
            if (set.contains(head2)) return head2;
            head2 = head2.next;
        }

        return null;
    }

    /* ====================== HELPER METHODS ====================== */

    static int getLength(Node head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }

    static Node createFromArray(int[] arr) {
        Node head = new Node(arr[0]);
        Node temp = head;
        for (int i = 1; i < arr.length; i++) {
            temp.next = new Node(arr[i]);
            temp = temp.next;
        }
        return head;
    }

    static Node[] createYList(int[] a, int[] b, int[] common) {

        Node head1 = createFromArray(a);
        Node head2 = createFromArray(b);
        Node commonHead = createFromArray(common);

        Node t1 = head1;
        while (t1.next != null) t1 = t1.next;
        t1.next = commonHead;

        Node t2 = head2;
        while (t2.next != null) t2 = t2.next;
        t2.next = commonHead;

        return new Node[]{head1, head2};
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

        Node[] lists = createYList(
                new int[]{4, 1},
                new int[]{5, 0, 1},
                new int[]{8, 4, 5}
        );

        Node head1 = lists[0];
        Node head2 = lists[1];

        display(head1);
        display(head2);

        Node intersection = findIntersectionTwoPointer(head1, head2);
        System.out.println("Intersection Node: " +
                (intersection != null ? intersection.data : "null"));
    }
}
