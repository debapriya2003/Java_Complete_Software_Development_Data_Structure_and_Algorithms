package Dynamic_Programming.MCMandPartition;

import java.util.*;

/**
 * EVALUATE BOOLEAN EXPRESSION TO TRUE (DP-52)
 *
 * Problem:
 * Given string of 0/1 operands and operators &|^, count ways to parenthesize to evaluate to true.
 *
 * Approach:
 * DP counting on intervals: dpTrue[i][j] and dpFalse[i][j] store counts for substring i..j (operands at even positions).
 * For each operator split k, combine counts based on operator semantics.
 *
 * Complexity: O(n^3) time where n = number of operands, O(n^2) space.
 */
public class EvaluateBooleanExpressionTrue {

    public static long countWays(String s) {
        // Expect form like: 1^0|0|1
        List<Character> ops = new ArrayList<>();
        List<Integer> vals = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '0' || c == '1') vals.add(c - '0');
            else ops.add(c);
        }
        int n = vals.size();
        long[][] T = new long[n][n];
        long[][] F = new long[n][n];
        for (int i = 0; i < n; i++) T[i][i] = vals.get(i) == 1 ? 1 : 0;
        for (int i = 0; i < n; i++) F[i][i] = vals.get(i) == 0 ? 1 : 0;
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                T[i][j] = F[i][j] = 0;
                for (int k = i; k < j; k++) {
                    char op = ops.get(k);
                    long lt = T[i][k], lf = F[i][k];
                    long rt = T[k + 1][j], rf = F[k + 1][j];
                    if (op == '&') {
                        T[i][j] += lt * rt;
                        F[i][j] += lt * rf + lf * rt + lf * rf;
                    } else if (op == '|') {
                        T[i][j] += lt * rt + lt * rf + lf * rt;
                        F[i][j] += lf * rf;
                    } else if (op == '^') {
                        T[i][j] += lt * rf + lf * rt;
                        F[i][j] += lt * rt + lf * rf;
                    }
                }
            }
        }
        return T[0][n - 1];
    }

    public static void main(String[] args) {
        System.out.println(countWays("1^0|0|1")); // expected 3
    }
}
