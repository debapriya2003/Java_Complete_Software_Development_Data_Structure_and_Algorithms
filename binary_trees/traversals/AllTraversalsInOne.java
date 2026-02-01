package binary_trees.traversals;

import java.util.*;

/**
 * Preorder, Inorder, and Postorder Traversal in ONE Traversal
 *
 * ---------------------------------------------------------
 * Problem:
 * ---------------------------------------------------------
 * Given the root of a Binary Tree, perform:
 * 1. Preorder Traversal
 * 2. Inorder Traversal
 * 3. Postorder Traversal
 *
 * 👉 All in a SINGLE traversal of the tree.
 *
 * ---------------------------------------------------------
 * Key Idea:
 * ---------------------------------------------------------
 * Instead of traversing the tree three times,
 * we simulate recursion using a stack and maintain
 * a "state" for each node.
 *
 * ---------------------------------------------------------
 * State Meaning:
 * ---------------------------------------------------------
 * Each node is pushed with a state:
 *
 * state = 1 → Preorder position (Root)
 * state = 2 → Inorder position  (After Left)
 * state = 3 → Postorder position (After Right)
 *
 * ---------------------------------------------------------
 * Algorithm (Single Traversal):
 * ---------------------------------------------------------
 * 1. Push (root, state = 1) into stack
 * 2. While stack is not empty:
 *      - Pop top element
 *      - If state == 1:
 *            → Add to Preorder
 *            → Increment state
 *            → Push back
 *            → Push left child (state = 1)
 *      - If state == 2:
 *            → Add to Inorder
 *            → Increment state
 *            → Push back
 *            → Push right child (state = 1)
 *      - If state == 3:
 *            → Add to Postorder
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
 * Output:
 * ---------------------------------------------------------
 * Preorder  : 1 2 4 5 3 6 7
 * Inorder   : 4 2 5 1 6 3 7
 * Postorder : 4 5 2 6 7 3 1
 *
 * ---------------------------------------------------------
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

public class AllTraversalsInOne {

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
     * Helper class to store node with its traversal state
     */
    static class Pair {
        Node node;
        int state;

        Pair(Node node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    /**
     * Performs Preorder, Inorder, and Postorder in one traversal
     */
    public static void allTraversals(Node root) {

        if (root == null) return;

        List<Integer> preorder = new ArrayList<>();
        List<Integer> inorder = new ArrayList<>();
        List<Integer> postorder = new ArrayList<>();

        Stack<Pair> stack = new Stack<>();
        stack.push(new Pair(root, 1));

        while (!stack.isEmpty()) {

            Pair curr = stack.pop();

            // Preorder position
            if (curr.state == 1) {
                preorder.add(curr.node.data);
                curr.state++;
                stack.push(curr);

                if (curr.node.left != null) {
                    stack.push(new Pair(curr.node.left, 1));
                }
            }

            // Inorder position
            else if (curr.state == 2) {
                inorder.add(curr.node.data);
                curr.state++;
                stack.push(curr);

                if (curr.node.right != null) {
                    stack.push(new Pair(curr.node.right, 1));
                }
            }

            // Postorder position
            else {
                postorder.add(curr.node.data);
            }
        }

        System.out.println("Preorder  Traversal: " + preorder);
        System.out.println("Inorder   Traversal: " + inorder);
        System.out.println("Postorder Traversal: " + postorder);
    }

    public static void main(String[] args) {

        System.out.println("=== All Tree Traversals in One Traversal ===\n");

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

        allTraversals(root);

        System.out.println("\n" + "=".repeat(60));
        System.out.println("WHY THIS WORKS");
        System.out.println("-".repeat(60));
        System.out.println("✔ Each node is processed exactly 3 times");
        System.out.println("✔ Mimics recursive call stack behavior");
        System.out.println("✔ Extremely popular interview problem");

        System.out.println("\n" + "=".repeat(60));
        System.out.println("COMPLEXITY ANALYSIS");
        System.out.println("-".repeat(60));
        System.out.println("Time Complexity  : O(n)");
        System.out.println("Space Complexity : O(n)");
    }
}
