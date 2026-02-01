package strings.medium;

import java.util.*;

public class RomanNumberConversion {

    /*
    =====================================================================================
    PROBLEM: ROMAN NUMBER TO INTEGER AND VICE VERSA
    -------------------------------------------------------------------------------------
    Roman numerals are represented by seven different symbols: I(1), V(5), X(10), L(50), 
    C(100), D(500), M(1000). The challenge is two-fold: convert Roman numerals to integers 
    and integers back to Roman numerals. Key insight for Roman-to-Integer: when a smaller 
    value appears before a larger value (like IV for 4), it represents subtraction. For 
    Integer-to-Roman: break number into 1000s, 100s, 10s, 1s using mapping tables. This 
    requires understanding numeral hierarchies and special subtractive cases.

    Time Complexity: O(n) for Roman to Integer (n = length), O(log num) for Integer to Roman
    Space Complexity: O(1) for both (constant space for mappings)

    Example:
    Input:  "MCMXCIV"
    Output: 1994 (1000 + 900 + 90 + 4)
    =====================================================================================
    */
    
    /**
     * Converts Roman numeral string to integer using HashMap lookup.
     * Strategy: iterate right to left. If current symbol < next symbol (right side),
     * subtract current value (subtractive notation like IV=4). Otherwise add current value.
     * Handles all subtractive cases: IV, IX, XL, XC, CD, CM.
     * 
     * @param s Roman numeral string
     * @return integer value
     */
    public static int romanToInteger(String s) {
        // Map of Roman symbols to their integer values
        Map<Character, Integer> romanMap = new HashMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);
        
        int result = 0;
        int prevValue = 0;
        
        // Process from right to left
        for (int i = s.length() - 1; i >= 0; i--) {
            int currentValue = romanMap.get(s.charAt(i));
            
            if (currentValue < prevValue) {
                // Subtractive case: smaller before larger means subtract
                result -= currentValue;
            } else {
                // Standard case: add the value
                result += currentValue;
            }
            
            prevValue = currentValue;
        }
        
        return result;
    }
    
    /**
     * Converts integer to Roman numeral string using array of values and symbols.
     * Strategy: use array of values in descending order (1000, 900, 500, 400, etc).
     * For each value, divide number and append corresponding symbol(s). Handles
     * subtractive notation cases (400=CD, 900=CM, 40=XL, 90=XC, 4=IV, 9=IX).
     * 
     * @param num integer value (1-3999)
     * @return Roman numeral string
     */
    public static String integerToRoman(int num) {
        // Arrays of values and symbols in descending order
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < values.length; i++) {
            while (num >= values[i]) {
                result.append(symbols[i]);
                num -= values[i];
            }
        }
        
        return result.toString();
    }
    
    /**
     * Alternative approach for Integer-to-Roman using string replacement.
     * Builds string by converting from thousands down to ones place.
     * Uses pattern of symbols for each digit position (thousands, hundreds, tens, ones).
     * Slightly different approach but handles subtractive notation elegantly.
     * 
     * @param num integer value
     * @return Roman numeral string
     */
    public static String integerToRomanAlternative(int num) {
        String[] ones = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] thousands = {"", "M", "MM", "MMM"};
        
        return thousands[num / 1000] + 
               hundreds[(num % 1000) / 100] + 
               tens[(num % 100) / 10] + 
               ones[num % 10];
    }
    
    /**
     * Converts Roman to Integer using array-based lookup instead of HashMap.
     * More efficient than HashMap for this specific use case. Handles
     * subtractive notation by checking if current < next (right-to-left scan).
     * 
     * @param s Roman numeral
     * @return integer value
     */
    public static int romanToIntegerArray(String s) {
        int[] values = new int[256];
        values['I'] = 1;
        values['V'] = 5;
        values['X'] = 10;
        values['L'] = 50;
        values['C'] = 100;
        values['D'] = 500;
        values['M'] = 1000;
        
        int result = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            int current = values[s.charAt(i)];
            int next = (i + 1 < s.length()) ? values[s.charAt(i + 1)] : 0;
            
            if (current < next) {
                result -= current;  // Subtractive case
            } else {
                result += current;  // Additive case
            }
        }
        
        return result;
    }
    
    /**
     * Helper function to validate if string is valid Roman numeral format.
     * Checks that string contains only valid Roman symbols and follows
     * basic rules (no more than 3 consecutive same symbols, etc).
     * 
     * @param s Roman numeral string
     * @return true if valid Roman numeral, false otherwise
     */
    public static boolean isValidRoman(String s) {
        String validPattern = "^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";
        return s.matches(validPattern);
    }
    
    /**
     * Helper function to get integer value of single Roman symbol.
     * Useful for debugging and understanding symbol mapping.
     * 
     * @param symbol single Roman numeral character
     * @return integer value of symbol
     */
    public static int getSymbolValue(char symbol) {
        return switch(symbol) {
            case 'I' -> 1;
            case 'V' -> 5;
            case 'X' -> 10;
            case 'L' -> 50;
            case 'C' -> 100;
            case 'D' -> 500;
            case 'M' -> 1000;
            default -> 0;
        };
    }
    
    public static void main(String[] args) {
        // Roman to Integer
        System.out.println("Roman to Integer:");
        System.out.println("\"III\" = " + romanToInteger("III"));           // 3
        System.out.println("\"LVIII\" = " + romanToInteger("LVIII"));       // 58
        System.out.println("\"MCMXCIV\" = " + romanToInteger("MCMXCIV"));   // 1994
        System.out.println("\"IV\" = " + romanToInteger("IV"));             // 4
        System.out.println("\"IX\" = " + romanToInteger("IX"));             // 9
        System.out.println("\"XLII\" = " + romanToInteger("XLII"));         // 42
        
        // Integer to Roman
        System.out.println("\nInteger to Roman:");
        System.out.println("3 = \"" + integerToRoman(3) + "\"");            // "III"
        System.out.println("58 = \"" + integerToRoman(58) + "\"");          // "LVIII"
        System.out.println("1994 = \"" + integerToRoman(1994) + "\"");      // "MCMXCIV"
        System.out.println("4 = \"" + integerToRoman(4) + "\"");            // "IV"
        System.out.println("9 = \"" + integerToRoman(9) + "\"");            // "IX"
        System.out.println("3999 = \"" + integerToRoman(3999) + "\"");      // "MMMCMXCIX"
        
        // Validation
        System.out.println("\nValidation:");
        System.out.println("Is \"MCMXCIV\" valid: " + isValidRoman("MCMXCIV"));    // true
        System.out.println("Is \"IIII\" valid: " + isValidRoman("IIII"));          // false
        System.out.println("Is \"IC\" valid: " + isValidRoman("IC"));              // false
        
        // Round trip test
        System.out.println("\nRound trip test:");
        String roman = "MCMXCIV";
        int num = romanToInteger(roman);
        String backToRoman = integerToRoman(num);
        System.out.println(roman + " -> " + num + " -> " + backToRoman);
    }
}
