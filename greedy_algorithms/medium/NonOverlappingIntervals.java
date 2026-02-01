package greedy_algorithms.medium;

import java.util.*;

/**
 * Non-overlapping Intervals
 *
 * Problem:
 * You are given an array of intervals where intervals[i] = [start, end].
 *
 * Task:
 * Find the minimum number of intervals you need to remove
 * so that the remaining intervals do NOT overlap.
 *
 * ---------------------------------------------------------
 * Key Insight:
 * ---------------------------------------------------------
 * - This is a classic interval scheduling / removal problem
 * - Instead of selecting intervals, we count how many must be removed
 *
 * ---------------------------------------------------------
 * Approach: Greedy (Sort by End Time) ✅ Optimal
 * ---------------------------------------------------------
 * - Sort intervals by their end time (ascending)
 * - Always keep the interval that finishes earliest
 * - If the current interval overlaps with the last kept interval,
 *   remove the current one
 *
 * Why sort by end time?
 * - The interval that ends earlier leaves more room for others
 * - This minimizes future overlaps
 *
 * ---------------------------------------------------------
 * Example:
 * intervals = [[1,2],[2,3],[3,4],[1,3]]
 *
 * Sorted by end:
 * [[1,2],[2,3],[1,3],[3,4]]
 *
 * Keep [1,2]
 * Keep [2,3]
 * Remove [1,3] (overlaps with [2,3])
 * Keep [3,4]
 *
 * Output: 1
 *
 * ---------------------------------------------------------
 * Time Complexity: O(n log n)
 * Space Complexity: O(1)
 */

public class NonOverlappingIntervals {

    /**
     * Finds minimum number of intervals to remove
     *
     * @param intervals array of intervals
     * @return minimum removals needed
     */
    public static int eraseOverlapIntervals(int[][] intervals) {

        // Edge case
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        // Sort intervals by end time
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));

        int removals = 0;
        int lastEnd = intervals[0][1];

        // Traverse intervals
        for (int i = 1; i < intervals.length; i++) {

            // Overlapping interval found
            if (intervals[i][0] < lastEnd) {
                removals++;
            }
            // No overlap, update end time
            else {
                lastEnd = intervals[i][1];
            }
        }

        return removals;
    }

    public static void main(String[] args) {

        System.out.println("=== Non-overlapping Intervals Problem ===\n");

        int[][] intervals = {
                {1, 2},
                {2, 3},
                {3, 4},
                {1, 3}
        };

        System.out.println("Intervals: " + Arrays.deepToString(intervals));

        int removals = eraseOverlapIntervals(intervals);

        System.out.println("Minimum intervals to remove: " + removals);

        System.out.println("\n" + "=".repeat(60));
        System.out.println("DETAILED STEP-BY-STEP WALKTHROUGH");
        System.out.println("-".repeat(60));

        int[][] test = {
                {1, 2},
                {1, 3},
                {2, 4},
                {3, 5}
        };

        System.out.println("Input Intervals: " + Arrays.deepToString(test));

        Arrays.sort(test, Comparator.comparingInt(a -> a[1]));
        System.out.println("Sorted by End : " + Arrays.deepToString(test));

        int end = test[0][1];
        int removeCount = 0;

        System.out.println("\nKeeping interval: " + Arrays.toString(test[0]));

        for (int i = 1; i < test.length; i++) {
            System.out.println("\nConsidering interval: " + Arrays.toString(test[i]));

            if (test[i][0] < end) {
                removeCount++;
                System.out.println("✖ Overlaps → Removed");
            } else {
                end = test[i][1];
                System.out.println("✔ No overlap → Kept");
            }
        }

        System.out.println("\nTotal Removals Needed: " + removeCount);

        System.out.println("\n" + "=".repeat(60));
        System.out.println("COMPLEXITY ANALYSIS");
        System.out.println("-".repeat(60));
        System.out.println("Time Complexity : O(n log n)");
        System.out.println("Space Complexity: O(1)");

        System.out.println("\nNOTE:");
        System.out.println("✔ Equivalent to maximizing non-overlapping intervals");
        System.out.println("✔ Greedy by earliest finish time is optimal");
    }
}

