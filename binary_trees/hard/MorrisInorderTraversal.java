package binary_trees.hard;

import java.util.*;

/**
 * Morris Inorder Traversal of a Binary Tree
 *
 * ---------------------------------------------------------
 * Problem:
 * ---------------------------------------------------------
 * Perform INORDER traversal of a Binary Tree WITHOUT using:
 * - Recursion
 * - Stack
 *
 * ---------------------------------------------------------
 * Inorder Traversal Order:
 * ---------------------------------------------------------
 * LEFT → ROOT → RIGHT
 *
 * ---------------------------------------------------------
 * Key Concept: Morris Traversal
 * ---------------------------------------------------------
 * Morris Traversal allows tree traversal in O(1) auxiliary
 * space by temporarily modifying the tree using
 * "threaded binary tree" technique.
 *
 * ---------------------------------------------------------
 * Core Idea:
 * ---------------------------------------------------------
 * For every current node:
 *
 * Case 1: If left child is NULL
 *      → Visit the node
 *      → Move to right child
 *
 * Case 2: If left child is NOT NULL
 *      → Find the INORDER PREDECESSOR
 *        (rightmost node in left subtree)
 *
 *      Case 2a: predecessor.right == NULL
 *          → Create a temporary thread to current node
 *          → Move to left child
 *
 *      Case 2b: predecessor.right == current
 *          → Remove the thread
 *          → Visit the node
 *          → Move to right child
 *
 * ---------------------------------------------------------
 * IMPORTANT DIFFERENCE FROM PREORDER:
 * ---------------------------------------------------------
 * - In Morris INORDER, the node is printed
 *   ONLY AFTER the left subtree is fully processed
 *   (i.e., when the thread is removed)
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
 * Inorder Output:
 * 4 2 5 1 3
 *
 * ---------------------------------------------------------
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

@SuppressWarnings("unused")
public class MorrisInorderTraversal {

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
     * Performs Morris Inorder Traversal
     *
     * @param root root of the binary tree
     */
    public static void morrisInorder(Node root) {

        Node curr = root;

        while (curr != null) {

            // Case 1: No left child
            if (curr.left == null) {
                // Visit node
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
                    // Move to left subtree
                    curr = curr.left;
                }
                // If thread already exists
                else {
                    // Remove thread
                    predecessor.right = null;

                    // Visit current node
                    System.out.print(curr.data + " ");

                    // Move to right subtree
                    curr = curr.right;
                }
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("=== Morris Inorder Traversal ===\n");

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

        System.out.print("Morris Inorder Traversal: ");
        morrisInorder(root);

        System.out.println("\n\n" + "=".repeat(60));
        System.out.println("WHY MORRIS INORDER WORKS");
        System.out.println("-".repeat(60));
        System.out.println("✔ Uses unused NULL right pointers");
        System.out.println("✔ Temporarily creates threads");
        System.out.println("✔ Threads are removed, tree restored");

        System.out.println("\n" + "=".repeat(60));
        System.out.println("COMPLEXITY ANALYSIS");
        System.out.println("-".repeat(60));
        System.out.println("Time Complexity  : O(n)");
        System.out.println("Space Complexity : O(1)");

        System.out.println("\nNOTE:");
        System.out.println("✔ Tree structure remains unchanged after traversal");
        System.out.println("✔ Morris Preorder prints earlier than Inorder");
        System.out.println("✔ One of the most elegant tree traversal techniques");
    }
}
