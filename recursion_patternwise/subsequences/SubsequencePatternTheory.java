package recursion_patternwise.subsequences;

import java.util.ArrayList;
import java.util.List;

public class SubsequencePatternTheory {

    /*
    =====================================================================================
    LEARNING ALL PATTERNS OF SUBSEQUENCES - THEORETICAL GUIDE
    -------------------------------------------------------------------------------------
    
    DEFINITION:
    A subsequence is a sequence that can be derived from another sequence by deleting 
    some (or no) elements without changing the order of the remaining elements.
    
    Key Differences:
    - Subsequence: Order maintained, non-contiguous allowed
    - Subarray: Contiguous elements
    - Subset: Order doesn't matter (for unordered collections)
    
    PATTERN 1: PICK/NOT-PICK (Binary Choice)
    ─────────────────────────────────────────
    At each element, we have 2 choices:
    1. Pick the element (include in subsequence)
    2. Don't pick the element (exclude from subsequence)
    
    Code Pattern:
    void solve(index, currentSubseq, result) {
        if (index == n) {
            result.add(currentSubseq);
            return;
        }
        
        // Pick
        currentSubseq.add(arr[index]);
        solve(index + 1, currentSubseq, result);
        currentSubseq.remove(last);
        
        // Don't pick
        solve(index + 1, currentSubseq, result);
    }
    
    Time: O(n * 2^n), Space: O(n)
    Examples: All subsequences, Subset Sum, Combinations with sum
    
    ─────────────────────────────────────────────────────────────
    
    PATTERN 2: INDEX-BASED (For combinations)
    ──────────────────────────────────────────
    Start from different indices to avoid duplicates.
    Used when we want combinations in specific order.
    
    Code Pattern:
    void solve(index, currentComb, result) {
        if (condition) {
            result.add(currentComb);
            return;
        }
        
        for (int i = index; i < arr.length; i++) {
            currentComb.add(arr[i]);
            solve(i + 1, currentComb, result);
            currentComb.remove(last);
        }
    }
    
    Time: O(2^n), Space: O(n)
    Examples: Combination Sum, Letter Combinations, Subsets II
    
    ─────────────────────────────────────────────────────────────
    
    PATTERN 3: CONSTRAINTS-BASED
    ────────────────────────────
    Add condition checks to prune invalid paths early.
    
    Code Pattern:
    void solve(index, current, target, result) {
        if (condition == target) {
            result.add(current);
            return;
        }
        
        if (index >= arr.length || condition > target) {
            return;  // Pruning
        }
        
        // Pick
        solve(index + 1, ...);
        
        // Don't pick
        solve(index + 1, ...);
    }
    
    Examples: Subset Sum with target, Combination Sum with sum K
    
    ─────────────────────────────────────────────────────────────
    
    COMMON PROBLEMS & PATTERNS:
    
    1. COUNT subsequences/combinations
       → Use counter instead of storing all
       → dp[i] = number of ways
    
    2. CHECK if subsequence exists with property
       → Return boolean, exit early on match
       → No need to generate all
    
    3. PRINT all subsequences
       → Store all in list
       → Use pick/not-pick pattern
    
    4. FIND maximum/minimum in subsequences
       → Track max/min while generating
       → Or generate and analyze
    
    5. WITH DUPLICATES (sort first)
       → Skip duplicate elements using loop
       → Use index-based pattern with duplicate check
    
    =====================================================================================
    */
    
    /**
     * Demonstration of Pattern 1: Pick/Not-Pick
     * Problem: Generate all subsequences
     */
    public static List<List<Integer>> pattern1PickNotPick(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        generatePickNotPick(arr, 0, new ArrayList<>(), result);
        return result;
    }
    
    private static void generatePickNotPick(int[] arr, int index, List<Integer> current, List<List<Integer>> result) {
        if (index == arr.length) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        // Pick
        current.add(arr[index]);
        generatePickNotPick(arr, index + 1, current, result);
        current.remove(current.size() - 1);
        
        // Not pick
        generatePickNotPick(arr, index + 1, current, result);
    }
    
    /**
     * Demonstration of Pattern 2: Index-Based (Loop)
     * Problem: Generate combinations
     */
    public static List<List<Integer>> pattern2IndexBased(int[] arr, int r) {
        List<List<Integer>> result = new ArrayList<>();
        generateCombinations(arr, 0, r, new ArrayList<>(), result);
        return result;
    }
    
