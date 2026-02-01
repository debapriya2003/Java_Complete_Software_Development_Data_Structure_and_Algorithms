package Dynamic_Programming.LIS;

import java.util.*;

/**
 * PRINT LONGEST INCREASING SUBSEQUENCE (DP-42)
 *
 * Problem:
 * Reconstruct and return one actual longest increasing subsequence (not just its length).
 *
 * Approach:
 * Use the O(n log n) patience method but maintain `prev` pointers and `tails` storing indices.
 * For each element, find its place in tails via binary search on values; set prev[current] = tails[pos-1]
 * and update tails[pos] = current. After processing, reconstruct by following prev from tails[size-1].
 *
 * Complexity: O(n log n) time, O(n) space.
 *
 * Example:
 * nums = [10,9,2,5,3,7,101,18] -> one LIS = [2,3,7,101]
 */
public class PrintLongestIncreasingSubsequence {

    public static List<Integer> lis(int[] nums) {
        int n = nums.length;
        if (n == 0) return new ArrayList<>();
        int[] tails = new int[n]; // store indices of smallest tail for each length
        int[] prev = new int[n]; // predecessor index for reconstruction
        Arrays.fill(prev, -1);
        int size = 0;
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            // binary search on tails by value
            int l = 0, r = size;
            while (l < r) {
                int mid = (l + r) >>> 1;
                if (nums[tails[mid]] < x) l = mid + 1; else r = mid;
            }
            int pos = l;
            if (pos > 0) prev[i] = tails[pos - 1];
            tails[pos] = i;
            if (pos == size) size++;
        }
        // reconstruct
        List<Integer> res = new ArrayList<>();
        int idx = tails[size - 1];
        while (idx != -1) { res.add(nums[idx]); idx = prev[idx]; }
        Collections.reverse(res);
        return res;
    }

    public static void main(String[] args) {
        System.out.println(lis(new int[]{10,9,2,5,3,7,101,18})); // [2,3,7,101]
    }
}
