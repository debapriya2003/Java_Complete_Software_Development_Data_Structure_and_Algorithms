package binary_trees.hard;

import java.util.*;

/**
 * Serialize and Deserialize a Binary Tree
 *
 * ---------------------------------------------------------
 * Problem:
 * ---------------------------------------------------------
 * Design algorithms to:
 * 1. SERIALIZE a Binary Tree into a string
 * 2. DESERIALIZE the string back into the original Binary Tree
 *
 * ---------------------------------------------------------
 * Definitions:
 * ---------------------------------------------------------
 * Serialization   → Convert tree into a string representation
 * Deserialization → Reconstruct tree from the string
 *
 * ---------------------------------------------------------
 * Why this is Important:
 * ---------------------------------------------------------
 * ✔ Used in data storage & transmission
 * ✔ Asked frequently in interviews (LeetCode / FAANG)
 * ✔ Foundation for tree persistence problems
 *
 * ---------------------------------------------------------
 * Approach Used Here: Level Order (BFS) Serialization ✅
 * ---------------------------------------------------------
 * - Use level order traversal
 * - Use a special marker (e.g. "#") for NULL nodes
 * - Store values separated by commas
 *
 * ---------------------------------------------------------
 * Why Level Order?
 * ---------------------------------------------------------
 * ✔ Simple to implement
 * ✔ Easy to reconstruct structure
 * ✔ Avoids recursion complexity
 *
 * ---------------------------------------------------------
 * Example Binary Tree:
 * ---------------------------------------------------------
 *
 *                 1
 *               /   \
 *              2     3
 *                   / \
 *                  4   5
 *
 * Serialized String:
 * "1,2,3,#,#,4,5,#,#,#,#"
 *
 * ---------------------------------------------------------
 * Time Complexity:
 * ---------------------------------------------------------
 * Serialization   : O(n)
 * Deserialization : O(n)
 *
 * Space Complexity:
 * ---------------------------------------------------------
 * O(n)
 */

public class SerializeDeserializeBinaryTree {

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

    private static final String NULL = "#";
    private static final String SEP = ",";

    /**
     * Serializes a binary tree to a string
     *
     * @param root root of the tree
     * @return serialized string
     */
    public static String serialize(Node root) {

        if (root == null) return "";

        StringBuilder sb = new StringBuilder();
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {

            Node curr = queue.poll();

            if (curr == null) {
                sb.append(NULL).append(SEP);
                continue;
            }

            sb.append(curr.data).append(SEP);
            queue.add(curr.left);
            queue.add(curr.right);
        }

        return sb.toString();
    }

    /**
     * Deserializes a string to a binary tree
     *
     * @param data serialized tree string
     * @return root of reconstructed tree
     */
    public static Node deserialize(String data) {

        if (data == null || data.isEmpty()) return null;

        String[] values = data.split(SEP);
        Queue<Node> queue = new LinkedList<>();

        Node root = new Node(Integer.parseInt(values[0]));
        queue.add(root);

        int index = 1;

        while (!queue.isEmpty()) {

            Node curr = queue.poll();

            // Left child
            if (!values[index].equals(NULL)) {
                curr.left = new Node(Integer.parseInt(values[index]));
                queue.add(curr.left);
            }
            index++;

            // Right child
            if (!values[index].equals(NULL)) {
                curr.right = new Node(Integer.parseInt(values[index]));
                queue.add(curr.right);
            }
            index++;
        }

        return root;
    }

    /**
     * Inorder traversal for verification
     */
    public static void inorder(Node root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    public static void main(String[] args) {

        System.out.println("=== Serialize & Deserialize Binary Tree ===\n");

        /*
                 1
               /   \
              2     3
                   / \
                  4   5
         */

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.right.left = new Node(4);
        root.right.right = new Node(5);

        // Serialize
        String serialized = serialize(root);
        System.out.println("Serialized Tree:");
        System.out.println(serialized);

        // Deserialize
        Node deserializedRoot = deserialize(serialized);

        System.out.print("\nInorder of Deserialized Tree: ");
        inorder(deserializedRoot);

        System.out.println("\n\n" + "=".repeat(60));
        System.out.println("KEY POINTS");
        System.out.println("-".repeat(60));
        System.out.println("✔ NULL markers preserve tree structure");
        System.out.println("✔ Level order makes reconstruction easy");
        System.out.println("✔ Serialization + Deserialization are inverses");

        System.out.println("\n" + "=".repeat(60));
        System.out.println("COMPLEXITY ANALYSIS");
        System.out.println("-".repeat(60));
        System.out.println("Time Complexity  : O(n)");
        System.out.println("Space Complexity : O(n)");

        System.out.println("\nNOTE:");
        System.out.println("✔ Preorder-based serialization is also possible");
        System.out.println("✔ BFS approach is easiest to debug");
        System.out.println("✔ Always include NULL markers");
    }
}
