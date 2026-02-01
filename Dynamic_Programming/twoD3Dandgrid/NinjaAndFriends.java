package Dynamic_Programming.twoD3Dandgrid;

import java.util.*;

/**
 * =========================================================
 * 3-D DP : NINJA AND HIS FRIENDS (DP-13)
 * =========================================================
 *
 * ---------------------------------------------------------
 * 1. PROBLEM STATEMENT
 * ---------------------------------------------------------
 * You are given an n x m grid representing chocolates.
 *
 * Two ninjas start at:
 *  - Ninja 1 at (0, 0)
 *  - Ninja 2 at (0, m-1)
 *
 * They move from the TOP ROW to the BOTTOM ROW.
 *
 * On each move, both ninjas move from row i to row i+1.
 * Each ninja can move to:
 *  - Down-Left  (j - 1)
 *  - Down       (j)
 *  - Down-Right (j + 1)
 *
 * If both ninjas land on the SAME cell:
 *  - Chocolates are counted ONLY ONCE
 *
 * Task:
 * Find the MAXIMUM chocolates both ninjas can collect.
 *
 * ---------------------------------------------------------
 * 2. WHY 3-D DP?
 * ---------------------------------------------------------
 * State depends on:
 * - Current row (i)
 * - Column of ninja 1 (j1)
 * - Column of ninja 2 (j2)
 *
 * Hence → dp[i][j1][j2]
 *
 * ---------------------------------------------------------
 * 3. DP STATE DEFINITION
 * ---------------------------------------------------------
 * dp[i][j1][j2] = maximum chocolates collectible starting
 *                 from row i when ninja 1 is at j1 and
 *                 ninja 2 is at j2
 *
 * ---------------------------------------------------------
 * 4. RECURRENCE RELATION
 * ---------------------------------------------------------
 * For all moves d1, d2 ∈ {-1, 0, +1}:
 *
 * chocolates =
 *   grid[i][j1] +
 *   (j1 != j2 ? grid[i][j2] : 0)
 *
 * dp[i][j1][j2] =
 *   max over all valid moves:
 *     chocolates + dp[i+1][j1+d1][j2+d2]
 *
 * ---------------------------------------------------------
 * 5. BASE CASE
 * ---------------------------------------------------------
 * Last row (i == n-1):
 *   If j1 == j2:
 *       dp = grid[i][j1]
 *   Else:
 *       dp = grid[i][j1] + grid[i][j2]
 *
 * ---------------------------------------------------------
 * 6. APPROACH USED
 * ---------------------------------------------------------
 * ✔ Tabulation (Bottom-Up 3D DP)
 * ✔ Space Optimized version (2 layers)
 *
 * ---------------------------------------------------------
 * 7. TIME & SPACE COMPLEXITY
 * ---------------------------------------------------------
 * Time Complexity  : O(n × m × m × 9)
 * Space Complexity : O(n × m × m)
 *
 * ---------------------------------------------------------
 * 8. ONE-LINE SUMMARY (INTERVIEW GOLD)
 * ---------------------------------------------------------
 * Track both ninjas simultaneously and explore all
 * combinations of their movements using 3D DP.
 *
 * =========================================================
 * IMPLEMENTATION BELOW
 * =========================================================
 */

@SuppressWarnings("unused")
public class NinjaAndFriends {

    /**
     * TABULATION (3D DP)
     */
    public int maximumChocolates(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        int[][][] dp = new int[n][m][m];

        /**
         * -----------------------------------------------------
         * BASE CASE: LAST ROW
         * -----------------------------------------------------
         */
        for (int j1 = 0; j1 < m; j1++) {
            for (int j2 = 0; j2 < m; j2++) {

                if (j1 == j2) {
                    dp[n - 1][j1][j2] = grid[n - 1][j1];
                } else {
                    dp[n - 1][j1][j2] =
                            grid[n - 1][j1] + grid[n - 1][j2];
                }
            }
        }

        /**
         * -----------------------------------------------------
         * FILL DP FROM BOTTOM TO TOP
         * -----------------------------------------------------
         */
        for (int i = n - 2; i >= 0; i--) {
            for (int j1 = 0; j1 < m; j1++) {
                for (int j2 = 0; j2 < m; j2++) {

                    int max = Integer.MIN_VALUE;

                    // Try all 9 combinations of moves
                    for (int d1 = -1; d1 <= 1; d1++) {
                        for (int d2 = -1; d2 <= 1; d2++) {

                            int nj1 = j1 + d1;
                            int nj2 = j2 + d2;

                            // Boundary check
                            if (nj1 >= 0 && nj1 < m &&
                                nj2 >= 0 && nj2 < m) {

                                int value =
                                        grid[i][j1]
                                      + (j1 != j2 ? grid[i][j2] : 0)
                                      + dp[i + 1][nj1][nj2];

                                max = Math.max(max, value);
                            }
                        }
                    }
                    dp[i][j1][j2] = max;
                }
            }
        }

        // Starting positions: (0,0) and (0,m-1)
        return dp[0][0][m - 1];
    }

    /**
     * ---------------------------------------------------------
     * SPACE OPTIMIZED VERSION
     * ---------------------------------------------------------
     * Uses only two 2D arrays
     * Space Complexity: O(m × m)
     */
    public int maximumChocolatesSpaceOptimized(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        int[][] front = new int[m][m];
        int[][] cur = new int[m][m];

        // Base case: last row
        for (int j1 = 0; j1 < m; j1++) {
            for (int j2 = 0; j2 < m; j2++) {
                if (j1 == j2) {
                    front[j1][j2] = grid[n - 1][j1];
                } else {
                    front[j1][j2] =
                            grid[n - 1][j1] + grid[n - 1][j2];
                }
            }
        }

        // Build upwards
        for (int i = n - 2; i >= 0; i--) {

            for (int j1 = 0; j1 < m; j1++) {
                for (int j2 = 0; j2 < m; j2++) {

                    int max = Integer.MIN_VALUE;

                    for (int d1 = -1; d1 <= 1; d1++) {
                        for (int d2 = -1; d2 <= 1; d2++) {

                            int nj1 = j1 + d1;
                            int nj2 = j2 + d2;

                            if (nj1 >= 0 && nj1 < m &&
                                nj2 >= 0 && nj2 < m) {

                                int val =
                                        grid[i][j1]
                                      + (j1 != j2 ? grid[i][j2] : 0)
                                      + front[nj1][nj2];

                                max = Math.max(max, val);
                            }
                        }
                    }
                    cur[j1][j2] = max;
                }
            }
            front = cur;
        }

        return front[0][m - 1];
    }

    public static void main(String[] args) {

        System.out.println("=== Ninja and His Friends (DP-13) ===\n");

        /*
         * Example:
         *
         * Grid:
         * 2 3 1 2
         * 3 4 2 2
         * 5 6 3 5
         *
         * Start:
         * Ninja 1 → (0,0)
         * Ninja 2 → (0,3)
         */

        int[][] grid = {
                {2, 3, 1, 2},
                {3, 4, 2, 2},
                {5, 6, 3, 5}
        };

        NinjaAndFriends sol = new NinjaAndFriends();

        System.out.println("Maximum Chocolates: "
                + sol.maximumChocolates(grid));

        System.out.println("\n" + "=".repeat(60));
        System.out.println("INTERVIEW TAKEAWAYS:");
        System.out.println("✔ Multi-agent DP");
        System.out.println("✔ 3D state reduction to 2 layers");
        System.out.println("✔ Very important advanced DP problem");
    }
}
