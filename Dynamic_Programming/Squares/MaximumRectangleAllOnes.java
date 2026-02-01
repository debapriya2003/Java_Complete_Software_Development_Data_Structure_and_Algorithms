package Dynamic_Programming.Squares;

import java.util.*;

/**
 * MAXIMUM RECTANGLE AREA WITH ALL 1s (DP-55)
 *
 * Problem:
 * Given a binary matrix of 0s and 1s, find the area of the largest rectangle containing only 1s.
 *
 * Approach:
 * Treat every row as the base of a histogram: maintain heights[j] = number of consecutive 1s ending
 * at the current row for column j. For each row compute the largest rectangle area in histogram using
 * a monotonic stack (nearest smaller to left/right). Maximum over all rows is the answer.
 *
 * Complexity: O(rows * cols) time, O(cols) extra space for heights and stack.
 *
 * Example:
 * matrix = [
 *  [1,0,1,0,0],
 *  [1,0,1,1,1],
 *  [1,1,1,1,1],
 *  [1,0,0,1,0]
 * ] -> max area = 6 (3x2 block)
 */
public class MaximumRectangleAllOnes {

    public static int maximalRectangle(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int n = matrix.length, m = matrix[0].length;
        int[] heights = new int[m];
        int maxArea = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 1) heights[j] += 1;
                else heights[j] = 0;
            }
            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }
        return maxArea;
    }

    // Largest rectangle in histogram using monotonic stack (indices of increasing heights)
    private static int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Deque<Integer> stack = new ArrayDeque<>();
        int maxArea = 0;
        for (int i = 0; i <= n; i++) {
            int h = (i == n) ? 0 : heights[i];
            while (!stack.isEmpty() && h < heights[stack.peek()]) {
                int height = heights[stack.pop()];
                int left = stack.isEmpty() ? -1 : stack.peek();
                int width = i - left - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[][] mat = {
            {1,0,1,0,0},
            {1,0,1,1,1},
            {1,1,1,1,1},
            {1,0,0,1,0}
        };
        System.out.println(maximalRectangle(mat)); // expected 6
    }
}
