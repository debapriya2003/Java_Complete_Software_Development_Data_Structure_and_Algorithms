package binary_trees.traversals;

import java.util.*;

/**
 * Iterative Preorder Traversal of a Binary Tree
 *
 * ---------------------------------------------------------
 * Problem:
 * ---------------------------------------------------------
 * Given the root of a Binary Tree, perform Preorder Traversal
 * WITHOUT using recursion.
 *
 * Preorder Traversal Order:
 * Root → Left → Right
 *
 * ---------------------------------------------------------
 * Why Iterative?
 * ---------------------------------------------------------
 * - Avoids recursion stack overflow
 * - Gives better control over traversal flow
 * - Important for interviews and low-level systems
 *
 * ---------------------------------------------------------
 * Approach: Stack (LIFO)
 * ---------------------------------------------------------
 * - Use a Stack to simulate recursive calls
 * - Push root first
 * - While stack is not empty:
 *      - Pop node
 *      - Visit node
 *      - Push right child (if exists)
 *      - Push left child (if exists)
 *
 * NOTE:
 * Right child is pushed before left child
 * so that left child is processed first
 *
 * ---------------------------------------------------------
 * Example:
 * ---------------------------------------------------------
 *
 *                1
 *              /   \
 *             2     3
 *            / \   / \
 *           4   5 6   7
 *
 * Preorder Output:
 * 1 2 4 5 3 6 7
 *
 * ---------------------------------------------------------
 * Time Complexity: O(n)
 * Space Complexity: O(h)
 */

public class IterativePreorderTraversal {

    /**
     * Node structure for Binary Tree
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
     * Iterative Preorder Traversal using Stack
     *
     * @param root root of the binary tree
     */
    public static void preorderIterative(Node root) {

        if (root == null) return;

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {

            Node curr = stack.pop();

            // Visit root
            System.out.print(curr.data + " ");

            // Push right child first
            if (curr.right != null) {
                stack.push(curr.right);
            }

            // Push left child
            if (curr.left != null) {
                stack.push(curr.left);
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("=== Iterative Preorder Traversal ===\n");

        /*
                 1
               /   \
              2     3
             / \   / \
            4   5 6   7
         */

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        System.out.print("Preorder Traversal (Iterative): ");
        preorderIterative(root);

        System.out.println("\n\n" + "=".repeat(60));
        System.out.println("STEP-BY-STEP EXPLANATION");
        System.out.println("-".repeat(60));

        Stack<Node> debugStack = new Stack<>();
        debugStack.push(root);

        while (!debugStack.isEmpty()) {
            Node curr = debugStack.pop();
            System.out.println("Visited: " + curr.data);

            if (curr.right != null) {
                debugStack.push(curr.right);
                System.out.println("  Pushed Right: " + curr.right.data);
            }

            if (curr.left != null) {
                debugStack.push(curr.left);
                System.out.println("  Pushed Left : " + curr.left.data);
            }

            System.out.println("  Stack State: " +
                    debugStack.stream().map(n -> n.data).toList());
        }

        System.out.println("\n" + "=".repeat(60));
        System.out.println("COMPLEXITY ANALYSIS");
        System.out.println("-".repeat(60));
        System.out.println("Time Complexity  : O(n)");
        System.out.println("Space Complexity : O(h)");

        System.out.println("\nNOTE:");
        System.out.println("✔ Stack simulates recursive call stack");
        System.out.println("✔ Right child pushed first to maintain order");
    }
}

