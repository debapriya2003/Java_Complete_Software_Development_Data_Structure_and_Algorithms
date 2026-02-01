package sliding_window_two_pointer.hard;

import java.util.*;

/**
 * Minimum Window Substring
 * 
 * Problem: Given two strings s and t of lengths m and n respectively,
 *          return the minimum window substring of s which will contain
 *          all the characters in t. If no such window exists, return empty string.
 *          If multiple answers exist, return the one with smallest starting index.
 * 
 * Example: s="ADOBECODEBANC", t="ABC" → "BANC"
 *          s="a", t="a" → "a"
 *          s="a", t="aa" → "" (impossible)
 *          s="ab", t="b" → "b"
 * 
 * Algorithm: Sliding Window with Character Frequency
 * ===================================================
 * 1. Create frequency map of characters in target string t
 * 2. Use sliding window [left, right] on source string s
 * 3. Expand window: move right until we have all required characters
 * 4. Contract window: move left to find minimum length window
 * 5. Track minimum window found
 * 
 * Key Concepts:
 * - required: number of unique characters in t that must be in window
 * - formed: number of unique characters in window with desired frequency
 * - When formed == required, window is valid
 * 
 * Time Complexity: O(m + n) where m=len(s), n=len(t)
 * Space Complexity: O(52) = O(1) for hashmap (26 lowercase + 26 uppercase)
 */

public class MinimumWindowSubstring {
    
    /**
     * Find minimum window substring containing all characters of t
     */
    public static String minWindow(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }
        
        // Dictionary which keeps the count of all unique characters in t
        Map<Character, Integer> dictT = new HashMap<>();
        for (char c : t.toCharArray()) {
            dictT.put(c, dictT.getOrDefault(c, 0) + 1);
        }
        
        // Number of unique characters in t that need to be present in the window
        int required = dictT.size();
        
        // Left and right pointers
        int left = 0, right = 0;
        
        // formed is used to keep track of how many unique characters in t
        // are present in the current window in their desired frequency
        int formed = 0;
        
        // Dictionary which keeps count of all unique characters in current window
        Map<Character, Integer> windowCounts = new HashMap<>();
        
        // ans tuple of the form (window length, left, right)
        int[] ans = {Integer.MAX_VALUE, 0, 0};
        
        while (right < s.length()) {
            // Add one character from the right to the window
            char c = s.charAt(right);
            windowCounts.put(c, windowCounts.getOrDefault(c, 0) + 1);
            
            // If the frequency of the current character added equals to the
            // desired count in t then increment the formed count
            if (dictT.containsKey(c) && windowCounts.get(c).intValue() == dictT.get(c).intValue()) {
                formed++;
            }
            
            // Try to contract the window until the point where it ceases to be 'desirable'
            while (left <= right && formed == required) {
                char leftChar = s.charAt(left);
                
                // Save the smallest window
                if (right - left + 1 < ans[0]) {
                    ans[0] = right - left + 1;
                    ans[1] = left;
                    ans[2] = right;
                }
                
                // The character at the left pointer is no longer
                // a part of the window
                windowCounts.put(leftChar, windowCounts.get(leftChar) - 1);
                if (dictT.containsKey(leftChar) && 
                    windowCounts.get(leftChar).intValue() < dictT.get(leftChar).intValue()) {
                    formed--;
                }
                
                // Move the left pointer ahead for the next iteration
                left++;
            }
            
            // Keep expanding the window by moving right pointer
            right++;
        }
        
