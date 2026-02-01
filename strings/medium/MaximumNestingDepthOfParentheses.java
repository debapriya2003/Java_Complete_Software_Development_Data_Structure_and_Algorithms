package strings.medium;

import java.util.*;

public class MaximumNestingDepthOfParentheses {

    /*
    =====================================================================================
    PROBLEM: MAXIMUM NESTING DEPTH OF PARENTHESES
    -------------------------------------------------------------------------------------
    Given a string s containing only digits and parentheses, find the maximum nesting 
    depth of the parentheses. The nesting depth is the number of opening parentheses 
    that must be closed at any given point. For example, "(1+(2*3)+((8)/4))+1" has 
    maximum nesting depth of 3 at position where we see "((". Characters that are not 
    parentheses (digits, operators, spaces) should be ignored. This is a fundamental 
    problem for understanding stack-based depth tracking.

    Time Complexity: O(n) where n is string length (single pass)
    Space Complexity: O(1) (constant space, no stack needed)

    Example:
    Input:  s = "(1+(2*3)+((8)/4))+1"
    Output: 3 (at "((", the depth is 3)
    =====================================================================================
    */
    
    /**
     * Finds maximum nesting depth by tracking opening and closing parentheses.
     * Iterates through string, increments depth for '(', decrements for ')'.
     * Records maximum depth seen. Ignores all non-parenthesis characters.
     * Clean and efficient approach using counter variable.
     * 
     * @param s input string with digits, parentheses, operators
     * @return maximum nesting depth of parentheses
     */
    public static int maxDepth(String s) {
        int currentDepth = 0;
        int maxDepth = 0;
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                currentDepth++;
                maxDepth = Math.max(maxDepth, currentDepth);
            } else if (c == ')') {
                currentDepth--;
            }
            // Ignore all other characters (digits, operators, spaces)
        }
        
        return maxDepth;
    }
    
    /**
     * Alternative approach using explicit stack to track parentheses.
     * While less efficient than counter approach, demonstrates stack-based
     * thinking useful for more complex parenthesis problems.
     * 
     * @param s input string
     * @return maximum nesting depth
     */
    public static int maxDepthStack(String s) {
        Stack<Character> stack = new Stack<>();
        int maxDepth = 0;
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(c);
                maxDepth = Math.max(maxDepth, stack.size());
            } else if (c == ')') {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            }
        }
        
        return maxDepth;
    }
    
    /**
     * Regex-based approach for finding pattern with maximum nested parentheses.
     * Finds the deepest level of nesting by repeatedly removing innermost
     * parentheses pairs until none remain. Less efficient but shows alternative thinking.
     * 
     * @param s input string
     * @return maximum nesting depth
     */
    public static int maxDepthRegex(String s) {
        int maxDepth = 0;
        String temp = s;
        
        while (temp.contains("()")) {
            maxDepth++;
            temp = temp.replaceAll("\\(\\)", "");
        }
        
        return maxDepth;
    }
    
    /**
     * Helper function to validate that parentheses are balanced.
     * Useful for preprocessing to ensure valid input before finding depth.
     * 
     * @param s input string
     * @return true if parentheses are balanced, false otherwise
     */
    public static boolean isBalanced(String s) {
        int depth = 0;
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                depth++;
            } else if (c == ')') {
                depth--;
                if (depth < 0) {
                    return false;  // More closing than opening
                }
            }
        }
        
        return depth == 0;  // All opened parentheses closed
    }
    
    /**
     * Helper function to find all positions with maximum depth.
     * Returns array of indices where maximum depth occurs in string.
     * Useful for analyzing where deepest nesting happens.
     * 
     * @param s input string
     * @return array of indices where max depth is reached
     */
    public static int[] getMaxDepthPositions(String s) {
        int currentDepth = 0;
        int maxDepth = 0;
        List<Integer> positions = new ArrayList<>();
        
        // First pass: find max depth
        for (char c : s.toCharArray()) {
            if (c == '(') {
                currentDepth++;
                maxDepth = Math.max(maxDepth, currentDepth);
            } else if (c == ')') {
                currentDepth--;
            }
        }
        
        // Second pass: record all positions where max depth is reached
        currentDepth = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                currentDepth++;
                if (currentDepth == maxDepth) {
                    positions.add(i);
                }
            } else if (c == ')') {
                currentDepth--;
            }
        }
        
        int[] result = new int[positions.size()];
        for (int i = 0; i < positions.size(); i++) {
            result[i] = positions.get(i);
        }
        return result;
    }
    
    public static void main(String[] args) {
        System.out.println("Max depth of \"(1+(2*3)+((8)/4))+1\": " + 
            maxDepth("(1+(2*3)+((8)/4))+1"));                      // 3
        System.out.println("Max depth of \"(1)+((2))\": " + 
            maxDepth("(1)+((2))"));                                 // 2
        System.out.println("Max depth of \"1+(2*3)/(2-1)\": " + 
            maxDepth("1+(2*3)/(2-1)"));                             // 1
        System.out.println("Max depth of \"((()))\": " + 
            maxDepth("((()))"));                                    // 3
        System.out.println("Max depth of \"123\": " + 
            maxDepth("123"));                                       // 0
        
        System.out.println("\nUsing stack approach:");
        System.out.println("Max depth of \"(1((2)3))\": " + maxDepthStack("(1((2)3))"));  // 3
        
        System.out.println("\nValidation:");
        System.out.println("Is \"(1+2)\" balanced: " + isBalanced("(1+2)"));               // true
        System.out.println("Is \"((1+2)\" balanced: " + isBalanced("((1+2)"));             // false
        System.out.println("Is \"(1+2))\" balanced: " + isBalanced("(1+2))"));             // false
        
        System.out.println("\nPositions with max depth in \"(1+(2*3)+((8)/4))+1\":");
        System.out.println(Arrays.toString(getMaxDepthPositions("(1+(2*3)+((8)/4))+1")));
    }
}
