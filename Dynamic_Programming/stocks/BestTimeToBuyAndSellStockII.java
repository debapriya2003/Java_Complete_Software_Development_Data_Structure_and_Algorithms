package Dynamic_Programming.stocks;

/**
 * BUY AND SELL STOCK - II (DP-36)
 *
 * Problem:
 * You may complete as many transactions as you like (buy/sell multiple times), but cannot hold
 * more than one share at a time. Maximize total profit.
 *
 * Approach:
 * Sum up all positive differences between consecutive days. Equivalent to buying before a rise
 * and selling at the peak.
 *
 * Complexity: O(n) time, O(1) space.
 *
 * Example:
 * prices=[7,1,5,3,6,4] -> buy at 1 sell at 5 (profit 4), buy at 3 sell at 6 (profit 3) => total 7
 */
public class BestTimeToBuyAndSellStockII {

    public static int maxProfit(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i++) if (prices[i] > prices[i - 1]) profit += prices[i] - prices[i - 1];
        return profit;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{7,1,5,3,6,4})); // 7
    }
}
