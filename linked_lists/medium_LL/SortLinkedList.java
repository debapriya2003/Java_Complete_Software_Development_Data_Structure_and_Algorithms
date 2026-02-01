package linked_lists.medium_LL;

public class SortLinkedList {

    /*
    =====================================================================================
    PROBLEM: SORT A LINKED LIST
    -------------------------------------------------------------------------------------
    Given the head of a singly linked list, sort the list in ascending order
    and return the head of the sorted list.

    WHY SORTING LINKED LISTS IS DIFFERENT:
    • Linked lists do NOT support random access
    • Algorithms like Heap Sort or Array Quick Sort are inefficient
    • Merge Sort works naturally due to pointer manipulation

    OPTIMAL APPROACH:
    • Merge Sort (Divide and Conquer)
    • Split list into halves
    • Recursively sort each half
    • Merge sorted halves

    Time Complexity  : O(n log n)
    Space Complexity : O(log n)  (recursion stack)

    Example:
    Input : 4 → 2 → 1 → 3
    Output: 1 → 2 → 3 → 4
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

    /* ====================== MERGE SORT (OPTIMAL) ====================== */

    /*
    =====================================================================================
    FUNCTION: mergeSortLinkedList
    -------------------------------------------------------------------------------------
    Sorts a linked list using Merge Sort.

    STEPS:
    1. Find the middle of the list
    2. Split the list into two halves
    3. Recursively sort both halves
    4. Merge the sorted halves

    Time Complexity  : O(n log n)
    Space Complexity : O(log n)
    =====================================================================================
    */
    static Node mergeSortLinkedList(Node head) {

        // Base case: empty or single node
        if (head == null || head.next == null) {
            return head;
        }

        // Step 1: Split the list
        Node mid = findMiddle(head);
        Node rightHead = mid.next;
        mid.next = null;

        // Step 2: Sort both halves
        Node left = mergeSortLinkedList(head);
        Node right = mergeSortLinkedList(rightHead);

        // Step 3: Merge sorted halves
        return mergeSortedLists(left, right);
    }

    /*
    =====================================================================================
    FUNCTION: findMiddle
    -------------------------------------------------------------------------------------
    Finds the middle of the linked list using slow-fast pointer technique.
    =====================================================================================
    */
    static Node findMiddle(Node head) {

        Node slow = head;
        Node fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    /*
    =====================================================================================
    FUNCTION: mergeSortedLists
    -------------------------------------------------------------------------------------
    Merges two sorted linked lists into one sorted list.
    =====================================================================================
    */
    static Node mergeSortedLists(Node l1, Node l2) {

        Node dummy = new Node(0);
        Node curr = dummy;

        while (l1 != null && l2 != null) {
            if (l1.data <= l2.data) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }

        curr.next = (l1 != null) ? l1 : l2;
        return dummy.next;
    }

    /* ====================== INSERTION SORT (SECONDARY) ====================== */

    /*
    =====================================================================================
    FUNCTION: insertionSortLinkedList
    -------------------------------------------------------------------------------------
    Sorts a linked list using Insertion Sort.

    WHEN TO USE:
    • Small lists
    • Nearly sorted lists

    Time Complexity  : O(n²)
    Space Complexity : O(1)
    =====================================================================================
    */
    static Node insertionSortLinkedList(Node head) {

        Node dummy = new Node(0);
        Node curr = head;

        while (curr != null) {
            Node next = curr.next;
            Node pos = dummy;

            while (pos.next != null && pos.next.data < curr.data) {
                pos = pos.next;
            }

            curr.next = pos.next;
            pos.next = curr;
            curr = next;
        }

        return dummy.next;
    }

    /* ====================== HELPER METHODS ====================== */

    static Node createFromArray(int[] arr) {

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

        System.out.println("=== MERGE SORT ===");
        Node head = createFromArray(new int[]{4, 2, 1, 3, 5});
        display(head);
        head = mergeSortLinkedList(head);
        display(head);

        System.out.println("\n=== INSERTION SORT ===");
        head = createFromArray(new int[]{4, 2, 1, 3});
        display(head);
        head = insertionSortLinkedList(head);
        display(head);

        System.out.println("\n=== SINGLE NODE ===");
        head = new Node(10);
        display(head);
        head = mergeSortLinkedList(head);
        display(head);

        System.out.println("\n=== ALREADY SORTED ===");
        head = createFromArray(new int[]{1, 2, 3, 4});
        display(head);
        head = mergeSortLinkedList(head);
        display(head);

        System.out.println("\n=== REVERSE SORTED ===");
        head = createFromArray(new int[]{5, 4, 3, 2, 1});
        display(head);
        head = mergeSortLinkedList(head);
        display(head);
    }
}
