package Graphs.others;

import java.util.*;

/**
 * BRIDGES IN GRAPH (Tarjan's algorithm / low-link values)
 *
 * Problem:
 * Given an undirected connected graph with n vertices (0-indexed) and edges, find all bridges.
 * A bridge is an edge that, when removed, increases the number of connected components.
 *
 * Approach:
 * Use a DFS-based low-link algorithm. Maintain arrays:
 * - tin[v]: time of first visit (discovery time) for v
 * - low[v]: the lowest discovery time reachable from v using at most one back-edge
 * During DFS, for edge v -> to:
 * - if 'to' is parent, skip
 * - if 'to' is visited, update low[v] = min(low[v], tin[to]) (back-edge)
 * - otherwise recurse and update low[v] = min(low[v], low[to]). If low[to] > tin[v], then (v,to) is a bridge.
 *
 * Complexity: O(V + E) time and O(V + E) space
 *
 * Example:
 * Graph: 0--1--3 and 0--2 with edges {0-1,1-2,2-0,1-3}
 * Bridges: (1,3)
 */
public class BridgesInGraph {
    private int timer;

    private void dfs(int v, int parent, List<Integer>[] adj, boolean[] visited, int[] tin, int[] low, List<int[]> bridges) {
        visited[v] = true;
        tin[v] = low[v] = timer++;
        for (int to : adj[v]) {
            if (to == parent) continue; // skip the edge we came from
            if (visited[to]) {
                // back edge
                low[v] = Math.min(low[v], tin[to]);
            } else {
                dfs(to, v, adj, visited, tin, low, bridges);
                low[v] = Math.min(low[v], low[to]);
                if (low[to] > tin[v]) {
                    bridges.add(new int[]{v, to});
                }
            }
        }
    }

    public List<int[]> findBridges(int n, List<Integer>[] adj) {
        timer = 0;
        boolean[] visited = new boolean[n];
        int[] tin = new int[n];
        int[] low = new int[n];
        List<int[]> bridges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!visited[i]) dfs(i, -1, adj, visited, tin, low, bridges);
        }
        return bridges;
    }

    // Simple driver
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        int n = 4;
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        int[][] edges = {{0,1},{1,2},{2,0},{1,3}}; // bridge is 1-3
        for (int[] e : edges) { adj[e[0]].add(e[1]); adj[e[1]].add(e[0]); }

        BridgesInGraph solver = new BridgesInGraph();
        List<int[]> bridges = solver.findBridges(n, adj);
        System.out.println("Bridges found:");
        for (int[] b : bridges) System.out.println(b[0] + " - " + b[1]);
    }
}
