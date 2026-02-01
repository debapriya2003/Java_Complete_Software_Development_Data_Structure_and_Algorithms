package recursion_patternwise.subsequences;

import java.util.ArrayList;
import java.util.List;

public class PrintAllSubsequences {

    /*
    =====================================================================================
    PROBLEM: PRINT ALL SUBSEQUENCES (POWER SET)
    -------------------------------------------------------------------------------------
    Given an array/string, generate all possible subsequences.
    A subsequence maintains the relative order of elements but doesn't need to be contiguous.
    
    Example:
    Input: "abc"
    Output: "", "a", "b", "c", "ab", "ac", "bc", "abc"
    
    Input: [1, 2, 3]
    Output: [], [1], [2], [3], [1,2], [1,3], [2,3], [1,2,3]
    
    Total subsequences for n elements: 2^n
    
    Approach 1: Recursive with pick/not-pick
    - For each element: pick it or don't pick it
    - Two choices lead to 2^n subsequences
    
    Approach 2: Iterative bit manipulation
    - Use each number from 0 to 2^n-1 as bitmask
    - If bit i is set, include element i
    
    Time Complexity: O(n * 2^n) - generate all 2^n subsequences
    Space Complexity: O(n) - recursion depth
    =====================================================================================
    */
    
    /**
     * Print all subsequences using pick/not-pick approach.
     * 
     * @param arr input array
     * @return list of all subsequences
     */
    public static List<List<Integer>> getAllSubsequences(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        generateSubsequences(arr, 0, current, result);
        return result;
    }
    
    /**
     * Recursive helper using pick/not-pick approach.
     * 
     * Algorithm:
     * 1. Base case: when index reaches array length, add current to result
     * 2. Pick current element: add to current, recurse, remove from current
     * 3. Don't pick current element: recurse directly
     * 
     * @param arr input array
     * @param index current index
     * @param current current subsequence
     * @param result all subsequences
     */
    private static void generateSubsequences(int[] arr, int index, List<Integer> current, List<List<Integer>> result) {
        // Base case: processed all elements
        if (index == arr.length) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        // Pick current element
        current.add(arr[index]);
        generateSubsequences(arr, index + 1, current, result);
        current.remove(current.size() - 1);
        
        // Don't pick current element
        generateSubsequences(arr, index + 1, current, result);
    }
    
    /**
     * Generate subsequences from string.
     * 
     * @param s input string
     * @return list of all subsequences
     */
    public static List<String> getAllSubsequencesString(String s) {
        List<String> result = new ArrayList<>();
        generateSubsequencesString(s, 0, "", result);
        return result;
    }
    
    private static void generateSubsequencesString(String s, int index, String current, List<String> result) {
        // Base case
        if (index == s.length()) {
            result.add(current);
            return;
        }
        
        // Pick current character
        generateSubsequencesString(s, index + 1, current + s.charAt(index), result);
        
        // Don't pick current character
        generateSubsequencesString(s, index + 1, current, result);
    }
    
    /**
     * Generate subsequences using bit manipulation.
     * 
     * @param arr input array
     * @return list of all subsequences
     */
    public static List<List<Integer>> getAllSubsequencesBitwise(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        int n = arr.length;
        int total = 1 << n;  // 2^n
        
        for (int mask = 0; mask < total; mask++) {
            List<Integer> subseq = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                // Check if i-th bit is set
                if ((mask & (1 << i)) != 0) {
                    subseq.add(arr[i]);
                }
            }
            result.add(subseq);
        }
        
        return result;
    }
    
    /**
     * Print subsequences in sorted order.
     * 
     * @param result list of subsequences
     */
    public static void printSubsequences(List<List<Integer>> result) {
        System.out.println("All subsequences:");
        for (List<Integer> subseq : result) {
            System.out.println("  " + subseq);
        }
    }
    
    public static void main(String[] args) {
        // Test Case 1: Array [1, 2]
        System.out.println("=== Array [1, 2] ===");
        int[] arr1 = {1, 2};
        List<List<Integer>> result1 = getAllSubsequences(arr1);
        printSubsequences(result1);
        System.out.println("Count: " + result1.size() + " (expected: 4)");
        System.out.println();
        
        // Test Case 2: Array [1, 2, 3]
        System.out.println("=== Array [1, 2, 3] ===");
        int[] arr2 = {1, 2, 3};
        List<List<Integer>> result2 = getAllSubsequences(arr2);
        System.out.println("Count: " + result2.size() + " (expected: 8)");
        System.out.println("First 5: " + result2.subList(0, 5));
        System.out.println();
        
        // Test Case 3: String "abc"
        System.out.println("=== String \"abc\" ===");
        List<String> result3 = getAllSubsequencesString("abc");
        System.out.println("All subsequences: " + result3);
        System.out.println("Count: " + result3.size() + " (expected: 8)");
        System.out.println();
        
        // Test Case 4: Single element
        System.out.println("=== Array [5] ===");
        int[] arr4 = {5};
        List<List<Integer>> result4 = getAllSubsequences(arr4);
        printSubsequences(result4);
        System.out.println("Count: " + result4.size() + " (expected: 2)");
        System.out.println();
        
        // Test Case 5: Bitwise approach
        System.out.println("=== Bitwise Approach [1, 2, 3] ===");
        List<List<Integer>> result5 = getAllSubsequencesBitwise(arr2);
        System.out.println("Match with recursive? " + result2.equals(result5));
        System.out.println("Count: " + result5.size());
        System.out.println();
        
        // Test Case 6: Empty array
        System.out.println("=== Empty Array ===");
        int[] arr6 = {};
        List<List<Integer>> result6 = getAllSubsequences(arr6);
        printSubsequences(result6);
        System.out.println("Count: " + result6.size() + " (expected: 1 - empty set)");
    }
}
