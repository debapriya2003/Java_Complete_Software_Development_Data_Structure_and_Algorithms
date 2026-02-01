package sliding_window_two_pointer.hard;

import java.util.*;

/**
 * Longest Substring with At Most K Distinct Characters
 * 
 * Problem: Given a string s and integer k, find the length of the longest
 *          substring that contains at most k distinct characters.
 * 
 * Example: s="eceba", k=2 → 4 ("eceb" has 'e','c','b')
 *          s="aa", k=1 → 2
 *          s="abcabcabcabc", k=2 → 4 ("abab" or "bcbc")
 *          s="a", k=0 → 0
 *          s="abcdefg", k=3 → 3 ("abc")
 * 
 * Algorithm: Sliding Window with Character Frequency Map
 * =======================================================
 * 1. Use sliding window [left, right]
 * 2. Maintain HashMap of character frequencies in window
 * 3. Expand window: add character at right
 * 4. If distinct count > k: shrink from left until valid
 * 5. Track maximum window size
 * 
 * Key Operations:
 * - Adding character: freq[c]++
 * - Removing character: freq[c]--, if 0 remove from map
 * - Valid condition: hashmap.size() <= k
 * 
 * Time Complexity: O(n) - each character added/removed once
 * Space Complexity: O(min(n, k)) - at most k characters in hashmap
 */

public class LongestSubstringWithAtMostKDistinctCharacters {
    
    /**
     * Find longest substring with at most k distinct characters
     */
    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (k == 0 || s.length() == 0) {
            return 0;
        }
        
        Map<Character, Integer> charFreq = new HashMap<>();
        int left = 0;
        int maxLen = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            charFreq.put(c, charFreq.getOrDefault(c, 0) + 1);
            
            // Shrink window if more than k distinct characters
            while (charFreq.size() > k) {
                char leftChar = s.charAt(left);
                charFreq.put(leftChar, charFreq.get(leftChar) - 1);
                if (charFreq.get(leftChar) == 0) {
                    charFreq.remove(leftChar);
                }
                left++;
            }
            
