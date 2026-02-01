package sliding_window_two_pointer.hard;

import java.util.*;

/**
 * Minimum Window Subsequence
 * 
 * Problem: Given strings s and t, find the minimum window substring of s
 *          that will contain all the characters in t as a subsequence.
 *          If no such window exists, return empty string.
 * 
 * Key Difference from Minimum Window Substring:
 * - Substring: characters must be CONTIGUOUS in s
 * - Subsequence: characters can be in ANY ORDER and non-contiguous
 * - This problem: window is contiguous, but t characters within it
 *                 can appear in any order (don't need to be contiguous)
 * 
 * Example: s="timmisajdgasdin", t="asd" → "adjgas" (or longer valid windows)
 *          s="dcbefebce", t="cdc" → "cdc" or "dcbc"
 *          s="abc", t="ad" → "" (no d in s)
 * 
 * Algorithm: Two Pointers with Forward and Backward Pass
 * ========================================================
 * Key Insight: Try every ending position and find the earliest start
 * 
 * Approach 1: Brute Force - O(n²)
 * 1. For each position i in s (as end of window)
 * 2. Try to form subsequence t backwards from i
 * 3. Find leftmost position j where s[j:i+1] contains t as subsequence
 * 4. Track minimum window
 * 
 * Approach 2: Optimized with Right and Left pointers - O(n*m)
 * 1. Use right pointer to find valid windows
 * 2. Use left pointer to contract and find minimum
 * 3. Maintain indices of characters in t within current window
 * 
 * Time Complexity: O(m*n) where m=len(s), n=len(t)
 * Space Complexity: O(1) no extra data structures
 */

@SuppressWarnings("unused")
public class MinimumWindowSubsequence {
    
    /**
     * Find minimum window substring containing t as subsequence
     * Approach: Two pointers with backward matching
     */
    public static String minWindow(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }
        
        int minLen = Integer.MAX_VALUE;
        int minStart = 0;
        
