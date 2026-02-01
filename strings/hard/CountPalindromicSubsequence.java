package strings.hard;

/**
 * =========================================================
 * COUNT PALINDROMIC SUBSEQUENCES IN A STRING
 * =========================================================
 *
 * ---------------------------------------------------------
 * 1. PROBLEM STATEMENT
 * ---------------------------------------------------------
 * Given a string s, count the number of PALINDROMIC
 * SUBSEQUENCES present in the string.
 *
 * A subsequence:
 * - Maintains relative order
 * - Characters need NOT be contiguous
 *
 * A palindrome:
 * - Reads the same forward and backward
 *
 * ---------------------------------------------------------
 * 2. IMPORTANT CLARIFICATIONS
 * ---------------------------------------------------------
 * ✔ Single characters are palindromic subsequences
 * ✔ Subsequences are counted by INDEX, not by value
 *   (same-looking subsequences at different indices count)
 *
 * ---------------------------------------------------------
 * 3. EXAMPLE
 * ---------------------------------------------------------
 * Input  : "aaa"
 *
 * Palindromic subsequences:
 *  a(0), a(1), a(2)
 *  aa(0,1), aa(0,2), aa(1,2)
 *  aaa(0,1,2)
 *
 * Output : 7
 *
 * ---------------------------------------------------------
 * 4. WHY DP IS REQUIRED
 * ---------------------------------------------------------
 * Subsequence problems have overlapping subproblems:
 * - Choices: include / exclude characters
 * - Palindrome condition spans both ends
 *
 * DP helps avoid recomputation.
 *
 * ---------------------------------------------------------
 * 5. DP DEFINITION
 * ---------------------------------------------------------
 * dp[i][j] =
 *   Number of palindromic subsequences
 *   in substring s[i..j]
 *
 * Final Answer:
 *   dp[0][n-1]
 *
 * ---------------------------------------------------------
 * 6. DP TRANSITION
 * ---------------------------------------------------------
 *
 * Case 1: s[i] == s[j]
 * --------------------------------
 * dp[i][j] =
 *   dp[i+1][j]      // exclude s[i]
 * + dp[i][j-1]      // exclude s[j]
 * + 1               // new palindromes formed using s[i] & s[j]
 *
 * Why +1?
 * - The pair (s[i], s[j]) itself forms a new palindrome
 *
 * --------------------------------
 * Case 2: s[i] != s[j]
 * --------------------------------
 * dp[i][j] =
 *   dp[i+1][j]
 * + dp[i][j-1]
 * - dp[i+1][j-1]    // remove double-counted subsequences
 *
 * ---------------------------------------------------------
 * 7. BASE CASES
 * ---------------------------------------------------------
 * dp[i][i] = 1
 * (Every single character is a palindrome)
 *
 * ---------------------------------------------------------
 * 8. DP ORDER (IMPORTANT)
 * ---------------------------------------------------------
 * We compute dp for increasing substring length:
 * - length = 1 → n
 *
 * ---------------------------------------------------------
 * 9. TIME & SPACE COMPLEXITY
 * ---------------------------------------------------------
 * Time Complexity  : O(n²)
 * Space Complexity : O(n²)
 *
 * ---------------------------------------------------------
 * 10. ONE-LINE SUMMARY (INTERVIEW GOLD)
 * ---------------------------------------------------------
 * Use 2D DP where dp[i][j] stores palindromic subsequences
 * in substring s[i..j], combining smaller substrings.
 *
 * =========================================================
 * IMPLEMENTATION BELOW
 * =========================================================
 */

public class CountPalindromicSubsequence {

    /**
     * -----------------------------------------------------
     * MAIN FUNCTION
     * -----------------------------------------------------
     *
     * @param s input string
     * @return number of palindromic subsequences
     */
    public static long countPalindromicSubsequences(String s) {

        int n = s.length();
        long[][] dp = new long[n][n];

        /**
         * -------------------------------------------------
         * BASE CASE: SINGLE CHAR SUBSTRINGS
         * -------------------------------------------------
         */
        for (int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }

        /**
         * -------------------------------------------------
         * FILL DP FOR INCREASING SUBSTRING LENGTH
         * -------------------------------------------------
         */
        for (int len = 2; len <= n; len++) {

            for (int i = 0; i + len - 1 < n; i++) {

                int j = i + len - 1;

                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] =
                            dp[i + 1][j]
                          + dp[i][j - 1]
                          + 1;
                } else {
                    dp[i][j] =
                            dp[i + 1][j]
                          + dp[i][j - 1]
                          - dp[i + 1][j - 1];
                }
            }
        }

        return dp[0][n - 1];
    }

    /**
     * -----------------------------------------------------
     * DRIVER CODE / TEST
     * -----------------------------------------------------
     */
    public static void main(String[] args) {

        System.out.println("=== Count Palindromic Subsequences ===\n");

        String s1 = "aaa";
        System.out.println("String: " + s1);
        System.out.println("Count : "
                + countPalindromicSubsequences(s1)); // 7

        System.out.println();

        String s2 = "abcb";
        System.out.println("String: " + s2);
        System.out.println("Count : "
                + countPalindromicSubsequences(s2));

        System.out.println("\n" + "=".repeat(60));
        System.out.println("INTERVIEW TAKEAWAYS:");
        System.out.println("✔ Subsequence ≠ Substring");
        System.out.println("✔ DP on intervals (i, j)");
        System.out.println("✔ Inclusion–Exclusion principle");
    }
}
