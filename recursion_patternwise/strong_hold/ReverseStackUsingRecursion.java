package recursion_patternwise.strong_hold;

import java.util.Stack;

public class ReverseStackUsingRecursion {

    /*
    =====================================================================================
    PROBLEM: REVERSE A STACK USING RECURSION
    -------------------------------------------------------------------------------------
    Reverse a stack using only recursion. Cannot use extra data structures like arrays,
    queues, or additional stacks for temporary storage. Only the recursive call stack
    can be used.
    
    Example:
    Input Stack (top to bottom): 1, 2, 3, 4, 5
    Output Stack (top to bottom): 5, 4, 3, 2, 1
    
    Approach 1: Pop-and-Insert Method
    1. Pop all elements using recursion
    2. During unwinding, insert each element at the bottom of stack
    3. Recursive call stack stores popped elements
    
    Approach 2: Using helper stack (if allowed)
    1. Pop and collect in temporary stack
    2. Push back in reverse order
    
    Time Complexity: O(n^2) - for each element, inserting at bottom takes O(n)
    Space Complexity: O(n) - recursive call stack
    =====================================================================================
    */
    
    /**
     * Reverse stack using recursion.
     * Pops all elements and inserts at bottom during unwinding.
     * 
     * Algorithm:
     * 1. Base case: if stack is empty, return
     * 2. Pop top element and store it
     * 3. Recursively reverse remaining stack
     * 4. Insert the popped element at the bottom
     * 
     * @param stack stack to reverse
     */
    public static void reverseStack(Stack<Integer> stack) {
        // Base case: empty stack
        if (stack.isEmpty()) {
            return;
        }
        
        // Pop top element
        int top = stack.pop();
        
        // Recursively reverse remaining stack
        reverseStack(stack);
        
        // Insert popped element at bottom
        insertAtBottom(stack, top);
    }
    
    /**
     * Insert element at the bottom of stack.
     * Recursively finds the bottom and inserts element there.
     * 
     * Algorithm:
     * 1. If stack is empty, push element
     * 2. Otherwise, pop top, recursively insert element, push back top
     * 
     * @param stack stack to insert into
     * @param value value to insert at bottom
     */
    private static void insertAtBottom(Stack<Integer> stack, int value) {
        // Base case: empty stack
        if (stack.isEmpty()) {
            stack.push(value);
            return;
        }
        
        // Pop top element
        int top = stack.pop();
        
        // Recursively insert value at bottom of remaining stack
        insertAtBottom(stack, value);
        
        // Push back the popped element
        stack.push(top);
    }
    
    /**
     * Alternative reverse using different approach.
     * More explicit step-by-step reversal.
     * 
     * @param stack stack to reverse
     */
    public static void reverseStackAlternative(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return;
        }
        
