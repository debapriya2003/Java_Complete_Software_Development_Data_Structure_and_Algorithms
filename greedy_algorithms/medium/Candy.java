package greedy_algorithms.medium;
import java.util.*;

/**
 * Candy
 *
 * Problem:
 * There are n children standing in a line.
 * Each child has a rating given in an integer array ratings[].
 *
 * You must give candies to the children such that:
 * 1. Each child has at least one candy
 * 2. Children with a higher rating than an adjacent child
 *    must get more candies than that neighbor
 *
 * Task:
 * Return the minimum number of candies you need to distribute.
 *
 * ---------------------------------------------------------
 * Approach: Two-Pass Greedy (Optimal)
 * ---------------------------------------------------------
 * Key Insight:
 * - Constraints are local (only neighbors matter)
 * - We need to satisfy both:
 *      left neighbor condition
 *      right neighbor condition
 *
 * Strategy:
 * 1. Initialize each child with 1 candy
 * 2. Left-to-right pass:
 *      - If rating[i] > rating[i-1]
 *        candies[i] = candies[i-1] + 1
 * 3. Right-to-left pass:
 *      - If rating[i] > rating[i+1]
 *        candies[i] = max(candies[i], candies[i+1] + 1)
 *
 * Why Two Passes?
 * - First pass ensures left condition
 * - Second pass ensures right condition
 * - max() keeps both constraints valid
 *
 * ---------------------------------------------------------
 * Example:
 * ratings = [1, 0, 2]
 *
 * Initial candies: [1, 1, 1]
 *
 * Left → Right:
 * rating[2] > rating[1]
 * candies = [1, 1, 2]
 *
 * Right → Left:
 * rating[0] > rating[1]
 * candies = [2, 1, 2]
 *
 * Total Candies = 5
 *
 * ---------------------------------------------------------
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

public class Candy {

    /**
     * Calculates minimum candies required
     *
     * @param ratings ratings of children
     * @return minimum candies needed
     */
    public static int minimumCandies(int[] ratings) {

        int n = ratings.length;

        // Each child gets at least one candy
        int[] candies = new int[n];
        Arrays.fill(candies, 1);

        // Pass 1: Left to Right
        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }

        // Pass 2: Right to Left
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
        }

        // Sum total candies
        int total = 0;
        for (int candy : candies) {
            total += candy;
        }

        return total;
    }

    public static void main(String[] args) {

        System.out.println("=== Candy Distribution Problem ===\n");

        int[][] testCases = {
                {1, 0, 2},
                {1, 2, 2},
                {1, 3, 4, 5, 2},
                {5, 4, 3, 2, 1},
                {1}
        };

        System.out.println(String.format("%-25s | %-15s",
                "Ratings", "Min Candies"));
        System.out.println("-".repeat(45));

        for (int[] test : testCases) {
            System.out.println(String.format("%-25s | %-15d",
                    Arrays.toString(test),
                    minimumCandies(test)));
        }

        System.out.println("\n" + "=".repeat(60));
        System.out.println("DETAILED WALKTHROUGH EXAMPLE");
        System.out.println("-".repeat(60));

        int[] ratings = {1, 0, 2};
        int[] candies = new int[ratings.length];
        Arrays.fill(candies, 1);

        System.out.println("Ratings : " + Arrays.toString(ratings));
        System.out.println("Initial Candies: " + Arrays.toString(candies));

        // Left to Right
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }
        System.out.println("After Left → Right pass: " + Arrays.toString(candies));

        // Right to Left
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
        }
        System.out.println("After Right → Left pass: " + Arrays.toString(candies));

        int sum = 0;
        for (int c : candies) sum += c;
        System.out.println("Total Candies Required: " + sum);

        System.out.println("\n" + "=".repeat(60));
        System.out.println("COMPLEXITY ANALYSIS");
        System.out.println("-".repeat(60));
        System.out.println("Time Complexity : O(n)");
        System.out.println("Space Complexity: O(n)");

        System.out.println("\nNOTE:");
        System.out.println("✔ Each child gets at least one candy");
        System.out.println("✔ Two-pass greedy guarantees minimum total candies");
    }
}

