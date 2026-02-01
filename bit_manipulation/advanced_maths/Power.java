package bit_manipulation.advanced_maths;

/**
 * Power(n, x) - Calculate n raised to power x
 * 
 * Problem: Compute n^x efficiently, where x can be very large.
 * 
 * Approaches:
 * 1. Brute Force: Multiply n, x times - O(x)
 * 2. Binary Exponentiation (Fast Exponentiation): O(log x)
 * 3. Modular Exponentiation: O(log x) with modulo to prevent overflow
 * 
 * Binary Exponentiation Idea:
 * - Use binary representation of x
 * - Example: 2^13 = 2^8 * 2^4 * 2^1 (13 = 1101 in binary)
 * - Precompute powers: 2^1, 2^2, 2^4, 2^8, 2^16...
 * - Only use powers where bit is set in x
 * 
 * Recursive Formula:
 * - If x is even: n^x = (n^(x/2))^2
 * - If x is odd:  n^x = n * n^(x-1)
 * 
 * Iterative Algorithm:
 * 1. Initialize result = 1
 * 2. While x > 0:
 *    - If x is odd, multiply result by n
 *    - Square n
 *    - Divide x by 2
 * 
 * Example: 2^13
 * x=13(1101), n=2, result=1
 * x is odd: result = 1 * 2 = 2, n = 4, x = 6(110)
 * x is even: n = 16, x = 3(11)
 * x is odd: result = 2 * 16 = 32, n = 256, x = 1(1)
 * x is odd: result = 32 * 256 = 8192, n = 65536, x = 0
 * Result: 8192 = 2^13
 * 
 * Time Complexity: O(log x)
 * Space Complexity: O(1) for iterative, O(log x) for recursive (call stack)
 */

public class Power {
    
    /**
     * Brute force: Multiply n, x times
     * Time Complexity: O(x) - Not suitable for large x
     */
    public static long powerBruteForce(long n, long x) {
        if (x == 0) return 1;
        if (n == 0) return 0;
        
        long result = 1;
        for (long i = 0; i < x; i++) {
            result *= n;
        }
        return result;
    }
    
    /**
     * Binary Exponentiation (Iterative)
     * Time Complexity: O(log x)
     * @param n base
     * @param x exponent
     * @return n^x
     */
    public static long powerIterative(long n, long x) {
        if (x == 0) return 1;
        if (n == 0) return 0;
        
        long result = 1;
        long base = n;
        
        while (x > 0) {
            // If x is odd, multiply result by base
            if ((x & 1) == 1) {
                result *= base;
            }
            
            // Square the base
            base *= base;
            
            // Divide x by 2
            x >>= 1;
        }
        
        return result;
    }
    
    /**
     * Binary Exponentiation (Recursive)
     * Time Complexity: O(log x)
     */
    public static long powerRecursive(long n, long x) {
        if (x == 0) return 1;
        if (n == 0) return 0;
        
        if ((x & 1) == 0) {
            // x is even
            long half = powerRecursive(n, x / 2);
            return half * half;
        } else {
            // x is odd
            return n * powerRecursive(n, x - 1);
        }
    }
    
    /**
     * Modular Exponentiation: (n^x) % mod
     * Used to prevent overflow and required in many problems
     * Time Complexity: O(log x)
     */
    public static long powerMod(long n, long x, long mod) {
        if (x == 0) return 1;
        if (n == 0) return 0;
        
        long result = 1;
        long base = n % mod;
        
        while (x > 0) {
            if ((x & 1) == 1) {
                result = (result * base) % mod;
            }
            
            base = (base * base) % mod;
            x >>= 1;
        }
        
        return result;
    }
    
    /**
     * Handle negative exponents
     */
    public static double powerWithNegative(double n, long x) {
        if (x == 0) return 1.0;
        if (n == 0) return (x < 0) ? Double.POSITIVE_INFINITY : 0.0;
        
        long absX = Math.abs(x);
        double result = 1.0;
        double base = n;
        
        while (absX > 0) {
            if ((absX & 1) == 1) {
                result *= base;
            }
            
            base *= base;
            absX >>= 1;
        }
        
        return (x < 0) ? (1.0 / result) : result;
    }
    
