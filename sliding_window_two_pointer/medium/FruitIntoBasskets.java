package sliding_window_two_pointer.medium;

import java.util.*;

/**
 * Fruit Into Baskets (At Most 2 Characters)
 * 
 * Problem: You have two baskets and want to put fruit from trees in a row.
 *          Each basket can contain at most one type of fruit.
 *          Find maximum number of fruits you can collect.
 * 
 * Example: [1, 2, 1] → 3 (take all: basket1=[1,1], basket2=[2])
 *          [0, 1, 2, 2] → 3 (take [1, 2, 2])
 *          [1, 2, 3, 2, 2] → 4 (take [2, 3, 2, 2])
 *          [3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4] → 5 ([1, 2, 1, 1, 2])
 * 
 * Algorithm: Sliding Window with At Most 2 Types
 * ==============================================
 * 1. Use sliding window approach
 * 2. Maintain HashMap of fruit types and their counts
 * 3. Expand window: add new fruit
 * 4. If more than 2 types: shrink window from left
 * 5. Track maximum length
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(1) - at most 2 fruits in hashmap
 */

public class FruitIntoBasskets {
    
    /**
     * Find maximum fruits with at most 2 types
     */
    public static int totalFruit(int[] fruits) {
        Map<Integer, Integer> fruitCount = new HashMap<>();
        int left = 0;
        int maxFruits = 0;
        
        for (int right = 0; right < fruits.length; right++) {
            // Add current fruit
            fruitCount.put(fruits[right], fruitCount.getOrDefault(fruits[right], 0) + 1);
            
            // Shrink window if more than 2 types
            while (fruitCount.size() > 2) {
                fruitCount.put(fruits[left], fruitCount.get(fruits[left]) - 1);
                if (fruitCount.get(fruits[left]) == 0) {
                    fruitCount.remove(fruits[left]);
                }
                left++;
            }
            
            // Update max
            maxFruits = Math.max(maxFruits, right - left + 1);
        }
        
        return maxFruits;
    }
    
    public static void main(String[] args) {
        System.out.println("=== Fruit Into Baskets ===\n");
        
        int[][] testCases = {
            {1, 2, 1},
            {0, 1, 2, 2},
            {1, 2, 3, 2, 2},
            {3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4},
            {1, 1, 1},
            {1}
        };
        
        System.out.println(String.format("%-45s | %-10s", "Fruits", "Max Count"));
        System.out.println("-".repeat(60));
        
        for (int[] fruits : testCases) {
            int result = totalFruit(fruits);
            System.out.println(String.format("%-45s | %-10d", 
                Arrays.toString(fruits), result));
        }
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("DETAILED EXAMPLE: [3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4]");
        System.out.println("-".repeat(60));
        
        int[] fruits = {3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4};
        
        System.out.println("\nSliding window trace:\n");
        System.out.println(String.format("%-8s | %-8s | %-10s | %-20s | %-10s", 
            "Left", "Right", "Fruit", "Basket (type→count)", "Window Size"));
        System.out.println("-".repeat(65));
        
        Map<Integer, Integer> fruitCount = new HashMap<>();
        int left = 0;
        int maxFruits = 0;
        int maxLeft = 0, maxRight = -1;
        
        for (int right = 0; right < fruits.length; right++) {
            fruitCount.put(fruits[right], fruitCount.getOrDefault(fruits[right], 0) + 1);
            
            System.out.print(String.format("%-8d | %-8d | %-10d | ", left, right, fruits[right]));
            
            while (fruitCount.size() > 2) {
                fruitCount.put(fruits[left], fruitCount.get(fruits[left]) - 1);
                if (fruitCount.get(fruits[left]) == 0) {
                    fruitCount.remove(fruits[left]);
                }
                left++;
            }
            
            int windowSize = right - left + 1;
            if (windowSize > maxFruits) {
                maxFruits = windowSize;
                maxLeft = left;
                maxRight = right;
            }
            
            System.out.println(String.format("%-20s | %-10d", 
                fruitCount, windowSize));
        }
        
        System.out.println("\nMax window: [" + maxLeft + ", " + maxRight + "] = " + 
                         Arrays.toString(Arrays.copyOfRange(fruits, maxLeft, maxRight + 1)));
        System.out.println("Max fruits: " + maxFruits);
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("ANOTHER EXAMPLE: [0, 1, 2, 2]");
        System.out.println("-".repeat(60));
        
        fruits = new int[]{0, 1, 2, 2};
        System.out.println("\nArray: " + Arrays.toString(fruits));
        System.out.println("\nExploration:");
        System.out.println("[0]: {0→1}, size=1");
        System.out.println("[0,1]: {0→1, 1→1}, size=2");
        System.out.println("[0,1,2]: {0→1, 1→1, 2→1}, size=3, MORE THAN 2!");
        System.out.println("  Shrink from left: remove 0");
        System.out.println("  Window [1,2]: {1→1, 2→1}, size=2");
        System.out.println("[1,2,2]: {1→1, 2→2}, size=3");
        System.out.println("\nMax: 3");
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("ALGORITHM EXPLANATION:");
        System.out.println("-".repeat(60));
        System.out.println("1. Use sliding window with left/right pointers");
        System.out.println("2. HashMap tracks count of each fruit type in window");
        System.out.println("3. Expand window: add fruit at right");
        System.out.println("4. If more than 2 types:");
        System.out.println("   - Remove fruit from left until 2 types remain");
        System.out.println("5. Track maximum window size");
        System.out.println("6. Each element added/removed at most once");
        
        System.out.println("\n" + "=".repeat(60));
        System.out.println("KEY POINTS:");
        System.out.println("-".repeat(60));
        System.out.println("1. At most 2 distinct fruit types in window");
        System.out.println("2. Window is always VALID (≤2 types)");
        System.out.println("3. Only shrink when size exceeds 2");
        System.out.println("4. HashMap size always ≤ 2");
        System.out.println("5. Time: O(n) - each element processed once");
        System.out.println("6. Space: O(1) - constant size hashmap");
    }
}
