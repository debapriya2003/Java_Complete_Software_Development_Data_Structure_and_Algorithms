package recursion_patternwise.strong_hold;

import java.util.Stack;

public class SortStackUsingRecursion {

    /*
    =====================================================================================
    PROBLEM: SORT A STACK USING RECURSION
    -------------------------------------------------------------------------------------
    Sort a stack in ascending order (largest element at bottom) using only recursion.
    Cannot use any extra data structure like arrays, queues, etc. but recursive call stack
    is allowed.
    
    Example:
    Input Stack (top to bottom): 3, 1, 4, 1, 5, 9
    Output Stack (top to bottom): 1, 1, 3, 4, 5, 9
    
    Approach:
    1. Pop elements from stack recursively until stack is empty
    2. For each popped element, insert it in correct position in remaining sorted stack
    3. Recursive call stack acts as temporary storage
    4. During unwinding, insert each element in proper position
    
    Algorithm Details:
    1. Pop top element from stack
    2. Recursively sort remaining stack
    3. Insert the popped element at correct position
    4. Use helper method to insert in sorted manner
    
    Time Complexity: O(n^2) - in worst case, inserting each element takes O(n)
    Space Complexity: O(n) - recursive call stack
    =====================================================================================
    */
    
    /**
     * Sort stack using recursion.
     * Pops all elements and sorts while pushing back.
     * 
     * Algorithm:
     * 1. Base case: if stack is empty, return
     * 2. Pop top element x
     * 3. Recursively sort remaining stack
     * 4. Insert x in correct position
     * 
     * @param stack stack to sort
     */
    public static void sortStack(Stack<Integer> stack) {
        // Base case: empty stack
        if (stack.isEmpty()) {
            return;
        }
        
        // Pop top element
        int top = stack.pop();
        
        // Recursively sort remaining stack
        sortStack(stack);
        
        // Insert top element at correct position
        insertInSortedStack(stack, top);
    }
    
    /**
     * Insert element in sorted stack.
     * Maintains stack in ascending order (top has smallest).
     * 
     * Algorithm:
     * 1. If stack is empty or top is smaller than element, push element
     * 2. Otherwise, pop top element, insert remaining element, then push back top
     * 
     * @param stack sorted stack
     * @param value value to insert
     */
    private static void insertInSortedStack(Stack<Integer> stack, int value) {
        // Base case: empty stack or value is smaller than top
        if (stack.isEmpty() || value <= stack.peek()) {
            stack.push(value);
            return;
        }
        
        // Pop top element
        int top = stack.pop();
        
        // Recursively insert value in remaining stack
        insertInSortedStack(stack, value);
        
        // Push back the popped element
        stack.push(top);
    }
    
    /**
     * Print stack contents (top to bottom).
     * 
     * @param stack stack to print
     */
    public static void printStack(Stack<Integer> stack) {
        System.out.print("Stack (top to bottom): ");
        Stack<Integer> temp = new Stack<>();
        
        // Copy to temp and print
        while (!stack.isEmpty()) {
            int val = stack.pop();
            System.out.print(val + " ");
            temp.push(val);
        }
        
        // Restore original stack
        while (!temp.isEmpty()) {
            stack.push(temp.pop());
        }
        
        System.out.println();
    }
    
    /**
     * Helper method to create stack from array.
     * Pushes elements in reverse order so first element is on top.
     * 
     * @param arr array of elements
     * @return stack with elements
     */
    public static Stack<Integer> createStack(int[] arr) {
        Stack<Integer> stack = new Stack<>();
        
        // Push in reverse to maintain order
        for (int i = arr.length - 1; i >= 0; i--) {
            stack.push(arr[i]);
        }
        
        return stack;
    }
    
    /**
     * Verify stack is sorted in ascending order (top to bottom means decreasing).
     * 
     * @param stack stack to verify
     * @return true if sorted correctly
     */
    public static boolean isSorted(Stack<Integer> stack) {
        Stack<Integer> temp = new Stack<>();
        int previous = Integer.MIN_VALUE;
        
        while (!stack.isEmpty()) {
            int current = stack.pop();
            if (current < previous) {
                // Restore stack
                while (!temp.isEmpty()) {
                    stack.push(temp.pop());
                }
                return false;
            }
            previous = current;
            temp.push(current);
        }
        
        // Restore stack
        while (!temp.isEmpty()) {
            stack.push(temp.pop());
        }
        
        return true;
    }
    
