package stacks_queues.PrefixInfixPostFixConversionProblems;

import java.util.*;

/**
 * Prefix to Postfix Conversion
 * 
 * Prefix (Polish Notation): Operator before operands
 *        Example: + 2 * 3 4
 * 
 * Postfix (Reverse Polish Notation): Operator after operands
 *         Example: 2 3 4 * +
 * 
 * Algorithm:
 * 1. Read prefix expression from RIGHT to LEFT
 * 2. Use a stack to store operands/postfix expressions
 * 3. If character is operand: push to stack
 * 4. If character is operator:
 *    - Pop two operands/expressions
 *    - Form postfix: operand1 operand2 operator
 *    - Push back to stack
 * 5. Final stack should have one element
 * 
 * Example: + 2 * 3 4
 * Reading from right to left: 4, 3, *, 2, +
 * 
 * 4: Stack = [4]
 * 3: Stack = [4, 3]
 * *: Pop 3, 4 -> (3 4 *), Stack = [(3 4 *)]
 * 2: Stack = [(3 4 *), 2]
 * +: Pop 2, (3 4 *) -> (2 3 4 * +), Stack = [(2 3 4 * +)]
 * Result: 2 3 4 * +
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n) for stack
 */

public class PrefixToPostfix {
    
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
     * Convert prefix to postfix
     */
    public static String prefixToPostfix(String prefix) {
        Stack<String> stack = new Stack<>();
        
        // Read prefix from right to left
        for (int i = prefix.length() - 1; i >= 0; i--) {
            char c = prefix.charAt(i);
            
            if (isOperand(c)) {
                // If operand, push to stack
                stack.push(String.valueOf(c));
            } else if (isOperator(c)) {
                // If operator, pop two expressions
                if (stack.size() >= 2) {
                    String exp1 = stack.pop();
                    String exp2 = stack.pop();
                    
                    // Form postfix expression
                    String postfix = exp1 + exp2 + c;
                    stack.push(postfix);
                }
            }
        }
        
        return stack.isEmpty() ? "" : stack.peek();
    }
    
    /**
     * Alternative: Convert prefix to infix, then infix to postfix
     * (Using methods from other classes)
     */
    public static String prefixToPostfixViaInfix(String prefix) {
        // This would use PrefixToInfix and InfixToPostfix
        // Not implemented here to avoid circular dependencies
        return "";
    }
    
    public static void main(String[] args) {
        System.out.println("=== Prefix to Postfix Conversion ===\n");
        
        String[] testCases = {
            "+2*34",
            "+a*b-cd",
            "*+ab-cd",
            "/+ab*cd",
            "^+ab-cd",
            "-*+abc/def"
        };
        
        System.out.println(String.format("%-25s | %-30s", "Prefix", "Postfix"));
        System.out.println("-".repeat(60));
        
        for (String prefix : testCases) {
            String postfix = prefixToPostfix(prefix);
            System.out.println(String.format("%-25s | %-30s", prefix, postfix));
        }
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("DETAILED EXAMPLE: + 2 * 3 4");
        System.out.println("-".repeat(60));
        
        String prefix = "+2*34";
        Stack<String> stack = new Stack<>();
        
        System.out.println("\nStep-by-step conversion (right to left):\n");
        System.out.println("Input Prefix: " + prefix);
        System.out.println("Expected Postfix: 2 3 4 * +");
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
                    String exp1 = stack.pop();
                    String exp2 = stack.pop();
                    System.out.println("  Pop: " + exp1 + ", " + exp2);
                    
                    String postfix = exp1 + exp2 + c;
                    System.out.println("  Form postfix: " + exp1 + " " + exp2 + " " + c);
                    System.out.println("  Concatenate: " + postfix);
                    
                    stack.push(postfix);
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
        
        System.out.println(String.format("%-8s | %-20s | %-35s", "Char", "Action", "Stack"));
        System.out.println("-".repeat(65));
        
        for (int i = prefix.length() - 1; i >= 0; i--) {
            char c = prefix.charAt(i);
            
            if (isOperand(c)) {
                stack.push(String.valueOf(c));
                System.out.println(String.format("%-8s | %-20s | %-35s", c, "Push", stack));
            } else if (isOperator(c)) {
                String exp1 = stack.pop();
                String exp2 = stack.pop();
                String postfix = exp1 + exp2 + c;
                stack.push(postfix);
                System.out.println(String.format("%-8s | %-20s | %-35s", c, "Pop & combine", stack));
            }
        }
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("CONVERSION RULES:");
        System.out.println("-".repeat(60));
        System.out.println("When we encounter an operator:");
        System.out.println("  Pop exp1, exp2");
        System.out.println("  Result = exp1 + exp2 + operator");
        System.out.println("\nWhy this works:");
        System.out.println("  Prefix: op exp1 exp2");
        System.out.println("  Postfix: exp1 exp2 op");
        System.out.println("  When reading prefix right-to-left:");
        System.out.println("  We encounter exp2, then exp1, then op");
        System.out.println("  So we combine as: exp1 exp2 op");
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("KEY POINTS:");
        System.out.println("-".repeat(60));
        System.out.println("1. Read prefix from RIGHT to LEFT");
        System.out.println("2. Operand: push to stack");
        System.out.println("3. Operator: pop 2 expressions, combine as exp1 exp2 op");
        System.out.println("4. Final result: single element in stack");
        System.out.println("5. Time Complexity: O(n)");
        System.out.println("6. Space Complexity: O(n)");
    }
}
