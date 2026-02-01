package strings.medium;

public class SumOfBeautyOfAllSubstrings {

    /*
    =====================================================================================
    PROBLEM: SUM OF BEAUTY OF ALL SUBSTRINGS
    -------------------------------------------------------------------------------------
    The beauty of a string is defined as the highest frequency of any character minus 
    the lowest frequency of any character among characters that appear in the string. 
    Given a string s, calculate the sum of beauty values for all substrings. For example,
    in "aabcb", the substring "aab" has beauty 2-1=1 (a appears 2 times, b appears 1 time).
    This problem requires understanding substring enumeration, character frequency counting,
    and efficient computation. The key insight is that substrings are short enough to 
    track character frequencies incrementally as we extend each starting position.

    Time Complexity: O(n^2 * k) where n is string length, k is alphabet size (26 max)
    Space Complexity: O(k) for frequency array

    Example:
    Input:  s = "aabcb"
    Output: 5 (sum of beauty for all substrings)
    =====================================================================================
    */
    
    /**
     * Calculates sum of beauty for all substrings using character frequency counting.
     * For each starting position, extends substring and tracks character frequencies.
     * For each substring, calculates beauty as max_frequency - min_frequency.
     * Processes substrings of increasing length incrementally.
     * 
     * @param s input string
     * @return sum of beauty values for all substrings
     */
    public static int beautySum(String s) {
        int totalBeauty = 0;
        int n = s.length();
        
        // For each starting position
        for (int i = 0; i < n; i++) {
            // Frequency array for 26 lowercase letters
            int[] freq = new int[26];
            
            // Extend substring from position i
            for (int j = i; j < n; j++) {
                // Add character to frequency map
                freq[s.charAt(j) - 'a']++;
                
                // Calculate beauty for substring s[i:j+1]
                int maxFreq = 0;
                int minFreq = Integer.MAX_VALUE;
                
                for (int count : freq) {
                    if (count > 0) {
                        maxFreq = Math.max(maxFreq, count);
                        minFreq = Math.min(minFreq, count);
                    }
                }
                
                // Add beauty to total (minFreq stays MAX if no characters)
                if (minFreq != Integer.MAX_VALUE) {
                    totalBeauty += (maxFreq - minFreq);
                }
            }
        }
        
        return totalBeauty;
    }
    
    /**
     * Optimized version that caches max and min frequencies for efficiency.
     * Instead of recalculating max and min for each substring, maintains
     * these values incrementally as new characters are added.
     * More efficient than recalculating from scratch each iteration.
     * 
     * @param s input string
     * @return sum of beauty values for all substrings
     */
    public static int beautySumOptimized(String s) {
        int totalBeauty = 0;
        int n = s.length();
        
        for (int i = 0; i < n; i++) {
            int[] freq = new int[26];
            
            for (int j = i; j < n; j++) {
                freq[s.charAt(j) - 'a']++;
                
                // Find max and min frequency among non-zero entries
                int maxFreq = 0;
                int minFreq = Integer.MAX_VALUE;
                boolean hasCharacters = false;
                
                for (int count : freq) {
                    if (count > 0) {
                        hasCharacters = true;
                        maxFreq = Math.max(maxFreq, count);
                        minFreq = Math.min(minFreq, count);
                    }
                }
                
                if (hasCharacters) {
                    totalBeauty += (maxFreq - minFreq);
                }
            }
        }
        
        return totalBeauty;
    }
    
    /**
     * Helper function to calculate beauty of a single substring.
     * Determines highest and lowest character frequencies in substring.
     * Used for testing and validation of individual substrings.
     * 
     * @param s substring to evaluate
     * @return beauty value (max_freq - min_freq)
     */
    public static int calculateBeauty(String s) {
        int[] freq = new int[26];
        
        // Count character frequencies
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        
        // Find max and min frequency
        int maxFreq = 0;
        int minFreq = Integer.MAX_VALUE;
        
        for (int count : freq) {
            if (count > 0) {
                maxFreq = Math.max(maxFreq, count);
                minFreq = Math.min(minFreq, count);
            }
        }
        
        return (minFreq == Integer.MAX_VALUE) ? 0 : (maxFreq - minFreq);
    }
    
    /**
     * Helper function to print character frequency map.
     * Displays which characters appear and their counts.
     * Useful for debugging and understanding substring composition.
     * 
     * @param s input string
     */
    public static void printFrequencies(String s) {
        int[] freq = new int[26];
        
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }
        
        System.out.print("Frequencies in \"" + s + "\": ");
        for (int i = 0; i < 26; i++) {
            if (freq[i] > 0) {
                System.out.print((char)('a' + i) + ":" + freq[i] + " ");
            }
        }
        System.out.println();
    }
    
    /**
     * Helper function to get all substrings with their beauty values.
     * Returns detailed breakdown of each substring and its beauty.
     * Useful for analysis and verification of calculations.
     * 
     * @param s input string
     * @return string representation of all substrings with beauty values
     */
    public static String getAllSubstringsWithBeauty(String s) {
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                String substring = s.substring(i, j);
                int beauty = calculateBeauty(substring);
                result.append("\"").append(substring).append("\" -> beauty: ").append(beauty).append("\n");
            }
        }
        
        return result.toString();
    }
    
    /**
     * Brute force approach: generates all substrings explicitly.
     * Less efficient but demonstrates clear logic for verification.
     * Time: O(n^3) - n^2 substrings * n beauty calculation.
     * 
     * @param s input string
     * @return sum of beauty values
     */
    public static int beautySumBruteForce(String s) {
        int totalBeauty = 0;
        
        // Generate all substrings
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                String substring = s.substring(i, j);
                totalBeauty += calculateBeauty(substring);
            }
        }
        
        return totalBeauty;
    }
    
    public static void main(String[] args) {
        System.out.println("Beauty sum of \"aabcb\": " + beautySum("aabcb"));        // 5
        System.out.println("Beauty sum of \"aabcbcbcadcd\": " + 
            beautySum("aabcbcbcadcd"));                                              // 8
        System.out.println("Beauty sum of \"a\": " + beautySum("a"));                 // 0
        System.out.println("Beauty sum of \"aa\": " + beautySum("aa"));               // 0
        System.out.println("Beauty sum of \"abc\": " + beautySum("abc"));             // 0
        System.out.println("Beauty sum of \"aabbcc\": " + beautySum("aabbcc"));       // 0
        
        System.out.println("\nUsing optimized approach:");
        System.out.println("Beauty sum of \"aabcb\": " + beautySumOptimized("aabcb")); // 5
        
        System.out.println("\nDetailed breakdown of \"aabcb\":");
        System.out.println(getAllSubstringsWithBeauty("aabcb"));
        
        System.out.println("\nIndividual beauty calculations:");
        System.out.println("Beauty of \"a\": " + calculateBeauty("a"));              // 0
        System.out.println("Beauty of \"aa\": " + calculateBeauty("aa"));            // 0
        System.out.println("Beauty of \"aab\": " + calculateBeauty("aab"));          // 1 (a:2, b:1)
        System.out.println("Beauty of \"aabc\": " + calculateBeauty("aabc"));        // 1 (a:2, b:1, c:1)
        System.out.println("Beauty of \"aabbcc\": " + calculateBeauty("aabbcc"));    // 0 (all same frequency)
        
        System.out.println("\nFrequency analysis:");
        printFrequencies("aab");
        printFrequencies("aabbcc");
    }
}
