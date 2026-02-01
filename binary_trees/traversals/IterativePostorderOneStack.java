package binary_trees.traversals;
import java.util.*;

/**
 * Post-order Traversal of a Binary Tree using One Stack
 *
 * ---------------------------------------------------------
 * Problem:
 * ---------------------------------------------------------
 * Given the root of a Binary Tree, perform Postorder Traversal
 * (Left → Right → Root) WITHOUT using recursion
 * and using ONLY ONE STACK.
 *
 * ---------------------------------------------------------
 * Why is this tricky?
 * ---------------------------------------------------------
 * - Postorder requires visiting root AFTER both subtrees
 * - With one stack, we must track whether the right subtree
 *   has already been processed or not
 *
 * ---------------------------------------------------------
 * Core Idea:
 * ---------------------------------------------------------
 * - Use a stack to traverse down the tree
 * - Keep track of the last visited node
 * - Decide whether to:
 *      1. Go left
 *      2. Go right
 *      3. Visit the node
 *
 * ---------------------------------------------------------
 * Algorithm (One Stack):
 * ---------------------------------------------------------
 * 1. Initialize:
 *      - Stack<Node>
 *      - curr = root
 *      - lastVisited = null
 *
 * 2. While curr != null OR stack is not empty:
 *      a) Go as left as possible and push nodes
 *      b) Peek the top node
 *      c) If right child exists AND not yet visited:
 *            move to right child
 *         Else:
 *            visit node
 *            pop from stack
 *            update lastVisited
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
 * Postorder Output:
 * 4 5 2 6 7 3 1
 *
 * ---------------------------------------------------------
 * Time Complexity: O(n)
 * Space Complexity: O(h)
 */

public class IterativePostorderOneStack {

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
     * Postorder Traversal using One Stack
     *
     * @param root root of the binary tree
     */
    public static void postorderOneStack(Node root) {

        if (root == null) return;

        Stack<Node> stack = new Stack<>();
        Node curr = root;
        Node lastVisited = null;

        // Continue until stack is empty and current is null
        while (curr != null || !stack.isEmpty()) {

            // Step 1: Go to the leftmost node
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            // Step 2: Peek the top node
            Node peekNode = stack.peek();

            // Step 3: If right child exists and not yet visited, move right
            if (peekNode.right != null && lastVisited != peekNode.right) {
                curr = peekNode.right;
            }
            // Step 4: Visit the node
            else {
                System.out.print(peekNode.data + " ");
                lastVisited = stack.pop();
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("=== Postorder Traversal using One Stack ===\n");

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

        System.out.print("Postorder Traversal: ");
        postorderOneStack(root);

        System.out.println("\n\n" + "=".repeat(60));
        System.out.println("STEP-BY-STEP WALKTHROUGH");
        System.out.println("-".repeat(60));

        Stack<Node> debugStack = new Stack<>();
        Node curr = root;
        Node last = null;

        while (curr != null || !debugStack.isEmpty()) {

            while (curr != null) {
                debugStack.push(curr);
                System.out.println("Pushed: " + curr.data);
                curr = curr.left;
            }

            Node peek = debugStack.peek();

            if (peek.right != null && last != peek.right) {
                System.out.println("Move to right of: " + peek.data);
                curr = peek.right;
            } else {
                System.out.println("Visited: " + peek.data);
                last = debugStack.pop();
            }
        }

        System.out.println("\n" + "=".repeat(60));
        System.out.println("COMPLEXITY ANALYSIS");
        System.out.println("-".repeat(60));
        System.out.println("Time Complexity  : O(n)");
        System.out.println("Space Complexity : O(h)");

        System.out.println("\nNOTE:");
        System.out.println("✔ One-stack postorder is interview favorite");
        System.out.println("✔ Requires careful tracking of last visited node");
        System.out.println("✔ Morris traversal can reduce space to O(1)");
    }
}

