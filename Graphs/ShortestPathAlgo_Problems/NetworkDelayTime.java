package Graphs.ShortestPathAlgo_Problems;

import java.util.*;

/**
 * =========================================================
 * NETWORK DELAY TIME (Dijkstra Algorithm)
 * =========================================================
 *
 * ---------------------------------------------------------
 * 1. PROBLEM STATEMENT
 * ---------------------------------------------------------
 * You are given:
 * - times[i] = {u, v, w}
 *      → a directed edge from node u to node v
 *        taking w time
 * - N → number of nodes (labeled 1 to N)
 * - K → starting node (signal source)
 *
 * A signal is sent from node K at time 0.
 *
 * Task:
 * Find the time required for ALL nodes to receive the signal.
 *
 * - If all nodes are reachable → return the MAXIMUM
 *   shortest time among all nodes
 * - If any node is unreachable → return -1
 *
 * ---------------------------------------------------------
 * 2. GRAPH PROPERTIES
 * ---------------------------------------------------------
 * - Directed graph
 * - Positive edge weights
 * - Single source shortest path
 *
 * ---------------------------------------------------------
 * 3. WHY DIJKSTRA?
 * ---------------------------------------------------------
 * ✔ Edge weights are non-negative
 * ✔ Need shortest path from ONE source to ALL nodes
 *
 * Dijkstra is the optimal choice.
 *
 * ---------------------------------------------------------
 * 4. CORE IDEA
 * ---------------------------------------------------------
 * 1. Build adjacency list from times[]
 * 2. Run Dijkstra from source K
 * 3. Find maximum distance among all nodes
 * 4. If any node is unreachable → return -1
 *
 * ---------------------------------------------------------
 * 5. INTERPRETATION
 * ---------------------------------------------------------
 * - dist[i] = time when node i receives the signal
 * - Answer = max(dist[i]) for all i
 *
 * ---------------------------------------------------------
 * 6. TIME & SPACE COMPLEXITY
 * ---------------------------------------------------------
 * Time Complexity  : O(E log V)
 * Space Complexity : O(V + E)
 *
 * ---------------------------------------------------------
 * 7. ONE-LINE SUMMARY (INTERVIEW GOLD)
 * ---------------------------------------------------------
 * Run Dijkstra from the source and return the maximum
 * shortest distance if all nodes are reachable.
 *
 * =========================================================
 * IMPLEMENTATION BELOW
 * =========================================================
 */

public class NetworkDelayTime {

    /**
     * Edge structure for adjacency list
     */
    static class Edge {
        int to;
        int w;

        Edge(int to, int w) {
            this.to = to;   // destination node
            this.w = w;     // time to reach destination
        }
    }

    /**
     * Computes network delay time
     *
     * @param times directed edges {u, v, w}
     * @param N     number of nodes
     * @param K     starting node
     * @return time for all nodes to receive signal or -1
     */
    public int networkDelayTime(int[][] times, int N, int K) {

        /**
         * -----------------------------------------------------
         * STEP 1: BUILD ADJACENCY LIST
         * -----------------------------------------------------
         * Nodes are labeled from 1 to N
         */
        @SuppressWarnings("unchecked")
        List<Edge>[] adj = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] t : times) {
            int u = t[0];
            int v = t[1];
            int w = t[2];
            adj[u].add(new Edge(v, w));   // directed edge
        }

        /**
         * -----------------------------------------------------
         * STEP 2: INITIALIZE DISTANCE ARRAY
         * -----------------------------------------------------
         */
        final int INF = Integer.MAX_VALUE / 4;
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);

        dist[K] = 0;   // source node

        /**
         * Min-heap priority queue
         * Stores {distance, node}
         */
        PriorityQueue<int[]> pq =
                new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        pq.add(new int[]{0, K});

        /**
         * -----------------------------------------------------
         * STEP 3: DIJKSTRA ALGORITHM
         * -----------------------------------------------------
         */
        while (!pq.isEmpty()) {

            int[] cur = pq.poll();
            int d = cur[0];
            int u = cur[1];

            // Skip outdated queue entries
            if (d != dist[u]) continue;

            for (Edge e : adj[u]) {
                if (dist[u] + e.w < dist[e.to]) {
                    dist[e.to] = dist[u] + e.w;
                    pq.add(new int[]{dist[e.to], e.to});
                }
            }
        }

        /**
         * -----------------------------------------------------
         * STEP 4: FIND MAXIMUM DISTANCE
         * -----------------------------------------------------
         */
        int answer = 0;

        for (int i = 1; i <= N; i++) {
            // If any node is unreachable
            if (dist[i] == INF) return -1;

            answer = Math.max(answer, dist[i]);
        }

        return answer;
    }

    public static void main(String[] args) {

        System.out.println("=== Network Delay Time ===\n");

        /*
         * Example:
         *
         * times = {
         *   {2,1,1},
         *   {2,3,1},
         *   {3,4,1}
         * }
         *
         * N = 4
         * K = 2
         *
         * Signal propagation:
         * 2 → 1 (1)
         * 2 → 3 (1)
         * 3 → 4 (2)
         *
         * Answer = 2
         */

        NetworkDelayTime solver = new NetworkDelayTime();

        int[][] times = {
                {2, 1, 1},
                {2, 3, 1},
                {3, 4, 1}
        };

        int result = solver.networkDelayTime(times, 4, 2);
        System.out.println("Network Delay Time: " + result);

        System.out.println("\n" + "=".repeat(60));
        System.out.println("INTERVIEW TAKEAWAYS:");
        System.out.println("✔ Directed graph shortest path");
        System.out.println("✔ Dijkstra from single source");
        System.out.println("✔ Max distance among all nodes is the answer");
    }
}
