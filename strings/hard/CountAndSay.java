package strings.hard;

/**
 * =========================================================
 * COUNT AND SAY
 * =========================================================
 *
 * ---------------------------------------------------------
 * 1. PROBLEM STATEMENT
 * ---------------------------------------------------------
 * The "Count and Say" sequence is a sequence of strings
 * defined recursively as follows:
 *
 * 1. countAndSay(1) = "1"
 *
 * For n > 1:
 *   countAndSay(n) is obtained by describing
 *   countAndSay(n-1)
 *
 * "Describing" means:
 * - Read off the digits of the previous term
 * - Count the number of digits in groups of the same digit
 *
 * ---------------------------------------------------------
 * 2. EXAMPLE SEQUENCE
 * ---------------------------------------------------------
 * n = 1 → "1"
 * n = 2 → "11"     (one 1)
 * n = 3 → "21"     (two 1s)
 * n = 4 → "1211"   (one 2, one 1)
 * n = 5 → "111221" (one 1, one 2, two 1s)
 *
 * ---------------------------------------------------------
 * 3. KEY OBSERVATIONS
 * ---------------------------------------------------------
 * ✔ Each term depends ONLY on the previous term
 * ✔ The sequence grows by grouping identical characters
 * ✔ This is NOT a numeric problem, but a STRING problem
 *
 * ---------------------------------------------------------
 * 4. CORE IDEA
 * ---------------------------------------------------------
 * To generate the next term:
 * 1. Traverse the previous string
 * 2. Count consecutive identical characters
 * 3. Append:
 *      count + character
 * 4. Repeat until string ends
 *
 * ---------------------------------------------------------
 * 5. APPROACH USED
 * ---------------------------------------------------------
 * ✔ Iterative construction
 * ✔ StringBuilder for efficiency
 *
 * ---------------------------------------------------------
 * 6. TIME & SPACE COMPLEXITY
 * ---------------------------------------------------------
 * Let L be the length of the final string.
 *
 * Time Complexity  : O(L)
 * Space Complexity : O(L)
 *
 * ---------------------------------------------------------
 * 7. ONE-LINE SUMMARY (INTERVIEW GOLD)
 * ---------------------------------------------------------
 * Each term is built by counting consecutive digits
 * of the previous term.
 *
 * =========================================================
 * IMPLEMENTATION BELOW
 * =========================================================
 */

public class CountAndSay {

    /**
     * Returns the nth term of the Count and Say sequence
     *
     * @param n position in sequence
     * @return nth count-and-say string
     */
    public String countAndSay(int n) {

        // Base case
        String result = "1";

        /**
         * -----------------------------------------------------
         * BUILD SEQUENCE FROM 2 TO n
         * -----------------------------------------------------
         */
        for (int i = 2; i <= n; i++) {

            StringBuilder sb = new StringBuilder();
            int count = 1;

            for (int j = 1; j < result.length(); j++) {

                // Same character → increase count
                if (result.charAt(j) == result.charAt(j - 1)) {
                    count++;
                } else {
                    // Different character → append previous group
                    sb.append(count);
                    sb.append(result.charAt(j - 1));
                    count = 1;
                }
            }

            // Append last group
            sb.append(count);
            sb.append(result.charAt(result.length() - 1));

            result = sb.toString();
        }

        return result;
    }

    /**
     * -----------------------------------------------------
     * DRIVER CODE / TEST
     * -----------------------------------------------------
     */
    public static void main(String[] args) {

        System.out.println("=== Count and Say ===\n");

        CountAndSay sol = new CountAndSay();

        int n = 5;
        System.out.println("n = " + n);
        System.out.println("Count and Say: " + sol.countAndSay(n));
        // Expected Output: "111221"

        System.out.println("\n" + "=".repeat(60));
        System.out.println("INTERVIEW TAKEAWAYS:");
        System.out.println("✔ String-based simulation problem");
        System.out.println("✔ Group consecutive characters");
        System.out.println("✔ Use StringBuilder for efficiency");
    }
}
