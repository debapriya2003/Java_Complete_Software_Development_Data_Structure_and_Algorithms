package sliding_window_two_pointer.medium;

import java.util.*;

/**
 * Longest Substring Without Repeating Characters
 * 
 * Problem: Find the length of longest substring without repeating characters.
 * 
 * Example: "abcabcbb" → 3 ("abc")
 *          "bbbbb" → 1 ("b")
 *          "pwwkew" → 3 ("wke")
 *          "" → 0
 * 
 * Algorithm: Sliding Window with HashMap
 * ========================================
 * 1. Use two pointers: left and right
 * 2. Use HashMap to store last seen index of each character
 * 3. Expand window: move right pointer
 * 4. If character repeats:
 *    - Move left pointer to just after previous occurrence
 *    - Update HashMap with new index
 * 5. Track maximum length
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(min(n, 26)) for alphabet size
 */

public class LongestSubstringWithoutRepeatingCharacters {
    
    /**
     * Find longest substring without repeating characters
     */
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        Map<Character, Integer> charIndex = new HashMap<>();
        int maxLen = 0;
        int left = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            
            // If character exists and is within current window
            if (charIndex.containsKey(c) && charIndex.get(c) >= left) {
                // Move left pointer
                left = charIndex.get(c) + 1;
            }
            
            // Update character index
            charIndex.put(c, right);
            
            // Update max length
            maxLen = Math.max(maxLen, right - left + 1);
        }
        
        return maxLen;
    }
    
    public static void main(String[] args) {
        System.out.println("=== Longest Substring Without Repeating Characters ===\n");
        
        String[] testCases = {
            "abcabcbb",
            "bbbbb",
            "pwwkew",
            "au",
            "dvdf",
            ""
        };
        
        System.out.println(String.format("%-25s | %-25s | %-8s", 
            "String", "Longest Substring", "Length"));
        System.out.println("-".repeat(60));
        
        for (String s : testCases) {
            int len = lengthOfLongestSubstring(s);
            String display = s.isEmpty() ? "(empty)" : s;
            System.out.println(String.format("%-25s | %-25s | %-8d", 
                display, findSubstring(s, len), len));
        }
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("DETAILED EXAMPLE: \"abcabcbb\"");
        System.out.println("-".repeat(60));
        
        String s = "abcabcbb";
        Map<Character, Integer> charIndex = new HashMap<>();
        int maxLen = 0;
        int left = 0;
        int maxLeft = 0, maxRight = -1;
        
        System.out.println("\nSliding window trace:\n");
        System.out.println(String.format("%-8s | %-8s | %-8s | %-15s | %-30s | %-10s", 
            "Left", "Right", "Char", "Window", "HashMap", "MaxLen"));
        System.out.println("-".repeat(90));
        
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            
            System.out.print(String.format("%-8d | %-8d | %-8c | ", left, right, c));
            
            if (charIndex.containsKey(c) && charIndex.get(c) >= left) {
                int newLeft = charIndex.get(c) + 1;
                System.out.print(String.format("%-15s | ", 
                    s.substring(left, right) + "(" + c + ")"));
                left = newLeft;
            } else {
                System.out.print(String.format("%-15s | ", s.substring(left, right + 1)));
            }
            
            charIndex.put(c, right);
            
            int currentLen = right - left + 1;
            if (currentLen > maxLen) {
                maxLen = currentLen;
                maxLeft = left;
                maxRight = right;
            }
            
            System.out.println(String.format("%-30s | %-10d", 
                charIndex, maxLen));
        }
        
        System.out.println("\nLongest substring: \"" + s.substring(maxLeft, maxRight + 1) + "\"");
        System.out.println("Length: " + maxLen);
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("ANOTHER EXAMPLE: \"pwwkew\"");
        System.out.println("-".repeat(60));
        
        s = "pwwkew";
        System.out.println("\nString: " + s);
        
        charIndex = new HashMap<>();
        maxLen = 0;
        left = 0;
        List<String> substrings = new ArrayList<>();
        
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            
            if (charIndex.containsKey(c) && charIndex.get(c) >= left) {
                System.out.println("Index " + right + ": char '" + c + "' already seen at index " + 
                                 charIndex.get(c) + ", move left from " + left + " to " + (charIndex.get(c) + 1));
                left = charIndex.get(c) + 1;
            }
            
            charIndex.put(c, right);
            
            int currentLen = right - left + 1;
            if (currentLen > maxLen) {
                maxLen = currentLen;
                substrings.add(s.substring(left, right + 1));
            }
            
            System.out.println("  Window [" + left + ", " + right + "]: \"" + s.substring(left, right + 1) + 
                             "\", len = " + currentLen);
        }
        
        System.out.println("\nMax length: " + maxLen);
        System.out.println("Longest substrings: " + substrings);
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("ALGORITHM EXPLANATION:");
        System.out.println("-".repeat(60));
        System.out.println("1. Use sliding window with left and right pointers");
        System.out.println("2. HashMap stores last seen index of each character");
        System.out.println("3. Expand window: move right pointer");
        System.out.println("4. On duplicate:");
        System.out.println("   - If last occurrence is in current window:");
        System.out.println("   - Move left pointer to just after it");
        System.out.println("5. Track maximum length encountered");
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("KEY POINTS:");
        System.out.println("-".repeat(60));
        System.out.println("1. Condition: charIndex.get(c) >= left");
        System.out.println("   - Checks if duplicate is within current window");
        System.out.println("   - Ignores duplicates outside window");
        System.out.println("2. Move left to charIndex.get(c) + 1");
        System.out.println("   - Moves past the previous occurrence");
        System.out.println("3. Time: O(n) - each character visited twice");
        System.out.println("4. Space: O(min(n, 26)) - at most 26 lowercase letters");
    }
    
    private static String findSubstring(String s, int len) {
        if (len == 0) return "(empty)";
        Map<Character, Integer> charIndex = new HashMap<>();
        int maxLen = 0;
        int left = 0;
        int maxLeft = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (charIndex.containsKey(c) && charIndex.get(c) >= left) {
                left = charIndex.get(c) + 1;
            }
            charIndex.put(c, right);
            
            if (right - left + 1 > maxLen) {
                maxLen = right - left + 1;
                maxLeft = left;
            }
        }
        
        return maxLen == 0 ? "" : s.substring(maxLeft, maxLeft + maxLen);
    }
}
