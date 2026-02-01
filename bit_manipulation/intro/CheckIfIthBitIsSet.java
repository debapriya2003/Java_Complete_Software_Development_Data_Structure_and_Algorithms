package bit_manipulation.intro;

/**
 * Check if the i-th bit is set or not
 * 
 * Problem: Given a number, check if the i-th bit (0-indexed from right) is set (1) or not.
 * 
 * Approach:
 * 1. Right shift the number by i positions: (n >> i)
 * 2. Check if the rightmost bit is 1: ((n >> i) & 1)
 * 
 * Time Complexity: O(1)
 * Space Complexity: O(1)
 */

public class CheckIfIthBitIsSet {
    
    /**
     * Check if the i-th bit is set
     * @param n the number
     * @param i the bit position (0-indexed from right)
     * @return true if bit is set, false otherwise
     */
    public static boolean isBitSet(int n, int i) {
        return ((n >> i) & 1) == 1;
    }
    
    /**
     * Alternative approach using left shift
     * @param n the number
     * @param i the bit position (0-indexed from right)
     * @return true if bit is set, false otherwise
     */
    public static boolean isBitSetAlternative(int n, int i) {
        return (n & (1 << i)) != 0;
    }
    
    public static void main(String[] args) {
        System.out.println("=== Check if i-th bit is set ===\n");
        
        int num = 13;  // Binary: 1101
        System.out.println("Number: " + num + " (binary: " + Integer.toBinaryString(num) + ")\n");
        
        for (int i = 0; i < 4; i++) {
            System.out.println("Is bit " + i + " set? " + isBitSet(num, i));
        }
        
        System.out.println("\n=== Test Cases ===");
        System.out.println("5 (0101), bit 0: " + isBitSet(5, 0));  // true
        System.out.println("5 (0101), bit 1: " + isBitSet(5, 1));  // false
        System.out.println("8 (1000), bit 3: " + isBitSet(8, 3));  // true
        System.out.println("8 (1000), bit 0: " + isBitSet(8, 0));  // false
    }
}
