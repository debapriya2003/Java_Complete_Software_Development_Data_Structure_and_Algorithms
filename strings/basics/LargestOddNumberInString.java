package strings.basics;

public class LargestOddNumberInString {

    /*
    =====================================================================================
    PROBLEM: LARGEST ODD NUMBER IN A STRING
    -------------------------------------------------------------------------------------
    Given a string representing a very large number, find the largest odd number that 
    can be created by deleting some or no digits without changing the relative order of 
    remaining digits. An odd number has an odd digit at the end. The key insight is that 
    the largest number is formed by removing as few digits as possible from the end, 
    specifically removing trailing even digits until an odd digit is found or string ends.

    Time Complexity: O(n)
    Space Complexity: O(1)

    Example:
    Input:  num = "52"
    Output: "5" (5 is odd, 2 is even, so return "5")
    =====================================================================================
    */
    
    /**
     * Finds the largest odd number by removing trailing even digits from the string.
     * Iterates from the end of the string and finds the rightmost odd digit. Returns
     * substring from start to that position inclusive. If no odd digit exists, returns
     * empty string. This approach maximizes the number by keeping maximum digits.
     * 
     * @param num string representation of a large number
     * @return largest odd number as substring, or empty string if none exists
     */
    public static String largestOddNumber(String num) {
        // Find the rightmost odd digit
        for (int i = num.length() - 1; i >= 0; i--) {
            int digit = num.charAt(i) - '0';
            if (digit % 2 != 0) {  // Odd digit found
                return num.substring(0, i + 1);
            }
        }
        return "";  // No odd digit found
    }
    
    /**
     * Helper function to check if a digit character is odd.
     * Converts character to integer and checks if it's odd using modulo operation.
     * 
     * @param digit character digit to check
     * @return true if digit is odd
     */
    private static boolean isOddDigit(char digit) {
        return (digit - '0') % 2 != 0;
    }
    
    /**
     * Alternative approach with helper function for clarity.
     * Same logic but uses helper function for improved readability and modularity.
     * 
     * @param num string representation of a large number
     * @return largest odd number as substring
     */
    public static String largestOddNumberWithHelper(String num) {
        for (int i = num.length() - 1; i >= 0; i--) {
            if (isOddDigit(num.charAt(i))) {
                return num.substring(0, i + 1);
            }
        }
        return "";
    }
    
    public static void main(String[] args) {
        System.out.println("Largest odd in \"52\": " + largestOddNumber("52"));        // "5"
        System.out.println("Largest odd in \"4206\": " + largestOddNumber("4206"));    // ""
        System.out.println("Largest odd in \"35427\": " + largestOddNumber("35427"));  // "35427"
        System.out.println("Largest odd in \"123456\": " + largestOddNumber("123456")); // "12345"
        System.out.println("Largest odd in \"9\": " + largestOddNumber("9"));          // "9"
        System.out.println("Largest odd in \"123450\": " + largestOddNumber("123450")); // "12345"
    }
}
