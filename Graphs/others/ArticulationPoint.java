package Graphs.others;

import java.util.*;

/**
 * ARTICULATION POINTS (Cut Vertices) in an undirected graph
 *
 * Problem:
 * Given an undirected graph, find all articulation points (vertices which when removed increase
 * the number of connected components).
 *
 * Approach:
 * Use a DFS low-link approach similar to bridges. Keep discovery time tin[v] and low[v].
 * During DFS from node v:
 * - For a child 'to' that is already visited (back-edge), update low[v] = min(low[v], tin[to]).
 * - For an unvisited child, recursively visit and set low[v] = min(low[v], low[to]) and then:
 *   - If v is root and it has 2 or more children, v is an articulation point.
 *   - If v is not root and low[to] >= tin[v], then v is an articulation point (no back-edge from 'to' subtree to ancestor of v).
 *
 * Complexity: O(V + E) time and O(V + E) space
 *
 * Example:
 * For graph with edges {0-1,1-2,2-0,1-3}, articulation point is 1 (removing 1 disconnects node 3).
 */
public class ArticulationPoint {
    private int timer;

    private void dfs(int v, int parent, List<Integer>[] adj, boolean[] visited, int[] tin, int[] low, boolean[] ap) {
        visited[v] = true;
        tin[v] = low[v] = timer++;
        int children = 0;
        for (int to : adj[v]) {
            if (to == parent) continue;
            if (visited[to]) {
                low[v] = Math.min(low[v], tin[to]);
            } else {
                children++;
                dfs(to, v, adj, visited, tin, low, ap);
                low[v] = Math.min(low[v], low[to]);
                if (parent != -1 && low[to] >= tin[v]) ap[v] = true;
            }
        }
        if (parent == -1 && children > 1) ap[v] = true;
    }

    public List<Integer> findArticulationPoints(int n, List<Integer>[] adj) {
        timer = 0;
        boolean[] visited = new boolean[n];
        int[] tin = new int[n];
        int[] low = new int[n];
        boolean[] ap = new boolean[n];
        for (int i = 0; i < n; i++) if (!visited[i]) dfs(i, -1, adj, visited, tin, low, ap);
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) if (ap[i]) res.add(i);
        return res;
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        int n = 4;
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        int[][] edges = {{0,1},{1,2},{2,0},{1,3}}; // articulation point: 1
        for (int[] e : edges) { adj[e[0]].add(e[1]); adj[e[1]].add(e[0]); }

        ArticulationPoint solver = new ArticulationPoint();
        List<Integer> points = solver.findArticulationPoints(n, adj);
        System.out.println("Articulation points:");
        for (int v : points) System.out.println(v);
    }
}
