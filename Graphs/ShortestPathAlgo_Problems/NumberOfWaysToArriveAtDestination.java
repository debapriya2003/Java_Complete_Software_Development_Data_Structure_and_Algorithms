package Graphs.ShortestPathAlgo_Problems;

import java.util.*;

/**
 * =========================================================
 * NUMBER OF WAYS TO ARRIVE AT DESTINATION
 * (Counting Shortest Paths using Dijkstra)
 * =========================================================
 *
 * ---------------------------------------------------------
 * 1. PROBLEM STATEMENT
 * ---------------------------------------------------------
 * You are given:
 * - n cities numbered from 0 to n-1
 * - roads[i] = {u, v, w}
 *      → an undirected road between u and v with travel time w
 *
 * Task:
 * - Find the NUMBER OF DIFFERENT SHORTEST PATHS
 *   from city 0 (source) to city n-1 (destination)
 * - Return the answer modulo 1e9 + 7
 *
 * ---------------------------------------------------------
 * 2. GRAPH PROPERTIES
 * ---------------------------------------------------------
 * - Undirected graph
 * - Non-negative edge weights
 * - Multiple shortest paths may exist
 *
 * ---------------------------------------------------------
 * 3. WHY NORMAL DIJKSTRA IS NOT ENOUGH?
 * ---------------------------------------------------------
 * Standard Dijkstra:
 * ✔ Finds shortest distance
 * ❌ Does NOT count number of shortest paths
 *
 * We need:
 * ✔ Shortest distance
 * ✔ Number of ways to achieve that distance
 *
 * ---------------------------------------------------------
 * 4. CORE IDEA (VERY IMPORTANT)
 * ---------------------------------------------------------
 * Use a MODIFIED DIJKSTRA with TWO ARRAYS:
 *
 * dist[v]  → shortest distance to reach v
 * ways[v]  → number of shortest paths to reach v
 *
 * ---------------------------------------------------------
 * 5. UPDATE RULES (INTERVIEW GOLD)
 * ---------------------------------------------------------
 * When exploring edge u → v with weight w:
 *
 * Case 1: SHORTER PATH FOUND
 *   if dist[u] + w < dist[v]:
 *      dist[v] = dist[u] + w
 *      ways[v] = ways[u]
 *
 * Case 2: ANOTHER SHORTEST PATH FOUND
 *   if dist[u] + w == dist[v]:
 *      ways[v] += ways[u]
 *
 * ---------------------------------------------------------
 * 6. WHY THIS WORKS?
 * ---------------------------------------------------------
 * - Dijkstra guarantees nodes are processed in increasing
 *   order of shortest distance
 * - When a node’s shortest distance is finalized,
 *   all contributing shortest paths are already counted
 *
 * ---------------------------------------------------------
 * 7. TIME & SPACE COMPLEXITY
 * ---------------------------------------------------------
 * Time Complexity  : O((V + E) log V)
 * Space Complexity : O(V + E)
 *
 * ---------------------------------------------------------
 * 8. ONE-LINE SUMMARY (INTERVIEW GOLD)
 * ---------------------------------------------------------
 * Modify Dijkstra by tracking both shortest distance and
 * number of ways to achieve it.
 *
 * =========================================================
 * IMPLEMENTATION BELOW
 * =========================================================
 */

public class NumberOfWaysToArriveAtDestination {

    private static final int MOD = 1_000_000_007;

    /**
     * Edge structure for adjacency list
     */
    static class Edge {
        int to;
        int w;

        Edge(int to, int w) {
            this.to = to;   // destination node
            this.w = w;     // weight (time)
        }
    }

    /**
     * Counts number of shortest paths from node 0 to node n-1
     *
     * @param n     number of cities
     * @param roads edge list {u, v, w}
     * @return number of shortest paths modulo 1e9+7
     */
    public int countPaths(int n, int[][] roads) {

        /**
         * -----------------------------------------------------
         * STEP 1: BUILD ADJACENCY LIST
         * -----------------------------------------------------
         */
        @SuppressWarnings("unchecked")
        List<Edge>[] adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        // Undirected graph
        for (int[] r : roads) {
            adj[r[0]].add(new Edge(r[1], r[2]));
            adj[r[1]].add(new Edge(r[0], r[2]));
        }

        /**
         * -----------------------------------------------------
         * STEP 2: INITIALIZE DISTANCE & WAYS ARRAYS
         * -----------------------------------------------------
         */
        long[] dist = new long[n];
        Arrays.fill(dist, Long.MAX_VALUE / 4);

        long[] ways = new long[n];

        dist[0] = 0;     // source distance
        ways[0] = 1;     // exactly one way to start at source

        /**
         * Min-heap priority queue
         * Stores {distance, node}
         */
        PriorityQueue<long[]> pq =
                new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));

        pq.add(new long[]{0, 0});

        /**
         * -----------------------------------------------------
         * STEP 3: MODIFIED DIJKSTRA
         * -----------------------------------------------------
         */
        while (!pq.isEmpty()) {

            long[] cur = pq.poll();
            long d = cur[0];
            int u = (int) cur[1];

            // Ignore outdated states
            if (d > dist[u]) continue;

            for (Edge e : adj[u]) {

                long newDist = dist[u] + e.w;

                // Case 1: Found a strictly shorter path
                if (newDist < dist[e.to]) {
                    dist[e.to] = newDist;
                    ways[e.to] = ways[u];
                    pq.add(new long[]{newDist, e.to});
                }
                // Case 2: Found another shortest path
                else if (newDist == dist[e.to]) {
                    ways[e.to] = (ways[e.to] + ways[u]) % MOD;
                }
            }
        }

        /**
         * -----------------------------------------------------
         * STEP 4: RETURN RESULT
         * -----------------------------------------------------
         */
        return (int) (ways[n - 1] % MOD);
    }

    public static void main(String[] args) {

        System.out.println("=== Number of Ways to Arrive at Destination ===\n");

        /*
         * Example:
         *
         * roads = {
         *   {0,1,1},
         *   {0,2,1},
         *   {1,3,1},
         *   {2,3,1}
         * }
         *
         * Shortest paths from 0 to 3:
         * 0 → 1 → 3
         * 0 → 2 → 3
         *
         * Answer = 2
         */

        NumberOfWaysToArriveAtDestination solver =
                new NumberOfWaysToArriveAtDestination();

        int[][] roads = {
                {0, 1, 1},
                {0, 2, 1},
                {1, 3, 1},
                {2, 3, 1}
        };

        int result = solver.countPaths(4, roads);
        System.out.println("Number of shortest paths: " + result);

        System.out.println("\n" + "=".repeat(60));
        System.out.println("INTERVIEW TAKEAWAYS:");
        System.out.println("✔ Track both distance and number of ways");
        System.out.println("✔ Reset ways[] when shorter path is found");
        System.out.println("✔ Add ways[] when equal shortest path is found");
    }
}
