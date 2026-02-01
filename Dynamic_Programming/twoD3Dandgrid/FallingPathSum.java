package Dynamic_Programming.twoD3Dandgrid;

import java.util.*;

/**
 * =========================================================
 * MINIMUM / MAXIMUM FALLING PATH SUM (DP-12)
 * =========================================================
 *
 * ---------------------------------------------------------
 * 1. PROBLEM STATEMENT
 * ---------------------------------------------------------
 * You are given an n x n matrix.
 *
 * A FALLING PATH starts at ANY cell in the first row and
 * ends at ANY cell in the last row.
 *
 * From cell (i, j), you can move to:
 *  - Down       → (i + 1, j)
 *  - Down-Left  → (i + 1, j - 1)
 *  - Down-Right → (i + 1, j + 1)
 *
 * Task:
 * 1️⃣ Find the MINIMUM falling path sum
 * 2️⃣ Find the MAXIMUM falling path sum
 *
 * ---------------------------------------------------------
 * 2. OBSERVATIONS
 * ---------------------------------------------------------
 * - Multiple starting points allowed (first row)
 * - Three downward choices per cell
 * - Overlapping subproblems → DP applies
 *
 * ---------------------------------------------------------
 * 3. DP STATE DEFINITION
 * ---------------------------------------------------------
 * dp[i][j] = best (min/max) falling path sum to reach (i,j)
 *
 * ---------------------------------------------------------
 * 4. RECURRENCE RELATION
 * ---------------------------------------------------------
 * For MINIMUM:
 * dp[i][j] = matrix[i][j] + min(
 *      dp[i-1][j],        // up
 *      dp[i-1][j-1],      // up-left
 *      dp[i-1][j+1]       // up-right
 * )
 *
 * For MAXIMUM:
 * dp[i][j] = matrix[i][j] + max(
 *      dp[i-1][j],
 *      dp[i-1][j-1],
 *      dp[i-1][j+1]
 * )
 *
 * ---------------------------------------------------------
 * 5. BASE CASE
 * ---------------------------------------------------------
 * First row:
 * dp[0][j] = matrix[0][j]
 *
 * ---------------------------------------------------------
 * 6. APPROACH USED
 * ---------------------------------------------------------
 * ✔ Tabulation (Top-Down DP)
 * ✔ Space Optimized version included
 *
 * ---------------------------------------------------------
 * 7. TIME & SPACE COMPLEXITY
 * ---------------------------------------------------------
 * Time Complexity  : O(n²)
 * Space Complexity : O(n²)
 * (Optimizable to O(n))
 *
 * ---------------------------------------------------------
 * 8. ONE-LINE SUMMARY (INTERVIEW GOLD)
 * ---------------------------------------------------------
 * At each cell, pick the best among the three possible
 * downward transitions.
 *
 * =========================================================
 * IMPLEMENTATION BELOW
 * =========================================================
 */

public class FallingPathSum {

    /**
     * MINIMUM FALLING PATH SUM
     */
    public int minFallingPathSum(int[][] matrix) {

        int n = matrix.length;
        int[][] dp = new int[n][n];

        /**
         * -----------------------------------------------------
         * BASE CASE: FIRST ROW
         * -----------------------------------------------------
         */
        for (int j = 0; j < n; j++) {
            dp[0][j] = matrix[0][j];
        }

        /**
         * -----------------------------------------------------
         * FILL DP TABLE (TOP TO BOTTOM)
         * -----------------------------------------------------
         */
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {

                int up = dp[i - 1][j];
                int upLeft = (j > 0) ? dp[i - 1][j - 1] : Integer.MAX_VALUE;
                int upRight = (j < n - 1) ? dp[i - 1][j + 1] : Integer.MAX_VALUE;

                dp[i][j] =
                        matrix[i][j]
                      + Math.min(up, Math.min(upLeft, upRight));
            }
        }

        /**
         * -----------------------------------------------------
         * RESULT: MIN VALUE IN LAST ROW
         * -----------------------------------------------------
         */
        int ans = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++) {
            ans = Math.min(ans, dp[n - 1][j]);
        }

        return ans;
    }

    /**
     * MAXIMUM FALLING PATH SUM
     */
    public int maxFallingPathSum(int[][] matrix) {

        int n = matrix.length;
        int[][] dp = new int[n][n];

        /**
         * BASE CASE
         */
        for (int j = 0; j < n; j++) {
            dp[0][j] = matrix[0][j];
        }

        /**
         * FILL DP TABLE
         */
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {

                int up = dp[i - 1][j];
                int upLeft = (j > 0) ? dp[i - 1][j - 1] : Integer.MIN_VALUE;
                int upRight = (j < n - 1) ? dp[i - 1][j + 1] : Integer.MIN_VALUE;

                dp[i][j] =
                        matrix[i][j]
                      + Math.max(up, Math.max(upLeft, upRight));
            }
        }

        /**
         * RESULT: MAX VALUE IN LAST ROW
         */
        int ans = Integer.MIN_VALUE;
        for (int j = 0; j < n; j++) {
            ans = Math.max(ans, dp[n - 1][j]);
        }

        return ans;
    }

    /**
     * ---------------------------------------------------------
     * SPACE OPTIMIZED VERSION (MINIMUM)
     * ---------------------------------------------------------
     * Space Complexity: O(n)
     */
    public int minFallingPathSumSpaceOptimized(int[][] matrix) {

        int n = matrix.length;
        int[] prev = Arrays.copyOf(matrix[0], n);

        for (int i = 1; i < n; i++) {
            int[] cur = new int[n];

            for (int j = 0; j < n; j++) {

                int up = prev[j];
                int upLeft = (j > 0) ? prev[j - 1] : Integer.MAX_VALUE;
                int upRight = (j < n - 1) ? prev[j + 1] : Integer.MAX_VALUE;

                cur[j] =
                        matrix[i][j]
                      + Math.min(up, Math.min(upLeft, upRight));
            }
            prev = cur;
        }

        int ans = Integer.MAX_VALUE;
        for (int val : prev) ans = Math.min(ans, val);

        return ans;
    }

    public static void main(String[] args) {

        System.out.println("=== Falling Path Sum (DP-12) ===\n");

        /*
         * Example:
         *
         * Matrix:
         * 2 1 3
         * 6 5 4
         * 7 8 9
         *
         * Min Path = 1 → 5 → 7 = 13
         * Max Path = 3 → 6 → 9 = 18
         */

        int[][] matrix = {
                {2, 1, 3},
                {6, 5, 4},
                {7, 8, 9}
        };

        FallingPathSum sol = new FallingPathSum();

        System.out.println("Minimum Falling Path Sum: "
                + sol.minFallingPathSum(matrix));

        System.out.println("Maximum Falling Path Sum: "
                + sol.maxFallingPathSum(matrix));

        System.out.println("\n" + "=".repeat(60));
        System.out.println("INTERVIEW TAKEAWAYS:");
        System.out.println("✔ Multiple starting points");
        System.out.println("✔ Three-direction transitions");
        System.out.println("✔ Matrix DP classic");
    }
}
