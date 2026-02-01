package Tries;

/**
 * =========================================================
 * IMPLEMENT TRIE – II (PREFIX TREE WITH COUNTS)
 * =========================================================
 *
 * ---------------------------------------------------------
 * 1. PROBLEM STATEMENT
 * ---------------------------------------------------------
 * Design a Trie data structure that supports the following
 * operations efficiently:
 *
 * ✔ insert(word)
 * ✔ countWordsEqualTo(word)
 * ✔ countWordsStartingWith(prefix)
 * ✔ erase(word)
 *
 * Unlike basic Trie, here we must:
 * - COUNT how many times a word was inserted
 * - SUPPORT deletion of words
 *
 * ---------------------------------------------------------
 * 2. KEY DIFFERENCE FROM BASIC TRIE
 * ---------------------------------------------------------
 * Basic Trie:
 * - Only stores presence (true / false)
 *
 * Trie-II:
 * - Stores FREQUENCY information
 *
 * Each node maintains:
 * - prefixCount → how many words pass through this node
 * - endCount    → how many words end at this node
 *
 * ---------------------------------------------------------
 * 3. TRIE NODE STRUCTURE
 * ---------------------------------------------------------
 * Node {
 *   Node[26] children
 *   int prefixCount
 *   int endCount
 * }
 *
 * ---------------------------------------------------------
 * 4. CORE IDEA
 * ---------------------------------------------------------
 * insert(word):
 *   - Increment prefixCount for every node
 *   - Increment endCount at last node
 *
 * countWordsEqualTo(word):
 *   - Return endCount at last node
 *
 * countWordsStartingWith(prefix):
 *   - Return prefixCount at prefix end node
 *
 * erase(word):
 *   - Decrement prefixCount on path
 *   - Decrement endCount at last node
 *
 * ---------------------------------------------------------
 * 5. TIME & SPACE COMPLEXITY
 * ---------------------------------------------------------
 * Let L = length of word
 *
 * insert / erase / count → O(L)
 * Space → O(N × alphabet)
 *
 * ---------------------------------------------------------
 * 6. ONE-LINE SUMMARY (INTERVIEW GOLD)
 * ---------------------------------------------------------
 * Trie-II extends Trie by maintaining word and prefix
 * frequencies, enabling counting and deletion.
 *
 * =========================================================
 * IMPLEMENTATION BELOW
 * =========================================================
 */

public class TrieII {

    /**
     * -----------------------------------------------------
     * TRIE NODE DEFINITION
     * -----------------------------------------------------
     */
    private static class Node {

        Node[] children = new Node[26];

        // Number of words ending at this node
        int endCount = 0;

        // Number of words passing through this node
        int prefixCount = 0;
    }

    private final Node root;

    /**
     * Constructor
     */
    public TrieII() {
        root = new Node();
    }

    /**
     * -----------------------------------------------------
     * INSERT WORD INTO TRIE
     * -----------------------------------------------------
     */
    public void insert(String word) {

        Node cur = root;

        for (int i = 0; i < word.length(); i++) {

            int idx = word.charAt(i) - 'a';

            if (cur.children[idx] == null) {
                cur.children[idx] = new Node();
            }

            cur = cur.children[idx];
            cur.prefixCount++;   // word passes through
        }

        cur.endCount++;          // word ends here
    }

    /**
     * -----------------------------------------------------
     * COUNT WORDS EQUAL TO GIVEN WORD
     * -----------------------------------------------------
     */
    public int countWordsEqualTo(String word) {

        Node node = traverse(word);
        return node == null ? 0 : node.endCount;
    }

    /**
     * -----------------------------------------------------
     * COUNT WORDS STARTING WITH PREFIX
     * -----------------------------------------------------
     */
    public int countWordsStartingWith(String prefix) {

        Node node = traverse(prefix);
        return node == null ? 0 : node.prefixCount;
    }

    /**
     * -----------------------------------------------------
     * ERASE A WORD FROM TRIE
     * -----------------------------------------------------
     */
    public void erase(String word) {

        Node cur = root;

        for (int i = 0; i < word.length(); i++) {

            int idx = word.charAt(i) - 'a';
            Node next = cur.children[idx];

            // If word does not exist, do nothing
            if (next == null) return;

            next.prefixCount--;   // reduce prefix count
            cur = next;
        }

        cur.endCount--;           // reduce word ending count
    }

    /**
     * -----------------------------------------------------
     * HELPER FUNCTION: TRAVERSE STRING
     * -----------------------------------------------------
     */
    private Node traverse(String s) {

        Node cur = root;

        for (int i = 0; i < s.length(); i++) {

            int idx = s.charAt(i) - 'a';

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

        System.out.println("=== Trie-II (Prefix Tree with Counts) ===\n");

        TrieII trie = new TrieII();

        trie.insert("apple");
        trie.insert("apple");
        trie.insert("app");

        System.out.println(trie.countWordsEqualTo("apple"));   // 2
        System.out.println(trie.countWordsStartingWith("app"));// 3

        trie.erase("apple");

        System.out.println(trie.countWordsEqualTo("apple"));   // 1
        System.out.println(trie.countWordsStartingWith("app"));// 2

        System.out.println("\n" + "=".repeat(60));
        System.out.println("INTERVIEW TAKEAWAYS:");
        System.out.println("✔ prefixCount tracks prefix frequency");
        System.out.println("✔ endCount tracks exact word frequency");
        System.out.println("✔ erase works safely without deleting nodes");
    }
}
