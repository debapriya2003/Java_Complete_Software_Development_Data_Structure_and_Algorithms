package stacks_queues.PrefixInfixPostFixConversionProblems;

import java.util.*;

/**
 * Prefix to Infix Conversion
 * 
 * Prefix (Polish Notation): Operator comes before operands
 *        Example: + 2 * 3 4
 * 
 * Infix: Normal notation where operator is between operands
 *        Example: 2 + 3 * 4
 * 
 * Algorithm:
 * 1. Read prefix expression from RIGHT to LEFT
 * 2. Use a stack to store operands/expressions
 * 3. If character is operand: push to stack
 * 4. If character is operator:
 *    - Pop two operands from stack
 *    - Form infix: (operand1 op operand2)
 *    - Push back to stack
 * 5. Final stack should have one element (the infix expression)
 * 
 * Example: + 2 * 3 4
 * Reading from right to left: 4, 3, *, 2, +
 * 
 * 4: Stack = [4]
 * 3: Stack = [4, 3]
 * *: Pop 4, 3 -> (3*4), Stack = [(3*4)]
 * 2: Stack = [(3*4), 2]
 * +: Pop (3*4), 2 -> (2+(3*4)), Stack = [(2+(3*4))]
 * Result: (2+(3*4)) or 2 + 3 * 4
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n) for stack
 */

public class PrefixToInfix {
    
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
     * Convert prefix to infix
     */
    public static String prefixToInfix(String prefix) {
        Stack<String> stack = new Stack<>();
        
        // Read prefix from right to left
        for (int i = prefix.length() - 1; i >= 0; i--) {
            char c = prefix.charAt(i);
            
            if (isOperand(c)) {
                // If operand, push to stack
                stack.push(String.valueOf(c));
            } else if (isOperator(c)) {
                // If operator, pop two operands
                if (stack.size() >= 2) {
                    String operand1 = stack.pop();
                    String operand2 = stack.pop();
                    
                    // Form infix expression
                    String infix = "(" + operand1 + c + operand2 + ")";
                    stack.push(infix);
                }
            }
        }
        
        return stack.isEmpty() ? "" : stack.peek();
    }
    
    public static void main(String[] args) {
        System.out.println("=== Prefix to Infix Conversion ===\n");
        
        String[] testCases = {
            "+2*34",
            "+a*b-cd",
            "*+ab-cd",
            "/+ab*cd",
            "^+ab-cd",
            "-*+abc/def"
        };
        
        System.out.println(String.format("%-25s | %-30s", "Prefix", "Infix"));
        System.out.println("-".repeat(60));
        
        for (String prefix : testCases) {
            String infix = prefixToInfix(prefix);
            System.out.println(String.format("%-25s | %-30s", prefix, infix));
        }
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("DETAILED EXAMPLE: + 2 * 3 4");
        System.out.println("-".repeat(60));
        
        String prefix = "+2*34";
        Stack<String> stack = new Stack<>();
        
        System.out.println("\nStep-by-step conversion (right to left):\n");
        System.out.println("Input: " + prefix);
        System.out.println();
        
        for (int i = prefix.length() - 1; i >= 0; i--) {
            char c = prefix.charAt(i);
            System.out.println("Step " + (prefix.length() - i) + ": Character = '" + c + "'");
            
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
                    
                    String infix = "(" + operand1 + c + operand2 + ")";
                    System.out.println("  Form: " + infix);
                    
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
        
        prefix = "+2*34";
        stack = new Stack<>();
        
        System.out.println(String.format("%-8s | %-20s | %-30s", "Char", "Action", "Stack"));
        System.out.println("-".repeat(60));
        
        for (int i = prefix.length() - 1; i >= 0; i--) {
            char c = prefix.charAt(i);
            
            if (isOperand(c)) {
                stack.push(String.valueOf(c));
                System.out.println(String.format("%-8s | %-20s | %-30s", c, "Push", stack));
            } else if (isOperator(c)) {
                String op1 = stack.pop();
                String op2 = stack.pop();
                String infix = "(" + op1 + c + op2 + ")";
                stack.push(infix);
                System.out.println(String.format("%-8s | %-20s | %-30s", c, "Pop & combine", stack));
            }
        }
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("KEY POINTS:");
        System.out.println("-".repeat(60));
        System.out.println("1. Read prefix from RIGHT to LEFT");
        System.out.println("2. Operand: push to stack");
        System.out.println("3. Operator: pop 2 operands, combine as (op1 op op2), push back");
        System.out.println("4. Final result: single element in stack");
        System.out.println("5. Parentheses added for clarity (can be optimized)");
        System.out.println("6. Time Complexity: O(n)");
        System.out.println("7. Space Complexity: O(n)");
    }
}
