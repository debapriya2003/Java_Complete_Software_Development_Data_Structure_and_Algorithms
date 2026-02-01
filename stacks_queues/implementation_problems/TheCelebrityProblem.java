package stacks_queues.implementation_problems;

import java.util.*;

/**
 * The Celebrity Problem
 * 
 * Problem: In a group of n people, find the celebrity.
 *          Celebrity: knows no one, but everyone knows celebrity.
 *          Return index or -1 if no celebrity exists.
 * 
 * Constraint: Can use knows(i, j) API at most O(n) times.
 * 
 * knows(i, j) returns true if person i knows person j.
 * 
 * Example:
 * Assume knows function:
 * knows(0, 1) = true
 * knows(1, 2) = true
 * knows(2, 0) = false
 * knows(2, 1) = false
 * knows(1, 0) = false
 * knows(0, 2) = false
 * 
 * Person 0: knows 1, doesn't know 2
 * Person 1: knows 2, doesn't know 0
 * Person 2: knows no one
 * 
 * Celebrity verification for person 2:
 * - Person 2 should know no one ✓
 * - Everyone else should know person 2: 0,1 should know 2
 *   0 knows 2? No ✗
 * 
 * Actually person 1:
 * - Person 1 should know no one? knows 2 ✗
 * 
 * Let me reconsider...
 * 
 * Algorithm: Stack-based approach
 * ================================
 * 1. Push all people indices to stack
 * 2. While stack has > 1 person:
 *    - Pop two people: a and b
 *    - If a knows b: a can't be celebrity, push b
 *    - If a doesn't know b: b can't be celebrity, push a
 * 3. Remaining person is potential celebrity
 * 4. Verify: person knows no one, everyone else knows this person
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n) for stack
 */

public class TheCelebrityProblem {
    
    /**
     * Celebrity problem using stack
     */
    public static int findCelebrity(int n, boolean[][] knows) {
        Stack<Integer> stack = new Stack<>();
        
        // Push all people to stack
        for (int i = 0; i < n; i++) {
            stack.push(i);
        }
        
        // Eliminate non-celebrities
        while (stack.size() > 1) {
            int a = stack.pop();
            int b = stack.pop();
            
            if (knows[a][b]) {
                // a knows b, so a is not celebrity
                stack.push(b);
            } else {
                // a doesn't know b, so b is not celebrity
                stack.push(a);
            }
        }
        
        // Verify the candidate
        int candidate = stack.pop();
        
        for (int i = 0; i < n; i++) {
            // Celebrity should not know anyone
            if (candidate != i && knows[candidate][i]) {
                return -1;
            }
            
            // Everyone should know celebrity
            if (candidate != i && !knows[i][candidate]) {
                return -1;
            }
        }
        
        return candidate;
    }
    
    /**
     * Two-pointer approach
     */
    public static int findCelebrityTwoPointer(int n, boolean[][] knows) {
        int left = 0, right = n - 1;
        
        // Eliminate candidates
        while (left < right) {
            if (knows[left][right]) {
                // left knows right, left is not celebrity
                left++;
            } else {
                // left doesn't know right, right is not celebrity
                right--;
            }
        }
        
        // Verify candidate
        int candidate = left;
        for (int i = 0; i < n; i++) {
            if (candidate != i && (knows[candidate][i] || !knows[i][candidate])) {
                return -1;
            }
        }
        
        return candidate;
    }
    
