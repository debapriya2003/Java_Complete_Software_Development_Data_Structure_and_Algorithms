package binary_trees.mid;

import java.util.*;

/**
 * Bottom View of a Binary Tree
 *
 * ---------------------------------------------------------
 * Problem:
 * ---------------------------------------------------------
 * Given the root of a Binary Tree, print the BOTTOM VIEW of the tree.
 *
 * ---------------------------------------------------------
 * Definition:
 * ---------------------------------------------------------
 * Bottom View of a Binary Tree consists of the nodes that are
 * visible when the tree is viewed from the bottom.
 *
 * For each vertical line (horizontal distance),
 * the LAST node encountered from top to bottom
 * is included in the bottom view.
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
 * - Overwrite value at each HD (bottom-most node wins)
 *
 * Why BFS?
 * - Ensures nodes are processed level by level
 * - Later (lower) nodes overwrite earlier ones
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
 * Bottom View:
 * 2 4 5 7 6
 *
 * ---------------------------------------------------------
 * Time Complexity: O(n log n)
 * Space Complexity: O(n)
 */

public class BottomViewBinaryTree {

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
     * Computes Bottom View of Binary Tree
     *
     * @param root root of the tree
     * @return list of bottom view nodes (left → right)
     */
    public static List<Integer> bottomView(Node root) {

        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        // TreeMap keeps horizontal distances sorted
        Map<Integer, Integer> map = new TreeMap<>();

        // Queue for BFS
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(root, 0));

        while (!queue.isEmpty()) {

            Pair curr = queue.poll();
            Node node = curr.node;
            int hd = curr.hd;

            // Overwrite value at HD (bottom-most node)
            map.put(hd, node.data);

            if (node.left != null) {
                queue.add(new Pair(node.left, hd - 1));
            }

            if (node.right != null) {
                queue.add(new Pair(node.right, hd + 1));
            }
        }

        // Collect result from TreeMap
        for (int value : map.values()) {
            result.add(value);
        }

        return result;
    }

    public static void main(String[] args) {

        System.out.println("=== Bottom View of Binary Tree ===\n");

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

        List<Integer> bottomView = bottomView(root);

        System.out.println("Bottom View of Binary Tree: " + bottomView);

        System.out.println("\n" + "=".repeat(60));
        System.out.println("STEP-BY-STEP LOGIC");
        System.out.println("-".repeat(60));
        System.out.println("✔ Assign HD to each node");
        System.out.println("✔ BFS traversal ensures correct vertical ordering");
        System.out.println("✔ Later nodes overwrite earlier ones");

        System.out.println("\n" + "=".repeat(60));
        System.out.println("COMPLEXITY ANALYSIS");
        System.out.println("-".repeat(60));
        System.out.println("Time Complexity  : O(n log n)");
        System.out.println("Space Complexity : O(n)");

        System.out.println("\nNOTE:");
        System.out.println("✔ Bottom View ≠ Top View");
        System.out.println("✔ BFS is crucial for correct result");
        System.out.println("✔ Frequently asked interview problem");
    }
}
