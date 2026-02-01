package Graphs.ShortestPathAlgo_Problems;

import java.util.*;

/**
 * =========================================================
 * CHEAPEST FLIGHTS WITHIN K STOPS
 * (Modified Bellman–Ford Algorithm)
 * =========================================================
 *
 * ---------------------------------------------------------
 * 1. PROBLEM STATEMENT
 * ---------------------------------------------------------
 * You are given:
 * - n cities numbered from 0 to n-1
 * - flights[i] = {u, v, w}
 *      → flight from city u to city v with cost w
 * - src → source city
 * - dst → destination city
 * - K   → maximum allowed stops
 *
 * Task:
 * Find the CHEAPEST price from src to dst
 * using AT MOST K stops.
 *
 * If no such route exists, return -1.
 *
 * ---------------------------------------------------------
 * 2. IMPORTANT OBSERVATIONS
 * ---------------------------------------------------------
 * - Stops = intermediate cities
 * - Max edges allowed = K + 1
 * - Graph may contain cycles
 * - Edge weights are NON-NEGATIVE
 *
 * ---------------------------------------------------------
 * 3. WHY NOT DIJKSTRA?
 * ---------------------------------------------------------
 * Standard Dijkstra:
 * ❌ Does NOT limit number of stops
 *
 * We need:
 * ✔ Cost optimization
 * ✔ Stop constraint
 *
 * ---------------------------------------------------------
 * 4. WHY MODIFIED BELLMAN–FORD?
 * ---------------------------------------------------------
 * Bellman–Ford relaxes edges in "layers"
 *
 * Key idea:
 * - Shortest path with at most (K+1) edges
 * - Perform EXACTLY (K+1) relaxation rounds
 *
 * ---------------------------------------------------------
 * 5. CORE IDEA
 * ---------------------------------------------------------
 * - dist[v] = cheapest cost to reach v
 * - For each iteration (0 → K):
 *      - Relax all edges
 *      - BUT use PREVIOUS iteration values only
 *
 * Why?
 * - Prevent using paths with more than K stops
 *
 * ---------------------------------------------------------
 * 6. IMPORTANT TECHNIQUE (VERY INTERVIEW-IMPORTANT)
 * ---------------------------------------------------------
 * Use a TEMP array (next[]) so that:
 * - Updates in current iteration do NOT affect
 *   other relaxations in the same iteration
 *
 * This preserves correct stop count.
 *
 * ---------------------------------------------------------
 * 7. TIME & SPACE COMPLEXITY
 * ---------------------------------------------------------
 * Time Complexity  : O(K × E)
 * Space Complexity : O(V)
 *
 * ---------------------------------------------------------
 * 8. ONE-LINE SUMMARY (INTERVIEW GOLD)
 * ---------------------------------------------------------
 * This problem is solved using a K-limited Bellman–Ford
 * where each iteration represents one more allowed edge.
 *
 * =========================================================
 * IMPLEMENTATION BELOW
 * =========================================================
 */

public class CheapestFlightsWithinKStops {

    /**
     * Finds the cheapest price within at most K stops
     *
     * @param n       number of cities
     * @param flights flight list {u, v, w}
     * @param src     source city
     * @param dst     destination city
     * @param K       maximum stops allowed
     * @return cheapest cost or -1 if impossible
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {

        final int INF = Integer.MAX_VALUE / 4;

        /**
         * dist[v] = cheapest cost to reach city v
         * using at most i edges (updated iteratively)
         */
        int[] dist = new int[n];
        Arrays.fill(dist, INF);

        // Cost to reach source is 0
        dist[src] = 0;

        /**
         * -----------------------------------------------------
         * RELAX EDGES (K + 1) TIMES
         * -----------------------------------------------------
         * Why K + 1?
         * - K stops → K+1 edges maximum
         */
        for (int i = 0; i <= K; i++) {

            // Create a copy to preserve previous layer
            int[] next = Arrays.copyOf(dist, n);

            // Relax all flights
            for (int[] f : flights) {

                int u = f[0];
                int v = f[1];
                int w = f[2];

                // Only relax if source city is reachable
                if (dist[u] != INF && dist[u] + w < next[v]) {
                    next[v] = dist[u] + w;
                }
            }

            // Move to next iteration
            dist = next;
        }

        // If destination is unreachable
        return dist[dst] >= INF ? -1 : dist[dst];
    }

    public static void main(String[] args) {

        System.out.println("=== Cheapest Flights Within K Stops ===\n");

        /*
         * Example:
         *
         * Cities: 0, 1, 2
         * Flights:
         * 0 → 1 (100)
         * 1 → 2 (100)
         * 0 → 2 (500)
         *
         * src = 0, dst = 2, K = 1
         *
         * Valid path:
         * 0 → 1 → 2 (cost = 200)
         */

        CheapestFlightsWithinKStops solver = new CheapestFlightsWithinKStops();

        int[][] flights = {
                {0, 1, 100},
                {1, 2, 100},
                {0, 2, 500}
        };

        int result = solver.findCheapestPrice(3, flights, 0, 2, 1);

        System.out.println("Cheapest Price: " + result);

        System.out.println("\n" + "=".repeat(60));
        System.out.println("INTERVIEW TAKEAWAYS:");
        System.out.println("✔ Bellman–Ford with limited iterations");
        System.out.println("✔ next[] array prevents stop overflow");
        System.out.println("✔ Common LeetCode hard-medium problem");
    }
}
