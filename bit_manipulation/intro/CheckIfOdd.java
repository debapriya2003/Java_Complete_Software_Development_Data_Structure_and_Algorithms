package bit_manipulation.intro;

/**
 * Check if a number is odd or not
 * 
 * Problem: Given a number, determine if it's odd or even.
 * 
 * Approach:
 * - A number is odd if its rightmost bit is 1
 * - We can check this using: (n & 1) == 1
 * - This works because:
 *   - Odd numbers: ...xxx1 (rightmost bit is 1)
 *   - Even numbers: ...xxx0 (rightmost bit is 0)
 * 
 * Time Complexity: O(1)
 * Space Complexity: O(1)
 */

public class CheckIfOdd {
    
    /**
     * Check if a number is odd using bit manipulation
     * @param n the number
     * @return true if odd, false if even
     */
    public static boolean isOdd(int n) {
        return (n & 1) == 1;
    }
    
    /**
     * Alternative: Check if number is odd using modulo
     * (For comparison, bit manipulation is more efficient)
     */
    public static boolean isOddModulo(int n) {
        return n % 2 != 0;
    }
    
    public static void main(String[] args) {
        System.out.println("=== Check if number is odd ===\n");
        
        int[] testNumbers = {1, 2, 3, 4, 5, 10, 15, 20, 100, 101};
        
        for (int num : testNumbers) {
            System.out.println(num + " is " + (isOdd(num) ? "odd" : "even"));
        }
        
        System.out.println("\n=== Binary Representation ===");
        System.out.println("5 (binary: " + Integer.toBinaryString(5) + ") - " + (isOdd(5) ? "odd" : "even"));
        System.out.println("8 (binary: " + Integer.toBinaryString(8) + ") - " + (isOdd(8) ? "odd" : "even"));
        System.out.println("0 (binary: " + Integer.toBinaryString(0) + ") - " + (isOdd(0) ? "odd" : "even"));
        System.out.println("-1 (binary: " + Integer.toBinaryString(-1) + ") - " + (isOdd(-1) ? "odd" : "even"));
    }
}
