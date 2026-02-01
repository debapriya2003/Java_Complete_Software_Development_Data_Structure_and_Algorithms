package arrays.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeOverlappingSubintervals {

    /*
    =====================================================================================
    PROBLEM: MERGE OVERLAPPING SUBINTERVALS
    -------------------------------------------------------------------------------------
    Given a collection of intervals where each interval is represented as [start, end],
    the task is to merge all overlapping intervals and return a list of non-overlapping
    intervals that cover all the original intervals.

    Two intervals [a, b] and [c, d] are said to overlap if:
        c <= b

    This problem is commonly asked in interviews and tests understanding of sorting,
    greedy strategies, and interval manipulation.
    =====================================================================================
    */

    public static void main(String[] args) {

        int[][] intervals = {
                {1, 3},
                {2, 6},
                {8, 10},
                {15, 18}
        };

        int[][] merged = merge(intervals);

        System.out.println("Merged Intervals:");
        for (int[] interval : merged) {
            System.out.println(Arrays.toString(interval));
        }
    }

    /*
    =====================================================================================
    FUNCTION: merge
    -------------------------------------------------------------------------------------
    This function merges overlapping intervals using a greedy approach.

    ALGORITHM:
    1. Sort the intervals based on their starting times
    2. Initialize a list to store merged intervals
    3. Traverse the sorted intervals:
        • If the current interval overlaps with the last merged interval,
          merge them by updating the end time
        • Otherwise, add the current interval as a new interval
    4. Convert the result list to a 2D array

    WHY THIS WORKS:
    Sorting ensures that overlapping intervals appear next to each other, making merging
    straightforward in a single traversal.

    Time Complexity  : O(n log n)
    Space Complexity : O(n)
    =====================================================================================
    */
    static int[][] merge(int[][] intervals) {

        if (intervals.length == 0)
            return new int[0][0];

        // Step 1: Sort intervals by start time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        List<int[]> mergedList = new ArrayList<>();

        // Step 2: Add first interval
        int[] current = intervals[0];
        mergedList.add(current);

        // Step 3: Merge overlapping intervals
        for (int i = 1; i < intervals.length; i++) {

            int[] next = intervals[i];

            // Check for overlap
            if (next[0] <= current[1]) {
                current[1] = Math.max(current[1], next[1]);
            } 
            else {
                current = next;
                mergedList.add(current);
            }
        }

        // Step 4: Convert list to array
        return mergedList.toArray(new int[mergedList.size()][]);
    }
}
