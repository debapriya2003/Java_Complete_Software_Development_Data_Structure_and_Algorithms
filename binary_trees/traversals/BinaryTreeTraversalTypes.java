package binary_trees.traversals;

import java.util.*;

/**
 * Preorder, Inorder and Postorder Traversal of a Binary Tree
 *
 * ---------------------------------------------------------
 * Problem:
 * ---------------------------------------------------------
 * Given the root of a Binary Tree, perform:
 * 1. Preorder Traversal
 * 2. Inorder Traversal
 * 3. Postorder Traversal
 *
 * Each traversal visits all nodes of the tree exactly once,
 * but in a different order.
 *
 * ---------------------------------------------------------
 * Binary Tree Traversal Types (DFS):
 * ---------------------------------------------------------
 *
 * 1️⃣ Preorder Traversal  (Root → Left → Right)
 *    - Visit root node first
 *    - Then traverse left subtree
 *    - Then traverse right subtree
 *
 * 2️⃣ Inorder Traversal   (Left → Root → Right)
 *    - Traverse left subtree
 *    - Visit root node
 *    - Traverse right subtree
 *
 * 3️⃣ Postorder Traversal (Left → Right → Root)
 *    - Traverse left subtree
 *    - Traverse right subtree
 *    - Visit root node last
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
 * ---------------------------------------------------------
 * Expected Output:
 * ---------------------------------------------------------
 * Preorder  : 1 2 4 5 3 6 7
 * Inorder   : 4 2 5 1 6 3 7
 * Postorder : 4 5 2 6 7 3 1
 *
 * ---------------------------------------------------------
 * Time Complexity (All Traversals): O(n)
 * Space Complexity:
 * - Recursive calls: O(h), where h is height of tree
 */

@SuppressWarnings("unused")
public class BinaryTreeTraversalTypes {

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
     * Preorder Traversal (Root → Left → Right)
     */
    public static void preorder(Node root) {
        if (root == null) return;

        // Step 1: Visit root
        System.out.print(root.data + " ");

        // Step 2: Traverse left subtree
        preorder(root.left);

        // Step 3: Traverse right subtree
        preorder(root.right);
    }

    /**
     * Inorder Traversal (Left → Root → Right)
     */
    public static void inorder(Node root) {
        if (root == null) return;

        // Step 1: Traverse left subtree
        inorder(root.left);

        // Step 2: Visit root
        System.out.print(root.data + " ");

        // Step 3: Traverse right subtree
        inorder(root.right);
    }

    /**
     * Postorder Traversal (Left → Right → Root)
     */
    public static void postorder(Node root) {
        if (root == null) return;

        // Step 1: Traverse left subtree
        postorder(root.left);

        // Step 2: Traverse right subtree
        postorder(root.right);

        // Step 3: Visit root
        System.out.print(root.data + " ");
    }

    public static void main(String[] args) {

        System.out.println("=== Binary Tree Traversal Demonstration ===\n");

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

        System.out.print("Preorder Traversal  : ");
        preorder(root);

        System.out.print("\nInorder Traversal   : ");
        inorder(root);

        System.out.print("\nPostorder Traversal : ");
        postorder(root);

        System.out.println("\n\n" + "=".repeat(60));
        System.out.println("TRAVERSAL EXPLANATION");
        System.out.println("-".repeat(60));
        System.out.println("Preorder  → Root, Left, Right");
        System.out.println("Inorder   → Left, Root, Right");
        System.out.println("Postorder → Left, Right, Root");

        System.out.println("\n" + "=".repeat(60));
        System.out.println("APPLICATIONS");
        System.out.println("-".repeat(60));
        System.out.println("✔ Preorder  → Tree copying, prefix expressions");
        System.out.println("✔ Inorder   → Sorted output in BST");
        System.out.println("✔ Postorder → Tree deletion, postfix expressions");

        System.out.println("\n" + "=".repeat(60));
        System.out.println("COMPLEXITY ANALYSIS");
        System.out.println("-".repeat(60));
        System.out.println("Time Complexity  : O(n)");
        System.out.println("Space Complexity : O(h)");
    }
}

