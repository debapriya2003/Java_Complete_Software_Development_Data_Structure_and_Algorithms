package Graphs.BFSandDFS;

/**
 * Surrounded Regions
 * Problem: Capture surrounded 'O' regions on board (flip surrounded O to X).
 * Approach: Flood-fill from border O's marking safe ones, then flip remaining O->X and marks back to O.
 * Time Complexity: O(n*m)
 * Space Complexity: O(n*m) recursion
 */

public class SurroundedRegions {

    public void solve(char[][] board) {
        int n = board.length;
        if (n == 0) return;
        int m = board[0].length;
        // Mark all 'O's reachable from the boundary as safe (temporary marker 'E')
        for (int i = 0; i < n; i++) {
            dfs(board, i, 0);
            dfs(board, i, m-1);
        }
        for (int j = 0; j < m; j++) {
            dfs(board, 0, j);
            dfs(board, n-1, j);
        }
        // Flip enclosed 'O' -> 'X' and restore safe 'E' -> 'O'
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'O') board[i][j] = 'X';
                else if (board[i][j] == 'E') board[i][j] = 'O';
            }
        }
    }

    private void dfs(char[][] b, int i, int j) {
        int n = b.length, m = b[0].length;
        if (i < 0 || j < 0 || i >= n || j >= m || b[i][j] != 'O') return;
        b[i][j] = 'E';
        dfs(b, i+1, j);
        dfs(b, i-1, j);
        dfs(b, i, j+1);
        dfs(b, i, j-1);
    }

    public static void main(String[] args) {
        SurroundedRegions sol = new SurroundedRegions();
        char[][] board = {
            {'X','X','X','X'},
            {'X','O','O','X'},
            {'X','X','O','X'},
            {'X','O','X','X'}
        };
        sol.solve(board);
        for (char[] row : board) System.out.println(java.util.Arrays.toString(row));
    }
}