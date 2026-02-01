package Graphs.BFSandDFS;

import java.util.*;

/**
 * Number of Distinct Islands
 * Problem: Count distinct island shapes (shape normalized by coordinates relative to a base cell).
 * Approach: DFS to record relative positions of cells for each island and store canonical string in a set.
 * Time Complexity: O(n*m)
 * Space Complexity: O(n*m)
 */

public class NumberOfDistinctIslands {

    public int numDistinctIslands(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        boolean[][] vis = new boolean[n][m];
        Set<String> shapes = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!vis[i][j] && grid[i][j] == 1) {
                    StringBuilder sb = new StringBuilder();
                    dfs(i, j, i, j, grid, vis, sb);
                    shapes.add(sb.toString());
                }
            }
        }
        return shapes.size();
    }

    private void dfs(int i, int j, int baseI, int baseJ, int[][] g, boolean[][] vis, StringBuilder sb) {
        int n = g.length, m = g[0].length;
        if (i < 0 || j < 0 || i >= n || j >= m || vis[i][j] || g[i][j] == 0) return;
        vis[i][j] = true;
        // Record cell coordinate relative to base cell to capture island shape independent of location
        sb.append((i-baseI)+":"+(j-baseJ)+";" );
        // Explore 4-directional neighbors to collect full island shape
        dfs(i+1,j,baseI,baseJ,g,vis,sb);
        dfs(i-1,j,baseI,baseJ,g,vis,sb);
        dfs(i,j+1,baseI,baseJ,g,vis,sb);
        dfs(i,j-1,baseI,baseJ,g,vis,sb);
    }

    public static void main(String[] args) {
        NumberOfDistinctIslands sol = new NumberOfDistinctIslands();
        int[][] grid = {
            {1,1,0,0,0},
            {1,0,0,0,0},
            {0,0,0,1,1},
            {0,0,0,1,1}
        };
        System.out.println("Distinct islands: " + sol.numDistinctIslands(grid));
    }
}
