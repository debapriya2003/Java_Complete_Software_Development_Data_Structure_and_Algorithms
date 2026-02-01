package greedy_algorithms.medium;
import java.util.*;

/**
 * Least Recently Used (LRU) Page Replacement Algorithm
 *
 * Problem:
 * In an operating system, memory has limited number of frames.
 * When a page fault occurs and memory is full, the OS must decide
 * which page to remove.
 *
 * LRU Policy:
 * - Remove the page that has not been used for the longest time
 *
 * Task:
 * Given:
 * - A sequence of page references
 * - Number of available frames
 *
 * Find:
 * 1. Total Page Faults
 * 2. Page Hit / Miss status
 *
 * ---------------------------------------------------------
 * Key Concepts:
 * ---------------------------------------------------------
 * Page Hit:
 * - Page is already present in memory
 *
 * Page Fault (Miss):
 * - Page is not present in memory → replacement needed
 *
 * ---------------------------------------------------------
 * Approach 1: Using HashSet + HashMap (Optimal & Clear)
 * ---------------------------------------------------------
 * - HashSet → stores pages currently in frames
 * - HashMap → stores last used index of each page
 * - When page fault occurs:
 *      - If space available → insert page
 *      - Else → remove least recently used page
 *
 * Greedy Justification:
 * - Past usage is used as heuristic to predict future usage
 *
 * ---------------------------------------------------------
 * Example:
 * Pages: [7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3]
 * Frames = 3
 *
 * Page Faults = 9
 *
 * ---------------------------------------------------------
 * Time Complexity: O(n * f)
 * Space Complexity: O(f)
 * where f = number of frames
 */

public class LRUPageReplacement {

    /**
     * Implements LRU Page Replacement Algorithm
     *
     * @param pages array of page references
     * @param frames number of memory frames
     * @return total number of page faults
     */
    public static int lruPageFaults(int[] pages, int frames) {

        // Set to store pages currently in memory
        Set<Integer> memory = new HashSet<>(frames);

        // Map to store last used index of pages
        Map<Integer, Integer> lastUsed = new HashMap<>();

        int pageFaults = 0;

        // Traverse page reference string
        for (int i = 0; i < pages.length; i++) {

            int page = pages[i];

            // Page Hit
            if (memory.contains(page)) {
                lastUsed.put(page, i);
            }
            // Page Fault
            else {
                pageFaults++;

                // If memory is full, remove LRU page
                if (memory.size() == frames) {

                    int lruPage = -1;
                    int minIndex = Integer.MAX_VALUE;

                    // Find least recently used page
                    for (int p : memory) {
                        if (lastUsed.get(p) < minIndex) {
                            minIndex = lastUsed.get(p);
                            lruPage = p;
                        }
                    }

                    memory.remove(lruPage);
                }

                // Insert new page
                memory.add(page);
                lastUsed.put(page, i);
            }
        }

        return pageFaults;
    }

    public static void main(String[] args) {

        System.out.println("=== LRU Page Replacement Algorithm ===\n");

        int[] pages = {7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3};
        int frames = 3;

        int faults = lruPageFaults(pages, frames);

        System.out.println("Page Reference String : " + Arrays.toString(pages));
        System.out.println("Number of Frames      : " + frames);
        System.out.println("Total Page Faults     : " + faults);

        System.out.println("\n" + "=".repeat(60));
        System.out.println("DETAILED STEP-BY-STEP WALKTHROUGH");
        System.out.println("-".repeat(60));

        Set<Integer> memory = new HashSet<>();
        Map<Integer, Integer> lastUsed = new HashMap<>();
        int faultsCount = 0;

        for (int i = 0; i < pages.length; i++) {

            int page = pages[i];
            System.out.print("Page " + page + " → ");

            if (memory.contains(page)) {
                System.out.println("HIT");
            } else {
                faultsCount++;
                System.out.print("FAULT");

                if (memory.size() == frames) {

                    int lruPage = -1;
                    int minIndex = Integer.MAX_VALUE;

                    for (int p : memory) {
                        if (lastUsed.get(p) < minIndex) {
                            minIndex = lastUsed.get(p);
                            lruPage = p;
                        }
                    }

                    memory.remove(lruPage);
                    System.out.print(" (Removed LRU Page: " + lruPage + ")");
                }
                System.out.println();
                memory.add(page);
            }

            lastUsed.put(page, i);
            System.out.println("Frames: " + memory + "\n");
        }

        System.out.println("Total Page Faults: " + faultsCount);

        System.out.println("\n" + "=".repeat(60));
        System.out.println("COMPLEXITY ANALYSIS");
        System.out.println("-".repeat(60));
        System.out.println("Time Complexity : O(n × frames)");
        System.out.println("Space Complexity: O(frames)");

        System.out.println("\nNOTE:");
        System.out.println("✔ LRU approximates optimal page replacement");
        System.out.println("✖ Higher overhead than FIFO");
        System.out.println("✔ Can be optimized using LinkedHashMap");
    }
}

