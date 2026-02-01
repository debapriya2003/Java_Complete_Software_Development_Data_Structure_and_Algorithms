package Dynamic_Programming.strings;

import java.util.*;

/**
 * PRINT LONGEST COMMON SUBSEQUENCE (DP-26)
 *
 * Problem:
 * Apart from length, also reconstruct an actual LCS string.
 *
 * Approach:
 * Build the DP table as in LCS, then backtrack from dp[n][m] to reconstruct one LCS by
 * moving diagonally when characters match, otherwise move towards the larger neighbor.
 *
 * Complexity: O(n*m) time and O(n*m) space for the table.
 */
@SuppressWarnings("unused")
public class PrintLongestCommonSubsequence {

    public static String printLCS(String s, String t) {
        int n = s.length(), m = t.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 1; i <= n; i++) for (int j = 1; j <= m; j++) {
            if (s.charAt(i - 1) == t.charAt(j - 1)) dp[i][j] = 1 + dp[i - 1][j - 1];
            else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
        StringBuilder sb = new StringBuilder();
        int i = n, j = m;
        while (i > 0 && j > 0) {
            if (s.charAt(i - 1) == t.charAt(j - 1)) { sb.append(s.charAt(i - 1)); i--; j--; }
            else if (dp[i - 1][j] >= dp[i][j - 1]) i--; else j--;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(printLCS("ABCBDAB", "BDCABA")); // e.g. BCBA or BDAB
    }
}
