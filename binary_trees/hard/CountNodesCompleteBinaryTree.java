package binary_trees.hard;

import java.util.*;

/**
 * Count Total Nodes in a COMPLETE Binary Tree
 *
 * ---------------------------------------------------------
 * Problem:
 * ---------------------------------------------------------
 * Given the root of a COMPLETE Binary Tree, count the total
 * number of nodes efficiently.
 *
 * ---------------------------------------------------------
 * Definition: Complete Binary Tree
 * ---------------------------------------------------------
 * A Binary Tree is COMPLETE if:
 * - All levels are completely filled except possibly the last
 * - The last level is filled from LEFT to RIGHT
 *
 * ---------------------------------------------------------
 * Brute Force Approach (Not Optimal):
 * ---------------------------------------------------------
 * - Traverse all nodes (DFS / BFS)
 * - Count nodes one by one
 * - Time Complexity: O(n)
 *
 * ---------------------------------------------------------
 * Optimized Approach (Using Tree Properties) ✅
 * ---------------------------------------------------------
 * Key Insight:
 * - In a complete binary tree:
 *      If left height == right height
 *      → Tree is a PERFECT binary tree
 *
 * Perfect Binary Tree:
 * - Total nodes = 2^height - 1
 *
 * ---------------------------------------------------------
 * Algorithm:
 * ---------------------------------------------------------
 * 1. Compute left height (go left-most)
 * 2. Compute right height (go right-most)
 * 3. If heights are equal:
 *      → return (2^height - 1)
 * 4. Else:
 *      → recursively count left and right subtrees
 *
 * ---------------------------------------------------------
 * Example:
 * ---------------------------------------------------------
 *
 *               1
 *             /   \
 *            2     3
 *           / \   /
 *          4   5 6
 *
 * Left height  = 3
 * Right height = 2
 *
 * Total nodes = 6
 *
 * ---------------------------------------------------------
 * Time Complexity:
 * ---------------------------------------------------------
 * O(log² n)
 *
 * Space Complexity:
 * ---------------------------------------------------------
 * O(log n) (recursive stack)
 */

@SuppressWarnings("unused")
public class CountNodesCompleteBinaryTree {

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

    /**
     * Counts total nodes in a complete binary tree
     *
     * @param root root of the tree
     * @return total number of nodes
     */
    public static int countNodes(Node root) {

        if (root == null) return 0;

        int leftHeight = getLeftHeight(root);
        int rightHeight = getRightHeight(root);

        // If tree is perfect
        if (leftHeight == rightHeight) {
            return (1 << leftHeight) - 1;
        }

        // Otherwise recurse
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    /**
     * Computes height by moving left
     */
    private static int getLeftHeight(Node node) {
        int height = 0;
        while (node != null) {
            height++;
            node = node.left;
        }
        return height;
    }

    /**
     * Computes height by moving right
     */
    private static int getRightHeight(Node node) {
        int height = 0;
        while (node != null) {
            height++;
            node = node.right;
        }
        return height;
    }

    public static void main(String[] args) {

        System.out.println("=== Count Nodes in Complete Binary Tree ===\n");

        /*
                   1
                 /   \
                2     3
               / \   /
              4   5 6
         */

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);

        int totalNodes = countNodes(root);

        System.out.println("Total Nodes in Complete Binary Tree: " + totalNodes);

        System.out.println("\n" + "=".repeat(60));
        System.out.println("WHY THIS WORKS");
        System.out.println("-".repeat(60));
        System.out.println("✔ Complete tree allows height comparison");
        System.out.println("✔ Perfect subtree nodes counted in O(1)");
        System.out.println("✔ Avoids visiting every node");

        System.out.println("\n" + "=".repeat(60));
        System.out.println("COMPLEXITY ANALYSIS");
        System.out.println("-".repeat(60));
        System.out.println("Time Complexity  : O(log² n)");
        System.out.println("Space Complexity : O(log n)");

        System.out.println("\nNOTE:");
        System.out.println("✔ This optimization ONLY works for COMPLETE trees");
        System.out.println("✔ For general trees, DFS/BFS is required");
        System.out.println("✔ Very common FAANG interview question");
    }
}
