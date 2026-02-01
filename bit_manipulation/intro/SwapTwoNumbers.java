package bit_manipulation.intro;

/**
 * Swap two numbers
 * 
 * Problem: Swap two numbers without using a temporary variable.
 * 
 * Approaches:
 * 1. Using Arithmetic: a = a + b; b = a - b; a = a - b;
 * 2. Using XOR: a = a ^ b; b = a ^ b; a = a ^ b;
 * 3. Using Bit Manipulation (XOR is cleaner)
 * 
 * Why XOR works for swapping:
 * - a ^ a = 0
 * - a ^ 0 = a
 * - XOR is commutative and associative
 * 
 * Example with a=5, b=3:
 * Step 1: a = 5 ^ 3 = 6
 * Step 2: b = 6 ^ 3 = 5
 * Step 3: a = 6 ^ 5 = 3
 * 
 * Time Complexity: O(1)
 * Space Complexity: O(1)
 */

public class SwapTwoNumbers {
    
    /**
     * Swap using arithmetic operations
     * @param a first number
     * @param b second number
     * @return array with swapped values
     */
    public static int[] swapArithmetic(int a, int b) {
        a = a + b;
        b = a - b;
        a = a - b;
        return new int[]{a, b};
    }
    
    /**
     * Swap using XOR bit manipulation
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     * @param a first number
     * @param b second number
     * @return array with swapped values
     */
    public static int[] swapXOR(int a, int b) {
        a = a ^ b;  // a now contains XOR of both
        b = a ^ b;  // b becomes original a
        a = a ^ b;  // a becomes original b
        return new int[]{a, b};
    }
    
    /**
     * Swap using multiplication and division
     * Note: Can cause overflow for large numbers
     */
    public static int[] swapMultiplication(int a, int b) {
        if (a != 0 && b != 0) {
            a = a * b;
            b = a / b;
            a = a / b;
        }
        return new int[]{a, b};
    }
    
    /**
     * Verify swap is correct
     */
    public static void verifySwap(int originalA, int originalB, int[] swapped) {
        System.out.println("Before: a=" + originalA + ", b=" + originalB);
        System.out.println("After:  a=" + swapped[0] + ", b=" + swapped[1]);
        if (swapped[0] == originalB && swapped[1] == originalA) {
            System.out.println("✓ Swap successful!");
        } else {
            System.out.println("✗ Swap failed!");
        }
    }
    
public static void main(String[] args) {

        System.out.println("=== Swap Two Numbers ===\n");

        int[][] testPairs = {
                {5, 3},
                {10, 20},
                {100, 200},
                {1, 1},
                {0, 5}
        };

        System.out.println("APPROACH 1: ARITHMETIC");
        System.out.println("-".repeat(40));
        for (int[] pair : testPairs) {
            verifySwap(pair[0], pair[1], swapArithmetic(pair[0], pair[1]));
            System.out.println();
        }

        System.out.println("APPROACH 2: XOR BIT MANIPULATION (BEST)");
        System.out.println("-".repeat(40));
        for (int[] pair : testPairs) {
            verifySwap(pair[0], pair[1], swapXOR(pair[0], pair[1]));
            System.out.println();
        }

        System.out.println("DETAILED XOR EXAMPLE (a=5, b=3)");
        System.out.println("-".repeat(40));

        int a = 5, b = 3;
        System.out.println("a = a ^ b → " + (a ^ b));
        a = a ^ b;

        System.out.println("b = a ^ b → " + (a ^ b));
        b = a ^ b;

        System.out.println("a = a ^ b → " + (a ^ b));
        a = a ^ b;

        System.out.println("\nFinal Result: a=" + a + ", b=" + b);
    }
}
