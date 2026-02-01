package linked_lists.medium_LL;

public class CheckIfLinkedListIsPalindrome {

    /*
    =====================================================================================
    PROBLEM: CHECK IF LINKEDLIST IS PALINDROME OR NOT
    -------------------------------------------------------------------------------------
    Palindrome checking in LinkedList is interesting because we can't directly access
    elements by index like arrays. Multiple approaches: (1) Stack-based: push all nodes,
    pop and compare (O(n) space). (2) Two-pointer with reversal: find middle using 
    slow-fast, reverse second half, compare with first half (O(n) time, O(1) space).
    (3) Recursive: traverse to end, compare nodes while returning (O(n) stack space).
    Optimal approach uses tortoise-hare to find middle, reverses second half, then 
    compares. Most challenging because no random access to middle. Common in interviews
    testing pointer manipulation and space-time tradeoffs. Requires careful handling of
    odd/even length lists. Can restore list to original by reversing second half again
    after checking. Essential problem for mastering LinkedList operations and algorithms.

    Time Complexity: O(n) two-pointer with reversal
    Space Complexity: O(1) optimal, O(n) stack for recursive

    Example:
    1->2->3->2->1 is palindrome
    1->2->3->4 is not palindrome
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
     * Checks if LinkedList is palindrome using Stack.
     * Push all nodes, pop and compare with traversal.
     * Simpler logic, uses O(n) extra space.
     * 
     * @param head head of LinkedList
     * @return true if palindrome, false otherwise
     */
    public static boolean isPalindromeStack(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        
        java.util.Stack<Integer> stack = new java.util.Stack<>();
        Node temp = head;
        
        // Push all values
        while (temp != null) {
            stack.push(temp.data);
            temp = temp.next;
        }
        
        // Compare by popping and traversing
        temp = head;
        while (temp != null) {
            if (temp.data != stack.pop()) {
                return false;
            }
            temp = temp.next;
        }
        
        return true;
    }
    
    /**
     * Checks palindrome using two-pointer with reversal (Optimal).
     * Find middle using slow-fast, reverse second half, compare halves.
     * Time: O(n), Space: O(1). Most elegant approach.
     * 
     * @param head head of LinkedList
     * @return true if palindrome, false otherwise
     */
    public static boolean isPalindromeOptimal(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        
        // Find middle using slow-fast pointers
        Node slow = head;
        Node fast = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // Reverse second half starting from slow
        Node secondHalf = reverseList(slow);
        
        // Compare first half with reversed second half
        Node firstHalf = head;
        Node secondHalfCopy = secondHalf;
        
        boolean isPalindrome = true;
        while (secondHalfCopy != null) {
            if (firstHalf.data != secondHalfCopy.data) {
                isPalindrome = false;
            }
            firstHalf = firstHalf.next;
            secondHalfCopy = secondHalfCopy.next;
        }
        
        // Restore list by reversing second half again (optional)
        reverseList(secondHalf);
        
        return isPalindrome;
    }
    
    /**
     * Checks palindrome recursively.
     * Traverses to end, compares on the way back.
     * Uses O(n) stack space for recursion.
     * 
     * @param head head of LinkedList
     * @return true if palindrome
     */
    public static boolean isPalindromeRecursive(Node head) {
        return isPalindromeUtil(head).isPalindrome;
    }
    
    /**
     * Helper for recursive palindrome check.
     * Returns node pointer and palindrome status.
     * 
     * @param head current node
     * @return result with forward pointer and palindrome status
     */
    private static PalindromeResult isPalindromeUtil(Node head) {
        // Base case
        if (head == null) {
            return new PalindromeResult(null, true);
        }
        
        // Recurse to end
        PalindromeResult result = isPalindromeUtil(head.next);
        
        // Check if rest is palindrome and current matches
        if (result.isPalindrome && result.forwardPointer != null) {
            result.isPalindrome = (head.data == result.forwardPointer.data);
            result.forwardPointer = result.forwardPointer.next;
        } else if (result.isPalindrome && result.forwardPointer == null) {
            // End of list reached, this is middle
            result.forwardPointer = head.next;
        }
        
        return result;
    }
    
    /**
     * Helper class for recursive palindrome checking.
     */
    private static class PalindromeResult {
        Node forwardPointer;
        boolean isPalindrome;
        
        PalindromeResult(Node forwardPointer, boolean isPalindrome) {
            this.forwardPointer = forwardPointer;
            this.isPalindrome = isPalindrome;
        }
    }
    
