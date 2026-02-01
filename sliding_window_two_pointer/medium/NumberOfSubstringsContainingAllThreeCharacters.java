package sliding_window_two_pointer.medium;

import java.util.*;

/**
 * Number of Substrings Containing All Three Characters
 * 
 * Problem: Given a string s consisting of only characters 'a', 'b', 'c',
 *          return the number of substrings containing at least one occurrence
 *          of all three characters 'a', 'b', and 'c'.
 * 
 * Example: s="abcabc" → 10
 *          Valid substrings: "abc", "abca", "abcab", "abcabc" (from index 0)
 *                           "bca", "bcab", "bcabc" (from index 1)
 *                           "cab", "cabc" (from index 2)
 *          s="aaacb" → 1 ("aaacb")
 *          s="abc" → 1 ("abc")
 * 
 * Algorithm: Sliding Window with Last Occurrence Tracking
 * =======================================================
 * Key Insight: For each ending position, count all valid starting positions
 * 
 * 1. Use sliding window [left, right]
 * 2. Track LAST occurrence of 'a', 'b', 'c' in lastPos map
 * 3. For each right position:
 *    - Update lastPos[s[right]]
 *    - Minimum of three last positions = can start from here
 *    - All positions from 0 to min are valid starting points
 *    - Count += min (all positions from 0 to min inclusive)
 * 
 * Optimization: No need for explicit left pointer!
 * - We only care about minimum of last occurrences
 * - Not a traditional sliding window contraction
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1) - only 3 characters
 */

public class NumberOfSubstringsContainingAllThreeCharacters {
    
    /**
     * Count substrings containing all three characters a, b, c
     */
    public static int numberOfSubstrings(String s) {
        // Track last occurrence of each character
        Map<Character, Integer> lastOccurrence = new HashMap<>();
        lastOccurrence.put('a', -1);
        lastOccurrence.put('b', -1);
        lastOccurrence.put('c', -1);
        
        int count = 0;
        
        for (int i = 0; i < s.length(); i++) {
            // Update last occurrence of current character
            lastOccurrence.put(s.charAt(i), i);
            
            // Minimum of three last occurrences
            int minLastPos = Math.min(lastOccurrence.get('a'), 
                                     Math.min(lastOccurrence.get('b'), 
                                             lastOccurrence.get('c')));
            
            // All positions from 0 to minLastPos can be valid starting points
            count += minLastPos + 1;
        }
        
        return count;
    }
    
    /**
     * Alternative: With explicit left pointer (less efficient)
     */
    public static int numberOfSubstrings_Verbose(String s) {
        int left = 0;
        int count = 0;
        Map<Character, Integer> freq = new HashMap<>();
        
        for (int right = 0; right < s.length(); right++) {
            freq.put(s.charAt(right), freq.getOrDefault(s.charAt(right), 0) + 1);
            
            // Shrink window while it contains all three
            while (freq.size() == 3) {
                // All positions from 0 to left are valid starting points
                count += s.length() - right;
                
                char leftChar = s.charAt(left);
                freq.put(leftChar, freq.get(leftChar) - 1);
                if (freq.get(leftChar) == 0) {
                    freq.remove(leftChar);
                }
                left++;
            }
        }
        
        return count;
    }
    
