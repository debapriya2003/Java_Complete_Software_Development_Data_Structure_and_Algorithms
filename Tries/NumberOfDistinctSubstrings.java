package Tries;

/**
 * =========================================================
 * NUMBER OF DISTINCT SUBSTRINGS IN A STRING
 * =========================================================
 *
 * ---------------------------------------------------------
 * 1. PROBLEM STATEMENT
 * ---------------------------------------------------------
 * Given a string s, count the number of DISTINCT substrings
 * present in the string.
 *
 * Note:
 * - A substring is a CONTIGUOUS part of the string
 * - Empty string "" is also considered a substring
 *
 * ---------------------------------------------------------
 * 2. EXAMPLE
 * ---------------------------------------------------------
 * Input:  "abc"
 *
 * Substrings:
 * "", "a", "b", "c", "ab", "bc", "abc"
 *
 * Output: 7
 *
 * ---------------------------------------------------------
 * 3. BRUTE FORCE APPROACH (NOT OPTIMAL)
 * ---------------------------------------------------------
 * - Generate all substrings → O(n²)
 * - Store in a set → O(n² log n)
 *
 * This is inefficient for large strings.
 *
 * ---------------------------------------------------------
 * 4. OPTIMIZED APPROACH USING TRIE
 * ---------------------------------------------------------
 * KEY IDEA:
 * - Insert ALL suffixes of the string into a Trie
 * - Every UNIQUE path in the Trie corresponds to
 *   a UNIQUE substring
 *
 * Why suffixes?
 * Example: s = "aba"
 * Suffixes:
 *  - "aba"
 *  - "ba"
 *  - "a"
 *
 * Inserting all suffixes ensures all substrings
 * are covered as prefixes of suffixes.
 *
 * ---------------------------------------------------------
 * 5. HOW COUNTING WORKS
 * ---------------------------------------------------------
 * - Each time we create a NEW Trie node,
 *   it represents a NEW distinct substring.
 *
 * Total distinct substrings =
 *   number of nodes created in Trie + 1 (empty string)
 *
 * ---------------------------------------------------------
 * 6. TIME & SPACE COMPLEXITY
 * ---------------------------------------------------------
 * Let n = length of string
 *
 * Time Complexity  : O(n²)
 * Space Complexity : O(n²)
 *
 * (Still optimal compared to brute-force with hashing)
 *
 * ---------------------------------------------------------
 * 7. ONE-LINE SUMMARY (INTERVIEW GOLD)
 * ---------------------------------------------------------
 * Insert all suffixes into a Trie and count newly
 * created nodes to get number of distinct substrings.
 *
 * =========================================================
 * IMPLEMENTATION BELOW
 * =========================================================
 */

public class NumberOfDistinctSubstrings {

    /**
     * -----------------------------------------------------
     * TRIE NODE DEFINITION
     * -----------------------------------------------------
     */
    private static class Node {
        Node[] children = new Node[26];
    }

    /**
     * -----------------------------------------------------
     * MAIN FUNCTION
     * -----------------------------------------------------
     */
    public int countDistinctSubstrings(String s) {

        Node root = new Node();
        int count = 0; // counts distinct non-empty substrings

        /**
         * -------------------------------------------------
         * INSERT ALL SUFFIXES INTO TRIE
         * -------------------------------------------------
         */
        for (int i = 0; i < s.length(); i++) {

            Node cur = root;

            // Insert suffix starting at index i
            for (int j = i; j < s.length(); j++) {

                int idx = s.charAt(j) - 'a';

                // If node does not exist, it's a NEW substring
                if (cur.children[idx] == null) {
                    cur.children[idx] = new Node();
                    count++; // new distinct substring found
                }

                cur = cur.children[idx];
            }
        }

        /**
         * +1 for empty string ""
         */
        return count + 1;
    }

    /**
     * -----------------------------------------------------
     * DRIVER CODE / TEST
     * -----------------------------------------------------
     */
    public static void main(String[] args) {

        System.out.println("=== Number of Distinct Substrings ===\n");

        NumberOfDistinctSubstrings sol =
                new NumberOfDistinctSubstrings();

        String s1 = "abc";
        System.out.println("String: " + s1);
        System.out.println("Distinct Substrings: "
                + sol.countDistinctSubstrings(s1)); // 7

        System.out.println();

        String s2 = "aba";
        System.out.println("String: " + s2);
        System.out.println("Distinct Substrings: "
                + sol.countDistinctSubstrings(s2)); // 6

        /*
         * Substrings of "aba":
         * "", "a", "b", "ab", "ba", "aba"
         */

        System.out.println("\n" + "=".repeat(60));
        System.out.println("INTERVIEW TAKEAWAYS:");
        System.out.println("✔ Trie efficiently tracks unique substrings");
        System.out.println("✔ Each new Trie node = new substring");
        System.out.println("✔ Much better than brute force + set");
    }
}
