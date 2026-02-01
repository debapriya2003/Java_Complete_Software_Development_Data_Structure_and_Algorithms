package Graphs.ShortestPathAlgo_Problems;

import java.util.*;

/**
 * Minimum multiplications to reach end using array elements with modulo operation
 *
 * Problem (GFG style):
 * Given an array arr[] of integers and two integers start and end, find minimum number of
 * multiplications (multiplying current value by any element in arr, then taking modulo 100000)
 * to reach end from start. Return -1 if impossible.
 *
 * Approach:
 * BFS on states (current value). State-space is modulo M (100000). Use queue and distance array
 * of size M. Each BFS step tries multiplying current by each arr[i] mod M and enqueues unseen states.
 *
 * Complexity: O(M * N) worst-case where M is modulo base and N number of multipliers.
 */

public class MinMultiplicationsModulo {

    public int minimumMultiplications(int[] arr, int start, int end) {
        final int MOD = 100000;
        int[] dist = new int[MOD]; Arrays.fill(dist, -1);
        Queue<Integer> q = new LinkedList<>();
        dist[start] = 0; q.add(start);
        while(!q.isEmpty()){
            int cur = q.poll();
            if (cur == end) return dist[cur];
            for (int a: arr){
                int nxt = (int)((1L * cur * a) % MOD);
                if (dist[nxt] == -1){ dist[nxt] = dist[cur] + 1; q.add(nxt); }
            }
        }
        return -1;
    }

    public static void main(String[] args){
        MinMultiplicationsModulo sol = new MinMultiplicationsModulo();
        System.out.println(sol.minimumMultiplications(new int[]{2,5,7}, 3, 30));
    }
}
