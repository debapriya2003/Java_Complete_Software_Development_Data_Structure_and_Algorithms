package Dynamic_Programming.strings;

/**
 * WILDCARD MATCHING (DP-34)
 *
 * Problem:
 * Given s and a pattern p where '?' matches any single character and '*' matches any sequence (including empty),
 * determine if pattern matches the entire string.
 *
 * Approach:
 * Use DP table dp[i][j] meaning first i chars of s match first j chars of p. Handle '*' specially
 * since it can match empty (dp[i][j-1]) or one more char (dp[i-1][j]).
 *
 * Complexity: O(n*m) time and O(n*m) space.
 */
public class WildcardMatching {

    public static boolean isMatch(String s, String p) {
        int n = s.length(), m = p.length();
        boolean[][] dp = new boolean[n + 1][m + 1];
        dp[0][0] = true;
        // Patterns like ** can match empty
        for (int j = 1; j <= m; j++) if (p.charAt(j - 1) == '*') dp[0][j] = dp[0][j - 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                char pc = p.charAt(j - 1);
                if (pc == '?') dp[i][j] = dp[i - 1][j - 1];
                else if (pc == '*') dp[i][j] = dp[i][j - 1] || dp[i - 1][j]; // empty or consume one char
                else dp[i][j] = (s.charAt(i - 1) == pc) && dp[i - 1][j - 1];
            }
        }
        return dp[n][m];
    }

    public static void main(String[] args) {
        System.out.println(isMatch("aa", "a")); // false
        System.out.println(isMatch("aa", "*")); // true
        System.out.println(isMatch("cb", "?b")); // true
    }
}
