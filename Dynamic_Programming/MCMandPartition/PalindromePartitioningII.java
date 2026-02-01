package Dynamic_Programming.MCMandPartition;

import java.util.*;

/**
 * PALINDROME PARTITIONING II (DP-53)
 *
 * Problem:
 * Given a string s, partition it into minimum number of cuts so that every substring is a palindrome.
 *
 * Approach:
 * Precompute palindrome table isPal[i][j]. Then dp[i] = min cuts for prefix s[0..i]. For each j<=i,
 * if isPal[j][i], dp[i] = min(dp[i], (j==0 ? 0 : dp[j-1]+1)). Final answer is dp[n-1].
 *
 * Complexity: O(n^2) time, O(n^2) space.
 */
public class PalindromePartitioningII {

    public static int minCut(String s) {
        int n = s.length();
        boolean[][] isPal = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i < 2 || isPal[i + 1][j - 1])) isPal[i][j] = true;
            }
        }
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        for (int i = 0; i < n; i++) {
            if (isPal[0][i]) dp[i] = 0;
            else {
                for (int j = 1; j <= i; j++) if (isPal[j][i]) dp[i] = Math.min(dp[i], dp[j - 1] + 1);
            }
        }
        return n == 0 ? 0 : dp[n - 1];
    }

    public static void main(String[] args) {
        System.out.println(minCut("aab")); // 1 ("aa"|"b")
    }
}
