package binary_trees.mid;
import java.util.*;

/**
 * Check if Two Binary Trees are Identical
 *
 * ---------------------------------------------------------
 * Problem:
 * ---------------------------------------------------------
 * Given the roots of two binary trees, determine whether
 * the two trees are IDENTICAL or NOT.
 *
 * ---------------------------------------------------------
 * Definition of Identical Trees:
 * ---------------------------------------------------------
 * Two binary trees are identical if:
 * 1. They have the same structure
 * 2. They have the same node values
 * 3. Corresponding left and right subtrees are identical
 *
 * ---------------------------------------------------------
 * Key Idea:
 * ---------------------------------------------------------
 * We must compare:
 * - Current node values
 * - Left subtrees
 * - Right subtrees
 *
 * All three conditions must be true.
 *
 * ---------------------------------------------------------
 * Approach 1: Recursive DFS (Most Common & Clean) ✅
 * ---------------------------------------------------------
 * - If both nodes are null → identical
 * - If one is null and other is not → not identical
 * - If data differs → not identical
 * - Recursively check left and right subtrees
 *
 * ---------------------------------------------------------
 * Approach 2: Iterative using Queue (BFS)
 * ---------------------------------------------------------
 * - Traverse both trees level by level
 * - Compare corresponding nodes
 *
 * ---------------------------------------------------------
 * Example:
 * ---------------------------------------------------------
 *
 * Tree 1:            Tree 2:
 *     1                  1
 *    / \                / \
 *   2   3              2   3
 *
 * Output: Identical
 *
 * ---------------------------------------------------------
 * Time Complexity: O(n)
 * Space Complexity: O(h)  (recursive stack)
 */

public class IdenticalBinaryTrees {

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
     * Recursive method to check if two trees are identical
     *
     * @param root1 root of first tree
     * @param root2 root of second tree
     * @return true if identical, false otherwise
     */
    public static boolean isIdentical(Node root1, Node root2) {

        // Case 1: both nodes are null
        if (root1 == null && root2 == null) {
            return true;
        }

        // Case 2: one node is null and other is not
        if (root1 == null || root2 == null) {
            return false;
        }

        // Case 3: data mismatch
        if (root1.data != root2.data) {
            return false;
        }

        // Case 4: recursively check left and right subtrees
        return isIdentical(root1.left, root2.left)
                && isIdentical(root1.right, root2.right);
    }

    /**
     * Iterative approach using Queue (Level Order)
     */
    public static boolean isIdenticalIterative(Node root1, Node root2) {

        Queue<Node> q1 = new LinkedList<>();
        Queue<Node> q2 = new LinkedList<>();

        q1.add(root1);
        q2.add(root2);

        while (!q1.isEmpty() && !q2.isEmpty()) {

            Node n1 = q1.poll();
            Node n2 = q2.poll();

            if (n1 == null && n2 == null) continue;
            if (n1 == null || n2 == null) return false;
            if (n1.data != n2.data) return false;

            q1.add(n1.left);
            q1.add(n1.right);
            q2.add(n2.left);
            q2.add(n2.right);
        }

        return q1.isEmpty() && q2.isEmpty();
    }

    public static void main(String[] args) {

        System.out.println("=== Check if Two Binary Trees are Identical ===\n");

        /*
             Tree 1:           Tree 2:
                 1                 1
                / \               / \
               2   3             2   3
         */

        Node root1 = new Node(1);
        root1.left = new Node(2);
        root1.right = new Node(3);

        Node root2 = new Node(1);
        root2.left = new Node(2);
        root2.right = new Node(3);

        /*
             Tree 3:
                 1
                /
               2
         */

        Node root3 = new Node(1);
        root3.left = new Node(2);

        System.out.println("Tree 1 & Tree 2 (Recursive): " +
                isIdentical(root1, root2));

        System.out.println("Tree 1 & Tree 3 (Recursive): " +
                isIdentical(root1, root3));

        System.out.println("\nTree 1 & Tree 2 (Iterative): " +
                isIdenticalIterative(root1, root2));

        System.out.println("Tree 1 & Tree 3 (Iterative): " +
                isIdenticalIterative(root1, root3));

        System.out.println("\n" + "=".repeat(60));
        System.out.println("STEP-BY-STEP LOGIC");
        System.out.println("-".repeat(60));
        System.out.println("✔ Compare node values");
        System.out.println("✔ Compare left subtrees");
        System.out.println("✔ Compare right subtrees");

        System.out.println("\n" + "=".repeat(60));
        System.out.println("COMPLEXITY ANALYSIS");
        System.out.println("-".repeat(60));
        System.out.println("Time Complexity  : O(n)");
        System.out.println("Space Complexity : O(h)");

        System.out.println("\nNOTE:");
        System.out.println("✔ Structure AND values must match");
        System.out.println("✔ Used in tree comparison & cloning problems");
        System.out.println("✔ Common interview question");
    }
}
