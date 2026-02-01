package bit_manipulation.interview_problems;

/**
 * Find the number that appears odd number of times
 * 
 * Problem: Given an array where all numbers appear even number of times except one,
 * find the number that appears odd number of times.
 * 
 * Key Insight:
 * - a ^ a = 0 (XOR of same numbers is 0)
 * - a ^ 0 = a (XOR with 0 is identity)
 * - XOR is commutative and associative
 * 
 * Approach:
 * XOR all numbers in the array. The result will be the number appearing odd times.
 * 
 * Example: [2, 2, 3, 4, 4, 4, 5, 5]
 * 2 ^ 2 ^ 3 ^ 4 ^ 4 ^ 4 ^ 5 ^ 5
 * = 0 ^ 3 ^ 0 ^ 4 ^ 0
 * = 3 ^ 4
 * = 7
 * 
 * Numbers appearing even times: 2 (2 times), 4 (2 times), 5 (2 times) -> XOR = 0
 * Number appearing odd times: 3 (1 time), 4 (1 more time making 3) -> XOR result = 3 ^ 4
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

public class OddOccurrence {
    
    /**
     * Find number appearing odd number of times
     * @param arr array of integers
     * @return number appearing odd times
     */
    public static int findOddOccurrence(int[] arr) {
        int result = 0;
        for (int num : arr) {
            result ^= num;
        }
        return result;
    }
    
    /**
     * Find the count of odd occurrences for a specific number
     * @param arr array of integers
     * @param num the number to count
     * @return count of occurrences
     */
    public static int countOccurrences(int[] arr, int num) {
        int count = 0;
        for (int val : arr) {
            if (val == num) count++;
        }
        return count;
    }
    
    public static void main(String[] args) {
        System.out.println("=== Find Number Appearing Odd Number of Times ===\n");
        
        int[][] testCases = {
            {1, 1, 2, 2, 3},
            {5, 5, 5, 10, 10},
            {2, 2, 4, 4, 4, 4, 5, 5},
            {7},
            {1, 2, 2, 3, 3},
            {10, 10, 10, 20, 20, 20, 30}
        };
        
        for (int[] arr : testCases) {
            int result = findOddOccurrence(arr);
            int count = countOccurrences(arr, result);
            
            System.out.print("Array: ");
            for (int num : arr) System.out.print(num + " ");
            System.out.println("\nResult: " + result + " (appears " + count + " times)");
            System.out.println("-".repeat(50));
        }
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("DETAILED EXAMPLE: [2, 2, 3, 4, 4, 4, 5, 5]");
        System.out.println("-".repeat(50));
        
        int[] arr = {2, 2, 3, 4, 4, 4, 5, 5};
        System.out.println("Array elements with binary:");
        for (int num : arr) {
            System.out.println("  " + num + " = " + Integer.toBinaryString(num));
        }
        
        System.out.println("\nXOR calculation:");
        int result = 0;
        for (int num : arr) {
            int prev = result;
            result ^= num;
            System.out.println("  " + prev + " ^ " + num + " = " + result);
        }
        
        System.out.println("\nFinal result: " + result);
        System.out.println("Count of " + result + " in array: " + countOccurrences(arr, result));
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("KEY INSIGHT: XOR Properties");
        System.out.println("-".repeat(50));
        System.out.println("a ^ a = 0        (same number XOR = 0)");
        System.out.println("a ^ 0 = a        (XOR with 0 is identity)");
        System.out.println("Commutative: a ^ b = b ^ a");
        System.out.println("Associative: (a ^ b) ^ c = a ^ (b ^ c)");
        System.out.println("\nSo even occurrences cancel out to 0, odd ones remain!");
    }
}
