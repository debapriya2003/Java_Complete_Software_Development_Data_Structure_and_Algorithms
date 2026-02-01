package binary_trees.mid;

import java.util.*;

/**
 * Maximum Path Sum in a Binary Tree
 *
 * ---------------------------------------------------------
 * Problem:
 * ---------------------------------------------------------
 * Given the root of a Binary Tree, find the maximum path sum.
 *
 * A path is defined as:
 * - Any sequence of nodes where each pair of adjacent nodes
 *   has an edge connecting them
 * - The path does NOT need to pass through the root
 * - The path must contain at least one node
 *
 * ---------------------------------------------------------
 * Important Notes:
 * ---------------------------------------------------------
 * - The path can start and end at ANY node
 * - Node values can be NEGATIVE
 * - You cannot reuse a node in a path
 *
 * ---------------------------------------------------------
 * Key Insight:
 * ---------------------------------------------------------
 * For every node, there are two values to consider:
 *
 * 1️⃣ Maximum path sum starting from this node and going DOWN
 *    (used by parent)
 *
 * 2️⃣ Maximum path sum passing THROUGH this node
 *    (used to update global answer)
 *
 * ---------------------------------------------------------
 * For a node with value x:
 *
 * leftGain  = max(0, max path sum from left child)
 * rightGain = max(0, max path sum from right child)
 *
 * Path THROUGH node = x + leftGain + rightGain
 *
 * Path DOWN from node = x + max(leftGain, rightGain)
 *
 * ---------------------------------------------------------
 * Example:
 * ---------------------------------------------------------
 *
 *                -10
 *               /    \
 *              9      20
 *                    /  \
 *                   15   7
 *
 * Maximum Path: 15 → 20 → 7
 * Maximum Sum = 42
 *
 * ---------------------------------------------------------
 * Time Complexity: O(n)
 * Space Complexity: O(h)
 */

@SuppressWarnings("unused")
public class MaximumPathSum {

    /**
     * Node structure of Binary Tree
     */
    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    // Global variable to store maximum path sum
    static int maxSum;

    /**
     * Helper method to compute maximum gain from each node
     *
     * @param root current node
     * @return maximum path sum going downward from this node
     */
    private static int maxGain(Node root) {

        // Base case: empty node contributes 0
        if (root == null) {
            return 0;
        }

        // Compute maximum gain from left and right subtrees
        // Ignore negative contributions
        int leftGain = Math.max(0, maxGain(root.left));
        int rightGain = Math.max(0, maxGain(root.right));

        // Path passing through current node
        int currentPathSum = root.data + leftGain + rightGain;

        // Update global maximum
        maxSum = Math.max(maxSum, currentPathSum);

        // Return maximum downward path
        return root.data + Math.max(leftGain, rightGain);
    }

    /**
     * Returns the maximum path sum in the binary tree
     */
    public static int maximumPathSum(Node root) {
        maxSum = Integer.MIN_VALUE;
        maxGain(root);
        return maxSum;
    }

    public static void main(String[] args) {

        System.out.println("=== Maximum Path Sum in Binary Tree ===\n");

        /*
                     -10
                    /    \
                   9      20
                         /  \
                        15   7
         */

        Node root = new Node(-10);
        root.left = new Node(9);
        root.right = new Node(20);
        root.right.left = new Node(15);
        root.right.right = new Node(7);

        int result = maximumPathSum(root);

        System.out.println("Maximum Path Sum: " + result);

        System.out.println("\n" + "=".repeat(60));
        System.out.println("STEP-BY-STEP LOGIC");
        System.out.println("-".repeat(60));
        System.out.println("✔ Negative paths are ignored using max(0, …)");
        System.out.println("✔ Global maximum updated at every node");
        System.out.println("✔ Path can start and end anywhere");

        System.out.println("\n" + "=".repeat(60));
        System.out.println("EDGE CASES");
        System.out.println("-".repeat(60));
        System.out.println("✔ Tree with all negative values");
        System.out.println("✔ Single node tree");
        System.out.println("✔ Skewed trees");

        System.out.println("\n" + "=".repeat(60));
        System.out.println("COMPLEXITY ANALYSIS");
        System.out.println("-".repeat(60));
        System.out.println("Time Complexity  : O(n)");
        System.out.println("Space Complexity : O(h)");

        System.out.println("\nNOTE:");
        System.out.println("✔ This is different from root-to-leaf max sum");
        System.out.println("✔ One of the MOST asked tree problems in interviews");
    }
}
