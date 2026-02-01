package binary_trees.traversals;

import java.util.*;

/**
 * Introduction to Trees (Data Structure)
 *
 * ---------------------------------------------------------
 * What is a Tree?
 * ---------------------------------------------------------
 * A tree is a non-linear hierarchical data structure that consists of:
 * - Nodes (data elements)
 * - Edges (connections between nodes)
 *
 * A tree represents relationships where each element (except the root)
 * has exactly one parent.
 *
 * ---------------------------------------------------------
 * Key Terminologies:
 * ---------------------------------------------------------
 * Root:
 * - The topmost node of the tree
 *
 * Parent:
 * - A node that has one or more children
 *
 * Child:
 * - A node directly connected below another node
 *
 * Leaf Node:
 * - A node with no children
 *
 * Edge:
 * - Connection between two nodes
 *
 * Height of Tree:
 * - Maximum number of edges from root to a leaf
 *
 * Depth of Node:
 * - Number of edges from root to that node
 *
 * Level:
 * - Root is at level 0, its children at level 1, and so on
 *
 * ---------------------------------------------------------
 * Properties of Trees:
 * ---------------------------------------------------------
 * - No cycles (acyclic)
 * - Connected graph
 * - For n nodes, there are exactly (n - 1) edges
 *
 * ---------------------------------------------------------
 * Types of Trees:
 * ---------------------------------------------------------
 * 1. Binary Tree
 * 2. Binary Search Tree (BST)
 * 3. Full Binary Tree
 * 4. Complete Binary Tree
 * 5. Perfect Binary Tree
 * 6. Balanced Binary Tree
 *
 * ---------------------------------------------------------
 * Binary Tree:
 * ---------------------------------------------------------
 * - Each node has at most 2 children
 * - Left child and Right child
 *
 * ---------------------------------------------------------
 * Why Trees?
 * ---------------------------------------------------------
 * - Efficient searching and sorting
 * - Hierarchical data representation
 * - Used in databases, file systems, compilers, AI, etc.
 *
 * ---------------------------------------------------------
 * Time Complexity (General):
 * ---------------------------------------------------------
 * Traversal: O(n)
 * Search (Balanced): O(log n)
 * Search (Skewed): O(n)
 */

public class IntroductionToTrees {

    /**
     * Node structure for a Binary Tree
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
     * Inorder Traversal (Left → Root → Right)
     */
    public static void inorder(Node root) {
        if (root == null) return;

        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    /**
     * Preorder Traversal (Root → Left → Right)
     */
    public static void preorder(Node root) {
        if (root == null) return;

        System.out.print(root.data + " ");
        preorder(root.left);
        preorder(root.right);
    }

    /**
     * Postorder Traversal (Left → Right → Root)
     */
    public static void postorder(Node root) {
        if (root == null) return;

        postorder(root.left);
        postorder(root.right);
        System.out.print(root.data + " ");
    }

    /**
     * Level Order Traversal (Breadth First Search)
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

    public static void main(String[] args) {

        System.out.println("=== Introduction to Trees ===\n");

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

        System.out.println("Tree Created Successfully!\n");

        System.out.print("Inorder Traversal   : ");
        inorder(root);

        System.out.print("\nPreorder Traversal  : ");
        preorder(root);

        System.out.print("\nPostorder Traversal : ");
        postorder(root);

        System.out.print("\nLevel Order         : ");
        levelOrder(root);

        System.out.println("\n\n" + "=".repeat(60));
        System.out.println("TRAVERSAL EXPLANATION");
        System.out.println("-".repeat(60));

        System.out.println("Inorder   → Left, Root, Right");
        System.out.println("Preorder  → Root, Left, Right");
        System.out.println("Postorder → Left, Right, Root");
        System.out.println("LevelOrder→ Level by level");

        System.out.println("\n" + "=".repeat(60));
        System.out.println("APPLICATIONS OF TREES");
        System.out.println("-".repeat(60));
        System.out.println("✔ File systems");
        System.out.println("✔ Database indexing (B-Trees, B+ Trees)");
        System.out.println("✔ Expression trees");
        System.out.println("✔ Decision trees in ML");
        System.out.println("✔ DOM structure in HTML");

        System.out.println("\n" + "=".repeat(60));
        System.out.println("COMPLEXITY ANALYSIS");
        System.out.println("-".repeat(60));
        System.out.println("Traversal Time Complexity : O(n)");
        System.out.println("Traversal Space Complexity: O(h) (recursion stack)");
    }
}
