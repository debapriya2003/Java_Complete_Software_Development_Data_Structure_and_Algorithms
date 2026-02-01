package Tries;

/**
 * =========================================================
 * BIT PREREQUISITES FOR TRIE PROBLEMS
 * =========================================================
 *
 * ---------------------------------------------------------
 * 1. WHY BITS ARE IMPORTANT FOR TRIE PROBLEMS
 * ---------------------------------------------------------
 * Not all Trie problems deal with characters ('a'–'z').
 *
 * Some important problems involve:
 * - Integers
 * - Binary representations
 *
 * For such problems, we use a **BINARY TRIE (0/1 Trie)**.
 *
 * Examples:
 * ✔ Maximum XOR of two numbers
 * ✔ Maximum XOR with an element from array
 * ✔ Count pairs with XOR less than K
 *
 * ---------------------------------------------------------
 * 2. BINARY REPRESENTATION BASICS
 * ---------------------------------------------------------
 * Any integer can be represented as a sequence of bits.
 *
 * Example:
 *   5  -> 00000101
 *   10 -> 00001010
 *
 * We usually consider:
 * - 32 bits for int
 * - 31 bits (0 to 30) for non-negative integers
 *
 * ---------------------------------------------------------
 * 3. IMPORTANT BIT OPERATIONS YOU MUST KNOW
 * ---------------------------------------------------------
 *
 * ---------------------------------------------------------
 * (A) CHECK i-th BIT
 * ---------------------------------------------------------
 * To check whether the i-th bit is set (1 or 0):
 *
 *   (num >> i) & 1
 *
 * Example:
 *   num = 5 (101)
 *   i = 0 -> 1
 *   i = 1 -> 0
 *   i = 2 -> 1
 *
 * ---------------------------------------------------------
 * (B) SET i-th BIT
 * ---------------------------------------------------------
 *   num | (1 << i)
 *
 * ---------------------------------------------------------
 * (C) CLEAR i-th BIT
 * ---------------------------------------------------------
 *   num & ~(1 << i)
 *
 * ---------------------------------------------------------
 * (D) TOGGLE i-th BIT
 * ---------------------------------------------------------
 *   num ^ (1 << i)
 *
 * ---------------------------------------------------------
 * 4. BIT ORDER MATTERS IN TRIE
 * ---------------------------------------------------------
 * In Binary Trie:
 * - We INSERT numbers from MOST SIGNIFICANT BIT (MSB)
 * - Typically from bit 31 → bit 0
 *
 * Reason:
 * - XOR maximization depends on higher bits first
 *
 * ---------------------------------------------------------
 * 5. XOR FUNDAMENTALS (VERY IMPORTANT)
 * ---------------------------------------------------------
 * XOR rules:
 * 0 ^ 0 = 0
 * 1 ^ 1 = 0
 * 0 ^ 1 = 1
 * 1 ^ 0 = 1
 *
 * KEY INSIGHT:
 * - XOR is MAXIMIZED when bits are DIFFERENT
 *
 * ---------------------------------------------------------
 * 6. HOW BINARY TRIE IS STRUCTURED
 * ---------------------------------------------------------
 * Each node has:
 * - child[0] -> represents bit 0
 * - child[1] -> represents bit 1
 *
 * Path from root to leaf = binary representation of number
 *
 * ---------------------------------------------------------
 * 7. INSERTING A NUMBER INTO BINARY TRIE
 * ---------------------------------------------------------
 * Steps:
 * 1. Start from root
 * 2. For bit = 31 to 0:
 *      - Extract bit using (num >> bit) & 1
 *      - Move to child[bit]
 *      - Create node if missing
 *
 * ---------------------------------------------------------
 * 8. QUERYING TRIE FOR MAXIMUM XOR
 * ---------------------------------------------------------
 * For each bit (MSB → LSB):
 * - If current bit = 0
 *     → prefer child[1]
 * - If current bit = 1
 *     → prefer child[0]
 *
 * Because DIFFERENT bits maximize XOR.
 *
 * ---------------------------------------------------------
 * 9. COMMON MISTAKES
 * ---------------------------------------------------------
 * ❌ Traversing from LSB to MSB
 * ❌ Forgetting to fix bit length
 * ❌ Mixing character Trie logic with Binary Trie
 *
 * ---------------------------------------------------------
 * 10. ONE-LINE SUMMARY (INTERVIEW GOLD)
 * ---------------------------------------------------------
 * Binary Trie stores numbers bit-by-bit (MSB → LSB) and
 * uses XOR properties to solve maximum/minimum XOR problems.
 *
 * =========================================================
 * DEMONSTRATION CODE (BIT OPERATIONS)
 * =========================================================
 */

public class BitPrerequisitesForTrie {

    /**
     * Check if i-th bit is set
     */
    public static int getBit(int num, int i) {
        return (num >> i) & 1;
    }

    /**
     * Set i-th bit
     */
    public static int setBit(int num, int i) {
        return num | (1 << i);
    }

    /**
     * Clear i-th bit
     */
    public static int clearBit(int num, int i) {
        return num & ~(1 << i);
    }

    /**
     * Toggle i-th bit
     */
    public static int toggleBit(int num, int i) {
        return num ^ (1 << i);
    }

    /**
     * Print binary representation (fixed 8 bits for demo)
     */
    public static void printBinary(int num) {
        for (int i = 7; i >= 0; i--) {
            System.out.print(getBit(num, i));
        }
        System.out.println();
    }

    public static void main(String[] args) {

        System.out.println("=== Bit Prerequisites for Trie Problems ===\n");

        int num = 5; // 00000101

        System.out.print("Binary of 5: ");
        printBinary(num);

        System.out.println("Bit at position 0: " + getBit(num, 0));
        System.out.println("Bit at position 1: " + getBit(num, 1));
        System.out.println("Bit at position 2: " + getBit(num, 2));

        System.out.println("\nSet bit 1:");
        num = setBit(num, 1);
        printBinary(num);

        System.out.println("Clear bit 2:");
        num = clearBit(num, 2);
        printBinary(num);

        System.out.println("Toggle bit 0:");
        num = toggleBit(num, 0);
        printBinary(num);

        System.out.println("\n" + "=".repeat(60));
        System.out.println("INTERVIEW TAKEAWAYS:");
        System.out.println("✔ Always traverse bits from MSB to LSB");
        System.out.println("✔ XOR prefers opposite bits");
        System.out.println("✔ Binary Trie = 2 children per node");
    }
}
