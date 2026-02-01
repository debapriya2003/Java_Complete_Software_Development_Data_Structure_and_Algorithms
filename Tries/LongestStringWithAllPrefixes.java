package Tries;

import java.util.*;

/**
 * =========================================================
 * LONGEST STRING WITH ALL PREFIXES
 * =========================================================
 *
 * ---------------------------------------------------------
 * 1. PROBLEM STATEMENT
 * ---------------------------------------------------------
 * You are given an array of strings.
 *
 * Task:
 * Find the LONGEST string such that **ALL of its prefixes**
 * are present in the array.
 *
 * If multiple answers exist:
 * - Return the LEXICOGRAPHICALLY SMALLEST one.
 *
 * If no such string exists:
 * - Return an empty string "".
 *
 * ---------------------------------------------------------
 * 2. WHAT DOES "ALL PREFIXES" MEAN?
 * ---------------------------------------------------------
 * For a word "apple":
 * Required prefixes:
 *  "a", "ap", "app", "appl", "apple"
 *
 * ALL must exist in the given list.
 *
 * ---------------------------------------------------------
 * 3. WHY TRIE IS IDEAL HERE?
 * ---------------------------------------------------------
 * - Trie naturally stores prefixes
 * - We can ensure every prefix ends at a valid word
 * - Efficient prefix traversal
 *
 * ---------------------------------------------------------
 * 4. CORE IDEA
 * ---------------------------------------------------------
 * 1. Insert all words into Trie
 * 2. For each word:
 *      - Traverse character by character
 *      - Ensure every prefix node has isEnd = true
 * 3. Track:
 *      - Maximum length
 *      - Lexicographically smallest (on tie)
 *
 * ---------------------------------------------------------
 * 5. TIME & SPACE COMPLEXITY
 * ---------------------------------------------------------
 * Let:
 *  N = number of words
 *  L = maximum length of word
 *
 * Time Complexity  : O(N × L)
 * Space Complexity : O(N × L)
 *
 * ---------------------------------------------------------
 * 6. ONE-LINE SUMMARY (INTERVIEW GOLD)
 * ---------------------------------------------------------
 * A string is valid only if every prefix of it is also a
 * complete word in the Trie.
 *
 * =========================================================
 * IMPLEMENTATION BELOW
 * =========================================================
 */

@SuppressWarnings("unused")
public class LongestStringWithAllPrefixes {

    /**
     * -----------------------------------------------------
     * TRIE NODE DEFINITION
     * -----------------------------------------------------
     */
    private static class Node {
        Node[] children = new Node[26];
        boolean isEnd = false;
    }

    private final Node root;

    public LongestStringWithAllPrefixes() {
        root = new Node();
    }

    /**
     * -----------------------------------------------------
     * INSERT WORD INTO TRIE
     * -----------------------------------------------------
     */
    private void insert(String word) {

        Node cur = root;

        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';

            if (cur.children[idx] == null) {
                cur.children[idx] = new Node();
            }
            cur = cur.children[idx];
        }
        cur.isEnd = true;
    }

    /**
     * -----------------------------------------------------
     * CHECK IF ALL PREFIXES OF WORD EXIST
     * -----------------------------------------------------
     */
    private boolean hasAllPrefixes(String word) {

        Node cur = root;

        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';

            cur = cur.children[idx];

            // If prefix path breaks OR prefix not a word
            if (cur == null || !cur.isEnd) {
                return false;
            }
        }
        return true;
    }

    /**
     * -----------------------------------------------------
     * MAIN FUNCTION
     * -----------------------------------------------------
     */
    public String longestWord(String[] words) {

        // Step 1: Insert all words into Trie
        for (String word : words) {
            insert(word);
        }

        String ans = "";

        // Step 2: Check each word
        for (String word : words) {

            if (hasAllPrefixes(word)) {

                // Choose longer word
                if (word.length() > ans.length()) {
                    ans = word;
                }
                // If same length, choose lexicographically smaller
                else if (word.length() == ans.length()
                        && word.compareTo(ans) < 0) {
                    ans = word;
                }
            }
        }
        return ans;
    }

    /**
     * -----------------------------------------------------
     * DRIVER CODE / TEST
     * -----------------------------------------------------
     */
    public static void main(String[] args) {

        System.out.println("=== Longest String With All Prefixes ===\n");

        /*
         * Example:
         * words = ["a", "ap", "app", "appl", "apple", "apply"]
         *
         * Valid:
         * "apple" → all prefixes exist
         * "apply" → prefix "appl" exists but "apply" ok only if "apply" prefix chain valid
         *
         * Output = "apple"
         */

        String[] words = {
                "a", "ap", "app", "appl", "apple", "apply"
        };

        LongestStringWithAllPrefixes sol =
                new LongestStringWithAllPrefixes();

        System.out.println("Answer: " + sol.longestWord(words));

        System.out.println("\n" + "=".repeat(60));
        System.out.println("INTERVIEW TAKEAWAYS:");
        System.out.println("✔ Trie enforces prefix condition naturally");
        System.out.println("✔ Check isEnd at every level");
        System.out.println("✔ Lexicographic tie-breaking is important");
    }
}
