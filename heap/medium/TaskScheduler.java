package heap.medium;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*
=====================================================================================
TASK SCHEDULER (GREEDY + PRIORITY QUEUE)
-------------------------------------------------------------------------------------
Problem:
You are given an array of tasks, where each task is represented by a character.
Each task takes exactly 1 unit of time to execute.

There is a cooldown period 'n' such that the same task cannot be executed again
until at least 'n' units of time have passed.

Goal:
Find the minimum number of time units required to execute all tasks.

Example:
Tasks = [A, A, A, B, B, B], n = 2
Output = 8

Explanation:
A → B → idle → A → B → idle → A → B
=====================================================================================
*/

public class TaskScheduler {

    /*
    =====================================================================================
    FUNCTION: leastInterval
    -------------------------------------------------------------------------------------
    This function calculates the minimum time required to finish all tasks while
    respecting the cooldown constraint.

    CORE IDEA:
    • Tasks with higher frequency should be executed first
    • Use a Max Heap to always select the task with highest remaining count
    • Execute tasks in cycles of (n + 1)
    • Fill idle time only if required

    ALGORITHM:
    1. Count frequency of each task
    2. Insert frequencies into a Max Heap
    3. While heap is not empty:
        - Try to execute up to (n + 1) tasks
        - Decrease their counts
        - Reinsert unfinished tasks
        - Add idle time if needed

    Time Complexity  : O(N log 26) ≈ O(N)
    Space Complexity : O(26) ≈ O(1)
    =====================================================================================
    */
    public static int leastInterval(char[] tasks, int n) {

        // Step 1: Count frequency of tasks
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char task : tasks) {
            freqMap.put(task, freqMap.getOrDefault(task, 0) + 1);
        }

        // Step 2: Max Heap based on frequency
        PriorityQueue<Integer> maxHeap =
                new PriorityQueue<>((a, b) -> b - a);

        maxHeap.addAll(freqMap.values());

        int time = 0;

        // Step 3: Process tasks in cycles
        while (!maxHeap.isEmpty()) {

            int cycle = n + 1;
            int tasksDone = 0;

            int[] temp = new int[cycle];

            // Execute up to n+1 tasks
            while (cycle > 0 && !maxHeap.isEmpty()) {
                temp[tasksDone] = maxHeap.poll() - 1;
                tasksDone++;
                cycle--;
            }

            // Reinsert unfinished tasks
            for (int i = 0; i < tasksDone; i++) {
                if (temp[i] > 0) {
                    maxHeap.add(temp[i]);
                }
            }

            // Add time
            if (maxHeap.isEmpty()) {
                time += tasksDone;   // no idle time needed
            } else {
                time += (n + 1);     // idle slots required
            }
        }

        return time;
    }

    /*
    =====================================================================================
    MAIN METHOD: DRIVER CODE
    -------------------------------------------------------------------------------------
    Demonstrates Task Scheduler execution.
    =====================================================================================
    */
    public static void main(String[] args) {

        char[] tasks = {'A', 'A', 'A', 'B', 'B', 'B'};
        int n = 2;

        int result = leastInterval(tasks, n);

        System.out.println("Tasks: A A A B B B");
        System.out.println("Cooldown: " + n);
        System.out.println("Minimum time required: " + result);
    }
}

