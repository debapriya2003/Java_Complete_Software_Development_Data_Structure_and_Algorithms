package greedy_algorithms.medium;

import java.util.*;

/**
 * Merge Intervals
 *
 * Problem:
 * You are given an array of intervals where intervals[i] = [start, end].
 *
 * Task:
 * Merge all overlapping intervals and return an array of
 * non-overlapping intervals that cover all the intervals.
 *
 * ---------------------------------------------------------
 * Key Observations:
 * ---------------------------------------------------------
 * - Intervals can overlap only if they intersect in time
 * - Sorting intervals by start time simplifies merging
 * - Once sorted, overlapping intervals appear consecutively
 *
 * ---------------------------------------------------------
 * Approach: Greedy + Sorting
 * ---------------------------------------------------------
 * 1. Sort intervals based on start time
 * 2. Initialize a result list
 * 3. Iterate through intervals:
 *      - If current interval overlaps with last merged interval,
 *        merge them by updating the end time
 *      - Otherwise, add the interval to result
 *
 * Greedy Justification:
 * - Always merge as early as possible
 * - Local merge decisions lead to a globally merged solution
 *
 * ---------------------------------------------------------
 * Example:
 * intervals = [[1,3],[2,6],[8,10],[15,18]]
 *
 * Sorted:
 * [[1,3],[2,6],[8,10],[15,18]]
 *
 * Merging:
 * [1,3] + [2,6] → [1,6]
 * [8,10] stays
 * [15,18] stays
 *
 * Output:
 * [[1,6],[8,10],[15,18]]
 *
 * ---------------------------------------------------------
 * Time Complexity: O(n log n)
 * Space Complexity: O(n)
 */

public class MergeIntervals {

    /**
     * Merges overlapping intervals
     *
     * @param intervals array of intervals
     * @return merged non-overlapping intervals
     */
    public static int[][] merge(int[][] intervals) {

        // Edge case: empty or single interval
        if (intervals == null || intervals.length <= 1) {
            return intervals;
        }

        // Sort intervals by start time
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        List<int[]> merged = new ArrayList<>();

        // Start with the first interval
        int[] current = intervals[0];
        merged.add(current);

        // Traverse remaining intervals
        for (int i = 1; i < intervals.length; i++) {

            int[] next = intervals[i];

            // Overlap condition
            if (next[0] <= current[1]) {
                // Merge intervals
                current[1] = Math.max(current[1], next[1]);
            }
            // No overlap
            else {
                current = next;
                merged.add(current);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }

    public static void main(String[] args) {

        System.out.println("=== Merge Intervals Problem ===\n");

        int[][] intervals = {
                {1, 3},
                {2, 6},
                {8, 10},
                {15, 18}
        };

        System.out.println("Original Intervals : " + Arrays.deepToString(intervals));

        int[][] result = merge(intervals);

        System.out.println("Merged Intervals   : " + Arrays.deepToString(result));

        System.out.println("\n" + "=".repeat(60));
        System.out.println("DETAILED STEP-BY-STEP WALKTHROUGH");
        System.out.println("-".repeat(60));

        int[][] testIntervals = {
                {1, 4},
                {2, 5},
                {7, 9},
                {8, 10},
                {12, 15}
        };

        System.out.println("Input Intervals: " + Arrays.deepToString(testIntervals));

        Arrays.sort(testIntervals, Comparator.comparingInt(a -> a[0]));
        System.out.println("After Sorting  : " + Arrays.deepToString(testIntervals));

        List<int[]> walkthrough = new ArrayList<>();
        int[] curr = testIntervals[0];
        walkthrough.add(curr);

        for (int i = 1; i < testIntervals.length; i++) {

            int[] next = testIntervals[i];

            System.out.println("\nCurrent Interval: " + Arrays.toString(curr));
            System.out.println("Next Interval   : " + Arrays.toString(next));

            if (next[0] <= curr[1]) {
                curr[1] = Math.max(curr[1], next[1]);
                System.out.println("✔ Overlap → Merged to: " + Arrays.toString(curr));
            } else {
                curr = next;
                walkthrough.add(curr);
                System.out.println("✖ No Overlap → Added: " + Arrays.toString(curr));
            }
        }

        System.out.println("\nFinal Merged Result: " +
                Arrays.deepToString(walkthrough.toArray(new int[walkthrough.size()][])));

        System.out.println("\n" + "=".repeat(60));
        System.out.println("COMPLEXITY ANALYSIS");
        System.out.println("-".repeat(60));
        System.out.println("Time Complexity : O(n log n)");
        System.out.println("Space Complexity: O(n)");

        System.out.println("\nNOTE:");
        System.out.println("✔ Sorting ensures overlapping intervals are adjacent");
        System.out.println("✔ Greedy merging guarantees minimal interval set");
    }
}