    /**
     * Two-pointer approach without modifying list.
     * Uses slow-fast to find middle, then compares carefully.
     * 
     * @param head head of LinkedList
     * @return true if palindrome
     */
    public static boolean isPalindromeNonDestructive(Node head) {
        if (head == null || head.next == null) {
            return true;
        }
        
        // Find middle
        Node slow = head;
        Node fast = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // If odd length, slow is middle, move to next
        if (fast != null) {
            slow = slow.next;
        }
        
        // Compare first half with second half
        Node firstHalf = head;
        Node secondHalf = slow;
        
        while (secondHalf != null) {
            if (firstHalf.data != secondHalf.data) {
                return false;
            }
            firstHalf = firstHalf.next;
            secondHalf = secondHalf.next;
        }
        
        return true;
    }
    
    /**
     * Reverses a LinkedList (helper function).
     * 
     * @param head head to reverse from
     * @return new head of reversed portion
     */
    private static Node reverseList(Node head) {
        Node prev = null;
        Node current = head;
        
        while (current != null) {
            Node next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        
        return prev;
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
        System.out.println("=== ODD LENGTH PALINDROME ===");
        Node head = createFromArray(new int[]{1, 2, 3, 2, 1});
        display(head);
        System.out.println("Stack approach: " + isPalindromeStack(head));
        System.out.println("Optimal approach: " + isPalindromeOptimal(head));
        System.out.println("Recursive approach: " + isPalindromeRecursive(head));
        System.out.println("Non-destructive approach: " + isPalindromeNonDestructive(head));
        
        System.out.println("\n=== EVEN LENGTH PALINDROME ===");
        head = createFromArray(new int[]{1, 2, 2, 1});
        display(head);
        System.out.println("Stack approach: " + isPalindromeStack(head));
        System.out.println("Optimal approach: " + isPalindromeOptimal(head));
        System.out.println("Recursive approach: " + isPalindromeRecursive(head));
        System.out.println("Non-destructive approach: " + isPalindromeNonDestructive(head));
        
        System.out.println("\n=== NOT PALINDROME ===");
        head = createFromArray(new int[]{1, 2, 3, 4, 5});
        display(head);
        System.out.println("Stack approach: " + isPalindromeStack(head));
        System.out.println("Optimal approach: " + isPalindromeOptimal(head));
        System.out.println("Recursive approach: " + isPalindromeRecursive(head));
        System.out.println("Non-destructive approach: " + isPalindromeNonDestructive(head));
        
        System.out.println("\n=== SINGLE NODE (PALINDROME) ===");
        head = new Node(42);
        display(head);
        System.out.println("Stack approach: " + isPalindromeStack(head));
        System.out.println("Optimal approach: " + isPalindromeOptimal(head));
        System.out.println("Recursive approach: " + isPalindromeRecursive(head));
        System.out.println("Non-destructive approach: " + isPalindromeNonDestructive(head));
        
        System.out.println("\n=== TWO NODES PALINDROME ===");
        head = createFromArray(new int[]{5, 5});
        display(head);
        System.out.println("Stack approach: " + isPalindromeStack(head));
        System.out.println("Optimal approach: " + isPalindromeOptimal(head));
        System.out.println("Recursive approach: " + isPalindromeRecursive(head));
        System.out.println("Non-destructive approach: " + isPalindromeNonDestructive(head));
        
        System.out.println("\n=== TWO NODES NOT PALINDROME ===");
        head = createFromArray(new int[]{1, 2});
        display(head);
        System.out.println("Stack approach: " + isPalindromeStack(head));
        System.out.println("Optimal approach: " + isPalindromeOptimal(head));
        System.out.println("Recursive approach: " + isPalindromeRecursive(head));
        System.out.println("Non-destructive approach: " + isPalindromeNonDestructive(head));
        
        System.out.println("\n=== LONG PALINDROME ===");
        head = createFromArray(new int[]{1, 2, 3, 4, 5, 4, 3, 2, 1});
        display(head);
        System.out.println("Stack approach: " + isPalindromeStack(head));
        System.out.println("Optimal approach: " + isPalindromeOptimal(head));
        System.out.println("Recursive approach: " + isPalindromeRecursive(head));
        System.out.println("Non-destructive approach: " + isPalindromeNonDestructive(head));
        
        System.out.println("\n=== ALL SAME ELEMENTS (PALINDROME) ===");
        head = createFromArray(new int[]{5, 5, 5, 5, 5});
        display(head);
        System.out.println("Stack approach: " + isPalindromeStack(head));
        System.out.println("Optimal approach: " + isPalindromeOptimal(head));
        System.out.println("Recursive approach: " + isPalindromeRecursive(head));
        System.out.println("Non-destructive approach: " + isPalindromeNonDestructive(head));
    }
}
