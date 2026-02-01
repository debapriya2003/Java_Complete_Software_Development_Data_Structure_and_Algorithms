package stacks_queues.monotonic_stack;

import java.util.*;

/**
 * Asteroid Collision
 * 
 * Problem: Asteroids are on a line, with given size and direction.
 *          Right (+) and Left (-).
 *          When two asteroids collide, smaller explodes.
 *          If equal, both explode.
 *          Find final state.
 * 
 * Example: [5, 10, -5]
 * 5 → (right)
 * 10 → (right)
 * -5 ← (left)
 * 
 * 10 and -5 collide: 10 > 5, -5 explodes
 * 5 and 10 both moving right, no collision
 * Result: [5, 10]
 * 
 * Example: [8, -8]
 * 8 → and -8 ← collide: equal size, both explode
 * Result: []
 * 
 * Algorithm: Stack-based approach
 * ================================
 * 1. For each asteroid:
 *    - If moving right (+): push to stack
 *    - If moving left (-):
 *      - Check collision with stack top (moving right)
 *      - Pop smaller ones, both explode if equal
 *      - Only push if no collision or destroyed
 * 2. Handle edge cases: asteroid survives if no collision
 * 
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

public class AsteroidCollision {
    
    /**
     * Simulate asteroid collisions
     */
    public static int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        
        for (int asteroid : asteroids) {
            boolean alive = true;
            
            // Collision only when stack has right-moving and current is left-moving
            while (alive && asteroid < 0 && !stack.isEmpty() && stack.peek() > 0) {
                int top = stack.pop();
                
                if (top < -asteroid) {
                    // Current asteroid survives, top explodes
                    alive = true;
                } else if (top == -asteroid) {
                    // Both explode
                    alive = false;
                } else {
                    // Top survives, current explodes
                    alive = false;
                    stack.push(top);
                }
            }
            
            if (alive) {
                stack.push(asteroid);
            }
        }
        
        int[] result = new int[stack.size()];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        System.out.println("=== Asteroid Collision ===\n");
        
        int[][] testCases = {
            {5, 10, -5},
            {8, -8},
            {10, 2, -5},
            {-2, -1, 1, 2},
            {1, -1, 1, -2}
        };
        
        System.out.println(String.format("%-35s | %-30s", "Asteroids", "Result"));
        System.out.println("-".repeat(70));
        
        for (int[] asteroids : testCases) {
            int[] result = asteroidCollision(asteroids);
            System.out.println(String.format("%-35s | %-30s", 
                Arrays.toString(asteroids), Arrays.toString(result)));
        }
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("DETAILED EXAMPLE: [5, 10, -5]");
        System.out.println("-".repeat(70));
        
        int[] asteroids = {5, 10, -5};
        
        System.out.println("\nVisualization:");
        System.out.println("→5   →10   ←5");
        System.out.println("\nStep-by-step:\n");
        
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < asteroids.length; i++) {
            int asteroid = asteroids[i];
            String direction = asteroid > 0 ? "→" : "←";
            System.out.println("Step " + (i + 1) + ": Asteroid " + Math.abs(asteroid) + " " + direction);
            
            boolean alive = true;
            
            System.out.println("  Stack before: " + stack);
            
            while (alive && asteroid < 0 && !stack.isEmpty() && stack.peek() > 0) {
                int top = stack.pop();
                System.out.println("  Collision: " + top + " → vs " + asteroid + " ←");
                
                if (top < -asteroid) {
                    System.out.println("    " + Math.abs(asteroid) + " > " + top + " → Left moving wins, " + top + " explodes");
                    alive = true;
                } else if (top == -asteroid) {
                    System.out.println("    Equal size → Both explode");
                    alive = false;
                } else {
                    System.out.println("    " + top + " > " + Math.abs(asteroid) + " → Right moving wins, " + asteroid + " explodes");
                    alive = false;
                    stack.push(top);
                }
            }
            
            if (alive) {
                stack.push(asteroid);
                System.out.println("  Asteroid " + asteroid + " survives, pushed to stack");
            } else if (stack.isEmpty() || stack.peek() != asteroid) {
                System.out.println("  Asteroid " + asteroid + " destroyed");
            }
            
            System.out.println("  Stack after: " + stack);
            System.out.println();
        }
        
        int[] result = new int[stack.size()];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }
        
        System.out.println("Final Result: " + Arrays.toString(result));
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("DETAILED EXAMPLE: [8, -8]");
        System.out.println("-".repeat(70));
        
        asteroids = new int[]{8, -8};
        result = asteroidCollision(asteroids);
        
        System.out.println("\nArray: " + Arrays.toString(asteroids));
        System.out.println("Step 1: Push 8 (right-moving)");
        System.out.println("Step 2: Push -8 (left-moving)");
        System.out.println("        Collision: 8 → vs -8 ←");
        System.out.println("        Equal size → Both explode");
        System.out.println("Result: " + Arrays.toString(result));
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("DETAILED EXAMPLE: [10, 2, -5]");
        System.out.println("-".repeat(70));
        
        asteroids = new int[]{10, 2, -5};
        result = asteroidCollision(asteroids);
        
        System.out.println("\nArray: " + Arrays.toString(asteroids));
        System.out.println("Step 1: Push 10");
        System.out.println("Step 2: Push 2 (both moving right)");
        System.out.println("Step 3: Process -5 (left-moving)");
        System.out.println("        Collision with 2: 2 < 5 → 2 explodes, -5 survives");
        System.out.println("        Collision with 10: 10 > 5 → -5 explodes, 10 survives");
        System.out.println("Result: " + Arrays.toString(result));
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("ANOTHER EXAMPLE: [1, -1, 1, -2]");
        System.out.println("-".repeat(70));
        
        asteroids = new int[]{1, -1, 1, -2};
        result = asteroidCollision(asteroids);
        
        System.out.println("\nArray: " + Arrays.toString(asteroids));
        System.out.println("Result: " + Arrays.toString(result));
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("ALGORITHM STEPS:");
        System.out.println("-".repeat(70));
        System.out.println("1. Use stack to track surviving asteroids");
        System.out.println("2. For each asteroid:");
        System.out.println("   - If right-moving (+): always push");
        System.out.println("   - If left-moving (-):");
        System.out.println("     - Check collision with stack top (if right-moving)");
        System.out.println("     - Pop smaller asteroids");
        System.out.println("     - If equal size: both explode");
        System.out.println("     - If larger: current survives");
        System.out.println("3. Push surviving asteroid to stack");
        System.out.println("4. Return stack contents as result");
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("COLLISION RULES:");
        System.out.println("-".repeat(70));
        System.out.println("Right → Right: No collision");
        System.out.println("Left ← Left: No collision");
        System.out.println("Right → Left ←: Collision!");
        System.out.println("  - If right size > left size: left explodes");
        System.out.println("  - If left size > right size: right explodes");
        System.out.println("  - If equal: both explode");
        System.out.println("Left ← Right →: No collision (moving apart)");
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("KEY INSIGHTS:");
        System.out.println("-".repeat(70));
        System.out.println("• Stack stores right-moving asteroids");
        System.out.println("• Only left-moving can cause collisions");
        System.out.println("• Process from left to right");
        System.out.println("• Handle collision immediately");
        System.out.println("• Time: O(n) - each asteroid processed once");
    }
}
