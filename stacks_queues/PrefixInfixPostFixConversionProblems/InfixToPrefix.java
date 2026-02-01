package stacks_queues.PrefixInfixPostFixConversionProblems;

import java.util.*;

/**
 * Infix to Prefix Conversion
 * 
 * Infix: Normal notation with operator between operands
 *        Example: 2 + 3 * 4
 * 
 * Prefix (Polish Notation): Operator before operands
 *        Example: + 2 * 3 4
 * 
 * APPROACH 1: Reverse-Postfix-Reverse Method
 * ==========================================
 * 1. Reverse the infix expression
 * 2. Swap opening and closing parentheses
 * 3. Convert to postfix using standard algorithm
 * 4. Reverse the result
 * 
 * Example: 2 + 3 * 4
 * Step 1 - Reverse: 4 * 3 + 2
 * Step 2 - Swap ( ): Same (no parens in this example)
 * Step 3 - To Postfix: 4 3 * 2 +
 * Step 4 - Reverse: + 2 * 3 4
 * 
 * APPROACH 2: Direct Conversion using Modified Precedence
 * ========================================================
 * Read infix from RIGHT to LEFT using a stack
 * For operators, use REVERSE precedence and associativity
 * (Right associative becomes left, left becomes right)
 * 
 * We'll implement APPROACH 1 (reverse-postfix-reverse)
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n) for stack and auxiliary string
 */

public class InfixToPrefix {
    
