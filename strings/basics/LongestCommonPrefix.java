package strings.basics;

public class LongestCommonPrefix {

    /*
    =====================================================================================
    PROBLEM: LONGEST COMMON PREFIX
    -------------------------------------------------------------------------------------
    Given an array of strings, write a function to find the longest string that is a 
    prefix of all the strings in the array. A prefix is a substring that starts from the 
    beginning of a string. If there is no common prefix, return an empty string. For example, 
    with ["flower","flow","flight"], the common prefix is "fl". Multiple approaches exist: 
    horizontal scanning, vertical scanning, divide and conquer, or using sorting.

    Time Complexity: O(m * n) where m is average length and n is number of strings
    Space Complexity: O(1)

    Example:
    Input:  strs = ["flower","flow","flight"]
    Output: "fl" (common prefix of all three strings)
    =====================================================================================
    */
    
    /**
     * Finds the longest common prefix using vertical scanning approach.
     * Compares characters at the same position across all strings from left to right.
     * Stops when a mismatch is found or any string ends. Returns the common prefix.
     * Efficient when the common prefix is short.
     * 
     * @param strs array of strings
     * @return longest common prefix
     */
    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            
            for (int j = 1; j < strs.length; j++) {
                if (i >= strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }
    
    /**
     * Horizontal scanning approach: compares strings pairwise from left to right.
     * Updates the prefix by taking common prefix of current prefix and next string.
     * Continues until prefix is empty or all strings processed.
     * 
     * @param strs array of strings
     * @return longest common prefix
     */
    public static String longestCommonPrefixHorizontal(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        
        String prefix = strs[0];
        
        for (int i = 1; i < strs.length; i++) {
            while (strs[i].indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }
        return prefix;
    }
    
    /**
     * Divide and conquer approach: recursively finds common prefix of left and right halves.
     * Combines results to find overall common prefix. Efficient for large arrays.
     * 
     * @param strs array of strings
     * @return longest common prefix
     */
    public static String longestCommonPrefixDivideConquer(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        return longestCommonPrefixHelper(strs, 0, strs.length - 1);
    }
    
    /**
     * Helper function for divide and conquer approach.
     * Recursively divides array and finds common prefix of results.
     * 
     * @param strs array of strings
     * @param left leftmost index
     * @param right rightmost index
     * @return longest common prefix of subarray
     */
    private static String longestCommonPrefixHelper(String[] strs, int left, int right) {
        if (left == right) {
            return strs[left];
        }
        
        int mid = left + (right - left) / 2;
        String leftPrefix = longestCommonPrefixHelper(strs, left, mid);
        String rightPrefix = longestCommonPrefixHelper(strs, mid + 1, right);
        
        return commonPrefix(leftPrefix, rightPrefix);
    }
    
    /**
     * Finds common prefix between two strings by comparing character by character.
     * Used as helper in divide and conquer and horizontal approaches.
     * 
     * @param s1 first string
     * @param s2 second string
     * @return common prefix of both strings
     */
    private static String commonPrefix(String s1, String s2) {
        int minLen = Math.min(s1.length(), s2.length());
        
        for (int i = 0; i < minLen; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                return s1.substring(0, i);
            }
        }
        return s1.substring(0, minLen);
    }
    
    public static void main(String[] args) {
        String[] strs1 = {"flower", "flow", "flight"};
        System.out.println("Longest common prefix: \"" + longestCommonPrefix(strs1) + "\""); // "fl"
        
        String[] strs2 = {"dog", "racecar", "car"};
        System.out.println("Longest common prefix: \"" + longestCommonPrefix(strs2) + "\""); // ""
        
        String[] strs3 = {"interspecies", "interstellar", "interstate"};
        System.out.println("Longest common prefix: \"" + longestCommonPrefix(strs3) + "\""); // "inters"
        
        String[] strs4 = {"a"};
        System.out.println("Longest common prefix: \"" + longestCommonPrefix(strs4) + "\""); // "a"
        
        String[] strs5 = {"ab", "a"};
        System.out.println("Longest common prefix (horizontal): \"" + 
            longestCommonPrefixHorizontal(strs5) + "\"");  // "a"
    }
}
