package Tries;

/**
 * =========================================================
 * TRIE (PREFIX TREE) — COMPLETE IMPLEMENTATION
 * =========================================================
 *
 * ---------------------------------------------------------
 * 1. WHAT IS A TRIE?
 * ---------------------------------------------------------
 * A Trie (also called Prefix Tree) is a special tree-based
 * data structure used to efficiently store and search
 * strings, especially prefixes.
 *
 * Each node represents:
 * - One character
 * - A link to next characters
 *
 * ---------------------------------------------------------
 * 2. WHY USE A TRIE?
 * ---------------------------------------------------------
 * Tries are extremely useful when dealing with:
 * - Dictionary / word search
 * - Prefix search (autocomplete)
 * - Spell checking
 * - Phone directories
 *
 * ---------------------------------------------------------
 * 3. KEY OPERATIONS SUPPORTED
 * ---------------------------------------------------------
 * ✔ insert(word)      → insert a word into the trie
 * ✔ search(word)      → check if an EXACT word exists
 * ✔ startsWith(prefix)→ check if any word starts with prefix
 *
 * ---------------------------------------------------------
 * 4. TRIE NODE STRUCTURE
 * ---------------------------------------------------------
 * Each Trie node contains:
 * - children[26] → references to next characters
 *                 (for lowercase 'a' to 'z')
 * - isEnd        → true if a word ends at this node
 *
 * ---------------------------------------------------------
 * 5. CORE IDEA
 * ---------------------------------------------------------
 * - Characters are stored level-by-level
 * - Common prefixes share the same path
 *
 * Example:
 * Words: "apple", "app"
 *
 *        a
 *        |
 *        p
 *        |
 *        p (isEnd = true for "app")
 *        |
 *        l
 *        |
 *        e (isEnd = true for "apple")
 *
 * ---------------------------------------------------------
 * 6. TIME & SPACE COMPLEXITY
 * ---------------------------------------------------------
 * Let L = length of word
 *
 * insert()     → O(L)
 * search()     → O(L)
 * startsWith() → O(L)
 *
 * Space Complexity:
 * - Worst case O(N × 26) where N is total characters stored
 *
 * ---------------------------------------------------------
 * 7. ONE-LINE SUMMARY (INTERVIEW GOLD)
 * ---------------------------------------------------------
 * A Trie stores strings character-by-character, enabling
 * fast prefix-based searches.
 *
 * =========================================================
 * IMPLEMENTATION BELOW
 * =========================================================
 */

public class Trie {

    /**
     * -----------------------------------------------------
     * TRIE NODE DEFINITION
     * -----------------------------------------------------
     */
    private static class Node {

        // Children for 26 lowercase English letters
        Node[] children = new Node[26];

        // Marks end of a valid word
        boolean isEnd = false;
    }

    // Root of the Trie (empty node)
    private final Node root;

    /**
     * Constructor
     * Initializes the Trie with an empty root node
     */
    public Trie() {
        root = new Node();
    }

    /**
     * -----------------------------------------------------
     * INSERT A WORD INTO THE TRIE
     * -----------------------------------------------------
     *
     * Steps:
     * 1. Start from root
     * 2. For each character:
     *      - Move to corresponding child
     *      - Create node if it does not exist
     * 3. Mark last node as end-of-word
     */
    public void insert(String word) {

        Node cur = root;

        for (int i = 0; i < word.length(); i++) {

            char c = word.charAt(i);
            int idx = c - 'a';

            // Create node if absent
            if (cur.children[idx] == null) {
                cur.children[idx] = new Node();
            }

            cur = cur.children[idx];
        }

        // Mark end of word
        cur.isEnd = true;
    }

    /**
     * -----------------------------------------------------
     * SEARCH FOR A COMPLETE WORD
     * -----------------------------------------------------
     *
     * Returns true if:
     * - The word exists in trie AND
     * - isEnd is true at the last character
     */
    public boolean search(String word) {

        Node node = searchNode(word);
        return node != null && node.isEnd;
    }

    /**
     * -----------------------------------------------------
     * PREFIX SEARCH
     * -----------------------------------------------------
     *
     * Returns true if:
     * - The prefix path exists in the trie
     * - Word may or may not end here
     */
    public boolean startsWith(String prefix) {

        return searchNode(prefix) != null;
    }

    /**
     * -----------------------------------------------------
     * HELPER METHOD
     * -----------------------------------------------------
     *
     * Traverses trie for a given string and
     * returns the node reached at the end.
     *
     * Returns null if path breaks.
     */
    private Node searchNode(String s) {

        Node cur = root;

        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);
            int idx = c - 'a';

            // Guard against invalid characters
            if (idx < 0 || idx >= 26) return null;

            if (cur.children[idx] == null) return null;

            cur = cur.children[idx];
        }

        return cur;
    }

    /**
     * -----------------------------------------------------
     * DRIVER CODE / BASIC TEST
     * -----------------------------------------------------
     */
    public static void main(String[] args) {

        System.out.println("=== Trie (Prefix Tree) Demo ===\n");

        Trie trie = new Trie();

        trie.insert("apple");

        System.out.println(trie.search("apple"));   // true
        System.out.println(trie.search("app"));     // false
        System.out.println(trie.startsWith("app")); // true

        trie.insert("app");

        System.out.println(trie.search("app"));     // true

        System.out.println("\n" + "=".repeat(60));
        System.out.println("INTERVIEW TAKEAWAYS:");
        System.out.println("✔ Trie is best for prefix-based search");
        System.out.println("✔ Time complexity depends on word length, not count");
        System.out.println("✔ Widely used in string problems");
    }
}
