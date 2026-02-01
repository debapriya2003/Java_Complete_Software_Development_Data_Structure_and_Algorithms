package Graphs.BFSandDFS;

import java.util.*;

/**
 * Number of Provinces (Friend Circles)
 * Problem: Count connected components in an adjacency matrix (undirected graph).
 * Approach: DFS to mark all nodes in a component; increment count for each unvisited node.
 * Time Complexity: O(n^2) for adjacency matrix traversal
 * Space Complexity: O(n)
 */

@SuppressWarnings("unused")
public class NumberOfProvinces {

    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        boolean[] vis = new boolean[n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                count++;
                dfs(i, isConnected, vis);
            }
        }
        return count;
    }

    private void dfs(int node, int[][] g, boolean[] vis) {
        // Mark current node and explore all directly connected nodes (matrix row)
        vis[node] = true;
        for (int nei = 0; nei < g.length; nei++) {
            if (g[node][nei] == 1 && !vis[nei]) {
                dfs(nei, g, vis);
            }
        }
    }

    public static void main(String[] args) {
        NumberOfProvinces sol = new NumberOfProvinces();
        int[][] isConnected = {{1,1,0},{1,1,0},{0,0,1}};
        System.out.println("Number of Provinces: " + sol.findCircleNum(isConnected));
    }
}
