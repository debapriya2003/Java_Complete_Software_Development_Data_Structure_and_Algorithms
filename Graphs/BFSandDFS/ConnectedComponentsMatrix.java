package Graphs.BFSandDFS;

/**
 * Connected Components in Matrix
 * Problem: Count number of connected components (islands) of 1s in a binary matrix using DFS.
 * Approach: DFS flood-fill from each unvisited cell with value 1 marking visited cells.
 * Time Complexity: O(n*m)
 * Space Complexity: O(n*m) recursion stack worst case
 */

public class ConnectedComponentsMatrix {

    public int countComponents(int[][] grid) {
        int n = grid.length;
        if (n == 0) return 0;
        int m = grid[0].length;
        boolean[][] vis = new boolean[n][m];
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1 && !vis[i][j]) {
                    count++;
                    dfs(i, j, grid, vis);
                }
            }
        }
        return count;
    }

    private void dfs(int i, int j, int[][] g, boolean[][] vis) {
        int n = g.length, m = g[0].length;
        // Boundary and visited checks
        if (i < 0 || j < 0 || i >= n || j >= m) return;
        if (vis[i][j] || g[i][j] == 0) return;
        // Mark current cell as visited and explore 4 directions
        vis[i][j] = true;
        dfs(i+1, j, g, vis);
        dfs(i-1, j, g, vis);
        dfs(i, j+1, g, vis);
        dfs(i, j-1, g, vis);
    }

    public static void main(String[] args) {
        ConnectedComponentsMatrix sol = new ConnectedComponentsMatrix();
        int[][] grid = {
            {1,0,0,1},
            {1,0,0,0},
            {0,0,1,1},
            {0,0,0,0}
        };
        System.out.println("Connected components: " + sol.countComponents(grid));
    }
}
