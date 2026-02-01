package Graphs.MSTandDisSet;

import java.util.*;

/**
 * KRUSKAL'S ALGORITHM (MST using DSU)
 *
 * ---------------------------------------------------------
 * 1. PROBLEM STATEMENT
 * ---------------------------------------------------------
 * Given an undirected weighted graph, compute MST total weight using Kruskal's algorithm.
 *
 * ---------------------------------------------------------
 * 2. CORE IDEA
 * ---------------------------------------------------------
 * - Sort all edges by weight ascending.
 * - Use a Disjoint Set Union to add the smallest edge that doesn't form a cycle (connects different sets).
 * - Stop when you have V-1 edges.
 *
 * ---------------------------------------------------------
 * 3. COMPLEXITY
 * ---------------------------------------------------------
 * Time: O(E log E) for sorting + DSU operations; Space: O(E + V)
 *
 * ---------------------------------------------------------
 * 4. EXAMPLE
 * ---------------------------------------------------------
 * Graph as in Prim example; Kruskal will select edges (0-1:2), (1-2:3), (0-3:6)
 * resulting MST weight 11.
 *
 * =========================================================
 */

public class KruskalAlgorithm {

    static class Edge { int u, v, w; Edge(int u, int v, int w){ this.u=u; this.v=v; this.w=w; } }

    public int kruskal(int V, List<Edge> edges) {
        edges.sort(Comparator.comparingInt(e -> e.w));
        DisjointSetUnionByRank dsu = new DisjointSetUnionByRank(V);
        int total = 0, used = 0;
        for (Edge e : edges) {
            if (dsu.union(e.u, e.v)) {
                total += e.w; used++;
                if (used == V-1) break;
            }
        }
        return total;
    }

    public static void main(String[] args) {
        int V = 4;
        List<Edge> edges = new ArrayList<>();
        edges.add(new Edge(0,1,2)); edges.add(new Edge(0,3,6));
        edges.add(new Edge(1,2,3)); edges.add(new Edge(1,3,8)); edges.add(new Edge(2,3,7));
        KruskalAlgorithm sol = new KruskalAlgorithm();
        System.out.println("Kruskal MST total weight: " + sol.kruskal(V, edges)); // expect 11
    }
}
