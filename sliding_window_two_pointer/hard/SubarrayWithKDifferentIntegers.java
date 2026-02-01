package sliding_window_two_pointer.hard;

import java.util.*;

/**
 * Subarray with K Different Integers
 * 
 * Problem: Given an array of positive integers nums and an integer k,
 *          return the number of subarrays that have exactly k different integers.
 * 
 * Example: nums=[1,2,1,2,3], k=2 → 7
 *          ([1,2], [1,2,1], [2,1], [2,1,2], [1,2,3] NO, [2,3] NO... correct: [1,2], [1,2,1], [2,1], [2,1,2], [1], [2], [1,2])
 *          Wait, let me recount: "exactly 2 different"
 *          [1,2] (0-1), [1,2,1] (0-2), [2,1] (1-2), [2,1,2] (1-3), [1] NO, [2] NO...
 *          Actually: [1,2], [2,1], [1,2,1], [2,1,2]... need to verify
 *          nums=[1,2,1,3], k=2 → 6
 * 
 * Algorithm: At Most K Technique
 * ==============================
 * Key Insight: exactly k = atMostK(k) - atMostK(k-1)
 * 
 * Use sliding window to count subarrays with AT MOST k distinct integers:
 * 1. For each right pointer, maintain frequency map
 * 2. Shrink window if distinct count > k
 * 3. All subarrays ending at right with left in valid range count
 * 4. Use formula: exactly k = atMostK(k) - atMostK(k-1)
 * 
 * Time Complexity: O(n) for each atMostK call, O(2n) total
 * Space Complexity: O(k) for hashmap
 */

public class SubarrayWithKDifferentIntegers {
    
    /**
     * Count subarrays with at most k different integers
     */
    private static int atMostKDistinct(int[] nums, int k) {
        if (k == 0) return 0;
        
        Map<Integer, Integer> freq = new HashMap<>();
        int left = 0;
        int count = 0;
        
        for (int right = 0; right < nums.length; right++) {
            // Add right element
            freq.put(nums[right], freq.getOrDefault(nums[right], 0) + 1);
            
            // Shrink window if more than k distinct
            while (freq.size() > k) {
                freq.put(nums[left], freq.get(nums[left]) - 1);
                if (freq.get(nums[left]) == 0) {
                    freq.remove(nums[left]);
                }
                left++;
            }
            
            // All subarrays ending at right with left at any valid position
            count += right - left + 1;
        }
        
        return count;
    }
    
    /**
     * Count subarrays with exactly k different integers
     */
    public static int subarraysWithKDistinct(int[] nums, int k) {
        return atMostKDistinct(nums, k) - atMostKDistinct(nums, k - 1);
    }
    
    public static void main(String[] args) {
        System.out.println("=== Subarray with K Different Integers ===\n");
        
        int[][] testCases = {
            {1, 2, 1, 2, 3},
            {1, 2, 1, 3},
            {1, 1, 1},
            {1, 2, 3},
            {1, 1, 2, 2}
        };
        
        int[] ks = {2, 2, 1, 3, 2};
        
        System.out.println(String.format("%-30s | %-5s | %-10s", 
            "Array", "k", "Count"));
        System.out.println("-".repeat(50));
        
        for (int i = 0; i < testCases.length; i++) {
            int result = subarraysWithKDistinct(testCases[i], ks[i]);
            System.out.println(String.format("%-30s | %-5d | %-10d", 
                Arrays.toString(testCases[i]), ks[i], result));
        }
        
        System.out.println("\n" + "=".repeat(75));
        System.out.println("DETAILED EXAMPLE 1: nums=[1,2,1,2,3], k=2");
        System.out.println("-".repeat(75));
        
        int[] nums = {1, 2, 1, 2, 3};
        int k = 2;
        
        System.out.println("\nArray: " + Arrays.toString(nums));
        System.out.println("k = " + k);
        System.out.println("\nFind subarrays with EXACTLY 2 different integers");
        
        System.out.println("\nAtMostK(2) computation:");
        System.out.println("Count subarrays with ≤ 2 distinct integers");
        
        Map<Integer, Integer> freq = new HashMap<>();
        int left = 0;
        int atMost2 = 0;
        
        System.out.println(String.format("%-8s | %-8s | %-10s | %-20s | %-15s | %-10s", 
            "Left", "Right", "Num", "Frequency", "Distinct", "Added"));
        System.out.println("-".repeat(75));
        
        for (int right = 0; right < nums.length; right++) {
            freq.put(nums[right], freq.getOrDefault(nums[right], 0) + 1);
            
            System.out.print(String.format("%-8d | %-8d | %-10d | ", left, right, nums[right]));
            
            while (freq.size() > k) {
                freq.put(nums[left], freq.get(nums[left]) - 1);
                if (freq.get(nums[left]) == 0) {
                    freq.remove(nums[left]);
                }
                left++;
            }
            
            int subarraysCount = right - left + 1;
            atMost2 += subarraysCount;
            
            System.out.println(String.format("%-20s | %-15d | +%-10d", 
                freq, freq.size(), subarraysCount));
        }
        System.out.println("Total atMostK(2) = " + atMost2);
        
        System.out.println("\nAtMostK(1) computation:");
        System.out.println("Count subarrays with ≤ 1 distinct integer");
        
        freq.clear();
        left = 0;
        int atMost1 = 0;
        
        System.out.println(String.format("%-8s | %-8s | %-10s | %-20s | %-15s | %-10s", 
            "Left", "Right", "Num", "Frequency", "Distinct", "Added"));
        System.out.println("-".repeat(75));
        
        for (int right = 0; right < nums.length; right++) {
            freq.put(nums[right], freq.getOrDefault(nums[right], 0) + 1);
            
            System.out.print(String.format("%-8d | %-8d | %-10d | ", left, right, nums[right]));
            
            while (freq.size() > 1) {
                freq.put(nums[left], freq.get(nums[left]) - 1);
                if (freq.get(nums[left]) == 0) {
                    freq.remove(nums[left]);
                }
                left++;
            }
            
            int subarraysCount = right - left + 1;
            atMost1 += subarraysCount;
            
            System.out.println(String.format("%-20s | %-15d | +%-10d", 
                freq, freq.size(), subarraysCount));
        }
        System.out.println("Total atMostK(1) = " + atMost1);
        
        System.out.println("\nExactly k(2) = atMostK(2) - atMostK(1) = " + atMost2 + " - " + atMost1 + " = " + (atMost2 - atMost1));
        
        System.out.println("\nVerification - Enumerate exactly 2 different subarrays:");
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            Set<Integer> distinct = new HashSet<>();
            for (int j = i; j < nums.length; j++) {
                distinct.add(nums[j]);
                if (distinct.size() == k) {
                    count++;
                    System.out.println("[" + i + "," + j + "]: " + 
                        Arrays.toString(Arrays.copyOfRange(nums, i, j + 1)) + 
                        " → " + distinct);
                } else if (distinct.size() > k) {
                    break;
                }
            }
        }
        System.out.println("Manual count: " + count);
        
