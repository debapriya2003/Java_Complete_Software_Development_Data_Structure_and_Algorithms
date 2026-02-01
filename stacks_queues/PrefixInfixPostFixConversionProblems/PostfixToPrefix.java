package stacks_queues.PrefixInfixPostFixConversionProblems;

import java.util.*;

/**
 * Postfix to Prefix Conversion
 * 
 * Postfix (Reverse Polish Notation): Operator after operands
 *         Example: 2 3 4 * +
 * 
 * Prefix (Polish Notation): Operator before operands
 *        Example: + 2 * 3 4
 * 
 * Algorithm:
 * 1. Read postfix expression from RIGHT to LEFT
 * 2. Use a stack to store operands/expressions
 * 3. If character is operand: push to stack
 * 4. If character is operator:
 *    - Pop two expressions/operands
 *    - Form prefix: operator operand1 operand2
 *    - Push back to stack
 * 5. Final stack should have one element
 * 
 * Example: 2 3 4 * +
 * Reading from right to left: +, *, 4, 3, 2
 * 
 * +: Stack = [+] (wait, need operands)
 * Actually, let me redo this:
 * Reading from right: +, *, 4, 3, 2
 * But that's backwards. Let me read as: 2, 3, 4, *, +
 * Actually reading RIGHT to LEFT from "2 3 4 * +"
 * That gives: +, *, 4, 3, 2
 * 
 * Process from right to left of "23 4*+":
 * Position: 4 3 2 1 0
 * Value:    + * 4 3 2
 * 
 * i=4(+): Stack = [+]
 * i=3(*): Stack = [+, *]
 * i=2(4): Stack = [+, *, 4]
 * i=1(3): Stack = [+, *, 4, 3]
 * i=0(2): Stack = [+, *, 4, 3, 2]
 * 
 * Then pop from left:
 * 2 pop: prefix = "2", stack = [+, *, 4, 3]
 * 3 pop: prefix = "3", stack = [+, *, 4]
 * 4 pop: prefix = "4", stack = [+, *]
 * * pop: prefix = "* 4 3", stack = [+]
 * + pop: prefix = "+ 2 * 4 3", stack = []
 * 
 * Wait, let me reconsider. Standard approach:
 * Read postfix from RIGHT to LEFT, use stack:
 * If operand: push
 * If operator: pop 2 operands, form "op operand1 operand2", push
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n) for stack
 */

public class PostfixToPrefix {
    
    /**
     * Check if character is operator
     */
    public static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '%' || c == '^';
    }
    
    /**
     * Check if character is operand
     */
    public static boolean isOperand(char c) {
        return Character.isLetterOrDigit(c);
    }
    
    /**
     * Convert postfix to prefix
     */
    public static String postfixToPrefix(String postfix) {
        Stack<String> stack = new Stack<>();
        
        // Read postfix from right to left
        for (int i = postfix.length() - 1; i >= 0; i--) {
            char c = postfix.charAt(i);
            
            if (isOperand(c)) {
                // If operand, push to stack
                stack.push(String.valueOf(c));
            } else if (isOperator(c)) {
                // If operator, pop two expressions
                if (stack.size() >= 2) {
                    String operand1 = stack.pop();
                    String operand2 = stack.pop();
                    
                    // Form prefix expression: operator operand1 operand2
                    String prefix = c + operand1 + operand2;
                    stack.push(prefix);
                }
            }
        }
        
        return stack.isEmpty() ? "" : stack.peek();
    }
    
    public static void main(String[] args) {
        System.out.println("=== Postfix to Prefix Conversion ===\n");
        
        String[] testCases = {
            "234*+",
            "ab*cd-+",
            "ab+cd-*",
            "ab+cd*-",
            "ab+cd-*",
            "abc*+def/-"
        };
        
        System.out.println(String.format("%-25s | %-30s", "Postfix", "Prefix"));
        System.out.println("-".repeat(60));
        
        for (String postfix : testCases) {
            String prefix = postfixToPrefix(postfix);
            System.out.println(String.format("%-25s | %-30s", postfix, prefix));
        }
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("DETAILED EXAMPLE: 2 3 4 * +");
        System.out.println("-".repeat(60));
        
        String postfix = "234*+";
        Stack<String> stack = new Stack<>();
        
        System.out.println("\nStep-by-step conversion (right to left):\n");
        System.out.println("Input Postfix: " + postfix);
        System.out.println("Expected Prefix: +2*34");
        System.out.println();
        
        for (int i = postfix.length() - 1; i >= 0; i--) {
            char c = postfix.charAt(i);
            System.out.println("Step " + (postfix.length() - i) + ": Character = '" + c + "'");
            
            if (isOperand(c)) {
                stack.push(String.valueOf(c));
                System.out.println("  Operand: push to stack");
                System.out.println("  Stack: " + stack);
            } else if (isOperator(c)) {
                System.out.println("  Operator: '" + c + "'");
                
                if (stack.size() >= 2) {
                    String operand1 = stack.pop();
                    String operand2 = stack.pop();
                    System.out.println("  Pop: " + operand1 + ", " + operand2);
                    
                    String prefix = c + operand1 + operand2;
                    System.out.println("  Form prefix: " + c + operand1 + operand2);
                    
                    stack.push(prefix);
                    System.out.println("  Push to stack");
                    System.out.println("  Stack: " + stack);
                }
            }
            
            System.out.println();
        }
        
        System.out.println("Result: " + stack.peek());
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("STEP-BY-STEP TABLE:");
        System.out.println("-".repeat(60));
        
        postfix = "234*+";
        stack = new Stack<>();
        
        System.out.println(String.format("%-8s | %-20s | %-35s", "Char", "Action", "Stack"));
        System.out.println("-".repeat(65));
        
        for (int i = postfix.length() - 1; i >= 0; i--) {
            char c = postfix.charAt(i);
            
            if (isOperand(c)) {
                stack.push(String.valueOf(c));
                System.out.println(String.format("%-8s | %-20s | %-35s", c, "Push", stack));
            } else if (isOperator(c)) {
                String op1 = stack.pop();
                String op2 = stack.pop();
                String prefix = c + op1 + op2;
                stack.push(prefix);
                System.out.println(String.format("%-8s | %-20s | %-35s", c, "Pop & combine", stack));
            }
        }
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("CONVERSION RULES:");
        System.out.println("-".repeat(60));
        System.out.println("When we encounter an operator (right to left):");
        System.out.println("  Pop operand1, operand2");
        System.out.println("  Result = operator + operand1 + operand2");
        System.out.println("\nWhy this works:");
        System.out.println("  Postfix: operand1 operand2 operator");
        System.out.println("  Prefix: operator operand1 operand2");
        System.out.println("  Reading postfix right-to-left:");
        System.out.println("  We encounter operator, then operand2, then operand1");
        System.out.println("  Stack order: operand1, operand2");
        System.out.println("  Pop order: operand1 first, operand2 second");
        System.out.println("  So: operator + operand1 + operand2");
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("KEY POINTS:");
        System.out.println("-".repeat(60));
        System.out.println("1. Read postfix from RIGHT to LEFT");
        System.out.println("2. Operand: push to stack");
        System.out.println("3. Operator: pop 2 operands, combine as op op1 op2");
        System.out.println("4. Final result: single element in stack");
        System.out.println("5. Time Complexity: O(n)");
        System.out.println("6. Space Complexity: O(n)");
    }
}
