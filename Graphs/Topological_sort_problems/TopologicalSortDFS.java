package Graphs.Topological_sort_problems;

import java.util.*;

/**
 * Topological Sort using DFS — In-depth Explanation (Postorder / Reverse Finish Time)
 *
 * Idea: A topological ordering can also be computed using DFS by recording nodes in reverse finish
 * time (postorder). When DFS finishes exploring a node (all descendants processed), we push the node
 * onto a stack. After processing all nodes, popping the stack yields a valid topological order.
 *
 * Steps with annotated code sketch:
 * 1) Maintain a visited[] array and a stack (Deque<Integer> stack = new ArrayDeque<>()).
 * 2) For each vertex 'i' not visited, call dfs(i). DFS:
 *    visited[node] = true;
 *    for (nei : adj.get(node)) if (!visited[nei]) dfs(nei);
 *    stack.push(node); // postorder capture
 * 3) After DFS for all vertices, pop stack into a list to get the topological order.
 *
 * Differences vs Kahn: DFS is recursive and uses implicit call-stack order to compute finish times,
 * while Kahn is iterative and relies on indegree. Both produce valid orders for DAGs; choice depends on
 * context (stack depth, need to detect cycles, preference).
 * Complexity: Time O(V+E), Space O(V) (plus recursion stack).
 */

public class TopologicalSortDFS {

    public List<Integer> topoSort(int V, List<List<Integer>> adj) {
        boolean[] vis = new boolean[V];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < V; i++) {
            if (!vis[i]) dfs(i, vis, adj, stack);
        }
        List<Integer> res = new ArrayList<>();
        while (!stack.isEmpty()) res.add(stack.pop());
        return res;
    }

    private void dfs(int node, boolean[] vis, List<List<Integer>> adj, Deque<Integer> stack) {
        vis[node] = true;
        for (int nei : adj.get(node)) {
            if (!vis[nei]) dfs(nei, vis, adj, stack);
        }
        stack.push(node);
    }

    public static void main(String[] args) {
        int V = 6;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());
        adj.get(5).add(2);
        adj.get(5).add(0);
        adj.get(4).add(0);
        adj.get(4).add(1);
        adj.get(2).add(3);
        adj.get(3).add(1);
        TopologicalSortDFS sol = new TopologicalSortDFS();
        System.out.println("Topo order (DFS): " + sol.topoSort(V, adj));
    }
}