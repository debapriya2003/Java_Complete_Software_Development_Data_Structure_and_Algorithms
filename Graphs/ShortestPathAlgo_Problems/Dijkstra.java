package Graphs.ShortestPathAlgo_Problems;

import java.util.*;

/**
 * Dijkstra's Algorithm (Single Source Shortest Path for non-negative weights)
 *
 * Problem:
 * Given a weighted graph with non-negative edge weights and a source node, compute shortest distances
 * to all nodes.
 *
 * Approach:
 * Use a priority queue (min-heap) keyed by tentative distance. Initialize distances to INF, set
 * distance[source] = 0 and push into PQ. While PQ not empty, pop the node with smallest tentative distance;
 * if it's outdated (larger than recorded distance) skip; otherwise relax all outgoing edges and push updated
 * distances into PQ.
 *
 * Why PQ: Extracting the node with minimum tentative distance ensures we finalize nodes in order of increasing
 * shortest path and avoid scanning all vertices each time; without PQ the algorithm would be inefficient.
 *
 * Complexity: O((V+E) log V) typical when using binary heap.
 */

public class Dijkstra {

    static class Edge { int to; int w; Edge(int t,int ww){to=t;w=ww;} }

    public long[] dijkstra(int V, List<Edge>[] adj, int src) {
        final long INF = Long.MAX_VALUE/4;
        long[] dist = new long[V]; Arrays.fill(dist, INF);
        dist[src] = 0;
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
        pq.add(new long[]{0, src});
        while (!pq.isEmpty()) {
            long[] cur = pq.poll(); long d = cur[0]; int u = (int)cur[1];
            if (d != dist[u]) continue; // outdated entry
            for (Edge e : adj[u]) {
                if (dist[u] + e.w < dist[e.to]) {
                    dist[e.to] = dist[u] + e.w;
                    pq.add(new long[]{dist[e.to], e.to});
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        int V = 5;
        @SuppressWarnings("unchecked")
        List<Edge>[] adj = new List[V];
        for (int i = 0; i < V; i++) adj[i] = new ArrayList<>();
        adj[0].add(new Edge(1, 10)); adj[0].add(new Edge(4, 5));
        adj[1].add(new Edge(2, 1)); adj[1].add(new Edge(4, 2));
        adj[4].add(new Edge(1, 3)); adj[4].add(new Edge(2, 9)); adj[4].add(new Edge(3, 2));
        adj[2].add(new Edge(3, 4));
        Dijkstra sol = new Dijkstra();
        System.out.println(Arrays.toString(sol.dijkstra(V, adj, 0)));
    }
}
