package Dynamic_Programming.twoD3Dandgrid;

import java.util.*;

/**
 * =========================================================
 * MINIMUM PATH SUM IN GRID (DP-10)
 * =========================================================
 *
 * ---------------------------------------------------------
 * 1. PROBLEM STATEMENT
 * ---------------------------------------------------------
 * You are given an m x n grid filled with NON-NEGATIVE integers.
 *
 * You start from the TOP-LEFT cell (0,0)
 * You want to reach the BOTTOM-RIGHT cell (m-1,n-1)
 *
 * Allowed moves:
 *  - Right (→)
 *  - Down  (↓)
 *
 * Cost of a path = sum of all values on the path
 *
 * Task:
 * Find the MINIMUM path sum from start to destination.
 *
 * ---------------------------------------------------------
 * 2. OBSERVATIONS
 * ---------------------------------------------------------
 * - Movement restricted to Right / Down
 * - Each cell cost must be added
 * - Greedy fails → DP needed
 *
 * ---------------------------------------------------------
 * 3. DP STATE DEFINITION
 * ---------------------------------------------------------
 * dp[i][j] = minimum path sum to reach cell (i,j)
 *
 * ---------------------------------------------------------
 * 4. RECURRENCE RELATION
 * ---------------------------------------------------------
 * dp[i][j] = grid[i][j] + min(
 *              dp[i-1][j],   // from top
 *              dp[i][j-1]    // from left
 *           )
 *
 * ---------------------------------------------------------
 * 5. BASE CASES
 * ---------------------------------------------------------
 * dp[0][0] = grid[0][0]
 *
 * First row:
 * dp[0][j] = grid[0][j] + dp[0][j-1]
 *
 * First column:
 * dp[i][0] = grid[i][0] + dp[i-1][0]
 *
 * ---------------------------------------------------------
 * 6. APPROACH USED
 * ---------------------------------------------------------
 * ✔ Tabulation (Bottom-Up DP)
 * ✔ Space Optimized version included
 *
 * ---------------------------------------------------------
 * 7. TIME & SPACE COMPLEXITY
 * ---------------------------------------------------------
 * Time Complexity  : O(m × n)
 * Space Complexity : O(m × n)
 * (Optimizable to O(n))
 *
 * ---------------------------------------------------------
 * 8. ONE-LINE SUMMARY (INTERVIEW GOLD)
 * ---------------------------------------------------------
 * To reach a cell with minimum cost, choose the cheaper of
 * coming from top or left.
 *
 * =========================================================
 * IMPLEMENTATION BELOW
 * =========================================================
 */

@SuppressWarnings("unused")
public class MinimumPathSum {

    /**
     * Tabulation approach
     */
    public int minPathSum(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        /**
         * dp[i][j] → minimum path sum to reach (i,j)
         */
        int[][] dp = new int[m][n];

        /**
         * -----------------------------------------------------
         * BASE CASE
         * -----------------------------------------------------
         */
        dp[0][0] = grid[0][0];

        /**
         * Initialize first row
         */
        for (int j = 1; j < n; j++) {
            dp[0][j] = grid[0][j] + dp[0][j - 1];
        }

        /**
         * Initialize first column
         */
        for (int i = 1; i < m; i++) {
            dp[i][0] = grid[i][0] + dp[i - 1][0];
        }

        /**
         * Fill DP table
         */
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {

                dp[i][j] =
                        grid[i][j]
                      + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }

        return dp[m - 1][n - 1];
    }

    /**
     * ---------------------------------------------------------
     * SPACE OPTIMIZED VERSION
     * ---------------------------------------------------------
     * Space Complexity: O(n)
     */
    public int minPathSumSpaceOptimized(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        int[] dp = new int[n];

        dp[0] = grid[0][0];

        // First row
        for (int j = 1; j < n; j++) {
            dp[j] = grid[0][j] + dp[j - 1];
        }

        for (int i = 1; i < m; i++) {
            dp[0] += grid[i][0];  // first column update

            for (int j = 1; j < n; j++) {
                dp[j] =
                        grid[i][j]
                      + Math.min(dp[j], dp[j - 1]);
            }
        }

        return dp[n - 1];
    }

    public static void main(String[] args) {

        System.out.println("=== Minimum Path Sum in Grid (DP-10) ===\n");

        /*
         * Example:
         *
         * Grid:
         * 1 3 1
         * 1 5 1
         * 4 2 1
         *
         * Minimum path:
         * 1 → 1 → 3 → 1 → 1
         *
         * Output = 7
         */

        MinimumPathSum sol = new MinimumPathSum();

        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };

        System.out.println("Minimum Path Sum: "
                + sol.minPathSum(grid));

        System.out.println("\n" + "=".repeat(60));
        System.out.println("INTERVIEW TAKEAWAYS:");
        System.out.println("✔ DP on grids with cost minimization");
        System.out.println("✔ Similar to Unique Paths but with min");
        System.out.println("✔ Frequently asked problem");
    }
}
