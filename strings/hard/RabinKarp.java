package strings.hard;

/**
 * =========================================================
 * RABIN–KARP ALGORITHM (STRING MATCHING)
 * =========================================================
 *
 * ---------------------------------------------------------
 * 1. PROBLEM STATEMENT
 * ---------------------------------------------------------
 * Given:
 *   - A text string TEXT
 *   - A pattern string PATTERN
 *
 * Task:
 *   Find all starting indices where PATTERN occurs in TEXT.
 *
 * ---------------------------------------------------------
 * 2. CORE IDEA
 * ---------------------------------------------------------
 * Rabin–Karp uses HASHING to compare substrings efficiently.
 *
 * Instead of comparing characters one by one for every
 * position (O(n*m)), we:
 *   ✔ Compute a hash for the pattern
 *   ✔ Compute rolling hashes for substrings of the text
 *   ✔ Compare hashes first (O(1))
 *   ✔ Verify characters only if hashes match
 *
 * ---------------------------------------------------------
 * 3. ROLLING HASH CONCEPT
 * ---------------------------------------------------------
 * Treat a string as a number in base p:
 *
 *   hash(s) = s[0]*p^0 + s[1]*p^1 + ... + s[k]*p^k (mod M)
 *
 * When sliding the window by 1:
 *   - Remove contribution of outgoing character
 *   - Add contribution of incoming character
 *
 * ---------------------------------------------------------
 * 4. WHY MODULO?
 * ---------------------------------------------------------
 * Hash values grow very fast.
 * Using modulo:
 *   ✔ Prevents overflow
 *   ✔ Keeps hash values manageable
 *
 * Common choices:
 *   p   = 31 or 53
 *   MOD = 1_000_000_007
 *
 * ---------------------------------------------------------
 * 5. HASH COLLISIONS
 * ---------------------------------------------------------
 * Two different strings can have the same hash.
 *
 * Solution:
 *   ✔ When hashes match, do a DIRECT string comparison
 *
 * This keeps correctness intact.
 *
 * ---------------------------------------------------------
 * 6. TIME & SPACE COMPLEXITY
 * ---------------------------------------------------------
 * Let:
 *   n = length of TEXT
 *   m = length of PATTERN
 *
 * Time Complexity:
 *   Average: O(n + m)
 *   Worst   : O(n * m) (rare, due to collisions)
 *
 * Space Complexity:
 *   O(1) extra space
 *
 * ---------------------------------------------------------
 * 7. ONE-LINE SUMMARY (INTERVIEW GOLD)
 * ---------------------------------------------------------
 * Rabin–Karp compares pattern hashes with rolling substring
 * hashes to find matches efficiently.
 *
 * =========================================================
 * IMPLEMENTATION BELOW
 * =========================================================
 */

public class RabinKarp {

    // Base and Modulus for rolling hash
    private static final int BASE = 31;
    private static final long MOD = 1_000_000_007;

    /**
     * -----------------------------------------------------
     * SEARCH PATTERN IN TEXT USING RABIN–KARP
     * -----------------------------------------------------
     *
     * @param text full text string
     * @param pattern pattern to search
     */
    public static void search(String text, String pattern) {

        int n = text.length();
        int m = pattern.length();

        if (m > n) return;

        long patternHash = 0;
        long textHash = 0;
        long power = 1; // BASE^(m-1)

        /**
         * -------------------------------------------------
         * PRECOMPUTE HASH FOR PATTERN AND FIRST WINDOW
         * -------------------------------------------------
         */
        for (int i = 0; i < m; i++) {
            patternHash =
                    (patternHash * BASE + (pattern.charAt(i) - 'a' + 1)) % MOD;

            textHash =
                    (textHash * BASE + (text.charAt(i) - 'a' + 1)) % MOD;

            if (i < m - 1) {
                power = (power * BASE) % MOD;
            }
        }

        /**
         * -------------------------------------------------
         * SLIDE THE WINDOW OVER TEXT
         * -------------------------------------------------
         */
        for (int i = 0; i <= n - m; i++) {

            // Step 1: Compare hashes
            if (patternHash == textHash) {

                // Step 2: Verify characters to avoid collision
                if (text.substring(i, i + m).equals(pattern)) {
                    System.out.println("Pattern found at index: " + i);
                }
            }

            // Step 3: Compute rolling hash for next window
            if (i < n - m) {

                // Remove leading character
                textHash =
                        (textHash - (text.charAt(i) - 'a' + 1) * power) % MOD;

                if (textHash < 0) textHash += MOD;

                // Add trailing character
                textHash =
                        (textHash * BASE
                         + (text.charAt(i + m) - 'a' + 1)) % MOD;
            }
        }
    }

    /**
     * -----------------------------------------------------
     * DRIVER CODE / TEST
     * -----------------------------------------------------
     */
    public static void main(String[] args) {

        System.out.println("=== Rabin–Karp Algorithm ===\n");

        String text = "ababcabcabababd";
        String pattern = "ababd";

        System.out.println("Text    : " + text);
        System.out.println("Pattern : " + pattern);
        System.out.println();

        search(text, pattern);

        System.out.println("\n" + "=".repeat(60));
        System.out.println("INTERVIEW TAKEAWAYS:");
        System.out.println("✔ Rolling hash avoids recomputation");
        System.out.println("✔ Always verify after hash match");
        System.out.println("✔ Faster than naive for large texts");
    }
}
