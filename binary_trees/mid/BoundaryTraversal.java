package binary_trees.mid;

import java.util.*;

/**
 * Boundary Traversal of a Binary Tree
 *
 * ---------------------------------------------------------
 * Problem:
 * ---------------------------------------------------------
 * Given the root of a Binary Tree, print the boundary traversal
 * of the tree in anti-clockwise direction.
 *
 * ---------------------------------------------------------
 * Boundary Traversal Order:
 * ---------------------------------------------------------
 * 1. Root node
 * 2. Left Boundary   (excluding leaf nodes)
 * 3. All Leaf Nodes (from left to right)
 * 4. Right Boundary (excluding leaf nodes, printed bottom-up)
 *
 * ---------------------------------------------------------
 * Important Rules:
 * ---------------------------------------------------------
 * - Root is printed only once
 * - Leaf nodes should NOT be printed twice
 * - Left and Right boundaries exclude leaf nodes
 *
 * ---------------------------------------------------------
 * Example Binary Tree:
 * ---------------------------------------------------------
 *
 *                    1
 *                  /   \
 *                 2     3
 *                / \     \
 *               4   5     6
 *                  / \   /
 *                 7   8 9
 *
 * Boundary Traversal:
 * 1 → 2 → 4 → 7 → 8 → 9 → 6 → 3
 *
 * ---------------------------------------------------------
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

public class BoundaryTraversal {

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
     * Main method to perform boundary traversal
     */
    public static List<Integer> boundaryTraversal(Node root) {

        List<Integer> result = new ArrayList<>();

        if (root == null) return result;

        // Step 1: Add root (if not leaf)
        if (!isLeaf(root)) {
            result.add(root.data);
        }

        // Step 2: Add left boundary
        addLeftBoundary(root.left, result);

        // Step 3: Add all leaf nodes
        addLeaves(root, result);

        // Step 4: Add right boundary
        addRightBoundary(root.right, result);

        return result;
    }

    /**
     * Adds left boundary nodes (excluding leaf nodes)
     */
    private static void addLeftBoundary(Node node, List<Integer> result) {

        while (node != null) {
            if (!isLeaf(node)) {
                result.add(node.data);
            }

            if (node.left != null) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
    }

    /**
     * Adds all leaf nodes using DFS
     */
    private static void addLeaves(Node node, List<Integer> result) {

        if (node == null) return;

        if (isLeaf(node)) {
            result.add(node.data);
            return;
        }

        addLeaves(node.left, result);
        addLeaves(node.right, result);
    }

    /**
     * Adds right boundary nodes (excluding leaf nodes)
     * Added in reverse order (bottom-up)
     */
    private static void addRightBoundary(Node node, List<Integer> result) {

        Stack<Integer> stack = new Stack<>();

        while (node != null) {
            if (!isLeaf(node)) {
                stack.push(node.data);
            }

            if (node.right != null) {
                node = node.right;
            } else {
                node = node.left;
            }
        }

        // Add right boundary in reverse order
        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }
    }

    /**
     * Checks if a node is a leaf node
     */
    private static boolean isLeaf(Node node) {
        return node.left == null && node.right == null;
    }

    public static void main(String[] args) {

        System.out.println("=== Boundary Traversal of Binary Tree ===\n");

        /*
                    1
                  /   \
                 2     3
                / \     \
               4   5     6
                  / \   /
                 7   8 9
         */

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.left.right.left = new Node(7);
        root.left.right.right = new Node(8);
        root.right.right = new Node(6);
        root.right.right.left = new Node(9);

        List<Integer> boundary = boundaryTraversal(root);

        System.out.println("Boundary Traversal (Anti-clockwise): " + boundary);

        System.out.println("\n" + "=".repeat(60));
        System.out.println("STEP-BY-STEP SUMMARY");
        System.out.println("-".repeat(60));
        System.out.println("✔ Root added first");
        System.out.println("✔ Left boundary added top-down");
        System.out.println("✔ Leaf nodes added left to right");
        System.out.println("✔ Right boundary added bottom-up");

        System.out.println("\n" + "=".repeat(60));
        System.out.println("COMPLEXITY ANALYSIS");
        System.out.println("-".repeat(60));
        System.out.println("Time Complexity  : O(n)");
        System.out.println("Space Complexity : O(n)");

        System.out.println("\nNOTE:");
        System.out.println("✔ Avoids duplicate leaf nodes");
        System.out.println("✔ Classic traversal composition problem");
        System.out.println("✔ Frequently asked in interviews");
    }
}
