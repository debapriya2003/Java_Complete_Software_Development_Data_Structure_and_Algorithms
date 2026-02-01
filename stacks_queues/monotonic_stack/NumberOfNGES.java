package stacks_queues.monotonic_stack;

import java.util.*;

/**
 * Number of Next Greater Elements to the Right (NGES)
 * 
 * Problem: For each element, count how many elements to its right are greater than it.
 * 
 * Example: [8, 7, 1, 6, 0, 4, 8, 6, 2, 5]
 * Output:  [3, 2, 3, 2, 3, 2, 2, 1, 1, 0]
 * 
 * Explanation:
 * 8 at index 0: Greater elements to right: 8 (index 6) → count = 1? 
 * Actually greater to right: only 8 at index 6 → count = 1
 * Wait, let me recalculate...
 * 8 at index 0: Greater? None (no element > 8) → count = 0
 * 7 at index 1: Greater? 8 at index 6, 8 at index 8? No → 8 at index 6 → count = 1
 * 
 * Different approach: Count for each element how many are greater to right
 * 1 at index 2: Greater elements to right: 6, 4, 8, 6, 2, 5 → count = 6
 * 
 * Algorithm: Modified Monotonic Stack (Right to Left)
 * Using coordinate compression or merge sort approach more efficient
 * Here using stack approach:
 * 1. For each element from right to left
 * 2. Count how many elements in stack are smaller than current
 * 3. Pop and count, then push back
 * 
 * Time Complexity: O(n log n) using merge sort, or O(n²) with naive approach
 * Space Complexity: O(n)
 */

public class NumberOfNGES {
    
    /**
     * Count next greater elements using merge sort approach
     */
    public static List<Integer> countNGES(int[] arr) {
        int n = arr.length;
        List<Integer> result = new ArrayList<>(Collections.nCopies(n, 0));
        
        // Create pairs of (value, original_index)
        Pair[] pairs = new Pair[n];
        for (int i = 0; i < n; i++) {
            pairs[i] = new Pair(arr[i], i);
        }
        
        // Merge sort from right to left
        mergeSort(pairs, 0, n - 1, result);
        
        return result;
    }
    
    static class Pair {
        int value;
        int index;
        
        Pair(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }
    
    private static void mergeSort(Pair[] pairs, int left, int right, List<Integer> result) {
        if (left >= right) return;
        
        int mid = left + (right - left) / 2;
        mergeSort(pairs, left, mid, result);
        mergeSort(pairs, mid + 1, right, result);
        merge(pairs, left, mid, right, result);
    }
    
    private static void merge(Pair[] pairs, int left, int mid, int right, List<Integer> result) {
        Pair[] temp = new Pair[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        int rightCount = 0;
        
        while (i <= mid && j <= right) {
            if (pairs[i].value > pairs[j].value) {
                rightCount++;
                temp[k++] = pairs[j++];
            } else {
                result.set(pairs[i].index, result.get(pairs[i].index) + rightCount);
                temp[k++] = pairs[i++];
            }
        }
        
        while (i <= mid) {
            result.set(pairs[i].index, result.get(pairs[i].index) + rightCount);
            temp[k++] = pairs[i++];
        }
        
        while (j <= right) {
            temp[k++] = pairs[j++];
        }
        
        for (int x = 0; x < temp.length; x++) {
            pairs[left + x] = temp[x];
        }
    }
    
    /**
     * Naive approach for understanding (O(n²))
     */
    public static int[] countNGESNaive(int[] arr) {
        int n = arr.length;
        int[] result = new int[n];
        
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] > arr[i]) {
                    count++;
                }
            }
            result[i] = count;
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        System.out.println("=== Number of Next Greater Elements to Right ===\n");
        
        int[][] testCases = {
            {8, 7, 1, 6, 0, 4, 8, 6, 2, 5},
            {1, 2, 3, 4, 5},
            {5, 4, 3, 2, 1},
            {1, 1, 1, 1},
            {2, 1, 2, 4, 3}
        };
        