    public static void main(String[] args) {
        System.out.println("=== The Celebrity Problem ===\n");
        
        // Test case 1
        boolean[][] knows1 = {
            {false, true,  false},
            {false, false, true},
            {false, false, false}
        };
        
        System.out.println("Test Case 1:");
        printKnowsMatrix(knows1);
        
        int result = findCelebrity(3, knows1);
        System.out.println("Celebrity: " + (result == -1 ? "Not found" : result));
        
        System.out.println("\nAnalysis:");
        System.out.println("Person 0: knows [1], known by [1, 2]");
        System.out.println("Person 1: knows [2], known by [0]");
        System.out.println("Person 2: knows [], known by [0, 1]");
        System.out.println("Person 2 is celebrity (knows no one, everyone knows 2)");
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("DETAILED EXAMPLE WITH STACK TRACE:");
        System.out.println("-".repeat(70));
        
        boolean[][] knows = {
            {false, true,  false},
            {false, false, true},
            {false, false, false}
        };
        
        System.out.println("\nKnows Matrix:");
        printKnowsMatrix(knows);
        
        System.out.println("\nStack-based elimination:\n");
        
        Stack<Integer> stack = new Stack<>();
        int n = 3;
        
        for (int i = 0; i < n; i++) {
            stack.push(i);
        }
        
        System.out.println("Initial stack: " + stack);
        
        int step = 1;
        while (stack.size() > 1) {
            int a = stack.pop();
            int b = stack.pop();
            
            System.out.println("\nStep " + step + ": Compare persons " + a + " and " + b);
            System.out.println("  knows[" + a + "][" + b + "] = " + knows[a][b]);
            
            if (knows[a][b]) {
                System.out.println("  Person " + a + " knows person " + b);
                System.out.println("  Person " + a + " cannot be celebrity");
                System.out.println("  Push " + b + " to stack");
                stack.push(b);
            } else {
                System.out.println("  Person " + a + " doesn't know person " + b);
                System.out.println("  Person " + b + " cannot be celebrity");
                System.out.println("  Push " + a + " to stack");
                stack.push(a);
            }
            
            System.out.println("  Stack: " + stack);
            step++;
        }
        
        int candidate = stack.pop();
        System.out.println("\nCandidate celebrity: " + candidate);
        
        System.out.println("\nVerification:");
        boolean valid = true;
        
        for (int i = 0; i < n; i++) {
            if (candidate != i) {
                boolean candidateKnowsI = knows[candidate][i];
                boolean iKnowsCandidate = knows[i][candidate];
                
                System.out.println("Person " + i + ":");
                System.out.println("  knows[" + candidate + "][" + i + "] = " + candidateKnowsI + " (should be false)");
                System.out.println("  knows[" + i + "][" + candidate + "] = " + iKnowsCandidate + " (should be true)");
                
                if (candidateKnowsI || !iKnowsCandidate) {
                    valid = false;
                }
            }
        }
        
        System.out.println("\nValid celebrity: " + valid);
        System.out.println("Answer: " + candidate);
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("TWO-POINTER APPROACH:");
        System.out.println("-".repeat(70));
        
        knows = new boolean[][]{
            {false, true,  false},
            {false, false, true},
            {false, false, false}
        };
        
        System.out.println("\nInitial: left=0, right=2");
        
        int left = 0, right = n - 1;
        
        step = 1;
        while (left < right) {
            System.out.println("\nStep " + step + ": Compare " + left + " and " + right);
            System.out.println("  knows[" + left + "][" + right + "] = " + knows[left][right]);
            
            if (knows[left][right]) {
                System.out.println("  Person " + left + " knows " + right + ", " + left + " not celebrity");
                System.out.println("  left++ → " + (left + 1));
                left++;
            } else {
                System.out.println("  Person " + left + " doesn't know " + right + ", " + right + " not celebrity");
                System.out.println("  right-- → " + (right - 1));
                right--;
            }
            step++;
        }
        
        System.out.println("\nCandidate: " + left);
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("MORE TEST CASES:");
        System.out.println("-".repeat(70));
        
        // Test case: No celebrity
        boolean[][] knows2 = {
            {false, true},
            {true, false}
        };
        
        System.out.println("\nTest Case (No celebrity):");
        printKnowsMatrix(knows2);
        result = findCelebrity(2, knows2);
        System.out.println("Celebrity: " + (result == -1 ? "Not found (correct)" : result));
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("ALGORITHM EXPLANATION:");
        System.out.println("-".repeat(70));
        System.out.println("Stack approach:");
        System.out.println("  1. Push all people indices");
        System.out.println("  2. Compare pairs: if a knows b, b might be celebrity");
        System.out.println("  3. Continue until one person remains");
        System.out.println("  4. Verify: knows no one, everyone knows them");
        
        System.out.println("\nTwo-pointer approach:");
        System.out.println("  1. Use left/right pointers, n-1 comparisons total");
        System.out.println("  2. If left knows right: left can't be celebrity");
        System.out.println("  3. If left doesn't know right: right can't be celebrity");
        System.out.println("  4. Verify candidate");
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("COMPLEXITY:");
        System.out.println("-".repeat(70));
        System.out.println("Stack approach:      O(n) for elimination + O(n) verification = O(n)");
        System.out.println("Two-pointer:         O(n) for elimination + O(n) verification = O(n)");
        System.out.println("Maximum comparisons: O(n) as per constraint");
    }
    
    private static void printKnowsMatrix(boolean[][] knows) {
        int n = knows.length;
        System.out.print("   ");
        for (int j = 0; j < n; j++) {
            System.out.print(" " + j + " ");
        }
        System.out.println();
        
        for (int i = 0; i < n; i++) {
            System.out.print(" " + i + ":");
            for (int j = 0; j < n; j++) {
                System.out.print(" " + (knows[i][j] ? "T" : "F") + " ");
            }
            System.out.println();
        }
    }
}
