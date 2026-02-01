package Graphs.others;

import java.util.*;

/**
 * KOSARAJU'S ALGORITHM for Strongly Connected Components (SCC)
 *
 * Problem:
 * Given a directed graph with n vertices, find all strongly connected components (SCCs).
 * Each SCC is a maximal set of vertices where each vertex is reachable from every other vertex in the set.
 *
 * Approach (Kosaraju):
 * 1. Run DFS on original graph and push nodes onto a stack in order of finishing times (post-order).
 * 2. Reverse all edges of the graph to obtain the transpose graph.
 * 3. Pop vertices from the stack and run DFS on the transpose graph. Each DFS from a popped node yields one SCC.
 *
 * Complexity: O(V + E) time and O(V + E) space
 *
 * Example:
 * Graph edges: 0->1,1->2,2->0 (SCC), 1->3,3->4,4->5,5->3 (SCC) -> components: [0,1,2] and [3,4,5]
 */
public class KosarajuAlgorithm {

    private void dfs1(int v, boolean[] vis, List<Integer>[] adj, Deque<Integer> stack) {
        vis[v] = true;
        for (int to : adj[v]) if (!vis[to]) dfs1(to, vis, adj, stack);
        stack.push(v);
    }

    private void dfs2(int v, boolean[] vis, List<Integer>[] revAdj, List<Integer> comp) {
        vis[v] = true; comp.add(v);
        for (int to : revAdj[v]) if (!vis[to]) dfs2(to, vis, revAdj, comp);
    }

    public List<List<Integer>> kosaraju(int n, List<Integer>[] adj) {
        boolean[] vis = new boolean[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) if (!vis[i]) dfs1(i, vis, adj, stack);

        // build reversed graph
        @SuppressWarnings("unchecked")
        List<Integer>[] revAdj = new ArrayList[n];
        for (int i = 0; i < n; i++) revAdj[i] = new ArrayList<>();
        for (int i = 0; i < n; i++) for (int j : adj[i]) revAdj[j].add(i);

        Arrays.fill(vis, false);
        List<List<Integer>> sccs = new ArrayList<>();
        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!vis[node]) {
                List<Integer> comp = new ArrayList<>();
                dfs2(node, vis, revAdj, comp);
                sccs.add(comp);
            }
        }
        return sccs;
    }

    public static void main(String[] args) {
        int n = 6;
        @SuppressWarnings("unchecked")
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        int[][] edges = {{0,1},{1,2},{2,0},{1,3},{3,4},{4,5},{5,3}}; // SCCs: [0,1,2] and [3,4,5]
        for (int[] e : edges) adj[e[0]].add(e[1]);

        KosarajuAlgorithm solver = new KosarajuAlgorithm();
        List<List<Integer>> sccs = solver.kosaraju(n, adj);
        System.out.println("Strongly Connected Components:");
        for (List<Integer> comp : sccs) System.out.println(comp);
    }
}
