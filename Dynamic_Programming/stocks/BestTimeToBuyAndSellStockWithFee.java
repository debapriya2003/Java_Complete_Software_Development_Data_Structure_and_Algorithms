package Dynamic_Programming.stocks;

/**
 * BUY AND SELL STOCKS WITH TRANSACTION FEE (DP-40)
 *
 * Problem:
 * Each sell transaction incurs a fee. Maximize profit.
 *
 * Approach:
 * Maintain two states: cash (not holding) and hold (holding). For each price:
 * cash = max(cash, hold + price - fee) // sell today and pay fee
 * hold = max(hold, cash - price) // buy today
 *
 * Complexity: O(n) time, O(1) space.
 *
 * Example:
 * prices=[1,3,2,8,4,9], fee=2 -> max profit = 8
 */
public class BestTimeToBuyAndSellStockWithFee {

    public static int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length == 0) return 0;
        int cash = 0;
        int hold = -prices[0];
        for (int p : prices) {
            int newCash = Math.max(cash, hold + p - fee);
            int newHold = Math.max(hold, cash - p);
            cash = newCash; hold = newHold;
        }
        return cash;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{1,3,2,8,4,9}, 2)); // 8
    }
}
