package greedy_algorithms.medium;
import java.util.*;

/**
 * Job Sequencing Problem (with Deadlines)
 *
 * Problem:
 * You are given n jobs, where each job has:
 * - An id
 * - A deadline (by which it must be completed)
 * - A profit earned if the job is completed before or on its deadline
 *
 * Each job takes exactly 1 unit of time.
 * Only one job can be scheduled at a time.
 *
 * Task:
 * Find:
 * 1. The maximum number of jobs that can be done
 * 2. The maximum profit that can be earned
 *
 * ---------------------------------------------------------
 * Approach: Greedy + Sorting
 * ---------------------------------------------------------
 * - Sort all jobs in descending order of profit
 * - Try to schedule each job as late as possible
 *   before its deadline
 * - Use a boolean array to mark occupied time slots
 *
 * Greedy Justification:
 * - Choosing the most profitable job first ensures
 *   maximum total profit
 * - Scheduling it as late as possible leaves room
 *   for other jobs
 *
 * ---------------------------------------------------------
 * Example:
 * Jobs:
 * ID   Deadline   Profit
 * A      2         100
 * B      1          19
 * C      2          27
 * D      1          25
 * E      3          15
 *
 * Optimal Sequence: A, C, E
 * Jobs Done = 3
 * Profit = 142
 *
 * ---------------------------------------------------------
 * Time Complexity: O(n log n)
 * Space Complexity: O(n)
 */

public class JobSequencing {

    /**
     * Class to represent a Job
     */
    static class Job {
        char id;
        int deadline;
        int profit;

        Job(char id, int deadline, int profit) {
            this.id = id;
            this.deadline = deadline;
            this.profit = profit;
        }
    }

    /**
     * Schedules jobs to maximize profit
     *
     * @param jobs array of jobs
     * @return array where index 0 = number of jobs done, index 1 = total profit
     */
    public static int[] scheduleJobs(Job[] jobs) {

        // Sort jobs by descending profit
        Arrays.sort(jobs, (a, b) -> b.profit - a.profit);

        // Find maximum deadline
        int maxDeadline = 0;
        for (Job job : jobs) {
            maxDeadline = Math.max(maxDeadline, job.deadline);
        }

        // Time slots (1-based indexing)
        boolean[] slots = new boolean[maxDeadline + 1];

        int jobCount = 0;
        int totalProfit = 0;

        // Try to schedule each job
        for (Job job : jobs) {

            // Try to place job at the latest free slot before deadline
            for (int t = job.deadline; t > 0; t--) {
                if (!slots[t]) {
                    slots[t] = true;
                    jobCount++;
                    totalProfit += job.profit;
                    break;
                }
            }
        }

        return new int[]{jobCount, totalProfit};
    }

    /**
     * Returns the actual job execution order (for understanding)
     */
    public static List<Character> getJobSequence(Job[] jobs) {

        Arrays.sort(jobs, (a, b) -> b.profit - a.profit);

        int maxDeadline = 0;
        for (Job job : jobs) {
            maxDeadline = Math.max(maxDeadline, job.deadline);
        }

        char[] result = new char[maxDeadline + 1];
        boolean[] slots = new boolean[maxDeadline + 1];

        for (Job job : jobs) {
            for (int t = job.deadline; t > 0; t--) {
                if (!slots[t]) {
                    slots[t] = true;
                    result[t] = job.id;
                    break;
                }
            }
        }

        List<Character> sequence = new ArrayList<>();
        for (int i = 1; i <= maxDeadline; i++) {
            if (slots[i]) {
                sequence.add(result[i]);
            }
        }

        return sequence;
    }

    public static void main(String[] args) {

        System.out.println("=== Job Sequencing Problem ===\n");

        Job[] jobs = {
                new Job('A', 2, 100),
                new Job('B', 1, 19),
                new Job('C', 2, 27),
                new Job('D', 1, 25),
                new Job('E', 3, 15)
        };

        int[] result = scheduleJobs(jobs);

        System.out.println("Job Details:");
        System.out.println("ID  Deadline  Profit");
        System.out.println("--------------------");

        for (Job job : jobs) {
            System.out.println(job.id + "     " + job.deadline + "        " + job.profit);
        }

        System.out.println("\nMaximum Jobs Done   : " + result[0]);
        System.out.println("Maximum Profit     : " + result[1]);

        System.out.println("\nJob Execution Order : " + getJobSequence(jobs));

        System.out.println("\n" + "=".repeat(60));
        System.out.println("DETAILED STEP-BY-STEP WALKTHROUGH");
        System.out.println("-".repeat(60));

        Arrays.sort(jobs, (a, b) -> b.profit - a.profit);

        boolean[] slots = new boolean[4];
        for (Job job : jobs) {
            System.out.println("\nConsidering Job " + job.id +
                    " (Profit: " + job.profit +
                    ", Deadline: " + job.deadline + ")");

            for (int t = job.deadline; t > 0; t--) {
                if (!slots[t]) {
                    slots[t] = true;
                    System.out.println("✔ Scheduled at time slot " + t);
                    break;
                }
            }
        }

        System.out.println("\n" + "=".repeat(60));
        System.out.println("COMPLEXITY ANALYSIS");
        System.out.println("-".repeat(60));
        System.out.println("Time Complexity : O(n log n)");
        System.out.println("Space Complexity: O(n)");

        System.out.println("\nNOTE:");
        System.out.println("✔ Jobs take exactly 1 unit time");
        System.out.println("✔ Greedy works because of profit prioritization");
    }
}

