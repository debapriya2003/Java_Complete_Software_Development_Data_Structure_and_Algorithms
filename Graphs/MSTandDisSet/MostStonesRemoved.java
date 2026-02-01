package Graphs.MSTandDisSet;

import java.util.*;

/**
 * MOST STONES REMOVED WITH THE SAME ROW OR COLUMN (LeetCode 947)
 *
 * Problem:
 * Given positions of stones on a 2D grid, you can remove a stone if there exists another stone in the same
 * row or the same column. Return the maximum number of stones you can remove.
 *
 * Approach:
 * Model as graph where stones are nodes and edges connect stones sharing row or column. The maximum
 * removable stones = total stones - number of connected components. Use DSU to group stones by row/col.
 *
 * Complexity: O(n * α(n)) with DSU.
 */

public class MostStonesRemoved {

    public int removeStones(int[][] stones) {
        int n = stones.length;
        DisjointSetUnionByRank dsu = new DisjointSetUnionByRank(n);
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) dsu.union(i, j);
            }
        }
        Set<Integer> comps = new HashSet<>();
        for (int i = 0; i < n; i++) comps.add(dsu.find(i));
        return n - comps.size();
    }

    public static void main(String[] args) {
        MostStonesRemoved sol = new MostStonesRemoved();
        int[][] stones = {{0,0},{0,1},{1,0},{1,2},{2,1},{2,2}};
        System.out.println(sol.removeStones(stones)); // expect 5
    }
}
