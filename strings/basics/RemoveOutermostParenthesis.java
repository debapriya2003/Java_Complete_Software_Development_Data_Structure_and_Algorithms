package strings.basics;

public class RemoveOutermostParenthesis {

    /*
    =====================================================================================
    PROBLEM: REMOVE OUTERMOST PARENTHESIS
    -------------------------------------------------------------------------------------
    A valid parentheses string is either an empty string or a parentheses string where 
    the outermost parenthesis are removed from a valid parentheses string. Given a valid 
    parentheses string, remove all outermost parentheses and return the resulting string. 
    Use a counter to track the depth of nesting. When counter goes from 0 to 1, skip the 
    opening parenthesis. When counter goes from 1 to 0, skip the closing parenthesis. 
    All other parentheses are part of inner valid strings.

    Time Complexity: O(n)
    Space Complexity: O(n)

    Example:
    Input:  s = "(()())(())"
    Output: "()()()" (removed outer parentheses from each valid component)
    =====================================================================================
    */
    
    /**
     * Removes the outermost parenthesis from each valid component of a parentheses string.
     * Uses a counter to track nesting depth. When counter becomes 1 (entering a component),
     * skip opening parenthesis. When counter becomes 0 (leaving a component), skip closing
     * parenthesis. All internal parentheses are kept to form the result string.
     * 
     * @param s valid parentheses string
     * @return string with outermost parentheses removed
     */
    public static String removeOuterParentheses(String s) {
        StringBuilder result = new StringBuilder();
        int count = 0;
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                if (count > 0) {
                    result.append(c);
                }
                count++;
            } else {
                count--;
                if (count > 0) {
                    result.append(c);
                }
            }
        }
        return result.toString();
    }
    
    public static void main(String[] args) {
        System.out.println("Remove outer: \"(()())(())\" -> \"" + 
            removeOuterParentheses("(()())(())") + "\"");  // "()()()"
        System.out.println("Remove outer: \"(())\" -> \"" + 
            removeOuterParentheses("(())") + "\"");        // ""
        System.out.println("Remove outer: \"()()\" -> \"" + 
            removeOuterParentheses("()()") + "\"");        // ""
        System.out.println("Remove outer: \"(())(())\" -> \"" + 
            removeOuterParentheses("(())(())") + "\"");    // "()()"
    }
}
