package Graphs.MSTandDisSet;

import java.util.*;

/**
 * MINIMUM SPANNING TREE (MST) — OVERVIEW
 *
 * ---------------------------------------------------------
 * 1. PROBLEM STATEMENT
 * ---------------------------------------------------------
 * Given an undirected, connected, weighted graph, a Minimum Spanning Tree (MST) is a subset of edges
 * that connects all the vertices with the minimum possible total edge weight and without cycles.
 *
 * ---------------------------------------------------------
 * 2. KEY PROPERTIES
 * ---------------------------------------------------------
 * ✔ MST spans all vertices
 * ✔ MST has exactly V-1 edges (for V vertices) when the graph is connected
 * ✔ There may be multiple MSTs with the same total weight
 *
 * ---------------------------------------------------------
 * 3. COMMON ALGORITHMS
 * ---------------------------------------------------------
 * - Prim's algorithm (greedy, uses priority queue; grows one tree)
 * - Kruskal's algorithm (greedy, sorts edges and uses Disjoint Set Union to avoid cycles)
 *
 * ---------------------------------------------------------
 * 4. WHEN TO USE WHICH
 * ---------------------------------------------------------
 * - Use Prim when graph is dense or when implementing with adjacency list + binary heap O(E log V)
 * - Use Kruskal when edges can be sorted efficiently, often simpler with DSU: O(E log E)
 *
 * ---------------------------------------------------------
 * 5. TIME & SPACE COMPLEXITY (GENERAL)
 * ---------------------------------------------------------
 * Prim (with PQ): O(E log V), Kruskal: O(E log E) ≈ O(E log V); Space: O(V+E)
 *
 * ---------------------------------------------------------
 * 6. IMPLEMENTATION NOTE
 * ---------------------------------------------------------
 * This file gives a conceptual overview and will be complemented by concrete implementations
 * in `PrimsAlgorithm.java` and `KruskalAlgorithm.java` in the same folder.
 *
 * =========================================================
 */

@SuppressWarnings("unused")
public class MinimumSpanningTree {

    public static void main(String[] args) {
        System.out.println("MST overview (see PrimsAlgorithm and KruskalAlgorithm for concrete implementations)");
    }
}
