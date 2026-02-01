package binary_trees.traversals;
import java.util.*;

/**
 * Iterative Inorder Traversal of a Binary Tree
 *
 * ---------------------------------------------------------
 * Problem:
 * ---------------------------------------------------------
 * Given the root of a Binary Tree, perform Inorder Traversal
 * WITHOUT using recursion.
 *
 * Inorder Traversal Order:
 * Left → Root → Right
 *
 * ---------------------------------------------------------
 * Why Iterative Inorder?
 * ---------------------------------------------------------
 * - Eliminates recursion overhead
 * - Frequently asked in interviews
 * - Foundation for Morris Traversal
 *
 * ---------------------------------------------------------
 * Approach: Stack Simulation of Recursion
 * ---------------------------------------------------------
 * Idea:
 * - Keep going left and push nodes onto stack
 * - When no left child exists:
 *      - Pop from stack
 *      - Visit node
 *      - Move to right subtree
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
 * Inorder Output:
 * 4 2 5 1 6 3 7
 *
 * ---------------------------------------------------------
 * Time Complexity: O(n)
 * Space Complexity: O(h)
 */

public class IterativeInorderTraversal {

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
     * Iterative Inorder Traversal using Stack
     *
     * @param root root of binary tree
     */
    public static void inorderIterative(Node root) {

        Stack<Node> stack = new Stack<>();
        Node curr = root;

        // Continue until all nodes are processed
        while (curr != null || !stack.isEmpty()) {

            // Reach the leftmost node
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            // Visit node
            curr = stack.pop();
            System.out.print(curr.data + " ");

            // Move to right subtree
            curr = curr.right;
        }
    }

    public static void main(String[] args) {

        System.out.println("=== Iterative Inorder Traversal ===\n");

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

        System.out.print("Inorder Traversal (Iterative): ");
        inorderIterative(root);

        System.out.println("\n\n" + "=".repeat(60));
        System.out.println("STEP-BY-STEP WALKTHROUGH");
        System.out.println("-".repeat(60));

        Stack<Node> debugStack = new Stack<>();
        Node temp = root;

        while (temp != null || !debugStack.isEmpty()) {

            while (temp != null) {
                debugStack.push(temp);
                System.out.println("Pushed: " + temp.data);
                temp = temp.left;
            }

            temp = debugStack.pop();
            System.out.println("Visited: " + temp.data);

            temp = temp.right;
            if (temp != null) {
                System.out.println("Move to right: " + temp.data);
            }
        }

        System.out.println("\n" + "=".repeat(60));
        System.out.println("COMPLEXITY ANALYSIS");
        System.out.println("-".repeat(60));
        System.out.println("Time Complexity  : O(n)");
        System.out.println("Space Complexity : O(h)");

        System.out.println("\nNOTE:");
        System.out.println("✔ Stack stores path to leftmost node");
        System.out.println("✔ Equivalent to recursive inorder traversal");
    }
}
