package sliding_window_two_pointer.medium;

import java.util.*;

/**
 * Maximum Points You Can Obtain From Cards
 * 
 * Problem: There are several cards arranged in a row and indexed from 0 to n-1.
 *          You can choose exactly k cards starting from either the beginning
 *          or the end of the row (but not both simultaneously in one turn).
 *          Find the maximum sum of points you can obtain by taking cards.
 * 
 * Example: cardPoints=[1,2,3,4,5], k=2 → 10 (take 5,4 or 1+5)
 *          cardPoints=[1,2,3,4,5], k=1 → 5 (take 5)
 *          cardPoints=[2,2,2], k=2 → 4 (take any 2)
 *          cardPoints=[100,40,17,9,73,75], k=3 → 248 (100+75+73)
 * 
 * Algorithm: Take from Both Ends - Use Complement
 * ================================================
 * Key Insight: Taking i cards from left + j cards from right (i+j=k)
 *              is equivalent to leaving (n-k) cards in the middle untouched
 * 
 * Approach:
 * 1. Total sum of k cards = (total of first i) + (total of last j)
 *                          = total_sum - (sum of middle n-k cards)
 * 2. Problem becomes: find minimum sum of (n-k) consecutive cards
 * 3. Use sliding window to find minimum sum window
 * 4. Answer = total_sum - min_window_sum
 * 
 * Alternative: Pre-compute all possible combinations
 * - Left[i] = sum of first i cards
 * - Right[j] = sum of last j cards
 * - For each i from 0 to k: best = max(Left[i] + Right[k-i])
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n) for precomputation, O(1) for sliding window
 */

public class MaximumPointsYouCanObtainFromCards {
    
    /**
     * Approach 1: Sliding Window (Find minimum middle n-k cards)
     */
    public static int maxScore_SlidingWindow(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int windowSize = n - k;  // Size of middle window
        
        // Calculate total sum
        int totalSum = 0;
        for (int point : cardPoints) {
            totalSum += point;
        }
        
        // Find minimum sum of windowSize consecutive cards
        int windowSum = 0;
        for (int i = 0; i < windowSize; i++) {
            windowSum += cardPoints[i];
        }
        
        int minWindowSum = windowSum;
        
        // Slide the window
        for (int i = windowSize; i < n; i++) {
            windowSum = windowSum - cardPoints[i - windowSize] + cardPoints[i];
            minWindowSum = Math.min(minWindowSum, windowSum);
        }
        
        return totalSum - minWindowSum;
    }
    
    /**
     * Approach 2: Pre-compute prefix and suffix sums
     */
    public static int maxScore_PreCompute(int[] cardPoints, int k) {
        int n = cardPoints.length;
        
        // Prefix sum from left
        int[] leftSum = new int[k + 1];
        for (int i = 0; i < k; i++) {
            leftSum[i + 1] = leftSum[i] + cardPoints[i];
        }
        
        // Prefix sum from right
        int[] rightSum = new int[k + 1];
        for (int i = 0; i < k; i++) {
            rightSum[i + 1] = rightSum[i] + cardPoints[n - 1 - i];
        }
        
        // Find maximum combination
        int maxPoints = 0;
        for (int i = 0; i <= k; i++) {
            int points = leftSum[i] + rightSum[k - i];
            maxPoints = Math.max(maxPoints, points);
        }
        
        return maxPoints;
    }
    
