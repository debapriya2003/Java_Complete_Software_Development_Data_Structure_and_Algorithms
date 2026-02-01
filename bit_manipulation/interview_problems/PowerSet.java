package bit_manipulation.interview_problems;

import java.util.*;

/**
 * Power Set
 * 
 * Problem: Generate all possible subsets of a given set (Power Set).
 * 
 * Approach: Bit Manipulation
 * - For a set of n elements, there are 2^n subsets
 * - We can use each number from 0 to 2^n - 1 as a bitmask
 * - If i-th bit is set in the bitmask, include i-th element in the subset
 * 
 * Example: Set = [1, 2, 3]
 * 2^3 = 8 subsets
 * 
 * Bitmask 0 (000) -> []
 * Bitmask 1 (001) -> [1]
 * Bitmask 2 (010) -> [2]
 * Bitmask 3 (011) -> [1, 2]
 * Bitmask 4 (100) -> [3]
 * Bitmask 5 (101) -> [1, 3]
 * Bitmask 6 (110) -> [2, 3]
 * Bitmask 7 (111) -> [1, 2, 3]
 * 
 * Time Complexity: O(n * 2^n) - we have 2^n subsets, each can have up to n elements
 * Space Complexity: O(2^n) - to store all subsets
 */

public class PowerSet {
    
    /**
     * Generate power set using bit manipulation
     * @param arr input array
     * @return list of all subsets
     */
    public static List<List<Integer>> generatePowerSet(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        int n = arr.length;
        int totalSubsets = 1 << n;  // 2^n
        
        // Iterate through all possible bitmasks from 0 to 2^n - 1
        for (int mask = 0; mask < totalSubsets; mask++) {
            List<Integer> subset = new ArrayList<>();
            
            // Check each bit position
            for (int i = 0; i < n; i++) {
                // If i-th bit is set in mask, include arr[i]
                if ((mask & (1 << i)) != 0) {
                    subset.add(arr[i]);
                }
            }
            
            result.add(subset);
        }
        
        return result;
    }
    
    /**
     * Generate power set with detailed bitmask info
     * @param arr input array
     * @return list of all subsets with their bitmasks
     */
    public static List<String> generatePowerSetWithMask(int[] arr) {
        List<String> result = new ArrayList<>();
        int n = arr.length;
        int totalSubsets = 1 << n;
        
        for (int mask = 0; mask < totalSubsets; mask++) {
            StringBuilder subset = new StringBuilder();
            String binaryMask = String.format("%" + n + "s", Integer.toBinaryString(mask))
                                      .replace(' ', '0');
            
            subset.append("[");
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    if (subset.length() > 1) subset.append(", ");
                    subset.append(arr[i]);
                }
            }
            subset.append("]");
            
            result.add(String.format("Mask: %s -> %s", binaryMask, subset.toString()));
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        System.out.println("=== Power Set using Bit Manipulation ===\n");
        
        int[][] testCases = {
            {1, 2},
            {1, 2, 3},
            {10, 20, 30, 40}
        };
        
        for (int[] arr : testCases) {
            System.out.println("Input: " + Arrays.toString(arr));
            List<List<Integer>> powerSet = generatePowerSet(arr);
            System.out.println("Power Set (" + powerSet.size() + " subsets):");
            
            for (List<Integer> subset : powerSet) {
                System.out.println("  " + subset);
            }
            System.out.println();
        }
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("DETAILED EXAMPLE: [1, 2, 3] with Bitmask");
        System.out.println("-".repeat(60));
        
        int[] arr = {1, 2, 3};
        List<String> details = generatePowerSetWithMask(arr);
        
        for (String detail : details) {
            System.out.println(detail);
        }
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("HOW IT WORKS:");
        System.out.println("-".repeat(60));
        System.out.println("For array [1, 2, 3]:");
        System.out.println("- Total subsets = 2^3 = 8");
        System.out.println("- Use masks from 0 to 7 (0b000 to 0b111)");
        System.out.println("- For each mask, check which bits are set");
        System.out.println("- Include arr[i] if (mask & (1 << i)) != 0");
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("COMPLEXITY ANALYSIS:");
        System.out.println("-".repeat(60));
        System.out.println("Time Complexity: O(n * 2^n)");
        System.out.println("  - 2^n subsets to generate");
        System.out.println("  - Each subset can have up to n elements");
        System.out.println("\nSpace Complexity: O(2^n)");
        System.out.println("  - To store all subsets");
    }
}
