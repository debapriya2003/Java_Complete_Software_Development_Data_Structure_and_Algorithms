package Dynamic_Programming.strings;

/**
 * LONGEST PALINDROMIC SUBSEQUENCE (DP-28)
 *
 * Problem:
 * Given a string, find the length (or sequence) of the longest palindromic subsequence.
 *
 * Approach:
 * Standard DP: dp[i][j] = length of LPS in s[i..j]. For i==j dp=1; if s[i]==s[j] dp=2+dp[i+1][j-1]
 * else dp=max(dp[i+1][j], dp[i][j-1]). We return dp[0][n-1]. Alternatively LPS = LCS(s, reverse(s)).
 *
 * Complexity: O(n^2) time and O(n^2) space.
 */
public class LongestPalindromicSubsequence {

    public static int lps(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) dp[i][j] = 2 + (j == i + 1 ? 0 : dp[i + 1][j - 1]);
                else dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
            }
        }
        return n == 0 ? 0 : dp[0][n - 1];
    }

    public static void main(String[] args) {
        System.out.println(lps("bbbab")); // expected 4 (bbbb)
    }
}