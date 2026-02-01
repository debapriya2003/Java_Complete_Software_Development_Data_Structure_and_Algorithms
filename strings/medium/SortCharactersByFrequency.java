package strings.medium;

import java.util.*;

public class SortCharactersByFrequency {

    /*
    =====================================================================================
    PROBLEM: SORT CHARACTERS BY FREQUENCY
    -------------------------------------------------------------------------------------
    Given a string s, sort all characters in the string by their frequency in descending 
    order. Return the sorted string where characters with higher frequencies appear first. 
    For characters with the same frequency, order doesn't matter. For example, given 
    "tree", the output can be "eert" since 'e' appears twice and 't' and 'r' appear once. 
    This is a common problem testing knowledge of hash maps and sorting techniques.

    Time Complexity: O(n + k log k) where n is string length, k is unique characters
    Space Complexity: O(k) for storing character frequencies

    Example:
    Input:  s = "tree"
    Output: "eert" or "eetr" (e appears 2 times, t and r appear 1 time each)
    =====================================================================================
    */
    
    /**
     * Sorts characters by frequency using HashMap and max heap (PriorityQueue).
     * Build frequency map, then use max heap to extract characters in descending
     * frequency order. Constructs result string by appending characters based on frequencies.
     * Most efficient approach for this problem.
     * 
     * @param s input string
     * @return string sorted by character frequency in descending order
     */
    public static String frequencySort(String s) {
        // Build frequency map
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }
        
        // Use max heap to sort by frequency (descending)
        PriorityQueue<Character> maxHeap = new PriorityQueue<>(
            (a, b) -> freqMap.get(b) - freqMap.get(a)
        );
        maxHeap.addAll(freqMap.keySet());
        
        // Build result string
        StringBuilder result = new StringBuilder();
        while (!maxHeap.isEmpty()) {
            char c = maxHeap.poll();
            for (int i = 0; i < freqMap.get(c); i++) {
                result.append(c);
            }
        }
        
        return result.toString();
    }
    
    /**
     * Sorts characters using sorted list of entries from frequency map.
     * Converts frequency map entries to list, sorts by value (frequency) descending,
     * then builds result string. Clear and straightforward approach.
     * 
     * @param s input string
     * @return string sorted by character frequency
     */
    public static String frequencySortList(String s) {
        // Build frequency map
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }
        
        // Convert to list and sort by frequency (descending)
        List<Map.Entry<Character, Integer>> entryList = new ArrayList<>(freqMap.entrySet());
        entryList.sort((a, b) -> b.getValue() - a.getValue());
        
        // Build result
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Character, Integer> entry : entryList) {
            for (int i = 0; i < entry.getValue(); i++) {
                result.append(entry.getKey());
            }
        }
        
        return result.toString();
    }
    
    /**
     * Sorts characters using bucket approach for fixed character set.
     * Creates buckets indexed by frequency, then builds result from highest
     * frequency bucket to lowest. O(n) time when character set is limited.
     * 
     * @param s input string
     * @return string sorted by character frequency
     */
    @SuppressWarnings("unchecked")
    public static String frequencySortBucket(String s) {
        // Build frequency map
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }
        
        // Create buckets for each frequency (0 to n)
        List<Character>[] buckets = new List[s.length() + 1];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new ArrayList<>();
        }
        
        // Place characters in buckets based on frequency
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            buckets[entry.getValue()].add(entry.getKey());
        }
        
        // Build result from highest to lowest frequency
        StringBuilder result = new StringBuilder();
        for (int i = buckets.length - 1; i >= 0; i--) {
            for (char c : buckets[i]) {
                for (int j = 0; j < i; j++) {
                    result.append(c);
                }
            }
        }
        
        return result.toString();
    }
    
    /**
     * Helper function to print character frequencies for debugging.
     * Displays each character and its count in the string.
     * 
     * @param s input string
     */
    public static void printFrequencies(String s) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }
        System.out.println("Frequencies: " + freqMap);
    }
    
    public static void main(String[] args) {
        System.out.println("Sort \"tree\" by frequency: \"" + frequencySort("tree") + "\"");     // "eert" or similar
        System.out.println("Sort \"cccaaa\" by frequency: \"" + frequencySort("cccaaa") + "\""); // "ccccaa" or similar
        System.out.println("Sort \"Aabb\" by frequency: \"" + frequencySort("Aabb") + "\"");     // "bbAa" or similar
        System.out.println("Sort \"abbacccccccdd\" by frequency: \"" + 
            frequencySort("abbacccccccdd") + "\"");                                              // "ccccccccddbbbaaa"
        
        System.out.println("\nUsing bucket approach:");
        System.out.println("Sort \"hello\" by frequency: \"" + frequencySortBucket("hello") + "\"");
        
        System.out.println("\nFrequencies in \"mississippi\":");
        printFrequencies("mississippi");
    }
}
