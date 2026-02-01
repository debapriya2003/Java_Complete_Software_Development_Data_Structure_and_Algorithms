package binary_trees.mid;

import java.util.*;

/**
 * Top View of a Binary Tree
 *
 * ---------------------------------------------------------
 * Problem:
 * ---------------------------------------------------------
 * Given the root of a Binary Tree, print the TOP VIEW of the tree.
 *
 * ---------------------------------------------------------
 * Definition:
 * ---------------------------------------------------------
 * Top View of a Binary Tree consists of the nodes that are
 * visible when the tree is viewed from the top.
 *
 * For each vertical line (horizontal distance),
 * ONLY the FIRST node encountered from top to bottom
 * is included in the top view.
 *
 * ---------------------------------------------------------
 * Key Concept: Horizontal Distance (HD)
 * ---------------------------------------------------------
 * - Root has HD = 0
 * - Left child  → HD - 1
 * - Right child → HD + 1
 *
 * ---------------------------------------------------------
 * Approach: BFS + TreeMap (Optimal)
 * ---------------------------------------------------------
 * - Perform level order traversal (BFS)
 * - Track HD for each node
 * - Use TreeMap<HD, NodeValue>
 * - Insert value ONLY if HD is not already present
 *
 * Why BFS?
 * - BFS ensures the topmost node at each HD
 *   is processed first
 *
 * ---------------------------------------------------------
 * Example Binary Tree:
 * ---------------------------------------------------------
 *
 *                     1
 *                   /   \
 *                  2     3
 *                   \   / \
 *                    4 5   6
 *                         /
 *                        7
 *
 * Top View:
 * 2 1 3 6
 *
 * ---------------------------------------------------------
 * Time Complexity: O(n log n)
 * Space Complexity: O(n)
 */

public class TopViewBinaryTree {

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
     * Helper class to store node with its horizontal distance
     */
    static class Pair {
        Node node;
        int hd;

        Pair(Node node, int hd) {
            this.node = node;
            this.hd = hd;
        }
    }

    /**
     * Prints the Top View of the Binary Tree
     */
    public static List<Integer> topView(Node root) {

        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        // TreeMap stores keys in sorted order
        Map<Integer, Integer> map = new TreeMap<>();

        // BFS queue
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(root, 0));

        while (!queue.isEmpty()) {

            Pair curr = queue.poll();
            Node node = curr.node;
            int hd = curr.hd;

            // Add only first node at each horizontal distance
            map.putIfAbsent(hd, node.data);

            if (node.left != null) {
                queue.add(new Pair(node.left, hd - 1));
            }

            if (node.right != null) {
                queue.add(new Pair(node.right, hd + 1));
            }
        }

        // Collect top view from TreeMap
        for (int value : map.values()) {
            result.add(value);
        }

        return result;
    }

    public static void main(String[] args) {

        System.out.println("=== Top View of Binary Tree ===\n");

        /*
                     1
                   /   \
                  2     3
                   \   / \
                    4 5   6
                         /
                        7
         */

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.right = new Node(4);
        root.right.left = new Node(5);
        root.right.right = new Node(6);
        root.right.right.left = new Node(7);

        List<Integer> topView = topView(root);

        System.out.println("Top View of Binary Tree: " + topView);

        System.out.println("\n" + "=".repeat(60));
        System.out.println("STEP-BY-STEP LOGIC");
        System.out.println("-".repeat(60));
        System.out.println("✔ Assign HD to each node");
        System.out.println("✔ BFS ensures topmost node is processed first");
        System.out.println("✔ TreeMap ensures left-to-right order");

        System.out.println("\n" + "=".repeat(60));
        System.out.println("COMPLEXITY ANALYSIS");
        System.out.println("-".repeat(60));
        System.out.println("Time Complexity  : O(n log n)");
        System.out.println("Space Complexity : O(n)");

        System.out.println("\nNOTE:");
        System.out.println("✔ Top View ≠ Vertical Order");
        System.out.println("✔ Only the first node per HD is selected");
        System.out.println("✔ BFS is mandatory for correctness");
    }
}
