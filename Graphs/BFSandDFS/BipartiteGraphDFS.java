package Graphs.BFSandDFS;

import java.util.*;

/**
 * Bipartite Graph Check (DFS)
 *
 * Problem:
 * Determine whether an undirected graph is bipartite (i.e., whether its vertices can be
 * colored using two colors so that no edge connects vertices of the same color).
 *
 * Approach:
 * - Use DFS to assign alternating colors (1 and -1) to neighbors.
 * - For every vertex that is uncolored, start a DFS (this ensures disconnected graphs are handled).
 * - If during DFS we encounter a neighbor with the same color, a conflict is found and the graph is not bipartite.
 *
 * Example:
 * For edges: 0-1, 1-2, 2-3 -> colors {0:1,1:-1,2:1,3:-1} => bipartite (true).
 * If we add 2-0 then 0 and 2 both become color 1 -> conflict -> not bipartite (false).
 *
 * Complexity: Time O(V+E), Space O(V) for recursion and color array.
 */

public class BipartiteGraphDFS {

    public boolean isBipartite(int V, List<List<Integer>> adj) {
        // color[i] : 0 -> uncolored, 1 or -1 -> alternate colors used for partitioning.
        // Start DFS from every uncolored node to cover disconnected components.
        int[] color = new int[V];
        for (int i = 0; i < V; i++) {
            if (color[i] == 0) {
                if (!dfs(i, 1, color, adj)) return false;
            }
        }
        return true;
    }

    private boolean dfs(int node, int c, int[] color, List<List<Integer>> adj) {
        color[node] = c;
        for (int nei : adj.get(node)) {
            if (color[nei] == 0) {
                if (!dfs(nei, -c, color, adj)) return false;
            } else if (color[nei] == c) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int V = 4;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
        adj.get(0).add(1); adj.get(1).add(0);
        adj.get(1).add(2); adj.get(2).add(1);
        adj.get(2).add(3); adj.get(3).add(2);
        BipartiteGraphDFS sol = new BipartiteGraphDFS();
        System.out.println("Is bipartite: " + sol.isBipartite(V, adj));
    }
}
