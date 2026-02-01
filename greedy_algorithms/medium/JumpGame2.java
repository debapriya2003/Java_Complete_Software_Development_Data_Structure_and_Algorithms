package greedy_algorithms.medium;
import java.util.*;

/**
 * Jump Game II
 *
 * Problem:
 * You are given an integer array nums where nums[i] represents
 * the maximum jump length from index i.
 *
 * You start at index 0 and want to reach the last index
 * using the minimum number of jumps.
 *
 * It is guaranteed that the last index is reachable.
 *
 * ---------------------------------------------------------
 * Key Difference from Jump Game I:
 * ---------------------------------------------------------
 * - Jump Game I → check if reachable (true / false)
 * - Jump Game II → find minimum number of jumps
 *
 * ---------------------------------------------------------
 * Approach: Greedy (Level-Based BFS Interpretation)
 * ---------------------------------------------------------
 * - Think of the array as levels in BFS
 * - Each jump defines the current reachable range
 * - Track:
 *     - currentEnd → end of current jump range
 *     - farthest → farthest index reachable so far
 * - When index reaches currentEnd:
 *     - Make a jump
 *     - Update currentEnd = farthest
 *
 * Greedy Justification:
 * - Always extend the jump range as far as possible
 * - Minimizes the number of jumps
 *
 * ---------------------------------------------------------
 * Example:
 * nums = [2, 3, 1, 1, 4]
 *
 * Step-by-step:
 * i=0 → farthest = 2 → currentEnd = 2 → jumps = 1
 * i=1 → farthest = 4
 * i=2 → currentEnd reached → jumps = 2 → currentEnd = 4
 *
 * Output: 2
 *
 * ---------------------------------------------------------
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

public class JumpGame2 {

    /**
     * Greedy approach to find minimum number of jumps
     *
     * @param nums jump lengths array
     * @return minimum number of jumps to reach last index
     */
    public static int jump(int[] nums) {

        // Edge case: single element array
        if (nums.length <= 1) {
            return 0;
        }

        int jumps = 0;       // Total jumps taken
        int currentEnd = 0;  // End of current jump range
        int farthest = 0;    // Farthest reachable index

        // We don't need to check last index
        for (int i = 0; i < nums.length - 1; i++) {

            // Update farthest reachable index
            farthest = Math.max(farthest, i + nums[i]);

            // When we reach the end of the current range
            if (i == currentEnd) {
                jumps++;
                currentEnd = farthest;

                // Early exit if last index is reachable
                if (currentEnd >= nums.length - 1) {
                    break;
                }
            }
        }

        return jumps;
    }

    public static void main(String[] args) {

        System.out.println("=== Jump Game II ===\n");

        int[][] testCases = {
                {2, 3, 1, 1, 4},
                {2, 3, 0, 1, 4},
                {1, 1, 1, 1},
                {1},
                {5, 4, 3, 2, 1, 0}
        };

        System.out.println(String.format("%-25s | %-10s",
                "Array", "Min Jumps"));
        System.out.println("-".repeat(40));

        for (int[] test : testCases) {
            System.out.println(String.format("%-25s | %-10d",
                    Arrays.toString(test),
                    jump(test)));
        }

        System.out.println("\n" + "=".repeat(40));
        System.out.println("DETAILED WALKTHROUGH EXAMPLE");
        System.out.println("-".repeat(40));

        int[] example = {2, 3, 1, 1, 4};

        int jumps = 0;
        int currentEnd = 0;
        int farthest = 0;

        for (int i = 0; i < example.length - 1; i++) {

            System.out.println("Index: " + i +
                    ", Jump Length: " + example[i] +
                    ", Farthest Before: " + farthest);

            farthest = Math.max(farthest, i + example[i]);
            System.out.println("Updated Farthest: " + farthest);

            if (i == currentEnd) {
                jumps++;
                currentEnd = farthest;
                System.out.println("✔ Jump " + jumps +
                        " → New Range End: " + currentEnd);
            }
        }

        System.out.println("\nMinimum Jumps Required: " + jumps);

        System.out.println("\n" + "=".repeat(40));
        System.out.println("COMPLEXITY ANALYSIS");
        System.out.println("-".repeat(40));
        System.out.println("Time Complexity : O(n)");
        System.out.println("Space Complexity: O(1)");

        System.out.println("\nNOTE:");
        System.out.println("✔ Guaranteed reachability makes greedy valid");
        System.out.println("✖ Without guarantee, Jump Game I check is required");
    }
}

