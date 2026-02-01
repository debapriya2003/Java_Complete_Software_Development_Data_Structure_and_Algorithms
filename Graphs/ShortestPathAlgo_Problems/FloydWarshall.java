package Graphs.ShortestPathAlgo_Problems;

import java.util.*;

/**
 * Floyd-Warshall Algorithm (All-Pairs Shortest Paths)
 *
 * Problem:
 * Compute shortest paths between all pairs of vertices in a weighted graph (can handle negative
 * edges but no negative cycles).
 *
 * Approach:
 * Initialize dist matrix with edge weights (INF where no edge, 0 on diagonal). Then for each k as intermediate,
 * relax dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j]). After k iterations, dist contains final shortest paths.
 *
 * Complexity: O(V^3) time, O(V^2) space.
 */

public class FloydWarshall {

    public long[][] floydWarshall(long[][] input) {
        int n = input.length;
        long INF = Long.MAX_VALUE/4;
        long[][] dist = new long[n][n];
        for (int i=0;i<n;i++) for (int j=0;j<n;j++) dist[i][j] = input[i][j];
        for (int k=0;k<n;k++) for (int i=0;i<n;i++) if (dist[i][k] < INF) for (int j=0;j<n;j++) if (dist[k][j] < INF) dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
        return dist;
    }

    public static void main(String[] args){
        long INF = Long.MAX_VALUE/4;
        long[][] input = {
            {0,3,INF,7},
            {8,0,2,INF},
            {5,INF,0,1},
            {2,INF,INF,0}
        };
        FloydWarshall sol = new FloydWarshall();
        long[][] res = sol.floydWarshall(input);
        for (long[] row: res) System.out.println(Arrays.toString(row));
    }
}
