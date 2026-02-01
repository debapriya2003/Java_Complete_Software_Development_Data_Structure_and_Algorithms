package strings.hard;

/**
 * =========================================================
 * SHORTEST PALINDROME (USING KMP / LPS ARRAY)
 * =========================================================
 *
 * ---------------------------------------------------------
 * 1. PROBLEM STATEMENT
 * ---------------------------------------------------------
 * Given a string s, you are allowed to ADD characters
 * ONLY AT THE FRONT of the string.
 *
 * Task:
 *   Return the SHORTEST PALINDROME that can be formed.
 *
 * ---------------------------------------------------------
 * 2. EXAMPLES
 * ---------------------------------------------------------
 * Input  : "aacecaaa"
 * Output : "aaacecaaa"
 *
 * Input  : "abcd"
 * Output : "dcbabcd"
 *
 * ---------------------------------------------------------
 * 3. KEY OBSERVATION
 * ---------------------------------------------------------
 * We want the LONGEST PREFIX of the string which is
 * already a PALINDROME.
 *
 * Once we know that:
 * - The remaining suffix can be reversed
 * - And added in front to form the palindrome
 *
 * ---------------------------------------------------------
 * 4. BRUTE FORCE (NOT OPTIMAL)
 * ---------------------------------------------------------
 * - Check all prefixes
 * - Verify palindrome for each
 *
 * Time Complexity: O(n²)
 *
 * ---------------------------------------------------------
 * 5. OPTIMIZED APPROACH USING KMP (LPS ARRAY)
 * ---------------------------------------------------------
 * Trick:
 * Construct a new string:
 *
 *   temp = s + "#" + reverse(s)
 *
 * Then compute LPS array for temp.
 *
 * Meaning of LPS[last index]:
 * - Length of the LONGEST prefix of s
 *   which is also a suffix of reverse(s)
 *
 * This exactly gives:
 * - Longest palindromic prefix of s
 *
 * ---------------------------------------------------------
 * 6. WHY THIS WORKS?
 * ---------------------------------------------------------
 * If prefix of s == suffix of reverse(s),
 * then that prefix is a palindrome.
 *
 * ---------------------------------------------------------
 * 7. FINAL CONSTRUCTION
 * ---------------------------------------------------------
 * Let:
 *   len = length of longest palindromic prefix
 *
 * Remaining string:
 *   suffix = s.substring(len)
 *
 * Answer:
 *   reverse(suffix) + s
 *
 * ---------------------------------------------------------
 * 8. TIME & SPACE COMPLEXITY
 * ---------------------------------------------------------
 * Time Complexity  : O(n)
 * Space Complexity : O(n)
 *
 * ---------------------------------------------------------
 * 9. ONE-LINE SUMMARY (INTERVIEW GOLD)
 * ---------------------------------------------------------
 * Use KMP on (s + "#" + reverse(s)) to find the longest
 * palindromic prefix and build the shortest palindrome.
 *
 * =========================================================
 * IMPLEMENTATION BELOW
 * =========================================================
 */

public class ShortestPalindrome {

    /**
     * -----------------------------------------------------
     * MAIN FUNCTION
     * -----------------------------------------------------
     *
     * @param s input string
     * @return shortest palindrome
     */
    public String shortestPalindrome(String s) {

        if (s == null || s.length() == 0) return s;

        // Reverse the string
        String rev = new StringBuilder(s).reverse().toString();

        // Build combined string
        String combined = s + "#" + rev;

        // Compute LPS array
        int[] lps = buildLPS(combined);

        // Length of longest palindromic prefix
        int palPrefixLen = lps[combined.length() - 1];

        // Characters to add in front
        String suffix = s.substring(palPrefixLen);
        String add = new StringBuilder(suffix).reverse().toString();

        return add + s;
    }

    /**
     * -----------------------------------------------------
     * BUILD LPS ARRAY (STANDARD KMP)
     * -----------------------------------------------------
     */
    private int[] buildLPS(String str) {

        int n = str.length();
        int[] lps = new int[n];

        int len = 0; // length of previous longest prefix suffix
        int i = 1;

        while (i < n) {

            if (str.charAt(i) == str.charAt(len)) {
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

        System.out.println("=== Shortest Palindrome ===\n");

        ShortestPalindrome sol = new ShortestPalindrome();

        String s1 = "aacecaaa";
        System.out.println("Input : " + s1);
        System.out.println("Output: " + sol.shortestPalindrome(s1));
        System.out.println();

        String s2 = "abcd";
        System.out.println("Input : " + s2);
        System.out.println("Output: " + sol.shortestPalindrome(s2));

        System.out.println("\n" + "=".repeat(60));
        System.out.println("INTERVIEW TAKEAWAYS:");
        System.out.println("✔ Convert palindrome problem into prefix matching");
        System.out.println("✔ KMP gives longest palindromic prefix in O(n)");
        System.out.println("✔ Extremely popular interview problem");
    }
}
