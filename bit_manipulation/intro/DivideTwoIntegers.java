package bit_manipulation.intro;

/**
 * Divide two integers without using multiplication, division, and mod operator
 * 
 * Problem: Divide dividend by divisor without using *, /, or % operators.
 * Return only the integer part of the result.
 * 
 * Constraints:
 * - Handle overflow (divisor = 0 or dividend = -2^31, divisor = -1)
 * - Return integer division result
 * 
 * Approach: Binary Exponentiation / Bit Shifting
 * - We can express division as repeated subtraction of powers of divisor
 * - divisor * quotient = dividend
 * - We find each bit of quotient from MSB to LSB
 * 
 * Example: 22 / 3
 * - 3 * 1 = 3, 3 * 2 = 6, 3 * 4 = 12, 3 * 8 = 24 (too large)
 * - So we use 3 * 8, subtract from 22: 22 - 24 = -2 (too much, go back)
 * - Use 3 * 4 = 12: 22 - 12 = 10
 * - Use 3 * 2 = 6: 10 - 6 = 4
 * - Use 3 * 1 = 3: 4 - 3 = 1 (remainder)
 * - Result: 4 + 2 + 1 = 7 (quotient bits at positions 2, 1, 0)
 * 
 * Time Complexity: O(log dividend) since we process O(log dividend) bits
 * Space Complexity: O(1)
 */

public class DivideTwoIntegers {
    
    /**
     * Divide two integers without using *, /, % operators
     * @param dividend the number to divide
     * @param divisor the number to divide by
     * @return quotient
     */
    public static int divide(int dividend, int divisor) {
        // Handle edge cases
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;  // Overflow case
        }
        
        // Determine sign of result
        boolean isNegative = (dividend < 0) ^ (divisor < 0);
        
        // Work with absolute values to avoid overflow issues
        long absDividend = Math.abs((long)dividend);
        long absDivisor = Math.abs((long)divisor);
        
        long quotient = 0;
        
        // Process each bit of the quotient from MSB to LSB
        for (int i = 31; i >= 0; i--) {
            // Check if (divisor * 2^i) <= dividend
            // divisor << i means divisor * 2^i
            if ((absDivisor << i) <= absDividend) {
                quotient += (1L << i);  // Add 2^i to quotient
                absDividend -= (absDivisor << i);  // Subtract divisor * 2^i
            }
        }
        
        // Apply the sign
        long result = isNegative ? -quotient : quotient;
        
        // Ensure result fits in 32-bit integer
        if (result > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        if (result < Integer.MIN_VALUE) return Integer.MIN_VALUE;
        
        return (int)result;
    }
    
    /**
     * Alternative iterative approach using repeated subtraction
     * Less efficient but more intuitive
     */
    public static int divideAlternative(int dividend, int divisor) {
        if (divisor == 0) return Integer.MAX_VALUE;
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        
        boolean isNegative = (dividend < 0) ^ (divisor < 0);
        long absDividend = Math.abs((long)dividend);
        long absDivisor = Math.abs((long)divisor);
        long quotient = 0;
        
        // Subtract powers of divisor
        while (absDividend >= absDivisor) {
            long tempDivisor = absDivisor;
            long count = 1;
            
            // Find the largest power of divisor that fits
            while (tempDivisor << 1 <= absDividend) {
                tempDivisor <<= 1;
                count <<= 1;
            }
            
            absDividend -= tempDivisor;
            quotient += count;
        }
        
        long result = isNegative ? -quotient : quotient;
        return (int)result;
    }
    
    public static void main(String[] args) {
        System.out.println("=== Divide Two Integers (Without *, /, %) ===\n");
        
        // Test cases
        int[][] testCases = {
            {22, 3},
            {7, -3},
            {-2147483648, -1},  // Overflow case
            {-2147483648, 1},
            {10, 1},
            {0, 1},
            {1, 1},
            {-1, 1},
            {2147483647, 2},
            {10, 10},
            {13, 2}
        };
        
        System.out.println(String.format("%-20s | %10s | %10s | %10s", 
                                         "Dividend / Divisor", "Expected", "Bit Div", "Alt Div"));
        System.out.println("-".repeat(60));
        
        for (int[] test : testCases) {
            int dividend = test[0];
            int divisor = test[1];
            int expected = (int)(dividend / divisor);
            int result1 = divide(dividend, divisor);
            int result2 = divideAlternative(dividend, divisor);
            
            String testCase = dividend + " / " + divisor;
            System.out.println(String.format("%-20s | %10d | %10d | %10d", 
                                           testCase, expected, result1, result2));
        }
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("DETAILED EXAMPLE: 22 / 3");
        System.out.println("-".repeat(60));
        
        int dividend = 22;
        int divisor = 3;
        
        System.out.println("Powers of " + divisor + ":");
        for (int i = 4; i >= 0; i--) {
            long power = (1L << i);
            long value = divisor * power;
            System.out.println("  3 * 2^" + i + " = 3 * " + power + " = " + value);
        }
        
        System.out.println("\nDivision process:");
        System.out.println("  Can we subtract 3*8=24 from 22? No");
        System.out.println("  Can we subtract 3*4=12 from 22? Yes, quotient += 4, remainder = 10");
        System.out.println("  Can we subtract 3*2=6 from 10? Yes, quotient += 2, remainder = 4");
        System.out.println("  Can we subtract 3*1=3 from 4? Yes, quotient += 1, remainder = 1");
        System.out.println("  Final quotient: 4 + 2 + 1 = 7");
        System.out.println("\nResult: " + divide(dividend, divisor));
    }
}
