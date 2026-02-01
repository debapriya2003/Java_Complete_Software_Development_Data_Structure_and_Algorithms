package bit_manipulation.interview_problems;

/**
 * Count number of bits to be flipped to convert A to B
 * 
 * Problem: Given two integers A and B, find the number of bits that need to be 
 * flipped to convert A to B.
 * 
 * Approach:
 * 1. XOR A and B: A ^ B will give 1 where bits are different, 0 where same
 * 2. Count the number of 1s in the XOR result
 * 
 * Example: A = 1 (001), B = 4 (100)
 * A ^ B = 001 ^ 100 = 101 (5)
 * Number of set bits in 5 = 2
 * So, 2 bits need to be flipped
 * 
 * Time Complexity: O(log n) = O(32) for 32-bit integers
 * Space Complexity: O(1)
 */

public class CountBitsToFlip {
    
    /**
     * Count bits to flip to convert A to B
     * @param a first number
     * @param b second number
     * @return number of bits to flip
     */
    public static int countBitsToFlip(int a, int b) {
        // XOR to find differing bits
        int xor = a ^ b;
        
        // Count set bits in xor
        int count = 0;
        while (xor > 0) {
            count += (xor & 1);
            xor >>= 1;
        }
        return count;
    }
    
    /**
     * Optimized: Using Brian Kernighan's Algorithm
     * Time Complexity: O(k) where k is number of set bits
     */
    public static int countBitsToFlipOptimized(int a, int b) {
        int xor = a ^ b;
        int count = 0;
        while (xor > 0) {
            xor = xor & (xor - 1);  // Remove rightmost set bit
            count++;
        }
        return count;
    }
    
    /**
     * Using built-in method
     */
    public static int countBitsToFlipBuiltIn(int a, int b) {
        return Integer.bitCount(a ^ b);
    }
    
    public static void main(String[] args) {
        System.out.println("=== Count Bits to Flip to Convert A to B ===\n");
        
        int[][] testCases = {
            {1, 4},
            {5, 10},
            {0, 15},
            {7, 7},
            {1, 2},
            {10, 20},
            {15, 0}
        };
        
        System.out.println(String.format("%-15s | %-15s | %10s | %10s", 
                                         "A (binary)", "B (binary)", "XOR", "Bits Flip"));
        System.out.println("-".repeat(60));
        
        for (int[] test : testCases) {
            int a = test[0];
            int b = test[1];
            int xor = a ^ b;
            int result = countBitsToFlip(a, b);
            
            String aBinary = String.format("%8s", Integer.toBinaryString(a)).replace(' ', '0');
            String bBinary = String.format("%8s", Integer.toBinaryString(b)).replace(' ', '0');
            String xorBinary = String.format("%8s", Integer.toBinaryString(xor)).replace(' ', '0');
            
            System.out.println(String.format("%-15s | %-15s | %10s | %10d", 
                                           aBinary, bBinary, xorBinary, result));
        }
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("DETAILED EXAMPLE: Convert 1 to 4");
        System.out.println("-".repeat(60));
        
        int a = 1, b = 4;
        System.out.println("A = " + a + " (binary: " + Integer.toBinaryString(a) + ")");
        System.out.println("B = " + b + " (binary: " + Integer.toBinaryString(b) + ")");
        System.out.println("A ^ B = " + (a ^ b) + " (binary: " + Integer.toBinaryString(a ^ b) + ")");
        System.out.println("Bits with value 1 in XOR: " + Integer.bitCount(a ^ b));
        System.out.println("Answer: " + countBitsToFlip(a, b) + " bits need to be flipped");
    }
}
