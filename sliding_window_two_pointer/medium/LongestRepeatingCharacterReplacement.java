package sliding_window_two_pointer.medium;

import java.util.*;

/**
 * Longest Repeating Character Replacement
 * 
 * Problem: Given a string s and integer k, you can choose any character and
 *          replace ALL occurrences of that character with k other characters.
 *          Find the length of longest substring with repeating characters.
 * 
 * Example: s="ABAB", k=2 → 4 (replace all A or B, get "AAAA" or "BBBB")
 *          s="AABABBA", k=1 → 4 (position [0,3]="AABA" replace B→A)
 *          s="AAAB", k=0 → 3 ("AAA")
 *          s="ABCDE", k=1 → 2 (replace any to match neighbor)
 * 
 * Algorithm: Sliding Window with Maximum Frequency
 * ================================================
 * 1. Use sliding window and frequency map
 * 2. Track maximum frequency of any character in window
 * 3. Valid condition: (window_size - max_freq) <= k
 *    This means: characters that are NOT the most frequent <= k
 * 4. If invalid: shrink window from left
 * 5. Track maximum valid window size
 * 
 * Key Insight: We can replace at most k characters to make them all same
 *              So if we have window of size L with max_freq f:
 *              We need to replace (L - f) characters
 *              If (L - f) <= k, window is valid
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(26) = O(1) for alphabet size
 */

public class LongestRepeatingCharacterReplacement {
    
    /**
     * Find longest substring with same character after k replacements
     */
    public static int characterReplacement(String s, int k) {
        Map<Character, Integer> charCount = new HashMap<>();
        int left = 0;
        int maxFreq = 0;  // max frequency of any character in current window
        int maxLen = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
            
            // Update max frequency
            maxFreq = Math.max(maxFreq, charCount.get(c));
            
            // Check if window is valid
            // Window size - max frequency = characters that need replacement
            int charsToReplace = (right - left + 1) - maxFreq;
            
            while (charsToReplace > k) {
                // Shrink window
                char leftChar = s.charAt(left);
                charCount.put(leftChar, charCount.get(leftChar) - 1);
                if (charCount.get(leftChar) == 0) {
                    charCount.remove(leftChar);
                }
                left++;
                
                // Recalculate max frequency after shrinking
                maxFreq = 0;
                for (int freq : charCount.values()) {
                    maxFreq = Math.max(maxFreq, freq);
                }
                charsToReplace = (right - left + 1) - maxFreq;
            }
            
            maxLen = Math.max(maxLen, right - left + 1);
        }
        
        return maxLen;
    }
    
    public static void main(String[] args) {
        System.out.println("=== Longest Repeating Character Replacement ===\n");
        
        String[][] testCases = {
            {"ABAB", "2"},
            {"AABABBA", "1"},
            {"AAAB", "0"},
            {"ABCDE", "1"},
            {"AAAA", "2"},
            {"A", "0"}
        };
        
        System.out.println(String.format("%-20s | %-5s | %-10s", "String", "k", "Max Length"));
        System.out.println("-".repeat(40));
        
        for (String[] test : testCases) {
            String s = test[0];
            int k = Integer.parseInt(test[1]);
            int result = characterReplacement(s, k);
            System.out.println(String.format("%-20s | %-5d | %-10d", s, k, result));
        }
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("DETAILED EXAMPLE 1: s=\"AABABBA\", k=1");
        System.out.println("-".repeat(60));
        
        String s = "AABABBA";
        int k = 1;
        
        System.out.println("\nArray: " + s + " (0-indexed)");
        System.out.println("\nKey Insight:");
        System.out.println("- Replace 1 character (k=1)");
        System.out.println("- Find substring where non-majority chars ≤ 1");
        System.out.println("\nSliding window trace:\n");
        System.out.println(String.format("%-6s | %-6s | %-10s | %-25s | %-15s | %-10s", 
            "Left", "Right", "Char", "Frequency", "MaxFreq", "Replacements"));
        System.out.println("-".repeat(75));
        
        Map<Character, Integer> charCount = new HashMap<>();
        int left = 0;
        int maxFreq = 0;
        int maxLen = 0;
        int maxLeft = 0, maxRight = -1;
        
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
            maxFreq = Math.max(maxFreq, charCount.get(c));
            
            System.out.print(String.format("%-6d | %-6d | %-10s | ", left, right, c));
            
            int charsToReplace = (right - left + 1) - maxFreq;
            System.out.print(String.format("%-25s | %-15d | %-10d", charCount, maxFreq, charsToReplace));
            
            while (charsToReplace > k) {
                char leftChar = s.charAt(left);
                charCount.put(leftChar, charCount.get(leftChar) - 1);
                if (charCount.get(leftChar) == 0) {
                    charCount.remove(leftChar);
                }
                left++;
                
                maxFreq = 0;
                for (int freq : charCount.values()) {
                    maxFreq = Math.max(maxFreq, freq);
                }
                charsToReplace = (right - left + 1) - maxFreq;
                System.out.print(" [SHRINK to " + left + "]");
            }
            
            if ((right - left + 1) > maxLen) {
                maxLen = right - left + 1;
                maxLeft = left;
                maxRight = right;
            }
            
            System.out.println();
        }
        
        System.out.println("\nMax window: [" + maxLeft + ", " + maxRight + "] = \"" + 
                         s.substring(maxLeft, maxRight + 1) + "\"");
        System.out.println("Length: " + maxLen);
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("DETAILED EXAMPLE 2: s=\"ABAB\", k=2");
        System.out.println("-".repeat(60));
        
        s = "ABAB";
        k = 2;
        System.out.println("\nString: " + s);
        System.out.println("Replacements allowed: " + k);
        System.out.println("\nAnalysis:");
        System.out.println("[0,3] = \"ABAB\"");
        System.out.println("  Frequencies: {A→2, B→2}");
        System.out.println("  MaxFreq = 2");
        System.out.println("  ToReplace = 4 - 2 = 2");
        System.out.println("  Can we replace 2 chars with 2 allowed? YES!");
        System.out.println("  Replace all B with A → \"AAAA\" ✓");
        System.out.println("\nResult: 4");
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("ALGORITHM EXPLANATION:");
        System.out.println("-".repeat(60));
        System.out.println("1. Use sliding window [left, right]");
        System.out.println("2. Maintain frequency map of characters in window");
        System.out.println("3. Track maxFreq = highest frequency in current window");
        System.out.println("4. Characters to replace = window_size - maxFreq");
        System.out.println("   (These are all non-majority characters)");
        System.out.println("5. Valid condition: charsToReplace ≤ k");
        System.out.println("6. If invalid: shrink window from left");
        System.out.println("7. Track maximum valid window size");
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("KEY POINTS:");
        System.out.println("-".repeat(60));
        System.out.println("1. Focus on MAJORITY character (most frequent)");
        System.out.println("2. Only need to replace minority characters");
        System.out.println("3. Formula: replacements = window_size - maxFreq");
        System.out.println("4. If formula > k, window violates constraint");
        System.out.println("5. Time: O(n) - each element added/removed once");
        System.out.println("6. Space: O(26) for English letters = O(1)");
        System.out.println("7. Don't reset maxFreq naively (optimization opportunity)");
    }
}
