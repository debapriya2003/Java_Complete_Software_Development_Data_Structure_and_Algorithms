package recursion_patternwise.strong_hold;

public class PowXN {

    /*
    =====================================================================================
    PROBLEM: POW(X, N) - POWER FUNCTION
    -------------------------------------------------------------------------------------
    Implement pow(x, n), which calculates x raised to the power n.
    Handle negative exponents and integer overflow cases.
    
    Example:
    pow(2.00, 10) -> 1024.00
    pow(2.10, 3) -> 9.261
    pow(2.00, -2) -> 0.25
    pow(0.00, 0) -> 1.00 (by convention)
    
    Naive Approach: Multiply x by itself n times - O(n) time
    Optimal Approach: Binary Exponentiation (Fast Exponentiation)
    - Use the property: x^n = (x^2)^(n/2) when n is even
    - Use the property: x^n = x * x^(n-1) when n is odd
    - This reduces time complexity to O(log n)
    
    Time Complexity: O(log n)
    Space Complexity: O(log n) for recursive call stack
    =====================================================================================
    */
    
    /**
     * Calculate x raised to power n using binary exponentiation.
     * 
     * Algorithm:
     * 1. Handle edge cases (n = 0, x = 0 or 1)
     * 2. Handle negative exponents by computing 1 / pow(x, -n)
     * 3. Use binary exponentiation:
     *    - If n is even: x^n = (x^2)^(n/2)
     *    - If n is odd: x^n = x * x^(n-1)
     * 
     * @param x base
     * @param n exponent
     * @return x raised to power n
     */
    public static double myPow(double x, int n) {
        // Handle edge case where exponent is minimum integer
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        
        return powHelper(x, N);
    }
    
    /**
     * Recursive helper using binary exponentiation.
     * 
     * @param x base
     * @param n non-negative exponent
     * @return x^n
     */
    private static double powHelper(double x, long n) {
        // Base case
        if (n == 0) {
            return 1.0;
        }
        
        // If n is even, compute (x^2)^(n/2)
        if (n % 2 == 0) {
            double half = powHelper(x, n / 2);
            return half * half;
        }
        
        // If n is odd, compute x * x^(n-1)
        return x * powHelper(x, n - 1);
    }
    
    /**
     * Iterative approach using binary exponentiation.
     * More space efficient than recursive approach.
     * 
     * Algorithm:
     * 1. Convert n to positive (if negative)
     * 2. Initialize result = 1
     * 3. While n > 0:
     *    - If n is odd: result *= x
     *    - x = x * x (square base)
     *    - n = n / 2
     * 
     * @param x base
     * @param n exponent
     * @return x^n
     */
    public static double myPowIterative(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        
        double result = 1.0;
        double base = x;
        
        while (N > 0) {
            // If N is odd, multiply result by current base
            if (N % 2 == 1) {
                result *= base;
            }
            
            // Square the base and halve the exponent
            base *= base;
            N /= 2;
        }
        
        return result;
    }
    
    /**
     * Alternative recursive implementation using bit operations.
     * 
     * @param x base
     * @param n exponent
     * @return x^n
     */
    public static double myPowBit(double x, int n) {
        long N = n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        
        return powHelperBit(x, N);
    }
    
    private static double powHelperBit(double x, long n) {
        if (n == 0) {
            return 1.0;
        }
        
        double half = powHelperBit(x, n / 2);
        
        // If n is odd (check last bit)
        if ((n & 1) == 1) {
            return half * half * x;
        } else {
            return half * half;
        }
    }
    
    public static void main(String[] args) {
        // Test Case 1: Positive exponent
        double x1 = 2.0;
        int n1 = 10;
        System.out.println("Input: x = " + x1 + ", n = " + n1);
        System.out.println("Output (Recursive): " + myPow(x1, n1));
        System.out.println("Output (Iterative): " + myPowIterative(x1, n1));
        System.out.println();
        
        // Test Case 2: Decimal base
        double x2 = 2.1;
        int n2 = 3;
        System.out.println("Input: x = " + x2 + ", n = " + n2);
        System.out.println("Output (Recursive): " + myPow(x2, n2));
        System.out.println("Output (Iterative): " + myPowIterative(x2, n2));
        System.out.println();
        
        // Test Case 3: Negative exponent
        double x3 = 2.0;
        int n3 = -2;
        System.out.println("Input: x = " + x3 + ", n = " + n3);
        System.out.println("Output (Recursive): " + myPow(x3, n3));
        System.out.println("Output (Iterative): " + myPowIterative(x3, n3));
        System.out.println();
        
        // Test Case 4: Edge case - zero exponent
        double x4 = 2.0;
        int n4 = 0;
        System.out.println("Input: x = " + x4 + ", n = " + n4);
        System.out.println("Output (Recursive): " + myPow(x4, n4));
        System.out.println("Output (Iterative): " + myPowIterative(x4, n4));
        System.out.println();
        
        // Test Case 5: Base is 0
        double x5 = 0.0;
        int n5 = 3;
        System.out.println("Input: x = " + x5 + ", n = " + n5);
        System.out.println("Output (Recursive): " + myPow(x5, n5));
        System.out.println("Output (Iterative): " + myPowIterative(x5, n5));
        System.out.println();
        
        // Test Case 6: Base is 1
        double x6 = 1.0;
        int n6 = 100;
        System.out.println("Input: x = " + x6 + ", n = " + n6);
        System.out.println("Output (Recursive): " + myPow(x6, n6));
        System.out.println("Output (Iterative): " + myPowIterative(x6, n6));
        System.out.println();
        
        // Test Case 7: Large exponent
        double x7 = 2.0;
        int n7 = 31;
        System.out.println("Input: x = " + x7 + ", n = " + n7);
        System.out.println("Output (Recursive): " + myPow(x7, n7));
        System.out.println("Output (Iterative): " + myPowIterative(x7, n7));
        System.out.println();
        
        // Test Case 8: Minimum integer
        double x8 = 1.0;
        int n8 = Integer.MIN_VALUE;
        System.out.println("Input: x = " + x8 + ", n = " + n8);
        System.out.println("Output (Recursive): " + myPow(x8, n8));
        System.out.println("Output (Iterative): " + myPowIterative(x8, n8));
    }
}
