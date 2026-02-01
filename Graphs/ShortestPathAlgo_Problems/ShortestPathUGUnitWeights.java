package Graphs.ShortestPathAlgo_Problems;

import java.util.*;

/**
 * Shortest Path in Undirected Graph with Unit Weights
 *
 * Problem:
 * Given an undirected graph with unit edge weights (weight = 1 for every edge) and a source node,
 * compute shortest distance from the source to every other node.
 *
 * Approach:
 * Use BFS starting from the source. Because every edge has weight 1, BFS visits nodes in order of
 * increasing distance (level-by-level). Maintain an integer distance array initialized to -1 (unvisited).
 * Set distance[source] = 0, enqueue source, then for each popped node, visit neighbors and set their
 * distance to current distance + 1 if unvisited.
 *
 * Example:
 * V = 5, edges = [[0,1],[0,4],[1,2],[2,3],[4,3]], source = 0 -> distances = [0,1,2,3,1]
 *
 * Complexity: Time O(V + E), Space O(V + E).
 */

public class ShortestPathUGUnitWeights {

    public int[] shortestPaths(int V, List<int[]> edges, int src) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }
        int[] dist = new int[V]; Arrays.fill(dist, -1);
        Queue<Integer> q = new LinkedList<>();
        dist[src] = 0; q.add(src);
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : adj.get(u)) {
                if (dist[v] == -1) {
                    dist[v] = dist[u] + 1;
                    q.add(v);
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        ShortestPathUGUnitWeights sol = new ShortestPathUGUnitWeights();
        List<int[]> edges = Arrays.asList(new int[]{0,1}, new int[]{0,4}, new int[]{1,2}, new int[]{2,3}, new int[]{4,3});
        System.out.println(Arrays.toString(sol.shortestPaths(5, edges, 0)));
    }
}
