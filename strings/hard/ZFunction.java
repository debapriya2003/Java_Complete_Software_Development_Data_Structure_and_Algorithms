package strings.hard;

/**
 * =========================================================
 * Z-FUNCTION (Z-ALGORITHM)
 * =========================================================
 *
 * ---------------------------------------------------------
 * 1. WHAT IS Z-FUNCTION?
 * ---------------------------------------------------------
 * Given a string s of length n,
 * the Z-array (or Z-function) is an integer array Z[0..n-1]
 * where:
 *
 *   Z[i] = length of the LONGEST substring starting at i
 *          which is also a PREFIX of s
 *
 * By definition:
 *   Z[0] = 0 (sometimes defined as n, but usually 0)
 *
 * ---------------------------------------------------------
 * 2. SIMPLE EXAMPLE
 * ---------------------------------------------------------
 * s = "aabxaabxcaabxaabxay"
 *
 * Z-array:
 * i :  0  1  2  3  4  5  6  7  8 ...
 * Z :  0  1  0  0  4  1  0  0  0 ...
 *
 * Meaning:
 * - At index 4, substring "aabx..." matches prefix "aabx"
 *   for length 4
 *
 * ---------------------------------------------------------
 * 3. BRUTE FORCE IDEA (NOT OPTIMAL)
 * ---------------------------------------------------------
 * For each index i:
 *   Compare s[0...] with s[i...]
 *
 * Time Complexity:
 *   O(n²) in worst case
 *
 * ---------------------------------------------------------
 * 4. OPTIMIZED Z-ALGORITHM (CORE IDEA)
 * ---------------------------------------------------------
 * We maintain a WINDOW [L, R] such that:
 * - s[L..R] is a substring matching the prefix of s
 * - R is the farthest right boundary reached so far
 *
 * For each index i:
 *
 * Case 1: i > R
 *   - Start matching from scratch
 *
 * Case 2: i ≤ R
 *   - Use previously computed Z values
 *   - Let k = i - L
 *   - Z[i] = min(Z[k], R - i + 1)
 *   - Then try to extend beyond R
 *
 * ---------------------------------------------------------
 * 5. WHY Z-ALGORITHM IS FAST?
 * ---------------------------------------------------------
 * Each character is compared at most:
 * - Once when expanding R
 *
 * Hence total complexity is LINEAR.
 *
 * ---------------------------------------------------------
 * 6. TIME & SPACE COMPLEXITY
 * ---------------------------------------------------------
 * Time Complexity  : O(n)
 * Space Complexity : O(n)
 *
 * ---------------------------------------------------------
 * 7. APPLICATIONS OF Z-FUNCTION
 * ---------------------------------------------------------
 * ✔ Pattern matching
 * ✔ Finding occurrences of a pattern in a text
 * ✔ String periodicity
 * ✔ Longest prefix which is also suffix
 *
 * ---------------------------------------------------------
 * 8. PATTERN MATCHING USING Z-FUNCTION
 * ---------------------------------------------------------
 * To find pattern P in text T:
 *
 * Build string:
 *   S = P + "$" + T
 *
 * Compute Z-array of S
 *
 * If Z[i] == length(P),
 * then pattern occurs at index:
 *   i - length(P) - 1
 *
 * ---------------------------------------------------------
 * 9. ONE-LINE SUMMARY (INTERVIEW GOLD)
 * ---------------------------------------------------------
 * Z-function tells how much of the prefix matches starting
 * at each index, computed efficiently in linear time.
 *
 * =========================================================
 * IMPLEMENTATION BELOW
 * =========================================================
 */

public class ZFunction {

    /**
     * -----------------------------------------------------
     * COMPUTES Z-ARRAY FOR A STRING
     * -----------------------------------------------------
     *
     * @param s input string
     * @return Z-array
     */
    public static int[] computeZ(String s) {

        int n = s.length();
        int[] z = new int[n];

        int L = 0, R = 0;

        for (int i = 1; i < n; i++) {

            // Case 1: i is outside the current [L, R] window
            if (i > R) {
                L = R = i;

                while (R < n && s.charAt(R) == s.charAt(R - L)) {
                    R++;
                }

                z[i] = R - L;
                R--;

            } 
            // Case 2: i is inside the window
            else {
                int k = i - L;

                // If Z[k] does not stretch outside window
                if (z[k] < R - i + 1) {
                    z[i] = z[k];
                }
                // Else try to extend the match
                else {
                    L = i;

                    while (R < n && s.charAt(R) == s.charAt(R - L)) {
                        R++;
                    }

                    z[i] = R - L;
                    R--;
                }
            }
        }

        return z;
    }

    /**
     * -----------------------------------------------------
     * PATTERN MATCHING USING Z-FUNCTION
     * -----------------------------------------------------
     */
    public static void patternSearch(String text, String pattern) {

        String combined = pattern + "$" + text;
        int[] z = computeZ(combined);

        int pLen = pattern.length();

        for (int i = 0; i < z.length; i++) {
            if (z[i] == pLen) {
                System.out.println("Pattern found at index: "
                        + (i - pLen - 1));
            }
        }
    }

    /**
     * -----------------------------------------------------
     * DRIVER CODE / TEST
     * -----------------------------------------------------
     */
    public static void main(String[] args) {

        System.out.println("=== Z-Function (Z-Algorithm) ===\n");

        String s = "aabxaabxcaabxaabxay";
        int[] z = computeZ(s);

        System.out.println("String: " + s);
        System.out.println("Z-array:");
        System.out.println(java.util.Arrays.toString(z));

        System.out.println("\nPattern Matching Demo:");
        patternSearch("ababcabcabababd", "ababd");

        System.out.println("\n" + "=".repeat(60));
        System.out.println("INTERVIEW TAKEAWAYS:");
        System.out.println("✔ Linear time string matching");
        System.out.println("✔ Easier than KMP to implement");
        System.out.println("✔ Uses prefix comparisons smartly");
    }
}
