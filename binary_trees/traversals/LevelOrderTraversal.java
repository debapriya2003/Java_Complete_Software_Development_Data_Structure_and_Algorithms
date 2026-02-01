package binary_trees.traversals;

import java.util.*;

/**
 * Level Order Traversal & Spiral (Zigzag) Level Order Traversal
 *
 * ---------------------------------------------------------
 * Problem:
 * ---------------------------------------------------------
 * Given the root of a Binary Tree, perform:
 * 1. Level Order Traversal (Breadth First Search)
 * 2. Level Order Traversal in Spiral / Zigzag form
 *
 * ---------------------------------------------------------
 * Definitions:
 * ---------------------------------------------------------
 * Level Order Traversal:
 * - Visit nodes level by level from left to right
 * - Uses Queue
 * - Also known as Breadth First Search (BFS)
 *
 * Spiral / Zigzag Traversal:
 * - First level: Left → Right
 * - Second level: Right → Left
 * - Alternates direction at each level
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
 * Level Order  : 1 2 3 4 5 6 7
 * Spiral Order : 1 3 2 4 5 6 7
 *
 * ---------------------------------------------------------
 * Time Complexity:
 * - Level Order  : O(n)
 * - Spiral Order : O(n)
 *
 * Space Complexity:
 * - O(n)
 */

public class LevelOrderTraversal {

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
     * Level Order Traversal (BFS)
     * Uses Queue
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
     * Level Order Traversal in Spiral (Zigzag) Form
     *
     * Approach:
     * - Use two stacks
     * - Stack1 → left to right
     * - Stack2 → right to left
     */
    public static void spiralOrder(Node root) {
        if (root == null) return;

        Stack<Node> s1 = new Stack<>(); // Left → Right
        Stack<Node> s2 = new Stack<>(); // Right → Left

        s1.push(root);

        while (!s1.isEmpty() || !s2.isEmpty()) {

            // Process current level from left to right
            while (!s1.isEmpty()) {
                Node curr = s1.pop();
                System.out.print(curr.data + " ");

                if (curr.left != null) s2.push(curr.left);
                if (curr.right != null) s2.push(curr.right);
            }

            // Process next level from right to left
            while (!s2.isEmpty()) {
                Node curr = s2.pop();
                System.out.print(curr.data + " ");

                if (curr.right != null) s1.push(curr.right);
                if (curr.left != null) s1.push(curr.left);
            }
        }
    }

    /**
     * Spiral Traversal using Deque (Alternative & Cleaner)
     */
    public static void spiralOrderUsingDeque(Node root) {
        if (root == null) return;

        Deque<Node> deque = new ArrayDeque<>();
        deque.add(root);

        boolean leftToRight = true;

        while (!deque.isEmpty()) {

            int size = deque.size();

            for (int i = 0; i < size; i++) {

                if (leftToRight) {
                    Node curr = deque.pollFirst();
                    System.out.print(curr.data + " ");

                    if (curr.left != null) deque.addLast(curr.left);
                    if (curr.right != null) deque.addLast(curr.right);
                } else {
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

    public static void main(String[] args) {

        System.out.println("=== Level Order & Spiral Traversal ===\n");

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

        System.out.print("Level Order Traversal        : ");
        levelOrder(root);

        System.out.print("\nSpiral Traversal (2 Stacks)  : ");
        spiralOrder(root);

        System.out.print("\nSpiral Traversal (Deque)     : ");
        spiralOrderUsingDeque(root);

        System.out.println("\n\n" + "=".repeat(60));
        System.out.println("TRAVERSAL SUMMARY");
        System.out.println("-".repeat(60));
        System.out.println("Level Order  → BFS using Queue");
        System.out.println("Spiral Order → Zigzag using Stack / Deque");

        System.out.println("\n" + "=".repeat(60));
        System.out.println("COMPLEXITY ANALYSIS");
        System.out.println("-".repeat(60));
        System.out.println("Time Complexity  : O(n)");
        System.out.println("Space Complexity : O(n)");
    }
}

