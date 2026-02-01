package Dynamic_Programming.strings;

/**
 * EDIT DISTANCE / LEVENSHTEIN (DP-33)
 *
 * Problem:
 * Given two strings s and t, compute minimum operations (insert, delete, replace) to convert s -> t.
 *
 * Approach:
 * dp[i][j] = min ops to convert s[0..i-1] to t[0..j-1]. Recurrence:
 * if equal: dp[i][j]=dp[i-1][j-1]
 * else dp[i][j] = 1 + min(dp[i-1][j] (delete), dp[i][j-1] (insert), dp[i-1][j-1] (replace)).
 *
 * Complexity: O(n*m) time and O(n*m) space.
 */
public class EditDistance {

    public static int minDistance(String s, String t) {
        int n = s.length(), m = t.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) dp[i][0] = i;
        for (int j = 0; j <= m; j++) dp[0][j] = j;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) dp[i][j] = dp[i - 1][j - 1];
                else dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
            }
        }
        return dp[n][m];
    }

    public static void main(String[] args) {
        System.out.println(minDistance("horse", "ros")); // expected 3
    }
}
