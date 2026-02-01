package greedy_algorithms.medium;


import java.util.*;

/**
 * N Meetings in One Room
 *
 * Problem:
 * You are given start[] and end[] arrays of length n,
 * where start[i] and end[i] denote the start and finish time
 * of the i-th meeting.
 *
 * Only one meeting can be held in the room at a time.
 *
 * Task:
 * Find the maximum number of meetings that can be accommodated
 * in the room.
 *
 * ---------------------------------------------------------
 * Approach: Greedy (Activity Selection Problem)
 * ---------------------------------------------------------
 * - Pair each meeting with its start and end time
 * - Sort meetings based on their end times (ascending)
 * - Always select the meeting that finishes earliest
 * - Select next meeting whose start time is strictly
 *   greater than the end time of the last selected meeting
 *
 * Greedy Justification:
 * - Choosing the meeting with the earliest finish time
 *   leaves maximum room for future meetings
 *
 * ---------------------------------------------------------
 * Example:
 * start = [1, 3, 0, 5, 8, 5]
 * end   = [2, 4, 6, 7, 9, 9]
 *
 * Sorted by end time:
 * (1,2), (3,4), (0,6), (5,7), (8,9), (5,9)
 *
 * Selected:
 * (1,2), (3,4), (5,7), (8,9)
 *
 * Output: 4
 *
 * ---------------------------------------------------------
 * Time Complexity: O(n log n)
 * Space Complexity: O(n)
 */

public class NMeetingsOneRoom {

    /**
     * Class to represent a meeting
     */
    static class Meeting {
        int start;
        int end;
        int index; // original index (optional, useful for tracking)

        Meeting(int start, int end, int index) {
            this.start = start;
            this.end = end;
            this.index = index;
        }
    }

    /**
     * Finds the maximum number of meetings that can be held
     *
     * @param start start times
     * @param end end times
     * @return maximum number of meetings
     */
    public static int maxMeetings(int[] start, int[] end) {

        int n = start.length;
        List<Meeting> meetings = new ArrayList<>();

        // Combine start and end times into Meeting objects
        for (int i = 0; i < n; i++) {
            meetings.add(new Meeting(start[i], end[i], i + 1));
        }

        // Sort meetings by end time
        meetings.sort(Comparator.comparingInt(m -> m.end));

        int count = 1; // At least one meeting can be selected
        int lastEndTime = meetings.get(0).end;

        // Select meetings greedily
        for (int i = 1; i < n; i++) {
            if (meetings.get(i).start > lastEndTime) {
                count++;
                lastEndTime = meetings.get(i).end;
            }
        }

        return count;
    }

    /**
     * Prints the selected meetings (for understanding)
     */
    public static List<Integer> getMeetingOrder(int[] start, int[] end) {

        int n = start.length;
        List<Meeting> meetings = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            meetings.add(new Meeting(start[i], end[i], i + 1));
        }

        meetings.sort(Comparator.comparingInt(m -> m.end));

        List<Integer> selected = new ArrayList<>();
        selected.add(meetings.get(0).index);

        int lastEnd = meetings.get(0).end;

        for (int i = 1; i < n; i++) {
            if (meetings.get(i).start > lastEnd) {
                selected.add(meetings.get(i).index);
                lastEnd = meetings.get(i).end;
            }
        }

        return selected;
    }

    public static void main(String[] args) {

        System.out.println("=== N Meetings in One Room ===\n");

        int[] start = {1, 3, 0, 5, 8, 5};
        int[] end   = {2, 4, 6, 7, 9, 9};

        int maxMeetings = maxMeetings(start, end);
        List<Integer> order = getMeetingOrder(start, end);

        System.out.println("Start Times: " + Arrays.toString(start));
        System.out.println("End Times  : " + Arrays.toString(end));

        System.out.println("\nMaximum meetings possible: " + maxMeetings);
        System.out.println("Meeting order (by index): " + order);

        System.out.println("\n" + "=".repeat(60));
        System.out.println("DETAILED STEP-BY-STEP WALKTHROUGH");
        System.out.println("-".repeat(60));

        List<Meeting> meetings = new ArrayList<>();
        for (int i = 0; i < start.length; i++) {
            meetings.add(new Meeting(start[i], end[i], i + 1));
        }

        meetings.sort(Comparator.comparingInt(m -> m.end));

        int lastEnd = meetings.get(0).end;
        System.out.println("Selected Meeting " + meetings.get(0).index +
                " → [" + meetings.get(0).start + ", " + meetings.get(0).end + "]");

        for (int i = 1; i < meetings.size(); i++) {
            Meeting m = meetings.get(i);
            System.out.println("\nConsidering Meeting " + m.index +
                    " → [" + m.start + ", " + m.end + "]");

            if (m.start > lastEnd) {
                System.out.println("✔ Selected");
                lastEnd = m.end;
            } else {
                System.out.println("✖ Overlaps, skipped");
            }
        }

        System.out.println("\n" + "=".repeat(60));
        System.out.println("COMPLEXITY ANALYSIS");
        System.out.println("-".repeat(60));
        System.out.println("Time Complexity : O(n log n)");
        System.out.println("Space Complexity: O(n)");
    }
}
