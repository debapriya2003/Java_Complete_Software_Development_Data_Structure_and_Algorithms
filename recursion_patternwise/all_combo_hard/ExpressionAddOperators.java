package recursion_patternwise.all_combo_hard;

import java.util.ArrayList;
import java.util.List;

public class ExpressionAddOperators {

    /*
    =====================================================================================
    PROBLEM: EXPRESSION ADD OPERATORS
    -------------------------------------------------------------------------------------
    Given a string containing only digits and a target value, add operators (+, -, *, /)
    between the digits such that the expression evaluates to the target.
    
    Rules:
    1. Can only add +, -, *, / between digits
    2. No leading zeros allowed in numbers (except "0" itself)
    3. / is integer division (truncate toward zero)
    4. All intermediate operations are performed in the order they appear
    5. Order of operations: * and / before + and -
    
    Example:
    Input: num = "123", target = 6
    Output: ["1+2+3", "1*2*3"]
    
    Approach: Backtracking with Expression Evaluation
    1. Use backtracking to generate all possible expressions
    2. At each position, try adding +, -, *, / operators
    3. Evaluate expression as we build it (handle operator precedence)
    4. Keep track of last operand to handle * and / properly
    5. When reaching end, check if expression equals target
    
    Time Complexity: O(4^N) - 4 choices of operators for N-1 positions
    Space Complexity: O(N) - recursion depth
    =====================================================================================
    */
    
    /**
     * Find all expressions that evaluate to target.
     * 
     * @param num string of digits
     * @param target target value
     * @return list of valid expressions
     */
    public static List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();
        backtrack(num, 0, "", 0, 0, target, result);
        return result;
    }
    
    /**
     * Backtracking helper to generate expressions.
     * 
     * Algorithm:
     * 1. path: current expression being built
     * 2. index: current position in num string
     * 3. eval: current evaluated value
     * 4. last: last operand (needed for * and / precedence)
     * 
     * For each digit:
     * - Try adding with + (eval + digit, last = digit)
     * - Try adding with - (eval - digit, last = -digit)
     * - Try adding with * (eval - last + last * digit, last = last * digit)
     * - Try adding with / (eval - last + last / digit, last = last / digit)
     * 
     * @param num digit string
     * @param index current position
     * @param path current expression
     * @param eval current evaluated sum (handles all + and -)
     * @param last last operand (for * and / precedence)
     * @param target target value
     * @param result valid expressions
     */
    private static void backtrack(String num, int index, String path, 
                                  long eval, long last, int target, List<String> result) {
        int n = num.length();
        
        // Base case: reached end of string
        if (index == n) {
            if (eval == target) {
                result.add(path);
            }
            return;
        }
        
        // Build current number
        for (int i = index; i < n; i++) {
            // Avoid leading zeros (except "0" itself)
            if (i > index && num.charAt(index) == '0') {
                break;
            }
            
            long currentNum = Long.parseLong(num.substring(index, i + 1));
            
            if (index == 0) {
                // First number - no operator before it
                backtrack(num, i + 1, Long.toString(currentNum), currentNum, currentNum, target, result);
            } else {
                // Try + operator
                backtrack(num, i + 1, path + "+" + currentNum, 
                         eval + currentNum, currentNum, target, result);
                
                // Try - operator
                backtrack(num, i + 1, path + "-" + currentNum, 
                         eval - currentNum, -currentNum, target, result);
                
                // Try * operator (precedence: remove last, multiply, add back)
                backtrack(num, i + 1, path + "*" + currentNum, 
                         eval - last + last * currentNum, last * currentNum, target, result);
                
                // Try / operator (integer division)
                backtrack(num, i + 1, path + "/" + currentNum, 
                         eval - last + last / currentNum, last / currentNum, target, result);
            }
        }
    }
    
    /**
     * Evaluate a mathematical expression with +, -, *, / operators.
     * Follows standard order of operations (* and / before + and -).
     * 
     * @param expression string expression
     * @return evaluated result
     */
    public static long evaluateExpression(String expression) {
        List<Long> numbers = new ArrayList<>();
        List<Character> operators = new ArrayList<>();
        
        // Parse expression
        int i = 0;
        while (i < expression.length()) {
            if (Character.isDigit(expression.charAt(i))) {
                long num = 0;
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    num = num * 10 + (expression.charAt(i) - '0');
                    i++;
                }
                numbers.add(num);
            } else {
                operators.add(expression.charAt(i));
                i++;
            }
        }
        
        // Handle * and /
        for (int j = 0; j < operators.size(); j++) {
            char op = operators.get(j);
            if (op == '*' || op == '/') {
                long left = numbers.get(j);
                long right = numbers.get(j + 1);
                long result = (op == '*') ? left * right : left / right;
                
                numbers.set(j, result);
                numbers.remove(j + 1);
                operators.remove(j);
                j--;
            }
        }
        
        // Handle + and -
        long result = numbers.get(0);
        for (int j = 0; j < operators.size(); j++) {
            char op = operators.get(j);
            result += (op == '+') ? numbers.get(j + 1) : -numbers.get(j + 1);
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        // Test Case 1: Simple case
        System.out.println("=== Test Case 1: num = \"123\", target = 6 ===");
        String num1 = "123";
        int target1 = 6;
        List<String> result1 = addOperators(num1, target1);
        System.out.println("Results: " + result1);
        for (String expr : result1) {
            System.out.println(expr + " = " + evaluateExpression(expr));
        }
        System.out.println();
        
        // Test Case 2: Another example
        System.out.println("=== Test Case 2: num = \"232\", target = 8 ===");
        String num2 = "232";
        int target2 = 8;
        List<String> result2 = addOperators(num2, target2);
        System.out.println("Results: " + result2);
        for (String expr : result2) {
            System.out.println(expr + " = " + evaluateExpression(expr));
        }
        System.out.println();
        
        // Test Case 3: Case with 0
        System.out.println("=== Test Case 3: num = \"3456237490\", target = 9191 ===");
        String num3 = "3456237490";
        int target3 = 9191;
        List<String> result3 = addOperators(num3, target3);
        System.out.println("Count: " + result3.size() + " expressions");
        if (result3.size() > 0) {
            System.out.println("First 3:");
            for (int i = 0; i < Math.min(3, result3.size()); i++) {
                String expr = result3.get(i);
                System.out.println(expr + " = " + evaluateExpression(expr));
            }
        }
        System.out.println();
        
        // Test Case 4: Single digit
        System.out.println("=== Test Case 4: num = \"9\", target = 9 ===");
        String num4 = "9";
        int target4 = 9;
        List<String> result4 = addOperators(num4, target4);
        System.out.println("Results: " + result4);
        System.out.println();
        
        // Test Case 5: Multiplication case
        System.out.println("=== Test Case 5: num = \"2483\", target = 2 ===");
        String num5 = "2483";
        int target5 = 2;
        List<String> result5 = addOperators(num5, target5);
        System.out.println("Results: " + result5);
        for (String expr : result5) {
            System.out.println(expr + " = " + evaluateExpression(expr));
        }
    }
}
