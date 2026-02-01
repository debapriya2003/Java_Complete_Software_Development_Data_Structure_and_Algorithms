package stacks_queues.monotonic_stack;

import java.util.*;

/**
 * Trapping Rainwater
 * 
 * Problem: Given an elevation map represented by array of heights,
 *          calculate how much water can be trapped after raining.
 * 
 * Example: [0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1]
 * Water trapped = 6 units
 * 
 * Visualization:
 *        |
 *    |   |
 *    | W |   |
 *    | W W |W|
 *    |WWWWWWWW|
 *    |WWWWWWWW|
 * _____|_______|___
 * [0,1,0,2,1,0,1,3,2,1,2,1]
 * 
 * Algorithm 1: Two-pointer (Optimal)
 * ================================
 * 1. Track left_max and right_max
 * 2. Use two pointers from both ends
 * 3. At each position, water trapped = min(left_max, right_max) - height
 * Time: O(n), Space: O(1)
 * 
 * Algorithm 2: Monotonic Stack
 * =============================
 * 1. Use decreasing monotonic stack to store indices
 * 2. For each bar:
 *    - If height > stack top, pop and calculate water
 *    - Push current bar
 * 3. Water between two bars depends on boundary heights and distance
 * Time: O(n), Space: O(n)
 * 
 * Algorithm 3: Pre-compute Max
 * ============================
 * 1. Pre-compute left_max and right_max for each position
 * 2. Water = min(left_max, right_max) - height
 * Time: O(n), Space: O(n)
 */

public class TrappingRainwater {
    
