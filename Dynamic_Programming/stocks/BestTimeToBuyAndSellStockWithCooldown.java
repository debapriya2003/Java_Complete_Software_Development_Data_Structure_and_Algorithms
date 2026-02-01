package Dynamic_Programming.stocks;

/**
 * BUY AND SELL STOCKS WITH COOLDOWN (DP-39)
 *
 * Problem:
 * After you sell your stock, you cannot buy stock on the next day (cooldown of 1 day). Maximize profit.
 *
 * Approach:
 * Maintain three states per day:
 * - hold[i]: max profit while holding a stock on day i
 * - sold[i]: max profit while having just sold stock on day i
 * - rest[i]: max profit while resting (no stock and didn't just sell) on day i
 * Transitions:
 * hold = max(hold_prev, rest_prev - price)
 * sold = hold_prev + price
 * rest = max(rest_prev, sold_prev)
 * Result = max(sold, rest)
 *
 * Complexity: O(n) time, O(1) space with rolling variables.
 *
 * Example:
 * prices=[1,2,3,0,2] -> max profit=3 (buy1 sell2 cool down buy0 sell2)
 */
public class BestTimeToBuyAndSellStockWithCooldown {

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int n = prices.length;
        int hold = -prices[0];
        int sold = 0;
        int rest = 0;
        for (int i = 1; i < n; i++) {
            int prevHold = hold, prevSold = sold, prevRest = rest;
            hold = Math.max(prevHold, prevRest - prices[i]);
            sold = prevHold + prices[i];
            rest = Math.max(prevRest, prevSold);
        }
        return Math.max(sold, rest);
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{1,2,3,0,2})); // 3
    }
}
