package Graphs.ShortestPathAlgo_Problems;

import java.util.*;

/**
 * =========================================================
 * FIND THE CITY WITH THE SMALLEST NUMBER OF NEIGHBORS
 * AT A GIVEN DISTANCE THRESHOLD
 * =========================================================
 *
 * ---------------------------------------------------------
 * 1. PROBLEM STATEMENT
 * ---------------------------------------------------------
 * You are given:
 * - n cities labeled from 0 to n-1
 * - edges[i] = {u, v, w} representing an undirected edge
 *   between city u and city v with distance w
 * - an integer distanceThreshold
 *
 * Task:
 * For each city, count how many OTHER cities are reachable
 * with a shortest path distance ≤ distanceThreshold.
 *
 * Return:
 * - The city with the SMALLEST number of such neighbors
 * - If there is a tie, return the city with the LARGEST index
 *
 * ---------------------------------------------------------
 * 2. IMPORTANT OBSERVATIONS
 * ---------------------------------------------------------
 * - Graph is UNDIRECTED
 * - Edge weights are NON-NEGATIVE
 * - Need SHORTEST PATHS between all pairs
 *
 * ---------------------------------------------------------
 * 3. POSSIBLE APPROACHES
 * ---------------------------------------------------------
 * 1️⃣ Floyd–Warshall
 *    - All-pairs shortest path
 *    - Time: O(n³)
 *
 * 2️⃣ Dijkstra from every node (Chosen here)
 *    - Run Dijkstra n times
 *    - Time: O(n × (E log V))
 *
 * ---------------------------------------------------------
 * 4. WHY DIJKSTRA FROM EACH CITY?
 * ---------------------------------------------------------
 * - Graph has non-negative weights
 * - n is usually small/moderate (LeetCode constraints)
 * - Simpler to reason about threshold-based counting
 *
 * ---------------------------------------------------------
 * 5. CORE LOGIC
 * ---------------------------------------------------------
 * For each city i:
 * 1. Run Dijkstra starting from i
 * 2. Count how many cities j (j ≠ i) satisfy:
 *        dist[j] ≤ distanceThreshold
 * 3. Track the city with minimum count
 * 4. In case of tie → prefer larger city index
 *
 * ---------------------------------------------------------
 * 6. TIE-BREAKING RULE (VERY IMPORTANT)
 * ---------------------------------------------------------
 * If two cities have the same number of reachable neighbors:
 * → choose the city with the GREATER index
 *
 * This is why we use:
 *    if (cnt <= bestCount)
 *
 * ---------------------------------------------------------
 * 7. TIME & SPACE COMPLEXITY
 * ---------------------------------------------------------
 * Time Complexity  : O(n × (E log V))
 * Space Complexity : O(V + E)
 *
 * ---------------------------------------------------------
 * 8. ONE-LINE SUMMARY (INTERVIEW GOLD)
 * ---------------------------------------------------------
 * Run Dijkstra from every city and choose the one with the
 * fewest reachable neighbors within the distance threshold.
 *
 * =========================================================
 * IMPLEMENTATION BELOW
 * =========================================================
 */

public class FindCityWithSmallestNeighborsThreshold {

    /**
     * Edge structure for adjacency list
     */
    static class Edge {
        int to;
        int w;

        Edge(int to, int w) {
            this.to = to;   // destination city
            this.w = w;     // distance
        }
    }

    /**
     * Main function to find the required city
     *
     * @param n                 number of cities
     * @param edges             edge list {u, v, w}
     * @param distanceThreshold max allowed distance
     * @return city index
     */
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {

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
        for (int[] e : edges) {
            adj[e[0]].add(new Edge(e[1], e[2]));
            adj[e[1]].add(new Edge(e[0], e[2]));
        }

        int bestCity = -1;
        int bestCount = Integer.MAX_VALUE;

        /**
         * -----------------------------------------------------
         * STEP 2: RUN DIJKSTRA FROM EACH CITY
         * -----------------------------------------------------
         */
        for (int city = 0; city < n; city++) {

            int reachableCount = dijkstraCount(adj, city, distanceThreshold);

            /**
             * <= ensures:
             * - smaller count preferred
             * - on tie, larger city index preferred
             */
            if (reachableCount <= bestCount) {
                bestCount = reachableCount;
                bestCity = city;
            }
        }

        return bestCity;
    }

    /**
     * Dijkstra to count reachable cities within threshold
     *
     * @param adj adjacency list
     * @param src source city
     * @param th  distance threshold
     * @return number of reachable cities
     */
    private int dijkstraCount(List<Edge>[] adj, int src, int th) {

        int n = adj.length;

        /**
         * dist[i] = shortest distance from src to i
         */
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE / 4);

        /**
         * Min-heap priority queue
         * Stores {distance, node}
         */
        PriorityQueue<int[]> pq =
                new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        dist[src] = 0;
        pq.add(new int[]{0, src});

        /**
         * Standard Dijkstra algorithm
         */
        while (!pq.isEmpty()) {

            int[] cur = pq.poll();
            int d = cur[0];
            int u = cur[1];

            // Ignore outdated entries
            if (d > dist[u]) continue;

            for (Edge e : adj[u]) {
                if (d + e.w < dist[e.to]) {
                    dist[e.to] = d + e.w;
                    pq.add(new int[]{dist[e.to], e.to});
                }
            }
        }

        /**
         * -----------------------------------------------------
         * STEP 3: COUNT REACHABLE CITIES
         * -----------------------------------------------------
         */
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (i != src && dist[i] <= th) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {

        System.out.println("=== Find City With Smallest Neighbors ===\n");

        /*
         * Example:
         *
         * n = 4
         * edges = {
         *   {0,1,3},
         *   {1,2,1},
         *   {1,3,4},
         *   {2,3,1}
         * }
         * threshold = 4
         */

        FindCityWithSmallestNeighborsThreshold solver =
                new FindCityWithSmallestNeighborsThreshold();

        int[][] edges = {
                {0, 1, 3},
                {1, 2, 1},
                {1, 3, 4},
                {2, 3, 1}
        };

        int result = solver.findTheCity(4, edges, 4);
        System.out.println("Answer City: " + result);

        System.out.println("\n" + "=".repeat(60));
        System.out.println("INTERVIEW TAKEAWAYS:");
        System.out.println("✔ Run Dijkstra from every node");
        System.out.println("✔ Count nodes within threshold");
        System.out.println("✔ Tie → choose larger index");
    }
}
