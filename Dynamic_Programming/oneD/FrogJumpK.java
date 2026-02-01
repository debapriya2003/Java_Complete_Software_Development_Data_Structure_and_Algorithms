package Dynamic_Programming.oneD;

/**
 * =========================================================
 * FROG JUMP WITH K DISTANCES (DP-4)
 * =========================================================
 *
 * Problem:
 * Frog can jump from i to i-j where 1 ≤ j ≤ K
 *
 * ---------------------------------------------------------
 * RECURRENCE:
 * ---------------------------------------------------------
 * dp[i] = min over j=1..k:
 *         dp[i-j] + |h[i] - h[i-j]|
 *
 * ---------------------------------------------------------
 * TIME & SPACE:
 * ---------------------------------------------------------
 * Time: O(n * k)
 * Space: O(n)
 * =========================================================
 */

public class FrogJumpK {

    public int frogJump(int[] height, int k) {
        int n = height.length;
        int[] dp = new int[n];
        dp[0] = 0;

        for (int i = 1; i < n; i++) {
            int min = Integer.MAX_VALUE;

            for (int j = 1; j <= k && i - j >= 0; j++) {
                int cost = dp[i - j] + Math.abs(height[i] - height[i - j]);
                min = Math.min(min, cost);
            }
            dp[i] = min;
        }

        return dp[n - 1];
    }

    public static void main(String[] args) {
        FrogJumpK sol = new FrogJumpK();
        int[] height = {10, 40, 50, 20};
        System.out.println(sol.frogJump(height, 3)); // 30
    }
}
