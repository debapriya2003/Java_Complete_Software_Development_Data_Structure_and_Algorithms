package stacks_queues.monotonic_stack;

import java.util.*;

/**
 * Largest Rectangle in Histogram
 * 
 * Problem: Given array of heights (histogram bars), find area of largest rectangle.
 * 
 * Example: heights = [2, 1, 5, 6, 2, 3]
 * 
 * Visualization:
 *     |   ||
 *   | ||  ||
 *   |||   |||
 *   ||||  |||
 * __|__|__|__|___
 * [2, 1, 5, 6, 2, 3]
 * 
 * Largest rectangle: height 5, width 2 → area = 10
 * Or: height 2, width 6 → area = 12? No, bounded by 2
 * 
 * Algorithm: Monotonic Stack (Optimal)
 * ======================================
 * 1. Maintain increasing stack of indices
 * 2. For each bar:
 *    - If height >= stack top: push
 *    - If height < stack top:
 *      - Pop and calculate area
 *      - Area = height[popped] * (width)
 *      - Width = current - stack.peek() - 1
 *      - Height extends from stack.peek() to current
 * 3. Pop remaining and calculate
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

public class LargestRectangleInHistogram {
    
    /**
     * Find largest rectangle area using monotonic stack
     */
    public static int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        int n = heights.length;
        
        for (int i = 0; i < n; i++) {
            // Pop bars taller than current and calculate area
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                int h = heights[stack.pop()];
                int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, h * w);
            }
            stack.push(i);
        }
        
        // Pop remaining bars
        while (!stack.isEmpty()) {
            int h = heights[stack.pop()];
            int w = stack.isEmpty() ? n : n - stack.peek() - 1;
            maxArea = Math.max(maxArea, h * w);
        }
        
        return maxArea;
    }
    
    public static void main(String[] args) {
        System.out.println("=== Largest Rectangle in Histogram ===\n");
        
        int[][] testCases = {
            {2, 1, 5, 6, 2, 3},
            {2, 4},
            {2, 1, 2},
            {1, 1, 1, 1},
            {1, 2, 2, 3, 2}
        };
        
        System.out.println(String.format("%-35s | %-15s", "Heights", "Max Area"));
        System.out.println("-".repeat(55));
        
        for (int[] heights : testCases) {
            int result = largestRectangleArea(heights);
            System.out.println(String.format("%-35s | %-15d", 
                Arrays.toString(heights), result));
        }
        
        System.out.println("\n" + "=".repeat(55));
        System.out.println("DETAILED EXAMPLE: [2, 1, 5, 6, 2, 3]");
        System.out.println("-".repeat(55));
        
        int[] heights = {2, 1, 5, 6, 2, 3};
        int n = heights.length;
        
        System.out.println("\nVisualization:");
        System.out.println("        |   ||");
        System.out.println("      | ||  ||");
        System.out.println("      |||   |||");
        System.out.println("      ||||  |||");
        System.out.println("  ____|__|__|__|___");
        System.out.println("  " + Arrays.toString(heights));
        System.out.println("  Index: 0  1  2  3  4  5");
        
        System.out.println("\n\nProcessing with Monotonic Stack:\n");
        
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        
        System.out.println(String.format("%-8s | %-15s | %-15s | %-15s | %-20s", 
            "Index", "Height", "Stack", "Area Calc", "Max Area"));
        System.out.println("-".repeat(85));
        
        for (int i = 0; i < n; i++) {
            System.out.print(String.format("%-8d | %-15d | ", i, heights[i]));
            
            String areaCalcs = "";
            
            // Pop bars taller than current
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                int idx = stack.pop();
                int h = heights[idx];
                int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                int area = h * w;
                maxArea = Math.max(maxArea, area);
                areaCalcs += "h=" + h + ",w=" + w + ",a=" + area + "; ";
            }
            
            stack.push(i);
            
            List<Integer> stackList = new ArrayList<>(stack);
            System.out.print(String.format("%-15s | %-15s | %-20d\n", 
                stackList, areaCalcs.isEmpty() ? "-" : areaCalcs, maxArea));
        }
        
        System.out.println("\nPopping remaining from stack:\n");
        
        while (!stack.isEmpty()) {
            int idx = stack.pop();
            int h = heights[idx];
            int w = stack.isEmpty() ? n : n - stack.peek() - 1;
            int area = h * w;
            maxArea = Math.max(maxArea, area);
            
            System.out.println("Index " + idx + ": h=" + h + ", w=" + w + ", area=" + area);
        }
        
        System.out.println("\nMax Area: " + maxArea);
        
        System.out.println("\n" + "=".repeat(55));
        System.out.println("DETAILED BREAKDOWN: [2, 1, 5, 6, 2, 3]");
        System.out.println("-".repeat(55));
        
        System.out.println("\nStep-by-step with detailed explanation:\n");
        
        heights = new int[]{2, 1, 5, 6, 2, 3};
        stack = new Stack<>();
        maxArea = 0;
        
        for (int i = 0; i < n; i++) {
            System.out.println("Step " + (i + 1) + ": Index " + i + ", Height " + heights[i]);
            System.out.println("  Stack before: " + stack);
            
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                int idx = stack.pop();
                int h = heights[idx];
                int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                int area = h * w;
                
                System.out.println("  Pop index " + idx + " (height " + h + ")");
                System.out.println("    Width = " + w + " (current=" + i + ", prev=" + (stack.isEmpty() ? "start" : stack.peek()) + ")");
                System.out.println("    Area = " + h + " × " + w + " = " + area);
                
                maxArea = Math.max(maxArea, area);
            }
            
            stack.push(i);
            System.out.println("  Push index " + i);
            System.out.println("  Stack after: " + stack);
            System.out.println("  Max area so far: " + maxArea);
            System.out.println();
        }
        
        System.out.println("Processing remaining bars in stack:\n");
        
        while (!stack.isEmpty()) {
            int idx = stack.pop();
            int h = heights[idx];
            int w = stack.isEmpty() ? n : n - stack.peek() - 1;
            int area = h * w;
            
            System.out.println("Index " + idx + " (height " + h + "):");
            System.out.println("  Width = " + w + " (end=" + n + ", prev=" + (stack.isEmpty() ? "start" : stack.peek()) + ")");
            System.out.println("  Area = " + h + " × " + w + " = " + area);
            
            maxArea = Math.max(maxArea, area);
        }
        
        System.out.println("\nFinal Max Area: " + maxArea);
        
        System.out.println("\n" + "=".repeat(55));
        System.out.println("ANOTHER EXAMPLE: [2, 4]");
        System.out.println("-".repeat(55));
        
        heights = new int[]{2, 4};
        int result = largestRectangleArea(heights);
        
        System.out.println("\nArray: " + Arrays.toString(heights));
        System.out.println("Result: " + result);
        
        System.out.println("\nPossible rectangles:");
        System.out.println("Height 2: width 2 → area = 4");
        System.out.println("Height 4: width 1 → area = 4");
        System.out.println("Max: 4");
        
        System.out.println("\n" + "=".repeat(55));
        System.out.println("ALGORITHM EXPLANATION:");
        System.out.println("-".repeat(55));
        System.out.println("1. Stack stores indices in increasing order of heights");
        System.out.println("2. For each new bar:");
        System.out.println("   - If taller: push its index");
        System.out.println("   - If shorter: pop taller bars and calculate areas");
        System.out.println("3. Area calculation:");
        System.out.println("   - Height = popped bar's height");
        System.out.println("   - Width = current index - previous index - 1");
        System.out.println("4. After processing all: pop remaining and calculate");
        System.out.println("5. Time: O(n) - each bar pushed/popped once");
        
        System.out.println("\n" + "=".repeat(55));
        System.out.println("WIDTH CALCULATION:");
        System.out.println("-".repeat(55));
        System.out.println("When popping at index i with stack top j:");
        System.out.println("• Current height < stack top height");
        System.out.println("• Stack top bar can't extend past index i");
        System.out.println("• Stack top bar can't extend before index j (next in stack)");
        System.out.println("• Width = i - j - 1");
        System.out.println("• If stack empty: width = i (extends from start)");
    }
}
