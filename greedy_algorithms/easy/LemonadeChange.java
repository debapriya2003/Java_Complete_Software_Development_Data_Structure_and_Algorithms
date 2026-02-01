package greedy_algorithms.easy;
import java.util.*;

/**
 * Lemonade Change
 *
 * Problem:
 * You are running a lemonade stand where each lemonade costs $5.
 * Customers are standing in a queue, and each customer pays with either:
 * - $5
 * - $10
 * - $20
 *
 * You must provide correct change to each customer.
 * Initially, you have no money.
 *
 * Task:
 * Determine whether you can provide correct change to every customer in order.
 *
 * ---------------------------------------------------------
 * Observations:
 * ---------------------------------------------------------
 * - $5 bill: No change required → always safe
 * - $10 bill: Requires $5 change
 * - $20 bill: Requires $15 change
 *      Best strategy:
 *      - Prefer using one $10 + one $5 (if available)
 *      - Otherwise, use three $5 bills
 *
 * ---------------------------------------------------------
 * Approach: Greedy
 * ---------------------------------------------------------
 * - Maintain count of $5 and $10 bills
 * - Iterate through each customer:
 *      - If $5 → increment fiveCount
 *      - If $10 → need one $5
 *      - If $20 → need ($10 + $5) OR (3 × $5)
 * - If change cannot be made at any point → return false
 *
 * Greedy Justification:
 * - Using larger denominations first preserves smaller bills
 * - Smaller bills are more flexible for future transactions
 *
 * ---------------------------------------------------------
 * Example:
 * bills = [5, 5, 5, 10, 20]
 *
 * Step-by-step:
 * - 5  → five = 1
 * - 5  → five = 2
 * - 5  → five = 3
 * - 10 → five = 2, ten = 1
 * - 20 → use (10 + 5) → five = 1, ten = 0
 *
 * Output: true
 *
 * ---------------------------------------------------------
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

public class LemonadeChange {

    /**
     * Determines if correct change can be provided to all customers
     *
     * @param bills array representing bills given by customers
     * @return true if change can be provided, false otherwise
     */
    public static boolean lemonadeChange(int[] bills) {

        int fiveCount = 0; // number of $5 bills
        int tenCount = 0;  // number of $10 bills

        // Process each customer in order
        for (int bill : bills) {

            // Customer pays with $5
            if (bill == 5) {
                fiveCount++;
            }

            // Customer pays with $10
            else if (bill == 10) {
                if (fiveCount == 0) {
                    return false; // cannot give $5 change
                }
                fiveCount--;
                tenCount++;
            }

            // Customer pays with $20
            else {
                // Prefer giving $10 + $5 as change
                if (tenCount > 0 && fiveCount > 0) {
                    tenCount--;
                    fiveCount--;
                }
                // Otherwise give three $5 bills
                else if (fiveCount >= 3) {
                    fiveCount -= 3;
                }
                // No valid way to give change
                else {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {

        System.out.println("=== Lemonade Change Problem ===\n");

        int[][] testCases = {
                {5, 5, 5, 10, 20},
                {5, 5, 10},
                {10, 10},
                {5, 5, 5, 20},
                {5, 10, 5, 20, 5, 5, 10, 20}
        };

        System.out.println(String.format("%-35s | %-10s", "Bills", "Result"));
        System.out.println("-".repeat(55));

        for (int[] test : testCases) {
            boolean result = lemonadeChange(test);
            System.out.println(String.format("%-35s | %-10s",
                    Arrays.toString(test),
                    result));
        }

        System.out.println("\n" + "=".repeat(55));
        System.out.println("DETAILED WALKTHROUGH EXAMPLE");
        System.out.println("-".repeat(55));

        int[] bills = {5, 5, 5, 10, 20};
        int five = 0, ten = 0;

        for (int bill : bills) {
            System.out.println("\nCustomer pays: $" + bill);

            if (bill == 5) {
                five++;
                System.out.println("No change needed");
            } else if (bill == 10) {
                five--;
                ten++;
                System.out.println("Gave $5 as change");
            } else {
                if (ten > 0) {
                    ten--;
                    five--;
                    System.out.println("Gave $10 + $5 as change");
                } else {
                    five -= 3;
                    System.out.println("Gave three $5 as change");
                }
            }

            System.out.println("Current State → $5: " + five + ", $10: " + ten);
        }

        System.out.println("\n" + "=".repeat(55));
        System.out.println("COMPLEXITY ANALYSIS");
        System.out.println("-".repeat(55));
        System.out.println("Time Complexity : O(n)");
        System.out.println("Space Complexity: O(1)");
    }
}

