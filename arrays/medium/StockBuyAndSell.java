package arrays.medium;

public class StockBuyAndSell {

    /*
    =====================================================================================
    PROBLEM: BEST TIME TO BUY AND SELL STOCK (SINGLE TRANSACTION)
    -------------------------------------------------------------------------------------
    Given an array where each element represents the stock price on a given day, the task
    is to find the maximum profit that can be achieved by buying the stock on one day and
    selling it on a later day.

    Only one transaction (one buy and one sell) is allowed. If no profit is possible,
    the maximum profit should be 0.

    This problem tests understanding of greedy algorithms and efficient array traversal.
    =====================================================================================
    */

    public static void main(String[] args) {

        int[] prices = {7, 1, 5, 3, 6, 4};

        int maxProfit = maxProfit(prices);

        System.out.println("Maximum profit: " + maxProfit);
    }

    /*
    =====================================================================================
    FUNCTION: maxProfit
    -------------------------------------------------------------------------------------
    This function calculates the maximum profit using a greedy approach.

    CORE IDEA:
    • Track the minimum price seen so far (buy price)
    • For each day, calculate potential profit = current price - minPrice
    • Update maxProfit if the potential profit is greater
    • Update minPrice whenever a lower price is found

    This ensures that we always buy at the lowest possible price before selling.

    Time Complexity  : O(n)
    Space Complexity : O(1)
    =====================================================================================
    */
    static int maxProfit(int[] prices) {

        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int price : prices) {

            if (price < minPrice) {
                minPrice = price;      // Best day to buy so far
            } else {
                maxProfit = Math.max(maxProfit, price - minPrice);
            }
        }

        return maxProfit;
    }
}

