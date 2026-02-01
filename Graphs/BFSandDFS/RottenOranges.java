package Graphs.BFSandDFS;

import java.util.*;

/**
 * Rotten Oranges
 *
 * Problem:
 * Given a grid where 0 = empty, 1 = fresh orange, 2 = rotten orange, compute the minimum number of
 * minutes until no fresh oranges remain. Each minute, rotten oranges rot adjacent fresh oranges (4 directions).
 * If some fresh oranges cannot be reached, return -1.
 *
 * Approach:
 * - Use multi-source BFS: enqueue all initially rotten oranges and perform level-order BFS.
 * - Track the number of fresh oranges; decrease when a fresh orange becomes rotten.
 * - Each level of BFS corresponds to one minute; increment minute only if any fresh orange became rotten during the level.
 *
 * Example:
 * grid = [[2,1,1],[1,1,0],[0,1,1]] -> output 4 minutes.
 *
 * Complexity: Time O(n*m), Space O(n*m).
 */

public class RottenOranges {

    public int orangesRotting(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        Queue<int[]> q = new LinkedList<>();
        int fresh = 0;
        // Collect initial rotten oranges (multi-source) and count fresh ones
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 2) q.add(new int[]{i,j});
                else if (grid[i][j] == 1) fresh++;
            }
        }
        if (fresh == 0) return 0;
        int minutes = 0;
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
        while (!q.isEmpty()) {
            int sz = q.size();
            boolean turned = false; // tracks if any orange turned during this minute
            for (int k = 0; k < sz; k++) {
                int[] cur = q.poll();
                for (int[] d : dirs) {
                    int ni = cur[0] + d[0], nj = cur[1] + d[1];
                    if (ni >= 0 && nj >= 0 && ni < n && nj < m && grid[ni][nj] == 1) {
                        grid[ni][nj] = 2; // fresh becomes rotten
                        q.add(new int[]{ni,nj});
                        fresh--;
                        turned = true;
                    }
                }
            }
            if (turned) minutes++; // only increment when at least one fresh turned rotten this round
        }
        return fresh == 0 ? minutes : -1;
    }

    public static void main(String[] args) {
        RottenOranges sol = new RottenOranges();
        int[][] grid = {
            {2,1,1},
            {1,1,0},
            {0,1,1}
        };
        System.out.println("Minutes to rot all: " + sol.orangesRotting(grid));
    }
}
