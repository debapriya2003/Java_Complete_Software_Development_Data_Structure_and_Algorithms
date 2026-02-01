package binary_trees.mid;

import java.util.*;

/**
 * Diameter of a Binary Tree
 *
 * ---------------------------------------------------------
 * Problem:
 * ---------------------------------------------------------
 * Given the root of a Binary Tree, find the diameter of the tree.
 *
 * ---------------------------------------------------------
 * Definition:
 * ---------------------------------------------------------
 * Diameter of a Binary Tree is the length of the longest path
 * between any two nodes in the tree.
 *
 * Important:
 * - The path may or may NOT pass through the root
 * - Diameter can be measured as:
 *      1. Number of edges (most common in DSA)
 *      2. Number of nodes (edges + 1)
 *
 * 👉 In this program, diameter is measured in TERMS OF EDGES.
 *
 * ---------------------------------------------------------
 * Key Observation:
 * ---------------------------------------------------------
 * For any node:
 * Diameter passing through that node =
 *     height(left subtree) + height(right subtree)
 *
 * Global Diameter =
 *     maximum of all such values
 *
 * ---------------------------------------------------------
 * Approach 1: Naive Recursive (O(n²)) ❌
 * ---------------------------------------------------------
 * - For each node:
 *      - Compute height of left subtree
 *      - Compute height of right subtree
 *      - Compute diameter
 * - Height is recalculated multiple times
 *
 * ---------------------------------------------------------
 * Approach 2: Optimized DFS (Single Traversal) ✅
 * ---------------------------------------------------------
 * - Compute height and diameter together
 * - Use a global variable to track maximum diameter
 *
 * ---------------------------------------------------------
 * Example Binary Tree:
 * ---------------------------------------------------------
 *
 *                1
 *              /   \
 *             2     3
 *            / \
 *           4   5
 *
 * Longest Path: 4 → 2 → 5
 * Diameter = 2 edges
 *
 * ---------------------------------------------------------
 * Time Complexity: O(n)
 * Space Complexity: O(h)
 */

@SuppressWarnings("unused")
public class DiameterOfBinaryTree {

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

    // Stores the maximum diameter found so far
    static int diameter = 0;

    /**
     * Optimized method to calculate diameter
     *
     * @param root root of the binary tree
     * @return height of the tree
     */
    public static int height(Node root) {

        // Base case: empty tree
        if (root == null) {
            return -1;
        }

        // Height of left and right subtrees
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        // Update diameter at current node
        int currentDiameter = leftHeight + rightHeight + 2;
        diameter = Math.max(diameter, currentDiameter);

        // Return height of current node
        return 1 + Math.max(leftHeight, rightHeight);
    }

    /**
     * Returns the diameter of the binary tree
     */
    public static int diameterOfBinaryTree(Node root) {
        diameter = 0;   // reset global variable
        height(root);
        return diameter;
    }

    public static void main(String[] args) {

        System.out.println("=== Diameter of Binary Tree ===\n");

        /*
                 1
               /   \
              2     3
             / \
            4   5
         */

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);

        int result = diameterOfBinaryTree(root);

        System.out.println("Diameter of the Binary Tree (edges): " + result);

        System.out.println("\n" + "=".repeat(60));
        System.out.println("STEP-BY-STEP EXPLANATION");
        System.out.println("-".repeat(60));
        System.out.println("✔ Height computed bottom-up");
        System.out.println("✔ At each node: leftHeight + rightHeight + 2");
        System.out.println("✔ Global maximum maintained");

        System.out.println("\n" + "=".repeat(60));
        System.out.println("EDGE VS NODE BASED DIAMETER");
        System.out.println("-".repeat(60));
        System.out.println("Diameter in edges = " + result);
        System.out.println("Diameter in nodes = " + (result + 1));

        System.out.println("\n" + "=".repeat(60));
        System.out.println("COMPLEXITY ANALYSIS");
        System.out.println("-".repeat(60));
        System.out.println("Time Complexity  : O(n)");
        System.out.println("Space Complexity : O(h)");

        System.out.println("\nNOTE:");
        System.out.println("✔ Diameter may not pass through root");
        System.out.println("✔ This is a classic DFS + height problem");
        System.out.println("✔ Frequently asked in interviews");
    }
}
