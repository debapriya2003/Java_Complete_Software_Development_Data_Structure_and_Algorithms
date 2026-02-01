package binary_trees.hard;

import java.util.*;

/**
 * Check for Children Sum Property in a Binary Tree
 *
 * ---------------------------------------------------------
 * Problem:
 * ---------------------------------------------------------
 * Given the root of a Binary Tree, check whether the tree
 * satisfies the CHILDREN SUM PROPERTY.
 *
 * ---------------------------------------------------------
 * Definition (Children Sum Property):
 * ---------------------------------------------------------
 * For every non-leaf node:
 *
 *      node.data = data(left child) + data(right child)
 *
 * Rules:
 * - Leaf nodes always satisfy the property
 * - Null children are considered to have value 0
 *
 * ---------------------------------------------------------
 * Example:
 * ---------------------------------------------------------
 *
 *        10
 *       /  \
 *      8    2
 *     / \
 *    3   5
 *
 * ✔ Valid (10 = 8 + 2, 8 = 3 + 5)
 *
 * ---------------------------------------------------------
 * Invalid Example:
 * ---------------------------------------------------------
 *
 *        10
 *       /  \
 *      5    3
 *
 * ✖ Invalid (10 ≠ 5 + 3)
 *
 * ---------------------------------------------------------
 * Approach: Recursive DFS
 * ---------------------------------------------------------
 * - If node is null → true
 * - If node is a leaf → true
 * - Compute sum of left and right child
 * - Check:
 *      node.data == left + right
 * - Recursively validate left and right subtrees
 *
 * ---------------------------------------------------------
 * Time Complexity: O(n)
 * Space Complexity: O(h)
 */

@SuppressWarnings("unused")
public class ChildrenSumProperty {

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
     * Checks whether the binary tree satisfies
     * the Children Sum Property
     *
     * @param root root of the binary tree
     * @return true if property holds, false otherwise
     */
    public static boolean isChildrenSum(Node root) {

        // Base case: empty tree or leaf node
        if (root == null ||
           (root.left == null && root.right == null)) {
            return true;
        }

        int leftSum = (root.left != null) ? root.left.data : 0;
        int rightSum = (root.right != null) ? root.right.data : 0;

        // Check current node and recurse
        return (root.data == leftSum + rightSum)
                && isChildrenSum(root.left)
                && isChildrenSum(root.right);
    }

    public static void main(String[] args) {

        System.out.println("=== Check Children Sum Property ===\n");

        /*
              Valid Tree:
                     10
                    /  \
                   8    2
                  / \
                 3   5
         */

        Node validRoot = new Node(10);
        validRoot.left = new Node(8);
        validRoot.right = new Node(2);
        validRoot.left.left = new Node(3);
        validRoot.left.right = new Node(5);

        /*
              Invalid Tree:
                     10
                    /  \
                   5    3
         */

        Node invalidRoot = new Node(10);
        invalidRoot.left = new Node(5);
        invalidRoot.right = new Node(3);

        System.out.println("Valid Tree satisfies property: "
                + isChildrenSum(validRoot));

        System.out.println("Invalid Tree satisfies property: "
                + isChildrenSum(invalidRoot));

        System.out.println("\n" + "=".repeat(60));
        System.out.println("STEP-BY-STEP LOGIC");
        System.out.println("-".repeat(60));
        System.out.println("✔ Leaf nodes automatically satisfy property");
        System.out.println("✔ Internal nodes must equal sum of children");
        System.out.println("✔ DFS validates entire tree");

        System.out.println("\n" + "=".repeat(60));
        System.out.println("COMPLEXITY ANALYSIS");
        System.out.println("-".repeat(60));
        System.out.println("Time Complexity  : O(n)");
        System.out.println("Space Complexity : O(h)");

        System.out.println("\nNOTE:");
        System.out.println("✔ Very common tree property question");
        System.out.println("✔ Used as a base for tree transformation problems");
        System.out.println("✔ Different from Sum Tree problem");
    }
}
