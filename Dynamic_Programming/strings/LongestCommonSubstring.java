package Dynamic_Programming.strings;

/**
 * LONGEST COMMON SUBSTRING (DP-27)
 *
 * Problem:
 * Find the longest substring (continuous) common to two strings.
 *
 * Approach:
 * dp[i][j] = length of longest common suffix of s[0..i-1] and t[0..j-1]. If s[i-1]==t[j-1],
 * dp[i][j] = 1 + dp[i-1][j-1], else 0. Track maximum and ending position.
 *
 * Complexity: O(n*m) time and O(n*m) space (can be optimized to O(min(n,m))).
 */
public class LongestCommonSubstring {

    public static String lcsSubstring(String s, String t) {
        int n = s.length(), m = t.length();
        int[][] dp = new int[n + 1][m + 1];
        int best = 0, end = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    if (dp[i][j] > best) { best = dp[i][j]; end = i; }
                }
            }
        }
        return s.substring(end - best, end);
    }

    public static void main(String[] args) {
        System.out.println(lcsSubstring("abcdxyz", "xyzabcd")); // expected "abcd" or "xyz"
    }
}
