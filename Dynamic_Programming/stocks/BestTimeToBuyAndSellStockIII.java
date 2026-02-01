package Dynamic_Programming.stocks;

/**
 * BUY AND SELL STOCKS III (DP-37)
 *
 * Problem:
 * At most two transactions (buy/sell) are allowed. Maximize profit.
 *
 * Approach:
 * Use two-pass / four-variable technique: maintain best states after first buy/sell and second buy/sell.
 * Let buy1 = max(-price), sell1 = max(buy1 + price), buy2 = max(sell1 - price), sell2 = max(buy2 + price).
 * This gives O(n) time and O(1) space.
 *
 * Complexity: O(n) time, O(1) space.
 *
 * Example:
 * prices = [3,3,5,0,0,3,1,4] -> max profit = 6 (buy@0 sell@3, buy@1 sell@4)
 */
public class BestTimeToBuyAndSellStockIII {

    public static int maxProfit(int[] prices) {
        int buy1 = Integer.MIN_VALUE/2, buy2 = Integer.MIN_VALUE/2;
        int sell1 = 0, sell2 = 0;
        for (int p : prices) {
            buy1 = Math.max(buy1, -p);
            sell1 = Math.max(sell1, buy1 + p);
            buy2 = Math.max(buy2, sell1 - p);
            sell2 = Math.max(sell2, buy2 + p);
        }
        return sell2;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{3,3,5,0,0,3,1,4})); // 6
    }
}
