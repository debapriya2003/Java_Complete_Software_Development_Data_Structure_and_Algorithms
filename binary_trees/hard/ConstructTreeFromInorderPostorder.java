package binary_trees.hard;

import java.util.*;

/**
 * Construct Binary Tree from Inorder and Postorder Traversal
 *
 * ---------------------------------------------------------
 * Problem:
 * ---------------------------------------------------------
 * Given:
 * - Inorder traversal of a Binary Tree
 * - Postorder traversal of the SAME Binary Tree
 *
 * Construct and return the original Binary Tree.
 *
 * ---------------------------------------------------------
 * Key Theory (VERY IMPORTANT):
 * ---------------------------------------------------------
 * ✔ Postorder traversal gives the ROOT at the END
 * ✔ Inorder traversal gives LEFT | ROOT | RIGHT structure
 *
 * This combination ALWAYS produces a UNIQUE binary tree.
 *
 * ---------------------------------------------------------
 * Observations:
 * ---------------------------------------------------------
 * 1. Last element of postorder[] is the ROOT
 * 2. Find this root in inorder[]
 * 3. Elements left of root in inorder[] → LEFT subtree
 * 4. Elements right of root in inorder[] → RIGHT subtree
 * 5. Build RIGHT subtree FIRST (important!)
 * 6. Recursively build LEFT subtree
 *
 * ---------------------------------------------------------
 * Example:
 * ---------------------------------------------------------
 * Postorder : [9, 15, 7, 20, 3]
 * Inorder   : [9, 3, 15, 20, 7]
 *
 * Tree:
 *                3
 *              /   \
 *             9    20
 *                 /  \
 *               15    7
 *
 * ---------------------------------------------------------
 * Time Complexity:
 * ---------------------------------------------------------
 * O(n)  (using HashMap for inorder index lookup)
 *
 * Space Complexity:
 * ---------------------------------------------------------
 * O(n)  (recursion + hashmap)
 */

public class ConstructTreeFromInorderPostorder {

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

    // Pointer to track current index in postorder traversal
    static int postorderIndex;

    // Map to store inorder value → index for O(1) lookup
    static Map<Integer, Integer> inorderIndexMap = new HashMap<>();

    /**
     * Builds the binary tree
     *
     * @param inorder   inorder traversal array
     * @param postorder postorder traversal array
     * @return root of constructed binary tree
     */
    public static Node buildTree(int[] inorder, int[] postorder) {

        postorderIndex = postorder.length - 1;
        inorderIndexMap.clear();

        // Build value → index map for inorder traversal
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }

        return construct(postorder, 0, inorder.length - 1);
    }

    /**
     * Recursive helper to construct tree
     *
     * @param postorder postorder array
     * @param inStart   start index in inorder
     * @param inEnd     end index in inorder
     * @return constructed subtree root
     */
    private static Node construct(int[] postorder, int inStart, int inEnd) {

        // Base case
        if (inStart > inEnd) {
            return null;
        }

        // Pick current root from postorder
        int rootValue = postorder[postorderIndex--];
        Node root = new Node(rootValue);

        // Find root index in inorder traversal
        int rootIndex = inorderIndexMap.get(rootValue);

        // IMPORTANT:
        // Build RIGHT subtree before LEFT subtree
        root.right = construct(postorder, rootIndex + 1, inEnd);
        root.left = construct(postorder, inStart, rootIndex - 1);

        return root;
    }

    /**
     * Utility method: Inorder traversal (verification)
     */
    public static void printInorder(Node root) {
        if (root == null) return;
        printInorder(root.left);
        System.out.print(root.data + " ");
        printInorder(root.right);
    }

    /**
     * Utility method: Postorder traversal (verification)
     */
    public static void printPostorder(Node root) {
        if (root == null) return;
        printPostorder(root.left);
        printPostorder(root.right);
        System.out.print(root.data + " ");
    }

    public static void main(String[] args) {

        System.out.println("=== Construct Binary Tree from Inorder & Postorder ===\n");

        int[] inorder   = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};

        Node root = buildTree(inorder, postorder);

        System.out.print("Constructed Tree Inorder    : ");
        printInorder(root);

        System.out.print("\nConstructed Tree Postorder : ");
        printPostorder(root);

        System.out.println("\n\n" + "=".repeat(60));
        System.out.println("STEP-BY-STEP LOGIC");
        System.out.println("-".repeat(60));
        System.out.println("✔ Postorder selects root from end");
        System.out.println("✔ Inorder divides left & right subtrees");
        System.out.println("✔ Right subtree built BEFORE left subtree");
        System.out.println("✔ HashMap ensures O(1) index lookup");

        System.out.println("\n" + "=".repeat(60));
        System.out.println("COMPLEXITY ANALYSIS");
        System.out.println("-".repeat(60));
        System.out.println("Time Complexity  : O(n)");
        System.out.println("Space Complexity : O(n)");

        System.out.println("\nNOTE:");
        System.out.println("✔ Inorder traversal is mandatory for uniqueness");
        System.out.println("✔ Order of recursion is CRITICAL here");
        System.out.println("✔ Very common interview problem");
    }
}
