package Graphs.ShortestPathAlgo_Problems;

import java.util.*;

/**
 * =========================================================
 * SHORTEST PATH IN A DIRECTED ACYCLIC GRAPH (DAG)
 * =========================================================
 *
 * ---------------------------------------------------------
 * 1. PROBLEM STATEMENT
 * ---------------------------------------------------------
 * Given:
 * - A Directed Acyclic Graph (DAG)
 * - Weighted edges (can be positive or negative)
 * - A source vertex src
 *
 * Task:
 * Compute the SHORTEST distance from src to ALL other vertices.
 *
 * ---------------------------------------------------------
 * 2. IMPORTANT PROPERTIES OF DAG
 * ---------------------------------------------------------
 * ✔ Graph has NO cycles
 * ✔ Nodes can be ordered topologically
 * ✔ Shortest paths can be computed in linear time
 *
 * ---------------------------------------------------------
 * 3. WHY NOT DIJKSTRA / BELLMAN-FORD?
 * ---------------------------------------------------------
 * Dijkstra:
 * ❌ Cannot handle negative edges
 *
 * Bellman–Ford:
 * ✔ Works but slower → O(V × E)
 *
 * DAG Shortest Path:
 * ✔ Handles negative weights
 * ✔ Faster → O(V + E)
 *
 * ---------------------------------------------------------
 * 4. CORE IDEA
 * ---------------------------------------------------------
 * 1. Perform TOPOLOGICAL SORT of the DAG
 * 2. Initialize distance array:
 *      dist[src] = 0
 *      dist[others] = INF
 * 3. Process nodes in TOPOLOGICAL ORDER
 * 4. Relax outgoing edges
 *
 * ---------------------------------------------------------
 * 5. WHY TOPOLOGICAL ORDER WORKS
 * ---------------------------------------------------------
 * In a DAG:
 * - All edges go from LEFT → RIGHT in topo order
 * - When processing node u:
 *      its shortest distance is already FINAL
 *
 * Hence:
 * - Each edge is relaxed exactly once
 *
 * ---------------------------------------------------------
 * 6. TIME & SPACE COMPLEXITY
 * ---------------------------------------------------------
 * Time Complexity  : O(V + E)
 * Space Complexity : O(V + E)
 *
 * ---------------------------------------------------------
 * 7. ONE-LINE SUMMARY (INTERVIEW GOLD)
 * ---------------------------------------------------------
 * In a DAG, shortest paths can be computed by relaxing edges
 * in topological order.
 *
 * =========================================================
 * IMPLEMENTATION BELOW
 * =========================================================
 */

public class ShortestPathInDAG {

    // Represents infinity
    public static final long INF = Long.MAX_VALUE / 4;

    /**
     * Computes shortest path from src in a DAG
     *
     * @param V   number of vertices
     * @param adj adjacency list where each entry is {to, weight}
     * @param src source vertex
     * @return array of shortest distances
     */
    public long[] shortestPath(int V, List<int[]>[] adj, int src) {

        /**
         * -----------------------------------------------------
         * STEP 1: TOPOLOGICAL SORT (DFS BASED)
         * -----------------------------------------------------
         */
        boolean[] visited = new boolean[V];
        Deque<Integer> topoStack = new ArrayDeque<>();

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfsTopo(i, visited, adj, topoStack);
            }
        }

        /**
         * -----------------------------------------------------
         * STEP 2: INITIALIZE DISTANCE ARRAY
         * -----------------------------------------------------
         */
        long[] dist = new long[V];
        Arrays.fill(dist, INF);
        dist[src] = 0;

        /**
         * -----------------------------------------------------
         * STEP 3: RELAX EDGES IN TOPOLOGICAL ORDER
         * -----------------------------------------------------
         */
        while (!topoStack.isEmpty()) {

            int u = topoStack.pop();

            // Only relax edges if u is reachable
            if (dist[u] != INF) {
                for (int[] edge : adj[u]) {
                    int v = edge[0];
                    int w = edge[1];

                    if (dist[u] + w < dist[v]) {
                        dist[v] = dist[u] + w;
                    }
                }
            }
        }

        return dist;
    }

    /**
     * DFS utility for Topological Sort
     *
     * @param u     current node
     * @param vis   visited array
     * @param adj   adjacency list
     * @param stack stack storing topo order
     */
    private void dfsTopo(int u, boolean[] vis,
                         List<int[]>[] adj,
                         Deque<Integer> stack) {

        vis[u] = true;

        for (int[] edge : adj[u]) {
            int v = edge[0];
            if (!vis[v]) {
                dfsTopo(v, vis, adj, stack);
            }
        }

        // Push node after visiting all neighbors
        stack.push(u);
    }

    public static void main(String[] args) {

        System.out.println("=== Shortest Path in DAG ===\n");

        /*
         * Example DAG:
         *
         * 5 → 2 (2)
         * 5 → 0 (1)
         * 4 → 0 (1)
         * 4 → 1 (1)
         * 2 → 3 (1)
         * 3 → 1 (5)
         *
         * Source = 5
         *
         * Expected distances:
         * [1, 3, 2, 3, INF, 0]
         */

        int V = 6;
        @SuppressWarnings("unchecked")
        List<int[]>[] adj = (List<int[]>[]) new ArrayList[V];

        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
        }

        adj[5].add(new int[]{2, 2});
        adj[5].add(new int[]{0, 1});
        adj[4].add(new int[]{0, 1});
        adj[4].add(new int[]{1, 1});
        adj[2].add(new int[]{3, 1});
        adj[3].add(new int[]{1, 5});

        ShortestPathInDAG solver = new ShortestPathInDAG();
        long[] result = solver.shortestPath(V, adj, 5);

        System.out.println("Shortest distances from source 5:");
        System.out.println(Arrays.toString(result));

        System.out.println("\n" + "=".repeat(60));
        System.out.println("INTERVIEW TAKEAWAYS:");
        System.out.println("✔ DAG allows linear-time shortest path");
        System.out.println("✔ Topological order ensures correctness");
        System.out.println("✔ Works even with negative edge weights");
    }
}
