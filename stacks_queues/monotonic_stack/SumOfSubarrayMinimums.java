package stacks_queues.monotonic_stack;

import java.util.*;

/**
 * Sum of Subarray Minimums
 * 
 * Problem: For each element, it acts as minimum in some subarrays.
 *          Calculate sum of all minimums of all possible subarrays.
 * 
 * Example: [3, 1, 2, 4]
 * Subarrays and their minimums:
 * [3] → 3
 * [1] → 1
 * [2] → 2
 * [4] → 4
 * [3,1] → 1
 * [1,2] → 1
 * [2,4] → 2
 * [3,1,2] → 1
 * [1,2,4] → 1
 * [3,1,2,4] → 1
 * Sum = 3+1+2+4+1+1+2+1+1+1 = 17
 * 
 * Algorithm: Monotonic Stack
 * ===========================
 * For each element as minimum, count subarrays where it's minimum:
 * 1. Find left: Previous smaller element (PSE)
 * 2. Find right: Next smaller element (NSE)
 * 3. Contribution = element * (left_distance) * (right_distance)
 * 4. Distance = number of subarrays where this element is minimum
 * 
 * Example: Element 1 at index 1
 * - PSE: none (distance = 2, includes index 0,1)
 * - NSE: none (distance = 3, includes index 1,2,3)
 * - Contribution = 1 * 2 * 3 = 6
 * - Subarrays: [3,1], [1], [1,2], [1,2,4], [3,1,2], [3,1,2,4]
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

public class SumOfSubarrayMinimums {
    
    private static final long MOD = 1000000007;
    
    /**
     * Calculate sum of subarray minimums
     */
    public static long sumSubarrayMins(int[] arr) {
        int n = arr.length;
        long result = 0;
        
        // For each element, find PSE and NSE
        int[] pse = previousSmallerElement(arr);
        int[] nse = nextSmallerOrEqual(arr);
        
        for (int i = 0; i < n; i++) {
            int left = i - pse[i];      // Distance to PSE (or to start)
            int right = nse[i] - i;     // Distance to NSE (or to end)
            
            long contribution = (long) arr[i] * left % MOD * right % MOD;
            result = (result + contribution) % MOD;
        }
        
        return result;
    }
    
    /**
     * Find previous smaller element indices
     * -1 if no previous smaller exists
     */
    private static int[] previousSmallerElement(int[] arr) {
        int n = arr.length;
        int[] pse = new int[n];
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            pse[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        
        return pse;
    }
    
    /**
     * Find next smaller or equal element indices
     * n if no next smaller exists
     */
    private static int[] nextSmallerOrEqual(int[] arr) {
        int n = arr.length;
        int[] nse = new int[n];
        Stack<Integer> stack = new Stack<>();
        
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                stack.pop();
            }
            nse[i] = stack.isEmpty() ? n : stack.peek();
            stack.push(i);
        }
        
        return nse;
    }
    
    public static void main(String[] args) {
        System.out.println("=== Sum of Subarray Minimums ===\n");
        
        int[][] testCases = {
            {3, 1, 2, 4},
            {1, 2, 3, 4},
            {4, 3, 2, 1},
            {1, 1, 1, 1},
            {11, 81, 94, 43, 3}
        };
        
        System.out.println(String.format("%-30s | %-20s", "Array", "Sum of Min"));
        System.out.println("-".repeat(55));
        
        for (int[] arr : testCases) {
            long result = sumSubarrayMins(arr);
            System.out.println(String.format("%-30s | %-20d", 
                Arrays.toString(arr), result));
        }
        
        System.out.println("\n" + "=".repeat(55));
        System.out.println("DETAILED EXAMPLE: [3, 1, 2, 4]");
        System.out.println("-".repeat(55));
        
        int[] arr = {3, 1, 2, 4};
        int n = arr.length;
        
        System.out.println("\nAll subarrays with their minimums:\n");
        System.out.println(String.format("%-20s | %-10s | %-15s", "Subarray", "Min", "Index Range"));
        System.out.println("-".repeat(50));
        
        long totalSum = 0;
        List<int[]> subarrays = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int min = Integer.MAX_VALUE;
                for (int k = i; k <= j; k++) {
                    min = Math.min(min, arr[k]);
                }
                subarrays.add(new int[]{i, j, min});
                totalSum += min;
            }
        }
        
        for (int[] sub : subarrays) {
            StringBuilder sb = new StringBuilder("[");
            for (int k = sub[0]; k <= sub[1]; k++) {
                if (k > sub[0]) sb.append(", ");
                sb.append(arr[k]);
            }
            sb.append("]");
            
            System.out.println(String.format("%-20s | %-10d | %-15s", 
                sb.toString(), sub[2], "[" + sub[0] + ", " + sub[1] + "]"));
        }
        
        System.out.println("\nTotal Sum: " + totalSum);
        
        System.out.println("\n" + "=".repeat(55));
        System.out.println("CONTRIBUTION ANALYSIS:");
        System.out.println("-".repeat(55));
        
        arr = new int[]{3, 1, 2, 4};
        int[] pse = previousSmallerElement(arr);
        int[] nse = nextSmallerOrEqual(arr);
        
        System.out.println("\nFor each element, calculate its contribution:\n");
        System.out.println(String.format("%-8s | %-8s | %-8s | %-10s | %-10s | %-15s", 
            "Index", "Value", "PSE", "NSE", "Left*Right", "Contribution"));
        System.out.println("-".repeat(70));
        
        long result = 0;
        for (int i = 0; i < n; i++) {
            int left = i - pse[i];      // Elements where this can be start
            int right = nse[i] - i;     // Elements where this can be end
            long contribution = (long) arr[i] * left * right;
            result += contribution;
            
            String pse_str = pse[i] == -1 ? "None" : String.valueOf(pse[i]);
            String nse_str = nse[i] == n ? "None" : String.valueOf(nse[i]);
            
            System.out.println(String.format("%-8d | %-8d | %-8s | %-10s | %-10d | %-15d", 
                i, arr[i], pse_str, nse_str, left * right, contribution));
        }
        
        System.out.println("\nTotal Sum: " + result);
        
        System.out.println("\n" + "=".repeat(55));
        System.out.println("DETAILED CALCULATION FOR ELEMENT 1 (index 1):");
        System.out.println("-".repeat(55));
        
        System.out.println("\nArray: [3, 1, 2, 4]");
        System.out.println("Element: 1 at index 1");
        System.out.println("\nPrevious Smaller Element: None (index -1)");
        System.out.println("  → Left distance = 1 - (-1) = 2");
        System.out.println("  → Can start from indices: 0, 1");
        
        System.out.println("\nNext Smaller Element: None (end of array)");
        System.out.println("  → Right distance = 4 - 1 = 3");
        System.out.println("  → Can end at indices: 1, 2, 3");
        
        System.out.println("\nTotal subarrays where 1 is minimum:");
        System.out.println("  [3, 1] (start=0, end=1)");
        System.out.println("  [1] (start=1, end=1)");
        System.out.println("  [1, 2] (start=1, end=2)");
        System.out.println("  [1, 2, 4] (start=1, end=3)");
        System.out.println("  [3, 1, 2] (start=0, end=2)");
        System.out.println("  [3, 1, 2, 4] (start=0, end=3)");
        System.out.println("\nContribution = 1 * 2 * 3 = 6 subarrays × value 1 = 6");
        
        System.out.println("\n" + "=".repeat(55));
        System.out.println("ANOTHER EXAMPLE: [11, 81, 94, 43, 3]");
        System.out.println("-".repeat(55));
        
        arr = new int[]{11, 81, 94, 43, 3};
        result = sumSubarrayMins(arr);
        
        System.out.println("\nArray:  " + Arrays.toString(arr));
        System.out.println("Result: " + result);
        
        System.out.println("\n" + "=".repeat(55));
        System.out.println("ALGORITHM STEPS:");
        System.out.println("-".repeat(55));
        System.out.println("1. For each element, find previous smaller element (PSE)");
        System.out.println("   → Left boundary of subarrays where element is min");
        System.out.println("2. For each element, find next smaller element (NSE)");
        System.out.println("   → Right boundary of subarrays where element is min");
        System.out.println("3. Left distance = i - PSE[i]");
        System.out.println("4. Right distance = NSE[i] - i");
        System.out.println("5. Contribution = arr[i] * left * right");
        System.out.println("6. Sum all contributions");
        
        System.out.println("\n" + "=".repeat(55));
        System.out.println("KEY INSIGHTS:");
        System.out.println("-".repeat(55));
        System.out.println("• Each element contributes based on subarrays it minimizes");
        System.out.println("• Count = (elements to left including self) × (elements to right including self)");
        System.out.println("• Use PSE and NSE to find boundaries efficiently");
        System.out.println("• Time: O(n) using monotonic stack for PSE and NSE");
        System.out.println("• Important: NSE uses ≤ comparison to handle duplicates");
    }
}
