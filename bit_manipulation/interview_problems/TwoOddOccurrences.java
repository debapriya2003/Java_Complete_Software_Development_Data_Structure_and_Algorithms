package bit_manipulation.interview_problems;

/**
 * Find the two numbers appearing odd number of times
 * 
 * Problem: Given an array where all numbers appear even number of times except two,
 * find those two numbers.
 * 
 * Approach: Bit Manipulation with XOR
 * 
 * Step 1: XOR all numbers to get result1 = num1 ^ num2
 *         (Because all even occurrence numbers cancel out to 0)
 * 
 * Step 2: Find a bit that is set in result1 (this bit differs between num1 and num2)
 *         Use rightmost set bit: result1 & (-result1) or result1 & ~(result1-1)
 * 
 * Step 3: Partition array into two groups based on this bit
 *         - Group 1: Numbers with this bit set
 *         - Group 2: Numbers with this bit unset
 * 
 * Step 4: XOR each group separately to find num1 and num2
 * 
 * Example: [1, 2, 3, 2, 1, 4]
 * - 1 appears 2 times (even)
 * - 2 appears 2 times (even)
 * - 3 appears 1 time (odd)
 * - 4 appears 1 time (odd)
 * 
 * Result of XOR all = 1 ^ 2 ^ 3 ^ 2 ^ 1 ^ 4 = 3 ^ 4 = 0111 = 7
 * Rightmost set bit = 1 (0001)
 * 
 * Group 1 (bit set):   1 (0001), 3 (0011) -> 1 ^ 3 = 2 (but 1 appears twice)
 * Group 2 (bit unset): 2 (0010), 2 (0010), 4 (0100) -> 2 ^ 2 ^ 4 = 4
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

public class TwoOddOccurrences {
    
    /**
     * Find two numbers appearing odd number of times
     * @param arr array of integers
     * @return array containing the two odd occurrence numbers
     */
    public static int[] findTwoOddNumbers(int[] arr) {
        // Step 1: XOR all elements
        int xorAll = 0;
        for (int num : arr) {
            xorAll ^= num;
        }
        
        // Step 2: Find rightmost set bit in xorAll
        // This bit differs between the two numbers we're looking for
        int rightmostSetBit = xorAll & (-xorAll);
        // Alternative: xorAll & ~(xorAll - 1)
        
        // Step 3: Partition into two groups and XOR each
        int num1 = 0, num2 = 0;
        for (int num : arr) {
            // If bit is set in num, add to num1 group
            if ((num & rightmostSetBit) != 0) {
                num1 ^= num;
            } else {
                num2 ^= num;
            }
        }
        
        return new int[]{num1, num2};
    }
    
    /**
     * Count occurrences of a number in array
     */
    private static int countOccurrence(int[] arr, int num) {
        int count = 0;
        for (int val : arr) {
            if (val == num) count++;
        }
        return count;
    }
    
    public static void main(String[] args) {
        System.out.println("=== Find Two Numbers with Odd Occurrences ===\n");
        
        int[][] testCases = {
            {1, 2, 3, 2, 1, 4},
            {5, 7, 5, 4, 7, 8},
            {10, 20, 10, 30, 20, 40},
            {1, 1, 2, 2, 3, 3, 4, 4, 5, 6},
            {100, 100, 200}
        };
        
        System.out.println(String.format("%-40s | %-20s", "Array", "Two Odd Numbers"));
        System.out.println("-".repeat(65));
        
        for (int[] arr : testCases) {
            int[] result = findTwoOddNumbers(arr);
            
            StringBuilder arrayStr = new StringBuilder("[");
            for (int i = 0; i < arr.length; i++) {
                if (i > 0) arrayStr.append(", ");
                arrayStr.append(arr[i]);
            }
            arrayStr.append("]");
            
            String resultStr = "[" + result[0] + ", " + result[1] + "]";
            System.out.println(String.format("%-40s | %-20s", arrayStr.toString(), resultStr));
            
            // Verify
            int count1 = countOccurrence(arr, result[0]);
            int count2 = countOccurrence(arr, result[1]);
            System.out.println(String.format("  Verification: %d appears %d times, %d appears %d times",
                                           result[0], count1, result[1], count2));
        }
        
        System.out.println("\n" + "=".repeat(65));
        System.out.println("DETAILED EXAMPLE: [1, 2, 3, 2, 1, 4]");
        System.out.println("-".repeat(65));
        
        int[] arr = {1, 2, 3, 2, 1, 4};
        System.out.println("Array: " + java.util.Arrays.toString(arr));
        System.out.println("Element occurrences:");
        System.out.println("  1 -> 2 times (even)");
        System.out.println("  2 -> 2 times (even)");
        System.out.println("  3 -> 1 time (odd)");
        System.out.println("  4 -> 1 time (odd)");
        
        System.out.println("\nStep 1: XOR all elements");
        int xorAll = 0;
        for (int num : arr) {
            int prev = xorAll;
            xorAll ^= num;
            System.out.println("  " + prev + " ^ " + num + " = " + xorAll);
        }
        System.out.println("  Result: " + xorAll + " (binary: " + Integer.toBinaryString(xorAll) + ")");
        
        System.out.println("\nStep 2: Find rightmost set bit");
        int rightmost = xorAll & (-xorAll);
        System.out.println("  xorAll & (-xorAll) = " + xorAll + " & " + (-xorAll) + " = " + rightmost);
        System.out.println("  Rightmost set bit: " + Integer.toBinaryString(rightmost));
        System.out.println("  (This bit differs between 3 and 4)");
        
        System.out.println("\nStep 3: Partition and XOR");
        int num1 = 0, num2 = 0;
        for (int num : arr) {
            if ((num & rightmost) != 0) {
                num1 ^= num;
                System.out.println("  " + num + " has bit set -> add to group 1");
            } else {
                num2 ^= num;
                System.out.println("  " + num + " doesn't have bit -> add to group 2");
            }
        }
        
        System.out.println("\nStep 4: XOR results");
        System.out.println("  Group 1 XOR: " + num1);
        System.out.println("  Group 2 XOR: " + num2);
        System.out.println("  Answer: [" + num1 + ", " + num2 + "]");
        
        System.out.println("\n" + "=".repeat(65));
        System.out.println("WHY THIS WORKS:");
        System.out.println("-".repeat(65));
        System.out.println("1. XOR cancels out even occurrences");
        System.out.println("2. Result = num1 ^ num2 (where num1, num2 appear odd times)");
        System.out.println("3. Rightmost set bit must differ between num1 and num2");
        System.out.println("4. Partitioning separates them into different groups");
        System.out.println("5. XORing each group independently gives num1 and num2");
    }
}
