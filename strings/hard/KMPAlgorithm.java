package strings.hard;

/**
 * =========================================================
 * KMP ALGORITHM / LPS (PI) ARRAY
 * =========================================================
 *
 * ---------------------------------------------------------
 * 1. WHAT IS KMP?
 * ---------------------------------------------------------
 * KMP (Knuth–Morris–Pratt) is a LINEAR TIME string
 * pattern matching algorithm.
 *
 * Given:
 *  - TEXT string T
 *  - PATTERN string P
 *
 * Task:
 *  Find all occurrences of P in T.
 *
 * ---------------------------------------------------------
 * 2. WHY KMP IS NEEDED
 * ---------------------------------------------------------
 * Naive approach:
 *  - On mismatch, restart pattern comparison
 *  - Time: O(n * m)
 *
 * KMP optimization:
 *  - Avoid rechecking characters
 *  - Uses precomputed information from pattern itself
 *  - Time: O(n + m)
 *
 * ---------------------------------------------------------
 * 3. CORE IDEA OF KMP
 * ---------------------------------------------------------
 * When a mismatch occurs:
 * ❌ Do NOT move text pointer backward
 * ✔ Move pattern pointer using LPS array
 *
 * ---------------------------------------------------------
 * 4. WHAT IS LPS (PI) ARRAY?
 * ---------------------------------------------------------
 * LPS[i] = Longest Proper Prefix which is also a Suffix
 *          for the substring pattern[0..i]
 *
 * Proper prefix:
 *  - Prefix that is NOT equal to the whole string
 *
 * ---------------------------------------------------------
 * 5. EXAMPLE OF LPS ARRAY
 * ---------------------------------------------------------
 * Pattern = "ababaca"
 *
 * Index : 0 1 2 3 4 5 6
 * Char  : a b a b a c a
 * LPS   : 0 0 1 2 3 0 1
 *
 * Meaning:
 *  - At index 4 ("ababa"):
 *      longest prefix == suffix = "aba" → length 3
 *
 * ---------------------------------------------------------
 * 6. HOW LPS HELPS IN MATCHING
 * ---------------------------------------------------------
 * On mismatch at pattern index j:
 *   j = lps[j - 1]
 *
 * This skips unnecessary comparisons.
 *
 * ---------------------------------------------------------
 * 7. TIME & SPACE COMPLEXITY
 * ---------------------------------------------------------
 * LPS computation : O(m)
 * Pattern search  : O(n)
 *
 * Total Time      : O(n + m)
 * Space           : O(m)
 *
 * ---------------------------------------------------------
 * 8. ONE-LINE SUMMARY (INTERVIEW GOLD)
 * ---------------------------------------------------------
 * KMP avoids redundant comparisons by using prefix-suffix
 * information stored in the LPS array.
 *
 * =========================================================
 * IMPLEMENTATION BELOW
 * =========================================================
 */

public class KMPAlgorithm {

    /**
     * -----------------------------------------------------
     * BUILD LPS (PI) ARRAY
     * -----------------------------------------------------
     *
     * @param pattern input pattern
     * @return LPS array
     */
    public static int[] buildLPS(String pattern) {

        int m = pattern.length();
        int[] lps = new int[m];

        int len = 0; // length of previous longest prefix suffix
        int i = 1;

        /**
         * lps[0] is always 0
         */
        while (i < m) {

            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    // fallback using previous LPS
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
     * KMP SEARCH FUNCTION
     * -----------------------------------------------------
     *
     * @param text input text
     * @param pattern pattern to search
     */
    public static void KMPSearch(String text, String pattern) {

        int n = text.length();
        int m = pattern.length();

        int[] lps = buildLPS(pattern);

        int i = 0; // pointer for text
        int j = 0; // pointer for pattern

        /**
         * Traverse text
         */
        while (i < n) {

            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            }

            // Full match found
            if (j == m) {
                System.out.println("Pattern found at index: " + (i - j));
                j = lps[j - 1]; // continue searching
            }
            // Mismatch after j matches
            else if (i < n && text.charAt(i) != pattern.charAt(j)) {

                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
    }

    /**
     * -----------------------------------------------------
     * DRIVER CODE / TEST
     * -----------------------------------------------------
     */
    public static void main(String[] args) {

        System.out.println("=== KMP Algorithm (LPS / PI Array) ===\n");

        String text = "ababcabcabababd";
        String pattern = "ababd";

        System.out.println("Text    : " + text);
        System.out.println("Pattern : " + pattern);

        System.out.println("\nLPS Array:");
        int[] lps = buildLPS(pattern);
        System.out.println(java.util.Arrays.toString(lps));

        System.out.println("\nPattern Matching:");
        KMPSearch(text, pattern);

        System.out.println("\n" + "=".repeat(60));
        System.out.println("INTERVIEW TAKEAWAYS:");
        System.out.println("✔ LPS stores prefix-suffix information");
        System.out.println("✔ No backtracking in text");
        System.out.println("✔ Guaranteed linear time");
    }
}
