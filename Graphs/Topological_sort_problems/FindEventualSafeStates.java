package Graphs.Topological_sort_problems;

import java.util.*;

/**
 * Find Eventual Safe States — Deep Explanation with Code Sketch
 *
 * Definition: A node is eventually safe if every path starting from it leads to a terminal node
 * (a node with no outgoing edges) and never to a cycle. In practice, nodes that can reach cycles are
 * unsafe; nodes that cannot reach any cycle are safe.
 *
 * Approach (3-color DFS):
 * - Use an int[] state where 0 = unvisited, 1 = visiting (on current recursion stack), 2 = safe.
 * - For node 'u', mark state[u] = 1, then explore neighbors. If any neighbor is in state 1, a cycle
 *   is detected and 'u' is unsafe. If a neighbor is unvisited, recursively evaluate it; if it is
 *   unsafe, then 'u' is unsafe. If all neighbors are safe (no cycles found), mark state[u] = 2.
 *
 * Key code-pattern:
 *    if (state[node] != 0) return state[node] == 2;
 *    state[node] = 1;
 *    for (nei : graph[node]) { if (state[nei]==1) return false; if (state[nei]==0 && !dfs(nei)) return false; }
 *    state[node] = 2; return true;
 *
 * This technique memoizes results and avoids repeated work; once a node is marked safe (2), subsequent
 * calls are O(1). Complexity: Time O(V+E) because each edge and vertex processed at most once, Space O(V).
 */

public class FindEventualSafeStates {

    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        int[] state = new int[n]; // 0 unvisited, 1 visiting, 2 safe
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (dfs(i, graph, state)) res.add(i);
        }
        return res;
    }

    private boolean dfs(int node, int[][] graph, int[] state) {
        if (state[node] != 0) return state[node] == 2; // already computed
        state[node] = 1; // mark as visiting
        for (int nei : graph[node]) {
            // If neighbor is visiting -> cycle; if neighbor leads to cycle -> unsafe
            if (state[nei] == 1) return false;
            if (state[nei] == 0 && !dfs(nei, graph, state)) return false;
        }
        state[node] = 2; // safe
        return true;
    }

    public static void main(String[] args) {
        FindEventualSafeStates sol = new FindEventualSafeStates();
        int[][] graph = {{1,2},{2,3},{5},{0},{5}, {}, {}};
        System.out.println("Safe nodes: " + sol.eventualSafeNodes(graph));
    }
}
