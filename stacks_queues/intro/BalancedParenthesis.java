package stacks_queues.intro;

import java.util.*;

/**
 * Check for Balanced Parenthesis
 * 
 * Problem: Given a string containing parentheses, check if they are balanced.
 * 
 * Valid Cases:
 * - (), []
 * - ()[], {}, ([])
 * - (([{}]))
 * 
 * Invalid Cases:
 * - ([)]  - Interleaved
 * - ([{)  - Mismatch
 * - ((()  - Missing closing
 * 
 * Algorithm:
 * 1. Use stack to track opening brackets
 * 2. For each character:
 *    - If opening bracket: push to stack
 *    - If closing bracket:
 *      - If stack is empty: return false (no matching opening)
 *      - Pop from stack and check if matches
 *      - If not matching: return false
 * 3. At end, stack should be empty
 * 
 * Brackets Types: (), [], {}
 * 
 * Time Complexity: O(n) - scan string once
 * Space Complexity: O(n) - stack in worst case
 */

public class BalancedParenthesis {
    
    /**
     * Check if parenthesis in string are balanced
     * Handles (), [], {}
     */
    public static boolean isBalanced(String s) {
        Stack<Character> stack = new Stack<>();
        
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                // Opening bracket: push to stack
                stack.push(c);
            } else if (c == ')' || c == ']' || c == '}') {
                // Closing bracket: check if matches
                if (stack.isEmpty()) {
                    return false;  // Closing bracket without opening
                }
                
                char open = stack.pop();
                if (!isMatching(open, c)) {
                    return false;  // Mismatch
                }
            }
            // Ignore other characters
        }
        
        // Stack should be empty at end
        return stack.isEmpty();
    }
    
    /**
     * Check if opening and closing bracket match
     */
    private static boolean isMatching(char open, char close) {
        return (open == '(' && close == ')') ||
               (open == '[' && close == ']') ||
               (open == '{' && close == '}');
    }
    
    /**
     * Alternative: Only parentheses, no brackets
     */
    public static boolean isBalancedParentheses(String s) {
        int count = 0;
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                count++;
            } else if (c == ')') {
                count--;
                if (count < 0) {
                    return false;  // More closing than opening
                }
            }
        }
        
        return count == 0;
    }
    
    /**
     * Get detailed analysis of balance
     */
    public static void analyzeBalance(String s) {
        Stack<Character> stack = new Stack<>();
        List<String> steps = new ArrayList<>();
        boolean valid = true;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
                steps.add(i + ": '" + c + "' (push) -> stack: " + stack);
            } else if (c == ')' || c == ']' || c == '}') {
                if (stack.isEmpty()) {
                    steps.add(i + ": '" + c + "' (error: no matching opening)");
                    valid = false;
                    break;
                }
                
                char open = stack.pop();
                if (isMatching(open, c)) {
                    steps.add(i + ": '" + c + "' (matches '" + open + "', pop) -> stack: " + stack);
                } else {
                    steps.add(i + ": '" + c + "' (mismatch with '" + open + "')");
                    valid = false;
                    break;
                }
            }
        }
        
        for (String step : steps) {
            System.out.println(step);
        }
        
        if (valid && !stack.isEmpty()) {
            System.out.println("Error: Unclosed brackets - stack: " + stack);
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== Balanced Parenthesis Check ===\n");
        
        String[] testCases = {
            "()",
            "()[]{}",
            "([{}])",
            "({[]})",
            "([)]",
            "([{})",
            "((()))",
            "{[()]}",
            "",
            "(",
            ")",
            "([)]",
            "(())",
            "()()())"
        };
        
        System.out.println(String.format("%-20s | %-15s", "String", "Balanced?"));
        System.out.println("-".repeat(40));
        
        for (String test : testCases) {
            boolean result = isBalanced(test);
            String display = test.isEmpty() ? "(empty)" : test;
            System.out.println(String.format("%-20s | %-15s", display, result ? "Yes" : "No"));
        }
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("DETAILED ANALYSIS: ([{}])");
        System.out.println("-".repeat(50));
        analyzeBalance("([{}])");
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("DETAILED ANALYSIS: ([)]");
        System.out.println("-".repeat(50));
        analyzeBalance("([)]");
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("DETAILED ANALYSIS: ((())");
        System.out.println("-".repeat(50));
        analyzeBalance("((())");
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("COMPLEXITY ANALYSIS:");
        System.out.println("-".repeat(50));
        System.out.println("Time Complexity: O(n)");
        System.out.println("  - Single pass through string");
        System.out.println("  - Each character processed once");
        System.out.println("\nSpace Complexity: O(n)");
        System.out.println("  - Stack stores up to n opening brackets");
        System.out.println("  - Worst case: all opening brackets");
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("EXAMPLES:");
        System.out.println("-".repeat(50));
        System.out.println("Valid:   (), [], {}, ()[], ([{}]), (([]))");
        System.out.println("Invalid: ([)], ([{), (((), ))");
        System.out.println("\nCommon errors:");
        System.out.println("1. ([)] - Interleaved (mismatch)");
        System.out.println("2. (() - Unclosed bracket");
        System.out.println("3. )) - No matching opening");
    }
}