    /**
     * Alternative approach using explicit sorting logic.
     * More readable for understanding.
     */
    public static void sortStackAlternative(Stack<Integer> stack) {
        // Base case: empty stack
        if (stack.isEmpty()) {
            return;
        }
        
        // Pop element
        int element = stack.pop();
        
        // Recursively sort rest
        sortStackAlternative(stack);
        
        // Insert in correct position
        insertElement(stack, element);
    }
    
    /**
     * Insert element maintaining sorted order.
     * Stack should be sorted in ascending order (smallest on top).
     * 
     * @param stack sorted stack
     * @param element element to insert
     */
    private static void insertElement(Stack<Integer> stack, int element) {
        // If stack is empty or element is smaller than top, push it
        if (stack.isEmpty() || element <= stack.peek()) {
            stack.push(element);
            return;
        }
        
        // Temporarily pop, insert element, push back
        int top = stack.pop();
        insertElement(stack, element);
        stack.push(top);
    }
    
    public static void main(String[] args) {
        // Test Case 1: Simple unsorted stack
        System.out.println("=== Test Case 1: Simple Stack ===");
        int[] arr1 = {3, 1, 4, 1, 5, 9};
        Stack<Integer> stack1 = createStack(arr1);
        System.out.println("Before sorting:");
        printStack(stack1);
        
        sortStack(stack1);
        System.out.println("After sorting:");
        printStack(stack1);
        System.out.println("Is sorted correctly? " + isSorted(stack1));
        System.out.println();
        
        // Test Case 2: Already sorted stack
        System.out.println("=== Test Case 2: Already Sorted Stack ===");
        int[] arr2 = {5, 4, 3, 2, 1};
        Stack<Integer> stack2 = createStack(arr2);
        System.out.println("Before sorting:");
        printStack(stack2);
        
        sortStack(stack2);
        System.out.println("After sorting:");
        printStack(stack2);
        System.out.println("Is sorted correctly? " + isSorted(stack2));
        System.out.println();
        
        // Test Case 3: Reverse sorted stack
        System.out.println("=== Test Case 3: Reverse Sorted Stack ===");
        int[] arr3 = {1, 2, 3, 4, 5};
        Stack<Integer> stack3 = createStack(arr3);
        System.out.println("Before sorting:");
        printStack(stack3);
        
        sortStack(stack3);
        System.out.println("After sorting:");
        printStack(stack3);
        System.out.println("Is sorted correctly? " + isSorted(stack3));
        System.out.println();
        
        // Test Case 4: Single element
        System.out.println("=== Test Case 4: Single Element ===");
        int[] arr4 = {5};
        Stack<Integer> stack4 = createStack(arr4);
        System.out.println("Before sorting:");
        printStack(stack4);
        
        sortStack(stack4);
        System.out.println("After sorting:");
        printStack(stack4);
        System.out.println("Is sorted correctly? " + isSorted(stack4));
        System.out.println();
        
        // Test Case 5: Duplicates
        System.out.println("=== Test Case 5: Stack with Duplicates ===");
        int[] arr5 = {3, 3, 1, 2, 1, 4};
        Stack<Integer> stack5 = createStack(arr5);
        System.out.println("Before sorting:");
        printStack(stack5);
        
        sortStack(stack5);
        System.out.println("After sorting:");
        printStack(stack5);
        System.out.println("Is sorted correctly? " + isSorted(stack5));
        System.out.println();
        
        // Test Case 6: Negative numbers
        System.out.println("=== Test Case 6: Stack with Negative Numbers ===");
        int[] arr6 = {5, -2, 0, 3, -1};
        Stack<Integer> stack6 = createStack(arr6);
        System.out.println("Before sorting:");
        printStack(stack6);
        
        sortStack(stack6);
        System.out.println("After sorting:");
        printStack(stack6);
        System.out.println("Is sorted correctly? " + isSorted(stack6));
    }
}
