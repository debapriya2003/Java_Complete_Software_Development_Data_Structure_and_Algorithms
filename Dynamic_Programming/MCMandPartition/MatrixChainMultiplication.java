package Dynamic_Programming.MCMandPartition;

import java.util.*;

/**
 * MATRIX CHAIN MULTIPLICATION (DP-48)
 *
 * Problem:
 * Given a sequence of matrices with dimensions p0 x p1, p1 x p2, ..., pn-1 x pn, compute the
 * minimum number of scalar multiplications needed to compute the product M1*M2*...*Mn.
 *
 * Approach:
 * Top-down recursive DP (memoization): define dp[i][j] as minimum cost to multiply matrices i..j
 * (1-based). Try splitting at k: cost = dp[i][k] + dp[k+1][j] + p[i-1]*p[k]*p[j]. Use memo to avoid recomputation.
 *
 * Complexity: O(n^3) time, O(n^2) space.
 */
public class MatrixChainMultiplication {

    private int[][] memo;
    private int[] p;

    public int mcm(int[] p) {
        int n = p.length - 1; // number of matrices
        this.p = p;
        memo = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) Arrays.fill(memo[i], -1);
        return solve(1, n);
    }

    private int solve(int i, int j) {
        if (i >= j) return 0;
        if (memo[i][j] != -1) return memo[i][j];
        int best = Integer.MAX_VALUE;
        for (int k = i; k < j; k++) {
            int left = solve(i, k);
            int right = solve(k + 1, j);
            int cost = left + right + p[i - 1] * p[k] * p[j];
            best = Math.min(best, cost);
        }
        return memo[i][j] = best;
    }

    public static void main(String[] args) {
        MatrixChainMultiplication sol = new MatrixChainMultiplication();
        int[] p = {40,20,30,10,30}; // matrices: 40x20,20x30,30x10,10x30
        System.out.println(sol.mcm(p)); // expected 26000
    }
}
