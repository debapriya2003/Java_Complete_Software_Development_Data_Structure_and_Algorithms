package Graphs.BFSandDFS;

/**
 * Number of Enclaves
 * Problem: Count land cells that cannot reach boundary (use multisource flood-fill from boundary lands to sink them).
 * Approach: Run DFS from all boundary land cells to mark/sink reachable land, then count remaining land cells.
 * Time Complexity: O(n*m)
 * Space Complexity: O(n*m)
 */

public class NumberOfEnclaves {

    public int numEnclaves(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        for (int i = 0; i < n; i++) {
            if (grid[i][0] == 1) dfs(i,0,grid);
            if (grid[i][m-1] == 1) dfs(i,m-1,grid);
        }
        for (int j = 0; j < m; j++) {
            if (grid[0][j] == 1) dfs(0,j,grid);
            if (grid[n-1][j] == 1) dfs(n-1,j,grid);
        }
        int cnt = 0;
        for (int i = 0; i < n; i++) for (int j = 0; j < m; j++) if (grid[i][j] == 1) cnt++;
        return cnt;
    }

    private void dfs(int i, int j, int[][] g) {
        int n = g.length, m = g[0].length;
        if (i < 0 || j < 0 || i >= n || j >= m || g[i][j] == 0) return;
        // Sink this land cell to mark it visited and prevent revisiting
        g[i][j] = 0; // mark visited by sinking
        dfs(i+1,j,g); dfs(i-1,j,g); dfs(i,j+1,g); dfs(i,j-1,g);
    }

    public static void main(String[] args) {
        NumberOfEnclaves sol = new NumberOfEnclaves();
        int[][] grid = {
            {0,0,0,0},
            {1,0,1,0},
            {0,1,1,0},
            {0,0,0,0}
        };
        System.out.println("Enclaves count: " + sol.numEnclaves(grid));
    }
}
