package Dynamic_Programming.oneD;

/**
 * =========================================================
 * FROG JUMP (DP-3)
 * =========================================================
 *
 * Problem:
 * A frog is at index 0 and wants to reach index n-1.
 * It can jump either:
 *  - 1 step
 *  - 2 steps
 *
 * Cost = |height[i] - height[j]|
 * Minimize total cost.
 *
 * ---------------------------------------------------------
 * RECURRENCE:
 * ---------------------------------------------------------
 * dp[i] = min(
 *     dp[i-1] + |h[i] - h[i-1]|,
 *     dp[i-2] + |h[i] - h[i-2]|
 * )
 *
 * ---------------------------------------------------------
 * TIME & SPACE:
 * ---------------------------------------------------------
 * Time: O(n)
 * Space: O(1)
 * =========================================================
 */

public class FrogJump {

    public int frogJump(int[] height) {
        int n = height.length;

        int prev2 = 0; // dp[i-2]
        int prev1 = 0; // dp[i-1]

        for (int i = 1; i < n; i++) {
            int jump1 = prev1 + Math.abs(height[i] - height[i - 1]);
            int jump2 = Integer.MAX_VALUE;

            if (i > 1) {
                jump2 = prev2 + Math.abs(height[i] - height[i - 2]);
            }

            int cur = Math.min(jump1, jump2);
            prev2 = prev1;
            prev1 = cur;
        }

        return prev1;
    }

    public static void main(String[] args) {
        FrogJump sol = new FrogJump();
        int[] height = {10, 20, 30, 10};
        System.out.println(sol.frogJump(height)); // 20
    }
}
