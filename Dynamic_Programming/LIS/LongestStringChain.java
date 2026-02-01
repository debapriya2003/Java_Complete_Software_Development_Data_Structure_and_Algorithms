package Dynamic_Programming.LIS;

import java.util.*;

/**
 * LONGEST STRING CHAIN (DP-45)
 *
 * Problem:
 * Given an array of words, each word's predecessor is a word formed by removing a single letter.
 * Find the length of the longest chain where each word is a predecessor of the next.
 *
 * Approach:
 * Sort words by length ascending. Use DP map: dp[word] = longest chain ending at word. For each
 * word, try removing one character at each position to form predecessor and update dp[word] = max(dp[word], dp[prev]+1).
 * Track maximum.
 *
 * Complexity: O(n * L^2) where L is max word length (since building predecessors costs O(L^2) across words), O(n) space.
 */
public class LongestStringChain {

    public static int longestStrChain(String[] words) {
        Arrays.sort(words, Comparator.comparingInt(String::length));
        Map<String, Integer> dp = new HashMap<>();
        int best = 1;
        for (String w : words) {
            int cur = 1;
            for (int i = 0; i < w.length(); i++) {
                String prev = w.substring(0, i) + w.substring(i + 1);
                cur = Math.max(cur, dp.getOrDefault(prev, 0) + 1);
            }
            dp.put(w, cur);
            best = Math.max(best, cur);
        }
        return best;
    }

    public static void main(String[] args) {
        System.out.println(longestStrChain(new String[]{"a","b","ba","bca","bda","bdca"})); // 4
    }
}
