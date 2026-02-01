package recursion_patternwise.all_combo_hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordBreak {

    /*
    =====================================================================================
    PROBLEM: WORD BREAK
    -------------------------------------------------------------------------------------
    Given a non-empty string s and a dictionary wordDict containing a list of non-empty
    words, determine if s can be segmented into words from the dictionary.
    
    Example 1:
    Input: s = "leetcode", wordDict = ["leet", "code"]
    Output: true (can be segmented as "leet" and "code")
    
    Example 2:
    Input: s = "catsandog", wordDict = ["cat", "cats", "and", "sand", "dog"]
    Output: false (cannot be segmented)
    
    Approach 1: Dynamic Programming
    - dp[i] = true if substring s[0:i] can be segmented
    - For each i, check all j < i: if dp[j] and s[j:i] in dict, set dp[i] = true
    - Time: O(n^2), Space: O(n)
    
    Approach 2: Backtracking with Memoization
    - Recursively try to match words from current position
    - Memoize results to avoid recomputation
    - Time: O(2^n) worst case, better with memoization
    
    Time Complexity: O(n^2) DP approach
    Space Complexity: O(n) for DP array
    =====================================================================================
    */
    
    /**
     * Check if string can be segmented into dictionary words (DP approach).
     * 
     * @param s string to segment
     * @param wordDict dictionary of valid words
     * @return true if can be segmented
     */
    public static boolean canSegment(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        int n = s.length();
        
        // dp[i] = true if s[0:i] can be segmented
        boolean[] dp = new boolean[n + 1];
        dp[0] = true; // empty string can be segmented
        
        for (int i = 1; i <= n; i++) {
            // Try all possible last words
            for (int j = 0; j < i; j++) {
                String word = s.substring(j, i);
                if (dp[j] && wordSet.contains(word)) {
                    dp[i] = true;
                    break;
                }
            }
        }
        
        return dp[n];
    }
    
    /**
     * Find all possible segmentations of the string.
     * 
     * @param s string to segment
     * @param wordDict dictionary of valid words
     * @return list of all possible segmentations
     */
    public static List<String> wordBreakII(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();
        Set<String> wordSet = new HashSet<>(wordDict);
        Map<Integer, List<String>> memo = new HashMap<>();
        
        backtrack(s, 0, wordSet, new ArrayList<>(), result, memo);
        return result;
    }
    
    /**
     * Backtracking with memoization to find all segmentations.
     * 
     * @param s string
     * @param start starting index
     * @param wordSet dictionary set
     * @param path current segmentation
     * @param result all valid segmentations
     * @param memo memoization map
     */
    private static void backtrack(String s, int start, Set<String> wordSet, 
                                   List<String> path, List<String> result,
                                   Map<Integer, List<String>> memo) {
        // Base case: reached end of string
        if (start == s.length()) {
            result.add(String.join(" ", path));
            return;
        }
        
        // Check memo
        if (memo.containsKey(start)) {
            for (String segmentation : memo.get(start)) {
                if (segmentation.isEmpty()) {
                    result.add(String.join(" ", path));
                } else {
                    List<String> newPath = new ArrayList<>(path);
                    for (String word : segmentation.split(" ")) {
                        newPath.add(word);
                    }
                    result.add(String.join(" ", newPath));
                }
            }
            return;
        }
        
        List<String> localResult = new ArrayList<>();
        
        // Try all possible next words
        for (int i = start + 1; i <= s.length(); i++) {
            String word = s.substring(start, i);
            if (wordSet.contains(word)) {
                path.add(word);
                int resultSize = result.size();
                
                backtrack(s, i, wordSet, path, result, memo);
                
                // Store segmentations found in this branch
                List<String> segmentations = result.subList(resultSize, result.size());
                for (String seg : segmentations) {
                    localResult.add(seg.substring(word.length()).trim());
                }
                
                path.remove(path.size() - 1);
            }
        }
        
        memo.put(start, localResult);
    }
    
    /**
     * Simpler backtracking approach to find all segmentations.
     * 
     * @param s string to segment
     * @param wordDict dictionary of valid words
     * @return list of all possible segmentations
     */
    public static List<String> wordBreakSimple(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();
        Set<String> wordSet = new HashSet<>(wordDict);
        backtrackSimple(s, 0, new StringBuilder(), result, wordSet);
        return result;
    }
    
    private static void backtrackSimple(String s, int start, StringBuilder path, 
                                        List<String> result, Set<String> wordSet) {
        if (start == s.length()) {
            result.add(path.toString().trim());
            return;
        }
        
        for (int i = start + 1; i <= s.length(); i++) {
            String word = s.substring(start, i);
            if (wordSet.contains(word)) {
                int len = path.length();
                if (len > 0) path.append(" ");
                path.append(word);
                
                backtrackSimple(s, i, path, result, wordSet);
                
                path.setLength(len);
            }
        }
    }
    
    public static void main(String[] args) {
        // Test Case 1: Basic segmentation check
        System.out.println("=== Test Case 1: Basic Check ===");
        String s1 = "leetcode";
        List<String> dict1 = new ArrayList<>();
        dict1.add("leet");
        dict1.add("code");
        System.out.println("String: " + s1);
        System.out.println("Dictionary: " + dict1);
        System.out.println("Can segment: " + canSegment(s1, dict1));
        System.out.println("Segmentations: " + wordBreakSimple(s1, dict1));
        System.out.println();
        
        // Test Case 2: Cannot segment
        System.out.println("=== Test Case 2: Cannot Segment ===");
        String s2 = "catsandog";
        List<String> dict2 = new ArrayList<>();
        dict2.add("cat");
        dict2.add("cats");
        dict2.add("and");
        dict2.add("sand");
        dict2.add("dog");
        System.out.println("String: " + s2);
        System.out.println("Dictionary: " + dict2);
        System.out.println("Can segment: " + canSegment(s2, dict2));
        System.out.println();
        
        // Test Case 3: Multiple segmentations
        System.out.println("=== Test Case 3: Multiple Segmentations ===");
        String s3 = "catsandcatsdog";
        List<String> dict3 = new ArrayList<>();
        dict3.add("cat");
        dict3.add("cats");
        dict3.add("and");
        dict3.add("sand");
        dict3.add("dog");
        System.out.println("String: " + s3);
        System.out.println("Segmentations: " + wordBreakSimple(s3, dict3));
        System.out.println();
        
        // Test Case 4: Single word
        System.out.println("=== Test Case 4: Single Word ===");
        String s4 = "hello";
        List<String> dict4 = new ArrayList<>();
        dict4.add("hello");
        System.out.println("String: " + s4);
        System.out.println("Can segment: " + canSegment(s4, dict4));
        System.out.println();
        
        // Test Case 5: Overlapping words
        System.out.println("=== Test Case 5: Overlapping Words ===");
        String s5 = "pineapplepenapple";
        List<String> dict5 = new ArrayList<>();
        dict5.add("apple");
        dict5.add("pen");
        dict5.add("applepen");
        dict5.add("pine");
        dict5.add("pineapple");
        System.out.println("String: " + s5);
        System.out.println("Segmentations: " + wordBreakSimple(s5, dict5));
    }
}
