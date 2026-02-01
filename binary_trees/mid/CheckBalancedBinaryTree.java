package binary_trees.mid;
import java.util.*;

/**
 * Check if a Binary Tree is Height-Balanced
 *
 * ---------------------------------------------------------
 * Problem:
 * ---------------------------------------------------------
 * Given the root of a Binary Tree, check whether the tree is
 * height-balanced or not.
 *
 * ---------------------------------------------------------
 * Definition (Height-Balanced Binary Tree):
 * ---------------------------------------------------------
 * A Binary Tree is height-balanced if:
 * - For every node in the tree,
 *   | height(left subtree) - height(right subtree) | ≤ 1
 *
 * ---------------------------------------------------------
 * Important Notes:
 * ---------------------------------------------------------
 * - Balance condition must hold for EVERY node
 * - Checking only at root is NOT sufficient
 *
 * ---------------------------------------------------------
 * Approach 1: Naive Recursive (Not Optimal)
 * ---------------------------------------------------------
 * - For each node:
 *      - Calculate height of left subtree
 *      - Calculate height of right subtree
 *      - Check balance condition
 * - Time Complexity: O(n²) in worst case
 *
 * ---------------------------------------------------------
 * Approach 2: Optimized DFS (Bottom-Up) ✅
 * ---------------------------------------------------------
 * Key Idea:
 * - Calculate height and balance together
 * - If any subtree is unbalanced, propagate failure upward
 *
 * Trick:
 * - Return height if balanced
 * - Return -1 if unbalanced
 *
 * ---------------------------------------------------------
 * Example:
 * ---------------------------------------------------------
 *
 * Balanced Tree:
 *        1
 *       / \
 *      2   3
 *
 * Unbalanced Tree:
 *        1
 *       /
 *      2
 *     /
 *    3
 *
 * ---------------------------------------------------------
 * Time Complexity: O(n)
 * Space Complexity: O(h)
 */

@SuppressWarnings("unused")
public class CheckBalancedBinaryTree {

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
     * Optimized method to check balance
     *
     * @param root root of the tree
     * @return true if tree is height-balanced
     */
    public static boolean isBalanced(Node root) {
        return checkHeight(root) != -1;
    }

    /**
     * Returns height if subtree is balanced
     * Returns -1 if subtree is NOT balanced
     */
    private static int checkHeight(Node root) {

        // Base case: empty tree
        if (root == null) {
            return 0;
        }

        // Check left subtree
        int leftHeight = checkHeight(root.left);
        if (leftHeight == -1) return -1;

        // Check right subtree
        int rightHeight = checkHeight(root.right);
        if (rightHeight == -1) return -1;

        // Check balance condition
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }

        // Return height
        return 1 + Math.max(leftHeight, rightHeight);
    }

    public static void main(String[] args) {

        System.out.println("=== Check if Binary Tree is Height-Balanced ===\n");

        /*
             Balanced Tree:
                     1
                   /   \
                  2     3
                 / \
                4   5
         */

        Node balancedRoot = new Node(1);
        balancedRoot.left = new Node(2);
        balancedRoot.right = new Node(3);
        balancedRoot.left.left = new Node(4);
        balancedRoot.left.right = new Node(5);

        /*
             Unbalanced Tree:
                     1
                    /
                   2
                  /
                 3
         */

        Node unbalancedRoot = new Node(1);
        unbalancedRoot.left = new Node(2);
        unbalancedRoot.left.left = new Node(3);

        System.out.println("Balanced Tree → " + isBalanced(balancedRoot));
        System.out.println("Unbalanced Tree → " + isBalanced(unbalancedRoot));

        System.out.println("\n" + "=".repeat(60));
        System.out.println("STEP-BY-STEP LOGIC");
        System.out.println("-".repeat(60));
        System.out.println("✔ Height calculated bottom-up");
        System.out.println("✔ If any subtree fails → return -1");
        System.out.println("✔ Final check at root");

        System.out.println("\n" + "=".repeat(60));
        System.out.println("COMPLEXITY ANALYSIS");
        System.out.println("-".repeat(60));
        System.out.println("Time Complexity  : O(n)");
        System.out.println("Space Complexity : O(h)");

        System.out.println("\nNOTE:");
        System.out.println("✔ Optimized approach avoids repeated height calculations");
        System.out.println("✔ Very common interview question");
        System.out.println("✔ Used in AVL Trees and Red-Black Trees");
    }
}
