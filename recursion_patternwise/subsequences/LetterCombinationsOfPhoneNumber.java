package recursion_patternwise.subsequences;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinationsOfPhoneNumber {

    /*
    =====================================================================================
    PROBLEM: LETTER COMBINATIONS OF A PHONE NUMBER
    -------------------------------------------------------------------------------------
    Given a string containing digits 2-9, return all possible letter combinations that
    the number could represent. Each digit maps to multiple letters (like on phone keypad).
    
    Mapping:
    2 -> "abc"
    3 -> "def"
    4 -> "ghi"
    5 -> "jkl"
    6 -> "mno"
    7 -> "pqrs"
    8 -> "tuv"
    9 -> "wxyz"
    
    Example 1:
    Input: "23"
    Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]
    
    Example 2:
    Input: ""
    Output: []
    
    Example 3:
    Input: "2"
    Output: ["a", "b", "c"]
    
    Approach:
    1. Map each digit to its letters
    2. Use recursion to build combinations
    3. For each digit, try all corresponding letters
    4. Build combinations step by step
    
    Time Complexity: O(4^n * n) where n is length of input (max 4 letters per digit)
    Space Complexity: O(4^n) for output storage
    =====================================================================================
    */
    
    /**
     * Find all letter combinations for given digit string.
     * 
     * @param digits string of digits 2-9
     * @return list of all letter combinations
     */
    public static List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        
        // Handle empty string
        if (digits == null || digits.length() == 0) {
            return result;
        }
        
        // Mapping of digits to letters
        Map<Character, String> digitToLetters = new HashMap<>();
        digitToLetters.put('2', "abc");
        digitToLetters.put('3', "def");
        digitToLetters.put('4', "ghi");
        digitToLetters.put('5', "jkl");
        digitToLetters.put('6', "mno");
        digitToLetters.put('7', "pqrs");
        digitToLetters.put('8', "tuv");
        digitToLetters.put('9', "wxyz");
        
        combineHelper(digits, 0, "", result, digitToLetters);
        return result;
    }
    
    /**
     * Recursive helper to build letter combinations.
     * 
     * Algorithm:
     * 1. Base case: when index reaches end of digits, add current combination
     * 2. Get letters corresponding to current digit
     * 3. For each letter:
     *    - Add it to current combination
     *    - Recurse to next digit
     *    - Current string grows with each recursion
     * 
     * @param digits input digit string
     * @param index current index in digits
     * @param current current combination being built
     * @param result all combinations
     * @param digitToLetters mapping from digit to letters
     */
    private static void combineHelper(String digits, int index, String current, List<String> result, Map<Character, String> digitToLetters) {
        // Base case: processed all digits
        if (index == digits.length()) {
            result.add(current);
            return;
        }
        
        // Get letters for current digit
        char currentDigit = digits.charAt(index);
        String letters = digitToLetters.get(currentDigit);
        
        // Try each letter for current digit
        for (char letter : letters.toCharArray()) {
            combineHelper(digits, index + 1, current + letter, result, digitToLetters);
        }
    }
    
    /**
     * Alternative implementation using StringBuilder for efficiency.
     * 
     * @param digits input digit string
     * @return list of combinations
     */
    public static List<String> letterCombinationsOptimized(String digits) {
        List<String> result = new ArrayList<>();
        
        if (digits == null || digits.length() == 0) {
            return result;
        }
        
        String[] mapping = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        StringBuilder sb = new StringBuilder();
        combineHelperOptimized(digits, 0, sb, result, mapping);
        return result;
    }
    
    private static void combineHelperOptimized(String digits, int index, StringBuilder sb, List<String> result, String[] mapping) {
        if (index == digits.length()) {
            result.add(sb.toString());
            return;
        }
        
        int digit = digits.charAt(index) - '0';
        String letters = mapping[digit];
        
        for (char letter : letters.toCharArray()) {
            sb.append(letter);
            combineHelperOptimized(digits, index + 1, sb, result, mapping);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
    
    /**
     * Iterative approach using BFS.
     * Build combinations level by level.
     * 
     * @param digits input digit string
     * @return list of combinations
     */
    public static List<String> letterCombinationsIterative(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }
        
        String[] mapping = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        List<String> result = new ArrayList<>();
        result.add("");
        
        for (char digit : digits.toCharArray()) {
            List<String> temp = new ArrayList<>();
            String letters = mapping[digit - '0'];
            
            for (String combination : result) {
                for (char letter : letters.toCharArray()) {
                    temp.add(combination + letter);
                }
            }
            
            result = temp;
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        // Test Case 1: "23"
        System.out.println("=== Test Case 1: \"23\" ===");
        List<String> result1 = letterCombinations("23");
        System.out.println("Digit string: \"23\"");
        System.out.println("Combinations: " + result1);
        System.out.println("Count: " + result1.size() + " (expected: 9 = 3*3)");
        System.out.println();
        
        // Test Case 2: ""
        System.out.println("=== Test Case 2: \"\" (Empty) ===");
        List<String> result2 = letterCombinations("");
        System.out.println("Combinations: " + result2 + " (expected empty)");
        System.out.println();
        
        // Test Case 3: "2"
        System.out.println("=== Test Case 3: \"2\" ===");
        List<String> result3 = letterCombinations("2");
        System.out.println("Combinations: " + result3);
        System.out.println("Count: " + result3.size() + " (expected: 3)");
        System.out.println();
        
        // Test Case 4: "234"
        System.out.println("=== Test Case 4: \"234\" ===");
        List<String> result4 = letterCombinations("234");
        System.out.println("Count: " + result4.size() + " (expected: 27 = 3*3*3)");
        System.out.println("First 5: " + result4.subList(0, Math.min(5, result4.size())));
        System.out.println();
        
        // Test Case 5: "7" (4 letters)
        System.out.println("=== Test Case 5: \"7\" ===");
        List<String> result5 = letterCombinations("7");
        System.out.println("Combinations: " + result5);
        System.out.println();
        
        // Test Case 6: "23456"
        System.out.println("=== Test Case 6: \"23456\" ===");
        List<String> result6 = letterCombinations("23456");
        System.out.println("Count: " + result6.size() + " (expected: 3*3*4*3*3 = 324)");
        System.out.println();
        
        // Comparison of approaches
        System.out.println("=== Approach Comparison (\"234\") ===");
        List<String> opt = letterCombinationsOptimized("234");
        List<String> iter = letterCombinationsIterative("234");
        System.out.println("Recursive matches optimized? " + result4.equals(opt));
        System.out.println("Recursive matches iterative? " + result4.equals(iter));
    }
}
