package Graphs.MSTandDisSet;

import java.util.*;

/**
 * PRIM'S ALGORITHM (MST)
 *
 * ---------------------------------------------------------
 * 1. PROBLEM STATEMENT
 * ---------------------------------------------------------
 * Given a connected undirected weighted graph, compute the total weight of a Minimum Spanning Tree
 * using Prim's greedy algorithm.
 *
 * ---------------------------------------------------------
 * 2. CORE IDEA
 * ---------------------------------------------------------
 * - Start from an arbitrary node and grow the MST by repeatedly selecting the minimum weight edge
 *   that connects a visited node to an unvisited node.
 * - Use a priority queue (min-heap) of edges crossing the current cut to extract the smallest edge.
 *
 * ---------------------------------------------------------
 * 3. WHY PRIORITY QUEUE?
 * ---------------------------------------------------------
 * The PQ allows efficient extraction of the smallest available edge (O(log E)). Without PQ, selecting
 * the next minimal edge each time would be much slower.
 *
 * ---------------------------------------------------------
 * 4. TIME & SPACE COMPLEXITY
 * ---------------------------------------------------------
 * Time: O(E log E) ~ O(E log V) with binary heap; Space: O(V+E)
 *
 * ---------------------------------------------------------
 * 5. EXAMPLE (used in main)
 * ---------------------------------------------------------
 * Graph: 0--1(2), 0--3(6), 1--2(3), 1--3(8), 2--3(7)
 * MST weight = 2 + 3 + 6 = 11 (edges 0-1,1-2,0-3)
 *
 * =========================================================
 */

public class PrimsAlgorithm {

    static class Edge { int to, w; Edge(int t, int ww){to=t; w=ww;} }

    public int primMST(int V, List<Edge>[] adj) {
        boolean[] inMST = new boolean[V];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        // pair {weight, node}
        pq.add(new int[]{0, 0});
        int total = 0;
        while (!pq.isEmpty()) {
            int[] cur = pq.poll(); int w = cur[0], u = cur[1];
            if (inMST[u]) continue;
            inMST[u] = true;
            total += w;
            for (Edge e : adj[u]) {
                if (!inMST[e.to]) pq.add(new int[]{e.w, e.to});
            }
        }
        return total;
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        int V = 4;
        List<Edge>[] adj = new List[V];
        for (int i=0;i<V;i++) adj[i] = new ArrayList<>();
        // edges: 0-1 (2), 0-3 (6), 1-2 (3), 1-3 (8), 2-3 (7)
        adj[0].add(new Edge(1,2)); adj[1].add(new Edge(0,2));
        adj[0].add(new Edge(3,6)); adj[3].add(new Edge(0,6));
        adj[1].add(new Edge(2,3)); adj[2].add(new Edge(1,3));
        adj[1].add(new Edge(3,8)); adj[3].add(new Edge(1,8));
        adj[2].add(new Edge(3,7)); adj[3].add(new Edge(2,7));

        PrimsAlgorithm sol = new PrimsAlgorithm();
        System.out.println("Prim MST total weight: " + sol.primMST(V, adj)); // expect 11
    }
}
