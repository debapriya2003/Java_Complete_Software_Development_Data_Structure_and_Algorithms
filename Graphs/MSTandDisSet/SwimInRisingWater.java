package Graphs.MSTandDisSet;

import java.util.*;

/**
 * SWIM IN RISING WATER (LeetCode 778)
 *
 * Problem:
 * Given an n x n grid where grid[i][j] is elevation, you start at (0,0) and want to reach (n-1,n-1).
 * At time t you can swim to any square with elevation <= t. Find the minimum time needed to reach the end.
 *
 * Approach:
 * Use a Dijkstra-like (minimax) approach: use a priority queue keyed by the current path's maximum elevation so far.
 * Pop the cell with smallest current max, expand neighbors and push updated max = max(currentMax, neighborHeight).
 * When we reach target, currentMax is the answer.
 *
 * Complexity: O(n^2 log n^2)
 */

public class SwimInRisingWater {

    static class Node { int r,c; int cost; Node(int r,int c,int cost){this.r=r;this.c=c;this.cost=cost;} }

    public int swimInWater(int[][] grid) {
        int n = grid.length;
        final int INF = Integer.MAX_VALUE/4;
        int[][] dist = new int[n][n]; for (int i=0;i<n;i++) Arrays.fill(dist[i], INF);
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a->a.cost));
        dist[0][0] = grid[0][0]; pq.add(new Node(0,0,dist[0][0]));
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
        while (!pq.isEmpty()){
            Node cur = pq.poll(); if (cur.cost != dist[cur.r][cur.c]) continue;
            if (cur.r==n-1 && cur.c==n-1) return cur.cost;
            for (int[] d:dirs){
                int nr=cur.r+d[0], nc=cur.c+d[1];
                if (nr>=0 && nc>=0 && nr<n && nc<n){
                    int nd = Math.max(cur.cost, grid[nr][nc]);
                    if (nd < dist[nr][nc]) { dist[nr][nc] = nd; pq.add(new Node(nr,nc,nd)); }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args){
        SwimInRisingWater sol = new SwimInRisingWater();
        int[][] grid = {{0,2},{1,3}}; // minimal time 3
        System.out.println(sol.swimInWater(grid));
    }
}
