package strings.medium;

public class ReverseEveryWordInAString {

    /*
    =====================================================================================
    PROBLEM: REVERSE EVERY WORD IN A STRING
    -------------------------------------------------------------------------------------
    Given a string s containing words separated by spaces, reverse each individual word 
    while keeping the word order unchanged. For example, "Hello World" becomes "olleH 
    dlroW". Words are typically separated by single or multiple spaces, and in some 
    variations, leading/trailing spaces or extra spaces between words should be preserved.
    This problem tests string manipulation, word boundary detection, and understanding of 
    in-place vs extra space techniques. Multiple approaches exist: split and join, character
    array reversal, two-pointer technique, or StringBuilder approaches.

    Time Complexity: O(n) where n is string length (single pass)
    Space Complexity: O(n) for output string or O(1) for in-place on character array

    Example:
    Input:  s = "Hello World"
    Output: "olleH dlroW" (each word reversed, order preserved)
    =====================================================================================
    */
    
    /**
     * Reverses every word in string using split and StringBuilder.
     * Splits by spaces, reverses each word, and joins back.
     * Clean and straightforward approach. Handles multiple spaces by
     * using split with regex and then reconstructing with single spaces.
     * 
     * @param s input string with words separated by spaces
     * @return string with each word reversed
     */
    public static String reverseEachWord(String s) {
        String[] words = s.split(" ");
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < words.length; i++) {
            // Reverse current word
            result.append(new StringBuilder(words[i]).reverse());
            
            // Add space between words (but not after last word)
            if (i < words.length - 1) {
                result.append(" ");
            }
        }
        
        return result.toString();
    }
    
    /**
     * Reverses words by converting to character array and reversing each word in-place.
     * Iterates through string, identifies word boundaries, and reverses characters
     * within each word. More memory efficient for large strings. O(1) extra space
     * if input is already character array.
     * 
     * @param s input string
     * @return string with each word reversed
     */
    public static String reverseEachWordArray(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        
        int i = 0;
        while (i < n) {
            // Skip spaces to find word start
            if (chars[i] == ' ') {
                i++;
                continue;
            }
            
            // Find word end
            int j = i;
            while (j < n && chars[j] != ' ') {
                j++;
            }
            
            // Reverse word from i to j-1
            reverseSubstring(chars, i, j - 1);
            
            i = j;
        }
        
        return new String(chars);
    }
    
    /**
     * Helper function to reverse a substring within character array.
     * Swaps characters from both ends moving toward center.
     * Used by in-place word reversal approach.
     * 
     * @param chars character array
     * @param start start index
     * @param end end index (inclusive)
     */
    private static void reverseSubstring(char[] chars, int start, int end) {
        while (start < end) {
            // Swap characters
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
            
            start++;
            end--;
        }
    }
    
    /**
     * Preserves multiple consecutive spaces by processing word-by-word.
     * Tracks both word content and spacing pattern. Reconstructs string
     * with original spacing preserved. Handles edge cases with multiple spaces.
     * 
     * @param s input string (may have multiple spaces)
     * @return string with words reversed and spaces preserved
     */
    public static String reverseEachWordPreserveSpaces(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        
        StringBuilder result = new StringBuilder();
        int i = 0;
        int n = s.length();
        
        while (i < n) {
            // If current character is space, append it as-is
            if (s.charAt(i) == ' ') {
                result.append(' ');
                i++;
            } else {
                // Find word boundaries
                int j = i;
                while (j < n && s.charAt(j) != ' ') {
                    j++;
                }
                
                // Reverse word and append
                String word = s.substring(i, j);
                result.append(new StringBuilder(word).reverse());
                
                i = j;
            }
        }
        
        return result.toString();
    }
    
    /**
     * Alternative two-pointer approach for character array manipulation.
     * Finds word boundaries and reverses using two pointers. Efficient and
     * demonstrates low-level string manipulation technique.
     * 
     * @param s input string
     * @return string with each word reversed
     */
    public static String reverseEachWordTwoPointer(String s) {
        char[] arr = s.toCharArray();
        int start = 0;
        
        for (int end = 0; end <= arr.length; end++) {
            // Found word boundary (space or end of string)
            if (end == arr.length || arr[end] == ' ') {
                // Reverse word from start to end-1
                if (end > start) {
                    reverseSubstring(arr, start, end - 1);
                }
                start = end + 1;
            }
        }
        
        return new String(arr);
    }
    
    /**
     * Reverses individual word using helper function.
     * Demonstrates single word reversal for testing and debugging.
     * 
     * @param word input word
     * @return reversed word
     */
    public static String reverseSingleWord(String word) {
        return new StringBuilder(word).reverse().toString();
    }
    
    /**
     * Helper function to identify words and their positions in string.
     * Useful for debugging and understanding word boundaries.
     * 
     * @param s input string
     */
    public static void printWordBoundaries(String s) {
        System.out.println("Word boundaries in \"" + s + "\":");
        int i = 0;
        int wordNum = 0;
        
        while (i < s.length()) {
            if (s.charAt(i) != ' ') {
                int j = i;
                while (j < s.length() && s.charAt(j) != ' ') {
                    j++;
                }
                System.out.println("Word " + (++wordNum) + ": \"" + 
                    s.substring(i, j) + "\" at position " + i);
                i = j;
            } else {
                i++;
            }
        }
    }
    
    /**
     * Counts number of words in string.
     * Identifies space-separated tokens.
     * 
     * @param s input string
     * @return number of words
     */
    public static int countWords(String s) {
        int count = 0;
        boolean inWord = false;
        
        for (char c : s.toCharArray()) {
            if (c != ' ') {
                if (!inWord) {
                    count++;
                    inWord = true;
                }
            } else {
                inWord = false;
            }
        }
        
        return count;
    }
    
    public static void main(String[] args) {
        System.out.println("Reverse each word in \"Hello World\":");
        System.out.println("Result: \"" + reverseEachWord("Hello World") + "\"");          // "olleH dlroW"
        
        System.out.println("\nReverse each word in \"Java Programming Language\":");
        System.out.println("Result: \"" + reverseEachWord("Java Programming Language") + "\""); // "avaJ gnimmargorP egaugnaL"
        
        System.out.println("\nUsing character array approach:");
        System.out.println("Result: \"" + reverseEachWordArray("Hello World") + "\"");     // "olleH dlroW"
        
        System.out.println("\nWith space preservation:");
        System.out.println("Result: \"" + reverseEachWordPreserveSpaces("Hello  World") + "\""); // Preserves double space
        
        System.out.println("\nUsing two-pointer approach:");
        System.out.println("Result: \"" + reverseEachWordTwoPointer("The Quick Fox") + "\""); // "ehT kciuQ xoF"
        
        System.out.println("\nIndividual word reversal:");
        System.out.println("\"Hello\" -> \"" + reverseSingleWord("Hello") + "\"");        // "olleH"
        System.out.println("\"World\" -> \"" + reverseSingleWord("World") + "\"");        // "dlroW"
        
        System.out.println("\nWord analysis:");
        printWordBoundaries("Hello World");
        System.out.println("Number of words in \"The Quick Brown Fox\": " + 
            countWords("The Quick Brown Fox"));                                           // 4
        
        System.out.println("\nEdge cases:");
        System.out.println("\"a\" -> \"" + reverseEachWord("a") + "\"");                  // "a"
        System.out.println("\"\" -> \"" + reverseEachWord("") + "\"");                    // ""
        System.out.println("\"a b c\" -> \"" + reverseEachWord("a b c") + "\"");          // "a b c"
    }
}
