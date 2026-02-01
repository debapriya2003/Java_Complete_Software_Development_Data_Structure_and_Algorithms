package binary_trees.hard;

import java.util.*;

/**
 * Maximum Width of a Binary Tree
 *
 * ---------------------------------------------------------
 * Problem:
 * ---------------------------------------------------------
 * Given the root of a Binary Tree, find the MAXIMUM WIDTH of the tree.
 *
 * ---------------------------------------------------------
 * Definition:
 * ---------------------------------------------------------
 * The width of a level is defined as the number of nodes
 * between the leftmost and rightmost NON-NULL nodes at that level,
 * considering the positions of nodes as if the tree were a complete
 * binary tree.
 *
 * Width = (rightmost_index - leftmost_index + 1)
 *
 * ---------------------------------------------------------
 * Important Notes:
 * ---------------------------------------------------------
 * - Null nodes between two nodes are counted
 * - Indexing follows complete binary tree rules:
 *      root index = 0
 *      left child  = 2*i + 1
 *      right child = 2*i + 2
 *
 * ---------------------------------------------------------
 * Approach: Level Order Traversal (BFS) with Indexing ✅
 * ---------------------------------------------------------
 * - Perform BFS using a queue
 * - Store each node with its index
 * - For each level:
 *      - Record first (min) index
 *      - Record last (max) index
 *      - Compute width
 * - Normalize indices at each level to avoid overflow
 *
 * ---------------------------------------------------------
 * Example:
 * ---------------------------------------------------------
 *
 *                     1
 *                   /   \
 *                  2     3
 *                 / \     \
 *                4   5     6
 *
 * Indices:
 * Level 0 → [0]
 * Level 1 → [1, 2]
 * Level 2 → [3, 4, 6]
 *
 * Width at level 2 = 6 - 3 + 1 = 4
 *
 * ---------------------------------------------------------
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

public class MaximumWidthBinaryTree {

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
     * Helper class to store node with its index
     */
    static class Pair {
        Node node;
        long index;   // use long to prevent overflow

        Pair(Node node, long index) {
            this.node = node;
            this.index = index;
        }
    }

    /**
     * Computes maximum width of the binary tree
     *
     * @param root root of the tree
     * @return maximum width
     */
    public static int maximumWidth(Node root) {

        if (root == null) return 0;

        int maxWidth = 0;
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(root, 0));

        while (!queue.isEmpty()) {

            int size = queue.size();
            long minIndex = queue.peek().index; // normalize base
            long first = 0, last = 0;

            for (int i = 0; i < size; i++) {

                Pair curr = queue.poll();
                long currIndex = curr.index - minIndex;

                if (i == 0) first = currIndex;
                if (i == size - 1) last = currIndex;

                if (curr.node.left != null) {
                    queue.add(new Pair(curr.node.left, 2 * currIndex + 1));
                }

                if (curr.node.right != null) {
                    queue.add(new Pair(curr.node.right, 2 * currIndex + 2));
                }
            }

            maxWidth = Math.max(maxWidth, (int) (last - first + 1));
        }

        return maxWidth;
    }

    public static void main(String[] args) {

        System.out.println("=== Maximum Width of Binary Tree ===\n");

        /*
                     1
                   /   \
                  2     3
                 / \     \
                4   5     6
         */

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(6);

        int width = maximumWidth(root);

        System.out.println("Maximum Width of the Binary Tree: " + width);

        System.out.println("\n" + "=".repeat(60));
        System.out.println("KEY IDEAS");
        System.out.println("-".repeat(60));
        System.out.println("✔ BFS traversal with indexing");
        System.out.println("✔ Index normalization avoids overflow");
        System.out.println("✔ Width includes gaps between nodes");

        System.out.println("\n" + "=".repeat(60));
        System.out.println("COMPLEXITY ANALYSIS");
        System.out.println("-".repeat(60));
        System.out.println("Time Complexity  : O(n)");
        System.out.println("Space Complexity : O(n)");

        System.out.println("\nNOTE:");
        System.out.println("✔ Different from counting nodes per level");
        System.out.println("✔ Matches LeetCode-style definition of width");
        System.out.println("✔ Frequently asked interview problem");
    }
}
