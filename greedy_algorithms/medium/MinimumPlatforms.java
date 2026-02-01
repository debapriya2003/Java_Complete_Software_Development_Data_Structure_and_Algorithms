package greedy_algorithms.medium;
import java.util.*;

/**
 * Minimum Number of Platforms Required for a Railway Station
 *
 * Problem:
 * You are given two arrays:
 * - arr[] representing arrival times of trains
 * - dep[] representing departure times of trains
 *
 * Each train arrives and departs on the same day.
 * You need to find the minimum number of platforms required
 * so that no train has to wait.
 *
 * ---------------------------------------------------------
 * Approach 1: Sorting + Two Pointers (Greedy) ✅ Optimal
 * ---------------------------------------------------------
 * - Sort arrival and departure arrays separately
 * - Use two pointers:
 *      i → arrival index
 *      j → departure index
 * - If a train arrives before or at the same time another departs,
 *   we need an extra platform
 * - If a train departs before the next arrival,
 *   free a platform
 *
 * Greedy Justification:
 * - Process events (arrival / departure) in chronological order
 * - Track maximum simultaneous trains
 *
 * ---------------------------------------------------------
 * Example:
 * arr = [900, 940, 950, 1100, 1500, 1800]
 * dep = [910, 1200, 1120, 1130, 1900, 2000]
 *
 * Timeline:
 * 900  → +1
 * 910  → -1
 * 940  → +1
 * 950  → +1
 * ...
 *
 * Output: 3
 *
 * ---------------------------------------------------------
 * Time Complexity: O(n log n)
 * Space Complexity: O(1)
 */

public class MinimumPlatforms {

    /**
     * Finds minimum number of platforms required
     *
     * @param arr arrival times
     * @param dep departure times
     * @return minimum number of platforms
     */
    public static int findMinimumPlatforms(int[] arr, int[] dep) {

        int n = arr.length;

        // Sort arrival and departure times
        Arrays.sort(arr);
        Arrays.sort(dep);

        int platformsNeeded = 0;
        int maxPlatforms = 0;

        int i = 0; // pointer for arrival
        int j = 0; // pointer for departure

        // Traverse through all events
        while (i < n && j < n) {

            // Train arrives before or exactly when another departs
            if (arr[i] <= dep[j]) {
                platformsNeeded++;
                maxPlatforms = Math.max(maxPlatforms, platformsNeeded);
                i++;
            }
            // Train departs before next arrival
            else {
                platformsNeeded--;
                j++;
            }
        }

        return maxPlatforms;
    }

    public static void main(String[] args) {

        System.out.println("=== Minimum Platforms Required ===\n");

        int[] arrival = {900, 940, 950, 1100, 1500, 1800};
        int[] departure = {910, 1200, 1120, 1130, 1900, 2000};

        int platforms = findMinimumPlatforms(arrival, departure);

        System.out.println("Arrival Times  : " + Arrays.toString(arrival));
        System.out.println("Departure Times: " + Arrays.toString(departure));
        System.out.println("\nMinimum Platforms Required: " + platforms);

        System.out.println("\n" + "=".repeat(60));
        System.out.println("DETAILED STEP-BY-STEP WALKTHROUGH");
        System.out.println("-".repeat(60));

        int[] arr = arrival.clone();
        int[] dep = departure.clone();

        Arrays.sort(arr);
        Arrays.sort(dep);

        int i = 0, j = 0;
        int currPlatforms = 0;
        int maxPlatforms = 0;

        while (i < arr.length && j < dep.length) {

            if (arr[i] <= dep[j]) {
                currPlatforms++;
                maxPlatforms = Math.max(maxPlatforms, currPlatforms);
                System.out.println("Arrival at " + arr[i] +
                        " → Platforms in use: " + currPlatforms);
                i++;
            } else {
                currPlatforms--;
                System.out.println("Departure at " + dep[j] +
                        " → Platforms in use: " + currPlatforms);
                j++;
            }
        }

        System.out.println("\nMaximum Platforms Needed: " + maxPlatforms);

        System.out.println("\n" + "=".repeat(60));
        System.out.println("COMPLEXITY ANALYSIS");
        System.out.println("-".repeat(60));
        System.out.println("Time Complexity : O(n log n)");
        System.out.println("Space Complexity: O(1)");

        System.out.println("\nNOTE:");
        System.out.println("✔ Arrival at same time as departure requires a new platform");
        System.out.println("✔ Times are assumed to be in 24-hour format (HHMM)");
    }
}
