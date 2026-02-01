package binary_trees.hard;
import java.util.*;

/**
 * Lowest Common Ancestor (LCA) in a Binary Tree
 *
 * ---------------------------------------------------------
 * Problem:
 * ---------------------------------------------------------
 * Given the root of a Binary Tree and two node values n1 and n2,
 * find their Lowest Common Ancestor (LCA).
 *
 * ---------------------------------------------------------
 * Definition:
 * ---------------------------------------------------------
 * The Lowest Common Ancestor of two nodes n1 and n2 is the
 * LOWEST node in the tree that has both n1 and n2 as descendants
 * (a node can be a descendant of itself).
 *
 * ---------------------------------------------------------
 * Key Observations:
 * ---------------------------------------------------------
 * - Binary Tree is NOT necessarily a BST
 * - Nodes can be anywhere in the tree
 * - We must search both left and right subtrees
 *
 * ---------------------------------------------------------
 * Approach: Recursive DFS (Optimal) ✅
 * ---------------------------------------------------------
 * Logic:
 * - If root is null → return null
 * - If root matches n1 or n2 → return root
 * - Recur for left and right subtrees
 *
 * Cases:
 * 1. Both left and right return non-null
 *    → current root is LCA
 * 2. Only one side returns non-null
 *    → propagate that value upward
 * 3. Both sides return null
 *    → return null
 *
 * ---------------------------------------------------------
 * Example:
 * ---------------------------------------------------------
 *
 *                 1
 *               /   \
 *              2     3
 *             / \     \
 *            4   5     6
 *               /
 *              7
 *
 * LCA(4, 7) = 2
 * LCA(5, 6) = 1
 *
 * ---------------------------------------------------------
 * Time Complexity: O(n)
 * Space Complexity: O(h)
 */

@SuppressWarnings("unused")
public class LCAInBinaryTree {

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
     * Finds Lowest Common Ancestor (LCA) of two nodes
     *
     * @param root root of the binary tree
     * @param n1   first node value
     * @param n2   second node value
     * @return LCA node (or null if not found)
     */
    public static Node lca(Node root, int n1, int n2) {

        // Base case
        if (root == null) {
            return null;
        }

        // If current node matches either n1 or n2
        if (root.data == n1 || root.data == n2) {
            return root;
        }

        // Recur for left and right subtrees
        Node leftLCA = lca(root.left, n1, n2);
        Node rightLCA = lca(root.right, n1, n2);

        // If both sides return non-null, this is the LCA
        if (leftLCA != null && rightLCA != null) {
            return root;
        }

        // Otherwise return the non-null child
        return (leftLCA != null) ? leftLCA : rightLCA;
    }

    public static void main(String[] args) {

        System.out.println("=== Lowest Common Ancestor (LCA) in Binary Tree ===\n");

        /*
                 1
               /   \
              2     3
             / \     \
            4   5     6
               /
              7
         */

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.left.right.left = new Node(7);
        root.right.right = new Node(6);

        int n1 = 4, n2 = 7;
        Node result1 = lca(root, n1, n2);
        System.out.println("LCA(" + n1 + ", " + n2 + ") = " +
                (result1 != null ? result1.data : "Not Found"));

        int n3 = 5, n4 = 6;
        Node result2 = lca(root, n3, n4);
        System.out.println("LCA(" + n3 + ", " + n4 + ") = " +
                (result2 != null ? result2.data : "Not Found"));

        System.out.println("\n" + "=".repeat(60));
        System.out.println("STEP-BY-STEP LOGIC");
        System.out.println("-".repeat(60));
        System.out.println("✔ Traverse entire tree using DFS");
        System.out.println("✔ Match either node and bubble result up");
        System.out.println("✔ First node where both sides return non-null is LCA");

        System.out.println("\n" + "=".repeat(60));
        System.out.println("COMPLEXITY ANALYSIS");
        System.out.println("-".repeat(60));
        System.out.println("Time Complexity  : O(n)");
        System.out.println("Space Complexity : O(h)");

        System.out.println("\nNOTE:");
        System.out.println("✔ Works for general Binary Trees");
        System.out.println("✔ Different logic than LCA in BST");
        System.out.println("✔ Foundation for distance-between-nodes problems");
    }
}