            maxLen = Math.max(maxLen, right - left + 1);
        }
        
        return maxLen;
    }
    
    public static void main(String[] args) {
        System.out.println("=== Longest Substring with At Most K Distinct Characters ===\n");
        
        String[][] testCases = {
            {"eceba", "2"},
            {"aa", "1"},
            {"abcabcabcabc", "2"},
            {"a", "0"},
            {"abcdefg", "3"},
            {"ababdaaab", "2"}
        };
        
        System.out.println(String.format("%-25s | %-5s | %-10s", 
            "String", "k", "Max Length"));
        System.out.println("-".repeat(45));
        
        for (String[] test : testCases) {
            String s = test[0];
            int k = Integer.parseInt(test[1]);
            int result = lengthOfLongestSubstringKDistinct(s, k);
            System.out.println(String.format("%-25s | %-5d | %-10d", s, k, result));
        }
        
        System.out.println("\n" + "=".repeat(75));
        System.out.println("DETAILED EXAMPLE 1: s=\"eceba\", k=2");
        System.out.println("-".repeat(75));
        
        String s = "eceba";
        int k = 2;
        
        System.out.println("\nString: " + s);
        System.out.println("k = " + k);
        System.out.println("\nSliding window trace:\n");
        
        Map<Character, Integer> charFreq = new HashMap<>();
        int left = 0;
        int maxLen = 0;
        int maxLeft = 0, maxRight = -1;
        
        System.out.println(String.format("%-8s | %-8s | %-6s | %-20s | %-15s | %-10s", 
            "Left", "Right", "Char", "Frequency Map", "Distinct", "Window"));
        System.out.println("-".repeat(80));
        
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            charFreq.put(c, charFreq.getOrDefault(c, 0) + 1);
            
            System.out.print(String.format("%-8d | %-8d | %-6s | ", left, right, c));
            
            while (charFreq.size() > k) {
                char leftChar = s.charAt(left);
                charFreq.put(leftChar, charFreq.get(leftChar) - 1);
                if (charFreq.get(leftChar) == 0) {
                    charFreq.remove(leftChar);
                }
                left++;
            }
            
            int windowLen = right - left + 1;
            if (windowLen > maxLen) {
                maxLen = windowLen;
                maxLeft = left;
                maxRight = right;
            }
            
            System.out.println(String.format("%-20s | %-15d | %-10s", 
                charFreq, charFreq.size(), 
                "\"" + s.substring(left, right + 1) + "\""));
        }
        
        System.out.println("\nMax window: \"" + s.substring(maxLeft, maxRight + 1) + 
                         "\" at [" + maxLeft + ", " + maxRight + "]");
        System.out.println("Length: " + maxLen);
        
        System.out.println("\n" + "=".repeat(75));
        System.out.println("STEP-BY-STEP BREAKDOWN:");
        System.out.println("-".repeat(75));
        System.out.println("Index:     0(e)  1(c)  2(e)  3(b)  4(a)");
        System.out.println("\nStep 1: right=0, char='e'");
        System.out.println("  freq={e:1}, size=1 ≤ k=2 ✓");
        System.out.println("  window=\"e\", len=1");
        
        System.out.println("\nStep 2: right=1, char='c'");
        System.out.println("  freq={e:1, c:1}, size=2 ≤ k=2 ✓");
        System.out.println("  window=\"ec\", len=2");
        
        System.out.println("\nStep 3: right=2, char='e'");
        System.out.println("  freq={e:2, c:1}, size=2 ≤ k=2 ✓");
        System.out.println("  window=\"ece\", len=3");
        
        System.out.println("\nStep 4: right=3, char='b'");
        System.out.println("  freq={e:2, c:1, b:1}, size=3 > k=2 ✗");
        System.out.println("  Need to shrink: remove left[0]='e'");
        System.out.println("  freq={e:1, c:1, b:1}, size=3 > k=2 ✗");
        System.out.println("  Need to shrink: remove left[1]='c'");
        System.out.println("  freq={e:1, b:1}, size=2 ≤ k=2 ✓");
        System.out.println("  window=\"eb\", len=2");
        
        System.out.println("\nStep 5: right=4, char='a'");
        System.out.println("  freq={e:1, b:1, a:1}, size=3 > k=2 ✗");
        System.out.println("  Need to shrink: remove left[2]='e'");
        System.out.println("  freq={b:1, a:1}, size=2 ≤ k=2 ✓");
        System.out.println("  window=\"ba\", len=2");
        
        System.out.println("\nMax length found: 3 (\"ece\")");
        
        System.out.println("\n" + "=".repeat(75));
        System.out.println("DETAILED EXAMPLE 2: s=\"ababdaaab\", k=2");
        System.out.println("-".repeat(75));
        
        s = "ababdaaab";
        k = 2;
        
        System.out.println("\nString: " + s);
        System.out.println("k = " + k);
        System.out.println("Need: substrings with at most 2 distinct chars");
        
        System.out.println("\nKey substrings:");
        System.out.println("\"abab\" (0-3): {a,b} - 2 distinct ✓ len=4");
        System.out.println("\"bab\" (1-3): {a,b} - 2 distinct ✓ len=3");
        System.out.println("\"abd\" (2-4): {a,b,d} - 3 distinct ✗");
        System.out.println("\"da\" (4-5): {d,a} - 2 distinct ✓ len=2");
        System.out.println("\"aaa\" (5-7): {a} - 1 distinct ✓ len=3");
        System.out.println("\"aaab\" (5-8): {a,b} - 2 distinct ✓ len=4");
        
        int result = lengthOfLongestSubstringKDistinct(s, k);
        System.out.println("\nMax length: " + result);
        
        System.out.println("\n" + "=".repeat(75));
        System.out.println("ALGORITHM EXPLANATION:");
        System.out.println("-".repeat(75));
        System.out.println("1. Use sliding window with two pointers");
        System.out.println("2. Maintain HashMap of character frequencies");
        System.out.println("3. Expand window by moving right pointer");
        System.out.println("4. Add character to frequency map");
        System.out.println("5. While distinct count > k:");
        System.out.println("   - Decrement frequency of left character");
        System.out.println("   - Remove if frequency becomes 0");
        System.out.println("   - Move left pointer forward");
        System.out.println("6. Track maximum valid window size");
        System.out.println("7. Each element processed exactly twice (add/remove)");
        
        System.out.println("\n" + "=".repeat(75));
        System.out.println("KEY POINTS:");
        System.out.println("-".repeat(75));
        System.out.println("1. HashMap tracks character frequencies");
        System.out.println("2. Map.size() tells us distinct character count");
        System.out.println("3. Valid window: map.size() ≤ k");
        System.out.println("4. Shrink when we exceed k distinct chars");
        System.out.println("5. Time: O(n) - each char added/removed once");
        System.out.println("6. Space: O(min(n, alphabet_size))");
        System.out.println("7. Works for any character set (not just lowercase)");
        System.out.println("8. Edge case: k=0 → return 0 (can't have 0 distinct)");
    }
}
