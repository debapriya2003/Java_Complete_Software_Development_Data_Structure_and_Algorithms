package sliding_window_two_pointer.medium;

import java.util.*;

/**
 * Count Number of Nice Subarrays
 * 
 * Problem: A "nice" subarray is a subarray with exactly k odd numbers.
 *          Given an array of positive integers and integer k,
 *          return the total number of nice subarrays.
 * 
 * Example: nums=[1,1,2,1,1], k=3 → 2
 *          ([1,1,2,1] at index 0-3, [1,2,1,1] at index 1-4)
 *          nums=[2,4,6], k=1 → 0
 *          nums=[2,2,2], k=0 → 6 (all subarrays of [2,2,2])
 *          nums=[1,2,1], k=1 → 4 ([1], [1,2], [2,1], [1])
 * 
 * Algorithm: At Most K Technique
 * ==============================
 * Key Insight: exactly k = atMostK(k) - atMostK(k-1)
 * 
 * Transform the problem:
 * 1. Convert each odd number to 1, even to 0
 * 2. Problem becomes: subarrays with sum = k
 * 3. Use atMostK approach with prefix sum
 * 4. exactly k = atMostK(k) - atMostK(k-1)
 * 
 * atMostK(k) = count of subarrays with sum <= k
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n) for hashmap
 */

public class CountNumberOfNiceSubarrays {
    
    /**
     * Count subarrays with at most k odd numbers
     */
    private static int atMostKOdd(int[] nums, int k) {
        Map<Integer, Integer> prefixCount = new HashMap<>();
        prefixCount.put(0, 1);  // Base case
        
        int oddCount = 0;
        int result = 0;
        
        for (int num : nums) {
            // Add 1 if odd, 0 if even
            oddCount += num % 2;
            
            // Find how many prefixes give us sum <= k
            if (prefixCount.containsKey(oddCount - k)) {
                result += prefixCount.get(oddCount - k);
            }
            
            // Add current prefix count
            prefixCount.put(oddCount, prefixCount.getOrDefault(oddCount, 0) + 1);
        }
        
        return result;
    }
    
    /**
     * Count subarrays with exactly k odd numbers
     */
    public static int numberOfSubarrays(int[] nums, int k) {
        return atMostKOdd(nums, k) - atMostKOdd(nums, k - 1);
    }
    
    /**
     * Alternative approach using sliding window
     */
    public static int numberOfSubarrays_SlidingWindow(int[] nums, int k) {
        // Convert to binary: odd=1, even=0
        int[] converted = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            converted[i] = nums[i] % 2;
        }
        
        // Count subarrays with sum = k using sliding window
        int count = 0;
        int oddsSeen = 0;
        int left = 0;
        
        for (int right = 0; right < nums.length; right++) {
            oddsSeen += converted[right];
            
            // Shrink window if too many odds
            while (oddsSeen > k) {
                oddsSeen -= converted[left];
                left++;
            }
            
            // Find leftmost position with exactly k odds
            int tempLeft = left;
            int tempOdds = oddsSeen;
            while (tempOdds > k) {
                tempOdds -= converted[tempLeft];
                tempLeft++;
            }
            
            // Count subarrays ending at right with exactly k odds
            if (oddsSeen == k) {
                count++;
                // Also count subarrays with 0s before first odd
                while (left < right && converted[left] == 0) {
                    count++;
                    left++;
                }
                left = tempLeft;
                oddsSeen = tempOdds;
            }
        }
        
