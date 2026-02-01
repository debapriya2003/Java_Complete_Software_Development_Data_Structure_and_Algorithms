package Dynamic_Programming.twoD3Dandgrid;

import java.util.*;

/**
 * =========================================================
 * GRID UNIQUE PATHS II (DP-9)
 * =========================================================
 *
 * ---------------------------------------------------------
 * 1. PROBLEM STATEMENT
 * ---------------------------------------------------------
 * You are given an m x n grid where:
 *  - 0 → free cell
 *  - 1 → obstacle
 *
 * You start from the TOP-LEFT cell (0,0)
 * You want to reach the BOTTOM-RIGHT cell (m-1,n-1)
 *
 * Allowed moves:
 *  - Right (→)
 *  - Down  (↓)
 *
 * Task:
 * Find the NUMBER of UNIQUE PATHS avoiding obstacles.
 *
 * ---------------------------------------------------------
 * 2. OBSERVATIONS
 * ---------------------------------------------------------
 * - Same as Grid Unique Paths (DP-8)
 * - Additional constraint: obstacles block paths
 * - If start or end is blocked → answer = 0
 *
 * ---------------------------------------------------------
 * 3. DP STATE DEFINITION
 * ---------------------------------------------------------
 * dp[i][j] = number of unique paths to reach cell (i,j)
 *            considering obstacles
 *
 * ---------------------------------------------------------
 * 4. RECURRENCE RELATION
 * ---------------------------------------------------------
 * If grid[i][j] == 1:
 *     dp[i][j] = 0
 *
 * Else:
 *     dp[i][j] = dp[i-1][j] + dp[i][j-1]
 *
 * ---------------------------------------------------------
 * 5. BASE CASES
 * ---------------------------------------------------------
 * - dp[0][0] = 1 if grid[0][0] == 0
 * - First row / first column must stop at obstacle
 *
 * ---------------------------------------------------------
 * 6. APPROACH USED
 * ---------------------------------------------------------
 * ✔ Tabulation (Bottom-Up DP)
 * ✔ Space Optimized version also shown
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
 * Same as unique paths, but paths stop when obstacles occur.
 *
 * =========================================================
 * IMPLEMENTATION BELOW
 * =========================================================
 */

@SuppressWarnings("unused")
public class GridUniquePathsII {

    /**
     * Returns number of unique paths avoiding obstacles
     *
     * @param obstacleGrid grid with obstacles (1 = blocked)
     * @return number of unique paths
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        /**
         * dp[i][j] → number of ways to reach (i,j)
         */
        int[][] dp = new int[m][n];

        /**
         * -----------------------------------------------------
         * BASE CASE: START CELL
         * -----------------------------------------------------
         */
        if (obstacleGrid[0][0] == 1) return 0;
        dp[0][0] = 1;

        /**
         * -----------------------------------------------------
         * INITIALIZE FIRST ROW
         * -----------------------------------------------------
         */
        for (int j = 1; j < n; j++) {
            if (obstacleGrid[0][j] == 1)
                dp[0][j] = 0;
            else
                dp[0][j] = dp[0][j - 1];
        }

        /**
         * -----------------------------------------------------
         * INITIALIZE FIRST COLUMN
         * -----------------------------------------------------
         */
        for (int i = 1; i < m; i++) {
            if (obstacleGrid[i][0] == 1)
                dp[i][0] = 0;
            else
                dp[i][0] = dp[i - 1][0];
        }

        /**
         * -----------------------------------------------------
         * FILL DP TABLE
         * -----------------------------------------------------
         */
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {

                if (obstacleGrid[i][j] == 1) {
                    dp[i][j] = 0;  // blocked cell
                } else {
                    dp[i][j] =
                            dp[i - 1][j]   // from top
                          + dp[i][j - 1];  // from left
                }
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
    public int uniquePathsWithObstaclesSpaceOptimized(int[][] obstacleGrid) {

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        int[] dp = new int[n];

        dp[0] = obstacleGrid[0][0] == 1 ? 0 : 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0;  // reset paths at obstacle
                } else if (j > 0) {
                    dp[j] = dp[j] + dp[j - 1];
                }
            }
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {

        System.out.println("=== Grid Unique Paths II (DP-9) ===\n");

        /*
         * Example:
         *
         * Grid:
         * 0 0 0
         * 0 1 0
         * 0 0 0
         *
         * Output = 2
         */

        GridUniquePathsII sol = new GridUniquePathsII();

        int[][] obstacleGrid = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };

        System.out.println("Unique Paths: "
                + sol.uniquePathsWithObstacles(obstacleGrid));

        System.out.println("\n" + "=".repeat(60));
        System.out.println("INTERVIEW TAKEAWAYS:");
        System.out.println("✔ Obstacle cells reset paths to zero");
        System.out.println("✔ Same transition as DP-8");
        System.out.println("✔ Edge cases at first row & column");
    }
}
