package binary_trees.hard;

import java.util.*;

/**
 * Morris Preorder Traversal of a Binary Tree
 *
 * ---------------------------------------------------------
 * Problem:
 * ---------------------------------------------------------
 * Perform PREORDER traversal of a Binary Tree WITHOUT using:
 * - Recursion
 * - Stack
 *
 * ---------------------------------------------------------
 * Preorder Traversal Order:
 * ---------------------------------------------------------
 * ROOT → LEFT → RIGHT
 *
 * ---------------------------------------------------------
 * Key Concept: Morris Traversal
 * ---------------------------------------------------------
 * Morris Traversal uses the idea of
 * "temporary tree modification (threading)"
 * to achieve O(1) auxiliary space traversal.
 *
 * ---------------------------------------------------------
 * Core Idea:
 * ---------------------------------------------------------
 * For every node:
 * - If left child is NULL:
 *      → Visit node
 *      → Move to right
 *
 * - If left child is NOT NULL:
 *      → Find the INORDER PREDECESSOR (rightmost node of left subtree)
 *
 *      Case 1: predecessor.right == NULL
 *          → Create a temporary thread to current node
 *          → VISIT current node (Preorder step)
 *          → Move to left child
 *
 *      Case 2: predecessor.right == current
 *          → Remove the thread
 *          → Move to right child
 *
 * ---------------------------------------------------------
 * IMPORTANT DIFFERENCE:
 * ---------------------------------------------------------
 * In Morris PREORDER:
 * - We print the node when FIRST visiting it
 * - (i.e., when thread is created)
 *
 * ---------------------------------------------------------
 * Example Binary Tree:
 * ---------------------------------------------------------
 *
 *                 1
 *               /   \
 *              2     3
 *             / \
 *            4   5
 *
 * Preorder Output:
 * 1 2 4 5 3
 *
 * ---------------------------------------------------------
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

@SuppressWarnings("unused")
public class MorrisPreorderTraversal {

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
     * Performs Morris Preorder Traversal
     *
     * @param root root of the binary tree
     */
    public static void morrisPreorder(Node root) {

        Node curr = root;

        while (curr != null) {

            // Case 1: No left child
            if (curr.left == null) {
                // Visit current node
                System.out.print(curr.data + " ");
                // Move right
                curr = curr.right;
            }
            // Case 2: Left child exists
            else {
                // Find inorder predecessor
                Node predecessor = curr.left;

                while (predecessor.right != null &&
                       predecessor.right != curr) {
                    predecessor = predecessor.right;
                }

                // If thread not created
                if (predecessor.right == null) {

                    // Create thread
                    predecessor.right = curr;

                    // VISIT node (Preorder logic)
                    System.out.print(curr.data + " ");

                    // Move to left subtree
                    curr = curr.left;
                }
                // If thread already exists
                else {
                    // Remove thread
                    predecessor.right = null;

                    // Move to right subtree
                    curr = curr.right;
                }
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("=== Morris Preorder Traversal ===\n");

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

        System.out.print("Morris Preorder Traversal: ");
        morrisPreorder(root);

        System.out.println("\n\n" + "=".repeat(60));
        System.out.println("WHY MORRIS WORKS");
        System.out.println("-".repeat(60));
        System.out.println("✔ Uses unused NULL right pointers");
        System.out.println("✔ Temporarily modifies tree (restored later)");
        System.out.println("✔ No recursion, no stack");

        System.out.println("\n" + "=".repeat(60));
        System.out.println("COMPLEXITY ANALYSIS");
        System.out.println("-".repeat(60));
        System.out.println("Time Complexity  : O(n)");
        System.out.println("Space Complexity : O(1)");

        System.out.println("\nNOTE:");
        System.out.println("✔ Tree structure is restored after traversal");
        System.out.println("✔ Morris Inorder is slightly different");
        System.out.println("✔ Very advanced & high-value interview topic");
    }
}
