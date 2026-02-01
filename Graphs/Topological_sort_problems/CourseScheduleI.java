package Graphs.Topological_sort_problems;

import java.util.*;

/**
 * Course Schedule I — Detailed Explanation (Kahn's Algorithm for Cycle Detection)
 *
 * This problem asks whether it is possible to finish all courses given prerequisites; it reduces to
 * checking whether the directed graph (courses as nodes, prerequisites as directed edges) contains a
 * cycle. If there is a cycle, some courses depend on each other in a loop and no valid order exists.
 * The standard and efficient approach uses Kahn's algorithm (topological sort via BFS).
 *
 * Step-by-step explanation (illustrated with compact code fragments):
 * 1) Build adjacency list and in-degree array:
 *    int[] indeg = new int[numCourses];
 *    for (int[] p : prerequisites) { adj.get(p[1]).add(p[0]); indeg[p[0]]++; }
 * 2) Add all nodes with indegree 0 to a queue (these have no prerequisites):
 *    Queue<Integer> q = new LinkedList<>(); for(i...) if (indeg[i]==0) q.add(i);
 * 3) Repeatedly remove a node from the queue, decrement the indegree of its neighbors,
 *    and if any neighbor's indegree becomes 0, add it to the queue. Track how many nodes are processed.
 * 4) After queue processing, if processed count == numCourses, the graph is acyclic and all courses
 *    can be finished; otherwise a cycle exists and completion is impossible.
 *
 * Why this works: Topological sort processes nodes in dependency order; nodes in a cycle never reach
 * indegree 0 and thus remain unprocessed, providing a clear cycle detection mechanism.
 * Time Complexity: O(V + E), Space: O(V + E).
 */

public class CourseScheduleI {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) adj.add(new ArrayList<>());
        int[] indeg = new int[numCourses];
        for (int[] p : prerequisites) {
            adj.get(p[1]).add(p[0]);
            indeg[p[0]]++;
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) if (indeg[i] == 0) q.add(i);
        int processed = 0;
        while (!q.isEmpty()) {
            int cur = q.poll(); processed++;
            for (int nei : adj.get(cur)) {
                indeg[nei]--;
                if (indeg[nei] == 0) q.add(nei);
            }
        }
        return processed == numCourses;
    }

    public static void main(String[] args) {
        CourseScheduleI sol = new CourseScheduleI();
        System.out.println(sol.canFinish(2, new int[][]{{1,0}})); // true
        System.out.println(sol.canFinish(2, new int[][]{{1,0},{0,1}})); // false
    }
}