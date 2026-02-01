package stacks_queues.implementation_problems;

import java.util.*;

/**
 * Stock Span Problem
 * 
 * Problem: For each stock price, find span = number of consecutive days just before,
 *          where price was less than or equal to current.
 * 
 * Example: prices = [100, 80, 60, 70, 60, 75, 85]
 * 
 * Spans:
 * 100: 1 (first day)
 * 80:  1 (previous day has 100 > 80)
 * 60:  1 (previous day has 80 > 60)
 * 70:  2 (previous: 60 ≤ 70, then 80 > 70)
 * 60:  1 (previous day has 70 > 60)
 * 75:  4 (60 ≤ 75, 70 ≤ 75, 80 > 75 stops) → indices 2,3,4
 * 85:  6 (spans to day 1: 75 ≤ 85, 60 ≤ 85, 70 ≤ 85, 60 ≤ 85, 80 ≤ 85, 100 > 85)
 * 
 * Output: [1, 1, 1, 2, 1, 4, 6]
 * 
 * Algorithm: Monotonic Stack
 * ===========================
 * 1. Use stack to store (price, span) pairs
 * 2. For each price:
 *    - Keep removing from stack while top price ≤ current
 *    - Current span = index - (previous greater price index)
 *    - Pop all smaller/equal prices and add their spans
 * 3. Push current (price, span) to stack
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

public class StockSpanProblem {
    
    static class StockSpanner {
        Stack<int[]> stack; // [price, span]
        int index;
        
        public StockSpanner() {
            stack = new Stack<>();
            index = -1;
        }
        
        public int next(int price) {
            index++;
            int span = 1;
            
            // Pop smaller or equal prices and accumulate spans
            while (!stack.isEmpty() && stack.peek()[0] <= price) {
                span += stack.pop()[1];
            }
            
            stack.push(new int[]{price, span});
            return span;
        }
    }
    
    /**
     * Offline version: process all prices at once
     */
    public static int[] calculateSpans(int[] prices) {
        int n = prices.length;
        int[] span = new int[n];
        Stack<Integer> stack = new Stack<>(); // Store indices
        
        for (int i = 0; i < n; i++) {
            // Pop indices with smaller or equal price
            while (!stack.isEmpty() && prices[stack.peek()] <= prices[i]) {
                stack.pop();
            }
            
            // Calculate span
            span[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
            
            stack.push(i);
        }
        
        return span;
    }
    
    public static void main(String[] args) {
        System.out.println("=== Stock Span Problem ===\n");
        
        int[] prices = {100, 80, 60, 70, 60, 75, 85};
        
        System.out.println("Prices: " + Arrays.toString(prices));
        System.out.println("\nOnline processing (using StockSpanner):\n");
        
        StockSpanner spanner = new StockSpanner();
        
        System.out.println(String.format("%-8s | %-8s | %-15s", 
            "Price", "Day", "Span"));
        System.out.println("-".repeat(35));
        
        for (int i = 0; i < prices.length; i++) {
            int spanValue = spanner.next(prices[i]);
            System.out.println(String.format("%-8d | %-8d | %-15d", 
                prices[i], i + 1, spanValue));
        }
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("DETAILED EXAMPLE: [100, 80, 60, 70, 60, 75, 85]");
        System.out.println("-".repeat(70));
        
        prices = new int[]{100, 80, 60, 70, 60, 75, 85};
        int n = prices.length;
        int[] span = new int[n];
        Stack<Integer> stack = new Stack<>();
        
        System.out.println("\nStep-by-step processing:\n");
        
        for (int i = 0; i < n; i++) {
            System.out.println("Day " + (i + 1) + ": Price = " + prices[i]);
            System.out.println("  Stack before (indices): " + stack);
            
            int popCount = 0;
            while (!stack.isEmpty() && prices[stack.peek()] <= prices[i]) {
                int popped = stack.pop();
                System.out.println("  Pop index " + popped + " (price " + prices[popped] + " ≤ " + prices[i] + ")");
                popCount++;
            }
            
            if (popCount == 0) {
                System.out.println("  No pops");
            }
            
            if (stack.isEmpty()) {
                span[i] = i + 1;
                System.out.println("  Stack empty → Span = " + i + " + 1 = " + (i + 1));
            } else {
                int prevGreaterIdx = stack.peek();
                span[i] = i - prevGreaterIdx;
                System.out.println("  Previous greater price at index " + prevGreaterIdx + 
                                 " → Span = " + i + " - " + prevGreaterIdx + " = " + span[i]);
            }
            
            stack.push(i);
            System.out.println("  Push index " + i);
            System.out.println("  Stack after (indices): " + stack);
            System.out.println();
        }
        
        System.out.println("Result: " + Arrays.toString(span));
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("SPAN EXPLANATION:");
        System.out.println("-".repeat(70));
        
        prices = new int[]{100, 80, 60, 70, 60, 75, 85};
        span = calculateSpans(prices);
        
        System.out.println("\nFor each price, span = consecutive days before with price ≤ current:\n");
        System.out.println(String.format("%-8s | %-30s | %-8s", 
            "Price", "Consecutive days", "Span"));
        System.out.println("-".repeat(50));
        
        String[] explanations = {
            "First day",
            "Previous: 100 > 80, stop",
            "Previous: 80 > 60, stop",
            "Days: 60(≤70), 80>70",
            "Previous: 70 > 60, stop",
            "Days: 60(≤75), 70(≤75), 80>75",
            "Days: 75,60,70,60,80,100 all ≤85"
        };
        
        for (int i = 0; i < prices.length; i++) {
            System.out.println(String.format("%-8d | %-30s | %-8d", 
                prices[i], explanations[i], span[i]));
        }
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("MORE TEST CASES:");
        System.out.println("-".repeat(70));
        
        int[][] testCases = {
            {100, 80, 60, 70, 60, 75, 85},
            {100, 100, 100, 100},
            {1, 2, 3, 4, 5},
            {5, 4, 3, 2, 1}
        };
        
        for (int[] test : testCases) {
            int[] result = calculateSpans(test);
            System.out.println("Prices: " + Arrays.toString(test));
            System.out.println("Spans:  " + Arrays.toString(result) + "\n");
        }
        
        System.out.println("=".repeat(70));
        System.out.println("MONOTONIC STACK PATTERN:");
        System.out.println("-".repeat(70));
        System.out.println("Stack maintains indices in DECREASING order of prices");
        System.out.println("For each price:");
        System.out.println("  1. Pop all prices ≤ current (they can't be previous greater)");
        System.out.println("  2. If stack empty: all previous prices ≤ current");
        System.out.println("  3. If stack not empty: top is previous greater price");
        System.out.println("  4. Span = current_index - previous_greater_index");
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("ONLINE vs OFFLINE:");
        System.out.println("-".repeat(70));
        System.out.println("Online (StockSpanner):");
        System.out.println("  - Process prices one at a time");
        System.out.println("  - Call next(price) for each new price");
        System.out.println("  - Returns span immediately");
        System.out.println("  - Perfect for real-time stock data");
        
        System.out.println("\nOffline:");
        System.out.println("  - Process all prices at once");
        System.out.println("  - Batch processing");
        System.out.println("  - Same O(n) complexity");
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("KEY INSIGHTS:");
        System.out.println("-".repeat(70));
        System.out.println("1. Span = days consecutive before with price ≤ current");
        System.out.println("2. Use monotonic stack with DECREASING prices");
        System.out.println("3. Pop ≤ current (they block the span)");
        System.out.println("4. Top of stack = previous greater price");
        System.out.println("5. Time: O(n) - each element pushed/popped once");
        System.out.println("6. Space: O(n) - stack stores pairs or indices");
    }
}
