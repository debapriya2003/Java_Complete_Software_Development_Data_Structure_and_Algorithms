package Graphs.BFSandDFS;

import java.util.*;

/**
 * 0/1 Matrix (Distance to Nearest Zero)
 *
 * Problem:
 * For each cell that contains 1, compute the distance to the nearest 0. This is a classical
 * multi-source BFS problem where all zeros are enqueued initially.
 *
 * Approach:
 * - Enqueue all cells with value 0 (their distance is 0) and mark visited.
 * - Perform BFS expanding level by level; when visiting a neighbor, set its distance to current + 1.
 *
 * Example:
 * mat = [[0,0,0],[0,1,0],[1,1,1]] -> distances:
 * [0,0,0]
 * [0,1,0]
 * [1,2,1]
 *
 * Complexity: Time O(n*m), Space O(n*m)
 */

public class ZeroOneMatrix {

    public int[][] updateMatrix(int[][] mat) {
        int n = mat.length, m = mat[0].length;
        int[][] dist = new int[n][m];
        boolean[][] vis = new boolean[n][m];
        Queue<int[]> q = new LinkedList<>();
        // Multi-source: push all zeros and mark them visited
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 0) { q.add(new int[]{i,j}); vis[i][j] = true; }
            }
        }
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int[] d : dirs) {
                int ni = cur[0] + d[0], nj = cur[1] + d[1];
                if (ni >= 0 && nj >= 0 && ni < n && nj < m && !vis[ni][nj]) {
                    // distance is parent's distance + 1
                    dist[ni][nj] = dist[cur[0]][cur[1]] + 1;
                    vis[ni][nj] = true;
                    q.add(new int[]{ni,nj});
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        ZeroOneMatrix sol = new ZeroOneMatrix();
        int[][] mat = {{0,0,0},{0,1,0},{1,1,1}};
        int[][] res = sol.updateMatrix(mat);
        for (int[] r : res) System.out.println(Arrays.toString(r));
    }
}