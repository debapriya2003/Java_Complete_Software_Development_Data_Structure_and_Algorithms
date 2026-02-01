package bit_manipulation.intro;

/**
 * Set/Unset the rightmost unset bit
 * 
 * Problem 1: Set the rightmost unset bit (change 0 to 1)
 * Problem 2: Unset the rightmost set bit (change 1 to 0) - already known as n & (n-1)
 * 
 * For Setting the rightmost unset bit:
 * Approach: Find the position of rightmost 0 bit and set it to 1
 * 
 * The trick: ~n gives the NOT of n, and ~n & (n+1) gives a mask with only the 
 * rightmost 0 bit of n set to 1. Then we OR it with n.
 * 
 * Simplified approach:
 * - The rightmost 0 bit will be at position where n has consecutive 1s from right
 * - n+1 will flip all these consecutive 1s to 0s and the rightmost 0 to 1
 * - So (n+1) gives us the number with rightmost unset bit set
 * - But if we want to set it in n, we use: n | (~n & (n+1))
 * 
 * Even simpler: n | (n+1) sets the rightmost unset bit if all bits to the right are set
 */

public class SetUnsetRightmostBit {
    
    /**
     * Set the rightmost unset bit (0 to 1)
     * Approach: n | (~n & (n+1))
     * @param n the number
     * @return number with rightmost unset bit set
     */
    public static int setRightmostUnsetBit(int n) {
        return n | (~n & (n + 1));
    }
    
    /**
     * Alternative approach: (n+1) | n
     * This works by leveraging the property that n+1 sets the rightmost unset bit
     */
    public static int setRightmostUnsetBitAlt(int n) {
        return ((n + 1) | n);
    }
    
    /**
     * Unset the rightmost set bit (1 to 0)
     * Using n & (n-1)
     * @param n the number
     * @return number with rightmost set bit unset
     */
    public static int unsetRightmostSetBit(int n) {
        return n & (n - 1);
    }
    
    /**
     * Toggle the rightmost set bit
     * @param n the number
     * @return number with rightmost set bit toggled
     */
    public static int toggleRightmostSetBit(int n) {
        return n ^ (n & (n - 1));
    }
    
    /**
     * Find position of rightmost unset bit
     * @param n the number
     * @return position (0-indexed) of rightmost unset bit, -1 if none
     */
    public static int findRightmostUnsetBitPosition(int n) {
        // ~n gives NOT of n
        // ~n & (n+1) gives only the rightmost 0 bit of n set to 1
        int mask = ~n & (n + 1);
        if (mask == 0) return -1;
        
        int position = 0;
        while ((mask & 1) == 0) {
            mask >>= 1;
            position++;
        }
        return position;
    }
    
    public static void main(String[] args) {
        System.out.println("=== Set/Unset Rightmost Bit ===\n");
        
        int[] testNumbers = {5, 10, 15, 8, 12};
        
        System.out.println("SET RIGHTMOST UNSET BIT:");
        for (int num : testNumbers) {
            int result = setRightmostUnsetBit(num);
            System.out.println(num + " (" + Integer.toBinaryString(num) + ") -> " + 
                             result + " (" + Integer.toBinaryString(result) + ")");
        }
        
        System.out.println("\n" + "=".repeat(40));
        System.out.println("UNSET RIGHTMOST SET BIT:");
        for (int num : testNumbers) {
            int result = unsetRightmostSetBit(num);
            System.out.println(num + " (" + Integer.toBinaryString(num) + ") -> " + 
                             result + " (" + Integer.toBinaryString(result) + ")");
        }
        
        System.out.println("\n" + "=".repeat(40));
        System.out.println("POSITION OF RIGHTMOST UNSET BIT:");
        for (int num : testNumbers) {
            int pos = findRightmostUnsetBitPosition(num);
            System.out.println(num + " (" + Integer.toBinaryString(num) + ") -> position: " + pos);
        }
        
        System.out.println("\n" + "=".repeat(40));
        System.out.println("DETAILED EXAMPLE:");
        int n = 10;  // 1010 in binary
        System.out.println("Original: " + n + " (binary: " + Integer.toBinaryString(n) + ")");
        System.out.println("Set rightmost unset: " + setRightmostUnsetBit(n) + 
                         " (binary: " + Integer.toBinaryString(setRightmostUnsetBit(n)) + ")");
        System.out.println("Unset rightmost set: " + unsetRightmostSetBit(n) + 
                         " (binary: " + Integer.toBinaryString(unsetRightmostSetBit(n)) + ")");
    }
}
