package bit_manipulation.advanced_maths;

import java.util.*;

/**
 * Sieve of Eratosthenes
 * 
 * Problem: Find all prime numbers up to a given number n.
 * 
 * Algorithm:
 * 1. Create a boolean array "prime[0..n]" and initialize all entries as true
 * 2. Let p = 2 (the first prime)
 * 3. Mark all multiples of p (2p, 3p, 4p...) as not prime
 * 4. Find next unmarked number greater than p and repeat from step 3
 * 5. Continue until p^2 > n
 * 
 * Example: n = 30
 * Initially all marked as prime: [2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...]
 * Mark multiples of 2: [2, 3, X, 5, X, 7, X, 9, X, 11, ...]
 * Mark multiples of 3: [2, 3, X, 5, X, 7, X, X, X, 11, ...]
 * Mark multiples of 5: [2, 3, X, 5, X, 7, X, X, X, 11, ...]
 * Primes: [2, 3, 5, 7, 11, 13, 17, 19, 23, 29]
 * 
 * Time Complexity: O(n * log(log n))
 * Space Complexity: O(n)
 * 
 * This is one of the most efficient ways to find all primes up to n.
 */

public class SieveOfEratosthenes {
    
    /**
     * Find all primes up to n using Sieve of Eratosthenes
     * @param n the upper limit
     * @return list of all primes up to n
     */
    public static List<Integer> sieveOfEratosthenes(int n) {
        List<Integer> primes = new ArrayList<>();
        
        if (n < 2) return primes;
        
        // Create boolean array "prime[0..n]" and initialize all entries as true
        boolean[] prime = new boolean[n + 1];
        for (int i = 2; i <= n; i++) {
            prime[i] = true;
        }
        
        // Start with the smallest prime number, 2
        for (int p = 2; p * p <= n; p++) {
            // If prime[p] is not changed, then it's a prime
            if (prime[p]) {
                // Mark all multiples of p as not prime
                for (int i = p * p; i <= n; i += p) {
                    prime[i] = false;
                }
            }
        }
        
        // Collect all prime numbers
        for (int i = 2; i <= n; i++) {
            if (prime[i]) {
                primes.add(i);
            }
        }
        
        return primes;
    }
    
    /**
     * Count primes up to n
     */
    public static int countPrimes(int n) {
        if (n < 2) return 0;
        
        boolean[] prime = new boolean[n + 1];
        for (int i = 2; i <= n; i++) {
            prime[i] = true;
        }
        
        for (int p = 2; p * p <= n; p++) {
            if (prime[p]) {
                for (int i = p * p; i <= n; i += p) {
                    prime[i] = false;
                }
            }
        }
        
        int count = 0;
        for (int i = 2; i <= n; i++) {
            if (prime[i]) count++;
        }
        
        return count;
    }
    
    /**
     * Check if a number is prime (using sieve result)
     */
    public static boolean[] getPrimeArray(int n) {
        boolean[] prime = new boolean[n + 1];
        for (int i = 2; i <= n; i++) {
            prime[i] = true;
        }
        
        for (int p = 2; p * p <= n; p++) {
            if (prime[p]) {
                for (int i = p * p; i <= n; i += p) {
                    prime[i] = false;
                }
            }
        }
        
        return prime;
    }
    
    public static void main(String[] args) {
        System.out.println("=== Sieve of Eratosthenes ===\n");
        
        int[] testLimits = {10, 30, 50, 100, 1000};
        
        System.out.println(String.format("%-10s | %-10s | %-50s", 
                                         "Limit", "Count", "First 20 Primes"));
        System.out.println("-".repeat(75));
        
        for (int limit : testLimits) {
            List<Integer> primes = sieveOfEratosthenes(limit);
            
            StringBuilder primesStr = new StringBuilder();
            for (int i = 0; i < Math.min(20, primes.size()); i++) {
                if (i > 0) primesStr.append(", ");
                primesStr.append(primes.get(i));
            }
            if (primes.size() > 20) primesStr.append("...");
            
            System.out.println(String.format("%-10d | %-10d | %-50s", 
                                           limit, primes.size(), primesStr.toString()));
        }
        
        System.out.println("\n" + "=".repeat(75));
        System.out.println("DETAILED EXAMPLE: Sieve of Eratosthenes for n = 30");
        System.out.println("-".repeat(75));
        
        int n = 30;
        boolean[] prime = new boolean[n + 1];
        for (int i = 2; i <= n; i++) {
            prime[i] = true;
        }
        
        System.out.println("Initial: All numbers from 2 to " + n + " marked as prime\n");
        
        for (int p = 2; p * p <= n; p++) {
            if (prime[p]) {
                System.out.println("p = " + p + " (prime)");
                System.out.print("  Mark multiples: ");
                List<Integer> multiples = new ArrayList<>();
                for (int i = p * p; i <= n; i += p) {
                    prime[i] = false;
                    multiples.add(i);
                }
                for (int m : multiples) {
                    System.out.print(m + " ");
                }
                System.out.println("\n");
            }
        }
        
        System.out.println("Final primes up to " + n + ":");
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (prime[i]) {
                primes.add(i);
            }
        }
        System.out.println(primes);
        
        System.out.println("\n" + "=".repeat(75));
        System.out.println("VISUAL REPRESENTATION: n = 50");
        System.out.println("-".repeat(75));
        
        boolean[] visualPrime = getPrimeArray(50);
        System.out.println("P = Prime, . = Not Prime\n");
        
        for (int i = 2; i <= 50; i++) {
            System.out.print((visualPrime[i] ? "P" : "."));
            if (i % 10 == 1) System.out.println();
        }
        System.out.println();
        
        System.out.println("\n" + "=".repeat(75));
        System.out.println("COMPLEXITY ANALYSIS:");
        System.out.println("-".repeat(75));
        System.out.println("Time Complexity: O(n * log(log n))");
        System.out.println("  - For each prime p, mark multiples: n/p operations");
        System.out.println("  - Total: n/2 + n/3 + n/5 + ... = n * log(log n)");
        System.out.println("\nSpace Complexity: O(n)");
        System.out.println("  - Boolean array of size n+1");
        
        System.out.println("\nComparison with checking each number individually:");
        System.out.println("Individual: O(n * sqrt(n)) - check each number if prime");
        System.out.println("Sieve:      O(n * log(log n)) - Much faster!");
        
        System.out.println("\n" + "=".repeat(75));
        System.out.println("PERFORMANCE TEST:");
        System.out.println("-".repeat(75));
        
        int[] limits = {100, 1000, 10000, 100000, 1000000};
        for (int limit : limits) {
            long start = System.currentTimeMillis();
            int count = countPrimes(limit);
            long end = System.currentTimeMillis();
            System.out.println("Primes up to " + limit + ": " + count + " primes (took " + (end - start) + " ms)");
        }
    }
}
