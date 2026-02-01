package binary_trees.mid;
import java.util.*;

/**
 * Height of a Binary Tree
 *
 * ---------------------------------------------------------
 * Problem:
 * ---------------------------------------------------------
 * Given the root of a Binary Tree, find the height of the tree.
 *
 * ---------------------------------------------------------
 * Definition:
 * ---------------------------------------------------------
 * Height of a Binary Tree is defined as:
 * - The maximum number of edges from the root to any leaf node
 *
 * (Some definitions count number of nodes instead of edges.
 *  Here, we follow the standard DSA definition using edges.)
 *
 * Height of:
 * - Empty tree  = -1
 * - Single node = 0
 *
 * ---------------------------------------------------------
 * Key Observation:
 * ---------------------------------------------------------
 * Height of a node = 1 + max(height of left subtree,
 *                             height of right subtree)
 *
 * ---------------------------------------------------------
 * Approach 1: Recursive DFS (Most Common)
 * ---------------------------------------------------------
 * - Recursively calculate height of left subtree
 * - Recursively calculate height of right subtree
 * - Take maximum of both and add 1
 *
 * ---------------------------------------------------------
 * Example Binary Tree:
 * ---------------------------------------------------------
 *
 *                1
 *              /   \
 *             2     3
 *            / \   /
 *           4   5 6
 *                  \
 *                   7
 *
 * Height = 3
 *
 * ---------------------------------------------------------
 * Time Complexity: O(n)
 * Space Complexity: O(h)  (recursive stack)
 */

public class HeightOfBinaryTree {

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
     * Recursive method to calculate height of binary tree
     *
     * @param root root of the tree
     * @return height of tree
     */
    public static int height(Node root) {

        // Base case: empty tree
        if (root == null) {
            return -1;
        }

        // Height of left and right subtrees
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);

        // Height of current node
        return 1 + Math.max(leftHeight, rightHeight);
    }

    /**
     * Iterative approach using Level Order Traversal (BFS)
     * Height is counted level by level
     */
    public static int heightUsingLevelOrder(Node root) {

        if (root == null) return -1;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        int height = -1;

        while (!queue.isEmpty()) {

            int size = queue.size();
            height++;

            for (int i = 0; i < size; i++) {
                Node curr = queue.poll();

                if (curr.left != null) queue.add(curr.left);
                if (curr.right != null) queue.add(curr.right);
            }
        }

        return height;
    }

    public static void main(String[] args) {

        System.out.println("=== Height of a Binary Tree ===\n");

        /*
                 1
               /   \
              2     3
             / \   /
            4   5 6
                     \
                      7
         */

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.left.right = new Node(7);

        int recursiveHeight = height(root);
        int iterativeHeight = heightUsingLevelOrder(root);

        System.out.println("Height (Recursive DFS) : " + recursiveHeight);
        System.out.println("Height (Level Order)   : " + iterativeHeight);

        System.out.println("\n" + "=".repeat(60));
        System.out.println("STEP-BY-STEP EXPLANATION");
        System.out.println("-".repeat(60));
        System.out.println("Height(node) = 1 + max(leftHeight, rightHeight)");
        System.out.println("Leaf nodes have height 0");
        System.out.println("Empty tree has height -1");

        System.out.println("\n" + "=".repeat(60));
        System.out.println("COMPLEXITY ANALYSIS");
        System.out.println("-".repeat(60));
        System.out.println("Time Complexity  : O(n)");
        System.out.println("Space Complexity : O(h)");

        System.out.println("\nNOTE:");
        System.out.println("✔ Height depends on the longest path");
        System.out.println("✔ Skewed tree height = n - 1");
        System.out.println("✔ Balanced tree height ≈ log₂(n)");
    }
}
