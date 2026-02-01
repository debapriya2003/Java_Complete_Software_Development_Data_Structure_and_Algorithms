package strings.basics;

public class ReverseWordsInString {

    /*
    =====================================================================================
    PROBLEM: REVERSE WORDS IN A GIVEN STRING
    -------------------------------------------------------------------------------------
    Given a string s, reverse the order of words in the string. A word is defined as a 
    sequence of non-space characters. The reverse should maintain the original spacing 
    but reverse the word order. Multiple spaces between words should be preserved in the 
    output. For example, "the sky is blue" becomes "blue is sky the". Use a stack or 
    manual reversal by splitting and reconstructing to efficiently solve this problem.

    Time Complexity: O(n)
    Space Complexity: O(n)

    Example:
    Input:  s = "the sky is blue"
    Output: "blue is sky the"
    =====================================================================================
    */
    
    /**
     * Reverses the order of words in a string while preserving word boundaries.
     * Splits the string by spaces, filters out empty strings from multiple spaces,
     * then reconstructs the string in reverse word order. Handles edge cases like
     * leading/trailing spaces and multiple consecutive spaces between words.
     * 
     * @param s input string with words separated by spaces
     * @return string with words in reverse order
     */
    public static String reverseWords(String s) {
        String[] words = s.trim().split("\\s+");
        StringBuilder result = new StringBuilder();
        
        for (int i = words.length - 1; i >= 0; i--) {
            result.append(words[i]);
            if (i > 0) {
                result.append(" ");
            }
        }
        return result.toString();
    }
    
    /**
     * Alternative approach without using split function.
     * Iterates through string character by character, builds words, and uses
     * a stack-like approach to reverse them. More space efficient with manual parsing.
     * 
     * @param s input string
     * @return reversed word string
     */
    public static String reverseWordsManual(String s) {
        StringBuilder result = new StringBuilder();
        int i = s.length() - 1;
        
        while (i >= 0) {
            // Skip spaces
            while (i >= 0 && s.charAt(i) == ' ') {
                i--;
            }
            
            // Extract word
            int end = i;
            while (i >= 0 && s.charAt(i) != ' ') {
                i--;
            }
            
            // Add word to result
            if (end >= 0) {
                if (result.length() > 0) {
                    result.append(" ");
                }
                result.append(s.substring(i + 1, end + 1));
            }
        }
        return result.toString();
    }
    
    public static void main(String[] args) {
        System.out.println("Reverse: \"the sky is blue\" -> \"" + 
            reverseWords("the sky is blue") + "\"");           // "blue is sky the"
        System.out.println("Reverse: \"  hello world  \" -> \"" + 
            reverseWords("  hello world  ") + "\"");           // "world hello"
        System.out.println("Reverse: \"a good   example\" -> \"" + 
            reverseWords("a good   example") + "\"");          // "example good a"
        System.out.println("Reverse manual: \"the sky is blue\" -> \"" + 
            reverseWordsManual("the sky is blue") + "\"");     // "blue is sky the"
    }
}
