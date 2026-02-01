package greedy_algorithms.easy;
import java.util.*;

/**
 * Valid Parenthesis Checker
 *
 * Problem:
 * Given a string s containing only the characters:
 * '(' , ')' , '{' , '}' , '[' , ']'
 *
 * Determine if the input string is valid.
 *
 * A string is considered valid if:
 * 1. Open brackets are closed by the same type of brackets
 * 2. Open brackets are closed in the correct order
 * 3. Every closing bracket has a corresponding opening bracket
 *
 * ---------------------------------------------------------
 * Approach 1: Stack-Based Validation (Optimal)
 * ---------------------------------------------------------
 * - Use a stack to store opening brackets
 * - Traverse the string character by character
 * - If opening bracket → push to stack
 * - If closing bracket:
 *      - Stack must not be empty
 *      - Top of stack must match the closing bracket
 *      - Pop the matched opening bracket
 * - At the end, stack must be empty
 *
 * ---------------------------------------------------------
 * Why Stack?
 * ---------------------------------------------------------
 * - Parentheses follow LIFO (Last In First Out)
 * - The most recent opening bracket must be closed first
 *
 * ---------------------------------------------------------
 * Example:
 * s = "{[()]}"
 *
 * Steps:
 * { → push
 * [ → push
 * ( → push
 * ) → pop (
 * ] → pop [
 * } → pop {
 *
 * Stack empty → VALID
 *
 * ---------------------------------------------------------
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

public class ValidParenthesis {

    /**
     * Checks whether the given string has valid parentheses
     *
     * @param s input string
     * @return true if valid, false otherwise
     */
    public static boolean isValid(String s) {

        // Stack to store opening brackets
        Stack<Character> stack = new Stack<>();

        // Traverse each character in the string
        for (char ch : s.toCharArray()) {

            // If opening bracket, push onto stack
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            }
            // If closing bracket
            else {
                // If stack is empty, no matching opening bracket
                if (stack.isEmpty()) {
                    return false;
                }

                char top = stack.pop();

                // Check for matching pair
                if ((ch == ')' && top != '(') ||
                    (ch == '}' && top != '{') ||
                    (ch == ']' && top != '[')) {
                    return false;
                }
            }
        }

        // Stack should be empty if all brackets are matched
        return stack.isEmpty();
    }

    /**
     * Alternative implementation using HashMap (more scalable)
     */
    public static boolean isValidUsingMap(String s) {

        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');

        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {

            // Opening bracket
            if (map.containsValue(ch)) {
                stack.push(ch);
            }
            // Closing bracket
            else if (map.containsKey(ch)) {
                if (stack.isEmpty() || stack.pop() != map.get(ch)) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {

        System.out.println("=== Valid Parenthesis Checker ===\n");

        String[] testCases = {
                "()",
                "()[]{}",
                "{[()]}",
                "(]",
                "([)]",
                "{[]}",
                "",
                "((()))",
                "{[}"
        };

        System.out.println(String.format("%-15s | %-10s", "Input", "Valid"));
        System.out.println("-".repeat(30));

        for (String test : testCases) {
            System.out.println(String.format("%-15s | %-10s",
                    "\"" + test + "\"",
                    isValid(test)));
        }

        System.out.println("\n" + "=".repeat(40));
        System.out.println("DETAILED WALKTHROUGH EXAMPLE");
        System.out.println("-".repeat(40));

        String example = "{[()]}";
        Stack<Character> stack = new Stack<>();

        System.out.println("Input: " + example + "\n");

        for (char ch : example.toCharArray()) {
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
                System.out.println("Push: " + ch + " → Stack: " + stack);
            } else {
                char popped = stack.pop();
                System.out.println("Pop : " + popped + " for closing " + ch +
                        " → Stack: " + stack);
            }
        }

        System.out.println("\nFinal Stack: " + stack);
        System.out.println("Result: VALID");

        System.out.println("\n" + "=".repeat(40));
        System.out.println("COMPLEXITY ANALYSIS");
        System.out.println("-".repeat(40));
        System.out.println("Time Complexity : O(n)");
        System.out.println("Space Complexity: O(n)");
    }
}