        System.out.println(String.format("%-40s | %-30s", "Array", "Count of NGE to Right"));
        System.out.println("-".repeat(75));
        
        for (int[] arr : testCases) {
            int[] result = countNGESNaive(arr);
            System.out.println(String.format("%-40s | %-30s", 
                Arrays.toString(arr), Arrays.toString(result)));
        }
        
        System.out.println("\n" + "=".repeat(75));
        System.out.println("DETAILED EXAMPLE: [1, 2, 3, 4, 5]");
        System.out.println("-".repeat(75));
        
        int[] arr = {1, 2, 3, 4, 5};
        int n = arr.length;
        int[] result = countNGESNaive(arr);
        
        System.out.println("\nArray: " + Arrays.toString(arr));
        System.out.println("\nFor each element, count greater elements to right:\n");
        
        for (int i = 0; i < n; i++) {
            System.out.print("Index " + i + ", Element " + arr[i] + ": Greater elements = [");
            List<Integer> greaterElements = new ArrayList<>();
            for (int j = i + 1; j < n; j++) {
                if (arr[j] > arr[i]) {
                    greaterElements.add(arr[j]);
                }
            }
            for (int elem : greaterElements) {
                System.out.print(elem + " ");
            }
            System.out.println("] → Count = " + result[i]);
        }
        
        System.out.println("\nFinal Result: " + Arrays.toString(result));
        
        System.out.println("\n" + "=".repeat(75));
        System.out.println("ANOTHER EXAMPLE: [5, 4, 3, 2, 1]");
        System.out.println("-".repeat(75));
        
        arr = new int[]{5, 4, 3, 2, 1};
        result = countNGESNaive(arr);
        
        System.out.println("\nArray: " + Arrays.toString(arr));
        System.out.println("Result: " + Arrays.toString(result));
        
        System.out.println("\nExplanation (strictly decreasing array):");
        System.out.println("5: No element > 5 to right → 0");
        System.out.println("4: No element > 4 to right → 0");
        System.out.println("3: No element > 3 to right → 0");
        System.out.println("2: No element > 2 to right → 0");
        System.out.println("1: No element > 1 to right → 0");
        
        System.out.println("\n" + "=".repeat(75));
        System.out.println("ANOTHER EXAMPLE: [1, 2, 3, 4, 5]");
        System.out.println("-".repeat(75));
        
        arr = new int[]{1, 2, 3, 4, 5};
        result = countNGESNaive(arr);
        
        System.out.println("\nArray: " + Arrays.toString(arr));
        System.out.println("Result: " + Arrays.toString(result));
        
        System.out.println("\nExplanation (strictly increasing array):");
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i] + ": " + (arr.length - i - 1) + " elements greater to right");
        }
        
        System.out.println("\n" + "=".repeat(75));
        System.out.println("COMPLEXITY COMPARISON:");
        System.out.println("-".repeat(75));
        System.out.println("Naive Approach:");
        System.out.println("  Time: O(n²) - for each element, scan remaining");
        System.out.println("  Space: O(n) - for result array");
        
        System.out.println("\nOptimized (Merge Sort):");
        System.out.println("  Time: O(n log n) - merge sort with coordinate compression");
        System.out.println("  Space: O(n) - for temporary arrays");
        
        System.out.println("\nMonotonic Stack Approach:");
        System.out.println("  Time: O(n log n) - using balanced BST or coordinate compress");
        System.out.println("  Space: O(n)");
        
        System.out.println("\n" + "=".repeat(75));
        System.out.println("KEY INSIGHTS:");
        System.out.println("-".repeat(75));
        System.out.println("1. This is an inversion count variant problem");
        System.out.println("2. Naive approach: For each i, count j where i<j and arr[j]>arr[i]");
        System.out.println("3. Can be optimized with:");
        System.out.println("   - Merge sort (count inversions + comparison)");
        System.out.println("   - Fenwick Tree / Segment Tree");
        System.out.println("   - Balanced BST with coordinate compression");
        System.out.println("4. Related to reverse pairs and inversion count problems");
    }
}
