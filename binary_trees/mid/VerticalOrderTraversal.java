package binary_trees.mid;

import java.util.*;

/**
 * Vertical Order Traversal of a Binary Tree
 *
 * ---------------------------------------------------------
 * Problem:
 * ---------------------------------------------------------
 * Given the root of a Binary Tree, print its nodes in
 * Vertical Order Traversal.
 *
 * ---------------------------------------------------------
 * Definition:
 * ---------------------------------------------------------
 * Vertical Order Traversal groups nodes based on their
 * Horizontal Distance (HD) from the root.
 *
 * - Root has HD = 0
 * - Left child  → HD - 1
 * - Right child → HD + 1
 *
 * Nodes having the same HD belong to the same vertical line.
 *
 * ---------------------------------------------------------
 * Traversal Order Rules:
 * ---------------------------------------------------------
 * 1. Vertical lines are printed from leftmost to rightmost
 * 2. Within the same vertical line, nodes are printed
 *    from top to bottom (level order)
 *
 * ---------------------------------------------------------
 * Example Binary Tree:
 * ---------------------------------------------------------
 *
 *                   1
 *                 /   \
 *                2     3
 *               / \   / \
 *              4   5 6   7
 *                   \
 *                    8
 *
 * Horizontal Distances:
 * HD -2 → [4]
 * HD -1 → [2]
 * HD  0 → [1, 5, 6]
 * HD +1 → [3, 8]
 * HD +2 → [7]
 *
 * Output:
 * [[4], [2], [1,5,6], [3,8], [7]]
 *
 * ---------------------------------------------------------
 * Approach: BFS + TreeMap
 * ---------------------------------------------------------
 * - Use BFS to ensure top-to-bottom order
 * - Maintain a TreeMap<HD, List<Nodes>>
 * - TreeMap keeps keys sorted automatically
 *
 * ---------------------------------------------------------
 * Time Complexity: O(n log n)
 * Space Complexity: O(n)
 */

public class VerticalOrderTraversal {

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
     * Performs Vertical Order Traversal
     *
     * @param root root of the binary tree
     * @return list of vertical levels
     */
    public static List<List<Integer>> verticalOrder(Node root) {

        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        // TreeMap keeps HDs sorted
        Map<Integer, List<Integer>> map = new TreeMap<>();

        // Queue for BFS
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(root, 0));

        while (!queue.isEmpty()) {

            Pair curr = queue.poll();
            Node node = curr.node;
            int hd = curr.hd;

            map.putIfAbsent(hd, new ArrayList<>());
            map.get(hd).add(node.data);

            if (node.left != null) {
                queue.add(new Pair(node.left, hd - 1));
            }

            if (node.right != null) {
                queue.add(new Pair(node.right, hd + 1));
            }
        }

        // Collect results from TreeMap
        for (List<Integer> vertical : map.values()) {
            result.add(vertical);
        }

        return result;
    }

    public static void main(String[] args) {

        System.out.println("=== Vertical Order Traversal of Binary Tree ===\n");

        /*
                       1
                     /   \
                    2     3
                   / \   / \
                  4   5 6   7
                       \
                        8
         */

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.right.left.right = new Node(8);

        List<List<Integer>> vertical = verticalOrder(root);

        System.out.println("Vertical Order Traversal:");
        System.out.println(vertical);

        System.out.println("\n" + "=".repeat(60));
        System.out.println("STEP-BY-STEP LOGIC");
        System.out.println("-".repeat(60));
        System.out.println("✔ Assign horizontal distance (HD) to each node");
        System.out.println("✔ Use BFS to maintain top-to-bottom order");
        System.out.println("✔ TreeMap ensures left-to-right vertical order");

        System.out.println("\n" + "=".repeat(60));
        System.out.println("COMPLEXITY ANALYSIS");
        System.out.println("-".repeat(60));
        System.out.println("Time Complexity  : O(n log n)");
        System.out.println("Space Complexity : O(n)");

        System.out.println("\nNOTE:");
        System.out.println("✔ Different from Top / Bottom View");
        System.out.println("✔ BFS is important to preserve vertical order");
        System.out.println("✔ Very common interview problem");
    }
}
