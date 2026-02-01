package sliding_window_two_pointer.medium;

import java.util.*;

/**
 * Binary Subarray With Sum
 * 
 * Problem: Given a binary array nums and an integer goal, find the total number
 *          of subarrays with sum equal to goal.
 * 
 * Example: nums=[1,0,1,0,1], goal=2 → 4
 *          (subarrays: [1,0,1], [1,0,1], [0,1,0,1], [1,0,1])
 *          nums=[1,1,1], goal=2 → 2
 *          ([1,1] appears at positions [0,1] and [1,2])
 *          nums=[0,0,0], goal=0 → 6
 * 
 * Algorithm: Prefix Sum with HashMap
 * ===================================
 * Key Insight: "exactly k" = "at most k" - "at most (k-1)"
 * 
 * For prefix sum approach:
 * - If current_sum - goal exists in hashmap, found subarrays
 * - Example: curr_sum=5, goal=2
 *            Need prefix_sum=3 (because 5-3=2)
 *            All positions with prefix_sum=3 give us valid subarrays
 * 
 * Alternative: Use atMostKSum helper
 * - countSubarrays(goal) = atMostK(goal) - atMostK(goal-1)
 * - atMostK uses sliding window to count all subarrays with sum ≤ k
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n) for hashmap
 */

public class BinarySubarrayWithSum {
    
    /**
     * Approach 1: Prefix Sum HashMap
     */
    public static int findMaxLength_PrefixSum(int[] nums, int goal) {
        Map<Integer, Integer> prefixCount = new HashMap<>();
        prefixCount.put(0, 1);  // Base case: sum 0 seen once before any element
        
        int currentSum = 0;
        int count = 0;
        
        for (int num : nums) {
            currentSum += num;
            
            // Check if (currentSum - goal) exists in map
            if (prefixCount.containsKey(currentSum - goal)) {
                count += prefixCount.get(currentSum - goal);
            }
            
            // Add current sum to map
            prefixCount.put(currentSum, prefixCount.getOrDefault(currentSum, 0) + 1);
        }
        
        return count;
    }
    
    /**
     * Approach 2: At Most K technique
     * exactly k = atMostK(k) - atMostK(k-1)
     */
    private static int atMostKSum(int[] nums, int k) {
        Map<Integer, Integer> prefixCount = new HashMap<>();
        prefixCount.put(0, 1);
        
        int currentSum = 0;
        int count = 0;
        
        for (int num : nums) {
            currentSum += num;
            
            if (prefixCount.containsKey(currentSum - k)) {
                count += prefixCount.get(currentSum - k);
            }
            
            prefixCount.put(currentSum, prefixCount.getOrDefault(currentSum, 0) + 1);
        }
        
        return count;
    }
    
    public static int findMaxLength_AtMostK(int[] nums, int goal) {
        return atMostKSum(nums, goal) - atMostKSum(nums, goal - 1);
    }
    
