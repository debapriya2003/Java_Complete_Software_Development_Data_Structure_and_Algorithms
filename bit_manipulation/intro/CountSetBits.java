package bit_manipulation.intro;

/**
 * Count the number of set bits (Number of 1s in binary)
 * 
 * Problem: Given a number, count how many bits are set (1) in its binary representation.
 * Also known as counting "popcount" or "Hamming weight".
 * 
 * Approaches:
 * 1. Brute Force: Check each bit - O(log n) or O(32) for 32-bit integer
 * 2. Brian Kernighan's Algorithm: n & (n-1) repeatedly - O(number of set bits)
 * 3. Built-in method: Integer.bitCount() - O(1)
 * 
 * Brian Kernighan's Algorithm explanation:
 * - n & (n-1) removes the rightmost set bit
 * - We keep doing this until n becomes 0
 * - Count the number of operations
 */

public class CountSetBits {
    
    /**
     * Count set bits using bit by bit approach
     * Time Complexity: O(log n) = O(32) for 32-bit integer
     */
    public static int countSetBits1(int n) {
        int count = 0;
        while (n > 0) {
            count += (n & 1);  // Check if rightmost bit is set
            n >>= 1;            // Right shift by 1
        }
        return count;
    }
    
    /**
     * Count set bits using Brian Kernighan's Algorithm
     * Time Complexity: O(k) where k is number of set bits
     * Space Complexity: O(1)
     */
    public static int countSetBits2(int n) {
        int count = 0;
        while (n > 0) {
            n = n & (n - 1);  // Remove rightmost set bit
            count++;
        }
        return count;
    }
    
    /**
     * Using built-in method
     * Time Complexity: O(1)
     */
    public static int countSetBits3(int n) {
        return Integer.bitCount(n);
    }
    
    public static void main(String[] args) {
        System.out.println("=== Count Set Bits ===\n");
        
        int[] testNumbers = {5, 13, 15, 16, 255, 1024, 1000};
        
        for (int num : testNumbers) {
            System.out.println("Number: " + num + " (binary: " + Integer.toBinaryString(num) + ")");
            System.out.println("  Approach 1: " + countSetBits1(num) + " set bits");
            System.out.println("  Approach 2: " + countSetBits2(num) + " set bits");
            System.out.println("  Built-in:   " + countSetBits3(num) + " set bits");
            System.out.println();
        }
        
        System.out.println("=== Examples ===");
        System.out.println("5 = 0101 -> " + countSetBits1(5) + " set bits");
        System.out.println("13 = 1101 -> " + countSetBits1(13) + " set bits");
        System.out.println("0 = 0000 -> " + countSetBits1(0) + " set bits");
        System.out.println("7 = 0111 -> " + countSetBits1(7) + " set bits");
    }
}
