package recursion_patternwise.strong_hold;

public class CountGoodNumbers {

    /*
    =====================================================================================
    PROBLEM: COUNT GOOD NUMBERS
    -------------------------------------------------------------------------------------
    A number is called "good" if:
    - All even positions have even digits (0, 2, 4, 6, 8)
    - All odd positions have prime digits (2, 3, 5, 7)
    
    Position counting starts from 1 (left to right).
    Example: 2040 is good (pos 1:2-even, pos 2:0-prime? No)
    
    Example:
    n = 1: "0", "2", "4", "6", "8" are good -> count = 5
    n = 3: "202", "222", "242", ... -> count = 20
    
    Approach:
    1. Count positions: odd positions = ceil(n/2), even positions = floor(n/2)
    2. For odd positions: 5 choices (2,3,5,7, and we exclude 0 for first digit)
    3. For even positions: 5 choices (0,2,4,6,8)
    4. Total = 5^odd_count * 4^even_count
    5. Use modular exponentiation to avoid overflow
    
    Time Complexity: O(log n) - recursive exponentiation
    Space Complexity: O(log n) - recursive call stack
    =====================================================================================
    */
    
    private static final long MOD = 1000000007;
    
    /**
     * Count good numbers with n digits.
     * 
     * Algorithm:
     * 1. Calculate number of odd positions (1, 3, 5, ...)
     * 2. Calculate number of even positions (2, 4, 6, ...)
     * 3. For odd positions: 5 choices (2, 3, 5, 7, and handle first digit)
     *    - If first digit is in odd position: 4 choices (2, 3, 5, 7 - can't be 0)
     *    - Otherwise: 5 choices (2, 3, 5, 7 for this position)
     * 4. For even positions: 5 choices (0, 2, 4, 6, 8)
     * 5. Result = (4 * 5^(odd-1)) * (5^even)
     * 6. Use modular exponentiation
     * 
     * @param n number of digits
     * @return count of good numbers with n digits modulo 10^9+7
     */
    public static long countGoodNumbers(long n) {
        long oddCount = (n + 1) / 2;      // positions: 1, 3, 5, ... (ceil(n/2))
        long evenCount = n / 2;            // positions: 2, 4, 6, ... (floor(n/2))
        
        // For odd positions: 4 choices for first position * 5^(oddCount-1)
        // For even positions: 5^evenCount choices
        long oddResult = modPow(4, oddCount, MOD);  // 4 for first odd position
        long evenResult = modPow(5, evenCount, MOD);
        
        return (oddResult * evenResult) % MOD;
    }
    
    /**
     * Recursive modular exponentiation using binary exponentiation.
     * Calculates (base^exp) % mod efficiently.
     * 
     * Algorithm:
     * 1. Base case: exp = 0, return 1
     * 2. If exp is even: (base^exp) % mod = ((base^2)^(exp/2)) % mod
     * 3. If exp is odd: (base^exp) % mod = (base * (base^(exp-1))) % mod
     * 
     * @param base base
     * @param exp exponent
     * @param mod modulo
     * @return (base^exp) % mod
     */
    private static long modPow(long base, long exp, long mod) {
        // Base case
        if (exp == 0) {
            return 1;
        }
        
        // If exp is even
        if (exp % 2 == 0) {
            long half = modPow(base, exp / 2, mod);
            return (half * half) % mod;
        }
        
        // If exp is odd
        return (base * modPow(base, exp - 1, mod)) % mod;
    }
    
    /**
     * Iterative modular exponentiation (alternative).
     * More space-efficient than recursive approach.
     * 
     * @param base base
     * @param exp exponent
     * @param mod modulo
     * @return (base^exp) % mod
     */
    private static long modPowIterative(long base, long exp, long mod) {
        long result = 1;
        base %= mod;
        
        while (exp > 0) {
            if (exp % 2 == 1) {
                result = (result * base) % mod;
            }
            base = (base * base) % mod;
            exp /= 2;
        }
        
        return result;
    }
    
    /**
     * Detailed explanation for different n values.
     */
    public static void explainGoodNumbers(long n) {
        long oddCount = (n + 1) / 2;
        long evenCount = n / 2;
        
        System.out.println("For n = " + n + ":");
        System.out.println("  Number of digits: " + n);
        System.out.println("  Odd positions (1, 3, 5, ...): " + oddCount);
        System.out.println("  Even positions (2, 4, 6, ...): " + evenCount);
        System.out.println("  Choices for first position (odd): 4 (2, 3, 5, 7)");
        System.out.println("  Choices for remaining odd positions: 5 (2, 3, 5, 7, and don't have)");
        System.out.println("  Choices for even positions: 5 (0, 2, 4, 6, 8)");
        System.out.println("  Total = 4 * 5^(" + (oddCount - 1) + ") * 5^(" + evenCount + ")");
        System.out.println("  Total = " + countGoodNumbers(n));
        System.out.println();
    }
    
    public static void main(String[] args) {
        // Test Case 1: Single digit
        System.out.println("=== Test Case 1: n = 1 ===");
        explainGoodNumbers(1);
        // Good numbers: 2, 3, 5, 7 (odd position, 4 choices)
        System.out.println("Expected: 4 (2, 3, 5, 7)");
        System.out.println();
        
        // Test Case 2: Two digits
        System.out.println("=== Test Case 2: n = 2 ===");
        explainGoodNumbers(2);
        // Position 1 (odd): 4 choices (2,3,5,7)
        // Position 2 (even): 5 choices (0,2,4,6,8)
        // Total = 4 * 5 = 20
        System.out.println("Expected: 20");
        System.out.println();
        
        // Test Case 3: Three digits
        System.out.println("=== Test Case 3: n = 3 ===");
        explainGoodNumbers(3);
        // Position 1 (odd): 4 choices (2,3,5,7)
        // Position 2 (even): 5 choices (0,2,4,6,8)
        // Position 3 (odd): 4 choices (2,3,5,7)
        // Total = 4 * 5 * 4 = 80
        System.out.println("Expected: 80");
        System.out.println();
        
        // Test Case 4: Four digits
        System.out.println("=== Test Case 4: n = 4 ===");
        explainGoodNumbers(4);
        // Position 1 (odd): 4 choices
        // Position 2 (even): 5 choices
        // Position 3 (odd): 4 choices
        // Position 4 (even): 5 choices
        // Total = 4 * 5 * 4 * 5 = 400
        System.out.println("Expected: 400");
        System.out.println();
        
        // Test Case 5: Large n
        System.out.println("=== Test Case 5: n = 10 ===");
        long result5 = countGoodNumbers(10);
        System.out.println("Result: " + result5);
        System.out.println();
        
        // Test Case 6: Very large n
        System.out.println("=== Test Case 6: n = 1000000000 ===");
        long result6 = countGoodNumbers(1000000000L);
        System.out.println("Result: " + result6);
        System.out.println();
        
        // Verification: Compare recursive and iterative approaches
        System.out.println("=== Verification: Recursive vs Iterative ===");
        for (int n = 1; n <= 5; n++) {
            long oddCount = (n + 1) / 2;
            long evenCount = n / 2;
            long resultRecursive = (modPow(4, oddCount, MOD) * modPow(5, evenCount, MOD)) % MOD;
            long resultIterative = (modPowIterative(4, oddCount, MOD) * modPowIterative(5, evenCount, MOD)) % MOD;
            System.out.println("n = " + n + ": Recursive = " + resultRecursive + ", Iterative = " + resultIterative);
        }
    }
}