    public static void main(String[] args) {
        System.out.println("=== Power Function: n^x ===\n");
        
        int[][] testCases = {{2, 10}, {3, 5}, {10, 3}, {2, 0}, {0, 5}, {5, 1}, {2, 20}};
        
        System.out.println(String.format("%-10s | %-15s | %-15s | %-15s", 
                                         "n^x", "Brute Force", "Iterative", "Recursive"));
        System.out.println("-".repeat(60));
        
        for (int[] test : testCases) {
            long n = test[0];
            long x = test[1];
            
            long brute = (x <= 30) ? powerBruteForce(n, x) : -1;  // Skip for large x
            long iterative = powerIterative(n, x);
            long recursive = powerRecursive(n, x);
            
            String bruteStr = (brute == -1) ? "N/A" : String.valueOf(brute);
            System.out.println(String.format("%-10s | %-15s | %-15d | %-15d", 
                                           n + "^" + x, bruteStr, iterative, recursive));
        }
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("MODULAR EXPONENTIATION TEST:");
        System.out.println("-".repeat(60));
        
        System.out.println(String.format("%-20s | %-15s | Mod 1000000007", "Base^Exponent", "Result"));
        System.out.println("-".repeat(60));
        
        long mod = 1000000007L;
        long[][] modTests = {{2, 100}, {10, 50}, {123, 456}, {999999, 1000000}};
        
        for (long[] test : modTests) {
            long n = test[0];
            long x = test[1];
            long result = powerMod(n, x, mod);
            System.out.println(String.format("%-20s | %-15d", n + "^" + x, result));
        }
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("NEGATIVE EXPONENT TEST:");
        System.out.println("-".repeat(60));
        
        double[][] negTests = {{2, -3}, {3, -2}, {10, -2}, {0.5, 3}};
        
        System.out.println(String.format("%-15s | %-15s", "Base^Exponent", "Result"));
        System.out.println("-".repeat(35));
        
        for (double[] test : negTests) {
            double n = test[0];
            long x = (long)test[1];
            double result = powerWithNegative(n, x);
            System.out.println(String.format("%-15s | %-15.6f", n + "^" + x, result));
        }
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("DETAILED EXAMPLE: 2^13 using Binary Exponentiation");
        System.out.println("-".repeat(60));
        
        long n = 2;
        long x = 13;
        System.out.println("n = " + n + ", x = " + x);
        System.out.println("Binary representation of " + x + ": " + Long.toBinaryString(x));
        System.out.println("\nStep-by-step execution:\n");
        
        long result = 1;
        long base = n;
        long xCopy = x;
        int step = 0;
        
        while (xCopy > 0) {
            System.out.println("Step " + step + ":");
            System.out.println("  x = " + xCopy + " (binary: " + Long.toBinaryString(xCopy) + ")");
            System.out.println("  base = " + base + ", result = " + result);
            
            if ((xCopy & 1) == 1) {
                result *= base;
                System.out.println("  x is odd: result = result * base = " + result);
            } else {
                System.out.println("  x is even: skip");
            }
            
            base *= base;
            System.out.println("  base = base * base = " + base);
            
            xCopy >>= 1;
            System.out.println("  x >>= 1 -> x = " + xCopy);
            System.out.println();
            step++;
        }
        
        System.out.println("Final result: " + result + " = 2^13");
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("COMPLEXITY COMPARISON:");
        System.out.println("-".repeat(60));
        System.out.println("Brute Force:            O(x) - Multiply x times");
        System.out.println("Binary Exponentiation:  O(log x) - Only log x multiplications");
        System.out.println("\nFor x = 1,000,000:");
        System.out.println("  Brute Force:  1,000,000 operations");
        System.out.println("  Binary Exp:   ~20 operations");
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("REAL WORLD APPLICATIONS:");
        System.out.println("-".repeat(60));
        System.out.println("1. Modular Exponentiation in RSA encryption");
        System.out.println("2. Computing large powers without overflow");
        System.out.println("3. Matrix exponentiation (for Fibonacci, etc.)");
        System.out.println("4. Number theory problems");
        System.out.println("5. Competitive programming");
    }
}
