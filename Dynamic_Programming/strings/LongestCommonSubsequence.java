package Dynamic_Programming.strings;

/**
 * LONGEST COMMON SUBSEQUENCE (DP-25)
 *
 * Problem:
 * Given two strings s and t, find length of the longest subsequence common to both.
 *
 * Approach:
 * Classic DP: dp[i][j] = length of LCS between s[0..i-1] and t[0..j-1].
 * Transition: if s.charAt(i-1)==t.charAt(j-1) then 1 + dp[i-1][j-1] else max(dp[i-1][j], dp[i][j-1]).
 * Use O(min(n,m)) space by keeping only two rows if needed, but here we show full table for clarity.
 *
 * Complexity: O(n*m) time, O(n*m) space (can be optimized to O(min(n,m))).
 */
public class LongestCommonSubsequence {

    public static int lcs(String s, String t) {
        int n = s.length(), m = t.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) dp[i][j] = 1 + dp[i - 1][j - 1];
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[n][m];
    }

    public static void main(String[] args) {
        System.out.println(lcs("ABCBDAB", "BDCABA")); // expected 4 (BCAB or BDAB etc.)
    }
}
