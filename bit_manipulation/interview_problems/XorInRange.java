package bit_manipulation.interview_problems;

/**
 * Find XOR of numbers from L to R
 * 
 * Problem: Given a range [L, R], find the XOR of all numbers in this range.
 * 
 * Approaches:
 * 1. Brute Force: XOR all numbers from L to R - O(R - L)
 * 2. Optimal: Use prefix XOR - O(1) after O(R) preprocessing
 * 
 * Key Insight:
 * XOR from L to R = XOR(0 to R) ^ XOR(0 to L-1)
 * 
 * Because if we compute XOR of all numbers from 1 to R and 1 to L-1,
 * then XORing them cancels out the common parts.
 * 
 * Pattern in XOR(1 to n):
 * n % 4 == 0 -> result = n
 * n % 4 == 1 -> result = 1
 * n % 4 == 2 -> result = n + 1
 * n % 4 == 3 -> result = 0
 * 
 * Proof:
 * 1 = 1
 * 1^2 = 3
 * 1^2^3 = 0
 * 1^2^3^4 = 4
 * 1^2^3^4^5 = 1
 * 1^2^3^4^5^6 = 7
 * 1^2^3^4^5^6^7 = 0
 * Pattern repeats!
 * 
 * Time Complexity: O(1) for optimal approach
 * Space Complexity: O(1)
 */

public class XorInRange {
    
    /**
     * Brute force: XOR all numbers from L to R
     * @param l start of range
     * @param r end of range
     * @return XOR of all numbers from l to r
     */
    public static int xorInRangeBruteForce(int l, int r) {
        int result = 0;
        for (int i = l; i <= r; i++) {
            result ^= i;
        }
        return result;
    }
    
    /**
     * Compute XOR from 1 to n using pattern
     * @param n the number
     * @return XOR of 1, 2, 3, ..., n
     */
    public static int xorUpToN(int n) {
        switch (n % 4) {
            case 0:
                return n;
            case 1:
                return 1;
            case 2:
                return n + 1;
            case 3:
                return 0;
        }
        return 0;
    }
    
    /**
     * Optimal: XOR from L to R using prefix XOR
     * @param l start of range
     * @param r end of range
     * @return XOR of all numbers from l to r
     */
    public static int xorInRangeOptimal(int l, int r) {
        // XOR(L to R) = XOR(1 to R) ^ XOR(1 to L-1)
        return xorUpToN(r) ^ xorUpToN(l - 1);
    }
    
    public static void main(String[] args) {
        System.out.println("=== XOR of Numbers in Range [L, R] ===\n");
        
        int[][] testCases = {
            {1, 10},
            {5, 10},
            {1, 5},
            {3, 7},
            {1, 1},
            {10, 15},
            {1, 20}
        };
        
        System.out.println(String.format("%-10s | %-15s | %-15s | %-10s | Match",
                                         "Range", "Brute Force", "Optimal", "Pattern"));
        System.out.println("-".repeat(70));
        
        for (int[] test : testCases) {
            int l = test[0];
            int r = test[1];
            
            int bruteFy = xorInRangeBruteForce(l, r);
            int optimal = xorInRangeOptimal(l, r);
            
            System.out.println(String.format("[%2d,%2d]    | %-15d | %-15d | %-10s | %s",
                                           l, r, bruteFy, optimal, 
                                           String.valueOf(optimal),
                                           (bruteFy == optimal) ? "✓" : "✗"));
        }
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("PATTERN ANALYSIS: XOR(1 to n)");
        System.out.println("-".repeat(70));
        System.out.println(String.format("%-5s | %-15s | %-10s | %10s", 
                                         "n", "XOR(1 to n)", "n % 4", "Pattern"));
        System.out.println("-".repeat(70));
        
        for (int n = 1; n <= 20; n++) {
            int xor = xorUpToN(n);
            int mod = n % 4;
            System.out.println(String.format("%-5d | %-15d | %-10d | %10s", 
                                           n, xor, mod, getPattern(mod)));
        }
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("DETAILED EXAMPLE: XOR(5 to 10)");
        System.out.println("-".repeat(70));
        
        int l = 5, r = 10;
        System.out.println("\nBrute Force Method:");
        int result = 0;
        System.out.print("5");
        result = 5;
        for (int i = 6; i <= 10; i++) {
            System.out.print(" ^ " + i);
            result ^= i;
        }
        System.out.println(" = " + result);
        System.out.println("Binary: " + Integer.toBinaryString(5) + " ^ " + 
                         Integer.toBinaryString(6) + " ^ " + Integer.toBinaryString(7) + " ^ " +
                         Integer.toBinaryString(8) + " ^ " + Integer.toBinaryString(9) + " ^ " +
                         Integer.toBinaryString(10) + " = " + Integer.toBinaryString(result));
        
        System.out.println("\nOptimal Method (Using Pattern):");
        System.out.println("XOR(1 to 10) = " + xorUpToN(10) + " (10 % 4 = " + (10 % 4) + ")");
        System.out.println("XOR(1 to 4)  = " + xorUpToN(4) + " (4 % 4 = " + (4 % 4) + ")");
        System.out.println("XOR(5 to 10) = XOR(1 to 10) ^ XOR(1 to 4) = " + xorUpToN(10) + " ^ " + xorUpToN(4) + " = " + xorInRangeOptimal(l, r));
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("TIME COMPLEXITY:");
        System.out.println("-".repeat(70));
        System.out.println("Brute Force: O(R - L)");
        System.out.println("Optimal:     O(1)");
    }
    
    private static String getPattern(int mod) {
        switch (mod) {
            case 0: return "return n";
            case 1: return "return 1";
            case 2: return "return n+1";
            case 3: return "return 0";
        }
        return "";
    }
}
