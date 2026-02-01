package sliding_window_two_pointer.medium;

import java.util.*;

/**
 * Max Consecutive Ones
 * 
 * Problem: Given array of 0s and 1s, find max consecutive 1s.
 * 
 * Example: [1, 1, 0, 1, 1, 1] → 3
 *          [1, 0, 1, 1, 0, 1] → 2
 *          [0, 0, 0] → 0
 *          [1, 1, 1, 1] → 4
 * 
 * Algorithm: Single Pass
 * =======================
 * 1. Keep counter of consecutive 1s
 * 2. When encounter 1: increment counter
 * 3. When encounter 0: update max, reset counter
 * 4. Return maximum
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

public class MaxConsecutiveOnes {
    
    /**
     * Find maximum consecutive 1s
     */
    public static int findMaxConsecutiveOnes(int[] nums) {
        int maxCount = 0;
        int currentCount = 0;
        
        for (int num : nums) {
            if (num == 1) {
                currentCount++;
                maxCount = Math.max(maxCount, currentCount);
            } else {
                currentCount = 0;
            }
        }
        
        return maxCount;
    }
    
    public static void main(String[] args) {
        System.out.println("=== Max Consecutive Ones ===\n");
        
        int[][] testCases = {
            {1, 1, 0, 1, 1, 1},
            {1, 0, 1, 1, 0, 1},
            {0, 0, 0},
            {1, 1, 1, 1},
            {1},
            {0}
        };
        
        System.out.println(String.format("%-40s | %-10s", "Array", "Max Ones"));
        System.out.println("-".repeat(55));
        
        for (int[] arr : testCases) {
            int result = findMaxConsecutiveOnes(arr);
            System.out.println(String.format("%-40s | %-10d", 
                Arrays.toString(arr), result));
        }
        
        System.out.println("\n" + "=".repeat(55));
        System.out.println("DETAILED EXAMPLE: [1, 1, 0, 1, 1, 1]");
        System.out.println("-".repeat(55));
        
        int[] nums = {1, 1, 0, 1, 1, 1};
        
        System.out.println("\nStep-by-step trace:\n");
        System.out.println(String.format("%-8s | %-8s | %-15s | %-15s", 
            "Index", "Value", "Current Count", "Max Count"));
        System.out.println("-".repeat(50));
        
        int maxCount = 0;
        int currentCount = 0;
        
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                currentCount++;
                maxCount = Math.max(maxCount, currentCount);
                System.out.println(String.format("%-8d | %-8d | %-15d | %-15d", 
                    i, nums[i], currentCount, maxCount));
            } else {
                System.out.println(String.format("%-8d | %-8d | %-15d | %-15d (reset)", 
                    i, nums[i], 0, maxCount));
                currentCount = 0;
            }
        }
        
        System.out.println("\nResult: " + maxCount);
        
        System.out.println("\n" + "=".repeat(55));
        System.out.println("VISUALIZED EXAMPLE: [1, 1, 0, 1, 1, 1]");
        System.out.println("-".repeat(55));
        
        nums = new int[]{1, 1, 0, 1, 1, 1};
        
        System.out.println("\nArray with indices:");
        System.out.print("Index: ");
        for (int i = 0; i < nums.length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        
        System.out.print("Value: ");
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println("\n");
        
        System.out.println("Consecutive 1s groups:");
        System.out.println("[0-1]: 1, 1 → count = 2");
        System.out.println("[2]: 0 → reset");
        System.out.println("[3-5]: 1, 1, 1 → count = 3 ← MAXIMUM");
        System.out.println("\nMax: 3");
        
        System.out.println("\n" + "=".repeat(55));
        System.out.println("ANOTHER EXAMPLE: [1, 0, 1, 1, 0, 1]");
        System.out.println("-".repeat(55));
        
        nums = new int[]{1, 0, 1, 1, 0, 1};
        int result = findMaxConsecutiveOnes(nums);
        
        System.out.println("\nArray: " + Arrays.toString(nums));
        System.out.println("Groups: [0]=1(count 1), [1]=0(reset), [2-3]=1,1(count 2), [4]=0(reset), [5]=1(count 1)");
        System.out.println("Max: " + result);
        
        System.out.println("\n" + "=".repeat(55));
        System.out.println("ALGORITHM EXPLANATION:");
        System.out.println("-".repeat(55));
        System.out.println("1. Single pass through array");
        System.out.println("2. If current element is 1:");
        System.out.println("   - Increment current count");
        System.out.println("   - Update max if needed");
        System.out.println("3. If current element is 0:");
        System.out.println("   - Reset current count to 0");
        System.out.println("4. Return maximum count");
        
        System.out.println("\n" + "=".repeat(55));
        System.out.println("COMPLEXITY:");
        System.out.println("-".repeat(55));
        System.out.println("Time:  O(n) - single pass");
        System.out.println("Space: O(1) - only counters");
    }
}
