package stacks_queues.monotonic_stack;

import java.util.*;

/**
 * Maximal Rectangles (Maximal Rectangle in Binary Matrix)
 * 
 * Problem: Given a 2D binary matrix, find area of largest rectangle containing only 1s.
 * 
 * Example:
 * [["1","0","1","0","0"],
 *  ["1","0","1","1","1"],
 *  ["1","1","1","1","1"],
 *  ["1","0","0","1","0"]]
 * 
 * Visualization:
 * 1 . 1 . .
 * 1 . 1 1 1
 * 1 1 1 1 1
 * 1 . . 1 .
 * 
 * Largest rectangle at bottom: 1×5 = 5 area
 * Or: 3×3 = 9 area (rows 0-2, cols 0,2,3,4)? No, col 0,1 don't align
 * Best: rows 1-2, cols 2-4: 2×3 = 6 area
 * 
 * Algorithm: Histogram Approach
 * ==============================
 * 1. For each row, build histogram of heights
 *    - If matrix[i][j] == '1': height[j] += 1
 *    - If matrix[i][j] == '0': height[j] = 0
 * 2. For each row, find largest rectangle in histogram
 * 3. Return maximum across all rows
 * 
 * Time Complexity: O(m × n) where m = rows, n = cols
 * Space Complexity: O(n) for histogram
 */

public class MaximalRectangles {
    
    /**
     * Find maximal rectangle in binary matrix
     */
    public static int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] heights = new int[cols];
        int maxArea = 0;
        
        for (int i = 0; i < rows; i++) {
            // Update heights
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == '1') {
                    heights[j]++;
                } else {
                    heights[j] = 0;
                }
            }
            
            // Find largest rectangle in current histogram
            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }
        
        return maxArea;
    }
    
    /**
     * Largest rectangle in histogram (helper)
     */
    private static int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;
        
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                int h = heights[stack.pop()];
                int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, h * w);
            }
            stack.push(i);
        }
        
        while (!stack.isEmpty()) {
            int h = heights[stack.pop()];
            int w = stack.isEmpty() ? heights.length : heights.length - stack.peek() - 1;
            maxArea = Math.max(maxArea, h * w);
        }
        
        return maxArea;
    }
    
    public static void main(String[] args) {
        System.out.println("=== Maximal Rectangles ===\n");
        
        char[][][] testCases = {
            {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
            },
            {
                {'0'}
            },
            {
                {'1'}
            },
            {
                {'1', '1'},
                {'1', '1'}
            },
            {
                {'1', '1', '1', '1', '1'},
                {'0', '1', '1', '1', '1'},
                {'0', '1', '1', '1', '1'}
            }
        };
        
        System.out.println("Test Cases:\n");
        
        for (int t = 0; t < testCases.length; t++) {
            char[][] matrix = testCases[t];
            int result = maximalRectangle(matrix);
            
            System.out.println("Test Case " + (t + 1) + ":");
            printMatrix(matrix);
            System.out.println("Max Area: " + result);
            System.out.println();
        }
        
        System.out.println("=".repeat(70));
        System.out.println("DETAILED EXAMPLE:");
        System.out.println("-".repeat(70));
        
        char[][] matrix = {
            {'1', '0', '1', '0', '0'},
            {'1', '0', '1', '1', '1'},
            {'1', '1', '1', '1', '1'},
            {'1', '0', '0', '1', '0'}
        };
        
        System.out.println("\nMatrix:");
        printMatrix(matrix);
        
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] heights = new int[cols];
        int maxArea = 0;
        
        System.out.println("\nProcessing row by row:\n");
        
        for (int i = 0; i < rows; i++) {
            System.out.println("Row " + i + ":");
            
            // Update heights
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == '1') {
                    heights[j]++;
                } else {
                    heights[j] = 0;
                }
            }
            
            System.out.println("  Matrix row: " + Arrays.toString(matrix[i]));
            System.out.println("  Heights: " + Arrays.toString(heights));
            
            // Find largest rectangle in current histogram
            int area = largestRectangleArea(heights);
            System.out.println("  Largest rect in histogram: " + area);
            
            maxArea = Math.max(maxArea, area);
            System.out.println("  Max so far: " + maxArea);
            System.out.println();
        }
        
        System.out.println("Final Max Area: " + maxArea);
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("DETAILED TRACE FOR ROW 2:");
        System.out.println("-".repeat(70));
        
        System.out.println("\nAfter row 2, heights = [3, 2, 3, 3, 3]");
        System.out.println("\nFinding largest rectangle in histogram [3, 2, 3, 3, 3]:\n");
        
        int[] hist = {3, 2, 3, 3, 3};
        Stack<Integer> stack = new Stack<>();
        maxArea = 0;
        
        System.out.println(String.format("%-10s | %-15s | %-20s | %-15s", 
            "Index", "Height", "Stack", "Max Area"));
        System.out.println("-".repeat(65));
        
        for (int i = 0; i < hist.length; i++) {
            System.out.print(String.format("%-10d | %-15d | ", i, hist[i]));
            
            while (!stack.isEmpty() && hist[stack.peek()] > hist[i]) {
                int h = hist[stack.pop()];
                int w = stack.isEmpty() ? i : i - stack.peek() - 1;
                int area = h * w;
                maxArea = Math.max(maxArea, area);
            }
            
            stack.push(i);
            
            System.out.println(String.format("%-20s | %-15d", 
                new ArrayList<>(stack), maxArea));
        }
        
        System.out.println("\nPopping remaining:");
        while (!stack.isEmpty()) {
            int idx = stack.pop();
            int h = hist[idx];
            int w = stack.isEmpty() ? hist.length : hist.length - stack.peek() - 1;
            int area = h * w;
            maxArea = Math.max(maxArea, area);
            System.out.println("Index " + idx + ": h=" + h + ", w=" + w + ", area=" + area);
        }
        
        System.out.println("\nMax area from row 2: " + maxArea);
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("ALGORITHM EXPLANATION:");
        System.out.println("-".repeat(70));
        System.out.println("1. Treat each row as base of histogram");
        System.out.println("   - Height increases if current cell is '1'");
        System.out.println("   - Height resets to 0 if current cell is '0'");
        System.out.println("2. For each row, find max rectangle in histogram");
        System.out.println("3. Use monotonic stack for histogram (O(n) per row)");
        System.out.println("4. Track maximum area across all rows");
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("HEIGHT BUILDING EXAMPLE:");
        System.out.println("-".repeat(70));
        
        matrix = new char[][]{
            {'1', '1', '1'},
            {'1', '1', '0'},
            {'1', '1', '1'}
        };
        
        System.out.println("\nMatrix:");
        printMatrix(matrix);
        
        System.out.println("\nHeight updates:");
        int[] h = new int[3];
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    h[j]++;
                } else {
                    h[j] = 0;
                }
            }
            System.out.println("After row " + i + ": heights = " + Arrays.toString(h));
        }
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("KEY INSIGHTS:");
        System.out.println("-".repeat(70));
        System.out.println("1. Transform 2D problem to multiple 1D histogram problems");
        System.out.println("2. Heights represent consecutive 1s above current row");
        System.out.println("3. Each histogram row is independent problem");
        System.out.println("4. Use largest rectangle in histogram for each row");
        System.out.println("5. Time: O(m×n) - m rows, n columns, O(n) per histogram");
        System.out.println("6. Space: O(n) - single height array");
    }
    
    private static void printMatrix(char[][] matrix) {
        System.out.println();
        for (char[] row : matrix) {
            System.out.print("  ");
            for (char c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
