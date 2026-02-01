package strings.basics;

public class PalindromeCheck {

    /*
    =====================================================================================
    PROBLEM: PALINDROME CHECK
    -------------------------------------------------------------------------------------
    A palindrome is a word, phrase, or string that reads the same forward and backward.

    Given a string, determine whether it is a palindrome by:
    • Considering only alphanumeric characters (letters and digits)
    • Ignoring case sensitivity

    NON-ALPHANUMERIC characters (spaces, punctuation, symbols) must be ignored.

    Example:
    Input : "A man, a plan, a canal: Panama"
    Valid : "amanaplanacanalpanama"
    Output: true

    APPROACH USED:
    • Two-pointer technique
    • One pointer starts from the beginning
    • One pointer starts from the end
    • Both pointers move inward while skipping non-alphanumeric characters

    Time Complexity : O(n)
    Space Complexity: O(1)
    =====================================================================================
    */

    public static void main(String[] args) {

        System.out.println("Is palindrome \"A man, a plan, a canal: Panama\": "
                + isPalindrome("A man, a plan, a canal: Panama"));

        System.out.println("Is palindrome \"race a car\": "
                + isPalindrome("race a car"));

        System.out.println("Is palindrome \" \": "
                + isPalindrome(" "));

        System.out.println("Is palindrome \"0P\": "
                + isPalindrome("0P"));

        System.out.println("Is simple palindrome \"racecar\": "
                + isSimplePalindrome("racecar"));

        System.out.println("Is simple palindrome \"abc\": "
                + isSimplePalindrome("abc"));
    }

    /*
    =====================================================================================
    FUNCTION: isPalindrome
    -------------------------------------------------------------------------------------
    Checks whether a string is a palindrome considering ONLY alphanumeric characters.

    LOGIC:
    1. Initialize two pointers: left (start), right (end)
    2. Skip non-alphanumeric characters from both ends
    3. Compare characters ignoring case
    4. If mismatch occurs → not a palindrome
    5. Continue until pointers meet

    Time Complexity : O(n)
    Space Complexity: O(1)
    =====================================================================================
    */
    static boolean isPalindrome(String s) {

        int left = 0;
        int right = s.length() - 1;

        while (left < right) {

            // Skip non-alphanumeric characters from left
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }

            // Skip non-alphanumeric characters from right
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }

            // Compare characters ignoring case
            if (Character.toLowerCase(s.charAt(left)) !=
                Character.toLowerCase(s.charAt(right))) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    /*
    =====================================================================================
    FUNCTION: isSimplePalindrome
    -------------------------------------------------------------------------------------
    Checks whether a simple string (already sanitized: letters/digits only)
    is a palindrome.

    LOGIC:
    • Compare characters from both ends
    • No skipping required
    • Ignore case during comparison

    Time Complexity : O(n)
    Space Complexity: O(1)
    =====================================================================================
    */
    static boolean isSimplePalindrome(String s) {

        int left = 0;
        int right = s.length() - 1;

        while (left < right) {

            if (Character.toLowerCase(s.charAt(left)) !=
                Character.toLowerCase(s.charAt(right))) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }
}
