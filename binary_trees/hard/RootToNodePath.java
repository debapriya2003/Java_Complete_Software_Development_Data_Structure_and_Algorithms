package binary_trees.hard;

import java.util.*;

/**
 * Root to Node Path in a Binary Tree
 *
 * ---------------------------------------------------------
 * Problem:
 * ---------------------------------------------------------
 * Given the root of a Binary Tree and a target node value,
 * find the path from the ROOT to that target node.
 *
 * If the node exists:
 * - Return the path as a list (root → target)
 *
 * If the node does NOT exist:
 * - Return an empty list
 *
 * ---------------------------------------------------------
 * Key Idea:
 * ---------------------------------------------------------
 * Use DFS (Depth First Search).
 *
 * - Start from root
 * - Add current node to path
 * - If current node == target → SUCCESS
 * - Else search left and right subtrees
 * - If neither subtree contains target → BACKTRACK
 *
 * Backtracking is the core concept here.
 *
 * ---------------------------------------------------------
 * Example Binary Tree:
 * ---------------------------------------------------------
 *
 *                 1
 *               /   \
 *              2     3
 *             / \     \
 *            4   5     6
 *               /
 *              7
 *
 * Target = 7
 *
 * Path = [1, 2, 5, 7]
 *
 * ---------------------------------------------------------
 * Time Complexity: O(n)
 * Space Complexity: O(h)
 * (h = height of tree, due to recursion stack)
 */

public class RootToNodePath {

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
     * Finds path from root to given target node
     *
     * @param root   root of the binary tree
     * @param target target node value
     * @return list representing root-to-node path
     */
    public static List<Integer> findPath(Node root, int target) {

        List<Integer> path = new ArrayList<>();
        dfs(root, target, path);
        return path;
    }

    /**
     * Helper DFS method with backtracking
     *
     * @param node   current node
     * @param target target value
     * @param path   current path from root
     * @return true if target found in subtree
     */
    private static boolean dfs(Node node, int target, List<Integer> path) {

        // Base case: empty node
        if (node == null) {
            return false;
        }

        // Add current node to path
        path.add(node.data);

        // If target is found
        if (node.data == target) {
            return true;
        }

        // Search left or right subtree
        if (dfs(node.left, target, path) ||
            dfs(node.right, target, path)) {
            return true;
        }

        // Target not found → backtrack
        path.remove(path.size() - 1);
        return false;
    }

    public static void main(String[] args) {

        System.out.println("=== Root to Node Path in Binary Tree ===\n");

        /*
                 1
               /   \
              2     3
             / \     \
            4   5     6
               /
              7
         */

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.left.right.left = new Node(7);
        root.right.right = new Node(6);

        int target = 7;

        List<Integer> path = findPath(root, target);

        if (!path.isEmpty()) {
            System.out.println("Path from Root to " + target + ": " + path);
        } else {
            System.out.println("Target node not found in tree.");
        }

        System.out.println("\n" + "=".repeat(60));
        System.out.println("STEP-BY-STEP LOGIC");
        System.out.println("-".repeat(60));
        System.out.println("✔ DFS traversal from root");
        System.out.println("✔ Add node to path when visited");
        System.out.println("✔ Remove node when backtracking");
        System.out.println("✔ Stop recursion when target is found");

        System.out.println("\n" + "=".repeat(60));
        System.out.println("COMPLEXITY ANALYSIS");
        System.out.println("-".repeat(60));
        System.out.println("Time Complexity  : O(n)");
        System.out.println("Space Complexity : O(h)");

        System.out.println("\nNOTE:");
        System.out.println("✔ Backtracking is the key concept");
        System.out.println("✔ Useful for LCA, distance between nodes");
        System.out.println("✔ Very common interview problem");
    }
}
