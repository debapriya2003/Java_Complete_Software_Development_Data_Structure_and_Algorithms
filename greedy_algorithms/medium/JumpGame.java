package greedy_algorithms.medium;
import java.util.*;

/**
 * Jump Game
 *
 * Problem:
 * You are given an integer array nums where nums[i] represents
 * the maximum jump length from index i.
 *
 * You start at index 0.
 *
 * Task:
 * Determine whether you can reach the last index.
 *
 * ---------------------------------------------------------
 * Approach 1: Greedy (Optimal)
 * ---------------------------------------------------------
 * - Maintain the farthest index that can be reached so far
 * - Iterate through the array
 * - If at any index i, i > farthestReach → return false
 * - Update farthestReach = max(farthestReach, i + nums[i])
 * - If farthestReach >= last index → return true
 *
 * Greedy Justification:
 * - At each position, we only care about the maximum reach
 * - Local optimal (max reach so far) leads to global solution
 *
 * ---------------------------------------------------------
 * Example 1:
 * nums = [2,3,1,1,4]
 *
 * Index | nums[i] | farthestReach
 *   0   |   2     | max(0, 0+2) = 2
 *   1   |   3     | max(2, 1+3) = 4  → last index reachable
 *
 * Output: true
 *
 * ---------------------------------------------------------
 * Example 2:
 * nums = [3,2,1,0,4]
 *
 * At index 3 → farthestReach = 3
 * Cannot move further → return false
 *
 * ---------------------------------------------------------
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

public class JumpGame {

    /**
     * Greedy approach to check if last index is reachable
     *
     * @param nums jump lengths array
     * @return true if last index can be reached
     */
    public static boolean canJump(int[] nums) {

        int farthestReach = 0;

        // Traverse the array
        for (int i = 0; i < nums.length; i++) {

            // If current index is beyond reachable range
            if (i > farthestReach) {
                return false;
            }

            // Update farthest reachable index
            farthestReach = Math.max(farthestReach, i + nums[i]);

            // Early exit if last index is reachable
            if (farthestReach >= nums.length - 1) {
                return true;
            }
        }

        return true;
    }

    /**
     * Alternative Dynamic Programming explanation (not implemented)
     *
     * - dp[i] = true if index i is reachable
     * - Check all previous jumps
     * - Time Complexity: O(n²)
     * - Space Complexity: O(n)
     *
     * Greedy is preferred.
     */

    public static void main(String[] args) {

        System.out.println("=== Jump Game ===\n");

        int[][] testCases = {
                {2, 3, 1, 1, 4},
                {3, 2, 1, 0, 4},
                {0},
                {2, 0, 0},
                {1, 1, 0, 1}
        };

        System.out.println(String.format("%-25s | %-10s", "Array", "Can Jump"));
        System.out.println("-".repeat(40));

        for (int[] test : testCases) {
            System.out.println(String.format("%-25s | %-10s",
                    Arrays.toString(test),
                    canJump(test)));
        }

        System.out.println("\n" + "=".repeat(40));
        System.out.println("DETAILED WALKTHROUGH EXAMPLE");
        System.out.println("-".repeat(40));

        int[] example = {2, 3, 1, 1, 4};
        int farthest = 0;

        for (int i = 0; i < example.length; i++) {
            System.out.println("Index: " + i +
                    ", Jump Length: " + example[i] +
                    ", Farthest Reach Before: " + farthest);

            if (i > farthest) {
                System.out.println("✖ Cannot reach this index");
                break;
            }

            farthest = Math.max(farthest, i + example[i]);
            System.out.println("✔ Updated Farthest Reach: " + farthest);

            if (farthest >= example.length - 1) {
                System.out.println("✔ Last index reachable");
                break;
            }
        }

        System.out.println("\n" + "=".repeat(40));
        System.out.println("COMPLEXITY ANALYSIS");
        System.out.println("-".repeat(40));
        System.out.println("Time Complexity : O(n)");
        System.out.println("Space Complexity: O(1)");
    }
}

