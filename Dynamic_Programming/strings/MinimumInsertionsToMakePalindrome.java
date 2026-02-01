package Dynamic_Programming.strings;

/**
 * MINIMUM INSERTIONS TO MAKE STRING PALINDROME (DP-29)
 *
 * Problem:
 * Minimum number of insertions needed to make a string palindrome.
 *
 * Approach:
 * Equivalent to n - LPS(s) where LPS is longest palindromic subsequence length. Compute LPS
 * using DP (or LCS with reversed string) and subtract from n.
 *
 * Complexity: O(n^2) time and O(n^2) space.
 */
public class MinimumInsertionsToMakePalindrome {

    public static int minInsertions(String s) {
        int n = s.length();
        if (n == 0) return 0;
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) dp[i][j] = 2 + (j == i + 1 ? 0 : dp[i + 1][j - 1]);
                else dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
            }
        }
        return n - dp[0][n - 1];
    }

    public static void main(String[] args) {
        System.out.println(minInsertions("abcda")); // expected 2 (make "adcbcda" or similar)
    }
}
