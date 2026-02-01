package Tries;

/**
 * =========================================================
 * MAXIMUM XOR OF TWO NUMBERS IN AN ARRAY
 * =========================================================
 *
 * ---------------------------------------------------------
 * 1. PROBLEM STATEMENT
 * ---------------------------------------------------------
 * Given an integer array nums[], find the MAXIMUM value of:
 *
 *      nums[i] XOR nums[j]
 *
 * where i != j.
 *
 * ---------------------------------------------------------
 * 2. WHY THIS IS A TRIE PROBLEM?
 * ---------------------------------------------------------
 * XOR is maximized when bits are DIFFERENT.
 *
 * To make the XOR large:
 * - We want opposite bits at higher positions (MSB first)
 *
 * A Binary Trie (0/1 Trie) allows us to:
 * - Store numbers bit-by-bit
 * - For each number, greedily search for the best opposite bits
 *
 * ---------------------------------------------------------
 * 3. KEY BIT INSIGHT (INTERVIEW GOLD)
 * ---------------------------------------------------------
 * For a bit position:
 * - If current bit = 0 → prefer 1
 * - If current bit = 1 → prefer 0
 *
 * Always process from:
 * MOST SIGNIFICANT BIT (31) → LEAST SIGNIFICANT BIT (0)
 *
 * ---------------------------------------------------------
 * 4. BINARY TRIE NODE STRUCTURE
 * ---------------------------------------------------------
 * Node {
 *   Node zero;  // represents bit 0
 *   Node one;   // represents bit 1
 * }
 *
 * ---------------------------------------------------------
 * 5. ALGORITHM STEPS
 * ---------------------------------------------------------
 * 1. Insert all numbers into Binary Trie
 * 2. For each number:
 *      - Traverse Trie to find best possible XOR partner
 *      - Try to take opposite bits greedily
 * 3. Track maximum XOR found
 *
 * ---------------------------------------------------------
 * 6. TIME & SPACE COMPLEXITY
 * ---------------------------------------------------------
 * Let N = number of elements
 *
 * Time Complexity  : O(N × 32)
 * Space Complexity : O(N × 32)
 *
 * ---------------------------------------------------------
 * 7. ONE-LINE SUMMARY (INTERVIEW GOLD)
 * ---------------------------------------------------------
 * Store numbers in a Binary Trie and greedily choose
 * opposite bits to maximize XOR.
 *
 * =========================================================
 * IMPLEMENTATION BELOW
 * =========================================================
 */

public class MaximumXORInArray {

    /**
     * -----------------------------------------------------
     * BINARY TRIE NODE
     * -----------------------------------------------------
     */
    private static class Node {
        Node zero; // child for bit 0
        Node one;  // child for bit 1
    }

    private final Node root;

    public MaximumXORInArray() {
        root = new Node();
    }

    /**
     * -----------------------------------------------------
     * INSERT NUMBER INTO BINARY TRIE
     * -----------------------------------------------------
     *
     * Inserts bits from MSB (31) to LSB (0)
     */
    private void insert(int num) {

        Node cur = root;

        for (int bit = 31; bit >= 0; bit--) {

            int currentBit = (num >> bit) & 1;

            if (currentBit == 0) {
                if (cur.zero == null) {
                    cur.zero = new Node();
                }
                cur = cur.zero;
            } else {
                if (cur.one == null) {
                    cur.one = new Node();
                }
                cur = cur.one;
            }
        }
    }

    /**
     * -----------------------------------------------------
     * FIND MAXIMUM XOR FOR A GIVEN NUMBER
     * -----------------------------------------------------
     *
     * Greedily tries to take opposite bits
     */
    private int getMaxXOR(int num) {

        Node cur = root;
        int maxXor = 0;

        for (int bit = 31; bit >= 0; bit--) {

            int currentBit = (num >> bit) & 1;

            // Prefer opposite bit
            if (currentBit == 0) {
                if (cur.one != null) {
                    maxXor |= (1 << bit);
                    cur = cur.one;
                } else {
                    cur = cur.zero;
                }
            } else {
                if (cur.zero != null) {
                    maxXor |= (1 << bit);
                    cur = cur.zero;
                } else {
                    cur = cur.one;
                }
            }
        }
        return maxXor;
    }

    /**
     * -----------------------------------------------------
     * MAIN FUNCTION
     * -----------------------------------------------------
     */
    public int findMaximumXOR(int[] nums) {

        // Step 1: Insert all numbers into Trie
        for (int num : nums) {
            insert(num);
        }

        int max = 0;

        // Step 2: Query maximum XOR for each number
        for (int num : nums) {
            max = Math.max(max, getMaxXOR(num));
        }

        return max;
    }

    /**
     * -----------------------------------------------------
     * DRIVER CODE / TEST
     * -----------------------------------------------------
     */
    public static void main(String[] args) {

        System.out.println("=== Maximum XOR of Two Numbers ===\n");

        /*
         * Example:
         * nums = [3, 10, 5, 25, 2, 8]
         *
         * Best pair:
         * 5  (00101)
         * 25 (11001)
         * XOR = 28 (11100)
         */

        int[] nums = {3, 10, 5, 25, 2, 8};

        MaximumXORInArray sol = new MaximumXORInArray();

        System.out.println("Maximum XOR: "
                + sol.findMaximumXOR(nums));

        System.out.println("\n" + "=".repeat(60));
        System.out.println("INTERVIEW TAKEAWAYS:");
        System.out.println("✔ Use Binary Trie for XOR problems");
        System.out.println("✔ Traverse from MSB to LSB");
        System.out.println("✔ Opposite bits maximize XOR");
    }
}
