package Graphs.ShortestPathAlgo_Problems;

import java.util.*;

/**
 * Path With Minimum Effort (grid path minimizing max edge cost between adjacent cells)
 *
 * Problem:
 * Given a grid of heights, move from top-left to bottom-right minimizing the maximum absolute
 * height difference along the path. Return the minimum possible effort.
 *
 * Approach:
 * Run Dijkstra where cost to move to neighbor = max(current path cost, abs(height[u]-height[v])).
 * Use PQ keyed by current path cost.
 *
 * Example:
 * heights = [[1,2,2],[3,8,2],[5,3,5]] -> minimum effort = 2
 */

public class PathWithMinimumEffort {

    static class Node { int r,c; int cost; Node(int r,int c,int cost){this.r=r;this.c=c;this.cost=cost;} }

    public int minimumEffort(int[][] heights) {
        int n = heights.length, m = heights[0].length;
        int[][] dist = new int[n][m]; for (int i=0;i<n;i++) Arrays.fill(dist[i], Integer.MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a->a.cost));
        dist[0][0] = 0; pq.add(new Node(0,0,0));
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (cur.cost != dist[cur.r][cur.c]) continue;
            if (cur.r==n-1 && cur.c==m-1) return cur.cost;
            for (int[] d:dirs) {
                int nr = cur.r + d[0], nc = cur.c + d[1];
                if (nr>=0 && nc>=0 && nr<n && nc<m) {
                    int nd = Math.max(cur.cost, Math.abs(heights[cur.r][cur.c] - heights[nr][nc]));
                    if (nd < dist[nr][nc]) { dist[nr][nc] = nd; pq.add(new Node(nr,nc,nd)); }
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        PathWithMinimumEffort sol = new PathWithMinimumEffort();
        int[][] heights = {{1,2,2},{3,8,2},{5,3,5}};
        System.out.println(sol.minimumEffort(heights));
    }
}
