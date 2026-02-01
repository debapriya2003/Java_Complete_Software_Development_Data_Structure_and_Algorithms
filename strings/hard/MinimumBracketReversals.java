package strings.hard;

/**
 * =========================================================
 * MINIMUM NUMBER OF BRACKET REVERSALS TO MAKE EXPRESSION BALANCED
 * =========================================================
 *
 * ---------------------------------------------------------
 * 1. PROBLEM STATEMENT
 * ---------------------------------------------------------
 * You are given a string consisting ONLY of:
 *   '{' and '}'
 *
 * You can reverse any bracket:
 *   '{' ↔ '}'
 *
 * Task:
 * Find the MINIMUM number of reversals required to make
 * the expression BALANCED.
 *
 * If it is NOT possible to balance the expression,
 * return -1.
 *
 * ---------------------------------------------------------
 * 2. IMPORTANT OBSERVATIONS
 * ---------------------------------------------------------
 * ✔ Only curly brackets are involved
 * ✔ A balanced expression must have EVEN length
 * ✔ Odd-length strings can NEVER be balanced
 *
 * ---------------------------------------------------------
 * 3. WHAT IS A BALANCED EXPRESSION?
 * ---------------------------------------------------------
 * Examples:
 *   "{}{}"     → balanced
 *   "{{}}"     → balanced
 *   "}{"       → NOT balanced
 *
 * ---------------------------------------------------------
 * 4. CORE IDEA (STACK BASED)
 * ---------------------------------------------------------
 * Step 1:
 * - Use a stack to REMOVE already balanced pairs "{}"
 *
 * Step 2:
 * - After removal, stack contains ONLY UNBALANCED brackets
 *
 * Step 3:
 * - Count remaining:
 *     open  = number of '{'
 *     close = number of '}'
 *
 * Minimum reversals needed:
 *   (open + 1) / 2 + (close + 1) / 2
 *
 * ---------------------------------------------------------
 * 5. WHY THIS FORMULA WORKS?
 * ---------------------------------------------------------
 * Possible remaining patterns:
 *
 * "{{"  → needs 1 reversal
 * "}}"  → needs 1 reversal
 * "}{"  → needs 2 reversals
 *
 * Integer math:
 *   ceil(open / 2) + ceil(close / 2)
 *
 * ---------------------------------------------------------
 * 6. TIME & SPACE COMPLEXITY
 * ---------------------------------------------------------
 * Time Complexity  : O(n)
 * Space Complexity : O(n)
 *
 * ---------------------------------------------------------
 * 7. ONE-LINE SUMMARY (INTERVIEW GOLD)
 * ---------------------------------------------------------
 * Remove valid pairs using stack, then count minimum
 * reversals needed for remaining unmatched brackets.
 *
 * =========================================================
 * IMPLEMENTATION BELOW
 * =========================================================
 */

public class MinimumBracketReversals {

    /**
     * Returns minimum reversals required to balance expression
     *
     * @param s input string of '{' and '}'
     * @return minimum reversals or -1 if impossible
     */
    public static int minReversals(String s) {

        int n = s.length();

        /**
         * -----------------------------------------------------
         * ODD LENGTH CHECK
         * -----------------------------------------------------
         */
        if (n % 2 != 0) {
            return -1; // impossible
        }

        java.util.Stack<Character> stack = new java.util.Stack<>();

        /**
         * -----------------------------------------------------
         * REMOVE ALREADY BALANCED PARTS
         * -----------------------------------------------------
         */
        for (int i = 0; i < n; i++) {

            char ch = s.charAt(i);

            if (ch == '{') {
                stack.push(ch);
            } else {
                // current char is '}'
                if (!stack.isEmpty() && stack.peek() == '{') {
                    stack.pop(); // balanced pair "{}"
                } else {
                    stack.push(ch);
                }
            }
        }

        /**
         * -----------------------------------------------------
         * COUNT UNBALANCED BRACKETS
         * -----------------------------------------------------
         */
        int open = 0;
        int close = 0;

        while (!stack.isEmpty()) {
            if (stack.pop() == '{') {
                open++;
            } else {
                close++;
            }
        }

        /**
         * -----------------------------------------------------
         * CALCULATE MINIMUM REVERSALS
         * -----------------------------------------------------
         */
        int reversals =
                (open + 1) / 2
              + (close + 1) / 2;

        return reversals;
    }

    /**
     * -----------------------------------------------------
     * DRIVER CODE / TEST
     * -----------------------------------------------------
     */
    public static void main(String[] args) {

        System.out.println("=== Minimum Bracket Reversals ===\n");

        String s1 = "}{{}}{{{";
        System.out.println("Expression: " + s1);
        System.out.println("Minimum Reversals: "
                + minReversals(s1)); // 3

        System.out.println();

        String s2 = "{{{{";
        System.out.println("Expression: " + s2);
        System.out.println("Minimum Reversals: "
                + minReversals(s2)); // 2

        System.out.println();

        String s3 = "{{{{}";
        System.out.println("Expression: " + s3);
        System.out.println("Minimum Reversals: "
                + minReversals(s3)); // -1 (odd length)

        System.out.println("\n" + "=".repeat(60));
        System.out.println("INTERVIEW TAKEAWAYS:");
        System.out.println("✔ Odd length → immediately impossible");
        System.out.println("✔ Stack removes already balanced pairs");
        System.out.println("✔ Formula handles remaining imbalance");
    }
}
