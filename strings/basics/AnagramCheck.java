package strings.basics;

import java.util.HashMap;
import java.util.Arrays;

public class AnagramCheck {

    /*
    =====================================================================================
    PROBLEM: CHECK IF TWO STRINGS ARE ANAGRAM OF EACH OTHER
    -------------------------------------------------------------------------------------
    Two strings are anagrams if they contain the same characters with same frequencies 
    in any different order. For example, "listen" and "silent" are anagrams as both 
    have same character frequencies. This is a classic string manipulation problem that 
    tests understanding of character frequency counting. Multiple approaches exist: 
    sorting characters, using HashMap for frequencies, or character count arrays. All 
    approaches have different trade-offs between time and space complexity.

    Time Complexity: O(n log n) for sorting approach, O(n) for frequency counting
    Space Complexity: O(1) for fixed 26 lowercase letters, O(n) for HashMap

    Example:
    Input:  s1 = "listen", s2 = "silent"
    Output: true (both strings contain same characters with same frequencies)
    =====================================================================================
    */
    
    /**
     * Checks if two strings are anagrams using character frequency counting with HashMap.
     * Creates frequency map of s1 and verifies each character from s2 exists with
     * matching frequency. More readable approach, especially for Unicode characters.
     * 
     * @param s1 first string
     * @param s2 second string
     * @return true if s2 is anagram of s1, false otherwise
     */
    public static boolean isAnagram(String s1, String s2) {
        // Different lengths cannot be anagrams
        if (s1.length() != s2.length()) {
            return false;
        }
        
        // Build frequency map for first string
        HashMap<Character, Integer> charCount = new HashMap<>();
        for (char c : s1.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }
        
        // Verify each character in second string
        for (char c : s2.toCharArray()) {
            if (!charCount.containsKey(c)) {
                return false;  // Character not found
            }
            charCount.put(c, charCount.get(c) - 1);
            if (charCount.get(c) < 0) {
                return false;  // More occurrences in s2 than s1
            }
        }
        
        return true;
    }
    
    /**
     * Checks if two strings are anagrams using sorting approach.
     * Converts strings to character arrays, sorts them, and compares.
     * Simple and elegant, but O(n log n) time complexity.
     * 
     * @param s1 first string
     * @param s2 second string
     * @return true if anagrams, false otherwise
     */
    public static boolean isAnagramSorting(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        
        char[] arr1 = s1.toCharArray();
        char[] arr2 = s2.toCharArray();
        
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        
        return Arrays.equals(arr1, arr2);
    }
    
    /**
     * Checks if two strings are anagrams using fixed-size character count array.
     * Most efficient for lowercase English letters. Uses array instead of HashMap
     * for better space and time performance with limited character set.
     * 
     * @param s1 first string
     * @param s2 second string
     * @return true if anagrams, false otherwise
     */
    public static boolean isAnagramArray(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        
        // Array to store character frequencies (26 lowercase letters)
        int[] charCount = new int[26];
        
        // Count characters from first string
        for (char c : s1.toCharArray()) {
            charCount[c - 'a']++;
        }
        
        // Decrement for characters in second string
        for (char c : s2.toCharArray()) {
            charCount[c - 'a']--;
        }
        
        // Check if all counts are zero
        for (int count : charCount) {
            if (count != 0) {
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Checks anagrams considering only alphanumeric characters and case-insensitive.
     * Ignores spaces and special characters. Useful for real-world scenarios
     * where input strings might have punctuation.
     * 
     * @param s1 first string
     * @param s2 second string
     * @return true if anagrams (ignoring non-alphanumeric and case)
     */
    public static boolean isAnagramIgnoreCase(String s1, String s2) {
        String clean1 = s1.toLowerCase().replaceAll("[^a-z0-9]", "");
        String clean2 = s2.toLowerCase().replaceAll("[^a-z0-9]", "");
        
        return isAnagramArray(clean1, clean2);
    }
    
    /**
     * Helper function to get character frequency map of a string.
     * Returns HashMap with each character and its count. Useful for
     * analyzing character distribution or other string problems.
     * 
     * @param str input string
     * @return HashMap with character frequencies
     */
    public static HashMap<Character, Integer> getCharacterFrequency(String str) {
        HashMap<Character, Integer> freq = new HashMap<>();
        for (char c : str.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }
        return freq;
    }
    
    /**
     * Checks if s2 is anagram of s1 by comparing frequency maps.
     * Useful when frequency information is already available.
     * 
     * @param s1 first string
     * @param s2 second string
     * @return true if frequency maps are identical
     */
    public static boolean isAnagramUsingFrequency(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        
        HashMap<Character, Integer> freq1 = getCharacterFrequency(s1);
        HashMap<Character, Integer> freq2 = getCharacterFrequency(s2);
        
        return freq1.equals(freq2);
    }
    
    public static void main(String[] args) {
        System.out.println("Is \"listen\" anagram of \"silent\": " + 
            isAnagram("listen", "silent"));                      // true
        System.out.println("Is \"hello\" anagram of \"world\": " + 
            isAnagram("hello", "world"));                        // false
        System.out.println("Is \"evil\" anagram of \"vile\": " + 
            isAnagram("evil", "vile"));                          // true
        System.out.println("Is \"anagram\" anagram of \"nagaram\": " + 
            isAnagram("anagram", "nagaram"));                    // true
        System.out.println("Is \"abc\" anagram of \"def\": " + 
            isAnagram("abc", "def"));                            // false
        
        System.out.println("\nSorting approach:");
        System.out.println("Is \"dormitory\" anagram of \"dirtyroom\": " + 
            isAnagramSorting("dormitory", "dirtyroom"));         // true
        
        System.out.println("\nArray approach:");
        System.out.println("Is \"aabbcc\" anagram of \"abcabc\": " + 
            isAnagramArray("aabbcc", "abcabc"));                 // true
        
        System.out.println("\nIgnore case approach:");
        System.out.println("Is \"Tea\" anagram of \"Eat\": " + 
            isAnagramIgnoreCase("Tea", "Eat"));                  // true
        System.out.println("Is \"Listen!\" anagram of \"Silent?\": " + 
            isAnagramIgnoreCase("Listen!", "Silent?"));          // true
        
        System.out.println("\nFrequency of 'hello': " + 
            getCharacterFrequency("hello"));
    }
}
