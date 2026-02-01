package Dynamic_Programming.stocks;

/**
 * BEST TIME TO BUY AND SELL STOCK (DP-35)
 *
 * Problem:
 * Given an array prices where prices[i] is the price of a given stock on day i, maximize profit
 * with at most one transaction (buy once and sell once).
 *
 * Approach:
 * Track the minimum price seen so far and compute potential profit at each day: profit = price - minPrice.
 * Keep the maximum profit.
 *
 * Complexity: O(n) time, O(1) space.
 *
 * Example:
 * prices = [7,1,5,3,6,4] -> max profit = 5 (buy at 1, sell at 6)
 */
public class BestTimeToBuyAndSellStock {

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int minPrice = Integer.MAX_VALUE, maxProfit = 0;
        for (int p : prices) {
            minPrice = Math.min(minPrice, p);
            maxProfit = Math.max(maxProfit, p - minPrice);
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{7,1,5,3,6,4})); // 5
    }
}