    /**
     * Trapping rainwater using two-pointer approach (Optimal)
     */
    public static int trapWater(int[] height) {
        if (height == null || height.length == 0) return 0;
        
        int n = height.length;
        int left = 0, right = n - 1;
        int leftMax = 0, rightMax = 0;
        int water = 0;
        
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    water += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    water += rightMax - height[right];
                }
                right--;
            }
        }
        
        return water;
    }
    
    /**
     * Trapping rainwater using monotonic stack
     */
    public static int trapWaterStack(int[] height) {
        if (height == null || height.length == 0) return 0;
        
        Stack<Integer> stack = new Stack<>();
        int water = 0;
        
        for (int i = 0; i < height.length; i++) {
            int h = height[i];
            
            while (!stack.isEmpty() && h > height[stack.peek()]) {
                int top = stack.pop();
                
                if (stack.isEmpty()) break;
                
                int leftIdx = stack.peek();
                int width = i - leftIdx - 1;
                int boundedHeight = Math.min(height[leftIdx], h) - height[top];
                water += width * boundedHeight;
            }
            
            stack.push(i);
        }
        
        return water;
    }
    
    /**
     * Trapping rainwater using pre-computed max arrays
     */
    public static int trapWaterPrecompute(int[] height) {
        if (height == null || height.length == 0) return 0;
        
        int n = height.length;
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];
        
        // Pre-compute left_max
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
        
        // Pre-compute right_max
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }
        
        // Calculate water
        int water = 0;
        for (int i = 0; i < n; i++) {
            int waterLevel = Math.min(leftMax[i], rightMax[i]);
            water += waterLevel - height[i];
        }
        
        return water;
    }
    
    public static void main(String[] args) {
        System.out.println("=== Trapping Rainwater ===\n");
        
        int[][] testCases = {
            {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1},
            {4, 2, 0, 3, 2, 5},
            {0, 1, 2, 3, 4, 5},
            {5, 4, 3, 2, 1, 0},
            {2, 0, 2},
            {3, 0, 2, 0, 4}
        };
        
        System.out.println(String.format("%-40s | %-15s", "Heights", "Water Trapped"));
        System.out.println("-".repeat(60));
        
        for (int[] heights : testCases) {
            int result = trapWater(heights);
            System.out.println(String.format("%-40s | %-15d", 
                Arrays.toString(heights), result));
        }
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("DETAILED EXAMPLE: [0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1]");
        System.out.println("-".repeat(60));
        
        int[] heights = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int n = heights.length;
        
        System.out.println("\nVisual representation:");
        System.out.println("        |");
        System.out.println("    |   |");
        System.out.println("    | W |   |");
        System.out.println("    | W W |W|");
        System.out.println("    |WWWWWWWW|");
        System.out.println("    |WWWWWWWW|");
        System.out.println("____|_______|___");
        System.out.println(Arrays.toString(heights));
        
        System.out.println("\n\nUSING TWO-POINTER APPROACH:");
        System.out.println("-".repeat(60));
        
        int left = 0, right = n - 1;
        int leftMax = 0, rightMax = 0;
        int water = 0;
        
        System.out.println(String.format("%-8s | %-8s | %-10s | %-10s | %-15s | %-10s", 
            "Left", "Right", "LeftMax", "RightMax", "Water at pos", "Total"));
        System.out.println("-".repeat(70));
        
        left = 0;
        right = n - 1;
        leftMax = 0;
        rightMax = 0;
        water = 0;
        
        while (left < right) {
            if (heights[left] < heights[right]) {
                if (heights[left] >= leftMax) {
                    leftMax = heights[left];
                    System.out.println(String.format("%-8d | %-8s | %-10d | %-10d | %-15d | %-10d", 
                        left, "", leftMax, rightMax, 0, water));
                } else {
                    int w = leftMax - heights[left];
                    water += w;
                    System.out.println(String.format("%-8d | %-8s | %-10d | %-10d | %-15d | %-10d", 
                        left, "", leftMax, rightMax, w, water));
                }
                left++;
            } else {
                if (heights[right] >= rightMax) {
                    rightMax = heights[right];
                    System.out.println(String.format("%-8s | %-8d | %-10d | %-10d | %-15d | %-10d", 
                        "", right, leftMax, rightMax, 0, water));
                } else {
                    int w = rightMax - heights[right];
                    water += w;
                    System.out.println(String.format("%-8s | %-8d | %-10d | %-10d | %-15d | %-10d", 
                        "", right, leftMax, rightMax, w, water));
                }
                right--;
            }
        }
        
        System.out.println("\nTotal Water Trapped: " + water);
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("DETAILED INDEX-BY-INDEX ANALYSIS:");
        System.out.println("-".repeat(60));
        
        heights = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int[] leftMax_arr = new int[n];
        int[] rightMax_arr = new int[n];
        
        leftMax_arr[0] = heights[0];
        for (int i = 1; i < n; i++) {
            leftMax_arr[i] = Math.max(leftMax_arr[i - 1], heights[i]);
        }
        
        rightMax_arr[n - 1] = heights[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax_arr[i] = Math.max(rightMax_arr[i + 1], heights[i]);
        }
        
        System.out.println(String.format("%-6s | %-8s | %-8s | %-8s | %-12s | %-10s", 
            "Index", "Height", "LeftMax", "RightMax", "WaterLevel", "Water"));
        System.out.println("-".repeat(65));
        
        int totalWater = 0;
        for (int i = 0; i < n; i++) {
            int waterLevel = Math.min(leftMax_arr[i], rightMax_arr[i]);
            int waterAtI = waterLevel - heights[i];
            totalWater += waterAtI;
            
            System.out.println(String.format("%-6d | %-8d | %-8d | %-8d | %-12d | %-10d", 
                i, heights[i], leftMax_arr[i], rightMax_arr[i], waterLevel, waterAtI));
        }
        
        System.out.println("\nTotal Water Trapped: " + totalWater);
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("ANOTHER EXAMPLE: [3, 0, 2, 0, 4]");
        System.out.println("-".repeat(60));
        
        heights = new int[]{3, 0, 2, 0, 4};
        int result = trapWater(heights);
        
        System.out.println("\nArray:   " + Arrays.toString(heights));
        System.out.println("Output:  " + result);
        
        System.out.println("\nVisualization:");
        System.out.println("|   |");
        System.out.println("|   |  |");
        System.out.println("|W W|  |");
        System.out.println("|WWW|  |");
        System.out.println("|___|__|");
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("COMPLEXITY COMPARISON:");
        System.out.println("-".repeat(60));
        System.out.println("Two-pointer (Optimal):");
        System.out.println("  Time: O(n) - single pass");
        System.out.println("  Space: O(1) - only pointers");
        
        System.out.println("\nMonotonic Stack:");
        System.out.println("  Time: O(n) - each index pushed/popped once");
        System.out.println("  Space: O(n) - stack storage");
        
        System.out.println("\nPre-compute Max:");
        System.out.println("  Time: O(n) - three passes");
        System.out.println("  Space: O(n) - two arrays");
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("KEY INSIGHTS:");
        System.out.println("-".repeat(60));
        System.out.println("1. Water at position i = min(max_left, max_right) - height[i]");
        System.out.println("2. Water trapped by boundaries from both sides");
        System.out.println("3. Two-pointer: Advance from side with smaller boundary");
        System.out.println("4. Monotonic stack: Process elevation changes in order");
        System.out.println("5. Pre-compute: Forward and backward passes for max values");
    }
}
