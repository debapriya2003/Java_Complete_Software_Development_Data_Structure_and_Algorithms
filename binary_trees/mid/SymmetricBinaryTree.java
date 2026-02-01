package binary_trees.mid;

import java.util.*;

/**
 * Symmetric Binary Tree
 *
 * ---------------------------------------------------------
 * Problem:
 * ---------------------------------------------------------
 * Given the root of a Binary Tree, check whether the tree
 * is symmetric (a mirror of itself) around its center.
 *
 * ---------------------------------------------------------
 * Definition:
 * ---------------------------------------------------------
 * A Binary Tree is symmetric if:
 * - Left subtree is a mirror reflection of the right subtree
 *
 * ---------------------------------------------------------
 * Key Conditions for Symmetry:
 * ---------------------------------------------------------
 * For two nodes A and B:
 * - A.data == B.data
 * - A.left  is mirror of B.right
 * - A.right is mirror of B.left
 *
 * ---------------------------------------------------------
 * Approaches:
 * ---------------------------------------------------------
 * 1️⃣ Recursive Mirror Check (Most Common) ✅
 * 2️⃣ Iterative using Queue (BFS)
 *
 * ---------------------------------------------------------
 * Example:
 * ---------------------------------------------------------
 *
 * Symmetric Tree:
 *            1
 *          /   \
 *         2     2
 *        / \   / \
 *       3   4 4   3
 *
 * Output: true
 *
 * ---------------------------------------------------------
 * Non-Symmetric Tree:
 *            1
 *          /   \
 *         2     2
 *          \     \
 *           3     3
 *
 * Output: false
 *
 * ---------------------------------------------------------
 * Time Complexity: O(n)
 * Space Complexity: O(h)
 */

public class SymmetricBinaryTree {

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
     * Checks if the tree is symmetric
     *
     * @param root root of the binary tree
     * @return true if symmetric, false otherwise
     */
    public static boolean isSymmetric(Node root) {

        if (root == null) return true;

        return isMirror(root.left, root.right);
    }

    /**
     * Helper method to check if two trees are mirror images
     */
    private static boolean isMirror(Node t1, Node t2) {

        // Both null → symmetric
        if (t1 == null && t2 == null) return true;

        // One null → not symmetric
        if (t1 == null || t2 == null) return false;

        // Data mismatch → not symmetric
        if (t1.data != t2.data) return false;

        // Check mirror condition
        return isMirror(t1.left, t2.right)
                && isMirror(t1.right, t2.left);
    }

    /**
     * Iterative approach using Queue
     */
    public static boolean isSymmetricIterative(Node root) {

        if (root == null) return true;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);

        while (!queue.isEmpty()) {

            Node n1 = queue.poll();
            Node n2 = queue.poll();

            if (n1 == null && n2 == null) continue;
            if (n1 == null || n2 == null) return false;
            if (n1.data != n2.data) return false;

            queue.add(n1.left);
            queue.add(n2.right);
            queue.add(n1.right);
            queue.add(n2.left);
        }

        return true;
    }

    public static void main(String[] args) {

        System.out.println("=== Symmetric Binary Tree ===\n");

        /*
             Symmetric Tree:
                     1
                   /   \
                  2     2
                 / \   / \
                3   4 4   3
         */

        Node symmetricRoot = new Node(1);
        symmetricRoot.left = new Node(2);
        symmetricRoot.right = new Node(2);
        symmetricRoot.left.left = new Node(3);
        symmetricRoot.left.right = new Node(4);
        symmetricRoot.right.left = new Node(4);
        symmetricRoot.right.right = new Node(3);

        /*
             Non-Symmetric Tree:
                     1
                   /   \
                  2     2
                   \     \
                    3     3
         */

        Node nonSymmetricRoot = new Node(1);
        nonSymmetricRoot.left = new Node(2);
        nonSymmetricRoot.right = new Node(2);
        nonSymmetricRoot.left.right = new Node(3);
        nonSymmetricRoot.right.right = new Node(3);

        System.out.println("Symmetric Tree (Recursive): " +
                isSymmetric(symmetricRoot));

        System.out.println("Non-Symmetric Tree (Recursive): " +
                isSymmetric(nonSymmetricRoot));

        System.out.println("\nSymmetric Tree (Iterative): " +
                isSymmetricIterative(symmetricRoot));

        System.out.println("Non-Symmetric Tree (Iterative): " +
                isSymmetricIterative(nonSymmetricRoot));

        System.out.println("\n" + "=".repeat(60));
        System.out.println("KEY OBSERVATIONS");
        System.out.println("-".repeat(60));
        System.out.println("✔ Left subtree mirrors right subtree");
        System.out.println("✔ Node values AND structure must match");
        System.out.println("✔ Recursive solution is simplest");

        System.out.println("\n" + "=".repeat(60));
        System.out.println("COMPLEXITY ANALYSIS");
        System.out.println("-".repeat(60));
        System.out.println("Time Complexity  : O(n)");
        System.out.println("Space Complexity : O(h)");

        System.out.println("\nNOTE:");
        System.out.println("✔ Common mirror-tree interview problem");
        System.out.println("✔ BFS and DFS both valid");
        System.out.println("✔ Empty tree is symmetric");
    }
}
