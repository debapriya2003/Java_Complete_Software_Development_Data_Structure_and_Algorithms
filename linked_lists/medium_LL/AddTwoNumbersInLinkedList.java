package linked_lists.medium_LL;

public class AddTwoNumbersInLinkedList {

    /*
    =====================================================================================
    PROBLEM: ADD TWO NUMBERS REPRESENTED BY LINKED LISTS
    -------------------------------------------------------------------------------------
    Two non-negative integers are represented using linked lists, where each node
    contains a single digit.

    There are TWO possible representations:
    1) Reverse Order:
       - Least Significant Digit (LSD) is at the head
       - Example: 2 -> 4 -> 3 represents 342

    2) Normal Order:
       - Most Significant Digit (MSD) is at the head
       - Example: 1 -> 2 -> 3 represents 123

    The task is to add the two numbers and return the result as a linked list
    in the SAME order as input.

    KEY CHALLENGES:
    • Carry propagation
    • Different length lists
    • Final carry creating a new node

    Time Complexity  : O(max(n, m))
    Space Complexity : O(max(n, m))   (for result list)
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

    /* ====================== REVERSE ORDER ADDITION ====================== */

    /*
    =====================================================================================
    FUNCTION: addTwoNumbersReverse
    -------------------------------------------------------------------------------------
    Adds two numbers where digits are stored in REVERSE order.
    (Least Significant Digit at head)

    LOGIC:
    • Traverse both lists simultaneously
    • Add corresponding digits + carry
    • Create new nodes for result
    • Handle leftover carry at the end

    Example:
    2 -> 4 -> 3   (342)
    5 -> 6 -> 4   (465)
    ------------------
    7 -> 0 -> 8   (807)

    Time Complexity  : O(max(n, m))
    Space Complexity : O(max(n, m))
    =====================================================================================
    */
    static Node addTwoNumbersReverse(Node head1, Node head2) {

        Node dummy = new Node(0);
        Node current = dummy;
        int carry = 0;

        while (head1 != null || head2 != null || carry > 0) {

            int val1 = (head1 != null) ? head1.data : 0;
            int val2 = (head2 != null) ? head2.data : 0;

            int sum = val1 + val2 + carry;
            carry = sum / 10;

            current.next = new Node(sum % 10);
            current = current.next;

            if (head1 != null) head1 = head1.next;
            if (head2 != null) head2 = head2.next;
        }

        return dummy.next;
    }

    /* ====================== NORMAL ORDER ADDITION ====================== */

    /*
    =====================================================================================
    FUNCTION: addTwoNumbersNormal
    -------------------------------------------------------------------------------------
    Adds two numbers stored in NORMAL order (MSD at head).

    APPROACH:
    • Reverse both input lists
    • Apply reverse-order addition
    • Reverse the result back

    Time Complexity  : O(n + m)
    Space Complexity : O(n + m)
    =====================================================================================
    */
    static Node addTwoNumbersNormal(Node head1, Node head2) {

        head1 = reverseList(head1);
        head2 = reverseList(head2);

        Node result = addTwoNumbersReverse(head1, head2);

        return reverseList(result);
    }

    /* ====================== HELPER METHODS ====================== */

    /*
    =====================================================================================
    FUNCTION: reverseList
    -------------------------------------------------------------------------------------
    Reverses a linked list iteratively.

    Time Complexity  : O(n)
    Space Complexity : O(1)
    =====================================================================================
    */
    static Node reverseList(Node head) {

        Node prev = null;
        Node curr = head;

        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }

    /*
    =====================================================================================
    FUNCTION: createFromArray
    -------------------------------------------------------------------------------------
    Creates a linked list from an array of digits.
    =====================================================================================
    */
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

    /*
    =====================================================================================
    FUNCTION: displayNumber
    -------------------------------------------------------------------------------------
    Displays a linked list as a number.
    =====================================================================================
    */
    static void displayNumber(Node head) {

        while (head != null) {
            System.out.print(head.data);
            head = head.next;
        }
        System.out.println();
    }

    /* ============================ DRIVER CODE ============================ */

    public static void main(String[] args) {

        System.out.println("=== REVERSE ORDER (342 + 465) ===");
        Node num1 = createFromArray(new int[]{2, 4, 3});
        Node num2 = createFromArray(new int[]{5, 6, 4});
        displayNumber(addTwoNumbersReverse(num1, num2));   // 807

        System.out.println("\n=== DIFFERENT LENGTHS (12 + 3456) ===");
        num1 = createFromArray(new int[]{2, 1});
        num2 = createFromArray(new int[]{6, 5, 4, 3});
        displayNumber(addTwoNumbersReverse(num1, num2));   // 3468

        System.out.println("\n=== WITH CARRY (999 + 1) ===");
        num1 = createFromArray(new int[]{9, 9, 9});
        num2 = createFromArray(new int[]{1});
        displayNumber(addTwoNumbersReverse(num1, num2));   // 1000

        System.out.println("\n=== NORMAL ORDER (123 + 456) ===");
        num1 = createFromArray(new int[]{1, 2, 3});
        num2 = createFromArray(new int[]{4, 5, 6});
        displayNumber(addTwoNumbersNormal(num1, num2));    // 579
    }
}
