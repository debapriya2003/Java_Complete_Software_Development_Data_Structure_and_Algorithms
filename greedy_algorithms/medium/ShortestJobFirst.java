package greedy_algorithms.medium;

import java.util.*;

/**
 * Shortest Job First (SJF) CPU Scheduling
 *
 * Problem:
 * Given n processes with their burst times, schedule them using
 * Shortest Job First (SJF) scheduling algorithm.
 *
 * Assumptions (Non-Preemptive SJF):
 * - All processes arrive at time 0
 * - Once a process starts executing, it runs till completion
 * - CPU always selects the process with the smallest burst time
 *
 * Task:
 * Calculate:
 * 1. Waiting Time (WT)
 * 2. Turnaround Time (TAT)
 * 3. Average Waiting Time
 * 4. Average Turnaround Time
 *
 * ---------------------------------------------------------
 * Definitions:
 * ---------------------------------------------------------
 * Burst Time (BT):
 * - Time required by a process for CPU execution
 *
 * Waiting Time (WT):
 * - Time a process waits in the ready queue
 * - WT = Start Time - Arrival Time
 *
 * Turnaround Time (TAT):
 * - Total time taken from arrival to completion
 * - TAT = Completion Time - Arrival Time
 *
 * ---------------------------------------------------------
 * Approach: Greedy (Non-Preemptive SJF)
 * ---------------------------------------------------------
 * - Sort processes based on burst time (ascending)
 * - Execute processes in that order
 * - Compute waiting time cumulatively
 *
 * Greedy Justification:
 * - Executing the shortest job first minimizes
 *   average waiting time
 *
 * ---------------------------------------------------------
 * Example:
 * Processes: P1, P2, P3, P4
 * Burst Time: 6, 8, 7, 3
 *
 * Order after sorting:
 * P4 (3) → P1 (6) → P3 (7) → P2 (8)
 *
 * ---------------------------------------------------------
 * Time Complexity: O(n log n)
 * Space Complexity: O(n)
 */

public class ShortestJobFirst {

    /**
     * Class to represent a process
     */
    static class Process {
        int pid;
        int burstTime;
        int waitingTime;
        int turnaroundTime;

        Process(int pid, int burstTime) {
            this.pid = pid;
            this.burstTime = burstTime;
        }
    }

    /**
     * Executes Non-Preemptive SJF Scheduling
     *
     * @param processes list of processes
     */
    public static void sjfScheduling(List<Process> processes) {

        // Sort processes by burst time (Shortest Job First)
        processes.sort(Comparator.comparingInt(p -> p.burstTime));

        int currentTime = 0;

        // Calculate Waiting Time and Turnaround Time
        for (Process p : processes) {
            p.waitingTime = currentTime;
            currentTime += p.burstTime;
            p.turnaroundTime = currentTime;
        }
    }

    public static void main(String[] args) {

        System.out.println("=== Shortest Job First (SJF) CPU Scheduling ===\n");

        List<Process> processes = new ArrayList<>();
        processes.add(new Process(1, 6));
        processes.add(new Process(2, 8));
        processes.add(new Process(3, 7));
        processes.add(new Process(4, 3));

        sjfScheduling(processes);

        int totalWT = 0;
        int totalTAT = 0;

        System.out.println("PID | Burst Time | Waiting Time | Turnaround Time");
        System.out.println("-------------------------------------------------");

        for (Process p : processes) {
            totalWT += p.waitingTime;
            totalTAT += p.turnaroundTime;

            System.out.println(String.format("P%-3d| %-11d| %-13d| %-16d",
                    p.pid,
                    p.burstTime,
                    p.waitingTime,
                    p.turnaroundTime));
        }

        double avgWT = (double) totalWT / processes.size();
        double avgTAT = (double) totalTAT / processes.size();

        System.out.println("\nAverage Waiting Time    : " + avgWT);
        System.out.println("Average Turnaround Time : " + avgTAT);

        System.out.println("\n" + "=".repeat(60));
        System.out.println("DETAILED STEP-BY-STEP WALKTHROUGH");
        System.out.println("-".repeat(60));

        int time = 0;
        for (Process p : processes) {
            System.out.println("Process P" + p.pid +
                    " executes from time " + time +
                    " to " + (time + p.burstTime));
            time += p.burstTime;
        }

        System.out.println("\n" + "=".repeat(60));
        System.out.println("COMPLEXITY ANALYSIS");
        System.out.println("-".repeat(60));
        System.out.println("Time Complexity : O(n log n)");
        System.out.println("Space Complexity: O(n)");

        System.out.println("\nNOTE:");
        System.out.println("✔ This is Non-Preemptive SJF");
        System.out.println("✔ Optimal for minimizing average waiting time");
        System.out.println("✖ May cause starvation for long processes");
    }
}

