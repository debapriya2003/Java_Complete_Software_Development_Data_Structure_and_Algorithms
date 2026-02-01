package binary_trees.hard;

import java.util.*;

/**
 * Minimum Time Taken to BURN a Binary Tree from a Given Node
 *
 * ---------------------------------------------------------
 * Problem:
 * ---------------------------------------------------------
 * Given:
 * - Root of a Binary Tree
 * - A target node (fire starts here)
 *
 * Every second:
 * - Fire spreads to:
 *      1. Left child
 *      2. Right child
 *      3. Parent
 *
 * Find the MINIMUM TIME required to burn the ENTIRE tree.
 *
 * ---------------------------------------------------------
 * Key Observation:
 * ---------------------------------------------------------
 * This problem is an EXTENSION of:
 * 👉 "Nodes at Distance K from a Target Node"
 *
 * The tree behaves like an UNDIRECTED GRAPH once parents
 * are considered.
 *
 * ---------------------------------------------------------
 * Approach: BFS with Parent Mapping (Optimal) ✅
 * ---------------------------------------------------------
 * Step 1: Build Parent Map
 * - Traverse tree and store parent of each node
 * - Identify the target node
 *
 * Step 2: Multi-directional BFS from Target Node
 * - Move to:
 *      left child
 *      right child
 *      parent
 * - Each BFS level = 1 second
 * - Count number of levels until tree is fully burned
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
 * Fire starts at node = 5
 *
 * Time:
 * t=0 → 5
 * t=1 → 7, 2
 * t=2 → 4, 1
 * t=3 → 3
 * t=4 → 6
 *
 * Minimum Time = 4
 *
 * ---------------------------------------------------------
 * Time Complexity: O(n)
 * Space Complexity: O(n)
 */

public class BurnBinaryTree {

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
     * Returns minimum time to burn the entire binary tree
     *
     * @param root   root of the binary tree
     * @param target value where fire starts
     * @return minimum time in seconds
     */
    public static int minTimeToBurn(Node root, int target) {

        if (root == null) return 0;

        // Step 1: Build parent map and find target node
        Map<Node, Node> parentMap = new HashMap<>();
        Node targetNode = buildParentMap(root, parentMap, target);

        // Step 2: BFS from target node
        Queue<Node> queue = new LinkedList<>();
        Set<Node> visited = new HashSet<>();

        queue.add(targetNode);
        visited.add(targetNode);

        int time = 0;

        while (!queue.isEmpty()) {

            int size = queue.size();
            boolean burnedNewNode = false;

            for (int i = 0; i < size; i++) {

                Node curr = queue.poll();

                // Burn left child
                if (curr.left != null && !visited.contains(curr.left)) {
                    visited.add(curr.left);
                    queue.add(curr.left);
                    burnedNewNode = true;
                }

                // Burn right child
                if (curr.right != null && !visited.contains(curr.right)) {
                    visited.add(curr.right);
                    queue.add(curr.right);
                    burnedNewNode = true;
                }

                // Burn parent
                Node parent = parentMap.get(curr);
                if (parent != null && !visited.contains(parent)) {
                    visited.add(parent);
                    queue.add(parent);
                    burnedNewNode = true;
                }
            }

            // Increase time only if fire spreads further
            if (burnedNewNode) {
                time++;
            }
        }

        return time;
    }

    /**
     * Builds parent mapping and finds the target node
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

        System.out.println("=== Minimum Time to Burn Binary Tree ===\n");

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

        int target = 5;

        int time = minTimeToBurn(root, target);

        System.out.println("Fire starts at node: " + target);
        System.out.println("Minimum time to burn tree: " + time + " seconds");

        System.out.println("\n" + "=".repeat(60));
        System.out.println("KEY INSIGHTS");
        System.out.println("-".repeat(60));
        System.out.println("✔ Tree is treated as an undirected graph");
        System.out.println("✔ Parent mapping enables upward fire spread");
        System.out.println("✔ BFS level count = time");

        System.out.println("\n" + "=".repeat(60));
        System.out.println("COMPLEXITY ANALYSIS");
        System.out.println("-".repeat(60));
        System.out.println("Time Complexity  : O(n)");
        System.out.println("Space Complexity : O(n)");

        System.out.println("\nNOTE:");
        System.out.println("✔ Direct application of Nodes-at-distance-K logic");
        System.out.println("✔ Very popular interview problem");
        System.out.println("✔ Fire-spread simulation using BFS");
    }
}
