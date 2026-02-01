package greedy_algorithms.medium;
import java.util.*;

/**
 * Insert Interval
 *
 * Problem:
 * You are given:
 * - A list of non-overlapping intervals sorted by start time
 * - A new interval to be inserted
 *
 * Each interval is represented as [start, end].
 *
 * Task:
 * Insert the new interval into the list such that:
 * - The resulting list is still sorted
 * - Any overlapping intervals are merged
 *
 * ---------------------------------------------------------
 * Approach: Greedy Interval Processing
 * ---------------------------------------------------------
 * We process intervals in three logical phases:
 *
 * 1. Left Part (No Overlap):
 *    - Add all intervals that end before the new interval starts
 *
 * 2. Overlapping Part:
 *    - Merge all intervals that overlap with the new interval
 *    - Update newInterval.start = min(start)
 *    - Update newInterval.end   = max(end)
 *
 * 3. Right Part (No Overlap):
 *    - Add remaining intervals after merging
 *
 * Greedy Justification:
 * - Since intervals are already sorted,
 *   a single linear pass is sufficient
 *
 * ---------------------------------------------------------
 * Example:
 * intervals = [[1,3],[6,9]]
 * newInterval = [2,5]
 *
 * Process:
 * - [1,3] overlaps with [2,5] → merge → [1,5]
 * - Add remaining [6,9]
 *
 * Output: [[1,5],[6,9]]
 *
 * ---------------------------------------------------------
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

public class InsertInterval {

    /**
     * Inserts a new interval into sorted non-overlapping intervals
     *
     * @param intervals existing intervals
     * @param newInterval interval to insert
     * @return updated list of merged intervals
     */
    public static int[][] insert(int[][] intervals, int[] newInterval) {

        List<int[]> result = new ArrayList<>();
        int i = 0;
        int n = intervals.length;

        // 1️⃣ Add all intervals that come before newInterval
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }

        // 2️⃣ Merge overlapping intervals
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        result.add(newInterval);

        // 3️⃣ Add remaining intervals
        while (i < n) {
            result.add(intervals[i]);
            i++;
        }

        return result.toArray(new int[result.size()][]);
    }

    public static void main(String[] args) {

        System.out.println("=== Insert Interval Problem ===\n");

        int[][] intervals = {
                {1, 3},
                {6, 9}
        };
        int[] newInterval = {2, 5};

        System.out.println("Original Intervals : " + Arrays.deepToString(intervals));
        System.out.println("New Interval       : " + Arrays.toString(newInterval));

        int[][] result = insert(intervals, newInterval);

        System.out.println("\nUpdated Intervals  : " + Arrays.deepToString(result));

        System.out.println("\n" + "=".repeat(60));
        System.out.println("DETAILED STEP-BY-STEP WALKTHROUGH");
        System.out.println("-".repeat(60));

        int[][] testIntervals = {
                {1, 2},
                {3, 5},
                {6, 7},
                {8, 10},
                {12, 16}
        };
        int[] testNew = {4, 8};

        System.out.println("Intervals    : " + Arrays.deepToString(testIntervals));
        System.out.println("New Interval : " + Arrays.toString(testNew));

        List<int[]> walkthrough = new ArrayList<>();
        int idx = 0;

        // Left part
        while (idx < testIntervals.length && testIntervals[idx][1] < testNew[0]) {
            walkthrough.add(testIntervals[idx]);
            System.out.println("Added (no overlap): " + Arrays.toString(testIntervals[idx]));
            idx++;
        }

        // Overlapping
        while (idx < testIntervals.length && testIntervals[idx][0] <= testNew[1]) {
            testNew[0] = Math.min(testNew[0], testIntervals[idx][0]);
            testNew[1] = Math.max(testNew[1], testIntervals[idx][1]);
            System.out.println("Merging with: " + Arrays.toString(testIntervals[idx]));
            idx++;
        }
        walkthrough.add(testNew);
        System.out.println("Merged Interval: " + Arrays.toString(testNew));

        // Right part
        while (idx < testIntervals.length) {
            walkthrough.add(testIntervals[idx]);
            System.out.println("Added (right side): " + Arrays.toString(testIntervals[idx]));
            idx++;
        }

        System.out.println("\nFinal Result: " + Arrays.deepToString(
                walkthrough.toArray(new int[walkthrough.size()][])));

        System.out.println("\n" + "=".repeat(60));
        System.out.println("COMPLEXITY ANALYSIS");
        System.out.println("-".repeat(60));
        System.out.println("Time Complexity : O(n)");
        System.out.println("Space Complexity: O(n)");

        System.out.println("\nNOTE:");
        System.out.println("✔ Intervals are assumed to be sorted and non-overlapping");
        System.out.println("✔ Single pass greedy solution");
    }
}

