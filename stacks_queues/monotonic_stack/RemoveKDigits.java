package stacks_queues.monotonic_stack;

import java.util.*;

/**
 * Remove K Digits (Smallest Number After Removing K Digits)
 * 
 * Problem: Given a string of digits and integer k,
 *          remove k digits to get the smallest possible number.
 *          Return as string. Leading zeros OK except if result is "0".
 * 
 * Example: num = "1432219", k = 3
 * Remove 4, 3, 2 → "1219" (smallest)
 * 
 * Example: num = "10200", k = 1
 * Remove 1 → "0200" → "200"
 * 
 * Example: num = "10", k = 2
 * Remove both → "0" → "" → "0"
 * 
 * Algorithm: Greedy with Monotonic Stack
 * ========================================
 * 1. Use stack to build result
 * 2. For each digit:
 *    - While stack not empty AND stack top > current digit AND k > 0:
 *      - Pop from stack and decrement k
 *    - Push current digit
 * 3. If k > 0 after processing all, remove k from end
 * 4. Remove leading zeros and return
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

public class RemoveKDigits {
    
    /**
     * Remove k digits to get smallest number
     */
    public static String removeKdigits(String num, int k) {
        int n = num.length();
        
        // If k >= n, return "0"
        if (k >= n) {
            return "0";
        }
        
        Stack<Character> stack = new Stack<>();
        
        // Process each digit
        for (char digit : num.toCharArray()) {
            // Remove larger digits when smaller one comes
            while (!stack.isEmpty() && k > 0 && stack.peek() > digit) {
                stack.pop();
                k--;
            }
            stack.push(digit);
        }
        
        // If k > 0, remove from end
        while (k > 0) {
            stack.pop();
            k--;
        }
        
        // Build result
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        
        // Reverse to get correct order
        String result = sb.reverse().toString();
        
        // Remove leading zeros
        result = result.replaceAll("^0+", "");
        
        // Return "0" if empty
        return result.isEmpty() ? "0" : result;
    }
    
    public static void main(String[] args) {
        System.out.println("=== Remove K Digits ===\n");
        
        String[][] testCases = {
            {"1432219", "3"},
            {"10200", "1"},
            {"10", "2"},
            {"9", "1"},
            {"112", "1"},
            {"99999999999", "9"}
        };
        
        System.out.println(String.format("%-20s | %-5s | %-20s", "Number", "K", "Result"));
        System.out.println("-".repeat(50));
        
        for (String[] test : testCases) {
            String num = test[0];
            int k = Integer.parseInt(test[1]);
            String result = removeKdigits(num, k);
            System.out.println(String.format("%-20s | %-5d | %-20s", num, k, result));
        }
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("DETAILED EXAMPLE: \"1432219\", k=3");
        System.out.println("-".repeat(50));
        
        String num = "1432219";
        int k = 3;
        
        System.out.println("\nGoal: Remove 3 digits to get smallest number");
        System.out.println("String: " + num);
        System.out.println("\nStep-by-step processing:\n");
        
        Stack<Character> stack = new Stack<>();
        int tempK = k;
        
        for (int i = 0; i < num.length(); i++) {
            char digit = num.charAt(i);
            System.out.println("Step " + (i + 1) + ": Process digit '" + digit + "'");
            System.out.println("  Stack before: " + stack);
            System.out.println("  k remaining: " + tempK);
            
            int popCount = 0;
            while (!stack.isEmpty() && tempK > 0 && stack.peek() > digit) {
                char popped = stack.pop();
                System.out.println("  Pop '" + popped + "' (> " + digit + "), k--");
                tempK--;
                popCount++;
            }
            
            if (popCount == 0) {
                System.out.println("  No pops needed");
            }
            
            stack.push(digit);
            System.out.println("  Push '" + digit + "'");
            System.out.println("  Stack after: " + stack);
            System.out.println();
        }
        
        System.out.println("After processing all digits:");
        System.out.println("  Stack: " + stack);
        System.out.println("  k remaining: " + tempK);
        
        if (tempK > 0) {
            System.out.println("\nRemaining k=" + tempK + ", remove from end:");
            for (int i = 0; i < tempK; i++) {
                char popped = stack.pop();
                System.out.println("  Pop '" + popped + "'");
            }
        }
        
        System.out.println("\nFinal stack: " + stack);
        
        // Build result
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        String result = sb.reverse().toString();
        
        System.out.println("Result before cleanup: " + result);
        
        result = result.replaceAll("^0+", "");
        if (result.isEmpty()) {
            result = "0";
        }
        
        System.out.println("Final result: " + result);
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("DETAILED EXAMPLE: \"10200\", k=1");
        System.out.println("-".repeat(50));
        
        num = "10200";
        k = 1;
        
        System.out.println("\nString: " + num);
        System.out.println("\nProcessing:");
        
        stack = new Stack<>();
        tempK = k;
        
        for (int i = 0; i < num.length(); i++) {
            char digit = num.charAt(i);
            System.out.print("'" + digit + "': ");
            
            while (!stack.isEmpty() && tempK > 0 && stack.peek() > digit) {
                stack.pop();
                tempK--;
                System.out.print("pop, ");
            }
            
            stack.push(digit);
            System.out.println("push. Stack=" + stack);
        }
        
        result = removeKdigits(num, k);
        System.out.println("\nResult: " + result);
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("EDGE CASE: \"10\", k=2");
        System.out.println("-".repeat(50));
        
        num = "10";
        k = 2;
        result = removeKdigits(num, k);
        
        System.out.println("\nString: " + num);
        System.out.println("Remove all digits: " + result);
        System.out.println("Return \"0\" for empty result");
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("ALGORITHM EXPLANATION:");
        System.out.println("-".repeat(50));
        System.out.println("1. Use monotonic increasing stack");
        System.out.println("2. When smaller digit comes, pop larger ones");
        System.out.println("3. Greedy: Remove larger digit when possible");
        System.out.println("4. If k digits still remain, remove from end");
        System.out.println("5. This leaves smaller digits at front");
        System.out.println("6. Remove leading zeros and handle edge case");
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("WHY THIS WORKS:");
        System.out.println("-".repeat(50));
        System.out.println("• Each digit has certain significance (left = higher)");
        System.out.println("• To minimize, we want smaller digits on left");
        System.out.println("• If current digit < previous, previous can be removed");
        System.out.println("• Greedy approach: always remove when beneficial");
        System.out.println("• Monotonic stack maintains increasing order");
        System.out.println("• Ensures lexicographically smallest result");
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("KEY INSIGHTS:");
        System.out.println("-".repeat(50));
        System.out.println("1. Time: O(n) - each digit processed once");
        System.out.println("2. Space: O(n) - stack stores characters");
        System.out.println("3. Handle k exhaustion: remove from end if needed");
        System.out.println("4. Remove leading zeros at the end");
        System.out.println("5. Return \"0\" for empty result");
    }
}
