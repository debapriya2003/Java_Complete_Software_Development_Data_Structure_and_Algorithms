package Graphs.Topological_sort_problems;

import java.util.*;

/**
 * Kahn's Algorithm — Topological Sort (Detailed Explanation and Example Code)
 *
 * Purpose: Produce a topological ordering of a directed acyclic graph (DAG) using a BFS-style method.
 * The algorithm is often used in scheduling, build systems, and dependency resolution.
 *
 * Detailed steps (illustrated with inline pseudocode):
 * 1) Compute the indegree of each vertex:
 *    int[] indeg = new int[V]; for (u) for (v : adj.get(u)) indeg[v]++;
 * 2) Initialize a queue with all vertices having indegree 0:
 *    Queue<Integer> q = new LinkedList<>(); for (i) if (indeg[i]==0) q.add(i);
 * 3) While queue not empty: pop node, append to result list, and decrease indegree of its neighbors.
 *    If a neighbor's indegree becomes 0, add it to the queue. Example core loop:
 *    while (!q.isEmpty()) { int node = q.poll(); order.add(node); for (nei : adj.get(node)) { indeg[nei]--; if (indeg[nei]==0) q.add(nei); } }
 * 4) If order.size() != V, the graph had a cycle and a full topological ordering doesn't exist.
 *
 * Practical notes: Kahn's algorithm is simple, iterative, and can detect cycles while producing the ordering.
 * Complexity: Time O(V + E), Space O(V + E).
 */

public class KahnAlgorithm {

    public List<Integer> topoSort(int V, List<List<Integer>> adj) {
        int[] indeg = new int[V];
        for (int u = 0; u < V; u++) for (int v : adj.get(u)) indeg[v]++;
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < V; i++) if (indeg[i] == 0) q.add(i);
        List<Integer> order = new ArrayList<>();
        while (!q.isEmpty()) {
            int node = q.poll();
            order.add(node);
            for (int nei : adj.get(node)) {
                indeg[nei]--;
                if (indeg[nei] == 0) q.add(nei);
            }
        }
        return order.size() == V ? order : new ArrayList<>(); // empty if cycle
    }

    public static void main(String[] args) {
        int V = 6;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
        adj.get(5).add(2);
        adj.get(5).add(0);
        adj.get(4).add(0);
        adj.get(4).add(1);
        adj.get(2).add(3);
        adj.get(3).add(1);
        KahnAlgorithm sol = new KahnAlgorithm();
        System.out.println("Topo order (Kahn): " + sol.topoSort(V, adj));
    }
}