package bit_manipulation.intro;

/**
 * Check if a number is power of 2 or not
 * 
 * Problem: Given a number, determine if it's a power of 2.
 * 
 * Key Insight:
 * Powers of 2 have exactly one bit set in their binary representation:
 * - 1 = 0001 (2^0)
 * - 2 = 0010 (2^1)
 * - 4 = 0100 (2^2)
 * - 8 = 1000 (2^3)
 * - 16 = 10000 (2^4)
 * 
 * Approach:
 * For a number n:
 * - If n is a power of 2, then n has exactly one bit set
 * - (n & (n-1)) will be 0 if n is a power of 2
 * 
 * Why? Because n-1 will flip all the bits after the rightmost set bit.
 * Example:
 * - n = 8 = 1000
 * - n-1 = 7 = 0111
 * - n & (n-1) = 1000 & 0111 = 0000 = 0
 * 
 * Time Complexity: O(1)
 * Space Complexity: O(1)
 */

public class CheckIfPowerOfTwo {
    
    /**
     * Check if a number is power of 2
     * @param n the number
     * @return true if n is power of 2, false otherwise
     */
    public static boolean isPowerOfTwo(int n) {
        if (n <= 0) return false;
        return (n & (n - 1)) == 0;
    }
    
    /**
     * Alternative approach: Count set bits
     * A power of 2 has exactly one set bit
     */
    public static boolean isPowerOfTwoAlternative(int n) {
        if (n <= 0) return false;
        int count = 0;
        while (n > 0) {
            if ((n & 1) == 1) count++;
            n >>= 1;
        }
        return count == 1;
    }
    
    public static void main(String[] args) {
        System.out.println("=== Check if number is power of 2 ===\n");
        
        int[] testNumbers = {1, 2, 3, 4, 5, 8, 15, 16, 17, 32, 64, 100, 128, 256};
        
        for (int num : testNumbers) {
            System.out.println(num + " is " + (isPowerOfTwo(num) ? "power of 2" : "not power of 2") + 
                             " (binary: " + Integer.toBinaryString(num) + ")");
        }
        
        System.out.println("\n=== Edge Cases ===");
        System.out.println("0: " + isPowerOfTwo(0));
        System.out.println("-4: " + isPowerOfTwo(-4));
        System.out.println("1: " + isPowerOfTwo(1));
    }
}
