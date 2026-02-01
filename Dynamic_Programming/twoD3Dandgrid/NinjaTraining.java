package Dynamic_Programming.twoD3Dandgrid;

import java.util.*;

/**
 * =========================================================
 * NINJA'S TRAINING (DP-7)
 * =========================================================
 *
 * ---------------------------------------------------------
 * 1. PROBLEM STATEMENT
 * ---------------------------------------------------------
 * A ninja trains for N days.
 * Every day, the ninja can perform ONE of the following 3 tasks:
 *
 *   Task 0 → Running
 *   Task 1 → Fighting
 *   Task 2 → Learning
 *
 * You are given a 2D array points[n][3], where:
 *   points[day][task] = points gained by doing "task" on "day"
 *
 * CONSTRAINT:
 * - The ninja CANNOT perform the SAME task on two consecutive days.
 *
 * Task:
 * Find the MAXIMUM points the ninja can accumulate in N days.
 *
 * ---------------------------------------------------------
 * 2. OBSERVATIONS
 * ---------------------------------------------------------
 * - Each day → 3 choices
 * - Choice depends on what was done the PREVIOUS day
 * - Overlapping subproblems → DP applicable
 *
 * ---------------------------------------------------------
 * 3. STATE DEFINITION
 * ---------------------------------------------------------
 * dp[day][last]
 *
 * where:
 * - day  → current day (0 to n-1)
 * - last → task done on the previous day
 *           0,1,2 → specific task
 *           3     → no task restriction (used for day 0)
 *
 * dp[day][last] = maximum points achievable from day 0 to "day"
 *                 if the task done on the previous day was "last"
 *
 * ---------------------------------------------------------
 * 4. RECURRENCE RELATION
 * ---------------------------------------------------------
 * dp[day][last] =
 *      max over all tasks "task" such that task != last of
 *      ( points[day][task] + dp[day-1][task] )
 *
 * ---------------------------------------------------------
 * 5. BASE CASE
 * ---------------------------------------------------------
 * day = 0
 *
 * dp[0][last] = max(points[0][task]) for all task != last
 *
 * ---------------------------------------------------------
 * 6. APPROACH USED HERE
 * ---------------------------------------------------------
 * ✔ Tabulation (Bottom-Up DP)
 * ✔ Time efficient
 * ✔ Easy to visualize
 *
 * ---------------------------------------------------------
 * 7. TIME & SPACE COMPLEXITY
 * ---------------------------------------------------------
 * Time Complexity  : O(n × 4 × 3) ≈ O(n)
 * Space Complexity : O(n × 4)
 *
 * (Can be space optimized to O(4))
 *
 * ---------------------------------------------------------
 * 8. ONE-LINE SUMMARY (INTERVIEW GOLD)
 * ---------------------------------------------------------
 * On each day, choose the best task different from the one
 * performed on the previous day.
 *
 * =========================================================
 * IMPLEMENTATION BELOW
 * =========================================================
 */

@SuppressWarnings("unused")
public class NinjaTraining {

    /**
     * Returns maximum points ninja can achieve
     *
     * @param points points[day][task]
     * @return maximum total points
     */
    public int ninjaTraining(int[][] points) {

        int n = points.length;

        /**
         * dp[day][last]
         * last = 0,1,2 → last task done
         * last = 3     → no restriction (only for day 0)
         */
        int[][] dp = new int[n][4];

        /**
         * -----------------------------------------------------
         * BASE CASE (DAY 0)
         * -----------------------------------------------------
         */
        dp[0][0] = Math.max(points[0][1], points[0][2]);
        dp[0][1] = Math.max(points[0][0], points[0][2]);
        dp[0][2] = Math.max(points[0][0], points[0][1]);
        dp[0][3] = Math.max(
                        points[0][0],
                        Math.max(points[0][1], points[0][2])
                   );

        /**
         * -----------------------------------------------------
         * FILL DP TABLE FOR DAYS 1 TO N-1
         * -----------------------------------------------------
         */
        for (int day = 1; day < n; day++) {
            for (int last = 0; last < 4; last++) {

                dp[day][last] = 0;

                // Try all possible tasks for current day
                for (int task = 0; task < 3; task++) {

                    // Cannot repeat the same task
                    if (task != last) {

                        int curPoints =
                                points[day][task] + dp[day - 1][task];

                        dp[day][last] =
                                Math.max(dp[day][last], curPoints);
                    }
                }
            }
        }

        /**
         * Answer will be dp[n-1][3]
         * (no restriction on what was done before last day)
         */
        return dp[n - 1][3];
    }

    public static void main(String[] args) {

        System.out.println("=== Ninja's Training (DP-7) ===\n");

        /*
         * Example:
         *
         * Day →   Running  Fighting  Learning
         * 0         10        40        70
         * 1         20        50        80
         * 2         30        60        90
         *
         * Optimal path:
         * Day 0 → Learning (70)
         * Day 1 → Fighting (50)
         * Day 2 → Learning (90)
         *
         * Total = 210
         */

        int[][] points = {
                {10, 40, 70},
                {20, 50, 80},
                {30, 60, 90}
        };

        NinjaTraining sol = new NinjaTraining();
        System.out.println("Maximum Points: " + sol.ninjaTraining(points));

        System.out.println("\n" + "=".repeat(60));
        System.out.println("INTERVIEW TAKEAWAYS:");
        System.out.println("✔ DP with 'last task' state");
        System.out.println("✔ Cannot repeat consecutive tasks");
        System.out.println("✔ Classic 2D DP problem");
    }
}
