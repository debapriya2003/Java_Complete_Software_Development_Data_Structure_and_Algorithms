package Dynamic_Programming.strings;

/**
 * MIN INSERTIONS / DELETIONS TO CONVERT STRING (DP-30)
 *
 * Problem:
 * Given two strings s and t, find minimum number of insertions and deletions to convert s -> t.
 *
 * Approach:
 * Let L = LCS(s, t). Then:
 * - deletions = s.length() - L
 * - insertions = t.length() - L
 *
 * Complexity: O(n*m) time, using LCS DP.
 */
public class MinInsertionsDeletionsConvertString {

    private static int lcs(String s, String t) {
        int n = s.length(), m = t.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) for (int j = 1; j <= m; j++)
            if (s.charAt(i - 1) == t.charAt(j - 1)) dp[i][j] = 1 + dp[i - 1][j - 1];
            else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        return dp[n][m];
    }

    public static int[] minInsDel(String s, String t) {
        int L = lcs(s, t);
        return new int[]{s.length() - L, t.length() - L}; // {deletions, insertions}
    }

    public static void main(String[] args) {
        int[] res = minInsDel("heap", "pea");
        System.out.println("deletions=" + res[0] + " insertions=" + res[1]); // deletions=1 insertions=1
    }
}
