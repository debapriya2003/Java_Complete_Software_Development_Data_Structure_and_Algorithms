package Dynamic_Programming.oneD;

/**
 * =========================================================
 * MAXIMUM SUM OF NON-ADJACENT ELEMENTS (DP-5)
 * =========================================================
 *
 * Problem:
 * Choose elements such that no two chosen elements
 * are adjacent and sum is maximum.
 *
 * ---------------------------------------------------------
 * RECURRENCE:
 * ---------------------------------------------------------
 * dp[i] = max(
 *     dp[i-1],           // not pick
 *     dp[i-2] + arr[i]   // pick
 * )
 *
 * ---------------------------------------------------------
 * TIME & SPACE:
 * ---------------------------------------------------------
 * Time: O(n)
 * Space: O(1)
 * =========================================================
 */

public class MaxNonAdjacentSum {

    public int maxSum(int[] arr) {
        int prev2 = 0;
        int prev1 = arr[0];

        for (int i = 1; i < arr.length; i++) {
            int pick = arr[i] + (i > 1 ? prev2 : 0);
            int notPick = prev1;

            int cur = Math.max(pick, notPick);
            prev2 = prev1;
            prev1 = cur;
        }
        return prev1;
    }

    public static void main(String[] args) {
        MaxNonAdjacentSum sol = new MaxNonAdjacentSum();
        int[] arr = {2, 1, 4, 9};
        System.out.println(sol.maxSum(arr)); // 11
    }
}