        // Return the smallest window or empty string
        return ans[0] == Integer.MAX_VALUE ? "" : s.substring(ans[1], ans[2] + 1);
    }
    
    public static void main(String[] args) {
        System.out.println("=== Minimum Window Substring ===\n");
        
        String[][] testCases = {
            {"ADOBECODEBANC", "ABC"},
            {"a", "a"},
            {"a", "aa"},
            {"ab", "b"},
            {"aaaaaaaaaaaabbbbbcdd", "abcdd"}
        };
        
        System.out.println(String.format("%-30s | %-10s | %-15s", 
            "String", "Target", "Result"));
        System.out.println("-".repeat(60));
        
        for (String[] test : testCases) {
            String result = minWindow(test[0], test[1]);
            System.out.println(String.format("%-30s | %-10s | %-15s", 
                test[0], test[1], result));
        }
        
        System.out.println("\n" + "=".repeat(80));
        System.out.println("DETAILED EXAMPLE 1: s=\"ADOBECODEBANC\", t=\"ABC\"");
        System.out.println("-".repeat(80));
        
        String s = "ADOBECODEBANC";
        String t = "ABC";
        
        System.out.println("\nString: " + s);
        System.out.println("Target: " + t);
        System.out.println("\nRequired characters: A(1), B(1), C(1)");
        System.out.println("\nSliding window process:\n");
        
        Map<Character, Integer> dictT = new HashMap<>();
        for (char c : t.toCharArray()) {
            dictT.put(c, dictT.getOrDefault(c, 0) + 1);
        }
        
        int required = dictT.size();
        int left = 0, right = 0;
        int formed = 0;
        Map<Character, Integer> windowCounts = new HashMap<>();
        
        String minWindow = "";
        int minLen = Integer.MAX_VALUE;
        
        System.out.println(String.format("%-6s | %-6s | %-6s | %-20s | %-10s | %-20s", 
            "Left", "Right", "Char", "Window Count", "Formed", "Window"));
        System.out.println("-".repeat(80));
        
        while (right < s.length()) {
            char c = s.charAt(right);
            windowCounts.put(c, windowCounts.getOrDefault(c, 0) + 1);
            
            if (dictT.containsKey(c) && 
                windowCounts.get(c).intValue() == dictT.get(c).intValue()) {
                formed++;
            }
            
            System.out.print(String.format("%-6d | %-6d | %-6s | ", left, right, c));
            
            while (left <= right && formed == required) {
                char leftChar = s.charAt(left);
                
                String window = s.substring(left, right + 1);
                System.out.print(String.format("%-20s | %-10d | %-20s", 
                    windowCounts, formed, "\"" + window + "\""));
                
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minWindow = window;
                    System.out.print(" ← NEW MIN");
                }
                System.out.println();
                
                windowCounts.put(leftChar, windowCounts.get(leftChar) - 1);
                if (dictT.containsKey(leftChar) && 
                    windowCounts.get(leftChar).intValue() < dictT.get(leftChar).intValue()) {
                    formed--;
                }
                
                left++;
                
                System.out.print(String.format("%-6d | %-6d | %-6s | ", left, right, "-"));
            }
            
            if (!(left <= right && formed == required)) {
                System.out.println(String.format("%-20s | %-10d | (expanding)", 
                    windowCounts, formed));
            }
            
            right++;
        }
        
        System.out.println("\nMinimum window found: \"" + minWindow + "\"");
        System.out.println("Length: " + minLen);
        
        System.out.println("\n" + "=".repeat(80));
        System.out.println("STEP-BY-STEP BREAKDOWN:");
        System.out.println("-".repeat(80));
        
        System.out.println("Index:  0 1 2 3 4 5 6 7 8 9 10 11 12");
        System.out.println("Char:   A D O B E C O D E B A  N  C");
        System.out.println("\nPhase 1: Expand window to find first valid window");
        System.out.println("  [0,8]: \"ADOBECODE\" has all A,B,C ✓ (len=9)");
        System.out.println("\nPhase 2: Contract from left to minimize");
        System.out.println("  [3,8]: \"BECODE\" missing A ✗");
        System.out.println("  Go back to [2,8]: \"OBECODE\" has all ✓ (len=7)");
        System.out.println("\nPhase 3: Continue expanding and contracting");
        System.out.println("  Eventually find [9,11]: \"BANC\" (len=4)");
        System.out.println("\nPhase 4: No more valid windows smaller than 4");
        System.out.println("  Answer: \"BANC\"");
        
        System.out.println("\n" + "=".repeat(80));
        System.out.println("ALGORITHM EXPLANATION:");
        System.out.println("-".repeat(80));
        System.out.println("1. Build frequency map of target string t");
        System.out.println("2. Maintain 'required' = number of unique chars in t");
        System.out.println("3. Use two pointers (left, right) for sliding window");
        System.out.println("4. Expand: move right, add character to window");
        System.out.println("5. If character matches target frequency, increment 'formed'");
        System.out.println("6. Contract: when formed == required (valid window):");
        System.out.println("   - Record this window if smaller than best");
        System.out.println("   - Move left to shrink and find even smaller window");
        System.out.println("7. Return smallest valid window");
        
        System.out.println("\n" + "=".repeat(80));
        System.out.println("KEY POINTS:");
        System.out.println("-".repeat(80));
        System.out.println("1. 'formed' tracks how many unique chars have correct count");
        System.out.println("2. Window is valid only when formed == required");
        System.out.println("3. Two-phase: expand to make valid, then contract to minimize");
        System.out.println("4. Time: O(m + n) - each char processed at most twice");
        System.out.println("5. Space: O(52) = O(1) for character frequency maps");
        System.out.println("6. Window can contain extra characters (only counts those in t)");
        System.out.println("7. Edge case: if no valid window exists, return empty string");
    }
}
