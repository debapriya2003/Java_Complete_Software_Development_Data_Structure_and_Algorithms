package binary_trees.hard;

import java.util.*;

/**
 * Flatten Binary Tree to Linked List
 *
 * ---------------------------------------------------------
 * Problem:
 * ---------------------------------------------------------
 * Given the root of a Binary Tree, flatten it into a
 * LINKED LIST in-place following PREORDER traversal.
 *
 * After flattening:
 * - Left child of every node should be NULL
 * - Right child points to the next node in preorder sequence
 *
 * ---------------------------------------------------------
 * Required Order:
 * ---------------------------------------------------------
 * PREORDER → ROOT → LEFT → RIGHT
 *
 * ---------------------------------------------------------
 * Example:
 * ---------------------------------------------------------
 *
 * Original Tree:
 *                 1
 *               /   \
 *              2     5
 *             / \     \
 *            3   4     6
 *
 * Flattened Linked List:
 * 1 → 2 → 3 → 4 → 5 → 6
 *
 * ---------------------------------------------------------
 * Approaches:
 * ---------------------------------------------------------
 * 1️⃣ Recursive (Reverse Preorder) – O(n) time, O(h) space
 * 2️⃣ Iterative using Stack – O(n) time, O(n) space
 * 3️⃣ Morris Traversal (Best) – O(n) time, O(1) space ✅
 *
 * ---------------------------------------------------------
 * We implement the MORRIS approach (most optimal).
 *
 * ---------------------------------------------------------
 * Time Complexity: O(n)
 * Space Complexity: O(1)
 */

@SuppressWarnings("unused")
public class FlattenBinaryTree {

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
     * Flatten Binary Tree to Linked List using Morris Traversal
     *
     * Core Idea:
     * - For each node with a left child:
     *      1. Find rightmost node of left subtree
     *      2. Connect it to current.right
     *      3. Move left subtree to right
     *      4. Set left = null
     */
    public static void flatten(Node root) {

        Node curr = root;

        while (curr != null) {

            // If left subtree exists
            if (curr.left != null) {

                // Find rightmost node of left subtree
                Node predecessor = curr.left;
                while (predecessor.right != null) {
                    predecessor = predecessor.right;
                }

                // Rewire connections
                predecessor.right = curr.right;
                curr.right = curr.left;
                curr.left = null;
            }

            // Move to next node (right)
            curr = curr.right;
        }
    }

    /**
     * Utility method to print flattened tree
     */
    public static void printLinkedList(Node root) {
        Node curr = root;
        while (curr != null) {
            System.out.print(curr.data + " -> ");
            curr = curr.right;
        }
        System.out.println("NULL");
    }

    public static void main(String[] args) {

        System.out.println("=== Flatten Binary Tree to Linked List ===\n");

        /*
                 1
               /   \
              2     5
             / \     \
            3   4     6
         */

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(5);
        root.left.left = new Node(3);
        root.left.right = new Node(4);
        root.right.right = new Node(6);

        flatten(root);

        System.out.print("Flattened Linked List: ");
        printLinkedList(root);

        System.out.println("\n" + "=".repeat(60));
        System.out.println("KEY INSIGHTS");
        System.out.println("-".repeat(60));
        System.out.println("✔ Preorder sequence preserved");
        System.out.println("✔ Tree modified in-place");
        System.out.println("✔ No recursion, no stack");

        System.out.println("\n" + "=".repeat(60));
        System.out.println("COMPLEXITY ANALYSIS");
        System.out.println("-".repeat(60));
        System.out.println("Time Complexity  : O(n)");
        System.out.println("Space Complexity : O(1)");

        System.out.println("\nNOTE:");
        System.out.println("✔ Morris traversal makes this optimal");
        System.out.println("✔ Very common FAANG interview problem");
        System.out.println("✔ Left pointers are always set to NULL");
    }
}
