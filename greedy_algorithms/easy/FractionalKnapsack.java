package greedy_algorithms.easy;
import java.util.*;

/**
 * Fractional Knapsack Problem
 *
 * Problem:
 * You are given:
 * - An array of items, where each item has a weight and a value
 * - A knapsack with a maximum capacity W
 *
 * You are allowed to:
 * - Take the whole item
 * - Take a fraction of an item
 *
 * Goal:
 * Maximize the total value inside the knapsack.
 *
 * ---------------------------------------------------------
 * Approach: Greedy (Value-to-Weight Ratio)
 * ---------------------------------------------------------
 * - Compute value/weight ratio for each item
 * - Sort items in descending order of ratio
 * - Pick items greedily:
 *     - Take full item if it fits
 *     - Otherwise, take fractional part
 *
 * Greedy Justification:
 * - Taking the item with the highest value per unit weight
 *   gives maximum profit at every step
 *
 * ---------------------------------------------------------
 * Example:
 * Values  = [60, 100, 120]
 * Weights = [10, 20, 30]
 * Capacity = 50
 *
 * Ratios:
 * 60/10  = 6
 * 100/20 = 5
 * 120/30 = 4
 *
 * Take:
 * - Item 1 (10, 60)
 * - Item 2 (20, 100)
 * - 2/3 of Item 3 → value = 80
 *
 * Total Value = 240
 *
 * ---------------------------------------------------------
 * Time Complexity: O(n log n)
 * Space Complexity: O(n)
 */

public class FractionalKnapsack {

    /**
     * Class to represent an item in the knapsack
     */
    static class Item {
        int weight;
        int value;
        double ratio;

        Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
            this.ratio = (double) value / weight;
        }
    }

    /**
     * Greedy approach to solve Fractional Knapsack
     *
     * @param W maximum capacity of knapsack
     * @param weights array of weights
     * @param values array of values
     * @return maximum achievable value
     */
    public static double getMaxValue(int W, int[] weights, int[] values) {

        int n = weights.length;
        Item[] items = new Item[n];

        // Create item objects with value-to-weight ratio
        for (int i = 0; i < n; i++) {
            items[i] = new Item(weights[i], values[i]);
        }

        // Sort items by ratio in descending order
        Arrays.sort(items, (a, b) -> Double.compare(b.ratio, a.ratio));

        double totalValue = 0.0;
        int remainingCapacity = W;

        // Pick items greedily
        for (Item item : items) {

            // If the entire item fits
            if (item.weight <= remainingCapacity) {
                totalValue += item.value;
                remainingCapacity -= item.weight;
            }
            // Take fractional part of the item
            else {
                double fraction = (double) remainingCapacity / item.weight;
                totalValue += item.value * fraction;
                break; // Knapsack is full
            }
        }

        return totalValue;
    }

    public static void main(String[] args) {

        System.out.println("=== Fractional Knapsack Problem ===\n");

        int[] weights = {10, 20, 30};
        int[] values = {60, 100, 120};
        int capacity = 50;

        double maxValue = getMaxValue(capacity, weights, values);

        System.out.println("Weights  : " + Arrays.toString(weights));
        System.out.println("Values   : " + Arrays.toString(values));
        System.out.println("Capacity : " + capacity);

        System.out.println("\nMaximum value that can be obtained = " + maxValue);

        System.out.println("\n" + "=".repeat(60));
        System.out.println("DETAILED STEP-BY-STEP SELECTION");
        System.out.println("-".repeat(60));

        // Re-run with detailed tracing
        Item[] items = new Item[weights.length];
        for (int i = 0; i < weights.length; i++) {
            items[i] = new Item(weights[i], values[i]);
        }

        Arrays.sort(items, (a, b) -> Double.compare(b.ratio, a.ratio));

        int remainingCapacity = capacity;
        double totalValue = 0;

        for (Item item : items) {
            System.out.println("\nConsidering item:");
            System.out.println("Weight = " + item.weight);
            System.out.println("Value  = " + item.value);
            System.out.println("Ratio  = " + item.ratio);

            if (item.weight <= remainingCapacity) {
                System.out.println("✔ Taking full item");
                remainingCapacity -= item.weight;
                totalValue += item.value;
            } else {
                double fraction = (double) remainingCapacity / item.weight;
                System.out.println("✔ Taking fraction: " + fraction);
                totalValue += item.value * fraction;
                remainingCapacity = 0;
                break;
            }
        }

        System.out.println("\nTotal Value Collected = " + totalValue);

        System.out.println("\n" + "=".repeat(60));
        System.out.println("COMPLEXITY ANALYSIS");
        System.out.println("-".repeat(60));
        System.out.println("Time Complexity : O(n log n)");
        System.out.println("Space Complexity: O(n)");

        System.out.println("\nNOTE:");
        System.out.println("✔ Fractional Knapsack allows breaking items");
        System.out.println("✖ 0/1 Knapsack does NOT allow fractions");
        System.out.println("✔ Greedy works only for Fractional Knapsack");
    }
}