    public static void main(String[] args) {
        System.out.println("=== Maximum Points You Can Obtain From Cards ===\n");
        
        int[][] cardPointss = {
            {1, 2, 3, 4, 5},
            {1, 2, 3, 4, 5},
            {2, 2, 2},
            {100, 40, 17, 9, 73, 75},
            {1},
            {1, 1000, 1}
        };
        
        int[] ks = {2, 1, 2, 3, 1, 1};
        
        System.out.println(String.format("%-35s | %-5s | %-10s", 
            "Cards", "k", "Max Points"));
        System.out.println("-".repeat(55));
        
        for (int i = 0; i < cardPointss.length; i++) {
            int result = maxScore_SlidingWindow(cardPointss[i], ks[i]);
            System.out.println(String.format("%-35s | %-5d | %-10d", 
                Arrays.toString(cardPointss[i]), ks[i], result));
        }
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("DETAILED EXAMPLE 1: cardPoints=[1,2,3,4,5], k=2");
        System.out.println("-".repeat(70));
        
        int[] cards = {1, 2, 3, 4, 5};
        int k = 2;
        
        System.out.println("\nArray: " + Arrays.toString(cards));
        System.out.println("k = " + k);
        System.out.println("n = " + cards.length);
        
        System.out.println("\nApproach 1: Sliding Window (minimize middle)");
        System.out.println("--------------------------------------------");
        System.out.println("Window size (middle cards to ignore): n - k = " + (cards.length - k));
        
        int totalSum = 0;
        for (int c : cards) {
            totalSum += c;
        }
        System.out.println("Total sum: " + totalSum);
        
        int windowSize = cards.length - k;
        int windowSum = 0;
        for (int i = 0; i < windowSize; i++) {
            windowSum += cards[i];
        }
        System.out.println("Initial window [0," + (windowSize - 1) + "]: " + 
            Arrays.toString(Arrays.copyOfRange(cards, 0, windowSize)) + 
            " → sum = " + windowSum);
        
        int minWindowSum = windowSum;
        System.out.println("\nSliding window:");
        
        for (int i = windowSize; i < cards.length; i++) {
            int removed = cards[i - windowSize];
            int added = cards[i];
            windowSum = windowSum - removed + added;
            System.out.println("[" + (i - windowSize + 1) + "," + i + "]: remove " + removed + 
                             ", add " + added + " → " + 
                             Arrays.toString(Arrays.copyOfRange(cards, i - windowSize + 1, i + 1)) + 
                             " → sum = " + windowSum);
            minWindowSum = Math.min(minWindowSum, windowSum);
        }
        
        System.out.println("\nMin middle sum: " + minWindowSum);
        System.out.println("Max points = total - min_middle = " + totalSum + " - " + minWindowSum + 
                         " = " + (totalSum - minWindowSum));
        
        System.out.println("\nApproach 2: Pre-compute left and right combinations");
        System.out.println("---------------------------------------------------");
        
        int[] leftSum = new int[k + 1];
        System.out.println("\nLeft sums (take i from left):");
        for (int i = 0; i <= k; i++) {
            if (i == 0) {
                System.out.println("i=" + i + ": sum = 0 (take 0 from left)");
            } else {
                int sum = 0;
                for (int j = 0; j < i; j++) {
                    sum += cards[j];
                }
                leftSum[i] = sum;
                System.out.println("i=" + i + ": sum = " + 
                    Arrays.toString(Arrays.copyOfRange(cards, 0, i)) + 
                    " = " + sum);
            }
        }
        
        int[] rightSum = new int[k + 1];
        System.out.println("\nRight sums (take j from right):");
        for (int i = 0; i <= k; i++) {
            if (i == 0) {
                System.out.println("j=" + i + ": sum = 0 (take 0 from right)");
            } else {
                int sum = 0;
                for (int j = 0; j < i; j++) {
                    sum += cards[cards.length - 1 - j];
                }
                rightSum[i] = sum;
                System.out.println("j=" + i + ": sum = " + 
                    Arrays.toString(Arrays.copyOfRange(cards, cards.length - i, cards.length)) + 
                    " = " + sum);
            }
        }
        
        System.out.println("\nCombinations (i left + j right, i+j=k):");
        int maxPoints = 0;
        for (int i = 0; i <= k; i++) {
            int j = k - i;
            int points = leftSum[i] + rightSum[j];
            System.out.println("i=" + i + ", j=" + j + ": " + leftSum[i] + " + " + rightSum[j] + " = " + points);
            maxPoints = Math.max(maxPoints, points);
        }
        System.out.println("Max: " + maxPoints);
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("DETAILED EXAMPLE 2: cardPoints=[100,40,17,9,73,75], k=3");
        System.out.println("-".repeat(70));
        
        cards = new int[]{100, 40, 17, 9, 73, 75};
        k = 3;
        
        System.out.println("\nArray: " + Arrays.toString(cards));
        System.out.println("Take k=" + k + " cards from either end");
        
        System.out.println("\nPossible combinations:");
        System.out.println("- Take 0 from left, 3 from right: " + cards[5] + "+" + cards[4] + "+" + cards[3] + " = " + (cards[5]+cards[4]+cards[3]));
        System.out.println("- Take 1 from left, 2 from right: " + cards[0] + "+" + cards[5] + "+" + cards[4] + " = " + (cards[0]+cards[5]+cards[4]));
        System.out.println("- Take 2 from left, 1 from right: " + cards[0] + "+" + cards[1] + "+" + cards[5] + " = " + (cards[0]+cards[1]+cards[5]));
        System.out.println("- Take 3 from left, 0 from right: " + cards[0] + "+" + cards[1] + "+" + cards[2] + " = " + (cards[0]+cards[1]+cards[2]));
        
        maxPoints = 0;
        for (int i = 0; i <= k; i++) {
            int sum = 0;
            for (int j = 0; j < i; j++) {
                sum += cards[j];
            }
            for (int j = 0; j < k - i; j++) {
                sum += cards[cards.length - 1 - j];
            }
            maxPoints = Math.max(maxPoints, sum);
        }
        System.out.println("\nMaximum: " + maxPoints);
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("ALGORITHM EXPLANATION:");
        System.out.println("-".repeat(70));
        System.out.println("Key: Taking k cards from ends = taking n-k cards from middle");
        System.out.println("\nMethod 1: Sliding Window (minimize the excluded middle)");
        System.out.println("1. Find minimum sum of n-k consecutive cards");
        System.out.println("2. Maximum points = total_sum - minimum_middle_sum");
        System.out.println("3. Efficient: O(n) with single pass");
        System.out.println("\nMethod 2: Prefix/Suffix Precomputation");
        System.out.println("1. Precompute left[i] = sum of first i cards");
        System.out.println("2. Precompute right[j] = sum of last j cards");
        System.out.println("3. Try all combinations: i+j=k");
        System.out.println("4. Return maximum");
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("KEY POINTS:");
        System.out.println("-".repeat(70));
        System.out.println("1. Taking k from ends = excluding n-k from middle");
        System.out.println("2. Minimize middle to maximize edges");
        System.out.println("3. Sliding window finds minimum window easily");
        System.out.println("4. Method 1 time: O(n), space: O(1)");
        System.out.println("5. Method 2 time: O(n), space: O(k)");
        System.out.println("6. Both are optimal!");
        System.out.println("7. Problem constraint: must take from ends, not middle");
    }
}
