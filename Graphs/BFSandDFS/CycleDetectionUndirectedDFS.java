package Graphs.BFSandDFS;

import java.util.*;

/**
 * Cycle Detection in Undirected Graph (DFS)
 *
 * Problem:
 * Detect cycle in an undirected graph using DFS.
 *
 * Approach:
 * - Run DFS keeping track of the parent of each node.
 * - If a neighbor is visited and not equal to parent, we found a cycle.
 *
 * Example:
 * Graph: 0-1, 1-2, 2-0 -> DFS from 0 탐색 will find neighbor 2 visited and not parent -> cycle.
 *
 * Complexity: Time O(V+E), Space O(V) recursion stack.
 */

public class CycleDetectionUndirectedDFS {

    public boolean hasCycle(int V, List<List<Integer>> adj) {
        boolean[] vis = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                if (dfs(i, -1, vis, adj)) return true;
            }
        }
        return false;
    }

    private boolean dfs(int node, int parent, boolean[] vis, List<List<Integer>> adj) {
        vis[node] = true;
        for (int nei : adj.get(node)) {
            if (!vis[nei]) {
                if (dfs(nei, node, vis, adj)) return true;
            } else if (nei != parent) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int V = 4;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
        adj.get(0).add(1); adj.get(1).add(0);
        adj.get(1).add(2); adj.get(2).add(1);
        adj.get(2).add(0); adj.get(0).add(2);

        CycleDetectionUndirectedDFS sol = new CycleDetectionUndirectedDFS();
        System.out.println("Has cycle (DFS): " + sol.hasCycle(V, adj));
    }
}