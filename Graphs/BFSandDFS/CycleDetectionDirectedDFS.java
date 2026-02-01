package Graphs.BFSandDFS;

import java.util.*;

/**
 * Cycle Detection in Directed Graph (DFS)
 * Problem: Detect cycle using DFS with recursion stack tracking (visited & recursion stack arrays).
 * Approach: If during DFS we reach a node currently in recursion stack -> cycle.
 * Time Complexity: O(V+E)
 * Space Complexity: O(V)
 */

public class CycleDetectionDirectedDFS {

    public boolean hasCycle(int V, List<List<Integer>> adj) {
        boolean[] vis = new boolean[V];
        boolean[] rec = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                if (dfs(i, vis, rec, adj)) return true;
            }
        }
        return false;
    }

    private boolean dfs(int node, boolean[] vis, boolean[] rec, List<List<Integer>> adj) {
        // Mark current node as visited and add to recursion stack
        vis[node] = true;
        rec[node] = true;
        for (int nei : adj.get(node)) {
            // If neighbor not visited, recurse; if neighbor in rec stack -> cycle
            if (!vis[nei]) {
                if (dfs(nei, vis, rec, adj)) return true;
            } else if (rec[nei]) {
                // Found an ancestor in current recursion path -> back edge -> cycle
                return true;
            }
        }
        // Remove node from recursion stack before returning
        rec[node] = false;
        return false;
    }

    public static void main(String[] args) {
        int V = 4;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
        adj.get(0).add(1);
        adj.get(1).add(2);
        adj.get(2).add(0); // cycle 0->1->2->0
        adj.get(2).add(3);
        CycleDetectionDirectedDFS sol = new CycleDetectionDirectedDFS();
        System.out.println("Has directed cycle: " + sol.hasCycle(V, adj));
    }
}
