package Graphs.MSTandDisSet;

import java.util.*;

/**
 * DISJOINT SET UNION (UNION BY RANK + PATH COMPRESSION)
 *
 * ---------------------------------------------------------
 * 1. PROBLEM STATEMENT
 * ---------------------------------------------------------
 * Maintain disjoint sets with two operations: find(x) returns representative, union(x,y) merges sets.
 * Union by rank keeps the tree shallow by attaching smaller rank tree under larger rank tree.
 * Path compression flattens the tree on find operations.
 *
 * ---------------------------------------------------------
 * 2. BENEFITS
 * ---------------------------------------------------------
 * - Amortized nearly constant time per operation: inverse-Ackermann complexity (effectively O(1)).
 * - Very useful in Kruskal's MST, connectivity queries, and many graph problems.
 *
 * ---------------------------------------------------------
 * 3. OPERATIONS
 * ---------------------------------------------------------
 * - find(x): with path compression
 * - union(x,y): attach lower rank tree to higher rank; if equal increment rank
 *
 * ---------------------------------------------------------
 * 4. COMPLEXITY
 * ---------------------------------------------------------
 * Time: O(alpha(N)) amortized per operation, Space: O(N)
 *
 * =========================================================
 */

@SuppressWarnings("unused")
public class DisjointSetUnionByRank {

    private int[] parent, rank;

    public DisjointSetUnionByRank(int n) {
        parent = new int[n]; rank = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;
    }

    public int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    public boolean union(int a, int b) {
        int ra = find(a), rb = find(b);
        if (ra == rb) return false;
        if (rank[ra] < rank[rb]) parent[ra] = rb;
        else if (rank[rb] < rank[ra]) parent[rb] = ra;
        else { parent[rb] = ra; rank[ra]++; }
        return true;
    }

    public static void main(String[] args) {
        DisjointSetUnionByRank dsu = new DisjointSetUnionByRank(5);
        dsu.union(0,1); dsu.union(1,2);
        System.out.println("Find(2): " + dsu.find(2)); // same rep as 0
    }
}
