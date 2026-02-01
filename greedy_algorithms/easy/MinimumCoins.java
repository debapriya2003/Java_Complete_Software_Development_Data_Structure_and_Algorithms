package greedy_algorithms.easy;

import java.util.*;

/**
 * Minimum Number of Coins (Greedy Algorithm)
 *
 * Problem:
 * You are given:
 * - An integer amount representing total money
 * - An array of coin denominations
 *
 * You need to find the minimum number of coins required to make the given amount.
 * You can use unlimited coins of each denomination.
 *
 * NOTE:
 * This greedy solution works correctly only when the coin system is
 * canonical (like Indian or US currency).
 *
 * ---------------------------------------------------------
 * Approach: Greedy
 * ---------------------------------------------------------
 * - Sort the coin denominations in descending order
 * - Always pick the largest possible coin
 * - Subtract its value from the amount
 * - Repeat until the amount becomes 0
 *
 * Greedy Justification:
 * - Using the largest denomination first reduces the remaining amount fastest
 * - For canonical coin systems, this guarantees minimum coins
 *
 * ---------------------------------------------------------
 * Example:
 * Coins  = [1, 2, 5, 10]
 * Amount = 28
 *
 * Selection:
 * 10 → 10 → 5 → 2 → 1
 *
 * Total coins = 5
 *
 * ---------------------------------------------------------
 * Time Complexity: O(n log n)
 * Space Complexity: O(1)
 */

public class MinimumCoins {

    /**
     * Greedy method to find minimum number of coins
     *
     * @param coins available coin denominations
     * @param amount total amount
     * @return list of coins used
     */
    public static List<Integer> findMinimumCoins(int[] coins, int amount) {

        List<Integer> result = new ArrayList<>();

        // Sort coins in descending order
        Arrays.sort(coins);
        for (int i = coins.length - 1; i >= 0 && amount > 0; i--) {

            // Use the current coin as many times as possible
            while (coins[i] <= amount) {
                amount -= coins[i];
                result.add(coins[i]);
            }
        }

        return result;
    }

    /**
     * Count minimum number of coins
     */
    public static int countMinimumCoins(int[] coins, int amount) {
        return findMinimumCoins(coins, amount).size();
    }

    public static void main(String[] args) {

        System.out.println("=== Minimum Number of Coins (Greedy) ===\n");

        int[] coins = {1, 2, 5, 10, 20, 50, 100, 500};
        int amount = 275;

        List<Integer> usedCoins = findMinimumCoins(coins, amount);

        System.out.println("Available Coins : " + Arrays.toString(coins));
        System.out.println("Target Amount   : " + amount);

        System.out.println("\nCoins Used:");
        for (int coin : usedCoins) {
            System.out.print(coin + " ");
        }

        System.out.println("\n\nTotal Coins Required: " + usedCoins.size());

        System.out.println("\n" + "=".repeat(60));
        System.out.println("DETAILED SELECTION PROCESS");
        System.out.println("-".repeat(60));

        int remainingAmount = amount;
        Arrays.sort(coins);

        for (int i = coins.length - 1; i >= 0; i--) {
            if (coins[i] <= remainingAmount) {
                int count = remainingAmount / coins[i];
                remainingAmount %= coins[i];

                System.out.println("Coin: " + coins[i] +
                        " → Used: " + count +
                        " → Remaining Amount: " + remainingAmount);
            }
        }

        System.out.println("\n" + "=".repeat(60));
        System.out.println("IMPORTANT NOTE");
        System.out.println("-".repeat(60));
        System.out.println("✔ Greedy works for canonical coin systems (INR, USD)");
        System.out.println("✖ Greedy may fail for arbitrary denominations");
        System.out.println("✔ Dynamic Programming is required for non-canonical systems");

        System.out.println("\n" + "=".repeat(60));
        System.out.println("COMPLEXITY ANALYSIS");
        System.out.println("-".repeat(60));
        System.out.println("Time Complexity : O(n log n)");
        System.out.println("Space Complexity: O(1)");
    }
}
