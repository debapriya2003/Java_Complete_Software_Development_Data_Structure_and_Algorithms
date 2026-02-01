package Graphs.MSTandDisSet;

import java.util.*;

/**
 * NUMBER OF OPERATIONS TO MAKE NETWORK CONNECTED
 *
 * Problem (LeetCode 1319):
 * Given n computers and a list of connections where each connection is a pair of computers, return the minimum
 * number of operations (re-wiring an edge between two computers) required to make all computers connected.
 * If not possible, return -1.
 *
 * Approach:
 * - If number of connections < n - 1, impossible: return -1.
 * - Otherwise use DSU to count number of connected components; answer = components - 1.
 *
 * Complexity: O(n + connections) with DSU.
 */

public class MakeNetworkConnected {

    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) return -1;
        DisjointSetUnionByRank dsu = new DisjointSetUnionByRank(n);
        for (int[] c : connections) dsu.union(c[0], c[1]);
        Set<Integer> comps = new HashSet<>();
        for (int i = 0; i < n; i++) comps.add(dsu.find(i));
        return comps.size() - 1;
    }

    public static void main(String[] args) {
        MakeNetworkConnected sol = new MakeNetworkConnected();
        int[][] con = {{0,1},{0,2},{0,3}};
        System.out.println(sol.makeConnected(4, con)); // 0 (already connected)
    }
}