        System.out.println("\n" + "=".repeat(75));
        System.out.println("DETAILED EXAMPLE 2: nums=[1,2,1,3], k=2");
        System.out.println("-".repeat(75));
        
        nums = new int[]{1, 2, 1, 3};
        k = 2;
        
        System.out.println("\nArray: " + Arrays.toString(nums));
        System.out.println("k = " + k);
        System.out.println("\nSubarrays with exactly 2 different integers:");
        
        count = 0;
        for (int i = 0; i < nums.length; i++) {
            Set<Integer> distinct = new HashSet<>();
            for (int j = i; j < nums.length; j++) {
                distinct.add(nums[j]);
                if (distinct.size() == k) {
                    count++;
                    System.out.println("[" + i + "," + j + "]: " + 
                        Arrays.toString(Arrays.copyOfRange(nums, i, j + 1)) + 
                        " → {" + distinct + "}");
                } else if (distinct.size() > k) {
                    break;
                }
            }
        }
        System.out.println("Total: " + count);
        
        System.out.println("\n" + "=".repeat(75));
        System.out.println("ALGORITHM EXPLANATION:");
        System.out.println("-".repeat(75));
        System.out.println("Key Insight: exactly k = atMostK(k) - atMostK(k-1)");
        System.out.println("\natMostK approach:");
        System.out.println("1. Maintain sliding window with at most k distinct integers");
        System.out.println("2. For each right position:");
        System.out.println("   - Add nums[right] to frequency map");
        System.out.println("   - While distinct count > k, shrink from left");
        System.out.println("   - All subarrays from [any_valid_left, right] count");
        System.out.println("   - Add (right - left + 1) to total");
        System.out.println("\nExample [1,2,1,2,3] with atMostK(2):");
        System.out.println("[0,0]: [1] → {1} = 1 subarray");
        System.out.println("[0,1]: [1,2] → {1,2} = 2 subarrays: [1,2], [2]");
        System.out.println("[0,2]: [1,2,1] → {1,2} = 3 subarrays: [1,2,1], [2,1], [1]");
        System.out.println("etc.");
        
        System.out.println("\n" + "=".repeat(75));
        System.out.println("KEY POINTS:");
        System.out.println("-".repeat(75));
        System.out.println("1. Use 'exactly k' = 'at most k' - 'at most k-1'");
        System.out.println("2. Implement atMostK using sliding window");
        System.out.println("3. Frequency map tracks count of each integer");
        System.out.println("4. When distinct > k, shrink from left");
        System.out.println("5. All subarrays [left..right] to [right] are valid");
        System.out.println("6. Time: O(n) for each atMostK, O(2n) total");
        System.out.println("7. Space: O(k) for frequency map");
    }
}
