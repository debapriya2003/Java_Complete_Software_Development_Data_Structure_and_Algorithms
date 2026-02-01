package Dynamic_Programming.twoD3Dandgrid;

import java.util.*;

/**
 * =========================================================
 * GRID UNIQUE PATHS (DP-8)
 * =========================================================
 *
 * ---------------------------------------------------------
 * 1. PROBLEM STATEMENT
 * ---------------------------------------------------------
 * You are given an m x n grid.
 * You start from the TOP-LEFT cell (0,0).
 * You want to reach the BOTTOM-RIGHT cell (m-1,n-1).
 *
 * You are allowed to move ONLY:
 *  - Right (→)
 *  - Down  (↓)
 *
 * Task:
 * Find the NUMBER of UNIQUE paths from start to destination.
 *
 * ---------------------------------------------------------
 * 2. OBSERVATIONS
 * ---------------------------------------------------------
 * - Movement is restricted (Right / Down)
 * - Each cell depends on:
 *      → top cell
 *      → left cell
 * - Overlapping subproblems → DP applies
 *
 * ---------------------------------------------------------
 * 3. DP STATE DEFINITION
 * ---------------------------------------------------------
 * dp[i][j] = number of unique paths to reach cell (i,j)
 *
 * ---------------------------------------------------------
 * 4. RECURRENCE RELATION
 * ---------------------------------------------------------
 * dp[i][j] = dp[i-1][j] + dp[i][j-1]
 *
 * Because:
 * - You can reach (i,j) either from TOP or LEFT
 *
 * ---------------------------------------------------------
 * 5. BASE CASES
 * ---------------------------------------------------------
 * dp[0][0] = 1   (starting position)
 *
 * First row (i=0):
 * - Only way is moving RIGHT
 *
 * First column (j=0):
 * - Only way is moving DOWN
 *
 * ---------------------------------------------------------
 * 6. APPROACHES
 * ---------------------------------------------------------
 * 1️⃣ Recursion (inefficient)
 * 2️⃣ Memoization (Top-down DP)
 * 3️⃣ Tabulation (Bottom-up DP) ← USED HERE
 * 4️⃣ Space Optimized DP
 *
 * ---------------------------------------------------------
 * 7. TIME & SPACE COMPLEXITY
 * ---------------------------------------------------------
 * Time Complexity  : O(m × n)
 * Space Complexity : O(m × n)
 * (Can be optimized to O(n))
 *
 * ---------------------------------------------------------
 * 8. ONE-LINE SUMMARY (INTERVIEW GOLD)
 * ---------------------------------------------------------
 * Number of unique paths to a cell is the sum of paths from
 * the top and left cells.
 *
 * =========================================================
 * IMPLEMENTATION BELOW
 * =========================================================
 */

public class GridUniquePaths {

    /**
     * Returns number of unique paths in an m x n grid
     *
     * @param m number of rows
     * @param n number of columns
     * @return number of unique paths
     */
    public int uniquePaths(int m, int n) {

        /**
         * dp[i][j] → number of ways to reach cell (i,j)
         */
        int[][] dp = new int[m][n];

        /**
         * -----------------------------------------------------
         * BASE CASE INITIALIZATION
         * -----------------------------------------------------
         */
        // First row
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }

        // First column
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }

        /**
         * -----------------------------------------------------
         * FILL THE DP TABLE
         * -----------------------------------------------------
         */
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {

                dp[i][j] =
                        dp[i - 1][j]   // from top
                      + dp[i][j - 1];  // from left
            }
        }

        // Destination cell contains the answer
        return dp[m - 1][n - 1];
    }

    /**
     * SPACE OPTIMIZED VERSION (OPTIONAL)
     * Space Complexity: O(n)
     */
    public int uniquePathsSpaceOptimized(int m, int n) {

        int[] prev = new int[n];
        Arrays.fill(prev, 1);

        for (int i = 1; i < m; i++) {
            int[] cur = new int[n];
            cur[0] = 1;

            for (int j = 1; j < n; j++) {
                cur[j] = cur[j - 1] + prev[j];
            }
            prev = cur;
        }
        return prev[n - 1];
    }

    public static void main(String[] args) {

        System.out.println("=== Grid Unique Paths (DP-8) ===\n");

        /*
         * Example:
         *
         * Grid: 3 x 3
         *
         * Paths:
         * R R D D
         * R D R D
         * R D D R
         * D R R D
         * D R D R
         * D D R R
         *
         * Answer = 6
         */

        GridUniquePaths sol = new GridUniquePaths();

        System.out.println("Unique Paths (3x3): "
                + sol.uniquePaths(3, 3));

        System.out.println("\n" + "=".repeat(60));
        System.out.println("INTERVIEW TAKEAWAYS:");
        System.out.println("✔ DP on grids");
        System.out.println("✔ Depends on top & left");
        System.out.println("✔ Classic combinatorics + DP problem");
    }
}
