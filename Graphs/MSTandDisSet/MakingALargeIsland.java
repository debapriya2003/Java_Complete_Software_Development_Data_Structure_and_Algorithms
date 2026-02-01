package Graphs.MSTandDisSet;

import java.util.*;

/**
 * MAKING A LARGE ISLAND (LeetCode 827)
 *
 * Problem:
 * Given a binary grid, you may change at most one 0 to 1. Return the size of the largest island possible.
 *
 * Approach:
 * Use DSU (or label islands and compute sizes). Build components for existing 1s and their sizes. For each 0,
 * consider union of unique neighboring components and compute candidate size = 1 + sum(sizes).
 *
 * Complexity: O(n^2 α(n)) with DSU or O(n^2) labeling approach.
 */

public class MakingALargeIsland {

    public int largestIsland(int[][] grid) {
        int n = grid.length;
        DisjointSetUnionBySize dsu = new DisjointSetUnionBySize(n*n);
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
        for (int r = 0; r < n; r++) for (int c = 0; c < n; c++) if (grid[r][c]==1) {
            for (int[] d:dirs) {
                int nr=r+d[0], nc=c+d[1];
                if (nr>=0 && nc>=0 && nr<n && nc<n && grid[nr][nc]==1) dsu.union(r*n+c, nr*n+nc);
            }
        }
        int ans = 0;
        // compute sizes via parent->size array inside DSU (not directly exposed), simulate by counting
        Map<Integer,Integer> size = new HashMap<>();
        for (int r=0;r<n;r++) for (int c=0;c<n;c++) if (grid[r][c]==1) { int p=dsu.find(r*n+c); size.put(p, size.getOrDefault(p,0)+1); ans = Math.max(ans, size.get(p)); }
        for (int r=0;r<n;r++) for (int c=0;c<n;c++) if (grid[r][c]==0) {
            Set<Integer> seen = new HashSet<>(); int tot=1;
            for (int[] d:dirs) {
                int nr=r+d[0], nc=c+d[1];
                if (nr>=0 && nc>=0 && nr<n && nc<n && grid[nr][nc]==1) {
                    int p = dsu.find(nr*n+nc);
                    if (!seen.contains(p)) { seen.add(p); tot += size.getOrDefault(p,0); }
                }
            }
            ans = Math.max(ans, tot);
        }
        return ans;
    }

    public static void main(String[] args) {
        MakingALargeIsland sol = new MakingALargeIsland();
        int[][] grid = {{1,0},{0,1}};
        System.out.println(sol.largestIsland(grid)); // expect 3
    }
}
