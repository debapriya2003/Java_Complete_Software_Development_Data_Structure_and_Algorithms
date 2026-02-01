package binary_trees.traversals;

import java.util.*;

/**
 * Post-order Traversal of a Binary Tree using Two Stacks
 *
 * ---------------------------------------------------------
 * Problem:
 * ---------------------------------------------------------
 * Given the root of a Binary Tree, perform Postorder Traversal
 * (Left → Right → Root) WITHOUT using recursion.
 *
 * ---------------------------------------------------------
 * Why Two Stacks?
 * ---------------------------------------------------------
 * - Postorder is hardest to do iteratively
 * - Two stacks simplify the logic
 * - First stack processes nodes
 * - Second stack reverses the order to get postorder
 *
 * ---------------------------------------------------------
 * Core Idea:
 * ---------------------------------------------------------
 * - Use Stack1 for traversal
 * - Use Stack2 to store nodes in reverse postorder
 *
 * Steps:
 * 1. Push root to Stack1
 * 2. While Stack1 is not empty:
 *      - Pop node from Stack1
 *      - Push it to Stack2
 *      - Push left child to Stack1
 *      - Push right child to Stack1
 * 3. Pop all nodes from Stack2 → Postorder sequence
 *
 * ---------------------------------------------------------
 * Example Binary Tree:
 * ---------------------------------------------------------
 *
 *                1
 *              /   \
 *             2     3
 *            / \   / \
 *           4   5 6   7
 *
 * Postorder Output:
 * 4 5 2 6 7 3 1
 *
 * ---------------------------------------------------------
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

public class IterativePostorderTwoStacks {

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
     * Postorder Traversal using Two Stacks
     *
     * @param root root of the binary tree
     */
    public static void postorderTwoStacks(Node root) {

        if (root == null) return;

        Stack<Node> stack1 = new Stack<>();
        Stack<Node> stack2 = new Stack<>();

        // Step 1: Push root to stack1
        stack1.push(root);

        // Step 2: Process all nodes
        while (!stack1.isEmpty()) {

            Node curr = stack1.pop();
            stack2.push(curr);

            // Push left and right children to stack1
            if (curr.left != null) {
                stack1.push(curr.left);
            }

            if (curr.right != null) {
                stack1.push(curr.right);
            }
        }

        // Step 3: Print nodes from stack2
        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().data + " ");
        }
    }

    public static void main(String[] args) {

        System.out.println("=== Postorder Traversal using Two Stacks ===\n");

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

        System.out.print("Postorder Traversal: ");
        postorderTwoStacks(root);

        System.out.println("\n\n" + "=".repeat(60));
        System.out.println("STEP-BY-STEP WALKTHROUGH");
        System.out.println("-".repeat(60));

        Stack<Node> s1 = new Stack<>();
        Stack<Node> s2 = new Stack<>();

        s1.push(root);

        while (!s1.isEmpty()) {
            Node curr = s1.pop();
            s2.push(curr);

            System.out.println("Moved " + curr.data + " to Stack2");

            if (curr.left != null) {
                s1.push(curr.left);
                System.out.println("  Pushed Left  → " + curr.left.data);
            }
            if (curr.right != null) {
                s1.push(curr.right);
                System.out.println("  Pushed Right → " + curr.right.data);
            }
        }

        System.out.println("\nStack2 contents (Top to Bottom):");
        for (int i = s2.size() - 1; i >= 0; i--) {
            System.out.print(s2.get(i).data + " ");
        }

        System.out.println("\n\nFinal Postorder Output:");
        while (!s2.isEmpty()) {
            System.out.print(s2.pop().data + " ");
        }

        System.out.println("\n\n" + "=".repeat(60));
        System.out.println("COMPLEXITY ANALYSIS");
        System.out.println("-".repeat(60));
        System.out.println("Time Complexity  : O(n)");
        System.out.println("Space Complexity : O(n)");

        System.out.println("\nNOTE:");
        System.out.println("✔ Two stacks simplify postorder traversal");
        System.out.println("✔ One-stack version exists but is more complex");
    }
}

