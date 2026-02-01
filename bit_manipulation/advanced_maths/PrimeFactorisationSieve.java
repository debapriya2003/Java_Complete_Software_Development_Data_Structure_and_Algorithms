package bit_manipulation.advanced_maths;

import java.util.*;

/**
 * Prime Factorisation using Sieve
 * 
 * Problem: For numbers up to n, find the prime factorization of each using Sieve.
 * 
 * Approach:
 * 1. Use a modified Sieve to store the smallest prime factor (SPF) for each number
 * 2. For each number i, if SPF[i] == i, then i is prime
 * 3. Use SPF to quickly factorize any number
 * 
 * Algorithm:
 * - Create array SPF (Smallest Prime Factor) of size n+1
 * - Initialize SPF[i] = i for all i
 * - For each prime p from 2 to sqrt(n):
 *   - For each multiple of p, set SPF if not already set
 * - To factorize a number n:
 *   - Keep dividing by SPF[n] until n becomes 1
 * 
 * Advantages:
 * - Can quickly factorize multiple numbers
 * - Single preprocessing O(n log log n)
 * - Each factorization is O(log n)
 * 
 * Example: SPF array for n = 20
 * SPF[1] = 1, SPF[2] = 2, SPF[3] = 3, SPF[4] = 2, SPF[5] = 5
 * SPF[6] = 2, SPF[7] = 7, SPF[8] = 2, SPF[9] = 3, SPF[10] = 2
 * 
 * Factorize 12:
 * 12 -> SPF[12] = 2 -> 12/2 = 6
 * 6  -> SPF[6] = 2 -> 6/2 = 3
 * 3  -> SPF[3] = 3 -> 3/3 = 1
 * Factors: 2, 2, 3
 * 
 * Time Complexity: O(n log log n) preprocessing + O(k log n) for k factorizations
 * Space Complexity: O(n)
 */

public class PrimeFactorisationSieve {
    
    /**
     * Create Smallest Prime Factor array using Sieve
     * @param n the upper limit
     * @return SPF array where SPF[i] is smallest prime factor of i
     */
    public static int[] createSPFArray(int n) {
        int[] spf = new int[n + 1];
        
        // Initialize SPF[i] = i
        for (int i = 0; i <= n; i++) {
            spf[i] = i;
        }
        
        // Sieve to compute smallest prime factor
        for (int i = 2; i * i <= n; i++) {
            // If spf[i] == i, then i is prime
            if (spf[i] == i) {
                // Mark all multiples of i
                for (int j = i * i; j <= n; j += i) {
                    // Set spf[j] = i if not already set
                    if (spf[j] == j) {
                        spf[j] = i;
                    }
                }
            }
        }
        
        return spf;
    }
    
    /**
     * Factorize a number using SPF array
     * @param n the number to factorize
     * @param spf the SPF array
     * @return list of prime factors
     */
    public static List<Integer> factorize(int n, int[] spf) {
        List<Integer> factors = new ArrayList<>();
        
        while (n > 1) {
            factors.add(spf[n]);
            n = n / spf[n];
        }
        
        return factors;
    }
    
    /**
     * Get prime factors with their count
     */
    public static Map<Integer, Integer> factorizeWithCount(int n, int[] spf) {
        Map<Integer, Integer> factors = new LinkedHashMap<>();
        
        while (n > 1) {
            int prime = spf[n];
            factors.put(prime, factors.getOrDefault(prime, 0) + 1);
            n = n / prime;
        }
        
        return factors;
    }
    
    /**
     * Get distinct prime factors
     */
    public static List<Integer> getDistinctPrimeFactors(int n, int[] spf) {
        List<Integer> primes = new ArrayList<>();
        
        while (n > 1) {
            int prime = spf[n];
            if (!primes.contains(prime)) {
                primes.add(prime);
            }
            n = n / prime;
        }
        
        return primes;
    }
    
