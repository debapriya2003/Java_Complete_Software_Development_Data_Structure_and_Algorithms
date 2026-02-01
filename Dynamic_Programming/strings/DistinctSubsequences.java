package Dynamic_Programming.strings;

/**
 * DISTINCT SUBSEQUENCES (DP-32)
 *
 * Problem:
 * Given strings s and t, count distinct subsequences of s equal to t.
 *
 * Approach:
 * dp[i][j] = number of ways to form t[0..j-1] from s[0..i-1].
 * dp[0][0]=1, dp[i][0]=1 (empty target). Transition:
 * if s[i-1]==t[j-1] => dp[i][j] = dp[i-1][j] + dp[i-1][j-1]
 * else dp[i][j] = dp[i-1][j]
 *
 * Complexity: O(n*m) time and O(m) space possible (we show 2D for clarity).
 */
public class DistinctSubsequences {

    public static long numDistinct(String s, String t) {
        int n = s.length(), m = t.length();
        long[][] dp = new long[n + 1][m + 1];
        for (int i = 0; i <= n; i++) dp[i][0] = 1; // empty target
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                dp[i][j] = dp[i - 1][j];
                if (s.charAt(i - 1) == t.charAt(j - 1)) dp[i][j] += dp[i - 1][j - 1];
            }
        }
        return dp[n][m];
    }

    public static void main(String[] args) {
        System.out.println(numDistinct("rabbbit", "rabbit")); // expected 3
    }
}
