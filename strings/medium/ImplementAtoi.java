package strings.medium;

public class ImplementAtoi {

    /*
    =====================================================================================
    PROBLEM: IMPLEMENT ATOI (STRING TO INTEGER)
    -------------------------------------------------------------------------------------
    Implement the function myAtoi(String s) that converts a string into a
    32-bit signed integer.

    The conversion follows these rules:
    • Ignore leading whitespace
    • Read an optional '+' or '-' sign
    • Read consecutive digits until a non-digit is encountered
    • If no digits are found, return 0
    • If the integer overflows, clamp the value to:
        - Integer.MIN_VALUE  (-2^31)
        - Integer.MAX_VALUE  (2^31 - 1)

    This problem tests:
    • String parsing
    • Edge case handling
    • Integer overflow control

    Example:
    Input : "   -42"
    Output: -42

    Input : "-91283472332"
    Output: -2147483648
    =====================================================================================
    */

    public static void main(String[] args) {

        System.out.println("myAtoi(\"42\") = " + myAtoi("42"));
        System.out.println("myAtoi(\"   -42\") = " + myAtoi("   -42"));
        System.out.println("myAtoi(\"4193 with words\") = " + myAtoi("4193 with words"));
        System.out.println("myAtoi(\"words and 987\") = " + myAtoi("words and 987"));
        System.out.println("myAtoi(\"-91283472332\") = " + myAtoi("-91283472332"));
        System.out.println("myAtoi(\"\") = " + myAtoi(""));
        System.out.println("myAtoi(\"   \") = " + myAtoi("   "));
        System.out.println("myAtoi(\"+1\") = " + myAtoi("+1"));
        System.out.println("myAtoi(\"+-1\") = " + myAtoi("+-1"));
        System.out.println("myAtoi(\"2147483648\") = " + myAtoi("2147483648"));

        System.out.println("\nUsing explicit implementation:");
        System.out.println("myAtoiExplicit(\"-42\") = " + myAtoiExplicit("-42"));
        System.out.println("myAtoiExplicit(\"91283472332\") = " + myAtoiExplicit("91283472332"));
    }

    /*
    =====================================================================================
    FUNCTION: myAtoi
    -------------------------------------------------------------------------------------
    Converts a string into a 32-bit signed integer using a single-pass approach.

    ALGORITHM:
    1. Skip leading whitespaces
    2. Read optional sign
    3. Convert digits into number
    4. Detect overflow using long
    5. Clamp result if overflow occurs

    Time Complexity  : O(n)
    Space Complexity : O(1)
    =====================================================================================
    */
    static int myAtoi(String s) {

        int i = 0;
        int n = s.length();
        int sign = 1;
        long result = 0;

        // Step 1: Skip leading whitespace
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        // Step 2: Read sign if present
        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            sign = (s.charAt(i) == '-') ? -1 : 1;
            i++;
        }

        // Step 3: Convert digits
        while (i < n && Character.isDigit(s.charAt(i))) {

            int digit = s.charAt(i) - '0';
            result = result * 10 + digit;

            // Step 4: Overflow check
            if (result > Integer.MAX_VALUE) {
                return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            i++;
        }

        return (int) (result * sign);
    }

    /*
    =====================================================================================
    FUNCTION: myAtoiExplicit
    -------------------------------------------------------------------------------------
    Alternative implementation with more explicit character validation.

    FEATURES:
    • Handles null and empty strings
    • Stops immediately on invalid characters
    • Uses long for overflow detection

    Time Complexity  : O(n)
    Space Complexity : O(1)
    =====================================================================================
    */
    static int myAtoiExplicit(String s) {

        if (s == null || s.length() == 0) {
            return 0;
        }

        int i = 0;
        int sign = 1;
        long result = 0;

        // Skip leading whitespace
        while (i < s.length() && s.charAt(i) == ' ') {
            i++;
        }

        // End reached after spaces
        if (i == s.length()) {
            return 0;
        }

        // Read sign
        if (s.charAt(i) == '+' || s.charAt(i) == '-') {
            sign = (s.charAt(i) == '-') ? -1 : 1;
            i++;
        }

        // Parse digits
        while (i < s.length() && Character.isDigit(s.charAt(i))) {

            int digit = s.charAt(i) - '0';
            result = result * 10 + digit;

            // Overflow protection
            if (result > Integer.MAX_VALUE) {
                return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            i++;
        }

        return (int) (result * sign);
    }
}
