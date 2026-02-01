package Graphs.Topological_sort_problems;

import java.util.*;

/**
 * Course Schedule II — Detailed Explanation and Worked Example
 *
 * This variant requires returning a valid course ordering (topological order) if one exists, or an
 * empty array if impossible. The typical solution is Kahn's algorithm used to produce a topological
 * ordering by repeatedly removing nodes with indegree zero.
 *
 * Explanation (with inline code-style steps):
 * 1) Build adjacency list and indegree array:
 *    for (int[] p : prerequisites) { adj.get(p[1]).add(p[0]); indeg[p[0]]++; }
 * 2) Initialize queue with indegree-zero nodes and iterate:
 *    Queue<Integer> q = new LinkedList<>(); for (i) if (indeg[i]==0) q.add(i);
 *    while (!q.isEmpty()) { int cur = q.poll(); order[idx++] = cur; for (nei : adj.get(cur)) { indeg[nei]--; if (indeg[nei]==0) q.add(nei); } }
 * 3) If we have produced numCourses entries (idx == numCourses), return the order; otherwise return empty (cycle detected).
 *
 * Worked example:
 * numCourses=4, prereq=[[1,0],[2,0],[3,1],[3,2]] -> indegrees start as [0,1,1,2] -> queue initially [0] -> processed order [0,1,2,3].
 *
 * Complexity: Time O(V+E), Space O(V+E). This method is robust and easy to understand; it naturally detects cycles.
 */

public class CourseScheduleII {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) adj.add(new ArrayList<>());
        int[] indeg = new int[numCourses];
        for (int[] p : prerequisites) {
            int u = p[1], v = p[0];
            adj.get(u).add(v);
            indeg[v]++;
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) if (indeg[i] == 0) q.add(i);
        int[] order = new int[numCourses];
        int idx = 0;
        while (!q.isEmpty()) {
            int cur = q.poll();
            order[idx++] = cur;
            for (int nei : adj.get(cur)) {
                indeg[nei]--;
                if (indeg[nei] == 0) q.add(nei);
            }
        }
        return idx == numCourses ? order : new int[0];
    }

    public static void main(String[] args) {
        CourseScheduleII sol = new CourseScheduleII();
        int[][] prereq = {{1,0},{2,0},{3,1},{3,2}};
        System.out.println("Course order: " + Arrays.toString(sol.findOrder(4, prereq)));
        int[][] cyc = {{1,0},{0,1}};
        System.out.println("Course order (cycle): " + Arrays.toString(sol.findOrder(2, cyc))); // empty
    }
}
