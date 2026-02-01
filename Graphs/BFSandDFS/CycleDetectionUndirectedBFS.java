package Graphs.BFSandDFS;

import java.util.*;

/**
 * Cycle Detection in Undirected Graph (BFS)
 *
 * Problem:
 * Determine whether an undirected graph contains a cycle. Useful for verifying tree vs non-tree
 * structure and for detecting redundant connections.
 *
 * Approach:
 * - Use BFS starting from each unvisited node and track the parent of each visited node.
 * - For every neighbor encountered during BFS: if it's unvisited, mark it and set parent; if it is visited
 *   and not the parent of the current node, we have found a cycle.
 *
 * Example:
 * Edges: 0-1, 1-2, 2-0 -> Starting BFS at 0 visits 1, then 2. From 2 we see neighbor 0 visited and not parent => cycle true.
 * For a simple chain 0-1-2 there will be no such conflict -> false.
 *
 * Complexity: Time O(V+E), Space O(V).
 */

public class CycleDetectionUndirectedBFS {

    public boolean hasCycle(int V, List<List<Integer>> adj) {
        boolean[] vis = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                if (bfs(i, V, adj, vis)) return true;
            }
        }
        return false;
    }

    private boolean bfs(int src, int V, List<List<Integer>> adj, boolean[] vis) {
        // Queue stores pairs {node, parent} to allow detection of cross-edges that indicate cycles
        Queue<int[]> q = new LinkedList<>();
        vis[src] = true;
        q.add(new int[]{src, -1});
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int node = cur[0], parent = cur[1];
            for (int nei : adj.get(node)) {
                if (!vis[nei]) {
                    vis[nei] = true;
                    q.add(new int[]{nei, node});
                } else if (nei != parent) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        // Example
        int V = 5;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
        // edges: 0-1-2, 2-0 forms cycle
        adj.get(0).add(1); adj.get(1).add(0);
        adj.get(1).add(2); adj.get(2).add(1);
        adj.get(2).add(0); adj.get(0).add(2);
        adj.get(3).add(4); adj.get(4).add(3);

        CycleDetectionUndirectedBFS sol = new CycleDetectionUndirectedBFS();
        System.out.println("Has cycle (BFS): " + sol.hasCycle(V, adj));
    }
}