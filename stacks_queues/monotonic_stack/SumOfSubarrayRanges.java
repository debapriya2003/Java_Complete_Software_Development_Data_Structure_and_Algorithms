package stacks_queues.monotonic_stack;

import java.util.*;

/**
 * Sum of Subarray Ranges
 * 
 * Problem: For each subarray, calculate (max - min).
 *          Return sum of all (max - min) values for all subarrays.
 * 
 * Example: [1, 2, 3, 4]
 * Ranges:
 * [1]: 1-1 = 0
 * [2]: 2-2 = 0
 * [3]: 3-3 = 0
 * [4]: 4-4 = 0
 * [1,2]: 2-1 = 1
 * [2,3]: 3-2 = 1
 * [3,4]: 4-3 = 1
 * [1,2,3]: 3-1 = 2
 * [2,3,4]: 4-2 = 2
 * [1,2,3,4]: 4-1 = 3
 * Sum = 0+0+0+0+1+1+1+2+2+3 = 10
 * 
 * Formula: Sum = (Sum of subarray max) - (Sum of subarray min)
 * 
 * For each element:
 * - Count subarrays where it's maximum (use NGE and PGE)
 * - Count subarrays where it's minimum (use NSE and PSE)
 * 
 * Algorithm:
 * ==========
 * 1. Calculate sum of maximum of all subarrays using monotonic stack
 * 2. Calculate sum of minimum of all subarrays using monotonic stack
 * 3. Return difference
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

public class SumOfSubarrayRanges {
    
    /**
     * Sum of subarray ranges
     */
    public static long sumOfRanges(int[] arr) {
        long sumMax = sumOfSubarrayMax(arr);
        long sumMin = sumOfSubarrayMin(arr);
        return sumMax - sumMin;
    }
    
    /**
     * Calculate sum of subarray maximums
     */
    private static long sumOfSubarrayMax(int[] arr) {
        int n = arr.length;
        long result = 0;
        
        // For each element, find PGE and NGE
        int[] pge = previousGreaterOrEqual(arr);
        int[] nge = nextGreater(arr);
        
        for (int i = 0; i < n; i++) {
            long left = i - pge[i];
            long right = nge[i] - i;
            result += (long) arr[i] * left * right;
        }
        
        return result;
    }
    
    /**
     * Calculate sum of subarray minimums
     */
    private static long sumOfSubarrayMin(int[] arr) {
        int n = arr.length;
        long result = 0;
        
        // For each element, find PSE and NSE
        int[] pse = previousSmallerOrEqual(arr);
        int[] nse = nextSmaller(arr);
        
        for (int i = 0; i < n; i++) {
            long left = i - pse[i];
            long right = nse[i] - i;
            result += (long) arr[i] * left * right;
        }
        
        return result;
    }
    
    /**
     * Previous Greater or Equal (for max)
     */
    private static int[] previousGreaterOrEqual(int[] arr) {
        int n = arr.length;
        int[] pge = new int[n];
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                stack.pop();
            }
            pge[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        
        return pge;
    }
    
    /**
     * Next Greater
     */
    private static int[] nextGreater(int[] arr) {
        int n = arr.length;
        int[] nge = new int[n];
        Stack<Integer> stack = new Stack<>();
        
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
                stack.pop();
            }
            nge[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }
        
        return nge;
    }
    
    /**
     * Previous Smaller or Equal (for min)
     */
    private static int[] previousSmallerOrEqual(int[] arr) {
        int n = arr.length;
        int[] pse = new int[n];
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                stack.pop();
            }
            pse[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        
        return pse;
    }
    
    /**
     * Next Smaller
     */
    private static int[] nextSmaller(int[] arr) {
        int n = arr.length;
        int[] nse = new int[n];
        Stack<Integer> stack = new Stack<>();
        
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            nse[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }
        
        return nse;
    }
    
    public static void main(String[] args) {
        System.out.println("=== Sum of Subarray Ranges ===\n");
        
        int[][] testCases = {
            {1, 2, 3, 4},
            {1, 3, 3},
            {4, -2, -3, 4, 1},
            {1, 2, 1},
            {5, 4, 3, 2, 1}
        };
        
        System.out.println(String.format("%-30s | %-20s", "Array", "Sum of Ranges"));
        System.out.println("-".repeat(55));
        
        for (int[] arr : testCases) {
            long result = sumOfRanges(arr);
            System.out.println(String.format("%-30s | %-20d", 
                Arrays.toString(arr), result));
        }
        
        System.out.println("\n" + "=".repeat(55));
        System.out.println("DETAILED EXAMPLE: [1, 2, 3, 4]");
        System.out.println("-".repeat(55));
        
        int[] arr = {1, 2, 3, 4};
        int n = arr.length;
        
        System.out.println("\nAll subarrays with their ranges:\n");
        System.out.println(String.format("%-20s | %-6s | %-6s | %-10s", 
            "Subarray", "Max", "Min", "Range"));
        System.out.println("-".repeat(50));
        
        long totalRange = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int max = Integer.MIN_VALUE;
                int min = Integer.MAX_VALUE;
                
                for (int k = i; k <= j; k++) {
                    max = Math.max(max, arr[k]);
                    min = Math.min(min, arr[k]);
                }
                
                int range = max - min;
                totalRange += range;
                
                StringBuilder sb = new StringBuilder("[");
                for (int k = i; k <= j; k++) {
                    if (k > i) sb.append(", ");
                    sb.append(arr[k]);
                }
                sb.append("]");
                
                System.out.println(String.format("%-20s | %-6d | %-6d | %-10d", 
                    sb.toString(), max, min, range));
            }
        }
        
        System.out.println("\nTotal Sum of Ranges: " + totalRange);
        
        System.out.println("\n" + "=".repeat(55));
        System.out.println("USING FORMULA: Sum = (Sum of Max) - (Sum of Min)");
        System.out.println("-".repeat(55));
        
        arr = new int[]{1, 2, 3, 4};
        long sumMax = sumOfSubarrayMax(arr);
        long sumMin = sumOfSubarrayMin(arr);
        long result = sumMax - sumMin;
        
        System.out.println("\nArray: " + Arrays.toString(arr));
        System.out.println("\nSum of all subarray maximums: " + sumMax);
        System.out.println("Sum of all subarray minimums: " + sumMin);
        System.out.println("Sum of ranges: " + sumMax + " - " + sumMin + " = " + result);
        
        System.out.println("\n" + "=".repeat(55));
        System.out.println("BREAKDOWN FOR [1, 2, 3, 4]:");
        System.out.println("-".repeat(55));
        
        arr = new int[]{1, 2, 3, 4};
        int[] pge = previousGreaterOrEqual(arr);
        int[] nge = nextGreater(arr);
        int[] pse = previousSmallerOrEqual(arr);
        int[] nse = nextSmaller(arr);
        
        System.out.println("\nContribution to Sum of Max:\n");
        System.out.println(String.format("%-8s | %-8s | %-8s | %-8s | %-15s | %-15s", 
            "Index", "Value", "PGE", "NGE", "Left*Right", "Contribution"));
        System.out.println("-".repeat(70));
        
        long sumMaxDebug = 0;
        for (int i = 0; i < arr.length; i++) {
            long left = i - pge[i];
            long right = nge[i] - i;
            long contribution = arr[i] * left * right;
            sumMaxDebug += contribution;
            
            String pge_str = pge[i] == -1 ? "None" : String.valueOf(pge[i]);
            String nge_str = nge[i] == arr.length ? "None" : String.valueOf(nge[i]);
            
            System.out.println(String.format("%-8d | %-8d | %-8s | %-8s | %-15d | %-15d", 
                i, arr[i], pge_str, nge_str, left * right, contribution));
        }
        
        System.out.println("Total: " + sumMaxDebug);
        
        System.out.println("\nContribution to Sum of Min:\n");
        System.out.println(String.format("%-8s | %-8s | %-8s | %-8s | %-15s | %-15s", 
            "Index", "Value", "PSE", "NSE", "Left*Right", "Contribution"));
        System.out.println("-".repeat(70));
        
        long sumMinDebug = 0;
        for (int i = 0; i < arr.length; i++) {
            long left = i - pse[i];
            long right = nse[i] - i;
            long contribution = arr[i] * left * right;
            sumMinDebug += contribution;
            
            String pse_str = pse[i] == -1 ? "None" : String.valueOf(pse[i]);
            String nse_str = nse[i] == arr.length ? "None" : String.valueOf(nse[i]);
            
            System.out.println(String.format("%-8d | %-8d | %-8s | %-8s | %-15d | %-15d", 
                i, arr[i], pse_str, nse_str, left * right, contribution));
        }
        
        System.out.println("Total: " + sumMinDebug);
        
        System.out.println("\nFinal: " + sumMaxDebug + " - " + sumMinDebug + " = " + (sumMaxDebug - sumMinDebug));
        
        System.out.println("\n" + "=".repeat(55));
        System.out.println("ANOTHER EXAMPLE: [1, 3, 3]");
        System.out.println("-".repeat(55));
        
        arr = new int[]{1, 3, 3};
        result = sumOfRanges(arr);
        
        System.out.println("\nArray: " + Arrays.toString(arr));
        System.out.println("Result: " + result);
        
        System.out.println("\n" + "=".repeat(55));
        System.out.println("KEY INSIGHTS:");
        System.out.println("-".repeat(55));
        System.out.println("1. Sum of ranges = Sum of max - Sum of min");
        System.out.println("2. Use monotonic stack for PGE, NGE (max calculation)");
        System.out.println("3. Use monotonic stack for PSE, NSE (min calculation)");
        System.out.println("4. Handle equal values carefully in boundary conditions");
        System.out.println("5. Time: O(n) using monotonic stack");
        System.out.println("6. Space: O(n) for stack and auxiliary arrays");
    }
}
