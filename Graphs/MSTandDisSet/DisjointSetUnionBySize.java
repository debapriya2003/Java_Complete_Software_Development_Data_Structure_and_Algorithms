package Graphs.MSTandDisSet;

import java.util.*;

/**
 * DISJOINT SET UNION (UNION BY SIZE + PATH COMPRESSION)
 *
 * ---------------------------------------------------------
 * Instead of rank, maintain size of each set and attach smaller set under larger set when unioning.
 * Otherwise same benefits as union by rank.
 *
 * =========================================================
 */

@SuppressWarnings("unused")
public class DisjointSetUnionBySize {
    private int[] parent, size;

    public DisjointSetUnionBySize(int n) {
        parent = new int[n]; size = new int[n];
        for (int i = 0; i < n; i++) { parent[i] = i; size[i] = 1; }
    }

    public int find(int x) {
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    public boolean union(int a, int b) {
        int ra = find(a), rb = find(b);
        if (ra == rb) return false;
        if (size[ra] < size[rb]) { parent[ra] = rb; size[rb] += size[ra]; }
        else { parent[rb] = ra; size[ra] += size[rb]; }
        return true;
    }

    public static void main(String[] args) {
        DisjointSetUnionBySize dsu = new DisjointSetUnionBySize(5);
        dsu.union(0,1); dsu.union(1,2);
        System.out.println("Find(2): " + dsu.find(2));
    }
}
