package bit_manipulation.advanced_maths;

/**
 * Print Prime Factors of a Number
 * 
 * Problem: Given a number n, print all its prime factors.
 * 
 * Approach 1: Brute Force
 * - Iterate from 2 to n
 * - If i divides n, it's a factor
 * - Divide n by i repeatedly to remove all occurrences
 * - Time Complexity: O(n)
 * 
 * Approach 2: Optimized
 * - Check divisibility up to sqrt(n)
 * - First check for 2
 * - Then check odd numbers from 3 to sqrt(n)
 * - Any remaining n > 1 is a prime factor
 * - Time Complexity: O(sqrt(n))
 * 
 * Example: n = 60
 * 60 = 2 * 30
 * 30 = 2 * 15
 * 15 = 3 * 5
 * 5 = 5 * 1
 * Prime factors: 2, 2, 3, 5
 * Or: 2^2 * 3 * 5
 * 
 * Time Complexity: O(sqrt(n) * log n) - log n for repeated divisions
 * Space Complexity: O(1) - excluding output
 */

public class PrimeFactors {
    
    /**
     * Print all prime factors of a number
     * @param n the number
     */
    public static void printPrimeFactors(int n) {
        System.out.print("Prime factors of " + n + ": ");
        
        // Check for factor 2
        while (n % 2 == 0) {
            System.out.print("2 ");
            n = n / 2;
        }
        
        // Check for odd factors from 3 onwards
        for (int i = 3; i * i <= n; i += 2) {
            while (n % i == 0) {
                System.out.print(i + " ");
                n = n / i;
            }
        }
        
        // If n is still greater than 1, it's a prime factor
        if (n > 1) {
            System.out.print(n + " ");
        }
        System.out.println();
    }
    
    /**
     * Return prime factors as a list
     */
    public static java.util.List<Integer> getPrimeFactors(int n) {
        java.util.List<Integer> factors = new java.util.ArrayList<>();
        
        // Check for 2
        while (n % 2 == 0) {
            factors.add(2);
            n = n / 2;
        }
        
        // Check for odd factors
        for (int i = 3; i * i <= n; i += 2) {
            while (n % i == 0) {
                factors.add(i);
                n = n / i;
            }
        }
        
        if (n > 1) {
            factors.add(n);
        }
        
        return factors;
    }
    
    /**
     * Get prime factors with their count
     */
    public static java.util.Map<Integer, Integer> getPrimeFactorsWithCount(int n) {
        java.util.Map<Integer, Integer> factors = new java.util.LinkedHashMap<>();
        
        // Check for 2
        while (n % 2 == 0) {
            factors.put(2, factors.getOrDefault(2, 0) + 1);
            n = n / 2;
        }
        
        // Check for odd factors
        for (int i = 3; i * i <= n; i += 2) {
            while (n % i == 0) {
                factors.put(i, factors.getOrDefault(i, 0) + 1);
                n = n / i;
            }
        }
        
        if (n > 1) {
            factors.put(n, factors.getOrDefault(n, 0) + 1);
        }
        
        return factors;
    }
    
    public static void main(String[] args) {
        System.out.println("=== Prime Factors of a Number ===\n");
        
        int[] testNumbers = {12, 60, 100, 17, 48, 1, 2, 96, 210};
        
        System.out.println("Method 1: Print Prime Factors");
        System.out.println("-".repeat(50));
        for (int num : testNumbers) {
            printPrimeFactors(num);
        }
        
        System.out.println("\nMethod 2: Prime Factors with Count");
        System.out.println("-".repeat(50));
        for (int num : testNumbers) {
            java.util.Map<Integer, Integer> factors = getPrimeFactorsWithCount(num);
            System.out.print(num + " = ");
            
            StringBuilder sb = new StringBuilder();
            for (java.util.Map.Entry<Integer, Integer> entry : factors.entrySet()) {
                if (sb.length() > 0) sb.append(" * ");
                if (entry.getValue() > 1) {
                    sb.append(entry.getKey()).append("^").append(entry.getValue());
                } else {
                    sb.append(entry.getKey());
                }
            }
            System.out.println(sb.toString());
        }
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("DETAILED EXAMPLE: Prime factors of 60");
        System.out.println("-".repeat(50));
        
        int n = 60;
        System.out.println("n = " + n);
        System.out.println("\nStep 1: Check divisibility by 2");
        int temp = n;
        while (temp % 2 == 0) {
            System.out.println("  " + temp + " ÷ 2 = " + (temp / 2));
            temp = temp / 2;
        }
        
        System.out.println("\nStep 2: Check odd numbers from 3 to sqrt(" + n + ")");
        for (int i = 3; i * i <= n; i += 2) {
            while (temp % i == 0) {
                System.out.println("  " + temp + " ÷ " + i + " = " + (temp / i));
                temp = temp / i;
            }
        }
        
        if (temp > 1) {
            System.out.println("  Remaining: " + temp + " (prime factor)");
        }
        
        System.out.println("\nFinal result: 60 = 2^2 × 3 × 5");
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("COMPLEXITY ANALYSIS:");
        System.out.println("-".repeat(50));
        System.out.println("Time Complexity: O(sqrt(n) * log n)");
        System.out.println("  - sqrt(n) iterations for checking divisors");
        System.out.println("  - log n divisions for each divisor");
        System.out.println("Space Complexity: O(1) or O(log n) for storing factors");
    }
}
