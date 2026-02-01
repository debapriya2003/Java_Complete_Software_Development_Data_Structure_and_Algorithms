package binary_trees.traversals;

import java.util.*;

/**
 * Binary Tree Traversals
 *
 * ---------------------------------------------------------
 * What is Tree Traversal?
 * ---------------------------------------------------------
 * Tree traversal is the process of visiting each node of a tree
 * exactly once in a specific order.
 *
 * In a Binary Tree, there are two major categories of traversals:
 *
 * 1. Depth First Traversal (DFS)
 *    - Inorder
 *    - Preorder
 *    - Postorder
 *
 * 2. Breadth First Traversal (BFS)
 *    - Level Order
 *
 * ---------------------------------------------------------
 * Traversal Orders Explained:
 * ---------------------------------------------------------
 *
 * 1. Inorder Traversal (Left → Root → Right)
 *    - Visits left subtree
 *    - Visits root
 *    - Visits right subtree
 *    - Used in BST to get sorted order
 *
 * 2. Preorder Traversal (Root → Left → Right)
 *    - Visits root first
 *    - Used to copy trees and create expression trees
 *
 * 3. Postorder Traversal (Left → Right → Root)
 *    - Visits root last
 *    - Used for deleting trees and evaluating expressions
 *
 * 4. Level Order Traversal (Level by Level)
 *    - Uses Queue
 *    - Also called Breadth First Search (BFS)
 *
 * ---------------------------------------------------------
 * Example Binary Tree:
 * ---------------------------------------------------------
 *
 *              1
 *            /   \
 *           2     3
 *          / \   / \
 *         4   5 6   7
 *
 * ---------------------------------------------------------
 * Expected Traversals:
 * ---------------------------------------------------------
 * Inorder    : 4 2 5 1 6 3 7
 * Preorder   : 1 2 4 5 3 6 7
 * Postorder  : 4 5 2 6 7 3 1
 * LevelOrder : 1 2 3 4 5 6 7
 *
 * ---------------------------------------------------------
 * Time Complexity (All Traversals): O(n)
 * Space Complexity:
 * - Recursive DFS: O(h) where h = height of tree
 * - Level Order: O(n)
 */

public class BinaryTreeTraversals {

    /**
     * Node structure for Binary Tree
     */
    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    /**
     * Inorder Traversal (Left → Root → Right)
     */
    public static void inorder(Node root) {
        if (root == null) return;

        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    /**
     * Preorder Traversal (Root → Left → Right)
     */
    public static void preorder(Node root) {
        if (root == null) return;

        System.out.print(root.data + " ");
        preorder(root.left);
        preorder(root.right);
    }

    /**
     * Postorder Traversal (Left → Right → Root)
     */
    public static void postorder(Node root) {
        if (root == null) return;

        postorder(root.left);
        postorder(root.right);
        System.out.print(root.data + " ");
    }

    /**
     * Level Order Traversal (Breadth First Search)
     */
    public static void levelOrder(Node root) {
        if (root == null) return;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node curr = queue.poll();
            System.out.print(curr.data + " ");

            if (curr.left != null) queue.add(curr.left);
            if (curr.right != null) queue.add(curr.right);
        }
    }

    /**
     * Iterative Inorder Traversal using Stack
     */
    public static void inorderIterative(Node root) {
        Stack<Node> stack = new Stack<>();
        Node curr = root;

        while (curr != null || !stack.isEmpty()) {

            // Reach the leftmost node
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            curr = stack.pop();
            System.out.print(curr.data + " ");

            curr = curr.right;
        }
    }

    public static void main(String[] args) {

        System.out.println("=== Binary Tree Traversals ===\n");

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

        System.out.print("Inorder Traversal        : ");
        inorder(root);

        System.out.print("\nPreorder Traversal       : ");
        preorder(root);

        System.out.print("\nPostorder Traversal      : ");
        postorder(root);

        System.out.print("\nLevel Order Traversal    : ");
        levelOrder(root);

        System.out.print("\nIterative Inorder        : ");
        inorderIterative(root);

        System.out.println("\n\n" + "=".repeat(60));
        System.out.println("TRAVERSAL SUMMARY");
        System.out.println("-".repeat(60));
        System.out.println("Inorder    → Left, Root, Right");
        System.out.println("Preorder   → Root, Left, Right");
        System.out.println("Postorder  → Left, Right, Root");
        System.out.println("LevelOrder → BFS using Queue");

        System.out.println("\n" + "=".repeat(60));
        System.out.println("APPLICATIONS");
        System.out.println("-".repeat(60));
        System.out.println("✔ Inorder   → Sorted order in BST");
        System.out.println("✔ Preorder  → Tree copying, expression trees");
        System.out.println("✔ Postorder → Tree deletion, evaluation");
        System.out.println("✔ LevelOrder→ Shortest path, level-wise ops");

        System.out.println("\n" + "=".repeat(60));
        System.out.println("COMPLEXITY ANALYSIS");
        System.out.println("-".repeat(60));
        System.out.println("Time Complexity  : O(n)");
        System.out.println("Space Complexity : O(h) for DFS, O(n) for BFS");
    }
}
