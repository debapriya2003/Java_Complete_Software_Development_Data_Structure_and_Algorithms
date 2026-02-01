package binary_trees.hard;

import java.util.*;

/**
 * Construct Binary Tree from Inorder and Preorder Traversal
 *
 * ---------------------------------------------------------
 * Problem:
 * ---------------------------------------------------------
 * Given:
 * - Inorder traversal of a Binary Tree
 * - Preorder traversal of the SAME Binary Tree
 *
 * Construct and return the original Binary Tree.
 *
 * ---------------------------------------------------------
 * Key Theory (VERY IMPORTANT):
 * ---------------------------------------------------------
 * ✔ Preorder traversal gives the ROOT first
 * ✔ Inorder traversal gives LEFT | ROOT | RIGHT structure
 *
 * This combination ALWAYS produces a UNIQUE binary tree.
 *
 * ---------------------------------------------------------
 * Observations:
 * ---------------------------------------------------------
 * 1. First element of preorder[] is the ROOT
 * 2. Find this root in inorder[]
 * 3. Elements left of root in inorder[] → LEFT subtree
 * 4. Elements right of root in inorder[] → RIGHT subtree
 * 5. Recursively apply the same logic
 *
 * ---------------------------------------------------------
 * Example:
 * ---------------------------------------------------------
 * Preorder : [3, 9, 20, 15, 7]
 * Inorder  : [9, 3, 15, 20, 7]
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

public class ConstructTreeFromInorderPreorder {

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

    // Pointer to track current index in preorder traversal
    static int preorderIndex = 0;

    // Map to store inorder value → index for O(1) lookup
    static Map<Integer, Integer> inorderIndexMap = new HashMap<>();

    /**
     * Builds the binary tree
     *
     * @param preorder preorder traversal array
     * @param inorder  inorder traversal array
     * @return root of constructed binary tree
     */
    public static Node buildTree(int[] preorder, int[] inorder) {

        preorderIndex = 0;
        inorderIndexMap.clear();

        // Build value → index map for inorder traversal
        for (int i = 0; i < inorder.length; i++) {
            inorderIndexMap.put(inorder[i], i);
        }

        return construct(preorder, 0, inorder.length - 1);
    }

    /**
     * Recursive helper to construct tree
     *
     * @param preorder preorder array
     * @param inStart  start index in inorder
     * @param inEnd    end index in inorder
     * @return constructed subtree root
     */
    private static Node construct(int[] preorder, int inStart, int inEnd) {

        // Base case: no elements to construct subtree
        if (inStart > inEnd) {
            return null;
        }

        // Pick current root from preorder
        int rootValue = preorder[preorderIndex++];
        Node root = new Node(rootValue);

        // Find root position in inorder traversal
        int rootIndex = inorderIndexMap.get(rootValue);

        // Build left subtree
        root.left = construct(preorder, inStart, rootIndex - 1);

        // Build right subtree
        root.right = construct(preorder, rootIndex + 1, inEnd);

        return root;
    }

    /**
     * Utility method: Inorder traversal (for verification)
     */
    public static void printInorder(Node root) {
        if (root == null) return;
        printInorder(root.left);
        System.out.print(root.data + " ");
        printInorder(root.right);
    }

    /**
     * Utility method: Preorder traversal (for verification)
     */
    public static void printPreorder(Node root) {
        if (root == null) return;
        System.out.print(root.data + " ");
        printPreorder(root.left);
        printPreorder(root.right);
    }

    public static void main(String[] args) {

        System.out.println("=== Construct Binary Tree from Inorder & Preorder ===\n");

        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder  = {9, 3, 15, 20, 7};

        Node root = buildTree(preorder, inorder);

        System.out.print("Constructed Tree Inorder   : ");
        printInorder(root);

        System.out.print("\nConstructed Tree Preorder  : ");
        printPreorder(root);

        System.out.println("\n\n" + "=".repeat(60));
        System.out.println("STEP-BY-STEP LOGIC");
        System.out.println("-".repeat(60));
        System.out.println("✔ Preorder selects root first");
        System.out.println("✔ Inorder divides left & right subtrees");
        System.out.println("✔ HashMap enables O(1) root index lookup");
        System.out.println("✔ Recursive construction");

        System.out.println("\n" + "=".repeat(60));
        System.out.println("COMPLEXITY ANALYSIS");
        System.out.println("-".repeat(60));
        System.out.println("Time Complexity  : O(n)");
        System.out.println("Space Complexity : O(n)");

        System.out.println("\nNOTE:");
        System.out.println("✔ Inorder traversal is mandatory for uniqueness");
        System.out.println("✔ Very common FAANG interview problem");
        System.out.println("✔ Same logic applies to Postorder + Inorder");
    }
}