        // For each character in s, try it as the end of window
        for (int end = 0; end < s.length(); end++) {
            // Try to form subsequence t backwards from end
            int tIdx = t.length() - 1;
            
            for (int sIdx = end; sIdx >= 0 && tIdx >= 0; sIdx--) {
                if (s.charAt(sIdx) == t.charAt(tIdx)) {
                    tIdx--;
                }
            }
            
            // If we matched all of t
            if (tIdx == -1) {
                // We found subsequence in s[start:end+1]
                // tIdx is now -1, so start would be at sIdx + 1
                int start = 0;
                // Find where matching ended
                tIdx = t.length() - 1;
                for (int sIdx = end; sIdx >= 0 && tIdx >= 0; sIdx--) {
                    if (s.charAt(sIdx) == t.charAt(tIdx)) {
                        tIdx--;
                    }
                    if (tIdx == -1) {
                        start = sIdx;
                    }
                }
                
                int windowLen = end - start + 1;
                if (windowLen < minLen) {
                    minLen = windowLen;
                    minStart = start;
                }
            }
        }
        
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }
    
    /**
     * Better approach: Use two-pointer technique
     */
    public static String minWindow_TwoPointer(String s, String t) {
        int minLen = Integer.MAX_VALUE;
        int minStart = 0;
        int right = 0;
        
        for (right = 0; right < s.length(); right++) {
            // Check if s[0:right+1] contains t as subsequence
            int tIdx = 0;
            for (int sIdx = 0; sIdx <= right && tIdx < t.length(); sIdx++) {
                if (s.charAt(sIdx) == t.charAt(tIdx)) {
                    tIdx++;
                }
            }
            
            // If we found complete subsequence
            if (tIdx == t.length()) {
                // Now find the leftmost starting position
                int tIdx2 = t.length() - 1;
                for (int sIdx = right; sIdx >= 0 && tIdx2 >= 0; sIdx--) {
                    if (s.charAt(sIdx) == t.charAt(tIdx2)) {
                        tIdx2--;
                    }
                    
                    if (tIdx2 == -1) {
                        int start = sIdx;
                        int windowLen = right - start + 1;
                        if (windowLen < minLen) {
                            minLen = windowLen;
                            minStart = start;
                        }
                        break;
                    }
                }
            }
        }
        
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }
    
    public static void main(String[] args) {
        System.out.println("=== Minimum Window Subsequence ===\n");
        
        String[][] testCases = {
            {"timmisajdgasdin", "asd"},
            {"dcbefebce", "cdc"},
            {"abc", "ad"},
            {"aa", "a"},
            {"a", "a"},
            {"abcbab", "ab"}
        };
        
        System.out.println(String.format("%-25s | %-10s | %-15s", 
            "String", "Target", "Result"));
        System.out.println("-".repeat(55));
        
        for (String[] test : testCases) {
            String result = minWindow(test[0], test[1]);
            System.out.println(String.format("%-25s | %-10s | %-15s", 
                test[0], test[1], result));
        }
        
        System.out.println("\n" + "=".repeat(80));
        System.out.println("DETAILED EXAMPLE 1: s=\"timmisajdgasdin\", t=\"asd\"");
        System.out.println("-".repeat(80));
        
        String s = "timmisajdgasdin";
        String t = "asd";
        
        System.out.println("\nString: " + s);
        System.out.println("Target: " + t);
        System.out.println("\nFind minimum window containing 'a', 's', 'd' as subsequence");
        System.out.println("(in that order, but not necessarily contiguous)");
        
        System.out.println("\nIndex analysis:");
        System.out.println("a: positions [5, 10]");
        System.out.println("s: positions [7, 10, 13]");
        System.out.println("d: positions [9, 14]");
        
        System.out.println("\nPossible windows containing a-s-d subsequence:");
        System.out.println("\"asddin\" [7:12]: a[10], s[10], d[12] - but a comes after s!");
        System.out.println("\"adjgas\" [5:11]: a[5], d[9], s[10] - need to find actual");
        System.out.println("Let's trace through:");
        
        System.out.println("\nFor each end position, find leftmost start:");
        System.out.println("end=14, char='n': need a,s,d");
        System.out.println("  Going backward from 14:");
        System.out.println("  'n'(14) != 'd', 'd'(14) at index 14? YES");
        System.out.println("  Need s before d: found at 13");
        System.out.println("  Need a before s: found at 10");
        System.out.println("  Window [10:14]: \"sdin\" - NO, 'a' not there");
        
        String result = minWindow(s, t);
        System.out.println("\nResult: \"" + result + "\"");
        
        System.out.println("\n" + "=".repeat(80));
        System.out.println("DETAILED EXAMPLE 2: s=\"dcbefebce\", t=\"cdc\"");
        System.out.println("-".repeat(80));
        
        s = "dcbefebce";
        t = "cdc";
        
        System.out.println("\nString: " + s);
        System.out.println("Target: " + t);
        System.out.println("\nNeed: 'c' then 'd' then 'c' (as subsequence)");
        
        System.out.println("\nCharacter positions:");
        System.out.println("c: positions [0, 6, 8]");
        System.out.println("d: positions [1, 5]");
        
        System.out.println("\nTrying windows:");
        System.out.println("[0,1]: \"dc\" - has c[0], d[1], but need another c ✗");
        System.out.println("[0,5]: \"dcbefe\" - has c[0], d[1,5], need c after d");
        System.out.println("       Looking for c after d[1]: found c[6]? No, d[5]");
        System.out.println("       Actually: c[0], d[5], then c[6] or c[8]");
        System.out.println("       Window [0,6]: \"dcbefec\" ✓");
        System.out.println("       Or [0,8]: \"dcbefebce\" ✓");
        System.out.println("[2,8]: \"befebce\" - has c[6], no d... ✗");
        System.out.println("[1,8]: \"cbefebce\" - has c[0]? No, c[6,8]");
        
        result = minWindow(s, t);
        System.out.println("\nResult: \"" + result + "\"");
        
        System.out.println("\n" + "=".repeat(80));
        System.out.println("ALGORITHM EXPLANATION:");
        System.out.println("-".repeat(80));
        System.out.println("Key Insight: For each end position, find best start");
        System.out.println("\n1. For end = 0 to len(s)-1:");
        System.out.println("   - Try to match entire subsequence t in s[0:end+1]");
        System.out.println("   - If match found, find leftmost position that works");
        System.out.println("   - Do this by matching t backwards from end");
        System.out.println("\n2. Matching backwards (greedy):");
        System.out.println("   - Start from end of s and end of t");
        System.out.println("   - Match characters from right to left");
        System.out.println("   - Record leftmost position where all of t matched");
        System.out.println("\n3. Track minimum window found");
        
        System.out.println("\n" + "=".repeat(80));
        System.out.println("DIFFERENCE FROM MINIMUM WINDOW SUBSTRING:");
        System.out.println("-".repeat(80));
        System.out.println("Substring problem:");
        System.out.println("  - Need ALL characters of t to appear contiguously");
        System.out.println("  - And in the exact frequency they appear in t");
        System.out.println("  - Example: t=\"ABC\" needs 1 A, 1 B, 1 C");
        System.out.println("\nSubsequence problem:");
        System.out.println("  - Characters of t can be anywhere in window");
        System.out.println("  - Just need to appear in order");
        System.out.println("  - Can have extra characters between them");
        System.out.println("  - Example: t=\"ABC\" can be found in \"AXBXCX\"");
        
        System.out.println("\n" + "=".repeat(80));
        System.out.println("KEY POINTS:");
        System.out.println("-".repeat(80));
        System.out.println("1. Window is substring (contiguous) but content is subsequence");
        System.out.println("2. For each ending position, find best starting position");
        System.out.println("3. Use greedy backward matching to find start");
        System.out.println("4. Match from end to find leftmost position");
        System.out.println("5. Time: O(m*n) where m=len(s), n=len(t)");
        System.out.println("6. Space: O(1) only using pointers");
        System.out.println("7. Result is contiguous substring, not subsequence");
    }
}
