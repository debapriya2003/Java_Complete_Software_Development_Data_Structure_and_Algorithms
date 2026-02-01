package Tries;

import java.util.*;

/**
 * =========================================================
 * MAXIMUM XOR WITH AN ELEMENT FROM ARRAY
 * =========================================================
 *
 * ---------------------------------------------------------
 * 1. PROBLEM STATEMENT
 * ---------------------------------------------------------
 * You are given:
 * - An integer array nums[]
 * - Queries of the form [xi, mi]
 *
 * For each query:
 *   Find the MAXIMUM value of (xi XOR num)
 *   such that:
 *      num ∈ nums AND num ≤ mi
 *
 * If no such num exists:
 *   return -1 for that query.
 *
 * ---------------------------------------------------------
 * 2. WHY THIS IS NOT A SIMPLE XOR PROBLEM?
 * ---------------------------------------------------------
 * In "Maximum XOR of two numbers":
 * - We could use ALL numbers freely
 *
 * Here:
 * ❗ Each query has a CONSTRAINT (num ≤ mi)
 *
 * So we cannot insert all numbers blindly.
 *
 * ---------------------------------------------------------
 * 3. KEY IDEA (OFFLINE QUERIES + TRIE)
 * ---------------------------------------------------------
 * ✔ Sort nums in ascending order
 * ✔ Sort queries by mi
 * ✔ Gradually insert numbers into Binary Trie
 *   ONLY when num ≤ current mi
 *
 * This ensures:
 * - Trie always contains only VALID numbers
 * - Each query is answered optimally
 *
 * ---------------------------------------------------------
 * 4. BINARY TRIE STRUCTURE
 * ---------------------------------------------------------
 * Each node has:
 * - zero → bit 0
 * - one  → bit 1
 *
 * Numbers are stored from:
 *   MSB (31) → LSB (0)
 *
 * ---------------------------------------------------------
 * 5. ALGORITHM STEPS
 * ---------------------------------------------------------
 * 1. Sort nums[]
 * 2. Convert queries to:
 *      [mi, xi, originalIndex]
 * 3. Sort queries by mi
 * 4. Maintain pointer idx over nums
 * 5. For each query:
 *      - Insert nums[idx] into Trie while nums[idx] ≤ mi
 *      - If Trie is empty → answer = -1
 *      - Else → compute maximum XOR with xi
 *
 * ---------------------------------------------------------
 * 6. TIME & SPACE COMPLEXITY
 * ---------------------------------------------------------
 * Let:
 *   N = size of nums
 *   Q = number of queries
 *
 * Time Complexity:
 *   O((N + Q) × 32)
 *
 * Space Complexity:
 *   O(N × 32)
 *
 * ---------------------------------------------------------
 * 7. ONE-LINE SUMMARY (INTERVIEW GOLD)
 * ---------------------------------------------------------
 * Sort queries by constraint and insert valid numbers
 * incrementally into a Binary Trie to answer XOR queries.
 *
 * =========================================================
 * IMPLEMENTATION BELOW
 * =========================================================
 */

public class MaximumXORWithElement {

    /**
     * -----------------------------------------------------
     * BINARY TRIE NODE
     * -----------------------------------------------------
     */
    private static class Node {
        Node zero;
        Node one;
    }

    private final Node root;

    public MaximumXORWithElement() {
        root = new Node();
    }

    /**
     * -----------------------------------------------------
     * INSERT NUMBER INTO BINARY TRIE
     * -----------------------------------------------------
     */
    private void insert(int num) {

        Node cur = root;

        for (int bit = 31; bit >= 0; bit--) {

            int b = (num >> bit) & 1;

            if (b == 0) {
                if (cur.zero == null) cur.zero = new Node();
                cur = cur.zero;
            } else {
                if (cur.one == null) cur.one = new Node();
                cur = cur.one;
            }
        }
    }

    /**
     * -----------------------------------------------------
     * GET MAX XOR WITH GIVEN NUMBER
     * -----------------------------------------------------
     */
    private int getMaxXor(int num) {

        Node cur = root;
        int ans = 0;

        for (int bit = 31; bit >= 0; bit--) {

            int b = (num >> bit) & 1;

            // Prefer opposite bit
            if (b == 0) {
                if (cur.one != null) {
                    ans |= (1 << bit);
                    cur = cur.one;
                } else {
                    cur = cur.zero;
                }
            } else {
                if (cur.zero != null) {
                    ans |= (1 << bit);
                    cur = cur.zero;
                } else {
                    cur = cur.one;
                }
            }
        }
        return ans;
    }

    /**
     * -----------------------------------------------------
     * MAIN FUNCTION
     * -----------------------------------------------------
     */
    public int[] maximizeXor(int[] nums, int[][] queries) {

        int n = nums.length;
        int q = queries.length;

        // Sort nums
        Arrays.sort(nums);

        /**
         * Each query transformed to:
         * [mi, xi, originalIndex]
         */
        int[][] offlineQueries = new int[q][3];
        for (int i = 0; i < q; i++) {
            offlineQueries[i][0] = queries[i][1]; // mi
            offlineQueries[i][1] = queries[i][0]; // xi
            offlineQueries[i][2] = i;              // index
        }

        // Sort queries by mi
        Arrays.sort(offlineQueries, Comparator.comparingInt(a -> a[0]));

        int[] result = new int[q];
        int idx = 0; // pointer on nums

        /**
         * -------------------------------------------------
         * PROCESS QUERIES IN ORDER OF mi
         * -------------------------------------------------
         */
        for (int[] query : offlineQueries) {

            int mi = query[0];
            int xi = query[1];
            int qi = query[2];

            // Insert all nums <= mi into Trie
            while (idx < n && nums[idx] <= mi) {
                insert(nums[idx]);
                idx++;
            }

            // If no valid numbers inserted
            if (idx == 0) {
                result[qi] = -1;
            } else {
                result[qi] = getMaxXor(xi);
            }
        }

        return result;
    }

    /**
     * -----------------------------------------------------
     * DRIVER CODE / TEST
     * -----------------------------------------------------
     */
    public static void main(String[] args) {

        System.out.println("=== Maximum XOR With an Element From Array ===\n");

        /*
         * nums = [0,1,2,3,4]
         * queries:
         *   [3,1] → max XOR of 3 with num ≤ 1 → 3^1 = 2
         *   [1,3] → max XOR of 1 with num ≤ 3 → 1^2 = 3
         *   [5,6] → max XOR of 5 with num ≤ 6 → 5^3 = 6
         */

        int[] nums = {0, 1, 2, 3, 4};
        int[][] queries = {
                {3, 1},
                {1, 3},
                {5, 6}
        };

        MaximumXORWithElement sol = new MaximumXORWithElement();
        int[] ans = sol.maximizeXor(nums, queries);

        System.out.println("Answers:");
        System.out.println(Arrays.toString(ans));

        System.out.println("\n" + "=".repeat(60));
        System.out.println("INTERVIEW TAKEAWAYS:");
        System.out.println("✔ Offline queries simplify constraints");
        System.out.println("✔ Binary Trie ensures optimal XOR");
        System.out.println("✔ Very frequently asked FAANG problem");
    }
}