    public static void main(String[] args) {
        System.out.println("=== Binary Subarray With Sum ===\n");
        
        int[][] testCases = {
            {1, 0, 1, 0, 1},
            {1, 1, 1},
            {0, 0, 0},
            {0},
            {1},
            {1, 1, 1, 1, 1}
        };
        
        int[] goals = {2, 2, 0, 1, 1, 3};
        
        System.out.println(String.format("%-30s | %-8s | %-12s", 
            "Array", "Goal", "Count"));
        System.out.println("-".repeat(55));
        
        for (int i = 0; i < testCases.length; i++) {
            int result = findMaxLength_PrefixSum(testCases[i], goals[i]);
            System.out.println(String.format("%-30s | %-8d | %-12d", 
                Arrays.toString(testCases[i]), goals[i], result));
        }
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("DETAILED EXAMPLE 1: nums=[1,0,1,0,1], goal=2");
        System.out.println("-".repeat(60));
        
        int[] nums = {1, 0, 1, 0, 1};
        int goal = 2;
        
        System.out.println("\nArray: " + Arrays.toString(nums));
        System.out.println("Goal: " + goal);
        System.out.println("\nPrefix Sum Trace:\n");
        System.out.println(String.format("%-8s | %-8s | %-15s | %-15s | %-10s", 
            "Index", "Num", "PrefixSum", "Prefix Map", "Count"));
        System.out.println("-".repeat(65));
        
        Map<Integer, Integer> prefixCount = new HashMap<>();
        prefixCount.put(0, 1);
        
        int currentSum = 0;
        int count = 0;
        
        for (int i = 0; i < nums.length; i++) {
            currentSum += nums[i];
            
            int needed = currentSum - goal;
            int prevCount = 0;
            if (prefixCount.containsKey(needed)) {
                prevCount = prefixCount.get(needed);
                count += prevCount;
            }
            
            prefixCount.put(currentSum, prefixCount.getOrDefault(currentSum, 0) + 1);
            
            System.out.println(String.format("%-8d | %-8d | %-15d | %-15s | %-10d", 
                i, nums[i], currentSum, prefixCount, count));
        }
        
        System.out.println("\nTotal subarrays with sum = 2: " + count);
        
        System.out.println("\nSubarrays found:");
        // Manual enumeration for verification
        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += nums[k];
                }
                if (sum == goal) {
                    System.out.println("[" + i + "," + j + "]: " + 
                        Arrays.toString(Arrays.copyOfRange(nums, i, j + 1)) + " = " + sum);
                }
            }
        }
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("DETAILED EXAMPLE 2: nums=[1,1,1], goal=2");
        System.out.println("-".repeat(60));
        
        nums = new int[]{1, 1, 1};
        goal = 2;
        
        System.out.println("\nArray: " + Arrays.toString(nums));
        System.out.println("Goal: " + goal);
        System.out.println("\nPrefix Sum computation:");
        System.out.println("Initial: prefixMap = {0→1}");
        System.out.println("\ni=0, num=1:");
        System.out.println("  currentSum = 0 + 1 = 1");
        System.out.println("  Look for (1-2) = -1 in map: NOT FOUND");
        System.out.println("  prefixMap = {0→1, 1→1}");
        System.out.println("  count = 0");
        
        System.out.println("\ni=1, num=1:");
        System.out.println("  currentSum = 1 + 1 = 2");
        System.out.println("  Look for (2-2) = 0 in map: FOUND! count += 1");
        System.out.println("  prefixMap = {0→1, 1→1, 2→1}");
        System.out.println("  count = 1");
        System.out.println("  → Subarray [0,1]: [1,1] = 2 ✓");
        
        System.out.println("\ni=2, num=1:");
        System.out.println("  currentSum = 2 + 1 = 3");
        System.out.println("  Look for (3-2) = 1 in map: FOUND! count += 1");
        System.out.println("  prefixMap = {0→1, 1→1, 2→1, 3→1}");
        System.out.println("  count = 2");
        System.out.println("  → Subarray [1,2]: [1,1] = 2 ✓");
        
        System.out.println("\nResult: 2 subarrays");
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("ALGORITHM EXPLANATION:");
        System.out.println("-".repeat(60));
        System.out.println("Key Insight: Use Prefix Sum");
        System.out.println("  If prefix[j] - prefix[i-1] = goal");
        System.out.println("  Then sum of subarray[i,j] = goal");
        System.out.println("\nAlgorithm:");
        System.out.println("1. Maintain current prefix sum and its frequency map");
        System.out.println("2. For each element, add to current sum");
        System.out.println("3. Check if (currentSum - goal) exists in map");
        System.out.println("4. If yes, add its frequency to count");
        System.out.println("5. Update frequency map with current sum");
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("WHY DOES IT WORK:");
        System.out.println("-".repeat(60));
        System.out.println("If currentSum = C and we want sum = goal");
        System.out.println("  We need prefix where prefix = C - goal");
        System.out.println("  Because: C - (C - goal) = goal");
        System.out.println("\nExample: Array [1,0,1,0,1], at index 2:");
        System.out.println("  currentSum = 2");
        System.out.println("  Look for 2 - 2 = 0");
        System.out.println("  This was seen at index -1 (before start)");
        System.out.println("  So subarray [0,2] = [1,0,1] has sum 2");
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("KEY POINTS:");
        System.out.println("-".repeat(60));
        System.out.println("1. Prefix sum = cumulative sum up to current index");
        System.out.println("2. HashMap stores frequency of each prefix sum");
        System.out.println("3. Initialize with {0→1} for empty prefix");
        System.out.println("4. Time: O(n) single pass with HashMap");
        System.out.println("5. Space: O(n) for HashMap");
        System.out.println("6. Works for any target sum, not just binary array");
    }
}
