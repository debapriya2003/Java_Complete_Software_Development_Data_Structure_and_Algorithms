package Dynamic_Programming.Squares;

/**
 * COUNT SQUARE SUBMATRICES WITH ALL ONES (DP-56)
 *
 * Problem:
 * Given a binary matrix, return the number of square submatrices that contain all 1s.
 *
 * Approach:
 * DP: dp[i][j] = side length of largest square with bottom-right corner at (i,j).
 * If matrix[i][j] == 1, dp[i][j] = 1 + min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]). Sum all dp[i][j] values for answer.
 *
 * Complexity: O(rows * cols) time and O(rows * cols) space (can be optimized to O(cols)).
 *
 * Example:
 * matrix = [[0,1,1,1],[1,1,1,1],[0,1,1,1]] -> total squares = 15
 */
public class CountSquareSubmatricesWithAllOnes {

    public static int countSquares(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int n = matrix.length, m = matrix[0].length;
        int[][] dp = new int[n][m];
        int total = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 1) {
                    if (i == 0 || j == 0) dp[i][j] = 1;
                    else dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                    total += dp[i][j];
                }
            }
        }
        return total;
    }

    public static void main(String[] args) {
        int[][] mat = {{0,1,1,1},{1,1,1,1},{0,1,1,1}};
        System.out.println(countSquares(mat)); // expected 15
    }
}
