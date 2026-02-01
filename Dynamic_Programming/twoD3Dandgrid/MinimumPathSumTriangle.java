package Dynamic_Programming.twoD3Dandgrid;

import java.util.*;

/**
 * =========================================================
 * MINIMUM PATH SUM IN TRIANGULAR GRID (DP-11)
 * =========================================================
 *
 * ---------------------------------------------------------
 * 1. PROBLEM STATEMENT
 * ---------------------------------------------------------
 * You are given a TRIANGULAR array (list of lists) where:
 * - Row 0 has 1 element
 * - Row 1 has 2 elements
 * - Row i has (i + 1) elements
 *
 * You start from the TOP element (row 0, col 0).
 * At each step, you may move to:
 *  - Down      → (i+1, j)
 *  - Down-Right→ (i+1, j+1)
 *
 * Task:
 * Find the MINIMUM path sum from top to bottom.
 *
 * ---------------------------------------------------------
 * 2. OBSERVATIONS
 * ---------------------------------------------------------
 * - Movement restricted to two downward directions
 * - Greedy choice may fail → DP required
 * - Subproblem: minimum cost to reach bottom from (i,j)
 *
 * ---------------------------------------------------------
 * 3. DP STATE DEFINITION
 * ---------------------------------------------------------
 * dp[i][j] = minimum path sum from position (i,j) to bottom
 *
 * ---------------------------------------------------------
 * 4. RECURRENCE RELATION
 * ---------------------------------------------------------
 * dp[i][j] = triangle[i][j] +
 *            min(
 *                dp[i+1][j],      // down
 *                dp[i+1][j+1]     // down-right
 *            )
 *
 * ---------------------------------------------------------
 * 5. BASE CASE
 * ---------------------------------------------------------
 * Last row:
 * dp[n-1][j] = triangle[n-1][j]
 *
 * ---------------------------------------------------------
 * 6. APPROACH USED
 * ---------------------------------------------------------
 * ✔ Bottom-Up DP (Tabulation)
 * ✔ Space Optimized approach included
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
 * From each cell, choose the minimum of the two downward
 * paths to reach the bottom with least cost.
 *
 * =========================================================
 * IMPLEMENTATION BELOW
 * =========================================================
 */

public class MinimumPathSumTriangle {

    /**
     * TABULATION APPROACH (BOTTOM-UP)
     */
    public int minimumTotal(List<List<Integer>> triangle) {

        int n = triangle.size();

        /**
         * dp[i][j] → minimum path sum from (i,j) to bottom
         */
        int[][] dp = new int[n][n];

        /**
         * -----------------------------------------------------
         * BASE CASE: LAST ROW
         * -----------------------------------------------------
         */
        for (int j = 0; j < n; j++) {
            dp[n - 1][j] = triangle.get(n - 1).get(j);
        }

        /**
         * -----------------------------------------------------
         * BUILD DP FROM BOTTOM TO TOP
         * -----------------------------------------------------
         */
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {

                dp[i][j] =
                        triangle.get(i).get(j)
                      + Math.min(dp[i + 1][j], dp[i + 1][j + 1]);
            }
        }

        // Top element holds the answer
        return dp[0][0];
    }

    /**
     * ---------------------------------------------------------
     * SPACE OPTIMIZED VERSION
     * ---------------------------------------------------------
     * Space Complexity: O(n)
     */
    public int minimumTotalSpaceOptimized(List<List<Integer>> triangle) {

        int n = triangle.size();

        int[] dp = new int[n];

        // Initialize with last row
        for (int j = 0; j < n; j++) {
            dp[j] = triangle.get(n - 1).get(j);
        }

        // Build upwards
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {

                dp[j] =
                        triangle.get(i).get(j)
                      + Math.min(dp[j], dp[j + 1]);
            }
        }

        return dp[0];
    }

    public static void main(String[] args) {

        System.out.println("=== Minimum Path Sum in Triangle (DP-11) ===\n");

        /*
         * Example:
         *
         * Triangle:
         *       2
         *      3 4
         *     6 5 7
         *    4 1 8 3
         *
         * Minimum Path:
         * 2 → 3 → 5 → 1
         *
         * Output = 11
         */

        List<List<Integer>> triangle = new ArrayList<>();
        triangle.add(Arrays.asList(2));
        triangle.add(Arrays.asList(3, 4));
        triangle.add(Arrays.asList(6, 5, 7));
        triangle.add(Arrays.asList(4, 1, 8, 3));

        MinimumPathSumTriangle sol = new MinimumPathSumTriangle();

        System.out.println("Minimum Path Sum: "
                + sol.minimumTotal(triangle));

        System.out.println("\n" + "=".repeat(60));
        System.out.println("INTERVIEW TAKEAWAYS:");
        System.out.println("✔ Bottom-up DP is most intuitive");
        System.out.println("✔ Space optimized solution preferred");
        System.out.println("✔ Classic triangle DP problem");
    }
}
