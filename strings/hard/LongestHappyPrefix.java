package strings.hard;

/**
 * =========================================================
 * LONGEST HAPPY PREFIX
 * =========================================================
 *
 * ---------------------------------------------------------
 * 1. PROBLEM STATEMENT
 * ---------------------------------------------------------
 * Given a string s, find the LONGEST PREFIX of s
 * which is ALSO a SUFFIX of s (excluding the whole string).
 *
 * Such a prefix is called a "HAPPY PREFIX".
 *
 * Return the longest happy prefix.
 * If no such prefix exists, return an empty string "".
 *
 * ---------------------------------------------------------
 * 2. IMPORTANT CONSTRAINT
 * ---------------------------------------------------------
 * ❗ The prefix and suffix must be PROPER
 *    (i.e., length < s.length()).
 *
 * ---------------------------------------------------------
 * 3. EXAMPLES
 * ---------------------------------------------------------
 * Input : "level"
 * Output: "l"
 *
 * Input : "ababab"
 * Output: "abab"
 *
 * Input : "leetcode"
 * Output: ""
 *
 * ---------------------------------------------------------
 * 4. BRUTE FORCE IDEA (NOT OPTIMAL)
 * ---------------------------------------------------------
 * - Check all prefixes
 * - Check if prefix == suffix
 *
 * Time Complexity: O(n²)
 *
 * ---------------------------------------------------------
 * 5. OPTIMIZED APPROACH USING KMP / LPS ARRAY
 * ---------------------------------------------------------
 * KEY OBSERVATION:
 * LPS (Longest Prefix Suffix) array already stores
 * EXACTLY what we need.
 *
 * lps[i] = length of the longest proper prefix
 *          which is also a suffix for s[0..i]
 *
 * Therefore:
 * - lps[n-1] gives the length of the LONGEST HAPPY PREFIX
 *
 * ---------------------------------------------------------
 * 6. WHY THIS WORKS?
 * ---------------------------------------------------------
 * KMP preprocesses the string to track prefix-suffix
 * relationships efficiently.
 *
 * No extra comparison required.
 *
 * ---------------------------------------------------------
 * 7. TIME & SPACE COMPLEXITY
 * ---------------------------------------------------------
 * Time Complexity  : O(n)
 * Space Complexity : O(n)
 *
 * ---------------------------------------------------------
 * 8. ONE-LINE SUMMARY (INTERVIEW GOLD)
 * ---------------------------------------------------------
 * The longest happy prefix is simply the last value
 * of the LPS array.
 *
 * =========================================================
 * IMPLEMENTATION BELOW
 * =========================================================
 */

public class LongestHappyPrefix {

    /**
     * -----------------------------------------------------
     * MAIN FUNCTION
     * -----------------------------------------------------
     *
     * @param s input string
     * @return longest happy prefix
     */
    public String longestPrefix(String s) {

        int n = s.length();
        if (n == 0) return "";

        int[] lps = buildLPS(s);

        int len = lps[n - 1]; // longest prefix which is also suffix

        return s.substring(0, len);
    }

    /**
     * -----------------------------------------------------
     * BUILD LPS ARRAY (STANDARD KMP)
     * -----------------------------------------------------
     *
     * @param s input string
     * @return lps array
     */
    private int[] buildLPS(String s) {

        int n = s.length();
        int[] lps = new int[n];

        int len = 0; // length of previous longest prefix suffix
        int i = 1;

        while (i < n) {

            if (s.charAt(i) == s.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }

    /**
     * -----------------------------------------------------
     * DRIVER CODE / TEST
     * -----------------------------------------------------
     */
    public static void main(String[] args) {

        System.out.println("=== Longest Happy Prefix ===\n");

        LongestHappyPrefix sol = new LongestHappyPrefix();

        String s1 = "ababab";
        System.out.println("Input : " + s1);
        System.out.println("Output: " + sol.longestPrefix(s1));
        System.out.println();

        String s2 = "level";
        System.out.println("Input : " + s2);
        System.out.println("Output: " + sol.longestPrefix(s2));
        System.out.println();

        String s3 = "leetcode";
        System.out.println("Input : " + s3);
        System.out.println("Output: " + sol.longestPrefix(s3));

        System.out.println("\n" + "=".repeat(60));
        System.out.println("INTERVIEW TAKEAWAYS:");
        System.out.println("✔ Happy prefix = prefix == suffix");
        System.out.println("✔ LPS array gives answer directly");
        System.out.println("✔ No extra string matching needed");
    }
}
