package Dynamic_Programming.oneD;

/**
 * =========================================================
 * HOUSE ROBBER (DP-6)
 * =========================================================
 *
 * Problem:
 * Rob houses such that no two adjacent houses are robbed.
 * Maximize the stolen amount.
 *
 * ---------------------------------------------------------
 * SAME AS:
 * ---------------------------------------------------------
 * Maximum Sum of Non-Adjacent Elements
 *
 * ---------------------------------------------------------
 * TIME & SPACE:
 * ---------------------------------------------------------
 * Time: O(n)
 * Space: O(1)
 * =========================================================
 */

public class HouseRobber {

    public int rob(int[] nums) {
        int prev2 = 0;
        int prev1 = 0;

        for (int num : nums) {
            int pick = num + prev2;
            int notPick = prev1;
            int cur = Math.max(pick, notPick);

            prev2 = prev1;
            prev1 = cur;
        }
        return prev1;
    }

    public static void main(String[] args) {
        HouseRobber sol = new HouseRobber();
        int[] houses = {1, 2, 3, 1};
        System.out.println(sol.rob(houses)); // 4
    }
}
