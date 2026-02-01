package Graphs.ShortestPathAlgo_Problems;

import java.util.*;

/**
 * =========================================================
 * BELLMAN–FORD ALGORITHM (Single Source Shortest Path)
 * =========================================================
 *
 * ---------------------------------------------------------
 * 1. PROBLEM STATEMENT
 * ---------------------------------------------------------
 * Given a graph with:
 * - V vertices
 * - E edges (may include NEGATIVE edge weights)
 * - A source vertex src
 *
 * Find the SHORTEST distance from src to all other vertices.
 *
 * ---------------------------------------------------------
 * 2. IMPORTANT CAPABILITIES
 * ---------------------------------------------------------
 * ✔ Works with NEGATIVE edge weights
 * ✔ Detects NEGATIVE WEIGHT CYCLES
 * ❌ Slower than Dijkstra
 *
 * ---------------------------------------------------------
 * 3. WHEN TO USE BELLMAN–FORD?
 * ---------------------------------------------------------
 * - Graph has negative weights
 * - Need to detect negative cycles
 * - Graph size is moderate (O(V * E))
 *
 * ---------------------------------------------------------
 * 4. KEY IDEA / CORE LOGIC
 * ---------------------------------------------------------
 * Shortest path between two vertices can have AT MOST (V-1)
 * edges (if no negative cycle exists).
 *
 * So:
 * - Relax ALL edges (V-1) times
 * - Each relaxation improves distance
 *
 * Relaxation rule:
 * if dist[u] + weight(u→v) < dist[v]
 *     → update dist[v]
 *
 * ---------------------------------------------------------
 * 5. NEGATIVE CYCLE DETECTION
 * ---------------------------------------------------------
 * After (V-1) relaxations:
 * - Do ONE MORE relaxation
 * - If any distance still reduces
 *   → NEGATIVE CYCLE EXISTS
 *
 * ---------------------------------------------------------
 * 6. TIME & SPACE COMPLEXITY
 * ---------------------------------------------------------
 * Time Complexity  : O(V × E)
 * Space Complexity : O(V)
 *
 * ---------------------------------------------------------
 * 7. ONE-LINE SUMMARY (INTERVIEW GOLD)
 * ---------------------------------------------------------
 * Bellman–Ford relaxes all edges V−1 times and detects
 * negative cycles using an extra relaxation pass.
 *
 * =========================================================
 * IMPLEMENTATION BELOW
 * =========================================================
 */

public class BellmanFord {

    /**
     * Edge structure representing:
     * u → v with weight w
     */
    static class Edge {
        int u, v, w;

        Edge(int u, int v, int w) {
            this.u = u;   // source vertex
            this.v = v;   // destination vertex
            this.w = w;   // edge weight
        }
    }

    /**
     * Bellman–Ford Algorithm
     *
     * @param V     number of vertices
     * @param edges list of all edges in graph
     * @param src   source vertex
     * @return array of shortest distances from src
     */
    public long[] bellmanFord(int V, List<Edge> edges, int src) {

        // Use a large value as INF to avoid overflow
        final long INF = Long.MAX_VALUE / 4;

        // Distance array
        long[] dist = new long[V];

        // Initialize all distances as INF
        Arrays.fill(dist, INF);

        // Distance to source is 0
        dist[src] = 0;

        /**
         * -----------------------------------------------------
         * STEP 1: RELAX ALL EDGES (V - 1) TIMES
         * -----------------------------------------------------
         * Why (V - 1)?
         * - Longest possible simple path has V - 1 edges
         */
        for (int i = 1; i <= V - 1; i++) {

            boolean anyUpdate = false;

            // Relax each edge
            for (Edge e : edges) {

                // Relaxation condition
                if (dist[e.u] != INF && dist[e.u] + e.w < dist[e.v]) {
                    dist[e.v] = dist[e.u] + e.w;
                    anyUpdate = true;
                }
            }

            // Optimization: if no update happened, stop early
            if (!anyUpdate) break;
        }

        /**
         * -----------------------------------------------------
         * STEP 2: NEGATIVE CYCLE DETECTION
         * -----------------------------------------------------
         * If we can still relax an edge,
         * then a negative weight cycle exists
         */
        for (Edge e : edges) {
            if (dist[e.u] != INF && dist[e.u] + e.w < dist[e.v]) {
                throw new RuntimeException("Negative weight cycle detected");
            }
        }

        return dist;
    }

    public static void main(String[] args) {

        System.out.println("=== Bellman–Ford Algorithm ===\n");

        BellmanFord solver = new BellmanFord();
        List<Edge> edges = new ArrayList<>();

        /*
         * Graph:
         *
         * 0 → 1 (-1)
         * 0 → 2 (4)
         * 1 → 2 (3)
         * 1 → 3 (2)
         * 1 → 4 (2)
         * 3 → 2 (5)
         * 3 → 1 (1)
         * 4 → 3 (-3)
         */

        edges.add(new Edge(0, 1, -1));
        edges.add(new Edge(0, 2, 4));
        edges.add(new Edge(1, 2, 3));
        edges.add(new Edge(1, 3, 2));
        edges.add(new Edge(1, 4, 2));
        edges.add(new Edge(3, 2, 5));
        edges.add(new Edge(3, 1, 1));
        edges.add(new Edge(4, 3, -3));

        long[] result = solver.bellmanFord(5, edges, 0);

        System.out.println("Shortest distances from source 0:");
        System.out.println(Arrays.toString(result));

        System.out.println("\n" + "=".repeat(60));
        System.out.println("INTERVIEW TAKEAWAYS:");
        System.out.println("✔ Handles negative weights");
        System.out.println("✔ Detects negative cycles");
        System.out.println("✔ Slower than Dijkstra but more powerful");
    }
}