    /**
     * Check if character is operator
     */
    public static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '%' || c == '^';
    }
    
    /**
     * Get operator precedence
     * Higher value = higher precedence
     */
    public static int getPrecedence(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
            case '%':
                return 2;
            case '^':
                return 3;
            default:
                return 0;
        }
    }
    
    /**
     * Check if character is operand
     */
    public static boolean isOperand(char c) {
        return Character.isLetterOrDigit(c);
    }
    
    /**
     * Convert infix to postfix
     */
    public static String infixToPostfix(String infix) {
        StringBuilder postfix = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        
        for (int i = 0; i < infix.length(); i++) {
            char c = infix.charAt(i);
            
            if (isOperand(c)) {
                postfix.append(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop());
                }
                if (!stack.isEmpty()) {
                    stack.pop(); // Remove '('
                }
            } else if (isOperator(c)) {
                while (!stack.isEmpty() && stack.peek() != '(' && 
                       isOperator(stack.peek()) && 
                       getPrecedence(stack.peek()) >= getPrecedence(c)) {
                    postfix.append(stack.pop());
                }
                stack.push(c);
            }
        }
        
        while (!stack.isEmpty()) {
            postfix.append(stack.pop());
        }
        
        return postfix.toString();
    }
    
    /**
     * Reverse a string
     */
    public static String reverseString(String s) {
        return new StringBuilder(s).reverse().toString();
    }
    
    /**
     * Swap parentheses: ( becomes ), ) becomes (
     */
    public static String swapParentheses(String s) {
        StringBuilder result = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                result.append(')');
            } else if (c == ')') {
                result.append('(');
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
    
    /**
     * Convert infix to prefix using reverse-postfix-reverse method
     */
    public static String infixToPrefix(String infix) {
        // Step 1: Reverse the infix expression
        String reversedInfix = reverseString(infix);
        
        // Step 2: Swap parentheses
        reversedInfix = swapParentheses(reversedInfix);
        
        // Step 3: Convert to postfix
        String postfix = infixToPostfix(reversedInfix);
        
        // Step 4: Reverse the postfix to get prefix
        String prefix = reverseString(postfix);
        
        return prefix;
    }
    
    public static void main(String[] args) {
        System.out.println("=== Infix to Prefix Conversion ===\n");
        
        String[] testCases = {
            "2+3*4",
            "a+b*c",
            "a*(b+c)",
            "(a+b)*(c+d)",
            "a+b/c-d",
            "a*(b+c*(d+e))"
        };
        
        System.out.println(String.format("%-25s | %-30s", "Infix", "Prefix"));
        System.out.println("-".repeat(60));
        
        for (String infix : testCases) {
            String prefix = infixToPrefix(infix);
            System.out.println(String.format("%-25s | %-30s", infix, prefix));
        }
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("DETAILED EXAMPLE: 2 + 3 * 4");
        System.out.println("-".repeat(60));
        
        String infix = "2+3*4";
        
        System.out.println("\nAlgorithm: Reverse-Postfix-Reverse Method\n");
        
        System.out.println("Step 1: Reverse the infix expression");
        String reversed = reverseString(infix);
        System.out.println("  Original: " + infix);
        System.out.println("  Reversed: " + reversed);
        
        System.out.println("\nStep 2: Swap parentheses");
        String swapped = swapParentheses(reversed);
        System.out.println("  Result: " + swapped + " (no change, no parentheses)");
        
        System.out.println("\nStep 3: Convert to postfix using Shunting Yard");
        String postfix = infixToPostfix(swapped);
        System.out.println("  Input: " + swapped);
        System.out.println("  Postfix: " + postfix);
        
        System.out.println("  Detailed steps:");
        Stack<Character> stack = new Stack<>();
        StringBuilder pb = new StringBuilder();
        
        System.out.println("  Character | Action              | Stack      | Postfix");
        System.out.println("  " + "-".repeat(58));
        
        for (int i = 0; i < swapped.length(); i++) {
            char c = swapped.charAt(i);
            String action = "";
            
            if (isOperand(c)) {
                pb.append(c);
                action = "Add to postfix";
            } else if (isOperator(c)) {
                while (!stack.isEmpty() && isOperator(stack.peek()) &&
                       getPrecedence(stack.peek()) >= getPrecedence(c)) {
                    pb.append(stack.pop());
                    action = "Pop & add";
                }
                stack.push(c);
                action = "Push to stack";
            }
            
            System.out.println(String.format("  %-9c | %-19s | %-10s | %-10s", 
                c, action, stack, pb.toString()));
        }
        
        while (!stack.isEmpty()) {
            pb.append(stack.pop());
        }
        
        System.out.println("  (empty) | Pop remaining       | " + stack + " | " + pb.toString());
        
        System.out.println("\nStep 4: Reverse the postfix to get prefix");
        String prefix = reverseString(postfix);
        System.out.println("  Postfix: " + postfix);
        System.out.println("  Prefix:  " + prefix);
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("DETAILED EXAMPLE: (a + b) * (c + d)");
        System.out.println("-".repeat(60));
        
        infix = "(a+b)*(c+d)";
        
        System.out.println("\nInfix: " + infix);
        
        reversed = reverseString(infix);
        System.out.println("Step 1 - Reverse: " + reversed);
        
        swapped = swapParentheses(reversed);
        System.out.println("Step 2 - Swap parentheses: " + swapped);
        
        postfix = infixToPostfix(swapped);
        System.out.println("Step 3 - To postfix: " + postfix);
        
        prefix = reverseString(postfix);
        System.out.println("Step 4 - Reverse: " + prefix);
        
        System.out.println("\nVerification:");
        System.out.println("  Prefix: " + prefix);
        System.out.println("  Meaning: " + "a + b multiply by c + d");
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("WHY THIS METHOD WORKS:");
        System.out.println("-".repeat(60));
        System.out.println("Infix to Prefix involves:");
        System.out.println("  1. Changing order of operands and operators");
        System.out.println("  2. Reversing associativity direction");
        System.out.println("  3. Handling operator precedence");
        System.out.println("\nUsing reverse-postfix-reverse:");
        System.out.println("  • Infix to Postfix is well understood (Shunting Yard)");
        System.out.println("  • Reversing before and after handles the transformation");
        System.out.println("  • Swapping parentheses preserves grouping");
        System.out.println("  • This avoids manual right-to-left processing");
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("KEY POINTS:");
        System.out.println("-".repeat(60));
        System.out.println("1. Reverse-Postfix-Reverse Method:");
        System.out.println("   Reverse infix → Convert to postfix → Reverse result");
        System.out.println("2. Must swap ( and ) in reversed infix");
        System.out.println("3. Operator precedence handled by Shunting Yard");
        System.out.println("4. Works for all expressions with +,-,*,/,%,^");
        System.out.println("5. Time Complexity: O(n)");
        System.out.println("6. Space Complexity: O(n)");
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("OPERATOR PRECEDENCE (used in Shunting Yard):");
        System.out.println("-".repeat(60));
        System.out.println("^ (Exponentiation): Precedence 3 (highest)");
        System.out.println("*, /, % (Multiply, Divide, Modulo): Precedence 2");
        System.out.println("+, - (Add, Subtract): Precedence 1 (lowest)");
        System.out.println("Parentheses: Override precedence");
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("STEP-BY-STEP TABLE FOR: a + b * c");
        System.out.println("-".repeat(60));
        
        String example = "a+b*c";
        
        System.out.println("\nInfix: " + example);
        
        reversed = reverseString(example);
        System.out.println("Reverse: " + reversed);
        
        swapped = swapParentheses(reversed);
        System.out.println("Swap parens: " + swapped);
        
        System.out.println("\nConvert to postfix:");
        System.out.println(String.format("%-8s | %-20s | %-15s | %-10s", "Char", "Action", "Stack", "Postfix"));
        System.out.println("-".repeat(60));
        
        stack = new Stack<>();
        pb = new StringBuilder();
        
        for (int i = 0; i < swapped.length(); i++) {
            char c = swapped.charAt(i);
            
            if (isOperand(c)) {
                pb.append(c);
                System.out.println(String.format("%-8c | %-20s | %-15s | %-10s", 
                    c, "Add to postfix", stack, pb.toString()));
            } else if (isOperator(c)) {
                while (!stack.isEmpty() && isOperator(stack.peek()) &&
                       getPrecedence(stack.peek()) >= getPrecedence(c)) {
                    pb.append(stack.pop());
                }
                stack.push(c);
                System.out.println(String.format("%-8c | %-20s | %-15s | %-10s", 
                    c, "Push to stack", stack, pb.toString()));
            }
        }
        
        while (!stack.isEmpty()) {
            pb.append(stack.pop());
        }
        
        System.out.println(String.format("%-8s | %-20s | %-15s | %-10s", 
            "END", "Pop remaining", stack, pb.toString()));
        
        postfix = pb.toString();
        System.out.println("\nPostfix: " + postfix);
        
        prefix = reverseString(postfix);
        System.out.println("Reverse to get Prefix: " + prefix);
    }
}