    public static void main(String[] args) {
        System.out.println("=== Number of Substrings Containing All Three Characters ===\n");
        
        String[] testCases = {
            "abcabc",
            "aaacb",
            "abc",
            "aabbcc",
            "abac",
            "a"
        };
        
        System.out.println(String.format("%-20s | %-10s", "String", "Count"));
        System.out.println("-".repeat(35));
        
        for (String s : testCases) {
            int result = numberOfSubstrings(s);
            System.out.println(String.format("%-20s | %-10d", s, result));
        }
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("DETAILED EXAMPLE 1: s=\"abcabc\"");
        System.out.println("-".repeat(70));
        
        String s = "abcabc";
        System.out.println("\nString: " + s);
        System.out.println("\nSliding window trace with last occurrence positions:\n");
        
        Map<Character, Integer> lastOccurrence = new HashMap<>();
        lastOccurrence.put('a', -1);
        lastOccurrence.put('b', -1);
        lastOccurrence.put('c', -1);
        
        int count = 0;
        
        System.out.println(String.format("%-8s | %-6s | %-15s | %-15s | %-12s | %-10s", 
            "Index", "Char", "LastPos(a,b,c)", "Min", "Valid Start", "Count"));
        System.out.println("-".repeat(75));
        
        for (int i = 0; i < s.length(); i++) {
            lastOccurrence.put(s.charAt(i), i);
            
            int minLastPos = Math.min(lastOccurrence.get('a'), 
                                     Math.min(lastOccurrence.get('b'), 
                                             lastOccurrence.get('c')));
            
            int added = minLastPos + 1;
            count += added;
            
            System.out.println(String.format("%-8d | %-6s | (%d,%d,%d) | %-15d | [0..%d] | %d", 
                i, s.charAt(i),
                lastOccurrence.get('a'),
                lastOccurrence.get('b'),
                lastOccurrence.get('c'),
                minLastPos,
                minLastPos,
                added));
        }
        
        System.out.println("\nTotal: " + count);
        
        System.out.println("\nSubstrings verification:");
        System.out.println("From index 0 (char='a'):");
        System.out.println("  Min={a:0, b:-1, c:-1} → Can start from [0..(-1)] = 0 substrings");
        System.out.println("From index 1 (char='b'):");
        System.out.println("  Min={a:0, b:1, c:-1} → Can start from [0..(-1)] = 0 substrings");
        System.out.println("From index 2 (char='c'):");
        System.out.println("  Min={a:0, b:1, c:2} → Can start from [0..0] = 1 substring: \"abc\"");
        System.out.println("From index 3 (char='a'):");
        System.out.println("  Min={a:3, b:1, c:2} → Can start from [0..1] = 2 substrings: \"abca\", \"bca\"");
        System.out.println("From index 4 (char='b'):");
        System.out.println("  Min={a:3, b:4, c:2} → Can start from [0..2] = 3 substrings");
        System.out.println("From index 5 (char='c'):");
        System.out.println("  Min={a:3, b:4, c:5} → Can start from [0..3] = 4 substrings");
        System.out.println("Total: 0+0+1+2+3+4 = 10");
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("DETAILED EXAMPLE 2: s=\"aaacb\"");
        System.out.println("-".repeat(70));
        
        s = "aaacb";
        System.out.println("\nString: " + s);
        System.out.println("\nCharacter occurrences:");
        System.out.println("Index: 0(a), 1(a), 2(a), 3(c), 4(b)");
        System.out.println("\nWe need all three: 'a', 'b', 'c'");
        System.out.println("They all appear only at the very end (index 4)");
        System.out.println("\nWhen we reach index 4:");
        System.out.println("  lastOccurrence: a=2, b=4, c=3");
        System.out.println("  min = 2");
        System.out.println("  Valid starting positions: [0..2] = 3 positions");
        System.out.println("    [0,4]: \"aaacb\" ✓");
        System.out.println("    [1,4]: \"aacb\" ✓");
        System.out.println("    [2,4]: \"acb\" ✓");
        System.out.println("\nTotal: 3 substrings");
        
        int result = numberOfSubstrings(s);
        System.out.println("Algorithm result: " + result);
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("WHY THIS WORKS:");
        System.out.println("-".repeat(70));
        System.out.println("Key Insight: For each ending position i:");
        System.out.println("  - We have lastPos[a], lastPos[b], lastPos[c]");
        System.out.println("  - min = minimum of three");
        System.out.println("  - Any subarray [j, i] where j <= min contains all three");
        System.out.println("  - Why? Because [j, i] includes min, and min is the");
        System.out.println("    rightmost position where we last saw the least");
        System.out.println("    recently updated character");
        System.out.println("\nOptimization: No need for traditional sliding window");
        System.out.println("  - Just track last occurrence of each character");
        System.out.println("  - No shrinking needed!");
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("ALGORITHM EXPLANATION:");
        System.out.println("-".repeat(70));
        System.out.println("1. Track last occurrence index of 'a', 'b', 'c'");
        System.out.println("2. For each position i:");
        System.out.println("   - Update lastOccurrence[s[i]] = i");
        System.out.println("   - Find min of three last occurrences");
        System.out.println("   - All positions [0..min] can be valid starts");
        System.out.println("   - Add (min + 1) to count");
        System.out.println("3. This counts all substrings [j,i] with all three chars");
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("KEY POINTS:");
        System.out.println("-".repeat(70));
        System.out.println("1. Don't need to maintain entire window!");
        System.out.println("2. Only track last positions of three characters");
        System.out.println("3. Minimum tells us earliest valid start");
        System.out.println("4. For each end position, add all valid starts");
        System.out.println("5. Time: O(n) single pass");
        System.out.println("6. Space: O(1) fixed 3 characters");
        System.out.println("7. Clever optimization: no explicit window shrinking");
    }
}
