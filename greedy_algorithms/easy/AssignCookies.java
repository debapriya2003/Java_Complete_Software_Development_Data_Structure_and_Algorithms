package greedy_algorithms.easy;
import java.util.*;

/**
 * Assign Cookies
 *
 * Problem:
 * You are given two integer arrays:
 * - g[] where g[i] represents the greed factor of the i-th child
 * - s[] where s[j] represents the size of the j-th cookie
 *
 * A child i will be content if assigned a cookie j such that:
 *     s[j] >= g[i]
 *
 * Each child can get at most one cookie, and each cookie can be assigned to at most one child.
 *
 * Task:
 * Return the maximum number of children that can be satisfied.
 *
 * ---------------------------------------------------------
 * Approach 1: Brute Force (Inefficient Conceptual Approach)
 * ---------------------------------------------------------
 * - For each child, try to find a cookie that satisfies them
 * - Mark cookies as used
 * - Time Complexity: O(n * m)
 * - Space Complexity: O(m)
 *
 * ---------------------------------------------------------
 * Approach 2: Greedy (Optimal Solution)
 * ---------------------------------------------------------
 * - Sort both arrays
 * - Try to satisfy the least greedy child with the smallest possible cookie
 * - If the cookie satisfies the child, move both pointers
 * - Otherwise, try a bigger cookie
 *
 * Greedy Justification:
 * - Assigning the smallest sufficient cookie avoids wasting larger cookies
 * - This maximizes total satisfied children
 *
 * ---------------------------------------------------------
 * Example:
 * g = [1, 2, 3]
 * s = [1, 1]
 *
 * Sorted:
 * g = [1, 2, 3]
 * s = [1, 1]
 *
 * Child 1 gets cookie 1 ✔
 * Child 2 cannot be satisfied ❌
 *
 * Output: 1
 *
 * ---------------------------------------------------------
 * Time Complexity: O(n log n + m log m)
 * Space Complexity: O(1) (ignoring sorting space)
 */

public class AssignCookies {

    /**
     * Greedy approach to assign cookies optimally
     *
     * @param g greed factors of children
     * @param s sizes of cookies
     * @return maximum number of content children
     */
    public static int findContentChildren(int[] g, int[] s) {
        // Sort greed factors and cookie sizes
        Arrays.sort(g);
        Arrays.sort(s);

        int child = 0;   // Pointer for children
        int cookie = 0;  // Pointer for cookies
        int contentChildren = 0;

        // Traverse both arrays
        while (child < g.length && cookie < s.length) {

            // If cookie can satisfy the child
            if (s[cookie] >= g[child]) {
                contentChildren++; // Child is satisfied
                child++;           // Move to next child
                cookie++;          // Move to next cookie
            } else {
                // Cookie too small, try a larger one
                cookie++;
            }
        }

        return contentChildren;
    }

    /**
     * Brute force approach (for understanding only)
     *
     * @param g greed factors
     * @param s cookie sizes
     * @return number of satisfied children
     */
    public static int findContentChildrenBruteForce(int[] g, int[] s) {
        boolean[] used = new boolean[s.length];
        int count = 0;

        for (int i = 0; i < g.length; i++) {
            for (int j = 0; j < s.length; j++) {
                if (!used[j] && s[j] >= g[i]) {
                    used[j] = true;
                    count++;
                    break;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {

        System.out.println("=== Assign Cookies Problem ===\n");

        int[][] childrenTests = {
                {1, 2, 3},
                {1, 2},
                {1, 2, 3},
                {2, 3, 4},
                {}
        };

        int[][] cookiesTests = {
                {1, 1},
                {1, 2, 3},
                {3},
                {1, 2},
                {1, 2}
        };

        System.out.println(String.format("%-20s | %-20s | %-10s",
                "Greed Factors", "Cookie Sizes", "Satisfied"));
        System.out.println("-".repeat(60));

        for (int i = 0; i < childrenTests.length; i++) {
            int result = findContentChildren(childrenTests[i], cookiesTests[i]);

            System.out.println(String.format("%-20s | %-20s | %-10d",
                    Arrays.toString(childrenTests[i]),
                    Arrays.toString(cookiesTests[i]),
                    result));
        }

        System.out.println("\n" + "=".repeat(60));
        System.out.println("DETAILED WALKTHROUGH EXAMPLE");
        System.out.println("-".repeat(60));

        int[] g = {1, 2, 3};
        int[] s = {1, 1};

        System.out.println("Children Greed: " + Arrays.toString(g));
        System.out.println("Cookie Sizes : " + Arrays.toString(s));

        Arrays.sort(g);
        Arrays.sort(s);

        System.out.println("\nAfter Sorting:");
        System.out.println("Greed:  " + Arrays.toString(g));
        System.out.println("Cookies:" + Arrays.toString(s));

        int i = 0, j = 0;

        while (i < g.length && j < s.length) {
            System.out.println("\nTrying to satisfy child with greed " + g[i]
                    + " using cookie size " + s[j]);

            if (s[j] >= g[i]) {
                System.out.println("✔ Child satisfied");
                i++;
                j++;
            } else {
                System.out.println("✖ Cookie too small, trying next");
                j++;
            }
        }

        System.out.println("\nTotal satisfied children: " + i);

        System.out.println("\n" + "=".repeat(60));
        System.out.println("COMPLEXITY ANALYSIS");
        System.out.println("-".repeat(60));
        System.out.println("Brute Force Time Complexity: O(n * m)");
        System.out.println("Greedy Time Complexity:      O(n log n + m log m)");
        System.out.println("Space Complexity:            O(1)");
    }
}

