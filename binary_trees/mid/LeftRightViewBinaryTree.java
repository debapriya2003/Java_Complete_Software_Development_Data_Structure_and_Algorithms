package binary_trees.mid;

import java.util.*;

/**
 * Left View and Right View of a Binary Tree
 *
 * ---------------------------------------------------------
 * Problem:
 * ---------------------------------------------------------
 * Given the root of a Binary Tree, print:
 * 1. Left View  → nodes visible when the tree is viewed from the LEFT
 * 2. Right View → nodes visible when the tree is viewed from the RIGHT
 *
 * ---------------------------------------------------------
 * Definition:
 * ---------------------------------------------------------
 * - Left View  : First node encountered at each level (left to right)
 * - Right View : Last node encountered at each level (left to right)
 *
 * ---------------------------------------------------------
 * Approach: Level Order Traversal (BFS)
 * ---------------------------------------------------------
 * - Traverse the tree level by level using a Queue
 * - For each level:
 *      - Left View  → pick the FIRST node
 *      - Right View → pick the LAST node
 *
 * Why BFS?
 * - Level information is explicit
 * - Simple and intuitive
 *
 * ---------------------------------------------------------
 * Alternative Approach:
 * ---------------------------------------------------------
 * - Recursive DFS using level tracking
 * - (first node per level for left view,
 *    last node per level for right view)
 *
 * ---------------------------------------------------------
 * Example Binary Tree:
 * ---------------------------------------------------------
 *
 *                   1
 *                 /   \
 *                2     3
 *               / \     \
 *              4   5     6
 *                         \
 *                          7
 *
 * Left View  : 1 2 4 7
 * Right View : 1 3 6 7
 *
 * ---------------------------------------------------------
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

public class LeftRightViewBinaryTree {

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
     * Computes Left View of Binary Tree
     */
    public static List<Integer> leftView(Node root) {

        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int i = 0; i < size; i++) {
                Node curr = queue.poll();

                // First node of this level
                if (i == 0) {
                    result.add(curr.data);
                }

                if (curr.left != null) queue.add(curr.left);
                if (curr.right != null) queue.add(curr.right);
            }
        }
        return result;
    }

    /**
     * Computes Right View of Binary Tree
     */
    public static List<Integer> rightView(Node root) {

        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {

            int size = queue.size();

            for (int i = 0; i < size; i++) {
                Node curr = queue.poll();

                // Last node of this level
                if (i == size - 1) {
                    result.add(curr.data);
                }

                if (curr.left != null) queue.add(curr.left);
                if (curr.right != null) queue.add(curr.right);
            }
        }
        return result;
    }

    public static void main(String[] args) {

        System.out.println("=== Left & Right View of Binary Tree ===\n");

        /*
                       1
                     /   \
                    2     3
                   / \     \
                  4   5     6
                             \
                              7
         */

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(6);
        root.right.right.right = new Node(7);

        System.out.println("Left View  : " + leftView(root));
        System.out.println("Right View : " + rightView(root));

        System.out.println("\n" + "=".repeat(60));
        System.out.println("STEP-BY-STEP LOGIC");
        System.out.println("-".repeat(60));
        System.out.println("✔ BFS traversal level by level");
        System.out.println("✔ Left View  → first node of each level");
        System.out.println("✔ Right View → last node of each level");

        System.out.println("\n" + "=".repeat(60));
        System.out.println("COMPLEXITY ANALYSIS");
        System.out.println("-".repeat(60));
        System.out.println("Time Complexity  : O(n)");
        System.out.println("Space Complexity : O(n)");

        System.out.println("\nNOTE:");
        System.out.println("✔ BFS approach is easiest to understand");
        System.out.println("✔ DFS solution also possible using level tracking");
        System.out.println("✔ Commonly asked tree view problem");
    }
}
