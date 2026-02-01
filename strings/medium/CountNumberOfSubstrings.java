package strings.medium;

public class CountNumberOfSubstrings {

    /*
    =====================================================================================
    PROBLEM: COUNT NUMBER OF SUBSTRINGS
    -------------------------------------------------------------------------------------
    Given a string s, count total number of non-empty substrings of the string. A substring
    is a contiguous sequence of characters within a string. For example, in string "abc", 
    the substrings are "a", "b", "c", "ab", "bc", "abc" totaling 6. The mathematical 
    formula is: for a string of length n, total substrings = n*(n+1)/2. This includes 
    all possible starting and ending positions. The problem tests combinatorics understanding
    and substring generation logic, with variations like counting substrings with specific
    properties (palindromes, containing pattern, etc).

    Time Complexity: O(1) for total count (formula), O(n^2) for explicit enumeration
    Space Complexity: O(n) for storing substrings, O(1) for counting

    Example:
    Input:  s = "abc"
    Output: 6 (substrings: "a", "b", "c", "ab", "bc", "abc")
    =====================================================================================
    */
    
    /**
     * Counts total number of substrings using mathematical formula.
     * For string of length n, total substrings = n*(n+1)/2.
     * Each character can be a start of (n-i) substrings where i is its position.
     * Most efficient O(1) approach.
     * 
     * @param s input string
     * @return total number of non-empty substrings
     */
    public static int countSubstrings(String s) {
        int n = s.length();
        return n * (n + 1) / 2;
    }
    
    /**
     * Counts total substrings by explicit enumeration using nested loops.
     * Outer loop selects starting position, inner loop extends to ending position.
     * Demonstrates all possible substring combinations. O(n^2) time but clearer logic.
     * 
     * @param s input string
     * @return total number of substrings
     */
    public static int countSubstringsExplicit(String s) {
        int count = 0;
        int n = s.length();
        
        // For each starting position
        for (int i = 0; i < n; i++) {
            // For each ending position from start to end
            for (int j = i + 1; j <= n; j++) {
                count++;
                // Substring would be: s.substring(i, j)
            }
        }
        
        return count;
    }
    
    /**
     * Counts palindromic substrings in the string.
     * Uses expand-around-center approach for each possible center.
     * For every character (odd length) and pair of characters (even length),
     * expand outward and count palindromes. O(n^2) time complexity.
     * 
     * @param s input string
     * @return count of palindromic substrings
     */
    public static int countPalindromicSubstrings(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        
        int count = 0;
        
        // Check every possible center (both odd and even length palindromes)
        for (int i = 0; i < s.length(); i++) {
            // Odd length palindromes (center is a single character)
            count += expandAroundCenter(s, i, i);
            
            // Even length palindromes (center is between two characters)
            count += expandAroundCenter(s, i, i + 1);
        }
        
        return count;
    }
    
    /**
     * Helper function to expand around center and count palindromes.
     * Starting from center(s), expands outward while characters match.
     * Returns count of palindromes found from this center.
     * 
     * @param s input string
     * @param left left center index
     * @param right right center index
     * @return count of palindromic substrings around center
     */
    private static int expandAroundCenter(String s, int left, int right) {
        int count = 0;
        
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;
            left--;
            right++;
        }
        
        return count;
    }
    
    /**
     * Counts distinct substrings (removes duplicates).
     * Uses HashSet to store all unique substrings encountered.
     * Useful for problems requiring unique substring count.
     * O(n^2) time and space.
     * 
     * @param s input string
     * @return count of distinct substrings
     */
    public static int countDistinctSubstrings(String s) {
        java.util.Set<String> substrings = new java.util.HashSet<>();
        int n = s.length();
        
        // Generate all substrings
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                substrings.add(s.substring(i, j));
            }
        }
        
        return substrings.size();
    }
    
    /**
     * Counts substrings with all unique characters (no repeated characters).
     * For each starting position, extends until a duplicate character appears.
     * Tracks maximum length found. O(n^2) worst case.
     * 
     * @param s input string
     * @return count of all-unique-character substrings
     */
    public static int countSubstringsWithUniqueChars(String s) {
        int count = 0;
        int n = s.length();
        
        for (int i = 0; i < n; i++) {
            java.util.Set<Character> seen = new java.util.HashSet<>();
            
            for (int j = i; j < n; j++) {
                char c = s.charAt(j);
                
                // Stop if character already seen in this substring
                if (seen.contains(c)) {
                    break;
                }
                
                seen.add(c);
                count++;  // Valid substring: s.substring(i, j+1)
            }
        }
        
        return count;
    }
    
    /**
     * Helper function to generate and print all substrings.
     * Useful for visualization and debugging substring problems.
     * 
     * @param s input string
     */
    public static void printAllSubstrings(String s) {
        int n = s.length();
        System.out.println("All substrings of \"" + s + "\":");
        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                System.out.print("\"" + s.substring(i, j) + "\" ");
            }
        }
        System.out.println();
    }
    
    /**
     * Helper function to calculate expected substring count for given length.
     * Useful for verification and understanding the formula.
     * 
     * @param length string length
     * @return total substring count
     */
    public static int calculateSubstringCount(int length) {
        return length * (length + 1) / 2;
    }
    
    public static void main(String[] args) {
        System.out.println("Count substrings in \"abc\": " + countSubstrings("abc"));  // 6
        System.out.println("Count substrings in \"a\": " + countSubstrings("a"));      // 1
        System.out.println("Count substrings in \"abcd\": " + countSubstrings("abcd")); // 10
        System.out.println("Count substrings in \"aab\": " + countSubstrings("aab")); // 6
        
        System.out.println("\nUsing explicit enumeration:");
        System.out.println("Count substrings in \"ab\": " + countSubstringsExplicit("ab")); // 3
        
        System.out.println("\nCount palindromic substrings:");
        System.out.println("Palindromes in \"abc\": " + countPalindromicSubstrings("abc"));     // 3 ("a", "b", "c")
        System.out.println("Palindromes in \"aab\": " + countPalindromicSubstrings("aab"));     // 4 ("a", "a", "b", "aa")
        System.out.println("Palindromes in \"ababa\": " + countPalindromicSubstrings("ababa")); // 9
        
        System.out.println("\nDistinct substrings:");
        System.out.println("Distinct in \"aab\": " + countDistinctSubstrings("aab"));      // 5 ("a", "aa", "aab", "ab", "b")
        
        System.out.println("\nSubstrings with unique characters:");
        System.out.println("Unique char substrings in \"abc\": " + countSubstringsWithUniqueChars("abc"));   // 6
        System.out.println("Unique char substrings in \"aab\": " + countSubstringsWithUniqueChars("aab"));   // 4 ("a", "b", "ab" - stops at second 'a')
        
        System.out.println("\nAll substrings visualization:");
        printAllSubstrings("abc");
        
        System.out.println("\nFormula verification:");
        System.out.println("String length 5 should have " + calculateSubstringCount(5) + " substrings");
    }
}