    public static void main(String[] args) {
        System.out.println("=== Prime Factorisation using Sieve ===\n");
        
        int n = 100;
        int[] spf = createSPFArray(n);
        
        System.out.println("SPF Array for numbers up to " + n + ":");
        System.out.println("-".repeat(70));
        
        for (int i = 1; i <= 20; i++) {
            System.out.print(String.format("SPF[%2d]=%2d ", i, spf[i]));
            if (i % 5 == 0) System.out.println();
        }
        System.out.println("\n");
        
        System.out.println("=".repeat(70));
        System.out.println("PRIME FACTORIZATION RESULTS:");
        System.out.println("-".repeat(70));
        
        int[] testNumbers = {12, 60, 100, 24, 48, 77, 90, 15};
        
        System.out.println(String.format("%-10s | %-30s | %-20s", 
                                         "Number", "Prime Factors", "Factorization"));
        System.out.println("-".repeat(70));
        
        for (int num : testNumbers) {
            List<Integer> factors = factorize(num, spf);
            Map<Integer, Integer> countMap = factorizeWithCount(num, spf);
            
            StringBuilder factorStr = new StringBuilder();
            for (int f : factors) {
                if (factorStr.length() > 0) factorStr.append(" × ");
                factorStr.append(f);
            }
            
            StringBuilder countStr = new StringBuilder();
            countStr.append(num).append(" = ");
            for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
                if (countStr.length() > 4) countStr.append(" × ");
                if (entry.getValue() > 1) {
                    countStr.append(entry.getKey()).append("^").append(entry.getValue());
                } else {
                    countStr.append(entry.getKey());
                }
            }
            
            System.out.println(String.format("%-10d | %-30s | %-20s", 
                                           num, factorStr.toString(), countStr.toString()));
        }
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("DETAILED EXAMPLE: Factorize 60");
        System.out.println("-".repeat(70));
        
        int num = 60;
        System.out.println("Number: " + num);
        System.out.println("\nStep-by-step using SPF array:\n");
        
        int temp = num;
        int step = 1;
        while (temp > 1) {
            int prime = spf[temp];
            int quotient = temp / prime;
            System.out.println("Step " + step + ": SPF[" + temp + "] = " + prime + " -> " + temp + " ÷ " + prime + " = " + quotient);
            temp = quotient;
            step++;
        }
        
        Map<Integer, Integer> factorMap = factorizeWithCount(num, spf);
        System.out.print("\nResult: " + num + " = ");
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Integer, Integer> entry : factorMap.entrySet()) {
            if (result.length() > 0) result.append(" × ");
            if (entry.getValue() > 1) {
                result.append(entry.getKey()).append("^").append(entry.getValue());
            } else {
                result.append(entry.getKey());
            }
        }
        System.out.println(result.toString());
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("SPF ARRAY VISUALIZATION: n = 30");
        System.out.println("-".repeat(70));
        
        int[] spf30 = createSPFArray(30);
        System.out.println("Number | SPF | Is Prime?");
        System.out.println("-".repeat(30));
        
        for (int i = 2; i <= 30; i++) {
            boolean isPrime = (spf30[i] == i);
            System.out.println(String.format("%-6d | %-3d | %s", i, spf30[i], isPrime ? "Yes" : "No"));
        }
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("ADVANTAGES OF SIEVE FACTORIZATION:");
        System.out.println("-".repeat(70));
        System.out.println("1. Preprocessing: O(n log log n) - done once");
        System.out.println("2. Each factorization: O(log n) - very fast");
        System.out.println("3. Ideal for multiple factorizations");
        System.out.println("4. Better than individual factorization for large ranges");
        
        System.out.println("\nCompare with direct factorization:");
        System.out.println("Direct method: O(sqrt(n)) per number");
        System.out.println("Sieve method:  O(log n) per number (after preprocessing)");
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("PERFORMANCE COMPARISON:");
        System.out.println("-".repeat(70));
        
        int limit = 100000;
        long start, end;
        
        // Sieve preprocessing
        start = System.currentTimeMillis();
        int[] spfArray = createSPFArray(limit);
        end = System.currentTimeMillis();
        long preprocessTime = end - start;
        
        // Factorize 1000 random numbers
        int tests = 1000;
        start = System.currentTimeMillis();
        for (int i = 0; i < tests; i++) {
            int randomNum = (int)(Math.random() * limit) + 2;
            factorize(randomNum, spfArray);
        }
        end = System.currentTimeMillis();
        long sieveTime = end - start;
        
        System.out.println("For " + limit + " numbers:");
        System.out.println("Sieve preprocessing: " + preprocessTime + " ms");
        System.out.println("Factorize " + tests + " numbers: " + sieveTime + " ms");
        System.out.println("Average per factorization: " + (sieveTime / (double)tests) + " ms");
    }
}
