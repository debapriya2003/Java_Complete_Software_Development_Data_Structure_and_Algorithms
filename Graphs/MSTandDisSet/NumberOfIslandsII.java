package Graphs.MSTandDisSet;

import java.util.*;

/**
 * NUMBER OF ISLANDS II (LeetCode 305)
 *
 * Problem:
 * Initially a grid is full of water. Add lands at given positions and after each addition return the number
 * of islands. Islands are connected 4-directionally.
 *
 * Approach:
 * Use DSU to unite newly added land with neighboring lands. Maintain a count of current islands; when new land
 * connects two different components, decrement count accordingly.
 *
 * Complexity: O(k α(n)) where k is number of additions and n grid cells; Space O(n).
 */

public class NumberOfIslandsII {

    private int rows, cols;
    private DisjointSetUnionByRank dsu;
    private boolean[] isLand;
    private int count;

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        this.rows = m; this.cols = n; int total = m * n;
        dsu = new DisjointSetUnionByRank(total);
        isLand = new boolean[total];
        count = 0;
        List<Integer> res = new ArrayList<>();
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};
        for (int[] p : positions) {
            int r = p[0], c = p[1];
            int idx = r * cols + c;
            if (isLand[idx]) { res.add(count); continue; }
            isLand[idx] = true; count++;
            for (int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                if (nr >= 0 && nc >= 0 && nr < rows && nc < cols) {
                    int nidx = nr * cols + nc;
                    if (isLand[nidx]) {
                        int r1 = dsu.find(idx), r2 = dsu.find(nidx);
                        if (r1 != r2) { dsu.union(idx, nidx); count--; }
                    }
                }
            }
            res.add(count);
        }
        return res;
    }

    public static void main(String[] args) {
        NumberOfIslandsII sol = new NumberOfIslandsII();
        int[][] pos = {{0,0},{0,1},{1,2},{2,1}};
        System.out.println(sol.numIslands2(3,3,pos)); // example result
    }
}
