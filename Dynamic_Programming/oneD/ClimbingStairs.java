package Dynamic_Programming.oneD;

/**
 * =========================================================
 * CLIMBING STAIRS (DP-1)
 * =========================================================
 *
 * Problem:
 * You are climbing a staircase with n steps.
 * You can climb either 1 step or 2 steps at a time.
 * Find the number of distinct ways to reach the top.
 *
 * ---------------------------------------------------------
 * RECURRENCE:
 * ---------------------------------------------------------
 * ways(n) = ways(n-1) + ways(n-2)
 *
 * Base Cases:
 * ways(0) = 1
 * ways(1) = 1
 *
 * ---------------------------------------------------------
 * APPROACH:
 * ---------------------------------------------------------
 * This is identical to Fibonacci sequence.
 * Use DP to avoid recomputation.
 *
 * ---------------------------------------------------------
 * TIME & SPACE:
 * ---------------------------------------------------------
 * Time: O(n)
 * Space: O(1)
 * =========================================================
 */

public class ClimbingStairs {

    public int climbStairs(int n) {
        if (n <= 1) return 1;

        int prev2 = 1; // ways(0)
        int prev1 = 1; // ways(1)

        for (int i = 2; i <= n; i++) {
            int cur = prev1 + prev2;
            prev2 = prev1;
            prev1 = cur;
        }

        return prev1;
    }

    public static void main(String[] args) {
        ClimbingStairs sol = new ClimbingStairs();
        System.out.println(sol.climbStairs(5)); // 8
    }
}