    private static void generateCombinations(int[] arr, int index, int remaining, List<Integer> current, List<List<Integer>> result) {
        if (remaining == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        
        if (index >= arr.length) {
            return;
        }
        
        for (int i = index; i < arr.length; i++) {
            current.add(arr[i]);
            generateCombinations(arr, i + 1, remaining - 1, current, result);
            current.remove(current.size() - 1);
        }
    }
    
    /**
     * Demonstration of Pattern 3: Constraints-Based
     * Problem: Find subsequences with sum = target
     */
    public static int pattern3ConstraintsBased(int[] arr, int target) {
        return countSubsequencesWithSum(arr, 0, 0, target);
    }
    
    private static int countSubsequencesWithSum(int[] arr, int index, int currentSum, int target) {
        // Base case 1: Found valid subsequence
        if (currentSum == target && index == arr.length) {
            return 1;
        }
        
        // Base case 2: Exceeded target or array limit
        if (index == arr.length || currentSum > target) {
            return 0;
        }
        
        // Pick current element
        int pick = countSubsequencesWithSum(arr, index + 1, currentSum + arr[index], target);
        
        // Don't pick current element
        int notPick = countSubsequencesWithSum(arr, index + 1, currentSum, target);
        
        return pick + notPick;
    }
    
    /**
     * Print theoretical overview
     */
    public static void printTheory() {
        System.out.println("╔════════════════════════════════════════════════════════════════════╗");
        System.out.println("║           SUBSEQUENCE PATTERNS - THEORETICAL OVERVIEW              ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════╝");
        System.out.println();
        
        System.out.println("PATTERN 1: PICK / NOT-PICK (Binary Choice)");
        System.out.println("─────────────────────────────────────────");
        System.out.println("Choice: Include element or exclude element");
        System.out.println("Time: O(n * 2^n)  |  Space: O(n)");
        System.out.println("Use: All subsequences, subset with properties");
        System.out.println();
        
        System.out.println("PATTERN 2: INDEX-BASED / LOOP");
        System.out.println("────────────────────────────");
        System.out.println("Choice: Start from different indices");
        System.out.println("Time: O(2^n)  |  Space: O(n)");
        System.out.println("Use: Combinations, avoid duplicates");
        System.out.println();
        
        System.out.println("PATTERN 3: CONSTRAINTS-BASED");
        System.out.println("───────────────────────────");
        System.out.println("Choice: With pruning conditions");
        System.out.println("Time: Varies  |  Space: O(n)");
        System.out.println("Use: Subset sum, target-based problems");
        System.out.println();
        
        System.out.println("WHEN TO USE EACH PATTERN:");
        System.out.println("─────────────────────────");
        System.out.println("1. Generate all subsequences? → Pattern 1");
        System.out.println("2. Combinations, permutations? → Pattern 2");
        System.out.println("3. With constraints/conditions? → Pattern 3");
        System.out.println("4. Avoid duplicates? → Pattern 2 with sorting");
        System.out.println("5. Just count? → Optimize recursion (no list)");
        System.out.println();
    }
    
    public static void main(String[] args) {
        printTheory();
        
        // Demo Pattern 1
        System.out.println("\n=== PATTERN 1 DEMO: Pick/Not-Pick ===");
        int[] arr1 = {1, 2};
        System.out.println("All subsequences of [1, 2]:");
        List<List<Integer>> p1 = pattern1PickNotPick(arr1);
        for (List<Integer> subseq : p1) {
            System.out.println("  " + subseq);
        }
        System.out.println();
        
        // Demo Pattern 2
        System.out.println("=== PATTERN 2 DEMO: Index-Based ===");
        int[] arr2 = {1, 2, 3};
        System.out.println("All 2-combinations of [1, 2, 3]:");
        List<List<Integer>> p2 = pattern2IndexBased(arr2, 2);
        for (List<Integer> comb : p2) {
            System.out.println("  " + comb);
        }
        System.out.println();
        
        // Demo Pattern 3
        System.out.println("=== PATTERN 3 DEMO: Constraints-Based ===");
        int[] arr3 = {1, 2, 3};
        System.out.println("Subsequences of [1, 2, 3] with sum = 3:");
        int count = pattern3ConstraintsBased(arr3, 3);
        System.out.println("  Count: " + count);
        System.out.println("  Subsequences: [3], [1, 2]");
    }
}
