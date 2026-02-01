package stacks_queues.PrefixInfixPostFixConversionProblems;

import java.util.*;

/**
 * Postfix to Infix Conversion
 * 
 * Postfix (Reverse Polish Notation): Operator after operands
 *         Example: 2 3 4 * +
 * 
 * Infix: Normal notation where operator is between operands
 *        Example: 2 + 3 * 4
 * 
 * Algorithm:
 * 1. Read postfix expression from LEFT to RIGHT
 * 2. Use a stack to store operands/expressions
 * 3. If character is operand: push to stack
 * 4. If character is operator:
 *    - Pop two operands/expressions
 *    - Form infix: (operand1 operator operand2)
 *    - Push back to stack
 * 5. Final stack should have one element (the infix expression)
 * 
 * Example: 2 3 4 * +
 * 
 * 2: Stack = [2]
 * 3: Stack = [2, 3]
 * 4: Stack = [2, 3, 4]
 * *: Pop 4, 3 -> (3*4), Stack = [2, (3*4)]
 * +: Pop (3*4), 2 -> (2+(3*4)), Stack = [(2+(3*4))]
 * Result: (2+(3*4)) or 2 + 3 * 4
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n) for stack
 */

public class PostfixToInfix {
    
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
     * Convert postfix to infix
     */
    public static String postfixToInfix(String postfix) {
        Stack<String> stack = new Stack<>();
        
        // Read postfix from left to right
        for (int i = 0; i < postfix.length(); i++) {
            char c = postfix.charAt(i);
            
            if (isOperand(c)) {
                // If operand, push to stack
                stack.push(String.valueOf(c));
            } else if (isOperator(c)) {
                // If operator, pop two operands
                if (stack.size() >= 2) {
                    String operand2 = stack.pop();  // Right operand
                    String operand1 = stack.pop();  // Left operand
                    
                    // Form infix expression
                    String infix = "(" + operand1 + c + operand2 + ")";
                    stack.push(infix);
                }
            }
        }
        
        return stack.isEmpty() ? "" : stack.peek();
    }
    
    public static void main(String[] args) {
        System.out.println("=== Postfix to Infix Conversion ===\n");
        
        String[] testCases = {
            "234*+",
            "ab*cd-+",
            "ab+cd-*",
            "ab+cd*-",
            "ab+cd-*",
            "abc*+def/-"
        };
        
        System.out.println(String.format("%-25s | %-30s", "Postfix", "Infix"));
        System.out.println("-".repeat(60));
        
        for (String postfix : testCases) {
            String infix = postfixToInfix(postfix);
            System.out.println(String.format("%-25s | %-30s", postfix, infix));
        }
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("DETAILED EXAMPLE: 2 3 4 * +");
        System.out.println("-".repeat(60));
        
        String postfix = "234*+";
        Stack<String> stack = new Stack<>();
        
        System.out.println("\nStep-by-step conversion (left to right):\n");
        System.out.println("Input: " + postfix);
        System.out.println();
        
        for (int i = 0; i < postfix.length(); i++) {
            char c = postfix.charAt(i);
            System.out.println("Step " + (i + 1) + ": Character = '" + c + "'");
            
            if (isOperand(c)) {
                stack.push(String.valueOf(c));
                System.out.println("  Operand: push to stack");
                System.out.println("  Stack: " + stack);
            } else if (isOperator(c)) {
                System.out.println("  Operator: '" + c + "'");
                
                if (stack.size() >= 2) {
                    String operand2 = stack.pop();
                    String operand1 = stack.pop();
                    System.out.println("  Pop: " + operand1 + ", " + operand2);
                    
                    String infix = "(" + operand1 + c + operand2 + ")";
                    System.out.println("  Form infix: (" + operand1 + c + operand2 + ")");
                    
                    stack.push(infix);
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
        
        for (int i = 0; i < postfix.length(); i++) {
            char c = postfix.charAt(i);
            
            if (isOperand(c)) {
                stack.push(String.valueOf(c));
                System.out.println(String.format("%-8s | %-20s | %-35s", c, "Push", stack));
            } else if (isOperator(c)) {
                String op2 = stack.pop();
                String op1 = stack.pop();
                String infix = "(" + op1 + c + op2 + ")";
                stack.push(infix);
                System.out.println(String.format("%-8s | %-20s | %-35s", c, "Pop & combine", stack));
            }
        }
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("CONVERSION RULES:");
        System.out.println("-".repeat(60));
        System.out.println("When we encounter an operator (left to right):");
        System.out.println("  Pop operand2 (right), operand1 (left)");
        System.out.println("  Result = (operand1 operator operand2)");
        System.out.println("\nWhy this works:");
        System.out.println("  Postfix: operand1 operand2 operator");
        System.out.println("  Infix: (operand1 operator operand2)");
        System.out.println("  When reading left-to-right:");
        System.out.println("  operand1 and operand2 are pushed in order");
        System.out.println("  When operator comes, we pop in reverse order");
        System.out.println("  Pop operand2 (top), then operand1");
        System.out.println("  Form: operand1 operator operand2");
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("KEY POINTS:");
        System.out.println("-".repeat(60));
        System.out.println("1. Read postfix from LEFT to RIGHT (unlike prefix)");
        System.out.println("2. Operand: push to stack");
        System.out.println("3. Operator: pop 2 operands, combine as (op1 op op2)");
        System.out.println("4. Final result: single element in stack");
        System.out.println("5. Parentheses added for clarity");
        System.out.println("6. Time Complexity: O(n)");
        System.out.println("7. Space Complexity: O(n)");
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("COMPARISON: PREFIX vs POSTFIX CONVERSION");
        System.out.println("-".repeat(60));
        System.out.println("To Infix:");
        System.out.println("  Prefix:  Read RIGHT to LEFT");
        System.out.println("  Postfix: Read LEFT to RIGHT");
        System.out.println("\nTo Postfix:");
        System.out.println("  Prefix:  Read RIGHT to LEFT");
        System.out.println("  Postfix: (to prefix)");
        System.out.println("\nTo Prefix:");
        System.out.println("  Postfix: Read RIGHT to LEFT");
        System.out.println("  Infix: Use precedence and associativity");
    }
}
