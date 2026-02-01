package stacks_queues.PrefixInfixPostFixConversionProblems;

import java.util.*;

/**
 * Infix to Postfix Conversion using Stack
 * 
 * Infix: Normal notation where operator is between operands
 *        Example: 2 + 3 * 4
 * 
 * Postfix (Reverse Polish Notation): Operator comes after operands
 *         Example: 2 3 4 * +
 * 
 * Algorithm (Shunting Yard Algorithm):
 * 1. Scan infix expression from left to right
 * 2. If operand: add to output
 * 3. If operator:
 *    - While stack top has higher/equal precedence (left-to-right associative)
 *      Pop and add to output
 *    - Push current operator to stack
 * 4. If '(': push to stack
 * 5. If ')': pop from stack to output until '('
 * 6. Pop remaining operators from stack to output
 * 
 * Operator Precedence:
 * *, / : 2
 * +, - : 1
 * 
 * Example: 2 + 3 * 4
 * 2     : Output = 2
 * +     : Stack = [+]
 * 3     : Output = 2 3
 * *     : * > +, so Stack = [+, *]
 * 4     : Output = 2 3 4
 * End   : Pop all, Output = 2 3 4 * +
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n) for stack
 */

public class InfixToPostfix {
    
    /**
     * Get precedence of operator
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
                return 3;  // Exponentiation (higher precedence)
            default:
                return -1;
        }
    }
    
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
     * Convert infix to postfix
     */
    public static String infixToPostfix(String infix) {
        StringBuilder postfix = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        
        for (char c : infix.toCharArray()) {
            if (isOperand(c)) {
                // If operand, add to output
                postfix.append(c);
            } else if (c == '(') {
                // If '(', push to stack
                stack.push(c);
            } else if (c == ')') {
                // If ')', pop until '('
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop());
                }
                if (!stack.isEmpty()) {
                    stack.pop();  // Remove '('
                }
            } else if (isOperator(c)) {
                // If operator, pop higher/equal precedence, then push
                while (!stack.isEmpty() && stack.peek() != '(' &&
                       getPrecedence(stack.peek()) >= getPrecedence(c)) {
                    postfix.append(stack.pop());
                }
                stack.push(c);
            }
        }
        
        // Pop remaining operators
        while (!stack.isEmpty()) {
            postfix.append(stack.pop());
        }
        
        return postfix.toString();
    }
    
    public static void main(String[] args) {
        System.out.println("=== Infix to Postfix Conversion ===\n");
        
        String[] testCases = {
            "2+3*4",
            "a+b*c-d",
            "(a+b)*(c-d)",
            "a/(b+c)",
            "((a+b)*c)-d",
            "a+b*c/d",
            "a^b+c",
            "(a+b)/(c+d)"
        };
        
        System.out.println(String.format("%-25s | %-25s", "Infix", "Postfix"));
        System.out.println("-".repeat(55));
        
        for (String infix : testCases) {
            String postfix = infixToPostfix(infix);
            System.out.println(String.format("%-25s | %-25s", infix, postfix));
        }
        
        System.out.println("\n" + "=".repeat(55));
        System.out.println("DETAILED EXAMPLE: 2 + 3 * 4");
        System.out.println("-".repeat(55));
        
        String infix = "2+3*4";
        Stack<Character> stack = new Stack<>();
        StringBuilder postfix = new StringBuilder();
        
        System.out.println("\nStep-by-step conversion:\n");
        System.out.println("Input: " + infix);
        System.out.println();
        
        for (int i = 0; i < infix.length(); i++) {
            char c = infix.charAt(i);
            System.out.println("Step " + (i + 1) + ": Character = '" + c + "'");
            
            if (isOperand(c)) {
                postfix.append(c);
                System.out.println("  Operand: append to output");
                System.out.println("  Output: " + postfix.toString());
            } else if (c == '(') {
                stack.push(c);
                System.out.println("  '(': push to stack");
                System.out.println("  Stack: " + stack);
            } else if (c == ')') {
                System.out.println("  ')': pop until '('");
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop());
                }
                if (!stack.isEmpty()) stack.pop();
                System.out.println("  Output: " + postfix.toString());
            } else if (isOperator(c)) {
                System.out.println("  Operator: '" + c + "' (precedence: " + getPrecedence(c) + ")");
                
                while (!stack.isEmpty() && stack.peek() != '(' &&
                       getPrecedence(stack.peek()) >= getPrecedence(c)) {
                    char popped = stack.pop();
                    postfix.append(popped);
                    System.out.println("    Pop '" + popped + "' (higher/equal precedence)");
                }
                
                stack.push(c);
                System.out.println("  Push '" + c + "' to stack");
                System.out.println("  Stack: " + stack);
            }
            
            System.out.println();
        }
        
        System.out.println("Final: Pop remaining from stack");
        while (!stack.isEmpty()) {
            postfix.append(stack.pop());
        }
        System.out.println("Output: " + postfix.toString());
        
        System.out.println("\n" + "=".repeat(55));
        System.out.println("OPERATOR PRECEDENCE:");
        System.out.println("-".repeat(55));
        System.out.println("^ (Exponentiation): 3");
        System.out.println("*, /, %: 2");
        System.out.println("+, -: 1");
        
        System.out.println("\n" + "=".repeat(55));
        System.out.println("KEY POINTS:");
        System.out.println("-".repeat(55));
        System.out.println("1. Higher precedence operators are evaluated first");
        System.out.println("2. Parentheses override precedence");
        System.out.println("3. Same precedence operators use associativity");
        System.out.println("4. Time Complexity: O(n)");
        System.out.println("5. Space Complexity: O(n)");
    }
}
