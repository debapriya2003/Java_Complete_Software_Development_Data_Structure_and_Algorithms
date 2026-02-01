package binary_trees.hard;

import java.util.*;

/**
 * Print All Nodes at Distance K from a Given Target Node in a Binary Tree
 *
 * ---------------------------------------------------------
 * Problem:
 * ---------------------------------------------------------
 * Given:
 * - Root of a Binary Tree
 * - A target node value
 * - An integer K
 *
 * Print all nodes that are at distance K from the target node.
 *
 * ---------------------------------------------------------
 * Distance Definition:
 * ---------------------------------------------------------
 * Distance between two nodes = number of edges between them
 *
 * Nodes at distance K can be:
 * - In the subtree of the target (downward)
 * - In the ancestors of the target (upward)
 * - In the sibling subtrees via ancestors
 *
 * ---------------------------------------------------------
 * Key Challenge:
 * ---------------------------------------------------------
 * Binary Tree nodes do NOT have parent pointers.
 * So we must somehow move:
 * - Downwards (easy)
 * - Upwards (needs parent tracking)
 *
 * ---------------------------------------------------------
 * Approach: BFS using Parent Mapping (Optimal) ✅
 * ---------------------------------------------------------
 * Step 1: Build Parent Map
 * - Traverse the tree and store parent of each node
 *
 * Step 2: Locate the Target Node
 *
 * Step 3: BFS from Target Node
 * - Move to:
 *      left child
 *      right child
 *      parent
 * - Keep track of visited nodes to avoid loops
 * - When distance == K → collect nodes
 *
 * ---------------------------------------------------------
 * Example:
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
 * Target = 2, K = 2
 *
 * Nodes at distance 2: [7, 3]
 *
 * ---------------------------------------------------------
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

public class NodesAtDistanceK {

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
     * Prints all nodes at distance K from target value
     */
    public static List<Integer> distanceK(Node root, int target, int k) {

        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        // Step 1: Map each node to its parent
        Map<Node, Node> parentMap = new HashMap<>();
        Node targetNode = buildParentMap(root, parentMap, target);

        if (targetNode == null) return result;

        // Step 2: BFS from target node
        Queue<Node> queue = new LinkedList<>();
        Set<Node> visited = new HashSet<>();

        queue.add(targetNode);
        visited.add(targetNode);

        int currentDistance = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();

            if (currentDistance == k) {
                // All nodes currently in queue are at distance K
                for (Node node : queue) {
                    result.add(node.data);
                }
                return result;
            }

            for (int i = 0; i < size; i++) {

                Node curr = queue.poll();

                // Visit left child
                if (curr.left != null && !visited.contains(curr.left)) {
                    visited.add(curr.left);
                    queue.add(curr.left);
                }

                // Visit right child
                if (curr.right != null && !visited.contains(curr.right)) {
                    visited.add(curr.right);
                    queue.add(curr.right);
                }

                // Visit parent
                Node parent = parentMap.get(curr);
                if (parent != null && !visited.contains(parent)) {
                    visited.add(parent);
                    queue.add(parent);
                }
            }

            currentDistance++;
        }

        return result;
    }

    /**
     * Builds parent map and finds target node
     */
    private static Node buildParentMap(Node root,
                                       Map<Node, Node> parentMap,
                                       int target) {

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        Node targetNode = null;

        while (!queue.isEmpty()) {

            Node curr = queue.poll();

            if (curr.data == target) {
                targetNode = curr;
            }

            if (curr.left != null) {
                parentMap.put(curr.left, curr);
                queue.add(curr.left);
            }

            if (curr.right != null) {
                parentMap.put(curr.right, curr);
                queue.add(curr.right);
            }
        }

        return targetNode;
    }

    public static void main(String[] args) {

        System.out.println("=== Nodes at Distance K in Binary Tree ===\n");

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

        int target = 2;
        int k = 2;

        List<Integer> nodes = distanceK(root, target, k);

        System.out.println("Target Node: " + target);
        System.out.println("Distance K : " + k);
        System.out.println("Nodes at Distance K: " + nodes);

        System.out.println("\n" + "=".repeat(60));
        System.out.println("KEY POINTS");
        System.out.println("-".repeat(60));
        System.out.println("✔ Parent mapping enables upward traversal");
        System.out.println("✔ BFS ensures correct distance tracking");
        System.out.println("✔ Visited set avoids infinite loops");

        System.out.println("\n" + "=".repeat(60));
        System.out.println("COMPLEXITY ANALYSIS");
        System.out.println("-".repeat(60));
        System.out.println("Time Complexity  : O(n)");
        System.out.println("Space Complexity : O(n)");

        System.out.println("\nNOTE:");
        System.out.println("✔ Very popular interview problem");
        System.out.println("✔ Builds foundation for burning tree problem");
        System.out.println("✔ Works for any binary tree (not BST)");
    }
}
