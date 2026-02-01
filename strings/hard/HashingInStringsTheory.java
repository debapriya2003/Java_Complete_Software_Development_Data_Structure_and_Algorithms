package strings.hard;

/**
 * =========================================================
 * HASHING IN STRINGS — THEORY (WRITTEN IN CODE FORM)
 * =========================================================
 *
 * ---------------------------------------------------------
 * 1. WHAT IS HASHING?
 * ---------------------------------------------------------
 * Hashing is a technique to map data (like strings) to a
 * fixed-size numerical value using a hash function.
 *
 * In strings, hashing helps us:
 * ✔ Compare strings efficiently
 * ✔ Count frequency of characters/substrings
 * ✔ Detect duplicates
 * ✔ Optimize pattern matching
 *
 * ---------------------------------------------------------
 * 2. WHY HASHING IS IMPORTANT IN STRING PROBLEMS
 * ---------------------------------------------------------
 * Direct string comparison:
 *   Time = O(length of string)
 *
 * Hash comparison:
 *   Time = O(1)
 *
 * Hence hashing is heavily used in:
 * - Anagram checking
 * - Substring problems
 * - Pattern matching (Rabin–Karp)
 * - Palindrome checking
 *
 * ---------------------------------------------------------
 * 3. TYPES OF HASHING IN STRINGS
 * ---------------------------------------------------------
 *
 * (A) CHARACTER FREQUENCY HASHING
 * --------------------------------
 * - Used when characters come from a LIMITED alphabet
 * - Example: lowercase letters ('a'–'z')
 *
 * Data Structure:
 *   int[26] freq
 *
 * Use cases:
 * ✔ Anagram
 * ✔ Character count
 *
 * --------------------------------
 * (B) HASH MAP BASED HASHING
 * --------------------------------
 * - Used when character set is LARGE or unknown
 * - Uses HashMap<Character, Integer>
 *
 * Use cases:
 * ✔ Unicode strings
 * ✔ Variable alphabets
 *
 * --------------------------------
 * (C) STRING HASHING (ROLLING HASH)
 * --------------------------------
 * - Converts entire string into a single number
 * - Used for substring comparison
 *
 * Use cases:
 * ✔ Rabin–Karp Algorithm
 * ✔ Longest duplicate substring
 *
 * ---------------------------------------------------------
 * 4. CHARACTER FREQUENCY HASHING (ARRAY METHOD)
 * ---------------------------------------------------------
 * Example:
 * String s = "abbca"
 *
 * freq['a'] = 2
 * freq['b'] = 2
 * freq['c'] = 1
 *
 * Code idea:
 *   freq[s.charAt(i) - 'a']++
 *
 * Time: O(n)
 * Space: O(1) (fixed size array)
 *
 * ---------------------------------------------------------
 * 5. HASH MAP BASED STRING HASHING
 * ---------------------------------------------------------
 * Useful when:
 * - Characters are not limited to 'a'–'z'
 *
 * Example:
 * Map<Character, Integer> map
 *
 * Code idea:
 *   map.put(ch, map.getOrDefault(ch, 0) + 1)
 *
 * Time: O(n)
 * Space: O(n)
 *
 * ---------------------------------------------------------
 * 6. ROLLING HASH (STRING HASHING)
 * ---------------------------------------------------------
 * A string is treated as a number in a base system.
 *
 * Formula:
 *   hash = s[0]*p^0 + s[1]*p^1 + s[2]*p^2 + ...
 *
 * Where:
 * - p is a base (commonly 31 or 53)
 * - Mod is used to prevent overflow
 *
 * ---------------------------------------------------------
 * 7. WHY MODULO IS REQUIRED?
 * ---------------------------------------------------------
 * Hash values grow very large quickly.
 *
 * Using:
 *   hash % MOD
 *
 * Keeps values in range and avoids overflow.
 *
 * Common MOD values:
 * ✔ 1_000_000_007
 * ✔ 1_000_000_009
 *
 * ---------------------------------------------------------
 * 8. HASH COLLISIONS
 * ---------------------------------------------------------
 * Collision:
 * - Two different strings produce the same hash
 *
 * Solutions:
 * ✔ Use large MOD
 * ✔ Use double hashing
 * ✔ Verify strings if hashes match
 *
 * ---------------------------------------------------------
 * 9. APPLICATIONS OF HASHING IN STRINGS
 * ---------------------------------------------------------
 * ✔ Anagram checking
 * ✔ Pattern matching (Rabin–Karp)
 * ✔ Longest substring without repetition
 * ✔ Duplicate substring detection
 * ✔ Palindrome checking
 *
 * ---------------------------------------------------------
 * 10. ONE-LINE SUMMARY (INTERVIEW GOLD)
 * ---------------------------------------------------------
 * Hashing converts strings into numeric representations
 * for fast comparison and lookup.
 *
 * =========================================================
 * DEMONSTRATION CODE
 * =========================================================
 */

public class HashingInStringsTheory {

    /**
     * CHARACTER FREQUENCY HASHING (a–z)
     */
    public static int[] frequencyHash(String s) {

        int[] freq = new int[26];

        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }
        return freq;
    }

    /**
     * HASH MAP BASED HASHING
     */
    public static java.util.Map<Character, Integer> hashMapHash(String s) {

        java.util.Map<Character, Integer> map = new java.util.HashMap<>();

        for (char ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        return map;
    }

    /**
     * SIMPLE ROLLING HASH IMPLEMENTATION
     */
    public static long rollingHash(String s) {

        long hash = 0;
        long p = 31;
        long pow = 1;
        long mod = 1_000_000_007;

        for (char ch : s.toCharArray()) {
            hash = (hash + (ch - 'a' + 1) * pow) % mod;
            pow = (pow * p) % mod;
        }
        return hash;
    }

    public static void main(String[] args) {

        System.out.println("=== Hashing in Strings (Theory) ===\n");

        String s = "abbca";

        int[] freq = frequencyHash(s);
        System.out.println("Frequency of 'a': " + freq['a' - 'a']);
        System.out.println("Frequency of 'b': " + freq['b' - 'a']);

        System.out.println("\nHashMap Hash:");
        System.out.println(hashMapHash(s));

        System.out.println("\nRolling Hash:");
        System.out.println(rollingHash(s));

        System.out.println("\n" + "=".repeat(60));
        System.out.println("INTERVIEW TAKEAWAYS:");
        System.out.println("✔ Array hashing for fixed alphabets");
        System.out.println("✔ HashMap for flexible character sets");
        System.out.println("✔ Rolling hash for substring problems");
    }
}