        return count;
    }
    
    public static void main(String[] args) {
        System.out.println("=== Count Number of Nice Subarrays ===\n");
        
        int[][] testCases = {
            {1, 1, 2, 1, 1},
            {2, 4, 6},
            {2, 2, 2},
            {1, 2, 1},
            {1},
            {2}
        };
        
        int[] ks = {3, 1, 0, 1, 1, 0};
        
        System.out.println(String.format("%-30s | %-5s | %-8s", 
            "Array", "k", "Count"));
        System.out.println("-".repeat(50));
        
        for (int i = 0; i < testCases.length; i++) {
            int result = numberOfSubarrays(testCases[i], ks[i]);
            System.out.println(String.format("%-30s | %-5d | %-8d", 
                Arrays.toString(testCases[i]), ks[i], result));
        }
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("DETAILED EXAMPLE 1: nums=[1,1,2,1,1], k=3");
        System.out.println("-".repeat(60));
        
        int[] nums = {1, 1, 2, 1, 1};
        int k = 3;
        
        System.out.println("\nArray: " + Arrays.toString(nums));
        System.out.println("k = " + k);
        System.out.println("\nConvert to binary (odd→1, even→0):");
        int[] binary = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            binary[i] = nums[i] % 2;
        }
        System.out.println(Arrays.toString(binary));
        System.out.println("\nNow problem: find subarrays with SUM = 3");
        
        System.out.println("\nAtMostK(3) computation:");
        Map<Integer, Integer> prefixCount = new HashMap<>();
        prefixCount.put(0, 1);
        int oddCount = 0;
        int atMost3 = 0;
        
        System.out.println(String.format("%-8s | %-10s | %-10s | %-20s | %-10s", 
            "Index", "OddCount", "Needed", "PrefixMap", "AtMost3"));
        System.out.println("-".repeat(70));
        
        for (int i = 0; i < nums.length; i++) {
            oddCount += binary[i];
            int needed = oddCount - 3;
            if (prefixCount.containsKey(needed)) {
                atMost3 += prefixCount.get(needed);
            }
            prefixCount.put(oddCount, prefixCount.getOrDefault(oddCount, 0) + 1);
            
            System.out.println(String.format("%-8d | %-10d | %-10d | %-20s | %-10d", 
                i, oddCount, needed, prefixCount, atMost3));
        }
        System.out.println("atMostK(3) = " + atMost3);
        
        System.out.println("\nAtMostK(2) computation:");
        prefixCount.clear();
        prefixCount.put(0, 1);
        oddCount = 0;
        int atMost2 = 0;
        
        for (int i = 0; i < nums.length; i++) {
            oddCount += binary[i];
            int needed = oddCount - 2;
            if (prefixCount.containsKey(needed)) {
                atMost2 += prefixCount.get(needed);
            }
            prefixCount.put(oddCount, prefixCount.getOrDefault(oddCount, 0) + 1);
        }
        System.out.println("atMostK(2) = " + atMost2);
        
        System.out.println("\nExactly k = atMostK(3) - atMostK(2) = " + atMost3 + " - " + atMost2 + " = " + (atMost3 - atMost2));
        
        System.out.println("\nSubarrays with exactly 3 odd numbers:");
        int niceCount = 0;
        for (int i = 0; i < nums.length; i++) {
            int oddSum = 0;
            for (int j = i; j < nums.length; j++) {
                oddSum += binary[j];
                if (oddSum == k) {
                    System.out.println("[" + i + "," + j + "]: " + 
                        Arrays.toString(Arrays.copyOfRange(nums, i, j + 1)));
                    niceCount++;
                } else if (oddSum > k) {
                    break;
                }
            }
        }
        System.out.println("Total: " + niceCount);
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("DETAILED EXAMPLE 2: nums=[1,2,1], k=1");
        System.out.println("-".repeat(60));
        
        nums = new int[]{1, 2, 1};
        k = 1;
        
        System.out.println("\nArray: " + Arrays.toString(nums));
        System.out.println("k = " + k);
        System.out.println("\nBinary: [1, 0, 1] (odd, even, odd)");
        System.out.println("\nEnumerate all subarrays:");
        
        niceCount = 0;
        for (int i = 0; i < nums.length; i++) {
            int oddSum = 0;
            for (int j = i; j < nums.length; j++) {
                oddSum += binary[j];
                String subarray = "[" + i + "," + j + "]: " + 
                    Arrays.toString(Arrays.copyOfRange(nums, i, j + 1));
                System.out.print(subarray + " → odd_count=" + oddSum);
                if (oddSum == 1) {
                    System.out.println(" ✓ NICE");
                    niceCount++;
                } else {
                    System.out.println();
                }
            }
        }
        System.out.println("\nTotal nice subarrays: " + niceCount);
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("ALGORITHM EXPLANATION:");
        System.out.println("-".repeat(60));
        System.out.println("Key Insight: Use 'exactly k' = 'at most k' - 'at most k-1'");
        System.out.println("\n1. Convert odd numbers to 1, even to 0");
        System.out.println("2. Problem becomes: find subarrays with sum = k");
        System.out.println("3. Use prefix sum + hashmap for counting");
        System.out.println("4. atMostK(k): For each position, count how many");
        System.out.println("   previous prefixes give sum <= current");
        System.out.println("5. exactly k = atMostK(k) - atMostK(k-1)");
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("KEY POINTS:");
        System.out.println("-".repeat(60));
        System.out.println("1. Transform to binary array (odd→1, even→0)");
        System.out.println("2. Convert to subarray sum problem");
        System.out.println("3. Use prefix sum with frequency tracking");
        System.out.println("4. 'Exactly k' = 'at most k' - 'at most k-1'");
        System.out.println("5. For 'at most k': look for prefix = curr - k");
        System.out.println("6. Time: O(n) for each call, O(2n) total");
        System.out.println("7. Space: O(n) for hashmap");
    }
}
