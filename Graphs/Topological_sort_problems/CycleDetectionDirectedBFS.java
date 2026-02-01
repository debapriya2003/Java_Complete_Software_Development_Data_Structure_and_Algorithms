package Graphs.Topological_sort_problems;

import java.util.*;

/**
 * Cycle Detection in Directed Graph using BFS (Kahn's Algorithm) — Detailed Explanation
 *
 * Objective: Detect if a directed graph has any cycle. The algorithm relies on topological sorting
 * (Kahn's algorithm) which processes nodes of indegree zero. In an acyclic graph, repeated removal
 * of indegree-zero nodes eventually removes all nodes. If nodes remain unprocessed, they must be part
 * of a cycle.
 *
 * Core Idea (with code-like steps):
 * 1) Compute indegree for every node: for (int u=0; u<V; u++) for (int v : adj.get(u)) indeg[v]++;
 * 2) Initialize queue with nodes of indegree 0: for (i) if (indeg[i]==0) q.add(i);
 * 3) Process queue: while (!q.isEmpty()) { int node = q.poll(); cnt++; for (nei : adj.get(node)) { indeg[nei]--; if (indeg[nei]==0) q.add(nei); } }
 * 4) If cnt != V, then some nodes could not be processed -> cycle exists.
 *
 * Note: This BFS-based cycle detection is very efficient and concise. It is particularly useful when
 * you must detect cycles while also computing a topological order (or verify its existence).
 * Complexity: Time O(V+E), Space O(V).
 */

public class CycleDetectionDirectedBFS {

    // Detect cycle in directed graph using Kahn's algorithm (BFS)
    public boolean hasCycle(int V, List<List<Integer>> adj) {
        int[] indeg = new int[V];
        for (int u = 0; u < V; u++) for (int v : adj.get(u)) indeg[v]++;
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < V; i++) if (indeg[i] == 0) q.add(i);
        int cnt = 0;
        while (!q.isEmpty()) {
            int node = q.poll();
            cnt++;
            for (int nei : adj.get(node)) {
                indeg[nei]--;
                if (indeg[nei] == 0) q.add(nei);
            }
        }
        return cnt != V; // if not all nodes processed -> cycle exists
    }

    public static void main(String[] args) {
        int V = 4;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
        adj.get(0).add(1);
        adj.get(1).add(2);
        adj.get(2).add(0); // cycle
        adj.get(2).add(3);
        CycleDetectionDirectedBFS sol = new CycleDetectionDirectedBFS();
        System.out.println("Has directed cycle (BFS): " + sol.hasCycle(V, adj));
    }
}