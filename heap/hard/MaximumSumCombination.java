package heap.hard;
import java.util.*;

/*
=====================================================================================
MAXIMUM SUM COMBINATION
-------------------------------------------------------------------------------------
Problem:
You are given two arrays A and B, each of size N.
Your task is to find the top K maximum sum combinations from all possible
pairs (A[i] + B[j]).

Naive Approach:
• Generate all N² combinations
• Sort them
• Pick top K
→ Time Complexity: O(N² log N²) ❌ (Too slow)

Optimal Approach:
• Sort both arrays
• Use a Max Heap to always get the next largest sum
• Use a HashSet to avoid duplicate index pairs

This is a very common interview + competitive programming problem.
=====================================================================================
*/

public class MaximumSumCombination {

    /*
    =====================================================================================
    HELPER CLASS: Pair
    -------------------------------------------------------------------------------------
    Represents a combination of:
    • sum → A[i] + B[j]
    • i   → index in array A
    • j   → index in array B
    =====================================================================================
    */
    static class Pair {
        int sum;
        int i;
        int j;

        Pair(int sum, int i, int j) {
            this.sum = sum;
            this.i = i;
            this.j = j;
        }
    }

    /*
    =====================================================================================
    FUNCTION: maxCombinations
    -------------------------------------------------------------------------------------
    Finds the top K maximum sum combinations.

    ALGORITHM:
    1. Sort arrays A and B
    2. Start with the largest possible sum: A[n-1] + B[n-1]
    3. Push it into a Max Heap
    4. Repeatedly:
        • Extract max sum
        • Generate next two candidates:
            (i-1, j) and (i, j-1)
        • Avoid duplicates using a HashSet
    5. Stop after extracting K sums

    Time Complexity  : O(K log K)
    Space Complexity : O(K)
    =====================================================================================
    */
    public static List<Integer> maxCombinations(int[] A, int[] B, int K) {

        int n = A.length;
        Arrays.sort(A);
        Arrays.sort(B);

        PriorityQueue<Pair> maxHeap =
                new PriorityQueue<>((a, b) -> b.sum - a.sum);

        Set<String> visited = new HashSet<>();

        // Start with maximum possible sum
        maxHeap.add(new Pair(A[n - 1] + B[n - 1], n - 1, n - 1));
        visited.add((n - 1) + "," + (n - 1));

        List<Integer> result = new ArrayList<>();

        while (K-- > 0 && !maxHeap.isEmpty()) {

            Pair current = maxHeap.poll();
            result.add(current.sum);

            int i = current.i;
            int j = current.j;

            // Move left in A
            if (i - 1 >= 0) {
                String key = (i - 1) + "," + j;
                if (!visited.contains(key)) {
                    maxHeap.add(new Pair(A[i - 1] + B[j], i - 1, j));
                    visited.add(key);
                }
            }

            // Move left in B
            if (j - 1 >= 0) {
                String key = i + "," + (j - 1);
                if (!visited.contains(key)) {
                    maxHeap.add(new Pair(A[i] + B[j - 1], i, j - 1));
                    visited.add(key);
                }
            }
        }

        return result;
    }

    /*
    =====================================================================================
    MAIN METHOD: DRIVER CODE
    -------------------------------------------------------------------------------------
    Demonstrates maximum sum combinations.
    =====================================================================================
    */
    public static void main(String[] args) {

        int[] A = {1, 4, 2, 3};
        int[] B = {2, 5, 1, 6};
        int K = 4;

        List<Integer> result = maxCombinations(A, B, K);

        System.out.println("Top " + K + " maximum sum combinations:");
        System.out.println(result);
    }
}

