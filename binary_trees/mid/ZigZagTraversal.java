package binary_trees.mid;
import java.util.*;

/**
 * Zig Zag Traversal of a Binary Tree
 *
 * ---------------------------------------------------------
 * Problem:
 * ---------------------------------------------------------
 * Given the root of a Binary Tree, print its nodes in
 * Zig Zag (or Spiral) level order traversal.
 *
 * Zig Zag Traversal means:
 * - Level 0 → Left to Right
 * - Level 1 → Right to Left
 * - Level 2 → Left to Right
 * - and so on...
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
 * Zig Zag Traversal Output:
 * ---------------------------------------------------------
 * 1 3 2 4 5 6 7
 *
 * ---------------------------------------------------------
 * Approaches:
 * ---------------------------------------------------------
 * 1️⃣ Using Deque (Recommended & Clean) ✅
 * 2️⃣ Using Two Stacks (Classic approach)
 *
 * ---------------------------------------------------------
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

public class ZigZagTraversal {

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
     * Zig Zag Traversal using Deque
     *
     * Logic:
     * - Use a Deque to support removal from both ends
     * - Use a boolean flag to track direction
     */
    public static void zigZagUsingDeque(Node root) {

        if (root == null) return;

        Deque<Node> deque = new ArrayDeque<>();
        deque.add(root);

        boolean leftToRight = true;

        while (!deque.isEmpty()) {

            int size = deque.size();

            for (int i = 0; i < size; i++) {

                if (leftToRight) {
                    // Remove from front
                    Node curr = deque.pollFirst();
                    System.out.print(curr.data + " ");

                    if (curr.left != null) deque.addLast(curr.left);
                    if (curr.right != null) deque.addLast(curr.right);

                } else {
                    // Remove from back
                    Node curr = deque.pollLast();
                    System.out.print(curr.data + " ");

                    if (curr.right != null) deque.addFirst(curr.right);
                    if (curr.left != null) deque.addFirst(curr.left);
                }
            }

            // Change direction after each level
            leftToRight = !leftToRight;
        }
    }

    /**
     * Zig Zag Traversal using Two Stacks
     */
    public static void zigZagUsingStacks(Node root) {

        if (root == null) return;

        Stack<Node> currentLevel = new Stack<>();
        Stack<Node> nextLevel = new Stack<>();

        boolean leftToRight = true;
        currentLevel.push(root);

        while (!currentLevel.isEmpty()) {

            Node curr = currentLevel.pop();
            System.out.print(curr.data + " ");

            if (leftToRight) {
                if (curr.left != null) nextLevel.push(curr.left);
                if (curr.right != null) nextLevel.push(curr.right);
            } else {
                if (curr.right != null) nextLevel.push(curr.right);
                if (curr.left != null) nextLevel.push(curr.left);
            }

            // If current level is finished
            if (currentLevel.isEmpty()) {
                leftToRight = !leftToRight;
                Stack<Node> temp = currentLevel;
                currentLevel = nextLevel;
                nextLevel = temp;
            }
        }
    }

    public static void main(String[] args) {

        System.out.println("=== Zig Zag Traversal of Binary Tree ===\n");

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

        System.out.print("Zig Zag Traversal (Deque)  : ");
        zigZagUsingDeque(root);

        System.out.print("\nZig Zag Traversal (Stacks) : ");
        zigZagUsingStacks(root);

        System.out.println("\n\n" + "=".repeat(60));
        System.out.println("KEY POINTS");
        System.out.println("-".repeat(60));
        System.out.println("✔ Direction alternates at each level");
        System.out.println("✔ Deque method is cleaner and intuitive");
        System.out.println("✔ Two-stack method is classical interview solution");

        System.out.println("\n" + "=".repeat(60));
        System.out.println("COMPLEXITY ANALYSIS");
        System.out.println("-".repeat(60));
        System.out.println("Time Complexity  : O(n)");
        System.out.println("Space Complexity : O(n)");
    }
}
