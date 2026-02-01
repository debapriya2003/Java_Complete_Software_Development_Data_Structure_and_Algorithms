package bit_manipulation.advanced_maths;

import java.util.*;

/**
 * All Divisors of a Number
 * 
 * Problem: Given a number n, find all its divisors.
 * 
 * Approach 1: Brute Force
 * - Check all numbers from 1 to n
 * - If i divides n, it's a divisor
 * - Time Complexity: O(n)
 * 
 * Approach 2: Optimized
 * - Check numbers from 1 to sqrt(n)
 * - If i divides n, both i and n/i are divisors
 * - Time Complexity: O(sqrt(n))
 * 
 * Example: n = 12
 * 1 divides 12, so both 1 and 12 are divisors
 * 2 divides 12, so both 2 and 6 are divisors
 * 3 divides 12, so both 3 and 4 are divisors
 * Divisors: 1, 2, 3, 4, 6, 12
 * 
 * Time Complexity: O(sqrt(n))
 * Space Complexity: O(d) where d is number of divisors
 */

public class AllDivisors {
    
    /**
     * Find all divisors of a number using optimized approach
     * @param n the number
     * @return list of divisors in sorted order
     */
    public static List<Integer> findDivisors(int n) {
        List<Integer> divisors = new ArrayList<>();
        
        // Check from 1 to sqrt(n)
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                divisors.add(i);
                if (i != n / i) {  // Avoid adding the same divisor twice for perfect squares
                    divisors.add(n / i);
                }
            }
        }
        
        Collections.sort(divisors);
        return divisors;
    }
    
    /**
     * Find divisors using brute force
     */
    public static List<Integer> findDivisorsBruteForce(int n) {
        List<Integer> divisors = new ArrayList<>();
        
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                divisors.add(i);
            }
        }
        
        return divisors;
    }
    
    /**
     * Count number of divisors
     */
    public static int countDivisors(int n) {
        int count = 0;
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                count++;
                if (i != n / i) {
                    count++;
                }
            }
        }
        return count;
    }
    
    /**
     * Sum of all divisors
     */
    public static long sumOfDivisors(int n) {
        long sum = 0;
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                sum += i;
                if (i != n / i) {
                    sum += n / i;
                }
            }
        }
        return sum;
    }
    
    public static void main(String[] args) {
        System.out.println("=== All Divisors of a Number ===\n");
        
        int[] testNumbers = {12, 28, 1, 17, 100, 36, 48};
        
        System.out.println(String.format("%-10s | %-30s | %-10s | %-15s", 
                                         "Number", "Divisors", "Count", "Sum"));
        System.out.println("-".repeat(70));
        
        for (int num : testNumbers) {
            List<Integer> divisors = findDivisors(num);
            int count = countDivisors(num);
            long sum = sumOfDivisors(num);
            
            StringBuilder divisorStr = new StringBuilder();
            for (int d : divisors) {
                if (divisorStr.length() > 0) divisorStr.append(", ");
                divisorStr.append(d);
            }
            
            System.out.println(String.format("%-10d | %-30s | %-10d | %-15d", 
                                           num, divisorStr.toString(), count, sum));
        }
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("DETAILED EXAMPLE: Divisors of 12");
        System.out.println("-".repeat(70));
        
        int n = 12;
        System.out.println("Number: " + n);
        System.out.println("\nOptimized Approach (Check up to sqrt(" + n + ")):");
        System.out.println("sqrt(12) ≈ 3.46, so we check from 1 to 3\n");
        
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                System.out.println("i = " + i + ":");
                System.out.println("  " + n + " % " + i + " = 0");
                System.out.println("  Divisors: " + i + " and " + (n / i));
                if (i == n / i) {
                    System.out.println("  (Same divisor, count once)");
                }
                System.out.println();
            }
        }
        
        List<Integer> divisors = findDivisors(n);
        System.out.println("All divisors of " + n + ": " + divisors);
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("PERFECT SQUARE EXAMPLE: Divisors of 36");
        System.out.println("-".repeat(70));
        
        n = 36;
        System.out.println("Number: " + n + " (perfect square)");
        divisors = findDivisors(n);
        System.out.println("Divisors: " + divisors);
        System.out.println("Count: " + divisors.size());
        System.out.println("Sum: " + sumOfDivisors(n));
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("COMPARISON: Brute Force vs Optimized");
        System.out.println("-".repeat(70));
        
        n = 100;
        long startTime, endTime;
        
        startTime = System.nanoTime();
        List<Integer> bruteForce = findDivisorsBruteForce(n);
        endTime = System.nanoTime();
        long bruteForceTime = endTime - startTime;
        
        startTime = System.nanoTime();
        List<Integer> optimized = findDivisors(n);
        endTime = System.nanoTime();
        long optimizedTime = endTime - startTime;
        
        System.out.println("Number: " + n);
        System.out.println("Brute Force: " + bruteForce + " (" + bruteForceTime + " ns)");
        System.out.println("Optimized:   " + optimized + " (" + optimizedTime + " ns)");
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("COMPLEXITY ANALYSIS:");
        System.out.println("-".repeat(70));
        System.out.println("Brute Force:  O(n)");
        System.out.println("Optimized:    O(sqrt(n))");
        System.out.println("\nFor n = 1,000,000:");
        System.out.println("  Brute Force: ~1,000,000 iterations");
        System.out.println("  Optimized:   ~1,000 iterations");
    }
}