        int bottom = stack.pop();
        reverseStackAlternative(stack);
        pushAtBottom(stack, bottom);
    }
    
    /**
     * Push element at the bottom of stack.
     * 
     * @param stack stack to push into
     * @param value value to push at bottom
     */
    private static void pushAtBottom(Stack<Integer> stack, int value) {
        if (stack.isEmpty()) {
            stack.push(value);
            return;
        }
        
        int temp = stack.pop();
        pushAtBottom(stack, value);
        stack.push(temp);
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
     * Verify that stack is reversed correctly.
     * 
     * @param original original order
     * @param current current stack order
     * @return true if correctly reversed
     */
    public static boolean isReversed(int[] original, Stack<Integer> current) {
        Stack<Integer> temp = new Stack<>();
        
        // Check from bottom to top
        for (int i = 0; i < original.length; i++) {
            if (current.isEmpty()) {
                return false;
            }
            int val = current.pop();
            if (val != original[original.length - 1 - i]) {
                // Restore stack
                temp.push(val);
                while (!current.isEmpty()) {
                    temp.push(current.pop());
                }
                while (!temp.isEmpty()) {
                    current.push(temp.pop());
                }
                return false;
            }
            temp.push(val);
        }
        
        // Restore stack
        while (!temp.isEmpty()) {
            current.push(temp.pop());
        }
        
        return true;
    }
    
    /**
     * Get stack size recursively.
     * 
     * @param stack stack
     * @return size of stack
     */
    public static int getStackSize(Stack<Integer> stack) {
        if (stack.isEmpty()) {
            return 0;
        }
        int temp = stack.pop();
        int size = 1 + getStackSize(stack);
        stack.push(temp);
        return size;
    }
    
    /**
     * Print stack size and contents.
     * 
     * @param stack stack to analyze
     */
    public static void analyzeStack(Stack<Integer> stack) {
        int size = getStackSize(stack);
        System.out.println("Stack size: " + size);
        printStack(stack);
    }
    
    public static void main(String[] args) {
        // Test Case 1: Simple stack
        System.out.println("=== Test Case 1: Simple Stack ===");
        int[] arr1 = {1, 2, 3, 4, 5};
        Stack<Integer> stack1 = createStack(arr1);
        System.out.println("Before reversing:");
        analyzeStack(stack1);
        
        reverseStack(stack1);
        System.out.println("After reversing:");
        analyzeStack(stack1);
        System.out.println("Is correctly reversed? " + isReversed(arr1, stack1));
        System.out.println();
        
        // Test Case 2: Two element stack
        System.out.println("=== Test Case 2: Two Element Stack ===");
        int[] arr2 = {10, 20};
        Stack<Integer> stack2 = createStack(arr2);
        System.out.println("Before reversing:");
        analyzeStack(stack2);
        
        reverseStack(stack2);
        System.out.println("After reversing:");
        analyzeStack(stack2);
        System.out.println("Is correctly reversed? " + isReversed(arr2, stack2));
        System.out.println();
        
        // Test Case 3: Single element
        System.out.println("=== Test Case 3: Single Element Stack ===");
        int[] arr3 = {100};
        Stack<Integer> stack3 = createStack(arr3);
        System.out.println("Before reversing:");
        analyzeStack(stack3);
        
        reverseStack(stack3);
        System.out.println("After reversing:");
        analyzeStack(stack3);
        System.out.println("Is correctly reversed? " + isReversed(arr3, stack3));
        System.out.println();
        
        // Test Case 4: Larger stack
        System.out.println("=== Test Case 4: Larger Stack ===");
        int[] arr4 = {5, 10, 15, 20, 25, 30};
        Stack<Integer> stack4 = createStack(arr4);
        System.out.println("Before reversing:");
        analyzeStack(stack4);
        
        reverseStack(stack4);
        System.out.println("After reversing:");
        analyzeStack(stack4);
        System.out.println("Is correctly reversed? " + isReversed(arr4, stack4));
        System.out.println();
        
        // Test Case 5: Stack with duplicates
        System.out.println("=== Test Case 5: Stack with Duplicates ===");
        int[] arr5 = {1, 2, 1, 2, 1};
        Stack<Integer> stack5 = createStack(arr5);
        System.out.println("Before reversing:");
        analyzeStack(stack5);
        
        reverseStack(stack5);
        System.out.println("After reversing:");
        analyzeStack(stack5);
        System.out.println("Is correctly reversed? " + isReversed(arr5, stack5));
        System.out.println();
        
        // Test Case 6: Reversing twice gives original
        System.out.println("=== Test Case 6: Reversing Twice ===");
        int[] arr6 = {1, 2, 3, 4, 5};
        Stack<Integer> stack6 = createStack(arr6);
        System.out.println("Original:");
        analyzeStack(stack6);
        
        reverseStack(stack6);
        System.out.println("After first reverse:");
        analyzeStack(stack6);
        
        reverseStack(stack6);
        System.out.println("After second reverse:");
        analyzeStack(stack6);
        System.out.println("Back to original? " + isReversed(arr6, stack6));
    }
}
