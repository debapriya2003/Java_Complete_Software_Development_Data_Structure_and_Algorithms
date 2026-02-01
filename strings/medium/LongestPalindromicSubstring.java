package strings.medium;

public class LongestPalindromicSubstring {

    /*
    =====================================================================================
    PROBLEM: LONGEST PALINDROMIC SUBSTRING
    -------------------------------------------------------------------------------------
    Given a string s, find the longest contiguous substring that is a palindrome (reads 
    same forwards and backwards). For example, in "babad", the longest palindromic 
    substrings are "bab" and "aba" with length 3. The problem explicitly asks to solve 
    WITHOUT dynamic programming. Optimal non-DP approach is expand-around-center: treat 
    each position as potential palindrome center and expand outward while characters match. 
    Handle both odd-length (single character center) and even-length (two character center)
    palindromes. This approach is intuitive and has O(n^2) time with O(1) space.

    Time Complexity: O(n^2) expand-around-center approach
    Space Complexity: O(1) excluding output string

    Example:
    Input:  s = "babad"
    Output: "bab" or "aba" (both are length 3 palindromes)
    =====================================================================================
    */
    
    /**
     * Finds longest palindromic substring using expand-around-center approach.
     * Treats each character (odd length) and pair (even length) as potential center.
     * Expands outward while characters match, tracking longest palindrome found.
     * No DP required. Intuitive and efficient non-DP solution.
     * 
     * @param s input string
     * @return longest palindromic substring
     */
    public static String longestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        
        int start = 0;
        int maxLen = 1;
        
        // Check every possible center (both odd and even length palindromes)
        for (int i = 0; i < s.length(); i++) {
            // Odd length palindromes: single character center
            int oddLen = expandAroundCenter(s, i, i);
            
            // Even length palindromes: center between two characters
            int evenLen = expandAroundCenter(s, i, i + 1);
            
            // Track longest palindrome found
            int len = Math.max(oddLen, evenLen);
            if (len > maxLen) {
                maxLen = len;
                start = i - (len - 1) / 2;
            }
        }
        
        return s.substring(start, start + maxLen);
    }
    
    /**
     * Helper function to expand around center and find palindrome length.
     * Starting from left and right centers, expands outward while characters match.
     * Returns the length of palindrome found from this center.
     * Used for both odd and even length palindrome detection.
     * 
     * @param s input string
     * @param left left center index
     * @param right right center index
     * @return length of palindrome from this center
     */
    private static int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        
        // Return length: right - left + 1 - 2 (for the overshot positions)
        return right - left - 1;
    }
    
    /**
     * Alternative approach: check all substrings and verify palindrome property.
     * Brute force but clear: generate substring, check if palindrome, track longest.
     * O(n^3) time: O(n^2) substrings * O(n) palindrome check. Less efficient but simple.
     * 
     * @param s input string
     * @return longest palindromic substring
     */
    public static String longestPalindromeBruteForce(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        
        String longest = s.substring(0, 1);
        
        // Check all possible substrings
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                String substring = s.substring(i, j);
                
                // Check if palindrome and longer than current longest
                if (isPalindrome(substring) && substring.length() > longest.length()) {
                    longest = substring;
                }
            }
        }
        
        return longest;
    }
    
    /**
     * Checks if a string is palindrome by comparing characters from both ends.
     * Helper function used by brute force approach.
     * 
     * @param s string to check
     * @return true if palindrome, false otherwise
     */
    public static boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        
        return true;
    }
    
    /**
     * Optimized center expansion with early termination.
     * Skips expanding if current maximum possible is smaller than already found.
     * Reduces unnecessary iterations. Variation of expand-around-center approach.
     * 
     * @param s input string
     * @return longest palindromic substring
     */
    public static String longestPalindromeOptimized(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        
        int start = 0;
        int maxLen = 1;
        
        for (int i = 0; i < s.length() && s.length() - i > maxLen / 2; i++) {
            // Odd length
            int oddLen = expandAroundCenter(s, i, i);
            if (oddLen > maxLen) {
                maxLen = oddLen;
                start = i - (oddLen - 1) / 2;
            }
            
            // Even length
            int evenLen = expandAroundCenter(s, i, i + 1);
            if (evenLen > maxLen) {
                maxLen = evenLen;
                start = i - (evenLen / 2) + 1;
            }
        }
        
        return s.substring(start, start + maxLen);
    }
    
    /**
     * Helper function to print palindrome details for debugging.
     * Shows the found palindrome and its properties (center, length).
     * 
     * @param s input string
     * @param palindrome the palindromic substring
     */
    public static void printPalindromeInfo(String s, String palindrome) {
        int index = s.indexOf(palindrome);
        System.out.println("Palindrome: \"" + palindrome + "\"");
        System.out.println("Position: " + index + " to " + (index + palindrome.length() - 1));
        System.out.println("Length: " + palindrome.length());
    }
    
    /**
     * Helper function to find all palindromic substrings.
     * Useful for analysis and understanding all palindromes in string.
     * Returns count of all palindromic substrings (including single characters).
     * 
     * @param s input string
     * @return count of palindromic substrings
     */
    public static int countAllPalindromes(String s) {
        int count = 0;
        
        for (int i = 0; i < s.length(); i++) {
            // Odd length palindromes
            int left = i, right = i;
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                count++;
                left--;
                right++;
            }
            
            // Even length palindromes
            left = i;
            right = i + 1;
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                count++;
                left--;
                right++;
            }
        }
        
        return count;
    }
    
    public static void main(String[] args) {
        System.out.println("Longest palindrome in \"babad\": \"" + 
            longestPalindrome("babad") + "\"");                                    // "bab" or "aba"
        System.out.println("Longest palindrome in \"cbbd\": \"" + 
            longestPalindrome("cbbd") + "\"");                                     // "bb"
        System.out.println("Longest palindrome in \"a\": \"" + 
            longestPalindrome("a") + "\"");                                        // "a"
        System.out.println("Longest palindrome in \"ac\": \"" + 
            longestPalindrome("ac") + "\"");                                       // "a" or "c"
        System.out.println("Longest palindrome in \"racecar\": \"" + 
            longestPalindrome("racecar") + "\"");                                  // "racecar"
        System.out.println("Longest palindrome in \"abacabad\": \"" + 
            longestPalindrome("abacabad") + "\"");                                 // "abacaba"
        
        System.out.println("\nUsing brute force approach:");
        System.out.println("Longest palindrome in \"babad\": \"" + 
            longestPalindromeBruteForce("babad") + "\"");
        
        System.out.println("\nUsing optimized approach:");
        System.out.println("Longest palindrome in \"abadaba\": \"" + 
            longestPalindromeOptimized("abadaba") + "\"");                         // "abadaba"
        
        System.out.println("\nPalindrome analysis:");
        String test = "babad";
        String result = longestPalindrome(test);
        printPalindromeInfo(test, result);
        System.out.println("Total palindromic substrings in \"" + test + "\": " + 
            countAllPalindromes(test));                                           // "b", "a", "b", "a", "d", "bab", "aba"
    }
}
